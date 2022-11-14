package platform.szxyzxx.resource.dao;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.resource.model.Resource;
import platform.education.resource.vo.ResourceCondition;

import java.util.List;
import java.util.Map;

public interface EasyResourceDao {

    List<Resource> findResourceByCondition(ResourceCondition condition, Page page, Order order);
    int updateBySql(Map<String, String> param);
}
