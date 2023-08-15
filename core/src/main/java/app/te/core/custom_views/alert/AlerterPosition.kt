package app.te.core.custom_views.alert

sealed class AlerterPosition {

    data object Top : AlerterPosition()

    data object Bottom : AlerterPosition()

    data object Float : AlerterPosition()
}
