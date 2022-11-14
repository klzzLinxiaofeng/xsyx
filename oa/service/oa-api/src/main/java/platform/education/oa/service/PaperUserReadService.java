package platform.education.oa.service;
import platform.education.oa.model.PaperUserRead;
import platform.education.oa.vo.PaperUserReadCondition;
import platform.education.oa.vo.PaperUserReadVo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface PaperUserReadService {
    PaperUserRead findPaperUserReadById(Integer id);
	   
	PaperUserRead add(PaperUserRead paperUserRead);
	   
	PaperUserRead modify(PaperUserRead paperUserRead);
	   
	void remove(PaperUserRead oaPaperUserRead);
	   
	List<PaperUserRead> findPaperUserReadByCondition(PaperUserReadCondition paperUserReadCondition, Page page, Order order);
	
	List<PaperUserRead> findPaperUserReadByCondition(PaperUserReadCondition paperUserReadCondition);
	
	List<PaperUserReadVo> findPaperUserReadByConditionVo(PaperUserReadCondition paperUserReadCondition, Page page, Order order);
	
	List<PaperUserRead> findPaperUserReadByCondition(PaperUserReadCondition paperUserReadCondition, Page page);
	
	List<PaperUserRead> findPaperUserReadByCondition(PaperUserReadCondition paperUserReadCondition, Order order);
	
	Long count();
	
	Long count(PaperUserReadCondition paperUserReadCondition);
	
	//根据paper_id 、user_id查询唯一
	PaperUserRead findByPaperIdAndUserId(Integer ownerId,String ownerType,Integer paperId,Integer userId);
	
	//根据paper_id进行删除
	void deleteByPaperId(PaperUserRead paperUserRead);
	
}
