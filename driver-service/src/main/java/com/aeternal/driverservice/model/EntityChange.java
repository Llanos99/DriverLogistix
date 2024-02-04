package com.aeternal.driverservice.model;

import lombok.Data;

@Data
public class EntityChange {

    private String property;

    private String left;

    private String right;

}
