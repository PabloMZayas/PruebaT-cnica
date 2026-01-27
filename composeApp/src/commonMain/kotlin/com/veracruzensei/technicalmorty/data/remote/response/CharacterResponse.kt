package com.veracruzensei.technicalmorty.data.remote.response

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
