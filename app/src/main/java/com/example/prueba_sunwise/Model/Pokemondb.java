package com.example.prueba_sunwise.Model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Pokemon")
public class Pokemondb {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    int id;
    @ColumnInfo(name = "pokemon")
    String nombrePokemon;

    public Pokemondb(String nombrePokemon) {
        this.nombrePokemon = nombrePokemon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getNombrePokemon() {
        return nombrePokemon;
    }

    public void setNombrePokemon(String nombrePokemon) {
        this.nombrePokemon = nombrePokemon;
    }
}
