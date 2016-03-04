package com.clicklabs.shopkeeperassignmentshared.activities;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.clicklabs.shopkeeperassignmentshared.Models.shopkeeper.shopkeeperDetails.ShopkeeperDetails;
import com.clicklabs.shopkeeperassignmentshared.R;
import com.clicklabs.shopkeeperassignmentshared.retrofit.RestClient;
import com.clicklabs.shopkeeperassignmentshared.utils.CommonAlert;
import com.clicklabs.shopkeeperassignmentshared.utils.CommonData;
import com.clicklabs.shopkeeperassignmentshared.utils.CommonProgress;
import com.clicklabs.shopkeeperassignmentshared.utils.RetrofitErrorHandler;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class SignIn extends BaseActivity implements View.OnClickListener {

    EditText enteredEmailEt;
    EditText enteredPasswordEt;
    String supplierEmail;
    String supplierPassword;
    Button loginBtn,logOut;
    TextView toolbar;
    ImageButton back;



    private void init() {
        enteredEmailEt = (EditText) findViewById(R.id.et_email);
        enteredPasswordEt = (EditText) findViewById(R.id.et_password);
        loginBtn = (Button) findViewById(R.id.btn_signin);
        toolbar=(TextView)findViewById(R.id.action);
        toolbar.setText("Login");
        back=(ImageButton)findViewById(R.id.img);
        back.setVisibility(View.GONE);
        logOut=(Button)findViewById(R.id.btn_log_out);
        logOut.setVisibility(View.GONE);


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        init();

        loginBtn.setOnClickListener(this);

    }

    private boolean onValidate(){
        Boolean isValid=true;
        if (!(!TextUtils.isEmpty(supplierEmail) && android.util.Patterns.EMAIL_ADDRESS.matcher(supplierEmail).matches()))
        {
            CommonAlert.alertDialogShow(SignIn.this,"Enter valid email");
            return false;
        }
        if(!(supplierPassword!=null && supplierPassword.length()>=6))
        {
            CommonAlert.alertDialogShow(SignIn.this,"Enter valid password");
            return false;
        }

        return isValid;

    }

    @Override
    public void onClick(View v) {
        supplierEmail = enteredEmailEt.getText().toString();
        supplierPassword = enteredPasswordEt.getText().toString();
       if(onValidate())
           loginbtnClicked();
    }

    private void loginbtnClicked(){
        CommonProgress.showProgressDialog(SignIn.this,null);

        RestClient.getApiService().signIn(supplierEmail, supplierPassword, new Callback<ShopkeeperDetails>() {
            @Override
            public void success(ShopkeeperDetails shopkeeperDetails, Response response) {
                CommonData.setShopkeeperDetails(shopkeeperDetails,getApplicationContext());
                Toast.makeText(getApplicationContext(), "Logged in successfully", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), ListCustomer.class);
                startActivity(intent);
                CommonProgress.dismissProgressDialog();
                finish();


            }

            @Override
            public void failure(RetrofitError error) {
                Log.v("login failure", error.toString());
                CommonProgress.dismissProgressDialog();
                RetrofitErrorHandler.checkCode(SignIn.this, error);

            }
        });

    }
}
