package com.xunyunedu.logger.dao;

import com.xunyunedu.logger.vo.Loggers;
import org.apache.ibatis.annotations.Param;

/**
 * @Author zhangyong
 * @Date 2022/10/31 15:17
 * @Version 1.0
 */
public interface LoggerDao {
    Integer create(@Param("logger") Loggers logger);
}
