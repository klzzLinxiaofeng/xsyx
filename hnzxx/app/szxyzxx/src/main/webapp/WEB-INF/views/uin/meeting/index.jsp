<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${ctp}/res/css/extra/add.css" rel="stylesheet">
<link href="${ctp}/res/css/extra/jcml.css" rel="stylesheet">
<title></title>
<style type="text/css">
.catalog .dl a{
	float : right;
}
/* .table .sorting_asc,.table .sorting_desc{
	background-position:center left;
	padding-left:20px;
} */
</style>
</head>
<body>
	<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon"/>
			<jsp:param value="今日会议" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3 class="jc_top">
							今日会议列表
							<p class="btn_link" style="float: right;">
								<a href="javascript:void(0)" class="a3"
									onclick="$.refreshWin();"><i class="fa  fa-undo"></i>刷新列表</a>
								<a href="javascript:void(0)" class="a6"
									onclick="loadCreatePage();"><i class="fa fa-plus"></i>创建会议</a>
							</p>
						</h3>
					</div>
					<div class="widget-head">
						<div class="row-fluid">
						<div class="widget-container">
<!-- 							<ul id="treeZylb" class="ztree"></ul> -->
						</div>
					</div>
					</div>
					<div class="light_grey"></div>
					<div class="content-widgets">
						<div class="widget-container">
							<table class="responsive table table-striped" id="data-table">
								<thead>
									<tr role="row">
											<th>会议号</th>
											<th>创建人</th>
											<th>开始时间</th>
											<th>主题</th>
											<th>密码</th>
											<th>状态</th>
											<th>在线人数</th>
										<th class="caozuo" style="max-width: 250px;">操作</th>
									</tr>
								</thead>
								<tbody id="uin_list_content">
									<jsp:include page="./list.jsp" />
								</tbody>
							</table>
							<div class="clear"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	// 	加载创建对话框
	function loadCreatePage() {
		$.initWinOnTopFromTop('创建', '${ctp}/uin/meeting/creator', '650', '760');
	}
	
	function joinConf(url){
// 		var loader = new loadLayer();
		window.open(url);
// 		loader.show();
// 		$.post("${ctp}/uin/meeting/closeConf",{"meetId" : url}, function(data, status) {
				
// 		});
		
	} 

	// 	结束会议
	function closeConf(meetId) {
		var loader = new loadLayer();
		loader.show();
		$.post("${ctp}/uin/meeting/closeConf", {"meetId" : meetId}, function(data, status) {
			if ("success" === status) {
				loader.close();
				if ("success" === data) {
					$.success("操作成功");
					window.location.reload();
				} else if ("fail" === data) {
					$.error("操作失败，系统异常", 1);
				}
			}
		});
	}
</script>
</html>