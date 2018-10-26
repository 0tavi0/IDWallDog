package com.jonasvieira.iddog.Server;

import com.jonasvieira.iddog.Data.Login;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface IDogServer {

    @FormUrlEncoded
    @POST("signup")
    @Headers("Content-Type:application/json")
    Call<List<Login>> requestLogin(@Field("email") String email);
}
