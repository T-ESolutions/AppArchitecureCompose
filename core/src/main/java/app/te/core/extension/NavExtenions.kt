package app.te.core.extension

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.fragment.app.Fragment
import androidx.navigation.NavController

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

fun NavController.backToPreviousScreen() {
    navigateUp()
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

