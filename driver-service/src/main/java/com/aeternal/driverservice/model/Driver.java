package com.aeternal.driverservice.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
@Document(collection = "drivers")
public class Driver {

    @Id
    @MongoId
    private String id;

    private String firstName;

    private String lastName;

    private int age;

}
