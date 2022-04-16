package com.apap.forecasty.domain.mapper

import com.apap.forecasty.data.ForecastResponse
import com.apap.forecasty.domain.model.*

class ApiParseException(message: String = "") : Exception(message)

fun ForecastResponse?.toForecast() = this?.run {
    Forecast(
        latitude = latitude ?: throw ApiParseException("latitude == null"),
        longitude = longitude ?: throw ApiParseException("longitude == null"),
        timezone = timezone ?: throw ApiParseException("timezone == null"),
        timezoneOffset = timezoneOffset ?: throw ApiParseException("timezone offset == null"),
        currentForecast = current.extractCurrentForecast(),
        minutelyForecast = minutely.extractMinutelyForecast(),
        hourlyForecast = hourly.extractHourlyForecast(),
        dailyForecast = daily.extractDailyForecast(),
        weatherAlerts = alerts.extractAlerts()
    )
}

private fun ForecastResponse.Current?.extractCurrentForecast() = this?.run {
    CurrentForecast(
        dt = dt ?: throw ApiParseException("dt == null"),
        sunrise = sunrise ?: throw ApiParseException("sunrise == null"),
        sunset = sunset ?: throw ApiParseException("sunset == null"),
        temperature = temperature ?: throw ApiParseException("temperature == null"),
        feelsLike = feelsLike ?: throw ApiParseException("feels like == null"),
        pressure = pressure ?: throw ApiParseException("pressure == null"),
        humidity = humidity ?: throw ApiParseException("humidity == null"),
        dewPoint = dewPoint ?: throw ApiParseException("dew point == null"),
        uvIndex = uvIndex ?: throw ApiParseException("ui index == null"),
        clouds = clouds ?: throw ApiParseException("clouds == null"),
        visibility = visibility ?: throw ApiParseException("visibility == null"),
        windSpeed = windSpeed ?: throw ApiParseException("wind speed == null"),
        windDirection = windDirection ?: throw ApiParseException("wind direction == null"),
        weather = weather.extractWeather(),
        rain = rain.extractRain(),
    )
} ?: throw ApiParseException("current forecast == null")

private fun ForecastResponse.Weather?.extractWeather() = this?.run {
    Weather(
        id = id ?: throw ApiParseException("weather id == null"),
        main = main ?: throw ApiParseException("weather main == null"),
        description = description ?: throw ApiParseException("weather description == null"),
        icon = icon ?: throw ApiParseException("weather icon == null"),
    )
} ?: throw ApiParseException("weather == null")

private fun ForecastResponse.Current.Rain?.extractRain() = this?.run {
    Rain(hourlyRainVolume = hourlyRainVolume ?: throw ApiParseException("hourly rain == null"))
} ?: throw ApiParseException("rain == null")

private fun List<ForecastResponse.Minutely>?.extractMinutelyForecast() = this?.run {
    map {
        MinutelyForecast(
            dt = it.dt ?: throw ApiParseException("dt == null"),
            precipitation = it.precipitation ?: throw ApiParseException("precipitation == null"),
        )
    }
} ?: emptyList()

private fun List<ForecastResponse.Hourly>?.extractHourlyForecast() = this?.run {
    map {
        HourlyForecast(
            dt = it.dt ?: throw ApiParseException("dt == null"),
            temperature = it.temperature ?: throw ApiParseException("temperature == null"),
            feelsLike = it.feelsLike ?: throw ApiParseException("feels like == null"),
            pressure = it.pressure ?: throw ApiParseException("pressure == null"),
            humidity = it.humidity ?: throw ApiParseException("humidity == null"),
            dewPoint = it.dewPoint ?: throw ApiParseException("dew point == null"),
            uvIndex = it.uvIndex ?: throw ApiParseException("ui index == null"),
            clouds = it.clouds ?: throw ApiParseException("clouds == null"),
            visibility = it.visibility ?: throw ApiParseException("visibility == null"),
            windSpeed = it.windSpeed ?: throw ApiParseException("wind speed == null"),
            windDirection = it.windDirection ?: throw ApiParseException("wind direction == null"),
            windGust = it.windGust ?: throw ApiParseException("wind gust == null"),
            weather = it.weather.extractWeather(),
            precipitationProbability = it.precipitationProbability ?: throw ApiParseException(
                "precipitation probability == null"
            )
        )
    }
} ?: emptyList()

private fun List<ForecastResponse.Daily>?.extractDailyForecast() = this?.run {
    map {
        DailyForecast(
            dt = it.dt ?: throw ApiParseException("dt == null"),
            sunrise = it.sunrise ?: throw ApiParseException("sunrise == null"),
            sunset = it.sunset ?: throw ApiParseException("sunset == null"),
            moonrise = it.moonrise ?: throw ApiParseException("moonrise == null"),
            moonset = it.moonset ?: throw ApiParseException("moonset == null"),
            moonPhase = it.moonPhase ?: throw ApiParseException("moon phase == null"),
            temperature = it.temperature.extractTemperature(),
            feelsLike = it.feelsLike.extractFeelsLike(),
            pressure = it.pressure ?: throw ApiParseException("pressure == null"),
            humidity = it.humidity ?: throw ApiParseException("humidity == null"),
            dewPoint = it.dewPoint ?: throw ApiParseException("dew point == null"),
            windSpeed = it.windSpeed ?: throw ApiParseException("wind speed == null"),
            windDirection = it.windDirection ?: throw ApiParseException("wind direction == null"),
            weather = it.weather.extractWeather(),
            clouds = it.clouds ?: throw ApiParseException("clouds == null"),
            precipitationProbability = it.precipitationProbability ?: throw ApiParseException(
                "precipitation probability == null"
            ),
            rain = it.rain ?: throw ApiParseException("rain == null"),
            uvIndex = it.uvIndex ?: throw ApiParseException("ui index == null"),
        )
    }
} ?: emptyList()

private fun ForecastResponse.Daily.Temperature?.extractTemperature() = this?.run {
    Temperature(
        day = day ?: throw ApiParseException("day temperature == null"),
        min = min ?: throw ApiParseException("min temperature == null"),
        max = max ?: throw ApiParseException("max temperature == null"),
        night = night ?: throw ApiParseException("night temperature == null"),
        evening = evening ?: throw ApiParseException("evening temperature == null"),
        morning = morning ?: throw ApiParseException("morning temperature == null"),
    )
} ?: throw ApiParseException("temperature == null")

private fun ForecastResponse.Daily.FeelsLike?.extractFeelsLike() = this?.run {
    FeelsLike(
        day = day ?: throw ApiParseException("day feels like == null"),
        night = night ?: throw ApiParseException("night feels like == null"),
        evening = evening ?: throw ApiParseException("evening feels like == null"),
        morning = morning ?: throw ApiParseException("morning feels like == null"),
    )
} ?: throw ApiParseException("feels like == null")

private fun List<ForecastResponse.Alert>?.extractAlerts() = this?.run {
    map {
        WeatherAlert(
            senderName = it.senderName ?: throw ApiParseException("alert sender name == null"),
            event = it.event ?: throw ApiParseException("alert event == null"),
            start = it.start ?: throw ApiParseException("alert start == null"),
            end = it.end ?: throw ApiParseException("alert end == null"),
            description = it.description
                ?: throw ApiParseException("alert description == null"),
        )
    }
} ?: emptyList()