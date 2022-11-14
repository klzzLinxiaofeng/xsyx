package platform.education.generalTeachingAffair.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.generalTeachingAffair.dao.SchoolYearDao;
import platform.education.generalTeachingAffair.model.SchoolYear;
import platform.education.generalTeachingAffair.service.SchoolYearService;
import platform.education.generalTeachingAffair.vo.SchoolYearCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public class SchoolYearServiceImpl implements SchoolYearService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private SchoolYearDao schoolYearDao;

	public void setSchoolYearDao(SchoolYearDao schoolYearDao) {
		this.schoolYearDao = schoolYearDao;
	}
	
	@Override
	public List<SchoolYear> findSchoolYearOfSchool(Integer schoolId){
		List<SchoolYear> schoolYearList = schoolYearDao.findSchoolYearOfSchool(schoolId);
		return schoolYearList;
	}
	
	
	@Override
	public SchoolYear findSchoolYearById(Integer id) {
		try {
			return schoolYearDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public SchoolYear add(SchoolYear schoolYear) {
		return schoolYearDao.create(schoolYear);
	}

	@Override
	public SchoolYear modify(SchoolYear schoolYear) {
		return schoolYearDao.update(schoolYear);
	}
	
	@Override
	public void remove(SchoolYear schoolYear) {
		try{
			schoolYearDao.delete(schoolYear);
		}catch(Exception e){
			e.printStackTrace();
			log.info("删除数据库无存在ID为 {} ");
		}
	}
	@Override
	public List<SchoolYear> findSchoolYearByCondition(SchoolYearCondition schoolYearCondition, Page page, Order order)
	{
		return schoolYearDao.findSchoolYearByCondition(schoolYearCondition, page, order);
	}

	@Override
	public SchoolYear findSchoolYearByYear(SchoolYearCondition schoolYearCondition) {
		return schoolYearDao.findSchoolYearByYear(schoolYearCondition);
	}

	/**
	 * 功能描述：逻辑上删除用户账号即数据库仍然保存记录，只是修改删除标识
	 */
	@Override
	public String abandon(SchoolYear schoolYear) {
		if(schoolYear != null) {
			schoolYear.setIsDelete(true);
			try {
				schoolYear = this.schoolYearDao.update(schoolYear);
				if(schoolYear != null){
					return SchoolYearService.OPERATE_SUCCESS;
				}
			} catch (Exception e) {
				if(log.isInfoEnabled()) {
					log.info("废弃 -> {} 失败，异常信息为 {}", schoolYear.getId(), e);
				}
				return SchoolYearService.OPERATE_ERROR;
			}
		}
		return SchoolYearService.OPERATE_FAIL;
	}

	@Override
	public SchoolYear findByYearAndSchoolId(Integer schoolId, String year) {
		if(schoolId == null || year == null || "".equals(year)){
			return null;
		}
		return this.schoolYearDao.findByYearAndSchoolId(schoolId, year);
	}

}
