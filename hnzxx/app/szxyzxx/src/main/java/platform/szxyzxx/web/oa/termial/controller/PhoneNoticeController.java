package platform.szxyzxx.web.oa.termial.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import platform.education.generalTeachingAffair.model.DepartmentTeacher;
import platform.education.generalTeachingAffair.model.Teacher;
import platform.education.generalTeachingAffair.vo.DepartmentTeacherCondition;
import platform.education.generalTeachingAffair.vo.TeacherCondition;
import platform.education.generalTeachingAffair.vo.TeacherVo;
import platform.education.im.service.IMService;
import platform.education.oa.model.Notice;
import platform.education.oa.model.NoticeImg;
import platform.education.oa.model.NoticeUser;
import platform.education.oa.model.NoticeUserDeleted;
import platform.education.oa.service.NoticeImgService;
import platform.education.oa.service.NoticeService;
import platform.education.oa.service.NoticeUserService;
import platform.education.oa.utils.UUIDUtils;
import platform.education.oa.utils.WebUtil;
import platform.education.oa.vo.NoticeCondition;
import platform.education.oa.vo.NoticeImgCondition;
import platform.education.oa.vo.NoticeUserDeletedCondition;
import platform.service.storage.model.EntityFile;
import platform.service.storage.vo.FileResult;
import platform.szxyzxx.web.common.contants.SysContants;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.oa.contans.ContansOfOa;
import platform.szxyzxx.web.oa.termial.vo.PhoneNoticeImgsVo;
import platform.szxyzxx.web.oa.termial.vo.PhoneNoticeVo;
import platform.szxyzxx.web.oa.utils.CommonUtil;
import platform.szxyzxx.web.oa.utils.JsonWriteUtils;
import platform.szxyzxx.web.oa.utils.PushUtils;

/**
 * 公告控制类
 * 
 * @author sky
 * @version 1.0 2015-6-10
 */

@Controller
@RequestMapping(value = "/termial/oa/notice")
public class PhoneNoticeController extends BaseController {

	@Autowired
	@Qualifier("noticeService")
	private NoticeService oaNoticeService;

	@Autowired
	private NoticeUserService noticeUserService;

	@Autowired
	NoticeImgService imgService;
	@Autowired
	IMService imService;
	@Resource(name="oa_notice_taskExecutor")
	private TaskExecutor taskExecutor;

	//发布对象是所有人
		private String all = "0";
		//发布对象是部门
		private String department = "1";
		//发布对象是个人
		private String person = "2";
	/**
	 * 发布公告
	 * 
	 * @param oaNotice
	 * @param user
	 * @return
	 */

	@RequestMapping(value = "/creator", method = RequestMethod.POST)
	@ResponseBody
	public void creator(HttpServletRequest request, HttpServletResponse response) {

		JSONObject json_return = new JSONObject();

		try {

			String timestamp = request.getParameter("timestamp");
			String signature = request.getParameter("signature");
			String posterId = request.getParameter("posterId");
			String posterName = request.getParameter("posterName");
			String ownerId = request.getParameter("ownerId");
			String ownerType = request.getParameter("ownerType");
			String departmentId = request.getParameter("departmentId");
			String title = request.getParameter("title");
			String type = request.getParameter("type");
			String content = request.getParameter("content");
			String receiverType = request.getParameter("receiverType");
			String receiverList = request.getParameter("receiverList");
			Set<MultipartFile> files = getFileSet(request);
			if (files != null) {
				posterName = request.getParameter("posterName");
				title = request.getParameter("title");
				content = request.getParameter("content");
				type = request.getParameter("type");
			}

			String[] str = { timestamp, posterId, posterName, ownerId,
					ownerType, title, type, content, receiverType, receiverList };
//			 if(WechatCallbackApi.isLegitimacyApi(str, signature)){//验证签名是否合法

			Notice notice = new Notice();

			notice.setPosterId(Integer.valueOf(posterId));

			notice.setPosterName(posterName);

			notice.setOwnerId(Integer.valueOf(ownerId));

			notice.setOwnerType(ownerType);
			notice.setAppId(SysContants.SYSTEM_APP_ID);
			notice.setUuid(UUIDUtils.getUUID());
			notice.setTitle(title);
			notice.setType(type);
			notice.setContent(content);
			notice.setReceiverType(Integer.parseInt(receiverType));
			Notice noticeOfAdd = this.oaNoticeService.add(notice);
			
			if (files != null) {
				saveFile(files, notice.getId(), Integer.valueOf(posterId),
						request);
			}
			
			List<Teacher> teachers = new ArrayList<Teacher>();
			List<String> userlists = new ArrayList<String>();
			
			if(person.equals(receiverType)){
				if (!StringUtils.isEmpty(receiverList)) {
					receiverList = posterId + "," + receiverList;
					String[] us = receiverList.split(",");
					userlists = CommonUtil.distinctString(us);
				}
			}
			
			Teacher teacher = null;
			if (person.equals(receiverType)) {  //指定人员的数据
				for (String s : userlists) {
					teacher = this.teacherService.findOfUser(
							Integer.valueOf(ownerId), Integer.valueOf(s));
					if (teacher != null) {
						saveNoticeToUser(notice.getId(), teacher.getUserId(),
								teacher.getName());
						teachers.add(teacher);
					}
				}
			} else if(department.equals(receiverType)) { //部门数据
				String[] depId = departmentId.split(",");
				NoticeUser nu = null;
				for(int i=0;i<depId.length;i++){
					nu = new NoticeUser();
					nu.setDepartmentId(Integer.parseInt(depId[i]));
					nu.setNoticeId(noticeOfAdd.getId());
					nu.setIsDeleted(false);
					this.noticeUserService.add(nu);
				}
			} else {  //所有数据 不做操作，只在主表标识是全部数据即可
				teachers = this.teacherService.findTeacherListBySchoolId(Integer.parseInt(ownerId));
			}
			
			if(department.equals(receiverType)){
				//部门的时候，手机端传部门ID过来，再根据部门找到所有的老师，包过“其他”这个选项，ID是-1 2015-10-23
				String[] departmenIds = departmentId.split(",");
				DepartmentTeacherCondition dtc = new DepartmentTeacherCondition();
				List<DepartmentTeacher> dtLists = new ArrayList<DepartmentTeacher>();
				for(String depId : departmenIds){
					dtc.setDepartmentId(Integer.valueOf(depId));
					dtc.setIsDeleted(false);
					List<DepartmentTeacher> dtList = departmentTeacherService.findDepartmentTeacherByCondition(dtc, null, null);
					dtLists.addAll(dtList);
				}
				for(String depId : departmenIds){
					if(depId.equals("-1")){
						TeacherCondition teacherCondition = new TeacherCondition();
						teacherCondition.setIsDelete(false);
						List<TeacherVo> teacher1 = teacherService.findTeacherVoByCondition(teacherCondition, null, null);
						for(TeacherVo t : teacher1){
							if(t.getDepartmentId() !=null || !"".equals(t.getDepartmentId())){
								Teacher teac = teacherService.findTeacherById(t.getId());
								teachers.add(teac);
							}
						}
						break;
					}
				}
				Teacher t = null;
				for(DepartmentTeacher dt : dtLists){
					t = teacherService.findTeacherById(dt.getTeacherId());
					if(t != null){
						teachers.add(t);
					}
				}
				teachers = CommonUtil.distinctTeacherOfTeacherId(teachers);
			}

			json_return.put("common_return", ResponseInfomation.OPERATION_SUC);
			json_return.put("noticeId", notice.getId() + "");
			/*
			 * }else{ json_return.put("common_return","非法调用！"); }
			 */

			JsonWriteUtils.setJson(json_return, response);
			/**
			 * 处理推送
			 */
			if(!teachers.isEmpty()){
				NoticeImgCondition condition = new NoticeImgCondition();
				condition.setNoticeId(notice.getId());
				List<NoticeImg> imgs = this.imgService
						.findNoticeImgByCondition(condition);
				List<PhoneNoticeImgsVo> images = new ArrayList<PhoneNoticeImgsVo>();
				PhoneNoticeImgsVo vo = null;
				for (NoticeImg img : imgs) {
					vo = new PhoneNoticeImgsVo();
					vo.setImage(img.getImgUrl());
					images.add(vo);
				}
				PhoneNoticeVo nvo = new PhoneNoticeVo(notice, images);
				JSONObject jsonObjects = JSONObject.fromObject(nvo);
				PushUtils.pushOfTaskExecutor(teachers, 3, receiverType, jsonObjects, imService,taskExecutor);	
			}

		} catch (Exception e) {
			json_return
					.put("common_return", ResponseInfomation.OPERATION_ERROR);
			JsonWriteUtils.setJson(json_return, response);
		} finally {
			if (json_return != null) {
				json_return.clear();
			}
		}
	}

	/**
	 * 查询发送给学校全体教工的通知
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "notesOfAllInSchool")
	@ResponseBody
	public void getNotesOfAllInSchool(HttpServletRequest request,
			HttpServletResponse response, @ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order) {

		lists(request, response, page, order, 0);

	}

	/**
	 * 查询发送给指定部门的通知
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "notesOfDepartment")
	@ResponseBody
	public void getNotesOfDepartment(HttpServletRequest request,
			HttpServletResponse response, @ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order) {

		lists(request, response, page, order, 1);

	}

	/**
	 * 查询自己发布的的通知
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "postedNotesInSchool")
	@ResponseBody
	public void getPostedNotesInSchool(HttpServletRequest request,
			HttpServletResponse response, @ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order) {

		lists(request, response, page, order, 2);

	}

	/**
	 * 删除公告
	 * 
	 * @param oaNotice
	 * @param user
	 * @return
	 */

	@RequestMapping(value = "/del", method = RequestMethod.POST)
	@ResponseBody
	public void del(HttpServletRequest request, NoticeUser notice,
			HttpServletResponse response) {

		JSONObject json_return = new JSONObject();
		try {
			request.setCharacterEncoding("UTF-8");
			String timestamp = request.getParameter("timestamp");
			String signature = request.getParameter("signature");

			String notice_id = request.getParameter("notice_id");
			String user_id = request.getParameter("user_id");
			
			//作用 ：当前用户删除数据的时候 不显示本条数据
			NoticeUserDeleted noticeUserDeleted = new NoticeUserDeleted();
			noticeUserDeleted.setIsDelete(true);
			noticeUserDeleted.setNoticeId(Integer.parseInt(notice_id));
			noticeUserDeleted.setUserId(Integer.parseInt(user_id));
			
			NoticeUserDeletedCondition noticeUserDeletedCondition = new NoticeUserDeletedCondition();
			noticeUserDeletedCondition.setIsDelete(true);
			noticeUserDeletedCondition.setNoticeId(Integer.parseInt(notice_id));
			noticeUserDeletedCondition.setUserId(Integer.parseInt(user_id));
			List<NoticeUserDeleted> list = noticeUserDeletedService.findNoticeUserDeletedByCondition(noticeUserDeletedCondition);
			if(list.size() <= 0){
				this.noticeUserDeletedService.add(noticeUserDeleted);
			}
			
			json_return.put("common_return", ResponseInfomation.OPERATION_SUC);

			JsonWriteUtils.setJson(json_return, response);
		} catch (Exception e) {

			json_return
					.put("common_return", ResponseInfomation.OPERATION_ERROR);
			JsonWriteUtils.setJson(json_return, response);
		} finally {

			if (json_return != null) {
				json_return.clear();
			}

		}
	}

	public void lists(HttpServletRequest request, HttpServletResponse response,
			Page page, Order order, int type) {

		JSONObject json_return = new JSONObject();
		JSONArray array = new JSONArray();
		try {

			String userId = request.getParameter("userId");
			String ownerId = request.getParameter("ownerId");
			String ownerType = request.getParameter("ownerType");
			String new_or_old = request.getParameter("new_or_old");
			String baseline_date = request.getParameter("baselineDate");
			String page_num = request.getParameter("page_num");
			int num = 8;
			if (!StringUtils.isEmpty(page_num)) {
				try {
					num = Integer.parseInt(page_num);
				} catch (Exception ex) {
					// TODO: handle exception
				}
			}

			order.setProperty(order.getProperty() != null ? order.getProperty()
					: "create_date");
			page.setPageSize(num);

			List<Notice> items = new ArrayList<Notice>();
			Teacher teacher = this.teacherService.findOfUser(
					Integer.valueOf(ownerId), Integer.valueOf(userId));
			
			NoticeCondition nCondition = new NoticeCondition();
			nCondition.setOwnerId(Integer.parseInt(ownerId));
			nCondition.setOwnerType(ownerType);
			nCondition.setReceiverType(ContansOfOa.all);
			nCondition.setReceiverId(Integer.parseInt(userId));
			nCondition.setTeacherId(teacher==null?-1:teacher.getId());
			nCondition.setPosterId(Integer.parseInt(userId));
			nCondition.setNew_or_old(new_or_old);
			nCondition.setBaseline_date(baseline_date);
			nCondition.setUserId(Integer.parseInt(userId));
			if(type==0){  //查询与我相关数据
				nCondition.setIsRelatedWithMe(true);
			}else if(type==1){  //查询部门数据
				nCondition.setIsDepartmentRecord(true);
			}else{  //查询我发表的数据
				nCondition.setIsMePublish(true);
			}
			items = this.oaNoticeService.findNoticeByRelatedWithMeForApp(nCondition, page, order);
			
			List<NoticeImg> imgs = null;
			List<PhoneNoticeImgsVo> images = null;
			PhoneNoticeImgsVo vo = null;
			PhoneNoticeVo pvo = null;
			NoticeImgCondition condition = null;
			for (Notice info : items) {
				condition = new NoticeImgCondition();
				condition.setNoticeId(info.getId());
				String content = info.getContent();
				if(content != null || !"".equals(content)){
					info.setContent(WebUtil.HtmltoText(content));
				}
				imgs = this.imgService
						.findNoticeImgByCondition(condition);
				images = new ArrayList<PhoneNoticeImgsVo>();
				for (NoticeImg img : imgs) {
					vo = new PhoneNoticeImgsVo();
					String url = fileService.findFileByUUID(img.getImgUuid()).getHttpUrl();
					vo.setImage(url);//本来放的是img.getImgUrl();
					images.add(vo);
				}
				pvo = new PhoneNoticeVo(info, images);
				JSONObject jsonObjects = JSONObject.fromObject(pvo);
				array.add(jsonObjects);
			}

			json_return.put("common_return", ResponseInfomation.OPERATION_SUC);
			json_return.put("return_info", array);
			JsonWriteUtils.setJson(json_return, response);
		} catch (Exception e) {

			json_return
					.put("common_return", ResponseInfomation.OPERATION_ERROR);
			JsonWriteUtils.setJson(json_return, response);
		} finally {
			if (!array.isEmpty()) {
				array.clear();
			}
			if (json_return != null) {
				json_return.clear();
			}

		}
	}

	private void saveNoticeToUser(Integer nid, Integer userid, String teachName) {
		NoticeUser ru = new NoticeUser();
		ru.setNoticeId(nid);
		ru.setReceiverId(userid);
		ru.setReceiverName(teachName);
		this.noticeUserService.add(ru);
	}

	private void saveFile(Set<MultipartFile> files, Integer aid,
			Integer userId, HttpServletRequest request) {
		try {

			if (files != null) {
				FileResult result = null;
				NoticeImg img = null;
				for (MultipartFile myfile : files) {
					if (!myfile.isEmpty()) {
						String uploadFileName = myfile.getOriginalFilename();
//						Entity persistentFile = this.fileUploadService.uploadFile(SysContants.SYSTEM_APP_ID, myfile, userId, uploadFileName);
						result = this.fileService.upload(myfile.getInputStream(), StringUtils.getFilenameExtension(uploadFileName), myfile.getContentType(), uploadFileName, String.valueOf(SysContants.SYSTEM_APP_ID));
						String imgid = "";
						String imgurl = "";
						
						if (result != null) {
							EntityFile entity = result.getEntityFile();
							if (entity != null) {
								imgid = entity.getUuid();
								imgurl = result.getHttpUrl();
							}
						}
						img = new NoticeImg();
						img.setNoticeId(aid);
						img.setImgUuid(imgid);
						img.setImgUrl(imgurl);
						this.imgService.add(img);
					}

				}
			}
		} catch (Exception ex) {
			// TODO: handle exception
		}

	}

	private Set<MultipartFile> getFileSet(HttpServletRequest request) {
		try {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			Set<MultipartFile> fileset = new LinkedHashSet<MultipartFile>();
			for (Iterator it = multipartRequest.getFileNames(); it.hasNext();) {
				String key = (String) it.next();
				MultipartFile file = multipartRequest.getFile(key);
				if (file.getOriginalFilename().length() > 0) {
					fileset.add(file);
				}
			}
			return fileset;
		} catch (Exception ex) {
			return null;
		}

	}

	private String parameter(HttpServletRequest request, String p) {
		try {
			return new String(request.getParameter(p).getBytes("ISO-8859-1"),
					"UTF-8");
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}
}
