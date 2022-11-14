package platform.education.paper.service;
import platform.education.paper.model.UserWrong;
import platform.education.paper.model.WrongPaper;
import platform.education.paper.vo.UserWrongCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface UserWrongService {
    UserWrong findUserWrongById(Integer id);
	   
	UserWrong add(UserWrong userWrong);
	   
	UserWrong modify(UserWrong userWrong);
	   
	void remove(UserWrong userWrong);
	   
	List<UserWrong> findUserWrongByCondition(UserWrongCondition userWrongCondition, Page page, Order order);
	
	List<UserWrong> findUserWrongByCondition(UserWrongCondition userWrongCondition);
	
	List<UserWrong> findUserWrongByCondition(UserWrongCondition userWrongCondition, Page page);
	
	List<UserWrong> findUserWrongByCondition(UserWrongCondition userWrongCondition, Order order);
	
	Long count();
	
	Long count(UserWrongCondition userWrongCondition);
	
	/**
	 * 
	* @Title: findUserWrongByUserIdAndQuestionUuId
	* @author pantq 
	* @Description: 根据用户ID和试题uuid查询错题本 
	* @param userId 用户ID
	* @param questionUuId 试题UUID
	* @return    设定文件 
	* @return UserWrong    返回类型 
	* @throws
	 */
	UserWrong findUserWrongByUserIdAndQuestionUuId(Integer userId,String questionUuId); 
	
	/**
	 * 
	* @Title: findUserWrongList
	* @author pantq 
	* @Description: 根据相关条件查询错题本列表
	* @param userId 用户ID
	* @param subjectCode 科目CODE
	* @param page
	* @param order
	* @return    设定文件 
	* @return List<WrongPaper>    返回类型 
	* @throws
	 */
	public List<WrongPaper> findUserWrongListByUserId(Integer userId,String subjectCode,Page page, Order order);
	
	
	/**
	 * 
	* @Title: findUserWrongCreateGroup
	* @author pantq 
	* @Description: 获取按日期分组的日期
	* @return    设定文件 
	* @return List<String>    返回类型 
	* @throws
	 */
	public List<String> findUserWrongCreateGroup();
	
	
	void deleteNotInPaperUuid(String[] uuids);
	
}
