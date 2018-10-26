package com.jonasvieira.iddog.Server;

import com.jonasvieira.iddog.Data.Login.Login;
import com.jonasvieira.iddog.Data.Requests.ModelRequestLogin;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface IDogServer {

    @POST("signup")
    @Headers({"Content-Type:application/json"})
    Call<Login> requestLogin(
            @Body ModelRequestLogin body);
}
