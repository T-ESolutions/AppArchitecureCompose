package app.te.architecture.presentation.auth.confirmCode

import app.te.architecture.presentation.base.events.BaseEventListener


interface ConfirmCodeEventListener :BaseEventListener {
  fun checkCode()
  fun resendCode()
}