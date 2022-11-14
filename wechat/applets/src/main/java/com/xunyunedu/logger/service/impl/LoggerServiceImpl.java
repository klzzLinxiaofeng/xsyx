package com.xunyunedu.logger.service.impl;

import com.xunyunedu.logger.dao.LoggerDao;
import com.xunyunedu.logger.service.LoggerService;
import com.xunyunedu.logger.vo.Loggers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author zhangyong
 * @Date 2022/10/31 15:17
 * @Version 1.0
 */
@Service
public class LoggerServiceImpl implements LoggerService {
    @Autowired
    private LoggerDao loggerDao;
    @Override
    public Boolean create(Loggers logger) {
        return loggerDao.create(logger)>0?true:false;
    }
}
