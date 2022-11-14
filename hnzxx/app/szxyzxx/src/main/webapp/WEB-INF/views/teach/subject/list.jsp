<%@ page language="java" import="platform.education.user.contants.UserContants" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
<tr style="display:none">
<td>
<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
	<input type="hidden" id="totalRows" name="totalPages" value="${page.totalRows}" />
</td>
</tr>
 <c:forEach items="${subjectList}" var="subject" varStatus="count">
    <tr id="${subject.id}">
     <td class="index">${count.count}</td>
     <td>${subject.name}</td>
<%--      <td>${subject.stageNames }</td> --%>
<%--      <td><jc:cache tableName="jc_stage" echoField="name" value="${subject.stageCode}" paramName="code"></jc:cache></td> --%>
     <td><jcgc:cache tableCode="JY-KCFW" value="${subject.subjectClass}"></jcgc:cache></td>
<%--      <td><jcgc:cache tableCode="JY-KCLB" value="${subject.subjectType}"></jcgc:cache></td> --%>
<%--      <td><jcgc:cache tableCode="JY-KCSX" value="${subject.subjectProperty}"></jcgc:cache></td> --%>
<%--      <td><jcgc:cache tableCode="JY-KCXZ" value="${subject.subjectCharacter}"></jcgc:cache></td> --%>
     <td class="caozuo" style="width:128px;">

	<c:if test="${subject.subjectClass ==2 || subject.subjectClass ==3}">
		<button onclick="loadModifyPage('${subject.id}');" type="button" class="btn btn-blue">编辑</button>
	</c:if>
      
     <%-- <c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 3)}">
     	<button onclick="deleteSubject('${subject.id}');" type="button" class="btn btn-red">删除</button>
     </c:if>--%>
     
     </td>
  </tr>
</c:forEach>
<!-- <script>
	$(function(){
		$("table tbody tr").hover(function(){
			$(this).children(".caozuo").children("span").show();
		},function(){
			$(this).children(".caozuo").children("span").hide();
		});
	});
</script> -->