package platform.szxyzxx.web.micro.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.FileUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import net.sf.json.JSONArray;
import net.sf.json.JSONNull;
import net.sf.json.JSONObject;
import platform.education.generalTeachingAffair.model.Grade;
import platform.education.generalTeachingAffair.model.SchoolInfo;
import platform.education.generalTeachingAffair.model.Student;
import platform.education.generalTeachingAffair.model.SubjectGrade;
import platform.education.generalTeachingAffair.model.Teacher;
import platform.education.generalTeachingAffair.model.Team;
import platform.education.generalTeachingAffair.service.GradeService;
import platform.education.generalTeachingAffair.service.SchoolService;
import platform.education.generalTeachingAffair.service.StudentService;
import platform.education.generalTeachingAffair.service.SubjectGradeService;
import platform.education.generalTeachingAffair.service.TeacherService;
import platform.education.generalTeachingAffair.service.TeamService;
import platform.education.message.contans.StatusDefaultContans;
import platform.education.message.model.Message;
import platform.education.message.service.MessageService;
import platform.education.micro.contants.MicroType;
import platform.education.micro.model.MicroBanner;
import platform.education.micro.model.MicroCatalog;
import platform.education.micro.model.MicroLesson;
import platform.education.micro.model.MicroLessonBgpicture;
import platform.education.micro.model.MicroLessonMessage;
import platform.education.micro.model.MicroLessonPublish;
import platform.education.micro.model.MicroPublishedRecord;
import platform.education.micro.model.MicroUserRecord;
import platform.education.micro.service.MicroBannerService;
import platform.education.micro.service.MicroCatalogService;
import platform.education.micro.service.MicroLessonBgpictureService;
import platform.education.micro.service.MicroLessonHotService;
import platform.education.micro.service.MicroLessonMessageService;
import platform.education.micro.service.MicroLessonService;
import platform.education.micro.service.MicroPrepareService;
import platform.education.micro.service.MicroUserRecordService;
import platform.education.micro.vo.FormatMicroLessonMessage;
import platform.education.micro.vo.MicroBannerCondition;
import platform.education.micro.vo.MicroBannerVo;
import platform.education.micro.vo.MicroCatalogCondition;
import platform.education.micro.vo.MicroLessonBgpictureCondition;
import platform.education.micro.vo.MicroLessonBgpictureVo;
import platform.education.micro.vo.MicroLessonCountCondition;
import platform.education.micro.vo.MicroLessonHotCondition;
import platform.education.micro.vo.MicroLessonMessageCondition;
import platform.education.micro.vo.MicroLessonRelateVo;
import platform.education.micro.vo.MicroLessonVo;
import platform.education.micro.vo.RealMicroList;
import platform.education.micro.vo.WkxVo;
import platform.education.resource.contants.StudyFinishedFlag;
import platform.education.resource.service.ResourceService;
import platform.education.resource.utils.IconUtil;
import platform.education.resource.utils.UUIDUtil;
import platform.education.resource.vo.ResourceCondition;
import platform.education.user.contants.GroupContants;
import platform.education.user.model.Group;
import platform.education.user.service.GroupService;
import platform.education.user.service.GroupUserService;
import platform.education.user.service.UserService;
import platform.service.storage.contants.FileStatusCode;
import platform.service.storage.holder.FileServiceHolder;
import platform.service.storage.model.EntityFile;
import platform.service.storage.service.EntityFileService;
import platform.service.storage.service.FileService;
import platform.service.storage.vo.FileResult;
import platform.szxyzxx.web.bbx.client.vo.CommonActivity;
import platform.szxyzxx.web.bbx.client.vo.CommonSubject;
import platform.szxyzxx.web.bbx.client.vo.ResponseError;
import platform.szxyzxx.web.bbx.client.vo.ResponseInfo;
import platform.szxyzxx.web.bbx.client.vo.ResponseNormal;
import platform.szxyzxx.web.bbx.client.vo.ResponseOrder;
import platform.szxyzxx.web.bbx.client.vo.ResponseVo;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.contants.MessageCenterContants;
import platform.szxyzxx.web.common.contants.SysContants;
import platform.szxyzxx.web.common.util.DateUtil;
import platform.szxyzxx.web.common.util.PushMessageUtil;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.util.SzxyHttpClientRequest;
import platform.szxyzxx.web.common.util.ZipFileUtil;
import platform.szxyzxx.web.common.vo.UserInfo;
import platform.szxyzxx.web.message.contans.TagsTypeContans;
import platform.szxyzxx.web.micro.contants.MicroContans;
import platform.szxyzxx.web.micro.contants.PathAndFileName;

/**
 *
 * @author 罗志明
 */
@Controller
@RequestMapping(value = "/termial/micro")
public class MicroAppController {
    //教师
    @Resource
    private TeacherService teacherService;
    
    //用户组
    @Resource
    private GroupUserService groupUserService;
    //基础用户
    @Resource
    private UserService userService;
    //上传
    @Resource
    protected FileService fileService;
    //微课
    @Resource
    protected MicroLessonService microLessonService;
    //微课学习记录
    @Resource
    private MicroUserRecordService microUserRecordService;
    //微课发布
    @Resource
    private MicroPrepareService microPrepareService;
    // 学生
    @Resource
    private StudentService studentService;
    // 年级
    @Resource
    private GradeService gradeService;
    //消息中心
    @Resource
    private MessageService messageService;
    //资源文件
    @Resource
    private EntityFileService entityFileService;
    //点赞记录
    @Resource
    private MicroLessonHotService microLessonPraiseService;
    //学校
    @Resource
    private SchoolService schoolService;
    //微课星知识
    @Resource
	private MicroCatalogService microCatalogService;
    //微课星banner
    @Resource
	private MicroBannerService microBannerService;
    //组
    @Resource
    private GroupService groupService;
    //微课星背景图
    @Resource
    private MicroLessonBgpictureService microLessonBgpictureService;
    // 班级
 	@Resource
 	protected TeamService teamService;
 	//年级科目
 	@Resource
 	protected SubjectGradeService subjectGradeService;
 	//微课学习消息
 	@Resource
	private MicroLessonMessageService microLessonMessageService;
    //资源
    @Resource
    private ResourceService resourceService;
 	
 	@Resource(name = "unzip_taskExecutor")
	private TaskExecutor taskExecutor;
    
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    private static final String WKX_DIR = "wkx/";
    
    /**
     * 热门微课
     * @param condition
     * @param page
     * @param order
     * @return
     */
    @RequestMapping(value = "/LessonHotDataForJson")
    @ResponseBody
    public String LessonHotDataForJson(
    		MicroLessonHotCondition condition,
    		@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order){
    	List<MicroLesson> hotLessons = microLessonService.findMicroLessonHot(condition,page,order);
    	List<MicroLessonVo> voList = new ArrayList<MicroLessonVo>(); 
    	if(hotLessons != null && hotLessons.size() >0){
    		for(MicroLesson m :hotLessons){
    			MicroLessonVo mvo = new MicroLessonVo();
    			BeanUtils.copyProperties(m, mvo);
    			EntityFile ent = this.entityFileService.findFileByUUID(m.getEntityId());
    			mvo.setThumbUrl(fileService.relativePath2HttpUrl(ent.getThumbnailUrl()));
    			Teacher lessonUser = teacherService.findOfUser(condition.getSchoolId(), m.getUserId());
    			if(lessonUser != null){
    				mvo.setName(lessonUser.getName());
    			}
    			String entityId = m.getEntityId();
        		if(!StringUtils.isEmpty(entityId)){
        			FileResult file = fileService.findFileByUUID(entityId);
        			if(file.getEntityFile() != null){
        				mvo.setEntityPath(fileService.relativePath2HttpUrl(file.getEntityFile().getRelativePath()));
        			}
        		}
    			voList.add(mvo);
    		}
    	}
    	//将数据转换成json
    	Map<String,Object> jsonMap = new HashMap<String, Object>(); 
    	jsonMap.put("hotLessons", voList);
    	jsonMap.put("page", page);
    	ObjectMapper mapper = new ObjectMapper();
    	String param = null;
    	try {
    		param = mapper.writeValueAsString(jsonMap);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	return param;
    }
    
    /**
     * 微课星知识预览
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/viewer")
	public String viewer(
			@RequestParam(value = "id", required = true) Integer id,
			Model model) {
		MicroCatalog microCatalog = this.microCatalogService.findMicroCatalogById(id);
		model.addAttribute("isCK", "disable");
		model.addAttribute("microCatalog", microCatalog);
		return WKX_DIR + "catalog/input";
	}
    /**
     * 获取移动端微课星主页数据
     * @return
     */
    @RequestMapping(value = "/microCourseData")
    @ResponseBody
    public String microCourseDataForJson(
    		@CurrentUser UserInfo user,
    		@RequestParam(value = "userId",required = true)Integer userId,
    		@RequestParam(value = "schoolId",required = false)Integer schoolId,
    		@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order){
    	
    	Map<String,Object> jsonMap = new HashMap<String, Object>(); 
    	
    	//获取微课星知识
    	MicroCatalogCondition mcCondition = new MicroCatalogCondition();
    	mcCondition.setPushState(MicroContans.PUSH_ENABLE);
    	List<MicroCatalog> catalogList = this.microCatalogService.findMicroCatalogRemoveContent(mcCondition, null, null);
    	
    	//****************************** 热门微课  *************************************
    	MicroLessonHotCondition condition = new MicroLessonHotCondition();
    	condition.setPushState(MicroContans.PUSH_ENABLE);
    	page.setPageSize(10);
    	List<MicroLesson> hotLessons = microLessonService.findMicroLessonHot(condition,page,order);
    	List<MicroLessonVo> voList = new ArrayList<MicroLessonVo>(); 
    	if(hotLessons != null && hotLessons.size() >0){
    		for(MicroLesson m :hotLessons){
    			MicroLessonVo mvo = new MicroLessonVo();
    			BeanUtils.copyProperties(m, mvo);
    			EntityFile ent = this.entityFileService.findFileByUUID(m.getEntityId());
    			if(ent != null){
    				mvo.setThumbUrl(fileService.relativePath2HttpUrl(ent.getThumbnailUrl()));
    			}
    			Integer userID = m.getUserId();
    			if(userID != null){
    				List<Integer> gList = groupUserService.findGroupIds(userID);
    				if(gList.size() > 0){
    					Group group = groupService.findGroupById(gList.get(0));
    					if(group != null){
    						Teacher lessonUser = teacherService.findOfUser(group.getOwnerId(), userID);
    						if(lessonUser != null){
    							mvo.setName(lessonUser.getName());
    						}
    					}
    				}
    			}
    			String entityId = m.getEntityId();
        		if(!StringUtils.isEmpty(entityId)){
        			FileResult file = fileService.findFileByUUID(entityId);
        			if(file.getEntityFile() != null){
        				mvo.setEntityPath(fileService.relativePath2HttpUrl(file.getEntityFile().getRelativePath()));
        			}
        		}
    			
    			voList.add(mvo);
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
		Long AllCount = microLessonService.findCountByTeacherAndCondition(countCondition);
    	//将数据转换成json
    	jsonMap.put("catalogList", catalogList);
    	jsonMap.put("hotLessons", voList);
    	jsonMap.put("microBanners", bannerList);
    	jsonMap.put("currentDayCount", currentDayCount);
    	jsonMap.put("currentMonthCount", currentMonthCount);
    	jsonMap.put("AllCount", AllCount);
    	ObjectMapper mapper = new ObjectMapper();
    	String param = null;
    	try {
    		param = mapper.writeValueAsString(jsonMap);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	return param;
    }
    
    //微课星微信分享
    @RequestMapping(value = "/lessonShare/{id}")
    public String shareLesson(
    		@PathVariable(value = "id") String id,
    		@CurrentUser UserInfo user,
    		@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,Model model){
    	//微课相关信息
    	MicroLesson ml = microLessonService.findMicroLessonByUuid(id);
    	MicroLessonVo vo = new MicroLessonVo();
    	org.json.JSONObject mainJson = null;
    	org.json.JSONObject propertyJson = null;
    	if(ml != null){
    		BeanUtils.copyProperties(ml, vo);
//    		String bgpictureEntityId = ml.getBgpictureEntityId();
    		String jsonEntityId = ml.getJsonEntityId();
    		String mediaEntityId = ml.getMediaEntityId();
    		String propertyEntityId = ml.getPropertyEntityId();
    		String logoEntityId = ml.getLogoEntityId();
    		if(!StringUtils.isEmpty(logoEntityId)){
    			FileResult logoFile = fileService.findFileByUUID(logoEntityId);
    			if(logoFile.getEntityFile() != null){
    				vo.setLogoPath(logoFile.getEntityFile().getRelativePath());
    			}
    		}
//    		if(!StringUtils.isEmpty(bgpictureEntityId)){
//    			FileResult bgpictureFile = fileService.findFileByUUID(bgpictureEntityId);
//    			if(bgpictureFile.getEntityFile() != null){
//    				vo.setBgpicturePath(bgpictureFile.getEntityFile().getRelativePath());
//    			}
//    		}
    		if(!StringUtils.isEmpty(jsonEntityId)){
    			FileResult jsonFile = fileService.findFileByUUID(jsonEntityId);
    			if(jsonFile.getEntityFile() != null){
    				vo.setJsonPath(jsonFile.getEntityFile().getRelativePath());
    				mainJson = getMainJson(jsonFile.getEntityFile().getRelativePath());
    			}
    		}
    		if(!StringUtils.isEmpty(mediaEntityId)){
    			FileResult mediaFile = fileService.findFileByUUID(mediaEntityId);
    			if(mediaFile.getEntityFile() != null){
    				vo.setMediaPath(mediaFile.getEntityFile().getRelativePath());
    			}
    		}
    		if(!StringUtils.isEmpty(propertyEntityId)){
    			FileResult propertyFile = fileService.findFileByUUID(propertyEntityId);
    			if(propertyFile.getEntityFile() != null){
    				vo.setPropertyPath(propertyFile.getEntityFile().getRelativePath());
    				propertyJson = getPropertyJson(propertyFile.getEntityFile().getRelativePath());
    			}
    		}
        	List<MicroLessonBgpictureVo> vos = bgSettings(ml.getUuid());
        	vo.setMlbs(vos);
    	}
    	//****************************** 点赞数   ***************************************
//    	MicroLessonHotCondition condition = new MicroLessonHotCondition();
//    	if(ml != null){
//    		condition.setLessonId(ml.getId());
//    	}
//    	Long praiseNum = microLessonPraiseService.count(condition);
    	
    	//****************************** 热门微课  *************************************
    	MicroLessonHotCondition condition = new MicroLessonHotCondition();
    	condition.setPushState(MicroContans.PUSH_ENABLE);
    	List<MicroLesson> hotLessons = microLessonService.findMicroLessonHot(condition,page,order);
    	List<MicroLessonVo> voList = new ArrayList<MicroLessonVo>(); 
    	if(hotLessons != null && hotLessons.size() >0){
    		for(MicroLesson m :hotLessons){
    			MicroLessonVo mvo = new MicroLessonVo();
    			BeanUtils.copyProperties(m, mvo);
    			EntityFile ent = this.entityFileService.findFileByUUID(m.getEntityId());
    			mvo.setThumbUrl(ent.getThumbnailUrl());
    			Integer userId = m.getUserId();
    			if(userId != null){
    				List<Integer> gList = groupUserService.findGroupIds(userId);
    				if(gList.size() > 0){
    					Group group = groupService.findGroupById(gList.get(0));
    					if(group != null){
    						Teacher lessonUser = teacherService.findOfUser(group.getOwnerId(), m.getUserId());
    						if(lessonUser != null){
    							mvo.setName(lessonUser.getName());
    							mvo.setUserName(lessonUser.getName());
    						}
    					}
    				}
    			}
    			voList.add(mvo);
    		}
    	}
    	
    	//微课作者
    	Teacher lessonUser = null;
    	SchoolInfo schoolInfo = null;
    	if(ml != null){
    		Integer userId = ml.getUserId();
			if(userId != null){
				List<Integer> gList = groupUserService.findGroupIds(userId);
				if(gList.size() > 0){
					Group group = groupService.findGroupById(gList.get(0));
					if(group != null){
						lessonUser = teacherService.findOfUser(group.getOwnerId(), userId);
						if(lessonUser != null){
			    			schoolInfo = schoolService.findSchoolInfoById(lessonUser.getSchoolId());
			    		}
					}
				}
			}
    	}
    	
    	//获取微课星知识
//    	List<MicroCatalog> catalogList = this.microCatalogService.findMicroCatalogByCondition(null, null, null);
    	
    	model.addAttribute("microLessonVo", vo);
//    	model.addAttribute("praiseNum", praiseNum);
    	model.addAttribute("hotLessons", voList);
    	model.addAttribute("mainJson", mainJson);
    	model.addAttribute("propertyJson", propertyJson);
    	model.addAttribute("lessonUser", lessonUser);
    	model.addAttribute("schoolInfo", schoolInfo);
//    	model.addAttribute("catalogList", catalogList);
    	return WKX_DIR+"share";
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
    //获取MainJson
    private org.json.JSONObject getMainJson(String jsonPath){
    	jsonPath = this.fileService.relativePath2InternalHttpUrl(jsonPath);
    	org.json.JSONObject jsonObject = SzxyHttpClientRequest.doGetRequest(jsonPath, null);
    	return jsonObject;
    }
    
    //获取PropertyJson
    private org.json.JSONObject getPropertyJson(String propertyPath){
    	propertyPath = this.fileService.relativePath2InternalHttpUrl(propertyPath);
    	org.json.JSONObject jsonObject = SzxyHttpClientRequest.doGetRequest(propertyPath, null);
    	return jsonObject;
    }
    
    //记录微课星播放次数
    @RequestMapping(value = "/saveSeeNumber")
    @ResponseBody
    public String saveSeeNumber(@RequestParam(value = "uuid", required = true)String uuid){
    	MicroLesson microLesson = microLessonService.findMicroLessonByUuid(uuid);
    	if(microLesson != null){
    		Integer playNumber = microLesson.getPlayNumber();
    		if(playNumber == null){
    			playNumber = 1;
    		}else{
    			playNumber++;
    		}
    		microLesson.setPlayNumber(playNumber);
    		this.microLessonService.modify(microLesson);
    	}
    	return null;
    }
    
	private File[] getFiles(String filePath){
    	if(!StringUtils.isEmpty(filePath)){
    		File root = new File(filePath);
    		File[] files = root.listFiles();
    		return files;
    	}
    	return null;
    }
    
    //客户端微课星上传
    @RequestMapping(value = "/uploadMicroCourse")
    @ResponseBody
    public Object uploadMicroCourse(@RequestParam("microFile") MultipartFile microFile, @RequestParam("thumbFile") MultipartFile thumbFile, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userId = request.getParameter("userId");
        String title = request.getParameter("title");
        String lessonLength = request.getParameter("lessonLength");
        String subjectName = request.getParameter("subjectName");
        String subjectCode = request.getParameter("subjectCode");
        Long lessonLe = null;
        if(!StringUtils.isEmpty(lessonLength)){
        	lessonLe = Long.parseLong(lessonLength);
        }
		String realPath = request.getServletContext().getRealPath("/") + "tmp";
		File deletePath = new File(realPath);
        forceDelete(deletePath);
		CommonsMultipartFile cf= (CommonsMultipartFile)microFile; 
		String contentType = cf.getContentType();
		DiskFileItem fi = (DiskFileItem)cf.getFileItem();
		File file = fi.getStoreLocation();
		String path = file.getAbsolutePath();
		String mainJsonFileName = null;
		String mediaFileName = null;
		String propertyFlieName = null;
		WkxVo vo = new WkxVo();
		List<FileResult> bgPictureResults = new ArrayList<FileResult>();
		vo.setLessonLength(lessonLe);
		vo.setSubjectCode(subjectCode);
		vo.setSubjectName(subjectName);
        if (microFile != null) {
            String uploadFileName = microFile.getOriginalFilename();
            String destPath = realPath + File.separator + uploadFileName.substring(0,uploadFileName.indexOf(".wkx")) + File.separator + "tmp";
            if (userId != null) {
            	 //解压.wkx文件格式的文件
                if(uploadFileName.contains(".wkx")){
                	ZipFileUtil.unZipFileWithPassword(path, destPath);
                	mainJsonFileName = destPath + PathAndFileName.MAIN_JSON;
                	mediaFileName =  destPath + PathAndFileName.MEDIA;
                	propertyFlieName = destPath + PathAndFileName.PROPERTY;
//                	logoFileName = destPath + PathAndFileName.LOGO;
                	//上传 解压后的文件
                	File mainJson = new File(mainJsonFileName);
                	File media = new File(mediaFileName);
//                	File white_bgPicture = new File(white_bgPictureFileName);
//                	File black_bgPicture = new File(black_bgPictureFileName);
//                	File green_bgPicture = new File(green_bgPictureFileName);
                	File property = new File(propertyFlieName);
//                	File logo = new File(logoFileName);
                	File logo = null;
//                	File bgPicture = null;
                	List<File> bgPictures = new ArrayList<File>();
                	File[] files = getFiles(destPath + PathAndFileName.RESOURCE_PATH);
                	if(files.length > 0){
                		for(File f : files){
                			String filename = f.getName();
                			if(filename.indexOf("logo.png") > -1){
                				logo = f;
                			}else{
//                				bgPicture = f;
                				bgPictures.add(f);
                			}
                		}
                	}
                	
                	FileResult mainJsonResult = null;
                	FileResult mediaResult =  null;
                	FileResult propertyResult = null;
                	FileResult logoResult =  null;
                	if(bgPictures.size() > 0 ){
                		for(File bgPicture : bgPictures){
                			FileResult bgPictureResult =  this.fileService.upload(bgPicture, contentType, String.valueOf(SysContants.SYSTEM_APP_ID));
                			if (bgPictureResult != null && FileStatusCode.UPLOAD_SUCCESS.equals(bgPictureResult.getStatusCode())) {
                				bgPictureResult.getEntityFile().setFileName(bgPicture.getName());
                				bgPictureResults.add(bgPictureResult);
                			}
                		}
                	}
                	if(mainJson != null && mainJson.exists()){
                		mainJsonResult = this.fileService.upload(mainJson, contentType, String.valueOf(SysContants.SYSTEM_APP_ID));
                	}
                	if(media != null && media.exists()){
                		mediaResult = this.fileService.upload(media, contentType, String.valueOf(SysContants.SYSTEM_APP_ID));
                	}
//                	if(bgPicture != null && bgPicture.exists()){
//                		bgPictureResult =  this.fileService.upload(bgPicture, contentType, String.valueOf(SysContants.SYSTEM_APP_ID));
//                	}
                	if(property != null && property.exists()){
                		propertyResult =  this.fileService.upload(property, contentType, String.valueOf(SysContants.SYSTEM_APP_ID));
                	}
                	if(logo != null && logo.exists()){
                		logoResult =  this.fileService.upload(logo, contentType, String.valueOf(SysContants.SYSTEM_APP_ID));
                	}
                	if (mainJsonResult != null && FileStatusCode.UPLOAD_SUCCESS.equals(mainJsonResult.getStatusCode())) {
                		String jsonEntityId = mainJsonResult.getEntityFile().getUuid();
                		vo.setJsonEntityId(jsonEntityId);
                	}
                	if (mediaResult != null && FileStatusCode.UPLOAD_SUCCESS.equals(mediaResult.getStatusCode())) {
                		String mediaEntityId = mediaResult.getEntityFile().getUuid();
                		vo.setMediaEntityId(mediaEntityId);
                	}
//                	if (bgPictureResult != null && FileStatusCode.UPLOAD_SUCCESS.equals(bgPictureResult.getStatusCode())) {
//                		String bgpictureEntityId = bgPictureResult.getEntityFile().getUuid();
//                		vo.setBgpictureEntityId(bgpictureEntityId);
//                	}
                	if (propertyResult != null && FileStatusCode.UPLOAD_SUCCESS.equals(propertyResult.getStatusCode())) {
                		String propertyEntityId = propertyResult.getEntityFile().getUuid();
                		vo.setPropertyEntityId(propertyEntityId);
                	}
                	if (logoResult != null && FileStatusCode.UPLOAD_SUCCESS.equals(logoResult.getStatusCode())) {
                		String logoEntityId = logoResult.getEntityFile().getUuid();
                		vo.setLogoEntityId(logoEntityId);
                	}

                }
                
                FileResult result = this.fileService.upload(microFile.getInputStream(), StringUtils.getFilenameExtension(uploadFileName),
                        microFile.getContentType(), uploadFileName,
                        String.valueOf(SysContants.SYSTEM_APP_ID));
                if (result != null && FileStatusCode.UPLOAD_SUCCESS.equals(result.getStatusCode())) {
                    EntityFile entityFile = result.getEntityFile();
                    MicroLesson ml = this.microLessonService.saveOrUpdateMicroLesson(SysContants.SYSTEM_APP_ID, null, entityFile.getUuid(), MicroType.MICRO_COURSE, title, Integer.parseInt(userId), null, null, null,vo);
                    if (thumbFile != null) {
                        entityFile = this.fileService.updateThumbnail(entityFile, thumbFile.getInputStream(), "png", thumbFile.getContentType());
                    }
                    if(bgPictureResults.size() > 0){
                    	for(FileResult fr : bgPictureResults){
                    		MicroLessonBgpicture mlb = new MicroLessonBgpicture();
                    		mlb.setEntityId(fr.getEntityFile().getUuid());
                    		mlb.setEntityName(fr.getEntityFile().getFileName());
                    		mlb.setLessonId(ml.getUuid());
                    		microLessonBgpictureService.add(mlb);
                    	}
                    }
                    File deleteFile = new File(destPath);
                    forceDelete(deleteFile);
                    if (ml != null) {
                        return ResponseInfomation.OPERATION_SUC;
                    } else {
                        return ResponseInfomation.OPERATION_ERROR;
                    }
                }
            } else {
                return ResponseInfomation.NO_LOGIN;
            }
        }
        return ResponseInfomation.OPERATION_FAIL;
    }
    
    /**
     * 强制删除
     * @param deleteFile
     * @return  删除成功返回 true，否则返回 false
     */
    private boolean forceDelete(File deleteFile)  
    {  
        boolean result = false;  
        int tryCount = 0;  
        while(!result && tryCount++ <10)  
        {  
	        logger.debug("try to delete file "+ deleteFile.getName() +" cnt:"+tryCount);  
	        System.gc();  
	        result = deleteFile.delete();  
        }  
        return result;  
    } 
    
    /**
     * 个人微课列表
     * @param request
     * @param response
     * @param page
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/myMicroForJson")
    public String myMicroForJson(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("page") Page page) throws IOException {
        String pageSize = request.getParameter("pageSize");
        String pageNo = request.getParameter("pageNo");
        String userId = request.getParameter("userId");
        String title = request.getParameter("title");
        StringBuffer url = request.getRequestURL();  
        String serverName = url.delete(url.length() - request.getRequestURI().length(), url.length()).append(request.getServletContext().getContextPath()).append("/").toString();
        List<MicroLessonVo> lessonList = new ArrayList<MicroLessonVo>();
        if (pageSize != null && !"".equals(pageSize)) {
            page.setPageSize(Integer.parseInt(pageSize));
        }
        if (pageNo != null && !"".equals(pageNo)) {
            page.setCurrentPage(Integer.parseInt(pageNo));
        }
        List<MicroLessonVo> mllist = this.microLessonService.searchMicroLesson(SysContants.SYSTEM_APP_ID, page, Integer.parseInt(userId), title, null, null, null, null);
        //增加移动端显示的微课vo
        for (MicroLessonVo mlv : mllist) {
            mlv.setHttpUrl(FileServiceHolder.getInstance().getFileService().relativePath2HttpUrlByUUID(mlv.getEntityId()));
            mlv.setThumbUrl(FileServiceHolder.getInstance().getFileService().thumb2HttpUrlByUUID(mlv.getEntityId()));
            EntityFile ent = this.entityFileService.findFileByUUID(mlv.getEntityId());
            mlv.setMd5(ent.getMd5());
            mlv.setServerName(serverName.toString());
            lessonList.add(mlv);
        }
        JSONArray obj = JSONArray.fromObject(lessonList);
        PrintWriter pw = setAjaxResponse(request, response);
        pw.print(obj);
        return null;
    }
    /**
     * 删除单个微课
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/delete")
    public String delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userId = request.getParameter("userId");
        String microId = request.getParameter("microId");
        Integer flag = this.microLessonService.deleteMyMicroLesson(SysContants.SYSTEM_APP_ID, Integer.parseInt(userId), microId);
        PrintWriter pw = setAjaxResponse(request, response);
        pw.print(flag);
        return null;
    }
    /**
     * 发布微课
     * @param request
     * @param response
     * @return
     * @throws ParseException
     * @throws IOException
     */
    @RequestMapping(value = "/publishLesson")
    @ResponseBody
    public Object publishLesson(HttpServletRequest request, HttpServletResponse response) throws ParseException, IOException {
        String publishData = request.getParameter("publishData");
        JSONObject obj = JSONObject.fromObject(publishData);
        String startDate = (String) obj.get("startDate");
        String finishedDate = (String) obj.get("finishedDate");
        String startClock = (String) obj.get("startClock");
        String finishedClock = (String) obj.get("finishedClock");
        Integer publisherId = (Integer) obj.get("publisherId");
        JSONArray microList = (JSONArray) obj.get("microList");
        JSONArray classList = (JSONArray) obj.get("classList");
        String title = (String) obj.get("title");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH");
        SimpleDateFormat sdfm = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date sd = null;
        Date fd = null;
        if(startDate.contains(":")){
        	sd = sdfm.parse(startDate + " " + startClock);
        } else {
        	sd = sdf.parse(startDate + " " + startClock);
        }
        if(finishedDate.contains(":")){
        	fd = sdfm.parse(finishedDate + " " + finishedClock);
        } else {
        	fd = sdf.parse(finishedDate + " " + finishedClock);
        }
        MicroLessonPublish mlp = null;
        List<Group> groups = this.groupUserService.findGroups(publisherId, GroupContants.TYPE_SCHOOL);
        // 获取当前用户所属的Group 和 学校ID
        if (groups.size() > 0) {
            Group group = groups.get(0);
            if (group != null) {
                Integer schoolId = group.getOwnerId();
                Teacher teacher = this.teacherService.findOfUser(schoolId, publisherId);
                if (teacher != null) {
                    mlp = this.microPrepareService.publishMicroLesson(microList, classList, sd, fd, title, publisherId, teacher.getName());
                    //发送通知
                    //不用推送
                   sendMessageToStudent(publisherId, teacher.getName(), classList, schoolId);
                    adddMicroLessonMessage(microList, classList, sd, fd, title, publisherId, teacher.getName(), mlp.getUuid());
                    
                } else {
                    //发布者不是教师
                    return ResponseInfomation.OPERATION_ERROR;
                }
            } else {
                //发布者没有学校
                return ResponseInfomation.OPERATION_ERROR;
            }
        } else {
            //发布者没有用户组
            return ResponseInfomation.OPERATION_ERROR;
        }

        //数据返回
        if (mlp != null) {
            //发布成功
            return ResponseInfomation.OPERATION_SUC;
        } else {
            //发布失败
            return ResponseInfomation.OPERATION_FAIL;
        }
    }
    /**
     * 获取微课列表详情
     * @param request
     * @param response
     * @param page
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/publishDetails")
    public String publishDetails(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("page") Page page) throws IOException {
        String microPublishId = request.getParameter("microPublishedId");
        String relateId = request.getParameter("relateId");
        Integer relateIdint = Integer.parseInt(relateId);
        Integer finishedCount = 0;
        Integer unFinishedCount = 0;
        Integer partFinishedCount = 0;
        List<JSONObject> recordList = new ArrayList<JSONObject>();
        List<Student> stList = this.studentService.findStudentOfTeam(relateIdint);
//        MicroLessonRelateVo rvo = this.microPrepareService.searchPublishedLesson(microPublishId, Integer.parseInt(relateId));
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
                JSONObject mprobj = JSONObject.fromObject(mpr);
                if(mpr.getFinishedDate() != null){
                	 mprobj.put("fmtFinishedDate", new SimpleDateFormat("yyyy-MM-dd HH:mm").format(mpr.getFinishedDate()));
                }
               
                recordList.add(mprobj);
            }
        }
        JSONObject obj = new JSONObject();
        obj.put("finishedCount", finishedCount);
        obj.put("partFinishedCount", partFinishedCount);
        obj.put("unFinishedCount", unFinishedCount);
        obj.put("recordList", JSONArray.fromObject(recordList));
        PrintWriter pw = setAjaxResponse(request, response);
        pw.print(obj);
        return null;
    }

    /**
     * 提交微课学习进度
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/saveRecord")
    @ResponseBody
    public String saveRecord(HttpServletRequest request, HttpServletResponse response) {
        String microId = request.getParameter("microId");
        String lastPlayTime = request.getParameter("lastPlayTime");
        String finishedFlag = request.getParameter("finishedFlag");
        String publishLessonId = request.getParameter("publishLessonId");
        String publisherId = request.getParameter("publisherId");
        Integer userId = Integer.parseInt(request.getParameter("userId"));
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
                    if (publisherId != null && !"".equals(publisherId)) {
                        sendMessageToTeacher(publisherId, student.getName(), userId);
                    }
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
            //推送消息
//            if (publisherId != null && !"".equals(publisherId)) {
//                sendMessageToTeacher(publisherId, student.getName(), userId);
//            }
            this.microUserRecordService.add(ur);
        }
        return ResponseInfomation.OPERATION_SUC;
    }

    private void sendMessageToTeacher(String publisherId, String studentName, Integer studentId) {
        Message message = new Message();
        message.setAppId(SysContants.SYSTEM_APP_ID);
        message.setContent("您好，您的学生" + studentName + "完成了您布置的作业！");
        message.setPosterId(studentId);
        message.setPosterName(studentName);
        message.setRecordStatus(StatusDefaultContans.ZERO);
        message.setTag(MessageCenterContants.FINISHED_PATH_CODE_HOMEWORK);
        PushMessageUtil.sendMessage(message, Integer.parseInt(publisherId));
        PushMessageUtil.pushMessage(Integer.parseInt(publisherId));
    }

    
    //环信推送
    private void sendMessageToStudent(Integer publisherId, String teacherName, JSONArray classList, Integer schoolId) {
        Message message = new Message();
        message.setAppId(SysContants.SYSTEM_APP_ID);
        message.setContent("您有新的微课学习！");
        message.setPosterId(publisherId);
        message.setPosterName(teacherName);
        message.setRecordStatus(StatusDefaultContans.ZERO);
        message.setTag(MessageCenterContants.PATH_CODE_MICRO);
        List<Integer> receiverIdList = new ArrayList<Integer>();
        for (int i = 0; i < classList.size(); i++) {
            JSONObject receivers = (JSONObject) classList.get(i);
            String receiverId = (String) receivers.get("relateId");
            receiverIdList.add(Integer.parseInt(receiverId));
        }
        List<Student> studentList = this.studentService.findStudentListByListId(receiverIdList, TagsTypeContans.TEAM_ID, schoolId);
        receiverIdList = getUserId(studentList);
        PushMessageUtil.sendMessage(message, receiverIdList);
        PushMessageUtil.pushMessageList(receiverIdList);
    }
    
    private void adddMicroLessonMessage(JSONArray microList, JSONArray publishObjectList, Date startDate, Date finishedDate, String title, Integer publisherId, String publisherName, String publishUuid){
    	MicroLessonMessage mlm = null;
    	Date date = new Date();
         for (int j = 0; j < publishObjectList.size(); j++) {
             JSONObject mlrj = (JSONObject) publishObjectList.get(j);
             String relateId = (String) mlrj.get("relateId");
             Object relateName = mlrj.get("relateName");
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
    }
    
    /**
     * 微课管理
     * @param request
     * @param response
     * @param page
     * @return
     * @throws ParseException
     * @throws IOException
     */
    @RequestMapping(value = "/publishManager")
    public String publishManager(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("page") Page page) throws ParseException, IOException {
        String relateId = request.getParameter("relateId");
        String userId = request.getParameter("userId");
        String pageSize = request.getParameter("pageSize");
        String pageNo = request.getParameter("pageNo");
        if (pageSize != null && !"".equals(pageSize)) {
            page.setPageSize(Integer.parseInt(pageSize));
        }
        if (pageNo != null && !"".equals(pageNo)) {
            page.setCurrentPage(Integer.parseInt(pageNo));
        }
        Integer relateIdint = Integer.parseInt(relateId);
        List<Student> stList = this.studentService.findStudentOfTeam(relateIdint);
        List<MicroLessonRelateVo> mlrList = this.microPrepareService.searchPublishedLesson(Integer.parseInt(userId), Integer.parseInt(relateId), page);
        List<MicroLessonRelateVo> reMlrList = new ArrayList<MicroLessonRelateVo>();
        for (MicroLessonRelateVo rv : mlrList) {
            Integer finishedCount = 0;
            Integer unFinishedCount = 0;
            for (Student st : stList) {
                MicroPublishedRecord mpr = this.microPrepareService.searchUserPublishedRecord(rv.getPublishMicroLessonId(), st.getUserId(), st.getName(), st.getStudentNumber());
                if (mpr.getFinishedFlag() == StudyFinishedFlag.FINISHED) {
                    finishedCount++;
                } else if (mpr.getFinishedFlag() == StudyFinishedFlag.NOT_FINISHED) {
                    unFinishedCount++;
                } else if (mpr.getFinishedFlag() == StudyFinishedFlag.PART_FINISHED) {
                    finishedCount++;
                }
            }
            rv.setFinishedCount(finishedCount);
            rv.setUnFinishedCount(unFinishedCount);
            reMlrList.add(rv);
        }
        JSONArray obj = JSONArray.fromObject(reMlrList);
        PrintWriter pw = setAjaxResponse(request, response);
        pw.print(obj);
        return null;
    }
    /**
     * 删除单个已发布的微课
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/deletePublished")
    public String deletePublished(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String publishId = request.getParameter("publishId");
        String relateId = request.getParameter("relateId");
        Integer flag = this.microPrepareService.deletePublish(publishId, Integer.parseInt(relateId));
        PrintWriter pw = setAjaxResponse(request, response);
        pw.print(flag);
        return null;
    }
    
    /**
     * 获取某学生学习的微课列表
     * @param request
     * @param response
     * @param page
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/studyList")
    public String studyList(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("page") Page page) throws IOException {
        String userId = request.getParameter("userId");
        String pageSize = request.getParameter("pageSize");
        String pageNo = request.getParameter("pageNo");
        //为了兼容旧版本
        String pageNumber = request.getParameter("pageNumber");
        String subject = request.getParameter("subjectName");
        //作业的uuid
        String publishUuid = request.getParameter("publishUuid");
        if (pageSize != null && !"".equals(pageSize)) {
            page.setPageSize(Integer.parseInt(pageSize));
        }
        if (pageNo != null && !"".equals(pageNo)) {
            page.setCurrentPage(Integer.parseInt(pageNo));
        }
        if (pageNumber != null && !"".equals(pageNumber)) {
            page.setCurrentPage(Integer.parseInt(pageNumber));
        }
        Student s = this.studentService.findStudentByUserId(Integer.parseInt(userId));
        Object returnObj;
        if (s != null) {
            Integer teamId = s.getTeamId();
            if (teamId != null) {
                Date fdate = new Date();
                List<MicroLessonRelateVo> mlrvList = null;
                if (publishUuid != null && !"".equals(publishUuid)) {
                	mlrvList = this.microPrepareService.searchHistoryPublishedLesson(null, publishUuid, teamId, subject, fdate, false, page);
                } else {
                	mlrvList = this.microPrepareService.searchHistoryPublishedLesson(null, teamId, subject, fdate, false, page);
                }
                JSONArray studyList = new JSONArray();
                for (MicroLessonRelateVo rvo : mlrvList) {
                    JSONObject relateJson = new JSONObject();
                    Integer finishedCount = 0;
                    String relateName = rvo.getRelateName();
                    String subjectName = null;
                    if(!StringUtils.isEmpty(relateName)){
                    	subjectName = relateName.substring(relateName.indexOf("[") + 1, relateName.indexOf("]"));
                    }
                    relateJson.put("fmtStartDate", new SimpleDateFormat("yyyy-MM-dd HH:mm").format(rvo.getStartDate()));
                    relateJson.put("fmtFinishedDate", new SimpleDateFormat("yyyy-MM-dd HH:mm").format(rvo.getFinishedDate()));
                    relateJson.put("publishMicroLessonId", rvo.getPublishMicroLessonId());
                    relateJson.put("publiserId", rvo.getPublisherId());
                    relateJson.put("publisherName", rvo.getPublisherName());
                    relateJson.put("subjectName", subjectName);
                    relateJson.put("title", rvo.getTitle() == null ? "" : rvo.getTitle());
                    JSONArray array = microPrepareService.getPublishedPlayJson(rvo.getPublishMicroLessonId());
                    JSONArray rebuiltArray = new JSONArray();
                	for (int i = 0; i < array.size(); i++) {
                        JSONObject obj = array.getJSONObject(i);
                        String microId = (String) obj.get("id");
                        MicroUserRecord ur = this.microUserRecordService.getUniqueRecord(Integer.parseInt(userId), microId, rvo.getPublishMicroLessonId());
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
                JSONObject json = new JSONObject();
                json.put("studyList", studyList);
                returnObj = json;
            } else {
                returnObj = "no_class";
            }
        } else {
            returnObj = "not_a_student";
        }
        PrintWriter pw = setAjaxResponse(request, response);
        pw.print(returnObj);
        return null;
    }

    @RequestMapping(value = "/batchReviews")
    public String batchReviews(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String microPublishId = request.getParameter("microPublishedId");
        String relateId = request.getParameter("relateId");
        Integer relateIdint = Integer.parseInt(relateId);
        List<Student> fst = new ArrayList<Student>();
        List<Student> ust = new ArrayList<Student>();
        List<Student> pst = new ArrayList<Student>();
        List<Student> stList = this.studentService.findStudentOfTeam(relateIdint);
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
        JSONObject obj = new JSONObject();
        obj.put("microPublishId", microPublishId);
        obj.put("relateId", relateId);
        obj.put("allList", JSONArray.fromObject(stList));
        obj.put("finishedList", JSONArray.fromObject(fst));
        obj.put("unFinishedList", JSONArray.fromObject(ust));
        obj.put("partFinishedList", JSONArray.fromObject(pst));
        PrintWriter pw = setAjaxResponse(request, response);
        pw.print(obj);
        return null;
    }

    @RequestMapping(value = "/saveReviews")
    @ResponseBody
    public String saveReviews(HttpServletRequest request, HttpServletResponse response) {
        String data = request.getParameter("reviewData");
        JSONObject obj = JSONObject.fromObject(data);
        String reviews = (String) obj.get("reviews");
        String microPublishId = (String) obj.get("microPublishId");
        Integer reward = (Integer) obj.get("reward");
        JSONArray studentList = (JSONArray) obj.get("studentList");
        for (int i = 0; i < studentList.size(); i++) {
            Integer userId = (Integer) studentList.get(i);
            this.microPrepareService.updateUserPublishedRecord(microPublishId, userId, reviews, reward);
        }
        return ResponseInfomation.OPERATION_SUC;
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
    
    /*********************************************************************/
    /**************************  以下为微课星学生端接口   ***********************/
    /*********************************************************************/
    /**
     * 获取微课星学生端科目列表	
     * @param page
     * @param userId 用户id
     * @return
     * @author 陈业强
     */
    @RequestMapping(value = "/student/subjects")
    @ResponseBody
    public List<CommonSubject> subjects(@ModelAttribute("page") Page page,@RequestParam(value = "userId")Integer userId){
    	Student stu = this.studentService.findStudentByUserId(userId);
    	List<CommonSubject> subject = new ArrayList<CommonSubject>();
    	if(stu != null){
    		Integer teamId = stu.getTeamId();
    		if(teamId != null){
    			Team team = this.teamService.findTeamById(teamId);
    			Grade grade = this.gradeService.findGradeById(team.getGradeId());
    			List<SubjectGrade> subjectList = null;
    			CommonSubject vo = null;
    			if(grade!=null){
    				subjectList = this.subjectGradeService.findSubjectGradeByGradeCode(grade.getSchoolId(),grade.getUniGradeCode() );
    				for(SubjectGrade sbg : subjectList){
    					vo = new CommonSubject();
    					vo.setCode(sbg.getSubjectCode());
    					vo.setName(sbg.getSubjectName());
    					subject.add(vo);
    				}
    			}
    		}
    	}
		return subject;
    }
    

    private PrintWriter setAjaxResponse(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", -1);
        return response.getWriter();
    }
    
    
    /**=========================================================================================================
     * ==========================================微课星接口开发===============================================
     * =========================================================================================================
     */
   
    /**
     * @method 查询微课信息接口
     * @author hmzhang
     * @param condition
     * @param pageNumber
     * @param pageSize
     * @param sortItem
     * @param sortType
     * @param page
     * @param order
     * @return json
     * @data 2016年06月22日
     */
    @RequestMapping(value = "/student/microMessage/listByStudent")
    @ResponseBody
    public Object listByStudent(
    		@ModelAttribute("condition") MicroLessonMessageCondition condition,
			@RequestParam(value = "pageNumber", required = false, defaultValue = "1") String pageNumber,
			@RequestParam(value = "pageSize", required = false, defaultValue = "20") String pageSize,
			@RequestParam(value = "sortItem", required = false, defaultValue = "create_date") String sortItem,
			@RequestParam(value = "sortType", required = false, defaultValue = "1") Integer sortType,
			Page page, Order order){
    	try {
    		page.setCurrentPage(Integer.parseInt(pageNumber));// 当前页
			page.setPageSize(Integer.parseInt(pageSize));// pageSize
			order.setProperty(sortItem);
			order.setAscending(sortType == 0 ? true : false);
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
        	return new ResponseOrder<List<FormatMicroLessonMessage>>("0", messageVoList,page.getTotalRows());
		} catch (Exception e) {
			ResponseInfo info = new ResponseInfo();
			info.setDetail("参数异常...");
			info.setMsg("参数出错");
			info.setParam("studentUserId");
			return new ResponseError("060110", info);
		}
    }
    
    
    /**
     * @method 删除消息
     * @author hmzhang
     * @param id
     * @param microLessonMessage
     * @return
     * @date 2016年06月22日
     */
    @RequestMapping(value = "/student/microMessage/delete")
    @ResponseBody
    public Object deleteById(
    		@RequestParam(value = "id", required = true) String id,
    		MicroLessonMessage microLessonMessage){
    	try {
    		microLessonMessage = this.microLessonMessageService.findMicroLessonMessageById(Integer.parseInt(id));
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
			return new ResponseError("060612", info);
		}
    }
    
    /**
     * @method 批量更新消息，更新为已读
     * @author hmzhang
     * @param ids
     * @return
     * @date 2016年06月22日
     */
    @RequestMapping(value = "/student/microMessage/updateReadStatus")
    @ResponseBody
    public Object updateReadStatus(
    		@RequestParam(value = "ids", required = true) String ids){
    	try {
    		if (ids != null && !"".equals(ids)) {
				JSONArray jsonArray = JSONArray.fromObject(ids);
				for (int i = 0; i < jsonArray.size(); i++) {
					String id = (String) jsonArray.get(i);
					MicroLessonMessage microLessonMessage = this.microLessonMessageService.findMicroLessonMessageById(Integer.parseInt(id));
					microLessonMessage.setIsRead(true);
					this.microLessonMessageService.modify(microLessonMessage);
				}
			}
    		return new ResponseNormal("0");
		} catch (Exception e) {
			ResponseInfo info = new ResponseInfo();
			info.setDetail("服务器繁忙...");
			info.setMsg("服务器繁忙");
			info.setParam("...");
			return new ResponseError("000002", info);
		}
    }
    
    
    @RequestMapping(value = "/student/unStudyList")
    public String unStudyList(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("page") Page page) throws IOException {
        String userId = request.getParameter("userId");
        String pageSize = request.getParameter("pageSize");
        String pageNo = request.getParameter("pageNumber");
        String subject = request.getParameter("subjectName");
        if (pageSize != null && !"".equals(pageSize)) {
            page.setPageSize(Integer.parseInt(pageSize));
        }
        if (pageNo != null && !"".equals(pageNo)) {
            page.setCurrentPage(Integer.parseInt(pageNo));
        }
        Student s = this.studentService.findStudentByUserId(Integer.parseInt(userId));
        Object returnObj;
        boolean flag = false;
        if (s != null) {
            Integer teamId = s.getTeamId();
            if (teamId != null) {
            	//查询历史作业记录，不管有没有过期
                List<MicroLessonRelateVo> mlrvList = this.microPrepareService.searchHistoryPublishedLesson(null, teamId, subject, null, false, page);
                JSONArray studyList = new JSONArray();
                for (MicroLessonRelateVo rvo : mlrvList) {
                    JSONObject relateJson = new JSONObject();
                    String relateName = rvo.getRelateName();
                    String subjectName = null;
                    if(!StringUtils.isEmpty(relateName)){
                    	subjectName = relateName.substring(relateName.indexOf("[") + 1, relateName.indexOf("]"));
                    }
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
	                	relateJson.put("fmtStartDate", new SimpleDateFormat("yyyy-MM-dd HH:mm").format(rvo.getStartDate()));
	                    relateJson.put("fmtFinishedDate", new SimpleDateFormat("yyyy-MM-dd HH:mm").format(rvo.getFinishedDate()));
	                    relateJson.put("publishMicroLessonId", rvo.getPublishMicroLessonId());
	                    relateJson.put("publiserId", rvo.getPublisherId());
	                    relateJson.put("publisherName", rvo.getPublisherName());
	                    relateJson.put("subjectName", subjectName);
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
                JSONObject json = new JSONObject();
                json.put("unStudyList", studyList);
                returnObj = json;
            } else {
                returnObj = "no_class";
            }
        } else {
            returnObj = "not_a_student";
        }
        PrintWriter pw = setAjaxResponse(request, response);
        pw.print(returnObj);
        return null;
    }
    
    /**
     * @method 获取学生的班级信息
     * @param userId
     * @return
     */
    @RequestMapping(value = "/student/team")
    @ResponseBody
    public Object team(@RequestParam(value = "userId")String userId){
    	try {
    		Student stu = this.studentService.findStudentByUserId(Integer.parseInt(userId));
    		Team team = null;
    		if(stu != null){
    			team = this.teamService.findTeamById(stu.getTeamId());
    		}
        	return new ResponseVo<Team>("0", team);
		} catch (Exception e) {
			ResponseInfo info = new ResponseInfo();
			info.setDetail("服务器繁忙...");
			info.setMsg("服务器繁忙");
			info.setParam("...");
			return new ResponseError("000002", info);
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
    
    
    
    /**
     * 重构后的微课星
     * @Title: uploadMicroLesson 
     * @Description: TODO
     * @param microFile
     * @param thumbFile
     * @param request
     * @param response
     * @return
     * @throws IOException
     * @return: Object
     * @date 2016年11月29日
     */
    @RequestMapping(value = "/uploadMicroLesson")
    @ResponseBody
    public Object uploadMicroLesson(@RequestParam("microFile") final MultipartFile microFile, @RequestParam("thumbFile") final MultipartFile thumbFile, final HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        final String userId = request.getParameter("userId");
        if("".equals(userId) || userId == null || microFile == null || thumbFile == null){
        	ResponseInfo info = new ResponseInfo();
			info.setDetail("必填参数为空...");
			info.setMsg("必填参数为空...");
			info.setParam("userId,microFile,thumbFile...");
			return new ResponseError("000002", info);
        }
        final String title = request.getParameter("title");
        final String lessonLength = request.getParameter("lessonLength");
        String subjectName = request.getParameter("subjectName");
        String subjectCode = request.getParameter("subjectCode");
        
        
        WkxVo vo = new WkxVo();
        Long lessonLe = null;
        if(!StringUtils.isEmpty(lessonLength)){
        	lessonLe = Long.parseLong(lessonLength);
        	vo.setLessonLength(lessonLe);
        }
		vo.setLessonLength(lessonLe);
		vo.setSubjectCode(subjectCode);
		vo.setSubjectName(subjectName);
		
		final String uploadFileName = microFile.getOriginalFilename();
    	
        if(uploadFileName.contains(".wkx")){
        	
        	//上传微课星的包
        	FileResult result = this.fileService.upload(microFile.getInputStream(), StringUtils.getFilenameExtension(uploadFileName),
                microFile.getContentType(), uploadFileName,
                String.valueOf(SysContants.SYSTEM_APP_ID));
            if (result != null && FileStatusCode.UPLOAD_SUCCESS.equals(result.getStatusCode())) {
            	final EntityFile entityFile = result.getEntityFile();
            	final MicroLesson ml = this.microLessonService.saveOrUpdateMicroLesson(SysContants.SYSTEM_APP_ID, null, entityFile.getUuid(), MicroType.MICRO_COURSE, title, Integer.parseInt(userId), null, null, null,vo);
                if (thumbFile != null) {
                	this.fileService.updateThumbnail(entityFile, thumbFile.getInputStream(), "png", thumbFile.getContentType());
                }
                	
                String realPath = request.getServletContext().getRealPath("/") +"tmp";
                
        		CommonsMultipartFile cf= (CommonsMultipartFile)microFile; 
        		final String contentType = cf.getContentType();
        		
        		long currentTime = System.currentTimeMillis();
        		
                final String storePath = realPath + File.separator + uploadFileName.substring(0,uploadFileName.indexOf(".wkx")) + "_" + currentTime + File.separator + uploadFileName;
                File storeFile = new File(storePath);
                //判断文件是否存在
                if(storeFile.exists()){
                	storeFile.delete();
                }
                
                if(!storeFile.exists()){
                	if(!storeFile.getParentFile().exists()) {
                		storeFile.getParentFile().mkdirs();
                	}
                	storeFile.createNewFile();
                	//把spring文件转存为真正的文件
                	microFile.transferTo(storeFile);
                }
                
                final String destPath = realPath + File.separator + uploadFileName.substring(0,uploadFileName.indexOf(".wkx")) + "_" + currentTime + File.separator + "tmp";
                		
                //调用线程池方法
                taskExecutor.execute(new Runnable() {
    				public void run() {
    					
    					excute(storePath ,destPath, contentType,uploadFileName, userId, title, entityFile.getUuid(), lessonLength, ml !=null ? ml.getUuid() : null);
    					
    		        }
    			});
                if(ml != null){
                	return new ResponseNormal("0");
                }else{
                	ResponseInfo info = new ResponseInfo();
        			info.setDetail("文件关联失败...");
        			info.setMsg("文件关联失败...");
        			info.setParam("...");
        			return new ResponseError("000002", info);
                }
            } else {
            	ResponseInfo info = new ResponseInfo();
    			info.setDetail("文件上传失败...");
    			info.setMsg("文件上传失败...");
    			info.setParam("...");
    			return new ResponseError("000002", info);
            }
        } else {
        	ResponseInfo info = new ResponseInfo();
			info.setDetail("文件格式错误...");
			info.setMsg("文件格式错误...");
			info.setParam("...");
			return new ResponseError("000002", info);
        }
    }
    
    private void excute(String storePath, String destPath, String contentType, String uploadFileName, String userId, String title, String entityId, String lessonLength, String uuid){
    	try {
    		String mainJsonFileName = null;
    		String mediaFileName = null;
    		String propertyFlieName = null;
    		
    		//解压文件
            ZipFileUtil.unZipFileWithPassword(storePath, destPath);
    		
    		WkxVo vo = new WkxVo();
    		if(!StringUtils.isEmpty(lessonLength)){
            	Long lessonLe = Long.parseLong(lessonLength);
            	vo.setLessonLength(lessonLe);
            }
    		List<FileResult> bgPictureResults = new ArrayList<FileResult>();
            if (userId != null) {
            	 //解压.wkx文件格式的文件
                if(uploadFileName.contains(".wkx")){
                	mainJsonFileName = destPath + PathAndFileName.MAIN_JSON;
                	mediaFileName =  destPath + PathAndFileName.MEDIA;
                	propertyFlieName = destPath + PathAndFileName.PROPERTY;
                	//上传 解压后的文件
                	File mainJson = new File(mainJsonFileName);
                	File media = new File(mediaFileName);
                	File property = new File(propertyFlieName);
                	File logo = null;
                	List<File> bgPictures = new ArrayList<File>();
                	File[] files = getFiles(destPath + PathAndFileName.RESOURCE_PATH);
                	if(files.length > 0){
                		for(File f : files){
                			String filename = f.getName();
                			if(filename.indexOf("logo.png") > -1){
                				logo = f;
                			}else{
                				bgPictures.add(f);
                			}
                		}
                	}
                	
                	FileResult mainJsonResult = null;
                	FileResult mediaResult =  null;
                	FileResult propertyResult = null;
                	FileResult logoResult =  null;
                	if(bgPictures.size() > 0 ){
                		for(File bgPicture : bgPictures){
                			FileResult bgPictureResult =  this.fileService.upload(bgPicture, contentType, String.valueOf(SysContants.SYSTEM_APP_ID));
                			if (bgPictureResult != null && FileStatusCode.UPLOAD_SUCCESS.equals(bgPictureResult.getStatusCode())) {
                				bgPictureResult.getEntityFile().setFileName(bgPicture.getName());
                				bgPictureResults.add(bgPictureResult);
                			}
                		}
                	}
                	if(mainJson != null && mainJson.exists()){
                		mainJsonResult = this.fileService.upload(mainJson, contentType, String.valueOf(SysContants.SYSTEM_APP_ID));
                	}
                	if(media != null && media.exists()){
                		mediaResult = this.fileService.upload(media, contentType, String.valueOf(SysContants.SYSTEM_APP_ID));
                	}
                	if(property != null && property.exists()){
                		propertyResult =  this.fileService.upload(property, contentType, String.valueOf(SysContants.SYSTEM_APP_ID));
                	}
                	if(logo != null && logo.exists()){
                		logoResult =  this.fileService.upload(logo, contentType, String.valueOf(SysContants.SYSTEM_APP_ID));
                	}
                	if (mainJsonResult != null && FileStatusCode.UPLOAD_SUCCESS.equals(mainJsonResult.getStatusCode())) {
                		String jsonEntityId = mainJsonResult.getEntityFile().getUuid();
                		vo.setJsonEntityId(jsonEntityId);
                	}
                	if (mediaResult != null && FileStatusCode.UPLOAD_SUCCESS.equals(mediaResult.getStatusCode())) {
                		String mediaEntityId = mediaResult.getEntityFile().getUuid();
                		vo.setMediaEntityId(mediaEntityId);
                	}
                	if (propertyResult != null && FileStatusCode.UPLOAD_SUCCESS.equals(propertyResult.getStatusCode())) {
                		String propertyEntityId = propertyResult.getEntityFile().getUuid();
                		vo.setPropertyEntityId(propertyEntityId);
                	}
                	if (logoResult != null && FileStatusCode.UPLOAD_SUCCESS.equals(logoResult.getStatusCode())) {
                		String logoEntityId = logoResult.getEntityFile().getUuid();
                		vo.setLogoEntityId(logoEntityId);
                	}
                }
                
                MicroLesson ml = this.microLessonService.saveOrUpdateMicroLesson(SysContants.SYSTEM_APP_ID, uuid, entityId, MicroType.MICRO_COURSE, title, Integer.parseInt(userId), null, null, null,vo);
                EntityFile obj = this.entityFileService.findFileByUUID(ml.getEntityId());
              platform.education.resource.model.Resource resource=new platform.education.resource.model.Resource();
              resource.setCreateDate(new Date());
              resource.setModifyDate(new Date());
              resource.setDescription(ml.getDescription());
              resource.setIsPersonal(true);
              resource.setObjectId(ml.getUuid());
              resource.setResType(1);
              resource.setTitle(ml.getTitle());
              resource.setUserId(ml.getUserId());
              resource.setVerify(ResourceCondition.DEFAULT_VERIFY_NO);
              resource.setIconType(IconUtil.setIcon(obj.getExtension()));
              resource.setUuid(UUIDUtil.getUUID());
              this.resourceService.add(resource);
                if(bgPictureResults.size() > 0){
                	for(FileResult fr : bgPictureResults){
                		MicroLessonBgpicture mlb = new MicroLessonBgpicture();
                		mlb.setEntityId(fr.getEntityFile().getUuid());
                		mlb.setEntityName(fr.getEntityFile().getFileName());
                		mlb.setLessonId(ml.getUuid());
                		microLessonBgpictureService.add(mlb);
                	}
                }
            } 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			String path = storePath.substring(0, storePath.lastIndexOf(File.separator));
			deleteFile(path);
		}
    }
    
    /** 
	 * 删除文件、文件夹 
	 */  
	public static void deleteFile(String path) {  
	    File file = new File(path);  
	    if (file.isDirectory()) {  
	        File[] ff = file.listFiles();  
	        for (int i = 0; i < ff.length; i++) {  
	            deleteFile(ff[i].getPath());  
	        }  
	    }  
	    file.delete();  
	}
}
