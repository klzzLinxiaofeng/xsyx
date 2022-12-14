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
				info.setDetail("userId????????????");
				info.setMsg("userId??????????????????");
				info.setParam("userId");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			
			if(schoolId == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("schoolId????????????");
				info.setMsg("schoolId??????????????????");
				info.setParam("schoolId");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			
			Student student = studentService.findOfUser(schoolId, userId);
			
			if(student == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("?????????????????????");
				info.setMsg("?????????????????????");
				info.setParam("userId???schoolId");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			
			//??????????????????????????????
			StudentArchiveComplete studentArchiveComplete = studentService.getStudentArchiveComplete(student.getId());
			getAllRegionName(studentArchiveComplete);
			
			if(studentArchiveComplete != null){
				String uuid = studentArchiveComplete.getBasic().getPhotoUuid();
				studentArchiveComplete.getBasic().setPhotoUrl(FileServiceHolder.getInstance().getFileService().relativePath2HttpUrlByUUID(uuid));
				return new ResponseVo<StudentArchiveComplete>("0", studentArchiveComplete);
			}else{
				ResponseInfo info = new ResponseInfo();
				info.setDetail("???????????????????????????");
				info.setMsg("??????????????????");
				info.setParam("studentId");
				return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
			}
		}catch(NumberFormatException e){
			ResponseInfo info = new ResponseInfo();
			info.setDetail("studentId????????????");
			info.setMsg("studentId????????????");
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
				info.setDetail("studentArchiveComplete??????????????????????????????");
				info.setMsg("studentArchiveComplete??????????????????????????????");
				info.setParam("studentArchiveComplete");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			if(userId == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("userId??????????????????");
				info.setMsg("userId??????????????????");
				info.setParam("userId");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			if(schoolId == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("schoolId??????????????????");
				info.setMsg("schoolId??????????????????");
				info.setParam("schoolId");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			
			Student student = studentService.findOfUser(schoolId, userId);
			
			if(student == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("?????????????????????");
				info.setMsg("?????????????????????");
				info.setParam("userId???schoolId");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			
			if(isComplet == null){
				isComplet = false;
			}
			if(isBindingMobile == null){
				isBindingMobile = false;
			}
			StudentArchiveComplete studentArchiveComplete = JSON.parseObject(data, StudentArchiveComplete.class);
			
			//????????????
			StudentArchiveComplete archiveComplete = studentService.setStudentArchiveComplete(student.getId(),studentArchiveComplete,isComplet,isBindingMobile);
			archiveComplete.getBasic().setPhotoUrl(ImgUtil.getImgUrl(archiveComplete.getBasic().getPhotoUuid()));
			
			if(archiveComplete.getRemarks().getErrorCode() != null && !"".equals(archiveComplete.getRemarks().getErrorCode())){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("???????????????????????????????????????????????????");
				info.setMsg("???????????????????????????????????????????????????");
				info.setParam("userId,schoolId,studentArchiveComplete");
				return new ResponseVo<StudentArchiveComplete>(CommonCode.$PARAMETER_VALUE_CONTENT_ERROR, archiveComplete);
			}else{
				return new ResponseVo<StudentArchiveComplete>("0", archiveComplete);
			}
		}catch(IllegalArgumentException e){
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("?????????????????????");
			info.setMsg("??????????????????????????????");
			info.setParam("studentId,studentArchiveComplete");
			Log.info("????????????,????????????=======", e);
			return new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR, info);
		}catch (Exception e) {
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("??????????????????");
			info.setMsg("??????????????????");
			info.setParam("studentArchiveComplete");
			Log.info("????????????,????????????=======", e);
			return new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR, info);
		}
	}

	@Override
	public Object getArchiveEdit(String userId, String schoolId) {
		
		try{
			if (userId == null || ("").equals(userId)) {
				ResponseInfo info = new ResponseInfo();
				info.setDetail("??????????????????");
				info.setMsg("userId???????????????");
				info.setParam("userId");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL,info);
			}
			if (schoolId == null || ("").equals(schoolId)) {
				ResponseInfo info = new ResponseInfo();
				info.setDetail("??????????????????");
				info.setMsg("schoolId???????????????");
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
				info.setDetail("??????????????????...");
				info.setMsg("???????????????");
				info.setParam("canEdit");
				return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
			}
			
		}catch(Exception e){
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("??????????????????");
			info.setMsg("??????????????????");
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
				info.setDetail("teamId????????????");
				info.setMsg("teamId??????????????????");
				info.setParam("teamId");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			
			interrupteur = jobControlService.studentArchiveCanEdit(teamId);
			StudentArchiveEdit sae = new StudentArchiveEdit(interrupteur);
			return new ResponseVo<StudentArchiveEdit>("0", sae);
		
		} catch(Exception e) {
			ResponseInfo info = new ResponseInfo();
			info.setDetail("??????????????????...");
			info.setMsg("???????????????");
			info.setParam("studentArchiveInformation");
			return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
		}
		
	}
	
	@Override
	public Object getStudentArchiveFields() {
		try {
			StudentArchiveInformation studentArchiveInformation = new StudentArchiveInformation();
			StudentArchiveInformation.Basic basic = studentArchiveInformation.new Basic();
			basic.setName("??????");
			basic.setSex("??????");
			basic.setBirthday("????????????");
			basic.setBirthPlace("?????????");
			basic.setNativePlace("??????");
			basic.setRace("??????");
			basic.setNationality("??????");
			basic.setIdCardType("??????????????????");
			basic.setIdCardNumber("???????????????");
			basic.setAbroadCode("??????????????????");
			basic.setPoliticalStatus("????????????");
			basic.setHealthStatus("????????????");
			basic.setBloodType("??????");
			basic.setPhotoUuid("??????");
			studentArchiveInformation.setBasic(basic);
			
			StudentArchiveInformation.Assist assist = studentArchiveInformation.new Assist();
			assist.setPinyinName("????????????");
			assist.setUsedName("?????????");
			assist.setIdCardDate("??????????????????");
			assist.setResidenceAddress("???????????????");
			assist.setResidenceType("????????????");
			assist.setSpecialSkill("??????");
			studentArchiveInformation.setAssist(assist);
			
			StudentArchiveInformation.Archive archive = studentArchiveInformation.new Archive();
			archive.setStudentType("????????????");
			archive.setEnrollType("????????????");
			archive.setAttendSchoolType("????????????");
			archive.setStudentSource("????????????");
			archive.setStudentNumber("?????????");
			archive.setNumber("????????????");
			archive.setGradeId("??????");
			archive.setTeamId("??????");
			archive.setEnrollDate("????????????");
			archive.setLeaveDate("????????????");
			studentArchiveInformation.setArchive(archive);
			
			StudentArchiveInformation.Relation relation = studentArchiveInformation.new Relation();
			relation.setAddress("????????????");
			relation.setHomeAddress("????????????");
			relation.setResideAddress("????????????(????????????)");
			relation.setMobile("??????");
			relation.setTelephone("??????");
			relation.setZipCode("????????????");
			relation.setEmail("????????????");
			relation.setHomepage("????????????");
			studentArchiveInformation.setRelation(relation);
			
			StudentArchiveInformation.Extra extra = studentArchiveInformation.new Extra();
			extra.setIsOnlyChild("??????????????????");
			extra.setIsPreeducated("????????????????????????");
			extra.setIsUnattendedChild("??????????????????");
			extra.setIsCityLabourChild("????????????????????????????????????");
			extra.setIsOrphan("????????????");
			extra.setIsMartyrChild("???????????????????????????");
			extra.setFollowStudy("??????????????????");
			extra.setDisabilityType("????????????");
			extra.setIsBuyedDegree("???????????????????????????");
			extra.setIsSponsored("????????????????????????");
			extra.setIsFirstRecommended("??????????????????");
			extra.setNeedSpecialCare("?????????????????????????????????????????????");
			extra.setHouseAuthority("??????????????????");
			studentArchiveInformation.setExtra(extra);
			
			StudentArchiveInformation.Traffic traffic = studentArchiveInformation.new Traffic();
			traffic.setSchoolDistance("???????????????????????????");
			traffic.setTrafficType("?????????????????????");
			traffic.setBySchoolBus("????????????????????????");
			studentArchiveInformation.setTraffic(traffic);
			
			StudentArchiveInformation.Parent parent = studentArchiveInformation.new Parent();
			List<ParentMess> parentList = new ArrayList<ParentMess>();
			ParentMess parentMess = new ParentMess();
			parentMess.setParentUserId("????????????ID");
			parentMess.setName("????????????");
			parentMess.setParentRelation("??????");
			parentMess.setPrealtionRemarks("????????????");
			parentMess.setRace("??????");
			parentMess.setWorkingPlace("????????????");
			parentMess.setAddress("?????????");
			parentMess.setResidenceAddress("???????????????");
			parentMess.setMobile("????????????");
			parentMess.setRank("???????????????");
			parentMess.setIdCardType("???????????????");
			parentMess.setIdCardNumber("???????????????");
			parentMess.setPosition("??????");
			parentList.add(parentMess);
			parent.setParentMess(parentList);
			studentArchiveInformation.setParent(parent);
			
			StudentArchiveInformation.Remarks remarks = studentArchiveInformation.new Remarks();
			remarks.setRemark("??????");
			studentArchiveInformation.setRemarks(remarks);
			
			return new ResponseVo<StudentArchiveInformation>("0", studentArchiveInformation);
			
		} catch(Exception e) {
			ResponseInfo info = new ResponseInfo();
			info.setDetail("??????????????????...");
			info.setMsg("???????????????");
			info.setParam("studentArchiveInformation");
			return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
		}
	}

	@Override
	public Object setEnableStudentArchiveEditing(Integer teamId,Boolean interrupteur) {
		try {
			if(teamId == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("teamId????????????");
				info.setMsg("teamId??????????????????");
				info.setParam("teamId");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			
			if(interrupteur == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("interrupteur????????????");
				info.setMsg("interrupteur??????????????????");
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
				info.setDetail("??????????????????...");
				info.setMsg("???????????????");
				info.setParam("studentArchiveInformation");
				return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
			}
		}catch(Exception e) {
			ResponseInfo info = new ResponseInfo();
			info.setDetail("??????????????????");
			info.setMsg("??????????????????");
			info.setParam("studentArchiveInformation");
			return new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR, info);
		}
	}

	@Override
	public Object getStudentCompleteList(String userIds) {
		if(userIds == null){
			ResponseInfo info = new ResponseInfo();
			info.setDetail("userIds????????????");
			info.setMsg("userIds??????????????????");
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
				
				//2017-8-15   ??????????????????????????????????????????????????????
				StudentArchiveComplete.Parent parent = complete.new Parent();
				parent.setParentMess(familyMemberService.findParentMessByStudentId(vo.getStudentId()));
				complete.setParent(parent);
				
				getAllRegionName(complete);
				
				newList.add(complete);
			}
		
		} catch (NumberFormatException e) {
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("?????????????????????");
			info.setMsg("??????????????????????????????");
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
