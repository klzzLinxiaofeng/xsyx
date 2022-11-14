package platform.education.rest.user.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.rest.common.ResponseError;
import platform.education.rest.common.ResponseInfo;
import platform.education.rest.common.ResponseVo;
import platform.education.rest.common.constants.CommonCode;
import platform.education.rest.user.service.AppRestService;
import platform.education.rest.user.service.vo.ExtAppReleaseVo;
import platform.education.user.model.App;
import platform.education.user.model.AppEdition;
import platform.education.user.model.AppRelease;
import platform.education.user.service.AppEditionService;
import platform.education.user.service.AppReleaseService;
import platform.education.user.service.AppService;
import platform.education.user.vo.AppReleaseCondition;
import platform.service.storage.service.FileService;
import platform.service.storage.vo.FileResult;

public class AppRestServiceImpl implements AppRestService {
	private Logger log = LoggerFactory.getLogger(getClass());

	@Resource
	private AppReleaseService appReleaseService;

	@Resource
	private AppService appService;

	@Resource
	private AppEditionService appEditionService;

	@Resource
	private FileService fileService;

	@Override
	public Object getCurrent(String appKey) {
		if (appKey == null || "".equals(appKey)) {
			ResponseInfo info = new ResponseInfo();
			info.setDetail("appKey参数必填");
			info.setMsg("appKey参数不能为空");
			info.setParam("appKey");
			return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
		}

		AppEdition appEdition = appEditionService.findByAppKey(appKey);

		if (appEdition == null) {
			ResponseInfo info = new ResponseInfo();
			info.setDetail("appKey不存在");
			info.setMsg("appKey参数不存在");
			info.setParam("appKey");
			return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
		}

		// 存放单个版本信息
		ExtAppReleaseVo extAppReleaseVo = null;

		List<ExtAppReleaseVo> appReleaseVoList = new ArrayList<ExtAppReleaseVo>();

		try {
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
			if (appReleases != null && appReleases.size() > 0) {
				for (AppRelease appRelease : appReleases) {
					if (appEdition != null) {
						app = appService.findAppById(appRelease.getAppId());
						// 获取文件路径
						setupFile = fileService.findFileByUUID(appRelease.getSetupFile());
						qrCodeFile = fileService.findFileByUUID(appRelease.getQrCodeFile());
						packageFile = fileService.findFileByUUID(appRelease.getPackageFile());

						extAppReleaseVo = new ExtAppReleaseVo();
						extAppReleaseVo.setName(appEdition.getName());
						extAppReleaseVo.setVersion(appRelease.getVersion());
						extAppReleaseVo.setReleaseDate(format.format(appRelease.getReleaseDate()));
						extAppReleaseVo.setOsVersion(appRelease.getOsVersion());
						extAppReleaseVo.setTrademark(app.getTrademark());
						extAppReleaseVo.setManufacturer(app == null ? "" : app.getManufacturer());
						extAppReleaseVo.setCopyright(appRelease.getCopyright());
						extAppReleaseVo.setQrCodeFile(qrCodeFile == null ? "" : qrCodeFile.getHttpUrl());
						extAppReleaseVo.setSetupFile(setupFile == null ? "" : setupFile.getHttpUrl());
						extAppReleaseVo.setPackageFile(packageFile == null ? "" : packageFile.getHttpUrl());
						extAppReleaseVo.setDescription(appRelease.getDescription());
						extAppReleaseVo.setIsForce(appRelease.getIsForce());
						if(fileService.findFileByUUID(appRelease.getSetupFile()).getEntityFile() != null) {
							extAppReleaseVo.setFileSize(fileService.findFileByUUID(appRelease.getSetupFile()).getEntityFile().getSize());
						}
						appReleaseVoList.add(extAppReleaseVo);
					}
				}
			}
		} catch (Exception e) {
			log.debug("数据获取异常");
			return new ResponseVo<ExtAppReleaseVo>("000001", null);// 未知错误
		}
		return new ResponseVo<List<ExtAppReleaseVo>>("0", appReleaseVoList);
	}

	@Override
	public Object getCurrent(String appKey, String jsonpCallback) {

		if (appKey == null || "".equals(appKey)) {
			ResponseInfo info = new ResponseInfo();
			info.setDetail("appKey参数必填");
			info.setMsg("appKey参数不能为空");
			info.setParam("appKey");
			return jsonpCallback + "(" + new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info) + ")";
		}

		if (jsonpCallback == null || "".equals(jsonpCallback)) {
			ResponseInfo info = new ResponseInfo();
			info.setDetail("jsonpCallback参数必填");
			info.setMsg("jsonpCallback参数不能为空");
			info.setParam("jsonpCallback");
			return jsonpCallback + "(" + new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info) + ")";
		}
		
		AppEdition appEdition = appEditionService.findByAppKey(appKey);

		if (appEdition == null) {
			ResponseInfo info = new ResponseInfo();
			info.setDetail("appKey不存在");
			info.setMsg("appKey参数不存在");
			info.setParam("appKey");
			return jsonpCallback + "(" + new ResponseError(CommonCode.D$DATA_NOT_FOUND, info) + ")";
		}
		
		// 存放单个版本信息
		ExtAppReleaseVo extAppReleaseVo = null;
		List<ExtAppReleaseVo> appReleaseVoList = new ArrayList<ExtAppReleaseVo>();
		try {
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
			if (appReleases != null && appReleases.size() > 0) {
				for (AppRelease appRelease : appReleases) {
					if (appEdition != null) {
						app = appService.findAppById(appRelease.getAppId());
						// 获取文件路径
						setupFile = fileService.findFileByUUID(appRelease.getSetupFile());
						qrCodeFile = fileService.findFileByUUID(appRelease.getQrCodeFile());
						packageFile = fileService.findFileByUUID(appRelease.getPackageFile());

						extAppReleaseVo = new ExtAppReleaseVo();
						extAppReleaseVo.setName(appEdition.getName());
						extAppReleaseVo.setVersion(appRelease.getVersion());
						extAppReleaseVo.setReleaseDate(format.format(appRelease.getReleaseDate()));
						extAppReleaseVo.setOsVersion(appRelease.getOsVersion());
						extAppReleaseVo.setTrademark(app.getTrademark());
						extAppReleaseVo.setManufacturer(app == null ? "" : app.getManufacturer());
						extAppReleaseVo.setCopyright(appRelease.getCopyright());
						extAppReleaseVo.setQrCodeFile(qrCodeFile == null ? "" : qrCodeFile.getHttpUrl());
						extAppReleaseVo.setSetupFile(setupFile == null ? "" : setupFile.getHttpUrl());
						extAppReleaseVo.setPackageFile(packageFile == null ? "" : packageFile.getHttpUrl());
						extAppReleaseVo.setDescription(appRelease.getDescription().replaceAll("\\r\\n", "<br/>"));
						appReleaseVoList.add(extAppReleaseVo);
					}
				}
			}
		} catch (Exception e) {
			log.debug("数据获取异常");
			return jsonpCallback + "(" + new ResponseVo<ExtAppReleaseVo>("000001", null) + ")";// 未知错误
		}
		return jsonpCallback + "(" +  new ResponseVo<List<ExtAppReleaseVo>>("0", appReleaseVoList)  + ")";
	}

}
