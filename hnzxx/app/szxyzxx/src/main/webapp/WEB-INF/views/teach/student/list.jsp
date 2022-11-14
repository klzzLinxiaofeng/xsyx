<%@ page language="java" import="platform.education.user.contants.UserContants" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
<%@ taglib prefix="entity" uri="http://www.jiaoxueyun.com/entity"%>
<tr style="display:none"><td>
<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
	<input type="hidden" id="totalRows" name="totalPages" value="${page.totalRows}" />
</td></tr>
<style>
.a_img_title{
    width: 50px;
    height: 50px;
    border-radius: 100px;
    overflow: hidden; float:left;
    }
</style>
 <c:forEach items="${studentList}" var="student">
	 <tr id="tr_${student.id}">
	     <td>
	     	<c:choose>
				<c:when test="${not empty student.uuid}">
					<div class="a_img_title" style="background:url(<entity:getHttpUrl uuid='${student.uuid}'/>) 50% 50%;background-size:100%;">
					</div>
				</c:when>
				<c:otherwise>
				<div class="a_img_title" style="background:url(${pageContext.request.contextPath}/res/images/noPhoto.jpg) 50% 50%;background-size:100%;">
					</div>
				</c:otherwise>
			</c:choose>
			<span style="line-height:50px; margin-left:15px;">${student.name}</span>
     	</td>
	     <td>${student.userName}</td>
	      <td>${student.studentNumber}</td>
		  <td>${student.empCard}</td>
		  <td>${student.shiTangCard}</td>
	     <td><jcgc:cache tableCode="GB-XB" value="${student.sex}"></jcgc:cache></td>
	     <td>${student.mobile}</td>
	     <td>${student.teamName}</td>
	     <td><jcgc:cache tableCode="JY-XSDQZT" value="${student.studyState}"></jcgc:cache></td>
	     <td><jcgc:cache tableCode="JY-XSLB" value="${student.studentType}" /></td>
<%-- 	     <td>${student.position}</td> --%>

	     <td class="caozuo">
	     <button onclick="loadModifyStudentPhotoPage('${student.id}');" type="button" class="btn btn-blue">头像</button>
	     	<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 2)}">
<%-- 	     		<c:if test="${interrupteur eq true}"> --%>
	     		<button onclick="loadModifyStudentPage('${student.id}');" type="button" class="btn btn-blue">编辑</button>
	     		</c:if>
<%-- 	     	</c:if> --%>
	     	<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 3)}">
       			<button onclick="deleteStudent('${student.id}');" type="button" class="btn btn-red">删除</button>
       		</c:if>
			<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 2)}">
				<button class="btn btn-blue" type="button" onclick="loadViewerPage('${student.id}');">查看</button>
			</c:if>
	     </td>
	 </tr>
</c:forEach>
<script type="text/javascript">
$(function() {
	$("#totalNum span").text("${totalNum }");
	$("#manNum span").text("${manNum }");
	$("#womanNum span").text("${womanNum }");

	var teamId = $("#bj").val();
	if(teamId !="" && teamId != null && teamId != "undefind"){
		$("#d_switch").show();
	}else{
		$("#d_switch").hide();
	}
	/* if("${interrupteur}" == true){
// 	 	$(".student_right a").removeClass("auto_close").addClass("auto_open");
// 		$(".student_right span").html("家长端APP可以录入学生档案");
	} else{
// 		$(".student_right a").removeClass("auto_open").addClass("auto_close");
// 		$(".student_right span").html("家长端APP不可以录入学生档案");
	} */
})
</script>