package com.example.prueba_sunwise;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.prueba_sunwise.Model.IpokemonApi;
import com.example.prueba_sunwise.Model.Pokemon;
import com.example.prueba_sunwise.Model.Pokemondb;
import com.example.prueba_sunwise.Presenter.AdapterPokemon;
import com.example.prueba_sunwise.Model.PokemonRespuesta;
import com.example.prueba_sunwise.Presenter.PokemondbPresenter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private Retrofit retrofit;
    private RecyclerView rcvPokemon;
    private AdapterPokemon adapterPokemon;
    private int offset;
    private boolean CargarMas;
    private PokemondbPresenter pokemondbPresenter;
    private Pokemondb pokemondb;
    private EditText edtMostrarDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtMostrarDatos = findViewById(R.id.edtMostrarDatos);
        rcvPokemon = findViewById(R.id.rcvPokemon);

        pokemondbPresenter = PokemondbPresenter.get(this);
        //Mostrar 10 pokemons guardados
        List<Pokemondb> pokemondbList = pokemondbPresenter.getPokemons();
        String resultado = "";
        if(pokemondbList.size() > 0) {
            for(int i = 0; i < pokemondbList.size(); i++){
                resultado += pokemondbList.get(i).getId() + ": " + pokemondbList.get(i).getNombrePokemon() + "\n";
            }
            edtMostrarDatos.setText(resultado);
        }

        adapterPokemon = new AdapterPokemon();
        rcvPokemon.setAdapter(adapterPokemon);
        rcvPokemon.setHasFixedSize(true);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rcvPokemon.setLayoutManager(layoutManager);
        rcvPokemon.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(dy > 0){
                    int visibleItemCount = layoutManager.getChildCount();
                    int totalItemCount = layoutManager.getItemCount();
                    int pastVisibleItems = layoutManager.findFirstVisibleItemPosition();
                    if(CargarMas){
                        if((visibleItemCount + pastVisibleItems) >= totalItemCount){
                            Log.i("Final", " Llego al final.");
                            CargarMas = false;
                            offset += 20;
                            obtenerDatosPokemon(offset);
                        }
                    }
                }
            }
        });

        //Consume la API de pokemon
        retrofit = new Retrofit.Builder()
                .baseUrl("http://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        offset = 0;
        obtenerDatosPokemon(offset);
    }
    private void obtenerDatosPokemon(int offset){
        IpokemonApi ipokemonApi = retrofit.create(IpokemonApi.class);
        Call<PokemonRespuesta> respuestaCall = ipokemonApi.obtenerListaPokemon(20, offset);
        respuestaCall.enqueue(new Callback<PokemonRespuesta>() {
            @Override
            public void onResponse(Call<PokemonRespuesta> call, Response<PokemonRespuesta> response) {
                CargarMas = true;
                if(response.isSuccessful()){
                    PokemonRespuesta pokemonR = response.body();
                     ArrayList<Pokemon> listPokemon = pokemonR.getResults();
                     adapterPokemon.adicionarListaPokemon(listPokemon);
                }else{
                    Log.e("onResponse", ""+ response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<PokemonRespuesta> call, Throwable t) {
                CargarMas = true;
                Log.e("onFailure", ""+ t.getMessage());
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
                break;
            case R.id.btnGuardarP:
                guardarPokemonsLocal();
                getActionBar().setDisplayUseLogoEnabled(false);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem item = menu.findItem(R.id.btnGuardarP);

        List<Pokemondb> pokemondbList = pokemondbPresenter.getPokemons();
        if(pokemondbList.size() > 0) {
            item.setEnabled(false);
            item.getIcon().setAlpha(130);
        }else{
            item.setEnabled(true);
            item.getIcon().setAlpha(255);
        }
        return super.onPrepareOptionsMenu(menu);
    }



    private void guardarPokemonsLocal(){
        IpokemonApi ipokemonApi = retrofit.create(IpokemonApi.class);
        Call<PokemonRespuesta> respuestaCall = ipokemonApi.obtenerListaPokemon(10, 0);
        respuestaCall.enqueue(new Callback<PokemonRespuesta>() {
            @Override
            public void onResponse(Call<PokemonRespuesta> call, Response<PokemonRespuesta> response) {
                if(response.isSuccessful()){
                    PokemonRespuesta pokemonR = response.body();
                    ArrayList<Pokemon> listPokemon = pokemonR.getResults();
                    for (int i = 0; i < listPokemon.size();i++){
                        Pokemon p = listPokemon.get(i);
                        pokemondb = new Pokemondb(p.getName());
                        pokemondbPresenter.addPokemons(pokemondb);
                        Log.d("DiezPoke", ""+ p.getName());
                    }
                    List<Pokemondb> pokemondbList = pokemondbPresenter.getPokemons();
                    String resultado = "";
                    for(int i = 0; i < pokemondbList.size(); i++){
                        resultado += pokemondbList.get(i).getId() + ": " + pokemondbList.get(i).getNombrePokemon() + "\n";
                    }
                    edtMostrarDatos.setText(resultado);
                    Toast.makeText(MainActivity.this, "Se guardo con exito!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<PokemonRespuesta> call, Throwable t) {

            }
        });
    }
}