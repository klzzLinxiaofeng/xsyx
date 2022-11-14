package platform.education.rest.bp.service.impl;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import platform.education.clazz.model.SchoolInfo;
import platform.education.clazz.model.*;
import platform.education.clazz.service.*;
import platform.education.clazz.vo.*;
import platform.education.generalTeachingAffair.model.*;
import platform.education.generalTeachingAffair.service.*;
import platform.education.generalTeachingAffair.vo.*;
import platform.education.im.model.AppAuthorization;
import platform.education.im.model.ImAccount;
import platform.education.im.model.ImProvider;
import platform.education.im.service.AppAuthorizationService;
import platform.education.im.service.IMService;
import platform.education.im.service.ImAccountService;
import platform.education.im.service.ImProviderService;
import platform.education.im.vo.ImAccountCondition;
import platform.education.rest.bp.service.BpCommonRestService;
import platform.education.rest.bp.service.contants.BpCommonConstants;
import platform.education.rest.bp.service.contants.DataAction;
import platform.education.rest.bp.service.util.CalculateVisualFileSize;
import platform.education.rest.bp.service.util.DateUtil;
import platform.education.rest.bp.service.util.ValidateUtil;
import platform.education.rest.bp.service.vo.PersonVo;
import platform.education.rest.bp.service.vo.*;
import platform.education.rest.common.ResponseError;
import platform.education.rest.common.ResponseInfo;
import platform.education.rest.common.ResponseVo;
import platform.education.rest.common.constants.CommonCode;
import platform.education.rest.util.ImgUtil;
import platform.education.sns.service.CircleMessageService;
import platform.education.sns.vo.CircleMessageCondition;
import platform.education.sns.vo.CircleMessageVo;
import platform.education.user.model.*;
import platform.education.user.service.*;
import platform.education.user.vo.AppReleaseCondition;
import platform.education.user.vo.GroupUserCondition;
import platform.service.storage.service.FileService;
import platform.service.storage.vo.FileResult;

import java.text.SimpleDateFormat;
import java.util.*;

public class BpCommonRestServiceImpl implements BpCommonRestService {

    private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    @Qualifier("userService")
    protected UserService userService;

    @Autowired
    @Qualifier("appAuthorizationService")
    private AppAuthorizationService appAuthorizationService;

    @Autowired
    @Qualifier("appReleaseService")
    private AppReleaseService appReleaseService;

    // 当前学期
    @Autowired
    @Qualifier("schoolTermCurrentService")
    protected SchoolTermCurrentService schoolTermCurrentService;

    @Autowired
    @Qualifier("appService")
    private AppService appService;

    @Autowired
    @Qualifier("imService")
    private IMService imService;

    @Autowired
    @Qualifier("imProviderService")
    private ImProviderService imProviderService;

    @Autowired
    @Qualifier("imAccountService")
    private ImAccountService imAccountService;

    @Autowired
    @Qualifier("schoolService")
    private SchoolService schoolService;

    @Autowired
    @Qualifier("teamNoticeService")
    TeamNoticeService teamNoticeService;

    @Autowired
    @Qualifier("subjectService")
    protected SubjectService subjectService;

    @Autowired
    @Qualifier("teamService")
    private TeamService teamService;

    @Autowired
    @Qualifier("studentService")
    private StudentService studentService;

    @Autowired
    @Qualifier("teamStudentService")
    private TeamStudentService teamStudentService;

    @Autowired
    @Qualifier("teamTeacherService")
    private TeamTeacherService teamTeacherService;

    @Autowired
    @Qualifier("profileService")
    private ProfileService profileService;

    @Autowired
    @Qualifier("fileService")
    private FileService fileService;

    @Autowired
    @Qualifier("snsCircleMessageService")
    private CircleMessageService snsCircleMessageService;

    @Autowired
    @Qualifier("teamActivityService")
    private TeamActivityService teamActivityService;

    @Autowired
    @Qualifier("bpBwInfoService")
    private BpBwInfoService bpBwInfoService;

    @Autowired
    @Qualifier("teamDutyUserService")
    private TeamDutyUserService teamDutyUserService;

    @Autowired
    @Qualifier("praiseService")
    private PraiseService praiseService;

    @Autowired
    @Qualifier("teamAwardService")
    private TeamAwardService teamAwardService;

    @Autowired
    @Qualifier("teamHomeworkService")
    private TeamHomeworkService teamHomeworkService;

    @Autowired
    @Qualifier("schoolInfoService")
    private SchoolInfoService schoolInfoService;

    @Autowired
    @Qualifier("schoolInfoFileService")
    private SchoolInfoFileService schoolInfoFileService;

    @Autowired
    @Qualifier("bpBwFileService")
    private BpBwFileService bpBwFileService;

    @Autowired
    @Qualifier("groupUserService")
    private GroupUserService groupUserService;

    @Autowired
    @Qualifier("groupService")
    private GroupService groupService;

    @Autowired
    @Qualifier("appEditionService")
    private AppEditionService appEditionService;

    @Autowired
    @Qualifier("bpBwSignageService")
    private BpBwSignageService bpBwSignageService;

    @Autowired
    @Qualifier("bpBwPictureAlbumService")
    private BpBwPictureAlbumService bpBwPictureAlbumService;

    @Autowired
    @Qualifier("personService")
    private PersonService personService;

    @Autowired
    @Qualifier("roomService")
    private RoomService roomService;

    @Autowired
    @Qualifier("roomTeamService")
    private RoomTeamService roomTeamService;

    @Autowired
    @Qualifier("bpBwSchoolCardService")
    private BpBwSchoolCardService bpBwSchoolCardService;

    @Autowired
    @Qualifier("teacherService")
    private TeacherService teacherService;

    @Autowired
    @Qualifier("schoolUserService")
    private SchoolUserService schoolUserService;

    @Autowired
    @Qualifier("bpBwExamInfoService")
    private BpBwExamInfoService bpBwExamInfoService;


    @Override
    public Object getTeamInfoList(Integer schoolId) {
        try {

            if (schoolId == null) {
                ResponseInfo info = new ResponseInfo();
                info.setDetail("schoolId参数不能为空");
                info.setMsg("schoolId参数不能为空");
                info.setParam("schoolId");
                return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
            }
            List<Team> teamList = teamService.findCurrentTeamOfSchool(schoolId);
            return new ResponseVo<List<Team>>("0", teamList);
        } catch (Exception e) {
            e.printStackTrace();
            ResponseInfo info = new ResponseInfo();
            info.setDetail("接口异常");
            info.setMsg("接口异常");
            return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL, info);
        }
    }

    @Override
    public Object getSchoolTeamInfoListByUserId(Integer userId) {
        try {
            if (userId == null) {
                ResponseInfo info = new ResponseInfo();
                info.setDetail("userId参数不能为空");
                info.setMsg("userId参数不能为空");
                info.setParam("userId");
                return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
            }
            BpBandingPageVo bandingPageVo = new BpBandingPageVo();
            GroupUserCondition groupUserCondition = new GroupUserCondition();
            groupUserCondition.setUserId(userId);
            List<GroupUser> groupUserList = this.groupUserService.findGroupUserByCondition(groupUserCondition, null, null);
            GroupUser groupUser = groupUserList.get(0);
            Group group = groupService.findGroupById(groupUser.getGroupId());
            Integer schoolId = group.getOwnerId();
            String schoolName = this.schoolService.findSchoolInfoById(schoolId).getName();
            bandingPageVo.setSchoolId(schoolId);
            bandingPageVo.setSchoolName(schoolName);
			/*TeamCondition teamCondition = new TeamCondition();
			teamCondition.setSchoolId(schoolId);
			teamCondition.setIsDelete(false);
			Order order = new Order();
			order.setProperty("grade_id");
			order.setAscending(true);*/
            List<Team> teamList = teamService.findCurrentTeamOfSchool(schoolId);
            bandingPageVo.setTeamList(teamList);
            return new ResponseVo<BpBandingPageVo>("0", bandingPageVo);
        } catch (Exception e) {
            e.printStackTrace();
            ResponseInfo info = new ResponseInfo();
            info.setDetail("接口异常");
            info.setMsg("接口异常");
            return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL, info);
        }
    }

    @Override
    public Object getTopIndexInfo(Integer schoolId, Integer teamId,
                                  String appKey, String signage) {
        try {
            if (schoolId == null) {
                ResponseInfo info = new ResponseInfo();
                info.setDetail("schoolId参数不能为空");
                info.setMsg("schoolId参数不能为空");
                info.setParam("schoolId");
                return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
            }
            if (teamId == null) {
                ResponseInfo info = new ResponseInfo();
                info.setDetail("teamId参数不能为空");
                info.setMsg("teamId参数不能为空");
                info.setParam("teamId");
                return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
            }
            if (appKey == null) {
                ResponseInfo info = new ResponseInfo();
                info.setDetail("appKey参数不能为空");
                info.setMsg("appKey为必填参数");
                info.setParam("appKey");
                return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
            }
            AppEdition app = this.appEditionService.findByAppKey(appKey);
            if (app == null) {
                ResponseInfo info = new ResponseInfo();
                info.setDetail("appkey不存在,请确认");
                info.setMsg("不存在该appKey");
                info.setParam("appKey");
                return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
            }
            if (signage == null) {
                ResponseInfo info = new ResponseInfo();
                info.setDetail("signage参数不能为空");
                info.setMsg("signage为必填参数");
                info.setParam("signage");
                return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
            }
            BpBwSignage Signage = this.bpBwSignageService.findBwSignageByName(signage);
            if (Signage == null) {
                ResponseInfo info = new ResponseInfo();
                info.setDetail("signage不存在,请确认");
                info.setMsg("不存在该signage");
                info.setParam("signage");
                return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
            }
            BpTopIndexInfoVo bpTopIndexInfoVo = new BpTopIndexInfoVo();
            //获取学校信息
            School school = this.schoolService.findSchoolById(schoolId);
            if (school != null) {
                bpTopIndexInfoVo.setSchoolName(school.getName());
                String schoolIconUuid = school.getEntityId_badge();
              /*  if (!StringUtils.isEmpty(schoolIconUuid)) {
                    bpTopIndexInfoVo.setSchoolIcon(fileService.relativePath2HttpUrlByUUID(schoolIconUuid));
                }*/
            }
            //获取班级信息
            Team team = this.teamService.findTeamById(teamId);
            if (team != null) {
                bpTopIndexInfoVo.setTeamName(team.getName());
            }
            //获取该班级学生数量
            bpTopIndexInfoVo.setStudentNum(this.studentService.countByTeamId(teamId));
            //获取该班级教师数量
			/*List<TeamTeacher> teacherList = this.teamTeacherService.findClassTeacherByGradeIdAndTeamId(team.getGradeId(), teamId);
			if(teacherList != null && teacherList.size() > 0 ){
				bpTopIndexInfoVo.setTeacherNum(Long.valueOf(teacherList.size()));
			}else{
				bpTopIndexInfoVo.setTeacherNum(0l);
			}*/
            bpTopIndexInfoVo.setTeacherNum(Long.valueOf(this.teamTeacherService.findTeamTeacherByTeamId(teamId)));
            //获取班主任信息
            if (team != null) {
                TeamTeacherCondition teamTeacherCondition = new TeamTeacherCondition();
                teamTeacherCondition.setType(1);
                teamTeacherCondition.setTeamId(teamId);
                List<TeamTeacher> classTeacherList =
                        this.teamTeacherService.findTeamTeacherByCondition(teamTeacherCondition, null, null);
                if (classTeacherList != null && classTeacherList.size() > 0) {
                    bpTopIndexInfoVo.setClassTeacherName(classTeacherList.get(0).getName());
                    Profile profile = this.profileService.findByUserId(classTeacherList.get(0).getUserId());
                    if (profile != null) {
                        bpTopIndexInfoVo.setClassTeacherHeadUrl(fileService.relativePath2HttpUrlByUUID(profile.getIcon()));
                    }
                }
            }

            //获取班长信息
            StudentCondition studentCondition = new StudentCondition();
            studentCondition.setTeamId(teamId);
            studentCondition.setPosition("班长");
            studentCondition.setIsDelete(false);
            List<Student> studentList = this.studentService.findStudentByCondition(studentCondition, null, null);
            if (studentList != null && studentList.size() > 0) {
                bpTopIndexInfoVo.setMonitorName(studentList.get(0).getName());
                Person person = personService.findPersonById(studentList.get(0).getPersonId());

                if (person != null) {
                    bpTopIndexInfoVo.setMonitorHeadUrl(ImgUtil.getStudentIconUrl(person.getId(), personService));
                }

				/*bpTopIndexInfoVo.setMonitorName(studentList.get(0).getName());
				Profile profile = this.profileService.findByUserId(studentList.get(0).getUserId());
				if(profile != null){
					bpTopIndexInfoVo.setMonitorHeadUrl(fileService.relativePath2HttpUrlByUUID(profile.getIcon()));
				}*/
            }
            return new ResponseVo<BpTopIndexInfoVo>("0", bpTopIndexInfoVo);
        } catch (Exception e) {
            e.printStackTrace();
            ResponseInfo info = new ResponseInfo();
            info.setDetail("接口异常");
            info.setMsg("接口异常");
            return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL, info);
        }

    }

    @Override
    public Object getIndexContentInfo(Integer teamId,
                                      String appKey, String signage) {
        try {
            if (teamId == null) {
                ResponseInfo info = new ResponseInfo();
                info.setDetail("teamId参数不能为空");
                info.setMsg("teamId参数不能为空");
                info.setParam("teamId");
                return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
            }
            if (appKey == null) {
                ResponseInfo info = new ResponseInfo();
                info.setDetail("appKey参数不能为空");
                info.setMsg("appKey为必填参数");
                info.setParam("appKey");
                return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
            }
            AppEdition app = this.appEditionService.findByAppKey(appKey);
            if (app == null) {
                ResponseInfo info = new ResponseInfo();
                info.setDetail("appkey不存在,请确认");
                info.setMsg("不存在该appKey");
                info.setParam("appKey");
                return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
            }
            if (signage == null) {
                ResponseInfo info = new ResponseInfo();
                info.setDetail("signage参数不能为空");
                info.setMsg("signage为必填参数");
                info.setParam("signage");
                return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
            }
            BpBwSignage Signage = this.bpBwSignageService.findBwSignageByName(signage);
            if (Signage == null) {
                ResponseInfo info = new ResponseInfo();
                info.setDetail("signage不存在,请确认");
                info.setMsg("不存在该signage");
                info.setParam("signage");
                return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
            }
            BpIndexContentInfoVo bpIndexContentInfoVo = new BpIndexContentInfoVo();
            Team team = this.teamService.findTeamById(teamId);
            //获取前3条班级动态
            // 处理分页参数
            Page page = new Page();
            page.setCurrentPage(1);
            page.setPageSize(3);
            // 处理排序参数
            Order order = new Order();
            order.setProperty(order.getProperty() != null ? order.getProperty() : "create_date");
            CircleMessageCondition circleMessageCondition = new CircleMessageCondition();
            circleMessageCondition.setTeamIds(teamId + "");
            List<CircleMessageClientVo> circleMessageClientVos = null;
            List<CircleMessageVo> circleMessageVos = this.snsCircleMessageService
                    .findCircleMessageVoByCondition(circleMessageCondition, page, order);
            if (circleMessageVos != null && circleMessageVos.size() > 0) {
                circleMessageClientVos = new ArrayList<CircleMessageClientVo>();
                CircleMessageClientVo circleMessageClientVo = null;
                // 遍历结果集组拼客户端需要的数据
                for (CircleMessageVo circleMessageVo : circleMessageVos) {
                    circleMessageClientVo = new CircleMessageClientVo();
                    PropertyUtils.copyProperties(circleMessageClientVo,
                            circleMessageVo);
                    circleMessageClientVo.setPostTime(circleMessageVo.getCreateDate());
                    circleMessageClientVo.setModifyTime(DateUtil.dateToString(circleMessageVo.getModifyDate()));
                    // 处理图片.
                    String entityId = circleMessageVo.getEntityId();
                    if (entityId != null && !entityId.trim().equals("")) {
                        List<Map<String, Object>> files = new ArrayList<Map<String, Object>>();
                        String[] uuids = entityId.split(",");
                        for (int j = 0; j < uuids.length; j++) {
                            Map<String, Object> map = new HashMap<String, Object>();
                            map.put("fileUuid", uuids[j]);
                            map.put("fileUrl", circleMessageVo.getImgUrls()
                                    .get(j));
                            files.add(map);
                        }
                        circleMessageClientVo.setFiles(files);
                    }
                    circleMessageClientVos.add(circleMessageClientVo);
                }
            }
            bpIndexContentInfoVo.setCircleMessageClientVoList(circleMessageClientVos);
            //获取前2条班级活动
            page = new Page();
            page.setCurrentPage(1);
            page.setPageSize(2);
            TeamActivityCondition teamActivityCondition = new TeamActivityCondition();
            teamActivityCondition.setTeamId(teamId);
            List<TeamActivity> activityList = this.teamActivityService
                    .findTeamActivityByCondition(teamActivityCondition, page, order);
            List<CommonActivity> activityListVo = new ArrayList<CommonActivity>();
            CommonActivity activityVo = null;
            for (TeamActivity ta : activityList) {
                activityVo = new CommonActivity();
                BeanUtils.copyProperties(ta, activityVo);
                if (team != null) {
                    activityVo.setTeamName(team.getName());
                }
                if (ta.getFileId() != null) {
                    activityVo.setImgUrl(fileService.relativePath2HttpUrlByUUID(ta.getFileId()));
                }
                activityVo.setCreateDate(ta.getCreateDate());
                activityVo.setFinishTime(ta.getFinishTime());
                activityVo.setModifyDate(DateUtil.dateToString(ta.getModifyDate()));
                activityVo.setStartTime(ta.getStartTime());
                activityListVo.add(activityVo);
            }
            bpIndexContentInfoVo.setCommonActivityList(activityListVo);
            BpBwInfo bpBwInfo = this.bpBwInfoService.findBpBwInfoByTeamId(teamId);
            if (bpBwInfo != null) {
                //获取班级口号
                bpIndexContentInfoVo.setSignature(bpBwInfo.getSignature());
                //获取背景模版
                bpIndexContentInfoVo.setBackgroundTemplate(bpBwInfo.getBackgroundTemplate());
                //获取班级形象背景图片
                if (bpBwInfo.getBackgroundFile() != null) {
                    bpIndexContentInfoVo.setBackgroundFileUrl(fileService.relativePath2HttpUrlByUUID(bpBwInfo.getBackgroundFile()));
                }
            }
            return new ResponseVo<BpIndexContentInfoVo>("0", bpIndexContentInfoVo);
        } catch (Exception e) {
            e.printStackTrace();
            ResponseInfo info = new ResponseInfo();
            info.setDetail("接口异常");
            info.setMsg("接口异常");
            return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL, info);
        }
    }

    @Override
    public Object getBreakContentInfo(Integer teamId,
                                      String appKey, String signage) {

        try {
            if (teamId == null) {
                ResponseInfo info = new ResponseInfo();
                info.setDetail("teamId参数不能为空");
                info.setMsg("teamId参数不能为空");
                info.setParam("teamId");
                return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
            }
            if (appKey == null) {
                ResponseInfo info = new ResponseInfo();
                info.setDetail("appKey参数不能为空");
                info.setMsg("appKey为必填参数");
                info.setParam("appKey");
                return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
            }
            AppEdition app = this.appEditionService.findByAppKey(appKey);
            if (app == null) {
                ResponseInfo info = new ResponseInfo();
                info.setDetail("appkey不存在,请确认");
                info.setMsg("不存在该appKey");
                info.setParam("appKey");
                return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
            }
            if (signage == null) {
                ResponseInfo info = new ResponseInfo();
                info.setDetail("signage参数不能为空");
                info.setMsg("signage为必填参数");
                info.setParam("signage");
                return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
            }
            BpBwSignage Signage = this.bpBwSignageService.findBwSignageByName(signage);
            if (Signage == null) {
                ResponseInfo info = new ResponseInfo();
                info.setDetail("signage不存在,请确认");
                info.setMsg("不存在该signage");
                info.setParam("signage");
                return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
            }
            Order order = null;
            Page page = null;
            BpBreakContentInfoVo bpBreakContentInfoVo = new BpBreakContentInfoVo();
            //获取当天值日生
            Date nowDay = new Date();
            Date date = DateUtil.getDayStartTime(nowDay);
            List<TeamDutyUser> tdulist = this.teamDutyUserService.findTeamDutyStudent(teamId, date);
            List<DutyUser> userList = new ArrayList<DutyUser>();
            if (tdulist.size() > 0) {
                DutyUser vo = null;
                for (TeamDutyUser tdu : tdulist) {
                    vo = new DutyUser();
                    vo.setUserId(tdu.getUserId());
                    vo.setStudentName(this.studentService.findStudentByUserId(tdu.getUserId()).getName());
                    Integer personId = this.studentService.findStudentByUserId(tdu.getUserId()).getPersonId();
                    if (personId != null) {
                        vo.setUserIcon(ImgUtil.getStudentIconUrl(personId, personService));
                    }
                    userList.add(vo);
                }
            }
            bpBreakContentInfoVo.setTdulist(userList);

            //获取最新表扬栏
            order = new Order();
            page = new Page();
            BpPraiseClientVo bpPraiseClientVo = new BpPraiseClientVo();
            page.setCurrentPage(1);
            page.setPageSize(1);
            order.setProperty("create_date");
            order.setAscending(false);
            PraiseCondition praiseCondition = new PraiseCondition();
            // 如果指定班级则按班级查询.
            if (teamId != null) {
                praiseCondition.setTeamIds(teamId + "");
            }
            List<String> createDateList = this.praiseService.findPraiseCreateDateByCondition(praiseCondition, page, order);
            if (createDateList != null && createDateList.size() > 0) {
                List<PraiseClientVo> praiseClientList = new ArrayList<PraiseClientVo>();
                // 组拼手机端需要的信息
                praiseCondition.setCreateDateStr(createDateList.get(0));
                List<PraiseVo> praiseVos = praiseService.findPraiseVoByCondition(praiseCondition, null, null);
                if (praiseVos != null && praiseVos.size() > 0) {
                    //默认头像
                    String imgUrl = ImgUtil.getImgUrl(null);
                    PraiseClientVo praiseClientVo;
                    for (PraiseVo praiseVo : praiseVos) {
                        praiseClientVo = new PraiseClientVo();
                        Integer personId = this.studentService.findStudentByUserId(Integer.parseInt(praiseVo.getReceiverId())).getPersonId();
                        imgUrl = ImgUtil.getStudentIconUrl(personId, personService);
                        PropertyUtils.copyProperties(praiseClientVo, praiseVo);
                        List<Map<String, Object>> students = praiseClientVo.getStudents();
                        if (students != null && students.size() > 0) {
                            for (Map<String, Object> map : students) {
                                String url = map.get("iconUrl").toString();
                                if ("".equals(url)) {
                                    map.put("iconUrl", imgUrl);
                                }
                            }
                        }
                        praiseClientVo.setPostTime(praiseVo.getCreateDate());
                        praiseClientVo.setModifyTime(DateUtil.dateToString(praiseVo.getModifyDate()));
                        praiseClientList.add(praiseClientVo);
                    }
                }
                bpPraiseClientVo.setCreateDate(SDF.parse(createDateList.get(0)).getTime());
                bpPraiseClientVo.setPraiseClientList(praiseClientList);
            }
            bpBreakContentInfoVo.setBpPraiseClientVo(bpPraiseClientVo);


            //获取最新3个班级荣耀
            page = new Page();
            page.setCurrentPage(1);
            page.setPageSize(3);
            TeamAwardCondition condition = new TeamAwardCondition();
            condition.setTeamId(teamId);
            order.setProperty("create_date");
            order.setAscending(false);
            List<TeamAwardVo> list = this.teamAwardService.findTeamAwardVoByCondition(condition, page, order);
            List<ClientTeamAwardVo> clientTeamAwardVoList = new ArrayList<ClientTeamAwardVo>();
            if (list != null && list.size() > 0) {
                for (TeamAwardVo teamAwardVo : list) {
                    ClientTeamAwardVo clientTeamAwardVo = new ClientTeamAwardVo();
                    Team t = this.teamService.findTeamById(teamAwardVo.getTeamId());
                    if (t != null) {
                        clientTeamAwardVo.setId(teamAwardVo.getId());
                        clientTeamAwardVo.setSchoolId(teamAwardVo.getSchoolId());
                        clientTeamAwardVo.setTeamId(teamAwardVo.getTeamId());
                        clientTeamAwardVo.setTeanName(t.getName());
                        clientTeamAwardVo.setName(teamAwardVo.getName());
                        clientTeamAwardVo.setAwardTime(DateUtil.dateToString(teamAwardVo.getAwardTime()));
                        clientTeamAwardVo.setAwardImage(teamAwardVo.getAwardImage());
                        if (teamAwardVo.getAwardImage() != null || !"".equals(teamAwardVo.getAwardImage())) {
                            clientTeamAwardVo.setAwardImageURL(this.fileService.relativePath2HttpUrlByUUID(clientTeamAwardVo.getAwardImage()));
                        } else {
                            clientTeamAwardVo.setAwardImageURL("");
                        }
                        clientTeamAwardVo.setComment(clientTeamAwardVo.getComment());
                        clientTeamAwardVo.setSchoolYear(clientTeamAwardVo.getSchoolYear());
                        clientTeamAwardVo.setTermCode(clientTeamAwardVo.getTermCode());
                        clientTeamAwardVo.setCreateDate(clientTeamAwardVo.getCreateDate());
                        clientTeamAwardVo.setModifyDate(clientTeamAwardVo.getModifyDate());
                    }
                    clientTeamAwardVoList.add(clientTeamAwardVo);
                }
            }
            bpBreakContentInfoVo.setClientTeamAwardVoList(clientTeamAwardVoList);

            //获取第一页视频
            page = new Page();
            page.setCurrentPage(1);
            page.setPageSize(BpCommonConstants.PAGE_SIZE_ONE);
            order.setProperty(order.getProperty() != null ? order.getProperty() : "create_date");
            order.setAscending(false);
            BpBwPictureAlbumCondition bwPictureAlbumCondition = new BpBwPictureAlbumCondition();
            bwPictureAlbumCondition.setIsDeleted(false);
            bwPictureAlbumCondition.setTeamId(teamId);
            List<BpBwPictureAlbumVo> albumList = bpBwPictureAlbumService.findBwPictureAlbumByCondition(bwPictureAlbumCondition, page, order);
            bpBreakContentInfoVo.setAlbumList(albumList);
//			BpBwFileCondition bwFileCondition = new BpBwFileCondition();
//			bwFileCondition.setObjectType(BwFileConstants.TYPE_HISTORY_VIDEO);
//			bwFileCondition.setIsDeleted(false);;
//			List<platform.education.clazz.vo.BpBwFileVo> bpBwFileList = bpBwFileService.findBwFileByCondition(bwFileCondition, page, order);
//			List<BpBwFileVo> bpBwFileVoList = new ArrayList<BpBwFileVo>();
//			if(bpBwFileList != null && bpBwFileList.size() > 0){
//				for(BpBwFile bpBwFile: bpBwFileList){
//					BpBwFileVo bpBwFileVo = new BpBwFileVo();
//					bpBwFileVo.setId(bpBwFile.getId());
//					bpBwFileVo.setName(bpBwFile.getName());
//					bpBwFileVo.setCreateDate(DateUtil.dateToString(bpBwFile.getCreateDate()));
//					bpBwFileVo.setFileUrl(this.fileService.relativePath2HttpUrlByUUID(bpBwFile.getFileId()));
//					FileResult fileResult = fileService.findFileByUUID(bpBwFile.getFileId());
//					String httpPrefix = fileService.getHttpPrefix();
//					String spaceName = fileService.getSpaceName();
//					EntityFile entityFile = fileResult.getEntityFile();
//					if ( entityFile != null && entityFile.getThumbnailUrl() != null && entityFile.getThumbnailUrl().length() > 0 ) {
//						bpBwFileVo.setThumbnailUrl(httpPrefix+"/"+spaceName + entityFile.getThumbnailUrl());
//					}
//					bpBwFileVoList.add(bpBwFileVo);
//				}				
//			}	
//			bpBreakContentInfoVo.setBpBwFileVoList(bpBwFileVoList);


            return new ResponseVo<BpBreakContentInfoVo>("0", bpBreakContentInfoVo);
        } catch (Exception e) {
            e.printStackTrace();
            ResponseInfo info = new ResponseInfo();
            info.setDetail("接口异常");
            info.setMsg("接口异常");
            return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL, info);
        }

    }

    @Override
    public Object getRoomListInfo(Integer schoolId, String appKey, String signage) {
        try {
            /*if (StringUtils.isEmpty(schoolId)) {
                return ResponseUtil.paramerIsNull("schoolId:" + schoolId);
            }*/
            List<Map<String, Object>> list = this.roomService.findRoomGroupbyType(schoolId, null);
            return new ResponseVo<>("0", list);
        } catch (Exception e) {
            e.printStackTrace();
            ResponseInfo info = new ResponseInfo();
            info.setDetail("接口异常");
            info.setMsg("接口异常");
            return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL, info);
        }

    }

    @Override
    public Object getCurrent(String appKey) {
        if (appKey == null || "".equals(appKey)) {
            ResponseInfo info = new ResponseInfo();
            info.setDetail("appKey参数必填");
            info.setMsg("appKey参数不能为空");
            info.setParam("appKey");
            return new ResponseError("060111", info);
        }

        AppEdition appEdition = appEditionService.findByAppKey(appKey);

        if (appEdition == null) {
            ResponseInfo info = new ResponseInfo();
            info.setDetail("appKey不存在");
            info.setMsg("appKey参数不存在");
            info.setParam("appKey");
            return new ResponseError("020101", info);
        }

        //存放单个版本信息
        ExtAppReleaseVo extAppReleaseVo = null;

        List<ExtAppReleaseVo> appReleaseVoList = new ArrayList<ExtAppReleaseVo>();

        try {
            AppReleaseCondition appReleaseCondition = new AppReleaseCondition();
            appReleaseCondition.setAppKey(appKey);
            appReleaseCondition.setIsDeleted(false);
            appReleaseCondition.setIsCurrent(true);
            List<AppRelease> appReleases = appReleaseService.findAppReleaseByCondition(appReleaseCondition);

            App app = null;
            FileResult setupFile = null;
            FileResult qrCodeFile = null;
            FileResult packageFile = null;
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if (appReleases != null && appReleases.size() > 0) {
                for (AppRelease appRelease : appReleases) {
                    if (appEdition != null) {
                        app = appService.findAppById(appRelease.getAppId());
                        //获取文件路径
                        setupFile = fileService.findFileByUUID(appRelease.getSetupFile());
                        qrCodeFile = fileService.findFileByUUID(appRelease.getQrCodeFile());
                        packageFile = fileService.findFileByUUID(appRelease.getPackageFile());
                        String setUpUrl = setupFile == null ? "" : setupFile.getHttpUrl();
                        String newUrl = null;
                        if (setUpUrl != null && !"".equals(setUpUrl)) {
                            newUrl = setUpUrl.replace("https", "http");
                        }

                        extAppReleaseVo = new ExtAppReleaseVo();
                        extAppReleaseVo.setName(appEdition.getName());
                        extAppReleaseVo.setVersion(appRelease.getVersion());
                        extAppReleaseVo.setReleaseDate(format.format(appRelease.getReleaseDate()));
                        extAppReleaseVo.setOsVersion(appRelease.getOsVersion());
                        extAppReleaseVo.setTrademark(fileService.relativePath2HttpUrlByUUID(app.getTrademark()));
                        extAppReleaseVo.setManufacturer(app == null ? "" : app.getManufacturer());
                        extAppReleaseVo.setCopyright(appRelease.getCopyright());
                        extAppReleaseVo.setQrCodeFile(qrCodeFile == null ? "" : qrCodeFile.getHttpUrl());
                        extAppReleaseVo.setSetupFile(newUrl);
                        extAppReleaseVo.setPackageFile(packageFile == null ? "" : packageFile.getHttpUrl());
                        extAppReleaseVo.setDescription(appRelease.getDescription());
                        extAppReleaseVo.setIsForce(appRelease.getIsForce());
                        if (setupFile != null) {
                            extAppReleaseVo.setPackageSize(CalculateVisualFileSize.getFileSize(setupFile.getEntityFile().getSize()));
                            extAppReleaseVo.setExactSize(setupFile.getEntityFile().getSize());
                        }
                        appReleaseVoList.add(extAppReleaseVo);
                    }
                }
            }
        } catch (Exception e) {
//			log.debug("数据获取异常");
            return new ResponseVo<ExtAppReleaseVo>("000001", null);//未知错误
        }
        return new ResponseVo<List<ExtAppReleaseVo>>("0", appReleaseVoList);
    }

    @Override
    public Object imAccount(String appKey, String id, String type) {
        try {
            ImAccountCondition condition = new ImAccountCondition();
            ImProvider ip = this.imProviderService.findDefaultProvider();
            ImAccount imAccount = null;
            AppAuthorization appAuth = this.appAuthorizationService.findByAppKey(appKey);
            //个人账户
            if ("1".equals(type)) {
                condition.setAppKey(appAuth != null ? appAuth.getImAccountApp() : appKey);
                condition.setUserId(Integer.parseInt(id));
                condition.setImType(ip != null ? ip.getImType() : "1");
                List<ImAccount> list = this.imAccountService.findImAccountByCondition(condition, null, null);
                //无账号
                if (list.size() < 1) {
                    User user = this.userService.findUserById(Integer.parseInt(id));
                    if (user != null) {
                        imAccount = this.imService.createIMAccountForUser(Integer.parseInt(id), "", appAuth != null ? appAuth.getImAccountApp() : appKey);
                        if (imAccount != null) {
                            if (("error").equals(imAccount.getImAccountStatus()) || ("fail").equals(imAccount.getImAccountStatus())) {
                                ResponseInfo info = new ResponseInfo();
                                info.setDetail("环信账号不存在...");
                                info.setMsg("账号不存在");
                                info.setParam("");
                                return new ResponseError("000007", info);
                            }
                        }
                    } else {
                        ResponseInfo info = new ResponseInfo();
                        info.setDetail("不存在该user...");
                        info.setMsg("参数出错");
                        info.setParam("id");
                        return new ResponseError("511001", info);
                    }
                } else {
                    imAccount = list.get(0);
                }
            }
            if ("2".equals(type)) {
                condition.setAppKey(appAuth != null ? appAuth.getImAccountApp() : appKey);
                condition.setOwnerId(id);
                condition.setImType(ip != null ? ip.getImType() : "1");
                List<ImAccount> list = this.imAccountService.findImAccountByCondition(condition, null, null);
                //无账号
                if (list.size() < 1) {
                    imAccount = this.imService.createIMAccount(id, "", appAuth != null ? appAuth.getImAccountApp() : appKey);
                    if (imAccount != null) {
                        if (("error").equals(imAccount.getImAccountStatus()) || ("fail").equals(imAccount.getImAccountStatus())) {
                            ResponseInfo info = new ResponseInfo();
                            info.setDetail("环信账号不存在...");
                            info.setMsg("账号不存在");
                            info.setParam("");
                            return new ResponseError("000007", info);
                        }
                    }
                } else {
                    imAccount = list.get(0);
                }
            }
            AppAccount appAccount = new AppAccount();
            BeanUtils.copyProperties(imAccount, appAccount);
            return new ResponseVo<AppAccount>("0", appAccount);
        } catch (Exception e) {
            ResponseInfo info = new ResponseInfo();
            info.setDetail("参数解析异常...");
            info.setMsg("参数出错");
            info.setParam("");
            return new ResponseError("060113", info);
        }
    }

    @Override
    public Object getIndexInfo(Integer teamId, String appKey, String signage) {
        try {
            if (teamId == null) {
                ResponseInfo info = new ResponseInfo();
                info.setDetail("teamId参数不能为空");
                info.setMsg("teamId参数不能为空");
                info.setParam("teamId");
                return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
            }
            if (appKey == null) {
                ResponseInfo info = new ResponseInfo();
                info.setDetail("appKey参数不能为空");
                info.setMsg("appKey为必填参数");
                info.setParam("appKey");
                return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
            }
            AppEdition app = this.appEditionService.findByAppKey(appKey);
            if (app == null) {
                ResponseInfo info = new ResponseInfo();
                info.setDetail("appkey不存在,请确认");
                info.setMsg("不存在该appKey");
                info.setParam("appKey");
                return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
            }
            if (signage == null) {
                ResponseInfo info = new ResponseInfo();
                info.setDetail("signage参数不能为空");
                info.setMsg("signage为必填参数");
                info.setParam("signage");
                return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
            }
            BpBwSignage Signage = this.bpBwSignageService.findBwSignageByName(signage);
            if (Signage == null) {
                ResponseInfo info = new ResponseInfo();
                info.setDetail("signage不存在,请确认");
                info.setMsg("不存在该signage");
                info.setParam("signage");
                return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
            }
            Page page = new Page();
            Order order = new Order();
            BpIndexInfoVo infoVo = new BpIndexInfoVo();

            //获取班级荣耀
            page = new Page();
            TeamAwardCondition condition = new TeamAwardCondition();
            condition.setTeamId(teamId);
            order.setProperty("create_date");
            order.setAscending(false);
            List<TeamAwardVo> list = this.teamAwardService.findTeamAwardVoByCondition(condition, page, order);
            List<ClientTeamAwardVo> clientTeamAwardVoList = new ArrayList<ClientTeamAwardVo>();
            if (list != null && list.size() > 0) {
                for (TeamAwardVo teamAwardVo : list) {
                    ClientTeamAwardVo clientTeamAwardVo = new ClientTeamAwardVo();
                    Team t = this.teamService.findTeamById(teamAwardVo.getTeamId());
                    if (t != null) {
                        clientTeamAwardVo.setId(teamAwardVo.getId());
                        clientTeamAwardVo.setSchoolId(teamAwardVo.getSchoolId());
                        clientTeamAwardVo.setTeamId(teamAwardVo.getTeamId());
                        clientTeamAwardVo.setTeanName(t.getName());
                        clientTeamAwardVo.setName(teamAwardVo.getName());
                        clientTeamAwardVo.setAwardTime(DateUtil.dateToString(teamAwardVo.getAwardTime()));
                        clientTeamAwardVo.setAwardImage(teamAwardVo.getAwardImage());
                        if (teamAwardVo.getAwardImage() != null || !"".equals(teamAwardVo.getAwardImage())) {
                            clientTeamAwardVo.setAwardImageURL(this.fileService.relativePath2HttpUrlByUUID(clientTeamAwardVo.getAwardImage()));
                        } else {
                            clientTeamAwardVo.setAwardImageURL("");
                        }
                        clientTeamAwardVo.setComment(clientTeamAwardVo.getComment());
                        clientTeamAwardVo.setSchoolYear(clientTeamAwardVo.getSchoolYear());
                        clientTeamAwardVo.setTermCode(clientTeamAwardVo.getTermCode());
                        clientTeamAwardVo.setCreateDate(clientTeamAwardVo.getCreateDate());
                        clientTeamAwardVo.setModifyDate(clientTeamAwardVo.getModifyDate());
                    }
                    clientTeamAwardVoList.add(clientTeamAwardVo);
                }
            }
            infoVo.setClientTeamAwardVoList(clientTeamAwardVoList);

            //通知公告
			/*Team t = null;
			BBXNoticeClient data[] = null;
			BBXNoticeClient bc = null;*/
            List<BBXNotice> noticeList = this.teamNoticeService.findNoticeByCondition(teamId, null, null, null, page, order);

            infoVo.setNoticeList(noticeList);


            //班级相册
            page = new Page();
            page.setCurrentPage(1);
//			page.setPageSize(1);
            order = new Order();
            order.setProperty("create_date");
            order.setAscending(false);
            BpBwPictureAlbumCondition bwPictureAlbumCondition = new BpBwPictureAlbumCondition();
            bwPictureAlbumCondition.setTeamId(teamId);
            List<BpBwPictureAlbumVo> albumVoList = bpBwPictureAlbumService.findBwPictureAlbumByCondition(bwPictureAlbumCondition, page, order);

            infoVo.setAlbumList(albumVoList);


            //作业通知
            TeamHomeworkCondition homeworkCondition = new TeamHomeworkCondition();
            Team team = this.teamService.findTeamById(teamId);
            SchoolTermCurrent stc = null;
            if (team != null) {
                stc = this.schoolTermCurrentService.findSchoolTermCurrentBySchoolId(team.getSchoolId());
                condition.setTermCode(stc.getSchoolTermCode());
            }
            homeworkCondition.setIsDeleted(false);
            List<TeamHomework> hwList = this.teamHomeworkService.findTeamHomeworkByCondition(homeworkCondition, null, null);
            List<CommonHomework> voList = new ArrayList<CommonHomework>();
            if (team != null) {
                if (hwList != null && hwList.size() > 0) {
                    CommonHomework vo = null;
                    Subject subject = null;
                    for (TeamHomework thw : hwList) {
                        if (thw.getCreateDate().getDate() == new Date().getDate()
                                && thw.getCreateDate().getMonth() == new Date().getMonth()
                                && thw.getCreateDate().getYear() == new Date().getYear()) {
                            vo = new CommonHomework();
                            BeanUtils.copyProperties(thw, vo);
                            vo.setTeamName(team.getName());
                            subject = this.subjectService.findUnique(
                                    team.getSchoolId(), thw.getSubjectCode());
                            if (subject != null) {
                                vo.setSubjectName(subject.getName());
                            }
                            vo.setCreateDate(DateUtil.dateToString(thw.getCreateDate()));
                            vo.setModifyDate(DateUtil.dateToString(thw.getModifyDate()));
                            voList.add(vo);
                        }
                    }
                }
            }
            infoVo.setHomeworkList(voList);

            //校园信息
            page = new Page();
            page.setCurrentPage(1);
            page.setPageSize(10);
            order = new Order();
            order.setProperty("create_date");
            order.setAscending(false);
            SchoolInfoCondition schoolInfoCondition = new SchoolInfoCondition();
            schoolInfoCondition.setSchoolId(team.getSchoolId());
            List<SchoolInfo> schoolInfoList = this.schoolInfoService.findSchoolInfoByCondition(schoolInfoCondition, page, order);
            List<SchoolInfoVo> infoVoList = new ArrayList<SchoolInfoVo>();
            for (SchoolInfo SchoolInfo : schoolInfoList) {
                SchoolInfoVo vo = new SchoolInfoVo();
                BeanUtils.copyProperties(SchoolInfo, vo);
                List<String> urlList = new ArrayList<String>();
                SchoolInfoFileCondition fileCondition = new SchoolInfoFileCondition();
                fileCondition.setSchoolInfoId(SchoolInfo.getId());
                List<SchoolInfoFile> fileList = this.schoolInfoFileService.findSchoolInfoFileByCondition(fileCondition);
                for (SchoolInfoFile file : fileList) {
                    urlList.add(fileService.relativePath2HttpUrlByUUID(file.getFileId()));
                }
                vo.setImageUrlList(urlList);
                infoVoList.add(vo);
            }
            infoVo.setSchoolInfoList(infoVoList);


            //班级成员
            TeamStudentCondition teamStudentConditon = new TeamStudentCondition();
            teamStudentConditon.setTeamId(teamId);
            teamStudentConditon.setInState(true);
            teamStudentConditon.setSchoolYear(stc.getSchoolYear());
            List<TeamStudent> studentList = this.teamStudentService.findTeamStudentByCondition(teamStudentConditon, null, null);
            List<PersonVo> persons = new ArrayList<PersonVo>();
            String imgUrl = ImgUtil.getImgUrl(null);
            String imgSrc = "";
            for (TeamStudent student : studentList) {
                PersonVo person = new PersonVo();
                person.setUserId(student.getUserId());
                person.setName(student.getName());
                Integer personId = this.studentService.findStudentByUserId(student.getUserId()).getPersonId();
                imgSrc = ImgUtil.getStudentIconUrl(personId, personService);
                person.setIcon(imgSrc);
                if ("".equals(imgSrc)) {
                    person.setIcon(imgUrl);
                }
                persons.add(person);
            }
            infoVo.setStudentList(persons);

            //班级介绍
            BpBwInfo info = this.bpBwInfoService.findBpBwInfoByTeamId(teamId);
            if (info != null) {
                infoVo.setTeamIntroduce(info.getDescription());
            }

            return new ResponseVo<BpIndexInfoVo>("0", infoVo);
        } catch (Exception e) {
            e.printStackTrace();
            ResponseInfo info = new ResponseInfo();
            info.setDetail("接口异常");
            info.setMsg("接口异常");
            return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL, info);
        }
    }

    @Override
    public Object getNewBreakContentInfo(Integer teamId, String appKey,
                                         String signage) {

        try {
            if (teamId == null) {
                ResponseInfo info = new ResponseInfo();
                info.setDetail("teamId参数不能为空");
                info.setMsg("teamId参数不能为空");
                info.setParam("teamId");
                return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
            }
            if (appKey == null) {
                ResponseInfo info = new ResponseInfo();
                info.setDetail("appKey参数不能为空");
                info.setMsg("appKey为必填参数");
                info.setParam("appKey");
                return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
            }
            AppEdition app = this.appEditionService.findByAppKey(appKey);
            if (app == null) {
                ResponseInfo info = new ResponseInfo();
                info.setDetail("appkey不存在,请确认");
                info.setMsg("不存在该appKey");
                info.setParam("appKey");
                return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
            }
            if (signage == null) {
                ResponseInfo info = new ResponseInfo();
                info.setDetail("signage参数不能为空");
                info.setMsg("signage为必填参数");
                info.setParam("signage");
                return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
            }
            BpBwSignage Signage = this.bpBwSignageService.findBwSignageByName(signage);
            if (Signage == null) {
                ResponseInfo info = new ResponseInfo();
                info.setDetail("signage不存在,请确认");
                info.setMsg("不存在该signage");
                info.setParam("signage");
                return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
            }
            Order order = null;
            Page page = null;
            BpBreakContentInfoVo bpBreakContentInfoVo = new BpBreakContentInfoVo();
            //获取当天值日生
            Date nowDay = new Date();
            Integer week = DateUtil.getWeek(nowDay);
            TeamDutyUserCondition teamDutyUsercondition = new TeamDutyUserCondition();
            teamDutyUsercondition.setWeek(week);
            teamDutyUsercondition.setTeamId(teamId);
            List<TeamDutyUser> tdulist = this.teamDutyUserService.findTeamDutyUserByCondition(teamDutyUsercondition);
            List<DutyUser> userList = new ArrayList<DutyUser>();
            if (tdulist.size() > 0) {
                DutyUser vo = null;
                for (TeamDutyUser tdu : tdulist) {
                    vo = new DutyUser();
                    vo.setUserId(tdu.getUserId());
                    vo.setStudentName(this.studentService.findStudentByUserId(tdu.getUserId()).getName());
                    Integer personId = this.studentService.findStudentByUserId(tdu.getUserId()).getPersonId();
                    if (personId != null) {
                        vo.setUserIcon(ImgUtil.getStudentIconUrl(personId, personService));
                    }
                    userList.add(vo);
                }
            }
            bpBreakContentInfoVo.setTdulist(userList);

            //获取最新表扬栏
            order = new Order();
            page = new Page();
            BpPraiseClientVo bpPraiseClientVo = new BpPraiseClientVo();
            page.setCurrentPage(1);
            page.setPageSize(1);
            order.setProperty("create_date");
            order.setAscending(false);
            PraiseCondition praiseCondition = new PraiseCondition();
            // 如果指定班级则按班级查询.
            if (teamId != null) {
                praiseCondition.setTeamIds(teamId + "");
            }
            List<String> createDateList = this.praiseService.findPraiseCreateDateByCondition(praiseCondition, page, order);
            if (createDateList != null && createDateList.size() > 0) {
                List<PraiseClientVo> praiseClientList = new ArrayList<PraiseClientVo>();
                // 组拼手机端需要的信息
                praiseCondition.setCreateDateStr(createDateList.get(0));
                List<PraiseVo> praiseVos = praiseService.findPraiseVoByCondition(praiseCondition, null, null);
                if (praiseVos != null && praiseVos.size() > 0) {
                    //默认头像
                    String imgUrl = ImgUtil.getImgUrl(null);
                    PraiseClientVo praiseClientVo;
                    for (PraiseVo praiseVo : praiseVos) {
                        praiseClientVo = new PraiseClientVo();
                        Integer personId = this.studentService.findStudentByUserId(Integer.parseInt(praiseVo.getReceiverId())).getPersonId();
                        imgUrl = ImgUtil.getStudentIconUrl(personId, personService);
                        PropertyUtils.copyProperties(praiseClientVo, praiseVo);
                        List<Map<String, Object>> students = praiseClientVo.getStudents();
                        if (students != null && students.size() > 0) {
                            for (Map<String, Object> map : students) {
                                String url = map.get("iconUrl").toString();
                                if ("".equals(url)) {
                                    map.put("iconUrl", imgUrl);
                                }
                            }
                        }
                        praiseClientVo.setPostTime(praiseVo.getCreateDate());
                        praiseClientVo.setModifyTime(DateUtil.dateToString(praiseVo.getModifyDate()));
                        praiseClientList.add(praiseClientVo);
                    }
                }
                bpPraiseClientVo.setCreateDate(SDF.parse(createDateList.get(0)).getTime());
                bpPraiseClientVo.setPraiseClientList(praiseClientList);
            }
            bpBreakContentInfoVo.setBpPraiseClientVo(bpPraiseClientVo);


            //获取最新3个班级荣耀
            page = new Page();
            page.setCurrentPage(1);
            page.setPageSize(3);
            TeamAwardCondition condition = new TeamAwardCondition();
            condition.setTeamId(teamId);
            order.setProperty("create_date");
            order.setAscending(false);
            List<TeamAwardVo> list = this.teamAwardService.findTeamAwardVoByCondition(condition, page, order);
            List<ClientTeamAwardVo> clientTeamAwardVoList = new ArrayList<ClientTeamAwardVo>();
            if (list != null && list.size() > 0) {
                for (TeamAwardVo teamAwardVo : list) {
                    ClientTeamAwardVo clientTeamAwardVo = new ClientTeamAwardVo();
                    Team t = this.teamService.findTeamById(teamAwardVo.getTeamId());
                    if (t != null) {
                        clientTeamAwardVo.setId(teamAwardVo.getId());
                        clientTeamAwardVo.setSchoolId(teamAwardVo.getSchoolId());
                        clientTeamAwardVo.setTeamId(teamAwardVo.getTeamId());
                        clientTeamAwardVo.setTeanName(t.getName());
                        clientTeamAwardVo.setName(teamAwardVo.getName());
                        clientTeamAwardVo.setAwardTime(DateUtil.dateToString(teamAwardVo.getAwardTime()));
                        clientTeamAwardVo.setAwardImage(teamAwardVo.getAwardImage());
                        if (teamAwardVo.getAwardImage() != null || !"".equals(teamAwardVo.getAwardImage())) {
                            clientTeamAwardVo.setAwardImageURL(this.fileService.relativePath2HttpUrlByUUID(clientTeamAwardVo.getAwardImage()));
                        } else {
                            clientTeamAwardVo.setAwardImageURL("");
                        }
                        clientTeamAwardVo.setComment(clientTeamAwardVo.getComment());
                        clientTeamAwardVo.setSchoolYear(clientTeamAwardVo.getSchoolYear());
                        clientTeamAwardVo.setTermCode(clientTeamAwardVo.getTermCode());
                        clientTeamAwardVo.setCreateDate(clientTeamAwardVo.getCreateDate());
                        clientTeamAwardVo.setModifyDate(clientTeamAwardVo.getModifyDate());
                    }
                    clientTeamAwardVoList.add(clientTeamAwardVo);
                }
            }
            bpBreakContentInfoVo.setClientTeamAwardVoList(clientTeamAwardVoList);

            //获取第一页视频
            page = new Page();
            page.setCurrentPage(1);
            page.setPageSize(BpCommonConstants.PAGE_SIZE_ONE);
            order.setProperty(order.getProperty() != null ? order.getProperty() : "create_date");
            order.setAscending(false);
            BpBwPictureAlbumCondition bwPictureAlbumCondition = new BpBwPictureAlbumCondition();
            bwPictureAlbumCondition.setIsDeleted(false);
            bwPictureAlbumCondition.setTeamId(teamId);
            List<BpBwPictureAlbumVo> albumList = bpBwPictureAlbumService.findBwPictureAlbumByCondition(bwPictureAlbumCondition, page, order);
            bpBreakContentInfoVo.setAlbumList(albumList);
            return new ResponseVo<BpBreakContentInfoVo>("0", bpBreakContentInfoVo);
        } catch (Exception e) {
            e.printStackTrace();
            ResponseInfo info = new ResponseInfo();
            info.setDetail("接口异常");
            info.setMsg("接口异常");
            return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL, info);
        }
    }

    @Override
    public Object updatePushFlag(Integer roomId, Integer teamId, String type, String pushType, Integer objectId, String version, String appKey, String signage) {
        Object checkResult = ValidateUtil.checkAppKeyAndSignae(appKey, signage, appEditionService, bpBwSignageService);
        if (checkResult != null) {
            return checkResult;
        }
       /* if (StringUtils.isEmpty(pushType)) {
            return ResponseUtil.paramerIsNull("pushType:" + pushType);
        }
*/
        if (BpCommonConstants.PUSH_SIGNAGE_AUTO.equals(pushType)) {
            BpBwSignage bpBwSignage = this.bpBwSignageService.findBwSignageByName(signage);
            if (bpBwSignage != null) {
                bpBwSignage.setPushSignageAuto(true);
                bpBwSignage.setPushSignageAutoTime(new Date());
                this.bpBwSignageService.modify(bpBwSignage);
            }
        } else if (BpCommonConstants.CHECK_ONLINE.equals(pushType)) {
            BpBwSignage bpBwSignage = this.bpBwSignageService.findBwSignageByName(signage);
            if (bpBwSignage != null) {
                bpBwSignage.setUsingState(1);
                bpBwSignage.setNum(version);
                this.bpBwSignageService.modify(bpBwSignage);
            }
        } else if (BpCommonConstants.PUSH_EXAM_INFO.equals(pushType)) {
            if (DataAction.D$delete.equals(type)) {
                BpBwExamInfo exam = this.bpBwExamInfoService.findBwExamInfoById(objectId);
                exam.setBpDeleted(true);
                this.bpBwExamInfoService.modify(exam);
            } else if (DataAction.D$add.equals(type)) {
                BpBwExamInfo exam = this.bpBwExamInfoService.findBwExamInfoById(objectId);
                exam.setBpReceived(true);
                this.bpBwExamInfoService.modify(exam);
            }
        }
        return new ResponseVo<Boolean>("0", true);
    }

    @Override
    public Object getCardInfo(String cardId, String appKey, String signage) {
        try {
            Object checkResult = ValidateUtil.checkAppKeyAndSignae(appKey, signage, appEditionService, bpBwSignageService);
            if (checkResult != null) {
                return checkResult;
            }
          /*  if (StringUtils.isEmpty(cardId)) {
                return ResponseUtil.paramerIsNull("cardId:" + cardId);
            }*/
            BpBwSchoolCard bpBwSchoolCard = this.bpBwSchoolCardService.findBwSchoolCardByAccountId(cardId);
            if (bpBwSchoolCard == null) {
                ResponseInfo info = new ResponseInfo();
                info.setDetail("非法卡号");
                info.setMsg("非法卡号");
                return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL, info);
            }

            SchoolUserCondition schoolUserCondition = new SchoolUserCondition();
            schoolUserCondition.setUserId(bpBwSchoolCard.getUserId());
            schoolUserCondition.setIsDeleted(false);
            List<SchoolUser> schoolUserList = this.schoolUserService.findSchoolUserByCondition(schoolUserCondition, null, null);
            if (schoolUserList == null) {
                ResponseInfo info = new ResponseInfo();
                info.setDetail("接口异常");
                info.setMsg("接口异常");
                return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL, info);
            }
            SchoolUser schoolUser = schoolUserList.get(0);

            PersonVo personVo = new PersonVo();
            Integer userType = bpBwSchoolCard.getUserType();
            if (userType == BpCommonConstants.USER_TYPE_STUDENT) {
                //学生卡
                Student student = studentService.findStudentByUserId(bpBwSchoolCard.getUserId());
                personVo.setUserId(student.getUserId());
                personVo.setName(student.getName());
                personVo.setIcon(ImgUtil.getStudentIconUrl(student.getPersonId(), personService));
                personVo.setSchoolId(schoolUser.getSchoolId());
                personVo.setUserType(userType);
            } else if (userType == BpCommonConstants.USER_TYPE_TEACHER) {
                //老师卡
                TeacherCondition teacherCondition = new TeacherCondition();
                teacherCondition.setUserId(bpBwSchoolCard.getUserId());
                List<Teacher> teacherList = this.teacherService.findTeacherByCondition(teacherCondition, null, null);
                if (teacherList != null && teacherList.size() > 0) {
                    Teacher teacher = teacherList.get(0);
                    personVo.setUserId(teacher.getUserId());
                    personVo.setName(teacher.getName());
                    personVo.setIcon(ImgUtil.getImgSrc(teacher.getUserId(), profileService));
                    personVo.setSchoolId(schoolUser.getSchoolId());
                    personVo.setUserType(userType);
                }
            }
            return new ResponseVo<PersonVo>("0", personVo);
        } catch (Exception e) {
            e.printStackTrace();
            ResponseInfo info = new ResponseInfo();
            info.setDetail("接口异常");
            info.setMsg("接口异常");
            return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL, info);
        }
    }

}
