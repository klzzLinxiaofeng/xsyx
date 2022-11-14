package platform.education.generalTeachingAffair.service.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import platform.education.generalTeachingAffair.model.ParentStudent;
import platform.education.generalTeachingAffair.service.ParentService;
import platform.education.generalTeachingAffair.service.ParentStudentService;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:generalTeachingAffair-test.xml")
public class ParentStudentServiceTest{
	
	@Autowired
	@Qualifier("parentStudentService")
	private ParentStudentService parentStudentService;
	@Autowired
	@Qualifier("parentService")
	private ParentService parentService;
	
	@Test
	public void findParentStudentByParentUserId() {
		List<ParentStudent> list = parentStudentService.findParentStudentByParentUserId(94);
		for (ParentStudent parentStudent : list) {
			System.out.println(parentStudent.getId());
		};
	}
	
	
	@Test
	public void removeByParentUserId() {
//		parentService.removeAllByUserId(94, 1);
	}
	
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

	}
	
}
