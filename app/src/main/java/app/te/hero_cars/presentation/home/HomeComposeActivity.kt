package app.te.hero_cars.presentation.home

import android.annotation.SuppressLint
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import app.te.core.filepicker.core.FilePicker
import app.te.hero_cars.presentation.base.BaseActivity
import app.te.hero_cars.presentation.base.extensions.adjustFontScale
import app.te.core.extension.navigateSafe
import app.te.core.theme.AppArchitectureTheme
import app.te.hero_cars.presentation.home.nav_graph.BottomBarScreen
import app.te.hero_cars.presentation.home.nav_graph.SetupNavGraph
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@AndroidEntryPoint
class HomeComposeActivity : BaseActivity() {
    private lateinit var navHostController: NavHostController
    lateinit var filePicker: FilePicker
    override fun setUpContent() {
        filePicker = FilePicker.getInstance(this)
        setContent {
            val showFloatingButton = remember { mutableStateOf(true) }
            val showBottomBar = remember { mutableStateOf(true) }
            AppArchitectureTheme {
                LocalContext.current.adjustFontScale()
                navHostController = rememberNavController()
                Scaffold(
                    bottomBar = {
                        if (showBottomBar.value)
                            BottomBar(navHostController = navHostController)
                    }
                ) {
                    SetupNavGraph(navHostController = navHostController)
                }
            }

            navHostController.addOnDestinationChangedListener { _, destination, _ ->
                if (destination.route == BottomBarScreen.Home.route
                    || destination.route == BottomBarScreen.Account.route
                    || destination.route == BottomBarScreen.More.route
                ) {
                    showFloatingButton.value = true
                    showBottomBar.value = true
                } else {
                    showFloatingButton.value = false
                    showBottomBar.value = false
                }
            }
        }

    }


}

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

