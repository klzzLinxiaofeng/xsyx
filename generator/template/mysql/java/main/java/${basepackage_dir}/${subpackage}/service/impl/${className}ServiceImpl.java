<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
package ${basepackage}.${subpackage}.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ${basepackage}.${subpackage}.model.${className};
import ${basepackage}.${subpackage}.vo.${className}Condition;
import ${basepackage}.${subpackage}.service.${className}Service;
import ${basepackage}.${subpackage}.dao.${className}Dao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public class ${className}ServiceImpl implements ${className}Service{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private ${className}Dao ${classNameLower}Dao;

	public void set${className}Dao(${className}Dao ${classNameLower}Dao) {
		this.${classNameLower}Dao = ${classNameLower}Dao;
	}
	
	@Override
	public ${className} find${className}ById(${table.idColumn.possibleShortJavaType} ${table.idColumn.columnNameLower}) {
		try {
			return ${classNameLower}Dao.findById(${table.idColumn.columnNameLower});
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", ${table.idColumn.columnNameLower});
		}
		return null;
	}
	
	@Override
	public ${className} add(${className} ${classNameLower}) {
		if(${classNameLower} == null) {
    		return null;
    	}
    	Date createDate = ${classNameLower}.getCreateDate();
    	if(createDate == null) {
    		createDate = new Date();
    	}
    	${classNameLower}.setCreateDate(createDate);
    	${classNameLower}.setModifyDate(createDate);
		return ${classNameLower}Dao.create(${classNameLower});
	}

	@Override
	public ${className} modify(${className} ${classNameLower}) {
		if(${classNameLower} == null) {
    		return null;
    	}
    	Date modify = ${classNameLower}.getModifyDate();
    	${classNameLower}.setModifyDate(modify != null ? modify : new Date());
		return ${classNameLower}Dao.update(${classNameLower});
	}
	
	@Override
	public void remove(${className} ${classNameLower}) {
		try {
			${classNameLower}Dao.delete(${classNameLower});
		} catch(Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("删除数据库无存在ID为 {} ,异常为：{}", ${classNameLower}.getId(), e);
			}
		}
	}
	
	@Override
	public List<${className}> find${className}ByCondition(${className}Condition ${classNameLower}Condition, Page page, Order order) {
		return ${classNameLower}Dao.find${className}ByCondition(${classNameLower}Condition, page, order);
	}
	
	@Override
	public List<${className}> find${className}ByCondition(${className}Condition ${classNameLower}Condition) {
		return ${classNameLower}Dao.find${className}ByCondition(${classNameLower}Condition, null, null);
	}
	
	@Override
	public List<${className}> find${className}ByCondition(${className}Condition ${classNameLower}Condition, Page page) {
		return ${classNameLower}Dao.find${className}ByCondition(${classNameLower}Condition, page, null);
	}
	
	@Override
	public List<${className}> find${className}ByCondition(${className}Condition ${classNameLower}Condition, Order order) {
		return ${classNameLower}Dao.find${className}ByCondition(${classNameLower}Condition, null, order);
	}
	
	@Override
	public Long count() {
		return this.${classNameLower}Dao.count(null);
	}

	@Override
	public Long count(${className}Condition ${classNameLower}Condition) {
		return this.${classNameLower}Dao.count(${classNameLower}Condition);
	}

}
