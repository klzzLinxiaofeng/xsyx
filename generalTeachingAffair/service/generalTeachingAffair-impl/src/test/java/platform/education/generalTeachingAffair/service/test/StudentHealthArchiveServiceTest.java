package platform.education.generalTeachingAffair.service.test;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import platform.education.generalTeachingAffair.model.StudentHealthArchive;
import platform.education.generalTeachingAffair.service.StudentHealthArchiveService;
import platform.education.generalTeachingAffair.service.test.base.BaseTest;
import platform.education.generalTeachingAffair.vo.StudentHealthArchiveCondition;

public class StudentHealthArchiveServiceTest extends BaseTest{
	
	@Autowired
	@Qualifier("studentHealthArchiveService")
	private StudentHealthArchiveService studentHealthArchiveService;
	
	@Test
	public void testAdd() {
		
	}
	
	@Test
	public void testModify() {
		
	}
	
	@Test
	public void testFindById() {

	}
	
	@Test
	public void testRemove() {

	}
	
	@Test
	public void testFindByCondition() {
		StudentHealthArchiveCondition condition = new StudentHealthArchiveCondition();
		List<StudentHealthArchive> list = this.studentHealthArchiveService.findStudentHealthArchiveByCondition(condition);
		System.out.println(list);
	}
	
}
