package platform.education.generalTeachingAffair.service.impl;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import platform.education.generalTeachingAffair.dao.GradeDao;
import platform.education.generalTeachingAffair.model.Grade;
import platform.education.generalTeachingAffair.model.School;
import platform.education.generalTeachingAffair.model.SchoolTermCurrent;
import platform.education.generalTeachingAffair.model.SchoolYear;
import platform.education.generalTeachingAffair.service.GradeService;
import platform.education.generalTeachingAffair.service.SchoolService;
import platform.education.generalTeachingAffair.service.SchoolTermCurrentService;
import platform.education.generalTeachingAffair.service.SchoolYearService;
import platform.education.generalTeachingAffair.vo.GradeCondition;
import platform.education.generalTeachingAffair.vo.GradeEnum;
import platform.education.generalTeachingAffair.vo.SchoolYearCondition;

import java.util.Date;
import java.util.List;

public class GradeServiceImpl implements GradeService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private GradeDao gradeDao;
	
	private SchoolService schoolService;
	
	private platform.education.generalcode.service.GradeService jcGradeService;

	public void setSchoolService(SchoolService schoolService) {
		this.schoolService = schoolService;
	}

	public void setJcGradeService(platform.education.generalcode.service.GradeService jcGradeService) {
		this.jcGradeService = jcGradeService;
	}

	public GradeDao getGradeDao() {
		return gradeDao;
	}

	public void setGradeDao(GradeDao gradeDao) {
		this.gradeDao = gradeDao;
	}
	
	private SchoolTermCurrentService schoolTermCurrentService;
	
	private SchoolYearService schoolYearService;
	
	public void setSchoolTermCurrentService(SchoolTermCurrentService schoolTermCurrentService) {
		this.schoolTermCurrentService = schoolTermCurrentService;
	}

	public void setSchoolYearService(SchoolYearService schoolYearService) {
		this.schoolYearService = schoolYearService;
	}

	@Caching(
		cacheable = {
			@Cacheable(value = "affair_grade_cache", key = "#id")
		}
	)
	@Override
	public Grade findGradeById(Integer id) {
		try {
			return gradeDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("??????????????????ID??? {} ", id);
		}
		return null;
	}
	
	/**
	 * @param schoolId
	 * @param schoolYear
	 * @return
	 */
	@Override
	public List<Grade> findGradeBySchoolYear(Integer schoolId,String schoolYear){
		return gradeDao.findGradeBySchoolYearAndSchoolId(schoolId,schoolYear);
	}
	/**
	 * 
	 * @param schoolYear
	 * @return
	 */
	@Override
	public List<Grade> findGradeBySchoolYear(String schoolYear){
		return gradeDao.findGradeBySchoolYear(schoolYear);
	}
	
	@Override
	public List<Grade> findGrageListBySchoolId(Integer schoolId){
		
		List<Grade> gradeList = null;
		SchoolTermCurrent schoolTermCurrent = schoolTermCurrentService.findSchoolTermCurrentBySchoolId(schoolId);
		if(schoolTermCurrent != null){
			gradeList = gradeDao.findGradeBySchoolYearAndSchoolId(schoolId, schoolTermCurrent.getSchoolYear());
		}else{
			SchoolYearCondition schoolYearCondition = new SchoolYearCondition();
			schoolYearCondition.setSchoolId(schoolId);
			schoolYearCondition.setIsDelete(false);
			List<SchoolYear> schoolYearList = schoolYearService.findSchoolYearByCondition(schoolYearCondition, null, Order.desc("year"));
			if(schoolYearList != null && schoolYearList.size() > 0){
				gradeList = gradeDao.findGradeBySchoolYearAndSchoolId(schoolId, schoolYearList.get(0).getYear());
	}
		}
		return gradeList;
	}
	
	public List<Grade> findGradeByCode(String code){
		return gradeDao.findGradeByCode(code);
	}
	
	@Override
	public Grade add(Grade grade) {
		if(grade != null) {
			Date date = new Date();
			grade.setCreateDate(date);
			grade.setModifyDate(date);
			return gradeDao.create(grade);
		}
		return null;
	}
	
	@Caching(put = { @CachePut(value = "affair_grade_cache", key = "#result.id"), })
	@Override
	public Grade modify(Grade grade) {
		if(grade != null && grade.getId() != null) {
			try {
				grade.setModifyDate(new Date());
				return gradeDao.update(grade);
			} catch (Exception e) {
				if(log.isInfoEnabled()) {
					log.info("??????????????????,ID???{},???????????????:", grade.getId(), e);
				}
			}
		}
		return null;
	}
	
	@Override
	@CacheEvict(value = "affair_grade_cache", key = "#grade.id")
	public String remove(Grade grade) {
		if(grade != null && grade.getId() != null) {
			try{
				grade.setDelete(true);
				grade.setModifyDate(new Date());
				gradeDao.update(grade);
			}catch(Exception e){
				log.info("????????????????????????ID??? {} ??????????????????", grade.getId(), e);
				return GradeService.OPERATE_ERROR;
			}
		} else {
			return GradeService.OPERATE_FAIL;
		}
		return GradeService.OPERATE_SUCCESS;
	}
	
	@Override
	public List<Grade> findGradeByCondition(GradeCondition gradeCondition, Page page, Order order) {
		return gradeDao.findGradeByCondition(gradeCondition, page, order);
	}
	
	@Override
	public List<Grade> findGradeByConditionTemp(GradeCondition gradeCondition, Page page, Order order){
		return gradeDao.findGradeByConditionTemp(gradeCondition, page, order);
		
	}

	@Override
	public Grade createUpgradeGrade(Integer gradeId) {
		Grade oldGrade = gradeDao.findById(gradeId);
		if(oldGrade == null){
			return null;
		}
		Grade newGrade = null;
		String[] primary = {"?????????", "?????????", "?????????", "?????????", "?????????", "?????????"};
		String[] junior = {"?????????", "?????????", "?????????"};
		String[] senior = {"??????", "??????", "??????"};
		Integer schoolId = oldGrade.getSchoolId();
		String schoolYear = String.valueOf(Integer.valueOf(oldGrade.getSchoolYear()) + 1);
		String uniGradeCode = String.valueOf(Integer.valueOf(oldGrade.getUniGradeCode()) + 1);
		Integer gradeNumber = oldGrade.getGradeNumber() + 1;
		String stageCode = oldGrade.getStageCode();
		String code = schoolId + "-" + schoolYear + "-" + uniGradeCode;
		String name = "";
		switch (stageCode) {
		case "2":
			name = primary[gradeNumber-1];
			break;
		case "3":
			name = junior[gradeNumber-1];
			break;
		case "4":
			name = senior[gradeNumber-1];
			break;
		default:
			break;
		}
		List<Grade> gradeList = gradeDao.findGradeByCode(code);
		if (gradeList.isEmpty()) {
			newGrade = new Grade();
			newGrade.setSchoolId(schoolId);
			newGrade.setCode(code);
			newGrade.setStageCode(stageCode);
			newGrade.setUniGradeCode(uniGradeCode);
			newGrade.setSchoolYear(schoolYear);
			newGrade.setGradeNumber(gradeNumber);
			newGrade.setFullName(name);
			newGrade.setName(name);
			newGrade.setTeamCount(0);
			newGrade.setBeginDate(new Date());
			newGrade.setFinishDate(new Date());
			newGrade.setCreateDate(new Date());
			newGrade.setModifyDate(new Date());
			newGrade.setDelete(false);
			newGrade = gradeDao.create(newGrade);
		} else {
			newGrade = gradeList.get(0);
		}
		return newGrade;
	}

	@Override
	public List<Grade> findIncrementByModifyDate(Integer schoolId, String schoolYear, Boolean isDeleted, Date modifyDate, Integer gradeId, Boolean isGetNew) {
		return this.gradeDao.findIncrementByModifyDate(schoolId, schoolYear, isDeleted, modifyDate, gradeId, isGetNew);
	}

	@Override
	public Grade findGradeBySchoolIdYearAndName(Integer schoolId, String schoolYear, String gradeName) {
		if(schoolId==null) {
			return null;
		}
		if(schoolYear==null || "".equals(schoolYear)) {
			return null;
		}
		if(gradeName==null || "".equals(gradeName)) {
			return null;
		}
		GradeCondition condition = new GradeCondition();
		condition.setSchoolId(schoolId);
		condition.setSchoolYear(schoolYear);
		condition.setName(gradeName);
		List<Grade> result = gradeDao.findGradeByCondition(condition);
		if(result.size()>0) {
			return result.get(0);
		}
		return null;
	}

	@Override
	public Grade addGradeUseSchoolIdYearAndName(Integer schoolId, String schoolYear, String gradeName) {
		if(schoolId==null) {
			return null;
		}
		if(schoolYear==null || "".equals(schoolYear)) {
			return null;
		}
		if(gradeName==null || "".equals(gradeName)) {
			return null;
		}
		
		String realGradeName = GradeEnum.convertGrade(gradeName);
		
		School school = schoolService.findSchoolById(schoolId);
		String stageScope = school.getStageScope();
		String schoolSystem = school.getSchoolSystem();
		
		platform.education.generalcode.model.Grade jcGrade = jcGradeService.findGradeByName(realGradeName);
		String stageCode = jcGrade.getStageCode();
		String code = jcGrade.getCode();
		Integer gradeNumber = jcGrade.getGradeNumber();
		
		boolean check = checkGrade(schoolSystem, stageScope, stageCode, realGradeName);
		
		if(!check) {
			/**???????????????????????????????????????????????????*/
			return null;
		}
		
		Grade grade = this.findGradeBySchoolIdYearAndName(schoolId, schoolYear, realGradeName);
		if(grade==null) {
			grade = new Grade();
			Date now = new Date();
			
			grade.setName(realGradeName);
			grade.setSchoolId(schoolId);
			grade.setFullName(realGradeName);
			grade.setCode(schoolId+"-"+schoolYear+"-"+code);
			grade.setStageCode(stageCode);
			grade.setUniGradeCode(code);
			grade.setSchoolYear(schoolYear);
			grade.setGradeNumber(gradeNumber);
			grade.setTeamCount(0);
			grade.setDelete(false);
			grade.setBeginDate(now);
			grade.setFinishDate(now);
			grade.setCreateDate(now);
			grade.setModifyDate(now);
			
			gradeDao.create(grade);
		}
		return grade;
	}

	@Override
	public List<Grade> findGradeOfSchoolYearAndTeacherId(Integer schoolId, String schoolYear, Integer teacherId) {
		return gradeDao.findGradeOfSchoolYearAndTeacherId(schoolId,schoolYear,teacherId);
	}

	private boolean checkGrade(String schoolSystem, String stageScope, String stageCode, String gradeName) {
		boolean flag = true;
		/**??????????????????*/
		if(stageScope.contains(stageCode)) {
			/**???????????????2???????????????????????????????????????????????????*/
			if("2".equals(schoolSystem)) {
				/**?????????????????????????????????????????????*/
				String tmp = stageScope.split(",")[1];
				/**?????????????????????????????????????????????????????????????????????*/
				if("-1".equals(tmp) && "gradeName".equals("?????????")) {
					flag = false;
				}
			}
		}
		return flag;
	}
}
