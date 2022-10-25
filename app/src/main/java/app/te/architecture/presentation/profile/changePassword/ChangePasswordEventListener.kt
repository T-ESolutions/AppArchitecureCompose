package app.te.architecture.presentation.profile.changePassword

import app.te.architecture.presentation.base.events.BaseEventListener

interface ChangePasswordEventListener :BaseEventListener{
  fun changePassword()
}