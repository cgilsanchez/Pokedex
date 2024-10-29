package com.example.pokedex_desde_cero.core.remote

import com.example.pokedex_desde_cero.core.Pokemon
import retrofit2.Response

interface PokemonRemoteRepositoryInterface {

    suspend fun readPaginated(): Response<PokemonListRawResponse>
    suspend fun readOne(id: Int): Response<Pokemon>

}