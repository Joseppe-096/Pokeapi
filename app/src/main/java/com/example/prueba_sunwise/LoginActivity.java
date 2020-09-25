package com.example.prueba_sunwise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.prueba_sunwise.Presenter.ILoginPresenter;
import com.example.prueba_sunwise.Presenter.LoginPresenter;
import com.example.prueba_sunwise.View.ILoginView;

public class LoginActivity extends AppCompatActivity implements ILoginView {

    private EditText edtCorreo, edtPassword;
    private Button btnLogin;
    private ProgressBar progressBarLogin;

    //Interface
    private ILoginPresenter loginPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Instancia de los componenetes
        edtCorreo = findViewById(R.id.edtCorreo);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        progressBarLogin = findViewById(R.id.progress_login);

        //instancia de la interface del presenter
        loginPresenter = new LoginPresenter(this);
        loginPresenter.setProgressBarVisiblity(View.INVISIBLE);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String correo = edtCorreo.getText().toString();
                String password = edtPassword.getText().toString();
                loginPresenter.setProgressBarVisiblity(View.VISIBLE);
                loginPresenter.onLogin(correo, password);
                GuardarPreferencias(correo, password);
            }
        });

        CargarPreferencias();
    }

    @Override
    public void onResult(Boolean result, int code) {
        if(result){
            Toast.makeText(this, "Bienvenido...", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        else
            Toast.makeText(this, "Datos incorrectos" + code, Toast.LENGTH_LONG).show();
            loginPresenter.setProgressBarVisiblity(View.GONE);
    }

    @Override
    public void onSetProgressBarVisibility(int visibility) {
        progressBarLogin.setVisibility(visibility);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    private void GuardarPreferencias(String correo, String password){
        SharedPreferences preferences = getSharedPreferences("UsuarioPref",MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("Correo", correo);
        editor.putString("Password", password);
        editor.commit();
    }
    private void CargarPreferencias(){
        SharedPreferences preferences = getSharedPreferences("UsuarioPref",MODE_PRIVATE);
        String correo = preferences.getString("Correo", "");
        String password = preferences.getString("Password", "");
        if(correo.isEmpty() && password.isEmpty()){

        }else
        {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}