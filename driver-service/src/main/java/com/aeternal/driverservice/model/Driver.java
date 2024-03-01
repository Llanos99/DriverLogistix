package com.aeternal.driverservice.model;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

@Data
@Document(collection = "drivers")
public class Driver {

    @Id
    @MongoId
    private String id;

    private String firstName;

    private String lastName;

    private int age;

    private String associatedCompanyName;

    private ObjectId companyId;
    
    private List<Truck> trucks;

}
