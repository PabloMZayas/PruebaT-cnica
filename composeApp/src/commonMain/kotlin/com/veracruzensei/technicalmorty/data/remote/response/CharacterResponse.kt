package com.veracruzensei.technicalmorty.data.remote.response

import com.veracruzensei.technicalmorty.domain.model.CharacterModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharacterResponse(
    @SerialName("id") val id: String = "",
    val status: String = "dead",
    val image: String = "",
    val name: String = "",
    val species: String = "",
    val gender: String = "",
    val origin: String = "",
    val location: String = ""
)

fun CharacterResponse.toDomain(): CharacterModel = CharacterModel(
    id = this.id,
    status = this.status,
    image = this.image,
    name = this.name,
    origin = this.origin,
    location = this.location,
    gender = this.gender,
    species = this.species
)
