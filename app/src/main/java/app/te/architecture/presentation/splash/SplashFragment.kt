package app.te.architecture.presentation.splash

import androidx.lifecycle.lifecycleScope
import app.te.architecture.R
import app.te.architecture.databinding.FragmentSplashBinding
import app.te.architecture.presentation.base.BaseFragment
import app.te.architecture.presentation.base.extensions.navigateSafe
import app.te.architecture.presentation.base.extensions.openActivityAndClearStack
import app.te.architecture.presentation.home.HomeActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay


@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding>(), SplashEventListener {

    override
    fun getLayoutId() = R.layout.fragment_splash
    override fun setUpViews() {
        setupStatusBar(R.color.splash)
        lifecycleScope.launchWhenResumed {
            delay(2000)
            openOnBoarding()
        }
    }

    override fun openHome() {
        openActivityAndClearStack(HomeActivity::class.java)
    }

    override fun openOnBoarding() {
        setLanguage("en")
        navigateSafe(SplashFragmentDirections.actionSplashFragmentToTutorialFragment())
    }

}