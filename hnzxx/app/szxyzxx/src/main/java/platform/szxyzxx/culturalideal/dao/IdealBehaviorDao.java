package platform.szxyzxx.culturalideal.dao;

import framework.generic.dao.Page;
import platform.szxyzxx.culturalideal.vo.IdealBehavior;
import platform.szxyzxx.lifebehavior.vo.QueryPojo;

import java.util.List;

/**
 * @Author zhangyong
 * @Date 2022/11/3 13:53
 * @Version 1.0
 */
public interface IdealBehaviorDao {
    /**
     * @param queryPojo
     * @return
     */
    List<IdealBehavior> findByAll(QueryPojo queryPojo, Page page);
    Integer findByNumber(String schoolYear,String schoolTrem,Integer studentId,Integer liId);

}
