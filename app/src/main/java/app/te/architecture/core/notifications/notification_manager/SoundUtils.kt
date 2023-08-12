package app.te.architecture.core.notifications.notification_manager

import android.content.Context
import android.media.RingtoneManager
import android.net.Uri
import app.te.architecture.R

object SoundUtils {
    var uriString = ""

    fun getNotificationsSound(): Uri {
        return Uri.parse(uriString + R.raw.squek_sms)
    }

    fun playNotificationSound(context: Context) {
        val ringTone = RingtoneManager.getRingtone(context, getNotificationsSound())
        ringTone.play()

    }

}