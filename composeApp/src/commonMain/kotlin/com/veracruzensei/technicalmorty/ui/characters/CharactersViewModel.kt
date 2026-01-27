package com.veracruzensei.technicalmorty.ui.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.veracruzensei.technicalmorty.domain.GetSingleCharacter
import com.veracruzensei.technicalmorty.domain.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class CharactersViewModel(
    val getRandomCharacter: GetSingleCharacter,
    private val repository: Repository
): ViewModel() {

    private val _state = MutableStateFlow<CharactersState>(CharactersState())
    val state: StateFlow<CharactersState> = _state

    init {
        getAllCharacters()
    }

    private fun getAllCharacters() {
        _state.update { state ->
            state.copy( characters = repository.getAllCharacters().cachedIn(viewModelScope) )
        }
    }
}
