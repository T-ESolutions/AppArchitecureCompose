package app.te.architecture.presentation.auth.forgot_password

import app.te.architecture.presentation.base.events.BaseEventListener


interface ForgetPasswordEventListener : BaseEventListener {
  fun openConfirm(message: String)
  fun sendCode()
}