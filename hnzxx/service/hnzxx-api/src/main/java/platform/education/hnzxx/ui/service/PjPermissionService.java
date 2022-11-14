package platform.education.hnzxx.ui.service;
import java.util.List;

import platform.education.hnzxx.ui.model.PjPermission;
import platform.education.hnzxx.ui.vo.PjPermissionCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public interface PjPermissionService {
    PjPermission findPjPermissionById(Integer id);
	   
	PjPermission add(PjPermission pjPermission);
	   
	PjPermission modify(PjPermission pjPermission);
	   
	void remove(PjPermission pjPermission);
	   
	List<PjPermission> findPjPermissionByCondition(PjPermissionCondition pjPermissionCondition, Page page, Order order);
	
	List<PjPermission> findPjPermissionByCondition(PjPermissionCondition pjPermissionCondition);
	
	List<PjPermission> findPjPermissionByCondition(PjPermissionCondition pjPermissionCondition, Page page);
	
	List<PjPermission> findPjPermissionByCondition(PjPermissionCondition pjPermissionCondition, Order order);
	
	Long count();
	
	Long count(PjPermissionCondition pjPermissionCondition);
	
	PjPermission findByCodeANDtype(String code,String type);
}
