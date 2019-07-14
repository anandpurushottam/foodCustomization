package com.example.foodorder

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.foodorder.data.source.repo.FoodOrderRepository
import com.example.foodorder.feature.customize.CustomizationViewModel
import com.example.foodorder.util.NetworkUtils


class ViewModelFactory(private val mApplication: Application, private val repo: FoodOrderRepository) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>) =
        with(modelClass) {
            when {
                isAssignableFrom(CustomizationViewModel::class.java) ->
                    CustomizationViewModel(repo)
                else
                ->
                    throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        } as T

    companion object {

        @SuppressLint("StaticFieldLeak")
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        fun getInstance(application: Application) =
            INSTANCE ?: synchronized(ViewModelFactory::class.java) {
                val isDebug = true
                INSTANCE ?: ViewModelFactory(
                    application, FoodOrderRepository(NetworkModule.makeFoodService(isDebug))
                )
                    .also { INSTANCE = it }
            }


        fun destroyInstance() {
            INSTANCE = null
        }
    }


}
