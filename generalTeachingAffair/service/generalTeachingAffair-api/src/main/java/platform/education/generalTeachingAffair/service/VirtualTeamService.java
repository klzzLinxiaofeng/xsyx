package platform.education.generalTeachingAffair.service;

import platform.education.generalTeachingAffair.model.VirtualTeam;
import platform.education.generalTeachingAffair.vo.VirtualTeamCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface VirtualTeamService {
    VirtualTeam findVirtualTeamById(Integer id);

    VirtualTeam add(VirtualTeam virtualTeam);

    VirtualTeam modify(VirtualTeam virtualTeam);

    void remove(VirtualTeam virtualTeam);

    List<VirtualTeam> findVirtualTeamByCondition(VirtualTeamCondition virtualTeamCondition, Page page, Order order);

    List<VirtualTeam> findVirtualTeamByCondition(VirtualTeamCondition virtualTeamCondition);

    List<VirtualTeam> findVirtualTeamByCondition(VirtualTeamCondition virtualTeamCondition, Page page);

    List<VirtualTeam> findVirtualTeamByCondition(VirtualTeamCondition virtualTeamCondition, Order order);

    Long count();

    Long count(VirtualTeamCondition virtualTeamCondition);

    List<VirtualTeam> findByGradeId(Integer gradeId);

    List<VirtualTeam> findByGradeIdAndName(Integer schoolId, String year, Integer gradeId, String name);

    /**
     * 校验虚拟班级姓名是否重复
     *
     * @param gradeId 年级ID
     * @param name    班级名，需以年级开头，如一年级XXX，高一XXX
     * @param virtualTeamId     可为null，需排除的班级，编辑已有班级时需排除自身
     * @return true=重复 false=不重复
     */
    boolean checkRepeatName(Integer gradeId, String name, Integer virtualTeamId);
}
