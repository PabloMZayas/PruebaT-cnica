package com.veracruzensei.technicalmorty.ui.core.bottomnavigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.veracruzensei.technicalmorty.domain.model.CharacterModel
import com.veracruzensei.technicalmorty.ui.characters.CharactersScreen
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Composable
fun NavigationBottomWrapper(
    navController: NavHostController,
    navigateToDetailScreen: (CharacterModel) -> Unit,
    mainNavController: NavHostController
) {

    NavHost(navController = navController, startDestination = RoutesBottom.Characters.route) {
        composable(RoutesBottom.Characters.route) {
            CharactersScreen(navigateToDetailScreen = navigateToDetailScreen)
        }
        composable(route = RoutesBottom.Characters.route) {
            CharactersScreen(
                navigateToDetailScreen = { characterModel ->
                    val encode: String = Json.encodeToString(characterModel)
                    mainNavController.navigate(CharacterDetail(encode))
                }
            )
        }
        composable(RoutesBottom.Locations.route) {

        }
        composable(RoutesBottom.Episodes.route) {

        }
        composable(RoutesBottom.Profile.route) {

        }
    }
}

@Serializable
data class CharacterDetail(val characterModel: String)
