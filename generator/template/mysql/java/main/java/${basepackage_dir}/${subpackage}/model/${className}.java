<#include "/java/macro.include"/>
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
package ${basepackage}.${subpackage}.model;

import framework.generic.dao.Model;
/**
 * ${table.tableAlias}
 * @author AutoCreate
 *
 */
public class ${className} implements Model<${table.idColumn.possibleShortJavaType}> {
	
	
	private static final long serialVersionUID = 1L;
	<#list table.columns as column>
	/**
	 * ${column.columnAlias}
	 */
	private ${column.possibleShortJavaType} ${column.columnNameLower};
	</#list>
	
	public ${className}() {
		
	}
	
	public ${className}(${table.idColumn.possibleShortJavaType} ${table.idColumn.columnNameLower}) {
		this.${table.idColumn.columnNameLower} = ${table.idColumn.columnNameLower};
	}
	
	public ${table.idColumn.possibleShortJavaType} getKey() {
		return this.${table.idColumn.columnNameLower};
	}

	<#list table.columns as column>
	/**
	 * 获取${column.columnAlias}
	 * @return ${column.javaType}
	 */
	public ${column.possibleShortJavaType} get${column.columnName}() {
		return this.${column.columnNameLower};
	}
	
	/**
	 * 设置${column.columnAlias}
	 * @param ${column.columnNameLower}
	 * @type ${column.javaType}
	 */
	public void set${column.columnName}(${column.possibleShortJavaType} ${column.columnNameLower}) {
		this.${column.columnNameLower} = ${column.columnNameLower};
	}
	
	</#list>
}