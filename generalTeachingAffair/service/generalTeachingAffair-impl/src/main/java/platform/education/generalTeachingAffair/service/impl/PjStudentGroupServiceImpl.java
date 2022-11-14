package platform.education.generalTeachingAffair.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.xml.crypto.dsig.keyinfo.PGPData;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import platform.education.generalTeachingAffair.model.PjStudentGroup;
import platform.education.generalTeachingAffair.model.PjStudentGroupInfo;
import platform.education.generalTeachingAffair.model.Team;
import platform.education.generalTeachingAffair.vo.PjStudentGroupCondition;
import platform.education.generalTeachingAffair.vo.TeamStudentCondition;
import platform.education.generalTeachingAffair.vo.TeamStudentVo;
import platform.education.generalTeachingAffair.service.PjStudentGroupInfoService;
import platform.education.generalTeachingAffair.service.PjStudentGroupService;
import platform.education.generalTeachingAffair.service.TeamService;
import platform.education.generalTeachingAffair.service.TeamStudentService;
import platform.education.generalTeachingAffair.dao.PjStudentGroupDao;
import platform.education.user.service.ProfileService;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public class PjStudentGroupServiceImpl implements PjStudentGroupService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private PjStudentGroupDao pjStudentGroupDao;
	@Resource
	private  TeamStudentService teamStudentService;
    @Resource
    private ProfileService profileService;
    @Resource
    private TeamService teamService;
    @Resource
    private PjStudentGroupInfoService pjStudentGroupInfoService;

	public void setPjStudentGroupDao(PjStudentGroupDao pjStudentGroupDao) {
		this.pjStudentGroupDao = pjStudentGroupDao;
	}
	
	@Override
	public PjStudentGroup findPjStudentGroupById(Integer id) {
		try {
			return pjStudentGroupDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public PjStudentGroup add(PjStudentGroup pjStudentGroup) {
		if(pjStudentGroup == null) {
    		return null;
    	}
    	Date createDate = pjStudentGroup.getCreateDate();
    	if(createDate == null) {
    		createDate = new Date();
    	}
    	pjStudentGroup.setCreateDate(createDate);
    	pjStudentGroup.setModifyDate(createDate);
		return pjStudentGroupDao.create(pjStudentGroup);
	}

	@Override
	public PjStudentGroup modify(PjStudentGroup pjStudentGroup) {
		if(pjStudentGroup == null) {
    		return null;
    	}
    	Date modify = pjStudentGroup.getModifyDate();
    	pjStudentGroup.setModifyDate(modify != null ? modify : new Date());
		return pjStudentGroupDao.update(pjStudentGroup);
	}
	
	@Override
	public void remove(PjStudentGroup pjStudentGroup) {
		try {
			pjStudentGroupDao.delete(pjStudentGroup);
		} catch(Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("删除数据库无存在ID为 {} ,异常为：{}", pjStudentGroup.getId(), e);
			}
		}
	}
	
	@Override
	public List<PjStudentGroup> findPjStudentGroupByCondition(PjStudentGroupCondition pjStudentGroupCondition, Page page, Order order) {
		return pjStudentGroupDao.findPjStudentGroupByCondition(pjStudentGroupCondition, page, order);
	}
	
	@Override
	public List<PjStudentGroup> findPjStudentGroupByCondition(PjStudentGroupCondition pjStudentGroupCondition) {
		return pjStudentGroupDao.findPjStudentGroupByCondition(pjStudentGroupCondition, null, null);
	}
	
	@Override
	public List<PjStudentGroup> findPjStudentGroupByCondition(PjStudentGroupCondition pjStudentGroupCondition, Page page) {
		return pjStudentGroupDao.findPjStudentGroupByCondition(pjStudentGroupCondition, page, null);
	}
	
	@Override
	public List<PjStudentGroup> findPjStudentGroupByCondition(PjStudentGroupCondition pjStudentGroupCondition, Order order) {
		return pjStudentGroupDao.findPjStudentGroupByCondition(pjStudentGroupCondition, null, order);
	}
	
	@Override
	public Long count() {
		return this.pjStudentGroupDao.count(null);
	}

	@Override
	public Long count(PjStudentGroupCondition pjStudentGroupCondition) {
		return this.pjStudentGroupDao.count(pjStudentGroupCondition);
	}

	@Override
	public void createBatch(PjStudentGroup[] pjStudentGroups) {
	
		this.pjStudentGroupDao.createBatch(pjStudentGroups);
		
	}

	@Override
	public void deleteByTeamId(Integer teamId) {
		this.pjStudentGroupDao.deleteByTeamId(teamId);
		this.pjStudentGroupInfoService.deleteByTeamId(teamId);
	}

	@Override
	public void addPjStudentGroupByDate(String data,Integer teamId) {
		pjStudentGroupDao.deleteByTeamId(teamId);
		pjStudentGroupInfoService.deleteByTeamId(teamId);
		Team t=teamService.findTeamById(teamId);
		JSONArray ay=JSONArray.fromObject(data);
		List<PjStudentGroup>list=new ArrayList<PjStudentGroup>();
		List<TeamStudentVo>volist=teamStudentService.findTeamStudentsByTeamId(teamId);
		Map<Integer,TeamStudentVo>tsMap=new HashMap<Integer, TeamStudentVo>();
		for(TeamStudentVo vo:volist){
			tsMap.put(vo.getId(), vo);
		}
		for(int i=0;i<ay.size();i++){
			JSONObject obj=ay.getJSONObject(i);
			JSONArray ay1=obj.getJSONArray("members");
			if(ay1.size()>0){
				String name="";
				if(obj.get("groupName")!=null){
					name=obj.get("groupName").toString();
				}
				PjStudentGroupInfo info=new PjStudentGroupInfo();
				info.setTeamId(teamId);
				info.setGradeId(t.getGradeId());
				info.setSchoolId(t.getSchoolId());
				info.setNumber(i+1);
				info.setGroupName(name);
				pjStudentGroupInfoService.add(info);
				for(int j=0;j<ay1.size();j++){
					PjStudentGroup pg=	new PjStudentGroup();
					pg.setStudentId(ay1.getInt(j));
					pg.setTeamId(teamId);
					pg.setGradeId(t.getGradeId());
					pg.setSchoolId(t.getSchoolId());
					pg.setGroupId(info.getId());
					list.add(pg);
				}
			}
		}
		PjStudentGroup[] pglist=list.toArray(new PjStudentGroup[list.size()]);
		pjStudentGroupDao.createBatch(pglist);
	}

}
