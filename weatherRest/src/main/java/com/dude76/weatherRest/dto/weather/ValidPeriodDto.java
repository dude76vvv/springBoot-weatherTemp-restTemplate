package com.dude76.weatherRest.dto.weather;

import lombok.Data;

import java.util.Date;


@Data
public class ValidPeriodDto {
    public Date start;
    public Date end;
}
