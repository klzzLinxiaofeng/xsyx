package platform.education.paper.service;
import platform.education.paper.model.PaUserWrong;
import platform.education.paper.vo.PaUserWrongCondition;
import platform.education.paper.vo.WrongPaperVo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;
import java.util.Map;

public interface PaUserWrongService {
    PaUserWrong findPaUserWrongById(Integer id);
	   
	PaUserWrong add(PaUserWrong paUserWrong);
	   
	PaUserWrong modify(PaUserWrong paUserWrong);
	   
	void remove(PaUserWrong paUserWrong);
	   
	List<PaUserWrong> findPaUserWrongByCondition(PaUserWrongCondition paUserWrongCondition, Page page, Order order);
	
	List<PaUserWrong> findPaUserWrongByCondition(PaUserWrongCondition paUserWrongCondition);
	
	List<PaUserWrong> findPaUserWrongByCondition(PaUserWrongCondition paUserWrongCondition, Page page);
	
	List<PaUserWrong> findPaUserWrongByCondition(PaUserWrongCondition paUserWrongCondition, Order order);
	
	Long count();
	
	Long count(PaUserWrongCondition paUserWrongCondition);

	public  List<WrongPaperVo> findUserWrongList(Integer userId, String subjectCode, Page page);

	public  List<WrongPaperVo> findUserWrongList(Integer userId, String subjectCode, Page page, Order order);

	Boolean redo(Integer userWrongId, String answers) throws Exception;
	
}
