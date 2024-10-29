package com.example.pokedex_desde_cero.core.remote

import com.example.pokedex_desde_cero.core.Pokemon
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PokeApiService {
    @GET("/api/v2/pokemon")
    suspend fun readFromService(): Response<PokemonListRawResponse>

    @GET("api/v2/pokemon/{id}")
    suspend fun readOneFomService(@Path("id") id: Int): Response<Pokemon>
}