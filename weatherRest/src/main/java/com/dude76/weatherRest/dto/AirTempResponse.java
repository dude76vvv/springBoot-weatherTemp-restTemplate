package com.dude76.weatherRest.dto;


import com.dude76.weatherRest.models.AirTemp;
import com.dude76.weatherRest.models.Weather2hr;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AirTempResponse {

    private List<AirTemp> Data;
    private String apiStatus;
    private ZonedDateTime lastUpdated_timeStamp;
}
