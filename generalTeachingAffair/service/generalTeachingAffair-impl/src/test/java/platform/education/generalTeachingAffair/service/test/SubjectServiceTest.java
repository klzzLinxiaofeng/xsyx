package platform.education.generalTeachingAffair.service.test;


import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import platform.education.generalTeachingAffair.model.Subject;
import platform.education.generalTeachingAffair.service.SubjectService;
import platform.education.generalTeachingAffair.service.test.base.BaseTest;

public class SubjectServiceTest extends BaseTest{
	
	@Autowired
	@Qualifier("subjectService")
	private SubjectService subjectService;
	
	@Test
	public void testAdd() {
		Subject subject = new Subject();
		subject.setCode("code");
		subject.setCreateDate(new Date());
//		subject.setFieldId(1);
//		subject.setIsDelete("Y");
		subject.setListOrder(1);
		subject.setModifyDate(new Date());
		subject.setName("name");
		subject.setSchoolId(1);
		subject.setStageCode("stageCode");
		subject.setSubjectCharacter("su");
		subject.setSubjectClass("s");
		subject.setSubjectProperty("su");
		subject.setSubjectType("1");
		subjectService.add(subject);
	}
	
	@Test
	public void testModify() {
		Subject subject = subjectService.findSubjectById(4);
		subject.setCode("code1");
		subject.setCreateDate(new Date());
//		subject.setFieldId(1);
//		subject.setIsDelete("Y1");
		subject.setListOrder(1);
		subject.setModifyDate(new Date());
		subject.setName("name");
		subject.setSchoolId(1);
		subject.setStageCode("stageCode1");
		subject.setSubjectCharacter("su1");
		subject.setSubjectClass("s1");
		subject.setSubjectProperty("su1");
		subject.setSubjectType("2");
		subjectService.modify(subject);
	}
	
	@Test
	public void testFindById() {
		Subject subject = subjectService.findSubjectById(3);
		subjectService.modify(subject);
	}
	
	@Test
	public void testRemove() {
		List<Subject> map=subjectService.findAllSubjectName();
    System.out.println(map.size());
	}
	
	@Test
	public void findBySchoolIdAndStageCodeAndCode(){
		Subject subject = subjectService.findUnique(10, "3", "18");
		System.out.println("subject.getName()="+subject.getName());
	}
	
}
