package platform.szxyzxx.evaluationclasses.dao;

import framework.generic.dao.Page;
import platform.szxyzxx.evaluationclasses.vo.ClassesBehavior;
import platform.szxyzxx.lifebehavior.vo.QueryPojo;

import java.util.List;

/**
 * @Author zhangyong
 * @Date 2022/11/3 13:53
 * @Version 1.0
 */
public interface ClassesBehaviorDao {
    /**
     * @param queryPojo
     * @return
     */
    List<ClassesBehavior> findByAll(QueryPojo queryPojo, Page page);
    Integer findByNumber(String schoolYear,String schoolTrem,Integer studentId,Integer liId);

}
