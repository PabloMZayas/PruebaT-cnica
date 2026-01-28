package com.veracruzensei.technicalmorty.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel: ViewModel() {

    private val _state = MutableStateFlow(LoginState())
    val state: StateFlow<LoginState> = _state

    private val _uiEvent = Channel<LoginUiEvent>(Channel.BUFFERED)
    val uiEvent = _uiEvent.receiveAsFlow()

    fun updateUserInput(user: String) {
        _state.update { state ->
            state.copy(
                user = user
            )
        }
    }

    fun updatePasswordInput(password: String) {
        _state.update { state ->
            state.copy(
                password = password
            )
        }
    }

    fun checkCredentials() {
        val rightUser = "usuario"
        val rightPassword = "contraseña"
        val inputUser = _state.value.user.trim()
        val inputPassword = _state.value.password.trim()

        viewModelScope.launch {
            if (inputUser == rightUser && inputPassword == rightPassword) {
                _uiEvent.send(LoginUiEvent.NavigateToHome)
            } else {
                _uiEvent.send(LoginUiEvent.ShowSnackbar("Usuario o contraseña incorrectos"))
            }
        }
    }
}
