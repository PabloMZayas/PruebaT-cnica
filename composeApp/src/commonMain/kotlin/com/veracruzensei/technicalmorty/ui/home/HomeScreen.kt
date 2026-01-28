package com.veracruzensei.technicalmorty.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.veracruzensei.technicalmorty.ui.core.bottomnavigation.BottomBarItem
import com.veracruzensei.technicalmorty.ui.core.bottomnavigation.NavigationBottomWrapper
import com.veracruzensei.technicalmorty.ui.core.colors.BackgroundPrimaryColor
import com.veracruzensei.technicalmorty.ui.core.colors.FontPrimaryColor

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

    Scaffold (
        modifier = Modifier.background(BackgroundPrimaryColor),
        bottomBar = { BottomNavigation(items = items, navController = bottomNavController) }
    ) { paddingValues ->
        Box(modifier = Modifier.background(BackgroundPrimaryColor).padding(paddingValues)) {
            NavigationBottomWrapper(
                navController = bottomNavController,
                navigateToDetailScreen = { navigateToDetailScreen() },
                mainNavController = mainNavController
            )
        }
    }
}

@Composable
fun BottomNavigation(items: List<BottomBarItem>, navController: NavHostController) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar(
        containerColor = BackgroundPrimaryColor.copy(0.5f)
    ) {
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
                selected = currentDestination?.hierarchy?.any { it.route == item.route } == true,
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = FontPrimaryColor,
                    indicatorColor = Color.Transparent,
                    selectedTextColor = FontPrimaryColor,
                    unselectedTextColor = FontPrimaryColor.copy(0.5f),
                    unselectedIconColor = FontPrimaryColor.copy(0.5f)
                )
            )
        }
    }
}
