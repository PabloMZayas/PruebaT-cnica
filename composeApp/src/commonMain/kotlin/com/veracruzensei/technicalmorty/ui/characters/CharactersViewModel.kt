package com.veracruzensei.technicalmorty.ui.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.filter
import com.veracruzensei.technicalmorty.domain.GetSingleCharacter
import com.veracruzensei.technicalmorty.domain.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
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

    fun updateQuery(query: String) {
        _state.update { currentState ->

            val filteredFlow = currentState.characters.map { pagingData ->
                pagingData.filter { character ->
                    character.name.contains(query, ignoreCase = true)
                }
            }

            currentState.copy(
                query = query,
                characters = filteredFlow
            )
        }
    }

}
