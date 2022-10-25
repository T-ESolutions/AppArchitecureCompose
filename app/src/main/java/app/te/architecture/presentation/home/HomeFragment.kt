package app.te.architecture.presentation.home

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import app.te.architecture.R
import app.te.architecture.databinding.FragmentHomeBinding
import app.te.architecture.domain.utils.Resource
import app.te.architecture.presentation.base.BaseFragment
import app.te.architecture.presentation.base.extensions.*
import app.te.architecture.presentation.base.utils.Constants
import app.te.architecture.presentation.home.adapters.CategoriesAdapter
import app.te.architecture.presentation.home.eventListener.HomeEventListener
import app.te.architecture.presentation.home.ui_state.CategoriesUiItemState
import app.te.architecture.presentation.home.viewModels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(), HomeEventListener {
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var categoriesAdapter: CategoriesAdapter

    override
    fun getLayoutId() = R.layout.fragment_home

    override
    fun setBindingVariables() {
        categoriesAdapter = CategoriesAdapter(this)
        viewModel.getHomeData(1)
    }

    override fun setUpViews() {
        checkNotificationsPermissions(requireActivity()) {}
    }

    override fun onResume() {
        super.onResume()
        setupStatusBar(R.color.home_status_bar)
    }

    override
    fun setupObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {

                launch {
                    viewModel.homeResponse.collect {
                        when (it) {
                            Resource.Loading -> {
                                hideKeyboard()
                                showLoading()
                            }
                            is Resource.Success -> {
                                hideLoading()
                                categoriesAdapter.differ.submitList(it.value.data.categories.map { catItem ->
                                    CategoriesUiItemState(
                                        catItem
                                    )
                                })
                                binding.rcOffers.setUpAdapter(categoriesAdapter, "2", "1")
                            }
                            is Resource.Failure -> {
                                hideLoading()
                                handleApiError(it)
                            }
                            else -> {}
                        }
                    }
                }
                launch {
                    viewModel.updateToken.collectLatest {
                        viewModel.updateFireBaseToken(requireActivity())
                    }
                }
            }

        }
    }

    override fun openVideos(cat_id: Int, type: String) {

    }

}