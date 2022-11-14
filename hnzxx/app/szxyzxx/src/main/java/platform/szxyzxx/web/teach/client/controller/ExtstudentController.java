package platform.szxyzxx.web.teach.client.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import platform.education.generalTeachingAffair.model.SchoolUser;
import platform.education.generalTeachingAffair.model.Student;
import platform.education.generalTeachingAffair.model.Team;
import platform.education.generalTeachingAffair.model.TeamStudent;
import platform.education.generalTeachingAffair.service.SchoolUserService;
import platform.education.generalTeachingAffair.vo.ExtImportErrorMsg;
import platform.education.generalTeachingAffair.vo.ExtImportStudentVo;
import platform.education.generalTeachingAffair.vo.SchoolUserCondition;
import platform.education.generalTeachingAffair.vo.StudentCondition;
import platform.szxyzxx.web.common.contants.SysContants;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.teach.client.vo.AliasVo;
import platform.szxyzxx.web.teach.client.vo.ResponseError;
import platform.szxyzxx.web.teach.client.vo.ResponseInfo;
import platform.szxyzxx.web.teach.client.vo.ResponseVo;

/**
 * 教师接口
 */
@Controller
@RequestMapping("/school/student")
public class ExtstudentController extends BaseController{

private Logger log = LoggerFactory.getLogger(getClass());
	@Resource
	private SchoolUserService schoolUserService;
	
	public void setSchoolUserService(SchoolUserService schoolUserService) {
		this.schoolUserService = schoolUserService;
	}

	/**
	 * @function 导入班级学生
	 * @param teamId
	 * @param dataJson
	 * @param isBindingMobile
	 * @date 2016-1-27
	 * @return
	 */
	@RequestMapping("/data/import")
	@ResponseBody
	public Object ImportStudent(
			@RequestParam(value = "teamId", required = false) Integer teamId,
			@RequestParam(value = "data", required = false) String dataJson,
			@RequestParam(value = "isBindingMobile", required = false) Boolean isBindingMobile){
		try{
			if(isBindingMobile == null){
				isBindingMobile = false;
			}
			if(teamId == null || "".equals(teamId)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("参数不能为空...");
				info.setMsg("必填参数为空");
				info.setParam("teamId");
				return new ResponseError("060111", info);
			}
			
			//用于存放错误信息
			List<ExtImportErrorMsg> msgList = new ArrayList<ExtImportErrorMsg>();
			
			//用于存放一开始就检测的错误信息
			List<ExtImportErrorMsg> msgCheckList = new ArrayList<ExtImportErrorMsg>();
			
			//用于存放保存过程中错误信息
			List<ExtImportErrorMsg> msgSaveList = new ArrayList<ExtImportErrorMsg>();
			
			//用与存放单个学生属性
			ExtImportStudentVo extImportStudentVo = null;
			
			//存放自检正确的学生集合
			List<ExtImportStudentVo> extImportStudentVoList = new ArrayList<ExtImportStudentVo>();
			
			//存放导入的错误信息
			ExtImportErrorMsg msg = null;
			
			//根据班级获取学校
			Team team = teamService.findTeamById(teamId);
			
			Integer schoolId = null;
			if(team != null){
				schoolId = team.getSchoolId();
			}
			
			if(schoolId == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("该班级所在学校不存在...");
				info.setMsg("班级所在学校不存在");
				info.setParam("teamId");
				return new ResponseError("060111", info);
			}
			
			if(team == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("该班级所在学校不存在...");
				info.setMsg("班级所在学校不存在");
				info.setParam("teamId");
				return new ResponseError("060111", info);
			}
			
			//解析JSON数据
			JSONArray data = JSONArray.fromObject(dataJson);
			
			//导入数据之前的检验   判断名字和别名的重复  校验同班
			AliasVo aliasVo = null;
			
			if(data != null && data.size() > 0){
				//判断学校内是否重复
				SchoolUserCondition schoolUserCondition = new SchoolUserCondition();
				schoolUserCondition.setSchoolId(schoolId);
				List<SchoolUser> schoolUserList = schoolUserService.findSchoolUserByCondition(schoolUserCondition, null, null);
				//判断班级学号是否重复 ,num是学号计数
				List<TeamStudent> teamStudentList = teamStudentService.findByTeamId(teamId);
				Integer num = 0;
				ArrayList<Integer> nlist = new ArrayList<>();
				
				for(int i = 0; i < data.size(); i++){
					JSONObject jsonJ = data.getJSONObject(i);
					Integer id = jsonJ.getInt("id");
					String name = jsonJ.getString("name");
					String sex = jsonJ.getString("sex");
					String alias = jsonJ.getString("alias");
					String position = jsonJ.getString("position");
					String number = jsonJ.getString("number");
					
					//2016-4-25 添加别名判断
					if(alias == null || "".equals(alias)){
						alias = name;
					}
					//与数据库已存在的数据做对比  如果存在同名  并且别名为空   或者别名一样的  记录错误信息  返回错误信息
					if(schoolUserList != null && schoolUserList.size() > 0){
						for(SchoolUser schoolUser : schoolUserList){
							if(name.equals(schoolUser.getName())){
								if(alias == null || "".equals(alias) || alias.equals(schoolUser.getAlias())){
									msg = new ExtImportErrorMsg();
									msg.setId(id);
									msg.setMsg("用户输入的姓名以及别名重复");
									msgCheckList.add(msg);
								}
							}
						}
					}
					aliasVo = new AliasVo();
					aliasVo.setIsCreate(false);
					aliasVo.setAlias(alias);
					aliasVo = createDiffAliasOffStudentInTeam(name,alias,teamId);
					
					if(aliasVo != null && aliasVo.getIsCreate() != null && aliasVo.getIsCreate()){
						ExtImportErrorMsg msg1 = new ExtImportErrorMsg();
						msg1.setId(id);
						msg1.setDetail(aliasVo.getAlias());
						msg1.setErrorCode("111005");
						msg1.setMsg("别名重复！");
						msgCheckList.add(msg1);
					}else{
						extImportStudentVo = new ExtImportStudentVo();
						extImportStudentVo.setId(id);
						extImportStudentVo.setName(name);
						extImportStudentVo.setSex(sex);
						extImportStudentVo.setAlias(alias);
						extImportStudentVo.setPosition(position);
						
						if ("".equals(number)) {
//							extImportStudentVo.setNumber(null);
							//判断班级学号是否重复
							if(teamStudentList != null && teamStudentList.size() > 0){
								for (TeamStudent teamStudent : teamStudentList) {
									nlist.add(teamStudent.getNumber());
								}
							}
							for (int j = 0; j < teamStudentList.size() + 1; j++) {
								num++;
								if(!nlist.contains(num)){
									extImportStudentVo.setNumber(num);
									nlist.add(num);
									break;
								}
							}
						}else {
							extImportStudentVo.setNumber(Integer.valueOf(number.trim()));
						}
						extImportStudentVoList.add(extImportStudentVo);
					}
					
					//与传过来的JSON数据对比  如果传过来的JSON中有重名  并且别名不填   或者 重名 重别名的  记录该条信息  返回
					for(int j = i+1; j < data.size(); j++){
						JSONObject json = data.getJSONObject(j);
						Integer nextId = json.getInt("id");
						String nextName = json.getString("name");
						String nextAlias = json.getString("alias");
						if(name.equals(nextName)){
							if(alias.equals(nextAlias) || "".equals(nextAlias)){
								msg = new ExtImportErrorMsg();
								msg.setId(nextId);
								msg.setMsg("用户输入的姓名以及别名重复");
								msgCheckList.add(msg);
							}
						}
					}
					
				}
				
			}
			
			if(extImportStudentVoList != null && extImportStudentVoList.size() > 0){
				for(ExtImportStudentVo extStudentVo : extImportStudentVoList){
					try{
						//该方法于2016-2-26多加了一个参数   用于客户端的导入时将学生添加到班级
						msg = new ExtImportErrorMsg();
						msg = this.studentService.ImportInfoForStudent(extStudentVo,SysContants.DEFAULT_PASSWORD,SysContants.USER_TYPE_STUDENT, SysContants.SYSTEM_APP_ID, schoolId,teamId,isBindingMobile);
						//将学生导入的错误信息收集
						if(msg != null && !"".equals(msg)) {
							if(msg.getMsg() != null && !"".equals(msg.getMsg())){
								msgSaveList.add(msg);
							}
						}
					}catch(Exception e){
						log.debug("导入失败");
						ExtImportErrorMsg msg1 = new ExtImportErrorMsg();
						msg1.setId(extStudentVo.getId());
						msg1.setDetail("程序异常");
						msg1.setErrorCode("");
						msg1.setMsg("导入失败！");
						msgSaveList.add(msg1);
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
	 * 在一个班级中产生一个唯一的学生别名
	 * @return
	 */
	public AliasVo createDiffAliasOffStudentInTeam(String name,String alias,Integer teamId){
		AliasVo aliasvo = new AliasVo();
		if (alias == null || "".equals(alias)) {
			alias = name + initAlias();
		}
		Boolean flag = checkStudentAliasIsSame(alias, teamId);
		aliasvo.setAlias(alias);
		if (flag == true) {
			alias = "";
			alias = name + initAlias();
			aliasvo.setAlias(alias);
			createDiffAliasOffStudentInTeam(name, alias, teamId);
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
	
	public Boolean checkStudentAliasIsSame(String alias, Integer teamId) {
		Boolean flag = false;
		Team team = teamService.findTeamById(teamId);
		if(team != null){
			StudentCondition studentCondition = new StudentCondition();
			studentCondition.setAlias(alias);
			studentCondition.setDelete(false);
			studentCondition.setGradeId(team.getGradeId());
			studentCondition.setTeamId(team.getId());
			List<Student> studentList = studentService.findStudentByCondition(studentCondition,null, null);
			if (studentList.size() > 0) {
				flag = true;
			}
		}
		return flag;
	}
}
