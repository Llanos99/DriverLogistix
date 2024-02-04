package com.aeternal.logservice.controller;

import com.aeternal.logservice.model.Log;
import com.aeternal.logservice.services.abs.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/log")
public class LogController {

    private final LogService logService;

    @Autowired
    public LogController(LogService logService) {
        this.logService = logService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<Log>> listLogs() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(logService.listLogs());
    }

}
