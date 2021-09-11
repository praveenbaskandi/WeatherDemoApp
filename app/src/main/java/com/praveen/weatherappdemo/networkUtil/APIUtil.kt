package com.praveen.weatherappdemo.networkUtil

import com.google.gson.JsonSyntaxException
import com.praveen.weatherappdemo.appUtil.StringUtils
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Response
import java.io.IOException


object APIUtil {
    fun parseError(response: Response<*>?): String? {
        val converter: Converter<ResponseBody, APIErrorResponseDataModel> = try {
            RetrofitClientInstance.getRetrofit().responseBodyConverter(
                APIErrorResponseDataModel::class.java,
                arrayOfNulls<Annotation>(0)
            )
        } catch (e: Exception) {
            e.printStackTrace()
            return APIConstants.SERVER_ERROR
        }
        var error: APIErrorResponseDataModel? = null
        if (response?.errorBody() == null) {
            return APIConstants.SERVER_ERROR
        }
        try {
            error = converter.convert(response.errorBody())
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: JsonSyntaxException) {
            e.printStackTrace()
        }
        return if (error != null && StringUtils.isNotEmptyNonNull(error.getMessage())) {
            error.getMessage()
        } else when (response.code()) {
            400 -> APIConstants.INVALID_REQUEST
            401 -> APIConstants.INCORRECT_USER_NAME_PASSWORD
            404 -> APIConstants.RESOURCE_NOT_FOUND
            else -> APIConstants.SERVER_ERROR
        }
    }
}