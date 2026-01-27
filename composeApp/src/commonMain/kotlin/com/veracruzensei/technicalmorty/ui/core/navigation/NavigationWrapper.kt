package com.veracruzensei.technicalmorty.ui.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.veracruzensei.technicalmorty.ui.detail.CharacterDetailScreen
import com.veracruzensei.technicalmorty.ui.home.HomeScreen

@Composable
fun NavigationWrapper() {

    val mainNavController = rememberNavController()

    NavHost(navController = mainNavController, startDestination = Home) {
        composable<Home> {
            HomeScreen(mainNavController = mainNavController)
        }
        composable<Detail> {
            CharacterDetailScreen()
        }
    }
}
