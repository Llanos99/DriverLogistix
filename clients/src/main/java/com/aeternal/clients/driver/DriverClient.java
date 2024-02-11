package com.aeternal.clients.driver;

import com.aeternal.clients.models.Driver;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "DRIVER-MS", path = "/driver")
public interface DriverClient {

    @GetMapping("/company/list")
    List<Driver> listCompanyDrivers(@RequestParam(name = "companyId") String companyId);

}
