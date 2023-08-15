package app.te.core.custom_views.alert

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import app.te.core.R
import app.te.core.custom_views.alert.TestTags.ALERTER
import app.te.core.custom_views.alert.TestTags.ALERTER_MESSAGE
import app.te.core.utils.ShowLottieLoader
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import java.util.*
import kotlin.concurrent.schedule

@Composable
fun rememberAlerterState(): AlerterState {
    return remember { AlerterState() }
}

@Composable
fun AlerterCustom(
    modifier: Modifier = Modifier,
    message: String,
    position: AlerterPosition = AlerterPosition.Bottom,
    duration: Long = 3000L,
    icon: Int = R.raw.success,
    containerColor: Color = Color.Gray,
    contentColor: Color = TextWhite,
    enterAnimation: EnterTransition = expandVertically(
        animationSpec = tween(delayMillis = 300),
        expandFrom = when (position) {
            is AlerterPosition.Top -> Alignment.Top
            is AlerterPosition.Bottom -> Alignment.Bottom
            is AlerterPosition.Float -> Alignment.CenterVertically
        }
    ),
    exitAnimation: ExitTransition = shrinkVertically(
        animationSpec = tween(delayMillis = 300),
        shrinkTowards = when (position) {
            is AlerterPosition.Top -> Alignment.Top
            is AlerterPosition.Bottom -> Alignment.Bottom
            is AlerterPosition.Float -> Alignment.CenterVertically
        }
    ),
    verticalPadding: Dp = 12.dp,
    horizontalPadding: Dp = 12.dp
) {
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        AlerterComponent(
            message,
            duration,
            position,
            containerColor,
            contentColor,
            verticalPadding,
            horizontalPadding,
            icon,
            enterAnimation,
            exitAnimation
        )
    }
}

@Composable
fun AlerterError(
    modifier: Modifier = Modifier,
    message: String,
    position: AlerterPosition = AlerterPosition.Float,
    duration: Long = 3000L,
    icon: Int = R.raw.error
) {
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        AlerterComponent(
            message = message,
            duration = duration,
            position = position,
            containerColor = BrightRed,
            contentColor = TextWhite,
            verticalPadding = 12.dp,
            horizontalPadding = 12.dp,
            icon = icon,
            enterAnimation = expandVertically(
                animationSpec = tween(delayMillis = 300),
                expandFrom = when (position) {
                    is AlerterPosition.Top -> Alignment.Top
                    is AlerterPosition.Bottom -> Alignment.Bottom
                    is AlerterPosition.Float -> Alignment.CenterVertically
                }
            ),
            exitAnimation = shrinkVertically(
                animationSpec = tween(delayMillis = 300),
                shrinkTowards = when (position) {
                    is AlerterPosition.Top -> Alignment.Top
                    is AlerterPosition.Bottom -> Alignment.Bottom
                    is AlerterPosition.Float -> Alignment.CenterVertically
                }
            )
        )
    }
}

@Composable
fun AlerterSuccess(
    message: String,
    modifier: Modifier = Modifier,
    position: AlerterPosition = AlerterPosition.Top,
    duration: Long = 3000L,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        AlerterComponent(
            message = message,
            duration = duration,
            position = position,
            containerColor = BrightGreen,
            contentColor = TextWhite,
            verticalPadding = 12.dp,
            horizontalPadding = 12.dp,
            icon = R.raw.success,
            enterAnimation = expandVertically(
                animationSpec = tween(delayMillis = 300),
                expandFrom = when (position) {
                    is AlerterPosition.Top -> Alignment.Top
                    is AlerterPosition.Bottom -> Alignment.Bottom
                    is AlerterPosition.Float -> Alignment.CenterVertically
                }
            ),
            exitAnimation = shrinkVertically(
                animationSpec = tween(delayMillis = 300),
                shrinkTowards = when (position) {
                    is AlerterPosition.Top -> Alignment.Top
                    is AlerterPosition.Bottom -> Alignment.Bottom
                    is AlerterPosition.Float -> Alignment.CenterVertically
                }
            )
        )
    }
}


@Composable
internal fun AlerterComponent(
    message: String,
    duration: Long,
    position: AlerterPosition,
    containerColor: Color,
    contentColor: Color,
    verticalPadding: Dp,
    horizontalPadding: Dp,
    icon: Int,
    enterAnimation: EnterTransition = fadeIn(),
    exitAnimation: ExitTransition = fadeOut(),
) {
    var showAlerter by remember { mutableStateOf(false) }

    DisposableEffect(
        key1 = true
    ) {
        showAlerter = true
        val timer = Timer("Animation Timer", true)
        timer.schedule(duration) {
            showAlerter = false
        }
        onDispose {
            timer.cancel()
            timer.purge()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                bottom = when (position) {
                    is AlerterPosition.Top -> 0.dp
                    is AlerterPosition.Bottom -> 0.dp
                    is AlerterPosition.Float -> 24.dp
                }
            ),
        verticalArrangement = when (position) {
            is AlerterPosition.Top -> Arrangement.Center
            is AlerterPosition.Bottom -> Arrangement.Center
            is AlerterPosition.Float -> Arrangement.Center
        },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AnimatedVisibility(
            visible = showAlerter,
            enter = when (position) {
                is AlerterPosition.Top -> enterAnimation
                is AlerterPosition.Bottom -> enterAnimation
                is AlerterPosition.Float -> fadeIn()
            },
            exit = when (position) {
                is AlerterPosition.Top -> exitAnimation
                is AlerterPosition.Bottom -> exitAnimation
                is AlerterPosition.Float -> fadeOut()
            }
        ) {
            Alerter(
                message,
                position,
                containerColor,
                contentColor,
                verticalPadding,
                horizontalPadding,
                icon
            )
        }
    }
}


@Composable
internal fun Alerter(
    message: String?,
    position: AlerterPosition,
    containerColor: Color,
    contentColor: Color,
    verticalPadding: Dp,
    horizontalPadding: Dp,
    icon: Int,
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(icon))
    val progress by animateLottieCompositionAsState(composition, iterations = Int.MAX_VALUE)
    Row(
        modifier = Modifier
            .fillMaxWidth(
                fraction = when (position) {
                    is AlerterPosition.Top -> 1f
                    is AlerterPosition.Bottom -> 1f
                    is AlerterPosition.Float -> 0.8f
                }
            )
            .background(
                color = containerColor,
                shape = when (position) {
                    is AlerterPosition.Top -> RectangleShape
                    is AlerterPosition.Bottom -> RectangleShape
                    is AlerterPosition.Float -> RoundedCornerShape(8.dp)
                }
            )
            .padding(vertical = verticalPadding)
            .padding(horizontal = horizontalPadding)
            .animateContentSize()
            .testTag(ALERTER),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier.weight(4f),
            verticalAlignment = Alignment.CenterVertically
        ) {
//            ShowLottieLoader(
//                modifier = Modifier
//                    .width(60.dp)
//                    .height(60.dp),
//                icon
//            )
            LottieAnimation(
                composition = composition,
                progress = { progress },
                modifier = Modifier
                    .width(60.dp)
                    .height(60.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))

            Text(
                modifier = Modifier.testTag(ALERTER_MESSAGE),
                text = message ?: "Unknown",
                color = contentColor,
                style = MaterialTheme.typography.labelSmall,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
        }
    }
}

val BrightGreen = Color(0xFF19B661)
val BrightRed = Color(0xFFE8503A)
val TextWhite = Color(0xFFEEEEEE)