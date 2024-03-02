package com.aeternal.logservice.services.abs;

import com.aeternal.entitychangeservice.model.Log;
import com.aeternal.logservice.model.LogChanges;

import java.util.List;

public interface LogService {

    void saveLog(Log log);

    List<LogChanges> listLogs();

}
