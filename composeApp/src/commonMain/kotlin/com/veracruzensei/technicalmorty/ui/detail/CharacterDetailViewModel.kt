package com.veracruzensei.technicalmorty.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.veracruzensei.technicalmorty.domain.GetSingleCharacter
import com.veracruzensei.technicalmorty.domain.model.CharacterModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CharacterDetailViewModel(
    private val getSingleCharacter: GetSingleCharacter
): ViewModel() {

    private val _state = MutableStateFlow(CharacterDetailState())
    val state: StateFlow<CharacterDetailState> = _state

    init {
        getCharacter()
    }

    private fun getCharacter() {
        viewModelScope.launch(Dispatchers.IO) {
            _state.update { state ->
                state.copy(
                    characterModel = getSingleCharacter.getSingleCharacter(2)
                )
            }
        }
    }
}
