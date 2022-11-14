package platform.szxyzxx.web.schoolaffair.controller;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import platform.education.resource.utils.DownloadUtil;
import platform.education.school.affair.model.SoftwareResources;
import platform.education.school.affair.service.SoftwareResourcesService;
import platform.education.school.affair.vo.SoftwareResourcesCondition;
import platform.education.school.affair.vo.SoftwareResourcesVo;
import platform.service.storage.model.EntityFile;
import platform.service.storage.service.FileService;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;




@Controller
@RequestMapping("/schoolaffair/softwareResources")
public class SoftwareResourcesController { 
	
	@Autowired
    @Qualifier("fileService")
    protected FileService fileService;
	
	private final static String viewBasePath = "/schoolaffair/softwareResource";
	
	@Autowired
	@Qualifier("softwareResourcesService")
	private SoftwareResourcesService softwareResourcesService;
	
	@RequestMapping(value = "/index")
	public ModelAndView index(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("condition") SoftwareResourcesCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) {
		String viewPath = null;
		conditionFilter(user, condition);
		order.setProperty(order.getProperty() != null ? order.getProperty() : "modify_date");
		List<SoftwareResources> items = this.softwareResourcesService.findSoftwareResourcesByCondition(condition, page, order);
		List<SoftwareResourcesVo> vos=new ArrayList<SoftwareResourcesVo>();
		if(items!=null&&items.size()>0) {
			Integer num=(page.getCurrentPage()-1)*page.getPageSize();
			for(SoftwareResources item:items) {
				SoftwareResourcesVo vo=new SoftwareResourcesVo();
				BeanUtils.copyProperties(item, vo);
				DecimalFormat df = new DecimalFormat("0.00"); 
				vo.setSizeString(df.format((float)vo.getSize()/1024/1024)+"MB");
				vo.setUrl(fileService.findFileByUUID(item.getFileUuid()).getHttpUrl());
				vo.setNum(++num);
				vos.add(vo);
			}
		}
		if ("list".equals(sub)) {
			viewPath = structurePath("/list");
		} else {
			viewPath = structurePath("/index");
		}
		model.addAttribute("items", vos);
		return new ModelAndView(viewPath, model.asMap());
	}
	
	@RequestMapping(value = "/list/json", method = RequestMethod.GET)
	@ResponseBody
	public List<SoftwareResources> jsonList(
			@CurrentUser UserInfo user, 
			@ModelAttribute("condition") SoftwareResourcesCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "usePage", required = false) boolean usePage) {
		conditionFilter(user, condition);
		page = usePage ? page : null;
		return this.softwareResourcesService.findSoftwareResourcesByCondition(condition, page, order);
	}
	
	@RequestMapping(value = "/creator", method = RequestMethod.GET)
	public ModelAndView creator() {
		return new ModelAndView(structurePath("/input"));
	}

	@RequestMapping(value = "/creator", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation creator(SoftwareResources softwareResources, @CurrentUser UserInfo user) {
//		Integer groupId = softwareResources.getGroupId();
//		Integer appId = softwareResources.getAppId();
//		if(groupId == null) {
//			softwareResources.setGroupId(user.getGroupId());
//		}
//		if(appId == null) {
//			softwareResources.setAppId(SysContants.SYSTEM_APP_ID);
//		}
		softwareResources.setDownloadCount(0);
		softwareResources = this.softwareResourcesService.add(softwareResources);
		return softwareResources != null ? new ResponseInfomation(softwareResources.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}

	@RequestMapping(value = "/editor", method = RequestMethod.GET)
	public ModelAndView editor(
			@RequestParam(value = "id", required = true) Integer id, Model model) {
		SoftwareResources softwareResources = this.softwareResourcesService.findSoftwareResourcesById(id);
		SoftwareResourcesVo vo=new SoftwareResourcesVo();
		if(softwareResources!=null) {
			BeanUtils.copyProperties(softwareResources,vo);
			vo.setUrl(fileService.findFileByUUID(vo.getFileUuid()).getHttpUrl());
		}
		model.addAttribute("softwareresources", vo);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}
	
	@RequestMapping(value = "/viewer", method = RequestMethod.GET)
	public ModelAndView viewer(
			@RequestParam(value = "id", required = true) Integer id,
			Model model) {
		SoftwareResources softwareResources = this.softwareResourcesService.findSoftwareResourcesById(id);
		model.addAttribute("isCK", "disable");
		model.addAttribute("softwareresources", softwareResources);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable(value = "id") Integer id, SoftwareResources softwareResources) {
		if (softwareResources != null) {
			softwareResources.setId(id);
		}
		try {
			this.softwareResourcesService.remove(softwareResources);
		} catch (Exception e) {
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseInfomation edit(@PathVariable(value = "id") Integer id, SoftwareResources softwareResources) {
		softwareResources.setId(id);
		if("".equals(softwareResources.getFileName()))softwareResources.setFileName(null);
		if("".equals(softwareResources.getFileUuid()))softwareResources.setFileUuid(null);
		softwareResources = this.softwareResourcesService.modify(softwareResources);
		return softwareResources != null ? new ResponseInfomation(softwareResources.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}
	
	private String structurePath(String subPath) {
		return viewBasePath + subPath;
	}
	
	private void conditionFilter(UserInfo user, SoftwareResourcesCondition condition) {
//		Integer groupId = condition.getGroupId();
//		Integer appId = condition.getAppId();
//		if(user != null && groupId == null) {
//			condition.setGroupId(user.getGroupId());
//		}
//		
//		if(appId == null) {
//			condition.setAppId(SysContants.SYSTEM_APP_ID);
//		}
	}
	
	@RequestMapping(value = "/downloadFile")
	public String downloadFile(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "id", required = true) Integer id)
			throws IOException {
		String downloadTitle = null;
		SoftwareResources resources = softwareResourcesService.findSoftwareResourcesById(id);
		downloadTitle = resources.getFileName();
		if (resources != null) {
			String filename = DownloadUtil.encodeFilenameForDownload(request,
					URLDecoder.decode(downloadTitle, "UTF-8"));
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/x-download");
			response.setContentLength(resources.getSize());
			response.setHeader("Content-Disposition", "attachment;filename=" + filename);
			fileService.download(resources.getFileUuid(), response.getOutputStream());
			resources.setDownloadCount(resources.getDownloadCount()+1);
			resources.setModifyDate(resources.getModifyDate());
			softwareResourcesService.modify(resources);
		}
		return null;
	}
}
