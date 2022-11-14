package platform.education.rest.basic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import platform.education.generalTeachingAffair.model.SchoolServerManage;
import platform.education.generalTeachingAffair.service.SchoolServerManageService;
import platform.education.rest.basic.service.SchoolRestService;
import platform.education.rest.common.ResponseError;
import platform.education.rest.common.ResponseInfo;
import platform.education.rest.common.ResponseVo;
import platform.education.rest.common.constants.CommonCode;

public class SchoolRestServiceImpl implements SchoolRestService {

	@Autowired
	@Qualifier("schoolServerManageService")
	private SchoolServerManageService schoolServerManageService;
	
	@Override
	public Object getSchoolServerManage(Integer schoolId) {
		try {
			if(schoolId == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("schoolId参数不能为空");
				info.setMsg("schoolId参数不能为空");
				info.setParam("schoolId");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			SchoolServerManage schoolServerManage = schoolServerManageService.findSchoolServerManageBySchoolId(schoolId);
			return new ResponseVo<SchoolServerManage>("0",schoolServerManage);
		}catch (Exception e) {
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("schoolId异常");
			info.setMsg("参数异常");
			info.setParam("schoolId");
			return new ResponseError(CommonCode.$PARAMETER_VALUE_CONTENT_ERROR,info);
		}
	}

	@Override
	public Object getSchoolServerManageBySchoolName(String schoolName) {
		try {
			List<SchoolServerManage> list = schoolServerManageService.findSchoolServerManageBySchoolName(schoolName);
			return new ResponseVo<List<SchoolServerManage>>("0",list);
		} catch (Exception e) {
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("schoolName异常");
			info.setMsg("参数异常");
			info.setParam("schoolName");
			return new ResponseError(CommonCode.$PARAMETER_VALUE_CONTENT_ERROR,info);
		}
	}

}
