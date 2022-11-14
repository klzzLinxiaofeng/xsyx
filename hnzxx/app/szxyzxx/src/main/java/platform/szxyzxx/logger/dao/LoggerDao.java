package platform.szxyzxx.logger.dao;

import framework.generic.dao.Page;
import platform.szxyzxx.logger.vo.Loggers;
import platform.szxyzxx.logger.vo.LoggerPojo;

import java.util.List;

/**
 * @Author zhangyong
 * @Date 2022/10/31 15:17
 * @Version 1.0
 */
public interface LoggerDao {
    Integer create(Loggers logger);
    List<Loggers> findByAll(LoggerPojo loggerPojo, Page page);
    List<Loggers> findByAlldaochu(LoggerPojo loggerPojo);
}
