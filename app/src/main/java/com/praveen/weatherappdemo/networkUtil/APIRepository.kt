package com.praveen.weatherappdemo.networkUtil


object APIRepository {
    private var mApiInterface: APIInterface? = null

    init {
        mApiInterface =
            RetrofitClientInstance.apiService
    }

    suspend fun getDataObj(
        location: String?,
        appId: String?,
    ) = mApiInterface?.getWeatherData(location, appId)
}