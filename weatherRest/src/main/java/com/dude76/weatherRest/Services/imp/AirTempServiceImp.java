package com.dude76.weatherRest.Services.imp;

import com.dude76.weatherRest.Services.AirTempService;
import com.dude76.weatherRest.dto.AirTempResponse;
import com.dude76.weatherRest.dto.WeatherResponse;
import com.dude76.weatherRest.dto.airTemp.AirTempDto;
import com.dude76.weatherRest.dto.airTemp.ReadingDto;
import com.dude76.weatherRest.dto.airTemp.StationDto;
import com.dude76.weatherRest.dto.weather.AreaMetaDto;
import com.dude76.weatherRest.dto.weather.ForecastDto;
import com.dude76.weatherRest.dto.weather.WeatherDto;
import com.dude76.weatherRest.models.AirTemp;
import com.dude76.weatherRest.models.Weather2hr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@PropertySource("classpath:myConstants.yaml")
@Service
public class AirTempServiceImp implements AirTempService {
    @Autowired
    private RestTemplate restTemplate;
    @Value("${airTempBaseUrl}")
    String BaseURl_2;

    @Override
    public AirTempResponse getAirTemp() {

        //System.out.println(BaseURl_1);

        //get the json object and mapped to POJO entity
        AirTempDto res = restTemplate.getForObject(BaseURl_2, AirTempDto.class);

        //get all stns
        List<StationDto> allStns = res.getMetadata().getStations();

        //get all readings
        List<ReadingDto> allReadings = res.getItems().get(0).getReadings();

        //get api health status as from the provider
        String _status = res.getApi_info().getStatus();

        //get last updated timestamp
        //Date type have utc time only, no timezone
        Date _lastUpdated = res.getItems().get(0).getTimestamp();
        ZonedDateTime zdt_lastUpdated = null;

        if (_lastUpdated != null) {

            ZoneId zid = ZoneId.of("Asia/Singapore");
            zdt_lastUpdated = _lastUpdated.toInstant().atZone(zid);

        }


        //for collecting the combined result
        List<AirTemp> airTempArr = new ArrayList<>();

        //combine both data
        for (var area : allStns) {

            String _id = area.getId();
            String _name = area.getName();


            float _lat = (float) area.getLocation().getLatitude();
            float _lon = (float) area.getLocation().getLongitude();

            //find reading that have the same stn id from stnArray and readingArr
            ReadingDto readingObj = allReadings.stream().filter(x -> (x.getStation_id().equals(_id)))
                    .findFirst()
                    .orElse(null);

            float _temp = readingObj == null ? null : (float) readingObj.getValue();

            AirTemp at = AirTemp.builder()
                    .name(_name)
                    .lat(_lat)
                    .lon(_lon)
                    .stn_id(_id)
                    .temp(_temp)
                    .build();

            airTempArr.add(at);

        }

        AirTempResponse airTempResponse = AirTempResponse.builder()
                .Data(airTempArr)
                .apiStatus(_status)
                .lastUpdated_timeStamp(zdt_lastUpdated)
                .build();


        return airTempResponse;
    }
}
