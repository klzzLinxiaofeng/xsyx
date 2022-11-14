<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${ctp}/res/css/extra/add.css" rel="stylesheet">
<title>手动操作</title>
<style>
  li{
        list-style:none;
    }
    .d ul{
        margin:0px;padding: 0px;
    }
    .d ul li{
        background: #aaa;float: left;margin-right: 20px;
    }
    .d ul li p{
        font-size: 14px;margin:0px;
    }

</style>
</head>
<body>
<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon"/>
			<jsp:param value="手动生成页面" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>
							学校列表
							<p class="btn_link" style="float: right;">
								<a href="javascript:void(0)" class="a3"
									onclick="$.refreshWin();"><i class="fa  fa-undo"></i>刷新列表</a>
							</p>
						</h3>
					</div>
				</div>
			</div>
				<table class="responsive table table-striped" id="data-table">
					<thead>
						<tr role="row">
								<th>序号</th>
								<th>学校</th>
								<th>AppId</th>
								<th>SchoolId</th>
							<th class="caozuo" style="max-width: 250px;">操作</th>
						</tr>
					</thead>
					<tbody id="">
					<c:forEach items="${rlList}" var="rl" varStatus="i">
							<tr id="${rl.id}_tr">
										<td>${i.index+1}</td>       <!--序号  -->
										<td>${rl.name}</td>
										<td>${rl.appId}</td>
										<td>${rl.owerId}</td>
								<td class="caozuo">
									<button class="btn btn-green" type="button"
										onclick="createMicro('${rl.id}');">生成微课首页</button>
									<button class="btn btn-blue" type="button"
										onclick="createResource('${rl.id}');">生成资源首页</button>
								</td>
							</tr>
						</c:forEach>
					<tr>
						<td>all</td>
						<td>所有学校</td>
						<td>所有学校</td>
						<td>所有学校</td>
						<td class="caozuo">
						<button class="btn btn-green" type="button"
								onclick="createDisplay();">生成基础资源</button>
							<button class="btn btn-green" type="button"
								onclick="createAllMicro();">生成所有微课首页</button>
							<button class="btn btn-blue" type="button"
								onclick="createAllResource();">生成所有资源首页</button>
						</td>
					</tr>
					</tbody>
				</table>
		</div>
		</div>
</body>
<script type="text/javascript">
function createMicro(id){
	var url = "${ctp}/mmm/micro";
	var loader = new loadLayer();
	loader.show();
	$.post(url,{"id":id},function(data, status){
		if("success" === status) {
			$.success('生成成功');
		}else{
			$.error("生成成功");
		}
		loader.close();
	});
}
function createResource(id){
	var url = "${ctp}/rrr/resource";
	var loader = new loadLayer();
	loader.show();
	$.post(url,{"id":id},function(data, status){
		if("success" === status) {
			$.success('生成成功');
		}else{
			$.error("生成成功");
		}
		loader.close();
	});
}
function createDisplay(){
	var url = "${ctp}/choiceAll/display";
	var loader = new loadLayer();
	loader.show();
	$.post(url,function(data, status){
		if("success" === status) {
			$.success('生成成功');
		}else{
			$.error("生成成功");
		}
		loader.close();
	});
}
function createAllMicro(){
	var url = "${ctp}/choiceAll/micro";
	var loader = new loadLayer();
	loader.show();
	$.post(url,function(data, status){
		if("success" === status) {
			$.success('生成成功');
		}else{
			$.error("生成成功");
		}
		loader.close();
	});
}
function createAllResource(){
	var url = "${ctp}/choiceAll/resource";
	var loader = new loadLayer();
	loader.show();
	$.post(url,function(data, status){
		if("success" === status) {
			$.success('生成成功');
		}else{
			$.error("生成成功");
		}
		loader.close();
	});
}
</script>
</html>