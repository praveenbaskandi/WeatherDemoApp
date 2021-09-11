package com.praveen.weatherappdemo.appUtil

object ConstantFun {

    /**
     * Convert string to link to show image
     * @param weatherName is model class data from API.
     * @return link for the images.
     * */
    fun getWeatherLink(weatherName: String): String {
        return when (weatherName) {
            "fod" -> "https://res.cloudinary.com/dzmfpzfou/image/upload/v1631376274/weather/fog_hbm21z.png"
            "mist" -> "https://res.cloudinary.com/dzmfpzfou/image/upload/v1631376274/weather/mist_t6oryz.png"
            "wind" -> "https://res.cloudinary.com/dzmfpzfou/image/upload/v1631376178/weather/wind_okfgjy.png"
            "Clear" -> "https://res.cloudinary.com/dzmfpzfou/image/upload/v1631376133/weather/sun_zwij1m.png"
            "Clouds" -> "https://res.cloudinary.com/dzmfpzfou/image/upload/v1631376099/weather/clouds_z9dcum.png"
            "Drizzle" -> "https://res.cloudinary.com/dzmfpzfou/image/upload/v1631377560/weather/rain_nafxzb.png"
            "Rain" -> "https://res.cloudinary.com/dzmfpzfou/image/upload/v1631377560/weather/rain_nafxzb.png"
            "Snow" -> "https://res.cloudinary.com/dzmfpzfou/image/upload/v1631377612/weather/snow_w4eb0f.png"
            else ->
                "https://res.cloudinary.com/dzmfpzfou/image/upload/v1631376330/weather/default_xr7sy6.png"

        }
    }
}