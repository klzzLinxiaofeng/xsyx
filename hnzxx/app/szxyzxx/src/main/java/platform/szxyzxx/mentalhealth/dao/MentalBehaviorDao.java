package platform.szxyzxx.mentalhealth.dao;

import framework.generic.dao.Page;
import platform.szxyzxx.lifebehavior.vo.QueryPojo;
import platform.szxyzxx.mentalhealth.vo.MentalBehavior;

import java.util.List;

/**
 * @Author zhangyong
 * @Date 2022/11/3 13:53
 * @Version 1.0
 */
public interface MentalBehaviorDao {
    /**
     * @param queryPojo
     * @return
     */
    List<MentalBehavior> findByAll(QueryPojo queryPojo, Page page);
    Integer findByNumber(String schoolYear,String schoolTrem,Integer studentId,Integer liId);

}
