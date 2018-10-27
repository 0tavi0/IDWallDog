package com.jonasvieira.iddog.Presentations.Feed;

import android.content.Context;
import android.support.annotation.NonNull;

import com.jonasvieira.iddog.Data.Feed.Feed;
import com.jonasvieira.iddog.Data.Login.Login;
import com.jonasvieira.iddog.Data.Login.User;
import com.jonasvieira.iddog.Data.Requests.ModelRequestLogin;
import com.jonasvieira.iddog.Database.LoginDAO;
import com.jonasvieira.iddog.Server.DogServer;
import com.jonasvieira.iddog.Utils.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeedPresenter implements FeedContract.Presenter {

    public FeedContract.View view;
    private Context mContext;

    FeedPresenter(FeedContract.View view, Context mContext) {
        this.view = view;
        this.mContext = mContext;
    }

    @Override
    public void getTokenToRequestDogs() {
        LoginDAO loginDAO = new LoginDAO();

        if (loginDAO.count() > 0) {
            List<User> mList = loginDAO.consultAll();
            if (mList != null) {
                makeRequest(mList.get(0).getToken());
            } else {
                view.showSnackBarError("erro ao consultar token do banco");
            }
        }
    }

    private void makeRequest(String token) {
        getDogsByBreed(Constants.HUSKY, token);
        getDogsByBreed(Constants.LABRADOR, token);
        getDogsByBreed(Constants.HOUND, token);
        getDogsByBreed(Constants.PUG, token);
    }

    private void getDogsByBreed(String husky, String token) {
        DogServer.getInstance(mContext).requestFeed(husky, token, new Callback<Feed>() {
            @Override
            public void onResponse(@NonNull Call<Feed> call, @NonNull Response<Feed> response) {
                if (response.isSuccessful()) {
                    view.showSnackBarError("Login com sucesso!" + response.body().getList().get(1));
                } else {
                    view.showSnackBarError("Erro!" + response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<Feed> call, @NonNull Throwable t) {
                view.showSnackBarError("Erro!" + t);
            }
        });
    }
}

