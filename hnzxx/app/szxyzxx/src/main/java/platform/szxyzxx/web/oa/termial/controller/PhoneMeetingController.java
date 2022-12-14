package platform.szxyzxx.web.oa.termial.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
import platform.education.oa.constants.OaPushConstant;
import platform.education.oa.model.Comments;
import platform.education.oa.model.Meeting;
import platform.education.oa.model.MeetingDepartmentCount;
import platform.education.oa.model.MeetingSummary;
import platform.education.oa.model.MeetingUser;
import platform.education.oa.model.SummaryReadUser;
import platform.education.oa.service.CommentsService;
import platform.education.oa.service.MeetingService;
import platform.education.oa.service.MeetingSummaryService;
import platform.education.oa.service.MeetingUserService;
import platform.education.oa.service.SummaryReadUserService;
import platform.education.oa.utils.UtilDate;
import platform.education.oa.vo.MeetingCondition;
import platform.education.oa.vo.MeetingDepartmentCountCondition;
import platform.education.oa.vo.MeetingUserCondition;
import platform.education.oa.vo.MeetingVo;
import platform.education.oa.vo.SummaryReadUserCondition;
import platform.education.user.model.Profile;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.oa.contans.ContansOfOa;
import platform.szxyzxx.web.oa.termial.vo.PhoneCommentsVo;
import platform.szxyzxx.web.oa.termial.vo.PhoneMeetingUserVo;
import platform.szxyzxx.web.oa.termial.vo.PhoneMeetingVo;
import platform.szxyzxx.web.oa.termial.vo.PhoneSummaryReadUserVo;
import platform.szxyzxx.web.oa.utils.CommonUtil;
import platform.szxyzxx.web.oa.utils.JsonWriteUtils;
import platform.szxyzxx.web.oa.utils.PushUtils;
import platform.szxyzxx.web.oa.utils.StringUtils;

/**
 * ???????????????
 * 
 * @author sky
 * @version 1.0 2015-6-10
 */

@Controller
@RequestMapping(value = "/termial/oa/meeting")
public class PhoneMeetingController extends BaseController {

	@Autowired
	MeetingService meetingService;
	@Autowired
	MeetingUserService muService;
	@Autowired
	MeetingSummaryService summaryService;

	@Autowired
	CommentsService commentService;
	@Autowired
	SummaryReadUserService sruService;

	@Autowired
	IMService imService;
	@Resource(name="oa_meeting_taskExecutor")
	private TaskExecutor taskExecutor;
	//????????????????????????
	private String all = "0";
	//?????????????????????
	private String department = "1";
	//?????????????????????
	private String person = "2";
	//????????????
	private String relatedWithMe = "0";
	//????????????
	private String departmentRecord = "1";
	//????????????
	private String allRecord = "2";
	//????????????
	private String myPublish = "3";
	/**
	 * ????????????
	 * @param oaNotice
	 * @param user
	 * @return
	 * 
	 * 2015-9-5   ????????????
	 * ?????????????????????????????????
	 * 	???fanwei?????????  ?????????????????????  0?????????????????????   1?????????????????????  2?????????????????????
	 * 1???????????????  ???????????????????????? ??????????????????  ?????? ????????????type?????????????????? ????????????????????????
	 * 2???????????????  ??????????????????  ???????????????  ???????????????????????????2?????????  ??????????????????
	 * 3???????????? ??????????????????  ?????????????????????????????????????????????ID???????????????????????????
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
			String departmentId = request.getParameter("departmentId");
			String ownerId = request.getParameter("ownerId");
			String ownerType = request.getParameter("ownerType");
			String meetingName = request.getParameter("meetingName");
			String startTime = request.getParameter("startTime");
			String finishTime = request.getParameter("finishTime");
			String address = request.getParameter("address");
			String meetingContent = request.getParameter("meetingContent");
			String fanwei = request.getParameter("fanwei");
			String receiverType = request.getParameter("receiverType");
			String receiverList = request.getParameter("receiverList");
			List<String> userlists = new ArrayList<String>();
			List<Teacher> teachers=new ArrayList<Teacher>();

			String[] str = { timestamp, posterId, posterName, departmentId,
					ownerId, ownerType, meetingName, startTime, finishTime,
					address, meetingContent, fanwei, receiverType, receiverList };

			//?????????????????????  ??????  ??????  ?????????  ?????????  ?????????????????????????????????  2015-10-22
			if(fanwei.equals(person)){
				String[] us = receiverList.split(",");
				//??????????????????????????????ID??????????????????
				boolean flag = true;
				for (String s : us) {// ????????????ID
					for (String si : userlists) {
						if (s.equals(si)) {
							flag = false;
							break;
						}
					}
					if (flag) {
						if (StringUtils.isNotEmpty(s)) {
							userlists.add(s);
						}
					}
				}
			}else if(fanwei.equals(department)){
				//????????????????????????????????????ID?????????????????????????????????????????????????????????????????????????????????ID???-1
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
						List<TeacherVo> teacher = teacherService.findTeacherVoByCondition(teacherCondition, null, null);
						for(TeacherVo t : teacher){
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
					teachers.add(t);
				}
				teachers = CommonUtil.distinctTeacherOfTeacherId(teachers);
			}else if(fanwei.equals(all)){
				List<Teacher> tList = teacherService.findActiveTeacherOfSchool(Integer.valueOf(ownerId));
				teachers.addAll(tList);
			}
			Meeting meeting = new Meeting();
			meeting.setSchoolId(Integer.valueOf(ownerId));
			meeting.setCreateuserId(Integer.valueOf(posterId));
			meeting.setCreatename(posterName);
			Profile profile = this.profileService.findByUserId(Integer
					.valueOf(posterId));
			String imgurl = "";
			if (profile != null) {
				String icon = profile.getIcon();

				if (StringUtils.isNotEmpty(icon)) {
					imgurl = this.fileService.relativePath2HttpUrlByUUID(icon);
				}
			}
            meeting.setUserimage(imgurl);
			meeting.setMeetingName(meetingName);
			meeting.setStarttime(startTime);
			meeting.setEndtime(finishTime);
			meeting.setAddress(address);
			meeting.setMeetingContent(meetingContent);
			if (StringUtils.isNotEmpty(receiverType)) {
				meeting.setQuanbu(Integer.valueOf(receiverType));
			}
			if (StringUtils.isNotEmpty(fanwei)) {
				meeting.setFanwei(Integer.valueOf(fanwei));
			}

			if (StringUtils.isNotEmpty(receiverList)) {
				meeting.setMeetingNum(userlists.size());
			}
			this.meetingService.add(meeting);
			if (StringUtils.isNotEmpty(receiverList)) {

				//========================= ????????????  ==============================//
				/** 2015-9-5 ??????????????????????????????
				 *  ??????????????? ???????????????????????????
				 *  ?????????????????????  ???????????????
				 *  ??????  ?????????  
				 *   0?????????????????????   1?????????????????????  2?????????????????????
				 */
				if(fanwei.equals(person)){
					Teacher teacher = null;
					MeetingUser mu = null;
					for (String s : userlists) {
						teacher = this.teacherService.findOfUser(
								Integer.valueOf(ownerId), Integer.valueOf(s));
						if (teacher != null) {
							mu = new MeetingUser();
							mu.setMeetingId(meeting.getId());
							mu.setUserId(teacher.getUserId());
							mu.setUserName(teacher.getName());
							mu.setIsCanhui(Integer.valueOf(receiverType));
							this.muService.add(mu);
						}
					}
				}else if(fanwei.equals(department)){
					String[] depIds = departmentId.split(",");
					MeetingUser mu = null;
					for (int i=0;i<depIds.length;i++) {
							mu = new MeetingUser();
							mu.setMeetingId(meeting.getId());
							mu.setIsCanhui(Integer.valueOf(receiverType));
							mu.setDepartmentId(Integer.parseInt(depIds[i]));
							this.muService.add(mu);
							
							//???????????????????????????
							addDepartment(Integer.parseInt(depIds[i]),Integer.parseInt(ownerId),Integer.parseInt(ownerType));
					}
				}else if(fanwei.equals(all)){
					//??????????????????????????????????????????else if ????????????
				}
				
				//???????????????
				Teacher teacher = null;
				for (String s : userlists) {
					teacher = this.teacherService.findOfUser(
							Integer.valueOf(ownerId), Integer.valueOf(s));
					if (teacher != null) {
						teachers.add(teacher);
					}
				}
				//========================= ????????????  ==============================//
				
				//================ ???????????????===================//
				/*for (String s : userlists) {
					Teacher teacher = this.teacherService.findOfUser(
							Integer.valueOf(ownerId), Integer.valueOf(s));
					if (teacher != null) {
						MeetingUser mu = new MeetingUser();
						mu.setMeetingId(meeting.getId());
						mu.setUserId(teacher.getUserId());
						mu.setUserName(teacher.getName());
						mu.setIsCanhui(Integer.valueOf(receiverType));
						this.muService.add(mu);
						teachers.add(teacher);
					}
				}*/
				//================ ???????????????===================//
				
			}

			json_return.put("common_return", ResponseInfomation.OPERATION_SUC);
			json_return.put("meetingId", meeting.getId() + "");

			JsonWriteUtils.setJson(json_return, response);
			if(!teachers.isEmpty()){
				PhoneMeetingVo vo = new PhoneMeetingVo(meeting);
				JSONObject jsonObjects = JSONObject.fromObject(vo);
				PushUtils.pushOfTaskExecutor(teachers, OaPushConstant.oaMeeting, receiverType, jsonObjects, imService,taskExecutor);	
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
	 * 
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "list")
	@ResponseBody
	public void getdateList(HttpServletRequest request,
			HttpServletResponse response, @ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order) {

		JSONObject json_return = new JSONObject();
		JSONArray array = new JSONArray();
		try {

			String userId = request.getParameter("userId");
			String ownerId = request.getParameter("ownerId");
			String ownerType = request.getParameter("ownerType");
			String type = request.getParameter("type");
			String new_or_old = request.getParameter("new_or_old");
			String baselineDate = request.getParameter("baselineDate");
			String page_num = request.getParameter("page_num");

			int num = 8;
			if (StringUtils.isNotEmpty(page_num)) {
				try {
					num = Integer.parseInt(page_num);
				} catch (Exception ex) {
					// TODO: handle exception
				}
			}

			order.setProperty(order.getProperty() != null ? order.getProperty()
					: "create_date");
			page.setPageSize(num);
			/**
			 * ??????????????????  2015-9-5
			 * type ??????????????????  
			 * 0????????? ??????????????????   1???????????????????????????  2???????????????????????????  3???????????????????????????
			 */
			//=============== ????????????????????????=================//
			List<MeetingVo> meeting = new ArrayList<MeetingVo>();
			Teacher t = this.teacherService.findOfUser(Integer.valueOf(ownerId), Integer.valueOf(userId));
			MeetingCondition condition = new MeetingCondition();
			condition.setSchoolId(Integer.valueOf(ownerId));
			condition.setCreateuserId(Integer.valueOf(userId));
			condition.setTeacherId(t==null ? -1 : t.getId());
			condition.setNew_or_old(new_or_old);
			condition.setBaselineDate(baselineDate);
			//????????????
			if(type!=null && type.equals(relatedWithMe)){
				condition.setFanwei(0);
				condition.setIsRelatedWithMe(true);
			}
			//????????????
			if(type!=null && type.equals(departmentRecord)){
				condition.setIsDepartmentRecord(true);
			}
			//????????????
			if(type!=null && type.equals(allRecord)){
//				condition.setIsRelatedWithMe(true);
			}
			//????????????
			if(type!=null && type.equals(myPublish)){
				condition.setIsMePublish(true);
			}
			meeting = this.meetingService.findMeetingByRelatedWithMe(condition, page, order);
			//=============== ????????????????????????=================//
			
			//=============== ????????????????????????=================//
			/**
			List<Meeting> meeting = new ArrayList<Meeting>();
			if ("0".equals(type)) {
				meeting = this.meetingService.findMeetingToUser(
						Integer.valueOf(ownerId), Integer.valueOf(userId),
						Integer.valueOf(new_or_old), baselineDate, page, order);
			} else if ("1".equals(type)) {

				meeting = this.meetingService.findMeeting(
						Integer.valueOf(ownerId), null, 1,
						Integer.valueOf(departmentId),
						Integer.valueOf(new_or_old), baselineDate, page, order);
			} else if ("2".equals(type)) {
				meeting = this.meetingService.findMeeting(
						Integer.valueOf(ownerId), null, 2, null,
						Integer.valueOf(new_or_old), baselineDate, page, order);
			} else if ("3".equals(type)) {
				meeting = this.meetingService.findMeeting(
						Integer.valueOf(ownerId), Integer.valueOf(userId),
						null, null, Integer.valueOf(new_or_old), baselineDate,
						page, order);
			}
			*/
			//=============== ????????????????????????=================//

			JSONObject jsonObjects = null;
			PhoneMeetingVo vo = null;
			for (Meeting m : meeting) {
				vo = new PhoneMeetingVo(m);
				jsonObjects = JSONObject.fromObject(vo);
				array.add(jsonObjects);
			}
			json_return.put("common_return", ResponseInfomation.OPERATION_SUC);
			json_return.put("return_info", array);
			JsonWriteUtils.setJson(json_return, response);
		} catch (Exception e) {
			e.printStackTrace();
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

	/**
	 * ????????????
	 * 
	 * @param oaNotice
	 * @param user
	 * @return
	 */

	@RequestMapping(value = "/summary_creator", method = RequestMethod.POST)
	@ResponseBody
	public void summary_creator(HttpServletRequest request,
			HttpServletResponse response) {

		JSONObject json_return = new JSONObject();
		try {

			String timestamp = request.getParameter("timestamp");
			String signature = request.getParameter("signature");
			String posterId = request.getParameter("posterId");
			String posterName = request.getParameter("posterName");
			String meetingId = request.getParameter("meetingId");
			String summaryContent = request.getParameter("summaryContent");

			String[] str = { timestamp, posterId, posterName, meetingId,
					summaryContent };
			// if(WechatCallbackApi.isLegitimacyApi(str, signature)){ //????????????????????????

			MeetingSummary summary = new MeetingSummary();
			summary.setUserId(Integer.valueOf(posterId));
			summary.setUserName(posterName);
			summary.setMeetingId(Integer.valueOf(meetingId));
			summary.setSummaryContent(summaryContent);
			this.summaryService.add(summary);
			Meeting meeting = this.meetingService.findMeetingById(summary
					.getMeetingId());
			meeting.setSummaryId(summary.getId());
			this.meetingService.modify(meeting);

			/*
			 * }else{ json_return.put("common_return","???????????????"); }
			 */

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

	/**
	 * ??????????????????
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "summaryInfo")
	@ResponseBody
	public void summary(HttpServletRequest request, HttpServletResponse response) {

		JSONObject json_return = new JSONObject();

		try {

			String timestamp = request.getParameter("timestamp");
			String signature = request.getParameter("signature");
			String meetingId = request.getParameter("meetingId");
			String userId = request.getParameter("userId");
			String userName = request.getParameter("userName");
			Meeting meeting = this.meetingService.findMeetingById(Integer
					.valueOf(meetingId));
			if (meeting.getSummaryId() != null) {
				MeetingSummary summary = this.summaryService
						.findMeetingSummaryById(meeting.getSummaryId());
				json_return.put("common_return",
						ResponseInfomation.OPERATION_SUC);
				json_return.put("summaryId", summary.getId() + "");
				json_return.put("summaryContent", summary.getSummaryContent());
				json_return.put("createDate",
						UtilDate.getDateFormatter(summary.getCreateDate()));

				SummaryReadUser sru = this.sruService.findBySummaryAndUserId(
						summary.getId(), Integer.valueOf(userId));
				if (sru == null) {
					sru = new SummaryReadUser();
					Profile profile = this.profileService.findByUserId(Integer
							.valueOf(userId));
					String imgurl = "";
					if (profile != null) {
						String icon = profile.getIcon();
						if (StringUtils.isNotEmpty(icon)) {
							imgurl = this.fileService.relativePath2HttpUrlByUUID(icon);
						}
					}

					sru.setSummaryId(summary.getId());
					sru.setUserId(Integer.valueOf(userId));
					sru.setUserImage(imgurl);
					sru.setUserName(userName);
					this.sruService.add(sru);
				}

			} else {
				json_return.put("common_return", "???????????????????????????");
			}

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

	/**
	 * ??????????????????????????????
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "participant")
	@ResponseBody
	public void participant(HttpServletRequest request,
			HttpServletResponse response) {

		JSONObject json_return = new JSONObject();
		JSONArray array = new JSONArray();
		try {

			String meetingId = request.getParameter("meetingId");
			/**
			 * ???????????????????????????????????????
			 */
			Meeting meeting = this.meetingService.findMeetingById(Integer.parseInt(meetingId));
			MeetingUserCondition meetingUserCondition = new MeetingUserCondition();
			meetingUserCondition.setMeetingId(Integer.valueOf(meetingId));
			List<MeetingUser> mus = this.muService
					.findMeetingUserByCondition(meetingUserCondition);
			Profile profile = null;
			PhoneMeetingUserVo vo = null;
			JSONObject jsonObjects = null;
			if(meeting.getFanwei().equals(ContansOfOa.personer)){    //?????????????????????
				for (MeetingUser m : mus) {
					String imgurl = "";
					profile = this.profileService.findByUserId(Integer
							.valueOf(m.getUserId()));
					if (profile != null) {
						String icon = profile.getIcon();
						if (StringUtils.isNotEmpty(icon)) {
							imgurl = this.fileService.relativePath2HttpUrlByUUID(icon);
						}
					}
					vo = new PhoneMeetingUserVo(m, imgurl);
					jsonObjects = JSONObject.fromObject(vo);
					array.add(jsonObjects);
				}
			}else if(meeting.getFanwei().equals(ContansOfOa.department)){  //?????????????????????
				MeetingUser m1 = new MeetingUser();
				List<Integer> uList = new ArrayList<Integer>();
				List<DepartmentTeacher> list = null;
				DepartmentTeacherCondition cond = null;
				Teacher t = null;
				for (MeetingUser m : mus) {
					Integer departmentId = m.getDepartmentId();
					cond = new DepartmentTeacherCondition();
					cond.setDepartmentId(departmentId);
					cond.setIsDeleted(false);
					list = this.departmentTeacherService.findDepartmentTeacherByCondition(cond, null, null);
					for(int i=0;i<list.size();i++){
						t = this.teacherService.findTeacherById(list.get(i).getTeacherId());
						if(t==null){
							continue;
						}
						boolean flag = true;
						for(Integer ul : uList){
							if(ul.equals(t.getId())){
								flag = false;
								break;
							}
						}
						if(flag){
							uList.add(list.get(i).getTeacherId());
							
							m1.setMeetingId(meeting.getId());
							m1.setUserId(list.get(i).getTeacherId());
							m1.setUserName(list.get(i).getTeacherName());
							profile = this.profileService.findByUserId(Integer
									.valueOf(m1.getUserId()));
							String imgurl = null;
							if (profile != null) {
								String icon = profile.getIcon();
								if (StringUtils.isNotEmpty(icon)) {
									imgurl = this.fileService.relativePath2HttpUrlByUUID(icon);
								}
							}
							vo = new PhoneMeetingUserVo(m1, imgurl);
							jsonObjects = JSONObject.fromObject(vo);
							array.add(jsonObjects);
						}
					}
				}
			}else{   //????????????????????????
				List<Teacher> list = this.teacherService.findTeacherListBySchoolId(meeting.getSchoolId());
				MeetingUser m1 = null;
				for (int i=0;i<list.size();i++) {
					m1 = new MeetingUser();
					m1.setMeetingId(meeting.getId());
					m1.setUserId(list.get(i).getUserId());
					m1.setUserName(list.get(i).getName());
					profile = this.profileService.findByUserId(Integer
							.valueOf(list.get(i).getUserId()));
					String imgurl = null;
					if (profile != null) {
						String icon = profile.getIcon();
						if (StringUtils.isNotEmpty(icon)) {
							imgurl = this.fileService.relativePath2HttpUrlByUUID(icon);
						}
					}
					vo = new PhoneMeetingUserVo(m1, imgurl);
					jsonObjects = JSONObject.fromObject(vo);
					array.add(jsonObjects);
				}
			}
			json_return.put("common_return", ResponseInfomation.OPERATION_SUC);
			json_return.put("return_info", array);
			JsonWriteUtils.setJson(json_return, response);
		} catch (Exception e) {
			e.printStackTrace();
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

	/**
	 * ????????????
	 * 
	 * @param oaNotice
	 * @param user
	 * @return
	 */

	@RequestMapping(value = "/comment", method = RequestMethod.POST)
	@ResponseBody
	public void comment(HttpServletRequest request, HttpServletResponse response) {

		JSONObject json_return = new JSONObject();
		try {

			String timestamp = request.getParameter("timestamp");
			String signature = request.getParameter("signature");
			String user_id = request.getParameter("userId");
			String userName = request.getParameter("userName");
			String meetingId = request.getParameter("meetingId");
			String comment = request.getParameter("comment");
			Profile profile = this.profileService.findByUserId(Integer
					.valueOf(user_id));
			String imgurl = "";
			if (profile != null) {
				String icon = profile.getIcon();

				if (StringUtils.isNotEmpty(icon)) {
					imgurl = this.fileService.relativePath2HttpUrlByUUID(icon);
				}
			}

			Comments comments = new Comments();
			comments.setCreateuserImage(imgurl);
			comments.setComment(comment);
			comments.setCreatename(userName);
			comments.setCreateuserId(Integer.valueOf(user_id));
			comments.setMeetingId(Integer.valueOf(meetingId));

			this.commentService.add(comments);
			json_return.put("common_return", ResponseInfomation.OPERATION_SUC);

			json_return.put("comment_id", comments.getId());
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

	/**
	 * ????????????
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "commentList")
	@ResponseBody
	public void commentList(HttpServletRequest request,
			HttpServletResponse response, @ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order) {

		JSONObject json_return = new JSONObject();
		JSONArray array = new JSONArray();
		try {

			String meetingId = request.getParameter("meetingId");
			String new_or_old = request.getParameter("new_or_old");
			String baselineDate = request.getParameter("baselineDate");
			String page_num = request.getParameter("page_num");

			int num = 8;
			if (StringUtils.isNotEmpty(page_num)) {
				try {
					num = Integer.parseInt(page_num);
				} catch (Exception ex) {
					// TODO: handle exception
				}
			}

			order.setProperty(order.getProperty() != null ? order.getProperty()
					: "create_date");
			page.setPageSize(num);
			List<Comments> comments = this.commentService
					.findCommentsByMeeting(Integer.valueOf(meetingId),
							Integer.valueOf(new_or_old), baselineDate, page,
							order);

			PhoneCommentsVo vo = null;
			JSONObject jsonObjects = null;
			for (Comments m : comments) {
				vo = new PhoneCommentsVo(m);
				jsonObjects = JSONObject.fromObject(vo);
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

	/**
	 * 
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "summaryReadUserList")
	@ResponseBody
	public void summaryReadUserList(HttpServletRequest request,
			HttpServletResponse response) {

		JSONObject json_return = new JSONObject();
		JSONArray array = new JSONArray();
		try {

			String summaryId = request.getParameter("summaryId");
			SummaryReadUserCondition summaryReadUserCondition = new SummaryReadUserCondition();
			summaryReadUserCondition.setSummaryId(Integer.valueOf(summaryId));
			List<SummaryReadUser> srus = this.sruService
					.findSummaryReadUserByCondition(summaryReadUserCondition);

			PhoneSummaryReadUserVo vo = null;
			JSONObject jsonObjects = null;
			for (SummaryReadUser m : srus) {
				vo = new PhoneSummaryReadUserVo(m);
				jsonObjects = JSONObject.fromObject(vo);
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

	private String parameter(HttpServletRequest request, String p) {
		try {
			return new String(request.getParameter(p).getBytes("ISO-8859-1"),
					"UTF-8");
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}
	
	 /**
     * ???????????????
     * ?????????????????????
     */
	public void addDepartment(Integer departmentId,Integer ownerId,Integer ownerType){
		MeetingDepartmentCountCondition mCondition = new MeetingDepartmentCountCondition();
		mCondition.setDepartmentId(departmentId);
		mCondition.setOwnerId(ownerId);
		mCondition.setOwnerType(ownerType);
		List<MeetingDepartmentCount> list = this.meetingDepartmentCountService.findMeetingDepartmentCountByCondition(mCondition);
		MeetingDepartmentCount mdc = new MeetingDepartmentCount();
		if(list.size() > 0){
			Integer num = list.get(0).getMeetingCount();
			mdc.setMeetingCount(num+1);
			mdc.setId(list.get(0).getId());
			this.meetingDepartmentCountService.modify(mdc);
		}else{
			mdc.setMeetingCount(1);
			mdc.setDepartmentId(departmentId);
			mdc.setOwnerId(ownerId);
			mdc.setOwnerType(ownerType);
			this.meetingDepartmentCountService.add(mdc);
		}
	}

}
