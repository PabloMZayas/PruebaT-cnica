package com.veracruzensei.technicalmorty.di

import com.veracruzensei.technicalmorty.ui.characters.CharactersViewModel
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val uiModule = module {
    viewModelOf(::CharactersViewModel)
}