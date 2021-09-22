package org.sonatype.mavenbook.weather;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.sonatype.mavenbook.weather.model.Atmosphere;
import org.sonatype.mavenbook.weather.model.Condition;
import org.sonatype.mavenbook.weather.model.Location;
import org.sonatype.mavenbook.weather.model.Weather;
import org.sonatype.mavenbook.weather.model.Wind;

import java.io.InputStream;
import java.util.Date;

public class OpenWeatherParser {
    private final static Logger log = Logger.getLogger(OpenWeatherParser.class);

    public Weather parse(InputStream inputStream) throws Exception {
        Weather weather = new Weather();
        log.info("Creating XML Reader");
        SAXReader xmlReader = new SAXReader();
        Document doc = xmlReader.read(inputStream);

        log.info("Parsing XML Response");
        Location location = new Location();
        location.setCity(doc.selectSingleNode("current/city").valueOf("@name"));
        location.setCountry(doc.selectSingleNode("current/city").selectSingleNode("country").getText());
        weather.setLocation(location);

        Condition condition = new Condition();
        condition.setTemperature(doc.selectSingleNode("current/temperature").valueOf("@value")
                + " " + doc.selectSingleNode("current/temperature").valueOf("@unit"));
        condition.setFeelsLike(doc.selectSingleNode("current/feels_like").valueOf("@value")
                + " " + doc.selectSingleNode("current/feels_like").valueOf("@unit"));
        weather.setCondition(condition);

        Atmosphere atmosphere = new Atmosphere();
        atmosphere.setHumidity(doc.selectSingleNode("current/humidity").valueOf("@value")
                + doc.selectSingleNode("current/humidity").valueOf("@unit"));
        atmosphere.setPressure(doc.selectSingleNode("current/pressure").valueOf("@value")
                + " " + doc.selectSingleNode("current/pressure").valueOf("@unit"));
        atmosphere.setVisibility(doc.selectSingleNode("current/visibility").valueOf("@value"));
        atmosphere.setClouds(doc.selectSingleNode("current/clouds").valueOf("@name"));
        weather.setAtmosphere(atmosphere);

        Wind wind = new Wind();
        Node windNode = doc.selectSingleNode("current/wind");
        wind.setSpeed(windNode.selectSingleNode("speed").valueOf("@value")
                + " " + windNode.selectSingleNode("speed").valueOf("@unit"));
        wind.setDescription(windNode.selectSingleNode("speed").valueOf("@name"));
        wind.setDirection(windNode.selectSingleNode("direction").valueOf("@name"));
        weather.setWind(wind);

        weather.setDate(new Date());

        return weather;
    }
}