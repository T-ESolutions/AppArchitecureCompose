package app.te.architecture.presentation.account

import app.te.architecture.R
import app.te.architecture.databinding.FragmentLanguageBinding
import app.te.architecture.presentation.auth.AuthActivity
import app.te.architecture.presentation.base.BaseFragment
import app.te.architecture.presentation.base.events.BaseEventListener
import app.te.architecture.presentation.base.extensions.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LanguageFragment : BaseFragment<FragmentLanguageBinding>(), BaseEventListener {

    private  var selectedLang: String = currentLanguage.language

    override
    fun getLayoutId() = R.layout.fragment_language
    override fun setBindingVariables() {
        binding.eventListener = this
    }
    override fun onResume() {
        super.onResume()
        setupStatusBar(R.color.colorPrimary)
    }
    override fun setUpViews() {

        binding.langGroup.check(if (currentLanguage.language == "ar") R.id.radio_ar else R.id.radio_en)

        binding.langGroup.setOnCheckedChangeListener { _, i ->
            selectedLang = if (i == R.id.radio_ar)
                "ar"
            else
                "en"
        }

        binding.confirmButton.setOnClickListener {
            setLanguage(selectedLang)
            openActivityAndClearStack(AuthActivity::class.java)
        }

    }

    override fun back() {
        backToPreviousScreen()
    }

}