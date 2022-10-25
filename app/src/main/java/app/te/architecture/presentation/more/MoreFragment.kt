package app.te.architecture.presentation.more

import app.te.architecture.BuildConfig
import app.te.architecture.R
import app.te.architecture.databinding.FragmentMoreBinding
import app.te.architecture.presentation.base.BaseFragment
import app.te.architecture.presentation.base.extensions.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoreFragment : BaseFragment<FragmentMoreBinding>(), MoreEventListener {

    override
    fun getLayoutId() = R.layout.fragment_more
    override fun setBindingVariables() {
        updateVersionName()
        binding.eventListener = this
    }

    override fun onResume() {
        super.onResume()
        setupStatusBar(R.color.account_status_bar)
    }
    private fun updateVersionName() {
        binding.versionName.text =
            getMyString(R.string.version_name).plus(" ( ").plus(BuildConfig.VERSION_NAME).plus(" )")
    }

    override fun openTeam() {
        navigateSafe(MoreFragmentDirections.actionMoreFragmentToTeamFragment())
    }

    override fun openAbout() {
        navigateSafe(MoreFragmentDirections.actionMoreFragmentToAboutFragment())
    }

    override fun openAbout80Fekra() {
        navigateSafe(MoreFragmentDirections.actionMoreFragmentToAboutFekraFragment())
    }

    override fun openPrivacyPolicy() {
        navigateSafe(MoreFragmentDirections.actionMoreFragmentToPrivacyFragment())
    }

    override fun openTerms() {
        navigateSafe(MoreFragmentDirections.actionMoreFragmentToTermsFragment())
    }

    override fun openContact() {
        navigateSafe(MoreFragmentDirections.actionMoreFragmentToContactFragment())
    }

    override fun openTesDialog() {
        navigateSafe(MoreFragmentDirections.actionMoreFragmentToNavTes())
    }
}