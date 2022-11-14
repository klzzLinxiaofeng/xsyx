package platform.education.generalTeachingAffair.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.generalTeachingAffair.dao.ComplainFileDao;
import platform.education.generalTeachingAffair.model.Complain;
import platform.education.generalTeachingAffair.model.ComplainFile;
import platform.education.generalTeachingAffair.model.Department;
import platform.education.generalTeachingAffair.vo.ComplainCondition;
import platform.education.generalTeachingAffair.service.ComplainService;
import platform.education.generalTeachingAffair.dao.ComplainDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.vo.ComplainVo;

public class ComplainServiceImpl implements ComplainService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private ComplainDao complainDao;

	public void setComplainDao(ComplainDao complainDao) {
		this.complainDao = complainDao;
	}

	private ComplainFileDao complainFileDao;

	public void setComplainFileDao(ComplainFileDao complainFileDao) {
		this.complainFileDao = complainFileDao;
	}

	@Override
	public Complain findComplainById(Integer id) {
		try {
			return complainDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public Complain add(Complain complain) {
		if(complain == null) {
    		return null;
    	}
    	Date createDate = complain.getCreateDate();
    	if(createDate == null) {
    		createDate = new Date();
    	}
    	complain.setCreateDate(createDate);
    	complain.setModifyDate(createDate);
		return complainDao.create(complain);
	}

	@Override
	public Complain modify(Complain complain) {
		if(complain == null) {
    		return null;
    	}
    	Date modify = complain.getModifyDate();
    	complain.setModifyDate(modify != null ? modify : new Date());
		return complainDao.update(complain);
	}
	
	@Override
	public void remove(Complain complain) {
		try {
			complainDao.delete(complain);
		} catch(Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("删除数据库无存在ID为 {} ,异常为：{}", complain.getId(), e);
			}
		}
	}
	
	@Override
	public List<Complain> findComplainByCondition(ComplainCondition complainCondition, Page page, Order order) {
		return complainDao.findComplainByCondition(complainCondition, page, order);
	}
	
	@Override
	public List<Complain> findComplainByCondition(ComplainCondition complainCondition) {
		return complainDao.findComplainByCondition(complainCondition, null, null);
	}
	
	@Override
	public List<Complain> findComplainByCondition(ComplainCondition complainCondition, Page page) {
		return complainDao.findComplainByCondition(complainCondition, page, null);
	}
	
	@Override
	public List<Complain> findComplainByCondition(ComplainCondition complainCondition, Order order) {
		return complainDao.findComplainByCondition(complainCondition, null, order);
	}
	
	@Override
	public Long count() {
		return this.complainDao.count(null);
	}

	@Override
	public Long count(ComplainCondition complainCondition) {
		return this.complainDao.count(complainCondition);
	}


	@Override
	public List<ComplainVo> findByComplainant(Integer schoolId, Integer complainantId, String description, Page page) {
		List<ComplainVo> list = complainDao.findByComplainant(schoolId, complainantId, description, page);
		getUUIDs(list);
		return list;
	}

	@Override
	public List<ComplainVo> findAllComplain(Integer schoolId, Integer departId, String description, Boolean isDispose, Page page) {
		List<ComplainVo> list = complainDao.findAllComplain(schoolId, departId, description, isDispose, page);
		getUUIDs(list);
		return list;
	}

	private void getUUIDs(List<ComplainVo> list){
		for (ComplainVo complainVo : list) {
			String fileUUID = complainVo.getFileUUIDs();
			if (fileUUID != null && !"".equals(fileUUID)) {
				String[] fileUUIDs = fileUUID.split(",");
				complainVo.setFileUUIDList(Arrays.asList(fileUUIDs));
			}
		}
	}

	@Override
	public List<ComplainVo> findByCreateDate(Integer schoolId, Integer departId, String description, Date beginDate, Date endDate) {
		List<ComplainVo> list = complainDao.findByCreateDate(schoolId, departId, description, beginDate, endDate);
		return list;
	}

	@Override
	public Complain addComplain(Complain complain,String fileUUIDs) {
		if (complain == null) {
			return null;
		}
		if (complain.getCreateDate() == null) {
			complain.setCreateDate(new Date());
		}
		complain.setModifyDate(new Date());
		complain = complainDao.create(complain);
		createFiles(fileUUIDs, complain.getId());
		return complain;
	}
	//创建投诉图片
	private void createFiles(String fileUUIDs, Integer complainId){
		if (fileUUIDs != null && !"".equals(fileUUIDs)) {
			String[] uuids =  fileUUIDs.split(",");
			for (String uuid : uuids) {
				ComplainFile complainFile = new ComplainFile();
				complainFile.setComplainId(complainId);
				complainFile.setFileId(uuid);
				complainFile.setCreateDate(new Date());
				complainFileDao.create(complainFile);
			}
		}
	}

	@Override
	public Complain modifyComplain(Complain complain, String fileUUIDs, boolean isChange) {
		if (complain == null) {
			return null;
		}
		complain.setModifyDate(new Date());
		complain = complainDao.update(complain);
		if (isChange) {
			List<ComplainFile> fileList = complainFileDao.findByComplainId(complain.getId());
			for (ComplainFile file : fileList) {
				complainFileDao.delete(file);
			}
			createFiles(fileUUIDs, complain.getId());
		}
		return complain;
	}

	@Override
	public ComplainVo findComplainVoById(Integer id) {
		ComplainVo complainVo = complainDao.findComplainVoById(id);
		String fileUUID = complainVo.getFileUUIDs();
		if (fileUUID != null && !"".equals(fileUUID)) {
			String[] fileUUIDs = fileUUID.split(",");
			complainVo.setFileUUIDList(Arrays.asList(fileUUIDs));
		}
		return complainVo;
	}
}
