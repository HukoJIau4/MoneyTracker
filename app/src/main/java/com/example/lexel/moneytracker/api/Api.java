package com.example.lexel.moneytracker.api;

import com.example.lexel.moneytracker.AuthResult;
import com.example.lexel.moneytracker.Item;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;



public interface Api {



    @GET("items")
    Call<List<Item>> items(@Query("type") String type);

    @GET("auth")
    Call<AuthResult> auth(@Query("social_user_id") String SocialUserId);
}
