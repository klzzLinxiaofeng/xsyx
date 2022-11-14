package platform.education.generalTeachingAffair.service;

import platform.education.generalTeachingAffair.model.StudentAlteration;
import platform.education.generalTeachingAffair.model.TeamStudent;
import platform.education.generalTeachingAffair.vo.TeamStudentCondition;
import platform.education.generalTeachingAffair.vo.TeamStudentVo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface TeamStudentService {

    /**
     * 当前接口crud操作 成功时返回的状态值
     */
    public final static String OPERATE_SUCCESS = "success";

    /**
     * 当前接口crud操作 失败时返回的状态值
     */
    public final static String OPERATE_FAIL = "fail";

    /**
     * 系统异常造成的操作失败 系统返回的状态值
     */
    public final static String OPERATE_ERROR = "error";

    /**
     * 当前接口crud操作 已存在记录返回的状态值
     */
    public final static String OPERATE_EXIST = "exist";

    TeamStudent findTeamStudentById(Integer id);

    /**
     * 功能描述：通过班级ID,学生ID查询出唯一记录
     *
     * @param teamId
     * @param studentId
     * @return
     */
    TeamStudent findUnique(Integer teamId, Integer studentId);
    /**
     * 功能描述：通过班级ID,学生ID查询出唯一记录2
     *
     * @param teamId
     * @param studentId
     * @return
     */
    TeamStudent findUnique2(Integer teamId, Integer studentId,String year);

    /**
     * 功能描述：通过id关联查询出该学生所属的学年、年级、班级
     *
     * @param id
     * @return
     */
    TeamStudentVo findTeamStudentByIdMore(Integer id, Integer schoolId);

    TeamStudent add(TeamStudent teamStudent);

    TeamStudent modify(TeamStudent teamStudent);

    void remove(TeamStudent teamStudent);

    List<TeamStudent> findTeamStudentByCondition(TeamStudentCondition teamStudentCondition, Page page, Order order);

    /**
     * 些方法为调班制定，并不通用
     *
     * @param teamStudentCondition
     * @param page
     * @param order
     * @return
     */
    List<TeamStudent> findTeamStudentByConditionForTransfer(TeamStudentCondition teamStudentCondition, Page page, Order order);


    /**
     * 功能描述：关联查询出学年名称、年级名称、班级名称
     *
     * @param teamStudentCondition
     * @param page
     * @param order
     * @return
     */
    List<TeamStudentVo> findTeamStudentByConditionMore(TeamStudentCondition teamStudentCondition, Page page, Order order);

    /**
     * 功能描述：关联查询出学生的个人信息
     *
     * @param teamStudentCondition
     * @param page
     * @param order
     * @return
     */
    List<TeamStudentVo> findTeamStudentByConditionStudent(TeamStudentCondition teamStudentCondition, Page page, Order order);


    /**
     * 校车系统查询分配的学生信息
     *
     * @param teamStudentCondition
     * @param page
     * @param order
     * @return
     */
    List<TeamStudentVo> findTeamStudentByConditionStudentSchoolBus(TeamStudentCondition teamStudentCondition, Page page, Order order);

    //public void findStudentNumByGrade(Integer schoolId);

    /**
     * 功能描述：通过条件（学校ID，学校当前学年等条件）查询班级学生
     *
     * @param teamStudentCondition
     * @param page
     * @param order
     * @return
     */
    List<TeamStudent> findCurrentTeamStudentByCondition(TeamStudentCondition teamStudentCondition, Page page, Order order);

    /***
     * 统计每个班的人数
     */
    public List<TeamStudentVo> findTeamNum();

    /**
     * 获得总数
     * 2015-12-25
     *
     * @return
     */
    Long count();

    /**
     * 功能描述：通过条件获得总数
     * 2015-12-25
     *
     * @param teamStudentCondition
     * @return
     */
    Long count(TeamStudentCondition teamStudentCondition);

    /**
     * 功能描述：学生更换班级
     * 2015-12-28
     *
     * @param studentId
     * @param oldTeamId
     * @param newTeamId
     * @return
     */
    String moveStudentToTeam(Integer studentId, Integer oldTeamId, Integer newTeamId);

    /**
     * 功能描述：学生加入班级
     * 2016-1-26
     *
     * @param studentId
     * @param teamId
     * @return
     */
    String assignStudentToTeam(Integer studentId, Integer teamId);

    /**
     * 功能描述：学生从班级移除
     *
     * @param studentId
     * @param teamId
     * @return
     */
    String removeStudentFromTeam(Integer studentId, Integer teamId);

    /**
     * 功能描述：通过班级ID(teamId)获得一个班的当前在读的学生名单
     * 2016-01-12
     *
     * @param teamId
     * @return
     */
    List<TeamStudentVo> getTeamStudentsByTeamId(Integer teamId);

    /**
     * 功能描述：学生变更记录信息添加
     * 2016-03-14
     *
     * @param studentId
     * @param oldTeamId
     * @param newTeamId
     * @param alterType
     * @return
     */
    StudentAlteration addStudentAlteration(Integer studentId,
                                           Integer oldTeamId, Integer newTeamId, String alterType);


    /**
     * 功能描述：升级并分班（适用于在校生分班业务）
     * 2017-07-28
     *
     * @param studentId
     * @param newTeamId
     * @param oldTeamId
     * @return
     */
    String upgradeAndAssignToTeam(Integer studentId, Integer newTeamId, Integer oldTeamId);

    /**
     * 通过班级查找学生
     *
     * @param teamId
     * @return
     */
    List<TeamStudent> findByTeamId(Integer teamId);
    /**
     * 通过班级和学生在读状态查找学生
     *
     * @param teamId
     * @return
     */
    List<TeamStudent> findByTeamId2(Integer teamId,String stats);

    /**
     * 通过班级查找学生
     *
     * @param teamId
     * @return
     */
    List<TeamStudent> findByTeamIds(Integer[] teamIds);

    List<TeamStudentVo> findTeamStudentVoByTeamIds(Integer[] teamIds);


    List<TeamStudentVo> findTeamStudentsByTeamId(Integer teamId);

    /**
     * 查询一个年级的总学生数
     *
     * @param gradeId 年级ID
     * @return
     */
    Integer findGradeStudentCountByGradeId(Integer gradeId);

    /**
     * 根据班级id和班内学号查询学生
     *
     * @param teamId
     * @param number
     * @return
     */
    TeamStudent findByTeamIdAndNumber(Integer teamId, Integer number);

    /**
     * 查找最大班内学号
     *
     * @param teamId
     * @return
     */
    Integer findMaxTeamNumberByTeamId(Integer teamId);

    void deleteByTeamId(Integer teamId);


    /**
     * 通过条件获取学生 : gradeId,teamId可为空
     *
     * @return 带有teamName, gradeName
     */
    List<TeamStudentVo> findTeamStudentVo(Integer schoolId, String schoolYear, Integer gradeId, Integer teamId);

}
