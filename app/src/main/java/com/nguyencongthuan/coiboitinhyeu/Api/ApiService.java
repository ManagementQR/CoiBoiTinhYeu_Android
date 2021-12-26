package com.nguyencongthuan.coiboitinhyeu.Api;

import com.google.api.ResourceDescriptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nguyencongthuan.coiboitinhyeu.Model.History;
import com.nguyencongthuan.coiboitinhyeu.Model.User;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ApiService {
    String url = "http://192.168.1.6:5000/";
    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    ApiService apiService = new Retrofit.Builder()
            .baseUrl(url)
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

    @Multipart
    @POST("api/User/image")
    Call<User> uploadImage(@Part("username") RequestBody username,
                           @Part MultipartBody.Part image);

    //history
    @GET("api/History")
    Call<ArrayList<History>> getHistory(@Query("username") String username);

    @POST("api/History")
    Call<History> createHistory(@Body History history);

    @DELETE("api/History")
    Call<History> delete(@Query("id") int id);


}
