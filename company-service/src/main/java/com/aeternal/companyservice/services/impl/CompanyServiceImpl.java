package com.aeternal.companyservice.services.impl;

import com.aeternal.clients.driver.DriverClient;
import com.aeternal.clients.models.Driver;
import com.aeternal.companyservice.model.Company;
import com.aeternal.companyservice.model.CompanyBusiness;
import com.aeternal.companyservice.producer.CompanyProducer;
import com.aeternal.companyservice.repositories.CompanyRepository;
import com.aeternal.companyservice.services.abs.CompanyService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    private final DriverClient driverClient;

    private final CompanyProducer companyProducer;

    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository, DriverClient driverClient, CompanyProducer companyProducer) {
        this.companyRepository = companyRepository;
        this.driverClient = driverClient;
        this.companyProducer = companyProducer;
    }

    @Override
    public boolean saveCompany(Company company) {
        if(company != null) {
            if (company.getId() != null) {
                String companyId = company.getId();
                String currentCompanyName = company.getCompanyName();
                String databaseCompanyName = companyRepository.getCurrentCompanyNameById(new ObjectId(company.getId())).getCompanyName();
                if (!databaseCompanyName.equals(currentCompanyName)) {
                    companyProducer.sendMessage(companyId, currentCompanyName);
                }
            }
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
        return companyRepository.findAll()
                .stream()
                .map(company -> {
                    List<Driver> companyDrivers = driverClient.listCompanyDrivers(company.getId());
                    return CompanyBusiness
                            .builder()
                            .companyData(company)
                            .companyDrivers(companyDrivers)
                            .build();
                })
                .toList();
    }

    @Override
    public List<CompanyBusiness> listCompaniesWithGivenDrivers(int numberOfDriver) {
        return null;
    }
}
