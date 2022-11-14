package platform.education.generalTeachingAffair.service.test;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import platform.education.generalTeachingAffair.model.Grade;
import platform.education.generalTeachingAffair.service.GradeService;
import platform.education.generalTeachingAffair.service.test.base.BaseTest;

public class GradeServiceTest extends BaseTest{
	
	@Autowired
	@Qualifier("gradeService")
	private GradeService gradeService;
	
	@Test
	public void testAdd() {
		Grade grade = new Grade();
		grade.setSchoolId(1);
		grade.setStageCode("code2");
		grade.setName("hello2");
		grade.setSchoolYear("11-09");
		grade.setGradeNumber(89);
		//grade.setLength(8);
		grade.setTeamCount(50);
		grade.setFinishDate(new Date());
		grade.setBeginDate(new Date());
		grade.setCreateDate(new Date());
		grade.setModifyDate(new Date());
		gradeService.add(grade);
	}
	
	@Test
	public void testModify() {
		Grade grade = gradeService.findGradeById(1);
		grade.setName("hello9");
		grade.setStageCode("code9");
		grade.setName("hello9");
		grade.setSchoolYear("12-09");
		grade.setGradeNumber(90);
		//grade.setLength(80);
		grade.setTeamCount(500);
		grade.setFinishDate(new Date());
		grade.setBeginDate(new Date());
		grade.setCreateDate(new Date());
		grade.setModifyDate(new Date());
		this.gradeService.modify(grade);
	}
	
	
	@Test
	public void testRemove() {
		Grade grade = new Grade();
		grade.setId(4);
		gradeService.remove(grade);
	}
	
	@Test
	public void testFindByCondition() {
	    		
	}
	
	@Test
	public void testFindGradeById() {
		Grade grade = this.gradeService.findGradeById(1);
		System.out.println(grade);
		grade = this.gradeService.findGradeById(1);
		System.out.println(grade);
	}
	
	@Test
	public void testCache() {
		Grade grade = this.gradeService.findGradeById(1);
		System.out.println(grade + "-------------");
		grade = this.gradeService.findGradeById(1);
		System.out.println(grade + "-------------");
		grade.setName("一年级");
		grade = this.gradeService.modify(grade);
		System.out.println(grade + "--------");
		grade = this.gradeService.findGradeById(1);
		System.out.println(grade);
	}
	
	@Test
	public void testCache1() {
		Grade grade = this.gradeService.findGradeById(1);
		System.out.println(grade + "-------------");
		grade = this.gradeService.findGradeById(1);
		System.out.println(grade + "-------------");
		grade = this.gradeService.findGradeById(2);
		System.out.println(grade + "-------------");
		grade = this.gradeService.findGradeById(2);
		System.out.println(grade + "-------------");
		grade = this.gradeService.findGradeById(1);
		System.out.println(grade + "-------------");
	}
	
	@Test
	public void testCache2() {
		Grade grade = this.gradeService.findGradeById(1);
		System.out.println(grade + "-------------");
		grade = this.gradeService.findGradeById(1);
		System.out.println(grade + "-------------");
		System.out.println(this.gradeService.remove(grade));
		grade = this.gradeService.findGradeById(1);
		System.out.println(grade + "-------------");
	}
	
}
