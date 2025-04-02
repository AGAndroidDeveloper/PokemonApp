package com.ankit.pokedoxapp.domain.utill

import com.ankit.pokedoxapp.data.model.PokemonResponseByName
import com.ankit.pokedoxapp.data.model.Species
import com.ankit.pokedoxapp.data.model.Stat
import com.ankit.pokedoxapp.data.model.States
import com.ankit.pokedoxapp.data.model.Type
import com.ankit.pokedoxapp.data.model.TypeX

object Helper {
    fun String.serialnumberFormatter(): String {
        return if (this.length < 3) {
            val outPut = this.padStart(3, '0')
            "#$outPut"
        } else {
            "#$this"
        }
    }


    val pokemonData  = PokemonResponseByName(
        id = 2,
        name = "ivysaur",
        species = Species(
            name = "ivysaur",
            url = "https://pokeapi.co/api/v2/pokemon-species/2/"
        ),
        stats = listOf(
            States(
                baseStat = 60,
                effort = 0,
                stat = Stat(name = "hp", url = "https://pokeapi.co/api/v2/stat/1/")
            ),
            States(
                baseStat = 62,
                effort = 0,
                stat = Stat(name = "attack", url = "https://pokeapi.co/api/v2/stat/2/")
            ),
            States(
                baseStat = 63,
                effort = 0,
                stat = Stat(name = "defense", url = "https://pokeapi.co/api/v2/stat/3/")
            ),
            States(
                baseStat = 80,
                effort = 1,
                stat = Stat(name = "special-attack", url = "https://pokeapi.co/api/v2/stat/4/")
            ),
            States(
                baseStat = 80,
                effort = 1,
                stat = Stat(name = "special-defense", url = "https://pokeapi.co/api/v2/stat/5/")
            ),
            States(
                baseStat = 60,
                effort = 0,
                stat = Stat(name = "speed", url = "https://pokeapi.co/api/v2/stat/6/")
            )
        ),
        types = listOf(
            Type(
                slot = 1,
                type = TypeX(name = "grass", url = "https://pokeapi.co/api/v2/type/12/")
            ),
            Type(slot = 2, type = TypeX(name = "poison", url = "https://pokeapi.co/api/v2/type/4/"))
        ),
        weight = 130,
        height = 10
    )

}