package platform.education.generalTeachingAffair.service.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import platform.education.generalTeachingAffair.model.DepartmentTeacher;
import platform.education.generalTeachingAffair.service.DepartmentTeacherService;
import platform.education.generalTeachingAffair.service.test.base.BaseTest;
import platform.education.generalTeachingAffair.vo.DepartmentTeacherVo;

import java.util.Date;
import java.util.List;

public class DepartmentTeacherServiceTest extends BaseTest{
	
	@Autowired
	@Qualifier("departmentTeacherService")
	private DepartmentTeacherService departmentTeacherService;
	
	@Test
	public void testAdd() {
		DepartmentTeacher dt = new DepartmentTeacher();
		dt.setDepartmentId(34);
		dt.setDepartmentName("学生处");
		dt.setSchoolId(2);
		dt.setTeacherId(2);
		dt.setTeacherName("周津");
		dt.setCreateDate(new Date());
		dt.setModifyDate(new Date());
		this.departmentTeacherService.add(dt);
	}
	
	/*@Test
	public void testAddBatch() {
		
		Integer[] teachIds = {1, 2, 3};
		
		this.departmentTeacherService.addBatch(33, teachIds, 2);
	}*/
	
	@Test
	public void testDepartmentTeacherVoByDepartmentId() {
		
		List<DepartmentTeacherVo> vo = this.departmentTeacherService.findDepartmentTeacherVoByDepartmentId(1);
		System.out.println(vo.size());
	}
	
	@Test
	public void countNoPhotoDepartmentId() {
		
		Long vo = this.departmentTeacherService.countNoPhotoDepartmentId(1);
		System.out.println(vo);
	}
	
	@Test
	public void findNoPhotoName() {
		
		String  vo = this.departmentTeacherService.findNoPhotoName(1);
		System.out.println(vo);
	}
}
