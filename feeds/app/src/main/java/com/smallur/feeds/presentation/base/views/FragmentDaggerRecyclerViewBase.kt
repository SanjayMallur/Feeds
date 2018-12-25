package com.smallur.feeds.presentation.base.views

import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.AnimRes
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.content.res.AppCompatResources
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewStub
import android.view.animation.AnimationUtils
import android.widget.ProgressBar
import android.widget.Toast
import com.sanjay.baselibrary.data.helper.DataErrorType
import com.sanjay.baselibrary.data.helper.DataExceptionViewHandler
import com.sanjay.baselibrary.data.helper.EmptyViewHandler
import com.sanjay.baselibrary.databinding.LayoutEmptyBinding
import com.sanjay.baselibrary.databinding.LayoutErrorBinding
import com.sanjay.baselibrary.presentation.views.recycler.AdapterBase
import com.sanjay.baselibrary.presentation.views.recycler.RecyclerViewContract
import com.smallur.feeds.R

/**
 * Created by Sanjay
 * Base class for dagger injected classes
 * */
abstract class FragmentDaggerRecyclerViewBase<V : ViewDataBinding, P : RecyclerViewContract.Presenter> : FragmentDaggerBase<V, P>(), RecyclerViewContract.View {


    override fun provideLayoutResId(): Int {
        return R.layout.recycler_with_refresh
    }

    protected var progressBar: ProgressBar? = null
    protected var swipeRefreshLayout: SwipeRefreshLayout? = null

    protected lateinit var recyclerView: RecyclerView

    protected lateinit var dataExceptionViewHandler: DataExceptionViewHandler

    private lateinit var viewStubError: ViewStub
    private lateinit var viewStubEmpty: ViewStub

    override fun initView(inflater: LayoutInflater, savedInstanceState: Bundle?) {

        // Swipe Refresh Layout
        this.swipeRefreshLayout = this.binding.root.findViewById(R.id.swipeRefresh)
        this.swipeRefreshLayout?.apply {
            setColorSchemeColors(*resources.getIntArray(R.array.refreshColors))
            setOnRefreshListener {
                viewStubEmpty.visibility = View.GONE
                presenter.refresh()
            }
        }

        this.progressBar = this.binding.root.findViewById(R.id.progressBar)

        // Recycler View
        this.recyclerView = binding.root.findViewById(R.id.recyclerView)
        this.recyclerView.apply {
            layoutManager = provideLayoutManager()
            if (provideItemAnimator() != 0 && savedInstanceState == null) {
                layoutAnimation = AnimationUtils.loadLayoutAnimation(context, provideItemAnimator())
            }
            adapter = provideAdapter()
            adapter.registerAdapterDataObserver(adapterDataObserver)
        }

        // Error View
        dataExceptionViewHandler = DataExceptionViewHandler(presenter)
        viewStubError = binding.root.findViewById(R.id.view_error)
        viewStubError.setOnInflateListener { _, view ->
            val errorBinding = LayoutErrorBinding.bind(view)
            errorBinding.error = dataExceptionViewHandler
        }

        // Empty View
        viewStubEmpty = binding.root.findViewById(R.id.view_empty)
        viewStubEmpty.setOnInflateListener { _, view ->
            val emptyViewHandler = provideEmptyViewHandler()
            val viewEmptyBinding = LayoutEmptyBinding.bind(view)
            viewEmptyBinding.empty = emptyViewHandler
            viewEmptyBinding.textTitleEmpty.setCompoundDrawablesWithIntrinsicBounds(null, AppCompatResources.getDrawable(context!!, emptyViewHandler.iconResId), null, null)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (initStartOnInitView() && isRecyclerViewEmpty()) {
            when {
                swipeRefreshLayout != null -> swipeRefreshLayout?.post {
                    showProgress(true)
                    presenter.start()
                }
                progressBar != null -> progressBar?.post {
                    showProgress(true)
                    presenter.start()
                }
                else -> presenter.start()
            }
        } else showProgress(false)
    }

    override fun onDestroyView() {
        recyclerView.adapter.unregisterAdapterDataObserver(adapterDataObserver)
        recyclerView.adapter = null
        recyclerView.layoutManager = null
        dataExceptionViewHandler.presenter = null
        super.onDestroyView()
    }

    override fun setEmptyListVisible(show: Boolean) {
        this.viewStubEmpty.visibility = if (show) View.VISIBLE else View.GONE
    }

    override fun showProgress(show: Boolean) {
        swipeRefreshLayout?.isRefreshing = show
        progressBar?.visibility = if (show) View.VISIBLE else View.GONE
    }

    protected open fun initStartOnInitView(): Boolean {
        return true
    }

    protected fun registerNetworkReceiver(): Boolean {
        return true
    }

    private fun checkEmptyView() {
        setEmptyListVisible(isRecyclerViewEmpty())
    }

    private fun checkErrorView() {
        if (!isRecyclerViewEmpty()) {
            dataExceptionViewHandler.dataErrorType = null
        }
    }

    override fun showError(errorType: DataErrorType?, message: String?) {
        val errorMessage = DataErrorType.getErrorMessage(resources, errorType, message)
        if (isRecyclerViewEmpty()) {
            dataExceptionViewHandler.dataErrorType = errorType
            dataExceptionViewHandler.message = errorMessage
            if (!viewStubError.isShown) {
                viewStubError.visibility = View.VISIBLE
            }
        } else Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
        showProgress(false)
    }

    protected open fun provideEmptyViewHandler(): EmptyViewHandler {
        return EmptyViewHandler(R.drawable.ic_empty_list, R.string.empty__title, R.string.empty__message)
    }

    protected fun isRecyclerViewEmpty(): Boolean {
        return recyclerView.adapter == null || recyclerView.adapter.itemCount == 0
    }

    protected fun isRefreshing(): Boolean {
        return swipeRefreshLayout?.isRefreshing ?: progressBar?.isShown ?: false
    }

    @AnimRes
    protected open fun provideItemAnimator(): Int {
        return 0
    }

    protected open fun onConnectionStatusChanged(connected: Boolean) {}

    protected open fun onRecyclerVerticallyScroll(firstVisibleItem: Int, dy: Int, scrollPosition: Int) {}

    protected abstract fun provideLayoutManager(): RecyclerView.LayoutManager
    protected abstract fun provideAdapter(): AdapterBase<*>

    // Recycler Adapter Observer
    private val adapterDataObserver = object : RecyclerView.AdapterDataObserver() {
        override fun onChanged() {
            super.onChanged()
            checkEmptyView()
            checkErrorView()
        }
    }

}