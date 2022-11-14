<%@ page language="java" import="platform.education.user.contants.UserContants" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
<tr style="display:none">
<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
    <input type="hidden" id="totalRows" name="totalPages" value="${page.totalRows}" />
</tr>

 <c:forEach items="${schoolList}" var="school">
   <tr>
       <td>${school.name}</td>
       <td>${school.englishName}</td>
       <td>${school.code}</td>
       <td>${school.code2}</td>
       <td><jcgc:cache tableCode="JY-XXLB" value="${school.schoolType}"></jcgc:cache></td>
       <td><jcgc:cache tableCode="JY-BXLB" value="${school.runningType}"></jcgc:cache></td>
       <td><t:showTime createTime="${school.createDate}" /></td>
       <td class="caozuo">
       	<%-- <a href="javascript:void(0);" onclick="loadModifyPage('${school.id}');">【编辑】</a>
       	<a href="javascript:void(0);" onclick="deleteSchool('${school.id}');">【删除】</a>
       	<a href="javascript:void(0);" onclick="detailSchool('${school.id}');">【详情】</a> --%>
       	<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 2)}">
       		<button onclick="loadModifyPage('${school.id}');" type="button" class="btn btn-blue">编辑</button>
       	</c:if>
       	<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 3)}">
       		<button onclick="deleteSchool('${school.id}');" type="button" class="btn btn-red">删除</button>
       	</c:if>
       	<%-- <button onclick="detailSchool('${school.id}');" type="button" class="btn btn-blue">详情</button>--%>
       </td>
   </tr>
</c:forEach>
