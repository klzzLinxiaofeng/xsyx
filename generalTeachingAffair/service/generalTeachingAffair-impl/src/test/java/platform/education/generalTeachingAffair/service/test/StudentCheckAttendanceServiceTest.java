package platform.education.generalTeachingAffair.service.test;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import platform.education.generalTeachingAffair.service.test.base.BaseTest;
import platform.education.generalTeachingAffair.model.StudentCheckAttendance;
import platform.education.generalTeachingAffair.service.StudentCheckAttendanceService;
import platform.education.generalTeachingAffair.vo.StudentCheckAttendanceCondition;
import platform.education.generalTeachingAffair.vo.StudentCheckAttendanceVo;

public class StudentCheckAttendanceServiceTest extends BaseTest {
	@Autowired
	@Qualifier("studentCheckAttendanceService")
	private StudentCheckAttendanceService studentCheckAttendanceService;
	
	@Test
	public void testAdd() {
		StudentCheckAttendance studentCheckAttendance = new StudentCheckAttendance();
		studentCheckAttendance.setSchoolId(10);
		studentCheckAttendance.setSchoolYearId(203);
		studentCheckAttendance.setGradeId(60);
		studentCheckAttendance.setTeamNumber(69);
		studentCheckAttendance.setStudentId(261);
		studentCheckAttendance.setStudentName("gary1");
		studentCheckAttendance.setAttendanceType("1");
		studentCheckAttendance.setIsDeleted(0);
		studentCheckAttendance.setBeginDate(new Date());
		studentCheckAttendance.setEndDate(new Date());
		studentCheckAttendance.setModifyDate(new Date());
		
		studentCheckAttendanceService.add(studentCheckAttendance);
	}
	
	@Test
	public void testModify() {
		
	}
	
	@Test
	public void testFindById() {
		StudentCheckAttendance sca = this.studentCheckAttendanceService.findStudentCheckAttendanceById(6);
		System.out.println("sca.getStudentName()===="+sca.getStudentName());
	}
	
	@Test
	public void testFindByStudentId() {
		
	}
	
	@Test
	public void testRemove() {
	}
	
	@Test
	public void testFindByCondition() {
		
	}
	
	@Test
	public void testGetCuurentSchoolYear(){
	}
	
	@Test
	public void testFindGroupByStudentId(){
		StudentCheckAttendanceCondition condition = new StudentCheckAttendanceCondition();
		condition.setSchoolId(10);
		List<StudentCheckAttendanceVo> studentCheckAttendance =  this.studentCheckAttendanceService.findGroupVoByStudentId(condition, null, null);
//		System.out.println("size1======"+studentCheckAttendance.get(0).getTeamName());
		System.out.println("studentCheckAttendance.size()==="+studentCheckAttendance.size());
	}
	
	@Test
	public void testFindUnique() {
		StudentCheckAttendanceCondition condition = new StudentCheckAttendanceCondition();
		List<StudentCheckAttendance> sca = this.studentCheckAttendanceService.findUnique(10, 261, "0");
		System.out.println("sca.size()====="+sca.size());
	}

	
}
