package app.te.architecture.presentation.home.ui_state

import app.te.architecture.domain.home.models.Slider

data class SliderUiItemState(val slider: Slider) {
  fun getSliderImage(): String = slider.image
  fun getSliderUrl(): String = slider.url
}