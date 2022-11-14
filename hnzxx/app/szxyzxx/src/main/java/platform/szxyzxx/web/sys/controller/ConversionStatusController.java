package platform.szxyzxx.web.sys.controller;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import platform.basis.converter.doc.client.service.DocConversionClient;
import platform.basis.converter.jave.client.service.JaveConversionClient;
import platform.service.storage.service.ConversionStatusService;
import platform.service.storage.service.FileService;
import platform.service.storage.vo.ConversionStatusResultVo;
import platform.service.storage.vo.FileConversionResult;
import platform.service.storage.vo.FileResult;

import java.util.List;

import javax.annotation.Resource;

@Controller
@RequestMapping("/conversion/status")
public class ConversionStatusController {
	
	private final static String viewBasePath = "/sys/conversion/";
	
	@Autowired
	@Qualifier("conversionStatusService")
	private ConversionStatusService conversionStatusService;
	
	@Resource
	private DocConversionClient docConversonClient;
	
	@Resource
	private FileService fileService;
	
	@Resource
	private JaveConversionClient javeConversonClient;
	
	@RequestMapping(value = "/index")
	public ModelAndView conversionIndex(
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "type", required = false, defaultValue = "VID") String type,
			Model model){
		String url = "";
		List<ConversionStatusResultVo> ConversionStatusResultVoList = conversionStatusService.findConversionStillWorking(type,page,order);
		model.addAttribute("ConversionStatusResultVoList", ConversionStatusResultVoList);
		model.addAttribute("type",type);
		if ("list".equals(sub)) {
			url = viewBasePath+"list";
		} else {
			url = viewBasePath+"index";
		}
		return new ModelAndView(url, model.asMap());
	}

	/**
	 * @function 跳转详细页面
	 * @param uuid
	 * @param model
     * @return
     */
	@RequestMapping(value = "/detailed")
	public ModelAndView detailed(
			@RequestParam(value = "uuid", required = true) String uuid,
			Model model){
		model.addAttribute("uuid",uuid);
		return new ModelAndView(viewBasePath+"progress", model.asMap());
	}

	/**
	 * @function 定时获取转码进度
	 * @param uuid
     * @return
     */
	@RequestMapping(value = "/getDetailedByTime",method = RequestMethod.POST)
	@ResponseBody
	public Object getDetailedByTime(
			@RequestParam(value = "uuid", required = true) String uuid){
		return conversionStatusService.findBySourceFileUUID(uuid);
	}
	
	/**
	 * @function 重新请求转换，主要针对转换失败的文件
	 * @param uuid
	 * @return
	 */
	@RequestMapping(value = "/conversionAgain",method = RequestMethod.POST)
	@ResponseBody
	public String conversionAgain(
			@RequestParam(value = "uuid", required = true) String uuid,
			@RequestParam(value = "type", required = true) String type){
		if(uuid == null || "".equals(uuid)){
			return "UUID IS NULL";
		}
		FileResult file = fileService.findFileByUUID(uuid);
		if(file == null){
			return "FILE IS NOT EXITS";
		}
		if(type == null || "".equals(type)){
			return "type IS NULL";
		}
		if("DOC".equals(type)){
			//文档转换先注释掉。
			docConversonClient.submitConversionJobAgain(uuid, null, null);
		}else if("VID".equals(type)){
			javeConversonClient.submitJaveConversionJobAgain(uuid);
		}else{
			return "Unknown type";
		}
		return "SUCCESS";
	}
}
