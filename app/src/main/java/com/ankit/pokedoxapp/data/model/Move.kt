package com.ankit.pokedoxapp.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Move(
    val move: MoveX? = null,
    val version_group_details: List<VersionGroupDetail>?  = null
)