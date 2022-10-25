package app.te.architecture.presentation.home.ui_state

import app.te.architecture.domain.home.models.HomeMainData

class HomeUiState(val homeMainData: HomeMainData) {

  fun setUpSlider(): List<SliderUiItemState> {
    return homeMainData.slider.map { SliderUiItemState(it) }
  }

  fun setUpCategories(): List<CategoriesUiItemState> {
    return homeMainData.categories_subcategories.map { CategoriesUiItemState(it) }
  }


}