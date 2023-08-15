package te.app.storage.domain.use_case

import te.app.storage.domain.repository.StorageRepository
import javax.inject.Inject

class LanguageUseCase @Inject constructor(private val storageRepository: StorageRepository) {
  suspend fun invoke() = storageRepository.getLang()
  suspend fun invoke(lang: String) = storageRepository.setLang(lang)
}