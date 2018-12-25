package com.sanjay.baselibrary.presentation

import com.sanjay.baselibrary.data.helper.DataErrorType

/**
 * Created by Sanjay
 * Base interface for networking handling
 */
interface ViewNetworking {
    fun showProgress(show: Boolean)
    fun showError(errorType: DataErrorType?, message: String?)
}