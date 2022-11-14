<%@ page language="java" import="platform.education.user.contants.UserContants" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />

 <c:forEach items="${exams}" var="exam" varStatus="status">
	 <tr>
		 <td><fmt:formatDate value="${exam.examDate}" pattern="yyyy-MM-dd" /></td>
	     <td>${jcFn:getValue("jc_subject","code",exam.subjectCode, "name")}</td>
	     <td>${jcgcFn:getColValue("XY-JY-KSLX", exam.examType, "name")}</td>
	     <td>
			 <c:choose>
				 <c:when test="${exam.studentCount != null}">${exam.studentCount}</c:when>
				 <c:otherwise>暂无</c:otherwise>
			 </c:choose>
		 </td>
	     <td style="text-align:right">
			 <button onclick="staticPage('${exam.id}','${exam.teamId}');" type="button" class="btn btn-blue">查看统计</button>
	     </td>
	 </tr>
</c:forEach>