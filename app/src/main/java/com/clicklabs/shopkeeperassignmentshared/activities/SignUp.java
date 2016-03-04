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

public class SignUp extends BaseActivity implements View.OnClickListener{
    Button signUp,logOut;
    Intent intent;
    TextView toolbar;
    EditText etName,etEmail,etMobile,etPassword;
    String name,email,mobile,password;
    ImageButton back;



    public void init()
    {
        signUp=(Button) findViewById(R.id.btn_reg_signup);
        etName=(EditText) findViewById(R.id.et_reg_name);
        etEmail=(EditText) findViewById(R.id.et_reg_mail);
        etMobile=(EditText) findViewById(R.id.et_reg_phone);
        etPassword=(EditText) findViewById(R.id.et_reg_pass);
        toolbar=(TextView)findViewById(R.id.action);
        toolbar.setText("Sign Up");
        back=(ImageButton)findViewById(R.id.img);
        back.setVisibility(View.GONE);
        logOut=(Button)findViewById(R.id.btn_log_out);
        logOut.setVisibility(View.GONE);

    }
    public void get()
    {
        name=etName.getText().toString();
        email=etEmail.getText().toString();
        mobile=etMobile.getText().toString();
        password=etPassword.getText().toString();

    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        init();
        signUp.setOnClickListener(this);
    }
    private boolean onValidate(){
        Boolean isValid=true;
        if(name.equals(null)||name.equals(""))
        {
            CommonAlert.alertDialogShow(SignUp.this,"Enter Name");
            return false;
        }
        if(mobile.length()!=10){
            CommonAlert.alertDialogShow(SignUp.this,"Enter valid Phone No");
            return false;
        }


        if (!(!TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()))
        {
            CommonAlert.alertDialogShow(SignUp.this,"Enter valid email");
            return false;
        }
        if(!(password!=null && password.length()>=6))
        {
            CommonAlert.alertDialogShow(SignUp.this,"Enter valid password");
            return false;
        }

        return isValid;

    }
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btn_reg_signup:
                get();
                if(onValidate())
                    putData();
                break;
        }
    }
    public void putData(){
        CommonProgress.showProgressDialog(SignUp.this,null);

            RestClient.getApiService().login(name, mobile, email, password, new Callback<ShopkeeperDetails>() {
            @Override
            public void success(ShopkeeperDetails shopkeeperDetails, Response response) {

                    CommonData.setShopkeeperDetails(shopkeeperDetails,getApplicationContext());
                Log.v("successful", shopkeeperDetails.getData().get(0).getAccessToken());
                CommonAlert.alertDialogShow(SignUp.this, "Account Created", new CommonAlert.OnAlertOkClickListener() {
                    @Override
                    public void onOkButtonClicked() {
                        intent = new Intent(getApplicationContext(), ListCustomer.class);
                        startActivity(intent);
                        CommonProgress.dismissProgressDialog();

                    }
                });


            }

            @Override
            public void failure(RetrofitError error) {
                Log.i("signup", "Error, body: " + error.getLocalizedMessage() + "ghj");
                CommonProgress.dismissProgressDialog();
                RetrofitErrorHandler.checkCode(SignUp.this, error);
            }
        });
    }


}
