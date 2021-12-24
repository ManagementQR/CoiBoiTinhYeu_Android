package com.nguyencongthuan.coiboitinhyeu.Api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nguyencongthuan.coiboitinhyeu.Model.History;
import com.nguyencongthuan.coiboitinhyeu.Model.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {
    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    ApiService apiService = new Retrofit.Builder()
            .baseUrl("http://192.168.202.48:5000/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);

    //user
    @GET("api/User")
    Call<User> getUser(@Query("Username") String Username);

    @PATCH("api/User")
    Call<User> updateUser(@Body User user);

    @POST("api/User")
    Call<User> createUser(@Body User user);

    //history
    @GET("api/History")
    Call<ArrayList<History>> getHistory(@Query("username") String username);

    @POST("api/History")
    Call<History> createHistory(@Body History history);


}
