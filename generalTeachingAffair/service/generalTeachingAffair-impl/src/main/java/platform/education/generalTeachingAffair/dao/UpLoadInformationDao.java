package platform.education.generalTeachingAffair.dao;

import java.util.List;

import platform.education.generalTeachingAffair.model.UpLoadInformation;
import platform.education.generalTeachingAffair.vo.UpLoadInformationCondition;
import framework.generic.dao.GenericDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public interface UpLoadInformationDao extends GenericDao<UpLoadInformation, java.lang.Integer>{

	
	UpLoadInformation findUpLoadInformationById(Integer id);
	
	
	List<UpLoadInformation> findUpLoadInformationByCondition(UpLoadInformationCondition upLoadInformationCondition, Page page, Order order);
	
}
