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

    private MainContract.View view;
    private Context mContext;

    MainPresenter(MainContract.View view, Context context) {
        this.view = view;
        this.mContext = context;
    }

    @Override
    public void validateCredentials(String email) {
        if (isNetworkAvailable()) {
            validateEmail(email);
        } else {
            view.showSnackBarError(mContext.getString(R.string.sem_conexao));
        }
    }

    private void validateEmail(String email) {
        if (!email.isEmpty()) {
            if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                makeRequestLogin(email);
            } else {
                view.showEditTextError("E-mail invalido");
            }
        } else {
            view.showEditTextError("E-mail vazio");
        }
    }

    private void makeRequestLogin(String email) {
        controlVisibilityProgress(true);
        requestLogin(email);
    }

    private void controlVisibilityProgress(boolean progressIsVisible) {
        if (progressIsVisible) {
            view.hideButtonLogin();
            view.showProgressBar();
        } else {
            view.showButtonLogin();
            view.hideProgressBar();
        }
    }

    private void requestLogin(String email) {
        DogServer.getInstance(mContext).requestLogin(new ModelRequestLogin(email), new Callback<Login>() {
            @Override
            public void onResponse(@NonNull Call<Login> call, @NonNull Response<Login> response) {

                if (response.isSuccessful()) {
                    view.showSnackBarError("Login com sucesso!" + response.body().getUser().getToken());

                    saveCredentials(response.body().getUser());
                } else {
                    view.showSnackBarError("Erro!" + response.body());
                    controlVisibilityProgress(false);
                }
            }

            @Override
            public void onFailure(@NonNull Call<Login> call, @NonNull Throwable t) {
                view.showSnackBarError("Erro!" + t);
                controlVisibilityProgress(false);
            }
        });

    }

    private void saveCredentials(User user) {
        if (new LoginDAO().save(user)) {
            view.navigateToFeed();
        } else {
            view.showSnackBarError("Erro ao salvar");
        }
        controlVisibilityProgress(false);
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager cm = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm != null ? cm.getActiveNetworkInfo() : null;
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}
