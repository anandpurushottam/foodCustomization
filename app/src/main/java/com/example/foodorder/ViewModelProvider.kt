package com.example.foodorder
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import com.example.foodorder.feature.customize.CustomizationViewModel

object ViewModelProvider {

    fun obtainCustomizeViewModel(activity: FragmentActivity): CustomizationViewModel {
        return ViewModelProviders.of(activity, ViewModelFactory.getInstance(activity.application))
                .get(CustomizationViewModel::class.java)
    }

}