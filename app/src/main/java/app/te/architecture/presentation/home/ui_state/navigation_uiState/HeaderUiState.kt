package app.te.architecture.presentation.home.ui_state.navigation_uiState

import androidx.databinding.Bindable
import app.te.architecture.BR
import app.te.architecture.presentation.base.BaseUiState

class HeaderUiState : BaseUiState() {
  @Bindable
  var username: String = ""

  @Bindable
  var expireDate: String = ""
  fun updateUi(name: String, expireDate: String) {
    username = name
    this.expireDate = expireDate
    notifyPropertyChanged(BR.username)
    notifyPropertyChanged(BR.expireDate)
  }
}