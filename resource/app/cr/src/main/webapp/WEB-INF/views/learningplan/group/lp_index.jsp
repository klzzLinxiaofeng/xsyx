<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<title>分组方案</title>
<style>
.layui-layer-dialog .layui-layer-content{
	text-align:center;
	font-size: 16px;
    color: #333333;
/*     line-height: 45px; */
}
.content_main .nr_right{border-radius:5px;}
.content_main .nr_right .px_top{    border-radius: 5px 5px 0 0;}
.layui-layer-content{box-sizing: border-box;}
</style>
</head>
<body>
<jsp:include page="./header.jsp"></jsp:include>
<div class="content_main">
	<jsp:include page="./groupWidget.jsp"></jsp:include>
	<div id="group_list">
<%-- 		<jsp:include page="./group_list.jsp"></jsp:include> --%>
	</div>
</div>
</body>
<script type="text/javascript">
var index = 0;
var param = null;
var topTab = "school";
var property = "modify_date";
var ascending = false;

$(function() {
	invokeStageWidget(0);
})

//调用学段科目组件
function invokeStageWidget(type, afterHandler) {
	var trail = true;
	if("fav"==topTab){
		type = 2
	}

    <c:choose>
    <c:when test="${fn:contains(sessionScope[sca:currentUserKey()].userTypes, 2) }">$.gradeWidget(1);</c:when>
    <c:otherwise>$.gradeWidget(0);</c:otherwise>
	</c:choose>

}
</script>
</html>