package platform.education.generalTeachingAffair.service.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.generalTeachingAffair.dao.PjTeacherSalaryFieldDao;
import platform.education.generalTeachingAffair.model.PjTeacherSalaryField;
import platform.education.generalTeachingAffair.service.PjTeacherSalaryFieldService;
import platform.education.generalTeachingAffair.vo.PjTeacherSalaryFieldCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public class PjTeacherSalaryFieldServiceImpl implements PjTeacherSalaryFieldService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private PjTeacherSalaryFieldDao pjTeacherSalaryFieldDao;

	public void setPjTeacherSalaryFieldDao(PjTeacherSalaryFieldDao pjTeacherSalaryFieldDao) {
		this.pjTeacherSalaryFieldDao = pjTeacherSalaryFieldDao;
	}
	
	@Override
	public PjTeacherSalaryField findPjTeacherSalaryFieldById(Integer id) {
		try {
			return pjTeacherSalaryFieldDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public PjTeacherSalaryField add(PjTeacherSalaryField pjTeacherSalaryField) {
		if(pjTeacherSalaryField == null ) {
    		return null;
    	}
		PjTeacherSalaryFieldCondition pjTeacherSalaryFieldCondition = new PjTeacherSalaryFieldCondition();
		pjTeacherSalaryFieldCondition.setSchoolId(pjTeacherSalaryField.getSchoolId());
		List<PjTeacherSalaryField> existList = pjTeacherSalaryFieldDao.findPjTeacherSalaryFieldByCondition(pjTeacherSalaryFieldCondition, null, null);
//		HashMap<String, PjTeacherSalaryField> attrNameMap = new HashMap();
//		for (PjTeacherSalaryField pjTeacherSalaryField2 : existList) {
//			attrNameMap.put(pjTeacherSalaryField2.getAttrName(), pjTeacherSalaryField2);
//		}
//		if ( attrNameMap.containsKey(pjTeacherSalaryField.getAttrName()) ) {
//			return attrNameMap.get(pjTeacherSalaryField.getAttrName());
//		}
		int number = 0;
		if ( existList != null && existList.size() >= 0 ) {
			number = existList.size();
		} else if ( existList != null && existList.size() >= 20 ) {
			throw new IllegalStateException("20个空间已用完");
		}
		pjTeacherSalaryField.setFieldName("s"+(++number));
		return pjTeacherSalaryFieldDao.create(pjTeacherSalaryField);
	}

	@Override
	public PjTeacherSalaryField modify(PjTeacherSalaryField pjTeacherSalaryField) {
		if(pjTeacherSalaryField == null) {
    		return null;
    	}
		return pjTeacherSalaryFieldDao.update(pjTeacherSalaryField);
	}
	
	@Override
	public void remove(PjTeacherSalaryField pjTeacherSalaryField) {
		try {
			pjTeacherSalaryFieldDao.delete(pjTeacherSalaryField);
		} catch(Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("删除数据库无存在ID为 {} ,异常为：{}", pjTeacherSalaryField.getId(), e);
			}
		}
	}
	
	@Override
	public List<PjTeacherSalaryField> findPjTeacherSalaryFieldByCondition(PjTeacherSalaryFieldCondition pjTeacherSalaryFieldCondition, Page page, Order order) {
		return pjTeacherSalaryFieldDao.findPjTeacherSalaryFieldByCondition(pjTeacherSalaryFieldCondition, page, order);
	}
	
	@Override
	public List<PjTeacherSalaryField> findPjTeacherSalaryFieldByCondition(PjTeacherSalaryFieldCondition pjTeacherSalaryFieldCondition) {
		return pjTeacherSalaryFieldDao.findPjTeacherSalaryFieldByCondition(pjTeacherSalaryFieldCondition, null, null);
	}
	
	@Override
	public List<PjTeacherSalaryField> findPjTeacherSalaryFieldByCondition(PjTeacherSalaryFieldCondition pjTeacherSalaryFieldCondition, Page page) {
		return pjTeacherSalaryFieldDao.findPjTeacherSalaryFieldByCondition(pjTeacherSalaryFieldCondition, page, null);
	}
	
	@Override
	public List<PjTeacherSalaryField> findPjTeacherSalaryFieldByCondition(PjTeacherSalaryFieldCondition pjTeacherSalaryFieldCondition, Order order) {
		return pjTeacherSalaryFieldDao.findPjTeacherSalaryFieldByCondition(pjTeacherSalaryFieldCondition, null, order);
	}
	
	@Override
	public Long count() {
		return this.pjTeacherSalaryFieldDao.count(null);
	}

	@Override
	public Long count(PjTeacherSalaryFieldCondition pjTeacherSalaryFieldCondition) {
		return this.pjTeacherSalaryFieldDao.count(pjTeacherSalaryFieldCondition);
	}

}
