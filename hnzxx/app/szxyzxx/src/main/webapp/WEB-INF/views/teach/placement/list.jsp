<%@ page language="java" import="platform.education.user.contants.UserContants" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
<input type="hidden" id="totalRows" name="totalPages" value="${page.totalRows}" />

 <c:forEach items="${studentList}" var="student">
	 <tr>
	 	 <td><input type="checkbox" name="checkList" value="${student.id}"/></td>	
	     <td>${student.userId}</td>
	     <td>${student.userName}</td>
	     <td>${student.name}</td>
	     <td><jcgc:cache tableCode="GB-XB" value="${student.sex}"></jcgc:cache></td>
	     <td>${student.mobile}</td>
	     <td>${student.teamId}</td>
	     <td><jcgc:cache tableCode="JY-XSDQZT" value="${student.studyState}"></jcgc:cache></td>
	     <td><jcgc:cache tableCode="JY-XSLB" value="${student.studentType}" /></td>
	     <td>${student.position}</td>
	     
	     <td>
	     	<button onclick="loadModifyStudentPage('${student.id}');" type="button" class="btn btn-blue">编辑</button>
       		<button onclick="deleteStudent('${student.id}');" type="button" class="btn btn-blue">删除</button>
	     </td>
	 </tr>
</c:forEach>