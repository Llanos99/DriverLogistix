package com.aeternal.logservice.services.impl;

import com.aeternal.entitychangeservice.model.Log;
import com.aeternal.logservice.model.LogChanges;
import com.aeternal.logservice.repositories.LogRepository;
import com.aeternal.logservice.services.abs.LogService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogServiceImpl implements LogService {

    private final LogRepository logRepository;

    @Autowired
    public LogServiceImpl(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    @Override
    @RabbitListener(queues = "${rabbit.queue.name}")
    public void saveLog(Log log) {
        if (log != null) {
            logRepository.save(new LogChanges(log.getTimeStamp(), log.getChanges()));
        }
    }

    @Override
    public List<LogChanges> listLogs() {
        return logRepository.findAll();
    }

}
