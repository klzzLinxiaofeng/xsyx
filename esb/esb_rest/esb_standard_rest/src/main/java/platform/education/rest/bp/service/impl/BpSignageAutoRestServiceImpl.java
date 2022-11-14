package platform.education.rest.bp.service.impl;

import com.alibaba.fastjson.JSON;
import framework.generic.dao.Order;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import platform.education.clazz.model.BpBwSignage;
import platform.education.clazz.model.RoomTeam;
import platform.education.clazz.model.SignageAuto;
import platform.education.clazz.service.*;
import platform.education.clazz.vo.BpBwSignageCondition;
import platform.education.clazz.vo.RoomTeamCondition;
import platform.education.clazz.vo.SignageAutoCondition;
import platform.education.clazz.vo.SignageAutoVo;
import platform.education.generalTeachingAffair.model.SchoolTermCurrent;
import platform.education.generalTeachingAffair.service.*;
import platform.education.im.dao.PushSubscriptionDao;
import platform.education.im.service.ImAccountService;
import platform.education.rest.bp.service.BpSignageAutoRestService;
import platform.education.rest.bp.service.contants.BpCommonConstants;
import platform.education.rest.bp.service.contants.DataAction;
import platform.education.rest.bp.service.util.IMPushUtil;
import platform.education.rest.bp.service.util.ResponseUtil;
import platform.education.rest.bp.service.util.ValidateUtil;
import platform.education.rest.bp.service.vo.ResponseOrder;
import platform.education.rest.bp.service.vo.SignageAutoVoForPush;
import platform.education.rest.common.ResponseError;
import platform.education.rest.common.ResponseInfo;
import platform.education.rest.common.ResponseVo;
import platform.education.rest.common.constants.CommonCode;
import platform.education.user.service.AppEditionService;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class BpSignageAutoRestServiceImpl implements BpSignageAutoRestService {
	
	private static long hour = 1000 * 60 * 60l;

	@Autowired
	@Qualifier("pushSubscriptionDao")
	private PushSubscriptionDao pushSubscriptionDao;
	
	@Autowired
	@Qualifier("imAccountService")
	private ImAccountService imAccountService;
	
	@Autowired
	@Qualifier("appEditionService")
	private AppEditionService appEditionService;
	@Autowired
	@Qualifier("signageAutoService")
	private SignageAutoService signageAutoService;
	@Autowired
	@Qualifier("bpBwSignageService")
	private BpBwSignageService bpBwSignageService;
	@Autowired
	@Qualifier("roomService")
	private RoomService roomService;
	@Autowired
	@Qualifier("teamAccountService")
	private TeamAccountService teamAccountService;
	@Autowired
	@Qualifier("teamTeacherService")
	private TeamTeacherService teamTeacherService;
	@Autowired
	@Qualifier("roomTeamService")
	private RoomTeamService roomTeamService;
	@Autowired
	@Qualifier("teacherService")
	private TeacherService teacherService;
	@Autowired
	@Qualifier("teamService")
	private TeamService teamService;
	@Autowired
	@Qualifier("teamUserService")
	private TeamUserService teamUserService;
	@Resource(name = "bp_taskExecutor")
	private TaskExecutor bp_taskExecutor;
	@Autowired
	@Qualifier("schoolTermCurrentService")
	private SchoolTermCurrentService schoolTermCurrentService;

	@Override
	public Object getBpSignageAutoListByRoomId(Integer roomId, String appKey,String signage) {
		try {
			if ( roomId == null ) {
				return ResponseUtil.paramerIsNull("roomId:"+roomId);
			}
			Object checkResult = ValidateUtil.checkAppKeyAndSignae(appKey, signage, appEditionService, bpBwSignageService);
			if ( checkResult != null ) {
				return checkResult;
			}
			SignageAutoCondition condition = new SignageAutoCondition();
			condition.setRoomId(roomId);
			Order order = new Order();
			order.setProperty("week_day");
			order.setAscending(true);
			List<SignageAuto> signageAutoList = signageAutoService.findSignageAutoByCondition(condition, null, order);
			List<SignageAutoVo> signageAutoVoList = new ArrayList<>();
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
			for (SignageAuto signageAuto : signageAutoList) {
				SignageAutoVo sv = new SignageAutoVo();
				sv.setId(signageAuto.getId());
				sv.setRoomId(signageAuto.getRoomId());
				sv.setWeekDay(signageAuto.getWeekDay());
				sv.setStatus(signageAuto.getStatus());
				if ( signageAuto.getOpenTime() != null ) {
					sv.setOpenTime2(sdf.format(signageAuto.getOpenTime()));
				}
				if ( signageAuto.getCloseTime() != null ) {
					sv.setCloseTime2(sdf.format(signageAuto.getCloseTime()));
				}
				signageAutoVoList.add(sv);
			}
			List<Integer> list = new ArrayList<Integer>();
			list.add(0);
			list.add(1);
			list.add(2);
			list.add(3);
			list.add(4);
			list.add(5);
			list.add(6);
			for (SignageAuto signageAuto : signageAutoList) {
				list.remove(signageAuto.getWeekDay());
			}
			for (Integer weekDay : list) {
				SignageAutoVo signageAutoVo = new SignageAutoVo();
				signageAutoVo.setWeekDay(weekDay);
				signageAutoVo.setStatus("0");
				signageAutoVoList.add(signageAutoVo);
			}
			Long count = signageAutoService.count(condition);
			return new ResponseOrder("0", signageAutoVoList, count.intValue());
		} catch (Exception e) {
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("接口异常");
			info.setMsg("接口异常");
			return new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR,info);
		}
	}
	
	@Override
	public Object getBpSignageAutoList2(Integer teamId, String appKey, String signage) {
		try {
			if ( teamId == null ) {
				return ResponseUtil.paramerIsNull("teamId:"+teamId);
			}
			Integer roomId = getRoomByTeamId(teamId);
			if ( roomId == null ) {
				return ResponseUtil.dataNotExist("teamId:"+teamId+"绑定的相应教室");
			}
			return this.getBpSignageAutoListByRoomId(roomId, appKey, signage);
		} catch (Exception e) {
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("接口异常");
			info.setMsg("接口异常");
			return new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR,info);
		}
	}
	
	private Integer getRoomByTeamId(Integer teamId) {
		RoomTeamCondition roomTeamCondition = new RoomTeamCondition();
		roomTeamCondition.setTeamId(teamId);
		List<RoomTeam> roomTeamList = roomTeamService.findRoomTeamByCondition(roomTeamCondition);
		Integer roomId = null;
		if ( roomTeamList != null && roomTeamList.size() > 0 ) {
			roomId = roomTeamList.get(0).getRoomId();
		}
		return roomId;
	}
	
	private void pushServiceByTeam(SignageAutoCondition signageAutoCondition, Integer schoolId, Integer roomId) {
		RoomTeamCondition roomTeamCondition = new RoomTeamCondition();
		roomTeamCondition.setRoomId(roomId);
		SchoolTermCurrent schoolTermCurrent = schoolTermCurrentService.findSchoolTermCurrentBySchoolId(schoolId);
		roomTeamCondition.setSchoolYear(schoolTermCurrent.getSchoolYear());
		List<RoomTeam> roomTeamList = roomTeamService.findRoomTeamByCondition(roomTeamCondition);
		RoomTeam roomTeam = null;
		if ( roomTeamList != null && roomTeamList.size() > 0 ) {
			roomTeam = roomTeamList.get(0);
		} else {
			return;
		}
		List<SignageAuto> signageAutoList = signageAutoService.findSignageAutoByCondition(signageAutoCondition);
		if ( signageAutoList == null || signageAutoList.size() == 0 ) {
			return;
		}
		List<SignageAutoVoForPush> list = new ArrayList<SignageAutoVoForPush>();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		for (SignageAuto signageAuto : signageAutoList) {
			SignageAutoVoForPush voForPush = new SignageAutoVoForPush();
			voForPush.setWeekDay(signageAuto.getWeekDay());
			if ( signageAuto.getCloseTime() != null ) {
				voForPush.setCloseTime(sdf.format(signageAuto.getCloseTime()));
			}
			if ( signageAuto.getOpenTime() != null ) {
				voForPush.setOpenTime(sdf.format(signageAuto.getOpenTime()));
			}
			list.add(voForPush);
		}
		String jsonString = JSON.toJSONString(list);
		//推送开始=====
		//要推送的班级
		List<Integer>teamIds = new ArrayList<Integer>();
		teamIds.add(roomTeam.getTeamId());
		//推送
//		IMPushUtil.push(teamIds, DataAction.D$add, null, BpCommonConstants.PUSH_SIGNAGE_AUTO, jsonString, 
//				bpBwSignageService, schoolTermCurrentService, roomTeamService, teamService, bp_taskExecutor);
		
//		IMPushUtil.signagePush(teamIds, 1, DataAction.D$add, null,BpCommonConstants.PUSH_SIGNAGE_AUTO, jsonString, bpBwSignageService, bp_taskExecutor);
		IMPushUtil.PushByXJXP(teamIds, 1, DataAction.D$add,null, BpCommonConstants.PUSH_SIGNAGE_AUTO, jsonString, bpBwSignageService,imAccountService ,pushSubscriptionDao,bp_taskExecutor);
		//推送结束 ====
	}
	
	private void saveOrModifySignageAuto(SignageAuto signageAuto) {
		SignageAuto signageAutoEntity = new SignageAuto();
		SignageAutoCondition signageAutoCondition = new SignageAutoCondition();
		signageAutoCondition.setRoomId(signageAuto.getRoomId());
		signageAutoCondition.setWeekDay(signageAuto.getWeekDay());
		List<SignageAuto> signageAutoEntityList = signageAutoService.findSignageAutoByCondition(signageAutoCondition);
		if ( signageAutoEntityList != null && signageAutoEntityList.size() > 0 ) {
			signageAutoEntity = signageAutoEntityList.get(0);
		}
		signageAutoEntity.setCloseTime(signageAuto.getCloseTime());
		signageAutoEntity.setOpenTime(signageAuto.getOpenTime());
		signageAutoEntity.setRoomId(signageAuto.getRoomId());
		signageAutoEntity.setStatus(signageAuto.getStatus());
		signageAutoEntity.setWeekDay(signageAuto.getWeekDay());
		signageAutoEntity.setModifyDate(new Date());
		if ( signageAutoEntity.getId() != null ) {
			signageAutoEntity = signageAutoService.modify(signageAutoEntity);
		} else {
			signageAutoEntity.setCreateDate(new Date());
			signageAutoEntity = this.signageAutoService.add(signageAutoEntity);
		}
	}
	
	@Override
	public Object createOrModifyBpSignageAutosByRooms(Integer id, String roomIds, Integer schoolId, String content, 
			String appKey, String signage) {
		String[] roomIdList = roomIds.split(",");
		Object checkResult = ValidateUtil.checkAppKeyAndSignae(appKey, signage, appEditionService, bpBwSignageService);
		if ( checkResult != null ) {
			return checkResult;
		}
		try {
			List<Map> parseArray = JSON.parseArray(content, Map.class);
			for (Map map2 : parseArray) {
				String closeTime2 = null;
				String openTime2 = null;
				if ( map2.containsKey("closeTime2") ) {
					closeTime2 = (String) map2.get("closeTime2");
				}
				if ( map2.containsKey("openTime2") ) {
					openTime2 =  (String) map2.get("openTime2");
				}
				String status = (String) map2.get("status");
				Integer weekDay = (Integer) map2.get("weekDay");
				for(String roomIdStr: roomIdList){
					Integer roomId = Integer.parseInt(roomIdStr);
					BpBwSignageCondition bwSignageCondition = new BpBwSignageCondition();
					bwSignageCondition.setRoomId(roomId);
					bwSignageCondition.setIsDeleted(false);
					List<BpBwSignage> signageList = this.bpBwSignageService.findBwSignageByCondition(bwSignageCondition);	
					if(signageList != null && signageList.size() > 0){
						for(BpBwSignage bpBwSignage: signageList){
							bpBwSignage.setPushSignageAuto(false);
							bpBwSignage.setPushSignageAutoTime(new Date());
							this.bpBwSignageService.modify(bpBwSignage);
						}
					}
					SignageAuto signageAuto = new SignageAuto();
					signageAuto.setId(id);
					signageAuto.setRoomId(roomId);
					signageAuto.setWeekDay(weekDay);
					signageAuto.setStatus(status);
					SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
					if ( openTime2 != null ) {
						signageAuto.setOpenTime(sdf.parse(openTime2));
					}
					if ( closeTime2 != null ) {
						signageAuto.setCloseTime(sdf.parse(closeTime2));
					}
					if ( id == null ) {
						if ( signageAuto.getRoomId() == null || schoolId == null ) {
							ResponseInfo info = new ResponseInfo();
							info.setDetail("roomId为null或schoolId为null");
							info.setMsg("roomId为null或schoolId为null");
							return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL,info);
						}
						saveOrModifySignageAuto(signageAuto);
					} else {
						signageAuto.setId(id);
						signageAuto = this.signageAutoService.modify(signageAuto);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("接口异常");
			info.setMsg("接口异常");
			return new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR,info);
		}
		SignageAutoCondition signageAutoCondition = new SignageAutoCondition();
		for(String roomIdStr: roomIdList){
			Integer roomId = Integer.parseInt(roomIdStr);
			signageAutoCondition.setRoomId(roomId);
			pushServiceByTeam(signageAutoCondition, schoolId,  roomId);
		}
		return new ResponseVo<Boolean>("0", true);
	}
	
	@Override
	public Object createOrModifyBpSignageAutos(Integer id, String teamIds,
			Integer schoolId, String content, String appKey, String signage) {
		String[] teamIdList = teamIds.split(",");
		String[] roomIds = new String[teamIdList.length];
		for(int i=0; i<teamIdList.length; i++){
			roomIds[i] = getRoomByTeamId(Integer.parseInt(teamIdList[i])) + "";
		}
		return this.createOrModifyBpSignageAutosByRooms(id, StringUtils.join(roomIds, ","), schoolId, content, appKey, signage);
	}
	
	@Override
	public Object createOrModifyBpSignageAutoByRoom1(Integer id, Integer roomId, Integer schoolId, String content, 
			String appKey, String signage) {
		Object checkResult = ValidateUtil.checkAppKeyAndSignae(appKey, signage, appEditionService, bpBwSignageService);
		if ( checkResult != null ) {
			return checkResult;
		}
		try {
			List<Map> parseArray = JSON.parseArray(content, Map.class);
			for (Map map2 : parseArray) {
				Long closeTime = null;
				Long openTime = null;
				if ( map2.containsKey("closeTime") ) {
					closeTime = Long.valueOf((Integer)map2.get("closeTime") + "") - 8 * hour;
				}
				if ( map2.containsKey("openTime") ) {
					openTime =  Long.valueOf((Integer)map2.get("openTime") + "") - 8 * hour;
				}
				String status = (String) map2.get("status");
				Integer weekDay = (Integer) map2.get("weekDay");
				SignageAuto signageAuto = new SignageAuto();
				signageAuto.setId(id);
				signageAuto.setRoomId(roomId);
				signageAuto.setWeekDay(weekDay);
				signageAuto.setStatus(status);
				signageAuto.setRoomId(roomId);
				if ( openTime != null ) {
					signageAuto.setOpenTime(new Date(openTime));
				}
				if ( closeTime != null ) {
					signageAuto.setCloseTime(new Date(closeTime));
				}
				if ( id == null ) {
					if ( signageAuto.getRoomId() == null || schoolId == null ) {
						ResponseInfo info = new ResponseInfo();
						info.setDetail("roomId为null或schoolId为null");
						info.setMsg("roomId为null或schoolId为null");
						return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL,info);
					}
					saveOrModifySignageAuto(signageAuto);
				} else {
					signageAuto.setId(id);
					signageAuto = this.signageAutoService.modify(signageAuto);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("接口异常");
			info.setMsg("接口异常");
			return new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR,info);
		}
		SignageAutoCondition signageAutoCondition = new SignageAutoCondition();
		signageAutoCondition.setRoomId(roomId);
		pushServiceByTeam(signageAutoCondition, schoolId,  roomId);
		return new ResponseVo<Boolean>("0", true);
	}
	
	@Override
	public Object createOrModifyBpSignageAuto(Integer id, Integer teamId, Integer schoolId, String content, 
			String appKey,  String signage) {
		Integer roomId = getRoomByTeamId(teamId);
		return this.createOrModifyBpSignageAutoByRoom1(teamId, roomId, schoolId, content, appKey, signage);
	}
	
	@Override
	public Object createOrModifyBpSignageAutoByRoom2(Integer id, Integer roomId, Integer schoolId, String content, 
			String appKey, String signage) {
		try {
			if ( roomId == null ) {
				return ResponseUtil.paramerIsNull("roomId:"+roomId);
			}
			Object checkResult = ValidateUtil.checkAppKeyAndSignae(appKey, signage, appEditionService, bpBwSignageService);
			if ( checkResult != null ) {
				return checkResult;
			}
			SignageAutoCondition condition = new SignageAutoCondition();
			condition.setRoomId(roomId);
			Order order = new Order();
			order.setProperty("week_day");
			order.setAscending(true);
			List<SignageAuto> signageAutoList = signageAutoService.findSignageAutoByCondition(condition, null, order);
			List<SignageAutoVo> signageAutoVoList = new ArrayList<>();
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
			for (SignageAuto signageAuto : signageAutoList) {
				SignageAutoVo sv = new SignageAutoVo();
				sv.setId(signageAuto.getId());
				sv.setRoomId(signageAuto.getRoomId());
				sv.setWeekDay(signageAuto.getWeekDay());
				sv.setStatus(signageAuto.getStatus());
				if ( signageAuto.getOpenTime() != null ) {
					sv.setOpenTime2(sdf.format(signageAuto.getOpenTime()));
				}
				if ( signageAuto.getCloseTime() != null ) {
					sv.setCloseTime2(sdf.format(signageAuto.getCloseTime()));
				}
				signageAutoVoList.add(sv);
			}
			List<Integer> list = new ArrayList<Integer>();
			list.add(0);
			list.add(1);
			list.add(2);
			list.add(3);
			list.add(4);
			list.add(5);
			list.add(6);
			for (SignageAuto signageAuto : signageAutoList) {
				list.remove(signageAuto.getWeekDay());
			}
			for (Integer weekDay : list) {
				SignageAutoVo signageAutoVo = new SignageAutoVo();
				signageAutoVo.setWeekDay(weekDay);
				signageAutoVo.setStatus("0");
				signageAutoVoList.add(signageAutoVo);
			}
			Long count = signageAutoService.count(condition);
			return new ResponseOrder("0", signageAutoVoList, count.intValue());
		} catch (Exception e) {
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("接口异常");
			info.setMsg("接口异常");
			return new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR,info);
		}
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public Object createOrModifyBpSignageAuto2(Integer id, Integer teamId, Integer schoolId, String content,
			String appKey, String signage) {	
		Object checkResult = ValidateUtil.checkAppKeyAndSignae(appKey, signage, appEditionService, bpBwSignageService);
		if ( checkResult != null ) {
			return checkResult;
		}
		Integer roomId = getRoomByTeamId(teamId);
		BpBwSignageCondition bwSignageCondition = new BpBwSignageCondition();
		bwSignageCondition.setRoomId(roomId);
		bwSignageCondition.setIsDeleted(false);
		List<BpBwSignage> signageList = this.bpBwSignageService.findBwSignageByCondition(bwSignageCondition);	
		if(signageList != null && signageList.size() > 0){
			for(BpBwSignage bpBwSignage: signageList){
				bpBwSignage.setPushSignageAuto(false);
				bpBwSignage.setPushSignageAutoTime(new Date());
				this.bpBwSignageService.modify(bpBwSignage);
			}
		}
		try {
			List<Map> parseArray = JSON.parseArray(content, Map.class);
			for (Map map2 : parseArray) {
				String closeTime2 = null;
				String openTime2 = null;
				if ( map2.containsKey("closeTime2") ) {
					closeTime2 = (String) map2.get("closeTime2");
				}
				if ( map2.containsKey("openTime2") ) {
					openTime2 =  (String) map2.get("openTime2");
				}
				String status = (String) map2.get("status");
				Integer weekDay = (Integer) map2.get("weekDay");
				SignageAuto signageAuto = new SignageAuto();
				signageAuto.setId(id);
				signageAuto.setRoomId(roomId);
				signageAuto.setWeekDay(weekDay);
				signageAuto.setStatus(status);
				signageAuto.setRoomId(roomId);
				SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
				if ( openTime2 != null ) {
					signageAuto.setOpenTime(sdf.parse(openTime2));
				}
				if ( closeTime2 != null ) {
					signageAuto.setCloseTime(sdf.parse(closeTime2));
				}
				if ( id == null ) {
					if ( signageAuto.getRoomId() == null || schoolId == null ) {
						ResponseInfo info = new ResponseInfo();
						info.setDetail("roomId为null或schoolId为null");
						info.setMsg("roomId为null或schoolId为null");
						return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL,info);
					}
					saveOrModifySignageAuto(signageAuto);
				} else {
					signageAuto.setId(id);
					signageAuto = this.signageAutoService.modify(signageAuto);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("接口异常");
			info.setMsg("接口异常");
			return new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR,info);
		}
		SignageAutoCondition signageAutoCondition = new SignageAutoCondition();
		signageAutoCondition.setRoomId(roomId);
		pushServiceByTeam(signageAutoCondition, schoolId,  roomId);
		return new ResponseVo<Boolean>("0", true);
	}

}
