package app.te.architecture.data.remote

import app.te.architecture.data.local.preferences.AppPreferences
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import app.te.architecture.domain.utils.BaseResponse
import app.te.architecture.domain.utils.ErrorResponse
import app.te.architecture.domain.utils.FailureStatus
import app.te.architecture.domain.utils.Resource
import org.json.JSONObject
import retrofit2.HttpException
import java.lang.Exception
import java.net.ConnectException
import java.net.UnknownHostException
import java.util.HashMap
import javax.inject.Inject

open class BaseRemoteDataSource @Inject constructor() {
  @Inject
  lateinit var appPreferences: AppPreferences
  var gson: Gson = Gson()
  protected fun getParameters(requestData: Any): Map<String, String> {
    val params: MutableMap<String, String> = HashMap()
    try {
      val jsonObject = JSONObject(gson.toJson(requestData))
      for (i in 0 until jsonObject.names().length()) {
        params[jsonObject.names().getString(i)] =
          jsonObject[jsonObject.names().getString(i)].toString() + ""
      }
    } catch (e: Exception) {
      e.stackTrace
    }
    return params
  }

  suspend fun <T> safeApiCall(apiCall: suspend () -> T): Resource<T> {
    println(apiCall)
    try {
      val apiResponse = apiCall.invoke()
      println(apiResponse)
      when ((apiResponse as BaseResponse<*>).status) {
        403 -> {
          return Resource.Failure(FailureStatus.TOKEN_EXPIRED)
        }
        200 -> {
          return Resource.Success(apiResponse)
        }
        401 -> {
          return Resource.Failure(
            FailureStatus.EMPTY,
            (apiResponse as BaseResponse<*>).status,
            (apiResponse as BaseResponse<*>).message
          )
        }
        405 -> {
          return Resource.Failure(
            FailureStatus.NOT_ACTIVE,
            (apiResponse as BaseResponse<*>).status,
            (apiResponse as BaseResponse<*>).message
          )
        }
        else -> {
          return Resource.Failure(FailureStatus.API_FAIL)
        }
      }
    } catch (throwable: Throwable) {
      println(throwable)
      when (throwable) {
        is HttpException -> {
          when {
            throwable.code() == 422 -> {
              val jObjError = JSONObject(throwable.response()!!.errorBody()!!.string())
              val apiResponse = jObjError.toString()
              val response = Gson().fromJson(apiResponse, BaseResponse::class.java)

              return Resource.Failure(FailureStatus.API_FAIL, throwable.code(), response.message)
            }
            throwable.code() == 401 -> {
              val errorResponse = Gson().fromJson(
                throwable.response()?.errorBody()!!.charStream().readText(),
                ErrorResponse::class.java
              )
              appPreferences.clearPreferences()
              return Resource.Failure(
                FailureStatus.TOKEN_EXPIRED,
                throwable.code(),
                errorResponse.detail
              )
            }
            throwable.code() == 404 -> {
              val errorResponse = Gson().fromJson(
                throwable.response()?.errorBody()!!.charStream().readText(),
                ErrorResponse::class.java
              )
              return Resource.Failure(
                FailureStatus.API_FAIL,
                throwable.code(),
                errorResponse.detail
              )
            }
            throwable.code() == 500 -> {
              val errorResponse = Gson().fromJson(
                throwable.response()?.errorBody()!!.charStream().readText(),
                ErrorResponse::class.java
              )

              return Resource.Failure(
                FailureStatus.API_FAIL,
                throwable.code(),
                errorResponse.detail
              )
            }
            else -> {
              return if (throwable.response()?.errorBody()!!.charStream().readText().isEmpty()) {
                Resource.Failure(FailureStatus.API_FAIL, throwable.code())
              } else {
                try {
                  val errorResponse = Gson().fromJson(
                    throwable.response()?.errorBody()!!.charStream().readText(),
                    ErrorResponse::class.java
                  )
                  Resource.Failure(FailureStatus.API_FAIL, throwable.code(), errorResponse?.detail)
                } catch (ex: JsonSyntaxException) {
                  Resource.Failure(FailureStatus.API_FAIL, throwable.code())
                }
              }
            }
          }
        }

        is UnknownHostException -> {
          return Resource.Failure(FailureStatus.NO_INTERNET)
        }

        is ConnectException -> {
          return Resource.Failure(FailureStatus.NO_INTERNET)
        }

        else -> {
          return Resource.Failure(FailureStatus.OTHER)
        }
      }
    }
  }
}