package org.sonatype.mavenbook.weather.yahoo;

import junit.framework.TestCase;
import org.sonatype.mavenbook.weather.OpenWeatherParser;
import org.sonatype.mavenbook.weather.model.Weather;

import java.io.InputStream;

public class OpenWeatherParserTest extends TestCase {
    public OpenWeatherParserTest(String name) {
        super(name);
    }

    public void testParser() throws Exception {
        InputStream nyData = getClass().getClassLoader()
                .getResourceAsStream("minsk-weather.xml");
        Weather weather = new OpenWeatherParser().parse(nyData);
        assertEquals("Minsk", weather.getLocation().getCity());
        assertEquals("BY", weather.getLocation().getCountry());
        assertEquals("18.86 celsius", weather.getCondition().getTemperature());
        assertEquals("18.63 celsius", weather.getCondition().getFeelsLike());
        assertEquals("70%", weather.getAtmosphere().getHumidity());
        assertEquals("1014 hPa", weather.getAtmosphere().getPressure());
        assertEquals("10000", weather.getAtmosphere().getVisibility());
        assertEquals("clear sky", weather.getAtmosphere().getClouds());
        assertEquals("Calm", weather.getWind().getDescription());
        assertEquals("0.93 m/s", weather.getWind().getSpeed());
        assertEquals("North-northeast", weather.getWind().getDirection());
    }
}