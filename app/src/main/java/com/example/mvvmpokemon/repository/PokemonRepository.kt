package com.example.mvvmpokemon.repository

import com.example.mvvmpokemon.services.RetrofitClient
import com.example.mvvmpokemon.services.WebService

class PokemonRepository {
    private var apiService: WebService? = null

    init {
        apiService = RetrofitClient.getClient?.create(WebService::class.java)
    }

    suspend fun getPokemon() = apiService?.getPokemons()
}