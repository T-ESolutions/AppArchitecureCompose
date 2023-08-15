package te.app.storage.domain.use_case

import te.app.storage.domain.repository.StorageRepository
import javax.inject.Inject

class SetFirstTimeUseCase @Inject constructor(private val storageRepository: StorageRepository) {

  suspend operator fun invoke(isFirstTime: Boolean) = storageRepository.setFirstTime(isFirstTime)
}