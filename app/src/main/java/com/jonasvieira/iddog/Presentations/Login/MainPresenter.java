package com.jonasvieira.iddog.Presentations.Login;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.util.Patterns;

import com.jonasvieira.iddog.Data.Login.Login;
import com.jonasvieira.iddog.Data.Login.User;
import com.jonasvieira.iddog.Database.LoginDAO;
import com.jonasvieira.iddog.R;
import com.jonasvieira.iddog.Server.DogServer;
import com.jonasvieira.iddog.Data.Requests.ModelRequestLogin;

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
        if (isNetworkAvailable()) {
            validateEmail(email);
        } else {
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

    private void makeRequestLogin(String email) {
        controlVisibilityProgress(true);
        requestLogin(email);
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
        DogServer.getInstance(mContext).requestLogin(new ModelRequestLogin(email), new Callback<Login>() {
            @Override
            public void onResponse(@NonNull Call<Login> call, @NonNull Response<Login> response) {

                if (response.isSuccessful()) {
                    mMainView.showSnackBarError("Login com sucesso!" + response.body().getUser().getToken());

                    saveCredentials(response.body().getUser());
                } else {
                    mMainView.showSnackBarError("Erro!" + response.body());
                    controlVisibilityProgress(false);
                }
            }

            @Override
            public void onFailure(@NonNull Call<Login> call, @NonNull Throwable t) {
                mMainView.showSnackBarError("Erro!" + t);
                controlVisibilityProgress(false);
            }
        });

    }

    private void saveCredentials(User user) {
        if (new LoginDAO().save(user)) {
            mMainView.navigateToFeed();
        } else {
            mMainView.showSnackBarError("Erro ao salvar");
        }
        controlVisibilityProgress(false);
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager cm = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm != null ? cm.getActiveNetworkInfo() : null;
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}
