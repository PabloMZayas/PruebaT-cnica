package com.veracruzensei.technicalmorty.data.remote.response

import com.veracruzensei.technicalmorty.domain.model.CharacterModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharacterResponse(
    @SerialName("id") val id: Int = 1,
    val status: String = "dead",
    val image: String = "",
    val name: String = "",
    val species: String = "",
    val gender: String = "",

    val origin: OriginResponse = OriginResponse(),
    val location: LocationResponse = LocationResponse(),

    val episode: List<String> = emptyList()
)

fun CharacterResponse.toDomain(): CharacterModel = CharacterModel(
    id = id,
    status = status,
    image = image,
    name = name,
    species = species,
    gender = gender,
    originName = origin.name,
    locationName = location.name,
    episodes = episode
)

@Serializable
data class LocationResponse(
    val name: String = "",
    val url: String = ""
)

@Serializable
data class OriginResponse(
    val name: String = "",
    val url: String = ""
)
