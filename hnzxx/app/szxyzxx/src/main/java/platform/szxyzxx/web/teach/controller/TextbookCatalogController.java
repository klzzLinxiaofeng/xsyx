package platform.szxyzxx.web.teach.controller;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import platform.education.generalcode.model.TextbookCatalog;
import platform.education.generalcode.vo.TextbookCatalogCondition;
import platform.education.generalcode.vo.TextbookCatalogVo;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.util.poi.excel.main.SzxyExcelTookit;
import platform.szxyzxx.web.common.vo.UserInfo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.facility.poi.excel.config.ParseConfig;


@Controller
@RequestMapping("/teach/textBookMaster/textBookCatalog")
public class TextbookCatalogController  extends BaseController{ 
	private Logger log = LoggerFactory.getLogger(getClass());
	private final static String viewBasePath = "/teach/textBookMaster/textBookCatalog";
	
	
	@RequestMapping(value = "/index")
	public ModelAndView index(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@RequestParam(value = "textBookId", required = false) Integer textBookId,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) {
		String viewPath = null;
		ModelAndView mav = new ModelAndView();
		//order.setProperty(order.getProperty() != null ? order.getProperty() : "create_date");
		//List<TextbookCatalog> items = this.jcTextbookCatalogService.findTextbookCatalogByCondition(condition, page, order);
		TextbookCatalogVo root = new TextbookCatalogVo();

		List<TextbookCatalogVo> resultList = new ArrayList<TextbookCatalogVo>();
		
		if(textBookId != null && textBookId >0){
			root = this.jcTextbookMasterService.findTextbookCatalogList(textBookId);
			
		}
		if(root.getId() != null && root.getId() >0){
			resultList = this.jcTextbookMasterService.nodeOutListExcludeQuote(root);
		}
		mav.setViewName(structurePath("/index"));
		mav.addObject("catalogList", resultList);
		if ("list".equals(sub)) {
			viewPath = structurePath("/list");
		} else {
			viewPath = structurePath("/index");
		}
		mav.setViewName(viewPath);
		mav.addObject("catalogList", resultList);
		return mav;
	}
	
	@RequestMapping(value = "/list/json", method = RequestMethod.GET)
	@ResponseBody
	public List<TextbookCatalog> jsonList(
			@CurrentUser UserInfo user, 
			@ModelAttribute("condition") TextbookCatalogCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "usePage", required = false) boolean usePage) {
		
		page = usePage ? page : null;
		return this.jcTextbookCatalogService.findTextbookCatalogByCondition(condition, page, order);
	}
	
	@RequestMapping(value = "/creator", method = RequestMethod.GET)
	public ModelAndView creator() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName(structurePath("/input"));
		return mav;
	}

	@RequestMapping(value = "/creator", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation creator(TextbookCatalog jcTextbookCatalog, @CurrentUser UserInfo user) {
		
		ResponseInfomation responseInfomation = new ResponseInfomation(ResponseInfomation.OPERATION_SUC);
		TextbookCatalog jcTextbookCatalogEnd = null;
		try {
			jcTextbookCatalogEnd = this.jcTextbookCatalogService.add(jcTextbookCatalog);
			responseInfomation = jcTextbookCatalogEnd != null?responseInfomation:new ResponseInfomation(ResponseInfomation.OPERATION_FAIL);
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
			responseInfomation = new ResponseInfomation(getMessage("???????????????????????????", e));
		}
		
		return responseInfomation;
		
	}

	@RequestMapping(value = "/editor", method = RequestMethod.GET)
	public ModelAndView editor(
			@RequestParam(value = "id", required = true) Integer id) {
		ModelAndView mav = new ModelAndView();
		
		TextbookCatalog jcTextbookCatalog = this.jcTextbookCatalogService.findTextbookCatalogById(id);
		mav.addObject("jcTextbookCatalog", jcTextbookCatalog);
		mav.setViewName(structurePath("/input"));

		return mav;
	}
	
	@RequestMapping(value = "/viewer", method = RequestMethod.GET)
	public ModelAndView viewer(
			@RequestParam(value = "id", required = true) Integer id) {
		
		ModelAndView mav = new ModelAndView();
		
		TextbookCatalog jcTextbookCatalog = this.jcTextbookCatalogService.findTextbookCatalogById(id);
		mav.addObject("isCK", "disable");
		mav.addObject("jcTextbookCatalog", jcTextbookCatalog);
		mav.setViewName(structurePath("/input"));

		return mav;
	}
	
	@RequestMapping(value = "/viewerByTextBookId", method = RequestMethod.GET)
	public ModelAndView viewerByTextBookId(
			@RequestParam(value = "testBookId", required = true) Integer testBookId) {
		
		ModelAndView mav = new ModelAndView();
		TextbookCatalogVo root = new TextbookCatalogVo();
		List<TextbookCatalogVo> resultList = new ArrayList<TextbookCatalogVo>();
		if(testBookId != null && testBookId >0){
			root = this.jcTextbookMasterService.findTextbookCatalogList(testBookId);
			
		}
		if(root.getId() != null && root.getId() >0){
			resultList = this.jcTextbookMasterService.nodeOutListExcludeQuote(root);
		}
		
		mav.addObject("isCK", "disable");
		mav.addObject("resultList", resultList);
		mav.setViewName(structurePath("/input"));
		return mav;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable(value = "id") Integer id, TextbookCatalog jcTextbookCatalog) {
		if (jcTextbookCatalog != null) {
			jcTextbookCatalog.setId(id);
		}
		try {
			this.jcTextbookCatalogService.remove(jcTextbookCatalog);
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
			return this.getMessage("???????????????????????????", e);
		}
		return ResponseInfomation.OPERATION_SUC;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseInfomation edit(@PathVariable(value = "id") Integer id, TextbookCatalog jcTextbookCatalog) {
		
		jcTextbookCatalog.setId(id);
		ResponseInfomation responseInfomation = new ResponseInfomation(ResponseInfomation.OPERATION_SUC);
		TextbookCatalog jcTextbookCatalogEnd = null;
		try {
			jcTextbookCatalogEnd = this.jcTextbookCatalogService.modify(jcTextbookCatalog);
			responseInfomation = jcTextbookCatalogEnd != null?responseInfomation:new ResponseInfomation(ResponseInfomation.OPERATION_FAIL);
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
			responseInfomation = new ResponseInfomation(getMessage("???????????????????????????", e));
		}
		
		return responseInfomation;
	
	}
	
	private String structurePath(String subPath) {
		return viewBasePath + subPath;
	}
	
	
	@RequestMapping(value = "/catalogList")
	@ResponseBody
	public ModelAndView findTextBookCatalogList(
			@RequestParam(value = "textBookId", required = false) Integer textBookId,
			@RequestParam(value = "sub", required = false) String sub){
		ModelAndView mav = new ModelAndView();
		TextbookCatalogVo root = new TextbookCatalogVo();

		List<TextbookCatalogVo> resultList = new ArrayList<TextbookCatalogVo>();
		
		if(textBookId != null && textBookId >0){
			root = this.jcTextbookMasterService.findTextbookCatalogList(textBookId);
			
		}
		if(root.getId() != null && root.getId() >0){
			resultList = this.jcTextbookMasterService.nodeOutListExcludeQuote(root);
		}
		mav.setViewName(structurePath("/index"));
		mav.addObject("catalogList", resultList);
		mav.addObject("list", sub);
		return mav;
	}
	
	/*downLoadModel
	downLoadExcel
	upLoadExcel*/
	
	@RequestMapping(value = "/downLoadModel")
	@ResponseBody
	public void downLoadModel(HttpServletRequest request,HttpServletResponse response,HttpSession session){
		
	
			// path???????????????????????????????????? 
			 String path= session.getServletContext().getRealPath("/")+"/template/catalog_info.xls";
			 try {
				 String filename = "????????????.xls";
				 String userAgent = request.getHeader("User-Agent");
				 //??????IE?????????IE????????????????????????
				 if(userAgent.contains("MSIE") || userAgent.contains("Trident")){
					 filename = URLEncoder.encode(filename, "UTF-8");
				 }else{
					//???IE?????????????????????
					 filename = new String(filename.getBytes("UTF-8"), "ISO8859_1");
				 }
				 response.addHeader("Content-Disposition", "attachment;filename=" + filename);
				 response.setContentType("text/html");
				 PrintWriter out = response.getWriter();
				 FileInputStream in = null;
				 File f = new File(path);
				 in = new FileInputStream(f);
				 byte b[] = new byte[1024];
				 while (in.read(b, 0, 1024) != -1) {
					 out.write(new String(b, "ISO8859_1"));
				 }
				 in.close();
				 
				 out.flush();
				 out.close();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	@RequestMapping(value = "/downLoadExcel")
	@ResponseBody
	public void downLoadExcel(
			@RequestParam(value = "textBookId", required = false) Integer textBookId
			,HttpServletResponse response,
			HttpServletRequest request){
		
			String filename = "????????????"+".xls";
			TextbookCatalogVo root = new TextbookCatalogVo();
			List<TextbookCatalogVo> resultList = new ArrayList<TextbookCatalogVo>();
			if(textBookId != null && textBookId >0){
				root = this.jcTextbookMasterService.findTextbookCatalogList(textBookId);
				
			}
			if(root.getId() != null && root.getId() >0){
				resultList = this.jcTextbookMasterService.nodeOutListExcludeQuote(root);
			}
		
			List<Object> list = new ArrayList<Object>();
			for (TextbookCatalogVo vo : resultList) {
				if(vo.getParentId()==0||vo.getLevel()==0){
					
				}else{
					//vo.setCode(a+"");
					vo.setBackCode(vo.getCode());
					list.add(vo);
				}
				
			}
			ParseConfig config = SzxyExcelTookit.getConfig("catalog");
			try {
				SzxyExcelTookit.exportExcelToWEB(list, config, request, response, filename);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
	}
	
	@RequestMapping("/upLoadInfoPage")
	@ResponseBody
	public ModelAndView upLoadStudentAwardInfoPage(@RequestParam(value = "textBookId", required = false) Integer textBookId
			){
		ModelAndView mav = new ModelAndView();
		mav.addObject("textBookId", textBookId);
		mav.setViewName(this.structurePath("/upLoadInfoPage"));
		return mav;
	}
	
	
	@RequestMapping("/upLoadInfo")
	@ResponseBody
	public  Map<String, Object>  upLoadInfo(
			@RequestParam("fileUpload") MultipartFile fileUpload,
			@RequestParam(value = "textBookId", required = false) Integer textBookId,
			@CurrentUser UserInfo userInfo,HttpServletResponse response,HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		//??????????????????
		List<TextbookCatalogVo> errorVoList = new ArrayList<TextbookCatalogVo>();
		
		String fileName = fileUpload.getOriginalFilename();//???????????????
		InputStream is= null;
		try {
			is = fileUpload.getInputStream();
			String suffix = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
			ParseConfig config = SzxyExcelTookit.getConfig("catalog");
			
			List<Object> catalogVoList = SzxyExcelTookit.excelDataToModels(config, is, suffix);
			List<TextbookCatalogVo> list = new ArrayList<TextbookCatalogVo>();
			for (Object object : catalogVoList) {
				TextbookCatalogVo textbookCatalogVo = (TextbookCatalogVo)object;
				textbookCatalogVo.setCode(textbookCatalogVo.getBackCode());
				//?????????????????????
				if(textbookCatalogVo.getName() != null && 
						/*textbookCatalogVo.getPage() != null&&
						textbookCatalogVo.getPage()>0&&*/
						textbookCatalogVo.getLevel() !=null&&
						textbookCatalogVo.getLevel()>0){
					//????????????
					textbookCatalogVo.setTestBookId(textBookId);
				}else{
					textbookCatalogVo.setErrorInfo("???????????????"+"???????????????"+"????????????????????????????????????????????????0???????????????");
					errorVoList.add(textbookCatalogVo);
				}
				list.add(textbookCatalogVo);
			}
			if(errorVoList.size() == 0){
				try {
					errorVoList = this.jcTextbookCatalogService.addList(list);
				} catch (Exception e) {
					new ResponseInfomation("?????????????????????"+e.getMessage().substring(0, e.getMessage().length()>15?15:e.getMessage().length()));
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
			map.put("status", "error");
			return map;
		}
			
		
		//????????????excel???????????????
		if(errorVoList.size()>0){
			
			map.put("status", "error");
			return map;
		}
				
		map.put("status", "success");
		return map;
	}
	
	/**
	 * ????????????
	 * @param e
	 * @return
	 */
	private String getMessage(String error,Exception e) {
		String message = e.getMessage().length() >=10 ?e.getMessage().substring(0, 10):e.getMessage();
		return error+":"+message;
	}
}
