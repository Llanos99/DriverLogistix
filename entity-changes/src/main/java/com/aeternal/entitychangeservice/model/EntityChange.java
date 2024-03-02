package com.aeternal.entitychangeservice.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class EntityChange implements Serializable{

    private String property;

    private Object oldValue;

    private Object newValue;

}
