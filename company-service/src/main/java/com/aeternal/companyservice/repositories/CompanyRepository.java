package com.aeternal.companyservice.repositories;

import com.aeternal.companyservice.model.Company;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends MongoRepository<Company, String> {

    @Query(value = "{'id' :  ?0}", fields = "{'companyName':  true}")
    String getCurrentCompanyNameById(ObjectId id);

}
