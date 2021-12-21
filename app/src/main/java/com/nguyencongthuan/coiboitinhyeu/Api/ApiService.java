package com.nguyencongthuan.coiboitinhyeu.Api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nguyencongthuan.coiboitinhyeu.Model.Category;
import com.nguyencongthuan.coiboitinhyeu.Model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    ApiService apiService = new Retrofit.Builder()
            .baseUrl("http://192.168.1.5:5000/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);

    @GET("api/User")
    Call<User> getUser(@Query("Username") String Username);

    @GET("api/category")
    Call<List<Category>> getCategory();
}
