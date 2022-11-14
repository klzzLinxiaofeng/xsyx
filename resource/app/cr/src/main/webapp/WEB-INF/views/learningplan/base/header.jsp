<%@include file="/views/embedded/taglib.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="qyzj_header">
	<div class="logo"><a href="${pageContext.request.contextPath}/learningPlan/index" style="display:block;width:100%;height:100%;"></a></div>
	<div class="qyzj_right">
		<ul>
			<li><a href="javascript:void(0)" class="a1" onclick="createLearningPlan();">创建导学案</a></li>
			<li><a href="${pageContext.request.contextPath}/learningPlan/task/my/index" class="a2">我布置的</a></li>
			<li><a href="${pageContext.request.contextPath}/learningPlan/task/index" class="a3">我接收的</a></li>
		</ul>
	</div>
</div>
<script type="text/javascript">
function createLearningPlan() {
	$.initWinOnTopFromLeft_qyjx("创建导学案", '${pageContext.request.contextPath}/learningPlan/creator', '674', '630');
}

function jump(type){
	location.href = "${pageContext.request.contextPath}/learningPlan/index?type="+type;
}
</script>