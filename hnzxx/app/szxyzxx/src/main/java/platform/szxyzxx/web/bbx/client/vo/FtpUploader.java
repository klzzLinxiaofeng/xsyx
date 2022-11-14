package platform.szxyzxx.web.bbx.client.vo;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import platform.education.resource.contants.UploadFinishedFlag;
import platform.education.resource.utils.DownloadUtil;
import platform.service.storage.contants.FileStatusCode;
import platform.service.storage.model.EntityFile;
import platform.service.storage.service.FileService;
import platform.service.storage.vo.FileResult;
import platform.szxyzxx.web.common.contants.SysContants;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.util.SessionManager;
import platform.szxyzxx.web.common.vo.EntityFileVo;
import platform.szxyzxx.web.common.vo.ImgResponseVo;
import platform.szxyzxx.web.common.vo.UserInfo;

@Controller
@RequestMapping(value = "/uploader")
public class FtpUploader extends BaseController {

	@Autowired
	@Qualifier("fileService")
	private FileService fileService;
	
	/**
	 * general 进行资源上传
	 *
	 * @param file
	 * @param jsessionId
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/common", method = RequestMethod.POST)
	@ResponseBody
	public Object uploadV2(@RequestParam("file") MultipartFile file,
			HttpServletRequest request) throws IOException {
		String jsessionId = request.getParameter("jsessionId");
		String editor = request.getParameter("editor");
		ImgResponseVo vo = new ImgResponseVo();
		if (file != null) {
			String uploadFileName = file.getOriginalFilename();
			UserInfo userInfo = SessionManager.getUserInfoShiro(jsessionId);
			if (userInfo != null) {
				FileResult result = this.fileService.upload(
						file.getInputStream(),
						StringUtils.getFilenameExtension(uploadFileName),
						file.getContentType(), uploadFileName,
						String.valueOf(SysContants.SYSTEM_APP_ID));
				if (result != null
						&& FileStatusCode.UPLOAD_SUCCESS.equals(result
								.getStatusCode())) {
					EntityFile entityFile = result.getEntityFile();
					EntityFileVo fileVo = new EntityFileVo();
					copyEntityFileProperties(entityFile, fileVo);
					fileVo.setUrl(result.getHttpUrl());
					vo.setUrl(result.getHttpUrl());
					vo.setError(0);
					if("editor".equals(editor)){
						return vo;
					}else{
						return fileVo;
					}
				}
			} else {
				return ResponseInfomation.NO_LOGIN;
			}
		}
		if("editor".equals(editor)){
			vo.setError(1);
			vo.setMessage("上传失败");
			return vo;
		}else{
			return ResponseInfomation.OPERATION_FAIL;
		}
	}
	/**
	 * general 进行资源上传
	 *
	 * @param file
	 * @param jsessionId
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/cropAvatarUpload",method = RequestMethod.POST)
	@ResponseBody
	public Object cropAvatarUpload(@RequestParam("image")String image,HttpServletRequest request){
		String jsessionId = request.getParameter("jsessionId");
		String contentType = "";
		String extension = "";
//		if(file.isEmpty()){
//			return ResponseInfomation.OPERATION_FAIL;
//		}
		ImgResponseVo vo = new ImgResponseVo();
		if(image != null){
			String dataMeta = image.split(",")[0];
			contentType = dataMeta.substring(dataMeta.indexOf(":")+1, dataMeta.indexOf(";")+1);
			extension = contentType.substring(contentType.indexOf("/")+1);
			image=image.split(",")[1];
		}
		Base64 decoder = new Base64();
		byte[] decodedBytes = decoder.decode(image);
		InputStream in = new ByteArrayInputStream(decodedBytes); 
		UserInfo userInfo = SessionManager.getUserInfoShiro(jsessionId);
		if (userInfo != null) {
			Date date = new Date();
			String newFileName = date.getTime() + "." + extension;
			FileResult result = this.fileService.upload(in,extension,contentType,newFileName,String.valueOf(SysContants.SYSTEM_APP_ID));
			if (result != null
					&& FileStatusCode.UPLOAD_SUCCESS.equals(result
							.getStatusCode())) {
				EntityFile entityFile = result.getEntityFile();
				EntityFileVo fileVo = new EntityFileVo();
				copyEntityFileProperties(entityFile, fileVo);
				fileVo.setUrl(result.getHttpUrl());
				vo.setUrl(result.getHttpUrl());
				vo.setError(0);
				return fileVo;
			}
		} else {
			return ResponseInfomation.NO_LOGIN;
		}
		return ResponseInfomation.OPERATION_FAIL;
	}
	/**
	 * 进行资源上传
	 *
	 * @param file
	 * @param jsessionId
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/bpru", method = RequestMethod.POST)
	@ResponseBody
	public Object continueUpload(@RequestParam("file") MultipartFile file,
			HttpServletRequest request) throws IOException {
		String md5 = request.getParameter("md5");
		String chunk = request.getParameter("chunk");
		String chunks = request.getParameter("chunks");
		Integer chunki = null;
		Integer chunksi = null;
		if (chunk != null && !"".equals(chunk)) {
			chunki = Integer.parseInt(chunk);
		}
		if (chunks != null && !"".equals(chunks)) {
			chunksi = Integer.parseInt(chunks);
		}
		String jsessionId = request.getParameter("jsessionId");
		if (file != null) {
			String uploadFileName = file.getOriginalFilename();
			UserInfo userInfo = SessionManager.getUserInfoShiro(jsessionId);
			if (userInfo != null) {
				boolean isComplete = chunki == null && chunksi == null ? true
						: (chunki == (chunksi - 1));
				FileResult result = this.fileService.resumeUpload(
						file.getInputStream(),
						StringUtils.getFilenameExtension(uploadFileName), md5,
						file.getContentType(), uploadFileName,
						String.valueOf(SysContants.SYSTEM_APP_ID), isComplete);
				if (result != null
						&& FileStatusCode.UPLOAD_SUCCESS.equals(result
								.getStatusCode()) && isComplete) {
					EntityFile entityFile = result.getEntityFile();
					EntityFileVo fileVo = new EntityFileVo();
					copyEntityFileProperties(entityFile, fileVo);
					fileVo.setUrl(result.getHttpUrl());
					fileVo.setFinishedFlag(UploadFinishedFlag.FINISHED);
					return fileVo;
				}
			} else {
				return ResponseInfomation.NO_LOGIN;
			}
		}
		return null;
	}

	@RequestMapping("/download")
	public String download(HttpServletRequest request, HttpServletResponse response) throws IOException {
		EntityFile ent = this.entityFileService.findFileByUUID("6b5b63bdbae6477cb2e07c189447d767");
		if (ent != null) {
			String suffix = ent.getExtension();
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			String filename = DownloadUtil.encodeFilenameForDownload(request, URLDecoder.decode("中国万岁", "UTF-8"));
			response.addHeader("content-type ", "application/x-download");
			response.setContentType("application/x-download");
			response.setContentLength(ent.getSize().intValue());
			response.setHeader("Content-Disposition", "attachment;filename="
					+ filename + "." + suffix);
			OutputStream out = response.getOutputStream();
			// Integer flag = this.fileUploadService.downloadEntity(entId, out);
			String result = this.fileService.download("6b5b63bdbae6477cb2e07c189447d767", out);

			if (FileStatusCode.DOWNLOAD_SUCCESS.equals(result)) {
				// 下载成功
			} else if (FileStatusCode.DOWNLOAD_FAIL_FILE_NOT_EXIT
					.equals(result)) {
				// 远程文件不存在
				// 还没处理好错误信息在前端显示
			} else if (FileStatusCode.DOWNLOAD_FAIL_STREAM_ERR.equals(result)
					|| FileStatusCode.CONNECT_SERVER_FAIL.equals(result)) {
				// 系统错误
				// 还没处理好错误信息在前端显示
			}
		}
		return null;
	}

	/**
	 * 文件md5验证
	 *
	 * @param file
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping("/exist")
	public Object getFile(@RequestParam("md5") String md5,
			HttpServletRequest request) {
		FileResult result = this.fileService.preResumeUpload(md5,
				String.valueOf(SysContants.SYSTEM_APP_ID));
		EntityFile entityFile = result.getEntityFile();
		long size = result.getTempFileSize();
		EntityFileVo entityFileVo = new EntityFileVo();
		if (entityFile != null) {
			copyEntityFileProperties(entityFile, entityFileVo);
			entityFileVo.setUrl(result.getHttpUrl());
			entityFileVo.setFinishedFlag(UploadFinishedFlag.FINISHED);
			return entityFileVo;
		} else if (size > 0) {
			entityFileVo.setSize((int) result.getTempFileSize());
			entityFileVo.setFinishedFlag(UploadFinishedFlag.NOT_FINISHED);
			return entityFileVo;
		}
		return "no_exist";
	}

	private void copyEntityFileProperties(EntityFile entityFile,
			EntityFileVo entityFileVo) {
		entityFileVo.setFileName(entityFile.getDiskFileName());
		entityFileVo.setCreateDate(entityFile.getCreateDate());
		entityFileVo.setId(entityFile.getId());
		entityFileVo.setMd5Code(entityFile.getMd5());
		entityFileVo.setRealFileName(entityFile.getFileName());
		entityFileVo.setSize(entityFile.getSize());
		entityFileVo.setSuffix(entityFile.getExtension());
		entityFileVo.setUuid(entityFile.getUuid());
	}

}
