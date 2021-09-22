package org.sonatype.mavenbook.weather;

import org.sonatype.mavenbook.weather.model.Weather;

public class WeatherService {

    private OpenWeatherRetriever weatherRetriever;
    private OpenWeatherParser weatherParser;

    public WeatherService() {
    }

    public Weather retrieveForecast(String city) throws Exception {
        return weatherParser.parse(weatherRetriever.retrieve(city));
    }

    public OpenWeatherRetriever getWeatherRetriever() {
        return weatherRetriever;
    }

    public void setWeatherRetriever(OpenWeatherRetriever weatherRetriever) {
        this.weatherRetriever = weatherRetriever;
    }

    public OpenWeatherParser getWeatherParser() {
        return weatherParser;
    }

    public void setWeatherParser(OpenWeatherParser weatherParser) {
        this.weatherParser = weatherParser;
    }
}
