package app.te.architecture.domain.home.models

import androidx.annotation.Keep

@Keep
data class HomeData(
  val subscriber: Int = 0,
  val categories: List<CategoriesApiModel> = listOf(),
)