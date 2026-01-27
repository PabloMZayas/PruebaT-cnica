package com.veracruzensei.technicalmorty.di

import com.veracruzensei.technicalmorty.domain.GetSingleCharacter
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val DomainModule = module {
    factoryOf(::GetSingleCharacter)
}