package com.example.prueba_sunwise.Model;

import com.example.prueba_sunwise.Presenter.pokemonRespuesta;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IpokemonApi {
    @GET("pokemon")
    Call<pokemonRespuesta> obtenerListaPokemon();
}
