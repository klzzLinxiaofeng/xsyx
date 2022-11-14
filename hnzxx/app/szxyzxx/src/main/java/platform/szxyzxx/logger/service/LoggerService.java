package platform.szxyzxx.logger.service;

import framework.generic.dao.Page;
import platform.szxyzxx.logger.vo.Loggers;
import platform.szxyzxx.logger.vo.LoggerPojo;

import java.util.List;

/**
 * @Author zhangyong
 * @Date 2022/10/31 15:16
 * @Version 1.0
 */
public interface LoggerService {
    Boolean create(Loggers logger);
    List<Loggers> findByAll(LoggerPojo loggerPojo, Page page);
     List<Loggers> findByAlldaochu(LoggerPojo loggerPojo);
}
