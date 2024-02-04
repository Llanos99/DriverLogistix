package com.aeternal.driverservice.model;

import lombok.Data;

import java.util.List;

@Data
public class Log {

    private String timestamp;

    private List<EntityChange> changes;

}
