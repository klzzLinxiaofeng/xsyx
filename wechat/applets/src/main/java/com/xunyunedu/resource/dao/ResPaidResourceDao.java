package com.xunyunedu.resource.dao;

import com.xunyunedu.resource.pojo.ResPaidResource;

/**
 * @author edison
 */
public interface ResPaidResourceDao {

    ResPaidResource selectByUuid(String uuid);

    ResPaidResource selectById(Integer id);

    Integer insert(ResPaidResource resPaidResource);


}
