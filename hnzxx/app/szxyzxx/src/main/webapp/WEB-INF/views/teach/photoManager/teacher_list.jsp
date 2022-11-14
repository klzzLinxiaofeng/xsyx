<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
<ul>
	<c:forEach items="${deptTeacherList }" var="deptTeacher" varStatus="i">
		<li>
			<img src="<avatar:avatar userId='${deptTeacher.teacherUserId }'></avatar:avatar>">
			<p class="name">${deptTeacher.teacherName }</p>
			<div class="cz"><a href="javascript:void(0)" class="a1" onclick="updatePhoto('${deptTeacher.teacherId }')">修改</a></div>
		</li>	
	</c:forEach>
</ul>
<script>
$(function(){
	$(".tu_list ul li").hover(function(){
		$(this).addClass("on");
	},function(){
		$(this).removeClass("on");
	});
	$(".tu_list ul .a2").click(function(){
		$(this).parent().parent().remove();
	})
});

<%--
	@function 修改图片
--%>
function updatePhoto(teacherId){
	$.initWinOnTopFromLeft('创建', '${ctp}/teach/photoManager/updatePhoto?teacherId='+teacherId, '500', '500');
}

</script>