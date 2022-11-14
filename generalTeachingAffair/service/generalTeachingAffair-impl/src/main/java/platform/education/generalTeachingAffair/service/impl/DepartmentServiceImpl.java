package platform.education.generalTeachingAffair.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.generalTeachingAffair.dao.DepartmentDao;
import platform.education.generalTeachingAffair.dao.DepartmentTeacherDao;
import platform.education.generalTeachingAffair.model.Department;
import platform.education.generalTeachingAffair.model.DepartmentTeacher;
import platform.education.generalTeachingAffair.service.DepartmentService;
import platform.education.generalTeachingAffair.service.DepartmentTeacherService;
import platform.education.generalTeachingAffair.vo.DepartmentCondition;
import platform.education.generalTeachingAffair.vo.DepartmentVo;
import platform.education.generalTeachingAffair.vo.TeacherVo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public class DepartmentServiceImpl implements DepartmentService {

	private Logger log = LoggerFactory.getLogger(getClass());

	private DepartmentDao departmentDao;
	
	private DepartmentTeacherDao departmentTeacherDao;

	public void setDepartmentDao(DepartmentDao departmentDao) {
		this.departmentDao = departmentDao;
	}
	
	public void setDepartmentTeacherDao(DepartmentTeacherDao departmentTeacherDao) {
		this.departmentTeacherDao = departmentTeacherDao;
	}
	
	private DepartmentTeacherService departmentTeacherService;
	
	public void setDepartmentTeacherService(
			DepartmentTeacherService departmentTeacherService) {
		this.departmentTeacherService = departmentTeacherService;
	}

	@Override
	public List<Department> findDepartmentBySchoolId(Integer schoolId, Page page, Order order) {
		return this.departmentDao.findDepartmentBySchoolId(schoolId, page, order);
	}

	@Override
	public List<Department> findBySchoolId(Integer schoolId){
		return this.departmentDao.findBySchoolId(schoolId);
	}
	@Override
	public List<Department> findDepartmentByParentId(Integer parentId) {
		return  this.departmentDao.findDepartmentByParentId(parentId, null, null);
	}

	@Override
	public Department findDepartmentById(Integer id) {
		try {
			return departmentDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			if(log.isInfoEnabled()) {
				log.info("数据库无存在ID为 ", id);
			}
		}
		return null;
	}
	
	@Override
	public DepartmentVo findDepartmentById1(Integer id) {
		try {
			return departmentDao.findById1(id);
		} catch (Exception e) {
			e.printStackTrace();
			if(log.isInfoEnabled()) {
				log.info("数据库无存在ID为 ", id);
			}
		}
		return null;
	}

	

	@Override
	public Department add(Department department) {
		if(department == null) {
			return null;
		}
		department.setCreateDate(new Date());
		return departmentDao.create(department);
	}

	@Override
	public Department modify(Department department) {
		if(department == null) {
			return null;
		}
		Integer deptId = department.getId();
		if(deptId == null) {
			return null;
		}
		Date modifyDate = new Date();
		String deptName = department.getName();
		if(deptName != null) {
			List<DepartmentTeacher> departmentTeachers = departmentTeacherDao.findByDeptId(deptId);
			for(DepartmentTeacher deptTeacher : departmentTeachers) {
				deptTeacher.setDepartmentName(deptName);
				deptTeacher.setModifyDate(modifyDate);
				deptTeacher = this.departmentTeacherDao.update(deptTeacher);
			}
		}
		department.setModifyDate(modifyDate);
		return departmentDao.update(department);
	}

	@Override
	public String remove(Department department) {
		if (department != null) {
			Integer id = department.getId();
			List<Department> depts = this.departmentDao.findDepartmentByParentId(id, null, null);
			if (depts.size() > 0) {
				if(log.isInfoEnabled()) {
					log.info("删除 -> " + id + " 失败, 存在子模块");
				}
				return EXIST_CHILDRES;
			}
			try {
				this.departmentDao.delete(department);
				return OPERATE_SUCCESS;
			} catch (Exception e) {
				if(log.isInfoEnabled()) {
					log.info("删除 -> " + id + " 失败, 异常信息为", e);
				}
				return OPERATE_ERROR;
			}
		}
		return OPERATE_FAIL;
	}

	@Override
	public List<Department> findDepartmentByCondition(DepartmentCondition departmentCondition, Page page, Order order) {
		return departmentDao.findDepartmentByCondition(departmentCondition, page, order);
	}

	@Override
	public int updateIncreaseDepatrmentNum(Integer id) {
		return departmentDao.updateIncreaseDepatrmentNum(id);
	}

	@Override
	public int updateReduceDepatrmentNum(Integer id) {
		return this.departmentDao.updateReduceDepatrmentNum(id);
	}

	@Override
	public List<Department> findByParentId(Integer parentId, Page page, Order order) {
		return this.departmentDao.findDepartmentByParentId(parentId, page, order);
	}

	@Override
	public List<Department> findByParentId(Integer parentId, Page page) {
		return this.departmentDao.findDepartmentByParentId(parentId, page, null);
	}

	@Override
	public List<Department> findByParentId(Integer parentId, Order order) {
		return this.departmentDao.findDepartmentByParentId(parentId, null, order);
	}

	@Override
	public List<Department> findChildrensByCascade(Integer parentId) {
		return this.departmentDao.findChildrensByCascade(parentId, null, null);
	}

	@Override
	public String deleteByRecursive(Department department) {
		if(department != null) {
			try {
				batchDelete(department);
				//2016-11-11  删除部门教师
				departmentTeacherService.batchDelete(department.getId());
				department.setIsDelete(true);
				department.setModifyDate(new Date());
				this.departmentDao.update(department);
				if(department != null) {
					return OPERATE_SUCCESS;
				}
			} catch (Exception e) {
				if(log.isInfoEnabled()) {
					log.info("删除 -> " + department.getId() + " 失败, 异常信息为", e);
				}
				return OPERATE_ERROR;
			}
		}
		return OPERATE_FAIL;
	}
	
	
	
	@Override
	public String abandon(Department department) {
		if (department != null) {
			Integer id = department.getId();
			if (id == null) {
				return OPERATE_FAIL;
			}
			List<Department> depts = this.departmentDao.findDepartmentByParentId(id, null, null);
			if (depts.size() > 0) {
				if(log.isInfoEnabled()) {
					log.info("删除 -> " + id + " 失败, 存在子模块");
				}
				return EXIST_CHILDRES;
			}
			try {
				department.setIsDelete(true);
				this.departmentDao.update(department);
				return OPERATE_SUCCESS;
			} catch (Exception e) {
				if(log.isInfoEnabled()) {
					log.info("删除 -> " + id + " 失败, 异常信息为", e);
				}
				return OPERATE_ERROR;
			}
		}
		return OPERATE_FAIL;
	}

	@Override
	public String recover(Department department) {
		if(department != null) {
			Integer id = department.getId();
			if(id == null) {
				return OPERATE_FAIL;
			}
			try {
				department.setIsDelete(false);
				this.departmentDao.update(department);
			} catch (Exception e) {
				if(log.isInfoEnabled()) {
					log.info("恢复 -> {} 失败,异常信息为 {}", id, e);
				}
				return OPERATE_ERROR;
			}
		}
		return OPERATE_FAIL;
	}

	private void batchDelete(Department department) {
		Integer id = department.getId();
		List<Department> depts = this.departmentDao.findDepartmentByParentId(id, null, null);
		Date date = new Date();
		for(Department dept : depts) {
			batchDelete(dept);
			//2016-11-11  删除部门教师
			departmentTeacherService.batchDelete(dept.getId());
			dept.setIsDelete(true);
			dept.setModifyDate(date);
			this.departmentDao.update(dept);
		}
	}

	@Override
	public List<Department> finDepartmentPeopleCountBySchoolId(Integer schoolId) {
		return this.departmentDao.findDepartmentPeopleCountBySchoolId(schoolId);
	}
	@Override
	public List<TeacherVo> findDepartmentPeopleCountBySchoolIdAndSex(Integer schoolId) {
		return this.departmentDao.findDepartmentPeopleCountBySchoolIdAndSex(schoolId);
	}

	@Override 
	public void modifyOrder(String ids) {
		String[] depIds = ids.split(",");
		Department dep = null;
		if(depIds.length > 0){
			for(int i = 0; i < depIds.length; i++){
				dep = new Department();
				dep.setId(Integer.valueOf(depIds[i]));
				dep.setListOrder(i+1);
				this.departmentDao.update(dep);
			}
		}
	}

	//根据部门名称和学校ID获取部门  2016-1-27
	@Override
	public Department findDepartmentByNameAndSchoolId(String departmentName,
			Integer schoolId) {
		return this.departmentDao.findDepartmentByNameAndSchoolId(departmentName,schoolId);
	}

	@Override
	public List<Department> findIncrementByModifyDate(Integer schoolId, Boolean isDeleted, Date modifyDate, Integer departmentId, Boolean isGetNew) {
		return this.departmentDao.findIncrementByModifyDate(schoolId, isDeleted, modifyDate, departmentId, isGetNew);
	}
}
