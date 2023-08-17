package app.te.core.extension

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.WindowManager

fun <A : Activity> Activity.openActivityAndClearStack(activity: Class<A>) {
    Intent(this, activity).apply {
        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(this)
        finish()
    }
}

fun <A : Activity> Activity.openActivity(activity: Class<A>) {
    Intent(this, activity).apply {
        startActivity(this)
    }

}

fun <A : Activity> Activity.openIntentActivity(activity: Class<A>, extra: String) {
    Intent(this, activity).apply {
        putExtra("extra", extra)
        startActivity(this)
    }
}


