package com.aeternal.companyservice.repositories;

import com.aeternal.companyservice.model.Company;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface CompanyRepository extends MongoRepository<Company, String> {

    @Query(value = "{'id' :  ?0}", fields = "{'id': false, 'companyName':  true}")
    Company getCurrentCompanyNameById(ObjectId id);

}
