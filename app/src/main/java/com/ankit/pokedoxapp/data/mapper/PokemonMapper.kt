package com.ankit.pokedoxapp.data.mapper

import android.util.Log
import com.ankit.pokedoxapp.data.local.entity.PokemonEntity
import com.ankit.pokedoxapp.data.model.PokemonData
import com.ankit.pokedoxapp.data.model.PokemonResponseByName
import com.ankit.pokedoxapp.domain.model.DStates
import com.ankit.pokedoxapp.domain.model.Pokemon
import com.ankit.pokedoxapp.domain.model.PokemonInfo
import com.ankit.pokedoxapp.domain.utill.Helper.makeNameFormat

object PokemonMapper {
    fun PokemonData.toPokemonEntity(): PokemonEntity {
        return PokemonEntity(
            id = 1,
            name = name ?: "",
            pokemonImageUrl = url ?: ""
        )

    }

    fun PokemonEntity.toDomain(): Pokemon {
        return Pokemon(
            id = this.id,
            name = this.name,
            imageUrl = this.pokemonImageUrl
        )
    }

    fun PokemonResponseByName.toPokemonInfoDomain(): PokemonInfo {
        return PokemonInfo(
            id = this.id,
            name = this.name,
            species = this.species,
            stats = this.stats?.map { states ->
                val name = states.stat?.name ?: ""
                Log.e("TAG", "toPokemonInfoDomain: $name")
                DStates(
                    baseStat = states.baseStat,
                    effort = states.effort,
                    name = name.makeNameFormat()
                )
            },
            types = this.types,
            weight = this.weight,
            height = this.height
        )
    }
}