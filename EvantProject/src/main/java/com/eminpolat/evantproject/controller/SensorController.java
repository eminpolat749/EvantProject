package com.eminpolat.evantproject.controller;

import com.eminpolat.evantproject.business.SensorService;
import com.eminpolat.evantproject.entites.Sensor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/evant")
public class SensorController {
    private SensorService sensorService;

    public SensorController(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @GetMapping("/getAll")
    public List<Sensor> getAll() {
        return sensorService.getAllData();
    }

    @GetMapping("/latest")
    public Sensor getLatest()
    {
        return sensorService.getLatestSensor();
    }
}
