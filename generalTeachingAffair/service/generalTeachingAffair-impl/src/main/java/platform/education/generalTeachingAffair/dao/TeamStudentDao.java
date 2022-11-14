package platform.education.generalTeachingAffair.dao;

import framework.generic.dao.GenericDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.model.TeamStudent;
import platform.education.generalTeachingAffair.vo.TeamStudentCondition;
import platform.education.generalTeachingAffair.vo.TeamStudentVo;

import java.util.List;

public interface TeamStudentDao extends GenericDao<TeamStudent, java.lang.Integer> {
	List<TeamStudent> findTeamStudentByCondition(TeamStudentCondition teamStudentCondition);

	List<TeamStudent> findTeamStudentByCondition(TeamStudentCondition teamStudentCondition, Page page, Order order);
	
	
	List<TeamStudent> findTeamStudentByConditionForTransfer(TeamStudentCondition teamStudentCondition, Page page, Order order);
	
	
	/**
	 * 功能描述：关联查询出学年名称、年级名称、班级名称
	 * @param teamStudentCondition
	 * @param page
	 * @param order
	 * @return
	 */
	List<TeamStudentVo> findTeamStudentByConditionMore(TeamStudentCondition teamStudentCondition, Page page, Order order);

	/**
	 * 功能描述：关联查询出学生的个人信息
	 * @param teamStudentCondition
	 * @param page
	 * @param order
	 * @return
	 */
	List<TeamStudentVo> findTeamStudentByConditionStudent(TeamStudentCondition teamStudentCondition, Page page, Order order);


	List<TeamStudentVo> findTeamStudentByConditionStudentSchoolBus(TeamStudentCondition teamStudentCondition, Page page, Order order);

	TeamStudent findById(Integer id);
	
	/**
     * 功能描述：通过班级ID,学生ID查询出唯一记录
     * @param teamId
     * @param studentId
     * @return
     */
    TeamStudent findUnique(Integer teamId, Integer studentId);
	TeamStudent findUnique2(Integer teamId, Integer studentId,String year);
    
    /**
     * 功能描述：通过id关联查询出该学生所属的学年、年级、班级
     * @param id
     * @return
     */
    TeamStudentVo findByIdMore(Integer id, Integer schoolId);
    
    /**
     * 功能描述：通过条件（学校ID，学校当前学年等条件）查询班级学生
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
	 * 功能描述：通过班级ID(teamId)获得一个班的当前在读的学生名单
	 * 2016-01-12
	 * @param teamId
	 * @return
	 */
    List<TeamStudentVo> findTeamStudentsByTeamId(Integer teamId);
    
    /**
     * 功能描述：通过条件获得总数
     * 2015-12-25
     * @param teamStudentCondition
     * @return
     */
    Long count(TeamStudentCondition teamStudentCondition);
    
    List<TeamStudent> findByTeamId(Integer teamId);

	List<TeamStudent> findByTeamId2(Integer teamId,String stats);
    List<TeamStudent> findByTeamIds(Integer[] teamIds);
    
    List<TeamStudentVo>findTeamStudentVoByTeamIds(Integer[] teamIds);
    
    Integer findGradeStudentCountByGradeId(Integer gradeId);

    /***
     * 查找班内最大学号
     * @param teamId
     * @return
     */
	Integer findMaxTeamNumberByTeamId(Integer teamId);

	void deleteByTeamId(Integer teamId);

	/**
	 * 通过条件获取学生 : gradeId,teamId可为空
	 * @return 带有teamName, gradeName
	 */
	List<TeamStudentVo> findTeamStudentVo(Integer schoolId, String schoolYear, Integer gradeId, Integer teamId);
}
