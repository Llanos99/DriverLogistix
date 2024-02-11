package com.aeternal.companyservice.controller;

import com.aeternal.companyservice.model.Company;
import com.aeternal.companyservice.model.CompanyBusiness;
import com.aeternal.companyservice.services.abs.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {

    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping("/save")
    public ResponseEntity<Boolean> saveCompany(@RequestBody Company company) {
        if (company != null) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(companyService.saveCompany(company));
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/list/all")
    public ResponseEntity<List<CompanyBusiness>> listAllCompanies() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(companyService.listCompaniesBusinesses());
    }

}
