package com.jonasvieira.iddog.Presentations.Feed;

import com.jonasvieira.iddog.Data.Feed.Feed;

public interface FeedContract {
    interface View {

        void populateRecyclerView(String breed, Feed list);

        void showProgressBar();

        void hideProgressBar();

        void showSnackBarError(String message);

        void navigateToHome();
    }

    interface Presenter {

        void getTokenToRequestDogs();

        void clearDatabase();
    }
}
