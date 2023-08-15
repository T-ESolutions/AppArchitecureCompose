package app.te.network

interface BaseRemoteRepository {
  suspend fun clearPreferences()
}