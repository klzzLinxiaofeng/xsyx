package platform.szxyzxx.web.sys.controller;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;







import platform.education.user.model.App;
import platform.education.user.model.AppEdition;
import platform.education.user.model.AppRelease;
import platform.education.user.service.AppEditionService;
import platform.education.user.service.AppReleaseService;
import platform.education.user.vo.AppAndAppReleaseVo;
import platform.education.user.vo.AppCondition;
import platform.education.user.vo.AppEditionCondition;
import platform.education.user.vo.AppReleaseCondition;
import platform.education.user.vo.AppReleaseVo;
import platform.service.storage.model.EntityFile;
import platform.service.storage.vo.FileResult;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.contants.SysContants;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;
import platform.szxyzxx.web.sys.util.QrCodeUtils;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

@Controller
@RequestMapping("/sys/appupdate")
public class AppUpdateController extends BaseController { 
	
	@Resource
	private AppReleaseService appReleaseService;
	
	@Resource
	private AppEditionService appEditionService;
	
	private final static String viewBasePath = "/sys/appupdate";
	
	//数据页面
	@RequestMapping(value = "/index")
	public ModelAndView index(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@RequestParam(value = "appKey", required = false) String appKey,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) {
		
		String viewPath = null;
		order.setProperty(order.getProperty() != null ? order.getProperty() : "create_date");
		
		//获取APP相关信息，用于页面下拉选
		AppEditionCondition appEditionCondition = new AppEditionCondition();
		appEditionCondition.setIsDeleted(false);
		appEditionCondition.setRecordType("2");
		List<AppEdition> selectItems = this.appEditionService.findAppEditionByCondition(appEditionCondition);
		
		//页面数据获取
		List<AppAndAppReleaseVo> dataItems = null;
		
		if ("list".equals(sub)) {
			dataItems = appReleaseService.findAppReleases(appKey, page, order);
			viewPath = structurePath("/list");
		} else {
			viewPath = structurePath("/index");
		}
		model.addAttribute("selectItems", selectItems);
		model.addAttribute("dataItems", dataItems);
		return new ModelAndView(viewPath, model.asMap());
	}
	
	//创建页面
	@RequestMapping(value = "/creator", method = RequestMethod.GET)
	public ModelAndView creator( Model model,@ModelAttribute("page") Page page,@ModelAttribute("order") Order order) {
		//获取APP相关信息，用于页面下拉选
		AppEditionCondition appEditionCondition = new AppEditionCondition();
		appEditionCondition.setIsDeleted(false);
		appEditionCondition.setRecordType("2");
		List<AppEdition> items = this.appEditionService.findAppEditionByCondition(appEditionCondition);
		model.addAttribute("items", items);
		return new ModelAndView(structurePath("/input"));
	}

	//创建方法，保存数据
	@RequestMapping(value = "/creator", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation creator(AppRelease appRelease, @CurrentUser UserInfo user,HttpServletRequest request,Model model){
		if(appRelease != null && appRelease.getAppKey() != null){
			AppEdition appEdition = appEditionService.findByAppKey(appRelease.getAppKey());
			App app = appService.findAppById(appEdition.getAppId());
			if(appEdition != null){
				//校验是否已经存在版本号
				AppReleaseCondition appReleaseCondition = new AppReleaseCondition();
				appReleaseCondition.setAppKey(appEdition.getAppKey());
				appReleaseCondition.setIsDeleted(false);
				appReleaseCondition.setVersion(appRelease.getVersion());
				List<AppRelease> appReleases = appReleaseService.findAppReleaseByCondition(appReleaseCondition);
				
				if(appReleases != null && appReleases.size() > 0){
					return new ResponseInfomation("exist");
				}else{
					String qrUUID = generatedQR(app == null ? "" : app.getTrademark(),appRelease.getSetupFile(),request);
					//判断 如果生成不了二维码，不在继续，返回信息
					if(qrUUID == null || "".equals(qrUUID)){
						return new ResponseInfomation(ResponseInfomation.OPERATION_ERROR);
					}
					
					appRelease.setAppId(app.getId());
					appRelease.setCreateDate(new Date());
					appRelease.setIsCurrent(true);
					appRelease.setModifyDate(new Date());
					appRelease.setQrCodeFile(qrUUID);
					appRelease.setReleaseDate(new Date());
					appRelease.setIsForce(false);
					appRelease = appReleaseService.add(appRelease);
				}
			}
		}
		return appRelease != null ? new ResponseInfomation(appRelease.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}

	@RequestMapping(value = "/editor", method = RequestMethod.GET)
	public ModelAndView editor(@RequestParam(value = "id", required = false) Integer id, Model model) {
		AppEdition appEdition = null;
		if (id != null) {
			AppAndAppReleaseVo appRelease = appReleaseService.findAppReleaseVoByAppReleaseId(id);
			if(appRelease != null && appRelease.getAppKey() != null && !"".equals(appRelease.getAppKey())){
				appEdition = appEditionService.findByAppKey(appRelease.getAppKey());
			}
			if(appRelease != null){
				if(appRelease.getPackageFile() != null && !"".equals(appRelease.getPackageFile())){
					EntityFile entityPackageFile = this.entityFileService.findFileByUUID(appRelease.getPackageFile());
					model.addAttribute("entityPackageFile", entityPackageFile);
				}
				if(appRelease.getSetupFile() != null && !"".equals(appRelease.getSetupFile())){
					EntityFile entitySetupFile = entityFileService.findFileByUUID(appRelease.getSetupFile());
					model.addAttribute("entitySetupFile", entitySetupFile);
				}
			}
			model.addAttribute("appRelease", appRelease);
			model.addAttribute("appEdition", appEdition);
		}
		return new ModelAndView(structurePath("/editor"), model.asMap());
	}
	
	//编辑数据
	@RequestMapping(value = "/editor", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation editor(AppRelease appRelease, @CurrentUser UserInfo user,Model model,HttpServletRequest request) throws Exception {
		App app = null;
		if(appRelease != null){
				AppReleaseCondition appReleaseCondition = new AppReleaseCondition();
				appReleaseCondition.setAppKey(appRelease.getAppKey());
				appReleaseCondition.setVersion(appRelease.getVersion());
				List<AppRelease> arList = appReleaseService.findAppReleaseByCondition(appReleaseCondition);
				if(arList != null && arList.size() > 0){
					for(AppRelease ar : arList){
						if(ar.getId() != appRelease.getId() && (ar.getVersion() == appRelease.getVersion() || ar.getVersion().equals(appRelease.getVersion()))){
							return new ResponseInfomation("exist");
						}
					}
				}
				
				if(appRelease.getAppId() != null && !"".equals(appRelease.getAppId())){
					app = appService.findAppById(appRelease.getAppId());
				}
				if(appRelease.getSetupFile() != null && !"".equals(appRelease.getSetupFile())){
					String qrUUID = generatedQR(app == null ? "" : app.getTrademark(),appRelease.getSetupFile(),request);
					//判断 如果生成不了二维码，不在继续，返回信息
					if(qrUUID == null || "".equals(qrUUID)){
						return new ResponseInfomation(ResponseInfomation.OPERATION_ERROR);
					}
					
					appRelease.setModifyDate(new Date());
					appRelease.setQrCodeFile(qrUUID);
					appRelease.setReleaseDate(new Date());
					appRelease = appReleaseService.modify(appRelease);
				}
				
			}
		return appRelease != null ? new ResponseInfomation(appRelease.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}
	
	@RequestMapping(value = "/setverison", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation setverison(@RequestParam(value = "id", required = true) Integer id) {
		AppRelease appRelease = appReleaseService.setAppCurrentRelease(id);
		return appRelease != null ? new ResponseInfomation(appRelease.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}

	@RequestMapping(value = "/setForce", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation setForce(@RequestParam(value = "id", required = true) Integer id,
			@RequestParam(value = "isForce", required = true) Boolean isForce) {
		AppRelease appRelease = appReleaseService.findAppReleaseById(id);
		appRelease.setIsForce(isForce);
		appRelease = appReleaseService.modify(appRelease);
		return appRelease != null ? new ResponseInfomation(appRelease.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable(value = "id") Integer id, AppRelease appRelease) {
		if (appRelease != null) {
			appRelease.setId(id);
		}
		try {
			this.appReleaseService.remove(appRelease);
		} catch (Exception e) {
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseInfomation edit(@PathVariable(value = "id") Integer id, App app) {
		return null;
	}
	
	@ResponseBody
	@RequestMapping(value = "/selectApp",method = RequestMethod.POST)
	public String selectApp(@RequestParam(value = "appId",required=true)Integer appId){
		if (appId !=null) {
			App app = appService.findAppById(appId);
			String osForm = app.getOsForm();
			return osForm;
		}
			return null;
	}
	
	private String structurePath(String subPath) {
		return viewBasePath + subPath;
	}
	
	@RequestMapping(value = "/download")
	public ModelAndView download(@RequestParam(value = "id",required=true)Integer id, Model model){
		String viewPath = structurePath("/downloaded_application");
		List<AppAndAppReleaseVo> appReleaseList = this.appReleaseService.findCurAppReleases(id);
		AppAndAppReleaseVo appAndAppReleaseVo = new AppAndAppReleaseVo();
		if(appReleaseList!=null && appReleaseList.size()>0){
			appAndAppReleaseVo = appReleaseList.get(0);
			App app = appService.findAppById(appAndAppReleaseVo.getAppId());
			AppEdition appEdition = appEditionService.findByAppKey(appAndAppReleaseVo.getAppKey());
			AppReleaseVo appReleaseVo = new AppReleaseVo();
			BeanUtils.copyProperties(appAndAppReleaseVo, appReleaseVo);
			appReleaseVo.setName(appEdition.getName());
			appReleaseVo.setTrademark(fileService.relativePath2HttpUrlByUUID(app.getTrademark()));
			appReleaseVo.setSetupFile(fileService.relativePath2HttpUrlByUUID(appReleaseVo.getSetupFile()));
			appReleaseVo.setQrCodeFile(fileService.relativePath2HttpUrlByUUID(appReleaseVo.getQrCodeFile()));
			model.addAttribute("appReleaseVo", appReleaseVo);
		}
		
		return new ModelAndView(viewPath, model.asMap());
	}

	@RequestMapping(value = "/download/page")
	public ModelAndView download(@RequestParam(value = "tag",required=true)String tag, Model model){
		String viewPath = structurePath("/downloaded_application");
		return new ModelAndView(viewPath, model.asMap());
	}
	
	
	//=========================================2016-7-1========================================//
	/**
	 * @function 生成二维码返回二维码的UUID
	 * @date 2016-7-1
	 * @author panfei
	 * @parame logoImgUuid
	 * 			表示二维码中间的logo图片的ＵＵＩＤ，没有可不传，使用讯云默认ｌｏｇｏ
	 * @parame sourceUuid
	 * 			表示扫二维码下载的源的UUID
	 * @parame request
	 * 			用户没有传logo图片时，用于获取默认图片
	 * @return UUID 二维码的UUID
	 * 
	 */
	public String generatedQR(String logoUuid,String sourceUuid,HttpServletRequest request){
		//判断如果没有传下载源路径，退出
		if(sourceUuid == null || "".equals(sourceUuid)){
			return null;
		}
		
		//表示二维码中间logo图片路径
		String logoUrl = "";
		
		//二维码的UUID
		String qrUuid = "";
		
		//下载源的地址
		String sourceUrl = fileService.relativePath2HttpUrlByUUID(sourceUuid);
		
		String extension = "";
		//根据穿的logoUUID获取logo图片的绝对路径，如果没有则使用默认logo
		if(logoUuid != null && !"".equals(logoUuid)){
			
			FileResult fileResult = this.fileService.findFileByUUID(logoUuid);
			if (fileResult != null) {
				if(fileResult.getEntityFile()!=null){
					extension = fileResult.getEntityFile().getExtension();
				}
				logoUrl = fileService.relativePath2InternalHttpUrl(fileResult.getEntityFile().getRelativePath());
			}
			if(logoUrl == null || "".equals(logoUrl)){
				logoUrl = request.getSession().getServletContext().getRealPath("/res/images/yunyun_logo.png");
			}
		}else{
			logoUrl = request.getSession().getServletContext().getRealPath("/res/images/yunyun_logo.png");
		}
		
		InputStream is = null;
		ByteArrayOutputStream os = null;
		try {
			if("png".equals(extension)){
				URL url = new URL(logoUrl);
				java.net.URLConnection conn = url.openConnection();
				//转成inputStream并上传
				is = conn.getInputStream();
			}else if ("".equals(extension)){
				File file = new File(logoUrl);
				is = new FileInputStream(file);
			}else {
				URL url = new URL(logoUrl);
				java.net.URLConnection conn = url.openConnection();
				File file = new File(logoUrl);
				//转成inputStream并上传
				InputStream fileInputStream = conn.getInputStream();
				//原图片不是png的图片
				FileResult upload = fileService.upload(fileInputStream, "png", "", file.getName(), String.valueOf(SysContants.SYSTEM_APP_ID));
				//获取上传的log图片绝对路径
				logoUrl = fileService.relativePath2HttpUrlByUUID(upload.getEntityFile().getUuid());
				
				URL url2 = new URL(logoUrl);
				is = url2.openStream();
			}
			BufferedImage genBarcode = QrCodeUtils.genBarcode(sourceUrl, 800, 800, is);
			os = new ByteArrayOutputStream();
			
			ImageIO.write(genBarcode, "png", os);
			InputStream inputStream = new ByteArrayInputStream(os.toByteArray());
			
			//上传二维码
			String randomUUID = UUID.randomUUID().toString();
			randomUUID = randomUUID+".png";
			FileResult sourceUpload = fileService.upload(inputStream, "png", "", randomUUID, String.valueOf(SysContants.SYSTEM_APP_ID));
			if(sourceUpload != null && sourceUpload.getEntityFile() != null){
				qrUuid = sourceUpload.getEntityFile().getUuid();
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				if(is != null){
					is.close();
				}
				if(os != null){
					os.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return qrUuid;
	}
	
	
}
