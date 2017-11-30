package com.example.lexel.moneytracker.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;



public interface Api {



    @GET("items")
    Call<List<Item>> items(@Query("type") String type);

    @GET("auth")
    Call<AuthResult> auth(@Query("social_user_id") String SocialUserId);


    @GET("balance")
    Call<BalanceResult> balance();

    @POST ("itens/add")
    Call<AddResult> add(@Query("name") String name, @Query("price") int price, @Query("type") String type);

    @POST ("itens/remove")
    Call<Result> remove(@Query("id") int id);

}
