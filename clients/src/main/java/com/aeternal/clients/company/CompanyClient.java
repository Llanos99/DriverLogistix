package com.aeternal.clients.company;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "COMPANY-MS", path = "/company")
public interface CompanyClient {
}
