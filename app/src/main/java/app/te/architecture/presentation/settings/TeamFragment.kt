package app.te.architecture.presentation.settings

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import app.te.architecture.domain.utils.Resource
import app.te.architecture.presentation.base.BaseFragment
import app.te.architecture.presentation.base.extensions.*
import app.te.architecture.R
import app.te.architecture.databinding.FragmentTeamBinding
import app.te.architecture.domain.settings.models.Teams
import app.te.architecture.presentation.base.events.BaseEventListener
import app.te.architecture.presentation.settings.adapters.TeamAdapter
import app.te.architecture.presentation.settings.viewModels.SettingsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TeamFragment : BaseFragment<FragmentTeamBinding>(), BaseEventListener {
  private val viewModel: SettingsViewModel by viewModels()
  private val teamAdapter = TeamAdapter()

  override
  fun getLayoutId() = R.layout.fragment_team
  override fun setBindingVariables() {
    binding.eventListener = this
    viewModel.getTeam()
  }

  override fun onResume() {
    super.onResume()
    setupStatusBar(R.color.colorPrimary)
  }
  override fun setupObservers() {
    lifecycleScope.launchWhenResumed {
      viewModel.team.collect {
        when (it) {
          Resource.Loading -> {
            hideKeyboard()
            showLoading()
          }
          is Resource.Success -> {
            hideLoading()
            updateUi(it.value.data)
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

  private fun updateUi(team: List<Teams>) {
    teamAdapter.differ.submitList(team)
    binding.rcTeam.setUpAdapter(teamAdapter, "2", "1")
  }

  override fun back() {
    backToPreviousScreen()
  }
}