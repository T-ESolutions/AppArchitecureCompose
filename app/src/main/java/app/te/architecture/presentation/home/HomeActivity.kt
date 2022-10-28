package app.te.architecture.presentation.home

import android.view.MenuItem
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.*
import app.te.architecture.presentation.base.BaseActivity
import app.te.architecture.databinding.ActivityHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import app.te.architecture.R

@AndroidEntryPoint
class HomeActivity : BaseActivity<ActivityHomeBinding>() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var nav: NavController

    override
    fun getLayoutId() = R.layout.activity_home

    override
    fun setUpBottomNavigation() {
        setUpNavigationWithGraphs()
    }

    private fun setUpNavigationWithGraphs() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_host_container) as NavHostFragment
        nav = navHostFragment.findNavController()
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.home_fragment,
                R.id.cartFragment,
                R.id.favoriteFragment,
                R.id.moreFragment
            )
        )
        setSupportActionBar(binding.toolbar)
        setupActionBarWithNavController(nav, appBarConfiguration)
        binding.bottomNavigationView.setupWithNavController(nav)
        navChangeListener()

    }

    private fun navChangeListener() {
        nav.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.home_fragment
                || destination.id == R.id.cartFragment
                || destination.id == R.id.favoriteFragment
                || destination.id == R.id.moreFragment
            ) {
                binding.bottomNavigationView.visibility = View.VISIBLE
                binding.toolbar.visibility = View.GONE
            } else {
                binding.toolbar.visibility = View.VISIBLE
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return nav.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return item.onNavDestinationSelected(nav) || super.onOptionsItemSelected(item)
    }


}