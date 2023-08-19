package te.app.storage.domain.use_case

import te.app.storage.domain.repository.StorageRepository
import javax.inject.Inject

class UserTypeUseCase @Inject constructor(
    private val storageRepository: StorageRepository,
) {

    suspend operator fun invoke(userType: Int) {
        storageRepository.saveUserType(userType)
        UserTypeSingle.userType = userType
    }

    suspend operator fun invoke(): Int = storageRepository.getUserType()

}