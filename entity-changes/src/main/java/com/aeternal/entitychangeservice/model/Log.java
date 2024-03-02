package com.aeternal.entitychangeservice.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Log implements Serializable {

    private String timeStamp;

    private List<EntityChange> changes;

}
