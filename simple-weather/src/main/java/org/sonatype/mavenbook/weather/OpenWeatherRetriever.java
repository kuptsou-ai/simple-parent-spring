package org.sonatype.mavenbook.weather;

import org.apache.log4j.Logger;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class OpenWeatherRetriever {
    private final static Logger log = Logger.getLogger(OpenWeatherRetriever.class);

    public InputStream retrieve(String cityName) throws Exception {
        log.info("Retrieving Weather Data");
        String url = String.format("http://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s&mode=xml&units=metric", cityName, "1050bacd3cd59d443f11bba264d6205f");
        URLConnection conn = new URL(url).openConnection();
        return conn.getInputStream();
    }
}