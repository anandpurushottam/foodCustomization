package com.example.foodorder.util

import com.example.foodorder.feature.customize.dto.ExcludeItem
import com.example.foodorder.feature.customize.dto.Variation

object VariationUtil {
    fun verifySelection(
        currentSelection: MutableMap<String, Variation>,
        excludeItems: List<List<ExcludeItem>>
    ): Boolean {

        excludeItems.forEach {
            var excludedItemCount = 0
            it.forEach {

                val variation = currentSelection[it.group_id]
                if (variation?.id.equals(it.variation_id)) {
                    excludedItemCount++;
                }
            }
            if (excludedItemCount >= 2) {
                return false
            } else {
                excludedItemCount = 0;
            }
        }

        return true
    }
}