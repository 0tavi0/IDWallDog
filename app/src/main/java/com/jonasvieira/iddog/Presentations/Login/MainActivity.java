package com.jonasvieira.iddog.Presentations.Login;

import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.jonasvieira.iddog.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    @BindView(R.id.textInputLogin)
    TextInputLayout mLayoutEditText;
    @BindView(R.id.editTextEmail)
    TextInputEditText mEtLogin;
    @BindView(R.id.buttonLogin)
    Button mButtonLogin;
    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;

    MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mainPresenter = new MainPresenter(this, MainActivity.this);
    }

    @OnClick(R.id.buttonLogin)
    public void validateCredentials() {
        mainPresenter.validateCredentials(mEtLogin.getText().toString());
    }

    @Override
    public void showButtonLogin() {
        mButtonLogin.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideButtonLogin() {
        mButtonLogin.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        mProgressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showSnackBarError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showEditTextError(String message) {
        mLayoutEditText.setError(message);
    }

    @Override
    public void navigateToFeed(String token) {

    }
}
