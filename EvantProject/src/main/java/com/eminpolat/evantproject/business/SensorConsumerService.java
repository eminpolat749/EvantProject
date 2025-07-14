package com.eminpolat.evantproject.business;

import com.eminpolat.evantproject.entites.Sensor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class SensorConsumerService {
    private RestTemplate restTemplate;

    public SensorConsumerService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Sensor> getSensorsFromExternalService()
    {
        String url = "http://localhost:8080/api/sensor/getAll";

        ResponseEntity<Sensor[]> response = restTemplate.getForEntity(url, Sensor[].class);
        Sensor[] body = response.getBody();

        return Arrays.asList(body);
    }
}
