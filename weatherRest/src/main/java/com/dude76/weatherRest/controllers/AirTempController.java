package com.dude76.weatherRest.controllers;

import com.dude76.weatherRest.Services.AirTempService;
import com.dude76.weatherRest.dto.AirTempResponse;
import com.dude76.weatherRest.exceptions.AirTempsDataNotFoundException;
import com.dude76.weatherRest.models.AirTemp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/v1/tempMap")
@RestController
//@CrossOrigin(origins = "http://localhost:3000")
@CrossOrigin("http://localhost:3000")
public class AirTempController {
    @Autowired
    private AirTempService as;

    @GetMapping("test")
    public String test() {
        return "temps test";
    }

    @GetMapping("/")
    public ResponseEntity<AirTempResponse> getTemps() {
        AirTempResponse result = as.getAirTemp();

        List<AirTemp> data = result.getData();

        if (data.isEmpty() || data == null) {

            throw new AirTempsDataNotFoundException("Oops.Unable to retrieve data from Air temps Data Provider");
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
