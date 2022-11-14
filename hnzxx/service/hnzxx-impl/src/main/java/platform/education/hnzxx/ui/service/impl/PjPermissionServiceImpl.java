package platform.education.hnzxx.ui.service.impl;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.education.hnzxx.ui.dao.PjPermissionDao;
import platform.education.hnzxx.ui.model.PjPermission;
import platform.education.hnzxx.ui.service.PjPermissionService;
import platform.education.hnzxx.ui.vo.PjPermissionCondition;

import java.util.Date;
import java.util.List;
@Service
public class PjPermissionServiceImpl implements PjPermissionService{

	private Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private PjPermissionDao pjPermissionDao;

	public void setPjPermissionDao(PjPermissionDao pjPermissionDao) {
		this.pjPermissionDao = pjPermissionDao;
	}
	
	@Override
	public PjPermission findPjPermissionById(Integer id) {
		try {
			return pjPermissionDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public PjPermission add(PjPermission pjPermission) {
		if(pjPermission == null) {
    		return null;
    	}
    	Date createDate = pjPermission.getCreateDate();
    	if(createDate == null) {
    		createDate = new Date();
    	}
    	pjPermission.setCreateDate(createDate);
    	pjPermission.setModifyDate(createDate);
		return pjPermissionDao.create(pjPermission);
	}

	@Override
	public PjPermission modify(PjPermission pjPermission) {
		if(pjPermission == null) {
    		return null;
    	}
    	Date modify = pjPermission.getModifyDate();
    	pjPermission.setModifyDate(modify != null ? modify : new Date());
		return pjPermissionDao.update(pjPermission);
	}
	
	@Override
	public void remove(PjPermission pjPermission) {
			PjPermission pjPermission1 = pjPermissionDao.findById(pjPermission.getId());
			PjPermissionCondition pjPermissionCondition = new PjPermissionCondition();
			pjPermissionCondition.setCode(pjPermission1.getCode());
			List<PjPermission> pjPermissions = pjPermissionDao.findPjPermissionByCondition(pjPermissionCondition, null, null);
			for (PjPermission pjPermission2 : pjPermissions) {
				try {
				pjPermissionDao.delete(pjPermission2);
				} catch(Exception e) {
					if(log.isDebugEnabled()) {
						log.debug("删除数据库无存在ID为 {} ,异常为：{}", pjPermission2.getId(), e);
					}
				}
			}
		
	}
	
	@Override
	public List<PjPermission> findPjPermissionByCondition(PjPermissionCondition pjPermissionCondition, Page page, Order order) {
		return pjPermissionDao.findPjPermissionByCondition(pjPermissionCondition, page, order);
	}
	
	@Override
	public List<PjPermission> findPjPermissionByCondition(PjPermissionCondition pjPermissionCondition) {
		return pjPermissionDao.findPjPermissionByCondition(pjPermissionCondition, null, null);
	}
	
	@Override
	public List<PjPermission> findPjPermissionByCondition(PjPermissionCondition pjPermissionCondition, Page page) {
		return pjPermissionDao.findPjPermissionByCondition(pjPermissionCondition, page, null);
	}
	
	@Override
	public List<PjPermission> findPjPermissionByCondition(PjPermissionCondition pjPermissionCondition, Order order) {
		return pjPermissionDao.findPjPermissionByCondition(pjPermissionCondition, null, order);
	}
	
	@Override
	public Long count() {
		return this.pjPermissionDao.count(null);
	}

	@Override
	public Long count(PjPermissionCondition pjPermissionCondition) {
		return this.pjPermissionDao.count(pjPermissionCondition);
	}

	@Override
	public PjPermission findByCodeANDtype(String code, String type) {
		return this.pjPermissionDao.findByCodeANDtype(code, type);
	}

}
