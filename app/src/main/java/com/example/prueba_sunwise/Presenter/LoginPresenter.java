package com.example.prueba_sunwise.Presenter;

import android.os.Handler;
import android.os.Looper;

import com.example.prueba_sunwise.Model.IUser;
import com.example.prueba_sunwise.Model.User;
import com.example.prueba_sunwise.View.ILoginView;


public class LoginPresenter implements ILoginPresenter{

    ILoginView loginView;
    IUser user;
    Handler handler;

    public LoginPresenter(ILoginView loginView) {
        this.loginView = loginView;
        initUsuer();
        handler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void onLogin(String correo, String password) {
        Boolean isLoginSuccess = true;
        final int code = user.isValid(correo,password);
        if(code!=0) isLoginSuccess = false;
        final boolean result = isLoginSuccess;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                loginView.onResult(result,code);
                }
            },500);
    }

    @Override
    public void setProgressBarVisiblity(int visiblity) {
        loginView.onSetProgressBarVisibility(visiblity);
    }


    private void initUsuer(){
        user = new User("jose@gmail.com", "12345");
    }

}
