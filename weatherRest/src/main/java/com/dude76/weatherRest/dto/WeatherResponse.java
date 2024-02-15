package com.dude76.weatherRest.dto;

import com.dude76.weatherRest.models.Weather2hr;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class WeatherResponse {
    private List<Weather2hr> Data;
    private String apiStatus;
    private ZonedDateTime update_timestamp;
    private ZonedDateTime valid_startPeriod;

}

