package com.veracruzensei.technicalmorty.domain

import com.veracruzensei.technicalmorty.domain.model.CharacterModel

interface Repository {

    suspend fun getSingleCharacter(id: String): CharacterModel
}