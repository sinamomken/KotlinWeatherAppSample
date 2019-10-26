package ir.momken.KotlinWeatherApp

import android.util.Log
import com.google.gson.Gson
import ir.momken.KotlinWeatherApp.data.ForecastResult
import java.net.URL

class ForecastRequest(val zipCode: String) {
    companion object {
        private val APP_ID = "15646a06818f61f7b8d7823ca833e1ce"
        private val URL = "https://api.openweathermap.org/data/2.5/" +
                "forecast/daily?mode=json&units=metric&cnt=7"
        private val COMPLETE_URL = "$URL&APPID=$APP_ID&q="
        private val TAG : String = ForecastRequest.javaClass.name
    }

    fun execute(): ForecastResult {
        val forecastJsonStr : String = URL(COMPLETE_URL+zipCode).readText()
        Log.d(TAG, "forecastJsonStr = $forecastJsonStr")
        return Gson().fromJson(forecastJsonStr, ForecastResult::class.java)
    }
}