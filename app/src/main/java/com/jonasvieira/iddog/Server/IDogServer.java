package com.jonasvieira.iddog.Server;

import com.jonasvieira.iddog.Data.Feed.Feed;
import com.jonasvieira.iddog.Data.Login.Login;
import com.jonasvieira.iddog.Data.Requests.ModelRequestLogin;
import com.jonasvieira.iddog.Utils.Constants;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface IDogServer {

    @POST(Constants.ENDPOINT_SIGNUP)
    @Headers({"Content-Type:application/json"})
    Call<Login> requestLogin(@Body ModelRequestLogin body);

    @GET(Constants.ENDPOINT_FEED)
    @Headers("Content-Type:application/json")
    Call<Feed> requestFeed(@Query("category") String breed, @Header("Authorization") String token);
}
