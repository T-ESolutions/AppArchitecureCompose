package app.te.architecture.presentation.base.extensions

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.pm.PackageManager
import android.os.Build
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import app.te.architecture.R
import app.te.architecture.domain.utils.FailureStatus
import app.te.architecture.domain.utils.Resource.Failure
import app.te.architecture.presentation.base.custom_views.AlerterError
import app.te.architecture.presentation.base.utils.*
import app.te.architecture.presentation.base.utils.Constants.MY_PERMISSIONS_REQUEST_POST_NOTIFICATIONS

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

fun checkNotificationsPermissions(activity: Activity, operation: () -> Unit) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        if (ContextCompat.checkSelfPermission(
                activity,
                Manifest.permission.POST_NOTIFICATIONS
            )
            != PackageManager.PERMISSION_GRANTED
        ) {
            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    activity,
                    Manifest.permission.POST_NOTIFICATIONS
                )
            ) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

                //to simplify, call requestPermissions again
                Toast.makeText(
                    activity,
                    activity.getString(R.string.permission_dialog_content1),
                    Toast.LENGTH_LONG
                ).show()

                ActivityCompat.requestPermissions(
                    activity,
                    arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                    MY_PERMISSIONS_REQUEST_POST_NOTIFICATIONS
                )
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(
                    activity,
                    arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                    MY_PERMISSIONS_REQUEST_POST_NOTIFICATIONS
                )
            }
        } else {
            // permission granted
            operation()
        }
    }
}