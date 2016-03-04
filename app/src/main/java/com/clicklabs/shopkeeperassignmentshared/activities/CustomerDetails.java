package com.clicklabs.shopkeeperassignmentshared.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.clicklabs.shopkeeperassignmentshared.Models.shopkeeper.GetCustomer.CustomerData;
import com.clicklabs.shopkeeperassignmentshared.R;

public class CustomerDetails extends BaseActivity {
    TextView tvCustomerName;
    TextView tvPhoneNo;
    TextView tvEmail;
    TextView tvAddress;
    TextView toolbar;
    Button logOut;


    public void init(){
        tvCustomerName= (TextView) findViewById(R.id.tv_Name);
        tvPhoneNo= (TextView) findViewById(R.id.tv_customerphone);
        tvEmail= (TextView) findViewById(R.id.tv_customerEmail);
        tvAddress= (TextView) findViewById(R.id.tv_address);
        toolbar=(TextView)findViewById(R.id.action);
        logOut=(Button)findViewById(R.id.btn_log_out);
        logOut.setVisibility(View.GONE);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_details);
        init();
        CustomerData objInfo=(CustomerData)getIntent().getSerializableExtra("DisplayDetails");
        tvCustomerName.setText(objInfo.getName());
        tvPhoneNo.setText(String.valueOf(objInfo.getPhoneNo()));
        tvEmail.setText(objInfo.getEmail());
        tvAddress.setText(objInfo.getAddress());

        toolbar.setText("Customer Details");
        ImageButton back=(ImageButton)findViewById(R.id.img);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });



    }
}
