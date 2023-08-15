package app.te.hero_cars.presentation.auth.intro.state

import app.te.hero_cars.domain.intro.entity.AppTutorialModel
import app.te.network.utils.Resource

data class IntroState(
    val data: List<AppTutorialModel>? = null,
    val isLoading: Boolean = false,
    val failureStatus: app.te.network.utils.Resource.Failure? = null
)
