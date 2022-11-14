package platform.education.generalTeachingAffair.service.test;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import platform.education.generalTeachingAffair.model.SchoolTerm;
import platform.education.generalTeachingAffair.service.SchoolTermService;
import platform.education.generalTeachingAffair.service.test.base.BaseTest;

public class SchoolTermServiceTest extends BaseTest{
	
	@Autowired
	@Qualifier("schoolTermService")
	private SchoolTermService schoolTermService;
	
	@Test
	public void testAdd() {
		SchoolTerm schoolTerm = new SchoolTerm();
		schoolTerm.setBeginDate(new Date());
		schoolTerm.setCode("code");
		schoolTerm.setCreateDate(new Date());
		schoolTerm.setFinishDate(new Date());
		schoolTerm.setModifyDate(new Date());
		schoolTerm.setName("name");
		
		schoolTerm.setSchoolYear("2013");
		schoolTermService.add(schoolTerm);
	}
	
	@Test
	public void testModify() {
		SchoolTerm schoolTerm = schoolTermService.findSchoolTermById(5);
		schoolTerm.setCode("code");
		schoolTerm.setCreateDate(new Date());
		schoolTerm.setFinishDate(new Date());
		schoolTerm.setModifyDate(new Date());
		schoolTerm.setName("name1");
		
		schoolTerm.setSchoolYear("2014");
		schoolTermService.modify(schoolTerm);
	}
	
	@Test
	public void testFindById() {

	}
	
	@Test
	public void testRemove() {
		SchoolTerm schoolTerm = schoolTermService.findSchoolTermById(6);
		schoolTermService.remove(schoolTerm);
	}
	
	@Test
	public void testFindByCondition() {
		List<SchoolTerm> ll = schoolTermService.getSchoolTermOfSchool(1);
		System.out.println(ll.size()+"====");
	}
	
	@Test
	public void testGetSchoolTermOfYear(){
		List<SchoolTerm> ll = schoolTermService.getSchoolTermOfYear(1, 2013);
		System.out.println(ll.size()+"====");
	}
	
}
