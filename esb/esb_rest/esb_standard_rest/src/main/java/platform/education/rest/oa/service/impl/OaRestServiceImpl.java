package platform.education.rest.oa.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.lang.math.NumberUtils;
import org.codehaus.stax2.ri.typed.NumberUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.model.Department;
import platform.education.generalTeachingAffair.model.DepartmentTeacher;
import platform.education.generalTeachingAffair.model.School;
import platform.education.generalTeachingAffair.model.Teacher;
import platform.education.generalTeachingAffair.service.DepartmentService;
import platform.education.generalTeachingAffair.service.DepartmentTeacherService;
import platform.education.generalTeachingAffair.service.SchoolService;
import platform.education.generalTeachingAffair.service.TeacherService;
import platform.education.generalTeachingAffair.vo.DepartmentTeacherCondition;
import platform.education.generalTeachingAffair.vo.TeacherCondition;
import platform.education.notice.model.Notice;
import platform.education.notice.model.NoticeFile;
import platform.education.notice.model.NoticeRead;
import platform.education.notice.model.NoticeReceiver;
import platform.education.oa.service.SchoolNoticeService;
import platform.education.rest.common.ResponseError;
import platform.education.rest.common.ResponseInfo;
import platform.education.rest.common.ResponseVo;
import platform.education.rest.common.constants.CommonCode;
import platform.education.rest.oa.service.OaRestService;
import platform.education.rest.oa.service.contants.OaPushConstant;
import platform.education.rest.oa.service.util.NoticeComparator;
import platform.education.rest.oa.service.util.NoticeUtil;
import platform.education.rest.oa.service.vo.FileInfo;
import platform.education.rest.oa.service.vo.FileMsg;
import platform.education.rest.oa.service.vo.NoticeInfo;
import platform.education.rest.oa.service.vo.NoticeMess;
import platform.education.rest.oa.service.vo.UserInfo;
import platform.education.rest.oa.service.vo.UserRead;
import platform.education.rest.util.ImgUtil;
import platform.education.rest.util.PushUtil;
import platform.education.rest.util.ReciverType;
import platform.education.user.service.ProfileService;
import platform.service.storage.model.EntityFile;
import platform.service.storage.service.EntityFileService;
import platform.service.storage.service.FileService;
import platform.service.storage.vo.FileResult;
public class OaRestServiceImpl implements OaRestService{
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Autowired
	@Qualifier("schoolService")
	private SchoolService schoolService;
	
	@Autowired
	@Qualifier("schoolnoticeService")
	private SchoolNoticeService schoolNoticeService;
	
	@Autowired
	@Qualifier("entityFileService")
	private EntityFileService entityFileService;
	
	@Autowired
	@Qualifier("profileService")
	private ProfileService profileService;
	
	@Autowired
	@Qualifier("departmentTeacherService")
	private DepartmentTeacherService departmentTeacherService;
	
	@Autowired
	@Qualifier("teacherService")
	private TeacherService teacherService;
	
	@Autowired
	@Qualifier("departmentService")
	private DepartmentService departmentService;
	
	@Autowired
	@Qualifier("fileService")
	private FileService fileService;
	

	@Override
	public Object getSchoolNotice(Integer userId,String schoolId, String beginDate,String endDate,
			String pageNumber,String pageSize,String sortItem,Integer sortType) {
		List<Notice> noticeList = new ArrayList<Notice>();
		Date bDate = null;
		Date eDate = null;
		NoticeMess noticeMess = null;
		
		FileMsg fm = null;
		List<NoticeMess> msgList = new ArrayList<NoticeMess>();
		List<NoticeRead> readList = new ArrayList<NoticeRead>();
		Order order = new Order();
		Page page = new Page();
		try {
			if(userId == null|| "".equals(userId)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("userId参数必填");
				info.setMsg("userId参数不能为空");
				info.setParam("userId");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			if(schoolId == null|| "".equals(schoolId)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("schoolId参数必填");
				info.setMsg("schoolId参数不能为空");
				info.setParam("schoolId");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			if(beginDate != null && !"".equals(beginDate) ){
				bDate = sdf.parse(beginDate);
			}
			if(endDate != null && !"".equals(endDate)){
				eDate = sdf.parse(endDate);
			}
			order.setProperty(sortItem);
			order.setAscending(sortType == 0? true:false);
			page.setCurrentPage(Integer.parseInt(pageNumber));
			page.setPageSize(Integer.parseInt(pageSize));
			noticeList = this.schoolNoticeService.findNoticesBySchool(Integer.parseInt(schoolId), 
					bDate, eDate, page, order);
			for(Notice notice:noticeList){
				List<FileMsg> files = new ArrayList<FileMsg>();
				noticeMess = new NoticeMess();
				List<NoticeFile> list = this.schoolNoticeService.findNoticeFiles(notice, null, null);
				FileResult fileresult = null;
				for(NoticeFile file:list){
					EntityFile entity = entityFileService.findFileByUUID(file.getFileUuid());
					if(entity!=null){
						if(entity.getExtension().contentEquals("gif")
								||entity.getExtension().contentEquals("jpg")
								||entity.getExtension().contentEquals("png")
								||entity.getExtension().contentEquals("jpeg")){
							fileresult = fileService.findFileByUUID(file.getFileUuid());
							if(fileresult != null){
								fm = new FileMsg();
								fm.setFileUrl(fileresult.getHttpUrl());
								fm.setFileUuid(file.getFileUuid());
								fm.setFileName(entity.getFileName());
								files.add(fm);
							}
						}
					}
				}
				readList = this.schoolNoticeService.isRead(notice.getId());
				if(readList.size()==0){
					noticeMess.setHasRead(0);
				}else{
					Boolean exit = false;
					for(NoticeRead read:readList){
						if(exit==false){
							if(read.getUserId().equals(userId)){
								noticeMess.setHasRead(1);
								exit=true;
							}else{
								noticeMess.setHasRead(0);
							}
						}
					}
				}
				notice = NoticeUtil.unescapeNoticeHtml(notice);
				noticeMess.setFiles(files);
				noticeMess.setId(notice.getId());
				noticeMess.setUuid(notice.getUuid());
				noticeMess.setAppKey(notice.getAppKey());
				noticeMess.setTitle(notice.getTitle());
				noticeMess.setPosterId(notice.getPosterId());
				noticeMess.setPosterName(notice.getPosterName());
				noticeMess.setPostTime(sdf.format(notice.getPostTime()));
				noticeMess.setReceiverType(notice.getReceiverType());
				noticeMess.setReceiverName(notice.getReceiverName());
				noticeMess.setContent(notice.getContent());
				noticeMess.setUserCount(notice.getUserCount());
				noticeMess.setReadCount(notice.getReadCount());
				noticeMess.setCreateDate(sdf.format(notice.getCreateDate()));
				
				msgList.add(noticeMess);
			}
			return new ResponseVo<List<NoticeMess>>("0",msgList);
		} catch (Exception e) {
			ResponseInfo info = new ResponseInfo();
			info.setDetail("数据返回异常...");
			info.setMsg("找不到数据");
			info.setParam("noticeMsg");
			return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
		}
	}


	@Override
	public Object getDepartmentNotice(Integer userId, String beginDate,
			String endDate, String pageNumber, String pageSize,
			String sortItem, Integer sortType) {
		Set<Notice> noticeList = new TreeSet<Notice>(new NoticeComparator());
		Date bDate = null;
		Date eDate = null;
		NoticeMess noticeMess = null;
		FileMsg fm = null;
		List<NoticeMess> msgList = new ArrayList<NoticeMess>();
		List<NoticeRead> readList = new ArrayList<NoticeRead>();
		List<DepartmentTeacher> teacherList = new ArrayList<DepartmentTeacher>();
		Order order = new Order();
		Page page = new Page();
		try {
			if(userId == null|| "".equals(userId)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("userId参数必填");
				info.setMsg("userId参数不能为空");
				info.setParam("userId");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			if(beginDate != null && !"".equals(beginDate) ){
				bDate = sdf.parse(beginDate);
			}
			if(endDate != null && !"".equals(endDate)){
				eDate = sdf.parse(endDate);
			}
			order.setProperty(sortItem);
			order.setAscending(sortType == 0? true:false);
			page.setCurrentPage(Integer.parseInt(pageNumber));
			page.setPageSize(Integer.parseInt(pageSize));
			TeacherCondition condition = new TeacherCondition();
			condition.setUserId(userId);
			List<Teacher> teachers = this.teacherService.findTeacherByCondition(condition, null, null);
			for(Teacher teacher :teachers){
				teacherList = this.departmentTeacherService.findDepartmentTeacherByTeacherIdAndSchoolId(teacher.getId(),teacher.getSchoolId());
			}
			for(DepartmentTeacher dTeacher :teacherList){
				List<Notice> notices= this.schoolNoticeService.findNoticesByDepartment(dTeacher.getDepartmentId(), 
						bDate, eDate, null, order);	
				noticeList.addAll(notices);
			}
			List<Notice> items = NoticeUtil.getNoticeListByPage(noticeList, page.getCurrentPage(), page.getPageSize());
			page = NoticeUtil.resetPage(items.size(), page);
			
			for(Notice notice:items){
				List<FileMsg> files = new ArrayList<FileMsg>();
				noticeMess = new NoticeMess();
				List<NoticeFile> list = this.schoolNoticeService.findNoticeFiles(notice, null, null);
				FileResult fileresult = null;
				for(NoticeFile file:list){
					EntityFile entity = entityFileService.findFileByUUID(file.getFileUuid());
					if(entity!=null){
						if(entity.getExtension().contentEquals("gif")
								||entity.getExtension().contentEquals("jpg")
								||entity.getExtension().contentEquals("png")
								||entity.getExtension().contentEquals("jpeg")){
							fileresult = fileService.findFileByUUID(file.getFileUuid());
							if(fileresult != null){
								fm = new FileMsg();
								fm.setFileUrl(fileresult.getHttpUrl());
								fm.setFileUuid(file.getFileUuid());
								fm.setFileName(entity.getFileName());
								files.add(fm);
							}
						}
						
					}
				}
				readList = this.schoolNoticeService.isRead(notice.getId());
				if(readList.size()==0){
					noticeMess.setHasRead(0);
				}else{
					Boolean exit = false;
					for(NoticeRead read:readList){
						if(exit==false){
							if(read.getUserId().equals(userId)){
								noticeMess.setHasRead(1);
								exit=true;
							}else{
								noticeMess.setHasRead(0);
							}
						}
					}
				}
				notice = NoticeUtil.unescapeNoticeHtml(notice);
				noticeMess.setFiles(files);
				noticeMess.setId(notice.getId());
				noticeMess.setUuid(notice.getUuid());
				noticeMess.setAppKey(notice.getAppKey());
				noticeMess.setTitle(notice.getTitle());
				noticeMess.setPosterId(notice.getPosterId());
				noticeMess.setPosterName(notice.getPosterName());
				noticeMess.setPostTime(sdf.format(notice.getPostTime()));
				noticeMess.setReceiverType(notice.getReceiverType());
				noticeMess.setReceiverName(notice.getReceiverName());
				noticeMess.setContent(notice.getContent());
				noticeMess.setUserCount(notice.getUserCount());
				noticeMess.setReadCount(notice.getReadCount());
				noticeMess.setCreateDate(sdf.format(notice.getCreateDate()));
				msgList.add(noticeMess);
			}
			return new ResponseVo<List<NoticeMess>>("0",msgList);
		} catch (Exception e) {
			ResponseInfo info = new ResponseInfo();
			info.setDetail("数据返回异常...");
			info.setMsg("找不到数据");
			info.setParam("noticeMsg");
			return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
		}
	}


	@Override
	public Object getPersonNotice(Integer userId, String beginDate,
			String endDate, String pageNumber, String pageSize,
			String sortItem, Integer sortType) {
		List<Notice> noticeList = new ArrayList<Notice>();
		NoticeMess noticeMess = null;
		
		FileMsg fm = null;
		List<NoticeMess> msgList = new ArrayList<NoticeMess>();
		List<NoticeRead> readList = new ArrayList<NoticeRead>();
		Date bDate = null;
		Date eDate = null;
		Order order = new Order();
		Page page = new Page();
		try {
			if(userId == null|| "".equals(userId)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("userId参数必填");
				info.setMsg("userId参数不能为空");
				info.setParam("userId");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			if(beginDate != null && !"".equals(beginDate) ){
				bDate = sdf.parse(beginDate);
			}
			if(endDate != null && !"".equals(endDate)){
				eDate = sdf.parse(endDate);
			}
			order.setProperty(sortItem);
			order.setAscending(sortType == 0? true:false);
			page.setCurrentPage(Integer.parseInt(pageNumber));
			page.setPageSize(Integer.parseInt(pageSize));
			noticeList = this.schoolNoticeService.findNoticesByPerson(userId, 
					bDate, eDate, page, order);
			for(Notice notice:noticeList){
				List<FileMsg> files = new ArrayList<FileMsg>();
				noticeMess = new NoticeMess();
				List<NoticeFile> list = this.schoolNoticeService.findNoticeFiles(notice, null, null);
				FileResult fileresult = null;
				for(NoticeFile file:list){
					fm = new FileMsg();
					EntityFile entity = entityFileService.findFileByUUID(file.getFileUuid());
					if(entity!=null){
						if(entity.getExtension().contentEquals("gif")
								||entity.getExtension().contentEquals("jpg")
								||entity.getExtension().contentEquals("png")
								||entity.getExtension().contentEquals("jpeg")){
							fileresult = fileService.findFileByUUID(file.getFileUuid());
							if(fileresult != null){
								fm.setFileUrl(fileresult.getHttpUrl());
								fm.setFileUuid(file.getFileUuid());
								fm.setFileName(entity.getFileName());
								files.add(fm);
							}
						}
											
					}
				}
				readList = this.schoolNoticeService.isRead(notice.getId());
				if(readList.size()==0){
					noticeMess.setHasRead(0);
				}else{
					Boolean exit = false;
					for(NoticeRead read:readList){
						if(exit==false){
							if(read.getUserId().equals(userId)){
								noticeMess.setHasRead(1);
								exit=true;
							}else{
								noticeMess.setHasRead(0);
							}
						}
					}
				}
				notice = NoticeUtil.unescapeNoticeHtml(notice);
				noticeMess.setFiles(files);
				noticeMess.setId(notice.getId());
				noticeMess.setUuid(notice.getUuid());
				noticeMess.setAppKey(notice.getAppKey());
				noticeMess.setTitle(notice.getTitle());
				noticeMess.setPosterId(notice.getPosterId());
				noticeMess.setPosterName(notice.getPosterName());
				noticeMess.setPostTime(sdf.format(notice.getPostTime()));
				noticeMess.setReceiverType(notice.getReceiverType());
				noticeMess.setReceiverName(notice.getReceiverName());
				noticeMess.setContent(notice.getContent());
				noticeMess.setUserCount(notice.getUserCount());
				noticeMess.setReadCount(notice.getReadCount());
				noticeMess.setCreateDate(sdf.format(notice.getCreateDate()));
				msgList.add(noticeMess);
			}
			return new ResponseVo<List<NoticeMess>>("0",msgList);
		} catch (Exception e) {
			ResponseInfo info = new ResponseInfo();
			info.setDetail("数据返回异常...");
			info.setMsg("找不到数据");
			info.setParam("noticeMsg");
			return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
		}
	}


	@Override
	public Object getUserNotice(String schoolId, Integer userId,
			String beginDate, String endDate, String pageNumber,
			String pageSize, String sortItem, Integer sortType) {
		List<DepartmentTeacher> dtList = new ArrayList<DepartmentTeacher>();
		List<Notice> schoolList = new ArrayList<Notice>();
		List<Notice> personList = new ArrayList<Notice>();
		List<Notice> departmentList = null;
		NoticeMess noticeMess = null;
		FileMsg fm = null;
		List<NoticeMess> msgList = new ArrayList<NoticeMess>();
		List<NoticeRead> readList = new ArrayList<NoticeRead>();
		Date bDate = null;
		Date eDate = null;
		Order order = new Order();
		Page page = new Page();
		try {
			if(schoolId == null|| "".equals(schoolId)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("schoolId参数必填");
				info.setMsg("schoolId参数不能为空");
				info.setParam("schoolId");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			if(userId == null|| "".equals(userId)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("userId参数必填");
				info.setMsg("userId参数不能为空");
				info.setParam("userId");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			
			Teacher teacher = this.teacherService.findOfUser(Integer.parseInt(schoolId), userId);
			if(teacher == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("不存在该教师");
				info.setMsg("userId可能出错");
				info.setParam("userId");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			
			if(beginDate != null && !"".equals(beginDate) ){
				bDate = sdf.parse(beginDate);
			}
			if(endDate != null && !"".equals(endDate)){
				eDate = sdf.parse(endDate);
			}
			order.setProperty(sortItem);
			order.setAscending(sortType == 0? true:false);
			page.setCurrentPage(Integer.parseInt(pageNumber));
			page.setPageSize(Integer.parseInt(pageSize));
			schoolList = this.schoolNoticeService.findNoticesBySchool(Integer.parseInt(schoolId), 
					bDate, eDate, page, order);
			for(Notice notice:schoolList){
				List<FileMsg> files = new ArrayList<FileMsg>();
				noticeMess = new NoticeMess();
				List<NoticeFile> list = this.schoolNoticeService.findNoticeFiles(notice, null, null);
				FileResult fileresult = null;
				for(NoticeFile file:list){
					fm = new FileMsg();
					EntityFile entity = entityFileService.findFileByUUID(file.getFileUuid());
					if(entity!=null){
						if(entity.getExtension().contentEquals("gif")
								||entity.getExtension().contentEquals("jpg")
								||entity.getExtension().contentEquals("png")
								||entity.getExtension().contentEquals("jpeg")){
							fileresult = fileService.findFileByUUID(file.getFileUuid());
							if(fileresult != null){
								fm.setFileUrl(fileresult.getHttpUrl());
								fm.setFileUuid(file.getFileUuid());
								fm.setFileName(entity.getFileName());
								files.add(fm);
							}
						}
						
					}
				}
				readList = this.schoolNoticeService.isRead(notice.getId());
				if(readList.size()==0){
					noticeMess.setHasRead(0);
				}else{
					Boolean exit = false;
					for(NoticeRead read:readList){
						if(exit==false){
							if(read.getUserId().equals(userId)){
								noticeMess.setHasRead(1);
								exit=true;
							}else{
								noticeMess.setHasRead(0);
							}
						}
					}
				}
				notice = NoticeUtil.unescapeNoticeHtml(notice);
				noticeMess.setFiles(files);
				noticeMess.setId(notice.getId());
				noticeMess.setUuid(notice.getUuid());
				noticeMess.setAppKey(notice.getAppKey());
				noticeMess.setTitle(notice.getTitle());
				noticeMess.setPosterId(notice.getPosterId());
				noticeMess.setPosterName(notice.getPosterName());
				noticeMess.setPostTime(sdf.format(notice.getPostTime()));
				noticeMess.setReceiverType(notice.getReceiverType());
				noticeMess.setReceiverName(notice.getReceiverName());
				noticeMess.setContent(notice.getContent());
				noticeMess.setUserCount(notice.getUserCount());
				noticeMess.setReadCount(notice.getReadCount());
				noticeMess.setCreateDate(sdf.format(notice.getCreateDate()));
				msgList.add(noticeMess);
			}
			personList = this.schoolNoticeService.findNoticesByPerson(userId, 
					bDate, eDate, page, order);
			for(Notice notice:personList){
				List<FileMsg> files = new ArrayList<FileMsg>();
				noticeMess = new NoticeMess();
				List<NoticeFile> list = this.schoolNoticeService.findNoticeFiles(notice, null, null);
				FileResult fileresult = null;
				for(NoticeFile file:list){
					fm = new FileMsg();
					EntityFile entity = entityFileService.findFileByUUID(file.getFileUuid());
					if(entity!=null){
						if(entity.getExtension().contentEquals("gif")
								||entity.getExtension().contentEquals("jpg")
								||entity.getExtension().contentEquals("png")
								||entity.getExtension().contentEquals("jpeg")){
							fileresult = fileService.findFileByUUID(file.getFileUuid());
							if(fileresult != null){
								fm.setFileUrl(fileresult.getHttpUrl());
								fm.setFileUuid(file.getFileUuid());
								fm.setFileName(entity.getFileName());
								files.add(fm);
							}
						}
					}
				}
				readList = this.schoolNoticeService.isRead(notice.getId());
				if(readList.size()==0){
					noticeMess.setHasRead(0);
				}else{
					Boolean exit = false;
					for(NoticeRead read:readList){
						if(exit==false){
							if(read.getUserId().equals(userId)){
								noticeMess.setHasRead(1);
								exit=true;
							}else{
								noticeMess.setHasRead(0);
							}
						}
					}
				}
				noticeMess.setFiles(files);
				noticeMess.setId(notice.getId());
				noticeMess.setUuid(notice.getUuid());
				noticeMess.setAppKey(notice.getAppKey());
				noticeMess.setTitle(notice.getTitle());
				noticeMess.setPosterId(notice.getPosterId());
				noticeMess.setPosterName(notice.getPosterName());
				noticeMess.setPostTime(sdf.format(notice.getPostTime()));
				noticeMess.setReceiverType(notice.getReceiverType());
				noticeMess.setReceiverName(notice.getReceiverName());
				noticeMess.setContent(notice.getContent());
				noticeMess.setUserCount(notice.getUserCount());
				noticeMess.setReadCount(notice.getReadCount());
				noticeMess.setCreateDate(sdf.format(notice.getCreateDate()));
				msgList.add(noticeMess);
			}
			
			dtList = this.departmentTeacherService.findDepartmentTeacherByTeacherIdAndSchoolId(teacher.getId(),Integer.parseInt(schoolId));
			
			for(DepartmentTeacher dt:dtList){
				departmentList = new ArrayList<Notice>();
				departmentList = this.schoolNoticeService.findNoticesByDepartment(
						dt.getDepartmentId(), bDate, eDate, page, order);
				for(Notice notice:departmentList){
					List<FileMsg> files = new ArrayList<FileMsg>();
					noticeMess = new NoticeMess();
					List<NoticeFile> list = this.schoolNoticeService.findNoticeFiles(notice, null, null);
					FileResult fileresult = null;
					for(NoticeFile file:list){
						fm = new FileMsg();
						EntityFile entity = entityFileService.findFileByUUID(file.getFileUuid());
						if(entity!=null){
							if(entity.getExtension().contentEquals("gif")
									||entity.getExtension().contentEquals("jpg")
									||entity.getExtension().contentEquals("png")
									||entity.getExtension().contentEquals("jpeg")){
								fileresult = fileService.findFileByUUID(file.getFileUuid());
								if(fileresult != null){
									fm.setFileUrl(fileresult.getHttpUrl());
									fm.setFileUuid(file.getFileUuid());
									fm.setFileName(entity.getFileName());
									files.add(fm);
								}
							}
						}
						
					}
					readList = this.schoolNoticeService.isRead(notice.getId());
					if(readList.size()==0){
						noticeMess.setHasRead(0);
					}else{
						Boolean exit = false;
						for(NoticeRead read:readList){
							if(exit==false){
								if(read.getUserId().equals(userId)){
									noticeMess.setHasRead(1);
									exit=true;
								}else{
									noticeMess.setHasRead(0);
								}
							}
						}
					}
					noticeMess.setFiles(files);
					noticeMess.setId(notice.getId());
					noticeMess.setUuid(notice.getUuid());
					noticeMess.setAppKey(notice.getAppKey());
					noticeMess.setTitle(notice.getTitle());
					noticeMess.setPosterId(notice.getPosterId());
					noticeMess.setPosterName(notice.getPosterName());
					noticeMess.setPostTime(sdf.format(notice.getPostTime()));
					noticeMess.setReceiverType(notice.getReceiverType());
					noticeMess.setReceiverName(notice.getReceiverName());
					noticeMess.setContent(notice.getContent());
					noticeMess.setUserCount(notice.getUserCount());
					noticeMess.setReadCount(notice.getReadCount());
					noticeMess.setCreateDate(sdf.format(notice.getCreateDate()));
					msgList.add(noticeMess);
				}
			}
			List<NoticeMess> newMsgList = new ArrayList<NoticeMess>();
			for (NoticeMess m1 : msgList) {
				boolean flag = true;
				if (newMsgList != null) {
					for (NoticeMess m2 : newMsgList) {
						if (m1.getId().equals(m2.getId())) {
							flag = false;
							break;
						}
					}
				}
				if (flag) {
					newMsgList.add(m1);
				}
			}
			
			return new ResponseVo<List<NoticeMess>>("0",newMsgList);
		} catch (Exception e) {
			ResponseInfo info = new ResponseInfo();
			info.setDetail("查询错误");
			info.setMsg("参数出错");
			info.setParam("noticeMsg");
			return new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR, info);
		}
	}

	@Override
	public Object sendSchoolNotice(String schoolIds, Integer posterId,
			String posterName, String appKey, String title, String content,
			String postTime, String files) {
		Integer count = null;
		Integer userCount = 0;
		String schools = new String();
		String[] uuids = null;
		String[] schoolId = null;
		List<String> noticeFiles = null;
		List<Integer> schoolIdList = new ArrayList<Integer>();
		List<Integer> allUserIds = new ArrayList<Integer>(); 
		List<Integer> userIds = null;
		
		Notice notice = new Notice();
		try {
			if(schoolIds == null|| "".equals(schoolIds)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("schoolIds参数必填");
				info.setMsg("schoolIds参数不能为空");
				info.setParam("schoolIds");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			if(posterId == null|| "".equals(posterId)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("posterId参数必填");
				info.setMsg("posterId参数不能为空");
				info.setParam("posterId");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			if(posterName == null|| "".equals(posterName)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("posterName参数必填");
				info.setMsg("posterName参数不能为空");
				info.setParam("posterName");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			if(appKey == null|| "".equals(appKey)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("appKey参数必填");
				info.setMsg("appKey参数不能为空");
				info.setParam("appKey");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			if(title == null|| "".equals(title)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("title参数必填");
				info.setMsg("title参数不能为空");
				info.setParam("title");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			if(content == null|| "".equals(content)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("content参数必填");
				info.setMsg("content参数不能为空");
				info.setParam("content");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			if(files != null){
				noticeFiles = new ArrayList<String>();
				uuids = files.split(",");
				for(int i=0;i<uuids.length;i++){
					noticeFiles.add(uuids[i]);
				}
			}

			schoolId = schoolIds.split(",");
			for(int i=0;i<schoolId.length;i++){
				schoolIdList.add(Integer.parseInt(schoolId[i]));
			}
			for(Integer id :schoolIdList){
				userIds = new ArrayList<Integer>();
				//获取每个学校教师的userId
				userIds = this.getUserIdsBySchool(id);
				//添加到所有的id里面
				allUserIds.addAll(userIds);
				School school = this.schoolService.findSchoolById(id);
				schools += school.getName()+",";
				count = Integer.parseInt(this.teacherService.findTeacherNumBySchoolId(id));
				userCount += count;
			}
			String schoolNames = schools.substring(0, schools.length()-1);
			if(postTime==null || "".equals(postTime)){
				postTime = sdf.format(new Date());
			}
		
			notice.setPosterId(posterId);
			notice.setPosterName(posterName);
			notice.setAppKey(appKey);
			notice.setTitle(title);
			notice.setReceiverName(schoolNames);
			notice.setPostTime(sdf.parse(postTime));
			notice.setContent(content);
			notice.setUserCount(userCount);
			Notice noti = this.schoolNoticeService.SendSchoolNotice(notice, noticeFiles, schoolIdList);
			
			//推送
			PushUtil.push(removeRep(allUserIds), ReciverType.R$SCHOOL, noti.getId(), "326", ("".equals(noti.getTitle())||noti.getTitle()==null)?getContent(noti.getContent()):noti.getTitle());
			
			NoticeInfo info = new NoticeInfo();
			info.setId(noti.getId());
			info.setCreateTime(sdf.format(noti.getCreateDate()));
			return new ResponseVo<NoticeInfo>("0", info);
		} catch (Exception e) {
			ResponseInfo info = new ResponseInfo();
			info.setMsg("添加记录异常");
			info.setDetail("参数异常");
			info.setParam("notice");
			return new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR, info);
		}
	}


	@Override
	public Object sendDepartmentNotice(String departmentIds, Integer posterId,
			String posterName, String appKey, String title, String content,
			String postTime, String files) {
		Integer userCount = 0;
		String[] uuids = null;
		String[] departmentId = null;
		List<String> noticeFiles = null;
		List<Integer> departmentIdList = new ArrayList<Integer>();
		List<Integer> allUserIds = new ArrayList<Integer>();
		List<Integer> userIds = null;
		
		Notice notice = new Notice();
		DepartmentTeacherCondition condition = null;
		String departments = new String();
		try {
			if(departmentIds == null|| "".equals(departmentIds)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("departmentIds参数必填");
				info.setMsg("departmentIds参数不能为空");
				info.setParam("departmentIds");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			if(posterId == null|| "".equals(posterId)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("posterId参数必填");
				info.setMsg("posterId参数不能为空");
				info.setParam("posterId");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			if(posterName == null|| "".equals(posterName)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("posterName参数必填");
				info.setMsg("posterName参数不能为空");
				info.setParam("posterName");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			if(appKey == null|| "".equals(appKey)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("appKey参数必填");
				info.setMsg("appKey参数不能为空");
				info.setParam("appKey");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			if(title == null|| "".equals(title)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("title参数必填");
				info.setMsg("title参数不能为空");
				info.setParam("title");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			if(content == null|| "".equals(content)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("content参数必填");
				info.setMsg("content参数不能为空");
				info.setParam("content");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			departmentId = departmentIds.split(",");
			if(departmentId.length>10){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("departmentIDs参数长度超出范围");
				info.setMsg("departmentIDs不能超出10个");
				info.setParam("departmentIDs");
				return new ResponseError(CommonCode.$PARAMETER_LENGTH_OUT_OF_RANGE, info);
			}
			if(files != null){
				noticeFiles = new ArrayList<String>();
				uuids = files.split(",");
				for(int i=0;i<uuids.length;i++){
					noticeFiles.add(uuids[i]);
				}
			}

			for(int i=0;i<departmentId.length;i++){
				departmentIdList.add(Integer.parseInt(departmentId[i]));
			}
			
			for(Integer id :departmentIdList){
				userIds = new ArrayList<Integer>();
				userIds = this.getUserIdsByDepartment(id);
				allUserIds.addAll(userIds);
				Department department = this.departmentService.findDepartmentById(id);
				departments += department.getName()+",";
				condition = new DepartmentTeacherCondition();
				condition.setDepartmentId(id);
				List<DepartmentTeacher> list = this.departmentTeacherService.
						findDepartmentTeacherByCondition(condition,null,null);
				userCount += list.size();
			}
			String departmentNames = departments.substring(0, departments.length()-1);
			if(postTime==null || "".equals(postTime)){
				postTime = sdf.format(new Date());
			}
		
			notice.setPosterId(posterId);
			notice.setPosterName(posterName);
			notice.setReceiverName(departmentNames);
			notice.setAppKey(appKey);
			notice.setTitle(title);
			notice.setPostTime(sdf.parse(postTime));
			notice.setContent(content);
			notice.setUserCount(userCount);
			notice.setCreateDate(new Date());
			Notice noti = this.schoolNoticeService.SendDepartmentNotice(notice, noticeFiles,departmentIdList );
			
			
			
			//推送
			PushUtil.push(removeRep(allUserIds), ReciverType.R$DEPARTMENT, noti.getId(), "326",("".equals(noti.getTitle())||noti.getTitle()==null)? getContent(noti.getContent()):noti.getTitle());
			
			NoticeInfo info = new NoticeInfo();
			info.setId(noti.getId());
			info.setCreateTime(sdf.format(noti.getCreateDate()));
			return new ResponseVo<NoticeInfo>("0", info);
		} catch (Exception e) {
			ResponseInfo info = new ResponseInfo();
			info.setMsg("添加记录异常");
			info.setDetail("参数异常");
			info.setParam("notice");
			return new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR, info);
		}
	}


	@Override
	public Object sendPersonNotice(String userIds, Integer posterId,
			String posterName, String appKey, String title, String content,
			String postTime, String files) {
		Integer userCount = 0;
		String[] uuids = null;
		String[] userId = null;
		List<String> noticeFiles = null;
		List<Integer> userIdList = new ArrayList<Integer>();
		Notice notice = new Notice();
		TeacherCondition condition = null;
		String teachers = new String();
		try {
			if(userIds == null|| "".equals(userIds)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("userIDs参数必填");
				info.setMsg("userIDs参数不能为空");
				info.setParam("userIDs");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			if(posterId == null|| "".equals(posterId)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("posterId参数必填");
				info.setMsg("posterId参数不能为空");
				info.setParam("posterId");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			if(posterName == null|| "".equals(posterName)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("posterName参数必填");
				info.setMsg("posterName参数不能为空");
				info.setParam("posterName");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			if(appKey == null|| "".equals(appKey)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("appKey参数必填");
				info.setMsg("appKey参数不能为空");
				info.setParam("appKey");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			if(title == null|| "".equals(title)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("title参数必填");
				info.setMsg("title参数不能为空");
				info.setParam("title");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			if(content == null|| "".equals(content)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("content参数必填");
				info.setMsg("content参数不能为空");
				info.setParam("content");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			userId = userIds.split(",");
			if(userId.length>100){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("userIDs参数长度超出范围");
				info.setMsg("userIDs不能超出100个");
				info.setParam("userIDs");
				return new ResponseError(CommonCode.$PARAMETER_LENGTH_OUT_OF_RANGE, info);
			}
			for(int i=0;i<userId.length;i++){
				userIdList.add(Integer.parseInt(userId[i]));
			}
			for(Integer id:userIdList){
				condition = new TeacherCondition();
				condition.setUserId(id);
				List<Teacher> teacherList = this.teacherService.findTeacherByCondition(condition, null, null);
				for(Teacher teacher:teacherList){
					teacher.getUserName();
					teachers += teacher.getName()+",";
				}
			}
			String teacherNames = teachers.substring(0, teachers.length()-1);
			if(files != null){
				noticeFiles = new ArrayList<String>();
				uuids = files.split(",");
				for(int i=0;i<uuids.length;i++){
					noticeFiles.add(uuids[i]);
				}
			}
//			

			if(postTime==null || "".equals(postTime)){
				postTime = sdf.format(new Date());
			}
		
			userCount = userIdList.size();
			notice.setReceiverName(teacherNames);
			notice.setPosterId(posterId);
			notice.setPosterName(posterName);
			notice.setAppKey(appKey);
			notice.setTitle(title);
			notice.setPostTime(sdf.parse(postTime));
			notice.setContent(content);
			notice.setUserCount(userCount);
			notice.setCreateDate(new Date());
			Notice noti = this.schoolNoticeService.SendPersonNotice(notice, noticeFiles, userIdList);
			
			//推送
			PushUtil.push(userIdList, ReciverType.R$TEACHER, noti.getId(), "326", ("".equals(noti.getTitle())||noti.getTitle()==null)?getContent(noti.getContent()):noti.getTitle());
			NoticeInfo info = new NoticeInfo();
			info.setId(noti.getId());
			info.setCreateTime(sdf.format(noti.getCreateDate()));
			return new ResponseVo<NoticeInfo>("0", info);
		} catch (Exception e) {
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setMsg("添加记录异常");
			info.setDetail("参数异常");
			info.setParam("notice");
			return new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR, info);
		}
	}


	@Override
	public Object getNotice(Integer id) {
		Notice notice = null;
		NoticeMess noticeMess = new NoticeMess();
		List<FileMsg> files = new ArrayList<FileMsg>();
		FileMsg fm = null;
		try {
			if(id == null|| "".equals(id)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("id参数必填");
				info.setMsg("id参数不能为空");
				info.setParam("id");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			notice = this.schoolNoticeService.findNoticeById(id);
			notice = NoticeUtil.unescapeNoticeHtml(notice);
			List<NoticeFile> list = this.schoolNoticeService.findNoticeFiles(notice, null, null);
			FileResult fileresult = null;
			for(NoticeFile file:list){
				fm = new FileMsg();
				EntityFile entityFile = entityFileService.findFileByUUID(file.getFileUuid());
				fileresult = fileService.findFileByUUID(file.getFileUuid());
				if(fileresult != null && entityFile !=null){
					fm.setFileUrl(fileresult.getHttpUrl());
					fm.setFileUuid(file.getFileUuid());
					fm.setFileName(entityFile.getFileName());
					files.add(fm);
				}
			}
			noticeMess.setId(notice.getId());
			noticeMess.setUuid(notice.getUuid());
			noticeMess.setAppKey(notice.getAppKey());
			noticeMess.setTitle(notice.getTitle());
			noticeMess.setPosterId(notice.getPosterId());
			noticeMess.setPosterName(notice.getPosterName());
			noticeMess.setPostTime(sdf.format(notice.getPostTime()));
			noticeMess.setReceiverType(notice.getReceiverType());
			noticeMess.setReceiverName(notice.getReceiverName());
			noticeMess.setContent(notice.getContent());
			noticeMess.setUserCount(notice.getUserCount());
			noticeMess.setReadCount(notice.getReadCount());
			noticeMess.setCreateDate(sdf.format(notice.getCreateDate()));
			noticeMess.setFiles(files);
			//2017-8-24  新增发布者的头像
			noticeMess.setPosterIcon(ImgUtil.getImgSrc(notice.getPosterId(), profileService));
			return new ResponseVo<NoticeMess>("0",noticeMess);
		} catch (Exception e) {
			ResponseInfo info = new ResponseInfo();
			info.setDetail("数据返回异常...");
			info.setMsg("找不到数据");
			info.setParam("NoticeMess");
			return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
		}
	}

	/*
	 * 根据通知id查询该通知的已浏览用户与未浏览用户
	 * @author Ken
	 * @date 2017年3月1日 下午1:54:01
	 * @param id 即 noticeId
	 * @return
	 */
	@Override
	public Object getNoticeList(Integer id) {
		List<NoticeReceiver> receiverList = new ArrayList<NoticeReceiver>();
		List<Teacher> teacherList = new ArrayList<Teacher>();
		List<DepartmentTeacher> dmTeacherList = new ArrayList<DepartmentTeacher>();
		List<NoticeRead> readList = new ArrayList<NoticeRead>();
		List<UserInfo> readedList = new ArrayList<UserInfo>();
		List<UserInfo> unreadList = new ArrayList<UserInfo>();
		List<UserInfo> readedList2 = new ArrayList<UserInfo>();
		List<UserInfo> unreadList2 = new ArrayList<UserInfo>();
		UserRead userRead = new UserRead();
		UserInfo userInfo = null;
		Notice notice = null;
		try {
			if(id == null|| "".equals(id)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("id参数必填");
				info.setMsg("id参数不能为空");
				info.setParam("id");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			notice = this.schoolNoticeService.findNoticeById(id);
			receiverList = this.schoolNoticeService.findNoticeReceiver(id);
			readList = this.schoolNoticeService.isRead(id);
			if(notice.getReceiverType().equals("pj.school")){
				for(NoticeReceiver receiver:receiverList){
					teacherList = this.teacherService.findTeacherListBySchoolId(receiver.getReceiverId());
					for(Teacher teacher:teacherList){
						userInfo = new UserInfo();
						userInfo.setUserId(teacher.getUserId());
						userInfo.setName(teacher.getName());
						String userIcon = ImgUtil.getImgSrc(teacher.getUserId(), profileService);
						userInfo.setUserIcon(userIcon);
						if(readList.size()==0){
							unreadList.add(userInfo);
						}else{
							Boolean exit = false;
							for(NoticeRead read : readList){
								if(read.getUserId().equals(teacher.getUserId())){
									readedList.add(userInfo);
									exit = true;
									break;
								}
							}
							if(!exit){
								unreadList.add(userInfo);
							}
						}
					}
				}
			}else if(notice.getReceiverType().equals("pj.dept")){
				for(NoticeReceiver receiver:receiverList){
					dmTeacherList = this.departmentTeacherService.findByDepartmentId(receiver.getReceiverId());
					for(DepartmentTeacher departmentTeacher:dmTeacherList){
						Teacher teacher = this.teacherService.findTeacherById(departmentTeacher.getTeacherId());
						if(teacher != null){
							userInfo = new UserInfo();
							userInfo.setName(departmentTeacher.getTeacherName());
							userInfo.setUserId(teacher.getUserId());
							userInfo.setUserIcon(ImgUtil.getImgSrc(teacher.getUserId(), profileService));
							if(readList.size()==0){
								unreadList.add(userInfo);
							}else{
								Boolean exit = false;
								for(NoticeRead read : readList){
									if(read.getUserId().equals(teacher.getUserId())){
										readedList.add(userInfo);
										exit = true;
										break;
									}
								}
								if(!exit){
									unreadList.add(userInfo);
								}
							}
						}
					}
				}
			}else if(notice.getReceiverType().equals("pj.person")){
				for(NoticeReceiver receiver:receiverList){
					TeacherCondition condition = new TeacherCondition();
					condition.setUserId(receiver.getReceiverId());
					List<Teacher> teachers = this.teacherService.findTeacherByCondition(condition, null, null);
					userInfo = new UserInfo();
					for(Teacher teacher:teachers){
						userInfo.setName(teacher.getName());
						userInfo.setUserId(teacher.getUserId());
						userInfo.setUserIcon(ImgUtil.getImgSrc(teacher.getUserId(), profileService));
						if(readList.size()==0){
							unreadList.add(userInfo);
						}else{
							Boolean exit = false;
							for(NoticeRead read : readList){
								if(read.getUserId().equals(teacher.getUserId())){
									readedList.add(userInfo);
									exit = true;
									break;
								}
							}
							if(!exit){
								unreadList.add(userInfo);
							}
						}
					}
				}
			}
			for (UserInfo t1 : readedList) {
				boolean flag = true;
				if (readedList2 != null) {
					for (UserInfo t2 : readedList2) {
						if (t1.getUserId().equals(t2.getUserId())) {
							flag = false;
							break;
						}
					}
				}
				if (flag) {
					readedList2.add(t1);
				}
			}
			for (UserInfo t1 : unreadList) {
				boolean flag = true;
				if (unreadList2 != null) {
					for (UserInfo t2 : unreadList2) {
						if (t1.getUserId().equals(t2.getUserId())) {
							flag = false;
							break;
						}
					}
				}
				if (flag) {
					unreadList2.add(t1);
				}
			}
			userRead.setReaded(readedList2);
			userRead.setUnread(unreadList2);
			return new ResponseVo<UserRead>("0",userRead);
		} catch (Exception e) {
			ResponseInfo info = new ResponseInfo();
			info.setDetail("数据返回异常...");
			info.setMsg("找不到数据");
			info.setParam("UserList");
			return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
		}
		
	}


	@Override
	public Object getFileList(Integer noticeId) {
		Notice notice = null;
		List<FileMsg> files = new ArrayList<FileMsg>();
		FileMsg fm = null;
		try {

			if(noticeId == null|| "".equals(noticeId)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("noticeId参数必填");
				info.setMsg("noticeId参数不能为空");
				info.setParam("noticeId");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			notice = this.schoolNoticeService.findNoticeById(noticeId);
			List<NoticeFile> list = this.schoolNoticeService.findNoticeFiles(notice, null, null);
			FileResult fileresult = null;
			for(NoticeFile file:list){
				fm = new FileMsg();
				EntityFile entityFile = entityFileService.findFileByUUID(file.getFileUuid());
				fileresult = fileService.findFileByUUID(file.getFileUuid());
				if(fileresult != null && entityFile !=null){
					fm.setFileUrl(fileresult.getHttpUrl());
					fm.setFileUuid(file.getFileUuid());
					fm.setFileName(entityFile.getFileName());
					files.add(fm);
				}
			}
			FileInfo info = new FileInfo();
			info.setNoticeId(noticeId);
			info.setFiles(files);
			return new ResponseVo<FileInfo>("0",info);
		}catch(Exception e){
			ResponseInfo info = new ResponseInfo();
			info.setDetail("数据返回异常...");
			info.setMsg("找不到数据");
			info.setParam("FileList");
			return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
		}
	}


	@Override
	public Object Read(Integer noticeId, Integer userId) {
		try {
			if(userId == null || "".equals(userId)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("userId参数必填");
				info.setMsg("userId参数不能为空");
				info.setParam("userId");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			if(noticeId == null || "".equals(noticeId)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("noticeId参数必填");
				info.setMsg("noticeId参数不能为空");
				info.setParam("noticeId");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			NoticeInfo info = null;
			Notice notice = this.schoolNoticeService.findNoticeById(noticeId);
			if(notice != null){
				//将用户通知设置为已读
				this.schoolNoticeService.noticeRead(notice, userId);
				List<NoticeRead> readList = this.schoolNoticeService.isRead(noticeId);
				for(NoticeRead read:readList){
					if(read.getUserId().equals(userId)){
						info = new NoticeInfo();
						info.setId(read.getId());
						info.setCreateTime(sdf.format(read.getCreateDate()));
					}
				}
			}
			return new ResponseVo<NoticeInfo>("0",info);
		} catch (Exception e) {
			ResponseInfo info = new ResponseInfo();
			info.setDetail("数据返回异常...");
			info.setMsg("找不到数据");
			info.setParam("NoticeRead");
			return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
		}
	}


	@Override
	public Object Delete(Integer noticeId, Integer userId) {
		try {
			if(userId == null|| "".equals(userId)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("userId参数必填");
				info.setMsg("userId参数不能为空");
				info.setParam("userId");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			if(noticeId == null|| "".equals(noticeId)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("noticeId参数必填");
				info.setMsg("noticeId参数不能为空");
				info.setParam("noticeId");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			Notice notice = this.schoolNoticeService.findNoticeById(noticeId);
			if(notice.getPosterId().equals(userId)){
				this.schoolNoticeService.delNotice(noticeId);
				ResponseInfo info = new ResponseInfo();
				info.setDetail("发送通知本人，允许删除本通知");
				info.setMsg("删除成功");
				info.setParam("noticeId,userId");
				return new ResponseError("0", info);
			}else{
				ResponseInfo info = new ResponseInfo();
				info.setDetail("不是发送通知本人，没有删除权限");
				info.setMsg("没有删除权限");
				info.setParam("noticeId,userId");
				return new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR, info);
			}
			
		} catch (Exception e) {
			ResponseInfo info = new ResponseInfo();
			info.setDetail("数据返回异常...");
			info.setMsg("找不到数据");
			info.setParam("noticeId,userId");
			return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
		}
		
	}


	@Override
	public Object MyPostedNotice(Integer userId, String beginDate, String endDate,
			String pageNumber,String pageSize,String sortItem,Integer sortType) {
		NoticeMess noticeMess = null;
		Date bDate = null;
		Date eDate = null;
		List<NoticeMess> msgList = new ArrayList<NoticeMess>();
		FileMsg fm = null;
		Page page = new Page();
		Order order = new Order();
		try {
			if(userId == null|| "".equals(userId)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("userId参数必填");
				info.setMsg("userId参数不能为空");
				info.setParam("userId");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			if(beginDate != null && !"".equals(beginDate) ){
				bDate = sdf.parse(beginDate);
			}
			if(endDate != null && !"".equals(endDate)){
				eDate = sdf.parse(endDate);
			}
			order.setProperty(sortItem);
			order.setAscending(sortType == 0? true:false);
			page.setCurrentPage(Integer.parseInt(pageNumber));
			page.setPageSize(Integer.parseInt(pageSize));
			List<Notice> noticeList =  this.schoolNoticeService.findMyPostedNotices(userId,OaPushConstant.NoticeType_school,bDate, eDate, null, null);
			List<Notice> noticeList1 = this.schoolNoticeService.findMyPostedNotices(userId,OaPushConstant.NoticeType_dept,bDate, eDate, null, null);
			List<Notice> noticeList2 = this.schoolNoticeService.findMyPostedNotices(userId,OaPushConstant.NoticeType_person,bDate, eDate, null, null);
			noticeList.addAll(noticeList1);
			noticeList.addAll(noticeList2);
			noticeList = this.setOrder(noticeList,sortType);
//			List<Notice> items = new ArrayList<Notice>();
//			Map<List<Notice>, Page> map = setpage(noticeList, page);
//			Iterator it = map.entrySet().iterator();
//			while (it.hasNext()) {
//				Map.Entry entry = (Map.Entry) it.next();
//				items = (List<Notice>) entry.getKey();
//				page = (Page) entry.getValue();
//			}
			List<Notice> items = NoticeUtil.getNoticeListByPage(noticeList, page.getCurrentPage(), page.getPageSize());
			page = NoticeUtil.resetPage(items.size(), page);
			for(Notice notice:items){
				noticeMess = new NoticeMess();
				List<FileMsg> files = new ArrayList<FileMsg>();
				List<NoticeFile> list = this.schoolNoticeService.findNoticeFiles(notice, null, null);
				FileResult fileresult = null;
				for(NoticeFile file:list){
					fm = new FileMsg();
					EntityFile entityFile = entityFileService.findFileByUUID(file.getFileUuid());
					fileresult = fileService.findFileByUUID(file.getFileUuid());
					if(fileresult != null && entityFile !=null){
						fm.setFileUrl(fileresult.getHttpUrl());
						fm.setFileUuid(file.getFileUuid());
						fm.setFileName(entityFile.getFileName());
						files.add(fm);
					}
				}
				notice = NoticeUtil.unescapeNoticeHtml(notice);
				
				noticeMess.setId(notice.getId());
				noticeMess.setUuid(notice.getUuid());
				noticeMess.setAppKey(notice.getAppKey());
				noticeMess.setTitle(notice.getTitle());
				noticeMess.setPosterId(notice.getPosterId());
				noticeMess.setPosterName(notice.getPosterName());
				noticeMess.setPostTime(sdf.format(notice.getPostTime()));
				noticeMess.setReceiverType(notice.getReceiverType());
				noticeMess.setReceiverName(notice.getReceiverName());
				noticeMess.setContent(notice.getContent());
				noticeMess.setUserCount(notice.getUserCount());
				noticeMess.setReadCount(notice.getReadCount());
				noticeMess.setCreateDate(sdf.format(notice.getCreateDate()));
				noticeMess.setFiles(files);
				noticeMess.setHasRead(1);
				msgList.add(noticeMess);
			}
			return new ResponseVo<List<NoticeMess>>("0",msgList);
		} catch (Exception e) {
			ResponseInfo info = new ResponseInfo();
			info.setDetail("数据返回异常...");
			info.setMsg("找不到数据");
			info.setParam("noticeMsg");
			return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
		}
	}

	@Override
	public Object BatchRead(String type,Integer schoolId, Integer userId) {
		if (userId == null || "".equals(userId)) {
			ResponseInfo info = new ResponseInfo();
			info.setDetail("userId参数必填");
			info.setMsg("userId参数不能为空");
			info.setParam("userId");
			return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
		}
		if (schoolId == null || "".equals(schoolId)) {
			ResponseInfo info = new ResponseInfo();
			info.setDetail("schoolId参数必填");
			info.setMsg("schoolId参数不能为空");
			info.setParam("schoolId");
			return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
		}
		List<Notice> notices = null;
		List<Notice> noticeList = new ArrayList<Notice>();;
		List<NoticeRead> readList = null;
		Boolean isAll = false;
		//类型为空，默认选择全部
		if (type == null || "".equals(type)) {
			isAll = true;
		}
		try {
			if ("school".equals(type) || isAll) {
                notices = this.schoolNoticeService.findNoticesBySchool(schoolId,
                        null, null, null, null);
                noticeList.addAll(notices);
            }
			if ("department".equals(type) || isAll) {
                Integer teacherId = teacherService.findUnqiueTeacherId(userId, schoolId);
                List<DepartmentTeacher> dtList = departmentTeacherService.findDepartmentTeacherByTeacherIdAndSchoolId(teacherId, schoolId);
                for (DepartmentTeacher dt : dtList) {
                    notices = this.schoolNoticeService.findNoticesByDepartment(dt.getDepartmentId(),
                            null, null, null, null);
                    noticeList.addAll(notices);
                }
            }
			if ("person".equals(type) || isAll) {
                notices = this.schoolNoticeService.findNoticesByPerson(userId,
                        null, null, null, null);
                noticeList.addAll(notices);
            }

			for (Notice notice : noticeList) {
                readList = this.schoolNoticeService.isRead(notice.getId());
                Boolean isRead = false;
                for (NoticeRead read : readList) {
                    if (read.getUserId().equals(userId)) {
                        isRead = true;
                        break;
                    }
                }
                if (!isRead) {
                    this.schoolNoticeService.noticeRead(notice, userId);
                }
            }
			ResponseInfo info = new ResponseInfo();
			info.setDetail("全部通知已设为已读");
			info.setMsg("设置成功");
			return new ResponseError("0", info);
		} catch (Exception e) {
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("数据返回异常...");
			info.setMsg("找不到数据");
			info.setParam("NoticeRead");
			return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
		}
	}

	//根据学校ID查找userId
	public List<Integer> getUserIdsBySchool(Integer schoolId){
		List<Teacher> teachers = this.teacherService.findTeacherListBySchoolId(schoolId);
		List<Integer> userIds = new ArrayList<Integer>();
		Integer userId = 0;
		for(Teacher teacher:teachers){
			userId = teacher.getUserId();
			userIds.add(userId);
		}
		return userIds;
	}

	//根据部门查找userId
	public List<Integer> getUserIdsByDepartment(Integer departmentId){
		List<DepartmentTeacher> teachers = this.departmentTeacherService.findByDepartmentId(departmentId);
		List<Integer> userIds = new ArrayList<Integer>();
		for(DepartmentTeacher deptTeacher:teachers){
			Teacher teacher = this.teacherService.findTeacherById(deptTeacher.getTeacherId());
			if(teacher != null){
				userIds.add(teacher.getUserId());
			}
		}
		return userIds;
	}
	
	/**
	 * 去重
	 * @param tList
	 * @return
	 */
	public List<Integer> removeRep(List<Integer> tList) {
		List<Integer> noRept = new ArrayList<Integer>();

		for (Integer t1 : tList) {
			boolean flag = true;
			if (noRept != null) {
				for (Integer t2 : noRept) {
					if (t1.equals(t2)) {
						flag = false;
						break;
					}
				}
			}
			if (flag) {
				noRept.add(t1);
			}
		}
		return noRept;
	}
	
	//根据创建时间排序
	public List<Notice> setOrder(List<Notice> notices,Integer sortType){
		if(sortType==1){
			Collections.sort(notices, new Comparator<Notice>(){
				public int compare(Notice n1, Notice n2) {  
					if(n1.getCreateDate().before(n2.getCreateDate())){  
						return 1;  
					}  
					if(n1.getCreateDate().equals(n2.getCreateDate())){  
						return 0;  
					}  
					return -1;  
				}  
			});
			return notices;
		}else{
			Collections.sort(notices, new Comparator<Notice>(){
				public int compare(Notice n1, Notice n2) {  
					if(n1.getCreateDate().after(n2.getCreateDate())){  
						return 1;  
					}  
					if(n1.getCreateDate().equals(n2.getCreateDate())){  
						return 0;  
					}  
					return -1;  
				}  
			});
			return notices;
		}
	}
	
	
	
	//自定义分页
//	public Map setpage(Collection<Notice> notices, Page page) {
//		List<Notice> items = new ArrayList<Notice>();
//		int currentPage = page.getCurrentPage();
//		int pageSize = page.getPageSize();
//		int start = (currentPage - 1) * pageSize;
//		int end = currentPage * pageSize - 1;
//		int i = 0;
//		for (Notice vo : notices) {
//			if (i >= start && i <= end) {
//				items.add(vo);
//			}
//			i++;
//		}
//		int totalRows = notices.size();
//		page.setTotalRows(totalRows);
//		page.setCurrentPage(currentPage);
//		int totalPages = (totalRows % pageSize) == 0 ? (totalRows / pageSize)
//				: (totalRows / pageSize) + 1;
//		page.setTotalPages(totalPages);
//		Map<List<Notice>, Page> map = new HashMap<List<Notice>, Page>();
//		map.put(items, page);
//		return map;
//	}
	
	private String getContent(String content){
		String data = "";
		if(content == null || "".equals(content)){
			return "您有新的消息啦！";
		}
		if(content.length()>20){
			data = content.substring(0, 20);
		} else {
			data = content;
		}
		return data;
	}
}
