package app.te.architecture.core

import android.content.Context
import android.content.res.Configuration
import androidx.multidex.MultiDex
import com.google.android.gms.common.GooglePlayServicesNotAvailableException
import com.google.android.gms.common.GooglePlayServicesRepairableException
import com.google.android.gms.security.ProviderInstaller
import com.zeugmasolutions.localehelper.LocaleAwareApplication
import com.zeugmasolutions.localehelper.LocaleHelper
import com.zeugmasolutions.localehelper.LocaleHelperApplicationDelegate
import dagger.hilt.android.HiltAndroidApp
import java.security.KeyManagementException
import java.security.NoSuchAlgorithmException
import javax.net.ssl.SSLContext

@HiltAndroidApp
class MyApplication : LocaleAwareApplication() {
    private val localeAppDelegate = LocaleHelperApplicationDelegate()

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        localeAppDelegate.onConfigurationChanged(this)
    }

    override fun getApplicationContext(): Context =
        LocaleHelper.onAttach(super.getApplicationContext())

    override
    fun attachBaseContext(base: Context) {
        super.attachBaseContext(localeAppDelegate.attachBaseContext(base))
        MultiDex.install(this)
    }

    override
    fun onCreate() {
        super.onCreate()
        updateAndroidSecurityProvider()
    }

    private fun updateAndroidSecurityProvider() {
        // To fix the following issue, when run app in cellular data, Apis not working
        // javax.net.ssl.SSLHandshakeException: SSL handshake aborted: ssl=0x7edfc49e08: I/O error during system call, Connection reset by peer
        try {
            ProviderInstaller.installIfNeeded(applicationContext)
            val sslContext: SSLContext = SSLContext.getInstance("TLSv1.3")
            sslContext.init(null, null, null)
            sslContext.createSSLEngine()
        } catch (e: GooglePlayServicesRepairableException) {
            e.printStackTrace()
        } catch (e: GooglePlayServicesNotAvailableException) {
            e.printStackTrace()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        } catch (e: KeyManagementException) {
            e.printStackTrace()
        }
    }
}