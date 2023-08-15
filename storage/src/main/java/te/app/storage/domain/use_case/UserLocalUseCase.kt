package te.app.storage.domain.use_case

import com.structure.base_mvvm.User
import kotlinx.coroutines.flow.Flow
import te.app.storage.domain.repository.StorageRepository
import javax.inject.Inject

class UserLocalUseCase @Inject constructor(private val storageRepository: StorageRepository) {

    suspend operator fun invoke(user: Any) {
        storageRepository.saveUserToLocal(user)
    }

    suspend operator fun invoke(): Flow<User> = storageRepository.getUserToLocal()

    suspend fun logOut() = storageRepository.clearPreferences()

}