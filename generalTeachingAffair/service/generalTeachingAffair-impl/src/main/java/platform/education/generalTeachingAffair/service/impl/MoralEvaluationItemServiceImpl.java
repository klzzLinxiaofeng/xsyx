package platform.education.generalTeachingAffair.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.generalTeachingAffair.dao.MoralEvaluationItemDao;
import platform.education.generalTeachingAffair.dao.MoralEvaluationResultDao;
import platform.education.generalTeachingAffair.model.MoralEvaluationItem;
import platform.education.generalTeachingAffair.model.MoralEvaluationResult;
import platform.education.generalTeachingAffair.service.MoralEvaluationItemService;
import platform.education.generalTeachingAffair.vo.MoralEvaluationItemCondition;
import platform.education.generalTeachingAffair.vo.MoralEvaluationItemVo;
import platform.education.generalTeachingAffair.vo.MoralEvaluationResultCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public class MoralEvaluationItemServiceImpl implements MoralEvaluationItemService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private MoralEvaluationItemDao moralEvaluationItemDao;
	
	private MoralEvaluationResultDao moralEvaluationResultDao;

	public void setMoralEvaluationItemDao(MoralEvaluationItemDao moralEvaluationItemDao) {
		this.moralEvaluationItemDao = moralEvaluationItemDao;
	}
	
	public void setMoralEvaluationResultDao(MoralEvaluationResultDao moralEvaluationResultDao) {
		this.moralEvaluationResultDao = moralEvaluationResultDao;
	}

	@Override
	public MoralEvaluationItem findMoralEvaluationItemById(Integer id) {
		try {
			return moralEvaluationItemDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public MoralEvaluationItem add(MoralEvaluationItem moralEvaluationItem) {
		if(moralEvaluationItem == null) {
    		return null;
    	}
    	Date createDate = moralEvaluationItem.getCreateDate();
    	if(createDate == null) {
    		createDate = new Date();
    	}
    	moralEvaluationItem.setCreateDate(createDate);
    	moralEvaluationItem.setModifyDate(createDate);
		return moralEvaluationItemDao.create(moralEvaluationItem);
	}

	@Override
	public MoralEvaluationItem modify(MoralEvaluationItem moralEvaluationItem) {
		if(moralEvaluationItem == null) {
    		return null;
    	}
    	Date modify = moralEvaluationItem.getModifyDate();
    	moralEvaluationItem.setModifyDate(modify != null ? modify : new Date());
		return moralEvaluationItemDao.update(moralEvaluationItem);
	}
	
	@Override
	public void remove(MoralEvaluationItem moralEvaluationItem) {
		 moralEvaluationItemDao.delete(moralEvaluationItem);
	}
	
	@Override
	public List<MoralEvaluationItem> findMoralEvaluationItemByCondition(MoralEvaluationItemCondition moralEvaluationItemCondition, Page page, Order order) {
		return moralEvaluationItemDao.findMoralEvaluationItemByCondition(moralEvaluationItemCondition, page, order);
	}
	
	@Override
	public List<MoralEvaluationItem> findMoralEvaluationItemByCondition(MoralEvaluationItemCondition moralEvaluationItemCondition) {
		return moralEvaluationItemDao.findMoralEvaluationItemByCondition(moralEvaluationItemCondition, null, null);
	}
	
	@Override
	public List<MoralEvaluationItem> findMoralEvaluationItemByCondition(MoralEvaluationItemCondition moralEvaluationItemCondition, Page page) {
		return moralEvaluationItemDao.findMoralEvaluationItemByCondition(moralEvaluationItemCondition, page, null);
	}
	
	@Override
	public List<MoralEvaluationItem> findMoralEvaluationItemByCondition(MoralEvaluationItemCondition moralEvaluationItemCondition, Order order) {
		return moralEvaluationItemDao.findMoralEvaluationItemByCondition(moralEvaluationItemCondition, null, order);
	}
	
	@Override
	public Long count() {
		return this.moralEvaluationItemDao.count(null);
	}

	@Override
	public Long count(MoralEvaluationItemCondition moralEvaluationItemCondition) {
		return this.moralEvaluationItemDao.count(moralEvaluationItemCondition);
	}

	@Override
	public void remove(MoralEvaluationItemCondition moralEvaluationItemCondition) {
		this.moralEvaluationItemDao.deleteByCondition(moralEvaluationItemCondition);
	}

	/**
	 * 功能描述：逻辑上删除用户账号即数据库仍然保存记录，只是修改删除标识
	 */
	@Override
	public String abandon(MoralEvaluationItem moralEvaluationItem) {
		if(moralEvaluationItem != null) {
			moralEvaluationItem.setIsDelete(true);
			try {
				moralEvaluationItem = this.moralEvaluationItemDao.update(moralEvaluationItem);
				if(moralEvaluationItem != null){
					MoralEvaluationResultCondition condition = new MoralEvaluationResultCondition();
					condition.setItemId(moralEvaluationItem.getId());
					List<MoralEvaluationResult> resultList = this.moralEvaluationResultDao.findMoralEvaluationResultByCondition(condition, null, null);
					if(resultList.size() > 0) {
						for(MoralEvaluationResult result : resultList) {
							this.moralEvaluationResultDao.delete(result);
						}
					}
//					this.moralEvaluationResultDao.deleteByCondition(condition);
					return MoralEvaluationItemService.OPERATE_SUCCESS;
				}
			} catch (Exception e) {
				if(log.isInfoEnabled()) {
					log.info("废弃 -> {} 失败，异常信息为 {}", moralEvaluationItem.getId(), e);
				}
				return MoralEvaluationItemService.OPERATE_ERROR;
			}
		}
		return MoralEvaluationItemService.OPERATE_FAIL;
	}

	/**
	 * 功能描述：从德育评价结果表里关联查询出某个学生的评价结果
	 */
	@Override
	public List<MoralEvaluationItemVo> findMoralEvaluationItemByConditionMore(
			MoralEvaluationItemCondition moralEvaluationItemCondition) {
		return moralEvaluationItemDao.findMoralEvaluationItemByConditionMore(moralEvaluationItemCondition);
	}

}
