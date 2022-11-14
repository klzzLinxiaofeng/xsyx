package platform.education.rest.ph.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.xml.crypto.dsig.keyinfo.PGPData;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import framework.generic.dao.Order;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import platform.education.generalTeachingAffair.model.PjStudentGroup;
import platform.education.generalTeachingAffair.model.PjStudentGroupInfo;
import platform.education.generalTeachingAffair.model.PjUserAction;
import platform.education.generalTeachingAffair.model.Team;
import platform.education.generalTeachingAffair.service.PjStudentGroupInfoService;
import platform.education.generalTeachingAffair.service.PjStudentGroupService;
import platform.education.generalTeachingAffair.service.PjUserActionService;
import platform.education.generalTeachingAffair.service.TeamService;
import platform.education.generalTeachingAffair.service.TeamStudentService;
import platform.education.generalTeachingAffair.vo.PjStudentGroupCondition;
import platform.education.generalTeachingAffair.vo.PjStudentGroupInfoCondition;
import platform.education.generalTeachingAffair.vo.TeamStudentCondition;
import platform.education.generalTeachingAffair.vo.TeamStudentVo;
import platform.education.rest.common.ResponseError;
import platform.education.rest.common.ResponseInfo;
import platform.education.rest.common.ResponseVo;
import platform.education.rest.common.constants.CommonCode;
import platform.education.rest.common.constants.SysContants;
import platform.education.rest.ph.service.FastCourseRestService;
import platform.education.rest.util.ImgUtil;
import platform.education.user.model.AppEdition;
import platform.education.user.service.AppEditionService;
import platform.education.user.service.ProfileService;

public class FastCourseRestServiceImpl implements FastCourseRestService{
	@Resource
	private  TeamStudentService teamStudentService;
	@Resource
	private  PjStudentGroupService pjStudentGroupService;
    @Resource
    private ProfileService profileService;
    @Resource
    private TeamService teamService;
    @Resource
    private PjStudentGroupInfoService pjStudentGroupInfoService;
    @Resource
    private AppEditionService appEditionService;
    @Resource
    private PjUserActionService pjUserActionService;
	@Override
	public Object examListBySubject(Integer teamId, String appKey) {
		AppEdition app = this.appEditionService.findByAppKey(appKey);
		if(app == null){
			ResponseInfo info = new ResponseInfo();
			info.setDetail("appkey不存在,请确认");
			info.setMsg("不存在该appKey");
			info.setParam("appKey");
			return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
		}
		PjStudentGroupCondition pgCondition=new PjStudentGroupCondition();
		pgCondition.setIsDeleted(false);
		pgCondition.setTeamId(teamId);
		List<PjStudentGroup> pgList=pjStudentGroupService.findPjStudentGroupByCondition(pgCondition);
		List<Map<String,Object>>pgMapList=new ArrayList<Map<String,Object>>();
		Map<String,Object>objMap=new HashMap<String, Object>();
		List<TeamStudentVo>volist=teamStudentService.findTeamStudentsByTeamId(teamId);
		if(volist.size()==0){
			ResponseInfo info = new ResponseInfo();
			info.setDetail("这个班级不存在学生,请确认");
			info.setMsg("不存在该teamId");
			info.setParam("teamId");
			return new ResponseError(CommonCode.S$INVALID_DATA, info);
		}
		Map<Integer,TeamStudentVo>tsMap=new HashMap<Integer, TeamStudentVo>();
		Integer[] userIds=new Integer[volist.size()];
		Integer i=0;
		for(TeamStudentVo vo:volist){
			tsMap.put(vo.getId(), vo);
			userIds[i]=vo.getStudentId();
			i++;
		}
		Map<Integer,String> iconMap=new HashMap<Integer, String>();
		if(userIds.length>0){
			
			iconMap = ImgUtil.getImgSrcByIds(userIds, profileService);
		}
		Order o=new Order();
		o.setProperty("number");
		o.setAscending(true);
		PjStudentGroupInfoCondition pgInfoCondition=new PjStudentGroupInfoCondition();
		pgInfoCondition.setTeamId(teamId);
		List<PjStudentGroupInfo>pginfoList=pjStudentGroupInfoService.findPjStudentGroupInfoByCondition(pgInfoCondition,null,o);
		
		
		if(pgList.size()>0&&pginfoList.size()>0){
			objMap.put("isGroup",1);
			objMap.put("teamId", teamId);
			Map<Integer,List<PjStudentGroup>>pgMap=new HashMap<Integer, List<PjStudentGroup>>();
			for(PjStudentGroup pg:pgList){
				List<PjStudentGroup>pGroupList=new ArrayList<PjStudentGroup>();
				if(pgMap.get(pg.getGroupId())!=null){
					pGroupList=pgMap.get(pg.getGroupId());
				}
				pGroupList.add(pg);
				pgMap.put(pg.getGroupId(), pGroupList);
			}
			List<Map<String,Object>>infoList=new ArrayList<Map<String,Object>>();
			for(PjStudentGroupInfo pginfo:pginfoList){
				List<PjStudentGroup> psgList=pgMap.get(pginfo.getId());
				Map<String,Object> info=new HashMap<String, Object>();
				List<Map<String,Object>> pg1List=new ArrayList<Map<String,Object>>();
				for(PjStudentGroup pg:psgList){
					if(tsMap.get(pg.getStudentId())!=null){
						Map<String,Object> infomap=new HashMap<String, Object>();
						TeamStudentVo vo=tsMap.get(pg.getStudentId());
						infomap.put("studentId", vo.getId());
						infomap.put("studentNumber", vo.getStudentNumber()==null?"":vo.getStudentNumber());
						infomap.put("studentName", vo.getName());
						infomap.put("userName", vo.getUserName());
						infomap.put("userId", vo.getUserId());
						String icon=SysContants.APP_DEFAULT_USERICON;
						if(iconMap.get(vo.getUserId())!=null){
							icon=iconMap.get(vo.getUserId());
						}
						infomap.put("icon", icon);
						pg1List.add(infomap);
					}
				}
				info.put("groupId", pginfo.getId());
				info.put("groupName", pginfo.getGroupName());
				info.put("members", pg1List);
				info.put("number", pginfo.getNumber());
				infoList.add(info);
			}
			objMap.put("groups", infoList);
		}else{
			for(TeamStudentVo vo:volist){
				Map<String,Object>map=new HashMap<String, Object>();
				map.put("studentId", vo.getId());
				map.put("studentNumber", vo.getStudentNumber()==null?"":vo.getStudentNumber());
				map.put("studentName", vo.getName());
				map.put("userName", vo.getUserName());
				map.put("userId", vo.getUserId());
				String icon=SysContants.APP_DEFAULT_USERICON;
				if(iconMap.get(vo.getUserId())!=null){
					icon=iconMap.get(vo.getUserId());
				}
				map.put("icon", icon);
				pgMapList.add(map);
			}
			List<Map<String,Object>>infoList=new ArrayList<Map<String,Object>>();
			Map<String,Object> info=new HashMap<String, Object>();
			info.put("groupId", 0);
			info.put("groupName", "未分组");
			info.put("members", pgMapList);
			info.put("number", 1);
			info.put("info", pgMapList);
			infoList.add(info);
			objMap.put("groups", infoList);
			objMap.put("teamId", teamId);
			objMap.put("isGroup", 0);
		}
		return new ResponseVo<Map<String,Object>>("0",objMap);
	}
	@Override
	public Object add(Integer teamId, String appKey, String data) {
		try{
			AppEdition app = this.appEditionService.findByAppKey(appKey);
			if(app == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("appkey不存在,请确认");
				info.setMsg("不存在该appKey");
				info.setParam("appKey");
				return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
			}
			pjStudentGroupService.addPjStudentGroupByDate(data, teamId);
		}catch(Exception e){
			ResponseInfo info = new ResponseInfo();
			info.setDetail("数据异常");
			info.setMsg("数据异常");
			info.setParam("data");
			return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
		}
		return new ResponseVo<Map<String,Object>>("0",new HashMap<String,Object>());
	}
	@Override
	public Object reload(Integer teamId, String appKey) {
		try{
			AppEdition app = this.appEditionService.findByAppKey(appKey);
			if(app == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("appkey不存在,请确认");
				info.setMsg("不存在该appKey");
				info.setParam("appKey");
				return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
			}
			pjStudentGroupService.deleteByTeamId(teamId);
		}catch(Exception e){
			ResponseInfo info = new ResponseInfo();
			info.setDetail("数据异常");
			info.setMsg("数据异常");
			info.setParam("data");
			return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
		}
		return new ResponseVo<Map<String,Object>>("0",new HashMap<String,Object>());
	}
	@Override
	public Object addData(String data, String appKey) {
		AppEdition app = this.appEditionService.findByAppKey(appKey);
		if(app == null){
			ResponseInfo info = new ResponseInfo();
			info.setDetail("appkey不存在,请确认");
			info.setMsg("不存在该appKey");
			info.setParam("appKey");
			return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
		}
		pjUserActionService.addJSONData(data);
		return new ResponseVo<Map<String,Object>>("0",new HashMap<String,Object>());
	}
}
