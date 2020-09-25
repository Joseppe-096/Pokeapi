package com.example.prueba_sunwise;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_principal:
                SharedPreferences preferences = getSharedPreferences("UsuarioPref",MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.remove("Correo");
                editor.remove("Password");
                editor.commit();
                Toast.makeText(this, "Sesion Cerrada", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}