package com.jonasvieira.iddog.Database;

import android.util.Log;

import com.jonasvieira.iddog.Data.Login.User;

import java.util.List;

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

    public long count() {
        return User.count(User.class);
    }

    public List<User> consultAll() {
        try {
            Log.i(TAG, "Recuperado com sucesso");
            return User.listAll(User.class);
        } catch (Exception e) {
            Log.i(TAG, "Falha ao consultar: " + e.getMessage());
            return null;
        }
    }

    public boolean clearAll() {
        try {
            Log.i(TAG, "Deletado com sucesso");
            User.deleteAll(User.class);
            return true;
        } catch (Exception e) {
            Log.i(TAG, "Erro ao deletar");
            return false;
        }
    }
}
