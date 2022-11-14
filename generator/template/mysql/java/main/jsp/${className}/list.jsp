<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
<%@include file="/WEB-INF/views/common/permission.jsp" %>
<tr>
	<td style="padding:0;border:0 none;">
		<input type="hidden" id="currentPage" name="currentPage" value="${r"${page.currentPage}"}" />
		<input type="hidden" id="totalPages" name="totalPages" value="${r"${page.totalPages}"}" />
	</td>
</tr>
<c:forEach items="${r"${items}"}" var="item">
	<tr id="${r"${item."}${table.idColumn.columnNameLower}${r"}"}_tr">
		<#list table.columns as column>
			<#if column.javaType="java.util.Date">
	    		<td><fmt:formatDate value="${r"${item."}${column.columnNameLower}${r"}"}" pattern="yyyy/MM/dd" /></td>
			<#else>
				<td>${r"${item."}${column.columnNameLower}${r"}"}</td>
			</#if>
		</#list>
		
		<td class="caozuo">
			<button class="btn btn-green permission_check" type="button" onclick="loadViewerPage('${r"${item."}${table.idColumn.columnNameLower}${r"}"}');">详情</button>
			<button class="btn btn-blue permission_update" type="button" onclick="loadEditPage('${r"${item."}${table.idColumn.columnNameLower}${r"}"}');">编辑</button>
			<button class="btn btn-red permission_del" type="button" onclick="deleteObj(this, '${r"${item."}${table.idColumn.columnNameLower}${r"}"}');">删除</button>
		</td>
	</tr>
</c:forEach>
