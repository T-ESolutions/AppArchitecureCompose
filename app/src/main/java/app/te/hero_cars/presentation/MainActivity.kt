package app.te.hero_cars.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavHostController
import app.te.core.extension.navigateSafe
import app.te.core.theme.AppArchitectureTheme
import androidx.navigation.compose.rememberNavController
import app.te.core.BaseActivity
import app.te.hero_cars.presentation.bottom_bar.BottomBar
import app.te.hero_cars.presentation.bottom_bar.BottomBarScreen
import app.te.hero_cars.presentation.home.nav_graph.SetupNavGraph
import app.te.navigation.NavigationManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private lateinit var navHostController: NavHostController

    @Inject
    lateinit var navigationManager: NavigationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen().setOnExitAnimationListener{
            updateLocale("ar")
        }
        super.onCreate(savedInstanceState)
    }
    override fun setUpContent() {
        setContent {
            val showFloatingButton = remember { mutableStateOf(false) }
            val showBottomBar = remember { mutableStateOf(false) }
            AppArchitectureTheme {
                navHostController = rememberNavController()
                HandleNavigation(navHostController)
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

    @Composable
    private fun HandleNavigation(navHostController: NavHostController) {
        SideEffect {
            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.RESUMED) {
                    navigationManager.commands.collect { command ->
                        if (command.destination.isNotEmpty()) {
                            navHostController.navigateSafe(
                                command.destination,
                                popUpTo = command.popUpTo
                            )
                        }
                    }
                }
            }
        }
    }

}


