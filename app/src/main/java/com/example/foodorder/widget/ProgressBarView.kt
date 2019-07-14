package com.example.foodorder.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.view.ViewCompat
import com.example.foodorder.R
import com.example.foodorder.util.extention.toDp

/**
 * Widget used to display an progress to the user
 */
class ProgressBarView : FrameLayout {


    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) :
            super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        LayoutInflater.from(context).inflate(R.layout.view_progress, this)
        ViewCompat.setTranslationZ(this.rootView, 8f.toDp(resources.displayMetrics))
    }

}