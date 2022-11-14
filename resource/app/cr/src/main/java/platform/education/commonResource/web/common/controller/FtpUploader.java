package platform.education.commonResource.web.common.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import platform.education.commonResource.web.common.contants.SysContants;
import platform.education.commonResource.web.common.controller.base.BaseController;
import platform.education.commonResource.web.common.util.SessionManager;
import platform.education.commonResource.web.common.vo.EntityFileVo;
import platform.education.commonResource.web.common.vo.ResponseInfomation;
import platform.education.commonResource.web.common.vo.UserInfo;
import platform.education.resource.contants.UploadFinishedFlag;
import platform.service.storage.contants.FileStatusCode;
import platform.service.storage.model.EntityFile;
import platform.service.storage.service.FileService;
import platform.service.storage.vo.FileResult;

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
       // String jsessionId = request.getParameter("jsessionId");
        String jsessionId = request.getSession().getId();
        if (file != null) {
            String uploadFileName = file.getOriginalFilename();
            UserInfo userInfo = SessionManager.getUserInfoShiro(jsessionId);
            if (userInfo != null) {
                FileResult result = this.fileService.upload(file.getInputStream(), StringUtils.getFilenameExtension(uploadFileName), file.getContentType(), uploadFileName, String.valueOf(SysContants.SYSTEM_APP_ID));
                if (result != null &&  FileStatusCode.UPLOAD_SUCCESS.equals(result.getStatusCode())) {
                	EntityFile entityFile = result.getEntityFile();
                    EntityFileVo fileVo = new EntityFileVo();
                    copyEntityFileProperties(entityFile, fileVo);
                    fileVo.setUrl(result.getHttpUrl());
                    return fileVo;
                }
            } else {
                return ResponseInfomation.NO_LOGIN;
            }
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
    public Object continueUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
        String md5 = request.getParameter("md5");
        String chunk = request.getParameter("chunk");
        String chunks = request.getParameter("chunks");
        Integer chunki = null;
        Integer chunksi = null;        
        if(chunk!=null&&!"".equals(chunk)){
            chunki = Integer.parseInt(chunk);
        }
        if(chunks!=null&&!"".equals(chunks)){
            chunksi = Integer.parseInt(chunks);
        }
        String jsessionId = request.getParameter("jsessionId");
        if (file != null) {
            String uploadFileName = file.getOriginalFilename();
            UserInfo userInfo = SessionManager.getUserInfoShiro(jsessionId);
            if (userInfo != null) {
                boolean isComplete = chunki == null && chunksi == null ? true : (chunki == (chunksi - 1));
                InputStream inputStream = file.getInputStream();
                StringUtils.getFilenameExtension(uploadFileName);
                String contentType = file.getContentType();
                System.out.println(isComplete);
                FileResult result = this.fileService.resumeUpload(inputStream, StringUtils.getFilenameExtension(uploadFileName), md5,contentType, uploadFileName, String.valueOf(SysContants.SYSTEM_APP_ID), isComplete);
                if (result != null &&  FileStatusCode.UPLOAD_SUCCESS.equals(result.getStatusCode()) && isComplete) {
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
    
    /**
     * general 富文本编辑器 KindEditor本地图片文件上传
     *
     * @param file
     * @param jsessionId
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/forKe", method = RequestMethod.POST)
    @ResponseBody
    public Object uploadForKe(@RequestParam("file") MultipartFile file,
             HttpServletRequest request) throws IOException {
        String jsessionId = request.getParameter("jsessionId");
        if (file != null) {
        	Map<String, Object> map = new HashMap<String, Object>();
            String uploadFileName = file.getOriginalFilename();
            String extension = StringUtils.getFilenameExtension(uploadFileName);
            if(extension==null || extension.equals("")) {
            	map.put("error", 1);
            	map.put("message", "文件格式有误，请重新上传");
            	return map;
            }
            if(!(extension.equalsIgnoreCase("png") || extension.equalsIgnoreCase("jpg") || extension.equalsIgnoreCase("jpeg"))) {
            	map.put("error", 1);
            	map.put("message", "图片格式不正确，请重新上传");
            	return map;
            }
            UserInfo userInfo = SessionManager.getUserInfoShiro(jsessionId);
            if (userInfo != null) {
                FileResult result = this.fileService.upload(file.getInputStream(), StringUtils.getFilenameExtension(uploadFileName), file.getContentType(), uploadFileName, String.valueOf(SysContants.SYSTEM_APP_ID));
                if (result != null &&  FileStatusCode.UPLOAD_SUCCESS.equals(result.getStatusCode())) {
                    map.put("error", 0);
                	map.put("message", "上传成功");
                	map.put("url", result.getHttpUrl());
                    return map;
                }
            } else {
                return ResponseInfomation.NO_LOGIN;
            }
        }
        return ResponseInfomation.OPERATION_FAIL;
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
    public Object getFile(@RequestParam("md5") String md5, HttpServletRequest request, Model model) {
    	FileResult result = this.fileService.preResumeUpload(md5, String.valueOf(SysContants.SYSTEM_APP_ID));
    	EntityFile entityFile = result.getEntityFile();
    	long size = result.getTempFileSize();
    	EntityFileVo entityFileVo = new EntityFileVo();
    	if (entityFile != null) {
    		copyEntityFileProperties(entityFile, entityFileVo);
    		entityFileVo.setUrl(result.getHttpUrl());
    		entityFileVo.setFinishedFlag(UploadFinishedFlag.FINISHED);
    		return entityFileVo;
    	} else if (size > 0){
    		entityFileVo.setSize((int)result.getTempFileSize());
    		entityFileVo.setFinishedFlag(UploadFinishedFlag.NOT_FINISHED);
    		return entityFileVo;
    	}
    	return "no_exist";
    }
    
    private void copyEntityFileProperties(EntityFile entityFile, EntityFileVo entityFileVo) {
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
