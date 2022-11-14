<#include "/java/macro.include"/>
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
package ${basepackage}.${subpackage}.vo;
import ${basepackage}.${subpackage}.model.${className};
/**
 * ${table.tableAlias}
 * @author AutoCreate
 *
 */
public class ${className}Vo extends ${className} {
	private static final long serialVersionUID = 1L;
}