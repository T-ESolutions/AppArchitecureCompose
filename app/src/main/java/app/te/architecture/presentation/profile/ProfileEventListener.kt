package app.te.architecture.presentation.profile

import app.te.architecture.presentation.base.events.BaseEventListener

interface ProfileEventListener : BaseEventListener {
  fun updateProfile()
  fun changePassword()
  fun showCities()
}