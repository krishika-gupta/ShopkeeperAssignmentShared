package com.clicklabs.shopkeeperassignmentshared.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.clicklabs.shopkeeperassignmentshared.R;

public class Welcome extends BaseActivity implements View.OnClickListener {
    Button btnSignUp;
    Button btnLogin;

    public void init(){
        btnSignUp=(Button)findViewById(R.id.btn_signup);
        btnLogin=(Button)findViewById(R.id.btn_login);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        init();
        btnSignUp.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn_signup:
                Intent intSignup=new Intent(Welcome.this,SignUp.class);
                startActivity(intSignup);
                finish();
                break;
            case R.id.btn_login:
                Intent intLogin=new Intent(Welcome.this,SignIn.class);
                startActivity(intLogin);
                finish();
                break;
        }
    }
}
