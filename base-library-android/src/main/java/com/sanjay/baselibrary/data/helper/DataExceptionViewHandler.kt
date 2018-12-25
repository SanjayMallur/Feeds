package com.sanjay.baselibrary.data.helper

import android.databinding.BaseObservable
import android.databinding.Bindable
import com.sanjay.baselibrary.BR
import com.sanjay.baselibrary.presentation.PresenterNetworking

/**
 * Created by Sanjay
 * Class to help viewException helper
 * @param presenter presenter
 */
class DataExceptionViewHandler(var presenter: PresenterNetworking?) : BaseObservable() {

    var dataErrorType: DataErrorType? = null
    @Bindable get() = field
    set(value) {
        field = value
        notifyPropertyChanged(BR.dataErrorType)
    }

    var message: String? = null
    @Bindable get() = field
    set(value) {
        field = value
        notifyPropertyChanged(BR.message)
    }

    fun tryAgain() {
        dataErrorType = null
        presenter?.onTryAgain()
    }
}