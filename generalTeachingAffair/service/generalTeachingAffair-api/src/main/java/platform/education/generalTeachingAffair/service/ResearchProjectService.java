package platform.education.generalTeachingAffair.service;
import platform.education.generalTeachingAffair.model.ResearchProject;
import platform.education.generalTeachingAffair.vo.ResearchProjectCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface ResearchProjectService {
	/**
	 * 当前接口crud操作 成功时返回的状态值
	 */
	public final static String OPERATE_SUCCESS = "success";
	
	/**
	 * 当前接口crud操作 失败时返回的状态值
	 */
	public final static String OPERATE_FAIL = "fail";
	
	/**
	 * 系统异常造成的操作失败 系统返回的状态值
	 */
	public final static String OPERATE_ERROR = "error";
	
    ResearchProject findResearchProjectById(Integer id);
	   
	ResearchProject add(ResearchProject researchProject);
	   
	ResearchProject modify(ResearchProject researchProject);
	   
	void remove(ResearchProject researchProject);
	   
	List<ResearchProject> findResearchProjectByCondition(ResearchProjectCondition researchProjectCondition, Page page, Order order);
	
	List<ResearchProject> findResearchProjectByCondition(ResearchProjectCondition researchProjectCondition);
	
	List<ResearchProject> findResearchProjectByCondition(ResearchProjectCondition researchProjectCondition, Page page);
	
	List<ResearchProject> findResearchProjectByCondition(ResearchProjectCondition researchProjectCondition, Order order);
	
	Long count();
	
	Long count(ResearchProjectCondition researchProjectCondition);

	String moveTo(ResearchProject researchProject);
	
}
