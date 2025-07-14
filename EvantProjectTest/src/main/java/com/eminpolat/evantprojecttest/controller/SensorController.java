package com.eminpolat.evantprojecttest.controller;

import com.eminpolat.evantprojecttest.business.SensorService;
import com.eminpolat.evantprojecttest.entities.Sensor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sensor")
public class SensorController {
    private SensorService sensorService;

    public SensorController(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @GetMapping("/getAll")
    public List<Sensor> getAll() {
        return sensorService.getAllSensors();
    }

    @PostMapping()
    public void addSensor(@RequestBody Sensor sensor) {
        sensorService.addSensor(sensor);
    }
}
