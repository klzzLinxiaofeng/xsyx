<%@ page language="java" import="platform.education.user.contants.UserContants" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
<input type="hidden" id="totalRows" name="totalPages" value="${page.totalRows}" />

 <c:forEach items="${newStudentList}" var="newStudent" varStatus="status">
	 <tr>
	     <td>${status.count+(page.currentPage-1)*10}</td>
	     <td>${newStudent.schoolYear}</td>
	     <td>${newStudent.name}</td>
	     <td>${newStudent.userName}</td>
	     <td><jcgc:cache tableCode="GB-XB" value="${newStudent.sex}"></jcgc:cache></td>
	     <td><jcgc:cache tableCode="GB-MZ" value="${newStudent.race}"></jcgc:cache></td>
	     <td>${newStudent.idCardNumber}</td>
	     <td><jcgc:cache tableCode="JY-XSLB" value="${newStudent.studentType}"></jcgc:cache></td>
	     <td><fmt:formatDate pattern="yyyy-MM-dd" value="${newStudent.entrollSchoolDate}"></fmt:formatDate></td>
	     <td>${newStudent.studentNum}</td>
	     <td><c:if test="${newStudent.regionStates==1}">待注册</c:if>
	         <c:if test="${newStudent.regionStates==2}">已注册</c:if>
	     </td>
	     <td>
	     	<c:if test="${newStudent.regionStates==1}">
	     		<button onclick="loadViewNewStudentRegistPage('${newStudent.id}');" type="button" class="btn btn-blue">查看</button>
	     		<button onclick="loadReginstStudentPage('${newStudent.id}');" type="button" class="btn btn-blue">注册</button>
<!--        			<button onclick="deleteNewStudentRegistPage('${newStudent.id}');" type="button" class="btn btn-red">删除</button> -->
	     	</c:if>
	     	<c:if test="${newStudent.regionStates==2}">
<!-- 	     		<button onclick="loadModifyStudentPage('${newStudent.id}');" type="button" class="btn btn-blue">查看</button> -->
	     		<button onclick="loadEditNewStudentRegistPage('${newStudent.id}');" type="button" class="btn btn-blue">编辑</button>
<!--        			<button onclick="deleteStudent('${newStudent.id}');" type="button" class="btn btn-red">删除</button> -->
	     	</c:if>
	     	
	     </td>
	 </tr>
</c:forEach>