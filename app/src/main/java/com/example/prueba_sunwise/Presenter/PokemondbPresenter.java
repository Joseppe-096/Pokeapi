package com.example.prueba_sunwise.Presenter;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.room.Room;

import com.example.prueba_sunwise.Model.AppDatabase;
import com.example.prueba_sunwise.Model.IPokemonDao;
import com.example.prueba_sunwise.Model.Pokemondb;

import java.util.List;


public class PokemondbPresenter {
    @SuppressLint("StaticFieldLeak")
    private static PokemondbPresenter pokemondbPresenter;

    private IPokemonDao iPokemonDao;

    private PokemondbPresenter(Context context) {
        Context appContext = context.getApplicationContext();
        AppDatabase database = Room.databaseBuilder(appContext, AppDatabase.class, "pokemon")
                .allowMainThreadQueries().build();
        iPokemonDao = database.iPokemonDao();
    }
    public static PokemondbPresenter get(Context context){
        if(pokemondbPresenter == null){
            pokemondbPresenter = new PokemondbPresenter(context);
        }
        return pokemondbPresenter;
    }

    public List<Pokemondb> getPokemons() {
        return iPokemonDao.getAll();
    }
    public void addPokemons(Pokemondb pokemondb) {
        iPokemonDao.insert(pokemondb);
    }
}
