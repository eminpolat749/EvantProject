package com.eminpolat.evantproject.business;

import com.eminpolat.evantproject.dataAccess.SensorRepository;
import com.eminpolat.evantproject.entites.Sensor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SensorManager implements SensorService {
    private SensorRepository sensorRepository;
    private SensorConsumerService sensorConsumerService;
    private List<Sensor> sensors;

    public SensorManager(SensorRepository sensorRepository, SensorConsumerService sensorConsumerService) {
        this.sensorRepository = sensorRepository;
        this.sensorConsumerService = sensorConsumerService;
        this.sensors = sensorConsumerService.getSensorsFromExternalService();
    }

    public List<Sensor> getSensors() {
        return sensors;
    }

    public void saveToDb() {
        for (Sensor sensor : sensors) 
            sensor.setId(0);

        sensorRepository.saveAll(sensors);
    }

}



