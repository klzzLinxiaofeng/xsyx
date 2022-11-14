package com.xunyunedu.student.dao;

import com.xunyunedu.student.pojo.ParentPojo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 家长信息
 *
 * @author: yhc
 * @Date: 2020/12/2 10:31
 * @Description:
 */
public interface ParentDao {

    List<ParentPojo> read(ParentPojo parentPojo);

    void update(ParentPojo pojo);

    ParentPojo getParentbyStuId(@Param("stuId") Integer stuId);

    Integer flagSameParentLicensePlate(ParentPojo parentPojo);
}
