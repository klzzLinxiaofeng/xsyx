package platform.szxyzxx.web.micro.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import net.sf.json.JSONArray;
import net.sf.json.JSONNull;
import net.sf.json.JSONObject;
import platform.education.exam.model.Exam;
import platform.education.exam.model.ExamPublish;
import platform.education.exam.model.ExamPublishedRecord;
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
import platform.education.learningDesign.model.LearningDesign;
import platform.education.learningDesign.model.LearningPlan;
import platform.education.material.model.Material;
import platform.education.message.contans.StatusDefaultContans;
import platform.education.message.model.Message;
import platform.education.message.service.MessageService;
import platform.education.micro.contants.MicroType;
import platform.education.micro.model.MiMicroPersonGroup;
import platform.education.micro.model.MiMicroPersonGroupUserId;
import platform.education.micro.model.MicroLesson;
import platform.education.micro.model.MicroLessonBgpicture;
import platform.education.micro.model.MicroLessonMessage;
import platform.education.micro.model.MicroLessonPublish;
import platform.education.micro.model.MicroLessonRelate;
import platform.education.micro.model.MicroPublishedRecord;
import platform.education.micro.model.MicroUserRecord;
import platform.education.micro.service.MiMicroPersonGroupService;
import platform.education.micro.service.MiMicroPersonGroupUserIdService;
import platform.education.micro.service.MicroLessonBgpictureService;
import platform.education.micro.service.MicroLessonMessageService;
import platform.education.micro.service.MicroLessonService;
import platform.education.micro.service.MicroPrepareService;
import platform.education.micro.service.MicroPublishedRecordService;
import platform.education.micro.service.MicroUserRecordService;
import platform.education.micro.vo.MiMicroPersonGroupCondition;
import platform.education.micro.vo.MiMicroPersonGroupUserIdCondition;
import platform.education.micro.vo.MicroLessonBgpictureCondition;
import platform.education.micro.vo.MicroLessonBgpictureVo;
import platform.education.micro.vo.MicroLessonFinishCount;
import platform.education.micro.vo.MicroLessonRelateVo;
import platform.education.micro.vo.MicroLessonVo;
import platform.education.micro.vo.MicroPublishedRecordCondition;
import platform.education.micro.vo.MicroStudyListVo;
import platform.education.micro.vo.MicroStudyRecordVo;
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
import platform.service.storage.service.FileService;
import platform.service.storage.vo.FileResult;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.contants.MessageCenterContants;
import platform.szxyzxx.web.common.contants.SysContants;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.PushMessageUtil;
import platform.szxyzxx.web.common.util.SessionManager;
import platform.szxyzxx.web.common.util.SzxyHttpClientRequest;
import platform.szxyzxx.web.common.vo.UserInfo;
import platform.szxyzxx.web.message.contans.TagsTypeContans;
import platform.szxyzxx.web.micro.contants.ContansOfResource;
import platform.szxyzxx.web.resource.vo.MyResourceVo;

/**
 *
 * @author 罗志明
 */
@Controller
@RequestMapping(value = "/micropublish")
public class MicroPublishController extends BaseController {

   
    //微课发布
    @javax.annotation.Resource
    private MicroPrepareService microPrepareService;
    //微课学习记录
    @javax.annotation.Resource
    private MicroUserRecordService microUserRecordService;
    
    // 微课发布记录
    @javax.annotation.Resource
    private MicroPublishedRecordService microPublishedRecordService;
    //微课星背景图
    @javax.annotation.Resource
    private MicroLessonBgpictureService microLessonBgpictureService;
    //微课学习消息
   	@javax.annotation.Resource
  	private MicroLessonMessageService microLessonMessageService;
    
    //人员分组
    @javax.annotation.Resource
    private MiMicroPersonGroupService miMicroPersonGroupService;
    @javax.annotation.Resource
    private MiMicroPersonGroupUserIdService microPersonGroupUserIdService;
    @javax.annotation.Resource(name = "publishExam_taskExecutor")
	private TaskExecutor taskExecutor;
    
    private static final String COMMON_DIR = "micro/common";
    private static final String DIR = "micro/publish";
    @RequestMapping(value = "/deletePublished")
    public String deletePublished(HttpServletRequest request, HttpServletResponse response) {
        String publishId = request.getParameter("publishId");
        String relateId = request.getParameter("relateId");
        this.microPrepareService.deletePublish(publishId, Integer.parseInt(relateId));
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
        MicroLessonRelate mlp = this.microPrepareService.updatePublishedDate(publishId, Integer.parseInt(relateId), sd, fd);
        return null;
    }

    @RequestMapping(value = "/mainJson")
    @ResponseBody
    public String getMainJson(HttpServletRequest request, HttpServletResponse response) {
        String jsonPath = request.getParameter("jsonPath");
        org.json.JSONObject jsonObject = SzxyHttpClientRequest.doGetRequest(jsonPath, null);
        String jsonString = "";
        if (!StringUtils.isEmpty(jsonObject)) {
            jsonString = jsonObject.toString();            
        }
        return jsonString;
    }

    @RequestMapping(value = "/propertyJson")
    @ResponseBody
    public String getPropertyJson(HttpServletRequest request, HttpServletResponse response) {
        String propertyPath = request.getParameter("propertyPath");
        org.json.JSONObject jsonObject = SzxyHttpClientRequest.doGetRequest(propertyPath, null);
        String jsonString = "";
        if (!StringUtils.isEmpty(jsonObject)) {
            jsonString = jsonObject.toString();            
        }
        return jsonString;
    }

    @RequestMapping(value = "/preview")
    public String preview(HttpServletRequest request, HttpServletResponse response) {
        playMicroImpl(request);
        return DIR + "/preview";
    }
    
    @RequestMapping(value = "/play")
    public String play(HttpServletRequest request, HttpServletResponse response) {
        playMicroImpl(request);
        return COMMON_DIR + "/play";
    }
    
    private void playMicroImpl(HttpServletRequest request) {
        String flag = request.getParameter("flag");
        String microId = request.getParameter("microId");
        String microPublishedId = request.getParameter("microPublishedId");
        List<MicroLessonVo> microList = new ArrayList<MicroLessonVo>();
        if (StringUtils.isEmpty(flag)) {
            flag = "0";
        }
        if (microPublishedId != null && !"".equals(microPublishedId) && (microId == null || "".equals(microId))) {
            JSONArray array = microPrepareService.getPublishedPlayJson(microPublishedId);
            for (int i = 0; i < array.size(); i++) {
                JSONObject micro = (JSONObject) array.get(i);
                MicroLesson ml = this.microLessonService.findMicroLessonByUuid((String) micro.get("id"));
                if(ml != null){
                	MicroLessonVo vo = BeanSettings(ml);
                	List<MicroLessonBgpictureVo> vos = bgSettings(ml.getUuid());
                	vo.setMlbs(vos);
                	microList.add(vo);
                }
            }
        } else {
            MicroLesson ml = this.microLessonService.findMicroLessonByUuid(microId);
            if(ml != null){
            	MicroLessonVo vo = BeanSettings(ml);
            	List<MicroLessonBgpictureVo> vos = bgSettings(ml.getUuid());
            	vo.setMlbs(vos);
            	microList.add(vo);
            }
        }
        request.setAttribute("type", MicroType.MICRO_COURSE);
        request.setAttribute("microPublishedId", microPublishedId);
        request.setAttribute("microList", microList);
        request.setAttribute("flag", flag);
    }
    
    private List<MicroLessonBgpictureVo> bgSettings(String uuid){
    	//设置背景文件的路径
    	MicroLessonBgpictureCondition mlbCondition = new MicroLessonBgpictureCondition();
    	mlbCondition.setLessonId(uuid);
    	List<MicroLessonBgpicture> mlbs = microLessonBgpictureService.findMicroLessonBgpictureByCondition(mlbCondition);
    	List<MicroLessonBgpictureVo> vos = new ArrayList<MicroLessonBgpictureVo>();
    	if(mlbs.size() > 0){
    		for(MicroLessonBgpicture mlb : mlbs){
    			MicroLessonBgpictureVo vo = new MicroLessonBgpictureVo();
    			BeanUtils.copyProperties(mlb, vo);
    			String entityId = mlb.getEntityId();
    			if(entityId != null){
    				FileResult fileResult = fileService.findFileByUUID(entityId);
    				vo.setBgPath(fileResult.getEntityFile().getRelativePath());
    			}
    			vos.add(vo);
    		}
    	}
    	return vos;
    }
    /**
     * 设置MicroLessonVo的属性
     * @param ml
     * @return
     */
    private MicroLessonVo BeanSettings(MicroLesson ml){
    	if(ml != null){
        	MicroLessonVo vo = new MicroLessonVo();
        	BeanUtils.copyProperties(ml, vo);
        	//设置对应文件的路径
//        	String bgpictureEntityId = ml.getBgpictureEntityId();
        	String jsonEntityId = ml.getJsonEntityId();
        	String mediaEntityId = ml.getMediaEntityId();
        	String propertyEntityId = ml.getPropertyEntityId();
        	String logoEntityId = ml.getLogoEntityId();
        	if (!StringUtils.isEmpty(logoEntityId)) {
        		FileResult logoFile = fileService.findFileByUUID(logoEntityId);
        		if (logoFile.getEntityFile() != null) {
        			vo.setLogoPath(logoFile.getEntityFile().getRelativePath());
        		}
        		
        	}
//        	if (!StringUtils.isEmpty(bgpictureEntityId)) {
//        		FileResult bgpictureFile = fileService.findFileByUUID(bgpictureEntityId);
//        		if (bgpictureFile.getEntityFile() != null) {
//        			vo.setBgpicturePath(bgpictureFile.getEntityFile().getRelativePath());
//        		}
//        	}
        	if (!StringUtils.isEmpty(jsonEntityId)) {
        		FileResult jsonFile = fileService.findFileByUUID(jsonEntityId);
        		if (jsonFile.getEntityFile() != null) {
        			vo.setJsonPath(jsonFile.getEntityFile().getRelativePath());
        		}
        	}
        	if (!StringUtils.isEmpty(mediaEntityId)) {
        		FileResult mediaFile = fileService.findFileByUUID(mediaEntityId);
        		if (mediaFile.getEntityFile() != null) {
        			vo.setMediaPath(mediaFile.getEntityFile().getRelativePath());
        		}
        	}
        	if (!StringUtils.isEmpty(propertyEntityId)) {
        		FileResult propertyFile = fileService.findFileByUUID(propertyEntityId);
        		if (propertyFile.getEntityFile() != null) {
        			vo.setPropertyPath(propertyFile.getEntityFile().getRelativePath());
        		}
        	}
        	return vo;
        }
    	return null;
    }
//    private OutputStream responseFilter(HttpServletResponse response){
//    	response.addHeader("content-type ","application/x-download");
//        response.setContentType("application/x-download");
//        response.setContentLength(ent.getSize().intValue());
//        response.setHeader("Content-Disposition", "attachment;filename=" + filename + "." + suffix);
//        OutputStream out = response.getOutputStream();
//    }

    @RequestMapping(value = "/ajaxGetStudyJson")
    public String ajaxGetStudyJson(HttpServletRequest request, HttpServletResponse response, @CurrentUser UserInfo user) throws IOException {
        String microPublishId = request.getParameter("microPublishId");
        JSONArray array = microPrepareService.getPublishedPlayJson(microPublishId);
        List<MyResourceVo> voList = new ArrayList<MyResourceVo>();
        Integer finishedCount = 0;
        for (int i = 0; i < array.size(); i++) {
            MyResourceVo mrvo = new MyResourceVo();
            MicroStudyListVo vo = new MicroStudyListVo();
            JSONObject obj = array.getJSONObject(i);
            String microId = (String) obj.get("id");
            MicroUserRecord ur = this.microUserRecordService.getUniqueRecord(user.getId(), microId, microPublishId);
            vo.setMicro(obj);
            vo.setRecord(ur);
            if (ur != null && ur.getLastPlayTime() != null) {
                Long lpt = ur.getLastPlayTime().longValue();
                String sd = timeMillisToString(lpt.intValue());
                vo.setFormatLastPlayTime(sd);
            }
            if (ur != null && ur.getFinishedFlag() != null) {
                if (ur.getFinishedFlag() == StudyFinishedFlag.FINISHED) {
                    finishedCount++;
                }
            }
            MicroLesson ml = this.microLessonService.findMicroLessonByUuid(microId);
            mrvo.setResEnt(vo);
            if (ml != null) {
                EntityFile ent = this.entityFileService.findFileByUUID(ml.getEntityId());
                mrvo.setThumbnailUrl(ent.getThumbnailUrl());
                mrvo.setIconType(IconUtil.setIcon(ent.getExtension()));
            }
            voList.add(mrvo);
        }
        request.setAttribute("microPublishId", microPublishId);
        request.setAttribute("finishedCount", finishedCount);
        request.setAttribute("microList", voList);
        return DIR + "/studyMicroList";
    }
    
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
        MicroLessonPublish mlp = this.microPrepareService.publishMicroLesson(microList, classList, sd, fd, null, user.getId(), user.getRealName());
        
        //发送消息
//        adddMicroLessonMessage(microList, classList, sd, fd, user.getId(), user.getRealName(), mlp.getUuid());
        
        push(taskExecutor,user,classList,microList,sd,fd,mlp.getUuid(),studentService,microPersonGroupUserIdService,microLessonMessageService);
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
        String relateType = request.getParameter("relateType");
        Integer relateIdint = Integer.parseInt(relateId);
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
        List<MicroLessonRelateVo> mlrList = this.microPrepareService.searchPublishedLesson(user.getId(), relateIdint, page,relateType);
        List<MicroLessonRelateVo> reMlrList = new ArrayList<MicroLessonRelateVo>();
        List<Integer>userId=new ArrayList<Integer>();
        for (Student st : stList) {
        	userId.add(st.getUserId());
        }
        Integer[] userIds=new Integer[userId.size()];
        for(int i = 0;i<userId.size();i++){
        	userIds[i] = userId.get(i);
    	}
        for (MicroLessonRelateVo rv : mlrList) {
            Integer finishedCount = 0;
            Integer unFinishedCount = stList.size(); 
            if(userId.size()!=0){
            	MicroLessonFinishCount mc= microPublishedRecordService.findMicroLessonFinishCount(rv.getPublishMicroLessonId(), userIds);
            	if(mc!=null){
            		finishedCount=mc.getFinishedCount();
            		unFinishedCount=unFinishedCount-finishedCount;
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
        request.setAttribute("relateId", request.getParameter("relateId"));
        request.setAttribute("relateType", request.getParameter("relateType"));
        request.setAttribute("classGradeMap", getClassGradeMap(user, false, false,true));
        return DIR + "/publishManagerIndex";
    }
    
    @RequestMapping(value = "/prepareLesson")
    public String prepareLesson(HttpServletRequest request, HttpServletResponse response, @CurrentUser UserInfo user) {
        request.setAttribute("classGradeMap", getClassGradeMap(user, true, true,true));
        return DIR + "/prepareLesson";
    }
    
    @RequestMapping(value = "/batchReviews")
    public ModelAndView batchReviews(HttpServletRequest request, HttpServletResponse response, @CurrentUser UserInfo user , Model model) {
        String microPublishId = request.getParameter("microPublishedId");
        String relateId = request.getParameter("relateId");
        String relateType = request.getParameter("relateType");
        Integer relateIdint = Integer.parseInt(relateId);
        List<Student> fst = new ArrayList<Student>();
        List<Student> ust = new ArrayList<Student>();
        List<Student> pst = new ArrayList<Student>();
        List<Student> stList = new ArrayList<Student>();
        if (relateType != null && (relateType == TagsTypeContans.TEAM_TYPE || relateType.equals(TagsTypeContans.TEAM_TYPE))) {
        	stList = this.studentService.findStudentOfTeam(relateIdint);
		}else if (relateType != null && (relateType == TagsTypeContans.GROUP_TYPE || relateType.equals(TagsTypeContans.GROUP_TYPE))) {
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
                MicroPublishedRecord mpr = this.microPrepareService.searchUserPublishedRecord(microPublishId, st.getUserId(), st.getName(), st.getStudentNumber());
                if (mpr.getFinishedFlag() == StudyFinishedFlag.FINISHED) {
                    fst.add(st);
                } else if (mpr.getFinishedFlag() == StudyFinishedFlag.NOT_FINISHED) {
                    ust.add(st);
                } else if (mpr.getFinishedFlag() == StudyFinishedFlag.PART_FINISHED) {
                    pst.add(st);
                }
            }
        }
        model.addAttribute("microPublishId", microPublishId);
        model.addAttribute("relateId", relateId);
        model.addAttribute("allList", stList);
        model.addAttribute("finishedList", fst);
        model.addAttribute("unFinishedList", ust);
        model.addAttribute("partFinishedList", pst);
        model.addAttribute("relateType", relateType);
        
        return new ModelAndView(DIR + "/batchReviews", model.asMap());
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
            MicroPublishedRecord mpr = this.microPrepareService.updateUserPublishedRecord(microPublishId, userId, reviews, reward);
        }
        return null;
    }
    
    @RequestMapping(value = "/editReviews")
    public String editReviews(HttpServletRequest request, HttpServletResponse response, @CurrentUser UserInfo user) {
        String microPublishId = request.getParameter("microPublishedId");
        Integer userId = Integer.parseInt(request.getParameter("userId"));
        MicroPublishedRecord mpr = this.microPrepareService.searchUserPublishedRecord(microPublishId, userId, null, null);
        request.setAttribute("mpr", mpr);
        return DIR + "/editReviews";
    }
    
    @RequestMapping(value = "/watchReviews")
    public String watchReviews(HttpServletRequest request, HttpServletResponse response, @CurrentUser UserInfo user) {
        String microPublishId = request.getParameter("microPublishedId");
        Integer userId = Integer.parseInt(request.getParameter("userId"));
        MicroPublishedRecord mpr = this.microPrepareService.searchUserPublishedRecord(microPublishId, userId, null, null);
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
        List<MicroPublishedRecord> recordList = new ArrayList<MicroPublishedRecord>();
        List<Student> stList = new ArrayList<Student>();
        MicroLessonRelateVo rvo = new MicroLessonRelateVo();
        if (relateType != null && (relateType == TagsTypeContans.TEAM_TYPE || relateType.equals(TagsTypeContans.TEAM_TYPE))) {
        	stList = this.studentService.findStudentOfTeam(relateIdint);
        	rvo = this.microPrepareService.searchPublishedLesson(microPublishId, Integer.parseInt(relateId),relateType);
		}else if (relateType != null && (relateType == TagsTypeContans.GROUP_TYPE || relateType.equals(TagsTypeContans.GROUP_TYPE))) {
				MiMicroPersonGroupUserIdCondition miMicroPersonGroupUserIdCondition = new MiMicroPersonGroupUserIdCondition();
				miMicroPersonGroupUserIdCondition.setMicroPersonGroupId(relateIdint);
				List<MiMicroPersonGroupUserId> list = microPersonGroupUserIdService.findMiMicroPersonGroupUserIdByCondition(miMicroPersonGroupUserIdCondition);
					for (MiMicroPersonGroupUserId miMicroPersonGroupUserId : list) {
						Student student = studentService.findOfUser(user.getSchoolId(), miMicroPersonGroupUserId.getUserId());
						stList.add(student);
					}
					rvo = this.microPrepareService.searchPublishedLesson(microPublishId, Integer.parseInt(relateId),relateType);	
		}
        if (stList != null && stList.size() > 0) {
            for (Student st : stList) {
                MicroPublishedRecord mpr = this.microPrepareService.searchUserPublishedRecord(microPublishId, st.getUserId(), st.getName(), st.getStudentNumber());
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
        MicroUserRecord ur = this.microUserRecordService.getUniqueRecord(userId, microId, publishLessonId);
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
        MicroUserRecord ur = this.microUserRecordService.getUniqueRecord(userId, microId, publishLessonId);
        if (ur != null) {
            ur.setModifyDate(new Date());
            ur.setPlayTime(ur.getPlayTime() + 1);
            if (finishedFlag != null && !"".equals(finishedFlag)) {
                int flag = Integer.parseInt(finishedFlag);
                if (flag == StudyFinishedFlag.FINISHED) {
                    ur.setFinishedDate(new Date());
                    ur.setFinishedFlag(flag);
                    //推送消息
                    if (publisherId != null && !"".equals(publisherId)) {
                        Message message = new Message();
                        message.setAppId(SysContants.SYSTEM_APP_ID);
                        message.setContent("您好，您的学生" + user.getRealName() + "完成了您布置的微课作业！");
                        message.setPosterId(user.getId());
                        message.setPosterName(user.getRealName());
                        message.setRecordStatus(StatusDefaultContans.ZERO);
                        message.setTag(MessageCenterContants.FINISHED_PATH_CODE_MICRO);
                        PushMessageUtil.sendMessage(message, Integer.parseInt(publisherId));
                        PushMessageUtil.pushMessage(Integer.parseInt(publisherId));
                    }
                } else //如果之前已经完成就不需要再改变学习状态
                if (ur.getFinishedFlag() != null && ur.getFinishedFlag() == StudyFinishedFlag.FINISHED) {
                } else {
                    ur.setFinishedFlag(flag);
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
            ur.setUserId(user.getId());
            ur.setUserName(user.getRealName());
            ur.setPlayTime(1);
            ur.setPublishLessonId(publishLessonId);
            //如果用户学习状态改变
            if (finishedFlag != null && !"".equals(finishedFlag)) {
	            ur.setFinishedFlag(Integer.parseInt(finishedFlag));
	            if(StudyFinishedFlag.FINISHED.equals(Integer.parseInt(finishedFlag))){
	            	ur.setFinishedDate(new Date());
	            }
            }
            //推送消息
//            if (publisherId != null && !"".equals(publisherId)) {
//                Message message = new Message();
//                message.setAppId(SysContants.SYSTEM_APP_ID);
//                message.setContent("您好，您的学生" + user.getRealName() + "完成了您布置的作业！");
//                message.setPosterId(user.getId());
//                message.setPosterName(user.getRealName());
//                message.setRecordStatus(StatusDefaultContans.ZERO);
//                message.setTag(MessageCenterContants.FINISHED_PATH_CODE_MICRO);
//                PushMessageUtil.sendMessage(message, Integer.parseInt(publisherId));
//                PushMessageUtil.pushMessage(Integer.parseInt(publisherId));
//            }
            this.microUserRecordService.add(ur);
        }
        
        return null;
    }
    
    @RequestMapping(value = "/myMicro")
    public String myMicro(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("page") Page page, @CurrentUser UserInfo user) {
        String index = request.getParameter("index");
        page.setPageSize(5);
        List<MyResourceVo> reslist = new ArrayList<MyResourceVo>();
        ResourceCondition condition=new ResourceCondition();
        condition.setUserId(user.getId());
        condition.setIsPersonal(true);
        condition.setResType(ResourceType.MICRO);
        condition.setIsDeleted(false);
        List<Resource> resourceList = this.resourceService.findResourceByCondition(condition,page, Order.desc("create_date"));
        reslist=findVoByType(resourceList);
        request.setAttribute("reslist",reslist );
        if (index != null && !"".equals(index)) {
            return DIR + "/myMicroIndex";
        } else {
            return DIR + "/myMicroList";
        }
    }
    
    @RequestMapping(value = "/studyList")
    public String studyList(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("page") Page page, @CurrentUser UserInfo user) throws IOException {
        Student s = this.studentService.findStudentById(user.getStudentId());
        if (s != null) {
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
             Team team = this.teamService.findCurrentTeamOfStudent(s.getUserId(), user.getSchoolYear());
             Integer teamId = team != null ? team.getId() : null;
            if (teamId != null) {
            	relateId.add(teamId);
                Date fdate = new Date();
                List<MicroLessonRelateVo> mlrvList = this.microPrepareService.searchHistoryPublishedLesson(null, relateId, null, fdate, false, page);
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
//            String teamName = s.getTeamName();
            if (teamId != null) {
            	relateId.add(teamId);
                Date fdate = new Date();
                List<MicroStudyRecordVo> msrvList = new ArrayList<MicroStudyRecordVo>();
                List<String> subjectList = this.microPrepareService.findSubjectNameByMicroLesson(null, relateId, fdate, true);
                //去除重复科目
                HashSet<String> subjectSet = new HashSet<String>(subjectList);
                String subjectName = request.getParameter("subjectName");
                //搜索全部
                if ("all".equals(subjectName)) {
                    subjectName = null;
                }
                List<MicroLessonRelateVo> mlrvList = this.microPrepareService.searchHistoryPublishedLesson(null, relateId, subjectName, fdate, true, page);
                if (mlrvList != null && mlrvList.size() > 0) {
                    for (MicroLessonRelateVo mr : mlrvList) {
                        MicroPublishedRecord mpr = this.microPrepareService.searchUserPublishedRecord(mr.getPublishMicroLessonId(), user.getId(), s.getName(), s.getStudentNumber());
                        //String rsn = mr.getRelateName().substring(mr.getRelateName().indexOf("[") + 1, mr.getRelateName().indexOf("]"));
                        MicroStudyRecordVo vo = new MicroStudyRecordVo();
                        vo.setMlrv(mr);
                        vo.setMpr(mpr);
                        msrvList.add(vo);
                    }
                }
                request.setAttribute("subjectList", subjectSet);
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
            List<TeamTeacher> teamTeacherList = teamTeacherService.findTeamTeacherByCondition(teamTeacherCondition, null, Order.asc("team_id"));
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
        	return DIR + "/myMicroIndex";
        } else {
        	return DIR + "/myMicroList";
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
        	return DIR + "/myMicroIndex";
        } else {
        	return DIR + "/myMicroList";
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
        	return DIR + "/myMicroIndex";
        } else {
        	return DIR + "/myMicroList";
        }
    }
    /**
     * 后台推送
     * @param taskExecutor
     * @param user
     * @param classList
     * @param studentService
     * @param microPersonGroupUserIdService
     */
    public static void push(final TaskExecutor taskExecutor,final UserInfo user,final JSONArray classList,final JSONArray microList,final Date sd,final Date fd,final String uuid,final StudentService studentService,final MiMicroPersonGroupUserIdService microPersonGroupUserIdService,final MicroLessonMessageService microLessonMessageService){
    	taskExecutor.execute(new Runnable() {

			public void run() {
				try {
				    adddMicroLessonMessage(microList, classList, sd, fd, user.getId(), user.getRealName(), uuid);
					Message message = new Message();
			        message.setAppId(SysContants.SYSTEM_APP_ID);
			        message.setContent("您有新的微课作业学习！");
			        message.setPosterId(user.getId());
			        message.setPosterName(user.getRealName());
			        message.setRecordStatus(StatusDefaultContans.ZERO);
			        message.setTag(MessageCenterContants.PATH_CODE_MICRO);
			        List<Integer> receiverIdList = new ArrayList<Integer>();
			        List<Integer> receiverIdList2 = new ArrayList<Integer>();
			        for (int i = 0; i < classList.size(); i++) {
			            JSONObject receivers = (JSONObject) classList.get(i);
			            String receiverId = (String) receivers.get("relateId");
			            String relateType = (String) receivers.get("relateType");
			            if(StringUtils.isEmpty(relateType)){
			            	relateType = TagsTypeContans.TEAM_TYPE;
			            }
			            if (relateType == TagsTypeContans.GROUP_TYPE || relateType.equals(TagsTypeContans.GROUP_TYPE)) {
			            	receiverIdList2.add(Integer.parseInt(receiverId));
						}else if (relateType == TagsTypeContans.TEAM_TYPE || relateType.equals(TagsTypeContans.TEAM_TYPE)) {
							receiverIdList.add(Integer.parseInt(receiverId));
						}
			        }
			        if (receiverIdList !=null && !receiverIdList.isEmpty()) {
			        	List<Student> studentList = studentService.findStudentListByListId(receiverIdList, TagsTypeContans.TEAM_ID, user.getSchoolId());
			        	receiverIdList = getUserId(studentList);
			        	PushMessageUtil.sendMessage(message, receiverIdList);
//			          for(Integer userId : receiverIdList){
//			          	PushMessageUtil.pushMessage(userId);
//			          }
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

				} catch (Exception e) {
					e.printStackTrace();
				}
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
			 private void adddMicroLessonMessage(JSONArray microList, JSONArray publishObjectList, Date startDate, Date finishedDate, Integer publisherId, String publisherName, String publishUuid){
			    	MicroLessonMessage mlm = null;
			    	Date date = new Date();
			         for (int j = 0; j < publishObjectList.size(); j++) {
			             JSONObject mlrj = (JSONObject) publishObjectList.get(j);
			             String relateId = (String) mlrj.get("relateId");
			             Object relateName = mlrj.get("relateName");
			             List<Student> studentList = studentService.findStudentByTeamId(Integer.parseInt(relateId));
			             MicroLessonMessage[] msg=new MicroLessonMessage[studentList.size()];
			             int i=0;
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
			            	 mlm.setTitle("");
			            	 mlm.setPublishUuid(publishUuid);
//			            	 microLessonMessageService.add(mlm);
			            	 msg[i]=mlm;
			            	 i++;
			             }
			            
			             //为了构建能成功 先注释叼。
			             // microLessonMessageService.createBatch(msg);
			        }
			    }
     });
   }
}
