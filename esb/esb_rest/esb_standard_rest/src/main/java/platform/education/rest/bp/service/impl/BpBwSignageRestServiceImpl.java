package platform.education.rest.bp.service.impl;

import framework.generic.dao.Order;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import platform.education.clazz.model.*;
import platform.education.clazz.service.*;
import platform.education.clazz.vo.BwSyllabusLessonCondition;
import platform.education.clazz.vo.BwSyllabusVo;
import platform.education.clazz.vo.BwUserInfoCondition;
import platform.education.clazz.vo.SchoolCourseCondition;
import platform.education.generalTeachingAffair.model.*;
import platform.education.generalTeachingAffair.service.*;
import platform.education.generalTeachingAffair.vo.SyllabusLessonCondition;
import platform.education.im.model.ImAccount;
import platform.education.im.service.IMService;
import platform.education.im.service.ImAccountService;
import platform.education.rest.bp.service.BpBwSignageRestService;
import platform.education.rest.bp.service.contants.BpCommonConstants;
import platform.education.rest.bp.service.contants.BpSignageConstants;
import platform.education.rest.bp.service.vo.BpSyllabusLessonDayVo;
import platform.education.rest.bp.service.vo.BpSyllabusLessonDetailVo;
import platform.education.rest.bp.service.vo.BpSyllabusLessonTimeVo;
import platform.education.rest.bp.service.vo.BpUserInfo;
import platform.education.rest.common.ResponseError;
import platform.education.rest.common.ResponseInfo;
import platform.education.rest.common.ResponseVo;
import platform.education.rest.common.constants.CommonCode;
import platform.education.sns.model.Circle;
import platform.education.sns.service.CircleService;
import platform.education.sns.vo.CircleCondition;
import platform.education.user.model.Profile;
import platform.education.user.service.AppEditionService;
import platform.education.user.service.ProfileService;
import platform.education.user.utils.holder.PwdEncoderHolder;
import platform.service.storage.service.FileService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BpBwSignageRestServiceImpl implements BpBwSignageRestService {

	@Autowired
	@Qualifier("teamService")
	private TeamService teamService;

	@Autowired
	@Qualifier("bwUserInfoService")
	private BwUserInfoService bwUserInfoService;

	@Autowired
	@Qualifier("bpBwSignageService")
	private BpBwSignageService bpBwSignageService;

	@Autowired
	@Qualifier("teamAccountService")
	private TeamAccountService teamAccountService;

	@Autowired
	@Qualifier("schoolTermCurrentService")
	private SchoolTermCurrentService schoolTermCurrentService;

	@Autowired
	@Qualifier("syllabusService")
	private SyllabusService syllabusService;

	// 教师
	@Autowired
	@Qualifier("teacherService")
	private TeacherService teacherService;

	// 科目
	@Autowired
	@Qualifier("subjectService")
	private SubjectService subjectService;

	@Autowired
	@Qualifier("imAccountService")
	private ImAccountService imAccountService;

	@Autowired
	@Qualifier("profileService")
	private ProfileService profileService;

	@Autowired
	@Qualifier("fileService")
	private FileService fileService;

	@Autowired
	@Qualifier("appEditionService")
	private AppEditionService appEditionService;

	@Autowired
	@Qualifier("imService")
	private IMService imService;

	@Autowired
	@Qualifier("roomTeamService")
	private RoomTeamService roomTeamService;

	@Autowired
	@Qualifier("schoolCourseService")
	private SchoolCourseService schoolCourseService;

	@Autowired
	@Qualifier("bwSyllabusService")
	private BwSyllabusService bwSyllabusService;

	@Autowired
	@Qualifier("bwSyllabusLessonService")
	private BwSyllabusLessonService bwSyllabusLessonService;

	@Autowired
	@Qualifier("snsCircleService")
	private CircleService snsCircleService;

	@Override
	public Object binddingSignage(Integer schoolId, Integer teamId, String name, Integer createUserId,  String appKey) {
		try {
			/*if(StringUtils.isEmpty(appKey)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("appKey参数不能为空");
				info.setMsg("appKey为必填参数");
				info.setParam("appKey");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL,info);
			}
			AppEdition app = this.appEditionService.findByAppKey(appKey);
			if(StringUtils.isEmpty(app)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("appkey不存在,请确认");
				info.setMsg("不存在该appKey");
				info.setParam("appKey");
				return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
			}
			if(StringUtils.isEmpty(schoolId)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("schoolId参数不能为空");
				info.setMsg("schoolId参数不能为空");
				info.setParam("schoolId");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			if(StringUtils.isEmpty(teamId)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("teamId参数不能为空");
				info.setMsg("teamId参数不能为空");
				info.setParam("teamId");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			if(StringUtils.isEmpty(name)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("name参数不能为空");
				info.setMsg("name参数不能为空");
				info.setParam("name");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}*/

			//开通班级帐号
			TeamAccount tc = this.teamAccountService.findBySchoolIdAndTeamId(schoolId, teamId);
			if(tc==null){
				//开通班级账号（即在班级账号表中添加对应的记录）
				tc = new TeamAccount();
				tc.setSchoolId(schoolId);
				//一开通就是公开使用并且设置一个初始密码
				tc.setPassword(PwdEncoderHolder.getInstance().getPwdEncoder().encodePassword(BpSignageConstants.DEFAULT_PASSWORD));
				//操作权限方式为公开使用
				tc.setAccessType("0");
				//班级的账号开通状态
				tc.setAccountState("0");
				tc.setCreateUserId(createUserId);
				tc = this.teamAccountService.add(tc);
			}else{
				tc.setPassword(PwdEncoderHolder.getInstance().getPwdEncoder().encodePassword(BpSignageConstants.DEFAULT_PASSWORD));
				tc.setAccountState("0");
				tc.setModifyDate(new Date());
				tc = this.teamAccountService.modify(tc);
			}

			//根据teamId查找对应的team实体对象
			Team team = this.teamService.findTeamById(teamId);
			if(team!=null){
				tc.setGradeId(team.getGradeId());
				tc.setTeamId(teamId);
				tc.setTeamCode(team.getCode());
				tc.setTeamUuid(team.getUuid());
				tc.setAccount(team.getSchoolId().toString()+team.getId().toString());
				this.teamAccountService.modify(tc);
			}else{
				ResponseInfo info = new ResponseInfo();
				info.setDetail("班级不存在");
				info.setMsg("班级不存在");
				return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL,info);
			}


			//绑定班牌信息
			BpBwSignage bpBwSignage = bpBwSignageService.findBwSignageByName(name);
			if(bpBwSignage == null){
				//新增
				bpBwSignage = new BpBwSignage();
				bpBwSignage.setSchoolId(schoolId);
				bpBwSignage.setGradeId(team.getGradeId());
				bpBwSignage.setTeamId(teamId);
				bpBwSignage.setName(name);
				bpBwSignage.setAccount(tc.getAccount());
				bpBwSignage.setWorkingState(BpSignageConstants.WORKING_STATE_OPEN);
				bpBwSignage.setUsingState(BpSignageConstants.USING_STATE_OPEN);
				bpBwSignage.setIsDeleted(false);
				this.bpBwSignageService.add(bpBwSignage);

			}else{
				//班牌信息修改或者更换班级
				bpBwSignage.setSchoolId(schoolId);
				bpBwSignage.setGradeId(team.getGradeId());
				bpBwSignage.setTeamId(teamId);
				bpBwSignage.setName(name);
				bpBwSignage.setAccount(tc.getAccount());
				bpBwSignage.setWorkingState(BpSignageConstants.WORKING_STATE_OPEN);
				bpBwSignage.setUsingState(BpSignageConstants.USING_STATE_OPEN);
				bpBwSignage.setIsDeleted(false);
				this.bpBwSignageService.modify(bpBwSignage);
			}

			//注册环信
			ImAccount imAccount = this.imService.findIMAccountByName(name);
			if(imAccount == null){
				//imService.createIMAccount(name, tc.getAccount());
				imAccount = imService.createIMAccount(name, tc.getAccount(), BpCommonConstants.APP_KEY_SIGNAGE);
				if("fail".equals(imAccount.getImAccountStatus())){
					ResponseInfo info = new ResponseInfo();
					info.setDetail("环信接口异常");
					info.setMsg("环信接口异常");
					return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL,info);
				}
			}

			BpUserInfo bpUserInfo = new BpUserInfo();
			bpUserInfo.setSchoolId(schoolId);
			bpUserInfo.setTeamId(teamId);
			bpUserInfo.setSignageName(name);
			bpUserInfo.setHxAccount(imAccount.getAccountName());

			//班级课程表用于班牌的自动切换模式
			SchoolTermCurrent stc = this.schoolTermCurrentService.findSchoolTermCurrentBySchoolId(schoolId);
			if(stc == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("接口异常");
				info.setMsg("接口异常");
				return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL,info);
			}
			bpUserInfo.setTermCode(stc.getSchoolTermCode());
			Syllabus syllabus = this.syllabusService.getTeamSyllabus(teamId, stc.getSchoolTermCode());
			if(syllabus != null){
				/*ResponseInfo info = new ResponseInfo();
				info.setDetail("接口异常");
				info.setMsg("接口异常");
				return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL,info);*/

				//上课时间表
				String lessonTimesJsonStr = syllabus.getLessonTimes();
				JSONArray jsonArr = JSONArray.fromObject(lessonTimesJsonStr);
				List<BpSyllabusLessonTimeVo> lessonTimeList =
						(List<BpSyllabusLessonTimeVo>)JSONArray.toCollection(jsonArr, BpSyllabusLessonTimeVo.class);
				bpUserInfo.setSyllabusLessonTimeList(lessonTimeList);

				//每日课程表列表
				String dayOfWeekArrStr = syllabus.getDaysPlan();
				if(dayOfWeekArrStr == null && "".equals(dayOfWeekArrStr)){
					ResponseInfo info = new ResponseInfo();
					info.setDetail("接口异常");
					info.setMsg("接口异常");
					return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL,info);
				}
				String[] dayOfWeekArr = dayOfWeekArrStr.split(",");

				List<BpSyllabusLessonDayVo> lessonDayList = new ArrayList<BpSyllabusLessonDayVo>();
				for(String dayOfWeek: dayOfWeekArr){
					BpSyllabusLessonDayVo lessonDayVo = new BpSyllabusLessonDayVo();
					SyllabusLessonCondition syllabusLessonCondition = new SyllabusLessonCondition();
					syllabusLessonCondition.setSyllabusId(syllabus.getId());
					syllabusLessonCondition.setDayOfWeek(dayOfWeek);
					Order order = new Order();
					order.setProperty(order.getProperty() != null ? order.getProperty() : "lesson");
					order.setAscending(true);
					List<SyllabusLesson> lessonlList = this.syllabusService.findSyllabusLessonByCondition(syllabusLessonCondition, null, order);
					List<BpSyllabusLessonDetailVo> lessonDetailList = new ArrayList<BpSyllabusLessonDetailVo>();
					int lessonNum = lessonTimeList.size();
					BwUserInfoCondition condition = null;
					for(int i=1; i<=lessonNum; i++){
						BpSyllabusLessonDetailVo lessonDetailVo = new BpSyllabusLessonDetailVo();
						lessonDetailVo.setLesson(i);
						if(lessonlList != null && lessonlList.size() > 0){
							for(SyllabusLesson syllabusLesson: lessonlList){
								if(i == syllabusLesson.getLesson() ){
									//lessonDetailVo.setLesson(syllabusLesson.getLesson());
									lessonDetailVo.setSyllabusLessonId(syllabusLesson.getId());
									lessonDetailVo.setSubjectCode(syllabusLesson.getSubjectCode());
									lessonDetailVo.setType(1);//默认全部行政科
									lessonDetailVo.setSubjectName(syllabusLesson.getSubjectName());
									if(syllabusLesson.getTeacherId()!=null){
										Teacher teacher = this.teacherService.findTeacherById(syllabusLesson.getTeacherId());
										if(teacher!=null){
											Profile profile = this.profileService.findByUserId(teacher.getUserId());
											lessonDetailVo.setTeacherUserId(teacher.getUserId());
											lessonDetailVo.setTeacherName(teacher.getName());
											lessonDetailVo.setTeacherPostion(teacher.getPosition());
											condition = new BwUserInfoCondition();
											condition.setUserId(teacher.getUserId());
											if(profile != null){
												lessonDetailVo.setTeacherHeadUrl(this.fileService.relativePath2HttpUrlByUUID(profile.getIcon()));
											}
											List<BwUserInfo> list = this.bwUserInfoService.findBwUserInfoByCondition(condition);
											if(list.size()>0){
												lessonDetailVo.setTeacherFeature(list.get(0).getCharacteristic());
											}
										}
									}

									break;
								}

							}
						}
						lessonDetailList.add(lessonDetailVo);
					}
					lessonDayVo.setDayOfWeek(dayOfWeek);
					lessonDayVo.setLessonDetailList(lessonDetailList);
					lessonDayList.add(lessonDayVo);
				}
				Boolean isOpen = false;
				SchoolCourseCondition schoolCourseCondition = new SchoolCourseCondition();
				schoolCourseCondition.setSchoolId(schoolId);
				List<SchoolCourse> schoolCourseList = schoolCourseService.findSchoolCourseByCondition(schoolCourseCondition);
				if ( schoolCourseList != null && schoolCourseList.size() > 0 ) {
					isOpen = schoolCourseList.get(0).getIsOpen();
				}
				bpUserInfo.setIsOpen(isOpen);
				bpUserInfo.setLessonDayList(lessonDayList);
			}
			return new ResponseVo<BpUserInfo>("0",bpUserInfo);
		}catch (Exception e) {
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("接口异常");
			info.setMsg("接口异常");
			return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL,info);
		}
	}

	@Override
	public Object binddingSignageByRoom(Integer schoolId, Integer roomId, String roomTypeCode, String name, Integer createUserId, String appKey) {
		try {
			Boolean isOpen = false;
			/*if(StringUtils.isEmpty(appKey)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("appKey参数不能为空");
				info.setMsg("appKey为必填参数");
				info.setParam("appKey");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL,info);
			}
			AppEdition app = this.appEditionService.findByAppKey(appKey);
			if(StringUtils.isEmpty(app)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("appkey不存在,请确认");
				info.setMsg("不存在该appKey");
				info.setParam("appKey");
				return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
			}
			if(StringUtils.isEmpty(schoolId)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("schoolId参数不能为空");
				info.setMsg("schoolId参数不能为空");
				info.setParam("schoolId");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			if(StringUtils.isEmpty(roomId)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("roomId参数不能为空");
				info.setMsg("roomId参数不能为空");
				info.setParam("roomId");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			if(StringUtils.isEmpty(name)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("name参数不能为空");
				info.setMsg("name参数不能为空");
				info.setParam("name");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}*/

			BpUserInfo bpUserInfo = new BpUserInfo();
			SchoolTermCurrent stc = this.schoolTermCurrentService.findSchoolTermCurrentBySchoolId(schoolId);
			if(stc == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("接口异常");
				info.setMsg("接口异常");
				return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL,info);
			}
			//获取房室类型
			if(BpCommonConstants.ROOM_TYPE_PU_TONG_JIAO_SHI.equals(roomTypeCode)){

				//获取房室绑定的班级
				RoomTeam roomTeam = this.roomTeamService.findRoomTeamByRoomId(roomId, stc.getSchoolYear());
				if(roomTeam == null){
					ResponseInfo info = new ResponseInfo();
					info.setDetail("房室未绑定班级");
					info.setMsg("房室未绑定班级");
					return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
				}
				Integer teamId = roomTeam.getTeamId();
				//根据teamId查找对应的team实体对象
				Team team = this.teamService.findTeamById(teamId);
				//开通班级帐号
				TeamAccount tc = this.teamAccountService.findBySchoolIdAndTeamId(schoolId, teamId);
				if(tc==null){
					//开通班级账号（即在班级账号表中添加对应的记录）
					tc = new TeamAccount();
					tc.setSchoolId(schoolId);
					//一开通就是公开使用并且设置一个初始密码
					tc.setPassword(PwdEncoderHolder.getInstance().getPwdEncoder().encodePassword(BpSignageConstants.DEFAULT_PASSWORD));
					//操作权限方式为公开使用
					tc.setAccessType("0");
					//班级的账号开通状态
					tc.setAccountState("0");
					tc.setCreateUserId(createUserId);
					tc.setGradeId(team.getGradeId());
					tc.setTeamId(teamId);
					tc = this.teamAccountService.add(tc);
				}else{
					tc.setPassword(PwdEncoderHolder.getInstance().getPwdEncoder().encodePassword(BpSignageConstants.DEFAULT_PASSWORD));
					tc.setAccountState("0");
					tc.setModifyDate(new Date());
					tc = this.teamAccountService.modify(tc);
				}

				//开通班级圈
				CircleCondition circleCondition = new CircleCondition();
				circleCondition.setObjectType(1);
				circleCondition.setObjectId(teamId);
				Integer circleId = this.snsCircleService.findCircleIdByObject(circleCondition);
				Circle circle = new Circle();
				circle.setAppId(1);
				circle.setOwnerId(schoolId);
				circle.setStatus(0);
				circle.setName(team.getName());
				circle.setObjectType(1);
				circle.setObjectId(teamId);
				circle.setId(circleId);
				circle = this.snsCircleService.saveOrUpdate(circle);


				if(team!=null){
					tc.setGradeId(team.getGradeId());
					tc.setTeamId(teamId);
					tc.setTeamCode(team.getCode());
					tc.setTeamUuid(team.getUuid());
					tc.setAccount(team.getSchoolId().toString()+team.getId().toString());
					this.teamAccountService.modify(tc);
				}else{
					ResponseInfo info = new ResponseInfo();
					info.setDetail("班级不存在");
					info.setMsg("班级不存在");
					return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL,info);
				}



				//绑定班牌信息
				BpBwSignage bpBwSignage = bpBwSignageService.findBwSignageByName(name);
				if(bpBwSignage == null){
					//新增
					bpBwSignage = new BpBwSignage();
					bpBwSignage.setSchoolId(schoolId);
					bpBwSignage.setGradeId(team.getGradeId());
					bpBwSignage.setTeamId(teamId);
					bpBwSignage.setRoomId(roomId);
					bpBwSignage.setName(name);
					bpBwSignage.setAccount(tc.getAccount());
					bpBwSignage.setWorkingState(BpSignageConstants.WORKING_STATE_OPEN);
					bpBwSignage.setUsingState(BpSignageConstants.USING_STATE_OPEN);
					bpBwSignage.setIsDeleted(false);
					this.bpBwSignageService.add(bpBwSignage);

				}else{
					//班牌信息修改或者更换班级
					bpBwSignage.setSchoolId(schoolId);
					bpBwSignage.setGradeId(team.getGradeId());
					bpBwSignage.setRoomId(roomId);
					bpBwSignage.setTeamId(teamId);
					bpBwSignage.setName(name);
					bpBwSignage.setAccount(tc.getAccount());
					bpBwSignage.setWorkingState(BpSignageConstants.WORKING_STATE_OPEN);
					bpBwSignage.setUsingState(BpSignageConstants.USING_STATE_OPEN);
					bpBwSignage.setIsDeleted(false);
					this.bpBwSignageService.modify(bpBwSignage);
				}
				bpUserInfo.setTermCode(stc.getSchoolTermCode());


				SchoolCourse schoolCourse = this.schoolCourseService.findSchoolCourseBySchoolId(team.getSchoolId());
				if(schoolCourse != null){
					isOpen = schoolCourse.getIsOpen();
				}
				//开通走班制
				if(isOpen){
					//获取走班课程表
					BwSyllabusVo syllabus = this.bwSyllabusService.findBwSyllabusByRoomIdAndTermCode(roomId, stc.getSchoolTermCode());
					if(syllabus != null){
						//上课时间表
						String lessonTimesJsonStr = syllabus.getLessonTimes();
						JSONArray jsonArr = JSONArray.fromObject(lessonTimesJsonStr);
						List<BpSyllabusLessonTimeVo> lessonTimeList =
								(List<BpSyllabusLessonTimeVo>)JSONArray.toCollection(jsonArr, BpSyllabusLessonTimeVo.class);
						bpUserInfo.setSyllabusLessonTimeList(lessonTimeList);

						//每日本教室课程（行政，走班）
						String dayOfWeekArrStr = syllabus.getDaysPlan();
						if(dayOfWeekArrStr == null && "".equals(dayOfWeekArrStr)){
							ResponseInfo info = new ResponseInfo();
							info.setDetail("接口异常");
							info.setMsg("接口异常");
							return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL,info);
						}
						String[] dayOfWeekArr = dayOfWeekArrStr.split(",");

						List<BpSyllabusLessonDayVo> lessonDayList = new ArrayList<BpSyllabusLessonDayVo>();
						for(String dayOfWeek: dayOfWeekArr){
							BpSyllabusLessonDayVo lessonDayVo = new BpSyllabusLessonDayVo();
							BwSyllabusLessonCondition syllabusLessonCondition = new BwSyllabusLessonCondition();
							syllabusLessonCondition.setSyllabusId(syllabus.getId());
							syllabusLessonCondition.setDayOfWeek(dayOfWeek);
							Order order = new Order();
							order.setProperty(order.getProperty() != null ? order.getProperty() : "lesson");
							order.setAscending(true);
							List<BwSyllabusLesson> lessonlList =
									this.bwSyllabusLessonService.findBwSyllabusLessonByCondition(syllabusLessonCondition, null, order);
							List<BpSyllabusLessonDetailVo> lessonDetailList = new ArrayList<BpSyllabusLessonDetailVo>();
							int lessonNum = lessonTimeList.size();
							BwUserInfoCondition condition = null;
							for(int i=1; i<=lessonNum; i++){
								BpSyllabusLessonDetailVo lessonDetailVo = new BpSyllabusLessonDetailVo();
								lessonDetailVo.setLesson(i);
								if(lessonlList != null && lessonlList.size() > 0){
									for(BwSyllabusLesson syllabusLesson: lessonlList){
										if(i == syllabusLesson.getLesson() ){
											//lessonDetailVo.setLesson(syllabusLesson.getLesson());
											int type = syllabusLesson.getType();
											lessonDetailVo.setType(syllabusLesson.getType());
											lessonDetailVo.setSyllabusLessonId(syllabusLesson.getId());
											if(type != 0){
												lessonDetailVo.setSubjectCode(syllabusLesson.getSubjectCode());
												lessonDetailVo.setSubjectName(syllabusLesson.getSubjectName());
												if(syllabusLesson.getTeacherId()!=null){
													Teacher teacher = this.teacherService.findTeacherById(syllabusLesson.getTeacherId());
													if(teacher !=null){
														Profile profile = this.profileService.findByUserId(teacher.getUserId());
														lessonDetailVo.setTeacherUserId(teacher.getUserId());
														lessonDetailVo.setTeacherName(teacher.getName());
														lessonDetailVo.setTeacherPostion(teacher.getPosition());
														condition = new BwUserInfoCondition();
														condition.setUserId(teacher.getUserId());
														List<BwUserInfo> list = this.bwUserInfoService.findBwUserInfoByCondition(condition);
														if(list.size()>0){
															lessonDetailVo.setTeacherFeature(list.get(0).getCharacteristic());
														}
														if(profile != null){
															lessonDetailVo.setTeacherHeadUrl(this.fileService.relativePath2HttpUrlByUUID(profile.getIcon()));
														}
													}
												}
											}
											break;
										}
									}
								}
								lessonDetailList.add(lessonDetailVo);
							}
							lessonDayVo.setDayOfWeek(dayOfWeek);
							lessonDayVo.setLessonDetailList(lessonDetailList);
							lessonDayList.add(lessonDayVo);
						}
						bpUserInfo.setLessonDayList(lessonDayList);
					}
					bpUserInfo.setTeamId(teamId);
					bpUserInfo.setSignageType(BpSignageConstants.SIGNAGE_TYPE_BP);
				}else{
					//行政班课程表
					Syllabus syllabus = this.syllabusService.getTeamSyllabus(teamId, stc.getSchoolTermCode());
					if(syllabus != null){

						//上课时间表
						String lessonTimesJsonStr = syllabus.getLessonTimes();
						JSONArray jsonArr = JSONArray.fromObject(lessonTimesJsonStr);
						List<BpSyllabusLessonTimeVo> lessonTimeList =
								(List<BpSyllabusLessonTimeVo>)JSONArray.toCollection(jsonArr, BpSyllabusLessonTimeVo.class);
						bpUserInfo.setSyllabusLessonTimeList(lessonTimeList);

						//每日课程表列表
						String dayOfWeekArrStr = syllabus.getDaysPlan();
						if(dayOfWeekArrStr == null && "".equals(dayOfWeekArrStr)){
							ResponseInfo info = new ResponseInfo();
							info.setDetail("接口异常");
							info.setMsg("接口异常");
							return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL,info);
						}
						String[] dayOfWeekArr = dayOfWeekArrStr.split(",");

						List<BpSyllabusLessonDayVo> lessonDayList = new ArrayList<BpSyllabusLessonDayVo>();
						for(String dayOfWeek: dayOfWeekArr){
							BpSyllabusLessonDayVo lessonDayVo = new BpSyllabusLessonDayVo();
							SyllabusLessonCondition syllabusLessonCondition = new SyllabusLessonCondition();
							syllabusLessonCondition.setSyllabusId(syllabus.getId());
							syllabusLessonCondition.setDayOfWeek(dayOfWeek);
							Order order = new Order();
							order.setProperty(order.getProperty() != null ? order.getProperty() : "lesson");
							order.setAscending(true);
							List<SyllabusLesson> lessonlList = this.syllabusService.findSyllabusLessonByCondition(syllabusLessonCondition, null, order);
							List<BpSyllabusLessonDetailVo> lessonDetailList = new ArrayList<BpSyllabusLessonDetailVo>();
							int lessonNum = lessonTimeList.size();
							BwUserInfoCondition condition = null;
							for(int i=1; i<=lessonNum; i++){
								BpSyllabusLessonDetailVo lessonDetailVo = new BpSyllabusLessonDetailVo();
								lessonDetailVo.setLesson(i);
								if(lessonlList != null && lessonlList.size() > 0){
									for(SyllabusLesson syllabusLesson: lessonlList){
										if(i == syllabusLesson.getLesson() ){
											lessonDetailVo.setSyllabusLessonId(syllabusLesson.getId());
											lessonDetailVo.setType(1);//默认全部行政课
											//lessonDetailVo.setLesson(syllabusLesson.getLesson());
											lessonDetailVo.setSubjectCode(syllabusLesson.getSubjectCode());
											lessonDetailVo.setSubjectName(syllabusLesson.getSubjectName());
											Teacher teacher = this.teacherService.findTeacherById(syllabusLesson.getTeacherId());
											if(teacher != null){
												Profile profile = this.profileService.findByUserId(teacher.getUserId());
												lessonDetailVo.setTeacherUserId(teacher.getUserId());
												lessonDetailVo.setTeacherName(teacher.getName());
												lessonDetailVo.setTeacherPostion(teacher.getPosition());
												condition = new BwUserInfoCondition();
												condition.setUserId(teacher.getUserId());
												List<BwUserInfo> list = this.bwUserInfoService.findBwUserInfoByCondition(condition);
												if(list.size()>0){
													lessonDetailVo.setTeacherFeature(list.get(0).getCharacteristic());
												}
												if(profile != null){
													lessonDetailVo.setTeacherHeadUrl(this.fileService.relativePath2HttpUrlByUUID(profile.getIcon()));
												}

											}
											break;
										}
									}
								}
								lessonDetailList.add(lessonDetailVo);
							}
							lessonDayVo.setDayOfWeek(dayOfWeek);
							lessonDayVo.setLessonDetailList(lessonDetailList);
							lessonDayList.add(lessonDayVo);
						}
						bpUserInfo.setLessonDayList(lessonDayList);
					}
					bpUserInfo.setTeamId(teamId);
					bpUserInfo.setSignageType(BpSignageConstants.SIGNAGE_TYPE_BP);
				}
			}else{
				//绑定班牌广告机信息
				BpBwSignage bpBwSignage = bpBwSignageService.findBwSignageByName(name);
				if(bpBwSignage == null){
					//新增
					bpBwSignage = new BpBwSignage();
					bpBwSignage.setSchoolId(schoolId);
					bpBwSignage.setRoomId(roomId);
					bpBwSignage.setName(name);
					bpBwSignage.setWorkingState(BpSignageConstants.WORKING_STATE_OPEN);
					bpBwSignage.setUsingState(BpSignageConstants.USING_STATE_OPEN);
					bpBwSignage.setIsDeleted(false);
					this.bpBwSignageService.add(bpBwSignage);

				}else{
					//班牌信息修改
					bpBwSignage.setSchoolId(schoolId);
					bpBwSignage.setRoomId(roomId);
					bpBwSignage.setName(name);
					bpBwSignage.setWorkingState(BpSignageConstants.WORKING_STATE_OPEN);
					bpBwSignage.setUsingState(BpSignageConstants.USING_STATE_OPEN);
					bpBwSignage.setIsDeleted(false);
					this.bpBwSignageService.modify(bpBwSignage);
				}
				bpUserInfo.setSignageType(BpSignageConstants.SIGNAGE_TYPE_AD);
			}

			//注册环信
			ImAccount imAccount = this.imService.findIMAccountByName(name);
			if(imAccount == null){
				//imService.createIMAccount(name, tc.getAccount());
				imAccount = imService.createIMAccount(name, roomId+"", BpCommonConstants.APP_KEY_SIGNAGE);
				if(imAccount != null){
					bpUserInfo.setHxAccount(imAccount.getAccountName());
				}
			}

			bpUserInfo.setIsOpen(isOpen);
			bpUserInfo.setSchoolId(schoolId);
			bpUserInfo.setRoomId(roomId);
			bpUserInfo.setSignageName(name);
			return new ResponseVo<BpUserInfo>("0",bpUserInfo);
		}catch (Exception e) {
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("接口异常");
			info.setMsg("接口异常");
			return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL,info);
		}
	}


	@Override
	public Object binddingSignageByRoomWithNoHuanxin(Integer schoolId, Integer roomId, String roomTypeCode, String name,
			Integer createUserId, String appKey) {
		try {
			Boolean isOpen = false;
			/*if(StringUtils.isEmpty(appKey)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("appKey参数不能为空");
				info.setMsg("appKey为必填参数");
				info.setParam("appKey");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL,info);
			}
			AppEdition app = this.appEditionService.findByAppKey(appKey);
			if(StringUtils.isEmpty(app)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("appkey不存在,请确认");
				info.setMsg("不存在该appKey");
				info.setParam("appKey");
				return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
			}
			if(StringUtils.isEmpty(schoolId)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("schoolId参数不能为空");
				info.setMsg("schoolId参数不能为空");
				info.setParam("schoolId");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			if(StringUtils.isEmpty(roomId)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("roomId参数不能为空");
				info.setMsg("roomId参数不能为空");
				info.setParam("roomId");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			if(StringUtils.isEmpty(name)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("name参数不能为空");
				info.setMsg("name参数不能为空");
				info.setParam("name");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}*/

			BpUserInfo bpUserInfo = new BpUserInfo();
			SchoolTermCurrent stc = this.schoolTermCurrentService.findSchoolTermCurrentBySchoolId(schoolId);
			if(stc == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("接口异常");
				info.setMsg("接口异常");
				return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL,info);
			}
			//获取房室类型
			if(BpCommonConstants.ROOM_TYPE_PU_TONG_JIAO_SHI.equals(roomTypeCode)){

				//获取房室绑定的班级
				RoomTeam roomTeam = this.roomTeamService.findRoomTeamByRoomId(roomId, stc.getSchoolYear());
				if(roomTeam == null){
					ResponseInfo info = new ResponseInfo();
					info.setDetail("房室未绑定班级");
					info.setMsg("房室未绑定班级");
					return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
				}
				Integer teamId = roomTeam.getTeamId();
				//根据teamId查找对应的team实体对象
				Team team = this.teamService.findTeamById(teamId);
				//开通班级帐号
				TeamAccount tc = this.teamAccountService.findBySchoolIdAndTeamId(schoolId, teamId);
				if(tc==null){
					//开通班级账号（即在班级账号表中添加对应的记录）
					tc = new TeamAccount();
					tc.setSchoolId(schoolId);
					//一开通就是公开使用并且设置一个初始密码
					tc.setPassword(PwdEncoderHolder.getInstance().getPwdEncoder().encodePassword(BpSignageConstants.DEFAULT_PASSWORD));
					//操作权限方式为公开使用
					tc.setAccessType("0");
					//班级的账号开通状态
					tc.setAccountState("0");
					tc.setCreateUserId(createUserId);
					tc.setGradeId(team.getGradeId());
					tc.setTeamId(teamId);
					tc = this.teamAccountService.add(tc);
				}else{
					tc.setPassword(PwdEncoderHolder.getInstance().getPwdEncoder().encodePassword(BpSignageConstants.DEFAULT_PASSWORD));
					tc.setAccountState("0");
					tc.setModifyDate(new Date());
					tc = this.teamAccountService.modify(tc);
				}


				if(team!=null){
					tc.setGradeId(team.getGradeId());
					tc.setTeamId(teamId);
					tc.setTeamCode(team.getCode());
					tc.setTeamUuid(team.getUuid());
					tc.setAccount(team.getSchoolId().toString()+team.getId().toString());
					this.teamAccountService.modify(tc);
				}else{
					ResponseInfo info = new ResponseInfo();
					info.setDetail("班级不存在");
					info.setMsg("班级不存在");
					return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL,info);
				}

				//绑定班牌信息
				BpBwSignage bpBwSignage = bpBwSignageService.findBwSignageByName(name);
				if(bpBwSignage == null){
					//新增
					bpBwSignage = new BpBwSignage();
					bpBwSignage.setSchoolId(schoolId);
					bpBwSignage.setGradeId(team.getGradeId());
					bpBwSignage.setTeamId(teamId);
					bpBwSignage.setRoomId(roomId);
					bpBwSignage.setName(name);
					bpBwSignage.setAccount(tc.getAccount());
					bpBwSignage.setWorkingState(BpSignageConstants.WORKING_STATE_OPEN);
					bpBwSignage.setUsingState(BpSignageConstants.USING_STATE_OPEN);
					bpBwSignage.setIsDeleted(false);
					this.bpBwSignageService.add(bpBwSignage);

				}else{
					//班牌信息修改或者更换班级
					bpBwSignage.setSchoolId(schoolId);
					bpBwSignage.setGradeId(team.getGradeId());
					bpBwSignage.setRoomId(roomId);
					bpBwSignage.setTeamId(teamId);
					bpBwSignage.setName(name);
					bpBwSignage.setAccount(tc.getAccount());
					bpBwSignage.setWorkingState(BpSignageConstants.WORKING_STATE_OPEN);
					bpBwSignage.setUsingState(BpSignageConstants.USING_STATE_OPEN);
					bpBwSignage.setIsDeleted(false);
					this.bpBwSignageService.modify(bpBwSignage);
				}
				bpUserInfo.setTermCode(stc.getSchoolTermCode());


				SchoolCourse schoolCourse = this.schoolCourseService.findSchoolCourseBySchoolId(team.getSchoolId());
				if(schoolCourse != null){
					isOpen = schoolCourse.getIsOpen();
				}
				//开通走班制
				if(isOpen){
					//获取走班课程表
					BwSyllabusVo syllabus = this.bwSyllabusService.findBwSyllabusByRoomIdAndTermCode(roomId, stc.getSchoolTermCode());
					if(syllabus != null){
						//上课时间表
						String lessonTimesJsonStr = syllabus.getLessonTimes();
						JSONArray jsonArr = JSONArray.fromObject(lessonTimesJsonStr);
						List<BpSyllabusLessonTimeVo> lessonTimeList =
								(List<BpSyllabusLessonTimeVo>)JSONArray.toCollection(jsonArr, BpSyllabusLessonTimeVo.class);
						bpUserInfo.setSyllabusLessonTimeList(lessonTimeList);

						//每日本教室课程（行政，走班）
						String dayOfWeekArrStr = syllabus.getDaysPlan();
						if(dayOfWeekArrStr == null && "".equals(dayOfWeekArrStr)){
							ResponseInfo info = new ResponseInfo();
							info.setDetail("接口异常");
							info.setMsg("接口异常");
							return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL,info);
						}
						String[] dayOfWeekArr = dayOfWeekArrStr.split(",");

						List<BpSyllabusLessonDayVo> lessonDayList = new ArrayList<BpSyllabusLessonDayVo>();
						for(String dayOfWeek: dayOfWeekArr){
							BpSyllabusLessonDayVo lessonDayVo = new BpSyllabusLessonDayVo();
							BwSyllabusLessonCondition syllabusLessonCondition = new BwSyllabusLessonCondition();
							syllabusLessonCondition.setSyllabusId(syllabus.getId());
							syllabusLessonCondition.setDayOfWeek(dayOfWeek);
							Order order = new Order();
							order.setProperty(order.getProperty() != null ? order.getProperty() : "lesson");
							order.setAscending(true);
							List<BwSyllabusLesson> lessonlList =
									this.bwSyllabusLessonService.findBwSyllabusLessonByCondition(syllabusLessonCondition, null, order);
							List<BpSyllabusLessonDetailVo> lessonDetailList = new ArrayList<BpSyllabusLessonDetailVo>();
							int lessonNum = lessonTimeList.size();
							BwUserInfoCondition condition = null;
							for(int i=1; i<=lessonNum; i++){
								BpSyllabusLessonDetailVo lessonDetailVo = new BpSyllabusLessonDetailVo();
								lessonDetailVo.setLesson(i);
								if(lessonlList != null && lessonlList.size() > 0){
									for(BwSyllabusLesson syllabusLesson: lessonlList){
										if(i == syllabusLesson.getLesson() ){
											//lessonDetailVo.setLesson(syllabusLesson.getLesson());
											int type = syllabusLesson.getType();
											lessonDetailVo.setType(syllabusLesson.getType());
											lessonDetailVo.setSyllabusLessonId(syllabusLesson.getId());
											if(type != 0){
												lessonDetailVo.setSubjectCode(syllabusLesson.getSubjectCode());
												lessonDetailVo.setSubjectName(syllabusLesson.getSubjectName());
												if(syllabusLesson.getTeacherId()!=null){
													Teacher teacher = this.teacherService.findTeacherById(syllabusLesson.getTeacherId());
													if(teacher !=null){
														Profile profile = this.profileService.findByUserId(teacher.getUserId());
														lessonDetailVo.setTeacherUserId(teacher.getUserId());
														lessonDetailVo.setTeacherName(teacher.getName());
														lessonDetailVo.setTeacherPostion(teacher.getPosition());
														condition = new BwUserInfoCondition();
														condition.setUserId(teacher.getUserId());
														List<BwUserInfo> list = this.bwUserInfoService.findBwUserInfoByCondition(condition);
														if(list.size()>0){
															lessonDetailVo.setTeacherFeature(list.get(0).getCharacteristic());
														}
														if(profile != null){
															lessonDetailVo.setTeacherHeadUrl(this.fileService.relativePath2HttpUrlByUUID(profile.getIcon()));
														}
													}
												}
											}
											break;
										}
									}
								}
								lessonDetailList.add(lessonDetailVo);
							}
							lessonDayVo.setDayOfWeek(dayOfWeek);
							lessonDayVo.setLessonDetailList(lessonDetailList);
							lessonDayList.add(lessonDayVo);
						}
						bpUserInfo.setLessonDayList(lessonDayList);
					}
					bpUserInfo.setTeamId(teamId);
					bpUserInfo.setSignageType(BpSignageConstants.SIGNAGE_TYPE_BP);
				}else{
					//行政班课程表
					Syllabus syllabus = this.syllabusService.getTeamSyllabus(teamId, stc.getSchoolTermCode());
					if(syllabus != null){

						//上课时间表
						String lessonTimesJsonStr = syllabus.getLessonTimes();
						JSONArray jsonArr = JSONArray.fromObject(lessonTimesJsonStr);
						List<BpSyllabusLessonTimeVo> lessonTimeList =
								(List<BpSyllabusLessonTimeVo>)JSONArray.toCollection(jsonArr, BpSyllabusLessonTimeVo.class);
						bpUserInfo.setSyllabusLessonTimeList(lessonTimeList);

						//每日课程表列表
						String dayOfWeekArrStr = syllabus.getDaysPlan();
						if(dayOfWeekArrStr == null && "".equals(dayOfWeekArrStr)){
							ResponseInfo info = new ResponseInfo();
							info.setDetail("接口异常");
							info.setMsg("接口异常");
							return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL,info);
						}
						String[] dayOfWeekArr = dayOfWeekArrStr.split(",");

						List<BpSyllabusLessonDayVo> lessonDayList = new ArrayList<BpSyllabusLessonDayVo>();
						for(String dayOfWeek: dayOfWeekArr){
							BpSyllabusLessonDayVo lessonDayVo = new BpSyllabusLessonDayVo();
							SyllabusLessonCondition syllabusLessonCondition = new SyllabusLessonCondition();
							syllabusLessonCondition.setSyllabusId(syllabus.getId());
							syllabusLessonCondition.setDayOfWeek(dayOfWeek);
							Order order = new Order();
							order.setProperty(order.getProperty() != null ? order.getProperty() : "lesson");
							order.setAscending(true);
							List<SyllabusLesson> lessonlList = this.syllabusService.findSyllabusLessonByCondition(syllabusLessonCondition, null, order);
							List<BpSyllabusLessonDetailVo> lessonDetailList = new ArrayList<BpSyllabusLessonDetailVo>();
							int lessonNum = lessonTimeList.size();
							BwUserInfoCondition condition = null;
							for(int i=1; i<=lessonNum; i++){
								BpSyllabusLessonDetailVo lessonDetailVo = new BpSyllabusLessonDetailVo();
								lessonDetailVo.setLesson(i);
								if(lessonlList != null && lessonlList.size() > 0){
									for(SyllabusLesson syllabusLesson: lessonlList){
										if(i == syllabusLesson.getLesson() ){
											lessonDetailVo.setSyllabusLessonId(syllabusLesson.getId());
											lessonDetailVo.setType(1);//默认全部行政课
											//lessonDetailVo.setLesson(syllabusLesson.getLesson());
											lessonDetailVo.setSubjectCode(syllabusLesson.getSubjectCode());
											lessonDetailVo.setSubjectName(syllabusLesson.getSubjectName());
											Teacher teacher = this.teacherService.findTeacherById(syllabusLesson.getTeacherId());
											if(teacher != null){
												Profile profile = this.profileService.findByUserId(teacher.getUserId());
												lessonDetailVo.setTeacherUserId(teacher.getUserId());
												lessonDetailVo.setTeacherName(teacher.getName());
												lessonDetailVo.setTeacherPostion(teacher.getPosition());
												condition = new BwUserInfoCondition();
												condition.setUserId(teacher.getUserId());
												List<BwUserInfo> list = this.bwUserInfoService.findBwUserInfoByCondition(condition);
												if(list.size()>0){
													lessonDetailVo.setTeacherFeature(list.get(0).getCharacteristic());
												}
												if(profile != null){
													lessonDetailVo.setTeacherHeadUrl(this.fileService.relativePath2HttpUrlByUUID(profile.getIcon()));
												}

											}
											break;
										}
									}
								}
								lessonDetailList.add(lessonDetailVo);
							}
							lessonDayVo.setDayOfWeek(dayOfWeek);
							lessonDayVo.setLessonDetailList(lessonDetailList);
							lessonDayList.add(lessonDayVo);
						}
						bpUserInfo.setLessonDayList(lessonDayList);
					}
					bpUserInfo.setTeamId(teamId);
					bpUserInfo.setSignageType(BpSignageConstants.SIGNAGE_TYPE_BP);
				}
			}else{
				//绑定班牌广告机信息
				BpBwSignage bpBwSignage = bpBwSignageService.findBwSignageByName(name);
				if(bpBwSignage == null){
					//新增
					bpBwSignage = new BpBwSignage();
					bpBwSignage.setSchoolId(schoolId);
					bpBwSignage.setRoomId(roomId);
					bpBwSignage.setName(name);
					bpBwSignage.setWorkingState(BpSignageConstants.WORKING_STATE_OPEN);
					bpBwSignage.setUsingState(BpSignageConstants.USING_STATE_OPEN);
					bpBwSignage.setIsDeleted(false);
					this.bpBwSignageService.add(bpBwSignage);

				}else{
					//班牌信息修改
					bpBwSignage.setSchoolId(schoolId);
					bpBwSignage.setRoomId(roomId);
					bpBwSignage.setName(name);
					bpBwSignage.setWorkingState(BpSignageConstants.WORKING_STATE_OPEN);
					bpBwSignage.setUsingState(BpSignageConstants.USING_STATE_OPEN);
					bpBwSignage.setIsDeleted(false);
					this.bpBwSignageService.modify(bpBwSignage);
				}
				bpUserInfo.setSignageType(BpSignageConstants.SIGNAGE_TYPE_AD);
			}

			//注册环信
			/*ImAccount imAccount = this.imService.findIMAccountByName(name);
			if(imAccount == null){
				//imService.createIMAccount(name, tc.getAccount());
				imAccount = imService.createIMAccount(name, roomId+"", BpCommonConstants.APP_KEY_SIGNAGE);
				if(imAccount != null){
					bpUserInfo.setHxAccount(imAccount.getAccountName());
				}			
			}*/

			bpUserInfo.setIsOpen(isOpen);
			bpUserInfo.setSchoolId(schoolId);
			bpUserInfo.setRoomId(roomId);
			bpUserInfo.setSignageName(name);
			return new ResponseVo<BpUserInfo>("0",bpUserInfo);
		}catch (Exception e) {
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("接口异常");
			info.setMsg("接口异常");
			return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL,info);
		}
	}
}
