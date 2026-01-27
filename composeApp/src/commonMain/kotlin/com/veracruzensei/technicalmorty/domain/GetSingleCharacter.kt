package com.veracruzensei.technicalmorty.domain

import com.veracruzensei.technicalmorty.domain.model.CharacterModel

class GetSingleCharacter(private val repository: Repository) {
    suspend  fun getSingleCharacter(id: Int): CharacterModel {
        return repository.getSingleCharacter(id)
    }
}
