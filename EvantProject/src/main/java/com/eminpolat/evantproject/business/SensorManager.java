package com.eminpolat.evantproject.business;

import com.eminpolat.evantproject.dataAccess.SensorRepository;
import com.eminpolat.evantproject.entites.Sensor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.List;

@Service
public class SensorManager implements SensorService {
    private SensorRepository sensorRepository;
    private RestTemplate restTemplate;

    public SensorManager(SensorRepository sensorRepository, RestTemplate restTemplate) {
        this.sensorRepository = sensorRepository;
        this.restTemplate = restTemplate;
    }

    public void fetchAndSave()
    {
        String url = "http://localhost:8080/api/sensor/getAll";
        // http://localhost:8080/api/sensor/getAll

        Sensor sensor = restTemplate.getForObject(url, Sensor.class);
        if (sensor != null)
            sensorRepository.save(sensor);

    }

    public List<Sensor> getAllData() {
        return sensorRepository.findAll();
    }

    public Sensor getLatestSensor() {
        return sensorRepository.findTopByOrderByMeasurementTimeDesc();
    }
}



