package platform.education.oa.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.notice.model.Notice;
import platform.education.notice.model.NoticeFile;
import platform.education.notice.model.NoticeRead;
import platform.education.notice.model.NoticeReceiver;
import platform.education.notice.service.NoticeService;
import platform.education.notice.vo.NoticeCondition;
import platform.education.notice.vo.SeachVo;
import platform.education.oa.constants.OaPushConstant;
import platform.education.oa.service.SchoolNoticeService;

public class SchoolNoticeServiceImpl implements SchoolNoticeService {
	private Logger log = LoggerFactory.getLogger(getClass());
	private NoticeService pubNoticeService;
	public void setPubNoticeService(NoticeService pubNoticeService) {
		this.pubNoticeService = pubNoticeService;
	}

	@Override
	public List<Notice> findNoticesBySchool(Integer schoolID, Date beginDate,
			Date endDate, Page page, Order order) {
		if (schoolID == null) {
			throw new IllegalArgumentException("schoolID 不能为空");
		} else {


			return this.pubNoticeService.findNoticesByReceiver(schoolID,
					OaPushConstant.NoticeType_school, beginDate, endDate, page,
					order);

		}
	}

	@Override
	public List<Notice> findNoticesByDepartment(Integer departmentID,
			Date beginDate, Date endDate, Page page, Order order) {

		if (departmentID == null) {
			throw new IllegalArgumentException("departmentID 不能为空");
		} else {

			List<Notice> s = this.pubNoticeService.findNoticesByReceiver(
					departmentID, OaPushConstant.NoticeType_dept, beginDate,
					endDate, page, order);
			return this.pubNoticeService.findNoticesByReceiver(departmentID,
					OaPushConstant.NoticeType_dept, beginDate, endDate, page,
					order);

		}
	}

	@Override
	public List<Notice> findNoticesByPerson(Integer userID, Date beginDate,
			Date endDate, Page page, Order order) {
		if (userID == null) {
			throw new IllegalArgumentException("userID 不能为空");
		} else {
			return this.pubNoticeService.findNoticesByReceiver(userID,
					OaPushConstant.NoticeType_person, beginDate, endDate, page,
					order);
		}
	}


	@Override
	public List<Notice> findMyPostedNotices(Integer userID, String type,Date beginDate,
			Date endDate, Page page, Order order) {
		if (userID == null) {
			throw new IllegalArgumentException("userID 不能为空");
		} else {
	
	

			return this.pubNoticeService.findNoticesByPoster(userID,type, null,
					null,  page, order);
		}
	}

	@Override
	public Notice SendSchoolNotice(Notice notice, List<String> noticeFiles,
			List<Integer> schoolID) {
		if (notice != null && schoolID.size() > 0) {
			try{
			notice.setReceiverType(OaPushConstant.NoticeType_school);
			return this.pubNoticeService.sendNotice(notice, noticeFiles, schoolID);
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
		} else {

			throw new IllegalArgumentException("notice 和 schoolID 不能为空");
		}
	}

	@Override
	public Notice SendDepartmentNotice(Notice notice, List<String> noticeFiles,
			List<Integer> departmentID) {
		if (notice != null && departmentID.size() >= 0) {
			try{
			notice.setReceiverType(OaPushConstant.NoticeType_dept);
			return	this.pubNoticeService.sendNotice(notice, noticeFiles, departmentID);
			}catch(Exception e){
				e.printStackTrace();
				throw e;
			}

		} else {

			throw new IllegalArgumentException("notice 和 departmentID 不能为空");
		}
	}


	@Override
	public Notice sendTeamNotice(Notice notice, List<String> noticeFiles,
								 List<Integer> teamIds) {
		if (notice != null && teamIds.size() >= 0) {
			try{
				notice.setReceiverType(OaPushConstant.NoticeType_team);
				return	this.pubNoticeService.sendNotice(notice, noticeFiles, teamIds);
			}catch(Exception e){
				e.printStackTrace();
				throw e;
			}

		} else {

			throw new IllegalArgumentException("notice 和 departmentID 不能为空");
		}
	}

	@Override
	public Notice sendAllStudentNotice(Notice notice, List<String> noticeFiles,
									   List<Integer> schoolIds) {
		if (notice != null && schoolIds.size() >= 0) {
			try{
				notice.setReceiverType(OaPushConstant.NoticeType_allStudent);
				return	this.pubNoticeService.sendNotice(notice, noticeFiles, schoolIds);
			}catch(Exception e){
				e.printStackTrace();
				throw e;
			}

		} else {

			throw new IllegalArgumentException("notice 和 departmentID 不能为空");
		}
	}

	@Override
	public Notice sendAllTeacherNotice(Notice notice, List<String> noticeFiles,
									   List<Integer> schoolIds) {
		if (notice != null && schoolIds.size() >= 0) {
			try{
				notice.setReceiverType(OaPushConstant.NoticeType_allTeacher);
				return	this.pubNoticeService.sendNotice(notice, noticeFiles, schoolIds);
			}catch(Exception e){
				e.printStackTrace();
				throw e;
			}

		} else {

			throw new IllegalArgumentException("notice 和 departmentID 不能为空");
		}
	}

	@Override
	public Notice sendTeamNotice(Notice notice, List<String> noticeFiles, Integer teamId) {

		List<Integer> list = new ArrayList<>();
		list.add(teamId);
		return sendTeamNotice(notice, noticeFiles,list);
	}

	@Override
	public Notice sendAllTeacherNotice(Notice notice, List<String> noticeFiles, Integer schoolId) {

		List<Integer> list = new ArrayList<>();
		list.add(schoolId);
		return sendAllTeacherNotice(notice, noticeFiles,list);
	}

	@Override
	public Notice sendAllStudentNotice(Notice notice, List<String> noticeFiles, Integer schoolId) {

		List<Integer> list = new ArrayList<>();
		list.add(schoolId);
		return sendAllStudentNotice(notice, noticeFiles,list);
	}


	@Override
	public Notice SendPersonNotice(Notice notice, List<String> noticeFiles,
			List<Integer> userID) {
		if (notice != null && userID.size() > 0) {
			try{
				notice.setReceiverType(OaPushConstant.NoticeType_person);
				return	this.pubNoticeService.sendNotice(notice, noticeFiles, userID);
			}catch(Exception e){
				e.printStackTrace();
				throw e;
			}
		

		} else {

			throw new IllegalArgumentException("notice 和 userID 不能为空");
		}
	}

	@Override
	public List<NoticeFile> findNoticeFiles(Notice notice, Page page,
			Order order) {
		if (notice != null) {
			return this.pubNoticeService.findNoticeFiles(notice.getId(), page,
					order);

		} else {
			throw new IllegalArgumentException("notice不能为空");
		}
	}

	@Override
	public void delNotice(Integer id) {

		this.pubNoticeService.removeNotice(id);
		if (id != null) {
			try{
			this.pubNoticeService.removeNotice(id);
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
		} else {
			throw new IllegalArgumentException("noticeid不能为空");
		}

	}

	// 通过notice_id找通知
	@Override
	public Notice findNoticeById(Integer id) {

		if (id != null) {
			return this.pubNoticeService.findNotice(id);
		} else {
			throw new IllegalArgumentException("noticeid不能为空");
		}
	}

	@Override
	public List<Notice> search(String title, String receiverType,
			Integer userID, Page page, Order order) {
		NoticeCondition noticeCondition = new NoticeCondition();

		noticeCondition.setTitle(title);
		
		if (userID == null || receiverType == null) {
			throw new IllegalArgumentException("userID和receiverType不能为空");
		} else {

			return this.pubNoticeService.findNoticesByReceiverCondition(userID,
					receiverType, noticeCondition, page, order);
		}
	}

	@Override
	public void noticeRead(Notice notice, Integer userID) {

		if (notice.getId() != null && userID != null) {
			try{
			this.pubNoticeService.noticeRead(notice.getId(), userID);
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
			
		} else {

			throw new IllegalArgumentException("noticeid和userID不能为空");
		}
	}

	@Override
	public List<NoticeReceiver> findNoticeReceiver(Integer noticeID) {
		
		if(noticeID==null){
			throw new IllegalArgumentException("noticeid不能为空");
		}else{
        return this.pubNoticeService.findNoticeReceivers(noticeID, null, null);
		}
	}

	@Override
	public List<NoticeRead> isRead(Integer noticeID) {
		
		if(noticeID==null){
			throw new IllegalArgumentException("noticeid不能为空");
		}else
		{
			try{
			
		return this.pubNoticeService.findReadCountByNoticeId(noticeID, null,
				null);
			}catch(Exception e){
				e.printStackTrace();
				throw e;
			}
		
		}

	}

	@Override
	public List<Notice> findByPosteridContidition(String title, Integer userID,Page page,Order order) {
		
		if(userID!=null){
		SeachVo seachVo=new SeachVo();
		seachVo.setTitle(title);
	
		return 	this.pubNoticeService.findNoticeByVugueSeach(userID, seachVo, page, order);
		}else{
			throw new IllegalArgumentException("userID不能为空");
		}
	}


}
