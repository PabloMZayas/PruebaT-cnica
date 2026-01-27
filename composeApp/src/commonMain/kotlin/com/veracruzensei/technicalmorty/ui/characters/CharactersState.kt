package com.veracruzensei.technicalmorty.ui.characters

import androidx.paging.PagingData
import com.veracruzensei.technicalmorty.domain.model.CharacterModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class CharactersState (
    val characters: Flow<PagingData<CharacterModel>> = emptyFlow()
)