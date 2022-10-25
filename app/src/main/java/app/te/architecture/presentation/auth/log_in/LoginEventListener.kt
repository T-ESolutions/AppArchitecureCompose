package app.te.architecture.presentation.auth.log_in

import app.te.architecture.presentation.base.events.BaseEventListener

interface LoginEventListener :BaseEventListener {
   fun login()
  fun toRegister()
  fun forgetPassword()
  fun openHome()
}