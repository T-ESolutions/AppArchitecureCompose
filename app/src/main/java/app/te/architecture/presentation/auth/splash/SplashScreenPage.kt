package app.te.architecture.presentation.auth.splash

import android.util.Log
import android.view.animation.OvershootInterpolator
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import app.te.architecture.R
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.draw.scale
import app.te.architecture.presentation.auth.nav_graph.AuthScreens
import app.te.architecture.presentation.base.extensions.navigateSafe
import kotlinx.coroutines.delay
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SplashScreenPage(
    navHostController: NavHostController = rememberAnimatedNavController(),
    viewModel: SplashViewModel = viewModel()
) {
    val splashState = viewModel.splashState.collectAsState()

    val scale = remember {
        Animatable(0f)
    }
    LaunchedEffect(key1 = true) {

        viewModel.checkFirstTime()

        scale.animateTo(
            targetValue = 0.3f,
            animationSpec = tween(
                durationMillis = 500,
                easing = {
                    OvershootInterpolator(2f).getInterpolation(it)
                }
            )
        )

        delay(3000L)
        Log.e("SplashScreenPage", "SplashScreenPage: "+splashState.value.openTutorialScreen)
        if (splashState.value.openTutorialScreen)
            openTutorialScreen(navHostController)
        else
            openLoginScreen(navHostController)
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "logo",
            modifier = Modifier
                .size(400.dp)
                .align(Alignment.Center)
                .scale(scale.value)
        )

        Image(
            painter = painterResource(id = R.drawable.tes),
            contentDescription = "logo",
            modifier = Modifier
                .size(50.dp)
                .align(Alignment.BottomCenter)
        )

    }
}

fun openLoginScreen(navHostController: NavHostController) {
    navHostController.navigateSafe(
        AuthScreens.LoginScreen.route,
        popUpTo = AuthScreens.SplashScreen.route
    )
}

fun openTutorialScreen(navHostController: NavHostController) {
    navHostController.navigateSafe(
        AuthScreens.OnBoardingScreen.route,
        popUpTo = AuthScreens.SplashScreen.route
    )
}

@Composable
@Preview(showBackground = true)
fun SplashScreenPagePreview() {
    SplashScreenPage(
        navHostController = rememberNavController()
    )
}