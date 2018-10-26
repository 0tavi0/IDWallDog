package com.jonasvieira.iddog.Presentations.Login;

public interface MainContract {

    interface View {
        void showProgressBar();

        void hideProgressBar();

        void showButtonLogin();

        void hideButtonLogin();

        void showSnackBarError(String message);

        void showEditTextError(String message);

        void navigateToFeed();
    }

    interface Presenter {

        void validateCredentials(String email);
    }
}
