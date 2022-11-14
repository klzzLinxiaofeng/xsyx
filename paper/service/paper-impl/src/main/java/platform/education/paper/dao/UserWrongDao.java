package platform.education.paper.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import framework.generic.dao.GenericDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.paper.model.UserQuestion;
import platform.education.paper.model.UserWrong;
import platform.education.paper.model.WrongPaper;
import platform.education.paper.vo.UserWrongCondition;

public interface UserWrongDao extends GenericDao<UserWrong, java.lang.Integer> {

	List<UserWrong> findUserWrongByCondition(UserWrongCondition userWrongCondition, Page page, Order order);
	
	UserWrong findById(Integer id);
	
	Long count(UserWrongCondition userWrongCondition);
	
	/**
	 * 
	* @Title: findUserWrongByUserIdAndQuestionUuId
	* @author pantq 
	* @Description: 根据用户ID和试题uuid查询错题本 
	* @param userId
	* @param questionUuId
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
	public List<WrongPaper> findUserWrongListByUserId(Integer userId,String subjectCode,String createDateStr,Page page, Order order);
	
	
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
	
	String findUserWrongCreateDateByUserId(Integer userId);
	void batchUserWrong(Object[] list);
	
	void deleteNotInPaperUuid(String[] uuids);
	
}
