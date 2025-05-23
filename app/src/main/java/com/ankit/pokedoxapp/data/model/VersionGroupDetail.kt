package com.ankit.pokedoxapp.data.model

import kotlinx.serialization.Serializable

@Serializable
data class VersionGroupDetail(
    val level_learned_at: Int,
    val move_learn_method: MoveLearnMethod,
    val order: Int? = null,
    val version_group: VersionGroup
)