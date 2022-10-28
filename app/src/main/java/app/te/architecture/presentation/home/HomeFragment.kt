package app.te.architecture.presentation.home

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import app.te.architecture.R
import app.te.architecture.databinding.FragmentHomeBinding
import app.te.architecture.domain.home.models.CategoriesApiModel
import app.te.architecture.domain.utils.Resource
import app.te.architecture.presentation.base.BaseFragment
import app.te.architecture.presentation.base.extensions.*
import app.te.architecture.presentation.base.utils.Constants
import app.te.architecture.presentation.home.adapters.CategoriesAdapter
import app.te.architecture.presentation.home.adapters.MenuAdapter
import app.te.architecture.presentation.home.adapters.PopularAdapter
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
    private lateinit var menuAdapter: MenuAdapter
    private lateinit var popularAdapter: PopularAdapter

    override
    fun getLayoutId() = R.layout.fragment_home

    override
    fun setBindingVariables() {
        binding.event=this
        categoriesAdapter = CategoriesAdapter(this)
        menuAdapter = MenuAdapter(this)
        popularAdapter = PopularAdapter(this)
//        viewModel.getHomeData(1)
    }

    override fun setUpViews() {
        checkNotificationsPermissions(requireActivity()) {}
    }


    override
    fun setupObservers() {
        updateCategories()
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
//                                categoriesAdapter.differ.submitList(it.value.data.categories.map { catItem ->
//                                    CategoriesUiItemState(
//                                        catItem
//                                    )
//                                })
//                                binding.rcOffers.setUpAdapter(categoriesAdapter, "2", "1")
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

    override fun openSearch() {
        navigateSafe(HomeFragmentDirections.actionHomeFragmentToSearchFragment())
    }

    private fun updateCategories() {
        val category = mutableListOf<CategoriesUiItemState>()
        category.add(
            CategoriesUiItemState(
                CategoriesApiModel(
                    name = "Burger",
                    id = 1,
                    image = "https://efood.tesolutionspro.com/storage/app/public/category/2022-10-27-635a88b992d7c.png"
                )
            )
        )
        category.add(
            CategoriesUiItemState(
                CategoriesApiModel(
                    name = "Beef",
                    id = 2,
                    image = "https://efood.tesolutionspro.com/storage/app/public/category/2022-10-27-635a88b992d7c.png"
                )
            )
        )
        category.add(
            CategoriesUiItemState(
                CategoriesApiModel(
                    name = "Beef",
                    id = 3,
                    image = "https://efood.tesolutionspro.com/storage/app/public/category/2022-10-27-635a88b992d7c.png"
                )
            )
        )
        category.add(
            CategoriesUiItemState(
                CategoriesApiModel(
                    name = "Beef",
                    id = 4,
                    image = "https://efood.tesolutionspro.com/storage/app/public/category/2022-10-27-635a88b992d7c.png"
                )
            )
        )
        category.add(
            CategoriesUiItemState(
                CategoriesApiModel(
                    name = "Beef",
                    id = 5,
                    image = "https://efood.tesolutionspro.com/storage/app/public/category/2022-10-27-635a88b992d7c.png"
                )
            )
        )

        categoriesAdapter.differ.submitList(category)
        binding.rcCategories.setUpAdapter(categoriesAdapter, "1", "2")

        menuAdapter.differ.submitList(category)
        binding.rcMenu.setUpAdapter(menuAdapter, "1", "2")

        popularAdapter.differ.submitList(category)
        binding.rcPopularItems.setUpAdapter(popularAdapter, "1", "2")

    }

}