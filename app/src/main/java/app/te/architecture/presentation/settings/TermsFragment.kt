package app.te.architecture.presentation.settings

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import app.te.architecture.domain.utils.Resource
import app.te.architecture.presentation.base.BaseFragment
import app.te.architecture.presentation.base.extensions.*
import app.te.architecture.R
import app.te.architecture.data.settings.mapToUiState
import app.te.architecture.databinding.FragmentTermsBinding
import app.te.architecture.presentation.settings.viewModels.SettingsViewModel
import app.te.architecture.presentation.base.events.BaseEventListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TermsFragment : BaseFragment<FragmentTermsBinding>(), BaseEventListener {

  private val viewModel: SettingsViewModel by viewModels()

  override
  fun getLayoutId() = R.layout.fragment_terms
  override fun setBindingVariables() {
    binding.eventListener = this
    viewModel.pages("terms")
  }

  override fun onResume() {
    super.onResume()
    setupStatusBar(R.color.colorPrimary)
  }
  override fun setupObservers() {
    lifecycleScope.launchWhenResumed {
      viewModel.aboutResponse.collect {
        when (it) {
          Resource.Loading -> {
            hideKeyboard()
            showLoading()
          }
          is Resource.Success -> {
            hideLoading()
            binding.uiState = it.value.data.mapToUiState()
          }
          is Resource.Failure -> {
            hideLoading()
            handleApiError(it)
          }
          else -> {}
        }
      }
    }

  }


  override fun back() {
    backToPreviousScreen()
  }
}