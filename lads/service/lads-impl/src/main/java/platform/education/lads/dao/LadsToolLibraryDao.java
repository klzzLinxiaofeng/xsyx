package platform.education.lads.dao;

import platform.education.lads.model.LadsToolLibrary;
import platform.education.lads.vo.LadsToolLibraryCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;

public interface LadsToolLibraryDao extends GenericDao<LadsToolLibrary, java.lang.String> {

	List<LadsToolLibrary> findLadsToolLibraryByCondition(LadsToolLibraryCondition ladsToolLibraryCondition, Page page, Order order);
	
	LadsToolLibrary findById(String id);
	
	Long count(LadsToolLibraryCondition ladsToolLibraryCondition);
	
	void deleteByCondition(LadsToolLibraryCondition ladsToolLibraryCondition);
        
        //以下是业务方法
        LadsToolLibrary findByToolName(String toolName);
}
