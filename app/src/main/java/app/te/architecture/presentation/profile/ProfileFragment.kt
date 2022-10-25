package app.te.architecture.presentation.profile

import android.view.MenuItem
import androidx.appcompat.widget.PopupMenu
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import app.te.architecture.R
import app.te.architecture.databinding.FragmentProfileBinding
import app.te.architecture.domain.utils.Resource
import app.te.architecture.domain.utils.showCityPopUp
import app.te.architecture.presentation.base.BaseFragment
import app.te.architecture.presentation.base.extensions.*
import app.te.architecture.presentation.base.utils.showSuccessAlert
import app.te.architecture.presentation.home.HomeActivity
import app.te.architecture.presentation.profile.viewModels.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>(), ProfileEventListener {

    private val viewModel: ProfileViewModel by viewModels()

    override
    fun getLayoutId() = R.layout.fragment_profile

    override
    fun setBindingVariables() {
        binding.eventListener = this
    }

    override fun onResume() {
        super.onResume()
        setupStatusBar(R.color.colorPrimary)
    }

    override
    fun setupObservers() {
        lifecycleScope.launchWhenResumed {
            viewModel.updateProfileResponse.collect {
                when (it) {
                    Resource.Loading -> {
                        hideKeyboard()
                        showLoading()
                    }
                    is Resource.Success -> {
                        hideLoading()
                        showSuccessAlert(requireActivity(),it.value.message)
                        openHome(it.value.message)
                    }
                    is Resource.Failure -> {
                        hideLoading()
                        handleApiError(
                            it,
                            retryAction = { viewModel.updateProfile() })
                    }
                    else -> {}
                }
            }
        }
        lifecycleScope.launchWhenResumed {
            viewModel.profileResponse.collect {
                when (it) {
                    Resource.Loading -> {
                        hideKeyboard()
                        showLoading()
                    }
                    is Resource.Success -> {
                        hideLoading()
                        viewModel.updateRequest(it.value.data)
                        binding.request = viewModel.request
                    }
                    is Resource.Failure -> {
                        hideLoading()
                        handleApiError(
                            it,
                            retryAction = { viewModel.updateProfile() })
                    }
                    else -> {}
                }
            }
        }
        lifecycleScope.launchWhenResumed {
            viewModel.citiesResponse.collect {
                when (it) {
                    Resource.Loading -> {
                        hideKeyboard()
                        showLoading()
                    }
                    is Resource.Success -> {
                        hideLoading()
                        viewModel.cities = it.value.data
                    }
                    is Resource.Failure -> {
                        hideLoading()
                        handleApiError(it, retryAction = { viewModel.getCities() })
                    }
                    else -> {}
                }
            }
        }

    }

    private fun openHome(message: String) {
        lifecycleScope.launch {
            delay(1000)
            showSuccessAlert(requireActivity(), message)
        }
        openActivityAndClearStack(HomeActivity::class.java)
    }

    override fun updateProfile() {
        viewModel.updateProfile()
    }

    override fun changePassword() {
        navigateSafe(ProfileFragmentDirections.actionProfileFragmentToChangePasswordFragment())
    }

    override fun back() {
        backToPreviousScreen()
    }

    override fun showCities() {
        if (viewModel.cities.isNotEmpty()) {
            val popUp = showCityPopUp(
                requireActivity(),
                binding.inputCity,
                viewModel.cities
            )
            popUp.setOnMenuItemClickListener(object : MenuItem.OnMenuItemClickListener,
                PopupMenu.OnMenuItemClickListener {
                override fun onMenuItemClick(item: MenuItem): Boolean {
                    binding.city.setText(viewModel.cities[item.itemId].name)
                    viewModel.request.city_id = viewModel.cities[item.itemId].id.toString()
                    return true
                }

            })
        } else {
            viewModel.getCities()
        }
    }
}