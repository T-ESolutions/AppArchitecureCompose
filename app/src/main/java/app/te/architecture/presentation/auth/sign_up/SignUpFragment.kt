package app.te.architecture.presentation.auth.sign_up

import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.PopupMenu
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import app.te.architecture.R
import app.te.architecture.databinding.FragmentSignUpBinding
import app.te.architecture.domain.auth.entity.request.ForgetPasswordRequest
import app.te.architecture.domain.utils.Resource
import app.te.architecture.domain.utils.showCityPopUp
import app.te.architecture.presentation.base.BaseFragment
import app.te.architecture.presentation.base.extensions.*
import app.te.architecture.presentation.base.utils.Constants
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SignUpFragment : BaseFragment<FragmentSignUpBinding>(), RegisterEventListener {

    private val viewModel: SignUpViewModel by viewModels()

    override
    fun getLayoutId() = R.layout.fragment_sign_up

    override
    fun setBindingVariables() {
        viewModel.registerUiState = RegisterUiState(requireActivity())
        binding.uiState = viewModel.registerUiState
        binding.eventListener = this
    }

    override fun onResume() {
        super.onResume()
        setupStatusBar(R.color.colorPrimary)
    }

    override
    fun setupObservers() {
        lifecycleScope.launchWhenResumed {
            viewModel.registerResponse.collect {
                when (it) {
                    Resource.Loading -> {
                        hideKeyboard()
                        showLoading()
                    }
                    is Resource.Success -> {
                        hideLoading()
                        openConfirmCode(it.value.message)
                    }
                    is Resource.Failure -> {
                        hideLoading()
                        handleApiError(it, retryAction = { viewModel.register() })
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

    private fun openConfirmCode(message: String) {
//        navigateSafe(
//            SignUpFragmentDirections.actionSignUpFragmentToConfirmCodeFragment(
//                viewModel.registerUiState.request,
//                ForgetPasswordRequest(),
//                message
//            )
//        )
    }

    override fun signUp() {
        if (viewModel.registerUiState.checkValidation())
            viewModel.register()

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
                    viewModel.registerUiState.cityName = viewModel.cities[item.itemId].name
                    viewModel.registerUiState.request.city_id =
                        viewModel.cities[item.itemId].id.toString()

                    viewModel.registerUiState.emailVisibility =
                        if (viewModel.cities[item.itemId].id.toString() == Constants.EGYPT_ID) View.GONE else View.VISIBLE

                    viewModel.registerUiState.updatePhone()

                    return true
                }

            })
        }
    }

    override fun back() {
        backToPreviousScreen()
    }

}