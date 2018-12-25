package com.sanjay.baselibrary.data.helper

import android.content.res.Resources
import android.support.annotation.StringRes
import android.text.TextUtils

import com.sanjay.baselibrary.R

/**
 * Created by Sanjay
 * Data error type class
 */

enum class DataErrorType {
    NO_CONNECTION,
    TIMEOUT,
    SERVER_FAILED,
    REQUEST_FAILED,
    PARSE,
    UNAUTHORIZED,
    DB_FAILED,
    UNKNOWN;

    companion object {

        fun getErrorMessage(resources: Resources, errorType: DataErrorType?, message: String?): String? {
            var message = message
            if (TextUtils.isEmpty(message) && errorType != null) message = resources.getString(getErrorMessageRes(errorType))
            return message
        }

        @StringRes
        protected fun getErrorMessageRes(errorType: DataErrorType): Int {
            when (errorType) {
                NO_CONNECTION -> return R.string.error__request_error
                SERVER_FAILED, TIMEOUT, REQUEST_FAILED, PARSE -> return R.string.error__request_error
                UNAUTHORIZED -> return R.string.error__unauthorized
                else -> return R.string.error__request_error
            }
        }
    }
}
