package platform.education.generalTeachingAffair.dao;

import framework.generic.dao.GenericDao;
import platform.education.generalTeachingAffair.model.SchoolBusMangerVo;

import java.util.List;

public interface SchoolBusDao extends GenericDao<SchoolBusMangerVo, Integer> {

    void createStuBusManger(String[] split, SchoolBusMangerVo schoolBusMangerVo);

    /**
     * 删除
     *
     * @param split
     */
    void updateStuBusManger(SchoolBusMangerVo schoolBusMangerVo, String[] split);

    void updateListenerStuBusManger(SchoolBusMangerVo schoolBusMangerVo, String[] split);

    /**
     * 修改这条信息的发送状态
     *
     * @param systemId
     * @param stuIds
     */
    void updateSendState(Integer systemId, Integer[] stuIds);

    List<String> findByCondition(SchoolBusMangerVo schoolBusMangerVo, String[] split);

    void deleteBatch(String split);
}
