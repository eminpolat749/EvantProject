package com.eminpolat.evantprojecttest.business;

import com.eminpolat.evantprojecttest.dataAccess.SensorRepository;
import com.eminpolat.evantprojecttest.entities.Sensor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SensorManager implements SensorService {

    private SensorRepository sensorRepository;

    public SensorManager(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    @Override
    public List<Sensor> getAllSensors() {
        return sensorRepository.findAll();
    }

    @Override
    public void addSensor(Sensor sensor) {
        sensorRepository.save(sensor);
    }
}
