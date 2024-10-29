package com.example.pokedex_desde_cero.di


import com.example.pokedex_desde_cero.core.DefaultPokemonRepository
import com.example.pokedex_desde_cero.core.PokemonRepositoryInterface
import com.example.pokedex_desde_cero.core.remote.PokeApiService
import com.example.pokedex_desde_cero.core.remote.PokemonRemoteRepository
import com.example.pokedex_desde_cero.core.remote.PokemonRemoteRepositoryInterface
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class Module {

    @Binds
    abstract fun provideDefaultPokemonRepository(defaultPokemonRepository: DefaultPokemonRepository): PokemonRepositoryInterface

    @Binds
    abstract fun providePokemonRemoteRepository(pokemonRemoteRepository: PokemonRemoteRepository): PokemonRemoteRepositoryInterface


}

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun providePokeApiServiceClass(): PokeApiService {
        val pokeApiUrl = "https://pokeapi.co"
        return Retrofit.Builder()
            .baseUrl(pokeApiUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PokeApiService::class.java)
    }

}