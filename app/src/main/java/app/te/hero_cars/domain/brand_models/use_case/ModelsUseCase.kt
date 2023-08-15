package app.te.hero_cars.domain.brand_models.use_case

import androidx.paging.PagingData
import app.te.hero_cars.data.home.dto.Model
import app.te.hero_cars.domain.brand_models.repository.BrandModelRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class ModelsUseCase @Inject constructor(
    private val brandModelRepository: BrandModelRepository
) {
    suspend operator fun invoke(brandId: Int): Flow<PagingData<Model>> =
        brandModelRepository.getModels(brandId)

}
