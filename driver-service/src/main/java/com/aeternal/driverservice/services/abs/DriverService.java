package com.aeternal.driverservice.services.abs;

import com.aeternal.driverservice.model.Driver;

import java.util.List;

public interface DriverService {

    boolean saveDriver(Driver driver);

    boolean deleteDriverByFirstName(String firstName);

    Driver getDriverByFirstName(String name);

    List<Driver> listAllDrivers();

    List<Driver> listDriversOlderThanGivenAge(int age);

}
