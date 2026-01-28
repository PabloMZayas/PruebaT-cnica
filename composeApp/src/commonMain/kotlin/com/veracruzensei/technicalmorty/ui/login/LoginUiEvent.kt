package com.veracruzensei.technicalmorty.ui.login

sealed class LoginUiEvent {
    object NavigateToHome : LoginUiEvent()
    data class ShowSnackbar(val message: String) : LoginUiEvent()
}
