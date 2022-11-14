package platform.szxyzxx.web.teach.client.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import platform.education.generalTeachingAffair.model.DepartmentTeacher;
import platform.education.generalTeachingAffair.model.School;
import platform.education.generalTeachingAffair.model.Teacher;
import platform.education.generalTeachingAffair.vo.ExtImportTeacherErrorMsg;
import platform.education.generalTeachingAffair.vo.ExtImportTeacherVo;
import platform.education.generalTeachingAffair.vo.TeacherCondition;
import platform.education.user.model.Profile;
import platform.service.storage.vo.FileResult;
import platform.szxyzxx.web.common.contants.SysContants;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.teach.client.vo.AliasVo;
import platform.szxyzxx.web.teach.client.vo.ExtImportErrorMsg;
import platform.szxyzxx.web.teach.client.vo.ExtTeacherVo;
import platform.szxyzxx.web.teach.client.vo.ResponseError;
import platform.szxyzxx.web.teach.client.vo.ResponseInfo;
import platform.szxyzxx.web.teach.client.vo.ResponseVo;

/**
 * 教师接口
 */
@Controller
@RequestMapping("/school/teacher")
public class ExtteacherController extends BaseController{
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	/**
	 * @function 获取学校教职工名单
	 * @param schoolId
	 * @date 2016-1-27
	 * @return
	 */
	@RequestMapping("/common/list")
	@ResponseBody
	public Object getTeacherListBySchoolId(HttpServletRequest request,@RequestParam(value = "schoolId", required = false) Integer schoolId){
		try{
			//判断是否传参
			if(schoolId == null || "".equals(schoolId)) {
				ResponseInfo info = new ResponseInfo();
				info.setDetail("参数不能为空...");
				info.setMsg("必填参数为空");
				info.setParam("schoolId");
				return new ResponseError("060111", info);
			}
			
			//默认的头像位置
			String defaultUserIcon = request.getServletContext().getRealPath("/") + "res/images/no_pic.jpg";
			
			ExtTeacherVo teacherVo = null;
			List<DepartmentTeacher> departmentTeacherList = null;
			List<ExtTeacherVo> extTeacherVoList = new ArrayList<ExtTeacherVo>();
		
			List<Teacher> teacherList = this.teacherService.findTeacherListBySchoolId(schoolId);
			if(teacherList.size() != 0){
				
				for(Teacher teacher : teacherList){
					String departmentName = "";
					String userIcon = "";
					
					//获取头像
					Profile profile = this.profileService.findByUserId(teacher.getUserId());
					if (profile != null) {
						String icon = profile.getIcon();
						if(icon != null) {
							FileResult file = this.fileService.findFileByUUID(icon);
							if(file != null) {
								userIcon = file.getHttpUrl();
							}
						}
					}
					
					//如果用户没有头像  将默认头像赋给当前教师
					if("".equals(userIcon)) {
						userIcon = defaultUserIcon;
					}
					
					//根据教师ID和学校ID获取某个教师所在的所有部门
					departmentTeacherList = departmentTeacherService.findDepartmentTeacherByTeacherIdAndSchoolId(teacher.getId(),schoolId);
					if(departmentTeacherList != null){
						for(int i = 0; i < departmentTeacherList.size(); i++){
							if(i==0){
								departmentName += departmentTeacherList.get(i).getDepartmentName();
							}else{
								departmentName += "," + departmentTeacherList.get(i).getDepartmentName();
							}
						}
					}
					
					teacherVo = new ExtTeacherVo();
					teacherVo.setId(teacher.getUserId());
					teacherVo.setAlias(teacher.getAlias());
					teacherVo.setDepartment(departmentName);
					teacherVo.setSex(teacher.getSex());
					teacherVo.setMobile(teacher.getMobile());
					teacherVo.setName(teacher.getName());
					teacherVo.setPosition(teacher.getPosition());
					teacherVo.setPostStaffing(teacher.getPostStaffing());
					teacherVo.setUserName(teacher.getUserName());
					teacherVo.setUserIcon(userIcon);
					extTeacherVoList.add(teacherVo);
				}
			}
			return new ResponseVo<List<ExtTeacherVo>>("0", extTeacherVoList);
		}catch(Exception e){
			e.printStackTrace();
			log.info("通过学校ID获取学校教职工列表异常");
			ResponseInfo info = new ResponseInfo();
			info.setDetail("通过学校ID获取教职工列表异常...");
			info.setMsg("未知错误");
			return new ResponseError("000001", info);
		}
	}
	
	/**
	 * @function 导入学校教职工档案记录
	 * @param schoolId  学校ID
	 * @param autoCreateDept  是否自动创建部门
	 * @date 2016-1-27
	 * @return
	 */
	@RequestMapping(value = "/data/import")
	@ResponseBody
	public Object teacherDataImport(
			@RequestParam(value = "schoolId", required = false) Integer schoolId,
			@RequestParam(value = "autoCreateDept", required = false) Boolean autoCreateDept,
			@RequestParam(value = "data", required = false) String dataJson){
		try{
			
			if(schoolId == null || "".equals(schoolId)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("参数不能为空...");
				info.setMsg("必填参数为空");
				info.setParam("schoolId");
				return new ResponseError("060111", info);
			}
			
			if(autoCreateDept == null || "".equals(autoCreateDept)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("参数不能为空...");
				info.setMsg("必填参数为空");
				info.setParam("autoCreateDept");
				return new ResponseError("060111", info);
			}
			
			//判断是否存在学校   不存在返回数据找不到
			School school = schoolService.findSchoolById(schoolId);
			if(school==null){
				return new ResponseVo<List<ExtImportTeacherErrorMsg>>("020101", null);
			}
			
			//用于存放自检错误信息
			List<ExtImportErrorMsg> msgCheckList = new ArrayList<ExtImportErrorMsg>();
			
			//用于存放保存错误信息
			List<ExtImportErrorMsg> msgSaveList = new ArrayList<ExtImportErrorMsg>();
			
			//用于存放自检错误信息
			List<ExtImportErrorMsg> msgList = new ArrayList<ExtImportErrorMsg>();
			
			//用于存放某个教师的属性值
			ExtImportTeacherVo extImTeacher = null;
			
			//用于存放自检正确的教师
			List<ExtImportTeacherVo> trueExtImportTeacherVo = new ArrayList<ExtImportTeacherVo>();
			
			//解析JSON数据
			JSONArray data = JSONArray.fromObject(dataJson);
			if(data != null && data.size() > 0){
				AliasVo aliasVo = null;
				//2016-4-28 添加 判断别名重复
				for(int i = 0; i < data.size(); i++){
					JSONObject jsonJ = data.getJSONObject(i);
//					Integer id = jsonJ.getInt("id");
//					String name = jsonJ.getString("name");
//					String mobile = jsonJ.getString("mobile");
//					String sex = jsonJ.getString("sex");
//					String alias = jsonJ.getString("alias");
//					String department = jsonJ.getString("department");
//					String position = jsonJ.getString("position");
//					String postStaffing = jsonJ.getString("postStaffing");
					
					extImTeacher = JSON.parseObject(jsonJ.toString(), ExtImportTeacherVo.class);
					Integer id = extImTeacher.getBasic().getId();
					String name = extImTeacher.getBasic().getName();
					String alias = extImTeacher.getBasic().getAlias();
					
					aliasVo = new AliasVo();
					aliasVo.setIsCreate(false);
					
					if(alias == null || "".equals(alias)){
						alias = name;
					}
					
					//判断别名是否重复 isCreate=false 表示没有重复 不需要创建  =true表示重复 已经自动创建新的别名
					aliasVo.setAlias(alias);
					aliasVo = createDiffAliasOffTeacherInSchool(name,alias,schoolId);
					
					if(aliasVo != null && aliasVo.getIsCreate() != null && aliasVo.getIsCreate()){
						ExtImportErrorMsg msg1 = new ExtImportErrorMsg();
						msg1.setId(id);
						msg1.setDetail(aliasVo.getAlias());
						msg1.setErrorCode("111005");
						msg1.setMsg("别名重复！");
						msgCheckList.add(msg1);
					}else{
//						extImTeacher = new ExtImportTeacherVo();
//						extImTeacher.setId(id);
//						extImTeacher.setName(name);
//						extImTeacher.setMobile(mobile);
//						extImTeacher.setSex(sex);
//						extImTeacher.setAlias(alias);
//						extImTeacher.setDepartment(department);
//						extImTeacher.setPosition(position);
//						extImTeacher.setPostStaffing(postStaffing);
						trueExtImportTeacherVo.add(extImTeacher);
					}
				}
				
				if(trueExtImportTeacherVo != null && trueExtImportTeacherVo.size() > 0){
					for(ExtImportTeacherVo extImTeacherVo : trueExtImportTeacherVo){
						try{
							ExtImportTeacherErrorMsg msg = this.teacherService.ImportInfoForTeacher(extImTeacherVo, SysContants.SYSTEM_APP_ID,autoCreateDept,schoolId,SysContants.DEFAULT_PASSWORD,SysContants.USER_TYPE_TEACHER);
							//有错误信息返回客户端   2016-2-26
							if(msg != null && !"".equals(msg)) {
								if(msg.getMsg() != null && !"".equals(msg.getMsg())){
									ExtImportErrorMsg msg1 = new ExtImportErrorMsg();
//									msg1.setId(extImTeacherVo.getId());
									msg1.setId(extImTeacherVo.getBasic().getId());
									msg1.setDetail(msg.getMsg());
									msg1.setErrorCode("060115");
									msg1.setMsg(msg.getMsg());
									msgSaveList.add(msg1);
								}
							}
						}catch(Exception e){
							log.debug("导入失败");
							ExtImportErrorMsg msg1 = new ExtImportErrorMsg();
//							msg1.setId(extImTeacherVo.getId());
							msg1.setId(extImTeacherVo.getBasic().getId());
							msg1.setDetail("程序异常");
							msg1.setErrorCode("000001");
							msg1.setMsg("导入失败！");
							msgSaveList.add(msg1);
						}
						
					}
				}
			}
			
			if(msgSaveList != null && msgSaveList.size() > 0){
				msgList.addAll(msgSaveList);
			}
			if(msgCheckList != null && msgCheckList.size() > 0){
				msgList.addAll(msgCheckList);
			}
			
			if(msgList != null && msgList.size() > 0){
				return new ResponseVo<List<ExtImportErrorMsg>>("060115", msgList);
			}else{
				return new ResponseVo<List<ExtImportErrorMsg>>("0", null);
			}
			
		}catch(Exception e){
			e.printStackTrace();
			log.debug("导入失败");
			ResponseInfo info = new ResponseInfo();
			info.setDetail("导入失败");
			info.setMsg("未知错误");
			return new ResponseError("000001", info);
		}
	}
	
	/**
	 * 在一个学校中产生一个唯一的教师别名
	 * @return
	 */
	public AliasVo createDiffAliasOffTeacherInSchool(String name,String alias,Integer schoolId){
		AliasVo aliasvo = new AliasVo();
		if (alias == null || "".equals(alias)) {
			alias = name + initAlias();
		}
		Boolean flag = checkTeacherAliasIsSame(alias, schoolId);
		aliasvo.setAlias(alias);
		if (flag == true) {
			aliasvo.setIsCreate(true);
			alias = "";
			alias = name + initAlias();
			aliasvo.setAlias(alias);
			createDiffAliasOffTeacherInSchool(name, alias, schoolId);
		}
		return aliasvo;
	}
	
	/**
	 * 生成别名规则 2015-11-19
	 * 
	 * @return
	 */
	public String initAlias() {
		Date date = new Date();
		Long dtime = date.getTime();
		String alias = dtime.toString().substring(
				dtime.toString().length() - 3, dtime.toString().length());
		return alias;
	}
	
	public Boolean checkTeacherAliasIsSame(String alias, Integer schoolId) {
		Boolean flag = false;
		TeacherCondition teacherCondition = new TeacherCondition();
		teacherCondition.setSchoolId(schoolId);
		teacherCondition.setAlias(alias);
		teacherCondition.setIsDelete(false);
		List<Teacher> teacherList = teacherService.findTeacherByCondition(teacherCondition, null, null);
		if (teacherList.size() > 0) {
			flag = true;
		}
		return flag;
	}
	
}
