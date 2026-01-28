package com.veracruzensei.technicalmorty.ui.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.veracruzensei.technicalmorty.domain.model.CharacterModel
import com.veracruzensei.technicalmorty.ui.core.bottomnavigation.CharacterDetail
import com.veracruzensei.technicalmorty.ui.detail.CharacterDetailScreen
import com.veracruzensei.technicalmorty.ui.home.HomeScreen
import kotlinx.serialization.json.Json

@Composable
fun NavigationWrapper() {

    val mainNavController = rememberNavController()

    NavHost(navController = mainNavController, startDestination = Home) {
        composable<Home> {
            HomeScreen(mainNavController = mainNavController)
        }
        composable<CharacterDetail> { navBackStackEntry ->
            val characterDetailEncoding = navBackStackEntry.toRoute<CharacterDetail>()
            val characterModel = Json.decodeFromString<CharacterModel>(characterDetailEncoding.characterModel)
            CharacterDetailScreen(
                characterModel = characterModel,
                onBack = { mainNavController.popBackStack() }
            )
        }
    }
}
