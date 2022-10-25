package app.te.architecture.presentation.intro.tutorial

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import app.te.architecture.domain.intro.entity.AppTutorialModel
import app.te.architecture.appTutorial.AppTutorialHelper
import app.te.architecture.domain.utils.Resource
import app.te.architecture.R
import app.te.architecture.presentation.base.BaseFragment
import app.te.architecture.presentation.base.extensions.handleApiError
import app.te.architecture.presentation.base.extensions.hideKeyboard
import app.te.architecture.databinding.FragmentTutorialBinding
import app.te.architecture.presentation.base.extensions.openActivityAndClearStack
import app.te.architecture.presentation.home.HomeActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TutorialFragment : BaseFragment<FragmentTutorialBinding>() {

  private val viewModel: TutorialViewModel by viewModels()

  override
  fun getLayoutId() = R.layout.fragment_tutorial

  override
  fun setBindingVariables() {
    binding.viewModel = viewModel
  }

  private fun setUpAppTutorial(tutorialModelData: List<AppTutorialModel> = ArrayList()) {
    AppTutorialHelper.Builder(requireActivity(), lifecycle)
      .setTutorialData(tutorialModelData)
      .setTitleColor(R.color.black)
      .setContentColor(R.color.medium_color)
      .setSliderContainerResourceID(R.id.tutorial_container)
      .setActiveIndicatorColor(R.color.colorPrimaryDark)
      .setInActiveIndicatorColor(R.color.black_light)
      .setAutoScrolling(false)
      .setNextButtonTextColor(R.color.white)
      .setNextButtonBackground(R.color.colorPrimaryDark)
//      .setPreviousTextColor(R.color.black)
      .setOpenAppTextColor(R.color.white)
      .setSkipTutorialClick { openIntro() }
      .create()
  }

  override
  fun setupObservers() {
    lifecycleScope.launchWhenResumed {
      viewModel.appTutorialResponse.collect {
        when (it) {
          Resource.Loading -> {
            hideKeyboard()
            showLoading()
          }
          is Resource.Success -> {
            hideLoading()
            setUpAppTutorial(it.value.data)
          }
          is Resource.Failure -> {
            hideLoading()
            handleApiError(it)
          }
          else -> {
          }
        }
      }
    }
  }

  private fun openIntro() {
    viewModel.setFirstTime(false)
    requireActivity().openActivityAndClearStack(HomeActivity::class.java)
  }
}