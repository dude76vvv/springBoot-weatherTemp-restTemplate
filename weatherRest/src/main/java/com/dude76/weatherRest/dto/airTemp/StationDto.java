package com.dude76.weatherRest.dto.airTemp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class StationDto {
    public String id;
    public String device_id;
    public String name;
    public LocationDto location;
}
