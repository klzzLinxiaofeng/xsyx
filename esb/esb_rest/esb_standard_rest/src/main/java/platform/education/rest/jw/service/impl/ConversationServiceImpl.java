package platform.education.rest.jw.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import platform.education.generalTeachingAffair.model.SchoolUser;
import platform.education.generalTeachingAffair.model.Student;
import platform.education.generalTeachingAffair.service.SchoolUserService;
import platform.education.generalTeachingAffair.service.StudentService;
import platform.education.generalTeachingAffair.service.TeacherService;
import platform.education.learningDesign.model.LearningPlan;
import platform.education.learningDesign.model.LpTaskUnitUser;
import platform.education.learningDesign.model.LpTaskUserActivity;
import platform.education.learningDesign.service.*;
import platform.education.learningDesign.vo.LpTaskUserActivityCondition;
import platform.education.rest.jw.service.vo.LpTaskUserActivityVo;
import platform.education.rest.common.ResponseError;
import platform.education.rest.common.ResponseInfo;
import platform.education.rest.common.ResponseVo;
import platform.education.rest.common.constants.CommonCode;
import platform.education.rest.jw.service.ConversationService;
import platform.education.rest.jw.service.vo.LpTaskUserActivityInfo;
import platform.education.rest.jw.service.vo.LpTaskUserActivityJson;
import platform.education.rest.util.ImgUtil;
import platform.education.user.model.AppEdition;
import platform.education.user.service.AppEditionService;
import platform.education.user.service.ProfileService;
import platform.service.storage.model.EntityFile;
import platform.service.storage.service.EntityFileService;
import platform.service.storage.service.FileService;

public class ConversationServiceImpl implements ConversationService{
	
	@javax.annotation.Resource
	private TeacherService teacherService;
	
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
	@Qualifier("lpTaskGroupService")
	private LpTaskGroupService lpTaskGroupService;
	
	@Autowired
	@Qualifier("lpTaskUserService")
	private LpTaskUserService lpTaskUserService;
	
	@Autowired
	@Qualifier("profileService")
	private ProfileService profileService;
	
	@Autowired
	@Qualifier("schoolUserService")
	private SchoolUserService schoolUserService;
	
	@Autowired
    @Qualifier("studentService")
	private StudentService studentService;
	
	@Autowired
	@Qualifier("learningPlanService")
	private LearningPlanService learningPlanService;
	
	@Autowired
	@Qualifier("entityFileService")
	private EntityFileService entityFileService;
	
	@Autowired
	@Qualifier("fileService")
	private FileService fileService;
	
	@Override
	public Object addConversation(String appKey, Integer lpId, Integer taskId, Integer unitId, Integer userId,
			String content, String files, Integer degree, String quote) {
		//保存到lp_task_user_activity表中
		try {
			AppEdition app = this.appEditionService.findByAppKey(appKey);
			if(app == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("appkey不存在,请确认");
				info.setMsg("不存在该appKey");
				info.setParam("appKey");
				return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
			}
			
			LpTaskUserActivity lpTaskUserActivity = new LpTaskUserActivity();
			lpTaskUserActivity.setLpId(lpId);
			lpTaskUserActivity.setTaskId(taskId);
			lpTaskUserActivity.setUnitId(unitId);
			lpTaskUserActivity.setUserId(userId);
			lpTaskUserActivity.setIsDeleted(false);
			if(content != null && !content.equals("")) {
				lpTaskUserActivity.setContent(content);
			}
			if(files != null && !files.equals("")) {
				String file = files.substring(1, files.length()-1);
				String file1[] = file.split(",");
				String file2 = "";
				for(int i=0; i<file1.length; i++) {
					file1[i] = file1[i].substring(1, file1[i].length()-1);
					if(i == file1.length-1) {
						file2 += file1[i];
					}else {
						file2 += file1[i] + ",";
					}
				}
				lpTaskUserActivity.setFiles(file2);
			}
			if(degree != null && degree != 0) {
				lpTaskUserActivity.setDegree(degree);
			}
			lpTaskUserActivity.setQuote(quote);
			LpTaskUserActivity l = lpTaskUserActivityService.add(lpTaskUserActivity);

			List<LpTaskUnitUser> lpTaskUnitUserList = lpTaskUnitUserService.findLpTaskUnitUserByCondition(taskId, unitId, userId);
			if(lpTaskUnitUserList.size()>0){
				lpTaskUnitUserService.updateFinishState(lpTaskUnitUserList.get(0).getId(),
						new SimpleDateFormat("yyyy-MM-dd HH:mm").format(l.getCreateDate()));
			}
			/** 好像师生是同个表，先注释掉 */
//			else {
//				LpTaskUnitUser user = new LpTaskUnitUser();
//				user.setLpId(lpId);
//				user.setTaskId(taskId);
//				user.setUnitId(unitId);
//				user.setUserId(userId);
//				user.setFinishTime(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(l.getCreateDate()));
//				user.setHasFinished(true);
//				user.setIsDeleted(false);
//				lpTaskUnitUserService.add(user);
//			}
			return new ResponseVo<LpTaskUserActivity>("0",l);
		} catch (Exception e) {
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("存入异常");
			info.setMsg("请检查参数是否有误");
			return new ResponseError(CommonCode.S$ABNORMAL_OPERATION, info);
		}
	}

	@Override
	public Object findConversation(String appKey, Integer lpId, Integer taskId, Integer unitId) {
		LearningPlan lp = learningPlanService.findLearningPlanById(lpId);
		Integer schoolId = lp.getOwnerId();//导学案的ownerId为schoolId
		//获取所有的讨论内容
		try {
			AppEdition app = this.appEditionService.findByAppKey(appKey);
			if(app == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("appkey不存在,请确认");
				info.setMsg("不存在该appKey");
				info.setParam("appKey");
				return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
			}
			
			LpTaskUserActivityCondition lpTaskUserActivityCondition = new LpTaskUserActivityCondition();
			lpTaskUserActivityCondition.setLpId(lpId);
			lpTaskUserActivityCondition.setTaskId(taskId);
			lpTaskUserActivityCondition.setUnitId(unitId);
			lpTaskUserActivityCondition.setIsDeleted(false);
			List<LpTaskUserActivity> l = lpTaskUserActivityService.findLpTaskUserActivityByCondition(lpTaskUserActivityCondition);
			
			Map<String, Object> conversationList = new HashMap<String, Object>();
			
			List<LpTaskUserActivityJson> jsonList = new ArrayList<LpTaskUserActivityJson>();
			
			for(LpTaskUserActivity lpTaskUserActivity : l) {
				//返回带有引用内容的json
//				List<LpTaskUserActivityInfo> data = new ArrayList<LpTaskUserActivityInfo>();
				//获取引用的讨论内容，放入新的list中
				LpTaskUserActivityInfo lpTaskUserActivityInfo = new LpTaskUserActivityInfo();
				String quotes = lpTaskUserActivity.getQuote();
				if(quotes != null) {
					String quote[] = quotes.split(",");
					
//					List<LpTaskUserActivityVo> quoteList = new ArrayList<>();
//					for(String q : quote) {
//						LpTaskUserActivityVo vo = new LpTaskUserActivityVo();
//						LpTaskUserActivity lt = lpTaskUserActivityService.findLpTaskUserActivityById(Integer.parseInt(q));
//						String name = "";
//						Student st = studentService.findOfUser(schoolId, lt.getUserId());
//						if(st!=null) {
//							name = st.getName();
//						}else {
//							name = teacherService.findOfUser(schoolId, lt.getUserId()).getName();
//						}
//						vo.setName(name);
//						
//						String files = lpTaskUserActivity.getFiles();
//						List<String> filePath = new ArrayList<String>();
//						if(files != null && !files.equals("")) {
//							String filesStr = files.substring(1, files.length()-1);
//							String filesSet[] = filesStr.split(",");
//							for(int j=0;j<filesSet.length;j++) {
//								EntityFile ef = entityFileService.findFileByUUID(filesSet[j]);
//								if(ef != null) {
//									filePath.add(ef.getRelativePath());
//								}
//							}
//						}
//						vo.setFiles(filePath);
//						
//						BeanUtils.copyProperties(lt, vo);
//						quoteList.add(vo);
//					}
					List<LpTaskUserActivityInfo> quoteList = new ArrayList<>();
					quoteList = loopQuote(quote, lpTaskUserActivity, schoolId);
					/**
					 * 对返回的结果做时间排序
					 */
					Collections.sort(quoteList, new Comparator<LpTaskUserActivityInfo>(){
						public int compare(LpTaskUserActivityInfo info1, LpTaskUserActivityInfo info2) {
							Long date1 = info1.getCreateDate().getTime();
							Long date2 = info2.getCreateDate().getTime();
							int flag = date1.compareTo(date2);
							return flag;
						}
					});
					lpTaskUserActivityInfo.setQuoteList(quoteList);
//					lpTaskUserActivityInfo.setQuoteList(quoteList);
				}
				
				String files = lpTaskUserActivity.getFiles();
				List<String> filePath = new ArrayList<String>();
				if(files != null && !files.equals("")) {
//					String filesStr = files.substring(1, files.length()-1);
					String filesSet[] = files.split(",");
					for(int j=0;j<filesSet.length;j++) {
//						EntityFile ef = entityFileService.findFileByUUID(filesSet[j]);
//						if(ef != null) {
//							filePath.add(ef.getRelativePath());
//						}
						String path = fileService.relativePath2HttpUrlByUUID(filesSet[j]);
						if(path != null && !"".equals(path)) {
							filePath.add(path);
						}
					}
				}
				lpTaskUserActivityInfo.setFilePath(filePath);
				
				String iocnPath = ImgUtil.getImgSrc(lpTaskUserActivity.getUserId(), profileService);
				SchoolUser su =schoolUserService.findSchoolUserByUserId(lpTaskUserActivity.getUserId());
				String role = "";
				if(su.getUserType().equals("1")) {
					role = "教师";
				}else if(su.getUserType().equals("2")) {
					role = "admin";
				}else if(su.getUserType().equals("3")){
					role = "家长";
				}else if(su.getUserType().equals("4")) {
					role = "学生";
				}
				String name = su.getName();
				lpTaskUserActivityInfo.setIocnPath(iocnPath);
				lpTaskUserActivityInfo.setRole(role);
				lpTaskUserActivityInfo.setName(name);
				
				BeanUtils.copyProperties(lpTaskUserActivity, lpTaskUserActivityInfo);
				
				LpTaskUserActivityJson json = new LpTaskUserActivityJson();
				json.setId(lpTaskUserActivityInfo.getId());
				json.setLpId(lpTaskUserActivityInfo.getLpId());
				json.setTaskId(lpTaskUserActivityInfo.getTaskId());
				json.setUnitId(lpTaskUserActivityInfo.getUnitId());
				json.setUserId(lpTaskUserActivityInfo.getUserId());
				json.setContent(lpTaskUserActivityInfo.getContent());
				json.setFiles(lpTaskUserActivityInfo.getFilePath());
				json.setDegree(lpTaskUserActivityInfo.getDegree());
				json.setCreateDate(lpTaskUserActivityInfo.getCreateDate());
				json.setModifyDate(lpTaskUserActivityInfo.getModifyDate());
				json.setIsDeleted(lpTaskUserActivityInfo.getIsDeleted());
				json.setQuote(lpTaskUserActivityInfo.getQuote());
				json.setQuoteList(lpTaskUserActivityInfo.getQuoteList());
				json.setIocnPath(lpTaskUserActivityInfo.getIocnPath());
				json.setRole(lpTaskUserActivityInfo.getRole());
				json.setName(lpTaskUserActivityInfo.getName());
				
				jsonList.add(json);
			}
			conversationList.put("conversationList", jsonList);
			
			return new ResponseVo<Map<String, Object>>("0",conversationList);//返回需要修改
		} catch (Exception e) {
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("获取所有讨论内容异常");
			info.setMsg("请检查参数是否有误");
			return new ResponseError(CommonCode.S$ABNORMAL_OPERATION, info);
		}
	}
	
	@Override
	public Object findConversationByUserId(String appKey, Integer lpId, Integer taskId, Integer unitId,
			Integer userId) {
		LearningPlan lp = learningPlanService.findLearningPlanById(lpId);
		Integer schoolId = lp.getOwnerId();//导学案的ownerId为schoolId
		//获取某个用户的讨论内容
		try {
			AppEdition app = this.appEditionService.findByAppKey(appKey);
			if(app == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("appkey不存在,请确认");
				info.setMsg("不存在该appKey");
				info.setParam("appKey");
				return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
			}
			
			LpTaskUserActivityCondition lpTaskUserActivityCondition = new LpTaskUserActivityCondition();
			lpTaskUserActivityCondition.setLpId(lpId);
			lpTaskUserActivityCondition.setTaskId(taskId);
			lpTaskUserActivityCondition.setUnitId(unitId);
			lpTaskUserActivityCondition.setUserId(userId);
			lpTaskUserActivityCondition.setIsDeleted(false);
			List<LpTaskUserActivity> l = lpTaskUserActivityService.findLpTaskUserActivityByCondition(lpTaskUserActivityCondition);
			
			Map<String, Object> conversationList = new HashMap<String, Object>();
			
			List<LpTaskUserActivityJson> jsonList = new ArrayList<LpTaskUserActivityJson>();
			
			for(LpTaskUserActivity lpTaskUserActivity : l) {//主文章
				//返回带有引用内容的json
//				List<LpTaskUserActivityInfo> data = new ArrayList<LpTaskUserActivityInfo>();
				//获取引用的讨论内容，放入新的list中
				LpTaskUserActivityInfo lpTaskUserActivityInfo = new LpTaskUserActivityInfo();
				String quotes = lpTaskUserActivity.getQuote();
				if(quotes != null) {
					String quote[] = quotes.split(",");
					
					//获取引用
//					List<LpTaskUserActivityVo> quoteList = new ArrayList<>();
//					for(String q : quote) {
//						LpTaskUserActivityVo vo = new LpTaskUserActivityVo();
//						LpTaskUserActivity lt = lpTaskUserActivityService.findLpTaskUserActivityById(Integer.parseInt(q));
						
//						String name = "";
//						Student st = studentService.findOfUser(schoolId, lt.getUserId());
//						if(st!=null) {
//							name = st.getName();
//						}else {
//							name = teacherService.findOfUser(schoolId, lt.getUserId()).getName();
//						}
//						vo.setName(name);
//						
//						String files = lpTaskUserActivity.getFiles();
//						List<String> filePath = new ArrayList<String>();
//						if(files != null && !files.equals("")) {//
//							String filesStr = files.substring(1, files.length()-1);
//							String filesSet[] = filesStr.split(",");
//							for(int j=0;j<filesSet.length;j++) {
//								EntityFile ef = entityFileService.findFileByUUID(filesSet[j]);
//								if(ef != null) {
//									filePath.add(ef.getRelativePath());
//								}
//							}
//						}
//						vo.setFiles(filePath);
//						BeanUtils.copyProperties(lt, vo);
//						quoteList.add(vo);
//					}
					
					List<LpTaskUserActivityInfo> quoteList = new ArrayList<>();
					quoteList = loopQuote(quote, lpTaskUserActivity, schoolId);
					/**
					 * 对返回的结果做时间排序
					 */
					Collections.sort(quoteList, new Comparator<LpTaskUserActivityInfo>(){
						public int compare(LpTaskUserActivityInfo info1, LpTaskUserActivityInfo info2) {
							Long date1 = info1.getCreateDate().getTime();
							Long date2 = info2.getCreateDate().getTime();
							int flag = date1.compareTo(date2);
							return flag;
						}
					});
					lpTaskUserActivityInfo.setQuoteList(quoteList);
				}
				
				String files = lpTaskUserActivity.getFiles();
				List<String> filePath = new ArrayList<String>();
				if(files != null && !files.equals("")) {
//					String filesStr = files.substring(1, files.length()-1);
					String filesSet[] = files.split(",");
					for(int j=0;j<filesSet.length;j++) {
//						EntityFile ef = entityFileService.findFileByUUID(filesSet[j]);
//						if(ef != null) {
//							filePath.add(ef.getRelativePath());
//						}
						String path = fileService.relativePath2HttpUrlByUUID(filesSet[j]);
						if(path != null && !"".equals(path)) {
							filePath.add(path);
						}
					}
				}
				lpTaskUserActivityInfo.setFilePath(filePath);
				
				String iocnPath = ImgUtil.getImgSrc(lpTaskUserActivity.getUserId(), profileService);
				SchoolUser su =schoolUserService.findSchoolUserByUserId(lpTaskUserActivity.getUserId());
				String role = "";
				if(su.getUserType().equals("1")) {
					role = "教师";
				}else if(su.getUserType().equals("2")) {
					role = "admin";
				}else if(su.getUserType().equals("3")){
					role = "家长";
				}else if(su.getUserType().equals("4")) {
					role = "学生";
				}
				String name = su.getName();
				lpTaskUserActivityInfo.setIocnPath(iocnPath);
				lpTaskUserActivityInfo.setRole(role);
				lpTaskUserActivityInfo.setName(name);
				
				BeanUtils.copyProperties(lpTaskUserActivity, lpTaskUserActivityInfo);
				
				LpTaskUserActivityJson json = new LpTaskUserActivityJson();
				json.setId(lpTaskUserActivityInfo.getId());
				json.setLpId(lpTaskUserActivityInfo.getLpId());
				json.setTaskId(lpTaskUserActivityInfo.getTaskId());
				json.setUnitId(lpTaskUserActivityInfo.getUnitId());
				json.setUserId(lpTaskUserActivityInfo.getUserId());
				json.setContent(lpTaskUserActivityInfo.getContent());
				json.setFiles(lpTaskUserActivityInfo.getFilePath());
				json.setDegree(lpTaskUserActivityInfo.getDegree());
				json.setCreateDate(lpTaskUserActivityInfo.getCreateDate());
				json.setModifyDate(lpTaskUserActivityInfo.getModifyDate());
				json.setIsDeleted(lpTaskUserActivityInfo.getIsDeleted());
				json.setQuote(lpTaskUserActivityInfo.getQuote());
				json.setQuoteList(lpTaskUserActivityInfo.getQuoteList());
				json.setIocnPath(lpTaskUserActivityInfo.getIocnPath());
				json.setRole(lpTaskUserActivityInfo.getRole());
				json.setName(lpTaskUserActivityInfo.getName());
				
				jsonList.add(json);
			}
			conversationList.put("conversationList", jsonList);
			
			return new ResponseVo<Map<String, Object>>("0",conversationList);
		} catch (Exception e) {
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("获取userId为"+userId+"的用户的讨论内容异常");
			info.setMsg("请检查参数是否有误");
			return new ResponseError(CommonCode.S$ABNORMAL_OPERATION, info);
		}
	}
	
	private List<LpTaskUserActivityInfo> loopQuote(String quote[], LpTaskUserActivity lpTaskUserActivity, Integer schoolId) {
		List<LpTaskUserActivityInfo> quoteList = new ArrayList<>();
		for(String q : quote) {
			LpTaskUserActivityInfo info = getQuote(q, lpTaskUserActivity, schoolId);
			quoteList.add(info);
			if(info.getQuote() != null && !info.getQuote().equals("")) {
				String qu[] = info.getQuote().split(",");
				List<LpTaskUserActivityInfo> quo = loopQuote(qu, lpTaskUserActivity, schoolId);
				info.setQuoteList(quo);
				for(LpTaskUserActivityInfo in : info.getQuoteList()) {
					quoteList.add(in);
				}
			}
		}
		return quoteList;
	}
	
	private LpTaskUserActivityInfo getQuote(String quote, LpTaskUserActivity lpTaskUserActivity, Integer schoolId) {
		LpTaskUserActivity lt = new LpTaskUserActivity();
		
		LpTaskUserActivityInfo info = new LpTaskUserActivityInfo();
		lt = lpTaskUserActivityService.findLpTaskUserActivityById(Integer.parseInt(quote));
		String name = "";
		Student st = studentService.findOfUser(schoolId, lt.getUserId());
		if(st!=null) {
			name = st.getName();
		}else {
			name = teacherService.findOfUser(schoolId, lt.getUserId()).getName();
		}
		info.setName(name);
		String files = lpTaskUserActivity.getFiles();
		List<String> filePath = new ArrayList<String>();
		if(files != null && !files.equals("")) {
//				String filesStr = files.substring(1, files.length()-1);
			String filesSet[] = files.split(",");
			for(int j=0;j<filesSet.length;j++) {
//					EntityFile ef = entityFileService.findFileByUUID(filesSet[j]);
//					if(ef != null) {
//						filePath.add(ef.getRelativePath());
//					}
				String path = fileService.relativePath2HttpUrlByUUID(filesSet[j]);
				if(path != null && !"".equals(path)) {
					filePath.add(path);
				}
			}
		}
		info.setFilePath(filePath);
		BeanUtils.copyProperties(lt, info);
			
		return info;
	}

	@Override
	public Object deleteConversation(String appKey, Integer id) {
		//删除某一条讨论内容，逻辑删除
		try {
			AppEdition app = this.appEditionService.findByAppKey(appKey);
			if(app == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("appkey不存在,请确认");
				info.setMsg("不存在该appKey");
				info.setParam("appKey");
				return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
			}
			
			LpTaskUserActivity lpTaskUserActivity = lpTaskUserActivityService.findLpTaskUserActivityById(id);
			lpTaskUserActivity.setIsDeleted(true);
			lpTaskUserActivityService.modify(lpTaskUserActivity);
			
			Map<String, Boolean> res = new HashMap<>();
			res.put("success", true);
			return new ResponseVo<Map<String, Boolean>>("0", res);
		} catch (Exception e) {
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("删除讨论内容异常");
			info.setMsg("请检查参数是否有误");
			info.setParam("id");
			return new ResponseError(CommonCode.S$ABNORMAL_OPERATION, info);
		}
	}

	@Override
	public Object findConversationDate(String appKey, Integer lpId, Integer taskId, Integer unitId) {
		LearningPlan lp = learningPlanService.findLearningPlanById(lpId);
		Integer schoolId = lp.getOwnerId();//导学案的ownerId为schoolId
		//获取所有的讨论内容
		try {
			AppEdition app = this.appEditionService.findByAppKey(appKey);
			if(app == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("appkey不存在,请确认");
				info.setMsg("不存在该appKey");
				info.setParam("appKey");
				return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
			}
			
			LpTaskUserActivityCondition lpTaskUserActivityCondition = new LpTaskUserActivityCondition();
			lpTaskUserActivityCondition.setLpId(lpId);
			lpTaskUserActivityCondition.setTaskId(taskId);
			lpTaskUserActivityCondition.setUnitId(unitId);
			lpTaskUserActivityCondition.setIsDeleted(false);
			List<LpTaskUserActivity> l = lpTaskUserActivityService.findLpTaskUserActivityByCondition(lpTaskUserActivityCondition);
			
			List<LpTaskUserActivityJson> jsonList = new ArrayList<LpTaskUserActivityJson>();
			
			for(LpTaskUserActivity lpTaskUserActivity : l) {
				//返回带有引用内容的json
//				List<LpTaskUserActivityInfo> data = new ArrayList<LpTaskUserActivityInfo>();
				//获取引用的讨论内容，放入新的list中
				LpTaskUserActivityInfo lpTaskUserActivityInfo = new LpTaskUserActivityInfo();
				String quotes = lpTaskUserActivity.getQuote();
				if(quotes != null) {
					String quote[] = quotes.split(",");
					List<LpTaskUserActivityInfo> quoteList = new ArrayList<>();
					quoteList = loopQuote(quote, lpTaskUserActivity, schoolId);
					/**
					 * 对返回的结果做时间排序
					 */
					Collections.sort(quoteList, new Comparator<LpTaskUserActivityInfo>(){
						public int compare(LpTaskUserActivityInfo info1, LpTaskUserActivityInfo info2) {
							Long date1 = info1.getCreateDate().getTime();
							Long date2 = info2.getCreateDate().getTime();
							int flag = date1.compareTo(date2);
							return flag;
						}
					});
					lpTaskUserActivityInfo.setQuoteList(quoteList);
				}
				
				String files = lpTaskUserActivity.getFiles();
				List<String> filePath = new ArrayList<String>();
				if(files != null && !files.equals("")) {
//					String filesStr = files.substring(1, files.length()-1);
					String filesSet[] = files.split(",");
					for(int j=0;j<filesSet.length;j++) {
//						EntityFile ef = entityFileService.findFileByUUID(filesSet[j]);
//						if(ef != null) {
//							filePath.add(ef.getRelativePath());
//						}
						String path = fileService.relativePath2HttpUrlByUUID(filesSet[j]);
						if(path != null && !"".equals(path)) {
							filePath.add(path);
						}
					}
				}
				lpTaskUserActivityInfo.setFilePath(filePath);
				
				String iocnPath = ImgUtil.getImgSrc(lpTaskUserActivity.getUserId(), profileService);
				SchoolUser su =schoolUserService.findSchoolUserByUserId(lpTaskUserActivity.getUserId());
				String role = "";
				if(su.getUserType().equals("1")) {
					role = "教师";
				}else if(su.getUserType().equals("2")) {
					role = "admin";
				}else if(su.getUserType().equals("3")){
					role = "家长";
				}else if(su.getUserType().equals("4")) {
					role = "学生";
				}
				String name = su.getName();
				lpTaskUserActivityInfo.setIocnPath(iocnPath);
				lpTaskUserActivityInfo.setRole(role);
				lpTaskUserActivityInfo.setName(name);
				
				BeanUtils.copyProperties(lpTaskUserActivity, lpTaskUserActivityInfo);
				
				LpTaskUserActivityJson json = new LpTaskUserActivityJson();
				json.setId(lpTaskUserActivityInfo.getId());
				json.setLpId(lpTaskUserActivityInfo.getLpId());
				json.setTaskId(lpTaskUserActivityInfo.getTaskId());
				json.setUnitId(lpTaskUserActivityInfo.getUnitId());
				json.setUserId(lpTaskUserActivityInfo.getUserId());
				json.setContent(lpTaskUserActivityInfo.getContent());
				json.setFiles(lpTaskUserActivityInfo.getFilePath());
				json.setDegree(lpTaskUserActivityInfo.getDegree());
				json.setCreateDate(lpTaskUserActivityInfo.getCreateDate());
				json.setModifyDate(lpTaskUserActivityInfo.getModifyDate());
				json.setIsDeleted(lpTaskUserActivityInfo.getIsDeleted());
				json.setQuote(lpTaskUserActivityInfo.getQuote());
				json.setQuoteList(lpTaskUserActivityInfo.getQuoteList());
				json.setIocnPath(lpTaskUserActivityInfo.getIocnPath());
				json.setRole(lpTaskUserActivityInfo.getRole());
				json.setName(lpTaskUserActivityInfo.getName());
				
				jsonList.add(json);
			}
			
			return new ResponseVo<List<LpTaskUserActivityJson>>("0", jsonList);//返回需要修改
		} catch (Exception e) {
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("获取所有讨论内容异常");
			info.setMsg("请检查参数是否有误");
			return new ResponseError(CommonCode.S$ABNORMAL_OPERATION, info);
		}
	}
	
}
