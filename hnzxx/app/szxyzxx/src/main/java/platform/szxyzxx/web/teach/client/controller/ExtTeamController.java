package platform.szxyzxx.web.teach.client.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import platform.education.generalTeachingAffair.model.Person;
import platform.education.generalTeachingAffair.model.Student;
import platform.education.generalTeachingAffair.vo.TeamStudentVo;
import platform.education.user.model.Profile;
import platform.service.storage.vo.FileResult;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.UserIconUtil;
import platform.szxyzxx.web.teach.client.vo.ExtTeamStuPhoto;
import platform.szxyzxx.web.teach.client.vo.ExtTeamStudentVo;
import platform.szxyzxx.web.teach.client.vo.ResponseError;
import platform.szxyzxx.web.teach.client.vo.ResponseInfo;
import platform.szxyzxx.web.teach.client.vo.ResponseVo;

@Controller
@RequestMapping("/school/team")
public class ExtTeamController extends BaseController{
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	/**
	 * 功能描述：获取班级学生名单
	 * 2016-01-12
	 * @param gradeId
	 * @return
	 */
	@RequestMapping("/student/list")
	@ResponseBody
	public Object getStudentList(HttpServletRequest request,
			@RequestParam(value = "teamId", required = false) Integer teamId) {
		
		List<TeamStudentVo> teamStudentVoList = new ArrayList<TeamStudentVo>();
		List<ExtTeamStudentVo> teamStudents = new ArrayList<ExtTeamStudentVo>();
		try{
			
			if(teamId == null || "".equals(teamId)) {
				ResponseInfo info = new ResponseInfo();
				info.setDetail("参数不能为空...");
				info.setMsg("必填参数为空");
				info.setParam("teamId");
				return new ResponseError("060111", info);
			}
			Student student = null;
			teamStudentVoList = this.teamStudentService.getTeamStudentsByTeamId(teamId);
			//默认的头像位置
			String defaulUrl = request.getServletContext().getRealPath("/") + "res/images/no_pic.jpg";
			for (TeamStudentVo vo : teamStudentVoList) {
				String url = UserIconUtil.getImgSrc(vo.getUserId(), request, profileService);
				student = studentService.findStudentById(vo.getStudentId());
				ExtTeamStudentVo teamStudentVo = new ExtTeamStudentVo();
				teamStudentVo.setTeamStudentId(vo.getId());
				if("".equals(url)){
					teamStudentVo.setUserIcon(defaulUrl);
				}else{
					teamStudentVo.setUserIcon(url);
				}
				teamStudentVo.setAlias(student == null ? null : student.getAlias());
				teamStudentVo.setId(vo.getUserId());
				teamStudentVo.setStudentId(vo.getStudentId());
				teamStudentVo.setName(vo.getName());
				teamStudentVo.setSex(vo.getSex());
				teamStudentVo.setNumber(vo.getNumber());
				teamStudentVo.setStudentNumber(vo.getStudentNumber());
				teamStudentVo.setUserName(vo.getUserName());
				teamStudents.add(teamStudentVo);
			}
			
			return new ResponseVo<List<ExtTeamStudentVo>>("0", teamStudents);
			
		}catch(Exception e){
			log.info("通过班级ID获取班级学生名单异常");
			ResponseInfo info = new ResponseInfo();
			info.setDetail("通过班级ID获取班级学生名单异常...");
			info.setMsg("未知错误");
			return new ResponseError("000001", info);
		}
	}
	
	@RequestMapping("/student/photo/list")
	@ResponseBody
	public Object getStudentPhotoList(HttpServletRequest request,
			@RequestParam(value = "teamId", required = false) Integer teamId) {
		
		List<TeamStudentVo> teamStudentVoList = new ArrayList<TeamStudentVo>();
		List<ExtTeamStuPhoto> teamStudents = new ArrayList<ExtTeamStuPhoto>();
		try{
			if(teamId == null || "".equals(teamId)) {
				ResponseInfo info = new ResponseInfo();
				info.setDetail("参数不能为空...");
				info.setMsg("必填参数为空");
				info.setParam("teamId");
				return new ResponseError("060111", info);
			}
			Student student = null;
			teamStudentVoList = this.teamStudentService.getTeamStudentsByTeamId(teamId);
			ExtTeamStuPhoto extTeamStuPhoto = null;
			for (TeamStudentVo vo : teamStudentVoList) {
				student = studentService.findStudentById(vo.getStudentId());
				if(student != null){
					extTeamStuPhoto = new ExtTeamStuPhoto();
					extTeamStuPhoto.setId(student.getUserId());
					extTeamStuPhoto.setName(student.getName());
					extTeamStuPhoto.setStudentId(student.getId());
					Person person = this.personService.findPersonById(student.getPersonId());
					if(person != null){
						if(person.getPhotoUuid() != null && !"".equals(person.getPhotoUuid())){
							//有照片
							String photoUrl = this.fileService.relativePath2HttpUrlByUUID(person.getPhotoUuid());
							extTeamStuPhoto.setPhotoUrl(photoUrl);
						}else{
							//无照片
							extTeamStuPhoto.setPhotoUrl("");
						}
					}
					teamStudents.add(extTeamStuPhoto);
				}
			}
			
			return new ResponseVo<List<ExtTeamStuPhoto>>("0", teamStudents);
			
		}catch(Exception e){
			log.info("通过班级ID获取班级学生名单异常");
			ResponseInfo info = new ResponseInfo();
			info.setDetail("通过班级ID获取班级学生名单异常...");
			info.setMsg("未知错误");
			return new ResponseError("000001", info);
		}
	}
	
	
}
