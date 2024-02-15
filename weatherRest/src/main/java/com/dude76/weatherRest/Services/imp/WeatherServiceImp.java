package com.dude76.weatherRest.Services.imp;

import com.dude76.weatherRest.Services.WeatherService;
import com.dude76.weatherRest.dto.WeatherResponse;
import com.dude76.weatherRest.dto.weather.AreaMetaDto;
import com.dude76.weatherRest.dto.weather.ForecastDto;
import com.dude76.weatherRest.dto.weather.WeatherDto;
import com.dude76.weatherRest.models.Weather2hr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//pls check if the api from provider is working before proceeding
//https://api.data.gov.sg/v1/environment/2-hour-weather-forecast

//this maybe down :(


@PropertySource("classpath:myConstants.yaml")
@Service
public class WeatherServiceImp implements WeatherService {
    @Autowired
    private RestTemplate restTemplate;
    @Value("${weatherBaseUrl}")
    String BaseURl_1;

    @Override
    public WeatherResponse getWeather() {

        //System.out.println(BaseURl_1);

        //get the json object and mapped to POJO entity
        WeatherDto res = restTemplate.getForObject(BaseURl_1, WeatherDto.class);

        //get all areas
        List<AreaMetaDto> allAreas = res.getArea_metadata();

        //get all forecasts
        List<ForecastDto> allForecasts = res.getItems().get(0).getForecasts();

        //get api health status as from the provider
        String _status = res.getApi_info().getStatus();

        //get updated timestamp
        Date _lastUpdated = res.getItems().get(0).getUpdate_timestamp();

        ZonedDateTime zdt_lastUpdated = null;
        ZonedDateTime zdt_startPeriod = null;

        if (_lastUpdated != null) {

            Date _startPeriod = res.getItems().get(0).getValid_period().getStart();
            ZoneId zid = ZoneId.of("Asia/Singapore");
            zdt_lastUpdated = _lastUpdated.toInstant().atZone(zid);
            zdt_startPeriod = _startPeriod.toInstant().atZone(zid);

        }

        //for collecting the combined result
        List<Weather2hr> weather2hrArr = new ArrayList<>();

        //combine both data
        for (var area : allAreas) {

            String _name = area.getName();

            float _lat = (float) area.getLabel_location().getLatitude();
            float _lon = (float) area.getLabel_location().getLongitude();

            //find forecast that have the same name as the area name
            ForecastDto forecastObj = allForecasts.stream().filter(x -> (x.getForecast().equals(_name)))
                    .findFirst()
                    .orElse(null);

            String _forecast = forecastObj == null ? "" : forecastObj.getForecast();

            Weather2hr w2 = Weather2hr.builder()
                    .name(_name)
                    .lat(_lat)
                    .lon(_lon)
                    .forecast(_forecast)
                    .build();

            weather2hrArr.add(w2);

        }

        WeatherResponse weatherResponse = WeatherResponse.builder()
                .Data(weather2hrArr)
                .apiStatus(_status)
                .update_timestamp(zdt_lastUpdated)
                .valid_startPeriod(zdt_startPeriod)
                .build();


        return weatherResponse;
    }
}
