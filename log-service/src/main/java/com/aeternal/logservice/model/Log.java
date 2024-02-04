package com.aeternal.logservice.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.io.Serializable;
import java.util.List;

@Data
@Document(collection = "logs")
public class Log implements Serializable {

    @Id
    @MongoId
    private String id;

    private String timestamp;

    private List<EntityChange> changes;

}
