package app.te.architecture.data.settings

import app.te.architecture.domain.settings.models.AboutData
import app.te.architecture.presentation.settings.ui_state.AboutDataUiState

fun AboutData.mapToUiState(): AboutDataUiState {
  return AboutDataUiState(
    title = this.title,
    image = this.image
  )
}