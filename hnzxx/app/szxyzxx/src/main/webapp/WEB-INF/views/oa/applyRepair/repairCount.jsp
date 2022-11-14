<%@ page language="java"
	import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/css/extra/my.css"
	rel="stylesheet">
<script
	src="${pageContext.request.contextPath}/res/plugin/falgun/js/date.js"></script>
<script
	src="${pageContext.request.contextPath}/res/plugin/falgun/js/daterangepicker.js"></script>
<script src="${pageContext.request.contextPath}/res/js/extra/add.js"></script>
<title>报修</title>
<script>
	$(function() {
		var startNum = ${all};
		for(i=0;i<startNum;i++){
			$("#m-star i").eq(i).addClass("active");
		}
		if(startNum == 0){
			$("#startpj").text("还未有评价");
		}else if(startNum == 1){
			$("#startpj").text("差");
		}else if(startNum == 2){
			$("#startpj").text("一般");
		}else if(startNum == 3){
			$("#startpj").text("较好");
		}else if(startNum == 4){
			$("#startpj").text("好");
		}else if(startNum == 5){
			$("#startpj").text("非常好");
		}else{
			$("#startpj").text("很差");
		}
		$('#reservation').daterangepicker();
	});
</script>
</head>
<body>
	<div class="container-fluid">
		<div class="row-fluid ">
			<div class="span12">
				<div class="content-widgets">
					<div class="x-main clearfix">
						<div class="x-pingjia">
							<span class="numa">报修申请数<i>${tatol}</i></span>
							<span class="numb">待处理<i>${wait}</i></span>
							<span class="numc">处理中<i>${being}</i></span>
							<span class="numd">已处理<i>${already}</i></span>
							<span class="nume">未修好<i>${notreapair}</i></span>
						</div>
						<div class="tc span12 x-mark" style="margin-left: 0">
							<span class="fl fen">总体评价</span> 
							<em class="m-star" id="m-star">
								<input type="hidden" value="${all}" id="all"/>
								<i class="fa fa-star" title="差"></i>
								<i class="fa fa-star" title="一般"></i>
								<i class="fa fa-star" title="较好"></i> 
								<i class="fa fa-star" title="好"></i> 
								<i class="fa fa-star" title="非常好"></i>
							</em>
							<span class="s_result fl pj" id="startpj"></span>
						</div>
					</div>

					<div class="content-widgets">
						<div class="widget-head"
							style="margin: 40px 0 0 0; border-top: #ccc 1px solid;">
							<h3>评价明细</h3>
						</div>
						<div class="widget-container" style="padding: 0 20px 1px 20px;">
							<table class="responsive table table-striped" id="data-table">
								<thead>
									<tr role="row">
										<th>序号</th>
										<th>维修人员</th>
										<th>联系电话</th>
										<th>维修次数</th>
										<th>总体评价</th>
									</tr>
								</thead>
								<tbody>
									<jsp:include page="./countList.jsp" />
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>