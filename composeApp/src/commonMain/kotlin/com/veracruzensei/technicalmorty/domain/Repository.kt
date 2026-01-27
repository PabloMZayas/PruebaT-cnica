package com.veracruzensei.technicalmorty.domain

import androidx.paging.PagingData
import com.veracruzensei.technicalmorty.domain.model.CharacterModel
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun getSingleCharacter(id: Int): CharacterModel
    fun getAllCharacters(): Flow<PagingData<CharacterModel>>
}