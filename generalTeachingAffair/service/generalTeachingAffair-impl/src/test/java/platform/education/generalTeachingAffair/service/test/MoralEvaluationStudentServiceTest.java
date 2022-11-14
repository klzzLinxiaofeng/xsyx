package platform.education.generalTeachingAffair.service.test;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import platform.education.generalTeachingAffair.model.MoralEvaluationStudent;
import platform.education.generalTeachingAffair.service.MoralEvaluationStudentService;
import platform.education.generalTeachingAffair.service.test.base.BaseTest;
import platform.education.generalTeachingAffair.vo.MoralEvaluationStudentCondition;
import platform.education.generalTeachingAffair.vo.MoralEvaluationStudentVo;

public class MoralEvaluationStudentServiceTest extends BaseTest{
	
	@Autowired
	@Qualifier("moralEvaluationStudentService")
	private MoralEvaluationStudentService moralEvaluationStudentService;
	
	@Test
	public void testAdd() {
		MoralEvaluationStudent student = new MoralEvaluationStudent();
		student.setSchoolId(1);
		student.setTeamId(3);
		student.setStudentId(22);
		student.setTotalEvaluation("1");
		student.setRemark("很好");
		student.setIsDelete(false);
		student.setCreateDate(new Date());
		student.setModifyDate(new Date());
		moralEvaluationStudentService.add(student);
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
		MoralEvaluationStudentCondition condition = new MoralEvaluationStudentCondition();
		condition.setSchoolId(10);
		List<MoralEvaluationStudent> students = this.moralEvaluationStudentService.findMoralEvaluationStudentByCondition(condition);
		System.out.println("**********" + students.size());
	}
	
	@Test
	public void testFindByConditionMore() {
		MoralEvaluationStudentCondition condition = new MoralEvaluationStudentCondition();
		condition.setSchoolId(10);
		List<MoralEvaluationStudentVo> students = this.moralEvaluationStudentService.findMoralEvaluationStudentByConditionMore(condition,null,null);
		System.out.println("**********" + students.size());
	}
}
