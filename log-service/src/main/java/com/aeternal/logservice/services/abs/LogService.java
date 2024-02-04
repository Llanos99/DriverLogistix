package com.aeternal.logservice.services.abs;

import com.aeternal.logservice.model.Log;

import java.util.List;

public interface LogService {

    void saveLog(Log log);

    List<Log> listLogs();

}
