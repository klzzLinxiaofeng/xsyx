<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>   
<bean id="${classNameLower}Dao" parent="baseDao">
	<property name="target" value="${basepackage}.${subpackage}.dao.${className}Dao" />
</bean>
<bean id="${classNameLower}Service" class="${basepackage}.${subpackage}.service.impl.${className}ServiceImpl" />
<!--  generator-insert-location -->