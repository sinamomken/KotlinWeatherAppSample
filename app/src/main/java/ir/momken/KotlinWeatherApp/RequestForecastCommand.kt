package ir.momken.KotlinWeatherApp

import ir.momken.KotlinWeatherApp.domain.ForecastDataMapper
import ir.momken.KotlinWeatherApp.domain.model.ForecastList

class RequestForecastCommand(val zipCode: String) : Command<ForecastList> {
    override fun execute(): ForecastList {
        val forecastRequest = ForecastRequest(zipCode)
        return ForecastDataMapper().convertFromDataModel(
            forecastRequest.execute()
        )
    }
}