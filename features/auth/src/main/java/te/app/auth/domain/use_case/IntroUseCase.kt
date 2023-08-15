package te.app.auth.domain.use_case

import app.te.network.utils.BaseResponse
import app.te.network.utils.Resource
import te.app.auth.domain.entity.model.AppTutorialModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import te.app.auth.domain.repository.AuthRepository
import javax.inject.Inject


class IntroUseCase @Inject constructor(
  private val introRepository: AuthRepository
) {

  operator fun invoke(): Flow<Resource<BaseResponse<List<AppTutorialModel>>>> = flow {
    emit(Resource.Loading)
    emit(introRepository.intro())
  }.flowOn(Dispatchers.IO)
}