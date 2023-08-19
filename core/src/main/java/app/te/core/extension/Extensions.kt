package app.te.core.extension

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.view.WindowManager
import app.te.core.BaseActivity

fun Context.findActivity(): Activity {
    var context = this
    while (context is ContextWrapper) {
        if (context is Activity) return context
        context = context.baseContext
    }
    throw IllegalStateException("no activity")
}

fun Context.adjustFontScale() {
    if (resources.configuration.fontScale > 1.1f) {
        resources.configuration.fontScale = 1f
        val metrics = resources.displayMetrics
        val wm = getSystemService(Context.WINDOW_SERVICE) as WindowManager
        wm.defaultDisplay.getMetrics(metrics)
        metrics.scaledDensity = resources.configuration.fontScale * metrics.density
        resources.updateConfiguration(resources.configuration, metrics)
    }
}

fun String.isNumeric(toCheck: String): Boolean {
    val regex = "-?[0-9]+(\\.[0-9]+)?".toRegex()
    return toCheck.matches(regex)
}

fun Context.isBackPressed() {
    (findActivity() as BaseActivity).isBackPressed()
}