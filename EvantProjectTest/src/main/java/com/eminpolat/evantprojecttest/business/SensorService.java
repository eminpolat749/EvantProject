package com.eminpolat.evantprojecttest.business;

import com.eminpolat.evantprojecttest.entities.Sensor;

import java.util.List;

public interface SensorService {
    List<Sensor> getAllSensors();
    void addSensor(Sensor sensor);
}
