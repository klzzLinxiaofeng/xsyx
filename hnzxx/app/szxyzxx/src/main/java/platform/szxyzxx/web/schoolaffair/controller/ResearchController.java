package platform.szxyzxx.web.schoolaffair.controller;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.school.affair.model.Research;
import platform.education.school.affair.service.ResearchService;
import platform.education.school.affair.vo.ResearchCondition;
import platform.service.storage.model.EntityFile;
import platform.service.storage.service.FileService;
import platform.service.storage.vo.FileResult;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;




@Controller
@RequestMapping("/schoolaffair/research")
public class ResearchController { 
	
	private final static String viewBasePath = "/schoolaffair/research";
	
	@Autowired
	@Qualifier("researchService")
	private ResearchService researchService;
	
	@Autowired
	@Qualifier("fileService")
	private FileService fileService;
	
	@RequestMapping(value = "/index")
	public ModelAndView index(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("condition") ResearchCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) {
		String viewPath = null;
		//conditionFilter(user, condition);
		if(user != null) {
			condition.setIsDeleted(false);
			condition.setSchoolId(user.getSchoolId());
			order.setProperty(order.getProperty() != null ? order.getProperty() : "create_date");
			List<Research> items = this.researchService.findResearchByCondition(condition, page, order);
			if(items != null && items.size() > 0) {
				for(Research research:items) {
					research.setFilesUrl(builFile(research.getFiles()));
				}
			}
			String title = "";
			Integer type = condition.getType();
			if(type == 1) {
				title = "科研成果管理";
			}else if(type == 2) {
				title = "科技著作管理";
			}else if(type == 3) {
				title = "科技论文管理";
			}else if(type == 4) {
				title = "鉴定成果管理";
			}else if(type == 5) {
				title = "专利成果管理";
			}
			else if(type == 6) {
				title = "技术转让管理";
			}
			model.addAttribute("type", type);
			model.addAttribute("title", title);
			if ("list".equals(sub)) {
				viewPath = structurePath("/list");
			} else {
				viewPath = structurePath("/index");
			}
			model.addAttribute("items", items);
		}
		return new ModelAndView(viewPath, model.asMap());
	}
	
	@RequestMapping(value = "/list/json", method = RequestMethod.GET)
	@ResponseBody
	public List<Research> jsonList(
			@CurrentUser UserInfo user, 
			@ModelAttribute("condition") ResearchCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "usePage", required = false) boolean usePage) {
		//conditionFilter(user, condition);
		page = usePage ? page : null;
		return this.researchService.findResearchByCondition(condition, page, order);
	}
	
	@RequestMapping(value = "/creator", method = RequestMethod.GET)
	public ModelAndView creator(Model model,@RequestParam(value = "type", required = false) Integer type) {
		
		model.addAttribute("type", type);
		return new ModelAndView(structurePath("/input"));
	}

	@RequestMapping(value = "/creator", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation creator(Research research, @CurrentUser UserInfo user,@RequestParam(value = "publishTime", required = false) String publishTime) {
		/*Integer groupId = research.getGroupId();
		Integer appId = research.getAppId();
		if(groupId == null) {
			research.setGroupId(user.getGroupId());
		}
		if(appId == null) {
			research.setAppId(SysContants.SYSTEM_APP_ID);
		}*/
		
		if(user != null) {
			
			Date publishDate = null;
			try {
				if(publishTime != null) {
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					publishDate = format.parse(publishTime);
					research.setPublishDate(publishDate);
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			research.setSchoolId(user.getSchoolId());
			research = this.researchService.add(research);
		}
		return research != null ? new ResponseInfomation(research.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}

	@RequestMapping(value = "/editor", method = RequestMethod.GET)
	public ModelAndView editor(
			@RequestParam(value = "id", required = true) Integer id, Model model) {
		Research research = this.researchService.findResearchById(id);
		
		if(research != null) {
			model.addAttribute("fileList", builFile(research.getFiles()));
		}
		
		model.addAttribute("type", research.getType());
		model.addAttribute("research", research);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}
	
	
	
	private List<Map<String,Object>> builFile(String files) {
		List<Map<String,Object>> fileList = new ArrayList<Map<String,Object>>();
		Map<String,Object> mp = null;
			
			if(files != null && !"".equals(files) && files.contains(",")) {
				String [] filess = files.split(",");
				if(filess != null &&filess.length>0) {
					for(int i=0;i<filess.length ;i++) {
						mp = new HashMap<String,Object>();
						FileResult fileResult = this.fileService.findFileByUUID(filess[i]);
						//真实名称
						if(fileResult != null && fileResult.getEntityFile() != null) {
							
							mp.put("fileName", fileResult.getEntityFile().getFileName());
						}
						String url = fileService.relativePath2HttpUrlByUUID(filess[i]);
						mp.put("url", url);
						mp.put("uuid", filess[i]);
						fileList.add(mp);
					}
					
				}
			}else {
				mp = new HashMap<String,Object>();
				FileResult fileResult = this.fileService.findFileByUUID(files);
				//真实名称
				if(fileResult != null && fileResult.getEntityFile() != null) {
					
					mp.put("fileName", fileResult.getEntityFile().getFileName());
				}
				String url = fileService.relativePath2HttpUrlByUUID(files);
				mp.put("url", url);
				mp.put("uuid", files);
				fileList.add(mp);
			}
			
			return fileList; 
		
	}
	
	
	@RequestMapping(value = "/viewer", method = RequestMethod.GET)
	public ModelAndView viewer(
			@RequestParam(value = "id", required = true) Integer id,
			Model model) {
		Research research = this.researchService.findResearchById(id);
		if(research != null) {
			model.addAttribute("fileList", builFile(research.getFiles()));
		}
		
		model.addAttribute("isCK", "disable");
		model.addAttribute("research", research);
		return new ModelAndView(structurePath("/viewer"), model.asMap());
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable(value = "id") Integer id, Research research) {
		if (research != null) {
			research.setId(id);
		}
		try {
			research = this.researchService.findResearchById(id);
			if(research != null) {
				research.setIsDeleted(true);
				this.researchService.modify(research);
			}
		} catch (Exception e) {
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseInfomation edit(@PathVariable(value = "id") Integer id, Research research,@RequestParam(value = "publishTime", required = true) String publishTime) {
		research.setId(id);
		
		Date publishDate = null;
		try {
			if(publishTime != null) {
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				publishDate = format.parse(publishTime);
				research.setPublishDate(publishDate);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		research = this.researchService.modify(research);
		return research != null ? new ResponseInfomation(research.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}
	
	private String structurePath(String subPath) {
		return viewBasePath + subPath;
	}
	
	/*private void conditionFilter(UserInfo user, ResearchCondition condition) {
		Integer groupId = condition.getGroupId();
		Integer appId = condition.getAppId();
		if(user != null && groupId == null) {
			condition.setGroupId(user.getGroupId());
		}
		
		if(appId == null) {
			condition.setAppId(SysContants.SYSTEM_APP_ID);
		}
	}*/
}
