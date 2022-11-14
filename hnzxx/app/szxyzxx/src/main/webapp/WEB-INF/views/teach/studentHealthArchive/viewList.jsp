<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
<%@ taglib prefix="entity" uri="http://www.jiaoxueyun.com/entity"%>
<tr>
	<td style="padding:0;border:0 none;">
		<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
		<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
	</td>
</tr>
<%--<c:forEach items="${studentHealthArchiveVoList}" var="vo" varStatus="i">--%>
<c:forEach items="${studentHealthArchiveVo}" var="vo" varStatus="i">
	<tr id="${vo.id}_tr">
				<td>${i.index+1}</td>
				<td>${vo.createDate}</td>
				<td><jcgc:cache tableCode="GB-JKZK3" value="${vo.type}" /></td>
				<td><a target="_blank" id="a" href='<entity:getHttpUrl uuid="${vo.accessory}"/>'>${vo.accessoryName}</a></td>
	</tr>
</c:forEach>
