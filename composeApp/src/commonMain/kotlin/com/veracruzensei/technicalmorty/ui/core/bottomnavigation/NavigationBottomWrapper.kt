package com.veracruzensei.technicalmorty.ui.core.bottomnavigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.veracruzensei.technicalmorty.ui.characters.CharactersScreen

@Composable
fun NavigationBottomWrapper(
    navController: NavHostController,
    navigateToDetailScreen: () -> Unit
) {

    NavHost(navController = navController, startDestination = RoutesBottom.Characters.route) {
        composable(RoutesBottom.Characters.route) {
            CharactersScreen(navigateToDetailScreen = navigateToDetailScreen)
        }
        composable(RoutesBottom.Locations.route) {

        }
        composable(RoutesBottom.Episodes.route) {

        }
        composable(RoutesBottom.Profile.route) {

        }
    }
}
