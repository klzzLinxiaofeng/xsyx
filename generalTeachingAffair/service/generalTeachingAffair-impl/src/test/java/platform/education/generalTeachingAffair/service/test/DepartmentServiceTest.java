package platform.education.generalTeachingAffair.service.test;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import platform.education.generalTeachingAffair.model.Department;
import platform.education.generalTeachingAffair.service.DepartmentService;
import platform.education.generalTeachingAffair.service.ExamStatService;
import platform.education.generalTeachingAffair.service.PjExamService;
import platform.education.generalTeachingAffair.service.test.base.BaseTest;
import platform.education.generalTeachingAffair.vo.DepartmentCondition;
import platform.education.generalTeachingAffair.vo.PjExamCondition;

public class DepartmentServiceTest extends BaseTest{
	
	@Autowired
	@Qualifier("departmentService")
	private DepartmentService departmentService;

	
	/*
	@Test
	public void test() throws IOException{
		Department department = new Department();
		department.setParentId(0);
		department.setSchoolId(1);
		department.setName("学生处");
		department.setListOrder(0);
		department.setMemberCount(90);
		department.setIsDelete(true);
		department.setCreateDate(new Date());
		departmentService.add(department);
	}
	
	@Test
	public void testModify() {
		Department dept = new Department();
		dept.setId(45);
		dept.setIsDelete(true);
		dept = departmentService.modify(dept);
		System.out.println(dept.getIsDelete());
	}
	
	@Test
	public void testFindById() {
		Department dept = this.departmentService.findDepartmentById(32);
		System.out.println(dept);
		Assert.assertNotNull(dept);
	}
	
	@Test
	public void testDepartmentByParentId() {
		List<Department> depts = this.departmentService.findDepartmentByParentId(0);
		Assert.assertEquals(new Integer(depts.size()), new Integer(1));
		System.out.println(depts);
	}
	
	@Test
	public void testRemove() {
		Department department = departmentService.findDepartmentById(1);
		departmentService.remove(department);
	}
	
	@Test
	public void testFindByCondition() {
//		Page page = new Page();
//		page.setCurrentPage(1);
		DepartmentCondition departmentCondition = new DepartmentCondition();
		departmentCondition.setSchoolId(2);
//		departmentCondition.setIsDelete(true);
		List<Department> ll = departmentService.findDepartmentByCondition(departmentCondition, null, null);
		System.out.println(ll.size()+"=====");
	}
	
	@Test
	public void testFindDepartmentBySchoolId() {
		List<Department> depts = this.departmentService.findDepartmentBySchoolId(2, null, null);
		System.out.println(depts);
		Assert.assertEquals(new Integer(depts.size()), new Integer(1));
	}
	
	
	*/
	
}
