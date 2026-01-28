package com.veracruzensei.technicalmorty.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class CharacterModel(
    val id: Int,
    val status: String,
    val image: String,
    val name: String,
    val species: String,
    val gender: String,
    val originName: String,
    val locationName: String,
    val episodes: List<String>
)
