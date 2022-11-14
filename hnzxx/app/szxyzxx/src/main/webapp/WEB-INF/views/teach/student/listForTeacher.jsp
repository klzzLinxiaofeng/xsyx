<%@ page language="java" import="platform.education.user.contants.UserContants" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
<tr style="display:none"><td>
<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
	<input type="hidden" id="totalRows" name="totalPages" value="${page.totalRows}" />
</td></tr>
 <c:forEach items="${studentList}" var="student">
	 <tr id="tr_${student.id}">
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
	     	<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 2)}">
	     		<c:if test="${interrupteur eq true}">
	     		<button onclick="loadModifyStudentPage('${student.id}');" type="button" class="btn btn-blue">编辑</button>
	     		</c:if>
	     	</c:if>
	     	<%--<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 3)}">
       			<button onclick="deleteStudent('${student.id}');" type="button" class="btn btn-red">删除</button>
       		</c:if>--%>
			<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 2)}">
				<button class="btn btn-blue" type="button" onclick="loadViewerPage('${student.id}');">查看档案</button>
			</c:if>
	     </td>
	 </tr>
</c:forEach>
<script type="text/javascript">
$(function() {
	$("#totalNum span").text("${totalNum }");
	$("#manNum span").text("${manNum }");
	$("#womanNum span").text("${womanNum }");
	
	var teamId = $("#teamId").val();
	if(teamId !="" && teamId != null && teamId != "undefind"){
		$("#d_switch").show();	
	}else{
		$("#d_switch").hide();	
	}
	if(${interrupteur} == true){
	 	$(".student_right a").removeClass("auto_close").addClass("auto_open");
		$(".student_right span").html("所有人可以录入学生档案"); 
	} else{
		$(".student_right a").removeClass("auto_open").addClass("auto_close");
		$(".student_right span").html("所有人不可以录入学生档案");
	}
})
</script>