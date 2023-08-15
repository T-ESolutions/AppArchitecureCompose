package app.te.hero_cars.presentation.brandsAndModels.view_model

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import app.te.hero_cars.data.home.dto.Brand
import app.te.hero_cars.data.home.dto.Model
import app.te.hero_cars.domain.brand_models.use_case.BrandsUseCase
import app.te.hero_cars.domain.brand_models.use_case.ModelsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BrandsAndModelsViewModel @Inject constructor(
    private val modelsUseCase: ModelsUseCase,
    private val brandsUseCase: BrandsUseCase,
    val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _modelsResponse =
        MutableStateFlow<PagingData<Model>>(PagingData.empty())
    val modelsResponse = _modelsResponse

    private val _brandsResponse =
        MutableStateFlow<PagingData<Brand>>(PagingData.empty())
    val brandsResponse = _brandsResponse

    fun getBrands() {
        viewModelScope.launch {
            brandsUseCase.invoke()
                .collect { result ->
                    _brandsResponse.value = result
                }
        }
    }


    fun getModels(brandId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            modelsUseCase.invoke(brandId.toInt())
                .collect { result ->
                    _modelsResponse.value = result
                }
        }

    }

}