package com.praveen.weatherappdemo.networkUtil

import androidx.annotation.Keep

class APIErrorResponseDataModel : BaseApiResponseDataModel(){
    private val message: String? = null
    private val details: String? = null

    /**
     * Class to get detail of the error
     */
    @Keep
    class ErrorData {
        val keyword: String? = null
        val dataPath: String? = null
        val schemaPath: String? = null
        private val message: String? = null
        fun getMessage(): String? {
            return message
        }
    }

    fun getMessage(): String? {
        return message
    }

    fun getDetails(): String? {
        return details
    }
}