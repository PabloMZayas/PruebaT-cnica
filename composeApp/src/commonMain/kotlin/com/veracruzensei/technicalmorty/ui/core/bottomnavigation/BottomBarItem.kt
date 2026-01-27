package com.veracruzensei.technicalmorty.ui.core.bottomnavigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import com.tuempresa.tuapp.generated.resources.Res
import com.tuempresa.tuapp.generated.resources.icon_characters
import com.tuempresa.tuapp.generated.resources.icon_episodes
import com.tuempresa.tuapp.generated.resources.icon_locations
import com.tuempresa.tuapp.generated.resources.icon_profile
import org.jetbrains.compose.resources.painterResource

sealed class BottomBarItem {
    abstract val route: String
    abstract val title: String
    abstract val icon: @Composable () -> Unit

    data class Menu(
        override val route: String = RoutesBottom.Characters.route,
        override val title: String = "Characters",
        override val icon: @Composable () -> Unit = {
            Icon(painter = painterResource(Res.drawable.icon_characters), contentDescription = null)
        }
    ): BottomBarItem()

    data class TabLocations(
        override val route: String = RoutesBottom.Locations.route,
        override val title: String = "Locations",
        override val icon: @Composable () -> Unit = {
            Icon(painter = painterResource(Res.drawable.icon_locations), contentDescription = null)
        }
    ): BottomBarItem()

    data class TabVideos(
        override val route: String = RoutesBottom.Episodes.route,
        override val title: String = "Episodes",
        override val icon: @Composable () -> Unit = {
            Icon(painter = painterResource(Res.drawable.icon_episodes), contentDescription = null)
        }
    ): BottomBarItem()

    data class TabProfile(
        override val route: String = RoutesBottom.Profile.route,
        override val title: String = "Profile",
        override val icon: @Composable () -> Unit = {
            Icon(painter = painterResource(Res.drawable.icon_profile), contentDescription = null)
        }
    ): BottomBarItem()
}
