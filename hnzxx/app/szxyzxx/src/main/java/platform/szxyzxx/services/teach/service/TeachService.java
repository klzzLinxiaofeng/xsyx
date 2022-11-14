package platform.szxyzxx.services.teach.service;


import platform.education.enrollStudent.model.NewStudent;
import platform.education.generalTeachingAffair.model.UserDetailInfo;

public interface TeachService {
	
	
	public UserDetailInfo saveNewStudentInfo(NewStudent newStudent) throws Exception;
	
	public void modifyNewStudentInfo(UserDetailInfo userDetailInfo) throws Exception;
	
}
