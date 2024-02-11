package com.aeternal.companyservice.services.abs;

import com.aeternal.companyservice.model.Company;
import com.aeternal.companyservice.model.CompanyBusiness;

import java.util.List;

public interface CompanyService {

    boolean saveCompany(Company company);

    boolean deleteCompanyByCompanyName(String companyName);

    CompanyBusiness getCompanyBusinessByCompanyName(String companyName);

    List<CompanyBusiness> listCompaniesBusinesses();

    List<CompanyBusiness> listCompaniesWithGivenDrivers(int numberOfDriver);

}
