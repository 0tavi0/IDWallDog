package com.jonasvieira.iddog.Login;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Patterns;

import com.jonasvieira.iddog.R;

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
        mMainView.showSnackBarError("Login com sucesso!");
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

    private boolean isNetworkAvailable() {
        ConnectivityManager cm = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}
