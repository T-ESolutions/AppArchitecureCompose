package app.te.core.utils

import android.os.Build
import android.text.Html
import android.widget.TextView
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import app.te.core.R
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import coil.request.ImageRequest
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition

@OptIn(ExperimentalMaterial3Api::class)
@Stable
@Composable
fun CenterAlignedTopAppBarCustom(
    modifier: Modifier = Modifier,
    navHostController: NavHostController? = null,
    title: Int? = null,
    navigationIcon: Boolean = true,
    backgroundColor: Color = MaterialTheme.colorScheme.primary,
    onNavigateClick: (() -> Unit)? = null
) {
    CenterAlignedTopAppBar(
        title = {
            if (title != null)
                Text(
                    text = stringResource(id = title),
                    color = MaterialTheme.colorScheme.background,
                    style = MaterialTheme.typography.titleMedium,
                )
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = backgroundColor),
        navigationIcon = {
            if (navigationIcon)
                IconButton(onClick = {
                    if (navHostController != null)
                        navHostController.navigateUp()
                    else
                        onNavigateClick?.invoke()
                }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "back",
                        tint = Color.White,
                        modifier = Modifier.mirror()
                    )
                }
        },
        modifier = modifier
    )
}

@Composable
fun ShowLottieLoader(modifier: Modifier, icon: Int) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(icon))
    val progress by animateLottieCompositionAsState(composition, iterations = Int.MAX_VALUE)
    LottieAnimation(
        composition = composition,
        progress = { progress },
        modifier = modifier
            .width(60.dp)
            .height(60.dp)
    )
}

@Composable
fun LoadAsyncImage(
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.None,
    url: String = ""
) {
    SubcomposeAsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(url)
            .placeholder(R.drawable.logo)
            .error(R.drawable.logo)
            .crossfade(true)
            .build(),
        contentDescription = "Image1",
        contentScale = contentScale,
        modifier = modifier
    ) {
        when (painter.state) {
            is AsyncImagePainter.State.Loading -> {
                ShimmerImageLoader()
            }

            else -> {
                SubcomposeAsyncImageContent()
            }
        }

    }
}

@Composable
fun ShimmerImageLoader() {
    val animatedShimmer = animatedShimmer()

    Column(modifier = Modifier.fillMaxWidth()) {
        Spacer(
            modifier = Modifier
                .fillMaxSize()
                .background(animatedShimmer)
        )
    }
}

@Composable
fun animatedShimmer(): Brush {
    val shimmerColors = listOf(
        Color.LightGray.copy(alpha = 0.6f),
        Color.LightGray.copy(alpha = 0.2f),
        Color.LightGray.copy(alpha = 0.6f),
    )

    val transition = rememberInfiniteTransition()
    val translateAnim = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = FastOutSlowInEasing
            ),
            repeatMode = RepeatMode.Reverse
        )
    )

    return Brush.linearGradient(
        colors = shimmerColors,
        start = Offset.Zero,
        end = Offset(x = translateAnim.value, y = translateAnim.value)
    )

}

@Composable
fun textFieldInteractionSource(onClick: () -> Unit): MutableInteractionSource {
    return remember { MutableInteractionSource() }
        .also { interactionSource ->
            LaunchedEffect(interactionSource) {
                interactionSource.interactions.collect {
                    if (it is PressInteraction.Release) {
                        onClick()
                    }
                }
            }
        }

}

@Composable
fun TextHtml(text: String, modifier: Modifier) {
    AndroidView(
        factory = { context -> TextView(context) },
        update = {
            it.text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                Html.fromHtml(text, Html.FROM_HTML_MODE_COMPACT)
            } else
                Html.fromHtml(text)

        },
        modifier = modifier
    )
}

@Composable
fun InputError(fieldError:String?=null) {
    if (fieldError != null) {
        Text(
            text = fieldError,
            color = MaterialTheme.colorScheme.error,
            textAlign = TextAlign.Start,
            style = TextStyle(
                fontSize = MaterialTheme.typography.labelMedium.fontSize,
                color = MaterialTheme.colorScheme.error,
                fontWeight = FontWeight(200)
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 15.dp, top = 2.dp)
        )
    }
}