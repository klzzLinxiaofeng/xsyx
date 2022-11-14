package platform.education.generalTeachingAffair.service.test;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import platform.education.generalTeachingAffair.model.Parent;
import platform.education.generalTeachingAffair.service.ParentService;


import platform.education.generalTeachingAffair.service.test.base.BaseTest;
import platform.education.generalTeachingAffair.vo.ParentCondition;


public class ParentServiceTest extends BaseTest{
	
	@Autowired
	@Qualifier("parentService")
	private ParentService parentService;
	
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
		ParentCondition parentCondition = new ParentCondition();
		parentCondition.setParentMobileLike(false);
		parentCondition.setMobile("121211545");
		List<Parent> list = this.parentService.findParentByCondition(parentCondition);
		for (Parent parent : list) {
		System.out.println(parent.getId()+":"+parent.getName()+":"+parent.getMobile());	
		}
	}
	
	@Test
	public void testFindByparent() {
		ParentCondition parentCondition = new ParentCondition();
		parentCondition.setParentMobileLike(false);
		parentCondition.setMobile("121211545");
		List<Parent> list = this.parentService.findParentByCondition(parentCondition);
		for (Parent parent : list) {
		System.out.println(parent.getId()+":"+parent.getName()+":"+parent.getMobile());	
		}
	}
	
}
