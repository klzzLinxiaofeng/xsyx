package platform.education.rest.jw.service.util;


import java.util.List;

import platform.education.generalTeachingAffair.model.PjTeacherSalary;
import platform.education.rest.common.ResponseError;
import platform.education.rest.common.ResponseInfo;
import platform.education.rest.common.constants.CommonCode;
import platform.education.rest.jw.service.vo.FieldNameValue;
import platform.education.rest.jw.service.vo.TeacherSalary;
import platform.education.rest.util.BeanUtilsSub;

import com.alibaba.fastjson.JSONObject;

import framework.generic.dao.Page;

public class SalaryUtils {

	private static SalaryUtils singleton = new SalaryUtils();
	
	public static SalaryUtils getSingleton() {
		return singleton;
	}
	
	public static Page getPage(int currentPage, int pageSize) {
		Page page = new Page();
		page.setCurrentPage(currentPage);
		page.setPageSize(pageSize);
		return page;
	}
	
	
	
	public class PropertyValue {
		private String property;
		private Object value;
		
		public PropertyValue(String property, Object value) {
			super();
			this.property = property;
			this.value = value;
		}
		public String getProperty() {
			return property;
		}
		public void setProperty(String property) {
			this.property = property;
		}
		public Object getValue() {
			return value;
		}
		public void setValue(Object value) {
			this.value = value;
		}
		
	}
	
	
	public static ResponseError checkNotNull(List<PropertyValue> data) {
		String property = null;
		for (PropertyValue propertyValue : data) {
			if ( propertyValue.getValue() == null ) {
				property = propertyValue.getProperty();
			} 
		}
		if ( property != null ) {
			ResponseInfo info = new ResponseInfo();
			info.setDetail(property + "参数异常");
			info.setMsg(property + "参数异常");
			info.setParam(property);
			return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
		}
		return null;
	}
	
	public static ResponseError queryNotExsit(String message) {
		ResponseInfo info = new ResponseInfo();
		info.setDetail(message+" 找不到匹配数据");
		info.setMsg(message+" 找不到匹配数据");
		info.setParam(message);
		return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
	}
	
	public static TeacherSalary getTeacherSalary(PjTeacherSalary pjTeacherSalary) {
		TeacherSalary salary = new TeacherSalary();
		salary.setId(pjTeacherSalary.getId());
		salary.setUserId(pjTeacherSalary.getUserId());
		salary.setName(pjTeacherSalary.getName());
		salary.setYear(pjTeacherSalary.getPayYear());
		salary.setMonth(pjTeacherSalary.getPayMonth());
		if ( pjTeacherSalary.getSalaryTotal() != null ) {
			salary.setSalaryTotal(pjTeacherSalary.getSalaryTotal());
		}
		FieldNameValue values = getFieldNameValue(pjTeacherSalary);
		salary.setSalaryDetail(values);
		return salary;
	}
	
	public static PjTeacherSalary getPjTeacherSalary(Integer id,Integer schoolId, Integer userId, String name, Integer year,
			Integer month, Float salaryTotal, FieldNameValue fieldValue) {
		PjTeacherSalary result = new PjTeacherSalary();
		if ( fieldValue != null ) {
			getPjTeacherSalaryByFieldNameValue(fieldValue, result);
		}
		if ( id != null ) {
			result.setId(id);
		}
		if ( schoolId != null ) {
			result.setSchoolId(schoolId);
		}
		if ( userId != null ) {
			result.setUserId(userId);
		}
		if ( name != null ) {
			result.setName(name);
		}
		if ( month != null ) {
			result.setPayMonth(month);
		}
		if ( year != null ) {
			result.setPayYear(year);
		}
		if ( salaryTotal != null ) {
			result.setSalaryTotal(salaryTotal);
		}
		return result;
	}
	
	// TODO 先不做校验
//	private Float getSalarySum(FieldNameValue fieldValue) {
//		Float sum = Float.valueOf(0);
//		if ( fieldValue.getS1() != null ) {
//			sum += fieldValue.getS1();
//		}
//		
//		return sum;
//	}
	
	public static FieldNameValue getFieldNameValue(PjTeacherSalary pjTeacherSalary) {
		FieldNameValue result = new FieldNameValue();
		BeanUtilsSub.copyProperties(result, pjTeacherSalary);
		return result;
	}
	
	public static PjTeacherSalary getPjTeacherSalaryByFieldNameValue(FieldNameValue fieldNameValue, PjTeacherSalary result) {
		BeanUtilsSub.copyProperties(result, fieldNameValue);
		return result;
	}
	
}
