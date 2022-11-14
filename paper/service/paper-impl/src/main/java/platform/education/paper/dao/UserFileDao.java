package platform.education.paper.dao;

import platform.education.paper.model.UserFile;
import platform.education.paper.vo.UserFileCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;

public interface UserFileDao extends GenericDao<UserFile, java.lang.Integer> {

	List<UserFile> findUserFileByCondition(UserFileCondition userFileCondition, Page page, Order order);
	
	UserFile findById(Integer id);
	
	Long count(UserFileCondition userFileCondition);

	Integer batchUpdateMarkedFile(List<UserFile> userFiles);

	List<UserFile> findUserFileByUserQuestionIds(Integer[] userQuestionIds);
	
	List<UserFile> findUserFileByUserPaperIds(Integer[] userPaperIds);
	
	List<UserFile> findUserFileByUserQuestionUuIds(String[] userQuestionIds);
	
}
