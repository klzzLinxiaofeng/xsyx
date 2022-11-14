package platform.education.rest.jw.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import platform.education.learningDesign.model.*;
import platform.education.learningDesign.service.*;
import platform.education.learningDesign.vo.LpTaskUserActivityCondition;
import platform.education.learningDesign.vo.LpTaskUserActivityVo;
import platform.education.learningDesign.vo.LpTaskUserCondition;
import platform.education.rest.common.ResponseError;
import platform.education.rest.common.ResponseInfo;
import platform.education.rest.common.ResponseVo;
import platform.education.rest.common.constants.CommonCode;
import platform.education.rest.jw.service.GroupSummarizeService;
import platform.education.rest.util.ImgUtil;
import platform.education.user.model.AppEdition;
import platform.education.user.service.AppEditionService;
import platform.education.user.service.ProfileService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupSummarizeServiceImpl implements GroupSummarizeService{

	@Autowired
	@Qualifier("appEditionService")
	private AppEditionService appEditionService;

	@Autowired
	@Qualifier("lpTaskUserActivityService")
	private LpTaskUserActivityService lpTaskUserActivityService;

    @Autowired
    @Qualifier("lpTaskUnitUserService")
    private LpTaskUnitUserService lpTaskUnitUserService;

	@Autowired
	@Qualifier("lpTaskUserService")
	private LpTaskUserService lpTaskUserService;

	@Autowired
	@Qualifier("lpTaskService")
	private LpTaskService lpTaskService;

	@Autowired
	@Qualifier("profileService")
	private ProfileService profileService;
	@Override
	public Object addSummarize(String appKey, Integer userId, Integer taskId, Integer unitId, String content, String[] files) {
		AppEdition app = this.appEditionService.findByAppKey(appKey);
		if(app == null){
			ResponseInfo info = new ResponseInfo();
			info.setDetail("appkey不存在,请确认");
			info.setMsg("不存在该appKey");
			info.setParam("appKey");
			return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
		}

		LpTask task = lpTaskService.findLpTaskById(taskId);
//		LpUnit unit = lpUnitService.findLpUnitById(unitId);
		if(task!=null) {
			try {
				LpTaskUserActivity taskUserActivity = new LpTaskUserActivity();
				taskUserActivity.setDegree(0);
				taskUserActivity.setIsDeleted(false);
				taskUserActivity.setLpId(task.getLpId());
				taskUserActivity.setTaskId(task.getId());
				taskUserActivity.setUnitId(unitId);
				taskUserActivity.setUserId(userId);
				if(files != null && files.length>0){
				String fileStr = "";//String.join(",",files);
					for(String file : files){
						fileStr+=file+",";
					}
					taskUserActivity.setFiles(fileStr);
				}
				taskUserActivity.setContent(content);
//				taskUserActivity.setQuote(quote);
				taskUserActivity = lpTaskUserActivityService.add(taskUserActivity);

				if(taskUserActivity != null) {
				    //获取组员
                    LpTaskUserCondition userCondition = new LpTaskUserCondition();
                    userCondition.setTaskId(taskId);
                    userCondition.setUserId(userId);
                    userCondition.setIsDeleted(false);
                    List<LpTaskUser> users = lpTaskUserService.findLpTaskUserByCondition(userCondition);
                    userCondition.setUserId(null);
                    userCondition.setGroupNumber(users.get(0).getGroupNumber());
                    users = lpTaskUserService.findLpTaskUserByCondition(userCondition);

                    //更新所有组员的单元完成信息
                    for(LpTaskUser user : users) {
                        List<LpTaskUnitUser> lpTaskUnitUserList = lpTaskUnitUserService.findLpTaskUnitUserByCondition(taskId, unitId, user.getUserId());
                        if (lpTaskUnitUserList.size() > 0) {
                            lpTaskUnitUserService.updateFinishState(lpTaskUnitUserList.get(0).getId(),
                                    new SimpleDateFormat("yyyy-MM-dd HH:mm").format(taskUserActivity.getCreateDate()));
                        }
                    }
                }
			}catch (Exception e) {
				e.printStackTrace();
				ResponseInfo info = new ResponseInfo();
				info.setDetail("数据录入失败，请检查数据格式及长度");
				info.setMsg("发送失败");
				return new ResponseError(CommonCode.D$DB_OPERATION_EXCEPTION, info);
			}
		}else{
			ResponseInfo info = new ResponseInfo();
			info.setDetail("无法获取指定的任务信息,相关记录可能被删除");
			info.setMsg("发送失败");
			info.setParam("taskId");
			return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
		}
		Map map = new HashMap();
		map.put("success", true);
		return new ResponseVo<>("0", map);
	}

	@Override
	public Object findSummarize(String appKey, Integer taskId, Integer unitId) {
		AppEdition app = this.appEditionService.findByAppKey(appKey);
		if(app == null){
			ResponseInfo info = new ResponseInfo();
			info.setDetail("appkey不存在,请确认");
			info.setMsg("不存在该appKey");
			info.setParam("appKey");
			return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
		}
		List<Map> resultList = new ArrayList<>();
//		Map<String, Object> result = new HashMap<>();
		LpTaskUserActivityCondition lpTaskUserActivityCondition = new LpTaskUserActivityCondition();
		lpTaskUserActivityCondition.setTaskId(taskId);
		lpTaskUserActivityCondition.setUnitId(unitId);
		lpTaskUserActivityCondition.setIsDeleted(false);
		List<LpTaskUserActivity> items = lpTaskUserActivityService.findLpTaskUserActivityByCondition(lpTaskUserActivityCondition);
//		result.put("summarize", items);

		LpTaskUserCondition userCondition = new LpTaskUserCondition();
		userCondition.setTaskId(taskId);
		userCondition.setIsDeleted(false);
		List<LpTaskUser> lpTaskUsers = lpTaskUserService.findLpTaskUserByCondition(userCondition);//根据taskId从表lp_task_user获取所有成员

		List[] listArray = new List[10];
		for(int i = 0; i<listArray.length; i++){
			listArray[i] = new ArrayList<Map>();
		}

		Map<Integer, List> groupMap = new HashMap<>();
		List<Integer> groupLeaders = new ArrayList<>();
		for(LpTaskUser user : lpTaskUsers){
		    if(user.getGroupNumber()==null) continue;
			String iocnPath = ImgUtil.getImgSrc(user.getUserId(), profileService);
			Map<String, Object> userMap = new HashMap<>();
			userMap.put("user", user);
			userMap.put("iocnPath", iocnPath);
			listArray[user.getGroupNumber() - 1].add(userMap);//将小组成员存入对应的List中
			if(user.getIsGroupLeader()==1){
				groupMap.put(user.getUserId(), listArray[user.getGroupNumber() - 1]);
				groupLeaders.add(user.getUserId());
			}
		}

		for(LpTaskUserActivity userActivity : items){
			Map<String, Object> result = new HashMap<>();
			if(groupMap.get(userActivity.getUserId()) == null || groupMap.get(userActivity.getUserId()).size() == 0) continue;
			result.put("summarize", getLpTaskUserActivityVoWithFilePath(userActivity));
			result.put("group", groupMap.get(userActivity.getUserId()));
//			groupMap.remove(userActivity.getUserId());
			resultList.add(result);
		}

		Map toReturn = new HashMap();
		toReturn.put("groupLeaders", groupLeaders);
		toReturn.put("summarizes", resultList);


		return new ResponseVo<>("0", toReturn);
	}

	@Override
	public Object findSummarizeByGroupNumber(String appKey, Integer unitId, Integer groupNumber, Integer taskId, Integer userId) {
		AppEdition app = this.appEditionService.findByAppKey(appKey);
		if(app == null){
			ResponseInfo info = new ResponseInfo();
			info.setDetail("appkey不存在,请确认");
			info.setMsg("不存在该appKey");
			info.setParam("appKey");
			return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
		}
		if(groupNumber==-1){
			LpTaskUserCondition userCondition = new LpTaskUserCondition();
			userCondition.setTaskId(taskId);
			userCondition.setUserId(userId);
			userCondition.setIsDeleted(false);
			List<LpTaskUser> users = lpTaskUserService.findLpTaskUserByCondition(userCondition);//根据taskId从表lp_task_user获取所有成员
			if(users.size()>0)
				groupNumber = users.get(0).getGroupNumber();
		}

		LpTaskUserCondition userCondition = new LpTaskUserCondition();
		userCondition.setGroupNumber(groupNumber);
		userCondition.setIsGroupLeader(1);
		userCondition.setIsDeleted(false);
		LpTaskUser leader = lpTaskUserService.findLpTaskUserByCondition(userCondition).get(0);

		LpTaskUserActivityCondition lpTaskUserActivityCondition = new LpTaskUserActivityCondition();
		lpTaskUserActivityCondition.setTaskId(taskId);
		lpTaskUserActivityCondition.setUnitId(unitId);
		lpTaskUserActivityCondition.setIsDeleted(false);
		lpTaskUserActivityCondition.setUserId(leader.getUserId());
		List<LpTaskUserActivity> userActivities = lpTaskUserActivityService.findLpTaskUserActivityByCondition(lpTaskUserActivityCondition);

		List<LpTaskUserActivityVo> items = new ArrayList<>();
		for(LpTaskUserActivity activity : userActivities){
			LpTaskUserActivityVo vo = getLpTaskUserActivityVoWithFilePath(activity);
			vo.setName(leader.getName());
			items.add(vo);
		}
		Map<String, Object> map = new HashMap();
		map.put("data", items);

		return  new ResponseVo<>("0", map);
	}

	@Override
	public Object deleteSummarize(String appKey, Integer userActivityId) {
		AppEdition app = this.appEditionService.findByAppKey(appKey);
		if(app == null){
			ResponseInfo info = new ResponseInfo();
			info.setDetail("appkey不存在,请确认");
			info.setMsg("不存在该appKey");
			info.setParam("appKey");
			return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
		}
		LpTaskUserActivity userActivity = new LpTaskUserActivity();

		if(userActivityId!=null){
			userActivity.setId(userActivityId);
			userActivity.setIsDeleted(true);

			lpTaskUserActivityService.modify(userActivity);

			Map map = new HashMap();
			map.put("success", true);

			return new ResponseVo<>("0", map);
		}else {
			ResponseInfo info = new ResponseInfo();
			info.setDetail("请输入要删除的信息的ID");
			info.setMsg("缺少必要参数");
			info.setParam("userActivityId");
			return new ResponseError(CommonCode.S$INVALID_DATA, info);
		}
	}

	private LpTaskUserActivityVo getLpTaskUserActivityVoWithFilePath(LpTaskUserActivity activity){
		LpTaskUserActivityVo vo = new LpTaskUserActivityVo();
		vo.setId(activity.getId());
		vo.setUserId(activity.getUserId());
		vo.setUnitId(activity.getUnitId());
		vo.setTaskId(activity.getTaskId());
		vo.setLpId(activity.getLpId());
		vo.setDegree(activity.getDegree());
		vo.setContent(activity.getContent());
		vo.setCreateDate(activity.getCreateDate());

		String files = activity.getFiles();
		if(files!=null && !"".equals(files)) {
			List<String> filePath = new ArrayList<>();
			String[] uuids = files.split(",");
			for(String uuid : uuids){
			    if(uuid.length()>2) {
                    String path = ImgUtil.getImgUrl(uuid.substring(1, uuid.length() - 1));
                    filePath.add(path);
                }
			}
			vo.setFilePath(filePath);
		}
		return vo;
	}

}
