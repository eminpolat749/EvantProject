package com.eminpolat.evantproject.business;

import com.eminpolat.evantproject.entites.Sensor;

import java.util.List;

public interface SensorService {
    void fetchAndSave();
    List<Sensor> getAllData();
    Sensor getLatestSensor();
}
