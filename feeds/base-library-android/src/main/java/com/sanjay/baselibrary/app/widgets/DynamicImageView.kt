package com.sanjay.baselibrary.app.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.View

import com.sanjay.baselibrary.R
/**
* Class to customize image view
* */
class DynamicImageView : ForegroundImageView {

    private var mHeightRatio: Float = 0.0f

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        val a = context.obtainStyledAttributes(attrs, R.styleable.DynamicImageView)
        mHeightRatio = a.getFloat(R.styleable.DynamicImageView_aspectRatio, 0.562f)
        a.recycle()
    }

    constructor(context: Context) : super(context) {}


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        if (mHeightRatio > 0.0) {
            val width = View.MeasureSpec.getSize(widthMeasureSpec)
            val height = Math.round(width * mHeightRatio)
            setMeasuredDimension(width, height)
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        }
    }
}
