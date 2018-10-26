package com.jonasvieira.iddog.Login;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Patterns;

import com.jonasvieira.iddog.Data.Login;
import com.jonasvieira.iddog.R;
import com.jonasvieira.iddog.Server.DogServer;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenter implements MainContract.Presenter {

    private MainContract.View mMainView;
    private Context mContext;

    MainPresenter(MainContract.View view, Context context) {
        this.mMainView = view;
        this.mContext = context;
    }

    @Override
    public void validateCredentials(String email) {
        if (isNetworkAvailable()){
            validateEmail(email);
        }else{
            mMainView.showSnackBarError(mContext.getString(R.string.sem_conexao));
        }
    }

    private void validateEmail(String email) {
        if (!email.isEmpty()) {
            if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                makeRequestLogin(email);
            } else {
                mMainView.showEditTextError("E-mail invalido");
            }
        } else {
            mMainView.showEditTextError("E-mail vazio");
        }
    }

    private void makeRequestLogin(String email){
        controlVisibilityProgress(true);
        requestLogin(email);
        controlVisibilityProgress(false);
    }

    private void controlVisibilityProgress(boolean progressIsVisible) {
        if (progressIsVisible) {
            mMainView.hideButtonLogin();
            mMainView.showProgressBar();
        } else {
            mMainView.showButtonLogin();
            mMainView.hideProgressBar();
        }
    }

    private void requestLogin(String email) {
        DogServer.getInstance(mContext).requestLogin(email, new Callback<List<Login>>() {
            @Override
            public void onResponse(Call<List<Login>> call, Response<List<Login>> response) {
                mMainView.showSnackBarError("Login com sucesso!" + response.body());
            }

            @Override
            public void onFailure(Call<List<Login>> call, Throwable t) {
                mMainView.showSnackBarError("Erro!" + t);
            }
        });

    }
    private boolean isNetworkAvailable() {
        ConnectivityManager cm = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}
