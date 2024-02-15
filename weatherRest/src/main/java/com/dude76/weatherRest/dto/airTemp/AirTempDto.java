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
public class AirTempDto {

    public MetaDataDto metadata;
    public ArrayList<ItemDto> items;
    public Api_infoDto api_info;
}
