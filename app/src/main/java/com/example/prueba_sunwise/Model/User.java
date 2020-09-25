package com.example.prueba_sunwise.Model;

import android.text.TextUtils;
import android.util.Patterns;

import java.util.regex.Pattern;

public class User implements IUser {
    private String correo, password;

    public User(String correo, String password) {
        this.correo = correo;
        this.password = password;
    }

    @Override
    public String getCorreo() {
        return correo;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public int isValid(String correo, String password) {
        if(correo==null||password==null||!correo.equals(getCorreo())||!password.equals(getPassword())){
            return -1;
        }
        return 0;
    }


}
