package com.smallur.feeds.data.exception

import com.sanjay.baselibrary.data.helper.DataErrorType
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

/**
 * Created by Sanjay
 * Data exception helper class
 * @param throwable throwable from server response
 * */
class DataExceptionHelper(private val throwable: Throwable) {

    fun dataErrorType(): DataErrorType = when (throwable) {
        is SocketTimeoutException -> DataErrorType.TIMEOUT
        is IOException -> DataErrorType.NO_CONNECTION
        else -> DataErrorType.UNKNOWN
    }

    private fun dataException(): DataException? {
        return throwable as? DataException
    }

    private fun httpException(exception: HttpException): DataErrorType = when (exception.code()) {
        401 -> DataErrorType.UNAUTHORIZED
        else -> DataErrorType.REQUEST_FAILED
    }

}