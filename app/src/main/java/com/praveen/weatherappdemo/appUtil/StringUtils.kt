package com.praveen.weatherappdemo.appUtil

object StringUtils {

    fun isNotEmptyNonNull(string: String?): Boolean {
        return string != null && string.trim { it <= ' ' }.isNotEmpty()
    }
}