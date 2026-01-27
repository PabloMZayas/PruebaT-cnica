package com.veracruzensei.technicalmorty.ui.core.bottomnavigation

sealed class RoutesBottom(val route: String) {
    data object Characters: RoutesBottom("characters")
    data object Locations: RoutesBottom("locations")
    data object Episodes: RoutesBottom("Episodes")
    data object Profile: RoutesBottom("profile")
}
