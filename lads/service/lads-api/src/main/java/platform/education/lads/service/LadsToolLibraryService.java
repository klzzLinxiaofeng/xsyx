package platform.education.lads.service;
import platform.education.lads.model.LadsToolLibrary;
import platform.education.lads.vo.LadsToolLibraryCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface LadsToolLibraryService {
    LadsToolLibrary findLadsToolLibraryById(String id);
	   
	LadsToolLibrary add(LadsToolLibrary ladsToolLibrary);
	   
	LadsToolLibrary modify(LadsToolLibrary ladsToolLibrary);
	   
	void remove(LadsToolLibrary ladsToolLibrary);
	   
	List<LadsToolLibrary> findLadsToolLibraryByCondition(LadsToolLibraryCondition ladsToolLibraryCondition, Page page, Order order);
	
	List<LadsToolLibrary> findLadsToolLibraryByCondition(LadsToolLibraryCondition ladsToolLibraryCondition);
	
	List<LadsToolLibrary> findLadsToolLibraryByCondition(LadsToolLibraryCondition ladsToolLibraryCondition, Page page);
	
	List<LadsToolLibrary> findLadsToolLibraryByCondition(LadsToolLibraryCondition ladsToolLibraryCondition, Order order);
	
	Long count();
	
	Long count(LadsToolLibraryCondition ladsToolLibraryCondition);
	
	void remove(LadsToolLibraryCondition ladsToolLibraryCondition);
	
        //以下是业务方法
        LadsToolLibrary findByToolName(String toolName);
}
