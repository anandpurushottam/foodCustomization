package com.example.foodorder.feature.customize

import android.view.animation.AnimationUtils
import androidx.lifecycle.Observer
import com.example.foodorder.R
import com.example.foodorder.ViewModelProvider
import com.example.foodorder.base.BaseActivity
import com.example.foodorder.feature.customize.adapter.VariationGroupAdapter
import com.example.foodorder.feature.customize.dto.CustomizeFood
import com.example.foodorder.feature.customize.dto.Variation
import com.example.foodorder.util.extention.toast
import kotlinx.android.synthetic.main.activity_main.*
import android.view.animation.AnimationUtils.loadLayoutAnimation


class MainActivity : BaseActivity(), OnOrderCustomized {
    private val selectedMap = mutableMapOf<String, Variation>()
    private lateinit var viewModel: CustomizationViewModel

    override fun initView() {

        setToolBar(getString(R.string.Customize), false)
        swipeRefresh.setOnRefreshListener {
            viewModel.getCustomization()
        }
    }

    override fun init() {
        viewModel = ViewModelProvider.obtainCustomizeViewModel(this)
        viewModel.getCustomization()

    }

    override fun subscribeToChanges() {
        viewModel.obProcessing.observe(this, Observer {

            when (it) {
                true -> displayProgress()
                false -> hideProgress()
            }
        })
        viewModel.obErrorMessage.observe(this, Observer {
            if (it?.code != null) {
                toast(it.error)
            }
        })
        viewModel.obResponse.observe(this, Observer {
            updateUi(it)
        })
    }

    private fun updateUi(data: CustomizeFood) {
        val adapter = VariationGroupAdapter(
            data.variants.variant_groups,
            data.variants.exclude_list,
            this
        )
        recyclerView.adapter = adapter

        val resId = com.example.foodorder.R.anim.layout_animation_fall_down
        val animation = AnimationUtils.loadLayoutAnimation(this, resId)
        recyclerView.layoutAnimation = animation


    }

    override fun onInvalidSelection() {
        toast(getString(R.string.not_a_valid_selection))
    }


    override fun getSelection(): MutableMap<String, Variation> {
        return selectedMap
    }

    override fun setSelection(group_id: String, selectedItem: Variation) {
        selectedMap[group_id] = selectedItem
        var total = 0
        selectedMap.iterator().forEach {
            total += it.value.price
        }
        tvTotal.text = "₹$total"
    }

    override fun displayProgress() {
        super.displayProgress()
        swipeRefresh.isRefreshing = true

    }

    override fun hideProgress() {
        super.hideProgress()
        swipeRefresh.isRefreshing = false
    }


    override fun getResLayout(): Int = R.layout.activity_main

}
