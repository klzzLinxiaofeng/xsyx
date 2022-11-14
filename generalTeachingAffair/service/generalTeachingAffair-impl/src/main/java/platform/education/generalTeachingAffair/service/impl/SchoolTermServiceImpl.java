package platform.education.generalTeachingAffair.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.generalTeachingAffair.model.SchoolTerm;
import platform.education.generalTeachingAffair.model.SchoolYear;
import platform.education.generalTeachingAffair.vo.SchoolTermCondition;
import platform.education.generalTeachingAffair.vo.SchoolTermVo;
import platform.education.generalTeachingAffair.service.SchoolTermService;
import platform.education.generalTeachingAffair.dao.SchoolTermDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public class SchoolTermServiceImpl implements SchoolTermService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private SchoolTermDao schoolTermDao;

	public void setSchoolTermDao(SchoolTermDao schoolTermDao) {
		this.schoolTermDao = schoolTermDao;
	}
	
	@Override
	public List<SchoolTerm> getSchoolTermOfSchool(Integer schoolId){
		List<SchoolTerm>  schoolTermList = schoolTermDao.findSchoolTermOfSchoolId(schoolId);
		return schoolTermList;
	}
	
	@Override
	public List<SchoolTerm> getSchoolTermOfYear(Integer schoolId,Integer schoolYearId){
		List<SchoolTerm>  schoolTermList = schoolTermDao.findSchoolTermOfYearBySchoolId(schoolId,schoolYearId);
		return schoolTermList;
	}
	
	@Override
	public SchoolTerm findSchoolTermById(Integer id) {
		try {
			return schoolTermDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public SchoolTerm add(SchoolTerm schoolTerm) {
		return schoolTermDao.create(schoolTerm);
	}

	@Override
	public SchoolTerm modify(SchoolTerm schoolTerm) {
		return schoolTermDao.update(schoolTerm);
	}
	
	@Override
	public void remove(SchoolTerm schoolTerm) {
		try{
			schoolTermDao.delete(schoolTerm);
		}catch(Exception e){
			e.printStackTrace();
			log.info("删除数据库无存在ID为 {} ");
		}
		 
	}
	@Override
	public List<SchoolTerm> findSchoolTermByCondition(SchoolTermCondition schoolTermCondition, Page page, Order order)
	{
		return schoolTermDao.findSchoolTermByCondition(schoolTermCondition, page, order);
	}

	@Override
	public List<SchoolTermVo> findSchoolTermByConditionMore(
			SchoolTermCondition schoolTermCondition, Page page, Order order) {
		return schoolTermDao.findSchoolTermByConditionMore(schoolTermCondition, page, order);
	}

	/**
	 * 功能描述：逻辑上删除用户账号即数据库仍然保存记录，只是修改删除标识
	 */
	@Override
	public String abandon(SchoolTerm schoolTerm) {
		if(schoolTerm != null) {
			schoolTerm.setIsDelete(true);
			try {
				schoolTerm = this.schoolTermDao.update(schoolTerm);
				if(schoolTerm != null){
					return SchoolTermService.OPERATE_SUCCESS;
				}
			} catch (Exception e) {
				if(log.isInfoEnabled()) {
					log.info("废弃 -> {} 失败，异常信息为 {}", schoolTerm.getId(), e);
				}
				return SchoolTermService.OPERATE_ERROR;
			}
		}
		return SchoolTermService.OPERATE_FAIL;
	}

	@Override
	public void modifyBySchoolYear(SchoolYear schoolYear) {
		SchoolTermCondition schoolTermCondition = new SchoolTermCondition();
		SchoolTerm schoolTerm = null;
		schoolTermCondition.setSchoolYearId(schoolYear.getId());
		List<SchoolTerm> termList = schoolTermDao.findSchoolTermByCondition(schoolTermCondition, null, null);
		if(termList != null && termList.size() > 0){
			for(SchoolTerm st : termList){
				if(st != null && st.getId() != null){
					schoolTerm = new SchoolTerm();
					schoolTerm.setId(st.getId());
					schoolTerm.setSchoolYear(schoolYear.getYear());
					schoolTerm.setModifyDate(new Date());
					schoolTermDao.update(schoolTerm);
				}
			}
		}
		
	}
	

	@Override
	public SchoolTerm findSchoolTermByCode(Integer schoolId, String code) {
		return schoolTermDao.findSchoolYearBySchoolTerm(schoolId, code);
	}

	@Override
	public SchoolTerm findSchoolYearByToday(Integer schoolId, Date date) {
		
		return schoolTermDao.findSchoolYearByToday(schoolId, date);
	}
}
