package platform.education.generalTeachingAffair.service.impl;

import java.util.ArrayList;
import java.util.List;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.dao.SchoolInitDao;
import platform.education.generalTeachingAffair.model.ParentStudentTmp;
import platform.education.generalTeachingAffair.model.StudentTmp;
import platform.education.generalTeachingAffair.model.SubjectTeacherTmp;
import platform.education.generalTeachingAffair.model.TeacherTmp;
import platform.education.generalTeachingAffair.service.SchoolInitService;
import platform.education.generalTeachingAffair.vo.SubjectTeacherTmpCondition;

public class SchoolInitServiceImpl implements SchoolInitService{

	private SchoolInitDao schoolInitDao;
	
	public void setSchoolInitDao(SchoolInitDao schoolInitDao) {
		this.schoolInitDao = schoolInitDao;
	}
	
	@Override
	public void addTeacherTmpBatch(Object[] array) {
		if(array.length==0) {
			return;
		}
		schoolInitDao.createTeacherTmpBatch(array);
	}

	@Override
	public void removeTeacherTmpByCode(String code) {
		if(code!=null && !"".equals(code)) {
			schoolInitDao.deleteTeacherTmpByCode(code);
		}
		
	}

	@Override
	public List<TeacherTmp> findTeacherTmpByCodeAndStatus(String code, Integer status, Page page, Order order) {
		if(code==null && "".equals(code)) {
			return new ArrayList<TeacherTmp>();
		}
		return schoolInitDao.findTeacherTmpByCodeAndStatus(code, status, page, order);
	}

	@Override
	public void deleteTeacherTmpByTeacherId(Integer teacherId) {
		if(teacherId==null) {
			return;
		}
		schoolInitDao.deleteTeacherTmpByTeacherId(teacherId);
	}

	@Override
	public TeacherTmp findTeacherTmpById(Integer id) {
		if(id==null) {
			return null;
		}
		return schoolInitDao.findTeacherTmpById(id);
	}

	@Override
	public Long countTeacherTmpByCodeAndStatus(String code, Integer status) {
		if(code==null && "".equals(code)) {
			return 0L;
		}
		return schoolInitDao.countTeacherTmpByCodeAndStatus(code, status);
	}

	@Override
	public void modifyTeacherTmp(TeacherTmp tmp, int i) {
		schoolInitDao.updateTeacherTmp(tmp, i);
	}

	@Override
	public void deleteTeacherTmpById(Integer id) {
		schoolInitDao.deleteTeacherTmpById(id);
	}

	@Override
	public void addStudentTmpBatch(Object[] array) {
		if(array.length==0) {
			return;
		}
		schoolInitDao.createStudentTmpBatch(array);
	}

	@Override
	public void removeStudentTmpByCode(String code) {
		if(code!=null && !"".equals(code)) {
			schoolInitDao.deleteStudentTmpByCode(code);
		}
	}

	@Override
	public List<StudentTmp> findStudentTmpByCodeAndStatus(String code, Integer status, Page page, Order order) {
		if(code==null && "".equals(code)) {
			return new ArrayList<StudentTmp>();
		}
		return schoolInitDao.findStudentTmpByCodeAndStatus(code, status, page, order);
	}

	@Override
	public void deleteStudentTmpByStudentId(Integer studentId) {
		if(studentId==null) {
			return;
		}
		schoolInitDao.deleteStudentTmpByStudentId(studentId);
	}

	@Override
	public StudentTmp findStudentTmpById(Integer id) {
		if(id==null) {
			return null;
		}
		return schoolInitDao.findStudentTmpById(id);
	}

	@Override
	public Long countStudentTmpByCodeAndStatus(String code, Integer status) {
		return schoolInitDao.countStudentTmpByCodeAndStatus(code, status);
	}

	@Override
	public void modifyStudentTmp(StudentTmp tmp, int i) {
		schoolInitDao.updateStudentTmp(tmp, i);
	}

	@Override
	public void deleteStudentTmpById(Integer id) {
		schoolInitDao.deleteStudentTmpById(id);
	}

	@Override
	public void addSubjectTeacherTmpBatch(Object[] array) {
		if(array.length==0) {
			return;
		}
		schoolInitDao.createSubjectTeacherTmpBatch(array);
		
	}

	@Override
	public void removeSubjectTeacherTmpByCode(String code) {
		if(code!=null && !"".equals(code)) {
			schoolInitDao.deleteSubjectTeacherTmpByCode(code);
		}
	}

	@Override
	public List<SubjectTeacherTmp> findSubjectTeacherByCodeAndStatus(String code, Integer status, Page page,
			Order order) {
		if(code==null && "".equals(code)) {
			return new ArrayList<SubjectTeacherTmp>();
		}
		return schoolInitDao.findSubjectTeacherTmpByCodeAndStatus(code, status, page, order);
	}

	@Override
	public void deleteSubjectTeacherTmpBySubjectTeacherId(Integer subjectTeacherId) {
		if(subjectTeacherId==null) {
			return;
		}
		schoolInitDao.deleteSubjectTeacherTmpBySubjectTeacherId(subjectTeacherId);
		
	}

	@Override
	public SubjectTeacherTmp findSubjectTeacherTmpById(Integer id) {
		if(id==null) {
			return null;
		}
		return schoolInitDao.findSubjectTeacherTmpById(id);
	}

	@Override
	public Long countSubjectTeacherTmpByCodeAndStatus(String code, Integer status) {
		if(code==null && "".equals(code)) {
			return 0L;
		}
		return schoolInitDao.countSubjectTeacherTmpByCodeAndStatus(code, status);
	}

	@Override
	public void modifySubjectTeacherTmp(SubjectTeacherTmp tmp, int i) {
		schoolInitDao.updateSubjectTeacherTmp(tmp, i);
	}

	@Override
	public void deleteSubjectTeacherTmpById(Integer id) {
		schoolInitDao.deleteSubjectTeacherTmpById(id);
	}

	@Override
	public List<SubjectTeacherTmp> findSubjectTeacherTmpByCondition(
			SubjectTeacherTmpCondition subjectTeacherTmpCondition, Page page, Order order) {
		return schoolInitDao.findSubjectTeacherTmpByCondition(subjectTeacherTmpCondition, page, order);
	}

	@Override
	public void addParentStudentTmpBatch(Object[] array) {
		if (array.length == 0) {
			return;
		}
		schoolInitDao.createParentStudentTmpBatch(array);
	}

	@Override
	public void removeParentStudentTmpByCode(String code) {
		if (code != null && !"".equals(code)) {
			schoolInitDao.deleteParentStudentTmpByCode(code);
		}
	}

	@Override
	public List<ParentStudentTmp> findParentStudentTmpByCodeAndStatus(String code, Integer status, Page page, Order order) {
		if (code != null && !"".equals(code)) {
			return schoolInitDao.findParentStudentTmpByCodeAndStatus(code, status, page, order);
		} else {
			return null;
		}
	}

	@Override
	public void deleteParentStudentTmpByStudentId(Integer studentId) {
		if (studentId != null) {
			schoolInitDao.deleteParentStudentTmpByStudentId(studentId);
		}
	}

	@Override
	public ParentStudentTmp findParentStudentTmpById(Integer id) {
		if (id != null) {
			return schoolInitDao.findParentStudentTmpById(id);
		}
		return null;
	}

	@Override
	public Long countParentStudentTmpByCodeAndStatus(String code, Integer status) {
		return schoolInitDao.countParentStudentTmpByCodeAndStatus(code, status);
	}

	@Override
	public void modifyParentStudentTmp(ParentStudentTmp tmp, int i) {
		schoolInitDao.updateParentStudentTmp(tmp, i);
	}

	@Override
	public void deleteParentStudentTmpById(Integer id) {
		schoolInitDao.deleteParentStudentTmpById(id);
	}
}
