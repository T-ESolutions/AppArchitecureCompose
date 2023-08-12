package app.te.architecture.presentation.base

import android.content.Context
import android.content.IntentSender
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import app.te.architecture.presentation.base.ImmediateUpdateActivity.Companion.UPDATE_REQUEST_CODE
import app.te.architecture.presentation.base.extensions.adjustFontScale
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.UpdateAvailability
import com.zeugmasolutions.localehelper.LocaleHelper
import com.zeugmasolutions.localehelper.LocaleHelperActivityDelegate
import com.zeugmasolutions.localehelper.LocaleHelperActivityDelegateImpl
import java.util.*

abstract class BaseActivity : ComponentActivity() {
    private val localeDelegate: LocaleHelperActivityDelegate = LocaleHelperActivityDelegateImpl()
    lateinit var immediateUpdateActivity: ImmediateUpdateActivity

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(localeDelegate.attachBaseContext(newBase))
    }

    override
    fun createConfigurationContext(overrideConfiguration: Configuration): Context {
        val context = super.createConfigurationContext(overrideConfiguration)
        return LocaleHelper.onAttach(context)
    }

    override
    fun getApplicationContext(): Context =
        localeDelegate.getApplicationContext(super.getApplicationContext())

    override
    fun onResume() {
        super.onResume()
        localeDelegate.onResumed(this)
        checkForUpdate()
    }

    override
    fun onPause() {
        super.onPause()
        localeDelegate.onPaused()
    }

    override
    fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        localeDelegate.onCreate(this)
        adjustFontScale()
        immediateUpdateActivity = ImmediateUpdateActivity(this)
        setUpContent()
    }

    open fun setUpContent() {}

    private fun checkForUpdate() {

        immediateUpdateActivity.getAppUpdateManager().appUpdateInfo
            .addOnSuccessListener { it ->
                if (it.updateAvailability() == UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS) {
                    try {
                        immediateUpdateActivity.getAppUpdateManager()
                            .startUpdateFlowForResult(
                                it,
                                AppUpdateType.IMMEDIATE,
                                this,
                                UPDATE_REQUEST_CODE
                            )
                    } catch (e: IntentSender.SendIntentException) {
                        e.printStackTrace()
                    }
                }
            }
    }

    open fun updateLocale(language: String) {
        localeDelegate.setLocale(this, Locale(language))
    }

//    override
//    fun getDelegate() = localeDelegate.getAppCompatDelegate(super.getDelegate())

}