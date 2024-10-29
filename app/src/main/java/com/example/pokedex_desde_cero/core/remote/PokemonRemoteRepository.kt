package com.example.pokedex_desde_cero.core.remote

import com.example.pokedex_desde_cero.core.Pokemon
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PokemonRemoteRepository @Inject constructor(
    private val pokeApiService: PokeApiService
): PokemonRemoteRepositoryInterface {

    override suspend fun readPaginated(): Response<PokemonListRawResponse> {
        return pokeApiService.readFromService()
    }

    override suspend fun readOne(id: Int): Response<Pokemon> {
        return pokeApiService.readOneFomService(id)
    }


}