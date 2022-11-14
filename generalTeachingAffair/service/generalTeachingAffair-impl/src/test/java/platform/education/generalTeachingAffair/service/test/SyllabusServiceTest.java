package platform.education.generalTeachingAffair.service.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.model.Syllabus;
import platform.education.generalTeachingAffair.model.SyllabusLesson;
import platform.education.generalTeachingAffair.service.SyllabusService;
import platform.education.generalTeachingAffair.service.test.base.BaseTest;
import platform.education.generalTeachingAffair.vo.SyllabusCondition;
import platform.education.generalTeachingAffair.vo.SyllabusVo;
import platform.education.generalTeachingAffair.vo.SyllabusVoCondition;

public class SyllabusServiceTest extends BaseTest{
	
	@Autowired
	@Qualifier("syllabusService")
	private SyllabusService syllabusService;
	
	@Test
	public void testAdd() {
		Syllabus syllabus = new Syllabus();
		syllabus.setDays(5);
		syllabus.setDaysPlan("0,1,2,3,4");
		syllabus.setLessonOfMorning(3);
		syllabus.setLessonOfAfternoon(4);
		syllabus.setLessonOfEvening(0);
		syllabus.setLessonTimes("{'lession' : '1'}");
		syllabus.setSchoolId(1);
		syllabus.setTeamId(2);
		syllabus.setTermCode("021");
		syllabus.setSchoolYear("2013");
		syllabus = this.syllabusService.add(syllabus);
		System.out.println(syllabus);
		Assert.assertNotNull(syllabus);
	}
	
	@Test
	public void testModify() {
		Syllabus sb = new Syllabus();
		sb.setId(1);
		sb.setDaysPlan("0,1,2,3,4");
		sb = this.syllabusService.modify(sb);
		Assert.assertEquals("0,1,2,3,4", sb.getDaysPlan());
	}
	
	@Test
	public void testFindById() {
		Syllabus sb = this.syllabusService.findSyllabusById(1);
		System.out.println(sb);
		Assert.assertNotNull(sb);
	}
	
	@Test
	public void testRemove() {
		Syllabus sb = new Syllabus();
		sb.setId(3);
		this.syllabusService.remove(sb);
		sb = this.syllabusService.findSyllabusById(2);
		Assert.assertNull(sb);
	}
	
	@Test
	public void testFindByCondition() {
		SyllabusCondition condition = new SyllabusCondition();
		Page page = new Page();
		condition.setSchoolId(1);
		page.setPageSize(1);
		List<Syllabus> syllabuses = this.syllabusService.findSyllabusByCondition(condition, page, null);
		Assert.assertEquals(new Integer(1), new Integer(syllabuses.size()));
		System.out.println(syllabuses);
	}
	
	@Test
	public void testCount() {
		Long count = this.syllabusService.count();
		System.out.println(count);
		Assert.assertEquals(new Long(2), count);
	}
	
	@Test
	public void testGetTeamSyllabus() {
		Syllabus syllabus = this.syllabusService.getTeamSyllabus(2, "021");
		System.out.println(syllabus);
		Assert.assertNotNull(syllabus);
	}
	
	@Test
	public void addSyllabusLesson() {
		SyllabusLesson lesson = new SyllabusLesson();
		lesson.setSyllabusId(2);
		lesson.setDayOfWeek("1");
		lesson.setLesson(3);
		lesson.setSubjectCode("001");
		lesson.setTeacherId(2);
		this.syllabusService.addSyllabusLesson(lesson);
	}
	
	@Test
	public void testGetSyllabusLessonBySyllabusId() {
		List<SyllabusLesson> lessons = this.syllabusService.getSyllabusLessonBySyllabusId(2);
		System.out.println(lessons);
		Assert.assertEquals(new Integer(1), new Integer(lessons.size()));
	}
	
	@Test
	public void testGetTeacherSyllabus() {
		List<SyllabusLesson> lessons = this.syllabusService.getTeacherSyllabus(2, "021");
		System.out.println(lessons);
		Assert.assertEquals(new Integer(2), new Integer(lessons.size()));
	}
	
	@Test
	public void testFindSyllabusVoByCondition() {
		SyllabusVoCondition condition = new SyllabusVoCondition();
		condition.setSchoolId(10);
		condition.setTermCode("10-2015-2");
		condition.setSchoolYear("2015");
		condition.setTeamId(76);
		condition.setSyllabusId(13);
		condition.setLesson(1);
		condition.setDayOfWeek("2");
		condition.setTeacherId(2);
		List<SyllabusVo> vos = this.syllabusService.findSyllabusVoByCondition(condition);
		System.out.println(vos);
		Assert.assertEquals(new Long(1), new Long(vos.size()));
	}
	
	@Test
	public void findSyllabusOfCount() {
		SyllabusVoCondition condition = new SyllabusVoCondition();
		condition.setSchoolId(10);
		condition.setSchoolYear("2015");
		condition.setSyllabusId(13);
		condition.setLesson(1);
		condition.setDayOfWeek("2");
		condition.setTeacherId(2);
		
		Long count = this.syllabusService.findSyllabusOfCount(condition);
		System.out.println(count);
		
		Assert.assertEquals(new Long(5), new Long(count));
	}
	
	@Test
	public void testDeleteSyllabusLessonBySyllabusId() {
		Integer l = this.syllabusService.removeSyllabusLessonBySyllabusId(null);
		System.out.println(l);
	}
}
