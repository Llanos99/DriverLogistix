package com.aeternal.clients.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class Driver {

    private String id;

    private String firstName;

    private String lastName;

    private int age;

    @JsonIgnore
    private Object companyId;

}
