package com.jonasvieira.iddog.Presentations.SplashScreen;

public interface SplashContract {

    interface Presenter {
        void delayToIntent();
    }

    interface View {
        void showLogin();
    }
}
