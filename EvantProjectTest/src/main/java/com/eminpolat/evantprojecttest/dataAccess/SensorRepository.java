package com.eminpolat.evantprojecttest.dataAccess;

import com.eminpolat.evantprojecttest.entities.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SensorRepository extends JpaRepository<Sensor, Integer> {

}
