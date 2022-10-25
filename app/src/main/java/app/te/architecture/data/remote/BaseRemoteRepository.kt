package app.te.architecture.data.remote

interface BaseRemoteRepository {
  suspend fun clearPreferences()
}