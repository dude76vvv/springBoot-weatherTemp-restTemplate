package com.dude76.weatherRest.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AirTemp {

    private String stn_id;
    private String name;
    private float lat;
    private float lon;
    private float temp;


}
