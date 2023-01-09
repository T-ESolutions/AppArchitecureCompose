package app.te.architecture.presentation.auth

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.*
import androidx.navigation.NavHostController
import app.te.architecture.presentation.auth.nav_graph.AUTH_GRAPH_ROUTE
import app.te.architecture.presentation.auth.nav_graph.authNavGraph
import app.te.architecture.presentation.auth.ui.theme.AppArchitectureTheme
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthComposeActivity : ComponentActivity() {
    private lateinit var navHostController: NavHostController

    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppArchitectureTheme {
                navHostController = rememberAnimatedNavController()

                AnimatedNavHost(
                    navController = navHostController,
                    startDestination = AUTH_GRAPH_ROUTE,
                ) {
                    authNavGraph(navHostController)
                }
            }

        }

    }

}

