package com.ankit.pokedoxapp.data.model

import kotlinx.serialization.Serializable
import java.sql.Types

@Serializable
data class Pokemon(
    val count: Int? = null,
    val next: String? = null,
    val previous: String? = null,
    val results: List<PokemonData>? = null
)

@Serializable
data class PokemonData(
    val name: String? = null,
    val url: String? = null
)


val samplePokemonResponse = Pokemon(
    count = 1302,
    next = "https://pokeapi.co/api/v2/pokemon?offset=21&limit=20",
    previous = "https://pokeapi.co/api/v2/pokemon?offset=0&limit=1",
    results = listOf(
        PokemonData("ivysaur", "https://pokeapi.co/api/v2/pokemon/2/"),
        PokemonData("venusaur", "https://pokeapi.co/api/v2/pokemon/3/"),
        PokemonData("charmander", "https://pokeapi.co/api/v2/pokemon/4/"),
        PokemonData("charmeleon", "https://pokeapi.co/api/v2/pokemon/5/"),
        PokemonData("charizard", "https://pokeapi.co/api/v2/pokemon/6/"),
        PokemonData("squirtle", "https://pokeapi.co/api/v2/pokemon/7/"),
        PokemonData("wartortle", "https://pokeapi.co/api/v2/pokemon/8/"),
        PokemonData("blastoise", "https://pokeapi.co/api/v2/pokemon/9/"),
        PokemonData("caterpie", "https://pokeapi.co/api/v2/pokemon/10/"),
        PokemonData("metapod", "https://pokeapi.co/api/v2/pokemon/11/"),
        PokemonData("butterfree", "https://pokeapi.co/api/v2/pokemon/12/"),
        PokemonData("weedle", "https://pokeapi.co/api/v2/pokemon/13/"),
        PokemonData("kakuna", "https://pokeapi.co/api/v2/pokemon/14/"),
        PokemonData("beedrill", "https://pokeapi.co/api/v2/pokemon/15/"),
        PokemonData("pidgey", "https://pokeapi.co/api/v2/pokemon/16/"),
        PokemonData("pidgeotto", "https://pokeapi.co/api/v2/pokemon/17/"),
        PokemonData("pidgeot", "https://pokeapi.co/api/v2/pokemon/18/"),
        PokemonData("rattata", "https://pokeapi.co/api/v2/pokemon/19/"),
        PokemonData("raticate", "https://pokeapi.co/api/v2/pokemon/20/"),
        PokemonData("spearow", "https://pokeapi.co/api/v2/pokemon/21/")
    )
)
