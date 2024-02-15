package com.dude76.weatherRest.controllers;

import com.dude76.weatherRest.Services.WeatherService;
import com.dude76.weatherRest.dto.WeatherResponse;
import com.dude76.weatherRest.exceptions.WeatherDataNotFoundException;
import com.dude76.weatherRest.models.Weather2hr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RequestMapping("/api/v1/weatherMap")
@RestController
public class WeatherController {
    @Autowired
    private WeatherService ws;

    @GetMapping("test")
    public String test() {
        return "weather test";
    }

    @GetMapping("/")
    public ResponseEntity<WeatherResponse> getWeather() {

        WeatherResponse result = ws.getWeather();
        List<Weather2hr> data = result.getData();


        if (data.isEmpty() || data == null) {

            throw new WeatherDataNotFoundException("Oops.Unable to retrieve data from Weather Data Provider");
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
