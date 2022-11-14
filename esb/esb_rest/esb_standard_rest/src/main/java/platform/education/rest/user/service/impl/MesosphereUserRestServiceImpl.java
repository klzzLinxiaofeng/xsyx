package platform.education.rest.user.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import framework.generic.dao.Order;
import platform.education.generalTeachingAffair.model.Grade;
import platform.education.generalTeachingAffair.model.School;
import platform.education.generalTeachingAffair.model.SchoolTerm;
import platform.education.generalTeachingAffair.model.SchoolTermCurrent;
import platform.education.generalTeachingAffair.model.Student;
import platform.education.generalTeachingAffair.model.SubjectGrade;
import platform.education.generalTeachingAffair.model.Teacher;
import platform.education.generalTeachingAffair.model.Team;
import platform.education.generalTeachingAffair.model.TeamTeacher;
import platform.education.generalTeachingAffair.service.GradeService;
import platform.education.generalTeachingAffair.service.SchoolService;
import platform.education.generalTeachingAffair.service.SchoolTermCurrentService;
import platform.education.generalTeachingAffair.service.SchoolTermService;
import platform.education.generalTeachingAffair.service.StudentService;
import platform.education.generalTeachingAffair.service.SubjectGradeService;
import platform.education.generalTeachingAffair.service.TeacherService;
import platform.education.generalTeachingAffair.service.TeamService;
import platform.education.generalTeachingAffair.service.TeamTeacherService;
import platform.education.generalTeachingAffair.vo.StudentCondition;
import platform.education.generalTeachingAffair.vo.TeacherCondition;
import platform.education.generalTeachingAffair.vo.TeamTeacherCondition;
import platform.education.generalTeachingAffair.vo.TeamTeacherVo;
import platform.education.rest.common.ResponseError;
import platform.education.rest.common.ResponseInfo;
import platform.education.rest.common.ResponseVo;
import platform.education.rest.common.constants.CommonCode;
import platform.education.rest.common.constants.SysContants;
import platform.education.rest.user.service.MesosphereUserRestService;
import platform.education.rest.user.service.contants.SchoolRoles;
import platform.education.rest.user.service.vo.MesosphereSubjectInfo;
import platform.education.rest.user.service.vo.MesosphereTeamInfo;
import platform.education.rest.user.service.vo.MesosphereUserInfo;
import platform.education.rest.util.DESHelper;
import platform.education.user.model.AppEdition;
import platform.education.user.model.Group;
import platform.education.user.model.Profile;
import platform.education.user.model.Role;
import platform.education.user.model.User;
import platform.education.user.model.UserBinding;
import platform.education.user.service.AppEditionService;
import platform.education.user.service.GroupService;
import platform.education.user.service.ProfileService;
import platform.education.user.service.RoleService;
import platform.education.user.service.UserBindingService;
import platform.education.user.service.UserRoleService;
import platform.education.user.service.UserService;
import platform.education.user.utils.holder.PwdEncoderHolder;
import platform.education.user.vo.UserBindingCondition;
import platform.service.storage.holder.FileServiceHolder;

/**
 * 登录接口中间层
 * @author pantq
 *创建时间：2017-11-02 10:20
 */
public class MesosphereUserRestServiceImpl implements MesosphereUserRestService {

	@Autowired
	@Qualifier("userService")
	private UserService userService;
	@Autowired
	@Qualifier("profileService")
	private ProfileService profileService;
	
	@Autowired
	@Qualifier("userBindingService")
	private UserBindingService userBindingService;
	
	@Autowired
	@Qualifier("userRoleService")
	private UserRoleService userRoleService;
	
	@Autowired
    @Qualifier("roleService")
    private RoleService roleService;	
	
	@Autowired
    @Qualifier("groupService")
    private GroupService groupService;

	@Autowired
    @Qualifier("schoolTermCurrentService")
    private SchoolTermCurrentService schoolTermCurrentService;
	
	@Autowired
    @Qualifier("teamService")
    private TeamService teamService;
	
	@Autowired
    @Qualifier("gradeService")
    private GradeService gradeService;
	
	@Autowired
    @Qualifier("subjectGradeService")
    private SubjectGradeService subjectGradeService;
	@Autowired
    @Qualifier("teamTeacherService")
    private TeamTeacherService teamTeacherService;
	
	@Autowired
    @Qualifier("studentService")
    private StudentService studentService;
	
	@Autowired
    @Qualifier("teacherService")
    private TeacherService teacherService;
	
	@Autowired
    @Qualifier("schoolService")
    private SchoolService schoolService;
	
	@Autowired
	  @Qualifier("appEditionService")
	  private AppEditionService appEditionService;

	@Resource
	 private  SchoolTermService  schoolTermService;

	private ResponseInfo info;

	
	@Override
	public Object mesosphereLogin(String username, String password, String appKey, String userType) {
		
		ResponseInfo info = new ResponseInfo();
		ResponseError responseError = new ResponseError();
		
		//为希沃appKey 做临时处理
		 AppEdition app = this.appEditionService.findByAppKey(appKey);
		    if (app == null) {
		      info = new ResponseInfo();
		      info.setDetail("appkey不存在,请确认");
		      info.setMsg("不存在该appKey");
		      info.setParam("appKey");
		      responseError.setResult("020101");
		      responseError.setInfo(info);
		      return responseError;
		    }else if("maiqituo#qyjx#educloud#mobile#seewo".equals(appKey)) {
		    	 String decryptPassword = "";
				try {
					//DES解密
					decryptPassword = DESHelper.getInstance().decode(password);
					//系统密码加密
					password = PwdEncoderHolder.getInstance().getPwdEncoder().encodePassword(decryptPassword);
				} catch (Exception e) {
					info = new ResponseInfo();
				      info.setDetail("DES解密失败");
				      info.setMsg("DES解密失败");
				      info.setParam("DES解密失败");
				      responseError.setResult("020101");
				      responseError.setInfo(info);
				      e.printStackTrace();
				      return responseError;
				}
		    }
		    
		    Map<String,Object> result = validateUser(username,password,userType);
			String code = "";
			if(result != null && result.get("code") != null) {
				code = result.get("code")+"";
				
			}
		
		if(code.equals(CommonCode.SUCCESS)) {//成功
			Integer userId = null;
			if(result != null && result.get("userId") != null) {
				userId = Integer.valueOf(result.get("userId")+"");
			} 

			MesosphereUserInfo mesosphereUserInfo = new  MesosphereUserInfo();
			mesosphereUserInfo.setUserId(userId);
			Profile profile = this.profileService.findByUserId(userId);
			String icon =  "https://www.studyo.cn/res/images/no_pic.jpg";
			if (profile != null) {
				mesosphereUserInfo.setName(profile.getName());
				if(icon != null && !"".equals(profile.getIcon())) {
					icon = FileServiceHolder.getInstance().getFileService().relativePath2HttpUrlByUUID(profile.getIcon());
				}
			}
			mesosphereUserInfo.setIconUrl(icon);
			if("1".equals(userType)) { //教师
				mesosphereUserInfo = getTeacherInfo(mesosphereUserInfo, appKey);
			}else if("2".equals(userType)){ //学生
				mesosphereUserInfo = getStudentInfo(mesosphereUserInfo);
			}
			
			
			//希沃只要是教师身份就让进入 希沃业务！
			if(!"maiqituo#qyjx#educloud#mobile#seewo".equals(appKey)) {
				if(mesosphereUserInfo.getTeamList() == null || mesosphereUserInfo.getTeamList().size() == 0) {
					info.setDetail("该用户的班级信息错误，详情请联系管理员。");
					info.setMsg("该用户的班级信息错误，详情请联系管理员。");
					info.setParam("teamList");
					return new ResponseError(CommonCode.$INCOMPLETE_NOT_TEAM,info);
				}
			}
			
			mesosphereUserInfo.setIconUrl(icon);
			return new ResponseVo<MesosphereUserInfo>("0",mesosphereUserInfo);
			
		}else if(code.equals(CommonCode.U$USERACCOUNT_NOT_IN_USE)) {
			info.setDetail("用户账号已失效");
			info.setMsg("用户账号失效");
			info.setParam("username");
			return new ResponseError(CommonCode.U$USERACCOUNT_NOT_IN_USE,info);
		}else if(code.equals(CommonCode.U$USERACCOUNT_LOCKED)) {//用户账号已锁定
			info.setDetail("用户账号已锁定");
			info.setMsg("用户账号锁定");
			info.setParam(username);
			return new ResponseError(CommonCode.U$USERACCOUNT_LOCKED,info);
		}else if(code.equals(CommonCode.U$USERACCOUNT_HAS_EXPIRED)) {
			
			info.setDetail("用户账号已经过期");
			info.setMsg("用户账号已经过期");
			info.setParam(username);
			return new ResponseError(CommonCode.U$USERACCOUNT_HAS_EXPIRED,info);
		}else if(code.equals(CommonCode.U$PASSWORD_ERROR)) {
			
			info.setDetail("密码错误");
			info.setMsg("密码错误");
			info.setParam("password");
			return new ResponseError(CommonCode.U$PASSWORD_ERROR,info);
		}else if(code.equals(CommonCode.U$USERNAME_NOT_EXIST)) {
			info.setDetail("用户名不存在");
			info.setMsg("用户名不存在");
			info.setParam(username);
			return new ResponseError(CommonCode.U$USERNAME_NOT_EXIST,info);
		}
		
		/*switch (code) {
		case CommonCode.SUCCESS: //成功后逻辑
			MesosphereUserInfo mesosphereUserInfo = new  MesosphereUserInfo();
			mesosphereUserInfo.setUserId(userId);
			Profile profile = this.profileService.findByUserId(userId);
			if (profile != null) {
				mesosphereUserInfo.setName(profile.getName());
				String icon = profile.getIcon();
				if(icon != null && !"".equals(icon)) {
					icon = FileServiceHolder.getInstance().getFileService().relativePath2HttpUrlByUUID(profile.getIcon());
				}else {
					if(profile.getSex() != null && "1".equals(profile.getSex())) { //默认男头像
						icon = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1509610074424&di=7cc4caedd9fc0f04dec1f612e4bcb5d9&imgtype=0&src=http%3A%2F%2Fwww.cidianwang.com%2Ffile%2Fshufa%2Fxingshu%2F2014121210510119.jpg";//男
					}else if("2".equals(profile.getSex())){ //女头像
						icon = "https://image.baidu.com/search/detail?ct=503316480&z=0&ipn=d&word=%E5%A5%B3%E5%AD%97&step_word=&hs=0&pn=63&spn=0&di=146189325810&pi=0&rn=1&tn=baiduimagedetail&is=0%2C0&istype=2&ie=utf-8&oe=utf-8&in=&cl=2&lm=-1&st=-1&cs=2212027779%2C2708050393&os=672277787%2C3168376673&simid=18880111%2C849809719&adpicid=0&lpn=0&ln=1986&fr=&fmq=1509600011496_R&fm=detail&ic=0&s=undefined&se=&sme=&tab=0&width=&height=&face=undefined&ist=&jit=&cg=&bdtype=0&oriquery=&objurl=http%3A%2F%2Fpic.baike.soso.com%2Fp%2F20131127%2F20131127174431-989686995.jpg&fromurl=ippr_z2C%24qAzdH3FAzdH3Fkwthj_z%26e3Bf5f5_z%26e3Bv54AzdH3Fi8d99nn_z%26e3Bip4%3Ffr%3Dsm9a0099a&gsm=1e&rpstart=0&rpnum=0";//女
					}else { //未知
						icon = "https://image.baidu.com/search/detail?ct=503316480&z=0&ipn=d&word=%E4%BF%9D%E5%AF%86&step_word=&hs=0&pn=21&spn=0&di=168858493100&pi=0&rn=1&tn=baiduimagedetail&is=0%2C0&istype=2&ie=utf-8&oe=utf-8&in=&cl=2&lm=-1&st=-1&cs=3953231928%2C3670106211&os=2795084981%2C2180277691&simid=4214383249%2C732072645&adpicid=0&lpn=0&ln=1984&fr=&fmq=1509600083267_R&fm=result&ic=0&s=undefined&se=&sme=&tab=0&width=&height=&face=undefined&ist=&jit=&cg=&bdtype=0&oriquery=&objurl=http%3A%2F%2Fandroid-artworks.25pp.com%2Ffs08%2F2016%2F06%2F09%2F4%2F1_01a88991dabd72f48c343bc2235b143f_con.png&fromurl=ippr_z2C%24qAzdH3FAzdH3Fooo_z%26e3Bdcrr_z%26e3Bv54AzdH3Fwg165t1AzdH3F1jpwts_8a9mdblAzdH3F&gsm=0&rpstart=0&rpnum=0";//未知
					}
					
				}
				mesosphereUserInfo.setIconUrl(icon);
				
				if("1".equals(userType)) { //教师
					mesosphereUserInfo = getTeacherInfo(mesosphereUserInfo);
				}else if("2".equals(userType)){ //学生
					mesosphereUserInfo = getStudentInfo(mesosphereUserInfo);
				}
				return new ResponseVo<MesosphereUserInfo>("0",mesosphereUserInfo);
			}
			info.setDetail("yh_profile表数据异常");
			info.setMsg("yh_profile表数据异常");
			info.setParam("username");
			responseError.setResult(CommonCode.D$DATA_NOT_FOUND);
			break;
		
		case CommonCode.U$USERACCOUNT_NOT_IN_USE: //用户账号已失效
			
			info.setDetail("用户账号已失效");
			info.setMsg("用户账号失效");
			info.setParam("username");
			responseError.setResult(CommonCode.U$USERACCOUNT_NOT_IN_USE);
			responseError.setInfo(info);
			break;
			
		case CommonCode.U$USERACCOUNT_LOCKED: //用户账号已锁定
			
			info.setDetail("用户账号已锁定");
			info.setMsg("用户账号锁定");
			info.setParam("username");
			responseError.setResult(CommonCode.U$USERACCOUNT_LOCKED);
			responseError.setInfo(info);
			break;	
			
		case CommonCode.U$USERACCOUNT_HAS_EXPIRED: //用户账号已经过期
			info.setDetail("用户账号已经过期");
			info.setMsg("用户账号已经过期");
			info.setParam("username");
			responseError.setResult(CommonCode.U$USERACCOUNT_HAS_EXPIRED);
			responseError.setInfo(info);
			break;
			
		case CommonCode.U$PASSWORD_ERROR: //密码错误
			info.setDetail("密码错误");
			info.setMsg("密码错误");
			info.setParam("password");
			responseError.setResult(CommonCode.U$PASSWORD_ERROR);
			responseError.setInfo(info);
			break;	

		default:
			
			info.setDetail("用户名不存在");
			info.setMsg("用户名不存在");
			info.setParam("username");
			responseError.setResult(CommonCode.U$USERNAME_NOT_EXIST);
			responseError.setInfo(info);
			break;
		}*/
		
		
		return responseError;
	}
	
	/**
	 * 获取学生班级，学科等信息
	 * @author pantq
	 * @param mesosphereUserInfo
	 * @return
	 */
	private MesosphereUserInfo getStudentInfo(MesosphereUserInfo mesosphereUserInfo) {
		
		List<MesosphereTeamInfo> mesosphereTeamInfoList = new ArrayList<MesosphereTeamInfo>();
    	Integer userId = mesosphereUserInfo.getUserId();
        Student student = this.studentService.findStudentByUserId(userId);
        Team team = teamService.findCurrentTeamOfStudent(userId, student.getSchoolId());

        if(student != null) {
        	Integer schoolId = student.getSchoolId();
        	if(schoolId != null) {
        		School school = schoolService.findSchoolById(schoolId);
        		if(school != null) {
        			mesosphereUserInfo.setSchoolName(school.getName());
        		}
        	}
        	mesosphereUserInfo.setName(student.getName());
        	mesosphereUserInfo.setSchoolId(schoolId);
        	MesosphereTeamInfo mesosphereTeamInfo = new MesosphereTeamInfo();
        	mesosphereTeamInfo.setTeamId(team.getId());
        	mesosphereTeamInfo.setTeamName(team.getName());
        	//Team team = this.teamService.findTeamById(student.getTeamId());
        	Grade grade = this.gradeService.findGradeById(team.getGradeId());
        	mesosphereTeamInfo.setSubjectList(getMesosphereSubjectInfoByTeamId(schoolId,grade.getId()));
        	mesosphereTeamInfoList.add(mesosphereTeamInfo);
        	mesosphereUserInfo.setTeamList(mesosphereTeamInfoList);
        }
        
	return mesosphereUserInfo;
}
	
	
	
	
	/**
	 * 获取教师班级，学科等信息
	 * @author pantq
	 * @param mesosphereUserInfo
	 * @return
	 */
	
	private MesosphereUserInfo getTeacherInfo(MesosphereUserInfo mesosphereUserInfo, String appKey) {
        	Integer userId = mesosphereUserInfo.getUserId();
            List<Integer> roleIds =  this.userRoleService.findRoleIdsByUserId(userId, 1);
            List<String> roleStrList = new ArrayList<String>();
            for (Integer roleId : roleIds) {
                Role role = this.roleService.findRoleById(roleId);
                if (role != null) {
                    //筛选角色
                    if(SchoolRoles.R$CLASS_MASTER.equals(role.getCode())
                            || SchoolRoles.R$PARENT.equals(role.getCode())
                            || SchoolRoles.R$SCHOOL_LEADER.equals(role.getCode())
                            || SchoolRoles.R$SCHOOL_MASTER.equals(role.getCode())
                            || SchoolRoles.R$STUDENT.equals(role.getCode())
                            || SchoolRoles.R$SUBJECT_TEACHER.equals(role.getCode())
                            || SchoolRoles.R$TEACHER.equals(role.getCode())
							|| SchoolRoles.R$SCHOOL_MANAGER.equals(role.getCode())){
                        Integer groupId = role.getGroupId();
                        Group group = this.groupService.findGroupById(groupId);
                        if (group != null) {
                        	mesosphereUserInfo.setSchoolId(group.getOwnerId());
                        	mesosphereUserInfo.setSchoolName(group.getName());
                        }
                        roleStrList.add(role.getCode());
                    }
                }
            }
            List<MesosphereTeamInfo> mesosphereTeamInfoList = new ArrayList<MesosphereTeamInfo>();
            Integer schoolId = mesosphereUserInfo.getSchoolId() ;
            mesosphereUserInfo.setRoleStrList(roleStrList);
            String name = mesosphereUserInfo.getName() ;
			if(name == null || "".equals(name)) {
				Teacher teacher = this.teacherService.findOfUser(schoolId, userId);
				mesosphereUserInfo.setName(teacher.getName());
			}
			  Integer teacherType=0;
			   SchoolTerm schoolTerm= schoolTermService.findSchoolYearByToday(schoolId, new Date());
			   if(schoolTerm!=null){
				   int count1=0;
				   int count2=0;
				   TeamTeacherCondition ttCondition=new TeamTeacherCondition();
				   ttCondition.setSchoolYear(schoolTerm.getSchoolYear());
				   ttCondition.setUserId(userId);
				   ttCondition.setDelete(false);
			    	List<TeamTeacher>ttlist= teamTeacherService.findTeamTeacherByCondition(ttCondition, null, null);
			    	for(TeamTeacher tt:ttlist){
			    		if(tt.getType().intValue()==1){
			    			count1=1;
			    		}
			    		if(tt.getType().intValue()==2){
			    			count2=2;
			    		}
			    	}
				    if(count1==1&&count2==2){
				    	teacherType=3;
				    }else if(count1==1){
				    	teacherType=1;
				    }else if(count2==2){
				    	teacherType=2;
				    }
			   }
			   mesosphereUserInfo.setTeacherType(teacherType);
            if(roleStrList.contains(SchoolRoles.R$SCHOOL_LEADER) || roleStrList.contains(SchoolRoles.R$SCHOOL_MANAGER)) {//备课组老师
            	//整个学校的班级
            	//根据学校ID找到当前学年
            	//找到当前学年学校所有班级
            	
            	SchoolTermCurrent schoolTermCurrent = schoolTermCurrentService.findSchoolTermCurrentBySchoolId(schoolId);
            	List<Team> teamlist =  teamService.findCurrentTeamOfSchoolAndYearNotExistCurrentClass(schoolId, schoolTermCurrent.getSchoolYear(), null);
            	if(teamlist != null && teamlist.size() >0) {
            		for(Team team :teamlist) {
            			MesosphereTeamInfo mesosphereTeamInfo = new MesosphereTeamInfo();
            			mesosphereTeamInfo.setTeamId(team.getId());
            			mesosphereTeamInfo.setTeamName(team.getName());
            			//通过班ID查找对应的科目
            			List<MesosphereSubjectInfo> mesosphereSubjectInfos = getMesosphereSubjectInfoByTeamId(schoolId,team.getGradeId());
            			if(mesosphereSubjectInfos != null && mesosphereSubjectInfos.size() >0) {
            				
            				mesosphereTeamInfo.setSubjectList(getMesosphereSubjectInfoByTeamId(schoolId,team.getGradeId()));
            				mesosphereTeamInfoList.add(mesosphereTeamInfo);
            			}
            		}
            	}
            	
            }else if(roleStrList.contains(SchoolRoles.R$SUBJECT_TEACHER)) { //任课组老师
            	//所任教的班级
            	SchoolTermCurrent schoolTermCurrent = schoolTermCurrentService.findSchoolTermCurrentBySchoolId(schoolId);
            	TeamTeacherCondition teamTeacherCondition = new TeamTeacherCondition();
            	teamTeacherCondition.setUserId(userId);
            	teamTeacherCondition.setType(2);
            	teamTeacherCondition.setSchoolYear(schoolTermCurrent.getSchoolYear());
            	List<TeamTeacherVo> teamTeacherList =  teamTeacherService.findTeamTeacherVoByConditionGroupByTeamId(teamTeacherCondition);
            	
            	if(teamTeacherList != null && teamTeacherList.size() >0) {
            		for(TeamTeacherVo teamTeacher :teamTeacherList) {
            			MesosphereTeamInfo mesosphereTeamInfo = new MesosphereTeamInfo();
            			mesosphereTeamInfo.setTeamId(teamTeacher.getTeamId());
            			mesosphereTeamInfo.setTeamName(teamTeacher.getTeamName());
            			
            			List<MesosphereSubjectInfo> mesosphereSubjectInfos = getMesosphereSubjectInfoByTeamId(schoolId,teamTeacher.getGradeId());
            			if(mesosphereSubjectInfos != null && mesosphereSubjectInfos.size() > 0) {
            				mesosphereTeamInfo.setSubjectList(mesosphereSubjectInfos);
            				mesosphereTeamInfoList.add(mesosphereTeamInfo);
            			}
            			
            		}
            	}
            	
            } else if (roleStrList.contains(SchoolRoles.R$CLASS_MASTER)) {
				SchoolTermCurrent schoolTermCurrent = schoolTermCurrentService.findSchoolTermCurrentBySchoolId(schoolId);
            	TeamTeacherCondition teamTeacherCondition = new TeamTeacherCondition();
            	teamTeacherCondition.setUserId(userId);
            	teamTeacherCondition.setType(1);
            	teamTeacherCondition.setSchoolYear(schoolTermCurrent.getSchoolYear());
            	List<TeamTeacherVo> teamTeacherList =  teamTeacherService.findTeamTeacherVoByConditionGroupByTeamId(teamTeacherCondition);
				if (teamTeacherList != null && teamTeacherList.size() > 0) {
					for (TeamTeacherVo teamTeacher : teamTeacherList) {
						MesosphereTeamInfo mesosphereTeamInfo = new MesosphereTeamInfo();
						mesosphereTeamInfo.setTeamId(teamTeacher.getTeamId());
						mesosphereTeamInfo.setTeamName(teamTeacher.getTeamName());

						List<MesosphereSubjectInfo> mesosphereSubjectInfos = getMesosphereSubjectInfoByTeamId(schoolId,teamTeacher.getGradeId());
						if(mesosphereSubjectInfos != null && mesosphereSubjectInfos.size() > 0) {
							mesosphereTeamInfo.setSubjectList(mesosphereSubjectInfos);
							mesosphereTeamInfoList.add(mesosphereTeamInfo);
						}
					}
				}
			}
            mesosphereUserInfo.setTeamList(mesosphereTeamInfoList);
		return mesosphereUserInfo;
	}
	
	/**
	 * 根据学校ID，年级ID获取当前班级的科目列表
	 * @author pantq
	 * @return
	 */
	private List<MesosphereSubjectInfo> getMesosphereSubjectInfoByTeamId(Integer schoolId,Integer gradeId){
	
		List<MesosphereSubjectInfo> mesosphereSubjectInfoList = new ArrayList<MesosphereSubjectInfo>();
		MesosphereSubjectInfo mesosphereSubjectInfo1 = new MesosphereSubjectInfo();
		mesosphereSubjectInfo1.setCode("-1");
		mesosphereSubjectInfo1.setName("全部科目");
		mesosphereSubjectInfoList.add(mesosphereSubjectInfo1);
		//1. 通过班级找到年级
		Grade grade = this.gradeService.findGradeById(gradeId);
		List<SubjectGrade> subjectGradeList = subjectGradeService.findSubjectGradeByGradeCode(schoolId, grade.getUniGradeCode());
		if(subjectGradeList != null && subjectGradeList.size()>0) {
			for(SubjectGrade subjectGrade :subjectGradeList) {
				MesosphereSubjectInfo mesosphereSubjectInfo = new MesosphereSubjectInfo();
				mesosphereSubjectInfo.setCode(subjectGrade.getSubjectCode());
				mesosphereSubjectInfo.setName(subjectGrade.getSubjectName());
				mesosphereSubjectInfoList.add(mesosphereSubjectInfo);
			}
		}
		return mesosphereSubjectInfoList;
	}
	
	
	/**
	 * 验证用户登录
	 * @author pantq
	 * @param username
	 * @param password
	 * @return
	 */
	private Map<String,Object> validateUser(String username, String password,String userType) {
		Map<String,Object> result = new HashMap<String,Object>();
		String code = CommonCode.U$USERNAME_NOT_EXIST;
		//判断绑定表是否有记录
		UserBinding ub = this.userBindingService.findByBindingName(username);
		if(ub != null) {
			Integer userId = ub.getUserId();
			User user = this.userService.findUserById(userId);
			
			if("1".equals(userType)) { //判断教师
				TeacherCondition teacherCondition = new TeacherCondition();
				teacherCondition.setUserId(userId);
				teacherCondition.setIsDelete(false);
				List<Teacher> teacher = this.teacherService.findTeacherByCondition(teacherCondition, null, null);
				if(teacher == null || teacher.size() ==0) { //用户不存在
					result.put("code", code);
					return result;
				}
			}else if("2".equals(userType)){ //判断学生
				StudentCondition studentCondition = new StudentCondition();
				studentCondition.setUserId(userId);
				studentCondition.setIsDelete(false);
				//studentCondition.setStudyState(studyState);
				List<Student> studentList = this.studentService.findStudentByCondition(studentCondition, null, null);
				if(studentList == null || studentList.size() == 0) {
					result.put("code", code);
					return result;
				}
			}
			
			
			if(user != null) {
				//2.验证密码是否正确
				if(user.getPassword().equals(password)) { //密码正确
					if(user.getValidDate() != null && user.getValidDate().before(new Date())){ //判断账号是否过期
						code = CommonCode.U$USERACCOUNT_HAS_EXPIRED;
						}else if("0".equals(user.getState())){ //正常状态 
							result.put("userId", userId);
							code = CommonCode.SUCCESS;
						}else if("1".equals(user.getState())) {
							code = CommonCode.U$USERACCOUNT_NOT_IN_USE;
						}else if("2".equals(user.getState())) {
							code = CommonCode.U$USERACCOUNT_LOCKED;
						}
					}else { //密码错误
						code = CommonCode.U$PASSWORD_ERROR;
						
					}
				}
			}
		result.put("code", code);
		return result ;
		
	}

	@Override
	public Object teachingLogin(String username, String password,
			String appKey) {

		
		ResponseInfo info = new ResponseInfo();
		ResponseError responseError = new ResponseError();
		
		//为希沃appKey 做临时处理
		 AppEdition app = this.appEditionService.findByAppKey(appKey);
		    if (app == null) {
		      info = new ResponseInfo();
		      info.setDetail("appkey不存在,请确认");
		      info.setMsg("不存在该appKey");
		      info.setParam("appKey");
		      responseError.setResult("020101");
		      responseError.setInfo(info);
		      return responseError;
		    }else if("maiqituo#qyjx#educloud#mobile#seewo".equals(appKey)) {
		    	 String decryptPassword = "";
				try {
					//DES解密
					decryptPassword = DESHelper.getInstance().decode(password);
					//系统密码加密
					password = PwdEncoderHolder.getInstance().getPwdEncoder().encodePassword(decryptPassword);
				} catch (Exception e) {
					info = new ResponseInfo();
				      info.setDetail("DES解密失败");
				      info.setMsg("DES解密失败");
				      info.setParam("DES解密失败");
				      responseError.setResult("020101");
				      responseError.setInfo(info);
				      e.printStackTrace();
				      return responseError;
				}
		    }
		    
		    Map<String,Object> result = validateUserOfNew(username,password);
			String code = "";
			if(result != null && result.get("code") != null) {
				code = result.get("code")+"";
				
			}
		
		if(code.equals(CommonCode.SUCCESS)) {//成功
			List<MesosphereUserInfo> list=new ArrayList<MesosphereUserInfo>();
			Map<Integer,Integer>userIds=(Map<Integer, Integer>) result.get("userMap");
			for(Integer userId:userIds.keySet()){
				MesosphereUserInfo mesosphereUserInfo = new  MesosphereUserInfo();
				mesosphereUserInfo.setUserId(userId);
				User user=userService.findUserById(userId);
				Profile profile = this.profileService.findByUserId(userId);
				String icon =  SysContants.APP_DEFAULT_USERICON;
				if (profile != null) {
					mesosphereUserInfo.setName(profile.getName());
					if(icon != null && !"".equals(profile.getIcon())) {
						icon = FileServiceHolder.getInstance().getFileService().relativePath2HttpUrlByUUID(profile.getIcon());
					}
				}
				mesosphereUserInfo.setIconUrl(icon);
				mesosphereUserInfo = getTeacherInfo(mesosphereUserInfo, appKey);
				mesosphereUserInfo.setUserName(user.getUserName());
				mesosphereUserInfo.setIconUrl(icon);
				if(mesosphereUserInfo.getTeamList().size()>0){
					list.add(mesosphereUserInfo);
				}
			}
			Map<String,Object> map=new HashMap<String, Object>();
			String phone="";
			if(Integer.valueOf(result.get("isTelphone").toString()) ==1){
				phone=username;
			}else{
				UserBindingCondition ubCondition=new UserBindingCondition();
				ubCondition.setBindingType(1);
				ubCondition.setUserId(list.get(0).getUserId());
				List<UserBinding>ubList=userBindingService.findUserBindingByCondition(ubCondition, null, null);
				if(ubList.size()>0){
					phone=ubList.get(0).getBindingName();
				}
			}
			map.put("isTelphone", result.get("isTelphone"));
			map.put("phoneNumber", phone);
			map.put("teacherList", list);
			return new ResponseVo<Map<String,Object>>("0",map);
			
		}else if(code.equals(CommonCode.U$USERACCOUNT_NOT_IN_USE)) {
			info.setDetail("用户账号已失效");
			info.setMsg("用户账号失效");
			info.setParam("username");
			return new ResponseError(CommonCode.U$USERACCOUNT_NOT_IN_USE,info);
		}else if(code.equals(CommonCode.U$USERACCOUNT_LOCKED)) {//用户账号已锁定
			info.setDetail("用户账号已锁定");
			info.setMsg("用户账号锁定");
			info.setParam(username);
			return new ResponseError(CommonCode.U$USERACCOUNT_LOCKED,info);
		}else if(code.equals(CommonCode.U$USERACCOUNT_HAS_EXPIRED)) {
			
			info.setDetail("用户账号已经过期");
			info.setMsg("用户账号已经过期");
			info.setParam(username);
			return new ResponseError(CommonCode.U$USERACCOUNT_HAS_EXPIRED,info);
		}else if(code.equals(CommonCode.U$PASSWORD_ERROR)) {
			
			info.setDetail("密码错误");
			info.setMsg("密码错误");
			info.setParam("password");
			return new ResponseError(CommonCode.U$PASSWORD_ERROR,info);
		}else if(code.equals(CommonCode.U$USERNAME_NOT_EXIST)) {
			info.setDetail("用户名不存在");
			info.setMsg("用户名不存在");
			info.setParam(username);
			return new ResponseError(CommonCode.U$USERNAME_NOT_EXIST,info);
		}
		return responseError;
	
	}
	/**
	 * 验证用户登录
	 * @author pantq
	 * @param username
	 * @param password
	 * @return
	 */
	private Map<String,Object> validateUserOfNew(String username, String password) {
		Map<String,Object> result = new HashMap<String,Object>();
		String code = CommonCode.U$USERNAME_NOT_EXIST;
		UserBindingCondition ubCondition=new UserBindingCondition();
		ubCondition.setBindingName(username);
		List<UserBinding> ublist=new ArrayList<UserBinding>();
		ublist=userBindingService.findUserBindingByCondition(ubCondition, null, null);
		Boolean b=false;
		Map<Integer,Integer>userMap=new HashMap<Integer, Integer>();
		if(ublist.size()>0){
			Integer userIdf = ublist.get(0).getUserId();
			User userF = this.userService.findUserById(userIdf);
			if(userF!=null&&userF.getPassword().equals(password)){
				for(UserBinding ub:ublist){
					Integer userId = ub.getUserId();
					if(ub.getBindingType().intValue()==1){
						b=true;
					}
					User user = this.userService.findUserById(userId);
					TeacherCondition teacherCondition = new TeacherCondition();
					teacherCondition.setUserId(userId);
					teacherCondition.setIsDelete(false);
					List<Teacher> teacher = this.teacherService.findTeacherByCondition(teacherCondition, null, null);
					if(user != null) {
						if("0".equals(user.getState())&&teacher.size()>0&&!(user.getValidDate() != null && user.getValidDate().before(new Date()))){ //正常状态 
								userMap.put(userId, userId);
								code = CommonCode.SUCCESS;
						}
					}
				}
			}else{
				code = CommonCode.U$PASSWORD_ERROR;
			}
		}
		if(userMap.size()==0){
			 code = CommonCode.U$USERNAME_NOT_EXIST;
		}
		result.put("isTelphone", b?1:0);
		result.put("userMap", userMap);
		result.put("code", code);
		return result ;
		
	}
}
