package platform.education.oa.dao;

import platform.education.oa.model.PaperUserRead;
import platform.education.oa.vo.PaperUserReadCondition;
import platform.education.oa.vo.PaperUserReadVo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;

public interface PaperUserReadDao extends GenericDao<PaperUserRead, java.lang.Integer> {

	List<PaperUserRead> findPaperUserReadByCondition(PaperUserReadCondition paperUserReadCondition, Page page, Order order);
	
	List<PaperUserReadVo> findPaperUserReadByConditionVo(PaperUserReadCondition paperUserReadCondition,Page page, Order order);
	
	PaperUserRead findById(Integer id);
	
	Long count(PaperUserReadCondition oaPaperUserReadCondition);
	
	//根据paper_id 、user_id查询唯一
	PaperUserRead findByPaperIdAndUserId(Integer ownerId,String ownerType,Integer paperId,Integer userId);
	
	//根据paper_id进行删除
	void deleteByPaperId(PaperUserRead paperUserRead);
	
}
