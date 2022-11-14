package platform.education.service.service;

import platform.education.service.model.InSchoolRoom;
import platform.education.service.vo.InSchoolRoomCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.service.vo.RoomVo;

import java.util.List;

public interface InSchoolRoomService {
    InSchoolRoom findInSchoolRoomById(Integer id);

    InSchoolRoom add(InSchoolRoom inSchoolRoom);

    InSchoolRoom modify(InSchoolRoom inSchoolRoom);

    void remove(InSchoolRoom inSchoolRoom);

    List<InSchoolRoom> findInSchoolRoomByCondition(InSchoolRoomCondition inSchoolRoomCondition, Page page, Order order);

    List<InSchoolRoom> findInSchoolRoomByCondition(InSchoolRoomCondition inSchoolRoomCondition);

    List<InSchoolRoom> findInSchoolRoomByCondition(InSchoolRoomCondition inSchoolRoomCondition, Page page);

    List<InSchoolRoom> findInSchoolRoomByCondition(InSchoolRoomCondition inSchoolRoomCondition, Order order);

    Long count();

    Long count(InSchoolRoomCondition inSchoolRoomCondition);

    InSchoolRoom findInSchoolRoomByName(String name);

    List<RoomVo> findInSchoolRoomByUserIdAndStatus(Integer userId, Integer status);
}
