package app.te.architecture.presentation.home.viewModels

import app.te.architecture.domain.account.use_case.UserLocalUseCase
import app.te.architecture.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeActivityViewModel @Inject constructor(
   val userLocalUseCase: UserLocalUseCase
) : BaseViewModel()