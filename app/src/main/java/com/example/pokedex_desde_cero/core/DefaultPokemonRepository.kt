package com.example.pokedex_desde_cero.core


import com.example.pokedex_desde_cero.core.remote.PokemonRemoteRepositoryInterface
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultPokemonRepository @Inject constructor(
    private val pokemonRemoteRepository: PokemonRemoteRepositoryInterface
): PokemonRepositoryInterface {

    private val _state = MutableStateFlow<List<Pokemon>>(listOf())
    override val setStream: StateFlow<List<Pokemon>>
        get() = _state.asStateFlow()


    override suspend fun read(): List<Pokemon> {
        val response = pokemonRemoteRepository.readPaginated()
        val pokemonList = _state.value.toMutableList()
        if (response.isSuccessful) {
            val pokemonRawList = response.body()!!.results
            pokemonRawList.forEach { pkr ->
                pokemonList.add(readOne(idFromUrl(pkr.url)!!))
                _state.emit(pokemonList.toList())
            }
        }
        else _state.value = pokemonList

        return pokemonList
    }


    override suspend fun readOne(id: Int): Pokemon {
        val response = pokemonRemoteRepository.readOne(id)
        return if (response.isSuccessful) response.body()!!
        else Pokemon(0, "")
    }

    private fun idFromUrl(url: String): Int? {
        return url.trimEnd('/').substringAfterLast('/').toIntOrNull()
    }

}