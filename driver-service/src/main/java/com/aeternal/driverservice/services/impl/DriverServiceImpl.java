package com.aeternal.driverservice.services.impl;

import com.aeternal.driverservice.model.Driver;
import com.aeternal.driverservice.repositories.DriverRepository;
import com.aeternal.driverservice.services.abs.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverServiceImpl implements DriverService {

    private final DriverRepository driverRepository;

    @Autowired
    public DriverServiceImpl(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    @Override
    public boolean saveDriver(Driver driver) {
        if (driver != null) {
            driverRepository.save(driver);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteDriverByFirstName(String firstName) {
        if (firstName != null && !firstName.isEmpty()) {
            Driver driver = getDriverByFirstName(firstName);
            driverRepository.delete(driver);
            return true;
        }
        return false;
    }

    @Override
    public Driver getDriverByFirstName(String name) {
        return driverRepository.findDriverByFirstName(name);
    }

    @Override
    public List<Driver> listAllDrivers() {
        return driverRepository.findAll();
    }

    @Override
    public List<Driver> listDriversOlderThanGivenAge(int age) {
        return driverRepository.getDriversOlderThanGivenAge(age);
    }
}
