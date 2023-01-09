package app.te.architecture.presentation.auth.intro.state

import app.te.architecture.domain.intro.entity.AppTutorialModel
import app.te.architecture.domain.utils.Resource

data class IntroState(
    val data: List<AppTutorialModel>? = null,
    val isLoading: Boolean = false,
    val failureStatus: Resource.Failure? = null
)
