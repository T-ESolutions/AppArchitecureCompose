package app.te.core.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.compose.runtime.Composable
import app.te.core.R
import app.te.core.custom_views.alert.AlerterError
import app.te.network.utils.FailureStatus
import app.te.network.utils.Resource

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

@Composable
fun HandleApiError(
    activity: Activity,
    failure: Resource.Failure,
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

fun openBrowser(context: Context, url: String) {
    var urlIntent = url
    if (!url.startsWith("http://") && !url.startsWith("https://")) {
        urlIntent = "https://$url"
    }
    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(urlIntent))
    context.startActivity(browserIntent)
}

fun openDial(context: Context, number: String) {
    val intent = Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", number, null))
    context.startActivity(intent)
}

@SuppressLint("HardwareIds")
fun getDeviceId(context: Context): String {
    return Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
}

fun openWhatsApp(context: Context, number: String) {
    val urlIntent = "https://api.whatsapp.com/send?phone=".plus(number)
    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(urlIntent))
    context.startActivity(browserIntent)
}

fun copyText(text: String, context: Context) {
    val clipboardManager = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val clipData = ClipData.newPlainText("text", text)
    clipboardManager.setPrimaryClip(clipData)
}

fun shareApp(activity: Activity) {
    val intent = Intent(Intent.ACTION_SEND)
    intent.type = "text/plain"
    val shareSub = activity.getString(R.string.app_name)
    val shareBody: String = getPlayStoreLink(activity)
    intent.putExtra(Intent.EXTRA_SUBJECT, shareSub)
    intent.putExtra(Intent.EXTRA_TEXT, shareBody)
    activity.startActivity(Intent.createChooser(intent, "share"))
}

fun getPlayStoreLink(context: Context): String {
    val appPackageName = context.packageName
    return "https://play.google.com/store/apps/details?id=$appPackageName"
}

fun rateApp(context: Context) {
    val uri = Uri.parse("market://details?id=" + context.packageName)
    val goToMarket = Intent(Intent.ACTION_VIEW, uri)
    goToMarket.addFlags(
        Intent.FLAG_ACTIVITY_NO_HISTORY or
                Intent.FLAG_ACTIVITY_NEW_DOCUMENT or
                Intent.FLAG_ACTIVITY_MULTIPLE_TASK
    )
    try {
        context.startActivity(goToMarket)
    } catch (e: ActivityNotFoundException) {
        context.startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse(getPlayStoreLink(context))
            )
        )
    }
}