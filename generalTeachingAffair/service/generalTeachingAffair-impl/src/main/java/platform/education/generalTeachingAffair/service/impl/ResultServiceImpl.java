package platform.education.generalTeachingAffair.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.dao.ResultDao;
import platform.education.generalTeachingAffair.model.Result;
import platform.education.generalTeachingAffair.service.ResultService;
import platform.education.generalTeachingAffair.vo.ResultCondition;
import platform.education.generalTeachingAffair.vo.ResultVo;

public class ResultServiceImpl implements ResultService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private ResultDao resultDao;

	public void setResultDao(ResultDao resultDao) {
		this.resultDao = resultDao;
	}
	
	@Override
	public Result findResultById(Integer id) {
		try {
			return resultDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public Result add(Result result) {
		if(result == null) {
    		return null;
    	}
    	Date createDate = result.getCreateDate();
    	if(createDate == null) {
    		createDate = new Date();
    	}
    	result.setCreateDate(createDate);
    	result.setModifyDate(createDate);
		return resultDao.create(result);
	}

	@Override
	public Result modify(Result result) {
		if(result == null) {
    		return null;
    	}
    	Date modify = result.getModifyDate();
    	result.setModifyDate(modify != null ? modify : new Date());
		return resultDao.update(result);
	}
	
	@Override
	public void remove(Result result) {
		try {
			resultDao.delete(result);
		} catch(Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("删除数据库无存在ID为 {} ,异常为：{}", result.getId(), e);
			}
		}
	}
	
	@Override
	public List<Result> findResultByCondition(ResultCondition resultCondition, Page page, Order order) {
		return resultDao.findResultByCondition(resultCondition, page, order);
	}
	
	@Override
	public List<Result> findResultByCondition(ResultCondition resultCondition) {
		return resultDao.findResultByCondition(resultCondition, null, null);
	}
	
	@Override
	public List<Result> findResultByCondition(ResultCondition resultCondition, Page page) {
		return resultDao.findResultByCondition(resultCondition, page, null);
	}
	
	@Override
	public List<Result> findResultByCondition(ResultCondition resultCondition, Order order) {
		return resultDao.findResultByCondition(resultCondition, null, order);
	}
	
	@Override
	public Long count() {
		return this.resultDao.count(null);
	}

	@Override
	public Long count(ResultCondition resultCondition) {
		return this.resultDao.count(resultCondition);
	}

	@Override
	public List<ResultVo> findResultVoByCondition(ResultCondition resultCondition, Page page, Order order) {
		return resultDao.findResultVoByCondition(resultCondition, page, order);
	}

	@Override
	public String moveTo(Result result) {
		if(result != null) {
			result.setIsDelete(true);
			try {
				result = this.resultDao.update(result);
				if(result != null){
					return ResultService.OPERATE_SUCCESS;
				}
			} catch (Exception e) {
				if(log.isInfoEnabled()) {
					log.info("废弃 -> {} 失败，异常信息为 {}", result.getId(), e);
				}
				return ResultService.OPERATE_ERROR;
			}
		}
		return ResultService.OPERATE_FAIL;
	}

}
