package platform.education.generalTeachingAffair.service.test;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import platform.education.generalTeachingAffair.model.SchoolYear;
import platform.education.generalTeachingAffair.service.SchoolYearService;
import platform.education.generalTeachingAffair.service.test.base.BaseTest;

public class SchoolYearServiceTest extends BaseTest{
	
	@Autowired
	@Qualifier("schoolYearService")
	private SchoolYearService schoolYearService;
	
	@Test
	public void testAdd() {
		SchoolYear schoolYear = new SchoolYear();
		schoolYear.setBeginDate(new Date());
		schoolYear.setCreateDate(new Date());
		schoolYear.setFinishDate(new Date());
		schoolYear.setModifyDate(new Date());
		schoolYear.setName("name");
		schoolYear.setSchoolId(1);
		schoolYear.setYear("2013");
		schoolYearService.add(schoolYear);
	}
	
	@Test
	public void testModify() {
		SchoolYear schoolYear = schoolYearService.findSchoolYearById(6);
		schoolYear.setBeginDate(new Date());
		schoolYear.setCreateDate(new Date());
		schoolYear.setFinishDate(new Date());
		schoolYear.setModifyDate(new Date());
		schoolYear.setName("name1");
		schoolYear.setSchoolId(1);
		schoolYear.setYear("2014");
		schoolYearService.modify(schoolYear);
	}
	
	@Test
	public void testFindById() {

	}
	
	@Test
	public void testRemove() {
		SchoolYear schoolYear = schoolYearService.findSchoolYearById(5);
		schoolYearService.remove(schoolYear);
	}
	
	@Test
	public void testFindByCondition() {

	}
	
}
