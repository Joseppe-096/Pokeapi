package com.example.prueba_sunwise.Presenter;

import android.content.Context;

public interface ILoginPresenter {
    void onLogin(String correo, String password);
    void setProgressBarVisiblity(int visiblity);
}
