package app.te.architecture.presentation.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import app.te.architecture.presentation.base.extensions.adjustFontScale
import app.te.architecture.presentation.base.extensions.navigateSafe
import app.te.architecture.presentation.ui.theme.AppArchitectureTheme
import app.te.architecture.presentation.home.nav_graph.BottomBarScreen
import app.te.architecture.presentation.home.nav_graph.SetupNavGraph
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dagger.hilt.android.AndroidEntryPoint

@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterial3Api::class)
@AndroidEntryPoint
class HomeComposeActivity : ComponentActivity() {
    private lateinit var navHostController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppArchitectureTheme {
                LocalContext.current.adjustFontScale()
                navHostController = rememberAnimatedNavController()
                Scaffold(
                    bottomBar = { BottomBar(navHostController = navHostController) }
                ) { paddingValues ->
                    paddingValues
                    SetupNavGraph(navHostController = navHostController)
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

