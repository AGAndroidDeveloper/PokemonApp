package com.ankit.pokedoxapp.data.mapper

import com.ankit.pokedoxapp.data.local.entity.PokemonEntity
import com.ankit.pokedoxapp.data.model.PokemonData
import com.ankit.pokedoxapp.domain.Pokemon

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
}