package com.clicklabs.shopkeeperassignmentshared.utils;

import android.content.Context;

import com.clicklabs.shopkeeperassignmentshared.Models.shopkeeper.GetCustomer.Customers;
import com.clicklabs.shopkeeperassignmentshared.Models.shopkeeper.Place.MapsPractice;
import com.clicklabs.shopkeeperassignmentshared.Models.shopkeeper.shopkeeperDetails.ShopkeeperDetails;
import com.clicklabs.shopkeeperassignmentshared.sharedpreferences.Prefs;
import com.clicklabs.shopkeeperassignmentshared.sharedpreferences.SharedPreferencesName;

import io.paperdb.Paper;

/**
 * Created by Krishika on 11-02-2016.
 */
public class CommonData {
    private static ShopkeeperDetails shopkeeperDetail=null;
    private static Customers customer=null;
    public static MapsPractice mapsPractice;

    public static MapsPractice getMapsPractice() {
        return mapsPractice;
    }

    public static void setMapsPractice(MapsPractice mapsPractice) {
        CommonData.mapsPractice = mapsPractice;
    }

    public static ShopkeeperDetails getShopkeeperDetails(Context context) {
        if (shopkeeperDetail == null) {
            shopkeeperDetail = Prefs.with(context).getObject(SharedPreferencesName.APP_USER, ShopkeeperDetails.class);
        }
        return shopkeeperDetail;

    }

    public static void setShopkeeperDetails(ShopkeeperDetails shopkeeperDetails,Context context) {
        Prefs.with(context).save(SharedPreferencesName.APP_USER, shopkeeperDetails);
        shopkeeperDetail=shopkeeperDetails;
    }



    public static Customers getCustomers(Context context) {
        if (customer == null) {
            customer = Prefs.with(context).getObject(SharedPreferencesName.APP_CUSTOMER, Customers.class);
        }


        return customer;
    }

    public static void setCustomers(Customers customers,Context context) {
        Prefs.with(context).save(SharedPreferencesName.APP_CUSTOMER,customers);
        customer=customers;
    }
    public static void clearAllAppData(Context context) {

        shopkeeperDetail=null ;
        customer=null;
        Prefs.with(context).removeAll();
    }


}
