package platform.education.oa.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.oa.model.Repair;
import platform.education.oa.vo.RepairCondition;
import platform.education.oa.vo.RepairVo;
import platform.education.oa.service.RepairService;
import platform.education.oa.dao.RepairDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public class RepairServiceImpl implements RepairService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private RepairDao repairDao;

	public void setRepairDao(RepairDao repairDao) {
		this.repairDao = repairDao;
	}
	
	@Override
	public Repair findRepairById(Integer id) {
		try {
			return repairDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public Repair add(Repair repair) {
		if(repair == null) {
    		return null;
    	}
    	Date createDate = repair.getCreateDate();
    	if(createDate == null) {
    		createDate = new Date();
    	}
    	repair.setCreateDate(createDate);
    	repair.setModifyDate(createDate);
		return repairDao.create(repair);
	}

	@Override
	public Repair modify(Repair repair) {
		if(repair == null) {
    		return null;
    	}
    	Date modify = repair.getModifyDate();
    	repair.setModifyDate(modify != null ? modify : new Date());
		return repairDao.update(repair);
	}
	
	@Override
	public void remove(Repair repair) {
		try {
			repairDao.delete(repair);
		} catch(Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("删除数据库无存在ID为 {} ,异常为：{}", repair.getId(), e);
			}
		}
	}
	
	@Override
	public List<Repair> findRepairByCondition(RepairCondition repairCondition, Page page, Order order) {
		return repairDao.findRepairByCondition(repairCondition, page, order);
	}
	
	@Override
	public List<Repair> findRepairByCondition(RepairCondition repairCondition) {
		return repairDao.findRepairByCondition(repairCondition, null, null);
	}
	
	@Override
	public List<Repair> findRepairByCondition(RepairCondition repairCondition, Page page) {
		return repairDao.findRepairByCondition(repairCondition, page, null);
	}
	
	@Override
	public List<Repair> findRepairByCondition(RepairCondition repairCondition, Order order) {
		return repairDao.findRepairByCondition(repairCondition, null, order);
	}
	
	@Override
	public Long count() {
		return this.repairDao.count(null);
	}

	@Override
	public Long count(RepairCondition repairCondition) {
		return this.repairDao.count(repairCondition);
	}

	@Override
	public List<RepairVo> findRepairAllByCondition(RepairCondition condition,
			Page page, Order order) {
		return repairDao.findRepairAllByCondition(condition, page, order);
	}

}
