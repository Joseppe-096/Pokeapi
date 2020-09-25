package com.example.prueba_sunwise;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.prueba_sunwise.Model.IpokemonApi;
import com.example.prueba_sunwise.Model.pokemon;
import com.example.prueba_sunwise.Presenter.AdapterPokemon;
import com.example.prueba_sunwise.Presenter.pokemonRespuesta;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private Retrofit retrofit;
    private RecyclerView rcvPokemon;
    private AdapterPokemon adapterPokemon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rcvPokemon = findViewById(R.id.rcvPokemon);
        rcvPokemon.setHasFixedSize(true);
        rcvPokemon.setLayoutManager(new LinearLayoutManager(this));

        //Consume la API de pokemon
        retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        obtenerDatosPokemon();
    }
    private void obtenerDatosPokemon(){
        IpokemonApi ipokemonApi = retrofit.create(IpokemonApi.class);
        Call<pokemonRespuesta> respuestaCall = ipokemonApi.obtenerListaPokemon();
        respuestaCall.enqueue(new Callback<pokemonRespuesta>() {
            @Override
            public void onResponse(Call<pokemonRespuesta> call, Response<pokemonRespuesta> response) {
                if(response.isSuccessful()){
                    try {
                        pokemonRespuesta pokemonR = response.body();
                        ArrayList<pokemon> listPokemon = pokemonR.getResult();
                        for(int i = 0; i < listPokemon.size(); i++){
                            pokemon p = listPokemon.get(i);
                            Log.e("Recycler", ""+ p.getName());
                        }
                    }catch (Exception e){}

                    //adapterPokemon = new AdapterPokemon(listDatos);
                    //rcvPokemon.setAdapter(adapterPokemon);
                }else{
                    Log.e("Recycler", ""+ response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<pokemonRespuesta> call, Throwable t) {

            }
        });
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