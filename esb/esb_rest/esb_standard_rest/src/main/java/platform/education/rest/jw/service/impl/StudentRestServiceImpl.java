package platform.education.rest.jw.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.beanutils.PropertyUtils;
import org.mortbay.log.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.alibaba.fastjson.JSON;

import platform.education.generalTeachingAffair.model.JobControl;
import platform.education.generalTeachingAffair.model.Student;
import platform.education.generalTeachingAffair.service.FamilyMemberService;
import platform.education.generalTeachingAffair.service.JobControlService;
import platform.education.generalTeachingAffair.service.ParentStudentService;
import platform.education.generalTeachingAffair.service.StudentService;
import platform.education.generalTeachingAffair.vo.StudentArchiveComplete;
import platform.education.generalTeachingAffair.vo.StudentArchiveCompleteVo;
import platform.education.generalcode.service.JcCacheService;
import platform.education.rest.common.ResponseError;
import platform.education.rest.common.ResponseInfo;
import platform.education.rest.common.ResponseVo;
import platform.education.rest.common.constants.CommonCode;
import platform.education.rest.jw.service.StudentRestService;
import platform.education.rest.jw.service.vo.JobControlVo;
import platform.education.rest.jw.service.vo.ParentMess;
import platform.education.rest.jw.service.vo.StudentArchiveEdit;
import platform.education.rest.jw.service.vo.StudentArchiveInformation;
import platform.education.rest.util.ImgUtil;
import platform.education.rest.util.RegLevelUtil;
import platform.service.storage.holder.FileServiceHolder;


public class StudentRestServiceImpl implements StudentRestService{
	
	@Autowired
	@Qualifier("studentService")
	private StudentService studentService;
	
	@Autowired
	@Qualifier("jobControlService")
	private JobControlService jobControlService;
	
	@Autowired
	@Qualifier("jcCacheService")
	private JcCacheService jcCacheService;
	
	@Autowired
	@Qualifier("parentStudentService")
	private ParentStudentService parentStudentService;
	
	@Autowired
	@Qualifier("familyMemberService")
	private FamilyMemberService familyMemberService;
	
	@Override
	public Object getStudentComplete(Integer userId,Integer schoolId){
		try{
			if(userId == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("userId参数必填");
				info.setMsg("userId参数不能为空");
				info.setParam("userId");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			
			if(schoolId == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("schoolId参数必填");
				info.setMsg("schoolId参数不能为空");
				info.setParam("schoolId");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			
			Student student = studentService.findOfUser(schoolId, userId);
			
			if(student == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("找不到学生数据");
				info.setMsg("找不到学生数据");
				info.setParam("userId，schoolId");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			
			//获取学生完整学籍档案
			StudentArchiveComplete studentArchiveComplete = studentService.getStudentArchiveComplete(student.getId());
			getAllRegionName(studentArchiveComplete);
			
			if(studentArchiveComplete != null){
				String uuid = studentArchiveComplete.getBasic().getPhotoUuid();
				studentArchiveComplete.getBasic().setPhotoUrl(FileServiceHolder.getInstance().getFileService().relativePath2HttpUrlByUUID(uuid));
				return new ResponseVo<StudentArchiveComplete>("0", studentArchiveComplete);
			}else{
				ResponseInfo info = new ResponseInfo();
				info.setDetail("查不到学生档案数据");
				info.setMsg("查询结果为空");
				info.setParam("studentId");
				return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
			}
		}catch(NumberFormatException e){
			ResponseInfo info = new ResponseInfo();
			info.setDetail("studentId参数异常");
			info.setMsg("studentId参数异常");
			info.setParam("studentId");
			return new ResponseError(CommonCode.$PARAMETER_VALUE_CONTENT_ERROR, info);
		}
	}

	private void getAllRegionName(StudentArchiveComplete studentArchiveComplete) {
		if(studentArchiveComplete != null){
			if(studentArchiveComplete.getBasic() != null){
				String birthPlace = getRegionCode(studentArchiveComplete.getBasic().getBirthPlaceCode());
				if("".equals(birthPlace)){
					studentArchiveComplete.getBasic().setBirthPlaceCode(null);
				}else{
					studentArchiveComplete.getBasic().setBirthPlaceCode(studentArchiveComplete.getBasic().getBirthPlaceCode());
				}
				studentArchiveComplete.getBasic().setBirthPlace(birthPlace);
				
				String nativePlace = getRegionCode(studentArchiveComplete.getBasic().getNativePlaceCode());
				if("".equals(nativePlace)){
					studentArchiveComplete.getBasic().setNativePlaceCode(null);
				}else{
					studentArchiveComplete.getBasic().setNativePlaceCode(studentArchiveComplete.getBasic().getNativePlaceCode());
				}
				studentArchiveComplete.getBasic().setNativePlace(nativePlace);
			}
			if(studentArchiveComplete.getAssist() != null){
				String residenceAddress = getRegionCode(studentArchiveComplete.getAssist().getResidenceAddressCode());
				if("".equals(residenceAddress)){
					studentArchiveComplete.getAssist().setResidenceAddressCode(null);
				}else{
					studentArchiveComplete.getAssist().setResidenceAddressCode(studentArchiveComplete.getAssist().getResidenceAddressCode());
				}
				studentArchiveComplete.getAssist().setResidenceAddress(residenceAddress);
			}
			
			if(studentArchiveComplete.getParent() != null && studentArchiveComplete.getParent().getParentMess() != null 
					&& studentArchiveComplete.getParent().getParentMess().size()>0){
				for(int i=0; i<studentArchiveComplete.getParent().getParentMess().size(); i++){
					String pResidenceAddress = getRegionCode(studentArchiveComplete.getParent().getParentMess().get(i).getResidenceAddressCode());
					if("".equals(pResidenceAddress)){
						studentArchiveComplete.getParent().getParentMess().get(i).setResidenceAddressCode(null);
					}else{
						studentArchiveComplete.getParent().getParentMess().get(i).setResidenceAddressCode(studentArchiveComplete.getParent().getParentMess().get(i).getResidenceAddressCode());
					}
					studentArchiveComplete.getParent().getParentMess().get(i).setResidenceAddress(pResidenceAddress);
				}
			}
		}
	}
	
	private String getRegionCode(String code){
		String regionCode = "";
		Object cache = null;
		Object cacheParentCode = null;
		Object cacheParentName = null;
		Object cacheGradeCode = null;
		Object cacheGradeName = null;
		try{
			String pattern = "[0-9]*";
			if(code != null && !"".equals(code) && Pattern.matches(pattern, code)){
				int lv = RegLevelUtil.getLevel(code);
				if(lv == 1){
					cache = jcCacheService.findUniqueByParam("jc_region", "code", code, "name");
					regionCode = (cache == null ? "" : cache.toString());
				}
				if(lv == 2){
					cache = jcCacheService.findUniqueByParam("jc_region", "code", code, "name");
					cacheParentCode = jcCacheService.findUniqueByParam("jc_region", "code", code, "parent");
					if(cacheParentCode != null){
						cacheParentName = jcCacheService.findUniqueByParam("jc_region", "code", cacheParentCode.toString(), "name");
						regionCode = (cacheParentName == null ? "" : cacheParentName.toString()) + (cache == null ? "" : cache.toString());
					}
				}
				if(lv == 3){
					cache = jcCacheService.findUniqueByParam("jc_region", "code", code, "name");
					cacheParentCode = jcCacheService.findUniqueByParam("jc_region", "code", code, "parent");
					if(cacheParentCode != null){
						cacheParentName = jcCacheService.findUniqueByParam("jc_region", "code", cacheParentCode.toString(), "name");
						cacheGradeCode = jcCacheService.findUniqueByParam("jc_region", "code", cacheParentCode.toString(), "parent");
						if(cacheGradeCode != null){
							cacheGradeName = jcCacheService.findUniqueByParam("jc_region", "code", cacheGradeCode.toString(), "name");
						}
					}
					regionCode = (cacheGradeName == null ? "" : cacheGradeName.toString()) + (cacheParentName == null ? "" : cacheParentName.toString()) + (cache == null ? "" : cache.toString());
				}
			}
		}catch(IllegalArgumentException e){
			e.printStackTrace();
		}
		return regionCode;
	}

	@Override
	public Object setStudentComplete(String data,Integer userId,Integer schoolId,Boolean isComplet,Boolean isBindingMobile){
		try{
			if(data == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("studentArchiveComplete参数对象不能全部为空");
				info.setMsg("studentArchiveComplete参数对象不能全部为空");
				info.setParam("studentArchiveComplete");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			if(userId == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("userId参数不能为空");
				info.setMsg("userId参数不能为空");
				info.setParam("userId");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			if(schoolId == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("schoolId参数不能为空");
				info.setMsg("schoolId参数不能为空");
				info.setParam("schoolId");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			
			Student student = studentService.findOfUser(schoolId, userId);
			
			if(student == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("找不到学生数据");
				info.setMsg("找不到学生数据");
				info.setParam("userId，schoolId");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			
			if(isComplet == null){
				isComplet = false;
			}
			if(isBindingMobile == null){
				isBindingMobile = false;
			}
			StudentArchiveComplete studentArchiveComplete = JSON.parseObject(data, StudentArchiveComplete.class);
			
			//保存数据
			StudentArchiveComplete archiveComplete = studentService.setStudentArchiveComplete(student.getId(),studentArchiveComplete,isComplet,isBindingMobile);
			archiveComplete.getBasic().setPhotoUrl(ImgUtil.getImgUrl(archiveComplete.getBasic().getPhotoUuid()));
			
			if(archiveComplete.getRemarks().getErrorCode() != null && !"".equals(archiveComplete.getRemarks().getErrorCode())){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("保存设置学生档案发生异常，保存失败");
				info.setMsg("保存设置学生档案发生异常，保存失败");
				info.setParam("userId,schoolId,studentArchiveComplete");
				return new ResponseVo<StudentArchiveComplete>(CommonCode.$PARAMETER_VALUE_CONTENT_ERROR, archiveComplete);
			}else{
				return new ResponseVo<StudentArchiveComplete>("0", archiveComplete);
			}
		}catch(IllegalArgumentException e){
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("数据格式不正确");
			info.setMsg("输入的数据格式不正确");
			info.setParam("studentId,studentArchiveComplete");
			Log.info("保存失败,失败信息=======", e);
			return new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR, info);
		}catch (Exception e) {
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("数据解析异常");
			info.setMsg("输入解析异常");
			info.setParam("studentArchiveComplete");
			Log.info("保存失败,失败信息=======", e);
			return new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR, info);
		}
	}

	@Override
	public Object getArchiveEdit(String userId, String schoolId) {
		
		try{
			if (userId == null || ("").equals(userId)) {
				ResponseInfo info = new ResponseInfo();
				info.setDetail("参数不能为空");
				info.setMsg("userId为必填参数");
				info.setParam("userId");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL,info);
			}
			if (schoolId == null || ("").equals(schoolId)) {
				ResponseInfo info = new ResponseInfo();
				info.setDetail("参数不能为空");
				info.setMsg("schoolId为必填参数");
				info.setParam("schoolId");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL,info);
			}
			Integer studentId = studentService.findUniqueStudentId(Integer.parseInt(userId), Integer.parseInt(schoolId));
			Student student = studentService.findStudentById(studentId);
			Integer teamId = null;
			Boolean canEdit = null;
			
			if (student != null) {
				teamId = student.getTeamId();
				canEdit = jobControlService.studentArchiveCanEdit(teamId);
				StudentArchiveEdit sae = new StudentArchiveEdit(canEdit);
				return new ResponseVo<StudentArchiveEdit>("0", sae);
			} else {
				ResponseInfo info = new ResponseInfo();
				info.setDetail("数据返回异常...");
				info.setMsg("找不到数据");
				info.setParam("canEdit");
				return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
			}
			
		}catch(Exception e){
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("参数格式错误");
			info.setMsg("参数转换异常");
			info.setParam("userId,schoolId");
			return new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR,info);
		}
	}

	@Override
	public Object getArchiveEditByTeamId(Integer teamId) {
		Boolean interrupteur = null;
		try{
			if(teamId == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("teamId参数必填");
				info.setMsg("teamId参数不能为空");
				info.setParam("teamId");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			
			interrupteur = jobControlService.studentArchiveCanEdit(teamId);
			StudentArchiveEdit sae = new StudentArchiveEdit(interrupteur);
			return new ResponseVo<StudentArchiveEdit>("0", sae);
		
		} catch(Exception e) {
			ResponseInfo info = new ResponseInfo();
			info.setDetail("数据返回异常...");
			info.setMsg("找不到数据");
			info.setParam("studentArchiveInformation");
			return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
		}
		
	}
	
	@Override
	public Object getStudentArchiveFields() {
		try {
			StudentArchiveInformation studentArchiveInformation = new StudentArchiveInformation();
			StudentArchiveInformation.Basic basic = studentArchiveInformation.new Basic();
			basic.setName("姓名");
			basic.setSex("性别");
			basic.setBirthday("出生年月");
			basic.setBirthPlace("出生地");
			basic.setNativePlace("籍贯");
			basic.setRace("民族");
			basic.setNationality("国籍");
			basic.setIdCardType("身份证件类型");
			basic.setIdCardNumber("身份证件号");
			basic.setAbroadCode("港澳台侨外码");
			basic.setPoliticalStatus("政治面貌");
			basic.setHealthStatus("健康状况");
			basic.setBloodType("血型");
			basic.setPhotoUuid("头像");
			studentArchiveInformation.setBasic(basic);
			
			StudentArchiveInformation.Assist assist = studentArchiveInformation.new Assist();
			assist.setPinyinName("姓名拼音");
			assist.setUsedName("曾用名");
			assist.setIdCardDate("身份证有效期");
			assist.setResidenceAddress("户口所在地");
			assist.setResidenceType("户口类别");
			assist.setSpecialSkill("特长");
			studentArchiveInformation.setAssist(assist);
			
			StudentArchiveInformation.Archive archive = studentArchiveInformation.new Archive();
			archive.setStudentType("学生类别");
			archive.setEnrollType("入学方式");
			archive.setAttendSchoolType("就读方式");
			archive.setStudentSource("学生来源");
			archive.setStudentNumber("学籍号");
			archive.setNumber("班内学号");
			archive.setGradeId("年级");
			archive.setTeamId("班级");
			archive.setEnrollDate("入学时间");
			archive.setLeaveDate("离校时间");
			studentArchiveInformation.setArchive(archive);
			
			StudentArchiveInformation.Relation relation = studentArchiveInformation.new Relation();
			relation.setAddress("现住地址");
			relation.setHomeAddress("家庭地址");
			relation.setResideAddress("通信地址(居住地址)");
			relation.setMobile("手机");
			relation.setTelephone("电话");
			relation.setZipCode("邮政编码");
			relation.setEmail("电子信箱");
			relation.setHomepage("主页地址");
			studentArchiveInformation.setRelation(relation);
			
			StudentArchiveInformation.Extra extra = studentArchiveInformation.new Extra();
			extra.setIsOnlyChild("是否独生子女");
			extra.setIsPreeducated("是否受过学前教育");
			extra.setIsUnattendedChild("是否留守儿童");
			extra.setIsCityLabourChild("是否进城务工人员随迁子女");
			extra.setIsOrphan("是否孤儿");
			extra.setIsMartyrChild("是否烈士或优抚子女");
			extra.setFollowStudy("是否随班就读");
			extra.setDisabilityType("残疾类型");
			extra.setIsBuyedDegree("是否由政府购买学位");
			extra.setIsSponsored("是否需要申请资助");
			extra.setIsFirstRecommended("是否享受一补");
			extra.setNeedSpecialCare("是否因身体原因需要学校特别照顾");
			extra.setHouseAuthority("所住房屋权属");
			studentArchiveInformation.setExtra(extra);
			
			StudentArchiveInformation.Traffic traffic = studentArchiveInformation.new Traffic();
			traffic.setSchoolDistance("上下学距离（公里）");
			traffic.setTrafficType("上下学交通方式");
			traffic.setBySchoolBus("是否需要乘坐校车");
			studentArchiveInformation.setTraffic(traffic);
			
			StudentArchiveInformation.Parent parent = studentArchiveInformation.new Parent();
			List<ParentMess> parentList = new ArrayList<ParentMess>();
			ParentMess parentMess = new ParentMess();
			parentMess.setParentUserId("家长用户ID");
			parentMess.setName("家长姓名");
			parentMess.setParentRelation("关系");
			parentMess.setPrealtionRemarks("关系说明");
			parentMess.setRace("民族");
			parentMess.setWorkingPlace("工作单位");
			parentMess.setAddress("现住址");
			parentMess.setResidenceAddress("户口所在地");
			parentMess.setMobile("联系电话");
			parentMess.setRank("是否监护人");
			parentMess.setIdCardType("身份证类型");
			parentMess.setIdCardNumber("身份证件号");
			parentMess.setPosition("职务");
			parentList.add(parentMess);
			parent.setParentMess(parentList);
			studentArchiveInformation.setParent(parent);
			
			StudentArchiveInformation.Remarks remarks = studentArchiveInformation.new Remarks();
			remarks.setRemark("备注");
			studentArchiveInformation.setRemarks(remarks);
			
			return new ResponseVo<StudentArchiveInformation>("0", studentArchiveInformation);
			
		} catch(Exception e) {
			ResponseInfo info = new ResponseInfo();
			info.setDetail("数据返回异常...");
			info.setMsg("找不到数据");
			info.setParam("studentArchiveInformation");
			return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
		}
	}

	@Override
	public Object setEnableStudentArchiveEditing(Integer teamId,Boolean interrupteur) {
		try {
			if(teamId == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("teamId参数必填");
				info.setMsg("teamId参数不能为空");
				info.setParam("teamId");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			
			if(interrupteur == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("interrupteur参数必填");
				info.setMsg("interrupteur参数不能为空");
				info.setParam("interrupteur");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			JobControl jobControl = jobControlService.enableStudentArchiveEditing(teamId, interrupteur);
			JobControlVo jcv = null;
			if(jobControl != null){
				jcv = new JobControlVo();
				jcv.setId(jobControl.getId());
				jcv.setInterrupteur(jobControl.getInterrupteur());
				jcv.setName(jobControl.getName());
				jcv.setObjectId(jobControl.getObjectId());
				jcv.setState(jobControl.getState());
			}
			if(jcv != null){
				return new ResponseVo<JobControlVo>("0", jcv);
			}else{
				ResponseInfo info = new ResponseInfo();
				info.setDetail("数据返回异常...");
				info.setMsg("找不到数据");
				info.setParam("studentArchiveInformation");
				return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
			}
		}catch(Exception e) {
			ResponseInfo info = new ResponseInfo();
			info.setDetail("参数格式异常");
			info.setMsg("参数格式异常");
			info.setParam("studentArchiveInformation");
			return new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR, info);
		}
	}

	@Override
	public Object getStudentCompleteList(String userIds) {
		if(userIds == null){
			ResponseInfo info = new ResponseInfo();
			info.setDetail("userIds参数必填");
			info.setMsg("userIds参数不能为空");
			info.setParam("userIds");
			return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
		}
		
		List<StudentArchiveComplete> newList = null;
		try {
			String[] strs = userIds.split(",");
			int[] uids = new int[strs.length];
			for(int i=0; i<strs.length; i++){
				uids[i] = Integer.parseInt(strs[i]);
			}
			List<StudentArchiveCompleteVo> list = studentService.findCompleteByUserIds(uids);
			newList = new ArrayList<StudentArchiveComplete>();
			StudentArchiveComplete complete = null;
			for(StudentArchiveCompleteVo vo : list){
				complete = new StudentArchiveComplete();
				
				StudentArchiveComplete.Basic basic = complete.new Basic();
				PropertyUtils.copyProperties(basic, vo);
				complete.setBasic(basic);
				
				StudentArchiveComplete.Archive archive = complete.new Archive();
				PropertyUtils.copyProperties(archive, vo);
				complete.setArchive(archive);
				
				StudentArchiveComplete.Assist assist = complete.new Assist();
				PropertyUtils.copyProperties(assist, vo);
				complete.setAssist(assist);
				
				StudentArchiveComplete.Extra extra = complete.new Extra();
				PropertyUtils.copyProperties(extra, vo);
				complete.setExtra(extra);
				
				StudentArchiveComplete.Relation relation = complete.new Relation();
				PropertyUtils.copyProperties(relation, vo);
				complete.setRelation(relation);
				
				StudentArchiveComplete.Traffic traffic = complete.new Traffic();
				PropertyUtils.copyProperties(traffic, vo);
				complete.setTraffic(traffic);
				
				StudentArchiveComplete.Remarks remarks = complete.new Remarks();
				PropertyUtils.copyProperties(remarks, vo);
				complete.setRemarks(remarks);
				
				//2017-8-15   修改学生档案中家庭成员信息的获取途径
				StudentArchiveComplete.Parent parent = complete.new Parent();
				parent.setParentMess(familyMemberService.findParentMessByStudentId(vo.getStudentId()));
				complete.setParent(parent);
				
				getAllRegionName(complete);
				
				newList.add(complete);
			}
		
		} catch (NumberFormatException e) {
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("数据格式不正确");
			info.setMsg("输入的数据格式不正确");
			info.setParam("userIds");
			return new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR, info);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		return new ResponseVo<List<StudentArchiveComplete>>("0", newList);
	}

	
}
