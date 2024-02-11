package com.aeternal.driverservice.repositories;

import com.aeternal.driverservice.model.Driver;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DriverRepository extends MongoRepository<Driver, String> {

    Driver findDriverByFirstName(String firstName);

    @Query("{'age' :  { $gte : ?0 }}")
    List<Driver> getDriversOlderThanGivenAge(int age);

    @Query("{'companyId' :  ?0}")
    List<Driver> getCompanyDrivers(ObjectId companyId);

}
