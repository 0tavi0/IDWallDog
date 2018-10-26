package com.jonasvieira.iddog.SplashScreen;

public interface SplashContract {

    interface Presenter {
        void delayToIntent();
    }

    interface View {
        void showLogin();
    }
}
