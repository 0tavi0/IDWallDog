package com.jonasvieira.iddog.Presentations.SplashScreen;

import android.content.Context;
import android.os.Handler;

public class SplashPresenter implements SplashContract.Presenter {

    private SplashContract.View mSplashView;
    private Context mContext;

    public SplashPresenter(SplashContract.View view, Context context) {
        this.mSplashView = view;
        this.mContext = context;
    }

    @Override
    public void delayToIntent() {
        Handler handle = new Handler();
        handle.postDelayed(new Runnable() {
            @Override
            public void run() {
                mSplashView.showLogin();
            }
        }, 2000);
    }
}
