package platform.szxyzxx.services.teach.service.impl;



import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.task.TaskExecutor;

import platform.education.enrollStudent.model.NewStudent;
import platform.education.enrollStudent.service.NewStudentService;
import platform.education.generalTeachingAffair.contants.StudentAlterationContants;
import platform.education.generalTeachingAffair.model.Student;
import platform.education.generalTeachingAffair.model.UserDetailInfo;
import platform.education.generalTeachingAffair.service.StudentService;
import platform.education.generalTeachingAffair.service.TeamStudentService;
import platform.szxyzxx.services.teach.service.TeachService;
import platform.szxyzxx.web.common.contants.SysContants;
/**
 * 教学教务
 * @author zhoujin
 *
 */
public class TeachServiceImpl implements TeachService {

	private Logger log = LoggerFactory.getLogger(getClass());
	//学生
	private StudentService studentService;
	//班级学生
	private TeamStudentService teamStudentService;
	
	@Resource(name = "addSchool_taskExecutor")
    private TaskExecutor taskExecutor;
	
	//新生注册
	private NewStudentService newStudentService;
	
	public NewStudentService getNewStudentService() {
		return newStudentService;
	}

	public void setNewStudentService(NewStudentService newStudentService) {
		this.newStudentService = newStudentService;
	}


	public StudentService getStudentService() {
		return studentService;
	}

	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}

	public TeamStudentService getTeamStudentService() {
		return teamStudentService;
	}

	public void setTeamStudentService(TeamStudentService teamStudentService) {
		this.teamStudentService = teamStudentService;
	}
	
	/***
	 * 保存新生注册信息
	 * @return
	 */
	@Override
	public UserDetailInfo saveNewStudentInfo(NewStudent newStudent) throws Exception{
		UserDetailInfo udi = null;
		try{
			/** 
			 * 2016-3-14
			 * 修改：是否可以正常执行C3.1创建学生的流程  如果可以创建  则修改状态等信息
			 *     如果 不可以  则将错误信息返回给页面  并提示
			 * 2016-9-5
			 * 修改：接口  saveUserInfo 新增一个参数  isBindingMobile
			 * 		参数为false 或者为空时 ，手机号不绑定账号
			 * 		参数为true时，绑定手机号为登陆账号
			 */
			Boolean isBindingMobile = false;
			NewStudent ns = newStudentService.findNewStudentById(newStudent.getId());
			
			UserDetailInfo userDetailInfo = new UserDetailInfo();
			userDetailInfo.setRole(newStudent.getRole());
			userDetailInfo.setName(ns.getName());
			userDetailInfo.setStudentNum(ns.getStudentNum());
			userDetailInfo.setStudentType(ns.getStudentType());
			userDetailInfo.setSchoolId(ns.getSchoolId());
			userDetailInfo.setEnglishName(ns.getEnglishName());
			userDetailInfo.setSex(ns.getSex());
			userDetailInfo.setCertificateType(ns.getIdCardType());
			userDetailInfo.setCertificateNum(ns.getIdCardNumber());
			userDetailInfo.setState("31");
			userDetailInfo.setNationality(ns.getNationality());
			userDetailInfo.setNation(ns.getRace());
			userDetailInfo.setBirthDate(ns.getBirthDate());
			userDetailInfo.setNativePlace(ns.getNativePlace());
			//religiousBelief
			userDetailInfo.setBirthPlace(ns.getBirthPlace());
			userDetailInfo.setBirthDate(ns.getBirthDate());
			userDetailInfo.setRegister(ns.getResidenceType());
			userDetailInfo.setRegisterPlace(ns.getResidenceAddress());
			userDetailInfo.setNowAddress(ns.getAddress());
			userDetailInfo.setLiveAddress(ns.getResideAddress());
			userDetailInfo.setPolitical(ns.getPoliticalStatus());
			userDetailInfo.setReligiousBelief(ns.getReligion());
			userDetailInfo.setTelephone(ns.getTelephone());
			userDetailInfo.setEmail(ns.getEmail());
			//userDetailInfo.setEntityId(newStudent.getEntityId());
			udi = studentService.saveUserInfo(userDetailInfo,SysContants.DEFAULT_PASSWORD,SysContants.USER_TYPE_STUDENT, SysContants.SYSTEM_APP_ID,isBindingMobile);
			
			//注册完成之后删除新生信息
			if(udi.getMessage() == null || "".equals(udi.getMessage())){
				ns.setStudentId(udi.getStudentId());
				ns.setUserName(udi.getUsername());
				ns.setRegionStates("2");
				newStudentService.modify(ns);
			}else{
				return udi;
			}
			
			//修改学生变动档案
			teamStudentService.addStudentAlteration(udi.getStudentId(), null, udi.getStudentTeamId(), StudentAlterationContants.TYPE_ZHAOSHENG);
			
			//将学生的teamStudent表中的班级ID置为空  等待下一次的分班
			if(udi.getStudentId() != null && !"".equals(udi.getStudentId())){
				Student student = studentService.findStudentById(udi.getStudentId());
				if(student != null){
					student.setTeamId(null);
					student.setTeamName(null);
					studentService.modify(student);
				}
			}
		}catch(Exception e){
			log.info("保存新生注册信息异常");
			e.printStackTrace();
			throw e;
		}
		return udi;
	}
	
	public void modifyNewStudentInfo(UserDetailInfo userDetailInfo) throws Exception{
		try{
			NewStudent newStudent = newStudentService.findNewStudentById(userDetailInfo.getId());
			newStudent.setName(userDetailInfo.getName());
			newStudent.setStudentNum(userDetailInfo.getStudentNum());
			newStudent.setStudentType(userDetailInfo.getStudentType());
			newStudent.setEnglishName(userDetailInfo.getEnglishName());
			newStudent.setSex(userDetailInfo.getSex());
			newStudent.setIdCardType(userDetailInfo.getCertificateType());
			newStudent.setIdCardNumber(userDetailInfo.getCertificateNum());
			newStudent.setReadingState(userDetailInfo.getState());
			newStudent.setNationality(userDetailInfo.getNationality());
			newStudent.setRace(userDetailInfo.getNation());
			newStudent.setBirthDate(userDetailInfo.getBirthDate());
			newStudent.setNativePlace(userDetailInfo.getNativePlace());
			newStudent.setBirthPlace(userDetailInfo.getBirthPlace());
			newStudent.setResidenceType(userDetailInfo.getRegister());
			newStudent.setResidenceAddress(userDetailInfo.getRegisterPlace());
			newStudent.setAddress(userDetailInfo.getNowAddress());
			newStudent.setResideAddress(userDetailInfo.getLiveAddress());
			newStudent.setPoliticalStatus(userDetailInfo.getPolitical());
			newStudent.setReligion(userDetailInfo.getReligiousBelief());
			newStudent.setTelephone(userDetailInfo.getTelephone());
			newStudent.setEmail(userDetailInfo.getEmail());
			newStudent.setEntrollSchoolDate(userDetailInfo.getEnterDate());
			newStudent.setIsOnlyChild(userDetailInfo.getIsOnlyChild() ? "1" : "0");
			newStudentService.modify(newStudent);
			
			Student student = studentService.findStudentById(userDetailInfo.getStudentId());
			if(student != null){
				userDetailInfo.setPersonId(student.getPersonId());
				studentService.modfiyUserInfo(userDetailInfo, false);
			}
			/**
			 * 2016-3-15 修改：招生的编辑 只修改newStudent表  因为这是还没有注册之前的数据   当注册之后会将数据删除   不会再有编辑
			 */
//			Student student = this.studentService.findStudentById(ns.getStudentId());
//			userDetailInfo.setPersonId(student.getPersonId());
//			userDetailInfo.setUserId(student.getUserId());
//			modfiyUserInfo(userDetailInfo);
		}catch(Exception e){
			log.info("修改注册学生信息异常");
			throw e;
		}
		
	}
	
	
	
	
}
