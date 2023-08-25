package app.te.hero_cars.presentation.base.extensions

import android.app.Activity
import android.content.Intent

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


