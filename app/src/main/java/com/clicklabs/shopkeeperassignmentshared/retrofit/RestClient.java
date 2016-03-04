package com.clicklabs.shopkeeperassignmentshared.retrofit;

import com.clicklabs.shopkeeperassignmentshared.Config.config;

import retrofit.RestAdapter;

/**
 * Created by Krishika on 09-02-2016.
 */
public class RestClient {
    private static ApiService apiService = null;
    private static ApiService apiService2=null;

    public static ApiService getApiService() {
        if (apiService == null) {

            // For object response which is default
            RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(config.getBaseURL()).build();


//            //For type string response
//            RestAdapter restAdapter = new RestAdapter.Builder()
//                    .setEndpoint(Config.getBaseURL())
//                    .setConverter(new StringConverter())    //converter for response type
//                    .build();


            apiService = restAdapter.create(ApiService.class);
        }
        return apiService;
    }

    public static ApiService getApiService2() {
        if(apiService2==null)
        {
            RestAdapter restAdapter1=new RestAdapter.Builder().setEndpoint(config.getServerUrl()).build();
            apiService2=restAdapter1.create(ApiService.class);
        }
        return apiService2;
    }
}
