package com.praveen.weatherappdemo.networkUtil

class APIConstants {
    companion object {
        //API constants
        const val BEARER = "Bearer "

        //API response message
        const val SERVER_ERROR = "Something went wrong please try again"
        const val LOCAL_STORAGE_ERROR = "Error in saving file"
        const val RESOURCE_NOT_FOUND = "Resource Not Found"
        const val INCORRECT_USER_NAME_PASSWORD = "Incorrect Username or password"
        const val INVALID_OTP_TOKEN = "Please enter correct OTP"
        const val INCORRECT_PASSWORD = "Incorrect Password"
        const val INCORRECT_PIN = "Incorrect Pin"
        const val INVALID_REQUEST = "Invalid Request"

        const val AUTHORIZATION = "Authorization"


        // database constant
        const val DATA_BASE_NAME = "data_base_const_app"
        const val DATA_BASE_VERSION = 1L
    }
}