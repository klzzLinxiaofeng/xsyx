package platform.education.rest.basic.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import platform.education.generalTeachingAffair.model.DepartmentTeacher;
import platform.education.generalTeachingAffair.model.Grade;
import platform.education.generalTeachingAffair.model.Teacher;
import platform.education.generalTeachingAffair.service.DepartmentTeacherService;
import platform.education.generalTeachingAffair.service.GradeService;
import platform.education.generalTeachingAffair.service.TeacherService;
import platform.education.rest.basic.service.GradeRestService;
import platform.education.rest.basic.service.vo.GradeInfo;
import platform.education.rest.basic.service.vo.GradeTeachers;
import platform.education.rest.basic.service.vo.GradeTeachersVo;
import platform.education.rest.common.ResponseError;
import platform.education.rest.common.ResponseInfo;
import platform.education.rest.common.ResponseVo;
import platform.education.rest.common.constants.CommonCode;

public class GradeRestServiceImpl implements GradeRestService{
	
	@Autowired
	@Qualifier("gradeService")
	private GradeService gradeService;
	
	@Autowired
	@Qualifier("teacherService")
	private TeacherService teacherService;
	
	@Autowired
	@Qualifier("departmentTeacherService")
	private DepartmentTeacherService departmentTeacherService;
	
	@Override
	public Object getGradeByYear(Integer schoolId,String schoolYear){
		List<GradeInfo> gradeInfoList = new ArrayList<GradeInfo>();
		GradeInfo gradeInfo = null;
		try{
			if(schoolId == null || "".equals(schoolId)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("schoolId参数必填");
				info.setMsg("schoolId参数不能为空");
				info.setParam("schoolId");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			if(schoolYear == null || "".equals(schoolYear)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("schoolYear参数必填");
				info.setMsg("schoolYear参数不能为空");
				info.setParam("schoolYear");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			
			List<Grade> gradeList = gradeService.findGradeBySchoolYear(schoolId, schoolYear);
			if(gradeList != null && gradeList.size() > 0){
				for(Grade grade : gradeList){
					if(grade != null && grade.getId() != null){
						gradeInfo = new GradeInfo();
						gradeInfo.setId(grade.getId());
						gradeInfo.setName(grade.getName());
					}
					gradeInfoList.add(gradeInfo);
				}
			}else{
				ResponseInfo info = new ResponseInfo();
				info.setDetail("根据schoolId和schoolYear查询不到数据");
				info.setMsg("查询结果为空");
				info.setParam("schoolId,schoolYear");
				return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
			}
		}catch(NumberFormatException e){
			ResponseInfo info = new ResponseInfo();
			info.setDetail("schoolId参数异常");
			info.setMsg("schoolId参数异常");
			info.setParam("schoolId");
			return new ResponseError(CommonCode.$PARAMETER_VALUE_CONTENT_ERROR, info);
		}
		return new ResponseVo<List<GradeInfo>>("0", gradeInfoList);
	}

	@Override
	public Object getGradeTeachers(String gradeIds, String schoolYear,Boolean isGetAll) {
		if(gradeIds == null || "".equals(gradeIds)){
			ResponseInfo info = new ResponseInfo();
			info.setDetail("gradeIds参数必填");
			info.setMsg("gradeIds参数不能为空");
			info.setParam("gradeIds");
			return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
		}
		if(isGetAll == null){
			isGetAll = false;
		}
		String[] gradeIdsArr = gradeIds.split(",");
		GradeTeachersVo gtv = null;
		GradeTeachers gt =null;
		List<GradeTeachers> gtList =  new ArrayList<>();
		List<GradeTeachersVo> gtvList = null;
		List<DepartmentTeacher> departmentTeacherList = null;
		if(gradeIdsArr != null && gradeIdsArr.length > 0){
			Boolean isInteger = false;
			for(String gradeId : gradeIdsArr){
				Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
				isInteger = pattern.matcher(gradeId).matches();
				gtv = new GradeTeachersVo();
				gtList = new ArrayList<>();
				if(isInteger){
					Grade grade = gradeService.findGradeById(Integer.valueOf(gradeId));
					if(grade != null){
						List<Teacher> teachers = teacherService.findGradeOfTeacher(grade.getId(), schoolYear,isGetAll);
						if(teachers != null && teachers.size() > 0){
							gt = new GradeTeachers();
							gtvList = new ArrayList<GradeTeachersVo>();
							for(Teacher t : teachers){
								String departmentName = "";
								//根据教师ID和学校ID获取某个教师所在的所有部门
								departmentTeacherList = departmentTeacherService.findDepartmentTeacherByTeacherIdAndSchoolId(t.getId(),grade.getSchoolId());
								if(departmentTeacherList != null){
									for(int i = 0; i < departmentTeacherList.size(); i++){
										if(i==0){
											departmentName += departmentTeacherList.get(i).getDepartmentName();
										}else{
											departmentName += "," + departmentTeacherList.get(i).getDepartmentName();
										}
									}
								}
								
								gtv = new GradeTeachersVo();
								gtv.setDeptName(departmentName);
								if(t != null){
									gtv.setId(t.getId());
									gtv.setAlias(t.getAlias());
									gtv.setMobile(t.getMobile());
									gtv.setName(t.getName());
									gtv.setPosition(t.getPosition());
									gtv.setPostStaffing(t.getPostStaffing());
									gtv.setSchoolId(t.getSchoolId());
									gtv.setSex(t.getSex());
									gtv.setUserId(t.getUserId());
									gtv.setUserName(t.getUserName());
									gtv.setQualification(t.getQualification());
									gtvList.add(gtv);
								}
								
							}
						}
						gt.setId(grade.getId());
						gt.setName(grade.getName());
						gt.setTeachers(gtvList);
					}
					gtList.add(gt);
				}
			}
		}
		return new ResponseVo<List<GradeTeachers>>("0", gtList);
	}

}
