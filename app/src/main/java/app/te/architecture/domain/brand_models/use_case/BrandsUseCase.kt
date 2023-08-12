package app.te.architecture.domain.brand_models.use_case

import androidx.paging.PagingData
import app.te.architecture.data.home.dto.Brand
import app.te.architecture.domain.brand_models.repository.BrandModelRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class BrandsUseCase @Inject constructor(
    private val brandModelRepository: BrandModelRepository
) {
    suspend operator fun invoke(): Flow<PagingData<Brand>> =
        brandModelRepository.getBrands()

}
