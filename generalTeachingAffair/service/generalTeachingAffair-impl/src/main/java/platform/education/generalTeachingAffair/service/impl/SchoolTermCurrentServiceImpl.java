package platform.education.generalTeachingAffair.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import platform.education.generalTeachingAffair.dao.SchoolTermCurrentDao;
import platform.education.generalTeachingAffair.model.SchoolTerm;
import platform.education.generalTeachingAffair.model.SchoolTermCurrent;
import platform.education.generalTeachingAffair.model.SchoolYear;
import platform.education.generalTeachingAffair.service.SchoolTermCurrentService;
import platform.education.generalTeachingAffair.service.SchoolTermService;
import platform.education.generalTeachingAffair.service.SchoolYearService;
import platform.education.generalTeachingAffair.vo.SchoolTermCurrentCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public class SchoolTermCurrentServiceImpl implements SchoolTermCurrentService {

	private Logger log = LoggerFactory.getLogger(getClass());

	private SchoolTermCurrentDao schoolTermCurrentDao;
	private SchoolTermService schoolTermService;
	private SchoolYearService schoolYearService;

	public void setSchoolTermCurrentDao(
			SchoolTermCurrentDao schoolTermCurrentDao) {
		this.schoolTermCurrentDao = schoolTermCurrentDao;
	}
	
	
	public void setSchoolTermService(SchoolTermService schoolTermService) {
		this.schoolTermService = schoolTermService;
	}



	public void setSchoolYearService(SchoolYearService schoolYearService) {
		this.schoolYearService = schoolYearService;
	}



	@Override
	public List<SchoolTermCurrent> findCurrentSchoolYear(Integer schoolId) {
		List<SchoolTermCurrent> schoolTermCurrent = schoolTermCurrentDao
				.findCurrentSchoolYearById(schoolId);
		return schoolTermCurrent;
	}
	
	@Override
	public SchoolTermCurrent findSchoolTermCurrentBySchoolIdAndSchoolYearId(Integer schoolId,Integer scoolYearId){
		return schoolTermCurrentDao.findSchoolTermCurrentBySchoolIdAndSchoolYearId(schoolId,scoolYearId);
	}

	@Override
	public SchoolTermCurrent findSchoolTermCurrentById(Integer id) {
		try {
			return schoolTermCurrentDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}

	@Override
	public SchoolTermCurrent add(SchoolTermCurrent schoolTermCurrent) {
		return schoolTermCurrentDao.create(schoolTermCurrent);
	}

	@Override
	public SchoolTermCurrent modify(SchoolTermCurrent schoolTermCurrent) {
		return schoolTermCurrentDao.update(schoolTermCurrent);
	}

	@Override
	public void remove(SchoolTermCurrent schoolTermCurrent) {
		try {
			schoolTermCurrentDao.delete(schoolTermCurrent);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("删除数据库无存在ID为 {} ");
		}

	}
	
	@Override
	public List<SchoolTermCurrent> findSchoolTermCurrentByCondition(
			SchoolTermCurrentCondition schoolTermCurrentCondition, Page page,
			Order order) {
		return schoolTermCurrentDao.findSchoolTermCurrentByCondition(
				schoolTermCurrentCondition, page, order);
	}

	@Override
	public String setCurrentSchoolTerm(Integer schoolTermId) {
		//查询该学校当前是否已存在记录
		SchoolTermCurrent stc = new SchoolTermCurrent();
		SchoolTermCurrent schoolTermCurrent = new SchoolTermCurrent();
		SchoolYear schoolYear = new SchoolYear();
		SchoolTerm schoolTerm = this.schoolTermService.findSchoolTermById(schoolTermId);
		if (schoolTerm != null) {
			//查询相关的学年记录String.valueOf()
			schoolYear = this.schoolYearService.findSchoolYearById(schoolTerm.getSchoolYearId());
			
			BeanUtils.copyProperties(schoolTerm, schoolTermCurrent);
			schoolTermCurrent.setModifyDate(new Date());
			schoolTermCurrent.setSchoolTermCode(schoolTerm.getCode());
			schoolTermCurrent.setSchoolTermName(schoolTerm.getName());
			schoolTermCurrent.setSchoolYearName(schoolYear.getName());
			schoolTermCurrent.setSchoolYear(schoolYear.getYear());
			schoolTermCurrent.setSchoolYearId(schoolYear.getId());
			
			
			//根据学校ID查询是否已存在该学校的记录，存在则更新，不存在则添加
			stc = this.schoolTermCurrentDao.findSchoolTermCurrentBySchoolId(schoolTerm.getSchoolId());
			if (stc != null) {
				schoolTermCurrent.setId(stc.getId());
				schoolTermCurrent.setCreateDate(stc.getCreateDate());
				schoolTermCurrent = this.schoolTermCurrentDao.update(schoolTermCurrent);
			}else{
				schoolTermCurrent.setId(null);
				schoolTermCurrent.setCreateDate(new Date());
				schoolTermCurrent = this.schoolTermCurrentDao.create(schoolTermCurrent);
			}
			return SchoolTermCurrentService.OPERATE_SUCCESS;
		}
		return SchoolTermCurrentService.OPERATE_FAIL;
	}


	@Override
	public SchoolTermCurrent findSchoolTermCurrentBySchoolId(Integer schoolId) {
		try {
			return schoolTermCurrentDao.findSchoolTermCurrentBySchoolId(schoolId);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", schoolId);
		}
		return null;
	}

}
