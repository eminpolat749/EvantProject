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
        return sensorService.getSensors();
    }

    @PostMapping("/save")
    public void saveToDb() {
        sensorService.saveToDb();
    }
}
