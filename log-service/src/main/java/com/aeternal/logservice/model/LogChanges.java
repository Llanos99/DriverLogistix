package com.aeternal.logservice.model;

import com.aeternal.entitychangeservice.model.EntityChange;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.io.Serializable;
import java.util.List;

@Data
@Document(collection = "logs")
@RequiredArgsConstructor
public class LogChanges implements Serializable {

    @Id
    @MongoId
    private String id;

    @NonNull
    private String timestamp;

    @NonNull
    private List<EntityChange> changes;

}
