package platform.education.rest.bp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import platform.education.clazz.model.BpBwSchoolCard;
import platform.education.clazz.service.BpBwSchoolCardService;
import platform.education.clazz.service.BpBwSignageService;
import platform.education.generalTeachingAffair.model.SchoolUser;
import platform.education.generalTeachingAffair.model.Student;
import platform.education.generalTeachingAffair.service.PersonService;
import platform.education.generalTeachingAffair.service.SchoolUserService;
import platform.education.generalTeachingAffair.service.StudentService;
import platform.education.generalTeachingAffair.service.TeamService;
import platform.education.generalTeachingAffair.vo.SchoolUserCondition;
import platform.education.rest.bp.service.BpPersonalSearchRestService;
import platform.education.rest.bp.service.BpSyllabusRestService;
import platform.education.rest.bp.service.contants.BpCommonConstants;
import platform.education.rest.bp.service.util.ValidateUtil;
import platform.education.rest.bp.service.vo.PersonalSearchUserInfoVo;
import platform.education.rest.common.ResponseError;
import platform.education.rest.common.ResponseInfo;
import platform.education.rest.common.ResponseVo;
import platform.education.rest.common.constants.CommonCode;
import platform.education.rest.util.ImgUtil;
import platform.education.user.service.AppEditionService;

import java.util.List;

public class BpPersonalSearchRestServiceImpl implements BpPersonalSearchRestService {
	
	@Autowired
	@Qualifier("appEditionService")
	private AppEditionService appEditionService;
	
	@Autowired
	@Qualifier("personService")
	protected PersonService personService;
	
	@Autowired
	@Qualifier("bpBwSignageService")
	private BpBwSignageService bpBwSignageService;
	
	@Autowired
	@Qualifier("bpBwSchoolCardService")
	private BpBwSchoolCardService bpBwSchoolCardService;
	
	@Autowired
	@Qualifier("studentService")
	private StudentService studentService;
	
	@Autowired
	@Qualifier("teamService")
	private TeamService teamService;
	
	@Autowired
	@Qualifier("schoolUserService")
	private SchoolUserService schoolUserService;
	
	@Autowired
	@Qualifier("bpSyllabusRestService")
	private BpSyllabusRestService bpSyllabusRestService;

	@Override
	public Object checkUserInfo(String cardId, Integer schoolId, String appKey, String signage) {
		try {
			Object checkResult = ValidateUtil.checkAppKeyAndSignae(appKey, signage, appEditionService, bpBwSignageService);
			if ( checkResult != null ) {
				return checkResult;
			}
			/*if (StringUtils.isEmpty(cardId)) {
				return ResponseUtil.paramerIsNull("cardId:"+cardId);
			}
			if (StringUtils.isEmpty(schoolId)) {
				return ResponseUtil.paramerIsNull("schoolId:"+ schoolId);
			}*/
			
			BpBwSchoolCard bpBwSchoolCard = this.bpBwSchoolCardService.findBwSchoolCardByAccountId(cardId);
			if(bpBwSchoolCard == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("非法卡号");
				info.setMsg("非法卡号");
				return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL,info);
			}
			
			SchoolUserCondition schoolUserCondition = new SchoolUserCondition();
			schoolUserCondition.setUserId(bpBwSchoolCard.getUserId());
			schoolUserCondition.setIsDeleted(false);
			List<SchoolUser> schoolUserList = this.schoolUserService.findSchoolUserByCondition(schoolUserCondition, null, null);
			if(schoolUserList == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("接口异常");
				info.setMsg("接口异常");
				return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL,info);
			}
			SchoolUser schoolUser = schoolUserList.get(0);
			if(!schoolId.equals(schoolUser.getSchoolId())){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("非本校IC卡");
				info.setMsg("非本校IC卡");
				return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL,info);
			}		
			PersonalSearchUserInfoVo personalSearchUserInfoVo = new PersonalSearchUserInfoVo();
			personalSearchUserInfoVo.setUserType(bpBwSchoolCard.getUserType());
			personalSearchUserInfoVo.setUserId(bpBwSchoolCard.getUserId());	
			personalSearchUserInfoVo.setSchoolId(schoolUser.getSchoolId());
			Student student= this.studentService.findStudentByUserId(bpBwSchoolCard.getUserId());
			personalSearchUserInfoVo.setUserName(student.getName());
			String imgSrc = "";
			//默认头像
			String imgUrl = ImgUtil.getImgUrl(null);
			imgSrc = ImgUtil.getStudentIconUrl(student.getPersonId(), personService);
			personalSearchUserInfoVo.setUserIcon(imgSrc);
			if("".equals(imgSrc)){
				personalSearchUserInfoVo.setUserIcon(imgUrl);
			}
			return new ResponseVo<PersonalSearchUserInfoVo>("0", personalSearchUserInfoVo);
			
		} catch (Exception e) {
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("接口异常");
			info.setMsg("接口异常");
			return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL,info);
		}	
	}

	@Override
	public Object searchSyllabus(Integer schoolId, Integer userType, Integer userId, String appKey, String signage) {
		try {
			Object checkResult = ValidateUtil.checkAppKeyAndSignae(appKey, signage, appEditionService, bpBwSignageService);
			if ( checkResult != null ) {
				return checkResult;
			}
			/*if (StringUtils.isEmpty(schoolId)) {
				return ResponseUtil.paramerIsNull("schoolId:"+schoolId);
			}
			if (StringUtils.isEmpty(userType)) {
				return ResponseUtil.paramerIsNull("userType:"+ userType);
			}
			if (StringUtils.isEmpty(userId)) {
				return ResponseUtil.paramerIsNull("userId:"+ userId);
			}*/
			
			//List[] list = null;
			if(userType.equals( BpCommonConstants.USER_TYPE_STUDENT)){
				return this.bpSyllabusRestService.getStudentSyllabus(schoolId, null, userId, appKey, signage);
			}else if(userType.equals(BpCommonConstants.USER_TYPE_TEACHER)){
				return this.bpSyllabusRestService.getTeacherSyllabus(schoolId, null, userId, appKey, signage);
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("接口异常");
			info.setMsg("接口异常");
			return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL,info);
		}
	}
	

}
