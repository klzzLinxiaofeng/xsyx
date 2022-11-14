package platform.education.generalTeachingAffair.service.test;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.Assert;

import platform.education.generalTeachingAffair.model.School;
import platform.education.generalTeachingAffair.model.SchoolInfo;
import platform.education.generalTeachingAffair.service.SchoolService;
import platform.education.generalTeachingAffair.service.test.base.BaseTest;
import platform.education.generalTeachingAffair.vo.SchoolCondition;
import framework.generic.dao.Page;

public class SchoolServiceTest extends BaseTest{
	
	@Autowired
	@Qualifier("schoolService")
	private SchoolService schoolService;
	
	@Test
	public void testAdd() {
		School school = new School();
		school.setAddress("address");
		//school.setBadge("badge");
		school.setBuildDate("2012-1-1");
		school.setCity("city");
		school.setCode("code");
		school.setCreateDate(new Date());
		school.setDecorationDay(new Date());
		school.setDistrict("district");
		school.setEnglishName("englishName");
		//school.setImage("image");
		school.setModifyDate(new Date());
		school.setName("name");
		school.setProvince("province");
		school.setRegionCode("regionCode");
		school.setRemark("remark");
		school.setRunningType("90");
		school.setSchoolType("89");
//		school.setStageDuration("9");
		school.setStageScope("888");
		school.setTelephone("13380087890");
		school.setWebsit("websit");
		school.setZipcode("510000");
		schoolService.add(school);
	}
	
	@Test
	public void testModify() {
		School school = schoolService.findSchoolById(4);
		school.setAddress("address1");
		//school.setBadge("badge1");
		school.setBuildDate("2012-1-2");
		school.setCity("city1");
		school.setCode("code1");
		school.setCreateDate(new Date());
		school.setDecorationDay(new Date());
		school.setDistrict("district1");
		school.setEnglishName("englishName1");
		//school.setImage("image1");
		school.setModifyDate(new Date());
		school.setName("name1");
		school.setProvince("province1");
		school.setRegionCode("regionCode1");
		school.setRemark("remark1");
		school.setRunningType("901");
		school.setSchoolType("891");
//		school.setStageDuration("91");
		school.setStageScope("8881");
		school.setTelephone("13380087890");
		school.setWebsit("websit1");
		school.setZipcode("510001");
		schoolService.modify(school);
	}
	
	@Test
	public void testFindById() {

	}
	
	@Test
	public void testRemove() {
		School school = schoolService.findSchoolById(10);
		schoolService.remove(school);
	}
	
	@Test
	public void testFindByCondition() {
		Page page = new Page();
		List<School> sslist = schoolService.getSchoolOfRegion(1, "regionCode", page, null);
		System.out.println(sslist.size()+"==================");
	}
	
	@Test
	public void tesFindSchoolInfoById() {
		SchoolInfo schoolInfo = this.schoolService.findSchoolInfoById(52);
		System.out.println(schoolInfo);
		Assert.notNull(schoolInfo);
	}
	
	@Test
	public void testFindSchoolInfoByCondition() {
		SchoolCondition condition = new SchoolCondition();
		condition.setId(52);
		List<SchoolInfo> schoolInfos = this.schoolService.findSchoolInfoByCondition(condition, null, null);
		System.out.println(schoolInfos);
	}
}
