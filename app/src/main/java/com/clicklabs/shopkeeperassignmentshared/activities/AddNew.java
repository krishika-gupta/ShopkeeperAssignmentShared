package com.clicklabs.shopkeeperassignmentshared.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.clicklabs.shopkeeperassignmentshared.Models.shopkeeper.Place.MapsPractice;
import com.clicklabs.shopkeeperassignmentshared.Models.shopkeeper.registerCustomer.RegCustomer;
import com.clicklabs.shopkeeperassignmentshared.R;
import com.clicklabs.shopkeeperassignmentshared.retrofit.RestClient;
import com.clicklabs.shopkeeperassignmentshared.utils.AppConstants;
import com.clicklabs.shopkeeperassignmentshared.utils.CommonAlert;
import com.clicklabs.shopkeeperassignmentshared.utils.CommonData;
import com.clicklabs.shopkeeperassignmentshared.utils.CommonProgress;
import com.clicklabs.shopkeeperassignmentshared.utils.RetrofitErrorHandler;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class AddNew extends BaseActivity implements View.OnClickListener {
    Button btnAdd, logOut;
    ImageButton back;
    Intent intent;
    TextView toolbar;
    EditText etName, etEmail, etMobile;
    AutoCompleteTextView etAddress;
    String name, email, mobile, address;
    private ArrayList<String> list=new ArrayList<>();
    private TextWatcher textWatcher;
    private ArrayAdapter<String> adapter;



    public void init() {
        etName = (EditText) findViewById(R.id.et_new_name);
        etMobile = (EditText) findViewById(R.id.et_new_phone);
        etAddress = (AutoCompleteTextView) findViewById(R.id.et_new_add);
        textWatcherbtn();
        etAddress.addTextChangedListener(textWatcher);
        setAdapter();
        etEmail = (EditText) findViewById(R.id.et_new_mail);
        btnAdd = (Button) findViewById(R.id.btn_add);
        back = (ImageButton) findViewById(R.id.img);
        toolbar = (TextView) findViewById(R.id.action);
        toolbar.setText("New Customer");
        logOut = (Button) findViewById(R.id.btn_log_out);
        logOut.setVisibility(View.GONE);

    }

    private void textWatcherbtn() {
        textWatcher=new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                callbackAddress();

            }

            @Override
            public void afterTextChanged(final Editable s) {
                Log.v("Success", s.toString());



            }
        };
    }

    public void get() {
        name = etName.getText().toString();
        mobile = etMobile.getText().toString();
        email = etEmail.getText().toString();
        address = etAddress.getText().toString();
    }
    private void setAdapter() {

        adapter = new ArrayAdapter<>(AddNew.this, R.layout.text_layout, list);
        etAddress.setAdapter(adapter);

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new);
        init();

        btnAdd.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    private boolean onValidate() {
        Boolean isValid = true;
        if(name.equals(null)||name.equals("")) {
            CommonAlert.alertDialogShow(AddNew.this, "Enter Name");
            return  false;
        }
        if (mobile.length() != 10) {
            CommonAlert.alertDialogShow(AddNew.this, "Enter valid Phone No");
            return false;
        }
        if (!(!TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches())) {
            CommonAlert.alertDialogShow(AddNew.this, "Enter valid Email");
            return false;
        }
        if(address.equals(null)||address.equals("")) {
            CommonAlert.alertDialogShow(AddNew.this, "Enter address");
            return false;
        }

        return isValid;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add:
                get();
                if (onValidate())
                    putdata();
                break;
            case R.id.img:
                onBackPressed();
                finish();
        }
    }

    public void putdata() {
        String accessToken = "bearer " + CommonData.getShopkeeperDetails(getApplicationContext()).getData().get(0).getAccessToken();

        CommonProgress.showProgressDialog(AddNew.this, null);
        Log.i("accessToken", accessToken);
        RestClient.getApiService().register(accessToken, name, mobile, email, address, new Callback<RegCustomer>() {
            @Override
            public void success(RegCustomer regCustomer, Response response) {
                CommonProgress.dismissProgressDialog();
                CommonAlert.alertDialogShow(AddNew.this, "Customer added successfully", new CommonAlert.OnAlertOkClickListener() {


                    @Override
                    public void onOkButtonClicked() {
                        intent = new Intent(AddNew.this, ListCustomer.class);
                        setResult(RESULT_OK, intent);
                        finish();


                    }
                });
            }

                @Override
            public void failure(RetrofitError error) {
                Log.i("register", "Error, body: " + error.getLocalizedMessage() + "ghj");
                CommonProgress.dismissProgressDialog();
                RetrofitErrorHandler.checkCode(AddNew.this, error);
            }
        });

    }
    private void callbackAddress(){
        RestClient.getApiService2().placeAuto(etAddress.getText().toString(), AppConstants.Google_places_Server_key, new Callback<MapsPractice>() {
            @Override
            public void success(MapsPractice mapsPractice, Response response) {
                CommonData.setMapsPractice(mapsPractice);
                createList(mapsPractice.getPredictions().size());
                adapter.clear();
                adapter.addAll(list);
                adapter.notifyDataSetChanged();
                Log.v("Success", mapsPractice.getStatus());

            }

            @Override
            public void failure(RetrofitError error) {
                Log.v("TAG", error.getLocalizedMessage());


            }
        });
    }
    private void createList(int size) {

        list.clear();
        Log.v("Success", "new list");
        for (int i = 0; i < size; i++) {
            Log.v("Success", CommonData.getMapsPractice().getPredictions().get(i).getDescription());
            list.add(CommonData.getMapsPractice().getPredictions().get(i).getDescription());
        }
    }


}
