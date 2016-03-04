package com.clicklabs.shopkeeperassignmentshared.retrofit;



import com.clicklabs.shopkeeperassignmentshared.Models.shopkeeper.GetCustomer.Customers;
import com.clicklabs.shopkeeperassignmentshared.Models.shopkeeper.Place.MapsPractice;
import com.clicklabs.shopkeeperassignmentshared.Models.shopkeeper.registerCustomer.RegCustomer;
import com.clicklabs.shopkeeperassignmentshared.Models.shopkeeper.shopkeeperDetails.ShopkeeperDetails;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * Created by Krishika on 09-02-2016.
 */
public interface ApiService {
    @FormUrlEncoded
    @POST("/api/admin/createSupplier")
    public void login(@Field("name") String name,@Field("phoneNo") String mobile, @Field("email") String email,@Field("password") String password, Callback<ShopkeeperDetails> callback);

    @FormUrlEncoded
    @POST("/api/admin/registerDriver")
    public void register(@Header("authorization") String accessToken,@Field("name") String name,@Field("phoneNo") String mobile, @Field("email") String email, @Field("address") String address, Callback<RegCustomer> callback);

    @GET("/api/admin/getAlldriver")
    public void getCustomer(@Header("authorization") String accessToken, Callback<Customers> callback);

    @FormUrlEncoded
    @POST("/api/admin/supplierLogin")
    public void signIn(@Field("email") String email, @Field("password") String password, Callback<ShopkeeperDetails> callback);

    @POST("/place/autocomplete/json")
    void placeAuto(@Query("input") String input,@Query("key") String key, Callback<MapsPractice> callback);








}
