package app.te.architecture.presentation.base.extensions

import androidx.appcompat.widget.SearchView
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import app.te.architecture.R
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import coil.request.ImageRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlin.math.abs

@Composable
fun LoadAsyncImage(
    url: String = "",
    modifier: Modifier = Modifier
) {
    SubcomposeAsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(url)
            .placeholder(R.drawable.logo)
            .error(R.drawable.logo)
            .crossfade(true)
            .build(),
        contentDescription = "Image1",
        modifier = modifier
    ) {
        when (painter.state) {
            is AsyncImagePainter.State.Loading -> {
                CircularProgressIndicator()
            }
            else -> {
                SubcomposeAsyncImageContent()
            }
        }

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

fun SearchView.getQueryTextChangeStateFlow(): StateFlow<String> {

    val query = MutableStateFlow("")

    setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            return true
        }

        override fun onQueryTextChange(newText: String): Boolean {
            query.value = newText
            return true
        }
    })

    return query

}