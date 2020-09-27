package com.example.prueba_sunwise.Model;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface IPokemonDao {
    @Query("SELECT * FROM pokemon")
    List<Pokemondb> getAll();

    @Query("SELECT * FROM Pokemon WHERE Pokemon LIKE :pokemon LIMIT 1")
    Pokemondb findByName(String pokemon);

    @Insert
    Long insert(Pokemondb pokemondb);
}
