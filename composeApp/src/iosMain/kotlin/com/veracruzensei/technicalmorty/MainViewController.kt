package com.veracruzensei.technicalmorty

import androidx.compose.ui.window.ComposeUIViewController
import com.veracruzensei.technicalmorty.di.initKoin

fun MainViewController() = ComposeUIViewController(configure = { initKoin() }) { App() }