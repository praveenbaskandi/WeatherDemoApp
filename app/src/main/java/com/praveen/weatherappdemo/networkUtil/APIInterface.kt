package com.praveen.weatherappdemo.networkUtil

import com.praveen.weatherappdemo.appUI.DataModelClass
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface APIInterface {
    @GET("data/2.5/weather")
    suspend fun getWeatherData(@Query("q") location: String?, @Query("appid") appId: String?): Response<DataModelClass>

}