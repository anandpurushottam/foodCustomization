package com.example.foodorder.base

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.example.foodorder.R
import com.example.foodorder.util.extention.gone
import com.example.foodorder.util.extention.visible
import com.example.foodorder.widget.ToolbarView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.view_progress.*


@SuppressLint("Registered")
abstract class BaseActivity : AppCompatActivity() {


    @LayoutRes
    protected abstract fun getResLayout(): Int


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getResLayout())
        initView()
        init()
        subscribeToChanges()
    }

    abstract fun subscribeToChanges()

    protected open fun setToolBar(title: String, canShowBackButton: Boolean) {
        val toolbarView = findViewById<ToolbarView>(R.id.toolbar)
        toolbarView?.setUpToolbar(title, canShowBackButton)
        toolbarView?.backButton()?.setOnClickListener {
            onBackPressed()
        }
    }

    protected abstract fun initView()
    protected abstract fun init()

    protected open fun displayProgress() {
        progressLayout?.visible()
    }

    protected open fun hideProgress() {
        progressLayout?.gone()
    }


    enum class TYPE {
        SUCCESS, FAILURE, NONE
    }

    protected fun showSnackbar(message: String, type: TYPE) {

        val snackbar = Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG)
        val snackbarView = snackbar.view
        when (type) {
            TYPE.SUCCESS -> {
                snackbarView.setBackgroundColor(Color.parseColor("#00a152"));
            }
            TYPE.FAILURE -> {
                snackbarView.setBackgroundColor(Color.parseColor("#f50057"));
            }
            TYPE.NONE -> {
                snackbarView.setBackgroundColor(Color.BLACK);
            }
        }

        snackbar.show()
    }


    enum class NAVIGATION {
        HOME, CATEGORY, FAV, CART, MENU
    }


}

