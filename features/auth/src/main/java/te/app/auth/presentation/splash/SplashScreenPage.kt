package te.app.auth.presentation.splash

import android.view.animation.OvershootInterpolator
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
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.tween
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import app.te.core.BaseActivity
import app.te.core.extension.findActivity

@Composable
fun SplashScreenPage(
    viewModel: SplashViewModel = viewModel()
) {
    val splashState by viewModel.splashState.collectAsStateWithLifecycle()
    val activity = LocalContext.current.findActivity()

    val scale = remember {
        Animatable(0f)
    }
    LaunchedEffect(key1 = true) {
        viewModel.checkFirstTime()
        scale.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 500,
                easing = {
                    OvershootInterpolator(2f).getInterpolation(it)
                }
            )
        )
//        if (splashState.openTutorialScreen)
//            (activity as BaseActivity).updateLocale("ar")

    }
    SplashScreenUI(scale)
}

@Composable
fun SplashScreenUI(scale: Animatable<Float, AnimationVector1D> = Animatable(0f)) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onBackground)
    ) {
        Image(
            painter = painterResource(id = app.te.core.R.drawable.logo),
            contentDescription = "logo",
            modifier = Modifier
                .align(Alignment.Center)
                .scale(scale.value)
        )

        Image(
            painter = painterResource(id = app.te.core.R.drawable.tes),
            contentDescription = "logo",
            modifier = Modifier
                .size(50.dp)
                .align(Alignment.BottomCenter)
        )

    }
}


@Composable
@Preview(showBackground = true)
fun SplashScreenPagePreview() {
    SplashScreenUI()
}