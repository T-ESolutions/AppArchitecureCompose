package app.te.architecture.domain.general.use_case

import androidx.paging.PagingData
import app.te.architecture.data.general.data_source.dto.countries.CityModel
import app.te.architecture.domain.general.repository.GeneralRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class CitiesUseCase @Inject constructor(
  private val generalRepository: GeneralRepository
) {
  suspend operator fun invoke(govId: String): Flow<PagingData<CityModel>> =
    generalRepository.getCities(govId)

}
