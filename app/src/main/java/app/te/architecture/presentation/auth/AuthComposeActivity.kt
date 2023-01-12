package app.te.architecture.presentation.auth

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.*
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import app.te.architecture.presentation.auth.nav_graph.AUTH_GRAPH_ROUTE
import app.te.architecture.presentation.auth.nav_graph.LOGIN_ROUTE
import app.te.architecture.presentation.auth.nav_graph.SPLASH_ROUTE
import app.te.architecture.presentation.auth.nav_graph.authNavGraph
import app.te.architecture.presentation.base.extensions.adjustFontScale
import app.te.architecture.presentation.ui.theme.AppArchitectureTheme
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
                LocalContext.current.adjustFontScale()
                navHostController = rememberAnimatedNavController()
                AnimatedNavHost(
                    navController = navHostController,
                    startDestination = AUTH_GRAPH_ROUTE,
                ) {
                    authNavGraph(
                        navHostController,
                        startDestination = if (intent.hasExtra("extra")) LOGIN_ROUTE else SPLASH_ROUTE
                    )
                }
            }

        }

    }

}

