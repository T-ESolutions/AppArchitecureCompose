package app.te.architecture.data.intro.repository

import app.te.architecture.data.intro.data_source.IntroRemoteDataSource
import app.te.architecture.domain.intro.entity.AppTutorialModel
import app.te.architecture.domain.intro.repository.IntroRepository
import app.te.architecture.domain.utils.BaseResponse
import app.te.architecture.domain.utils.Resource
import javax.inject.Inject

class IntroRepositoryImpl @Inject constructor(
  private val remoteDataSource: IntroRemoteDataSource
) : IntroRepository {
  override suspend fun intro(): Resource<BaseResponse<List<AppTutorialModel>>> =
    remoteDataSource.intro()

}