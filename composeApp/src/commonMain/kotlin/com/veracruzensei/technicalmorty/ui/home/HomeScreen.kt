package com.veracruzensei.technicalmorty.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.veracruzensei.technicalmorty.ui.core.bottomnavigation.BottomBarItem
import com.veracruzensei.technicalmorty.ui.core.bottomnavigation.NavigationBottomWrapper
import com.veracruzensei.technicalmorty.ui.core.navigation.Detail

@Composable
fun HomeScreen(
    mainNavController: NavHostController,
    navigateToDetailScreen: () -> Unit = {}
) {
    val items = listOf(
        BottomBarItem.Menu(),
        BottomBarItem.TabLocations(),
        BottomBarItem.TabVideos(),
        BottomBarItem.TabProfile())
    val bottomNavController = rememberNavController()

    Scaffold (bottomBar = { BottomNavigation(items, bottomNavController) }) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            NavigationBottomWrapper(
                bottomNavController,
                navigateToDetailScreen = { navigateToDetailScreen() }
            )
        }
    }
}

@Composable
fun BottomNavigation(items: List<BottomBarItem>, navController: NavHostController) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar {
        items.forEach { item ->
            NavigationBarItem(
                label = { Text(item.title) },
                onClick = {
                    navController.navigate(item.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = item.icon,
                selected = currentDestination?.hierarchy?.any { it.route == item.route } == true
            )
        }
    }
}
