package app.te.hero_cars.domain.home.repository
import app.te.hero_cars.domain.home.models.SerialSearchDM
import app.te.network.utils.BaseResponse
import app.te.network.utils.Resource

interface HomeRepository {
  suspend fun searchForPhone(serial: String): app.te.network.utils.Resource<app.te.network.utils.BaseResponse<SerialSearchDM>>
}