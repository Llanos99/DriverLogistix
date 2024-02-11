package com.aeternal.clients.driver;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "DRIVER-MS", path = "/driver")
public interface DriverClient {



}
