package platform.education.rest.bp.service.util;

import org.springframework.core.task.TaskExecutor;
import platform.education.clazz.model.BpBwSignage;
import platform.education.clazz.model.RoomTeam;
import platform.education.clazz.service.BpBwSignageService;
import platform.education.clazz.service.RoomTeamService;
import platform.education.clazz.vo.BpBwSignageCondition;
import platform.education.generalTeachingAffair.model.SchoolTermCurrent;
import platform.education.generalTeachingAffair.model.Team;
import platform.education.generalTeachingAffair.service.SchoolTermCurrentService;
import platform.education.generalTeachingAffair.service.TeamService;
import platform.education.im.dao.PushSubscriptionDao;
import platform.education.im.holder.IMServiceHolder;
import platform.education.im.model.ImAccount;
import platform.education.im.model.PushData;
import platform.education.im.service.IMService;
import platform.education.im.service.ImAccountService;
import platform.education.im.vo.ImAccountCondition;
import platform.education.rest.bp.service.contants.BpCommonConstants;
import platform.education.rest.util.ReciverType;

import java.util.ArrayList;
import java.util.List;

public class IMPushUtil {

	/**
	 * 获取imService
	 */
	private static IMService imService = IMServiceHolder.getInstance().getImService();
		
	public static void push1(final List<Integer> teamIds, final String data_action, final Integer data_id, final String data_code, final String data, 
			final BpBwSignageService bpBwSignageService, final SchoolTermCurrentService schoolTermCurrentService, final RoomTeamService roomTeamService, 
			final TeamService teamService,TaskExecutor taskExecutor){	
		taskExecutor.execute(new Runnable() {
	        public void run() {
	        	try {
	        		//查找班级的所有设备
					List<String> signageList = getSignageNames(teamIds, bpBwSignageService);
	        		/*List<String> signageList = getSignageNames(teamIds, bpBwSignageService, 
	        				schoolTermCurrentService, roomTeamService, teamService);*/
					//初始化数据
					PushData pushData = initPushData(data_action, data_id, data_code, data);
					//推送
					imService.pushToGroup(signageList, data_code, pushData);      		
	        	} catch (Exception e) {
	        		e.printStackTrace();
	        	}	
	        }
		});
	}
	
	
	public static void signagePush1(final List<Integer> ids, final Integer type ,final String data_action,final  Integer data_id, 
			final String data_code, final String data,final BpBwSignageService bpBwSignageService,TaskExecutor taskExecutor){	
		taskExecutor.execute(new Runnable() {
	        public void run() {
	        	try {
	        		//查找班级的所有设备
					List<String> signageList = new ArrayList<String>();
	        		if(type.equals(1)){
	        			//teamId
	        			
	        			//查找班级的所有设备
						signageList = getSignageNames(ids, bpBwSignageService);
	        		}else if(type.equals(2)){
	        			//roomId
	        			
	        			//查找班级的所有设备
						signageList = getSignageNamesOldByRoomId(ids, bpBwSignageService);
	        		}
	        		/*List<String> signageList = getSignageNames(teamIds, bpBwSignageService, 
	        				schoolTermCurrentService, roomTeamService, teamService);*/
					//初始化数据
					PushData pushData = initPushData(data_action, data_id, data_code, data);
					//推送
					imService.pushToGroup(signageList, data_code, pushData);      		
	        	} catch (Exception e) {
	        		e.printStackTrace();
	        	}	
	        }
		});
	}
	
	
	/**
	 * 新推送
	 * @param ids teamId或roomId
	 * @param type  判断teamId还是roomId (1:teamId , 2:roomId)
	 * @param data_action
	 * @param data_id
	 * @param data_code
	 * @param data
	 * @param bpBwSignageService
	 * @param taskExecutor
	 */
	public static void PushByXJXP(final List<Integer> ids, final Integer type ,final String data_action,final  Integer data_id, 
			final String data_code, final String data,final BpBwSignageService bpBwSignageService,final ImAccountService imAccountService,
			final PushSubscriptionDao pushSubscriptionDao,TaskExecutor taskExecutor){
		
		taskExecutor.execute(new Runnable() {
	        public void run() {
	        	try {
	        		
	        		PushData pushData = null;
	        		List<String> accountNameList = new ArrayList<String>();
	        		if(type.equals(1)){
	        			//teamId
	        			
	        			//查找班级的所有设备
	        			accountNameList = getAccountNamesOld(ids, imAccountService,bpBwSignageService);
	        		}else if(type.equals(2)){
	        			//roomId
	        			
	        			//查找班级的所有设备
	        			accountNameList = getAccountNamesOldByRoomId(ids,imAccountService,bpBwSignageService);
	        		}
	        		/*List<String> signageList = 
					getSignageNames(teamIds, bpBwSignageService, schoolTermCurrentService, roomTeamService, teamService);*/
					//初始化数据
	        		pushData = initPushData(data_action, data_id, data_code, data);
					//推送
					Push.pushMessageByMsgBody(accountNameList, BpCommonConstants.CLASSBRAND,data_code, pushData,pushSubscriptionDao);
//					imService.pushToGroup(accountNameList, data_code, pushData);
	        	} catch (Exception e) {
	        		e.printStackTrace();
	        	}	
	        }
		});
		
		
	}
	
	
	/**
	 * 通知推送
	 * 
	 * @param appKey 推送者的appKey
	 * @param userIds 推送的userId
	 * @param reciverType {@link ReciverType} 通知类型
	 * @param dataId 数据库操作id
	 * @param dataCode 模块标识
	 * @param data 推送内容
	 * @author hmzhang
	 * @date 2016-08-17
	 */
	public static void pushToUser(final List<Integer> userIds, final String data_action, final Integer data_id, 
			final String data_code, final String data, TaskExecutor taskExecutor){
		taskExecutor.execute(new Runnable() {
	        public void run() {
	        	try {
					//初始化数据
					PushData pushData = initPushData(data_action, data_id, data_code, data);
					//推送
					imService.push(userIds, data_code, pushData);      		
	        	} catch (Exception e) {
	        		e.printStackTrace();
	        	}	
	        }
		});
	}
	
	/**
	 * 初始化数据
	 * @param data_action
	 * @param data_id
	 * @param data_code
	 * @param data
	 * @return
	 */
	private static PushData initPushData(String data_action, Integer data_id, String data_code, String data){
		PushData pushData = null;
		if(data == null){
			data = "";
		}
		if(data_action == null){
			data_action = "";
		}
		if(data_id == null){
			data_id = 0;
		}
		pushData = new PushData();
		pushData.setReceiver_type("user");
		pushData.setData_id(String.valueOf(data_id));
		pushData.setData_action(data_action);
		//pushData.setRole(groupRole);
		pushData.setData_code(data_code);
		//pushData.setOwner_id(teamUuid);
		pushData.setData(data);
		return pushData;
	}
	
	
	private static List<String> getSignageNames(List<Integer> teamIds, BpBwSignageService bpBwSignageService){
		List<String> list = new ArrayList<String>();
		if(teamIds.size()>0){
			for(Integer tId :teamIds){
				BpBwSignageCondition bpBwSignageCondition = new BpBwSignageCondition();
				bpBwSignageCondition.setTeamId(tId);
				bpBwSignageCondition.setIsDeleted(false);
				List<BpBwSignage> signageList = bpBwSignageService.findBwSignageByCondition(bpBwSignageCondition);
				if(signageList != null && signageList.size() > 0){
					for(BpBwSignage s: signageList){
						list.add(s.getName());
					}
				}
			}
		}
		return list;
	}
	
	private static List<String> getSignageNamesOldByRoomId(List<Integer> roomIds, BpBwSignageService bpBwSignageService){
		List<String> list = new ArrayList<String>();
		if(roomIds.size()>0){
			for(Integer id :roomIds){
				BpBwSignageCondition bpBwSignageCondition = new BpBwSignageCondition();
				bpBwSignageCondition.setRoomId(id);
				bpBwSignageCondition.setIsDeleted(false);
				List<BpBwSignage> signageList = bpBwSignageService.findBwSignageByCondition(bpBwSignageCondition);
				if(signageList != null && signageList.size() > 0){
					for(BpBwSignage s: signageList){
						list.add(s.getName());
					}
				}
			}
		}
		return list;
	}
	
	//新推送
		private static List<String> getAccountNamesOldByRoomId(List<Integer> roomIds, ImAccountService imAccountService
				, BpBwSignageService bpBwSignageService){
			List<String> list = new ArrayList<String>();
			if(roomIds.size()>0){
				for(Integer id :roomIds){
					BpBwSignageCondition bpBwSignageCondition = new BpBwSignageCondition();
					bpBwSignageCondition.setRoomId(id);
					bpBwSignageCondition.setIsDeleted(false);
					List<BpBwSignage> signageList = bpBwSignageService.findBwSignageByCondition(bpBwSignageCondition);
					if(signageList != null && signageList.size() > 0){
						for(BpBwSignage s: signageList){
							ImAccountCondition condition = new ImAccountCondition();
							condition.setOwnerId(s.getName());
							List<ImAccount> accountList = imAccountService.findImAccountByCondition(condition);
							for(ImAccount imAccount:accountList){
								list.add(imAccount.getAccountName());
							}
						}
					}
				}
			}
			return list;
		}
		
		//新推送
		private static List<String> getAccountNamesOld(List<Integer> teamIds, ImAccountService imAccountService,
				BpBwSignageService bpBwSignageService){
			List<String> list = new ArrayList<String>();
			if(teamIds.size()>0){
				for(Integer tId :teamIds){
					BpBwSignageCondition bpBwSignageCondition = new BpBwSignageCondition();
					bpBwSignageCondition.setTeamId(tId);
					bpBwSignageCondition.setIsDeleted(false);
					List<BpBwSignage> signageList = bpBwSignageService.findBwSignageByCondition(bpBwSignageCondition);
					if(signageList != null && signageList.size() > 0){
						for(BpBwSignage s: signageList){
							ImAccountCondition condition = new ImAccountCondition();
							condition.setOwnerId(s.getName());
							List<ImAccount> accountList = imAccountService.findImAccountByCondition(condition);
							for(ImAccount imAccount:accountList){
								list.add(imAccount.getAccountName());
							}
						}
					}
				}
			}
			return list;
		}
	
	
	
	private static List<String> getSignageNames(List<Integer> teamIds, BpBwSignageService bpBwSignageService, 
			SchoolTermCurrentService schoolTermCurrentService, RoomTeamService roomTeamService, TeamService teamService){
		List<String> list = new ArrayList<String>();	
		if(teamIds.size()>0){
			for(Integer tId :teamIds){		
				Team team = teamService.findTeamById(tId);
				SchoolTermCurrent schoolTermCurrent = schoolTermCurrentService.findSchoolTermCurrentBySchoolId(team.getSchoolId());
				RoomTeam roomTeam = roomTeamService.findRoomTeamByTeamId(tId, schoolTermCurrent.getSchoolYear());
				if(roomTeam != null){
					Integer roomId = roomTeam.getRoomId();
					BpBwSignageCondition bpBwSignageCondition = new BpBwSignageCondition();
					//bpBwSignageCondition.setTeamId(tId);
					bpBwSignageCondition.setRoomId(roomId);
					bpBwSignageCondition.setIsDeleted(false);
					List<BpBwSignage> signageList = bpBwSignageService.findBwSignageByCondition(bpBwSignageCondition);
					if(signageList != null && signageList.size() > 0){
						for(BpBwSignage s: signageList){
							list.add(s.getName());
						}
					}
				}
			}
		}
		return list;
	}
		
}
