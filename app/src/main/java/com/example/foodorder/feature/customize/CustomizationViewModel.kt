package com.example.foodorder.feature.customize

import androidx.lifecycle.MutableLiveData
import com.example.foodorder.base.BaseViewModel
import com.example.foodorder.data.source.repo.FoodOrderRepository
import com.example.foodorder.feature.customize.dto.CustomizeFood
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class CustomizationViewModel(private val repo: FoodOrderRepository) : BaseViewModel() {
    val obResponse = MutableLiveData<CustomizeFood>()

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    fun getCustomization() {
        obProcessing.value = true
        val disposable = repo.getCustomization()
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ success -> onSuccess(success) }, { error -> onFailure(error.localizedMessage) })
        compositeDisposable.add(disposable)

    }

    private fun onSuccess(response: CustomizeFood?) {
        obProcessing.value = false
        obResponse.value = response
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}