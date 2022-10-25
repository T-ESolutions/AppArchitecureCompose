package app.te.architecture.presentation.home.ui_state

import app.te.architecture.domain.home.models.CategoriesApiModel

data class CategoriesUiItemState(val categoriesApiModel: CategoriesApiModel) {
  fun id(): Int = categoriesApiModel.id
  fun categoryName(): String = categoriesApiModel.name
  fun categoryImage(): String = categoriesApiModel.image
  val contentType: String = categoriesApiModel.type
}