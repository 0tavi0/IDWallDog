package com.jonasvieira.iddog.Server;

import android.content.Context;

import com.jonasvieira.iddog.Data.Login;
import com.jonasvieira.iddog.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DogServer {

    private static IDogServer iDogServer;
    private static DogServer dogServer;

    private DogServer(Context context) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(context.getString(R.string.base_url))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        iDogServer = retrofit.create(IDogServer.class);
    }

    public static DogServer getInstance(Context context) {
        if (dogServer == null) {
            dogServer = new DogServer(context);
        }
        return dogServer;
    }

    public void requestLogin(String email, Callback<List<Login>> callback) {
        Call<List<Login>> loginCall = iDogServer.requestLogin(email);
        loginCall.enqueue(callback);
    }
}
