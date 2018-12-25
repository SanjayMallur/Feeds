package com.sanjay.baselibrary.presentation

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by Sanjay
 * @param view view from fragments or activities
 */
abstract class PresenterBaseImpl<V>(protected val view: V) : PresenterBase {

    private var compositeDisposable = CompositeDisposable()

    override fun stop() {
        // Using clear will clear all, but can accept new disposable
        this.compositeDisposable.clear()
        // Using dispose will clear all and set isDisposed = true, so it will not accept any new disposable
//        this.compositeDisposable.dispose()
    }

    override fun destroy() {
        if (!this.compositeDisposable.isDisposed) {
            this.compositeDisposable.dispose()
        }
    }

    protected fun addDisposable(disposable: Disposable) {
        if (!this.compositeDisposable.isDisposed) {
            this.compositeDisposable.add(disposable)
        }
    }

    protected fun removeDisposable(disposable: Disposable): Boolean {
        return this.compositeDisposable.remove(disposable)
    }
}