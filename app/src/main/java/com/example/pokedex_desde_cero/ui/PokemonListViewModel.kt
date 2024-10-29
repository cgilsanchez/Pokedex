package com.example.pokedex_desde_cero.ui

import com.example.pokedex_desde_cero.core.Pokemon
import com.example.pokedex_desde_cero.core.PokemonRepositoryInterface
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val defaultPokemonRepository: PokemonRepositoryInterface
): ViewModel() {

    private val _uiState = MutableStateFlow<PokemonListUiState>(PokemonListUiState.Loading)
    val uiState: StateFlow<PokemonListUiState>
        get() = _uiState.asStateFlow()


    init {
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                defaultPokemonRepository.setStream.collect {
                        pokemonList ->
                    if (pokemonList.isEmpty()) _uiState.value = PokemonListUiState.Loading
                    else _uiState.value = PokemonListUiState.Success(pokemonList)
                }
            }
        }
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                defaultPokemonRepository.read()
            }
        }

    }

}

sealed class PokemonListUiState() {
    data object Loading: PokemonListUiState()
    class Success(val pokemonList: List<Pokemon>): PokemonListUiState()
    class Error(val message: String): PokemonListUiState()
}