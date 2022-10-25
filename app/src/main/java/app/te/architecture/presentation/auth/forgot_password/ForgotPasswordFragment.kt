package app.te.architecture.presentation.auth.forgot_password

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import app.te.architecture.R
import app.te.architecture.databinding.FragmentForgotPasswordBinding
import app.te.architecture.domain.auth.entity.request.RegisterRequest
import app.te.architecture.domain.utils.Resource
import app.te.architecture.presentation.base.BaseFragment
import app.te.architecture.presentation.base.extensions.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ForgotPasswordFragment : BaseFragment<FragmentForgotPasswordBinding>(),
    ForgetPasswordEventListener {
    private val viewModel: ForgotPasswordViewModel by viewModels()

    override
    fun getLayoutId() = R.layout.fragment_forgot_password
    override fun onResume() {
        super.onResume()
        setupStatusBar(R.color.details_status_bar)
    }

    override
    fun setBindingVariables() {
        binding.request = viewModel.request
        binding.eventListener = this
    }

    override
    fun setupObservers() {
        lifecycleScope.launchWhenResumed {
            viewModel.forgetPasswordResponse.collect {
                when (it) {
                    Resource.Loading -> {
                        hideKeyboard()
                        showLoading()
                    }
                    is Resource.Success -> {
                        hideLoading()
                        openConfirm(it.value.message)
                    }
                    is Resource.Failure -> {
                        hideLoading()
                        handleApiError(it, retryAction = { viewModel.sendCode() })
                    }
                    Resource.Default -> {
                    }
                }
            }
        }
    }

    override fun openConfirm(message: String) {
        navigateSafe(
            ForgotPasswordFragmentDirections.actionForgotPasswordFragmentToConfirmCodeFragment(
                RegisterRequest(), viewModel.request, message
            )
        )
    }

    override fun sendCode() {
        viewModel.sendCode()
    }

    override fun back() {
        backToPreviousScreen()
    }


}