package platform.education.rest.resource.service.impl;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import net.sf.json.JSONArray;
import net.sf.json.JSONNull;
import net.sf.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import platform.education.generalTeachingAffair.model.*;
import platform.education.generalTeachingAffair.service.*;
import platform.education.micro.model.*;
import platform.education.micro.service.*;
import platform.education.micro.vo.*;
import platform.education.resource.contants.DeleteStatus;
import platform.education.resource.contants.StudyFinishedFlag;
import platform.education.resource.model.Resource;
import platform.education.resource.service.ResourceService;
import platform.education.resource.vo.ResourceCondition;
import platform.education.rest.common.ResponseError;
import platform.education.rest.common.ResponseInfo;
import platform.education.rest.common.ResponseNormal;
import platform.education.rest.common.ResponseVo;
import platform.education.rest.common.constants.CommonCode;
import platform.education.rest.resource.service.MicroCourseRestService;
import platform.education.rest.resource.service.constant.MicroContans;
import platform.education.rest.resource.service.vo.*;
import platform.education.rest.util.DateUtil;
import platform.education.user.contants.GroupContants;
import platform.education.user.model.Group;
import platform.education.user.service.GroupService;
import platform.education.user.service.GroupUserService;
import platform.service.storage.holder.FileServiceHolder;
import platform.service.storage.model.EntityFile;
import platform.service.storage.service.EntityFileService;
import platform.service.storage.service.FileService;
import platform.service.storage.vo.FileResult;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

public class MicroCourseRestServiceImpl implements MicroCourseRestService{
	@Autowired
	@Qualifier("resourceService")
	private ResourceService resourceService;
	@Autowired
	@Qualifier("microLessonService")
	private MicroLessonService microLessonService;
	
	@Autowired
	@Qualifier("entityFileService")
	private EntityFileService entityFileService;
	
	@Autowired
	@Qualifier("groupUserService")
	private GroupUserService groupUserService;
	
	@Autowired
	@Qualifier("teacherService")
	private TeacherService teacherService;
	
	@Autowired
	@Qualifier("microPrepareService")
	private MicroPrepareService microPrepareService;
	
	@Autowired
	@Qualifier("studentService")
	private StudentService studentService;
	
	@Autowired
	@Qualifier("microCatalogService")
	private MicroCatalogService microCatalogService;
	
	@Autowired
	@Qualifier("fileService")
	private FileService fileService;
	
	@Autowired
	@Qualifier("groupService")
	private GroupService groupService;
	
	@Autowired
	@Qualifier("microBannerService")
	private MicroBannerService microBannerService;
	
	@Autowired
	@Qualifier("microLessonRelateService")
	private MicroLessonRelateService microLessonRelateService;
	
	@Autowired
	@Qualifier("microUserRecordService")
	private MicroUserRecordService microUserRecordService;
	
	@Autowired
	@Qualifier("microLessonMessageService")
	private MicroLessonMessageService microLessonMessageService;
	
	@Autowired
	@Qualifier("teamService")
	private TeamService teamService;
	
	@Autowired
	@Qualifier("gradeService")
	private GradeService gradeService;
	
	@Autowired
	@Qualifier("subjectGradeService")
	private SubjectGradeService subjectGradeService;
	
	@Override
	public Object myMicroForJson(Integer userId, String pageNumber, String pageSize, String title, String appKey, HttpServletRequest request) {
		if(userId == null|| "".equals(userId)){
			ResponseInfo info = new ResponseInfo();
			info.setDetail("userId参数必填");
			info.setMsg("userId参数不能为空");
			info.setParam("userId");
			return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
		}
		try{
			Page page = new Page();
			StringBuffer url = request.getRequestURL();  
	        String serverName = url.delete(url.length() - request.getRequestURI().length(), url.length()).append(request.getServletContext().getContextPath()).append("/").toString();
			if (pageSize != null && !"".equals(pageSize)) {
	            page.setPageSize(Integer.parseInt(pageSize));
	        }
	        if (pageNumber != null && !"".equals(pageNumber)) {
	            page.setCurrentPage(Integer.parseInt(pageNumber));
	        }
	        
	        //查找微课资源
	        List<MicroLessonVo> mllist = this.microLessonService.searchMicroLesson(1, page, userId, title, null, null, null, null);
	        
	        List<ExtMicroLessonVo> extList = new ArrayList<ExtMicroLessonVo>();
	        ExtMicroLessonVo extVo = null;
	        //增加移动端显示的微课vo
	        for (MicroLessonVo mlv : mllist) {
	        	extVo = new ExtMicroLessonVo();
	            mlv.setHttpUrl(FileServiceHolder.getInstance().getFileService().relativePath2HttpUrlByUUID(mlv.getEntityId()));
	            mlv.setThumbUrl(FileServiceHolder.getInstance().getFileService().thumb2HttpUrlByUUID(mlv.getEntityId()));
	            EntityFile ent = this.entityFileService.findFileByUUID(mlv.getEntityId());
	            mlv.setMd5(ent.getMd5());
	            mlv.setServerName(serverName.toString());
	            BeanUtils.copyProperties(mlv, extVo);
	            extVo.setCreateDate(mlv.getFmtCreateDate());
	            extVo.setModifyDate(mlv.getFmtModifyDate());
	            extList.add(extVo);
	        }
	        return new ResponseVo<List<ExtMicroLessonVo>>("0",extList);
		}catch(Exception e){
			ResponseInfo info = new ResponseInfo();
			info.setDetail("数据返回异常...");
			info.setMsg("找不到数据");
			info.setParam("userId");
			return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
        }
	}

	@Override
	public Object delete(Integer userId, String microId, String appKey) {
		MicroLesson ml=new MicroLesson();
		ml=this.microLessonService.findMicroLessonById(Integer.valueOf(microId));
		Integer status = this.microLessonService.deleteMyMicroLesson(1, userId, microId);
		ResourceCondition rc=new ResourceCondition();
		rc.setIsPersonal(true);
		rc.setObjectId(ml.getUuid());
		rc.setResType(MicroContans.MICRO_TYPE);
		List<Resource> rlist=new ArrayList<Resource>();
		rlist=this.resourceService.findResourceByCondition(rc);
		if(rlist!=null&&rlist.size()>0){
			this.resourceService.remove(rlist.get(0));
		}
		
		if(DeleteStatus.DELETE_SUCCESS.equals(status)){
			//正常删除
			return new ResponseNormal("0");
		} else if(DeleteStatus.DELETE_FAIL_NOT_EXIST.equals(status)){
			//删除失败，资源不存在
			ResponseInfo info = new ResponseInfo();
			info.setDetail("删除失败,资源不存在...");
			info.setMsg("资源不存在");
			info.setParam("userId,microId");
			return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
		} else {
			//资源不允许删除
			ResponseInfo info = new ResponseInfo();
			info.setDetail("删除失败,资源不允许删除...");
			info.setMsg("资源不允许删除");
			info.setParam("userId");
			return new ResponseError(CommonCode.S$ABNORMAL_OPERATION, info);
		}
	}

	@Override
	public Object publishLesson(Integer publisherId, String startDate, String finishedDate, String title,
			String microList, String classList, String appKey) {
		try {
			JSONArray microArray = null;
			JSONArray classArray = null;
			JSONArray microArray1 = new JSONArray();
			if(microList != null && !"".equals(microList)){
				microArray = JSONArray.fromObject(microList);
		        for(int i=0;i<microArray.size();i++){
		            JSONObject jsonobj=new JSONObject();
		            jsonobj=(JSONObject) microArray.get(i);
		            String objectId=jsonobj.getString("id");
		            ResourceCondition rc = new ResourceCondition();
		            rc.setObjectId(objectId);
		            rc.setResType(MicroContans.MICRO_TYPE);
		            rc.setIsPersonal(true);
		            List<Resource>rlist=new ArrayList<Resource>();
		            rlist=this.resourceService.findResourceByCondition(rc);
		            if(rlist!=null&&rlist.size()>0){
		            	
		            	jsonobj.put("resourceId", String.valueOf(rlist.get(0).getId()));
		            	microArray1.add(jsonobj);
		            }
		          }
			}
			if(classList != null && !"".equals(classList)){
				classArray = JSONArray.fromObject(classList);
			}
			Date sd = DateUtil.strToDate(startDate,"yyyy-MM-dd HH:mm:ss");
			Date fd = DateUtil.strToDate(finishedDate,"yyyy-MM-dd HH:mm:ss");
			MicroLessonPublish mlp = null;
	        List<Group> groups = this.groupUserService.findGroups(publisherId, GroupContants.TYPE_SCHOOL);
	        if (groups.size() > 0) {
	            Group group = groups.get(0);
	            if (group != null) {
	                Integer schoolId = group.getOwnerId();
	                Teacher teacher = this.teacherService.findOfUser(schoolId, publisherId);
	                if (teacher != null) {
	                    mlp = this.microPrepareService.publishMicroLesson(microArray1, classArray, sd, fd, title, publisherId, teacher.getName());
	                    //发送通知
	                    //不用推送
//	                   sendMessageToStudent(publisherId, teacher.getName(), classList, schoolId);
	                   String status = adddMicroLessonMessage(microArray1, classArray, sd, fd, title, publisherId, teacher.getName(), mlp.getUuid());
	                   if("fail".equals(status)){
	                	   ResponseInfo info = new ResponseInfo();
	                	   info.setDetail("relateName错误");
	                	   info.setMsg("relateName错误");
	                	   info.setParam("relateName");
	                	   return new ResponseError(CommonCode.$FAILED_TO_INTERPRET_PARAMETERS, info);
	                   }
	                } else {
	                    //发布者不是教师
	                	ResponseInfo info = new ResponseInfo();
	        			info.setDetail("发布者不是教师...");
	        			info.setMsg("发布者不是教师");
	        			info.setParam("publisherId");
	        			return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
	                }
	            } else {
	                //发布者没有学校
	            	ResponseInfo info = new ResponseInfo();
	    			info.setDetail("发布者没有学校...");
	    			info.setMsg("发布者没有学校");
	    			info.setParam("publisherId");
	    			return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
	            }
	        } else {
	            //发布者没有用户组
	        	ResponseInfo info = new ResponseInfo();
				info.setDetail("发布者没有用户组...");
				info.setMsg("发布者没有用户组");
				info.setParam("userId,microId");
				return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
	        }

	        //数据返回
	        if (mlp != null) {
	            //发布成功
	        	return new ResponseNormal("0");
	        } else {
	            //发布失败
	            ResponseInfo info = new ResponseInfo();
				info.setDetail("发布失败...");
				info.setMsg("发布失败");
				info.setParam("publisherId,microList,classList");
				return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
	        }
		} catch (Exception e) {
//			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("发布失败...");
			info.setMsg("资源不存在");
			info.setParam("publisherId,microList,classList");
			return new ResponseError(CommonCode.S$ABNORMAL_OPERATION, info);
		}
	}

	@Override
	public Object publishManager(Integer userId, Integer relateId, String pageNumber, String pageSize, String appKey) {
		try {
			Page page = new Page();
			if (pageSize != null && !"".equals(pageSize)) {
	            page.setPageSize(Integer.parseInt(pageSize));
	        }
	        if (pageNumber != null && !"".equals(pageNumber)) {
	            page.setCurrentPage(Integer.parseInt(pageNumber));
	        }
	        List<Student> stList = this.studentService.findStudentOfTeam(relateId);   
	        
	        List<ExtMicroLessonRelate> emlrvList = new ArrayList<ExtMicroLessonRelate>();
			MicroLessonRelateCondition mlrc = new MicroLessonRelateCondition();
			mlrc.setPublisherId(userId);
			mlrc.setRelateId(relateId);
			List<MicroLessonRelate> mlrList = this.microLessonRelateService.findMicroLessonRelateByCondition(mlrc, page,
					Order.desc("create_date"));
			ExtMicroLessonRelate emlr = null;
			for (MicroLessonRelate mlr : mlrList) {
				emlr = new ExtMicroLessonRelate();
				BeanUtils.copyProperties(mlr, emlr);
				Date today = new Date();
		        if (today.after(mlr.getFinishedDate())) {
		        	emlr.setFinishFlag(true);
		        } else {
		        	emlr.setFinishFlag(false);
		        }
				emlr.setCreateDate(DateUtil.dateToStrSS(mlr.getCreateDate()));
				emlr.setModifyDate(DateUtil.dateToStrSS(mlr.getModifyDate()));
				emlr.setStartDate(DateUtil.dateToStrSS(mlr.getStartDate()));
				emlr.setFinishedDate(DateUtil.dateToStrSS(mlr.getFinishedDate()));
				JSONArray realMicroList = JSONArray.fromObject(mlr.getRealMicroList());
				for (int i = 0; i < realMicroList.size(); i++) {
					JSONObject rml = (JSONObject) realMicroList.get(i);
					String id = (String) rml.get("id");
					MicroLesson ml = this.microLessonService.findMicroLessonByUuid(id);
					if(ml != null){
						rml.put("thumbUrl", FileServiceHolder.getInstance().getFileService().thumb2HttpUrlByUUID(ml.getEntityId()));	
						rml.put("subjectName", ml.getSubjectName());
						rml.put("lessonLength", ml.getLessonLength());
					}
					realMicroList.set(i, rml);
				}
				emlr.setRealMicroList(realMicroList);
				emlrvList.add(emlr);
			}
	        for (ExtMicroLessonRelate em : emlrvList) {
	            Integer finishedCount = 0;
	            Integer unFinishedCount = 0;
	           
	            if(stList!=null && stList.size()>0) {
	            	 List<MicroPublishedRecord> mprList = this.microPrepareService.searchTeamPublishedRecord(em.getPublishMicroLessonId(), stList);
	 	            for (MicroPublishedRecord mpr : mprList) {
	 	                if (mpr.getFinishedFlag() == StudyFinishedFlag.FINISHED) {
	 	                    finishedCount++;
	 	                } else if (mpr.getFinishedFlag() == StudyFinishedFlag.NOT_FINISHED) {
	 	                    unFinishedCount++;
	 	                } else if (mpr.getFinishedFlag() == StudyFinishedFlag.PART_FINISHED) {
	 	                    finishedCount++;
	 	                }
	 	            }
	            }
	            /*
	            for (Student st : stList) {
	            	MicroPublishedRecord mpr = this.microPrepareService.searchUserPublishedRecord(em.getPublishMicroLessonId(), st.getUserId(), st.getUserName(), st.getStudentNumber());
	                if (mpr.getFinishedFlag() == StudyFinishedFlag.FINISHED) {
	                    finishedCount++;
	                } else if (mpr.getFinishedFlag() == StudyFinishedFlag.NOT_FINISHED) {
	                    unFinishedCount++;
	                } else if (mpr.getFinishedFlag() == StudyFinishedFlag.PART_FINISHED) {
	                    finishedCount++;
	                }
	            }
	            */
	            em.setFinishedCount(finishedCount);
	            em.setUnFinishedCount(unFinishedCount);
	        }
	        return new ResponseVo<List<ExtMicroLessonRelate>>("0",emlrvList);
		} catch (Exception e) {
			ResponseInfo info = new ResponseInfo();
			info.setDetail("获取参数微课异常");
			info.setMsg("获取参数异常");
			info.setParam("relateId,userId...");
			return new ResponseError(CommonCode.S$ABNORMAL_OPERATION, info);
		}
	}

	@Override
	public Object deletePublished(String publishId, Integer relateId, String appKey) {
		try {
			Integer status = this.microPrepareService.deletePublish(publishId, relateId);
			if(DeleteStatus.DELETE_SUCCESS.equals(status)){
				//正常删除
				return new ResponseNormal("0");
			} else{
				//删除失败，资源不存在
				ResponseInfo info = new ResponseInfo();
				info.setDetail("删除失败,资源不存在...");
				info.setMsg("资源不存在");
				info.setParam("publishId,relateId");
				return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
			}
		} catch (Exception e) {
			ResponseInfo info = new ResponseInfo();
			info.setDetail("删除微课异常");
			info.setMsg("删除微课异常");
			info.setParam("publishId,relateId...");
			return new ResponseError(CommonCode.S$ABNORMAL_OPERATION, info);
		}
	}

	@Override
	public Object batchReviews(String publishId, Integer relateId, String appKey) {
		try {
			
	        List<Student> stList = this.studentService.findStudentOfTeam(relateId);
	        List<ExtStudent> extstList = new ArrayList<ExtStudent>();
	        List<ExtStudent> extfst = new ArrayList<ExtStudent>();
	        List<ExtStudent> extust = new ArrayList<ExtStudent>();
	        List<ExtStudent> extpst = new ArrayList<ExtStudent>();
	        ExtStudent exts = null;
	        for(Student s : stList){
	        	exts = new ExtStudent();
	        	BeanUtils.copyProperties(s, exts);
	        	extstList.add(exts);
	        }
	        if (extstList != null && extstList.size() > 0) {
	            for (ExtStudent st : extstList) {
	                MicroPublishedRecord mpr = this.microPrepareService.searchUserPublishedRecord(publishId, st.getUserId(), st.getName(), st.getStudentNumber());
	                if (mpr.getFinishedFlag() == StudyFinishedFlag.FINISHED) {
	                    extfst.add(st);
	                } else if (mpr.getFinishedFlag() == StudyFinishedFlag.NOT_FINISHED) {
	                    extust.add(st);
	                } else if (mpr.getFinishedFlag() == StudyFinishedFlag.PART_FINISHED) {
	                    extpst.add(st);
	                }
	            }
	        }
	        JSONObject obj = new JSONObject();
	        obj.put("publishId", publishId);
	        obj.put("relateId", relateId);
	        obj.put("allList", JSONArray.fromObject(extstList));
	        obj.put("finishedList", JSONArray.fromObject(extfst));
	        obj.put("unFinishedList", JSONArray.fromObject(extust));
	        obj.put("partFinishedList", JSONArray.fromObject(extpst));
	        return new ResponseVo<JSONObject>("0",obj);
		} catch (Exception e) {
			ResponseInfo info = new ResponseInfo();
			info.setDetail("数据获取异常");
			info.setMsg("数据获取异常");
			info.setParam("publishId,relateId...");
			return new ResponseError(CommonCode.S$ABNORMAL_OPERATION, info);
		}
	}

	@Override
	public Object saveReviews(String reviews, String publishId, Integer reward, String studentList, String appKey) {
		try {
			JSONArray studentArray = null;
			if(studentList != null && !"".equals(studentList)){
				studentArray = JSONArray.fromObject(studentList);
				for (int i = 0; i < studentArray.size(); i++) {
		            Integer userId = (Integer) studentArray.get(i);
		            this.microPrepareService.updateUserPublishedRecord(publishId, userId, reviews, reward);
		        }
			}
			return new ResponseNormal("0");
		} catch (Exception e) {
			ResponseInfo info = new ResponseInfo();
			info.setDetail("操作异常");
			info.setMsg("操作异常");
			info.setParam("studentList...");
			return new ResponseError(CommonCode.S$ABNORMAL_OPERATION, info);
		}
	}

	@Override
	public Object publishDetails(String publishId, Integer relateId, String appKey) {
		try {
			Integer finishedCount = 0;
	        Integer unFinishedCount = 0;
	        Integer partFinishedCount = 0;
	        
	        List<ExtMicroPublishedRecord> recordList = new ArrayList<ExtMicroPublishedRecord>();
	        ExtMicroPublishedRecord empr = null;
	        
	        List<Student> stList = this.studentService.findStudentOfTeam(relateId);
	        if (stList != null && stList.size() > 0) {
	            for (Student st : stList) {
	                MicroPublishedRecord mpr = this.microPrepareService.searchUserPublishedRecord(publishId, st.getUserId(), st.getName(), st.getStudentNumber());
	                if (mpr.getFinishedFlag() == StudyFinishedFlag.FINISHED) {
	                    finishedCount++;
	                } else if (mpr.getFinishedFlag() == StudyFinishedFlag.NOT_FINISHED) {
	                    unFinishedCount++;
	                } else if (mpr.getFinishedFlag() == StudyFinishedFlag.PART_FINISHED) {
	                    partFinishedCount++;
	                }
	                empr = new ExtMicroPublishedRecord();
	                BeanUtils.copyProperties(mpr, empr);
	                empr.setCreateDate(DateUtil.dateToStrSS(mpr.getCreateDate()));
	                empr.setModifyDate(DateUtil.dateToStrSS(mpr.getModifyDate()));
	                empr.setFinishedDate(DateUtil.dateToStrSS(mpr.getFinishedDate()));
	                recordList.add(empr);
	            }
	        }
	        ExtPushDetails epd = new ExtPushDetails();
	        epd.setFinishedCount(finishedCount);
	        epd.setPartFinishedCount(partFinishedCount);
	        epd.setRecordList(recordList);
	        epd.setUnFinishedCount(unFinishedCount);
	        return new ResponseVo<ExtPushDetails>("0",epd);
		} catch (Exception e) {
			ResponseInfo info = new ResponseInfo();
			info.setDetail("获取数据异常");
			info.setMsg("获取数据异常");
			info.setParam("publishId,relateId...");
			return new ResponseError(CommonCode.S$ABNORMAL_OPERATION, info);
		}
	}

	@Override
	public Object microCourseData(Integer userId, Integer schoolId, String appKey) {
		try {
			Map<String,Object> jsonMap = new HashMap<String, Object>(); 
	    	//获取微课星知识
	    	MicroCatalogCondition mcCondition = new MicroCatalogCondition();
	    	mcCondition.setPushState(MicroContans.PUSH_ENABLE);
	    	List<MicroCatalog> catalogList = this.microCatalogService.findMicroCatalogRemoveContent(mcCondition, null, null);
	    	
	    	//****************************** 热门微课  *************************************
	    	MicroLessonHotCondition condition = new MicroLessonHotCondition();
	    	Page page = new Page();
	    	Order order = new Order();
	    	condition.setPushState(MicroContans.PUSH_ENABLE);
	    	page.setPageSize(10);
	    	List<MicroLesson> hotLessons = microLessonService.findMicroLessonHot(condition,page,order);
	    	List<ExtMicroLessonVo> extvoList = new ArrayList<ExtMicroLessonVo>();
	    	ExtMicroLessonVo extVo = null;
	    	if(hotLessons != null && hotLessons.size() >0){
	    		for(MicroLesson m :hotLessons){
	    			extVo = new ExtMicroLessonVo();
	    			
	    			BeanUtils.copyProperties(m, extVo);
	    			EntityFile ent = this.entityFileService.findFileByUUID(m.getEntityId());
	    			if(ent != null){
	    				extVo.setThumbUrl(fileService.relativePath2HttpUrl(ent.getThumbnailUrl()));
	    			}
	    			Integer userID = m.getUserId();
	    			if(userID != null){
	    				List<Integer> gList = groupUserService.findGroupIds(userID);
	    				if(gList.size() > 0){
	    					Group group = groupService.findGroupById(gList.get(0));
	    					if(group != null){
	    						Teacher lessonUser = teacherService.findOfUser(group.getOwnerId(), userID);
	    						if(lessonUser != null){
	    							extVo.setName(lessonUser.getName());
	    						}
	    					}
	    				}
	    			}
	    			String entityId = m.getEntityId();
	        	/*	if(!StringUtils.isEmpty(entityId)){
	        			FileResult file = fileService.findFileByUUID(entityId);
	        			if(file.getEntityFile() != null){
	        				extVo.setEntityPath(fileService.relativePath2HttpUrl(file.getEntityFile().getRelativePath()));
	        			}
	        		}*/
	        		extvoList.add(extVo);
	    		}
	    	}
	    	
	    	//获取banner
	    	MicroBannerCondition microBannerCondition = new MicroBannerCondition();
	    	microBannerCondition.setPushState(MicroContans.PUSH_ENABLE);
	    	List<MicroBanner> microBanners = microBannerService.findMicroBannerByCondition(microBannerCondition);
	    	List<MicroBannerVo> bannerList = new ArrayList<MicroBannerVo>();
			if(microBanners.size() > 0){
				for(MicroBanner mb :microBanners){
					MicroBannerVo vo = new MicroBannerVo();
					BeanUtils.copyProperties(mb, vo);
					String entityId = mb.getEntityId();
					FileResult file = fileService.findFileByUUID(entityId);
					if(file != null){
						vo.setThumUrl(file.getHttpUrl());
					}
					bannerList.add(vo);
				}
			}
	    	
			//获取教师上传微课数目（当天、当月、所有）
			MicroLessonCountCondition countCondition = new MicroLessonCountCondition();
			countCondition.setUserId(userId);
			countCondition.setTodayMicroCount(true);
			Long currentDayCount = microLessonService.findCountByTeacherAndCondition(countCondition);
			countCondition.setMonthMicroCount(true);
			Long currentMonthCount = microLessonService.findCountByTeacherAndCondition(countCondition);
			countCondition.setAllCount(true);
			Long allCount = microLessonService.findCountByTeacherAndCondition(countCondition);
	    	//将数据转换成json
	    	jsonMap.put("catalogList", catalogList);
	    	jsonMap.put("hotLessons", extvoList);
	    	jsonMap.put("microBanners", bannerList);
	    	jsonMap.put("currentDayCount", currentDayCount);
	    	jsonMap.put("currentMonthCount", currentMonthCount);
	    	jsonMap.put("allCount", allCount);
//	    	ObjectMapper mapper = new ObjectMapper();
//	    	String param = mapper.writeValueAsString(jsonMap);
	    	return new ResponseVo<Map>("0",jsonMap);
		} catch (Exception e) {
			ResponseInfo info = new ResponseInfo();
			info.setDetail("获取数据异常");
			info.setMsg("获取数据异常");
			info.setParam("publishId,relateId...");
			return new ResponseError(CommonCode.S$ABNORMAL_OPERATION, info);
		}
	}

	@Override
	public Object studyList(Integer userId, String subjectName, String publishUuid, String pageNumber, String pageSize,
			String appKey) {
		try {
			Page page = new Page();
			if (pageSize != null && !"".equals(pageSize)) {
	            page.setPageSize(Integer.parseInt(pageSize));
	        }
	        if (pageNumber != null && !"".equals(pageNumber)) {
	            page.setCurrentPage(Integer.parseInt(pageNumber));
	        }
			Student s = this.studentService.findStudentByUserId(userId);
	        if (s != null) {
	            Integer teamId = s.getTeamId();
	            if (teamId != null) {
	                Date fdate = new Date();
	                List<MicroLessonRelateVo> mlrvList = null;
	                if (publishUuid != null && !"".equals(publishUuid)) {
	                	mlrvList = this.microPrepareService.searchHistoryPublishedLesson(null, publishUuid, teamId, subjectName, fdate, false, page);
	                } else {
	                	mlrvList = this.microPrepareService.searchHistoryPublishedLesson(null, teamId, subjectName, fdate, false, page);
	                }
	                JSONArray studyList = new JSONArray();
	                for (MicroLessonRelateVo rvo : mlrvList) {
	                    JSONObject relateJson = new JSONObject();
	                    Integer finishedCount = 0;
	                    String relateName = rvo.getRelateName();
	                    String subject = "";
	                   /* if(!StringUtils.isEmpty(relateName)){
	                    	subject = relateName.substring(relateName.indexOf("[") + 1, relateName.indexOf("]"));
	                    }*/
	                    relateJson.put("fmtStartDate", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(rvo.getStartDate()));
	                    relateJson.put("fmtFinishedDate", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(rvo.getFinishedDate()));
	                    relateJson.put("publishMicroLessonId", rvo.getPublishMicroLessonId());
	                    relateJson.put("publisherId", rvo.getPublisherId());
	                    relateJson.put("publisherName", rvo.getPublisherName());
	                    relateJson.put("subjectName", subject);
	                    relateJson.put("title", rvo.getTitle() == null ? "" : rvo.getTitle());
	                    JSONArray array = microPrepareService.getPublishedPlayJson(rvo.getPublishMicroLessonId());
	                    JSONArray rebuiltArray = new JSONArray();
	                	for (int i = 0; i < array.size(); i++) {
	                        JSONObject obj = array.getJSONObject(i);
	                        String microId = (String) obj.get("id");
	                        MicroUserRecord ur = this.microUserRecordService.getUniqueRecord(userId, microId, rvo.getPublishMicroLessonId());
	                        if (ur != null) {
	                            if (ur.getLastPlayTime() != null) {
	                                Long lpt = ur.getLastPlayTime().longValue();
	                                String sd = timeMillisToString(lpt.intValue());
	                                obj.put("formatLastPlayTime", sd);
	                            } else {
	                                obj.put("formatLastPlayTime", null);
	                            }
	                            if (ur.getFinishedFlag() != null) {
	                                if (ur.getFinishedFlag() == StudyFinishedFlag.FINISHED) {
	                                    obj.put("finishedFlag", StudyFinishedFlag.FINISHED);
	                                    finishedCount++;
	                                } else {
	                                    obj.put("finishedFlag", StudyFinishedFlag.NOT_FINISHED);
	                                }
	                            } else {
	                                obj.put("finishedFlag", StudyFinishedFlag.NOT_FINISHED);
	                            }
	                        } else {
	                            obj.put("finishedFlag", StudyFinishedFlag.NOT_FINISHED);
	                            obj.put("formatLastPlayTime", null);
	                        }
	                        MicroLesson ml = this.microLessonService.findMicroLessonByUuid(microId);
	                        if (ml != null) {
	                            EntityFile ent = this.entityFileService.findFileByUUID(ml.getEntityId());
	                            obj.put("httpUrl", FileServiceHolder.getInstance().getFileService().relativePath2HttpUrlByUUID(ml.getEntityId()));
	                            obj.put("thumbUrl", FileServiceHolder.getInstance().getFileService().thumb2HttpUrlByUUID(ml.getEntityId()));
	                            obj.put("md5", ent.getMd5());
	                        }
	                        rebuiltArray.add(obj);
	                    }
	                	relateJson.put("finishedCount", finishedCount);
	                    relateJson.put("microArray", rebuiltArray);
	                    studyList.add(relateJson);
	                }
	                return new ResponseVo<JSONArray>("0",studyList);
	            } else {
	            	//无班级
	            	ResponseInfo info = new ResponseInfo();
					info.setDetail("获取失败,学生无班级...");
					info.setMsg("学生无班级");
					info.setParam("userId");
					return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
	            }
	        } else {
	        	//该用户不是一个学生
	        	ResponseInfo info = new ResponseInfo();
				info.setDetail("获取失败,用户不是学生...");
				info.setMsg("该用户不是学生");
				info.setParam("userId");
				return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
	        }
		} catch (Exception e) {
			ResponseInfo info = new ResponseInfo();
			info.setDetail("获取数据异常");
			info.setMsg("获取数据异常");
			info.setParam("userId");
			return new ResponseError(CommonCode.S$ABNORMAL_OPERATION, info);
		}
	}
	
	/**
	 * ===========================================================================================================
	 */
	public String timeMillisToString(int time) {
        String timeStr = null;
        int hour = 0;
        int minute = 0;
        int second = 0;
        if (time <= 0) {
            return "00:00";
        } else {
            minute = time / 60;
            if (minute < 60) {
                second = time % 60;
                timeStr = unitFormat(minute) + ":" + unitFormat(second);
            } else {
                hour = minute / 60;
                if (hour > 99) {
                    return "99:59:59";
                }
                minute = minute % 60;
                second = time - hour * 3600 - minute * 60;
                timeStr = unitFormat(hour) + ":" + unitFormat(minute) + ":" + unitFormat(second);
            }
        }
        return timeStr;
    }
	public String unitFormat(int i) {
        String retStr = null;
        if (i >= 0 && i < 10) {
            retStr = "0" + Integer.toString(i);
        } else {
            retStr = "" + i;
        }
        return retStr;
    }
	/**
	 * ===========================================================================================================
	 */
	
	
	@Override
	public Object saveRecord(String microId, String lastPlayTime, String finishedFlag, String publisherId,
			Integer userId, String publishLessonId, String appKey) {
		try {
			MicroUserRecord ur = this.microUserRecordService.getUniqueRecord(userId, microId, publishLessonId);
	        Student student = this.studentService.findStudentByUserId(userId);
	        if (ur != null) {
	            ur.setModifyDate(new Date());
	            ur.setPlayTime(ur.getPlayTime() + 1);
	            if (finishedFlag != null && !"".equals(finishedFlag)) {
	                int flag = Integer.parseInt(finishedFlag);
	                if (flag == StudyFinishedFlag.FINISHED) {
	                    ur.setFinishedDate(new Date());
	                    ur.setFinishedFlag(flag);
	                    //推送消息
//	                    if (publisherId != null && !"".equals(publisherId)) {
//	                        sendMessageToTeacher(publisherId, student.getName(), userId);
//	                    }
	                    
	                }else{
	                    //如果之前已经完成就不需要再改变学习状态
	                    if(ur.getFinishedFlag()!=null&&ur.getFinishedFlag()==StudyFinishedFlag.FINISHED){
	                    }else{
	                        ur.setFinishedFlag(flag);
	                    }
	                }
	            }
	            if (lastPlayTime != null && !"".equals(lastPlayTime) && !"0".equals(lastPlayTime)) {
	                ur.setLastPlayTime(Double.parseDouble(lastPlayTime));
	            }
	            this.microUserRecordService.modify(ur);
	        } else {
	            ur = new MicroUserRecord();
	            ur.setCreateDate(new Date());
	            ur.setModifyDate(new Date());
	            ur.setMicroId(microId);
	            ur.setUserId(userId);
	            if (finishedFlag != null && !"".equals(finishedFlag)) {
	                ur.setFinishedFlag(Integer.parseInt(finishedFlag));
	            }
	            ur.setUserName(student.getName());
	            ur.setPlayTime(1);
	            ur.setPublishLessonId(publishLessonId);
	            this.microUserRecordService.add(ur);
	        }
	        return new ResponseNormal("0");
		} catch (Exception e) {
			ResponseInfo info = new ResponseInfo();
			info.setDetail("获取数据异常");
			info.setMsg("获取数据异常");
			info.setParam("userId,microId...");
			return new ResponseError(CommonCode.S$ABNORMAL_OPERATION, info);
		}
	}

	@Override
	public Object listByStudent(Integer studentUserId, String pageNumber, String pageSize, String sortItem,
			Integer sortType, String appKey) {
		try {
			Page page = new Page();
			if (pageSize != null && !"".equals(pageSize)) {
	            page.setPageSize(Integer.parseInt(pageSize));
	        }
	        if (pageNumber != null && !"".equals(pageNumber)) {
	            page.setCurrentPage(Integer.parseInt(pageNumber));
	        }
			Order order = new Order();
			MicroLessonMessageCondition condition = new MicroLessonMessageCondition();
			order.setProperty(sortItem);
			order.setAscending(sortType == 0 ? true : false);
			condition.setStudentUserId(studentUserId);
			condition.setIsRead(false);
    		List<MicroLessonMessage> mlmList = this.microLessonMessageService.findUsefulMessage(condition.getStudentUserId(), new Date(), page, order);
        	List<FormatMicroLessonMessage> messageVoList = new ArrayList<FormatMicroLessonMessage>();
        	FormatMicroLessonMessage messageVo = null;
        	JSONArray obj = new JSONArray();
        	for(MicroLessonMessage mlm : mlmList){
        		messageVo = new FormatMicroLessonMessage();
    			BeanUtils.copyProperties(mlm, messageVo);
    			//把关联的微课转为list
    			obj = JSONArray.fromObject(mlm.getRealMicroList());
    			List<RealMicroList> list = (List)JSONArray.toCollection(obj, RealMicroList.class);
    			
    			messageVo.setRealMicroList(list);
    			messageVo.setFinishDate(DateUtil.dateToStrSS(mlm.getFinishDate()));
    			messageVo.setStartDate(DateUtil.dateToStrSS(mlm.getStartDate()));
    			messageVo.setCreateDate(DateUtil.dateToStrSS(mlm.getCreateDate()));
    			messageVoList.add(messageVo);
        	}
        	return new ResponseVo<List<FormatMicroLessonMessage>>("0", messageVoList);
		} catch (Exception e) {
			ResponseInfo info = new ResponseInfo();
			info.setDetail("参数异常...");
			info.setMsg("参数出错");
			info.setParam("studentUserId");
			return new ResponseError(CommonCode.S$ABNORMAL_OPERATION, info);
		}
	}

	@Override
	public Object deleteMicroMessage(Integer id, String appKey) {
		try {
			MicroLessonMessage microLessonMessage = this.microLessonMessageService.findMicroLessonMessageById(id);
    		if(microLessonMessage == null){
    			throw new Exception();
    		}
    		this.microLessonMessageService.remove(microLessonMessage);
    		return new ResponseNormal("0");
		} catch (Exception e) {
			ResponseInfo info = new ResponseInfo();
			info.setDetail("消息id异常...");
			info.setMsg("参数出错");
			info.setParam("id");
			return new ResponseError(CommonCode.S$ABNORMAL_OPERATION, info);
		}
	}

	@Override
	public Object updateReadStatus(String ids, String appKey) {
		try {
    		if (ids != null && !"".equals(ids)) {
				JSONArray jsonArray = JSONArray.fromObject(ids);
				for (int i = 0; i < jsonArray.size(); i++) {
					String id = jsonArray.get(i)+"";
					MicroLessonMessage microLessonMessage = this.microLessonMessageService.findMicroLessonMessageById(Integer.parseInt(id));
					microLessonMessage.setIsRead(true);
					this.microLessonMessageService.modify(microLessonMessage);
				}
				return new ResponseNormal("0");
			} else {
				ResponseInfo info = new ResponseInfo();
				info.setDetail("数据出错...");
				info.setMsg("数据出错");
				info.setParam("ids...");
				return new ResponseError(CommonCode.S$INVALID_DATA, info);
			}
		} catch (Exception e) {
			ResponseInfo info = new ResponseInfo();
			info.setDetail("服务器繁忙...");
			info.setMsg("服务器繁忙");
			info.setParam("...");
			return new ResponseError(CommonCode.S$SERVER_BUSY, info);
		}
	}
	
	@Override
	public Object unStudyList(String userId, String subjectName, String pageNumber, String pageSize, String appKey) {
		try {
			Page page = new Page();
			if (pageSize != null && !"".equals(pageSize)) {
	            page.setPageSize(Integer.parseInt(pageSize));
	        }
	        if (pageNumber != null && !"".equals(pageNumber)) {
	            page.setCurrentPage(Integer.parseInt(pageNumber));
	        }
			Student s = this.studentService.findStudentByUserId(Integer.parseInt(userId));
	        boolean flag = false;
	        if (s != null) {
	            Integer teamId = s.getTeamId();
	            if (teamId != null) {
	            	//查询历史作业记录，不管有没有过期
	                List<MicroLessonRelateVo> mlrvList = this.microPrepareService.searchHistoryPublishedLesson(null, teamId, subjectName, null, false, page);
	                JSONArray studyList = new JSONArray();
	                for (MicroLessonRelateVo rvo : mlrvList) {
	                    JSONObject relateJson = new JSONObject();
	                    String relateName = rvo.getRelateName();
	                    String subject = null;
	                    /*if(!StringUtils.isEmpty(relateName)){
	                    	subject = relateName.substring(relateName.indexOf("[") + 1, relateName.indexOf("]"));
	                    }*/
	                    flag = false;
	                    JSONArray array = microPrepareService.getPublishedPlayJson(rvo.getPublishMicroLessonId());
	                    JSONArray rebuiltArray = new JSONArray();
	                    for(int j=0; j<array.size();j++){
	                    	 JSONObject obj = array.getJSONObject(j);
	                         String microId = (String) obj.get("id");
	                         MicroUserRecord ur = this.microUserRecordService.getUniqueRecord(Integer.parseInt(userId), microId, rvo.getPublishMicroLessonId());
	                         //学习过
	                         if (ur != null) {
	                        	 flag = true;
	                        	 break;
	                         }
	                    }
	                    if(!flag){
		                    //如果不存在未学习的
		                	relateJson.put("fmtStartDate", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(rvo.getStartDate()));
		                    relateJson.put("fmtFinishedDate", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(rvo.getFinishedDate()));
		                    relateJson.put("publishMicroLessonId", rvo.getPublishMicroLessonId());
		                    relateJson.put("publisherId", rvo.getPublisherId());
		                    relateJson.put("publisherName", rvo.getPublisherName());
		                    relateJson.put("subjectName", subject);
		                    relateJson.put("title", rvo.getTitle() == null ? "" : rvo.getTitle());
		                	for (int i = 0; i < array.size(); i++) {
		                        JSONObject obj = array.getJSONObject(i);
		                        String microId = (String) obj.get("id");
		                        //未学习
	                        	MicroLesson ml = this.microLessonService.findMicroLessonByUuid(microId);
	                            if (ml != null) {
	                                EntityFile ent = this.entityFileService.findFileByUUID(ml.getEntityId());
	                                obj.put("httpUrl", FileServiceHolder.getInstance().getFileService().relativePath2HttpUrlByUUID(ml.getEntityId()));
	                                obj.put("thumbUrl", FileServiceHolder.getInstance().getFileService().thumb2HttpUrlByUUID(ml.getEntityId()));
	                                obj.put("md5", ent.getMd5());
	                            }
	                            rebuiltArray.add(obj);
		                    }
		                    relateJson.put("microArray", rebuiltArray);
		                    studyList.add(relateJson);
		                }
	                }
	                return new ResponseVo<JSONArray>("0",studyList);
	            } else {
	            	//无班级
	            	ResponseInfo info = new ResponseInfo();
					info.setDetail("获取失败,学生无班级...");
					info.setMsg("学生无班级");
					info.setParam("userId");
					return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
	            }
	        } else {
	        	//该用户不是一个学生
	        	ResponseInfo info = new ResponseInfo();
				info.setDetail("获取失败,用户不是学生...");
				info.setMsg("该用户不是学生");
				info.setParam("userId");
				return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
	        }
		} catch (Exception e) {
			ResponseInfo info = new ResponseInfo();
			info.setDetail("获取数据异常");
			info.setMsg("获取数据异常");
			info.setParam("userId");
			return new ResponseError(CommonCode.S$ABNORMAL_OPERATION, info);
		}
		
	}

	@Override
	public Object team(String userId, String appKey) {
		try {
    		Student stu = this.studentService.findStudentByUserId(Integer.parseInt(userId));
    		Team team = null;
    		ExtTeam extTeam = null;
    		if(stu != null){
    			team = this.teamService.findTeamById(stu.getTeamId());
    			if(team != null){
    				extTeam = new ExtTeam(team.getId(), team.getName());
    			}
    		}
        	return new ResponseVo<ExtTeam>("0", extTeam);
		} catch (Exception e) {
			ResponseInfo info = new ResponseInfo();
			info.setDetail("服务器繁忙...");
			info.setMsg("服务器繁忙");
			info.setParam("...");
			return new ResponseError(CommonCode.S$SERVER_BUSY, info);
		}
	}

	private String adddMicroLessonMessage(JSONArray microList, JSONArray publishObjectList, Date startDate, Date finishedDate, String title, Integer publisherId, String publisherName, String publishUuid){
    	MicroLessonMessage mlm = null;
    	Date date = new Date();
         for (int j = 0; j < publishObjectList.size(); j++) {
             JSONObject mlrj = (JSONObject) publishObjectList.get(j);
             String relateId = (String) mlrj.get("relateId");
             Object relateName = mlrj.get("relateName");
             String name = relateName.toString();
             if(name.contains("[") && name.contains("]")){
            	 String subject = name.substring(name.indexOf("[") + 1, name.indexOf("]"));
            	 if("null".equals(subject) || subject == null || "".equals(subject)){
            		 return "fail";
            	 }
             } else {
            	 return "fail";
             }
             List<Student> studentList = this.studentService.findStudentByTeamId(Integer.parseInt(relateId));
             for(Student stu : studentList){
        		 mlm = new MicroLessonMessage();
            	 mlm.setCreateDate(date);
            	 mlm.setFinishDate(finishedDate);
            	 mlm.setIsRead(false);
            	 mlm.setRealMicroList(microList.toString());
            	 mlm.setModifyDate(new Date());
            	 mlm.setPublisherName(publisherName);
            	 mlm.setPublisherUserId(publisherId);
            	 if (relateName != null && !(relateName instanceof JSONNull)) {
            		 mlm.setRelateName(relateName.toString());
                 }
            	 mlm.setStartDate(startDate);
            	 mlm.setStudentUserId(stu.getUserId());
            	 mlm.setTitle(title);
            	 mlm.setPublishUuid(publishUuid);
            	 this.microLessonMessageService.add(mlm);
             }
         }
         return "success";
    }

	@Override
	public Object subject(String userId, String appKey) {
		try {
			Student stu = this.studentService.findStudentByUserId(Integer.parseInt(userId));
	    	List<ExtSubject> subject = new ArrayList<ExtSubject>();
	    	if(stu != null){
	    		Integer teamId = stu.getTeamId();
	    		if(teamId != null){
	    			Team team = this.teamService.findTeamById(teamId);
	    			Grade grade = this.gradeService.findGradeById(team.getGradeId());
	    			List<SubjectGrade> subjectList = null;
	    			ExtSubject vo = null;
	    			if(grade!=null){
	    				subjectList = this.subjectGradeService.findSubjectGradeByGradeCode(grade.getSchoolId(),grade.getUniGradeCode() );
	    				for(SubjectGrade sbg : subjectList){
	    					vo = new ExtSubject();
	    					vo.setCode(sbg.getSubjectCode());
	    					vo.setName(sbg.getSubjectName());
	    					subject.add(vo);
	    				}
	    			}
	    		}
	    	}
	    	return new ResponseVo<List<ExtSubject>>("0", subject);
		} catch (Exception e) {
			ResponseInfo info = new ResponseInfo();
			info.setDetail("服务器繁忙...");
			info.setMsg("服务器繁忙");
			info.setParam("...");
			return new ResponseError(CommonCode.S$SERVER_BUSY, info);
		}
	}

}
