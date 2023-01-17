package app.te.architecture.domain.home.repository
import app.te.architecture.domain.home.models.SerialSearchDM
import app.te.architecture.domain.utils.BaseResponse
import app.te.architecture.domain.utils.Resource

interface HomeRepository {
  suspend fun searchForPhone(serial: String): Resource<BaseResponse<SerialSearchDM>>
}