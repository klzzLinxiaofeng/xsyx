package platform.education.generalTeachingAffair.service;
import platform.education.generalTeachingAffair.model.JobControl;
import platform.education.generalTeachingAffair.vo.JobControlCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface JobControlService {
    JobControl findJobControlById(Integer id);
	   
	JobControl add(JobControl jobControl);
	   
	JobControl modify(JobControl jobControl);
	   
	void remove(JobControl jobControl);
	   
	List<JobControl> findJobControlByCondition(JobControlCondition jobControlCondition, Page page, Order order);
	
	List<JobControl> findJobControlByCondition(JobControlCondition jobControlCondition);
	
	List<JobControl> findJobControlByCondition(JobControlCondition jobControlCondition, Page page);
	
	List<JobControl> findJobControlByCondition(JobControlCondition jobControlCondition, Order order);
	
	Long count();
	
	Long count(JobControlCondition jobControlCondition);
	
	//允许学生档案公开给个人修改
	JobControl enableStudentArchiveEditing (Integer teamID, Boolean interrupteur);
	
	//是否允许学生档案公开给个人修改
	Boolean studentArchiveCanEdit (Integer teamID);


	Boolean studentArchiveCanEditApplets(String name);

	//根据项目名和用户id查找
	JobControl findJobControl(String name, Integer objectId);
	
	/**
	 * 修改 档案是否完成
	 * @param name 		业务名(教师或学生档案)
	 * @param userId	目标id(用户id)
	 * @param isCompleted	默认未完成
	 * @param appKey	可为空
	 * @return
	 */
	JobControl updateArchiveStatus(String name, Integer userId, Boolean isCompleted, String appKey);

	void modifyAppletsInterrupteur(Boolean boo, String name);
}
