package app.te.architecture.presentation.base.extensions

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import app.te.architecture.R
import app.te.architecture.presentation.base.utils.animatedShimmer
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import coil.request.ImageRequest
import kotlin.math.abs

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


fun String.isNumeric(toCheck: String): Boolean {
    val regex = "-?[0-9]+(\\.[0-9]+)?".toRegex()
    return toCheck.matches(regex)
}

fun Long.toStringMatch(): String {
    return if (abs(this / 1000000) >= 1) {
        (this / 1000000).toString().plus("m")

    } else if (this / 1000 >= 1) {
        (this / 1000).toString().plus("k")
    } else {
        this.toString()
    }
}

