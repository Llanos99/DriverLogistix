package com.aeternal.logservice.repositories;

import com.aeternal.logservice.model.LogChanges;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepository extends MongoRepository<LogChanges, String> {
}
