package platform.education.oa.service.impl;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import platform.education.oa.dao.ApplyRepairDao;
import platform.education.oa.model.ApplyRepair;
import platform.education.oa.service.ApplyRepairService;
import platform.education.oa.vo.ApplyRepairCondition;
import platform.education.oa.vo.ApplyRepairVo;

import java.util.Date;
import java.util.List;

public class ApplyRepairServiceImpl implements ApplyRepairService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private ApplyRepairDao applyRepairDao;

	public void setApplyRepairDao(ApplyRepairDao applyRepairDao) {
		this.applyRepairDao = applyRepairDao;
	}
	
	@Override
	public ApplyRepair findApplyRepairById(Integer id) {
		try {
			return applyRepairDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public ApplyRepair add(ApplyRepair applyRepair) {
		if(applyRepair == null) {
    		return null;
    	}
    	Date createDate = applyRepair.getCreateDate();
    	if(createDate == null) {
    		createDate = new Date();
    	}
    	applyRepair.setCreateDate(createDate);
    	applyRepair.setModifyDate(createDate);

//		Integer typeId = applyRepair.getTypeId();
//		if (typeId!=null) {
//			applyRepairDao.createTypeById(typeId);
//		} else {
//			throw new NullPointerException();
//		}

		ApplyRepair pojo = applyRepairDao.create(applyRepair);
		return pojo;
		//return applyRepairDao.create(applyRepair);
	}

	@Override
	public ApplyRepair modify(ApplyRepair applyRepair) {
		if(applyRepair == null) {
    		return null;
    	}
    	Date modify = applyRepair.getModifyDate();
    	applyRepair.setModifyDate(modify != null ? modify : new Date());
		return applyRepairDao.update(applyRepair);
	}
	
	@Override
	public void remove(ApplyRepair applyRepair) {
		try {
			applyRepairDao.delete(applyRepair);
		} catch(Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("删除数据库无存在ID为 {} ,异常为：{}", applyRepair.getId(), e);
			}
		}
	}
	
	@Override
	public List<ApplyRepair> findApplyRepairByCondition(ApplyRepairCondition applyRepairCondition, Page page, Order order) {
		return applyRepairDao.findApplyRepairByCondition(applyRepairCondition, page, order);
	}
	
	@Override
	public List<ApplyRepair> findApplyRepairByCondition(ApplyRepairCondition applyRepairCondition) {
		return applyRepairDao.findApplyRepairByCondition(applyRepairCondition, null, null);
	}
	
	@Override
	public List<ApplyRepair> findApplyRepairByCondition(ApplyRepairCondition applyRepairCondition, Page page) {
		return applyRepairDao.findApplyRepairByCondition(applyRepairCondition, page, null);
	}
	
	@Override
	public List<ApplyRepair> findApplyRepairByCondition(ApplyRepairCondition applyRepairCondition, Order order) {
		return applyRepairDao.findApplyRepairByCondition(applyRepairCondition, null, order);
	}
	
	@Override
	public Long count() {
		return this.applyRepairDao.count(null);
	}

	@Override
	public Long count(ApplyRepairCondition applyRepairCondition) {
		return this.applyRepairDao.count(applyRepairCondition);
	}

	@Override
	public List<ApplyRepairVo> findApplyAndAcceptRepairByCondition(ApplyRepairCondition applyRepairCondition, Page page, Order order) {
		return applyRepairDao.findApplyAndAcceptRepairByCondition(applyRepairCondition, page, order);
	}

	@Override
	public ApplyRepair findApplyAndAcceptRepairById(Integer id) {
		try {
			return applyRepairDao.findMoreById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}

	@Override
	public List<ApplyRepairVo> findRepairHasApprovalByTeacherId(
			Integer teacherId, Page page, Order order) {
		// TODO Auto-generated method stub
		return applyRepairDao.findRepairHasApprovalByTeacherId(teacherId, page, order);
	}

	@Override
	public List<ApplyRepair> findByshenhe(String shenqingren, Integer shenheId, Integer typeId, Integer isShenhe, Page page) {
		return applyRepairDao.findByshenhe( shenqingren,  shenheId,  typeId,isShenhe,  page);
	}

	@Override
	public ApplyRepair findByshenheId(Integer id) {
		return applyRepairDao.findByshenheId(id);
	}

	@Override
	public String updateShenhe(Integer id, Integer weixiugong, String liyou, Integer isshenhe) {
		Integer num= applyRepairDao.updateShenhe(id,weixiugong,liyou,isshenhe);
		if(num>0){
			return "success";
		}else{
			return "error";

		}	}
}
