package com.dude76.weatherRest.dto.weather;


import lombok.Data;

@Data
public class ForecastDto {
    private String area;
    private String forecast;
}
