package platform.szxyzxx.activitieshome.dao;

import framework.generic.dao.Page;
import platform.szxyzxx.activitieshome.vo.HomeBehavior;
import platform.szxyzxx.lifebehavior.vo.QueryPojo;

import java.util.List;

/**
 * @Author zhangyong
 * @Date 2022/11/3 13:53
 * @Version 1.0
 */
public interface HomeBehaviorDao {
    /**
     * @param queryPojo
     * @return
     */
    List<HomeBehavior> findByAll(QueryPojo queryPojo, Page page);
    Integer findByNumber(String schoolYear,String schoolTrem,Integer studentId,Integer liId);

}
