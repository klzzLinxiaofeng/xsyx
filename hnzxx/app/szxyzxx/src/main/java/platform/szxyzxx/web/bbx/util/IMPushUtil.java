package platform.szxyzxx.web.bbx.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.core.task.TaskExecutor;

import platform.education.clazz.model.BpBwSignage;
import platform.education.clazz.model.RoomTeam;
import platform.education.clazz.model.TeamAccount;
import platform.education.clazz.service.BpBwSignageService;
import platform.education.clazz.service.RoomTeamService;
import platform.education.clazz.service.TeamAccountService;
import platform.education.clazz.vo.BpBwSignageCondition;
import platform.education.generalTeachingAffair.model.SchoolTermCurrent;
import platform.education.generalTeachingAffair.model.Team;
import platform.education.generalTeachingAffair.model.TeamUser;
import platform.education.generalTeachingAffair.service.SchoolTermCurrentService;
import platform.education.generalTeachingAffair.service.TeacherService;
import platform.education.generalTeachingAffair.service.TeamService;
import platform.education.generalTeachingAffair.service.TeamTeacherService;
import platform.education.generalTeachingAffair.service.TeamUserService;
import platform.education.generalTeachingAffair.vo.TeamTeacherCondition;
import platform.education.generalTeachingAffair.vo.TeamTeacherVo;
import platform.education.im.contants.OwnerAppKey;
import platform.education.im.dao.PushSubscriptionDao;
import platform.education.im.holder.IMServiceHolder;
import platform.education.im.model.ImAccount;
import platform.education.im.model.ImProvider;
import platform.education.im.model.PushData;
import platform.education.im.service.IMService;
import platform.education.im.service.ImAccountService;
import platform.education.im.service.ImProviderService;
import platform.education.im.vo.ImAccountCondition;
import platform.szxyzxx.web.bbx.contants.BpCommonContants;
import platform.szxyzxx.web.bbx.contants.DataAction;
import platform.szxyzxx.web.bbx.contants.RoleGroup;

/**
 * 
 * @author hmzhang 2016/07/30
 *
 */
public class IMPushUtil {
	
	/**
	 * 获取imService
	 */
	private static IMService imService = IMServiceHolder.getInstance().getImService();
	
	/**
	 * 根据角色的不同，推送消息
	 * 
	 * @param roleGroups @see {@link RoleGroup}
	 * @param teamId 班级的id
	 * @param data_action @see {@link DataAction}
	 * @param data_id 推送对象的实体表的ID（即服务器端对记录增删改后的ID）
	 * @param data_code 推送内容订阅编码
	 * @param data 推送内容
	 * @date 2016年07月30日
	 * @author hmzhang
	 */
	public static void push(final String roleGroups, final List<Integer> teamIds, final String data_action,final  Integer data_id, 
			final String data_code, final String data, final TeamUserService teamUserService, final TeamAccountService teamAccountService,
			final TeamTeacherService teamTeacherService, final TeacherService teacherService, final BpBwSignageService bpBwSignageService, 
			final SchoolTermCurrentService schoolTermCurrentService, final RoomTeamService roomTeamService, final TeamService teamService,
			TaskExecutor taskExecutor){
		
		taskExecutor.execute(new Runnable() {
	        public void run() {
	        	try {
	        		
	        		PushData pushData = null;
	        		String[] rgArray = null;
	        		if(roleGroups!=null && !"".equals(roleGroups)){
	        			rgArray = roleGroups.split(",");
	        			
	        		}
	        		 
	        		List<String> roleList = new ArrayList<String>();
	        		if(rgArray.length>0){
	        			roleList = Arrays.asList(rgArray);
	        			
	        		}
	        		
	        		if(roleList.size()>0){
	        			
	        			for (String rg : roleList) {
	        				if(RoleGroup.T$BBXACCOUNT.equals(rg)){//班级账号
	        					/**
	        					 * 实现逻辑
	        					 * 根据teamId，找到对应班级的teamAccount，获取bw_team_account表中的uuid字段
	        					 */
	        					//找到所有班级账号的uuid
	        					List<String>ownerIds = getTeamAccountUuids(teamIds, teamAccountService);
	        					//初始化数据
	        					pushData = initPushData(RoleGroup.T$BBXACCOUNT, data_action, data_id, data_code, data, ownerIds.toString());
	        					imService.pushToGroup(OwnerAppKey.BBX_PC_APPKEY, ownerIds, pushData);
	        				} else if(RoleGroup.T$PARENT.equals(rg)){//家长
	        					
	        					//找到所有家长的id
	        				    List<Integer>parentUserIds = getParentUserIds(teamIds, teamUserService);
	        					//初始化数据
	        			 		pushData = initPushData(RoleGroup.T$PARENT, data_action, data_id, data_code, data, "");
	        			 		//推送
	        			 		imService.push(parentUserIds, data_code, pushData);
	        			 	} else if(RoleGroup.T$STUDENT.equals(rg)){
	        			 		//找到所有学生的id
	        			 		List<Integer> studentUserIds = new ArrayList<Integer>();
	        			 		//初始化数据
	        			 		pushData = initPushData(RoleGroup.T$STUDENT, data_action, data_id, data_code, data, "");
	        			 		//推送
	        			 		imService.push(studentUserIds, data_code, pushData);
	        			 	} else if(RoleGroup.T$TEACHER.equals(rg)){
	        			 		//找到所有教师的id
	        			 		List<Integer> teacherUserIds = getTeacherUserIds(teamIds, teamTeacherService, teacherService);
	        			 		//初始化数据
	        			 		pushData = initPushData(RoleGroup.T$TEACHER, data_action, data_id, data_code, data, "");
	        					//推送
	        					imService.push(teacherUserIds, data_code, pushData);
	        				} else if(RoleGroup.T$BPACCOUNT.equals(rg)){
	        					//查找班级的所有设备
	        					List<String> signageList = 
	        							getSignageNamesOld(teamIds, bpBwSignageService);
	        					/*List<String> signageList = 
	        							getSignageNames(teamIds, bpBwSignageService, schoolTermCurrentService, roomTeamService, teamService);*/
	        					//初始化数据
	        					pushData = initPushData(RoleGroup.T$BPACCOUNT, data_action, data_id, data_code, data, "");
	        					//推送
	        					imService.pushToGroup(signageList, data_code, pushData);	        					
	        				}
	        			}
	        		}       		
	        	} catch (Exception e) {
	        		e.printStackTrace();
	        	}	
	        }
		});
		
		
	}
	
	/**
	 * 
	 * @param ids teamId或roomId
	 * @param type  判断teamId还是roomId (1:teamId , 2:roomId)
	 * @param data_action
	 * @param data_id
	 * @param data_code
	 * @param data
	 * @param bpBwSignageService
	 * @param taskExecutor
	 */
	public static void signagePush(final List<Integer> ids, final Integer type ,final String data_action,final  Integer data_id, 
			final String data_code, final String data,final BpBwSignageService bpBwSignageService,
			TaskExecutor taskExecutor){
		
		taskExecutor.execute(new Runnable() {
	        public void run() {
	        	try {
	        		
	        		PushData pushData = null;
	        		List<String> signageList = new ArrayList<String>();
	        		if(type.equals(1)){
	        			//teamId
	        			
	        			//查找班级的所有设备
	        			
						signageList = getSignageNamesOld(ids, bpBwSignageService);
	        		}else if(type.equals(2)){
	        			//roomId
	        			
	        			//查找班级的所有设备
	        			signageList = getSignageNamesOldByRoomId(ids, bpBwSignageService);
	        		}
	        		/*List<String> signageList = 
					getSignageNames(teamIds, bpBwSignageService, schoolTermCurrentService, roomTeamService, teamService);*/
					//初始化数据
					pushData = initPushData(RoleGroup.T$BPACCOUNT, data_action, data_id, data_code, data, "");
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
			final ImProviderService imProviderService,
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
					pushData = initPushData(RoleGroup.T$BPACCOUNT, data_action, data_id, data_code, data, "");
					//推送
					ImProvider imProvider = imProviderService.findDefaultProvider();
					if(imProvider.getImType().equals("3")){
//						Push.pushMessageByMsgBody(accountNameList,BpCommonContants.CLASSBRAND,data_code, pushData,pushSubscriptionDao);
					}else{
						imService.pushToGroup(accountNameList, data_code, pushData);
					}
	        	} catch (Exception e) {
	        		e.printStackTrace();
	        	}	
	        }
		});
		
		
	}
	
	
	
	/**
	 * 推送给指定的userIds
	 * @param data_action @see {@link DataAction}
	 * @param data_id 推送对象的实体表的ID（即服务器端对记录增删改后的ID）
	 * @param data_code 推送内容订阅编码
	 * @param data 推送内容
	 */
	public static void pushToUser(final String roleGroups, final List<Integer> userIds, final String data_action,final  Integer data_id, final String data_code, final String data,TaskExecutor taskExecutor){
		taskExecutor.execute(new Runnable() {
	        public void run() {
	        	try {
					PushData pushData = null;
					pushData = initPushData(null, data_action, data_id, data_code, data, "");
					//推送
					imService.push(userIds, data_code, pushData);
	        	}catch (Exception e) {
	        		e.printStackTrace();
	        	}	
	        }
		});
	}
	
	
	/**
	 * 初始化数据
	 * 
	 * @param groupRole @see RoleGroup
	 * @param data_action @see DataAction
	 * @param data_id 
	 * @param data_code
	 * @param data
	 * @return
	 * @date 2016年07月30日
	 * @author hmzhang
	 */
	private static PushData initPushData(String groupRole, String data_action, Integer data_id, String data_code, String data, String teamUuid){
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
		if(RoleGroup.T$BBXACCOUNT.equals(groupRole)){
			pushData = new PushData();
			pushData.setData(data);
			pushData.setData_action(data_action);
			pushData.setData_code(data_code);
			pushData.setData_id(String.valueOf(data_id));
			pushData.setReceiver_type("group");
			pushData.setOwner_id(teamUuid);
			pushData.setRole(groupRole);
		} else {
			pushData = new PushData();
			pushData.setData(data);
			pushData.setData_action(data_action);
			pushData.setData_code(data_code);
			pushData.setData_id(String.valueOf(data_id));
			pushData.setReceiver_type("user");
			pushData.setOwner_id(teamUuid);
			pushData.setRole(groupRole);
		}
		return pushData;
	}
	
	
	
	private static List<String> getTeamAccountUuids(List<Integer> teamIds,TeamAccountService teamAccountService){
		//所有已经开通的班级账号信息(已去重)
		List<String> list = new ArrayList<String>();
		//查找到的所有的已经开通的班级账号信息
		Map<Integer,String>teamAccountMaps = new HashMap();
		
		if(teamIds.size()>0){
			for(Integer tId : teamIds){
				/*根据班级id（teamId）查找到对应已经开通的所有的班级账号信息
				 * 并把对应已经开通的班级的uuid添加到对应的集合，作为要推送的条件之一
				 */
				TeamAccount ta = teamAccountService.findByTeamId(tId);
				if(ta!=null){
					if(ta.getTeamId()!=null){
						teamAccountMaps.put(ta.getId(), ta.getTeamUuid());
					}
					
				}
			}
		}
		
		if(teamAccountMaps.values().size()>0){
			list = new ArrayList(teamAccountMaps.values());
		}
		
//		for(Integer teamId : teamIds){
//			list.add(teamId+"");
//		}
		return list;
	}
	
	
	
	
	/**
	 * 根据要做推送的班级集合（teamIds）查找对应的所有的家长集合
	 * @param teamIds
	 * @param teamUserService
	 * @return
	 */
	private static List<Integer>getParentUserIds(List<Integer>teamIds,TeamUserService teamUserService){
		Map<Integer,Integer>parentUserMaps = new HashMap();
		if(teamIds.size()>0){
			for(Integer tId : teamIds){
				//根据teamId查找对应的家长userId集合
				List<TeamUser>tuList = teamUserService.findTeamUserOfParents(tId);
				if(tuList.size()>0){
					for(TeamUser tu :tuList){
						parentUserMaps.put(tu.getId(), tu.getUserId());
					}
				}
				
				
			    }
			}
		
		//需要做推送的家长的useId集合
		List<Integer> list = new ArrayList<Integer>();
		if(parentUserMaps.values().size()>0){
			list = new ArrayList(parentUserMaps.values());
			
		}
		
		
		return list;
	}
	
	
	
	
	
	
	/**
	 * 根据要做推送的班级集合（teamIds）查找对应的所有的教师集合
	 * @param teamIds
	 * @param teamTeacherService
	 * @param teacherService
	 * @return
	 */
	private static List<Integer> getTeacherUserIds(List<Integer> teamIds, TeamTeacherService teamTeacherService,TeacherService teacherService){
		//去重
		Map<Integer,Integer>teamTeacherMaps = new HashMap();
		//查找到的所有教师集合
		List<Integer> list = new ArrayList<Integer>();
		if(teamIds.size()>0){
			for(Integer tId :teamIds){
				TeamTeacherCondition condition = new TeamTeacherCondition();
				condition.setTeamId(tId);
				List<TeamTeacherVo> ttList = teamTeacherService.findTeamTeacherVoByCondition(condition);
				if(ttList.size()>0){
					for (TeamTeacherVo vo : ttList) {
						teamTeacherMaps.put(vo.getTeacherId(), vo.getUserId());
					}
				}
				
			}
		}
		
		
		if(teamTeacherMaps.values().size()>0){
			list = new ArrayList(teamTeacherMaps.values());
		}
		return list;
	}
	
	private static List<String> getSignageNamesOld(List<Integer> teamIds, BpBwSignageService bpBwSignageService){
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
