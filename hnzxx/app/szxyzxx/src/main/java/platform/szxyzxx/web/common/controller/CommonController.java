package platform.szxyzxx.web.common.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import platform.education.generalTeachingAffair.vo.DepartmentCondition;
import platform.service.storage.contants.FileStatusCode;
import platform.service.storage.model.EntityFile;
import platform.service.storage.vo.FileResult;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.contants.SysContants;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.vo.UserInfo;
/**
 * <p>Title:HomeController.java</p>
 * <p>Description:数字校园系统</p>
 * <p>Copyright: Copyright (c) 2010-2015</p>
 * <p>Company: 广州迅云教育科技有限公司</p>
 * @Function 功能描述：管理系统入口
 * @Author 潘维良
 * @Version 1.0
 * @Date 2014年7月27日  MAVEN
 */
@Controller
@RequestMapping(value = "/common")
public class CommonController extends BaseController {
	
	@RequestMapping("/ryxx/selector")
	public ModelAndView ryxxSelector(
			Model model,
			@RequestParam("pageT") String pageT,
			@RequestParam("id") String id,
			@RequestParam("isTop") boolean fasle,
			@RequestParam("targetWN") String targetWN,
			@RequestParam(value = "selectSubject" , required = false) String selectSubject,
			@RequestParam(value = "enableMultiCampus" , required = false) String enableMultiCampus,
			@RequestParam(value = "selectSubjectForbidden" ,required = false) boolean selectSubjectForbidden,
			@RequestParam(value = "excludeSelf", defaultValue = "false") boolean excludeSelf,
			@RequestParam(value = "enableBatch", defaultValue = "true") boolean enableBatch) {
		String url = "";
		if(pageT.equals("stu") || pageT.equals("teach")){
			url = "/common/selector/" + pageT + "_selector";
		}
		model.addAttribute("stuType", pageT);
		model.addAttribute("selectSubjectForbidden", selectSubjectForbidden);
		model.addAttribute("enableMultiCampus", enableMultiCampus);
		model.addAttribute("selectSubject", selectSubject);
		model.addAttribute("excludeSelf", excludeSelf);
		return new ModelAndView(url,model.asMap());
	}
	
	/**
	 * oa人员选择组件跳转页面
	 * @param model
	 * @param pageT
	 * @param id
	 * @param fasle
	 * @param targetWN
	 * @param selectSubject
	 * @param selectSubjectForbidden
	 * @param excludeSelf
	 * @param enableBatch
	 * @return
	 */
	@RequestMapping("/ryxx/oaSelector")
	public ModelAndView ryxxOaSelector(
			Model model,
			@RequestParam("pageT") String pageT,
			@RequestParam("id") String id,
			@RequestParam("isTop") boolean fasle,
			@RequestParam("targetWN") String targetWN,
			@RequestParam(value = "selectSubject" , required = false) String selectSubject,
			@RequestParam(value = "selectSubjectForbidden" ,required = false) boolean selectSubjectForbidden,
			@RequestParam(value = "excludeSelf", defaultValue = "false") boolean excludeSelf,
			@RequestParam(value = "enableBatch", defaultValue = "true") boolean enableBatch) {
		String url = "";
		if(pageT.equals("stu") || pageT.equals("teach")){
			url = "/common/selector/oa_" + pageT + "_selector";
		}
		model.addAttribute("stuType", pageT);
		model.addAttribute("selectSubjectForbidden", selectSubjectForbidden);
		model.addAttribute("selectSubject", selectSubject);
		model.addAttribute("excludeSelf", excludeSelf);
		return new ModelAndView(url,model.asMap());
	}
	
	/**
	 * oa部门选择组件跳转页面
	 * @param model
	 * @param id
	 * @param fasle
	 * @param targetWN
	 * @param schoolId
	 * @param enableBatch
	 * @return
	 */
	@RequestMapping("/ryxx/oaDepartSelector")
	public ModelAndView ryxxOaDepartSelector(
			Model model,
			@RequestParam("id") String id,
			@RequestParam("isTop") boolean fasle,
			@RequestParam("targetWN") String targetWN,
			@RequestParam(value = "enableBatch", defaultValue = "true") boolean enableBatch) {
		String url = "/common/selector/oa_depart_selector";
		return new ModelAndView(url,model.asMap());
	}
	
	
	
	
	
	//oa部门选择组件跳转页面
	public ModelAndView oaDepartSelector(Model model,
			@RequestParam(value = "enableBatch", defaultValue = "true") boolean enableBatch){
		String url="/common/selector/oa_depart_selector";
		
		
		return new ModelAndView(url,model.asMap());
	}
	
	
	
	
	@RequestMapping("/cropperAvatar")
	public ModelAndView cropperAvatar(Model model,@RequestParam(value = "isTop",defaultValue = "false") boolean isTop) {
		String url = "common/selector/cropper";
		model.addAttribute("isTop", isTop);
		return new ModelAndView(url,model.asMap());
	}
	
	@RequestMapping("/bjxx/selector")
	public ModelAndView bjxxSelector(
			@RequestParam("idTo") String idTo,
			@RequestParam("titleTo") String titleTo) {
		return new ModelAndView("/common/selector/bjxx_selector");
	}
	
	
	@RequestMapping("/avartar/editor")
	public ModelAndView zpEditor() {
		return new ModelAndView("/common/edit_avatar");
	}
	
	@RequestMapping(value = "/portrait/uploader")
	public String uploadPortrait(
			HttpServletRequest request, 
			HttpServletResponse response, 
			@CurrentUser UserInfo userInfo) {
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			if(userInfo != null) {
				InputStream in = request.getInputStream();
//	            Integer userId = userInfo.getId();
	            FileResult result = this.fileService.upload(in, "jpg", "", "", String.valueOf(SysContants.SYSTEM_APP_ID));
	            if (result != null && FileStatusCode.UPLOAD_SUCCESS.equals(result.getStatusCode())) {
	            	EntityFile entityFile = result.getEntityFile();
//	            	String filePath_162 = file.getAbsolutePath();
//	            	ZoomImage zoomImg = new ZoomImage();
//	            	zoomImg.createZoomSizeImage(filePath_162, filePath_162.replace("_180", "_90"), 0.5);
//	            	zoomImg.createZoomSizeImage(filePath_162, filePath_162.replace("_180", "_35"), 0.195);
					String convertedUrl = result.getHttpUrl();
	            	writer.write("{\"code\" : 200, \"uuid\" : \"" + entityFile.getUuid() + "\", \"convertedUrl\" : \"" + convertedUrl + "\"}");
	            } else {
             		writer.write("{\"code\" : -200}");
            	}
			}
		} catch (IOException e) {
			writer.write("{\"code\" : -200}");
			e.printStackTrace();
		} finally {
			if(writer != null) {
				writer.flush();
				writer.close();
				writer = null;
			}
		}
		return null;
	}


	@RequestMapping("/teacher/selector")
	public ModelAndView teacherSelector(
			Model model,
			@RequestParam("pageT") String pageT,
			@RequestParam("id") String id,
			@RequestParam("isTop") boolean fasle,
			@RequestParam("targetWN") String targetWN,
			@RequestParam(value = "selectSubject" , required = false) String selectSubject,
			@RequestParam(value = "enableMultiCampus" , required = false) String enableMultiCampus,
			@RequestParam(value = "selectSubjectForbidden" ,required = false) boolean selectSubjectForbidden,
			@RequestParam(value = "excludeSelf", defaultValue = "false") boolean excludeSelf,
			@RequestParam(value = "enableBatch", defaultValue = "true") boolean enableBatch) {
		String url = "";
		if(pageT.equals("teachInfo")){
			url = "common/selector/" + pageT + "_selector";
		}
		model.addAttribute("stuType", pageT);
		model.addAttribute("selectSubjectForbidden", selectSubjectForbidden);
		model.addAttribute("enableMultiCampus", enableMultiCampus);
		model.addAttribute("selectSubject", selectSubject);
		model.addAttribute("excludeSelf", excludeSelf);
		return new ModelAndView(url,model.asMap());
	}


	@RequestMapping("/time/selector")
	public ModelAndView timeSelector(
			Model model,
			@RequestParam("pageT") String pageT,
			@RequestParam("id") String id,
			@RequestParam("isTop") boolean fasle,
			@RequestParam("targetWN") String targetWN,
			@RequestParam(value = "selectSubject" , required = false) String selectSubject,
			@RequestParam(value = "enableMultiCampus" , required = false) String enableMultiCampus,
			@RequestParam(value = "selectSubjectForbidden" ,required = false) boolean selectSubjectForbidden,
			@RequestParam(value = "excludeSelf", defaultValue = "false") boolean excludeSelf,
			@RequestParam(value = "enableBatch", defaultValue = "true") boolean enableBatch) {
		String url = "";
		if(pageT.equals("time")){
			url = "common/selector/" + pageT + "_selector";
		}
		model.addAttribute("stuType", pageT);
		model.addAttribute("selectSubjectForbidden", selectSubjectForbidden);
		model.addAttribute("enableMultiCampus", enableMultiCampus);
		model.addAttribute("selectSubject", selectSubject);
		model.addAttribute("excludeSelf", excludeSelf);
		return new ModelAndView(url,model.asMap());
	}
	@RequestMapping("/student/selector")
	public ModelAndView studentSelector(
			Model model,
			@RequestParam("pageT") String pageT,
			@RequestParam("id") String id,
			@RequestParam("isTop") boolean fasle,
			@RequestParam("targetWN") String targetWN,
			@RequestParam(value = "selectSubject" , required = false) String selectSubject,
			@RequestParam(value = "enableMultiCampus" , required = false) String enableMultiCampus,
			@RequestParam(value = "selectSubjectForbidden" ,required = false) boolean selectSubjectForbidden,
			@RequestParam(value = "excludeSelf", defaultValue = "false") boolean excludeSelf,
			@RequestParam(value = "enableBatch", defaultValue = "true") boolean enableBatch) {
		String url = "";
		if(pageT.equals("stu")){
			url = "common/selector/" + pageT + "_selector";
		} else if(pageT.equals("teacher_stu")){
			url = "common/selector/" + pageT + "_selector";
		}
		model.addAttribute("stuType", pageT);
		model.addAttribute("selectSubjectForbidden", selectSubjectForbidden);
		model.addAttribute("enableMultiCampus", enableMultiCampus);
		model.addAttribute("selectSubject", selectSubject);
		model.addAttribute("excludeSelf", excludeSelf);
		return new ModelAndView(url,model.asMap());
	}

//	private String buildPortraitPath(String userName, String fileName) {
//		StringBuilder path = new StringBuilder("/user/portrait/");
//		path.append(userName).append("/").append(fileName);
//		return  path.toString();
//	}
	
}
