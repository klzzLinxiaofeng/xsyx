<%@ page language="java" import="platform.education.user.contants.UserContants" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
<tr>
	<td style="padding:0;border:0 none;display:none;">
		<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
		<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
        <input type="hidden" id="totalRows" name="totalPages" value="${page.totalRows}" />
	</td> 
</tr>
<%-- <input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" /> --%>
<%-- <input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" /> --%>
<!-- onclick="loadCreatePage('${classTeacher.teamId }','${classTeacher.gradeId}');"
onclick="loadModifyPage('${classTeacher.id}','${classTeacher.teacherId}');"
 -->
 <c:forEach items="${classTeacherList}" var="classTeacher">
       <tr>
           <td>${classTeacher.teamName}</td>
           <td>${classTeacher.teacherName}</td>
           <td>
            <c:if test="${classTeacher.id==0}">
            	<button class="add_classTeacher btn btn-blue"  id="${classTeacher.teamId}" data_team="${classTeacher.teamId }" data_grade="${classTeacher.gradeId}"  type="button" >新增</button>
            </c:if>
            <c:if test="${classTeacher.id!=0}">
            	<button class="add_classTeacher btn btn-blue" id="${classTeacher.teamId}" data_team="${classTeacher.teamId }" data_grade="${classTeacher.gradeId}"  type="button">编辑</button><button class="btn btn-red" onclick="deleteObj(this, '${classTeacher.id}');">移除</button>
            </c:if>
           </td>
       </tr>
</c:forEach>

<script type="text/javascript">
	$(function() {
		$.createMemberSelectorByClass({
			"inputClassSelector" : ".add_classTeacher",
			"enableBatch" : false
		});	
	});
</script>