package com.jonasvieira.iddog.Presentations.Details;

public interface DetailsContract {
    interface View {

        void showProgressBar();

        void hideProgressBar();

        void showSnackBarError(String message);
    }

    interface Presenter {

    }
}
