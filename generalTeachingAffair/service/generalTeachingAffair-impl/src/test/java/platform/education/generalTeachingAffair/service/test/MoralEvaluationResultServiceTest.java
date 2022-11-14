package platform.education.generalTeachingAffair.service.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import platform.education.generalTeachingAffair.service.MoralEvaluationResultService;
import platform.education.generalTeachingAffair.service.test.base.BaseTest;

import java.util.List;

public class MoralEvaluationResultServiceTest extends BaseTest{
	
	@Autowired
	@Qualifier("moralEvaluationResultService")
	private MoralEvaluationResultService moralEvaluationResultService;
	
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
