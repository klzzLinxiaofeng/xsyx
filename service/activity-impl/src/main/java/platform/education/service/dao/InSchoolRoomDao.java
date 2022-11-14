package platform.education.service.dao;

import platform.education.service.model.InSchoolRoom;
import platform.education.service.vo.InSchoolRoomCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;
import platform.education.service.vo.RoomVo;

import java.util.List;

public interface InSchoolRoomDao extends GenericDao<InSchoolRoom, Integer> {

    List<InSchoolRoom> findInSchoolRoomByCondition(InSchoolRoomCondition inSchoolRoomCondition, Page page, Order order);

    InSchoolRoom findById(Integer id);

    Long count(InSchoolRoomCondition inSchoolRoomCondition);

    InSchoolRoom findInSchoolRoomByName(String name);

    List<RoomVo> findInSchoolRoomByUserIdAndStatus(Integer userId, Integer status);
}
