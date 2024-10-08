package app.te.architecture.presentation.base.extensions

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavOptions

fun NavController.navigateSafe(
    destination: String,
    launchSingleTop: Boolean = true,
    restoreState: Boolean = true,
    popUpTo: String? = null,
    popUpToId: Int? = null,
) {
    navigate(destination) {
        this.launchSingleTop = launchSingleTop
        this.restoreState = restoreState
        this.restoreState = restoreState
        if (popUpTo != null)
            popUpTo(popUpTo) {
                inclusive = true
            }
        if (popUpToId != null)
            popUpTo(popUpToId)

    }
}

@Composable
fun <T> NavController.GetOnceResult(keyResult: String, onResult: (T) -> Unit) {
    val valueScreenResult = currentBackStackEntry
        ?.savedStateHandle
        ?.getLiveData<T>(keyResult)?.observeAsState()

    valueScreenResult?.value?.let {
        onResult(it)

        currentBackStackEntry
            ?.savedStateHandle
            ?.remove<T>(keyResult)
    }
}

fun <T> NavController.navigateUpWithResult(key: String, data: T) {
    previousBackStackEntry
        ?.savedStateHandle
        ?.set<T>(key, data)
    navigateUp()
}

