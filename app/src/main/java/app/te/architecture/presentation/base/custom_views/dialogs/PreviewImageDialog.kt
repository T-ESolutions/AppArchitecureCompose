package app.te.architecture.presentation.base.custom_views.dialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import app.te.architecture.presentation.base.extensions.LoadAsyncImage


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun PreviewImageDialog(
    image: Any? = null,
    dismissOnBackPress: Boolean = true,
    dismissOnClickOutside: Boolean = true,
    onDismiss: () -> Unit,
) {
    Dialog(
        onDismissRequest = { onDismiss() },
        properties = DialogProperties(
            dismissOnBackPress = dismissOnBackPress,
            dismissOnClickOutside = dismissOnClickOutside,
            usePlatformDefaultWidth = false
        ),
    ) {
        val scale = remember { mutableStateOf(1f) }
        val rotationState = remember { mutableStateOf(1f) }
        Box(
            modifier = Modifier
                .clip(RectangleShape) // Clip the box content
                .fillMaxSize() // Give the size you want...
                .background(Color.Black)
                .pointerInput(Unit) {
                    detectTransformGestures { centroid, pan, zoom, rotation ->
                        scale.value *= zoom
                        rotationState.value += rotation
                    }
                }
        ) {

            Icon(
                imageVector = Icons.Filled.Close,
                contentDescription = "search",
                modifier = Modifier
                    .padding(10.dp)
                    .size(30.dp)
                    .clip(CircleShape)
                    .background(color = MaterialTheme.colorScheme.background)
                    .clickable { onDismiss() }
            )

            LoadAsyncImage(
                modifier = Modifier
                    .align(Alignment.Center) // keep the image centralized into the Box
                    .graphicsLayer(
                        // adding some zoom limits (min 50%, max 200%)
                        scaleX = maxOf(.5f, minOf(3f, scale.value)),
                        scaleY = maxOf(.5f, minOf(3f, scale.value)),
                        rotationZ = rotationState.value
                    ),
                url = image as String,
                contentScale = ContentScale.Fit
            )
        }

    }
}

@Composable
@Preview(showBackground = true)
fun PreviewImageDialogPreview() {
    PreviewImageDialog(
        image = "",
        dismissOnBackPress = true,
        dismissOnClickOutside = true,
        onDismiss = { /**/ },
    )
}
