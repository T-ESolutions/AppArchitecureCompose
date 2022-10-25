package app.te.architecture.presentation.auth.confirmCode

import android.os.CountDownTimer
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import app.te.architecture.R
import app.te.architecture.databinding.FragmentConfirmCodeBinding
import app.te.architecture.domain.utils.Resource
import app.te.architecture.presentation.base.BaseFragment
import app.te.architecture.presentation.base.extensions.*
import app.te.architecture.presentation.base.utils.showSuccessAlert
import app.te.architecture.presentation.home.HomeActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.*
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class ConfirmCodeFragment : BaseFragment<FragmentConfirmCodeBinding>(), ConfirmCodeEventListener {

    private val viewModel: ConfirmViewModel by viewModels()
    private lateinit var countDownTimer: CountDownTimer

    override
    fun getLayoutId() = R.layout.fragment_confirm_code

    override
    fun setBindingVariables() {
        binding.request = viewModel.request
        binding.message =
            ConfirmCodeFragmentArgs.fromSavedStateHandle(viewModel.savedStateHandle).confirmMessage
        binding.eventListener = this
    }

    override fun onResume() {
        super.onResume()
        setupStatusBar(R.color.details_status_bar)
    }

    override
    fun setupObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                /**
                 * Listen for verify response
                 */
                launch {
                    viewModel.verifyResponse.collect {
                        when (it) {
                            Resource.Loading -> {
                                hideKeyboard()
                                showLoading()
                            }
                            is Resource.Success -> {
                                hideLoading()
                                openHome()
                            }
                            is Resource.Failure -> {
                                hideLoading()
                                handleApiError(it, retryAction = { viewModel.verifyAccount() })
                            }
                            Resource.Default -> {
                            }
                        }
                    }
                }
                /**
                 * Listen for Forget password response
                 */
                launch {
                    viewModel.verifyForgetResponse.collect {
                        when (it) {
                            Resource.Loading -> {
                                hideKeyboard()
                                showLoading()
                            }
                            is Resource.Success -> {
                                hideLoading()
                                changePassword()
                            }
                            is Resource.Failure -> {
                                hideLoading()
                                handleApiError(it, retryAction = { viewModel.verifyAccount() })
                            }
                            Resource.Default -> {
                            }
                        }
                    }
                }
                /**
                 * Listen for Resend code response
                 */
                launch {
                    viewModel.resendResponse.collect {
                        when (it) {
                            Resource.Loading -> {
                                hideKeyboard()
                                showLoading()
                            }
                            is Resource.Success -> {
                                hideLoading()
                                showSuccessAlert(requireActivity(), it.value.message)
                                binding.tvLoginForget.isEnabled = false
                                startTimer()
                            }
                            is Resource.Failure -> {
                                hideLoading()
                                handleApiError(it, retryAction = { viewModel.verifyAccount() })
                            }
                            Resource.Default -> {
                            }
                        }
                    }
                }

            }
        }
    }

    private fun changePassword() {
        navigateSafe(
            ConfirmCodeFragmentDirections.actionConfirmCodeFragmentToChangePasswordFragment2(
                viewModel.forgetRequest.phone
            )
        )
    }

    private fun openHome() {
        openActivityAndClearStack(HomeActivity::class.java)
    }


    override fun checkCode() {
        viewModel.verifyAccount()
    }

    override fun resendCode() {
        viewModel.resendCode()
    }

    override fun back() {
        backToPreviousScreen()
    }

    override fun onStart() {
        super.onStart()
        startTimer()
    }

    private fun startTimer() {
        countDownTimer = object : CountDownTimer(180000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val sec =
                    TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)
                    )
                val min = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)
                val time = "" + String.format(Locale.ENGLISH, "%02d : %02d ", sec, min)
                binding.tvForgetTimer.text = time
            }

            override fun onFinish() {
                binding.tvLoginForget.isEnabled = true
            }
        }.start()
    }

}