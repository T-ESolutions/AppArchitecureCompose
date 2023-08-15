package te.app.auth.presentation.intro.state

import app.te.network.utils.Resource
import te.app.auth.domain.entity.model.AppTutorialModel

data class IntroState(
    val data: List<AppTutorialModel>? = null,
    val isLoading: Boolean = false,
    val failureStatus: Resource.Failure? = null
)
