package te.app.auth.presentation

import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.*
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import te.app.auth.presentation.nav_graph.AUTH_GRAPH_ROUTE
import te.app.auth.presentation.nav_graph.LOGIN_ROUTE
import te.app.auth.presentation.nav_graph.SPLASH_ROUTE
import te.app.auth.presentation.nav_graph.authNavGraph
import app.te.hero_cars.presentation.base.BaseActivity
import app.te.hero_cars.presentation.base.extensions.adjustFontScale
import app.te.core.theme.AppArchitectureTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class AuthComposeActivity : BaseActivity() {
    private lateinit var navHostController: NavHostController

    @OptIn(ExperimentalAnimationApi::class)
    override fun setUpContent() {
        
        setContent {
            AppArchitectureTheme {
                LocalContext.current.adjustFontScale()
                navHostController = rememberNavController()
                NavHost(
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

