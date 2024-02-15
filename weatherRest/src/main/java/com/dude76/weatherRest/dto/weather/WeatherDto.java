package com.dude76.weatherRest.dto.weather;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WeatherDto {

    public ArrayList<AreaMetaDto> area_metadata;
    public ArrayList<ItemDto> items;
    public Api_infoDto api_info;

}
