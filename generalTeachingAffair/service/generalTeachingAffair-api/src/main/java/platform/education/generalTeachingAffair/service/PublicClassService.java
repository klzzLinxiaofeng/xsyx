package platform.education.generalTeachingAffair.service;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.model.*;
import platform.education.generalTeachingAffair.vo.ClassSelectionCondition;
import platform.education.generalTeachingAffair.vo.CountPublicClassVo;
import platform.education.generalTeachingAffair.vo.PublicClassCondition;

import java.util.List;
import java.util.Map;

public interface PublicClassService {

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

	/* 开课管理开始   */
	PublicClass findPublicClassById(Integer id);

	PublicClass add(PublicClass publicClass);

	PublicClass modify(PublicClass publicClass);

	void remove(PublicClass publicClass);

	List<PublicClass> findPublicClassByCondition(PublicClassCondition publicClassCondition, Page page, Order order);
	List<PublicClass> findPublicClassByConditionLingxing(PublicClassCondition publicClassCondition, Page page, Order order,Integer leixing);
	List<PublicClass> findPublicClassByCondition(PublicClassCondition publicClassCondition);

	List<PublicClass> findPublicClassByCondition(PublicClassCondition publicClassCondition, Page page);

	List<PublicClass> findPublicClassByCondition(PublicClassCondition publicClassCondition, Order order);
	List<Grade>  findGradeBySchoolId(Integer schoolId);
//	Long count();

	Long count(PublicClassCondition publicClassCondition);

	/**
	 * 功能描述：逻辑上删除即数据库仍然保存记录，只是修改删除标识
	 * @param publicClass
	 * @return
	 */
	String abandon(PublicClass publicClass);

	/* 开课管理结束   */

	/* 选课管理开始   */

	ClassSelection findClassSelectionById(Integer id);

	ClassSelection add(ClassSelection classSelection);

	ClassSelection modify(ClassSelection classSelection);

	void remove(ClassSelection classSelection);

	List<ClassSelection> findClassSelectionByCondition(ClassSelectionCondition classSelectionCondition, Page page, Order order);

	List<ClassSelection> findClassSelectionByCondition(ClassSelectionCondition classSelectionCondition);

	List<ClassSelection> findClassSelectionByCondition(ClassSelectionCondition classSelectionCondition, Page page);

	List<ClassSelection> findClassSelectionByCondition(ClassSelectionCondition classSelectionCondition, Order order);

//	Long count();

	Long count(ClassSelectionCondition classSelectionCondition);

	/**
	 * 功能描述：逻辑上删除即数据库仍然保存记录，只是修改删除标识
	 * @param classSelection
	 * @return
	 */
	String abandon(ClassSelection classSelection);

	/**
	 * 功能描述：通过部分条件查询出某个学生是否已经存在报名记录 (schoolId publicClassId studentId teamId isDelete)
	 * @param classSelectionCondition
	 * @return
	 */
	ClassSelection findClassSelectionByPartCondition(ClassSelectionCondition classSelectionCondition);

	List<PublicTeacherVo> findPublicClassTeacherInfoByCondition(PublicTeacherVo condition, Page page, Order order);
//    List<PublicTeacherVo> findPublicClassTeacherInfo(PublicTeacherVo condition);

	void addTeacher(PublicTeacherVo publicTeacher);
	void modifyTeacher(PublicTeacherVo publicTeacher);

	PublicTeacherVo findByTeacherId(Integer id);

	String abandonTeacher(String ids);

	List<PublicTimeVo> findPublicClassTimeInfoByCondition(PublicTimeVo condition, Page page, Order order);

	Map addTime(PublicTimeVo publicTimeVo);

	void modifyTime(PublicTimeVo publicTimeVo);

	PublicTimeVo findByTimeId(Integer id);

	String abandonTime(String ids);

	List<PublicTeacherVo> findByClassId(Integer id, Integer schoolId);

	List<PublicTimeVo> findTimeInfoByClassId(Integer parseInt, Integer schoolId);

	List<Integer> findPublicUserByIdAndSchoolId(Integer id, Integer schoolId);

	void createPublicClassStu(PublicClass publicClass);

	void removePublicClassStu(PublicClass publicClass);

	List<PublicGradeRelatedVo> findGradeInfoByClassId(Integer id, Integer schoolId);

	List<CountPublicClassVo> findCountClass(CountPublicClassVo condition);

	List<CountPublicClassVo> findChoseClassStu(CountPublicClassVo condition);

	List<CountPublicClassVo> findNoChoseClassStu(CountPublicClassVo condition);

	/**
	 * @Description: 查找同教师的授课年级
	 * @Param: * @param publicClass
	 * @return: platform.education.generalTeachingAffair.model.PublicClass
	 * @Author: cmb
	 * @Date: 2020/10/21
	 */

	/* 选课管理结束   */

}
