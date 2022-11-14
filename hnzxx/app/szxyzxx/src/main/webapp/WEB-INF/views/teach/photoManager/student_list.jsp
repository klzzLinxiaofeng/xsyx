<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
<ul>
	<c:forEach items="${studentList }" var="student" varStatus="i">
		<li>
			<img src="<avatar:avatar userId='${student.userId }'></avatar:avatar>">
			<p class="name">${student.name }</p>
			<div class="cz"><a href="javascript:void(0)" class="a1" onclick="updatePhoto('${student.userId }')">修改</a></div>
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
function updatePhoto(userId){
	$.initWinOnTopFromLeft('创建', '${ctp}/teach/photoManager/updateStudentPhoto?userId='+userId, '500', '500');
}

</script>