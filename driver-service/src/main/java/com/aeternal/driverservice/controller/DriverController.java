package com.aeternal.driverservice.controller;

import com.aeternal.driverservice.model.Driver;
import com.aeternal.driverservice.services.abs.DriverService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/driver")
public class DriverController {

    private final DriverService driverService;

    @Autowired
    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @PostMapping("/save")
    public ResponseEntity<Boolean> saveDriver(@RequestBody Driver driver) {
        if (driver != null) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(driverService.saveDriver(driver));
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> deleteDriverByFirstName(@RequestParam(name = "firstName") String firstName) {
        if (firstName != null && !firstName.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(driverService.deleteDriverByFirstName(firstName));
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/get")
    public ResponseEntity<Driver> getDriverByFirstName(@RequestParam(name = "firstName") String firstName) {
        if (firstName != null && !firstName.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(driverService.getDriverByFirstName(firstName));
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/list/all")
    public ResponseEntity<List<Driver>> listAllDrivers() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(driverService.listAllDrivers());
    }

    @GetMapping("/list")
    public ResponseEntity<List<Driver>> listDriversOlderThanGivenAge(@RequestParam(name = "age") Integer age) {
        if (age != null) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(driverService.listDriversOlderThanGivenAge(age));
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/company/list")
    public ResponseEntity<List<Driver>> listCompanyDrivers(@RequestParam(name = "companyId") String companyId) {
        if (companyId != null) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(driverService.listCompanyDrivers(new ObjectId(companyId)));
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
