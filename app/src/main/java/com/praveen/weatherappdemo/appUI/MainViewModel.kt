package com.praveen.weatherappdemo.appUI

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import com.praveen.weatherappdemo.appUtil.ConstantFun
import com.praveen.weatherappdemo.appUtil.ConstantKey
import com.praveen.weatherappdemo.applicationUtil.BaseViewModel
import com.praveen.weatherappdemo.networkUtil.APIConstants
import com.praveen.weatherappdemo.networkUtil.APIRepository
import com.praveen.weatherappdemo.networkUtil.APIUtil
import com.praveen.weatherappdemo.networkUtil.ApiObserverDataModel
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainViewModel : BaseViewModel() {
    val showHumidity = MutableLiveData<String>()
    val showWind = MutableLiveData<String>()
    val showTemperature = MutableLiveData<String>()
    val showCity = MutableLiveData<String>()
    val showIcon = MutableLiveData<String>()

    val errorShow = MutableLiveData<Boolean>()
    private var dataList: MutableLiveData<List<DataModelClass.Weather>>? = null
    val dataListSuccess = MutableLiveData<ApiObserverDataModel<*>>()

    init {
        errorShow.value = false
        showHumidity.value = ""
        showWind.value = ""
        showTemperature.value = ""
        showCity.value = ""
        showIcon.value = "https://res.cloudinary.com/dzmfpzfou/image/upload/v1631376330/weather/default_xr7sy6.png"
    }

    fun getDataListData(): MutableLiveData<List<DataModelClass.Weather>> {
        if (dataList == null) {
            dataList = MutableLiveData<List<DataModelClass.Weather>>()
        }
        return dataList as MutableLiveData<List<DataModelClass.Weather>>
    }

    fun setDataListData(data: DataModelClass) {
        showTemperature.postValue(data.main.temp.toString())
        showCity.postValue(data.name)
        showWind.postValue(data.wind.speed.toString())
        showHumidity.postValue(data.main.humidity.toString())
        showIcon.postValue(ConstantFun.getWeatherLink(data.weather[0].main))
        dataList?.postValue(data.weather)
    }


    /**
     * calling data api to get the list
     * */
    fun getDataListApi() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = APIRepository.getDataObj(showCity.value, ConstantKey.WEATHER_KEY)
            withContext(Dispatchers.Default) {
                try {
                    if (response!!.isSuccessful) {
                        val dataItems: DataModelClass = response.body() as DataModelClass
                        dataListSuccess.postValue(
                            ApiObserverDataModel<DataModelClass>(
                                dataItems,
                                ""
                            )
                        )
                    } else {
                        val errorMessage = APIUtil.parseError(response)
                        if (errorMessage != null) {
                            setResponseError(errorMessage)
                        }
                    }
                } catch (error: Throwable) {
                    setResponseError(APIConstants.SERVER_ERROR)
                }
            }
        }
    }
}

@BindingAdapter("appIconDrawable", "default")
fun setImageViewDrawable(view: ImageView, url: String, default: Drawable) {
    Picasso.get()
        .load(url)
        .into(view)
}

