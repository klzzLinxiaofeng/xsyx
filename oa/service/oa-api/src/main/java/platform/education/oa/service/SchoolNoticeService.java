package platform.education.oa.service;

import java.util.Date;
import java.util.List;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.notice.model.Notice;
import platform.education.notice.model.NoticeFile;
import platform.education.notice.model.NoticeRead;
import platform.education.notice.model.NoticeReceiver;
import platform.education.notice.vo.NoticeCondition;
import platform.education.notice.vo.NoticeReceiverVo;
import platform.education.oa.model.OaNotice;


public interface SchoolNoticeService {
    //查找我发彪的通知
	List<Notice>findMyPostedNotices(Integer  userID,String type,Date beginDate,Date endDate, Page page, Order order);
	//发送学校通知
	Notice SendSchoolNotice(Notice notice,List<String> noticeFiles,List<Integer> schoolID);
	//发送部门同追
	Notice SendDepartmentNotice(Notice notice,List<String> noticeFiles,List<Integer> departmentID);

	Notice sendTeamNotice(Notice notice, List<String> noticeFiles,
						  List<Integer> teamIds);


	Notice sendAllStudentNotice(Notice notice, List<String> noticeFiles,
								List<Integer> schoolIds);

	Notice sendAllTeacherNotice(Notice notice, List<String> noticeFiles,
								List<Integer> schoolIds);

	Notice sendTeamNotice(Notice notice, List<String> noticeFiles, Integer teamId);

	Notice sendAllTeacherNotice(Notice notice, List<String> noticeFiles, Integer schoolId);

	Notice sendAllStudentNotice(Notice notice, List<String> noticeFiles, Integer schoolId);

	//发送个人通知
	Notice SendPersonNotice(Notice notice,List<String> noticeFiles,List<Integer> userID);
	//通过学校找通知
	List<Notice> findNoticesBySchool(Integer schoolID, Date beginDate,
			Date endDate, Page page, Order order);
	//通过部门找通知
	List<Notice> findNoticesByDepartment(Integer departmentID, Date beginDate,
			Date endDate, Page page, Order order);
	//通过userID找通知
	List<Notice> findNoticesByPerson(Integer userID, Date beginDate,
			Date endDate, Page page, Order order);
	//通过通知找通知的文件
	List<NoticeFile> findNoticeFiles(Notice notice, Page page, Order order);
	//删除通知
	void delNotice(Integer id);
	//通过noticeID找通知
	Notice findNoticeById(Integer id);
	//通过标题找接受通知
	List<Notice> search(String title,String receiverType,Integer userID,Page page,Order order);
	//读通知
	void noticeRead(Notice notice,Integer userID);
	//通过通知找到接受者
	List<NoticeReceiver>findNoticeReceiver(Integer noticeID);
	//通过noticeID找到阅读通知的人
	List<NoticeRead> isRead(Integer noticeID);
	//通过标题和userID找发送者的通知
    List<Notice>findByPosteridContidition(String title,Integer userID,Page page,Order order);
}
