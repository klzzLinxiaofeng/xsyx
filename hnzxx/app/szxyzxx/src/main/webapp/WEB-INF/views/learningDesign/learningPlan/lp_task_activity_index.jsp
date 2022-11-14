<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/css/dxa/dxa.css" rel="stylesheet">
<title>学生小结</title>
<style>
	.pagination{
		margin:20px 0 0 0;
	}
</style>
</head>
<body>
<div class="container-fluid">
	<jsp:include page="/views/embedded/navigation.jsp">
		<jsp:param value="fa-asterisk" name="icon"/>
		<jsp:param value="${title }- 查看学生小结" name="title" />
		<jsp:param value="${param.dm}" name="menuId" />
	</jsp:include>
	<div class="row-fluid ">
		<div class="xsxj">
		    <p class="btn_link" style="float: right;position:relative;top:5px; margin-right: 0px;">
				<a href="javascript:void(0)" class="a4" onclick="javascript:window.history.back();return false;"><i class="fa  fa-reply"></i>返回列表</a>
			</p>
			<c:if test="${fn:length(lpUnitList)>0 }">
				<div class="nav">
					<c:forEach items="${lpUnitList }" var="lpUnit">
						<a href="javascript:void(0)" unitId="${lpUnit.id }">${lpUnit.title }</a>
					</c:forEach>
				</div>
				<div class="xj_list" style="display: none;">
					<div class="xj_div">
						<div class="jszj">
							<p class="title">教师总结</p>
							<p>请写下你的学习感悟和学习后存在的疑问</p>
						</div>
						<div class="ckxq">
							<a href="javascript:void(0)" onclick="detail(this);">查看详情</a>
							<p>已提交：<span class="c_green">16人</span>丨未提交：<span class="c_yellow">16人</span></p>
						</div>
						<div class="xszj_list" id="xszj_list_id">
							<jsp:include page="./lp_task_activity_list.jsp" />
						</div>
						<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
							<jsp:param name="id" value="xszj_list_id" />
							<jsp:param name="url" value="${pageContext.request.contextPath}/learningPlan/task/activity/user/list" />
							<jsp:param name="pageSize" value="${page.pageSize}" />
						</jsp:include>
						<div class="clear"></div>
					</div>
				</div>
			</c:if>
			<c:if test="${fn:length(lpUnitList)==0 }">
				<div class="no_xjdy"></div> 
			</c:if>
		</div>
	</div>
</div>
<script>
	$(function(){
		$(".xsxj .nav a").click(function(){
			$(".xsxj .nav a").removeClass("on");
			$(this).addClass("on");
			var i=$(this).index();
			$(".xj_list .xj_div").eq(i).show();
			var unitId = $(this).attr("unitId");
			getUnitContent(unitId);
		})
		if($(".xsxj .nav a").length>0) {
			$(".xj_list").show();
			$(".xsxj .nav a").eq(0).click();
		}
	})
	
	function getUnitContent(unitId) {
		$.ajax({
		    url: "${pageContext.request.contextPath}/learningPlan/unit/get",
		    type: "POST",
		    data: {"unitId":unitId, taskId:"${taskId}"},
		    async: true,
		    success: function(data) {
		    	var info = JSON.parse(data);
		    	$(".jszj").children("p").eq(1).html(info.unitContent);
		    	$(".ckxq").children("a").attr("unitId",info.id);
		    	$(".ckxq").children("p").children(".c_green").text(info.finishedCount);
		    	$(".ckxq").children("p").children(".c_yellow").text(info.unfinishCount);
		    	getUserActivityList(info.id);
		    }
		});
	}
	
	function getUserActivityList(unitId) {
		var data = {"taskId":"${taskId}", "unitId":unitId}
		var url = "${pageContext.request.contextPath}/learningPlan/task/activity/user/list";
		
		var loader = new loadDialog();
        loader.show();
	    myPagination("xszj_list_id", data, url,function() {
        	loader.close();
        });
	}
	
	function detail(obj) {
		var unitId = $(obj).attr("unitid");
		$.initWinOnTopFromLeft("提交情况", '${pageContext.request.contextPath}/learningPlan/task/activity/detail?taskId=${taskId}&unitId='+unitId, '800', '635');
	}
</script>
</body>
</html>