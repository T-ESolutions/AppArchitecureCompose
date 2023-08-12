package app.te.architecture.data.remote

import app.te.architecture.data.local.preferences.AppPreferences
import javax.inject.Inject

class BaseRemoteRepositoryImpl @Inject constructor(
  private val appPreferences: AppPreferences
) : BaseRemoteRepository {
  override
  suspend fun clearPreferences() = appPreferences.clearPreferences()
}