package app.te.hero_cars.presentation.bottom_bar

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import app.te.core.extension.navigateSafe

@Composable
fun BottomBar(navHostController: NavHostController) {
    val bottomList = listOf(
        BottomBarScreen.Home,
        BottomBarScreen.Account,
        BottomBarScreen.More,
    )
    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.primary
    ) {
        bottomList.forEach { screen ->
            AddItem(
                screen = screen,
                currentDestination = currentDestination,
                navHostController = navHostController
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navHostController: NavHostController
) {
    NavigationBarItem(
        label = {
            Text(
                text = stringResource(id = screen.title),
                style = MaterialTheme.typography.labelMedium,
            )
        },
        icon = {
            Icon(imageVector = screen.icon, contentDescription = "ICON")
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        onClick = {
            navHostController.navigateSafe(
                screen.route,
                launchSingleTop = true,
                popUpToId = navHostController.graph.findStartDestination().id
            )
        },
        alwaysShowLabel = true,
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = MaterialTheme.colorScheme.primary,
            selectedTextColor = MaterialTheme.colorScheme.background,
            indicatorColor = MaterialTheme.colorScheme.background,
            unselectedIconColor = MaterialTheme.colorScheme.primaryContainer,
            unselectedTextColor = MaterialTheme.colorScheme.primaryContainer
        )
    )
}

