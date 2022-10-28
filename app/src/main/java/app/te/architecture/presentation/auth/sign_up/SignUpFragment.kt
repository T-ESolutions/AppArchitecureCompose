package app.te.architecture.presentation.auth.sign_up

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import app.te.architecture.R
import app.te.architecture.databinding.FragmentSignUpBinding
import app.te.architecture.domain.utils.Resource
import app.te.architecture.presentation.base.BaseFragment
import app.te.architecture.presentation.base.extensions.*
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


    override fun back() {
        backToPreviousScreen()
    }

}