<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>   
<#assign classNameUpper = className?upper_case>
<#assign sqlName = table.sqlName>   
<#assign sqlNameLower = sqlName?uncap_first>   
<#assign sqlNameUpper = sqlName?upper_case>
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${basepackage}.${subpackage}.dao.${className}Dao">

	<resultMap id="${className}Result" type="${basepackage}.${subpackage}.model.${className}">
		<#list table.columns as column>
		<result property="${column.columnNameLower}" column="${column.sqlName}" />
		</#list>
	</resultMap>

	<insert id="create" parameterType="${basepackage}.${subpackage}.model.${className}">
		<#if table.idColumn.columnNameLower=='id'>
		<selectKey resultType="java.lang.Integer" order="AFTER" keyProperty="id">
			SELECT LAST_INSERT_ID() AS id
		</selectKey>
		</#if>
		INSERT INTO ${sqlNameLower} (
		<#list table.columns as column>
			${column.sqlName}<#if column_has_next>, </#if>
		</#list>
		)
		VALUES (
		<#list table.columns as column>
			<#if column.javaType="java.lang.Boolean">
			<choose>
				<when test="${column.columnNameLower} != null">
					<choose>
						<when test="${column.columnNameLower} == true">
							1<#if column_has_next>, </#if>
						</when>
						<otherwise>
							0<#if column_has_next>, </#if>
						</otherwise>						
					</choose>
				</when>
				<otherwise>
					0<#if column_has_next>, </#if>
				</otherwise>
			</choose>
			<#else>
			${r"#{"}${column.columnNameLower}${r"}"}<#if column_has_next>, </#if>
			</#if>
		</#list>
		)
	</insert>

	<select id="read" resultMap="${className}Result" useCache="false">
		SELECT * FROM ${sqlNameLower} WHERE 1=1
		<if test="args!=null and args[0]!=null">
		AND ${table.idColumn.sqlName} = ${r"#{"}args[0],javaType=${table.idColumn.javaType}${r"}"}
		</if>  
		ORDER BY ${table.idColumn.sqlName} DESC
	</select>
	
	<select id="find${className}ByCondition" resultMap="${className}Result" useCache="false">
		SELECT * FROM ${sqlNameLower} 
		<if test="args[0] != null">
			WHERE 1=1
			<#list table.columns as column>
			<#if column.javaType="java.lang.Boolean">
			<if test="args[0].${column.columnNameLower} != null">
				<choose>
					<when test="args[0].${column.columnNameLower} == true">
						AND ${column.sqlName}  = 1
					</when>
					<otherwise>
						AND ${column.sqlName}  = 0
					</otherwise>
				</choose>
			</if>
			<#else>
			<if test="args[0].${column.columnNameLower} != null<#if column.javaType="java.lang.String"> and args[0].${column.columnNameLower} != ''</#if>">
				AND ${column.sqlName} = ${r"#{"}args[0].${column.columnNameLower},javaType=${column.javaType}${r"}"}
			</if>
			</#if>
			</#list>  
		</if>
	</select>
	
	<select id="count" resultType="java.lang.Long" useCache="false">
		SELECT count(*) FROM ${sqlNameLower} 
		<if test="args[0] != null">
			WHERE 1=1
			<#list table.columns as column>
			<#if column.javaType="java.lang.Boolean">
			<if test="args[0].${column.columnNameLower} != null">
				<choose>
					<when test="args[0].${column.columnNameLower} == true">
						AND ${column.sqlName}  = 1
					</when>
					<otherwise>
						AND ${column.sqlName}  = 0
					</otherwise>
				</choose>
			</if>
			<#else>
			<if test="args[0].${column.columnNameLower} != null<#if column.javaType="java.lang.String"> and args[0].${column.columnNameLower} != ''</#if>">
				AND ${column.sqlName} = ${r"#{"}args[0].${column.columnNameLower},javaType=${column.javaType}${r"}"}
			</if>
			</#if>
			</#list>  
		</if>
	</select>
	
	<select id="findById" resultMap="${className}Result" useCache="false">
		SELECT * FROM ${sqlNameLower} WHERE ${table.idColumn.sqlName} = ${r"#{"}args[0],javaType=${table.idColumn.javaType}${r"}"}
	</select>
	
	<update id="update" parameterType="${basepackage}.${subpackage}.model.${className}">
		UPDATE ${sqlNameLower}
		SET 
		<#list table.columns as column>
		<#if !column.pk>
		<#if column.javaType="java.lang.Boolean">
		<if test="${column.columnNameLower} != null">
			<choose>
				<when test="${column.columnNameLower} == true">
					${column.sqlName}  = 1,
				</when>
				<otherwise>
					${column.sqlName}  = 0,
				</otherwise>
			</choose>
		</if>
		<#else>
		<if test="${column.columnNameLower} != null">
			${column.sqlName}=${r"#{"}${column.columnNameLower}${r"}"},
		</if>
		</#if>
		</#if>
		</#list>
		${table.idColumn.sqlName} = ${r"#{"}${table.idColumn.columnNameLower}${r"}"}
		WHERE ${table.idColumn.sqlName} = ${r"#{"}${table.idColumn.columnNameLower}${r"}"}
	</update>
	
	<delete id="delete">
		DELETE FROM ${sqlNameLower}
		WHERE ${table.idColumn.sqlName} = ${r"#{"}${table.idColumn.columnNameLower}${r"}"}
	</delete>
</mapper>