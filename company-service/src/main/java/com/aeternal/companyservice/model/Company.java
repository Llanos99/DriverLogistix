package com.aeternal.companyservice.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
@Document(collection = "companies")
public class Company {

    @Id
    @MongoId
    private String id;

    private String companyName;

    private String companyAddress;

    private String companyEmail;

}
