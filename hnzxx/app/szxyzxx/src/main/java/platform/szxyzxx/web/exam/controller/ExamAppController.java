package platform.szxyzxx.web.exam.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import platform.education.exam.service.ExamService;
import platform.education.generalTeachingAffair.model.School;
import platform.education.generalTeachingAffair.model.SchoolUser;
import platform.education.generalTeachingAffair.service.SchoolService;
import platform.education.generalTeachingAffair.service.SchoolUserService;
import platform.education.generalTeachingAffair.service.SubjectService;
import platform.education.generalTeachingAffair.vo.SchoolUserCondition;
import platform.education.generalcode.service.GradeService;
import platform.education.generalcode.service.StageService;
import platform.education.generalcode.service.TextbookVersionService;
import platform.education.generalcode.service.TextbookVolumnService;
import platform.education.homework.service.HomeworkService;
import platform.education.paper.model.PaperResult;
import platform.education.paper.service.PaPaperHandleService;
import platform.education.resource.model.ResourceLibrary;
import platform.education.resource.service.CatalogResourceService;
import platform.education.resource.service.ResourceLibraryService;
import platform.education.resource.service.ResourceService;
import platform.service.storage.service.EntityFileService;
import platform.service.storage.service.FileService;
import platform.service.storage.vo.FileResult;
import platform.szxyzxx.web.bbx.client.vo.ResponseError;
import platform.szxyzxx.web.bbx.client.vo.ResponseInfo;
import platform.szxyzxx.web.common.contants.SysContants;
import platform.szxyzxx.web.common.controller.base.BaseController;

@Controller
@RequestMapping(value = "/res/paper")
public class ExamAppController extends BaseController {
	// 学校用户
	@Resource
	private SchoolUserService schoolUserService;
	// 学校
	@Resource
	private SchoolService schoolService;
	@Resource
	private TextbookVolumnService jcTextbookVolumnService;
	// 年级
	@Resource
	private GradeService jcGradeService;
	@Resource
	private ResourceLibraryService resourceLibraryService;
	// 资源
	@Resource
	private ResourceService resourceService;
	// 资源目录
	@Resource
	private CatalogResourceService catalogResourceService;
	// 试卷
	@Resource
	private ExamService examService;
	// 作业
	@Resource
	private HomeworkService homeworkService;
	// 资源文件
	@Resource
	private EntityFileService entityFileService;
	// 上传
	@Resource
	protected FileService fileService;
	@Resource
	private PaPaperHandleService paPaperHandleService;
	
	@Resource
	private StageService stageService;
	
	@Resource
	private SubjectService subjectService;
	
	@Resource
	private TextbookVersionService textbookVersionService;
	
	@Resource
	private TextbookVolumnService textbookVolumnService;

	@RequestMapping(value = "/school/upload")
	@ResponseBody
	public Object uploadXep(@RequestParam("file") MultipartFile xepFile,
			@RequestParam("userId") Integer userId,
			@RequestParam("type") Integer type,
			@RequestParam(value="isCover", defaultValue="0") Integer isCover,
			HttpServletRequest request,
			HttpServletResponse response) throws NumberFormatException, Exception {
		
		/**判断必填参数是否为null*/
		if (userId != null && !"".equals(userId) && xepFile != null) {
			try {
				/**上传磁盘*/
				Map<String, Object> map = writedisk(xepFile,request, response);
				if(map != null && map.size() >0){
					String filePath = map.get("filePath").toString();
					String targerPath = map.get("targerPath").toString();
					return uloadXepTFtp(userId, filePath, targerPath,targerPath, xepFile, type, isCover);
				}else{
					ResponseInfo info = new ResponseInfo();
					info.setDetail("上传文件失败！");
					info.setMsg("服务器异常");
					return new ResponseError("000002", info);
				}
			} catch (Exception e) {
				ResponseInfo info = new ResponseInfo();
				info.setDetail("服务器异常");
				info.setMsg("服务器异常");
				return new ResponseError("000002", info);
			}
			
		}
		return null;
	}
	
	private Object uloadXepTFtp(Integer userId,String filePath,String targerPath,
			String decompression,MultipartFile xepFile,Integer type, Integer isCover){
		PaperResult paperResult = null;
		try{
			/**带后缀的文件名*/	
			final String uploadFileName = xepFile.getOriginalFilename();
			/**xepFile*/
			if (uploadFileName.contains(".xep")) {
				/**用户所在学校信息*/
				School school = getSchoolIdByUserId(userId);
				ResourceLibrary resourceLibrary = this.resourceLibraryService.findByAppIdAndOwnerId(SysContants.SYSTEM_APP_ID, school.getId());
				String schoolUuid = school.getUuid();
				Integer relId = resourceLibrary.getId();
				
				/**上传xep包*/
				FileResult result = this.fileService.upload(xepFile.getInputStream(),StringUtils.getFilenameExtension(uploadFileName),xepFile.getContentType(), uploadFileName,String.valueOf(SysContants.SYSTEM_APP_ID));
				String fileUuid = result.getEntityFile().getUuid();
				paperResult = this.paPaperHandleService.paperSplit(userId,filePath, decompression, fileUuid, schoolUuid, relId, type, isCover);
			}
		}catch(NullPointerException e){
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("文档图片数据异常");
			info.setMsg("文档图片数据异常");
			return new ResponseError("000002", info);
		}catch(Exception e){
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("服务器异常");
			info.setMsg("服务器异常");
			return new ResponseError("000002", info);
		}
		
		ResponseInfo info = new ResponseInfo();
		if(paperResult.getStatus()==1) {
			info.setParam("file");
			info.setDetail(paperResult.getMsg());
			info.setMsg(paperResult.getMsg());
		} else {
			info.setDetail("试卷上传成功");
			info.setMsg("试卷上传成功");
		}
		return new ResponseError(paperResult.getStatus()+"", info);

	}

	private School getSchoolIdByUserId(Integer userId) {
		/**用户所在学校信息*/
		SchoolUserCondition suc=new SchoolUserCondition();
		suc.setUserId(userId);
		List<SchoolUser> schoolUserList =this.schoolUserService.findSchoolUserByCondition(suc,null,null);
		
		if(schoolUserList.size()>0) {
			Integer schoolId = schoolUserList.get(0).getSchoolId();
			School school = schoolService.findSchoolById(schoolId);
			return school;
		}
		return null;
	}
	
	private Map<String,Object> writedisk(MultipartFile xepFile,HttpServletRequest request,HttpServletResponse response){
		InputStream in = null;
		FileOutputStream os = null;
		Map<String,Object> result = new HashMap<String,Object>(); 
        CommonsMultipartFile cfile =(CommonsMultipartFile) xepFile;
        	
    	if(!cfile.isEmpty()){
			try {
				request.setCharacterEncoding("UTF-8");  
	        	response.setContentType("text/html;charset=UTF-8"); 
	        	String storePath = request.getServletContext().getRealPath("/WEB-INF/files/"); 
	            boolean isMultipart = ServletFileUpload.isMultipartContent(request);  
	            if(!isMultipart) {
	                return null;  
	            }
				
				Date date = new Date();
        		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        		String s = sdf.format(date);
        		
        		String fileName = cfile.getOriginalFilename();  
	    		fileName = fileName.substring(fileName.lastIndexOf("\\")+1);  
	    		fileName = UUID.randomUUID()+"_"+fileName;  
	    		
	    		String basePath = storePath + File.separator+ s + File.separator;
	    		
	    		result.put("filePath", basePath + fileName);
	    		result.put("targerPath", basePath);
	    		String newStorePath = makeStorePath(storePath,s);  
	            String storeFile = newStorePath+ File.separator +fileName; 
				
				//定义输出流 将文件保存在D盘    file.getOriginalFilename()为获得文件的名字 
				os = new FileOutputStream(storeFile);
				in = cfile.getInputStream();
				byte[] buff = new byte[1024];
				while((in.read(buff))!=-1){ //读取文件 
					os.write(buff);
				}
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}finally {
				try {
					if(os != null){
						os.flush();
						os.close();  
					}
					
					if(in != null){
						in.close();  
					}
				} catch (IOException e) {
					e.printStackTrace();
				}  
			}
		}
        	
		return result;
		
	}

	private String makeStorePath(String storePath,String s) {
		String path = storePath + File.separator + s;
		File file = new File(path);
		if (!file.exists()) {
			file.mkdirs();// 创建多级目录，mkdir只创建一级目录
		}
		return path;
	}

}
