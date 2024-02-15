package com.dude76.weatherRest.dto.airTemp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MetaDataDto {
    public ArrayList<StationDto> stations;
    public String reading_type;
    public String reading_unit;
}
