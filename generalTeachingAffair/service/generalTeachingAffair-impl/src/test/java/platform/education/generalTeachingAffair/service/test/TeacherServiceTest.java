package platform.education.generalTeachingAffair.service.test;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import platform.education.generalTeachingAffair.model.Teacher;
import platform.education.generalTeachingAffair.service.TeacherService;
import platform.education.generalTeachingAffair.service.test.base.BaseTest;
import platform.education.generalTeachingAffair.vo.TeacherArchiveVolist;
import platform.education.generalTeachingAffair.vo.TeacherCondition;
import platform.education.generalTeachingAffair.vo.TeacherVo;

public class TeacherServiceTest extends BaseTest{
	
	@Autowired
	@Qualifier("teacherService")
	private TeacherService teacherService;
	
	@Test
	public void testAdd() {
		Teacher teacher = new Teacher();
		teacher.setCreateDate(new Date());
		//teacher.setDepartmentId("1");
		//teacher.setDepartmentName("dddd");
		teacher.setEnrollDate(new Date());
		teacher.setIsDelete(true);
		teacher.setJobNumber("1111");
		
		teacher.setJobState("1");
		teacher.setLeaveDate(new Date());
		teacher.setMobile("13380085022");
		teacher.setModifyDate(new Date());
		teacher.setName("name");
		teacher.setPersionId(1);
		teacher.setPosition("position");
		teacher.setPostStaffing("pp");
		teacher.setSchoolId(1);
		teacher.setSex("1");
		teacher.setTelephone("13380085022");
		teacher.setUserId(1);
		teacher.setUserName("test");
		teacherService.add(teacher);
	}
	
	@Test
	public void testModify() {
		Teacher teacher = teacherService.findTeacherById(7);
		teacher.setName("name11");
		teacher.setPersionId(1);
		teacher.setPosition("position1");
		teacher.setPostStaffing("p1");
		teacher.setSchoolId(11);
		teacher.setSex("11");
		teacher.setTelephone("13380085022");
		teacher.setUserId(1);
		teacher.setUserName("test11");
		teacherService.modify(teacher);
	}
	
	@Test
	public void testFindById() {

	}
	
	@Test
	public void testRemove() {
		Teacher teacher = teacherService.findTeacherById(6);
		teacherService.remove(teacher);
	}
	
	@Test
	public void testFindByCondition() {

	}
	
	@Test
	public void testFindTeacherVoByCondition() {
		TeacherCondition condition = new TeacherCondition();
//		condition.setNameLike(false);
//		condition.setName("教");
//		/148394  教师
//		condition.setUserNameLike(false);
//		condition.setUserName("148394");
//		condition.setExcludeSelf(true);
		condition.setId(261);
		List<TeacherVo> vos = this.teacherService.findTeacherVoByCondition(condition, null, null);
		for (TeacherVo teacherVo : vos) {
			System.out.println(teacherVo.getUserId()+":"+teacherVo.getUserName()+":"+teacherVo.getName());
		}
		
	}
	
	@Test
	public void ttt(){
		TeacherArchiveVolist list = teacherService.getTeacherArchiveMess(112);
	}
}
