package platform.education.generalTeachingAffair.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import platform.education.generalTeachingAffair.vo.CheckResult;

public class DataImportCheck {
	public static Map<String, String> gradeMap;
	
	public static Map<String, Object> checkStudentData(String num, String name, String grade, String team, String guardian,
			String guardianPhone) {
		Map<String, Object> entity = getStudentDataHandlerResultEntity(num, name, grade, team, guardian, guardianPhone);
		
		Integer status = 0;
		String errorFiled = "";
		String errorInfo = "";
		
		/**年级检验*/
		CheckResult checkResult = checkGrade(grade);
		if (checkResult.checkError()) {
			errorFiled += "grade";
			errorInfo += checkResult.getInfo();
			status = 2;
		}
		/**班级校验*/
		checkResult = checkTeam(team);
		if (checkResult.checkError()) {
			if(!"".equals(errorFiled)) {
				errorFiled += ",team";
			} else {
				errorFiled += "team";
			}
			if(!"".equals(errorInfo)) {
				errorInfo += "、"+checkResult.getInfo();
			} else {
				errorInfo += checkResult.getInfo();
			}
			status = 2;
		}
		/**班内学号校验*/
		checkResult = checkNum(num);
		if (checkResult.checkError()) {
			if(!"".equals(errorFiled)) {
				errorFiled += ",num";
			} else {
				errorFiled += "num";
			}
			if(!"".equals(errorInfo)) {
				errorInfo += "、"+checkResult.getInfo();
			} else {
				errorInfo += checkResult.getInfo();
			}
			status = 2;
		}
		/**姓名校验*/
		checkResult = checkName(name);
		if (checkResult.checkError()) {
			if(!"".equals(errorFiled)) {
				errorFiled += ",name";
			} else {
				errorFiled += "name";
			}
			if(!"".equals(errorInfo)) {
				errorInfo += "、"+checkResult.getInfo();
			} else {
				errorInfo += checkResult.getInfo();
			}
			status = 2;
		}
		/**手机校验*/
		checkResult = checkPhone(guardianPhone);
		if (checkResult.checkError()) {
			if(!"".equals(errorFiled)) {
				errorFiled += ",guardianPhone";
			} else {
				errorFiled += "guardianPhone";
			}
			if(!"".equals(errorInfo)) {
				errorInfo += "、"+checkResult.getInfo();
			} else {
				errorInfo += checkResult.getInfo();
			}
			status = 2;
		}
		/**监护人姓名校验*/
		checkResult = checkGuardian(guardian);
		if (checkResult.checkError()) {
			if(!"".equals(errorFiled)) {
				errorFiled += ",guardian";
			} else {
				errorFiled += "guardian";
			}
			if(!"".equals(errorInfo)) {
				errorInfo += "、"+checkResult.getInfo();
			} else {
				errorInfo += checkResult.getInfo();
			}
			status = 2;
		}
		
		entity.put("status", status);
		entity.put("errorFiled", errorFiled);
		entity.put("errorInfo", errorInfo);
		
		return entity;
	}
	
	public static Map<String, Object> checkTeacherData(String name, String alias, String phone, String department,
			String position, String sex) {
		Map<String, Object> entity = getTeacherDataHandlerResultEntity(name, alias, phone, department, position, sex);
		
		Integer status = 0;
		String errorFiled = "";
		String errorInfo = "";
		
		/**姓名检验*/
		CheckResult checkResult = checkName(name);
		if (checkResult.checkError()) {
			errorFiled += "name";
			errorInfo += checkResult.getInfo();
			status = 2;
		}
		/**别名校验*/
		checkResult = checkAlias(alias);
		if (checkResult.checkError()) {
			if(!"".equals(errorFiled)) {
				errorFiled += ",alias";
			} else {
				errorFiled += "alias";
			}
			if(!"".equals(errorInfo)) {
				errorInfo += "、"+checkResult.getInfo();
			} else {
				errorInfo += checkResult.getInfo();
			}
			status = 2;
		}
		/**手机校验*/
		checkResult = checkMobile(phone);
		if (checkResult.checkError()) {
			if(!"".equals(errorFiled)) {
				errorFiled += ",phone";
			} else {
				errorFiled += "phone";
			}
			if(!"".equals(errorInfo)) {
				errorInfo += "、"+checkResult.getInfo();
			} else {
				errorInfo += checkResult.getInfo();
			}
			status = 2;
		}
		/**部门校验*/
		checkResult = checkDepartment(department);
		if (checkResult.checkError()) {
			if(!"".equals(errorFiled)) {
				errorFiled += ",department";
			} else {
				errorFiled += "department";
			}
			if(!"".equals(errorInfo)) {
				errorInfo += "、"+checkResult.getInfo();
			} else {
				errorInfo += checkResult.getInfo();
			}
			status = 2;
		}
		/**职务校验*/
		checkResult = checkPosition(position);
		if (checkResult.checkError()) {
			if(!"".equals(errorFiled)) {
				errorFiled += ",position";
			} else {
				errorFiled += "position";
			}
			if(!"".equals(errorInfo)) {
				errorInfo += "、"+checkResult.getInfo();
			} else {
				errorInfo += checkResult.getInfo();
			}
			status = 2;
		}
		
		entity.put("status", status);
		entity.put("errorFiled", errorFiled);
		entity.put("errorInfo", errorInfo);
		
		return entity;
	}

	public static Map<String, Object> checkParentStudentData(String gradeName, String teamNumber, String studentName, Integer number, String parentName, String mobile) {
		Map<String, Object> entity = getParentStudentDataHandlerResultEntity(gradeName, teamNumber, studentName, number, parentName, mobile);
		Integer status = 0;
		String errorFiled = "";
		String errorInfo = "";

		CheckResult checkResult = checkGrade(gradeName);
		if (checkResult.checkError()) {
			errorFiled += "gradeName";
			errorInfo += checkResult.getInfo();
			status = 2;
		}
		checkResult = checkTeam(teamNumber);
		if (checkResult.checkError()) {
			if(!"".equals(errorFiled)) {
				errorFiled += ",teamNumber";
			} else {
				errorFiled += "teamNumber";
			}
			if(!"".equals(errorInfo)) {
				errorInfo += "、"+checkResult.getInfo();
			} else {
				errorInfo += checkResult.getInfo();
			}
			status = 2;
		}
		checkResult = checkNum(String.valueOf(number));
		if (checkResult.checkError()) {
			if(!"".equals(errorFiled)) {
				errorFiled += ",number";
			} else {
				errorFiled += "number";
			}
			if(!"".equals(errorInfo)) {
				errorInfo += "、"+checkResult.getInfo();
			} else {
				errorInfo += checkResult.getInfo();
			}
			status = 2;
		}
		checkResult = checkName(studentName);
		if (checkResult.checkError()) {
			if(!"".equals(errorFiled)) {
				errorFiled += ",studentName";
			} else {
				errorFiled += "studentName";
			}
			if(!"".equals(errorInfo)) {
				errorInfo += "、"+checkResult.getInfo();
			} else {
				errorInfo += checkResult.getInfo();
			}
			status = 2;
		}
		checkResult = checkPhone(mobile);
		if (checkResult.checkError()) {
			if(!"".equals(errorFiled)) {
				errorFiled += ",mobile";
			} else {
				errorFiled += "mobile";
			}
			if(!"".equals(errorInfo)) {
				errorInfo += "、"+checkResult.getInfo();
			} else {
				errorInfo += checkResult.getInfo();
			}
			status = 2;
		}
		/**监护人姓名校验*/
		checkResult = checkGuardian(parentName);
		if (checkResult.checkError()) {
			if(!"".equals(errorFiled)) {
				errorFiled += ",parentName";
			} else {
				errorFiled += "parentName";
			}
			if(!"".equals(errorInfo)) {
				errorInfo += "、"+checkResult.getInfo();
			} else {
				errorInfo += checkResult.getInfo();
			}
			status = 2;
		}

		entity.put("status", status);
		entity.put("errorFiled", errorFiled);
		entity.put("errorInfo", errorInfo);

		return entity;
	}

	public static Map<String, Object> checkTeamTeacherData(String gradeName, String teamNumber, String name, String subjectName) {
		Map<String, Object> entity = getTeamTeacherDataHandlerResultEntity(gradeName, teamNumber, name, subjectName);
		Integer status = 0;
		String errorFiled = "";
		String errorInfo = "";

		CheckResult checkResult = checkGrade(gradeName);
		if (checkResult.checkError()) {
			errorFiled += "grade";
			errorInfo += checkResult.getInfo();
			status = 2;
		}
		checkResult = checkTeam(teamNumber);
		if (checkResult.checkError()) {
			if(!"".equals(errorFiled)) {
				errorFiled += ",team";
			} else {
				errorFiled += "team";
			}
			if(!"".equals(errorInfo)) {
				errorInfo += checkResult.getInfo();
			} else {
				errorInfo += checkResult.getInfo();
			}
			status = 2;
		}
		checkResult = checkName(name);
		if (checkResult.checkError()) {
			if(!"".equals(errorFiled)) {
				errorFiled += ",name";
			} else {
				errorFiled += "name";
			}
			if(!"".equals(errorInfo)) {
				errorInfo += checkResult.getInfo();
			} else {
				errorInfo += checkResult.getInfo();
			}
			status = 2;
		}
		checkResult = checkSubjectName(subjectName);
		if (checkResult.checkError()) {
			if(!"".equals(errorFiled)) {
				errorFiled += ",subjectName";
			} else {
				errorFiled += "subjectName";
			}
			if(!"".equals(errorInfo)) {
				errorInfo += checkResult.getInfo();
			} else {
				errorInfo += checkResult.getInfo();
			}
			status = 2;
		}

		entity.put("status", status);
		entity.put("errorFiled", errorFiled);
		entity.put("errorInfo", errorInfo);

		return entity;
	}

	private static CheckResult checkMobile(String phone) {
		CheckResult result = CheckResult.getInstance(phone);
		if(phone==null || "".equals(phone)) {
			result.setStatus(2);
			result.setInfo("手机号不能为空");
		} else {
			if(phone.length()-11!=0) {
				result.setStatus(2);
				result.setInfo("手机号格式不正确");
			}
		}
		return result;
	}

	
	public static Map<String, Object> getTeacherDataHandlerResultEntity(String name, String alias, String phone,
			String department, String position, String sex) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("name", name);
		result.put("alias", alias);
		result.put("phone", phone);
		result.put("department", department);
		result.put("position", position);
		result.put("sex", sex);
		return result;
	}

	public static Map<String, Object> getParentStudentDataHandlerResultEntity(String gradeName, String teamNumber, String studentName, Integer number, String parentName, String mobile){
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("gradeName", gradeName);
		result.put("teamNumber", teamNumber);
		result.put("studentName", studentName);
		result.put("number", number);
		result.put("parentName", parentName);
		result.put("mobile", mobile);
		return result;
	}
	
	public static Map<String, Object> getStudentDataHandlerResultEntity(String num, String name, String grade, String team,
			String guardian, String guardianPhone) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("num", num);
		result.put("name", name);
		result.put("grade", grade);
		result.put("team", team);
		result.put("guardian", guardian);
		result.put("guardianPhone", guardianPhone);
		
		return result;
	}

	public static Map<String, Object> getTeamTeacherDataHandlerResultEntity(String gradeName, String teamNumber, String name, String subjectName) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("gradeName", gradeName);
		result.put("teamNumber", teamNumber);
		result.put("name", name);
		result.put("subjectName", subjectName);
		return result;
	}

	public static CheckResult checkGuardian(String guardian) {
		CheckResult result = CheckResult.getInstance(guardian);
		if(guardian==null || "".equals(guardian)) {
			return result;
		} else {
			if(guardian.length()>30) {
				result.setStatus(2);
				result.setInfo("监护人姓名过长");
			}
			return result;
		}
	}

	public static CheckResult checkPhone(String guardianPhone) {
		CheckResult result = CheckResult.getInstance(guardianPhone);
		if(guardianPhone==null || "".equals(guardianPhone)) {
			return result;
		} else {
			if(guardianPhone.length()-11!=0) {
				result.setStatus(2);
				result.setInfo("手机号格式不正确");
			}
		}
		return result;
	}

	public static CheckResult checkName(String name) {
		CheckResult result = CheckResult.getInstance(name);
		if(name==null || "".equals(name)) {
			result.setStatus(2);
			result.setInfo("姓名不能为空");
		} else {
			if(name.length()>30) {
				result.setStatus(2);
				result.setInfo("姓名过长");
			}
		}
		return result;
	}
	
	public static CheckResult checkDepartment(String department) {
		CheckResult result = CheckResult.getInstance(department);
		if(department==null || "".equals(department)) {
			return result;
		} else {
			if(department.length()>10) {
				result.setStatus(2);
				result.setInfo("部门名称过长");
			}
		}
		return result;
	}
	
	public static CheckResult checkAlias(String alias) {
		CheckResult result = CheckResult.getInstance(alias);
		if(alias==null || "".equals(alias)) {
			return result;
		} else {
			if(alias.length()>6) {
				result.setStatus(2);
				result.setInfo("别名名称过长");
			}
		}
		return result;
	}
	
	
	public static CheckResult checkPosition(String position) {
		CheckResult result = CheckResult.getInstance(position);
		if(position==null || "".equals(position)) {
			return result;
		} else {
			if(position.length()>20) {
				result.setStatus(2);
				result.setInfo("职务名称过长");
			}
		}
		return result;
	}

	public static CheckResult checkTeam(String team) {
		CheckResult result = CheckResult.getInstance(team);
		if(team==null || "".equals(team)) {
			result.setStatus(2);
			result.setInfo("班级不能为空");
		} else {
			try {
				Integer tmp = Integer.parseInt(team);
				if(tmp<=0) {
					result.setStatus(2);
					result.setInfo("班级名称错误");
				}
			}catch (NumberFormatException e) {
				result.setStatus(2);
				result.setInfo("班级只能为数字");
			}
		}
		return result;
	}

	public static CheckResult checkGrade(String grade) {
		CheckResult result = CheckResult.getInstance(grade);
		if(grade==null || "".equals(grade)) {
			result.setStatus(2);
			result.setInfo("年级不能为空");
		} else {
			String value = gradeMap.get(grade);
			if(value==null) {
				result.setStatus(2);
				result.setInfo("年级格式不正确");
			}
		}
		return result;
	}
	/**
	 * 班内学号校验
	 * @param num
	 * @return
	 */
	public static CheckResult checkNum(String num) {
		CheckResult result = CheckResult.getInstance(num);
		
		if(num==null || "".equals(num)) {
			return result;
		}
		if(num.length()>20) {
			result.setStatus(2);
			result.setInfo("班内学号过长");
			return result;
		}
		num = num.trim();
		try {
			Integer.parseInt(num);
		} catch (NumberFormatException e) {
			result.setStatus(2);
			result.setInfo("班内学号只能为数字");
		}
		
		return result;
	}

	public static CheckResult checkSubjectName(String subjectName) {
		CheckResult result = CheckResult.getInstance(subjectName);
		if(subjectName == null || "".equals(subjectName)) {
			result.setStatus(2);
			result.setInfo("科目不能为空");
		}
		return result;
	}
	
	public static boolean isPhone(String mobiles){
		Pattern p = Pattern.compile("^((17[0-9])|(13[0-9])|(15[0-9])|(18[0-9]))\\d{8}$");
		Matcher m = p.matcher(mobiles);
		return m.matches();
	}
	
	
	static {
		gradeMap = new HashMap<String, String>();
		String[] gradeArray = {"一年级", "二年级", "三年级", "四年级", "五年级", "六年级",
			 "七年级", "八年级", "九年级", "初一年级", "初二年级", "初三年级", "高一年级", "高二年级", "高三年级",
			 "1年级", "2年级", "3年级", "4年级", "5年级", "6年级", "7年级", "8年级", "9年级", "初1年级", 
			 "初2年级", "初3年级", "高1年级", "高2年级", "高3年级", "一", "二", "三", "四", "五", "六", 
			 "七", "八", "九", "初一", "初二", "初三", "高一", "高二", "高三", "1","2", "3", "4",
			 "5", "6", "7", "8", "9", "初1", "初2", "初3", "高1", "高2", "高3"};
		for (String grade : gradeArray) {
			gradeMap.put(grade, grade);
		}
	}

}
