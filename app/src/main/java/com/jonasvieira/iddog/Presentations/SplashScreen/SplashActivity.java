package com.jonasvieira.iddog.Presentations.SplashScreen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jonasvieira.iddog.Presentations.Login.MainActivity;
import com.jonasvieira.iddog.R;

public class SplashActivity extends AppCompatActivity implements SplashContract.View {

    SplashPresenter splashPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        splashPresenter = new SplashPresenter(this, SplashActivity.this);
        splashPresenter.delayToIntent();
    }


    @Override
    public void showLogin() {
        startActivity(new Intent(SplashActivity.this, MainActivity.class));
        finish();
    }
}
