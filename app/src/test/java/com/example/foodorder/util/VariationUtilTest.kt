package com.example.foodorder.util

import com.example.foodorder.DataProvider.readJsonFile
import com.example.foodorder.feature.customize.dto.CustomizeFood
import com.example.foodorder.feature.customize.dto.Variation
import com.google.gson.Gson
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class VariationUtilTest {
    private lateinit var customization: CustomizeFood;
    @Before
    fun setUp() {
        val json = readJsonFile("data.json")
        customization = Gson().fromJson(json, CustomizeFood::class.java)
    }

    @Test
    fun should_return_false_on_invalid_selection() {
        val currentSelection = mutableMapOf<String, Variation>()
        val variationGroup1 = customization.variants.variant_groups[0]
        val variationGroup2 = customization.variants.variant_groups[1]
        val variationGroup3 = customization.variants.variant_groups[2]
        //Adding a invalid selection
        currentSelection[variationGroup1.group_id] = variationGroup1.variations[2]
        currentSelection[variationGroup2.group_id] = variationGroup2.variations[0]
        currentSelection[variationGroup3.group_id] = variationGroup3.variations[1]

        val excludeList = customization.variants.exclude_list


        assertEquals(false, VariationUtil.verifySelection(currentSelection, excludeList))
    }

    @Test
    fun should_return_true_on_valid_selection() {
        val currentSelection = mutableMapOf<String, Variation>()
        //Adding a valid selection
        val variationGroup1 = customization.variants.variant_groups[0]
        val variationGroup2 = customization.variants.variant_groups[1]
        val variationGroup3 = customization.variants.variant_groups[2]

        currentSelection[variationGroup1.group_id] = variationGroup1.variations[1]
        currentSelection[variationGroup2.group_id] = variationGroup2.variations[0]
        currentSelection[variationGroup3.group_id] = variationGroup3.variations[1]

        val excludeList = customization.variants.exclude_list

        assertEquals(true, VariationUtil.verifySelection(currentSelection, excludeList))
    }

}