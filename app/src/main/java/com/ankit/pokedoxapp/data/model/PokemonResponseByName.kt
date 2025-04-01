package com.ankit.pokedoxapp.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonResponseByName(
    @SerialName("id") val id: Int? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("species") val species: Species? = Species(),
    @SerialName("stats") val stats: List<States>? = null,
    @SerialName("types") val types: List<Type>? = null,
    @SerialName("weight") val weight: Int? = null,
    @SerialName("height") val height: Int? = null
)

@Serializable
data class States(
    @SerialName("base_stat") var baseStat: Int? = null,
    @SerialName("effort") var effort: Int? = null,
    @SerialName("stat") var stat: Stat? = Stat()
)


@Serializable
data class Stat (
    @SerialName("name" ) var name : String? = null,
    @SerialName("url"  ) var url  : String? = null
)

@Serializable
data class Species(
    @SerialName("name") val name: String? = null,
    @SerialName("url") val url: String? = null
)

//@Serializable
//data class PokemonResponseByName(
//    val abilities: List<Ability>,
//    val base_experience: Int,
//    val cries: Cries,
//    val forms: List<Form>,
//    val game_indices: List<GameIndice>,
//    val height: Int,
//    val held_items: List<String?>,
//    val id: Int,
//    val is_default: Boolean,
//    val location_area_encounters: String,
//    val moves: List<Move>,
//    val name: String,
//    val order: Int,
//    val past_abilities: List<String?>,
//    val past_types: List<String?>,
//    val species: Species,
//    val sprites: Sprites,
//    val stats: List<Stat>,
//    val types: List<Type>,
//    val weight: Int
//)

//data class Ability (
//
//    @SerializedName("name" ) var name : String? = null,
//    @SerializedName("url"  ) var url  : String? = null
//
//)
//data class Cries (
//
//    @SerializedName("latest" ) var latest : String? = null,
//    @SerializedName("legacy" ) var legacy : String? = null
//
//)
//
//
//data class Forms (
//
//    @SerializedName("name" ) var name : String? = null,
//    @SerializedName("url"  ) var url  : String? = null
//
//)
//
//
//data class Version (
//
//    @SerializedName("name" ) var name : String? = null,
//    @SerializedName("url"  ) var url  : String? = null
//
//)
//
//data class GameIndices (
//
//    @SerializedName("game_index" ) var gameIndex : Int?     = null,
//    @SerializedName("version"    ) var version   : Version? = Version()
//
//)
//
//
//
//
//data class Move (
//
//    @SerializedName("name" ) var name : String? = null,
//    @SerializedName("url"  ) var url  : String? = null
//
//)
//
//data class MoveLearnMethod (
//
//    @SerializedName("name" ) var name : String? = null,
//    @SerializedName("url"  ) var url  : String? = null
//
//)
//
//data class VersionGroup (
//
//    @SerializedName("name" ) var name : String? = null,
//    @SerializedName("url"  ) var url  : String? = null
//
//)
//
//data class VersionGroupDetails (
//
//    @SerializedName("level_learned_at"  ) var levelLearnedAt  : Int?             = null,
//    @SerializedName("move_learn_method" ) var moveLearnMethod : MoveLearnMethod? = MoveLearnMethod(),
//    @SerializedName("order"             ) var order           : String?          = null,
//    @SerializedName("version_group"     ) var versionGroup    : VersionGroup?    = VersionGroup()
//
//)
//
//data class Moves (
//
//    @SerializedName("move"                  ) var move                : Move?                          = Move(),
//    @SerializedName("version_group_details" ) var versionGroupDetails : ArrayList<VersionGroupDetails> = arrayListOf()
//
//)
//
//
//data class Species (
//
//    @SerializedName("name" ) var name : String? = null,
//    @SerializedName("url"  ) var url  : String? = null
//
//)
//
//
//data class DreamWorld (
//
//    @SerializedName("front_default" ) var frontDefault : String? = null,
//    @SerializedName("front_female"  ) var frontFemale  : String? = null
//
//)
//
//
//data class Home (
//
//    @SerializedName("front_default"      ) var frontDefault     : String? = null,
//    @SerializedName("front_female"       ) var frontFemale      : String? = null,
//    @SerializedName("front_shiny"        ) var frontShiny       : String? = null,
//    @SerializedName("front_shiny_female" ) var frontShinyFemale : String? = null
//
//)
//
//data class Official-artwork (
//
//@SerializedName("front_default" ) var frontDefault : String? = null,
//@SerializedName("front_shiny"   ) var frontShiny   : String? = null
//
//)
//
//
//data class Showdown (
//
//    @SerializedName("back_default"       ) var backDefault      : String? = null,
//    @SerializedName("back_female"        ) var backFemale       : String? = null,
//    @SerializedName("back_shiny"         ) var backShiny        : String? = null,
//    @SerializedName("back_shiny_female"  ) var backShinyFemale  : String? = null,
//    @SerializedName("front_default"      ) var frontDefault     : String? = null,
//    @SerializedName("front_female"       ) var frontFemale      : String? = null,
//    @SerializedName("front_shiny"        ) var frontShiny       : String? = null,
//    @SerializedName("front_shiny_female" ) var frontShinyFemale : String? = null
//
//)
//data class Other (
//
//    @SerializedName("dream_world"      ) var dreamWorld       : DreamWorld?       = DreamWorld(),
//    @SerializedName("home"             ) var home             : Home?             = Home(),
//    @SerializedName("official-artwork" ) var official-artwork : Official-artwork? = Official-artwork(),
//@SerializedName("showdown"         ) var showdown         : Showdown?         = Showdown()
//
//)
//
//
//data class Red-blue (
//
//@SerializedName("back_default"      ) var backDefault      : String? = null,
//@SerializedName("back_gray"         ) var backGray         : String? = null,
//@SerializedName("back_transparent"  ) var backTransparent  : String? = null,
//@SerializedName("front_default"     ) var frontDefault     : String? = null,
//@SerializedName("front_gray"        ) var frontGray        : String? = null,
//@SerializedName("front_transparent" ) var frontTransparent : String? = null
//
//)
//
//data class Yellow (
//
//    @SerializedName("back_default"      ) var backDefault      : String? = null,
//    @SerializedName("back_gray"         ) var backGray         : String? = null,
//    @SerializedName("back_transparent"  ) var backTransparent  : String? = null,
//    @SerializedName("front_default"     ) var frontDefault     : String? = null,
//    @SerializedName("front_gray"        ) var frontGray        : String? = null,
//    @SerializedName("front_transparent" ) var frontTransparent : String? = null
//
//)
//
//
//data class Generation-i (
//
//@SerializedName("red-blue" ) var red-blue : Red-blue? = Red-blue(),
//@SerializedName("yellow"   ) var yellow   : Yellow?   = Yellow()
//
//)
//
//
//data class Crystal (
//
//    @SerializedName("back_default"            ) var backDefault           : String? = null,
//    @SerializedName("back_shiny"              ) var backShiny             : String? = null,
//    @SerializedName("back_shiny_transparent"  ) var backShinyTransparent  : String? = null,
//    @SerializedName("back_transparent"        ) var backTransparent       : String? = null,
//    @SerializedName("front_default"           ) var frontDefault          : String? = null,
//    @SerializedName("front_shiny"             ) var frontShiny            : String? = null,
//    @SerializedName("front_shiny_transparent" ) var frontShinyTransparent : String? = null,
//    @SerializedName("front_transparent"       ) var frontTransparent      : String? = null
//
//)
//
//
//
//data class Gold (
//
//    @SerializedName("back_default"      ) var backDefault      : String? = null,
//    @SerializedName("back_shiny"        ) var backShiny        : String? = null,
//    @SerializedName("front_default"     ) var frontDefault     : String? = null,
//    @SerializedName("front_shiny"       ) var frontShiny       : String? = null,
//    @SerializedName("front_transparent" ) var frontTransparent : String? = null
//
//)
//
//data class Silver (
//
//    @SerializedName("back_default"      ) var backDefault      : String? = null,
//    @SerializedName("back_shiny"        ) var backShiny        : String? = null,
//    @SerializedName("front_default"     ) var frontDefault     : String? = null,
//    @SerializedName("front_shiny"       ) var frontShiny       : String? = null,
//    @SerializedName("front_transparent" ) var frontTransparent : String? = null
//
//)
//
//data class Generation-ii (
//
//@SerializedName("crystal" ) var crystal : Crystal? = Crystal(),
//@SerializedName("gold"    ) var gold    : Gold?    = Gold(),
//@SerializedName("silver"  ) var silver  : Silver?  = Silver()
//
//)
//
//data class Emerald (
//
//    @SerializedName("front_default" ) var frontDefault : String? = null,
//    @SerializedName("front_shiny"   ) var frontShiny   : String? = null
//
//)
//
//data class Firered-leafgreen (
//
//@SerializedName("back_default"  ) var backDefault  : String? = null,
//@SerializedName("back_shiny"    ) var backShiny    : String? = null,
//@SerializedName("front_default" ) var frontDefault : String? = null,
//@SerializedName("front_shiny"   ) var frontShiny   : String? = null
//
//)
//
//
//data class Ruby-sapphire (
//
//@SerializedName("back_default"  ) var backDefault  : String? = null,
//@SerializedName("back_shiny"    ) var backShiny    : String? = null,
//@SerializedName("front_default" ) var frontDefault : String? = null,
//@SerializedName("front_shiny"   ) var frontShiny   : String? = null
//
//)
//
//
//
//data class Generation-iii (
//
//@SerializedName("emerald"           ) var emerald           : Emerald?           = Emerald(),
//@SerializedName("firered-leafgreen" ) var firered-leafgreen : Firered-leafgreen? = Firered-leafgreen(),
//@SerializedName("ruby-sapphire"     ) var ruby-sapphire     : Ruby-sapphire?     = Ruby-sapphire()
//
//)
//
//
//data class Diamond-pearl (
//
//@SerializedName("back_default"       ) var backDefault      : String? = null,
//@SerializedName("back_female"        ) var backFemale       : String? = null,
//@SerializedName("back_shiny"         ) var backShiny        : String? = null,
//@SerializedName("back_shiny_female"  ) var backShinyFemale  : String? = null,
//@SerializedName("front_default"      ) var frontDefault     : String? = null,
//@SerializedName("front_female"       ) var frontFemale      : String? = null,
//@SerializedName("front_shiny"        ) var frontShiny       : String? = null,
//@SerializedName("front_shiny_female" ) var frontShinyFemale : String? = null
//
//)
//
//data class Heartgold-soulsilver (
//
//@SerializedName("back_default"       ) var backDefault      : String? = null,
//@SerializedName("back_female"        ) var backFemale       : String? = null,
//@SerializedName("back_shiny"         ) var backShiny        : String? = null,
//@SerializedName("back_shiny_female"  ) var backShinyFemale  : String? = null,
//@SerializedName("front_default"      ) var frontDefault     : String? = null,
//@SerializedName("front_female"       ) var frontFemale      : String? = null,
//@SerializedName("front_shiny"        ) var frontShiny       : String? = null,
//@SerializedName("front_shiny_female" ) var frontShinyFemale : String? = null
//
//)
//
//data class Heartgold-soulsilver (
//
//@SerializedName("back_default"       ) var backDefault      : String? = null,
//@SerializedName("back_female"        ) var backFemale       : String? = null,
//@SerializedName("back_shiny"         ) var backShiny        : String? = null,
//@SerializedName("back_shiny_female"  ) var backShinyFemale  : String? = null,
//@SerializedName("front_default"      ) var frontDefault     : String? = null,
//@SerializedName("front_female"       ) var frontFemale      : String? = null,
//@SerializedName("front_shiny"        ) var frontShiny       : String? = null,
//@SerializedName("front_shiny_female" ) var frontShinyFemale : String? = null
//
//)
