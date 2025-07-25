package com.eminpolat.evantproject.business;

import com.eminpolat.evantproject.dataAccess.SensorRepository;
import com.eminpolat.evantproject.dto.SensorDto;
import com.eminpolat.evantproject.entites.Sensor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.time.LocalDateTime;
import java.util.List;

@Service
public class SensorManager implements SensorService {
    private SensorRepository sensorRepository;
    private RestTemplate restTemplate;
    private final String url = "https://isi-nem-88c9f-default-rtdb.europe-west1.firebasedatabase.app/sensor101.json";

    public SensorManager(SensorRepository sensorRepository, RestTemplate restTemplate) {
        this.sensorRepository = sensorRepository;
        this.restTemplate = restTemplate;
    }

    public void fetchAndSave()
    {
        try {
            SensorDto dto = restTemplate.getForObject(url, SensorDto.class);
            if (dto != null) {
                Sensor sensor = new Sensor();
                sensor.setSensorId(dto.getSensorId());
                sensor.setTemperature(dto.getTemperature());
                sensor.setHumidity(dto.getHumidity());
                sensor.setMeasurementTime(LocalDateTime.now());

                sensorRepository.save(sensor);
                System.out.println("✅ Veri kaydedildi: " + sensor.getTemperature() + "°C / " + sensor.getHumidity() + "%");
                System.out.println("Veri kaydediliyor: sensorId=" + dto.getSensorId());
            }
        } catch (Exception e) {
            System.err.println("❌ Veri çekme hatası: " + e.getMessage());
        }
    }

    public List<Sensor> getAllData() {
        return sensorRepository.findAll();
    }

    public Sensor getLatestSensor() {
        return sensorRepository.findTopByOrderByMeasurementTimeDesc();
    }
}



