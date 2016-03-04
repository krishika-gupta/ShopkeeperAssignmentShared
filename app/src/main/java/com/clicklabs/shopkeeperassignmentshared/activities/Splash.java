package com.clicklabs.shopkeeperassignmentshared.activities;

import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;

import com.clicklabs.shopkeeperassignmentshared.R;
import com.clicklabs.shopkeeperassignmentshared.utils.CommonData;

public class Splash extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent;
                if (CommonData.getShopkeeperDetails(getApplicationContext()) == null) {
                    intent = new Intent(Splash.this, Welcome.class);
                }
                else{

                    intent = new Intent(Splash.this, ListCustomer.class);
                }


                startActivity(intent);
                finish();

            }
        }, 3000);
    }
}
