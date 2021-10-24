package org.sonatype.mavenbook.web;

import org.sonatype.mavenbook.weather.model.Location;
import org.sonatype.mavenbook.weather.model.Weather;
import org.sonatype.mavenbook.weather.persist.LocationDAO;
import org.sonatype.mavenbook.weather.persist.WeatherDAO;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HistoryController implements Controller {

    private LocationDAO locationDAO;
    private WeatherDAO weatherDAO;

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Location location = locationDAO.findByCity(request.getParameter("city"));
        List<Weather> weathers = weatherDAO.recentForLocation(location);

        Map<String, Object> model = new HashMap<>();
        model.put("location", location);
        model.put("weathers", weathers);

        return new ModelAndView("history", model);
    }

    public WeatherDAO getWeatherDAO() {
        return weatherDAO;
    }

    public void setWeatherDAO(WeatherDAO weatherDAO) {
        this.weatherDAO = weatherDAO;
    }

    public LocationDAO getLocationDAO() {
        return locationDAO;
    }

    public void setLocationDAO(LocationDAO locationDAO) {
        this.locationDAO = locationDAO;
    }
}