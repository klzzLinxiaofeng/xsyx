package platform.education.hnzxx.ui.dao;

import framework.generic.dao.GenericDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import org.springframework.stereotype.Component;
import platform.education.hnzxx.ui.model.PjPermission;
import platform.education.hnzxx.ui.vo.PjPermissionCondition;

import java.util.List;

@Component
public interface PjPermissionDao extends GenericDao<PjPermission, java.lang.Integer> {

	List<PjPermission> findPjPermissionByCondition(PjPermissionCondition pjPermissionCondition, Page page, Order order);
	
	PjPermission findById(Integer id);
	
	Long count(PjPermissionCondition pjPermissionCondition);
	
	PjPermission findByCodeANDtype(String code,String type);
	
}
