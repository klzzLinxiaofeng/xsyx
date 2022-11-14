package com.xunyunedu.common.dao;

import com.xunyunedu.common.pojo.EntityFile;
import org.apache.ibatis.annotations.Param;

/**
 * @author: yhc
 * @Date: 2020/11/2 12:00
 * @Description:
 */
public interface UploaderDao {
    EntityFile findFileByUUID(@Param("uuid") String uuid);

    Integer create(EntityFile entityFile);
}
