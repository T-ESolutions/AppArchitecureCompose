package app.te.architecture.data.home.repository

import app.te.architecture.data.home.data_source.remote.HomeRemoteDataSource
import app.te.architecture.domain.home.models.HomeData
import app.te.architecture.domain.home.repository.HomeRepository
import app.te.architecture.domain.utils.BaseResponse
import app.te.architecture.domain.utils.Resource
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(private val remoteDataSource: HomeRemoteDataSource) :
  HomeRepository {
  override suspend fun getHome(cat_id: Int): Resource<BaseResponse<HomeData>> =
    remoteDataSource.homeStudent(cat_id)
}