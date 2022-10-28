package app.te.architecture.presentation.auth.sign_up

import app.te.architecture.presentation.base.events.BaseEventListener


interface RegisterEventListener : BaseEventListener {
  fun signUp()
}