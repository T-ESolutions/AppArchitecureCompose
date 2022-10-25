package app.te.architecture.presentation.account

interface AccountEventListener {
  fun openProfile()
  fun openFavorite()
  fun openSubscribe()
  fun openChangeLanguage()
  fun logout()
}