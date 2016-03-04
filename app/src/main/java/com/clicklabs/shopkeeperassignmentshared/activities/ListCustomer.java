package com.clicklabs.shopkeeperassignmentshared.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.clicklabs.shopkeeperassignmentshared.Adapter.CustomerAdapter;
import com.clicklabs.shopkeeperassignmentshared.Models.shopkeeper.GetCustomer.CustomerData;
import com.clicklabs.shopkeeperassignmentshared.Models.shopkeeper.GetCustomer.Customers;
import com.clicklabs.shopkeeperassignmentshared.R;
import com.clicklabs.shopkeeperassignmentshared.retrofit.RestClient;
import com.clicklabs.shopkeeperassignmentshared.utils.CommonData;
import com.clicklabs.shopkeeperassignmentshared.utils.AppConstants;
import com.clicklabs.shopkeeperassignmentshared.utils.CommonProgress;
import com.clicklabs.shopkeeperassignmentshared.utils.RetrofitErrorHandler;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Krishika on 12-02-2016.
 */
public class ListCustomer extends BaseActivity implements View.OnClickListener {
    RecyclerView recyclerView;
    CustomerAdapter adapter;
    Button addBtn, logOut;
    String accessToken;
    ImageButton back;
    TextView toolBar;
    Intent intent;
    private List<CustomerData> dataList = new ArrayList<>();


    private void init() {
        addBtn = (Button) findViewById(R.id.btn_add_new);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        back = (ImageButton) findViewById(R.id.img);
        back.setVisibility(View.GONE);
        logOut = (Button) findViewById(R.id.btn_log_out);
        toolBar = (TextView) findViewById(R.id.action);
        toolBar.setText("Customers List");
        if (CommonData.getCustomers(getApplicationContext()) != null) {
            dataList = CommonData.getCustomers(getApplicationContext()).getData();
        }
        adapter = new CustomerAdapter(this, dataList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(adapter);




    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_recycler);


        init();
        putData();
        logOut.setOnClickListener(this);
        addBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add_new:
                intent = new Intent(getApplicationContext(), AddNew.class);
                startActivityForResult(intent, AppConstants.requestcode);
                break;
            case R.id.btn_log_out:
                CommonData.clearAllAppData(getApplicationContext());
                intent = new Intent(ListCustomer.this, Welcome.class);
                startActivity(intent);
                finish();
                break;
        }

    }

    public void putData() {
        accessToken = CommonData.getShopkeeperDetails(getApplicationContext()).getData().get(0).getAccessToken();
//        Log.v("hello", accessToken);
        if (CommonData.getCustomers(getApplicationContext()) == null || CommonData.getCustomers(getApplicationContext()).getData().size() == 0)
            CommonProgress.showProgressDialog(ListCustomer.this, "Loading Customers");


        RestClient.getApiService().getCustomer("bearer " + accessToken, new Callback<Customers>() {
            @Override
            public void success(Customers customers, Response response) {
                CommonData.setCustomers(customers,getApplicationContext());
                CommonProgress.dismissProgressDialog();
                adapter.resetData(customers.getData());




            }

            @Override
            public void failure(RetrofitError error) {
                Log.v("Display", error.toString());
                CommonProgress.dismissProgressDialog();
                RetrofitErrorHandler.checkCode(ListCustomer.this, error);


            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == AppConstants.requestcode) {
            if (resultCode == RESULT_OK) {
                putData();
            }
        }
    }
}
