package platform.szxyzxx.web.sys.client.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import platform.education.user.model.App;
import platform.education.user.model.AppEdition;
import platform.education.user.model.AppRelease;
import platform.education.user.service.AppEditionService;
import platform.education.user.service.AppReleaseService;
import platform.education.user.service.AppService;
import platform.education.user.vo.AppReleaseCondition;
import platform.service.storage.vo.FileResult;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.CalculateVisualFileSize;
import platform.szxyzxx.web.sys.vo.ExtAppReleaseVo;
import platform.szxyzxx.web.teach.client.vo.ResponseError;
import platform.szxyzxx.web.teach.client.vo.ResponseInfo;
import platform.szxyzxx.web.teach.client.vo.ResponseVo;

@Controller
@RequestMapping("/public/app")
public class ExtAppController extends BaseController { 
	private Logger log = LoggerFactory.getLogger(getClass());
	@Resource
	private AppReleaseService appReleaseService;
	@Resource
	private AppService appService;
	@Resource
	private AppEditionService appEditionService;
	
	@RequestMapping(value = "/release/getCurrent")
	@ResponseBody
	public Object index(
			@RequestParam(value="appKey")String appKey) {

		if(appKey == null || "".equals(appKey)){
			ResponseInfo info = new ResponseInfo();
			info.setDetail("appKey参数必填");
			info.setMsg("appKey参数不能为空");
			info.setParam("appKey");
			return new ResponseError("060111", info);
		}
		
		AppEdition appEdition = appEditionService.findByAppKey(appKey);
		
		if(appEdition == null){
			ResponseInfo info = new ResponseInfo();
			info.setDetail("appKey不存在");
			info.setMsg("appKey参数不存在");
			info.setParam("appKey");
			return new ResponseError("020101", info);
		}
		
		//存放单个版本信息
		ExtAppReleaseVo extAppReleaseVo = null;
		
		List<ExtAppReleaseVo> appReleaseVoList = new ArrayList<ExtAppReleaseVo>();
		
		try{
			AppReleaseCondition appReleaseCondition = new AppReleaseCondition();
			appReleaseCondition.setAppKey(appKey);
			appReleaseCondition.setIsDeleted(false);
			appReleaseCondition.setIsCurrent(true);
			List<AppRelease> appReleases = appReleaseService.findAppReleaseByCondition(appReleaseCondition);
			
			App app = null;
			FileResult setupFile = null;
			FileResult qrCodeFile = null;
			FileResult packageFile = null;
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(appReleases != null && appReleases.size() > 0){
				for (AppRelease appRelease : appReleases) {
					if(appEdition != null){
						app = appService.findAppById(appRelease.getAppId());
						//获取文件路径
						setupFile = fileService.findFileByUUID(appRelease.getSetupFile());
						qrCodeFile = fileService.findFileByUUID(appRelease.getQrCodeFile());
						packageFile = fileService.findFileByUUID(appRelease.getPackageFile());
						String setUpUrl = setupFile == null ? "" : setupFile.getHttpUrl();
						String newUrl = null;
						if(setUpUrl !=null && !"".equals(setUpUrl)){
							newUrl = setUpUrl.replace("https", "http");
						}
						
						extAppReleaseVo = new ExtAppReleaseVo();
						extAppReleaseVo.setName(appEdition.getName());
						extAppReleaseVo.setVersion(appRelease.getVersion());
						extAppReleaseVo.setReleaseDate(format.format(appRelease.getReleaseDate()));
						extAppReleaseVo.setOsVersion(appRelease.getOsVersion());
						extAppReleaseVo.setTrademark(fileService.relativePath2HttpUrlByUUID(app.getTrademark()));
						extAppReleaseVo.setManufacturer(app == null ? "" : app.getManufacturer());
						extAppReleaseVo.setCopyright(appRelease.getCopyright());
						extAppReleaseVo.setQrCodeFile(qrCodeFile == null ? "" : qrCodeFile.getHttpUrl());
						extAppReleaseVo.setSetupFile(newUrl);
						extAppReleaseVo.setPackageFile(packageFile == null ? "" : packageFile.getHttpUrl());
						extAppReleaseVo.setDescription(appRelease.getDescription());
						extAppReleaseVo.setIsForce(appRelease.getIsForce());
						if(setupFile != null){
							extAppReleaseVo.setPackageSize(CalculateVisualFileSize.getFileSize(setupFile.getEntityFile().getSize()));
							extAppReleaseVo.setExactSize(setupFile.getEntityFile().getSize());
						}
						appReleaseVoList.add(extAppReleaseVo);
					}
				}
			}
		}catch(Exception e){
			log.debug("数据获取异常");
			return new ResponseVo<ExtAppReleaseVo>("000001", null);//未知错误
		}
		return new ResponseVo<List<ExtAppReleaseVo>>("0", appReleaseVoList);
	
	}
}
