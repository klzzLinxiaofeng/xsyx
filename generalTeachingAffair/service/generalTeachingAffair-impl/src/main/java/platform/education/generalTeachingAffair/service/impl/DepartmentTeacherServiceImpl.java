package platform.education.generalTeachingAffair.service.impl;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import platform.education.generalTeachingAffair.dao.DepartmentDao;
import platform.education.generalTeachingAffair.dao.DepartmentTeacherDao;
import platform.education.generalTeachingAffair.dao.TeacherDao;
import platform.education.generalTeachingAffair.model.Department;
import platform.education.generalTeachingAffair.model.DepartmentTeacher;
import platform.education.generalTeachingAffair.model.Teacher;
import platform.education.generalTeachingAffair.service.DepartmentTeacherService;
import platform.education.generalTeachingAffair.vo.DepartmentTeacherCondition;
import platform.education.generalTeachingAffair.vo.DepartmentTeacherVo;
import platform.education.generalTeachingAffair.vo.TeacherVo;

import java.util.Date;
import java.util.List;

/***
 * 部门教师
 * 
 * @author zhoujin
 *
 */
public class DepartmentTeacherServiceImpl implements DepartmentTeacherService {

	private Logger log = LoggerFactory.getLogger(getClass());

	private DepartmentTeacherDao departmentTeacherDao;

	private DepartmentDao departmentDao;

	private TeacherDao teacherDao;

	@Override
	public DepartmentTeacher findDepartmentTeacherById(Integer id) {
		DepartmentTeacher departmentTeacher = departmentTeacherDao.findById(id);
		return departmentTeacher;
	}

	
	//根据部门id得到对应 的教师列表集合
	public List<DepartmentTeacher>findByDepartmentId(Integer departmentId){
	    List<DepartmentTeacher>dtList = this.departmentTeacherDao.findByDepartmentId(departmentId);
	    return dtList;
	}
	
	
	
	@Override
	public DepartmentTeacher add(DepartmentTeacher departmentTeacher) {
		if (departmentTeacher != null) {
			Integer teachId = departmentTeacher.getTeacherId();
			Integer deptId = departmentTeacher.getDepartmentId();
			if (teachId != null && deptId != null) {
				DepartmentTeacher dt = this.departmentTeacherDao.findUnique(
						teachId, deptId);
				if (dt != null) {
					return dt;
				}
				Date date = new Date();
				departmentTeacher.setCreateDate(date);
				departmentTeacher.setModifyDate(date);
				return departmentTeacherDao.create(departmentTeacher);
			}
		}
		return null;
	}
	
	@Override
	public String addBatch(Integer deptId, String[] teachIds, Integer schoolId) {
		Department dept = departmentDao.findById(deptId);
		if(dept != null) {
			String departmentName = dept.getName();
			Date date = new Date();
			for(String teachId : teachIds) {
				DepartmentTeacher dt = this.departmentTeacherDao.findUnique(Integer.parseInt(teachId), deptId);
				if(dt != null) {
					continue;
				}
				Teacher teach = this.teacherDao.findById(Integer.parseInt(teachId));
				if(teach != null) {
					DepartmentTeacher departmentTeacher = new DepartmentTeacher();
					departmentTeacher.setDepartmentId(deptId);
					departmentTeacher.setDepartmentName(departmentName);
					departmentTeacher.setModifyDate(date);
					departmentTeacher.setCreateDate(date);
					departmentTeacher.setSchoolId(schoolId);
					departmentTeacher.setTeacherId(Integer.parseInt(teachId));
					departmentTeacher.setTeacherName(teach.getName());
					this.departmentTeacherDao.create(departmentTeacher);

					//2017-11-29	添加时修改teacher表的最后修改时间
					Teacher t = new Teacher();
					t.setId(Integer.parseInt(teachId));
					t.setModifyDate(new Date());
					teacherDao.update(t);
				}
			}
			return OPERATE_SUCCESS;
		}
		return OPERATE_FAIL;
	}

	@Override
	public DepartmentTeacher modify(DepartmentTeacher departmentTeacher) {
		return departmentTeacherDao.update(departmentTeacher);
	}

	@Override
	public void remove(DepartmentTeacher departmentTeacher) {
		try {
			departmentTeacherDao.delete(departmentTeacher);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("删除数据库无存在ID ");
		}

	}

	@Override
	public List<DepartmentTeacher> findDepartmentTeacherByCondition(
			DepartmentTeacherCondition departmentTeacherCondition, Page page,
			Order order) {
		return departmentTeacherDao.findDepartmentTeacherByCondition(
				departmentTeacherCondition, page, order);

	}

	@Override
	public List<DepartmentTeacher> findDepartmentTeacherByCondition1(
			DepartmentTeacherCondition departmentTeacherCondition, Page page,
			Order order) {
		return departmentTeacherDao.findDepartmentTeacherByCondition1(departmentTeacherCondition,page,order);

	}

	@Override
	public List<DepartmentTeacher> findDepartmentTeacherByConditionMore(
			DepartmentTeacherCondition departmentTeacherCondition) {
		return departmentTeacherDao.findDepartmentTeacherByConditionMore(
				departmentTeacherCondition);

	}
	
	public DepartmentTeacherDao getDepartmentTeacherDao() {
		return departmentTeacherDao;
	}

	public void setDepartmentTeacherDao(
			DepartmentTeacherDao departmentTeacherDao) {
		this.departmentTeacherDao = departmentTeacherDao;
	}

	public void setDepartmentDao(DepartmentDao departmentDao) {
		this.departmentDao = departmentDao;
	}

	public void setTeacherDao(TeacherDao teacherDao) {
		this.teacherDao = teacherDao;
	}

	@Override
	public String remove(Integer teachId, Integer deptId) {
		DepartmentTeacher dt = this.departmentTeacherDao.findUnique(teachId, deptId);
		if(dt != null) {
			try {
				this.departmentTeacherDao.delete(dt);
				return DepartmentTeacherService.OPERATE_SUCCESS;
			} catch (Exception e) {
				if(log.isInfoEnabled()) {
					log.info("根据 教师ID ：{} 部门ID {} 删除失败 异常信息为 {}", teachId, deptId, e);
				}
				return DepartmentTeacherService.OPERATE_ERROR;
			}
		}
		return DepartmentTeacherService.OPERATE_FAIL;
	}

	@Override
	public String abandon(Integer teachId, Integer deptId) {
		DepartmentTeacher dt = this.departmentTeacherDao.findUnique(teachId, deptId);
		if(dt != null) {
			try {
				dt.setIsDeleted(true);
				dt.setModifyDate(new Date());
				this.departmentTeacherDao.update(dt);
				//2017-11-29	移出时修改teacher主表的最后修改时间
				Teacher teacher = new Teacher();
				teacher.setId(teachId);
				teacher.setModifyDate(new Date());
				teacherDao.update(teacher);

				return DepartmentTeacherService.OPERATE_SUCCESS;
			} catch (Exception e) {
				if(log.isInfoEnabled()) {
					log.info("根据 教师ID ：{} 部门ID {} 删除失败 异常信息为 {}", teachId, deptId, e);
				}
				return DepartmentTeacherService.OPERATE_ERROR;
			}
		}
		return DepartmentTeacherService.OPERATE_FAIL;
	}

	@Override
	public String recover(Integer teachId, Integer deptId) {
		DepartmentTeacher dt = this.departmentTeacherDao.findUnique(teachId, deptId);
		if(dt != null) {
			try {
				dt.setIsDeleted(false);
				dt.setModifyDate(new Date());
				this.departmentTeacherDao.update(dt);
				return DepartmentTeacherService.OPERATE_SUCCESS;
			} catch (Exception e) {
				if(log.isInfoEnabled()) {
					log.info("根据 教师ID ：{} 部门ID {} 删除失败 异常信息为 {}", teachId, deptId, e);
				}
				return DepartmentTeacherService.OPERATE_ERROR;
			}
		}
		return DepartmentTeacherService.OPERATE_FAIL;
	}


	@Override
	public void updateSort(String[] teachIds, Integer deptId,Integer schoolId) {
		try{
			if(deptId != null && teachIds != null){
				DepartmentTeacherCondition dtc = null;
				DepartmentTeacher depTea = null;
				for(int i = 0; i<teachIds.length; i++){
					dtc = new DepartmentTeacherCondition();
					dtc.setDepartmentId(deptId);
					dtc.setSchoolId(schoolId);
					dtc.setTeacherId(Integer.valueOf(teachIds[i]));
					List<DepartmentTeacher> list = departmentTeacherDao.findDepartmentTeacherByCondition(dtc, null, null);
					if(list.size() > 0){
						depTea = new DepartmentTeacher();
						depTea.setId(list.get(0).getId());
						depTea.setOrderNumber(i+1);
						departmentTeacherDao.update(depTea);
					}
				}
			}else{
				return;
			}
		}catch(Exception e){
			if(log.isInfoEnabled()) {
				log.info("修改部门教师序号出错");
			}
		}
	}


//	根据教师ID和学校ID查找教师所在的所有部门  2016-1-27
	@Override
	public List<DepartmentTeacher> findDepartmentTeacherByTeacherIdAndSchoolId(
			Integer teacherId, Integer schoolId) {
		return departmentTeacherDao.findDepartmentTeacherByTeacherIdAndSchoolId(teacherId,schoolId);
	}


	@Override
	public List<DepartmentTeacherVo> findDepartmentTeacherVoByDepartmentId(Integer departmentId) {
		return this.departmentTeacherDao.findDepartmentTeacherVoByDepartmentId(departmentId);
	}


	@Override
	public Long countNoPhotoDepartmentId(Integer departmentId) {
		return this.departmentTeacherDao.countNoPhotoDepartmentId(departmentId);
	}


	@Override
	public Long countByDepartmentId(Integer departmentId) {
		return this.departmentTeacherDao.countByDepartmentId(departmentId);
	}


	@Override
	public String findNoPhotoName(Integer departmentId) {
		return this.departmentTeacherDao.findNoPhotoName(departmentId);
	}


	@Override
	public String findHasPhotoName(Integer departmentId) {
		return this.departmentTeacherDao.findHasPhotoName(departmentId);
	}


	@Override
	public void batchDelete(Integer departmentId) {
		List<DepartmentTeacher> list = this.departmentTeacherDao.findByDepartmentId(departmentId);
		for(DepartmentTeacher depTeacher : list){
			depTeacher.setIsDeleted(true);
			depTeacher.setModifyDate(new Date());
			this.departmentTeacherDao.update(depTeacher);
		}
	}

	@Override
	public List<DepartmentTeacher> findIncrementByTeacherId(Integer schoolId, Integer teacherId, Date modifyDate, Boolean isDeleted) {
		return departmentTeacherDao.findIncrementByTeacherId(schoolId, teacherId, modifyDate, isDeleted);
	}

	@Override
	public List<TeacherVo> findTeacherInfoByDepartmentId(Integer departmentId) {
		return departmentTeacherDao.findTeacherInfoByDepartmentId(departmentId);
	}


	@Override
	public void removeByTeacherId(Integer teacherId) {
		List<DepartmentTeacher> result = departmentTeacherDao.findByTeacherId(teacherId);
		Date now = new Date();
		for (DepartmentTeacher departmentTeacher : result) {
			departmentTeacher.setIsDeleted(true);
			departmentTeacher.setModifyDate(now);
			departmentTeacherDao.update(departmentTeacher);
		}
	}

	@Override
	public List<DepartmentTeacherVo> findDepartmentTeacherBySchool(Integer schoolId) {
		return this.departmentTeacherDao.findDepartmentTeacherBySchool(schoolId);
	}
}
	