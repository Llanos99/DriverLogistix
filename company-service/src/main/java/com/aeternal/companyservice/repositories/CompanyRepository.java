package com.aeternal.companyservice.repositories;

import com.aeternal.companyservice.model.Company;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends MongoRepository<Company, String> {


}
