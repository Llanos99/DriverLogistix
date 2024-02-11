package com.aeternal.companyservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class CompanyBusiness {

    private Company companyData;

    private List<Driver> companyDrivers;

}
