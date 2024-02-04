package com.aeternal.logservice.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class EntityChange implements Serializable {

    private String property;

    private String left;

    private String right;

}
