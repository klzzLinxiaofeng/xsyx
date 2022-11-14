<%@ page language="java" import="platform.education.user.contants.UserContants" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
<tr style="display:none"><td>
<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
	<input type="hidden" id="totalRows" name="totalPages" value="${page.totalRows}" />
</td>
</tr>
   <c:forEach items="${subjectGradeVoList}" var="subjectGradeVo">
	 <tr>
	     <td>${subjectGradeVo.stageName}</td>
	     <td>${subjectGradeVo.gradeName}</td>
	     <td>${subjectGradeVo.subjectName}</td>
	     <td class="caozuo">
	     	<button onclick="loadModifyPage('${subjectGradeVo.id}');" type="button" class="btn btn-blue">【编辑】</button>
	     	<button onclick="deleteSubjectGrade('${subjectGradeVo.id}');" type="button" class="btn btn-blue">【删除】</button>
	     </td>
	   </tr>
	</c:forEach>
