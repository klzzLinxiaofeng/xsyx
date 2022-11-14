<%@ page language="java" import="platform.education.user.contants.UserContants" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
<tr style="display:none"><td>
<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
	<input type="hidden" id="totalRows" name="totalPages" value="${page.totalRows}" />
</td></tr>
 <c:forEach items="${studentListunfinish}" var="student">
	 <tr>
	     <td>${student.userName}</td>
	      <td>${student.studentNumber}</td>
	     <td>${student.name}</td>
	     <td><jcgc:cache tableCode="GB-XB" value="${student.sex}"></jcgc:cache></td>
	     <td>${student.mobile}</td>
	     <td>${student.teamName}</td>
	     <td><jcgc:cache tableCode="JY-XSDQZT" value="${student.studyState}"></jcgc:cache></td>
	     <td><jcgc:cache tableCode="JY-XSLB" value="${student.studentType}" /></td>
<%-- 	     <td>${student.position}</td> --%>
	     
	     <td class="caozuo">
<%-- 	     	<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 2)}"> --%>
	     		<button onclick="loadModifyStudentPage('${student.id}');" type="button" class="btn btn-blue">编辑</button>
<%-- 	     	</c:if> --%>
	     	<%--<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 3)}">
       			<button onclick="deleteStudent('${student.id}');" type="button" class="btn btn-red">删除</button>
       		</c:if>--%>
<%-- 			<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 2)}"> --%>
				<button class="btn btn-blue" type="button" onclick="loadViewerPage('${student.id}');">查看档案</button>
<%-- 			</c:if> --%>
	     </td>
	 </tr>
</c:forEach>
<tr style="display:none">
<td>总页数${page.totalPages}===当前页${page.currentPage}==页长${page.pageSize}</td>
<td style="border:0 none;padding-top:15px;" colspan="10">
	<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
		<jsp:param name="id" value="module_list_content1" />
		<jsp:param name="url"  value="/teach/student/studentTJUnfinish?sub=list&dm=${param.dm}" />
		<jsp:param name="pageSize" value="${page.pageSize}" />
	</jsp:include>
</td>
</tr>						
<script>
 $(function(){
	 addNumber();
 })
	function addNumber(){
	 var num = ${unfinishNum};
	 if(num == "" || num == null){
		 num = 0;
	 }
		$("#unfinishNum").text("未完成人数("+num+")");
	}
</script>