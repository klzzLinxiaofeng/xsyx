package platform.education.netdisk.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import platform.education.netdisk.common.ResponseBean;
import platform.education.netdisk.entity.FilesEntity;
import platform.education.netdisk.service.inter.FilesService;
import platform.education.netdisk.vo.FilesCondition;
import platform.education.netdisk.vo.FilesViewVo;
import platform.education.netdisk.vo.ResFileVo;
import platform.education.util.DownloadUtil;
import platform.service.storage.contants.FileStatusCode;
import platform.service.storage.model.EntityFile;
import platform.service.storage.service.FileService;
import platform.service.storage.vo.FileResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

//import platform.education.generalTeachingAffair.model.Teacher;


@Controller
@RequestMapping("/files")
public class FilesController { 
//
//	private final static String viewBasePath = "/resource/files";
//
	@Autowired
	private FilesService filesService;
//
	@Autowired
	@Qualifier("fileService")
	private FileService fileService;

//	@RequestMapping(value = "/creator", method = RequestMethod.POST)
//	@ResponseBody
//	public ResponseInfomation creator(Files files, @CurrentUser UserInfo user) {
//		files = this.filesService.add(files);
//		return files != null ? new ResponseInfomation(files.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
//	}

	@PostMapping(value = "/search")
	@ResponseBody
	public ResponseBean search(@RequestBody FilesCondition filesCondition,
							   HttpServletRequest request) {
		Integer userId = Integer.valueOf(request.getAttribute("userId").toString());

//		List<FilesEntity> filesEntitieList = filesService.selectWithEntityFile(userId, filesCondition.getName(), filesCondition.getType());
		List<ResFileVo> filesEntitieList = filesService.selectEntityFilesByFiles(filesCondition);
		List<FilesViewVo> voList = new ArrayList<>();
		for(ResFileVo entity : filesEntitieList){
			voList.add(new FilesViewVo(entity).setHttpUrl(this.fileService.relativePath2HttpUrl(entity.getRelativePath())));
		}
		return new ResponseBean(voList);
	}

	@PostMapping(value = "/delete/{id}")
//	@GetMapping("delete/{id}")
	@ResponseBody
	public ResponseBean delete(@PathVariable(value = "id") Integer id, HttpServletRequest request) {
		boolean result = false;
		Integer userId = Integer.valueOf(request.getAttribute("userId").toString());
//		Teacher teacher = (Teacher) request.getAttribute(userId.toString());
		FilesEntity entity = new FilesEntity();
//		if(teacher.getId()==null || !userId.equals(teacher.getUserId())) {
		entity.setId(id);
		entity.setOwnerId(userId);
//			List<FilesEntity> filesList = this.filesService.selectList(new EntityWrapper<>(entity));
//			entity = filesList==null || filesList.size() == 0? null : filesList.get(0);
//		}
//			this.filesService.deleteById(entity.getId());
		result = this.filesService.delete(new EntityWrapper<>(entity));
		return new ResponseBean(result);//result?new ResponseEntity(HttpStatus.OK):new ResponseEntity(HttpStatus.EXPECTATION_FAILED);
	}

	@PostMapping(value = "edit/{id}")
	@ResponseBody
	public ResponseBean edit(@PathVariable(value = "id") Integer id, FilesEntity files, HttpServletRequest request) {
		Integer userId = Integer.valueOf(request.getAttribute("userId").toString());
		files.setId(id);
		boolean b = this.filesService.update(files, new EntityWrapper<FilesEntity>().eq("id", id).eq("ownerId", userId));
		return new ResponseBean(b);//b ? new ResponseEntity(HttpStatus.OK):new ResponseEntity(HttpStatus.EXPECTATION_FAILED);
	}

	@PostMapping(value = "/copy")
	@ResponseBody
	public ResponseBean copy( @RequestBody FilesEntity filesEntity) {
		if(filesEntity.getId() == null)
			return new ResponseBean(ResponseBean.FAIL, "操作失败，请确认");

		FilesEntity files = this.filesService.selectById(filesEntity.getId());
		if(files != null) {
			files.setCatalogId(filesEntity.getCatalogId());
			files.setId(null);
			files.setCreateDate(null);
			files.setModifyDate(null);

		}
		return new ResponseBean(this.filesService.insert(files));// ? new ResponseEntity(HttpStatus.OK):new ResponseEntity(HttpStatus.EXPECTATION_FAILED);
	}


	@PostMapping(value = "/upload")
	@ResponseBody
	public ResponseBean upload(@RequestParam(required = false) MultipartFile file,
							   @RequestParam(defaultValue = "0") Integer catalogId,
								 HttpServletRequest request) throws IOException {
        Integer userId = Integer.valueOf(request.getAttribute("userId").toString());
//		String jsessionId = request.getParameter("jsessionId");
		if (file != null) {
			String uploadFileName = file.getOriginalFilename();
//			UserInfo userInfo = SessionManager.getUserInfoShiro(jsessionId);
			if (userId != null) {
				FileResult result = this.fileService.upload(file.getInputStream(), StringUtils.getFilenameExtension(uploadFileName), file.getContentType(), uploadFileName, "netdisk");
				if (result != null &&  FileStatusCode.UPLOAD_SUCCESS.equals(result.getStatusCode())) {
					EntityFile entityFile = result.getEntityFile();

					FilesEntity filesEntity = new FilesEntity();
					filesEntity.setName(entityFile.getFileName());
					filesEntity.setCatalogId(catalogId);
					while(filesService.selectCount(new EntityWrapper<>(filesEntity)) > 0){
//						String[] fileName = filesEntity.getName().split(".");
						String fileName = filesEntity.getName();
						int index = fileName.indexOf(".");
						String substring = fileName.substring(0, index);

//						fileName[fileName.length>=2?fileName.length - 2 : 0] += " - 副本";
						fileName = substring + " - 副本" + fileName.substring(index);
//						filesEntity.setName(String.join(".", fileName));
						filesEntity.setName(fileName);
					}
					filesEntity.setUuid(entityFile.getUuid());
					filesEntity.setOwnerId(userId);
					boolean insert = filesService.insert(filesEntity);
					if(insert && filesEntity.getId() != null){

						List<ResFileVo> resFileVos = filesService.selectEntityFilesByFiles(new FilesCondition().setId(filesEntity.getId()));
						if(resFileVos.size() > 0){
							return new ResponseBean(new FilesViewVo(resFileVos.get(0)).setHttpUrl(this.fileService.relativePath2HttpUrl(resFileVos.get(0).getRelativePath())));
						}
					}

					//?new ResponseEntity(HttpStatus.OK):new ResponseEntity(HttpStatus.EXPECTATION_FAILED);
				}
			}
		}
		return new ResponseBean(ResponseBean.FAIL, "操作失败，请确认");
	}


	@GetMapping(value = "/getUrl/{id}")
//	@GetMapping("delete/{id}")
	@ResponseBody
	public String getUrl(@PathVariable Integer id){
//		FilesCondition filesCondition = new FilesCondition();
//		filesCondition.setId(id);
//		List<ResFileVo> fileVos = filesService.selectEntityFilesByFiles(filesCondition);
		FilesEntity filesEntity = filesService.selectById(id);
		if(filesEntity != null){
//			String relativePath = fileVos.get(0).getRelativePath();
			FileResult file = fileService.findFileByUUID(filesEntity.getUuid());
			return file.getHttpUrl();
		} else {
			return "未找到该文件";
		}
	}
	@GetMapping(value = "/download/{id}")
//	@GetMapping("delete/{id}")
	public void download(@PathVariable Integer id,
								 HttpServletRequest request, HttpServletResponse response) {
		FilesEntity filesEntity = filesService.selectById(id);
		if (filesEntity == null){
			return;// new ResponseBean(ResponseBean.FAIL, "未找到指定的文件记录");
		}
		FileResult fileResult = fileService.findFileByUUID(filesEntity.getUuid());
		if (fileResult == null){
			return;// new ResponseBean(ResponseBean.FAIL, "源文件已丢失");
		}
		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			String filename = DownloadUtil.encodeFilenameForDownload(request, URLDecoder.decode(filesEntity.getName(), "UTF-8"));
			response.addHeader("content-type", "application/octet-stream");
			response.setContentType("application/octet-stream");
			response.setContentLength(fileResult.getEntityFile().getSize().intValue());
			response.setHeader("Content-Disposition", "attachment;filename="
					+ filename);
			OutputStream out = response.getOutputStream();
			String result = fileService.download(fileResult.getEntityFile().getUuid(), out);
//			return new ResponseBean(result);
		} catch (Exception e) {
			e.printStackTrace();
//			return new ResponseBean(ResponseBean.FAIL, "操作失败，请确认");
		}
	}
}
