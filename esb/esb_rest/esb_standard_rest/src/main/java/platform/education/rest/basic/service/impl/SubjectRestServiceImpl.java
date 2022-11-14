package platform.education.rest.basic.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.omg.CORBA.OBJ_ADAPTER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import platform.education.generalTeachingAffair.model.Grade;
import platform.education.generalTeachingAffair.model.Subject;
import platform.education.generalTeachingAffair.model.SubjectGrade;
import platform.education.generalTeachingAffair.service.GradeService;
import platform.education.generalTeachingAffair.service.SubjectGradeService;
import platform.education.generalTeachingAffair.service.SubjectService;
import platform.education.rest.basic.service.SubjectRestService;
import platform.education.rest.basic.service.vo.CommonSubject;
import platform.education.rest.common.ResponseError;
import platform.education.rest.common.ResponseInfo;
import platform.education.rest.common.ResponseVo;
import platform.education.rest.common.constants.CommonCode;

public class SubjectRestServiceImpl implements SubjectRestService
{

	
	// 年级
	@Autowired
	@Qualifier("gradeService")
	protected GradeService gradeService;
	
	@Autowired
	@Qualifier("subjectGradeService")
	protected SubjectGradeService subjectGradeService;

	@Autowired
	@Qualifier("subjectService")
	protected SubjectService subjectService;
		
	@Override
	public Object getSexNumber(String gradeId, String appKey)
	{
		if(gradeId == null)
		{
			ResponseInfo info = new ResponseInfo();
			info.setDetail("gradeId参数不能为空");
			info.setMsg("gradeId参数不能为空");
			info.setParam("gradeId");
			return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
		}
		try
		{
			Grade grade = this.gradeService.findGradeById(Integer.parseInt(gradeId));
			List<CommonSubject> subject = new ArrayList<CommonSubject>();
			CommonSubject vo = null;
			if(grade!=null){
				List<SubjectGrade> subjectList = this.subjectGradeService.findSubjectGradeByGradeCode(grade.getSchoolId(),grade.getUniGradeCode() );
				for(SubjectGrade sbg : subjectList){
					vo = new CommonSubject();
					vo.setCode(sbg.getSubjectCode());
					vo.setName(sbg.getSubjectName());
					subject.add(vo);
				}
			}
			return new ResponseVo<List<CommonSubject>>("0",subject);
		} catch (Exception e)
		{
			ResponseInfo info = new ResponseInfo();
			info.setDetail("年级id转换异常...");
			info.setMsg("参数出错");
			info.setParam("gradeId");
			return new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR,info);
		}
	}

	@Override
	public Object getAllSubject(Integer schoolId) {
		if(schoolId == null){
			ResponseInfo info = new ResponseInfo();
			info.setDetail("schoolId参数不能为空");
			info.setMsg("参数错误");
			info.setParam("schoolId");
			return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
		}
		try {
			List<Object> list = new ArrayList<>();
			Map<String, Object> map = null;
			List<Subject> subjectList = subjectService.findSubjectsOfSchool(schoolId);
			if (subjectList != null && subjectList.size() > 0) {
                for (Subject subject : subjectList) {
                    map = new HashMap<>();
                    map.put("subjectCode", subject.getCode());
                    map.put("subjectName", subject.getName());
                    list.add(map);
                }
            }
			return new ResponseVo<Object>("0", list);
		} catch (Exception e) {
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("接口调用异常");
			info.setMsg("接口调用异常");
			return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL,info);
		}
	}
}
