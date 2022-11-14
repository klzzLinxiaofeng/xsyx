<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
package ${basepackage}.${subpackage}.service;
import ${basepackage}.${subpackage}.model.${className};
import ${basepackage}.${subpackage}.vo.${className}Condition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface ${className}Service {
    ${className} find${className}ById(${table.idColumn.possibleShortJavaType} ${table.idColumn.columnNameLower});
	   
	${className} add(${className} ${classNameLower});
	   
	${className} modify(${className} ${classNameLower});
	   
	void remove(${className} ${classNameLower});
	   
	List<${className}> find${className}ByCondition(${className}Condition ${classNameLower}Condition, Page page, Order order);
	
	List<${className}> find${className}ByCondition(${className}Condition ${classNameLower}Condition);
	
	List<${className}> find${className}ByCondition(${className}Condition ${classNameLower}Condition, Page page);
	
	List<${className}> find${className}ByCondition(${className}Condition ${classNameLower}Condition, Order order);
	
	Long count();
	
	Long count(${className}Condition ${classNameLower}Condition);
	
}
