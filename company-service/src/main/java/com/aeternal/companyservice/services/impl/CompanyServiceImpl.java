package com.aeternal.companyservice.services.impl;

import com.aeternal.companyservice.model.Company;
import com.aeternal.companyservice.model.CompanyBusiness;
import com.aeternal.companyservice.repositories.CompanyRepository;
import com.aeternal.companyservice.services.abs.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public boolean saveCompany(Company company) {
        if(company != null) {
            companyRepository.save(company);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteCompanyByCompanyName(String companyName) {
        return true;
    }

    @Override
    public CompanyBusiness getCompanyBusinessByCompanyName(String companyName) {
        return null;
    }

    @Override
    public List<CompanyBusiness> listCompaniesBusinesses() {
        return null;
    }

    @Override
    public List<CompanyBusiness> listCompaniesWithGivenDrivers(int numberOfDriver) {
        return null;
    }
}
