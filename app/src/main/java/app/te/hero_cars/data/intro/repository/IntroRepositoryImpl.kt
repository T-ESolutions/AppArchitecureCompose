package app.te.hero_cars.data.intro.repository

import app.te.hero_cars.data.intro.data_source.IntroRemoteDataSource
import app.te.hero_cars.domain.intro.entity.AppTutorialModel
import app.te.hero_cars.domain.intro.repository.IntroRepository
import app.te.network.utils.BaseResponse
import app.te.network.utils.Resource
import javax.inject.Inject

class IntroRepositoryImpl @Inject constructor(
  private val remoteDataSource: IntroRemoteDataSource
) : IntroRepository {
  override suspend fun intro(): app.te.network.utils.Resource<app.te.network.utils.BaseResponse<List<AppTutorialModel>>> =
    remoteDataSource.intro()

}