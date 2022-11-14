package platform.szxyzxx.logger.service.impl;

import framework.generic.dao.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.szxyzxx.logger.dao.LoggerDao;
import platform.szxyzxx.logger.service.LoggerService;
import platform.szxyzxx.logger.vo.Loggers;
import platform.szxyzxx.logger.vo.LoggerPojo;

import java.util.List;

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

    @Override
    public List<Loggers> findByAll(LoggerPojo loggerPojo, Page page) {
        return loggerDao.findByAll(loggerPojo,page);
    }

    @Override
    public List<Loggers> findByAlldaochu(LoggerPojo loggerPojo) {
        return loggerDao.findByAlldaochu(loggerPojo);
    }
}
