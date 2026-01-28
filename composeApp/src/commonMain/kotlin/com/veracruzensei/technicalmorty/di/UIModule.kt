package com.veracruzensei.technicalmorty.di

import com.veracruzensei.technicalmorty.ui.characters.CharactersViewModel
import com.veracruzensei.technicalmorty.ui.detail.CharacterDetailViewModel
import com.veracruzensei.technicalmorty.ui.login.LoginViewModel
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val uiModule = module {
    viewModelOf(::CharactersViewModel)
    viewModelOf(::CharacterDetailViewModel)
    viewModelOf(::LoginViewModel)
}