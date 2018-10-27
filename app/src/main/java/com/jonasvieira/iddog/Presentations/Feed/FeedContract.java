package com.jonasvieira.iddog.Presentations.Feed;

public interface FeedContract {
    interface View {

        void showProgressBar();

        void hideProgressBar();

        void showSnackBarError(String message);
    }

    interface Presenter {

        void getTokenToRequestDogs();
    }
}
