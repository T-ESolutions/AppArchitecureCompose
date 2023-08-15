package app.te.hero_cars.domain.profile.use_case

import app.te.hero_cars.domain.auth.entity.model.UserResponse
import app.te.hero_cars.domain.profile.repository.ProfileRepository
import app.te.network.utils.BaseResponse
import app.te.network.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class ProfileUseCase @Inject constructor(
    private val profileRepository: ProfileRepository
) {

    operator fun invoke(): Flow<app.te.network.utils.Resource<app.te.network.utils.BaseResponse<UserResponse>>> = flow {
        emit(app.te.network.utils.Resource.Loading)
        val result = profileRepository.getProfile()
        emit(result)
    }.flowOn(Dispatchers.IO)

}