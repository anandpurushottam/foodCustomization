package com.example.foodorder.base
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodorder.data.source.remote.ErrorCodes
import com.example.foodorder.data.source.remote.ErrorDTO
import com.example.foodorder.data.source.remote.ErrorHandler


open class BaseViewModel : ViewModel() {
    var obProcessing = MutableLiveData<Boolean>()
    val obErrorMessage = MutableLiveData<ErrorDTO>()


    open fun onFailure(error: String) {
        obProcessing.value = false
        obErrorMessage.value = ErrorDTO(error, ErrorCodes.RESPONSE_FAILED_ERROR)
    }

    open fun onFailure(error: Throwable) {
        obErrorMessage.value = ErrorHandler.handleError(error)
        obProcessing.value = false

    }
}