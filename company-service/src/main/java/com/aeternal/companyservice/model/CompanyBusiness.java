package com.aeternal.companyservice.model;

import com.aeternal.clients.models.Driver;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompanyBusiness {

    private Company companyData;

    private List<Driver> companyDrivers;

}
