package platform.education.oa.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.oa.model.RepairFile;
import platform.education.oa.vo.RepairFileCondition;
import platform.education.oa.vo.RepairFileVo;
import platform.education.oa.service.RepairFileService;
import platform.education.oa.dao.RepairFileDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public class RepairFileServiceImpl implements RepairFileService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private RepairFileDao repairFileDao;

	public void setRepairFileDao(RepairFileDao repairFileDao) {
		this.repairFileDao = repairFileDao;
	}
	
	@Override
	public RepairFile findRepairFileById(Integer id) {
		try {
			return repairFileDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public RepairFile add(RepairFile repairFile) {
		if(repairFile == null) {
    		return null;
    	}
    	Date createDate = repairFile.getCreateDate();
    	if(createDate == null) {
    		createDate = new Date();
    	}
    	repairFile.setCreateDate(createDate);
    	repairFile.setModifyDate(createDate);
		return repairFileDao.create(repairFile);
	}

	@Override
	public RepairFile modify(RepairFile repairFile) {
		if(repairFile == null) {
    		return null;
    	}
    	Date modify = repairFile.getModifyDate();
    	repairFile.setModifyDate(modify != null ? modify : new Date());
		return repairFileDao.update(repairFile);
	}
	
	@Override
	public void remove(RepairFile repairFile) {
		try {
			repairFileDao.delete(repairFile);
		} catch(Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("删除数据库无存在ID为 {} ,异常为：{}", repairFile.getId(), e);
			}
		}
	}
	
	@Override
	public List<RepairFile> findRepairFileByCondition(RepairFileCondition repairFileCondition, Page page, Order order) {
		return repairFileDao.findRepairFileByCondition(repairFileCondition, page, order);
	}
	
	@Override
	public List<RepairFile> findRepairFileByCondition(RepairFileCondition repairFileCondition) {
		return repairFileDao.findRepairFileByCondition(repairFileCondition, null, null);
	}
	
	@Override
	public List<RepairFile> findRepairFileByCondition(RepairFileCondition repairFileCondition, Page page) {
		return repairFileDao.findRepairFileByCondition(repairFileCondition, page, null);
	}
	
	@Override
	public List<RepairFile> findRepairFileByCondition(RepairFileCondition repairFileCondition, Order order) {
		return repairFileDao.findRepairFileByCondition(repairFileCondition, null, order);
	}
	
	@Override
	public Long count() {
		return this.repairFileDao.count(null);
	}

	@Override
	public Long count(RepairFileCondition repairFileCondition) {
		return this.repairFileDao.count(repairFileCondition);
	}

	@Override
	public List<RepairFileVo> findRepairFileAllByCondition(
			RepairFileCondition repairFileCondition) {
		return repairFileDao.findRepairFileAllByCondition(repairFileCondition, null, null);
	}

}
