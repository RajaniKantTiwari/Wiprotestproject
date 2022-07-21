package com.wipro.networking

import retrofit2.HttpException
import java.lang.Exception
import java.net.SocketTimeoutException

enum class ErrorCodes(val code: Int) {
    SocketTimeOut(-1)
}
class ResponseHandler {
    fun <T : Any> handleSuccess(response: T): Resource<T> {
        return Resource.success(response)
    }
    fun <T : Any> handleException(exception: Exception): Resource<T> {
       return when(exception) {
           is HttpException -> Resource.error(getErrorMessage(exception.code()), null)
           is SocketTimeoutException -> Resource.error(getErrorMessage(ErrorCodes.SocketTimeOut.code) ,null)
           else -> Resource.error(getErrorMessage(Int.MAX_VALUE), null)
       }
    }

    private fun getErrorMessage(code: Int): String {
        return when (code) {
            ErrorCodes.SocketTimeOut.code -> "Timeout"
            401 -> "Unauthorised"
            404 -> "Not found"
            else -> "Something went wrong"
        }
    }
}