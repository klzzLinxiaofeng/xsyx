package platform.szxyzxx.socialresponsibility.dao;

import framework.generic.dao.Page;
import platform.szxyzxx.lifebehavior.vo.QueryPojo;
import platform.szxyzxx.socialresponsibility.vo.SocialBehavior;

import java.util.List;

/**
 * @Author zhangyong
 * @Date 2022/11/3 13:53
 * @Version 1.0
 */
public interface SocialalBehaviorDao {
    /**
     * @param queryPojo
     * @return
     */
    List<SocialBehavior> findByAll(QueryPojo queryPojo, Page page);
    Integer findByNumber(String schoolYear,String schoolTrem,Integer studentId,Integer liId);

}
