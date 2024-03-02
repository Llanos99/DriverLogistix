package com.aeternal.driverservice.services.impl;

import com.aeternal.driverservice.model.Driver;
import com.aeternal.driverservice.model.utils.CompanySubjectExchange;
import com.aeternal.driverservice.producer.RabbitMQProducer;
import com.aeternal.driverservice.repositories.DriverRepository;
import com.aeternal.driverservice.services.abs.DriverService;
import com.aeternal.entitychangeservice.model.Log;
import com.google.gson.Gson;
import org.bson.types.ObjectId;
import org.javers.core.Javers;
import org.javers.core.diff.Diff;
import org.javers.core.json.JsonConverterBuilder;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverServiceImpl implements DriverService {

    private final DriverRepository driverRepository;

    private final Javers javers;

    private final JsonConverterBuilder jsonConverterBuilder;

    private final RabbitMQProducer rabbitMQProducer;

    @Autowired
    public DriverServiceImpl(DriverRepository driverRepository, Javers javers, JsonConverterBuilder jsonConverterBuilder, RabbitMQProducer rabbitMQProducer) {
        this.driverRepository = driverRepository;
        this.javers = javers;
        this.jsonConverterBuilder = jsonConverterBuilder;
        this.rabbitMQProducer = rabbitMQProducer;
    }

    @Override
    public boolean saveDriver(Driver driver) {
        if (driver != null) {
            if (driver.getId() != null) {
                Log changes = driverChanges(driver.getId(), driver);
                rabbitMQProducer.sendMessage(changes);
            }
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

    @Override
    public List<Driver> listCompanyDrivers(ObjectId companyId) {
        if (companyId != null) {
            return driverRepository.getCompanyDrivers(companyId);
        }
        return null;
    }

    @Override
    @RabbitListener(queues = "company-subject")
    public void updateCompanyDrivers(CompanySubjectExchange data) {
        driverRepository.getCompanyDrivers(new ObjectId(data.getCompanyId()))
                .forEach(driver -> {
                    driver.setAssociatedCompanyName(data.getCompanyName());
                    driverRepository.save(driver);
                });
    }

    public Log driverChanges(String id, Driver newDriver) {
        Driver oldDriver = driverRepository.findById(id).orElse(null);
        if (oldDriver != null) {
            Gson gson = new Gson();
            Diff differences = javers.compare(oldDriver, newDriver);
            return gson.fromJson(jsonConverterBuilder.build().toJson(differences), Log.class);
        }
        return null;
    }

}
