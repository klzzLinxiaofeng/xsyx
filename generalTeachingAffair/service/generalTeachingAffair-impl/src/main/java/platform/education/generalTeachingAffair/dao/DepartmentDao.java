package platform.education.generalTeachingAffair.dao;

import framework.generic.dao.GenericDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.model.Department;
import platform.education.generalTeachingAffair.vo.DepartmentCondition;
import platform.education.generalTeachingAffair.vo.DepartmentVo;
import platform.education.generalTeachingAffair.vo.TeacherVo;

import java.util.Date;
import java.util.List;

public interface DepartmentDao extends GenericDao<Department, java.lang.Integer> {

	List<Department> findDepartmentByCondition(DepartmentCondition departmentCondition, Page page, Order order);
	
	Department findById(Integer id);
	
	DepartmentVo findById1(Integer id);
	
	List<Department> findDepartmentByParentId(Integer parentId, Page page, Order order);
	
	List<Department> findDepartmentBySchoolId(Integer schoolId, Page page, Order order);

    List<Department> findBySchoolId(Integer schoolId);
	
	/**
     * 根据Id使部门人数加1
     * */
   public int updateIncreaseDepatrmentNum(Integer id);

    /**
     * 根据Id使部门人数减1
     * */
   public int updateReduceDepatrmentNum(Integer id);
   
   void deleteById(Integer id);
   
   int deleteByParentId(Integer parentId);
   
   public List<Department> findChildrensByCascade(Integer parentId, Page page, Order order);

   List<Department> findDepartmentPeopleCountBySchoolId(Integer schoolId);

   //根据部门名称和学校ID获取部门  2016-1-27
   Department findDepartmentByNameAndSchoolId(String departmentName,Integer schoolId);
   /**
    * 根据学校id获取每个部门的男女教师人数
    * @param schoolId
    * @return
    */
   List<TeacherVo> findDepartmentPeopleCountBySchoolIdAndSex(Integer schoolId);

   List<Department> findIncrementByModifyDate(Integer schoolId, Boolean isDeleted, Date modifyDate, Integer departmentId, Boolean isGetNew);
   Department findDepartmentByteacherId(Integer schoolId,Integer teacherId);
}
