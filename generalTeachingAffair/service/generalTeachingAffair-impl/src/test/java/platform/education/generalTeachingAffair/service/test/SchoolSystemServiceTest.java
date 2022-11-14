package platform.education.generalTeachingAffair.service.test;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import platform.education.generalTeachingAffair.model.SchoolSystem;
import platform.education.generalTeachingAffair.service.SchoolSystemService;
import platform.education.generalTeachingAffair.service.test.base.BaseTest;

public class SchoolSystemServiceTest extends BaseTest{
	
	@Autowired
	@Qualifier("schoolSystemService")
	private SchoolSystemService schoolSystemService;
	
	@Test
	public void testAdd() {
		SchoolSystem ss = new SchoolSystem();
		ss.setSchoolId(10);
		ss.setStageCode("2");
		ss.setGradeCode("6");
		ss.setGradeName("六年级");
		ss = this.schoolSystemService.add(ss);
		System.out.println(ss);
		Assert.assertNotNull(ss);
//		ss.setDays(days);
//		ss.setDaysPlan(daysPlan);
//		ss.setLessonOfAfternoon(lessonOfAfternoon);
//		ss.setLessonOfEvening(lessonOfEvening);
//		ss.setLessonOfMorning(lessonOfMorning);
//		ss.setLessonTimes(lessonTimes);
		
	}
	
	@Test
	public void testFindUnique() {
		SchoolSystem ss = this.schoolSystemService.findUnique(10, "2", "1");
		System.out.println(ss);
		Assert.assertNotNull(ss);
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
