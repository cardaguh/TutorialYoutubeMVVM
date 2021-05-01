package com.example.mvvmpokemon.model

data class PokemonResponse(
    val pokemon: MutableList<PokemonDataModel> = mutableListOf()
)

data class PokemonDataModel (
    val id: Long,
    val name: String = "",
    val img: String
)
