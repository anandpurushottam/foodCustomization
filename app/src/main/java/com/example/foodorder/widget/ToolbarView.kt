package com.example.foodorder.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.cardview.widget.CardView
import com.example.foodorder.R
import com.example.foodorder.util.extention.gone
import com.example.foodorder.util.extention.visible

import kotlinx.android.synthetic.main.view_toolbar.view.*

class ToolbarView : CardView {


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
        LayoutInflater.from(context).inflate(R.layout.view_toolbar, this)
    }

    fun setUpToolbar(message: String?, canShowBackButton: Boolean) {
        toolbar_title.text = message
        when (canShowBackButton) {
            true -> backButton.visible()
            false -> backButton.gone()
        }
    }

    fun backButton() = backButton


}
