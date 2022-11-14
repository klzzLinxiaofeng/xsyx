<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/css/dxa/dxa.css" rel="stylesheet">
<style>
	.xj_list .xj_div{padding:0}
	.table th:first-child,.table td:first-child{padding-left:15px;}
	.finish_style{color:#189f4b}
	.unfinish_style{color:#9f0c1a}
</style>
<title>转码状态</title>
</head>
<body>
<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon"/>
			<jsp:param value="转码状态" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="xsxj">
				<div class="nav">
					<a href="javascript:void(0)" onclick="getListByType('VID')">视频</a>
					<a href="javascript:void(0)" onclick="getListByType('DOC')">文档</a>
				</div>
				<div class="xj_list">
					<div class="xj_div">
						<table class="responsive table table-striped" id="data-table">
								<thead>
									<tr>
										<th>序号</th>
										<th>文件名称</th>
										<th style="width:500px;">转换日志</th>
										<th>状态</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody id="tb_conversion">
									 <jsp:include page="./list.jsp" />
								</tbody>
							</table>
						<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
							<jsp:param name="id" value="tb_conversion" />
							<jsp:param name="url" value="/conversion/status/index?sub=list&type=${type}&dm=${param.dm}" />
							<jsp:param name="pageSize" value="${page.pageSize}" />
						</jsp:include>
						<div class="clear"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>
	$(function(){
		if("${type}" == "VID"){
			$(".xsxj .nav a").eq(0).addClass("on");
		}else{
			$(".xsxj .nav a").eq(1).addClass("on");
		}
		/* 列表页刷新时间间隔 */
		/* setInterval(seachByTime,5000); */
	});

	//根据类型获取转换列表
	function getListByType(type) {
		window.location.href =  "${ctp}/conversion/status/index?type=" + type;
	}

	//定时获取列表页数据
	function seachByTime(){
		var val = {};
		var id = "tb_conversion";
		var url = "/conversion/status/index?sub=list&type=${type}&dm=${param.dm}";
		myPagination(id, val, url);
	}

	//获取转换详情
	function getDetail(uuid){
		if(uuid == "" || "undefined" == uuid || uuid == null){
			$.alert("该资源异常，无法查看详情");
			return;
		}
		$.initWinOnTopFromLeft('转码详情', '${ctp}/conversion/status/detailed?uuid='+uuid, '500', '300');
	}
	
	//重新转换
	function conversionAgain(uuid,type){
		var url = "${ctp}/conversion/status/conversionAgain";
		$.post(url, {uuid:uuid,type:type}, function(data, status) {
			if("success" === status) {
				$.alert("发布转换任务成功");
			}else{
				$.alert("未知异常，转换失败");
			}
		});
	}
</script>
</body>
</html>