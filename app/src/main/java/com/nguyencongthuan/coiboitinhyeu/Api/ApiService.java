package com.nguyencongthuan.coiboitinhyeu.Api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nguyencongthuan.coiboitinhyeu.Model.User;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {
    Gson gson = new GsonBuilder().setDateFormat("dd-MM-yyyy HH:mm:ss")
            .create();
    ApiService apiService = new Retrofit.Builder()
            .baseUrl("http://192.168.1.7:32762/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);

    @GET("api/User/{username}")
    Call<User> getUser(@Path("username") String username);
}
