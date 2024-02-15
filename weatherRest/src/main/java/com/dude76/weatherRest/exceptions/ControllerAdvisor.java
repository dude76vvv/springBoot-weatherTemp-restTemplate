package com.dude76.weatherRest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(AirTempsDataNotFoundException.class)
    public ResponseEntity<Object> handleAirTempsDataNotFoundException(AirTempsDataNotFoundException ex, WebRequest request) {

        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(WeatherDataNotFoundException.class)
    public ResponseEntity<Object> handleWeatherDataNotFoundException(WeatherDataNotFoundException ex, WebRequest request) {

        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
