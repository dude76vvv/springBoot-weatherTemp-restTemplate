package com.dude76.weatherRest.dto.weather;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;

@Data
public class ItemDto {
    public Date update_timestamp;
    public Date timestamp;
    public ValidPeriodDto valid_period;
    public ArrayList<ForecastDto> forecasts;

    public static class WeatherResponseDto {
    }
}
