package platform.education.generalTeachingAffair.service.test;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import platform.education.generalTeachingAffair.model.SchoolTermCurrent;
import platform.education.generalTeachingAffair.service.SchoolTermCurrentService;
import platform.education.generalTeachingAffair.service.test.base.BaseTest;

public class SchoolTermCurrentServiceTest extends BaseTest{
	
	@Autowired
	@Qualifier("schoolTermCurrentService")
	private SchoolTermCurrentService schoolTermCurrentService;
	
	@Test
	public void testAdd() {
		SchoolTermCurrent schoolTermCurrent = new SchoolTermCurrent();
		schoolTermCurrent.setBeginDate(new Date());
		schoolTermCurrent.setCreateDate(new Date());
		schoolTermCurrent.setFinishDate(new Date());
		schoolTermCurrent.setModifyDate(new Date());
		schoolTermCurrent.setSchoolId(2);
		schoolTermCurrent.setSchoolYear("09-11");
		schoolTermCurrent.setSchoolYearName("schoolYearName3");
		schoolTermCurrent.setSchoolYearId(1);
		
		schoolTermCurrentService.add(schoolTermCurrent);
	}
	
	@Test
	public void testModify() {
		SchoolTermCurrent schoolTermCurrent = schoolTermCurrentService.findSchoolTermCurrentById(5);
		schoolTermCurrent.setBeginDate(new Date());
		schoolTermCurrent.setCreateDate(new Date());
		schoolTermCurrent.setFinishDate(new Date());
		schoolTermCurrent.setModifyDate(new Date());
		schoolTermCurrent.setSchoolId(2);
		schoolTermCurrent.setSchoolYear("09-11");
		schoolTermCurrent.setSchoolYearName("schoolYearName88");
		schoolTermCurrent.setSchoolYearId(1);
		
		schoolTermCurrentService.modify(schoolTermCurrent);
	}
	
	@Test
	public void testFindById() {

	}
	
	@Test
	public void testRemove() {
		SchoolTermCurrent schoolTermCurrent = schoolTermCurrentService.findSchoolTermCurrentById(4);
		schoolTermCurrentService.remove(schoolTermCurrent);
	}
	
	@Test
	public void testFindByCondition() {
		
	}
	
	@Test
	public void testGetCurrentSchoolYear(){
		List<SchoolTermCurrent> ff = schoolTermCurrentService.findCurrentSchoolYear(2);
		System.out.println("============"+ff.size());
	}
	
	
}
