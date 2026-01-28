package com.veracruzensei.technicalmorty.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class CharacterModel(
    val id: Int = 1,
    val status: String = "",
    val image: String = "",
    val name: String = "",
    val species: String = "",
    val gender: String = "",
    //val origin: String = "",
    //val location: String = "",
)
