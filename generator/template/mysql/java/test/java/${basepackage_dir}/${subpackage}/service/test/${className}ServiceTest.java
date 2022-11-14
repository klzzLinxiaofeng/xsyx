<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
package ${basepackage}.${subpackage}.service.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import ${basepackage}.${subpackage}.service.${className}Service;
import ${basepackage}.${subpackage}.service.test.base.BaseTest;

import java.util.List;

public class ${className}ServiceTest extends BasetTest{
	
	@Autowired
	@Qualifier("${classNameLower}Service")
	private ${className}Service ${classNameLower}Service;
	
	@Test
	public void testAdd() {
		
	}
	
	@Test
	public void testModify() {
		
	}
	
	@Test
	public void testFindById() {

	}
	
	@Test
	public void testRemove() {

	}
	
	@Test
	public void testFindByCondition() {

	}
	
}
