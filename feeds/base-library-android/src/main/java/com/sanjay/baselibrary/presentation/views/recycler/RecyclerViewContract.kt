package com.sanjay.baselibrary.presentation.views.recycler

import com.sanjay.baselibrary.presentation.PresenterBase
import com.sanjay.baselibrary.presentation.PresenterNetworking
import com.sanjay.baselibrary.presentation.ViewNetworking

/**
 * Created by Sanjay
 * Base recycler view contract
 */
interface RecyclerViewContract {

    interface View : ViewNetworking {
        fun setEmptyListVisible(show: Boolean)
    }

    interface Presenter : PresenterBase, PresenterNetworking {
        fun refresh()
    }
}