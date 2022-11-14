<%@ page language="java" import="platform.education.user.contants.UserContants" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
<%@ taglib prefix="entity" uri="http://www.jiaoxueyun.com/entity"%>
<tr style="display:none"><td>
<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
</td></tr>
<style>
.a_img_title{ 
    width: 50px;
    height: 50px;
    border-radius: 100px;
    overflow: hidden; float:left;
    }
</style>
 <c:forEach items="${items}" var="student">
	 <tr id="tr_${student.id}">
	     <td>${student.studentName}</td>
	     <td>${student.courseNames}</td>
	      <td>${student.passCourseNames}</td>
	     <td class="caozuo">
	     	<button onclick="enroll('${student.id}');" type="button" class="btn btn-blue">确认录取</button>
     		<button onclick="changePage('${student.id}');" type="button" class="btn btn-blue">更改录取</button>
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