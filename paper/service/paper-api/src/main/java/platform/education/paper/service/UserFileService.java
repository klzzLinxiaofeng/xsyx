package platform.education.paper.service;
import platform.education.paper.model.UserFile;
import platform.education.paper.vo.UserFileCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface UserFileService {
    UserFile findUserFileById(Integer id);
	   
	UserFile add(UserFile userFile);
	   
	UserFile modify(UserFile userFile);
	   
	void remove(UserFile userFile);
	   
	List<UserFile> findUserFileByCondition(UserFileCondition userFileCondition, Page page, Order order);
	
	List<UserFile> findUserFileByCondition(UserFileCondition userFileCondition);
	
	List<UserFile> findUserFileByCondition(UserFileCondition userFileCondition, Page page);
	
	List<UserFile> findUserFileByCondition(UserFileCondition userFileCondition, Order order);
	
	Long count();
	
	Long count(UserFileCondition userFileCondition);

	public void processUserFileAndOrUpdate(List<UserFile> userFileList);

	Integer batchUpdateMarkedFile(List<UserFile> userFiles);

//	List<UserFile> findUserFileByUserQuestionIds(Integer[] userQuestionIds);
	
	List<UserFile> findUserFileByUserPaperIds(Integer[] userPaperIds);
	
	List<UserFile> findUserFileByUserQuestionUuIds(String[] userQuestionIds);
}
