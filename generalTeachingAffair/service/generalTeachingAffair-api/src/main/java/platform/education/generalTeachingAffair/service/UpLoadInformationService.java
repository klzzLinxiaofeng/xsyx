package platform.education.generalTeachingAffair.service;


import java.util.List;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.model.UpLoadInformation;
import platform.education.generalTeachingAffair.vo.UpLoadInformationCondition;

public interface UpLoadInformationService {

	UpLoadInformation add(UpLoadInformation upLoadInformation);
	
	UpLoadInformation findUpLoadInformationById(Integer id);
	
	UpLoadInformation modify(UpLoadInformation upLoadInformation);
	
	void remove(UpLoadInformation upLoadInformation);
	
	List<UpLoadInformation> findUpLoadInformationByCondition(UpLoadInformationCondition upLoadInformationCondition, Page page, Order order);
	
}
