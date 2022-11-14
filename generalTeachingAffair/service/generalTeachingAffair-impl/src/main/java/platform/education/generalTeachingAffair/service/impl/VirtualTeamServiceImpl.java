package platform.education.generalTeachingAffair.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.generalTeachingAffair.model.VirtualTeam;
import platform.education.generalTeachingAffair.vo.VirtualTeamCondition;
import platform.education.generalTeachingAffair.service.VirtualTeamService;
import platform.education.generalTeachingAffair.dao.VirtualTeamDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public class VirtualTeamServiceImpl implements VirtualTeamService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private VirtualTeamDao virtualTeamDao;

	public void setVirtualTeamDao(VirtualTeamDao virtualTeamDao) {
		this.virtualTeamDao = virtualTeamDao;
	}
	
	@Override
	public VirtualTeam findVirtualTeamById(Integer id) {
		try {
			return virtualTeamDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public VirtualTeam add(VirtualTeam virtualTeam) {
		if(virtualTeam == null) {
    		return null;
    	}
    	if (virtualTeam.getUuid() == null || "".equals(virtualTeam.getUuid())) {
    		virtualTeam.setUuid(UUID.randomUUID().toString().replaceAll("-",""));
		}
    	Date createDate = new Date();
    	virtualTeam.setCreateDate(createDate);
    	virtualTeam.setModifyDate(createDate);
		return virtualTeamDao.create(virtualTeam);
	}

	@Override
	public VirtualTeam modify(VirtualTeam virtualTeam) {
		if(virtualTeam == null) {
    		return null;
    	}
    	Date modify = virtualTeam.getModifyDate();
    	virtualTeam.setModifyDate(modify != null ? modify : new Date());
		return virtualTeamDao.update(virtualTeam);
	}
	
	@Override
	public void remove(VirtualTeam virtualTeam) {
		try {
			virtualTeamDao.delete(virtualTeam);
		} catch(Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("删除数据库无存在ID为 {} ,异常为：{}", virtualTeam.getId(), e);
			}
		}
	}
	
	@Override
	public List<VirtualTeam> findVirtualTeamByCondition(VirtualTeamCondition virtualTeamCondition, Page page, Order order) {
		return virtualTeamDao.findVirtualTeamByCondition(virtualTeamCondition, page, order);
	}
	
	@Override
	public List<VirtualTeam> findVirtualTeamByCondition(VirtualTeamCondition virtualTeamCondition) {
		return virtualTeamDao.findVirtualTeamByCondition(virtualTeamCondition, null, null);
	}
	
	@Override
	public List<VirtualTeam> findVirtualTeamByCondition(VirtualTeamCondition virtualTeamCondition, Page page) {
		return virtualTeamDao.findVirtualTeamByCondition(virtualTeamCondition, page, null);
	}
	
	@Override
	public List<VirtualTeam> findVirtualTeamByCondition(VirtualTeamCondition virtualTeamCondition, Order order) {
		return virtualTeamDao.findVirtualTeamByCondition(virtualTeamCondition, null, order);
	}
	
	@Override
	public Long count() {
		return this.virtualTeamDao.count(null);
	}

	@Override
	public Long count(VirtualTeamCondition virtualTeamCondition) {
		return this.virtualTeamDao.count(virtualTeamCondition);
	}

	@Override
	public List<VirtualTeam> findByGradeId(Integer gradeId) {
		return this.virtualTeamDao.findByGradeId(gradeId);
	}

	@Override
	public List<VirtualTeam> findByGradeIdAndName(Integer schoolId, String year, Integer gradeId, String name) {
		return this.virtualTeamDao.findByGradeIdAndName(schoolId, year, gradeId, name);
	}

	@Override
	public boolean checkRepeatName(Integer gradeId, String name, Integer virtualTeamId) {
		List<VirtualTeam> list = virtualTeamDao.findByGradeIdAndName(null, null, gradeId, name);
		boolean isRepeat = false;
		if (list != null && !list.isEmpty()) {
			if (virtualTeamId != null) {
				for (VirtualTeam virtualTeam : list) {
					if (!virtualTeam.getId().equals(virtualTeamId)) {
						isRepeat = true;
						break;
					}
				}
			} else {
				isRepeat = true;
			}
		}
		return isRepeat;
	}
}
