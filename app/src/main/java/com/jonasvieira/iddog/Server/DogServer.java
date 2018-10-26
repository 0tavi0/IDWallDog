package com.jonasvieira.iddog.Server;

import android.content.Context;

import com.jonasvieira.iddog.Data.Login.Login;
import com.jonasvieira.iddog.R;

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

    public void requestLogin(ModelRequestLogin email, Callback<Login> callback) {
        Call<Login> loginCall = iDogServer.requestLogin(email);
        loginCall.enqueue(callback);
    }
}
