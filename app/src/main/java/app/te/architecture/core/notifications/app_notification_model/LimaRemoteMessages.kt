package app.te.architecture.core.notifications.app_notification_model

class LimaRemoteMessages(
    val data: MutableMap<String, String>,
    val title: String?,
    val body: String?,
    val sound: String?
)