package app.te.architecture.presentation.base.custom_views

sealed class AlerterPosition{

    object Top: AlerterPosition()

    object Bottom: AlerterPosition()

    object Float: AlerterPosition()
}
