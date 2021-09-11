package com.praveen.weatherappdemo.applicationUtil

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


open class BaseViewModel : ViewModel() {
    private val responseErrorModel = MutableLiveData<String>()

    private fun handleFailure(t: Throwable) {
        t.printStackTrace()
    }

    fun getResponseError(): MutableLiveData<String> {
        return responseErrorModel
    }

    fun setResponseError(responseError: String) {
        responseErrorModel.postValue(responseError)
    }
}