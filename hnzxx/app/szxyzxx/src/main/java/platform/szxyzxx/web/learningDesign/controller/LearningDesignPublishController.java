package platform.szxyzxx.web.learningDesign.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import platform.education.exam.model.Exam;
import platform.education.generalTeachingAffair.model.Grade;
import platform.education.generalTeachingAffair.model.School;
import platform.education.generalTeachingAffair.model.SchoolTermCurrent;
import platform.education.generalTeachingAffair.model.Student;
import platform.education.generalTeachingAffair.model.Teacher;
import platform.education.generalTeachingAffair.model.Team;
import platform.education.generalTeachingAffair.model.TeamTeacher;
import platform.education.generalTeachingAffair.service.GradeService;
import platform.education.generalTeachingAffair.service.SchoolTermCurrentService;
import platform.education.generalTeachingAffair.service.StudentService;
import platform.education.generalTeachingAffair.service.TeacherService;
import platform.education.generalTeachingAffair.service.TeamService;
import platform.education.generalTeachingAffair.service.TeamTeacherService;
import platform.education.generalTeachingAffair.vo.TeamTeacherCondition;
import platform.education.homework.model.Homework;
import platform.education.homework.model.HomeworkPublishedRecord;
import platform.education.homework.vo.HomeworkRelateVo;
import platform.education.learningDesign.contants.LearningDesignType;
import platform.education.learningDesign.model.LearningDesign;
import platform.education.learningDesign.model.LearningDesignPublish;
import platform.education.learningDesign.model.LearningDesignPublishedRecord;
import platform.education.learningDesign.model.LearningDesignRelate;
import platform.education.learningDesign.model.LearningDesignUserRecord;
import platform.education.learningDesign.model.LearningPlan;
import platform.education.learningDesign.service.LearningDesignPrepareService;
import platform.education.learningDesign.service.LearningDesignPublishedRecordService;
import platform.education.learningDesign.service.LearningDesignService;
import platform.education.learningDesign.service.LearningDesignUserRecordService;
import platform.education.learningDesign.vo.LearningDesignPublishedRecordCondition;
import platform.education.learningDesign.vo.LearningDesignRelateVo;
import platform.education.learningDesign.vo.LearningDesignStudyListVo;
import platform.education.learningDesign.vo.LearningDesignStudyRecordVo;
import platform.education.learningDesign.vo.LearningDesignVo;
import platform.education.material.model.Material;
import platform.education.message.contans.StatusDefaultContans;
import platform.education.message.model.Message;
import platform.education.message.service.MessageService;
import platform.education.micro.model.MiMicroPersonGroup;
import platform.education.micro.model.MiMicroPersonGroupUserId;
import platform.education.micro.model.MicroLesson;
import platform.education.micro.service.MiMicroPersonGroupService;
import platform.education.micro.service.MiMicroPersonGroupUserIdService;
import platform.education.micro.vo.MiMicroPersonGroupCondition;
import platform.education.micro.vo.MiMicroPersonGroupUserIdCondition;
import platform.education.resource.contants.ResourceType;
import platform.education.resource.contants.StudyFinishedFlag;
import platform.education.resource.model.Resource;
import platform.education.resource.model.ResourceLibrary;
import platform.education.resource.utils.IconUtil;
import platform.education.resource.utils.UUIDUtil;
import platform.education.resource.vo.ResourceCondition;
import platform.education.resource.vo.ResourceLibraryCondition;
import platform.service.storage.model.EntityFile;
import platform.service.storage.service.EntityFileService;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.contants.MessageCenterContants;
import platform.szxyzxx.web.common.contants.SysContants;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.PushMessageUtil;
import platform.szxyzxx.web.common.vo.UserInfo;
import platform.szxyzxx.web.message.contans.TagsTypeContans;
import platform.szxyzxx.web.micro.contants.ContansOfResource;
import platform.szxyzxx.web.resource.vo.MyResourceVo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

/**
 *
 * @author 罗志明
 */
@Controller
@RequestMapping(value = "/learningDesignpublish")
public class LearningDesignPublishController extends BaseController {

    //课件学习记录
    @javax.annotation.Resource
    private LearningDesignUserRecordService learningDesignUserRecordService;
    @javax.annotation.Resource
    private LearningDesignPrepareService learningDesignPrepareService;
    
    //课件发布记录
    @javax.annotation.Resource
    private LearningDesignPublishedRecordService learningDesignPublishedRecordService;
    
    @javax.annotation.Resource
    private MiMicroPersonGroupService miMicroPersonGroupService;
    @javax.annotation.Resource
    private MiMicroPersonGroupUserIdService microPersonGroupUserIdService;
    
    private static final String COMMON_DIR = "learningDesign/common";
    private static final String DIR = "learningDesign/publish";

    @RequestMapping(value = "/deletePublished")
    public String deletePublished(HttpServletRequest request, HttpServletResponse response) {
        String publishId = request.getParameter("publishId");
        String relateId = request.getParameter("relateId");
        this.learningDesignPrepareService.deletePublish(publishId, Integer.parseInt(relateId));
        return null;
    }

    @RequestMapping(value = "/resetDate")
    public String resetDate(HttpServletRequest request, HttpServletResponse response) {
        String publishId = request.getParameter("publishId");
        String relateId = request.getParameter("relateId");
        String startDate = request.getParameter("startDate");
        String finishedDate = request.getParameter("finishedDate");
        String[] ss = startDate.split(" ");
        String[] fs = finishedDate.split(" ");
        request.setAttribute("publishId", publishId);
        request.setAttribute("startDate", ss[0]);
        request.setAttribute("finishedDate", fs[0]);
        request.setAttribute("relateId", relateId);
        request.setAttribute("startClock", Integer.parseInt(ss[1]));
        request.setAttribute("finishedClock", Integer.parseInt(fs[1]));
        return DIR + "/resetDate";
    }

    @RequestMapping(value = "/updatePublishedDate")
    public String updatePublishedDate(HttpServletRequest request, HttpServletResponse response) throws ParseException {
        String publishId = request.getParameter("publishId");
        String relateId = request.getParameter("relateId");
        String startDate = request.getParameter("startDate");
        String finishedDate = request.getParameter("finishedDate");
        String startClock = request.getParameter("startClock");
        String finishedClock = request.getParameter("finishedClock");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH");
        Date sd = sdf.parse(startDate + " " + startClock);
        Date fd = sdf.parse(finishedDate + " " + finishedClock);
        LearningDesignRelate mlp = this.learningDesignPrepareService.updatePublishedDate(publishId, Integer.parseInt(relateId), sd, fd);
        return null;
    }

    @RequestMapping(value = "/preview")
    public String preview(HttpServletRequest request, HttpServletResponse response) {
        playMicroImpl(request);
        return DIR + "/preview";
    }

    @RequestMapping(value = "/play")
    public String play(HttpServletRequest request, HttpServletResponse response) {
   	 String objId = request.getParameter("objId");
     String study = request.getParameter("study");
     String microPublishedId = request.getParameter("microPublishedId");
     LearningDesign exam = null;
     Resource r=new Resource();
     if (microPublishedId != null && !"".equals(microPublishedId)) {
    	 if((objId==null||objId.equals(""))){
    		 
    		 JSONArray array = learningDesignPrepareService.getPublishedPlayJson(microPublishedId);
    		 if (array.size() > 0) {
    			 //只播第一个
    			 JSONObject micro = (JSONObject) array.get(0);
    			 String resourceid=(String)micro.get("resourceId");
    			 r=this.resourceService.findResourceById(Integer.valueOf(resourceid));
    			 exam = this.learningDesignService.findLearningDesignByUuid(r.getObjectId());
    		 }
    	 } else {
    		 
    		 JSONArray array = learningDesignPrepareService.getPublishedPlayJson(microPublishedId);
    		 if (array.size() != 0) {
    			 //寻找object对应的resourceId
    			 for(int i=0;i<array.size();i++){
    				 JSONObject micro = (JSONObject) array.get(i);
    				 String obId=(String)micro.get("id");
    				 if(obId.equals(objId)){
    					 String resourceid=(String)micro.get("resourceId");
    					 r=this.resourceService.findResourceById(Integer.valueOf(resourceid));
    				 }
    				 
    			 }
    			 
    			 
    		 }
    		 exam = this.learningDesignService.findLearningDesignByUuid(objId);
    	 }
         
     }
     if (exam != null&&r!=null) {
         MyResourceVo mrvo = new MyResourceVo();
         mrvo.setResEnt(r);
         String entityId = exam.getEntityId();
         if (entityId != null) {
             EntityFile entity = this.entityFileService.findFileByUUID(entityId);
             if (entity != null) {
                 mrvo.setThumbnailUrl(entity.getThumbnailUrl());
                 mrvo.setIconType(IconUtil.setIcon(entity.getExtension()));
                 request.setAttribute("entity", entity);
             }
         }
         request.setAttribute("res", mrvo);
     }
     if (study != null) {
         request.setAttribute("study", study);
     }
     request.setAttribute("microPublishedId", microPublishedId);
        return COMMON_DIR + "/play";
    }

    private void playMicroImpl(HttpServletRequest request) {
        String objId = request.getParameter("objId");
        String microPublishedId = request.getParameter("microPublishedId");
        String study = request.getParameter("study");
        LearningDesign exam = null;
        if (microPublishedId != null && !"".equals(microPublishedId) && (objId == null || "".equals(objId))) {
            JSONArray array = learningDesignPrepareService.getPublishedPlayJson(microPublishedId);
            if (array.size() > 0) {
                //只播第一个
                JSONObject micro = (JSONObject) array.get(0);
                exam = this.learningDesignService.findLearningDesignByUuid((String) micro.get("id"));
            }
        } else {
            exam = this.learningDesignService.findLearningDesignByUuid(objId);
        }
        if (exam != null) {
            MyResourceVo mrvo = new MyResourceVo();
            mrvo.setResEnt(exam);
            String entityId = exam.getEntityId();
            if (entityId != null) {
                EntityFile entity = this.entityFileService.findFileByUUID(entityId);
                if (entity != null) {
                    mrvo.setThumbnailUrl(entity.getThumbnailUrl());
                    mrvo.setIconType(IconUtil.setIcon(entity.getExtension()));
                    request.setAttribute("entity", entity);
                }
            }
            request.setAttribute("res", mrvo);
        }
        if (study != null) {
            request.setAttribute("study", study);
        }
        request.setAttribute("em", exam);
        request.setAttribute("microPublishedId", microPublishedId);
    }

    @RequestMapping(value = "/ajaxGetStudyJson")
    public String ajaxGetStudyJson(HttpServletRequest request, HttpServletResponse response, @CurrentUser UserInfo user) throws IOException {
        String microPublishId = request.getParameter("microPublishId");
        JSONArray array = learningDesignPrepareService.getPublishedPlayJson(microPublishId);
        List voList = new ArrayList();
        Integer finishedCount = 0;
        for (int i = 0; i < array.size(); i++) {
            MyResourceVo mrvo = new MyResourceVo();
            LearningDesignStudyListVo vo = new LearningDesignStudyListVo();
            JSONObject obj = array.getJSONObject(i);
            String microId = (String) obj.get("id");
            LearningDesign em = this.learningDesignService.findLearningDesignByUuid(microId);
            LearningDesignUserRecord ur = this.learningDesignUserRecordService.getUniqueRecord(user.getId(), microId, microPublishId);
            if (em != null) {
                EntityFile ent = this.entityFileService.findFileByUUID(em.getEntityId());
                mrvo.setThumbnailUrl(ent.getThumbnailUrl());
                mrvo.setIconType(IconUtil.setIcon(ent.getExtension()));
            }
            if (ur != null && ur.getLastPlayTime() != null) {
                Long lpt = ur.getLastPlayTime().longValue();
                String sd = timeMillisToString(lpt);
                vo.setFormatLastPlayTime(sd);
            }
            if (ur != null && ur.getFinishedFlag() != null) {
                if (ur.getFinishedFlag() == StudyFinishedFlag.FINISHED) {
                    finishedCount++;
                }
            }
            vo.setRecord(ur);
            vo.setLearningDesign(em);
            mrvo.setResEnt(vo);
            voList.add(mrvo);
        }
        request.setAttribute("microPublishId", microPublishId);
        request.setAttribute("finishedCount", finishedCount);
        request.setAttribute("microList", voList);
        return DIR + "/studyMicroList";
    }

    public String timeMillisToString(long timeMillis) {
        int minute;
        int second;
        String m;
        String s;
        timeMillis = timeMillis / 1000;
        minute = (int) (timeMillis / 60);
        second = (int) (timeMillis % 60);
        if (minute < 10) {
            m = "0" + minute;
        } else {
            m = String.valueOf(minute);
        }
        if (second < 10) {
            s = "0" + second;
        } else {
            s = String.valueOf(second);
        }
        return m + ":" + s;
    }

    @RequestMapping(value = "/publishLesson")
    public String publishLesson(HttpServletRequest request, HttpServletResponse response, @CurrentUser UserInfo user) throws ParseException, IOException {
        String publishData = request.getParameter("publishData");
        JSONObject obj = JSONObject.fromObject(publishData);
        String startDate = (String) obj.get("startDate");
        String finishedDate = (String) obj.get("finishedDate");
        String startClock = (String) obj.get("startClock");
        String finishedClock = (String) obj.get("finishedClock");
        JSONArray microList = (JSONArray) obj.get("microList");
        JSONArray classList = (JSONArray) obj.get("classList");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH");
        Date sd = sdf.parse(startDate + " " + startClock);
        Date fd = sdf.parse(finishedDate + " " + finishedClock);
        LearningDesignPublish mlp = this.learningDesignPrepareService.publishLearningDesign(microList, classList, sd, fd, null, user.getId(), user.getRealName(), LearningDesignType.COMMON_LEARNINGDESIGN);

        Message message = new Message();
        message.setAppId(SysContants.SYSTEM_APP_ID);
        message.setContent("您有新的课件学习！");
        message.setPosterId(user.getId());
        message.setPosterName(user.getRealName());
        message.setRecordStatus(StatusDefaultContans.ZERO);
        message.setTag(MessageCenterContants.PATH_CODE_LEARNING_DESIGN);
        List<Integer> receiverIdList = new ArrayList<Integer>();
        List<Integer> receiverIdList2 = new ArrayList<Integer>();
        for (int i = 0; i < classList.size(); i++) {
            JSONObject receivers = (JSONObject) classList.get(i);
            String receiverId = (String) receivers.get("relateId");
            String relateType = (String) receivers.get("relateType");
            if (relateType == TagsTypeContans.GROUP_TYPE || relateType.equals(TagsTypeContans.GROUP_TYPE)) {
            	receiverIdList2.add(Integer.parseInt(receiverId));
			}else if (relateType == TagsTypeContans.TEAM_TYPE || relateType.equals(TagsTypeContans.TEAM_TYPE)) {
				receiverIdList.add(Integer.parseInt(receiverId));
			}
        }
        if (receiverIdList !=null && !receiverIdList.isEmpty()) {
        	List<Student> studentList = this.studentService.findStudentListByListId(receiverIdList, TagsTypeContans.TEAM_ID, user.getSchoolId());
        	receiverIdList = getUserId(studentList);
        	PushMessageUtil.sendMessage(message, receiverIdList);
//          for(Integer userId : receiverIdList){
//          	PushMessageUtil.pushMessage(userId);
//          }
        	PushMessageUtil.pushMessageList(receiverIdList);
		}else if (receiverIdList2 !=null && !receiverIdList2.isEmpty()) {
			List<Integer> studentIds = new ArrayList<Integer>();
			for (Integer id : receiverIdList2) {
				MiMicroPersonGroupUserIdCondition miMicroPersonGroupUserIdCondition = new MiMicroPersonGroupUserIdCondition();
				miMicroPersonGroupUserIdCondition.setMicroPersonGroupId(id);
				List<MiMicroPersonGroupUserId> list = microPersonGroupUserIdService.findMiMicroPersonGroupUserIdByCondition(miMicroPersonGroupUserIdCondition);
				for (MiMicroPersonGroupUserId MiMicroPersonGroupUserId : list) {
					studentIds.add(MiMicroPersonGroupUserId.getUserId());
					MiMicroPersonGroupUserId.setType("1");
					microPersonGroupUserIdService.modify(MiMicroPersonGroupUserId);
				}
			}
		     PushMessageUtil.sendMessage(message, studentIds);
	         PushMessageUtil.pushMessageList(studentIds);
		}
        return null;
    }

    private List<Integer> getUserId(List<Student> studentList) {
        List<Integer> idList = new ArrayList<Integer>();
        if (studentList.size() > 0 && studentList != null) {
            for (Student student : studentList) {
                idList.add(student.getUserId());
            }
        }
        return idList;
    }

    @RequestMapping(value = "/publishManager")
    public String publishManager(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("page") Page page, @CurrentUser UserInfo user) throws ParseException {
    	String relateId = request.getParameter("relateId");
        Integer relateIdint = Integer.parseInt(relateId);
        String relateType = request.getParameter("relateType");
        List<Student> stList = new ArrayList<Student>();
        if (relateType == TagsTypeContans.TEAM_TYPE || relateType.equals(TagsTypeContans.TEAM_TYPE)) {
        	stList = this.studentService.findStudentOfTeam(relateIdint);
		}else if (relateType == TagsTypeContans.GROUP_TYPE || relateType.equals(TagsTypeContans.GROUP_TYPE)) {
				MiMicroPersonGroupUserIdCondition miMicroPersonGroupUserIdCondition = new MiMicroPersonGroupUserIdCondition();
				miMicroPersonGroupUserIdCondition.setMicroPersonGroupId(relateIdint);
				List<MiMicroPersonGroupUserId> list = microPersonGroupUserIdService.findMiMicroPersonGroupUserIdByCondition(miMicroPersonGroupUserIdCondition);
					for (MiMicroPersonGroupUserId miMicroPersonGroupUserId : list) {
						Student student = studentService.findOfUser(user.getSchoolId(), miMicroPersonGroupUserId.getUserId());
						stList.add(student);
					}
		}
        List<LearningDesignRelateVo> mlrList = this.learningDesignPrepareService.searchPublishedLesson(user.getId(), Integer.parseInt(relateId), relateType,page);
        List<LearningDesignRelateVo> reMlrList = new ArrayList<LearningDesignRelateVo>();
        Integer[] userId=new Integer[stList.size()];
        int i=0;
        for(Student s:stList){
        	userId[i]=s.getUserId();
        	i++;
        }
        for (LearningDesignRelateVo rv : mlrList) {
            Integer finishedCount = 0;
            Integer unFinishedCount = stList.size();
            if(stList.size()>0){
            	Integer hfc=this.learningDesignPublishedRecordService.findLearningDesignFinishCount(rv.getPublishMicroLessonId(), userId);
            	if(hfc!=null){
            		finishedCount=hfc;
            		unFinishedCount=stList.size()-finishedCount;
            	}
            }
            rv.setFinishedCount(finishedCount);
            rv.setUnFinishedCount(unFinishedCount);
            reMlrList.add(rv);
        }
        request.setAttribute("relateId", relateId);
        request.setAttribute("relateType", relateType);
        request.setAttribute("mlrList", reMlrList);
        return DIR + "/publishManager";
    }

    @RequestMapping(value = "/publishManagerIndex")
    public String publishManagerIndex(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("page") Page page, @CurrentUser UserInfo user) throws ParseException {
    	Map classGradeMap = getClassGradeMap(user, false, false,true);
        request.setAttribute("relateType", request.getParameter("relateType"));
        request.setAttribute("relateId", request.getParameter("relateId"));
        request.setAttribute("classGradeMap", classGradeMap);
        return DIR + "/publishManagerIndex";
    }

    @RequestMapping(value = "/prepareLesson")
    public String prepareLesson(HttpServletRequest request, HttpServletResponse response, @CurrentUser UserInfo user) {
        Map classGradeMap = getClassGradeMap(user, true, true,true);
        request.setAttribute("classGradeMap", classGradeMap);
        return DIR + "/prepareLesson";
    }

    @RequestMapping(value = "/batchReviews")
    public String batchReviews(HttpServletRequest request, HttpServletResponse response, @CurrentUser UserInfo user) {
        String microPublishId = request.getParameter("microPublishedId");
        String relateId = request.getParameter("relateId");
        String relateType = request.getParameter("relateType");
        Integer relateIdint = Integer.parseInt(relateId);
        List<Student> fst = new ArrayList();
        List<Student> ust = new ArrayList();
        List<Student> pst = new ArrayList();
        List<Student> stList = new ArrayList<Student>();
        if (relateType == TagsTypeContans.TEAM_TYPE || relateType.equals(TagsTypeContans.TEAM_TYPE)) {
        	stList = this.studentService.findStudentOfTeam(relateIdint);
		}else if (relateType == TagsTypeContans.GROUP_TYPE || relateType.equals(TagsTypeContans.GROUP_TYPE)) {
				MiMicroPersonGroupUserIdCondition miMicroPersonGroupUserIdCondition = new MiMicroPersonGroupUserIdCondition();
				miMicroPersonGroupUserIdCondition.setMicroPersonGroupId(relateIdint);
				List<MiMicroPersonGroupUserId> list = microPersonGroupUserIdService.findMiMicroPersonGroupUserIdByCondition(miMicroPersonGroupUserIdCondition);
					for (MiMicroPersonGroupUserId miMicroPersonGroupUserId : list) {
						Student student = studentService.findOfUser(user.getSchoolId(), miMicroPersonGroupUserId.getUserId());
						stList.add(student);
					}
		}
        if (stList != null && stList.size() > 0) {
            for (Student st : stList) {
                LearningDesignPublishedRecord mpr = this.learningDesignPrepareService.searchUserPublishedRecord(microPublishId, st.getUserId(), st.getName(), st.getStudentNumber());
                if (mpr.getFinishedFlag() == StudyFinishedFlag.FINISHED) {
                    fst.add(st);
                } else if (mpr.getFinishedFlag() == StudyFinishedFlag.NOT_FINISHED) {
                    ust.add(st);
                } else if (mpr.getFinishedFlag() == StudyFinishedFlag.PART_FINISHED) {
                    pst.add(st);
                }
            }
        }
        request.setAttribute("microPublishId", microPublishId);
        request.setAttribute("relateId", relateId);
        request.setAttribute("allList", stList);
        request.setAttribute("finishedList", fst);
        request.setAttribute("unFinishedList", ust);
        request.setAttribute("partFinishedList", pst);
        request.setAttribute("relateType", relateType);
        return DIR + "/batchReviews";
    }

    @RequestMapping(value = "/saveReviews")
    public String saveReviews(HttpServletRequest request, HttpServletResponse response, @CurrentUser UserInfo user) {
        String data = request.getParameter("reviewData");
        JSONObject obj = JSONObject.fromObject(data);
        String reviews = (String) obj.get("reviews");
        String microPublishId = (String) obj.get("microPublishId");
        Integer reward = (Integer) obj.get("reward");
        JSONArray studentList = (JSONArray) obj.get("studentList");
        for (int i = 0; i < studentList.size(); i++) {
            Integer userId = (Integer) studentList.get(i);
            LearningDesignPublishedRecord mpr = this.learningDesignPrepareService.updateUserPublishedRecord(microPublishId, userId, reviews, reward);
        }
        return null;
    }

    @RequestMapping(value = "/editReviews")
    public String editReviews(HttpServletRequest request, HttpServletResponse response, @CurrentUser UserInfo user) {
        String microPublishId = request.getParameter("microPublishedId");
        Integer userId = Integer.parseInt(request.getParameter("userId"));
        LearningDesignPublishedRecord mpr = this.learningDesignPrepareService.searchUserPublishedRecord(microPublishId, userId, null, null);
        request.setAttribute("mpr", mpr);
        return DIR + "/editReviews";
    }

    @RequestMapping(value = "/watchReviews")
    public String watchReviews(HttpServletRequest request, HttpServletResponse response, @CurrentUser UserInfo user) {
        String microPublishId = request.getParameter("microPublishedId");
        Integer userId = Integer.parseInt(request.getParameter("userId"));
        LearningDesignPublishedRecord mpr = this.learningDesignPrepareService.searchUserPublishedRecord(microPublishId, userId, null, null);
        request.setAttribute("mpr", mpr);
        return DIR + "/watchReviews";
    }

    @RequestMapping(value = "/publishDetails")
    public String publishDetails(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("page") Page page, @CurrentUser UserInfo user) {
        String microPublishId = request.getParameter("microPublishedId");
        String relateId = request.getParameter("relateId");
        String relateType = request.getParameter("relateType");
        String index = request.getParameter("index");
        Integer relateIdint = Integer.parseInt(relateId);
        Integer finishedCount = 0;
        Integer unFinishedCount = 0;
        Integer partFinishedCount = 0;
        List<LearningDesignPublishedRecord> recordList = new ArrayList<LearningDesignPublishedRecord>();
        List<Student> stList = new ArrayList<Student>();
        LearningDesignRelateVo rvo = new LearningDesignRelateVo();
        if (relateType != null && (relateType == TagsTypeContans.TEAM_TYPE || relateType.equals(TagsTypeContans.TEAM_TYPE))) {
        	stList = this.studentService.findStudentOfTeam(relateIdint);
        	rvo = this.learningDesignPrepareService.searchPublishedLesson(microPublishId, relateIdint,relateType);
		}else if (relateType != null && (relateType == TagsTypeContans.GROUP_TYPE || relateType.equals(TagsTypeContans.GROUP_TYPE))) {
				MiMicroPersonGroupUserIdCondition miMicroPersonGroupUserIdCondition = new MiMicroPersonGroupUserIdCondition();
				miMicroPersonGroupUserIdCondition.setMicroPersonGroupId(relateIdint);
				List<MiMicroPersonGroupUserId> list = microPersonGroupUserIdService.findMiMicroPersonGroupUserIdByCondition(miMicroPersonGroupUserIdCondition);
					for (MiMicroPersonGroupUserId miMicroPersonGroupUserId : list) {
						Student student = studentService.findOfUser(user.getSchoolId(), miMicroPersonGroupUserId.getUserId());
						stList.add(student);
					}
					rvo = this.learningDesignPrepareService.searchPublishedLesson(microPublishId, Integer.parseInt(relateId),relateType);
		}
        if (stList != null && stList.size() > 0) {
            for (Student st : stList) {
                LearningDesignPublishedRecord mpr = this.learningDesignPrepareService.searchUserPublishedRecord(microPublishId, st.getUserId(), st.getName(), st.getStudentNumber());
                if (mpr.getFinishedFlag() == StudyFinishedFlag.FINISHED) {
                    finishedCount++;
                } else if (mpr.getFinishedFlag() == StudyFinishedFlag.NOT_FINISHED) {
                    unFinishedCount++;
                } else if (mpr.getFinishedFlag() == StudyFinishedFlag.PART_FINISHED) {
                    partFinishedCount++;
                }
                recordList.add(mpr);
            }
        }
        request.setAttribute("micro", rvo);
        request.setAttribute("finishedCount", finishedCount);
        request.setAttribute("partFinishedCount", partFinishedCount);
        request.setAttribute("unFinishedCount", unFinishedCount);
        request.setAttribute("recordList", recordList);
        request.setAttribute("relateType", relateType);
        if (index != null && !"".equals(index)) {
            return DIR + "/publishDetailsIndex";
        } else {
            return DIR + "/publishDetailsList";
        }
    }

    @RequestMapping(value = "/getRecord")
    public String getRecord(HttpServletRequest request, HttpServletResponse response, @CurrentUser UserInfo user) throws IOException {
        String microId = request.getParameter("microId");
        String publishLessonId = request.getParameter("publishLessonId");
        Integer userId = user.getId();
        LearningDesignUserRecord ur = this.learningDesignUserRecordService.getUniqueRecord(userId, microId, publishLessonId);
        PrintWriter pw = setAjaxResponse(request, response);
        if (ur != null) {
            if (ur.getLastPlayTime() != null) {
                pw.print(ur.getLastPlayTime());
            } else {
                pw.print("fail");
            }
        } else {
            pw.print("fail");
        }
        return null;
    }

    @RequestMapping(value = "/saveRecord")
    public String saveRecord(HttpServletRequest request, HttpServletResponse response, @CurrentUser UserInfo user) {
        String microId = request.getParameter("microId");
        String lastPlayTime = request.getParameter("lastPlayTime");
        String finishedFlag = request.getParameter("finishedFlag");
        String publishLessonId = request.getParameter("publishLessonId");
        String publisherId = request.getParameter("publisherId");
        Integer userId = user.getId();
        LearningDesignUserRecord ur = this.learningDesignUserRecordService.getUniqueRecord(userId, microId, publishLessonId);
        if (ur != null) {
            ur.setModifyDate(new Date());
            ur.setPlayTime(ur.getPlayTime() + 1);
            if (finishedFlag != null && !"".equals(finishedFlag)) {
                int flag = Integer.parseInt(finishedFlag);
                if (flag == StudyFinishedFlag.FINISHED) {
                    ur.setFinishedDate(new Date());

                    //推送消息
                    if (publisherId != null && !"".equals(publisherId)) {
                        Message message = new Message();
                        message.setAppId(SysContants.SYSTEM_APP_ID);
                        message.setContent("您好，您的学生" + user.getRealName() + "完成了您布置的课件作业！");
                        message.setPosterId(user.getId());
                        message.setPosterName(user.getRealName());
                        message.setRecordStatus(StatusDefaultContans.ZERO);
                        message.setTag(MessageCenterContants.FINISHED_PATH_CODE_LEARNING_DESIGN);
                        PushMessageUtil.sendMessage(message, Integer.parseInt(publisherId));
                        PushMessageUtil.pushMessage(Integer.parseInt(publisherId));
                    }
                }
                ur.setFinishedFlag(flag);
            }
            if (lastPlayTime != null && !"".equals(lastPlayTime) && !"0".equals(lastPlayTime)) {
                ur.setLastPlayTime(Double.parseDouble(lastPlayTime));
            }
            this.learningDesignUserRecordService.modify(ur);
        } else {
            ur = new LearningDesignUserRecord();
            ur.setCreateDate(new Date());
            ur.setModifyDate(new Date());
            ur.setMicroId(microId);
            ur.setUserId(user.getId());
            ur.setUserName(user.getRealName());
            ur.setPlayTime(1);
            ur.setPublishLessonId(publishLessonId);
            if (finishedFlag != null && !"".equals(finishedFlag)) {
                int flag = Integer.parseInt(finishedFlag);
                if (flag == StudyFinishedFlag.FINISHED) {
                    ur.setFinishedDate(new Date());

                   /* //推送消息
                    if (publisherId != null && !"".equals(publisherId)) {
                        Message message = new Message();
                        message.setAppId(SysContants.SYSTEM_APP_ID);
                        message.setContent("您好，您的学生" + user.getRealName() + "完成了您布置的作业！");
                        message.setPosterId(user.getId());
                        message.setPosterName(user.getRealName());
                        message.setRecordStatus(StatusDefaultContans.ZERO);
                        message.setTag(MessageCenterContants.FINISHED_PATH_CODE_HOMEWORK);
                        PushMessageUtil.sendMessage(message, Integer.parseInt(publisherId));
                        PushMessageUtil.pushMessage(Integer.parseInt(publisherId));
                    }*/
                }
                ur.setFinishedFlag(flag);
            }
            this.learningDesignUserRecordService.add(ur);
        }

        return null;
    }

    @RequestMapping(value = "/myLearningDesign")
    public String myLearningDesign(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("page") Page page, @CurrentUser UserInfo user) {
        String index = request.getParameter("index");
        List<MyResourceVo> reslist = new ArrayList<MyResourceVo>();
        ResourceCondition condition=new ResourceCondition();
        condition.setUserId(user.getId());
        condition.setIsPersonal(true);
        condition.setResType(ResourceType.LEARNING_DESIGN);
        condition.setIsDeleted(false);
        List<Resource> resourceList = this.resourceService.findResourceByCondition(condition,page, Order.desc("create_date"));
        reslist=findVoByType(resourceList);
        request.setAttribute("reslist",reslist );
        request.setAttribute("ladsType", LearningDesignType.LADS_LEARNINGDESIGN);
        if (index != null && !"".equals(index)) {
            return DIR + "/myLearningDesignIndex";
        } else {
            return DIR + "/myLearningDesignList";
        }
    }

    @RequestMapping(value = "/studyList")
    public String studyList(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("page") Page page, @CurrentUser UserInfo user) throws IOException {
        Student s = this.studentService.findStudentById(user.getStudentId());
        if (s != null) {
        	Team team = this.teamService.findCurrentTeamOfStudent(s.getUserId(), user.getSchoolYear());
            Integer teamId = team != null ? team.getId() : null;
            List<Integer> relateId = new ArrayList<Integer>();
       	 	MiMicroPersonGroupUserIdCondition microPersonGroupUserIdCondition = new MiMicroPersonGroupUserIdCondition();
            microPersonGroupUserIdCondition.setUserId(user.getId());
            microPersonGroupUserIdCondition.setType("1");
            List<MiMicroPersonGroupUserId> microPersonGroupUserIds = microPersonGroupUserIdService.findMiMicroPersonGroupUserIdByCondition(microPersonGroupUserIdCondition);
            for (MiMicroPersonGroupUserId miMicroPersonGroupUserId : microPersonGroupUserIds) {
           	 if (miMicroPersonGroupUserId !=null) {
           		 relateId.add(miMicroPersonGroupUserId.getMicroPersonGroupId());
				}
    		}
            if (teamId != null) {
            	relateId.add(teamId);
                Date fdate = new Date();
                List<LearningDesignRelateVo> mlrvList = this.learningDesignPrepareService.searchHistoryPublishedLesson(null, relateId, null, LearningDesignType.COMMON_LEARNINGDESIGN, fdate, false, page);
                request.setAttribute("microList", mlrvList);
            } else {
                request.setAttribute("errorFlag", "no_class");
            }
        } else {
            request.setAttribute("errorFlag", "not_a_student");
        }
        return DIR + "/studyList";
    }

    @RequestMapping(value = "/studyHistory")
    public String studyHistory(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("page") Page page, @CurrentUser UserInfo user) throws IOException {
        Student s = this.studentService.findStudentById(user.getStudentId());
        String index = request.getParameter("index");
        if (s != null) {
        	Team team = this.teamService.findCurrentTeamOfStudent(s.getUserId(), user.getSchoolYear());
            Integer teamId = team != null ? team.getId() : null;
            List<Integer> relateId = new ArrayList<Integer>();
       	 	MiMicroPersonGroupUserIdCondition microPersonGroupUserIdCondition = new MiMicroPersonGroupUserIdCondition();
            microPersonGroupUserIdCondition.setUserId(user.getId());
            microPersonGroupUserIdCondition.setType("1");
            List<MiMicroPersonGroupUserId> microPersonGroupUserIds = microPersonGroupUserIdService.findMiMicroPersonGroupUserIdByCondition(microPersonGroupUserIdCondition);
            for (MiMicroPersonGroupUserId miMicroPersonGroupUserId : microPersonGroupUserIds) {
           	 if (miMicroPersonGroupUserId !=null) {
           		 relateId.add(miMicroPersonGroupUserId.getMicroPersonGroupId());
				}
    		}
            if (teamId != null) {
                Date fdate = new Date();
                relateId.add(teamId);
                List<LearningDesignStudyRecordVo> msrvList = new ArrayList();
                List<String> subjectList = this.learningDesignPrepareService.findSubjectNameByLearningDesign(null, relateId, fdate, true);
                String subjectName = request.getParameter("subjectName");
                //搜索全部
                if ("all".equals(subjectName)) {
                    subjectName = null;
                }
                List<LearningDesignRelateVo> mlrvList = this.learningDesignPrepareService.searchHistoryPublishedLesson(null, relateId, subjectName, LearningDesignType.COMMON_LEARNINGDESIGN, fdate, true, page);
                if (mlrvList != null && mlrvList.size() > 0) {
                    for (LearningDesignRelateVo mr : mlrvList) {
                        LearningDesignPublishedRecord mpr = this.learningDesignPrepareService.searchUserPublishedRecord(mr.getPublishMicroLessonId(), user.getId(), s.getName(), s.getStudentNumber());
                        String rsn = mr.getRelateName().substring(mr.getRelateName().indexOf("[") + 1, mr.getRelateName().indexOf("]"));
                        LearningDesignStudyRecordVo vo = new LearningDesignStudyRecordVo();
                        vo.setMlrv(mr);
                        vo.setMpr(mpr);
                        msrvList.add(vo);
                    }
                }
                request.setAttribute("subjectList", subjectList);
                request.setAttribute("recordList", msrvList);
            } else {
                request.setAttribute("errorFlag", "no_class");
            }
        } else {
            request.setAttribute("errorFlag", "not_a_student");
        }
        if (index != null && !"".equals(index)) {
            return DIR + "/studyHistoryIndex";
        } else {
            return DIR + "/studyHistoryList";
        }
    }

    private Map getClassGradeMap(UserInfo user, boolean includeSubject, boolean includeSameClass,boolean includeType) {
    	Map classGradeMap = new LinkedHashMap();
        SchoolTermCurrent schoolTermCurrent = schoolTermCurrentService.findSchoolTermCurrentBySchoolId(user.getSchoolId());
        Teacher teacher = this.teacherService.findOfUser(user.getSchoolId(), user.getId());
        if (teacher != null && schoolTermCurrent != null) {
        	TeamTeacherCondition teamTeacherCondition = new TeamTeacherCondition();
            teamTeacherCondition.setTeacherId(teacher.getId());
            //1 班主任  2 任课教师
            teamTeacherCondition.setType(2);
            teamTeacherCondition.setSchoolYear(schoolTermCurrent.getSchoolYear());
            List<TeamTeacher> teamTeacherList = teamTeacherService.findTeamTeacherByCondition(teamTeacherCondition, null, Order.asc("grade_id"));
            MiMicroPersonGroupCondition miMicroPersonGroupCondition = new MiMicroPersonGroupCondition();
            miMicroPersonGroupCondition.setUserId(teacher.getUserId());
            for (TeamTeacher tt : teamTeacherList) {
                Grade grade = this.gradeService.findGradeById(tt.getGradeId());
                if (grade != null) {
                    List<Map> classList = new ArrayList();
                    String viewName = grade.getName() + "&&" + grade.getUniGradeCode();
                    if (classGradeMap.containsKey(viewName)) {
                        classList = (List<Map>) classGradeMap.get(viewName);
                    }
                    miMicroPersonGroupCondition.setGradeId(tt.getGradeId());
                	List<MiMicroPersonGroup> personGroups = this.miMicroPersonGroupService.findMiMicroPersonGroupByCondition(miMicroPersonGroupCondition);
                	if (!personGroups.isEmpty() && personGroups != null) {
                		if (!classGradeMap.containsKey(viewName)) {
                			for (MiMicroPersonGroup personGroup : personGroups) {
                				Map mmap = new HashMap();
                				String personGroupName = personGroup.getName();
                				if (includeType) {
									personGroupName = personGroupName + "&&"+TagsTypeContans.GROUP_TYPE;
								}
                    			mmap.put(personGroup.getId(), personGroupName);
                    			classList.add(mmap);
        					}
                        }
                		
					}
                    Team team = this.teamService.findTeamById(tt.getTeamId());
                    if (team != null) {
                        Map map = new HashMap();
                        String classSubjectName = team.getName();
                        if (includeSubject) {
                            classSubjectName = classSubjectName + "   [" + tt.getSubjectName() + "]";
                        }
                        if (includeType) {
                        	classSubjectName = classSubjectName + "&&" + TagsTypeContans.TEAM_TYPE;
						}
                        map.put(team.getId(), classSubjectName);
                        if (includeSameClass) {
                            classList.add(map);
                        } else if (!classList.contains(map)) {
                            classList.add(map);
                        }
                    }
                    classGradeMap.put(viewName, classList);
                }
            }
        }
        return classGradeMap;
    }

    private PrintWriter setAjaxResponse(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", -1);
        return response.getWriter();
    }
    /**
     * 通过资源集合和类型封装
     * @param resourceList
     * @param typeint
     * @return
     */
    private List<MyResourceVo> findVoByType(List<Resource> resourceList){
    	List<MyResourceVo> list=new ArrayList<MyResourceVo>();
    	 for(Resource r:resourceList){
    		  MyResourceVo mrvo = new MyResourceVo();
    		  EntityFile ent=new EntityFile();
    		  mrvo.setResEnt(r);
    		  if(ResourceType.EXAM==r.getResType()){
    			  Exam em = new Exam();
    			  em=  this.examService.findExamByUuid(r.getObjectId());
    			  if(em!=null){
            		  
                	  ent = this.entityFileService.findFileByUUID(em.getEntityId());
            	  }
    		  }else if(ResourceType.HOMEWORK==r.getResType()){
            	  Homework em = new Homework();
            	  em=  this.homeworkService.findHomeworkByUuid(r.getObjectId());
            	  mrvo.setResEnt(r);
            	  if(em!=null){
            		  
            		   ent = this.entityFileService.findFileByUUID(em.getEntityId());
            	  }
    		  }else if(ResourceType.LEARNING_DESIGN==r.getResType()){
            	  LearningDesign em = new LearningDesign();
            
            	  em=  this.learningDesignService.findLearningDesignByUuid(r.getObjectId());
            	  if(em!=null){
            		   ent = this.entityFileService.findFileByUUID(em.getEntityId());
            		   mrvo.setEntityId(em.getEntityId());
            		   mrvo.setLearnDesignType(em.getType());
            	  }
    		  }else if(ResourceType.MATERIAL==r.getResType()){
    			  Material em=new Material();
    			  em=this.materialService.findMaterialByUuid(r.getObjectId());
            	  if(em!=null){
           		   ent = this.entityFileService.findFileByUUID(em.getEntityId());
           	      }
    		  }else if(ResourceType.MICRO==r.getResType()){
    			  MicroLesson em= new MicroLesson();
    			  em=this.microLessonService.findMicroLessonByUuid(r.getObjectId());
            	  if(em!=null)
            	  {
              		   ent = this.entityFileService.findFileByUUID(em.getEntityId());
              	  }
    		  }else if(ResourceType.LEARNING_PLAN == r.getResType()){
    			  LearningPlan lp = new LearningPlan();
    			  lp = this.learningPlanService.findLearningPlanByUuid(r.getObjectId());
    			  if(lp!=null){
    				  ent = this.entityFileService.findFileByUUID(lp.getEntityId());
    			  }
    		  }else if(ResourceType.OTHER==r.getResType()){
    			  ent = this.entityFileService.findFileByUUID(r.getObjectId());
    		  } 
    		  if (ent != null && ent.getThumbnailUrl() != null) {
    	            mrvo.setThumbnailUrl(ent.getThumbnailUrl());
    	            mrvo.setIconType(IconUtil.setIcon(ent.getExtension()));
    	        } else {
    	            mrvo.setThumbnailUrl("");
    	        }
    		  list.add(mrvo);
    	 }
    	 
    	
    	return list;
    }
    
    /**
     * 我上传的资源
     * @param 
     */
    @RequestMapping(value = "/myResource")
    public String myResource(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("page") Page page,
            @CurrentUser UserInfo user) {
        String index = request.getParameter("index");
        String title = request.getParameter("title");
        String resType = request.getParameter("resType");
        String personType = request.getParameter("personType");
        if(personType==null){
        	personType=ContansOfResource.SCHOOLRESOURCE;
        }
//        Integer relateAppId = this.getRelateApp(request);
        page.setPageSize(5);
        List<MyResourceVo> list = new ArrayList<MyResourceVo>();
            Integer typeint = Integer.parseInt(resType);
            ResourceLibraryCondition resourceLibraryCondition = new ResourceLibraryCondition();
    		resourceLibraryCondition.setOwerId(user.getSchoolId());
    		List<ResourceLibrary> resourceLibraryList = this.resourceLibraryService.findResourceLibraryByCondition(resourceLibraryCondition);
    		ResourceLibrary resourceLibrary = new ResourceLibrary();
    		if (resourceLibraryList != null && resourceLibraryList.size() > 0) {//如果存在取对应的uui
    			resourceLibrary = resourceLibraryList.get(0);
    		} else {//如果不存在添加对应的记录
    			ResourceLibrary resourceLibraryAdd = new ResourceLibrary();
    			resourceLibraryAdd.setOwerId(user.getSchoolId());
    			resourceLibraryAdd.setUuid(UUIDUtil.getUUID());//获取唯一值uuid
    			School  school=schoolService.findSchoolById(user.getSchoolId());
    			resourceLibraryAdd.setName(school.getName());
//    			resourceLibrary.setAppId(relateAppId);
    			resourceLibrary = this.resourceLibraryService.add(resourceLibraryAdd);
    		}
            ResourceCondition resResourceCondition = new ResourceCondition();
            resResourceCondition.setUserId(user.getId());
           
            if(resType!=null&&!resType.equals("")){
            	
            	resResourceCondition.setResType(Integer.valueOf(resType));
            }
            resResourceCondition.setTitle(title);
          //校本资源筛选
          if(personType.equals(ContansOfResource.SCHOOLRESOURCE)){
          	resResourceCondition.setLibraryId(resourceLibrary.getUuid());
          	resResourceCondition.setIsPersonal(false);
          	resResourceCondition.setVerify(ResourceCondition.DEFAULT_UPLOAD_YES);
          }
          //各人资源筛选
          if(personType.equals(ContansOfResource.PERSONRESOURCE)){
          	resResourceCondition.setIsPersonal(true);
          } 
          List<Resource> resourceList = this.resourceService.findResourceByCondition(resResourceCondition,page, Order.desc("create_date"));
          list=findVoByType(resourceList);
            String Url="/myResource";
            if(ContansOfResource.SHARERESOURCE.equals(personType)){
            	Url="/myResource/myShare";
            }else if(ContansOfResource.FAVRESOURCE.equals(personType)){
            	Url="/favResource";
            }
            request.setAttribute("Url", Url);
            request.setAttribute("reslist", list);
            request.setAttribute("resType", resType);
            request.setAttribute("personType", personType);
      

        if (index != null && !"".equals(index)) {
        	return DIR + "/myLearningDesignIndex";
        } else {
        	return DIR + "/myLearningDesignList";
        }
    }
    /**
     * 我收藏的资源
     * @param 
     */
    @RequestMapping(value = "/favResource")
    public String favResource(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("page") Page page, @CurrentUser UserInfo user) {
        String index = request.getParameter("index");
        String title = request.getParameter("title");
        String resType = request.getParameter("resType");
        String personType=request.getParameter("personType");
        page.setPageSize(5);
        List<MyResourceVo> list = new ArrayList<MyResourceVo>();
//        String playUrl = "";
        List<Resource> resources=new ArrayList<Resource>();
            Integer typeint = Integer.parseInt(resType);
            resources=this.resourceService.findMyFavResource(user.getId(), typeint,title,page,null);
            list=findVoByType(resources);
            String Url="/myResource";
            if(ContansOfResource.SHARERESOURCE.equals(personType)){
            	Url="/myResource/myShare";
            }else if(ContansOfResource.FAVRESOURCE.equals(personType)){
            	Url="/favResource";
            }
            request.setAttribute("reslist", list);
            request.setAttribute("resType", resType);
            request.setAttribute("personType",personType);
            request.setAttribute("Url", Url);
        if (index != null && !"".equals(index)) {
        	return DIR + "/myLearningDesignIndex";
        } else {
        	return DIR + "/myLearningDesignList";
        }
    }
    /**
     * 我的共享
     * @param 
     */
    @RequestMapping(value = "/myResource/myShare")
    public String myShare(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("page") Page page,
            @CurrentUser UserInfo user) {
        String index = request.getParameter("index");
        String resType = request.getParameter("resType");
        String personType = request.getParameter("personType");
        String title=request.getParameter("title");
        Integer typeint=Integer.valueOf(resType);
        List<Resource> resources=new ArrayList<Resource>();
        resources=this.resourceService.findMyShareResource(user.getId(),typeint,title,page,null);
        List<MyResourceVo> vos=new ArrayList<MyResourceVo>();
        vos=findVoByType(resources);
        String Url="/myResource";
        if(ContansOfResource.SHARERESOURCE.equals(personType)){
        	Url="/myResource/myShare";
        }else if(ContansOfResource.FAVRESOURCE.equals(personType)){
        	Url="/favResource";
        }
        request.setAttribute("Url", Url);
        request.setAttribute("reslist", vos);
        request.setAttribute("resType", resType);
        request.setAttribute("personType", personType);
        if (index != null && !"".equals(index)) {
        	return DIR + "/myLearningDesignIndex";
        } else {
        	return DIR + "/myLearningDesignList";
        }
    }
    
}
