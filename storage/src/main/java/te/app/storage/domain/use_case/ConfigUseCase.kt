package te.app.storage.domain.use_case

import te.app.storage.domain.repository.StorageRepository
import javax.inject.Inject

class ConfigUseCase @Inject constructor(private val storageRepository: StorageRepository) {
  suspend fun saveSplash(value: String) =
    storageRepository.saveSplash(value)

  suspend fun getSplash() =
    storageRepository.getSplash()

}