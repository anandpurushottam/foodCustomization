package com.example.foodorder.feature.customize

import com.example.foodorder.feature.customize.dto.Variation

interface OnOrderCustomized {
    fun setSelection(group_id: String, selectedItem: Variation)
    fun getSelection(): MutableMap<String, Variation>
    fun onInvalidSelection()
}