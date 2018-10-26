package com.jonasvieira.iddog.Database;

import android.util.Log;

import com.jonasvieira.iddog.Data.Login.User;

public class LoginDAO {

    private static String TAG = "BancoLogin";

    public boolean save(User user) {
        try {
            User mUser = new User(user.getEmail(), user.getToken());
            mUser.save();
            Log.i(TAG, "Inserido com sucesso");
            return true;
        } catch (Exception e) {
            Log.i(TAG, "Falha ao inserir: " + e.getMessage());
            return false;
        }
    }
}
