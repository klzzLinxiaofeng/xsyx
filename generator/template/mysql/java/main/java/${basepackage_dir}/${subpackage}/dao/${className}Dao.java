<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>   
package ${basepackage}.${subpackage}.dao;

import ${basepackage}.${subpackage}.model.${className};
import ${basepackage}.${subpackage}.vo.${className}Condition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;

public interface ${className}Dao extends GenericDao<${className}, ${table.idColumn.javaType}> {

	List<${className}> find${className}ByCondition(${className}Condition ${classNameLower}Condition, Page page, Order order);
	
	${className} findById(${table.idColumn.possibleShortJavaType} ${table.idColumn.columnNameLower});
	
	Long count(${className}Condition ${classNameLower}Condition);
	
}
