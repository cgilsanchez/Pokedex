package com.example.pokedex_desde_cero.core.remote

data class PokemonListRawResponse (
    val count: Int,
    val prev: String?,
    val next: String?,
    val results: List<PokemonRaw>
)