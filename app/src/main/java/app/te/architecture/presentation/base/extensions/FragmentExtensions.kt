package app.te.architecture.presentation.base.extensions

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.os.Build
import android.util.LayoutDirection
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.core.text.layoutDirection
import androidx.navigation.NavHostController
import app.te.architecture.R
import app.te.architecture.domain.utils.FailureStatus
import app.te.architecture.domain.utils.Resource.Failure
import app.te.architecture.presentation.base.custom_views.AlerterError
import app.te.architecture.presentation.base.utils.*
import java.util.*

fun Context.findActivity(): Activity {
    var context = this
    while (context is ContextWrapper) {
        if (context is Activity) return context
        context = context.baseContext
    }
    throw IllegalStateException("no activity")
}

@Composable
fun HandleApiError(
    activity: Activity,
    failure: Failure,
) {
    when (failure.failureStatus) {
        FailureStatus.EMPTY -> {
            failure.message?.let {
                if (it == Constants.NOT_MATCH_PASSWORD.toString())
                    AlerterError(message = activity.getString(R.string.not_match_password))
                else
                    AlerterError(message = it)
            }
        }
        FailureStatus.NO_INTERNET -> {
            AlerterError(message = activity.getString(R.string.no_internet), icon = R.raw.wifi)
        }

        FailureStatus.TOKEN_EXPIRED -> {
            AlerterError(message = activity.getString(R.string.log_out))
        }

        else -> {
            AlerterError(message = activity.getString(R.string.some_error))
        }
    }
}

fun checkPickPermission(
    activity: Activity,
): Boolean {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        if (checkStorage13Permissions(activity))
            return true
    } else {
        if (checkStoragePermissions(activity))
            return true
    }
    return false
}

@Stable
fun Modifier.mirror(): Modifier {
    return if (Locale.getDefault().layoutDirection == LayoutDirection.RTL)
        this.scale(scaleX = -1f, scaleY = 1f)
    else
        this
}

@OptIn(ExperimentalMaterial3Api::class)
@Stable
@Composable
fun CenterAlignedTopAppBarCustom(
    modifier: Modifier = Modifier,
    navHostController: NavHostController? = null,
    title: Int? = null,
    navigationIcon: Boolean = true,
    backgroundColor: Color = MaterialTheme.colorScheme.primary,
    onNavigateClick: (() -> Unit)? = null
) {
    CenterAlignedTopAppBar(
        title = {
            if (title != null)
                Text(
                    text = stringResource(id = title),
                    color = MaterialTheme.colorScheme.background,
                    style = MaterialTheme.typography.titleMedium,
                )
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = backgroundColor),
        navigationIcon = {
            if (navigationIcon)
                IconButton(onClick = {
                    if (navHostController != null)
                        navHostController.navigateUp()
                    else
                        onNavigateClick?.invoke()
                }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "back",
                        tint = Color.White,
                        modifier = Modifier.mirror()
                    )
                }
        },
        modifier = modifier
    )
}