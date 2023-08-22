package te.app.settings.presentation.contact_us

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import app.te.core.utils.LoadAsyncImage
import te.app.settings.presentation.contact_us.ui_state.ContactUsUiState

@Composable
fun ContactItem(item: ContactUsUiState, onItemClick: (String) -> Unit) {
    LoadAsyncImage(
        url = item.image,
        modifier = Modifier
            .width(65.dp)
            .height(65.dp)
            .clip(MaterialTheme.shapes.medium)
            .clickable {
                onItemClick(item.link)
            }
    )
}