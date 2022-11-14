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
	     <td><jcgc:cache tableCode="GB-XB" value="${newStudent.sex}"></jcgc:cache></td>
	     <td><jcgc:cache tableCode="GB-MZ" value="${newStudent.race}"></jcgc:cache></td>
	     <td>${newStudent.idCardNumber}</td>
	     <td>${newStudent.enrollPerson}</td>
	     <td><c:if test="${newStudent.regionStates==1}">未注册</c:if><c:if test="${newStudent.regionStates==2}">已注册</c:if></td>
	     <td><jcgc:cache tableCode="JY-XSLB" value="${newStudent.studentType}"></jcgc:cache></td>
	     <td>
	     <c:if test="${newStudent.regionStates==1}">
	     	<button onclick="loadModifyStudentPage('${newStudent.id}');" type="button" class="btn btn-blue">编辑</button>
	     </c:if>
	     <c:if test="${newStudent.regionStates==2}">
	     	<button onclick="loadViewNewStudentRegistPage('${newStudent.id}');" type="button" class="btn btn-blue">查看</button>
	     </c:if>
<!--        		<button onclick="deleteNewStudent('${newStudent.id}');" type="button" class="btn btn-red">删除</button> -->
	     </td>
	 </tr>
</c:forEach>