package com.aeternal.driverservice.model;

import lombok.Data;

@Data
public class EntityChange {

    private String property;

    private Object left;

    private Object right;

}
