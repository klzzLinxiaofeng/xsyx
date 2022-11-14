<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
<tr>
<td style="padding:0;border:0 none;">
<input type="hidden" id="currentPage" name="currentPage" value=" ${page.currentPage}" />
<input type="hidden" id="totalPages" name="totalPages" value=" ${page.totalPages}" />
</td>
</tr>
  <c:forEach items="${list}" var="item">
	<tr id="${item.ryDm}">
       <td>${item.jsGh}</td>
       <td>${item.xm}</td>
       <td><ca:cache tableName="t_dm_gy_xb" paramName="xbMc" id="${item.xb}" /></td>
       <td>${item.sfzmh}</td>
       <td><ca:cache tableName="t_dm_gy_zzmm" paramName="zzmmMc" id="${item.zzmmDm}" /></td>
       <td><ca:cache tableName="t_dm_gy_jszt" paramName="jsztMc" id="${item.jsztDm}" /></td>
       <td>
       
       		<a href="javascript:void(0);" onclick="loadShowPage('${item.ryDm}');">【详情】</a>

      <%--  	<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].yhDm, param.dm, 2)}">
       		<a href="javascript:void(0);" onclick="loadEditPage('${item.ryDm}');">【编辑】</a>
       	</c:if>
       	
       	<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].yhDm, param.dm, 3)}">
       		<a href="javascript:del('${item.ryDm}');" >【删除】</a>
       	</c:if> --%>
       
       </td>
	</tr>
</c:forEach>
 