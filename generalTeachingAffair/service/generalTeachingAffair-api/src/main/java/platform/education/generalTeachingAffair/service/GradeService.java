package platform.education.generalTeachingAffair.service;
import platform.education.generalTeachingAffair.model.Grade;
import platform.education.generalTeachingAffair.vo.GradeCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.Date;
import java.util.List;

public interface GradeService {
	
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
	 * 返回学校所指定学年的所有年级
	 * @param schoolId
	 * @param schoolYear
	 * @return
	 */
	List<Grade> findGradeBySchoolYear(Integer schoolId,String schoolYear);
	/**
	 * 返回学校所指定学年的所有年级
	 * @param schoolYear
	 * @return
	 */
	List<Grade> findGradeBySchoolYear(String schoolYear);
	
	/***
	 * 
	 * @param schoolId
	 * @return
	 */
	public List<Grade> findGrageListBySchoolId(Integer schoolId);
	/**
	 * 
	 * @param code
	 * @return
	 */
	public List<Grade> findGradeByCode(String code);
	
    Grade findGradeById(Integer id);
	   
	Grade add(Grade grade);
	   
	Grade modify(Grade grade);
	   
	String remove(Grade grade);
	
	List<Grade> findGradeByCondition(GradeCondition gradeCondition, Page page, Order order);
	
	List<Grade> findGradeByConditionTemp(GradeCondition gradeCondition, Page page, Order order);
	
	/**
	 * 根据旧年级创建升级后的年级
	 * @param gradeId
	 * @return
	 */
	Grade createUpgradeGrade(Integer gradeId);

	/**
	 * 获取学校年级（可增量）
	 * @param schoolId
	 * @param schoolYear
	 * @param isDeleted
	 * @param gradeId
	 * @param isGetNew
	 * @return
	 */
	List<Grade> findIncrementByModifyDate(Integer schoolId, String schoolYear, Boolean isDeleted, Date modifyDate, Integer gradeId, Boolean isGetNew);
	/**
	 * 根据学校id、学年和年级名称查询年级
	 * @param schoolId 学校id
	 * @param schoolYear 学年
	 * @param gradeName 年级名称
	 * @return
	 */
	Grade findGradeBySchoolIdYearAndName(Integer schoolId, String schoolYear, String gradeName);
	/**
	 * 使用学校id、学年和年级名称创建年级
	 * @param schoolId
	 * @param schoolYear
	 * @param gradeName
	 * @return
	 */
	Grade addGradeUseSchoolIdYearAndName(Integer schoolId, String schoolYear, String gradeName);


	List<Grade> findGradeOfSchoolYearAndTeacherId(Integer schoolId,String schoolYear,Integer teacherId);
	
}
