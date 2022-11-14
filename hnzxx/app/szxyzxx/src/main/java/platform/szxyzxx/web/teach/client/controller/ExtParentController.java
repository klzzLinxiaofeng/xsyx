package platform.szxyzxx.web.teach.client.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import platform.education.generalTeachingAffair.model.Parent;
import platform.education.generalTeachingAffair.model.ParentStudent;
import platform.education.generalTeachingAffair.model.Person;
import platform.education.generalTeachingAffair.model.Student;
import platform.education.generalTeachingAffair.model.Teacher;
import platform.education.generalTeachingAffair.model.Team;
import platform.education.generalTeachingAffair.vo.ParentStudentCondition;
import platform.education.generalTeachingAffair.vo.ParentVo;
import platform.education.generalcode.model.Item;
import platform.education.generalcode.vo.ItemCondition;
import platform.education.user.contants.GroupContants;
import platform.education.user.contants.SysDefRole;
import platform.education.user.model.Group;
import platform.education.user.model.Role;
import platform.szxyzxx.web.common.contants.SysContants;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.teach.client.vo.ExtImportErrorMsg;
import platform.szxyzxx.web.teach.client.vo.ExtParentVo;
import platform.szxyzxx.web.teach.client.vo.ExtStudentParentVo;
import platform.szxyzxx.web.teach.client.vo.ResponseError;
import platform.szxyzxx.web.teach.client.vo.ResponseInfo;
import platform.szxyzxx.web.teach.client.vo.ResponseVo;

/**
 * 家长接口
 */
@Controller
@RequestMapping("/school/parent")
public class ExtParentController extends BaseController{

private Logger log = LoggerFactory.getLogger(getClass());
	
	/**
	 * @function 导入学生家长
	 * @param schoolId
	 * @param dataJson
	 * @date 2016-2-2
	 * @return
	 */
	@RequestMapping("/data/import")
	@ResponseBody
	public Object ImportStudent(
			@RequestParam(value = "teamId", required = false) Integer teamId,
			@RequestParam(value = "data", required = false) String dataJson){
		try{
			ExtImportErrorMsg msg = null;
			Boolean relationIsNotExist = false;
			//根据班级获取学校
			Team team = teamService.findTeamById(teamId);
			
			Integer schoolId = null;
			if(team != null){
				schoolId = team.getSchoolId();
			}
			
			if(schoolId == null || "".equals(schoolId)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("参数不能为空...");
				info.setMsg("必填参数为空");
				info.setParam("schoolId");
				return new ResponseError("060111", info);
			}
			
			if(teamId == null || "".equals(teamId)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("参数不能为空...");
				info.setMsg("必填参数为空");
				info.setParam("teamId");
				return new ResponseError("060111", info);
			}
			
			List<Integer> ids = new ArrayList<Integer>();
			//用于存放错误信息
			List<ExtImportErrorMsg> msgList = new ArrayList<ExtImportErrorMsg>();
			
			//用与存放单个家长属性
			ParentVo parentVo = null;
			
			//解析JSON数据
			List<Item> itemList = this.jcGcCacheService.findItemsByTableCode("GB-JTGX");
			JSONArray data = JSONArray.fromObject(dataJson);
			if(data != null && data.size() > 0){
				for(int i = 0; i < data.size(); i++){
					JSONObject jsonJ = data.getJSONObject(i);
					Integer id = jsonJ.getInt("id");
					String name = jsonJ.getString("name");
					String mobile = jsonJ.getString("mobile");
					String relation = jsonJ.getString("relation");
					String rank = jsonJ.getString("rank");
					
					if(itemList != null && itemList.size() > 0){
						for(Item item : itemList) {
							if(item.getValue().equals(relation)){
								relationIsNotExist = true;
								break;
							}
						}
					}
					
					
					parentVo  = new ParentVo(); 
					ParentVo parentVoEnd = null;
					
					
					
					
					//2016-3-29 添加：如果家长姓名为空  使用  学生姓名+关系形式    如   张三母亲
					if(name == null || "".equals(name)){
						if(id != null){
							Student student = studentService.findStudentByUserId(id);
							if(student != null){
								if(itemList != null && itemList.size() > 0){
									for(Item item : itemList) {
										if(item.getValue().equals(relation)){
											name = student.getName() + item.getName();
											break;
										}
									}
								}
							}
						}
					}
					//parentVo  = new ParentVo(); 
					//ParentVo parentVoEnd = null;
					
					try{
						//2016-3-23  添加角色判断
						Role role = null;
						Group group = null; 
						group = this.groupService.findUnique(schoolId, GroupContants.TYPE_SCHOOL);
						if(group != null) {
							role = this.roleService.findUniqueInGroup(SysContants.SYSTEM_APP_ID, group.getId(), SysDefRole.PARENT);
							if(role != null) {
								parentVo.setRoleId(role.getId());
							}
						}
						
						parentVo.setTeamId(teamId);
						parentVo.setSchoolId(schoolId);
						parentVo.setSystem_app_id(SysContants.SYSTEM_APP_ID);
						parentVo.setStudentUserId(id);
						parentVo.setName(name);
						parentVo.setMobile(mobile);
						parentVo.setRank(rank);
						parentVo.setParentRelation(relation);

						if(rank == null || "".equals(rank)){
							parentVo.setRank("0");
						}
						
						//如果用户输入的关系不存在 不做创建操作
						if(!relationIsNotExist){
							parentVoEnd = new ParentVo();
							parentVoEnd.setErrorCode("020101");
							parentVoEnd.setDetail("关系不存在");
							msg.setStudentId(parentVoEnd.getStudentId());
							msg.setParentId(parentVoEnd.getPersonId());
							msg.setRelation(parentVoEnd.getParentRelation());
							parentVoEnd.setErrorInfo("输入的关系不存在");
						}else{
							parentVoEnd = this.parentProxyService.addInfoForParent(parentVo);
						}
						
					}catch(Exception e){
						msg = new ExtImportErrorMsg();
						msg.setId(id);
						msg.setErrorCode("000001");
						msg.setMsg("导入家长“"+name+"”时，程序发生异常，导入失败");
						msg.setStudentId(parentVoEnd.getStudentId());
						msg.setParentId(parentVoEnd.getPersonId());
						msg.setRelation(parentVoEnd.getParentRelation());
						msgList.add(msg);
					}
					
					//将家长导入的错误信息收集
					if(parentVoEnd != null){
						if(parentVoEnd.getErrorInfo() != null && !"".equals(parentVoEnd.getErrorInfo())){
							msg = new ExtImportErrorMsg();
							msg.setId(id);
							msg.setErrorCode(parentVoEnd.getErrorCode());
							msg.setMsg(parentVoEnd.getErrorInfo());
							msg.setDetail(parentVoEnd.getDetail());
							msg.setStudentId(parentVoEnd.getStudentId());
							msg.setParentId(parentVoEnd.getPersonId());
							msg.setRelation(parentVoEnd.getParentRelation());
							msgList.add(msg);
						}
					}
				}
			}
			
			if(msgList != null && msgList.size() > 0){
				for(int i=0;i<msgList.size();i++) {
					ExtImportErrorMsg importErrorMsg = msgList.get(i);
					if("031012".equals(importErrorMsg.getErrorCode())) {
						//appParent(importErrorMsg.get);
						appParent(importErrorMsg.getStudentId(),importErrorMsg.getParentId(),importErrorMsg.getRelation());
						msgList.remove(i);
						i--;
					}
				}
				return new ResponseVo<List<ExtImportErrorMsg>>("060115", msgList);
			}else{
				return new ResponseVo<List<ExtImportErrorMsg>>("0", null);
			}
			
		}catch(Exception e){
			log.debug("导入失败");
			ResponseInfo info = new ResponseInfo();
			info.setDetail("参数或参数格式出错");
			info.setMsg("未知错误");
			return new ResponseError("060112", info);
		}
	}
	
	
	/**
	 * @function 根据班级Id获取班级中学生的家长信息
	 * @param teamId
	 * @date 2016-2-22
	 * @return
	 */
	@RequestMapping("/common/list")
	@ResponseBody
	public Object getParenByTeam(@RequestParam(value = "teamId", required = false) Integer teamId){
		//存放家长属性信息
		ExtParentVo parentVo = null;
		
		//存放多个家长信息集合
		List<ExtParentVo> parentList = null;
		
		//存放单个学生与家长的属性信息
		ExtStudentParentVo esp = null;
		
		//存放所有学生与家长的属性信息
		List<ExtStudentParentVo> espList = new ArrayList<ExtStudentParentVo>();
		
		try{
			//判断是否传参
			if(teamId == null || "".equals(teamId)) {
				ResponseInfo info = new ResponseInfo();
				info.setDetail("参数不能为空...");
				info.setMsg("必填参数为空");
				info.setParam("gradeId");
				return new ResponseError("060111", info);
			}
			
			//根据班级获取学生数据
			List<Student> studentList = studentService.findStudentByTeamId(teamId);
			if(studentList != null && studentList.size() > 0){
				for(Student student : studentList){
					//存放多个家长信息集合
					parentList = new ArrayList<ExtParentVo>();
					if(student != null && student.getUserId() != null){
						List<ParentStudent> parentStudentList = parentStudentService.findParentStudentByStudentUserId(student.getUserId());
						if(parentStudentList != null && parentStudentList.size() > 0){
							for(ParentStudent parentStudent : parentStudentList){
								if(parentStudent != null && parentStudent.getParentUserId() != null){
									parentVo = new ExtParentVo();
									Parent parent = parentService.findUniqueByUserId(parentStudent.getParentUserId());
									if(parent != null){
										parentVo.setId(parent.getUserId());
										parentVo.setMobile(parent.getMobile());
										parentVo.setName(parent.getName());
										parentVo.setRelation(parentStudent.getParentRelation());
										parentVo.setRank(parentStudent.getRank());
										parentList.add(parentVo);
									}
								}
							}
						}
						
						esp = new ExtStudentParentVo();
						esp.setId(student.getUserId());
						esp.setName(student.getName());
						esp.setParents(parentList);
						espList.add(esp);
					}
				}
			}
		}catch(Exception e){
			log.debug("获取家长信息失败");
			ResponseInfo info = new ResponseInfo();
			info.setDetail("根据班级ID获取家长信息失败");
			info.setMsg("未知错误");
			return new ResponseError("000001", info);
		}
		return new ResponseVo<List<ExtStudentParentVo>>("0", espList);
	}
	
	
	
	
	private void appParent(Integer studentId,Integer parentId,String relation) {
				
				//判断所给的学生是否存在
				Student student = studentService.findStudentByUserId(studentId);
				
				//判断所给的家长是否存在,同时查询所给的家长是否为学校老师
				Parent parent = parentService.findUniqueByUserId(parentId);
				List<Teacher> teacher = teacherService.findTeacherByUserId(parentId);
				
				ItemCondition ic = new ItemCondition();
				ic.setTableCode("GB-JTGX");
				ic.setDisable(0);
				List<Item> list = itemService.findItemByCondition(ic, null, null);
				Boolean exits = false;
				if(list != null && list.size() > 0){
					for(Item item : list){
						if(item != null && (item.getValue().equals(relation) || item.getValue() == relation)){
							exits = true;
							break;
						}
					}
				}
				
				
				//判断此家长是否存在(针对teacher-家长),添加家长
				if(parent == null && teacher!=null && teacher.size()>0){
					Teacher teacher1 = teacher.get(0);
					ParentVo parentvo1 = new ParentVo();
					parentvo1.setMobile(teacher1.getMobile());
					parentvo1.setName(teacher1.getName());
					parentvo1.setUserId(teacher1.getUserId());
					parentvo1.setUserName(teacher1.getUserName());
					parentvo1.setSex(teacher1.getSex());
					parentvo1.setPersonId(teacher1.getPersionId());
					parentvo1.setIsDelete(false);
					parentvo1.setCreateDate(new Date());
					parentvo1.setModifyDate(new Date());
					
					Person person = personService.findPersonById(teacher1.getPersionId());
					if(person != null){
						parentvo1.setEmail(person.getEmail());
					}
					
					ParentVo vo = parentProxyService.addInfoForParent(parentvo1);
				}
				
				//查询数据  判断该学生和该家长是否 存在关系
				ParentStudentCondition parentStudentCondition = new ParentStudentCondition();
				parentStudentCondition.setIsDelete(false);
				parentStudentCondition.setParentUserId(parentId);
				parentStudentCondition.setStudentUserId(student.getUserId());
				List<ParentStudent> parentStudentList = parentStudentService.findParentStudentByCondition(parentStudentCondition);
				
				//创建或修改成功后 返回家长和学生的关系对象
				ParentStudent parStu = null;
				try{
					if(parentStudentList != null && parentStudentList.size() > 0){
						ParentStudent ps = parentStudentList.get(0);
						//已经存在关系，修改关系，不存在  创建
						if(ps != null){
							ps.setModifyDate(new Date());
							ps.setParentRelation(relation);
							parStu = parentStudentService.modify(ps);
						}
					}else{
						ParentStudent parentStudent = new ParentStudent();
						parentStudent.setCreateDate(new Date());
						parentStudent.setIsDelete(false);
						parentStudent.setModifyDate(new Date());
						parentStudent.setParentRelation(relation);
						parentStudent.setParentUserId(parentId);
						parentStudent.setRank(SysContants.TEACHINGAFFAIR_PARENT_RANK);
						parentStudent.setStudentName(student.getName());
						parentStudent.setStudentUserId(studentId);
						parStu = parentStudentService.add(parentStudent);
					}
				}catch(Exception e){
					e.printStackTrace();
				}
				
	}
	
	
	
	
	/**
	 * @function 添加已存在的学生和已存在的家长之间的关系 
	 * @param studentId(学生的userId)
	 * @param parentId（家长的userId）
	 * @param relation（学生和家长之间的关系）
	 * @return object
	 */
	@RequestMapping("/data/appendChild")
	@ResponseBody
	public Object appendChild(
			@RequestParam(value = "studentId", required = false) Integer studentId,
			@RequestParam(value = "parentId", required = false) Integer parentId,
			@RequestParam(value = "relation", required = false) String relation
			){
		
		//判断学生userId是否传参
		if(studentId == null || "".equals(studentId)) {
			ResponseInfo info = new ResponseInfo();
			info.setDetail("参数不能为空...");
			info.setMsg("必填参数为空");
			info.setParam("studentId");
			return new ResponseError("060111", info);
		}
		
		//判断家长userId是否传参
		if(parentId == null || "".equals(parentId)) {
			ResponseInfo info = new ResponseInfo();
			info.setDetail("参数不能为空...");
			info.setMsg("必填参数为空");
			info.setParam("parentId");
			return new ResponseError("060111", info);
		}
		
		//判断关系是否传参
		if(relation == null || "".equals(relation)) {
			ResponseInfo info = new ResponseInfo();
			info.setDetail("参数不能为空...");
			info.setMsg("必填参数为空");
			info.setParam("relation");
			return new ResponseError("060111", info);
		}
		
		//判断所给的学生是否存在
		Student student = studentService.findStudentByUserId(studentId);
		if(student == null){
			ResponseInfo info = new ResponseInfo();
			info.setDetail("学生参数找不到数据...");
			info.setMsg("学生不存在");
			info.setParam("studentId");
			return new ResponseError("020101", info);
		}
		
		//判断所给的家长是否存在,同时查询所给的家长是否为学校老师
		Parent parent = parentService.findUniqueByUserId(parentId);
		List<Teacher> teacher = teacherService.findTeacherByUserId(parentId);
		if(parent == null){
			if(!(teacher!=null && teacher.size()>0)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("家长参数找不到数据...");
				info.setMsg("家长不存在");
				info.setParam("parentId");
				return new ResponseError("020101", info);
			}
		}
		
		ItemCondition ic = new ItemCondition();
		ic.setTableCode("GB-JTGX");
		ic.setDisable(0);
		List<Item> list = itemService.findItemByCondition(ic, null, null);
		Boolean exits = false;
		if(list != null && list.size() > 0){
			for(Item item : list){
				if(item != null && (item.getValue().equals(relation) || item.getValue() == relation)){
					exits = true;
					break;
				}
			}
		}
		
		if(!exits){
			ResponseInfo info = new ResponseInfo();
			info.setDetail("关系参数找不到数据...");
			info.setMsg("关系不存在");
			info.setParam("relation");
			return new ResponseError("020101", info);
		}
		
		//判断此家长是否存在(针对teacher-家长),添加家长
		if(parent == null && teacher!=null && teacher.size()>0){
			Teacher teacher1 = teacher.get(0);
			ParentVo parentvo1 = new ParentVo();
			parentvo1.setMobile(teacher1.getMobile());
			parentvo1.setName(teacher1.getName());
			parentvo1.setUserId(teacher1.getUserId());
			parentvo1.setUserName(teacher1.getUserName());
			parentvo1.setSex(teacher1.getSex());
			parentvo1.setPersonId(teacher1.getPersionId());
			parentvo1.setIsDelete(false);
			parentvo1.setCreateDate(new Date());
			parentvo1.setModifyDate(new Date());
			
			Person person = personService.findPersonById(teacher1.getPersionId());
			if(person != null){
				parentvo1.setEmail(person.getEmail());
			}
			
			ParentVo vo = parentProxyService.addInfoForParent(parentvo1);
		}
		
		//查询数据  判断该学生和该家长是否 存在关系
		ParentStudentCondition parentStudentCondition = new ParentStudentCondition();
		parentStudentCondition.setIsDelete(false);
		parentStudentCondition.setParentUserId(parentId);
		parentStudentCondition.setStudentUserId(student.getUserId());
		List<ParentStudent> parentStudentList = parentStudentService.findParentStudentByCondition(parentStudentCondition);
		
		//创建或修改成功后 返回家长和学生的关系对象
		ParentStudent parStu = null;
		try{
			if(parentStudentList != null && parentStudentList.size() > 0){
				ParentStudent ps = parentStudentList.get(0);
				//已经存在关系，修改关系，不存在  创建
				if(ps != null){
					ps.setModifyDate(new Date());
					ps.setParentRelation(relation);
					parStu = parentStudentService.modify(ps);
				}
			}else{
				ParentStudent parentStudent = new ParentStudent();
				parentStudent.setCreateDate(new Date());
				parentStudent.setIsDelete(false);
				parentStudent.setModifyDate(new Date());
				parentStudent.setParentRelation(relation);
				parentStudent.setParentUserId(parentId);
				parentStudent.setRank(SysContants.TEACHINGAFFAIR_PARENT_RANK);
				parentStudent.setStudentName(student.getName());
				parentStudent.setStudentUserId(studentId);
				parStu = parentStudentService.add(parentStudent);
			}
		}catch(Exception e){
			e.printStackTrace();
			log.debug("添加家长学生关系失败");
			ResponseInfo info = new ResponseInfo();
			info.setDetail("添加家长学生关系失败");
			info.setMsg("未知错误");
			return new ResponseError("000001", info);
		}
		
		return new ResponseVo<ParentStudent>("0", parStu);
	}
	
	
	@RequestMapping("/data/SetMainGuardian")
	@ResponseBody
	public Object setMainGuardian(
			@RequestParam(value = "studentId", required = false) Integer studentId,
			@RequestParam(value = "parentId", required = false) Integer parentId
			){
			ParentStudent parentStudent = null;
			try{
				//判断学生userId是否传参
				if(studentId == null || "".equals(studentId)) {
					ResponseInfo info = new ResponseInfo();
					info.setDetail("参数不能为空...");
					info.setMsg("必填参数为空");
					info.setParam("studentId");
					return new ResponseError("020101", info);
				}
				
				//判断家长userId是否传参
				if(parentId == null || "".equals(parentId)) {
					ResponseInfo info = new ResponseInfo();
					info.setDetail("参数不能为空...");
					info.setMsg("必填参数为空");
					info.setParam("parentId");
					return new ResponseError("020101", info);
				}
				
				//查找学生数据是否存在
				Student student = studentService.findStudentByUserId(studentId);
				if(student ==null){
					ResponseInfo info = new ResponseInfo();
					info.setDetail("学生参数不正确...");
					info.setMsg("学生数据为空");
					info.setParam("studentId");
					return new ResponseError("020101", info);
				}
				
				Parent parent = parentService.findUniqueByUserId(parentId);
				if(parent == null){
					ResponseInfo info = new ResponseInfo();
					info.setDetail("家长参数不正确...");
					info.setMsg("家长数据为空");
					info.setParam("parentId");
					return new ResponseError("020101", info);
				}
				
				ParentStudentCondition parentStudentCondition = new ParentStudentCondition();
				parentStudentCondition.setStudentUserId(studentId);
				parentStudentCondition.setRank("1");
				List<ParentStudent> list = parentStudentService.findParentStudentByCondition(parentStudentCondition);
				if(list != null && list.size() > 0){
					ParentStudent ps = list.get(0);
					ps.setRank("0");
					parentStudentService.modify(ps);
				}
				
				ParentVo parentVo = parentStudentService.modifyMainGuardian(parentId,studentId);
				if(parentVo != null){
					ResponseInfo info = new ResponseInfo();
					info.setDetail("添加主监护人失败");
					info.setMsg(parentVo.getErrorInfo());
					return new ResponseError(parentVo.getErrorCode(), info);
				}
			}catch(Exception e){
				e.printStackTrace();
				ResponseInfo info = new ResponseInfo();
				info.setDetail("添加主监护人失败");
				info.setMsg("未知错误");
				return new ResponseError("000001", info);
			}
			return new ResponseVo<ParentStudent>("0", parentStudent);
	}
	
}
