package app.te.architecture.presentation.base.utils

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings.Secure
import android.widget.Toast
import app.te.architecture.R

fun showMessage(context: Context, message: String?) {
  Toast.makeText(
    context,
    message ?: context.resources.getString(R.string.some_error),
    Toast.LENGTH_SHORT
  )
    .show()
}


@SuppressLint("HardwareIds")
fun getDeviceId(context: Context): String {
  return Secure.getString(context.contentResolver, Secure.ANDROID_ID)
}

fun openBrowser(context: Context, url: String) {
  var urlIntent = url
  if (!url.startsWith("http://") && !url.startsWith("https://")) {
    urlIntent = "https://$url"
  }
  val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(urlIntent))
  context.startActivity(browserIntent)
}

fun openWhatsApp(context: Context, number: String) {
  var urlIntent = "https://api.whatsapp.com/send?phone=".plus(number)
  val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(urlIntent))
  context.startActivity(browserIntent)
}

fun openDial(context: Context, number: String) {
  val intent = Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", number, null))
  context.startActivity(intent)
}
fun copyText(text: String, context: Context) {
  val clipboardManager = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
  val clipData = ClipData.newPlainText("text", text)
  clipboardManager.setPrimaryClip(clipData)
}
