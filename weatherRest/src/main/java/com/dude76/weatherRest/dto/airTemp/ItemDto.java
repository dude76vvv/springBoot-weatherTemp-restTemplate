package com.dude76.weatherRest.dto.airTemp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ItemDto {
    public Date timestamp;
    public ArrayList<ReadingDto> readings;
}
