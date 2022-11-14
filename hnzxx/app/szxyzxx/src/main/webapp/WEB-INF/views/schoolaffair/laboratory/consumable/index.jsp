<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${ctp}/res/css/extra/add.css" rel="stylesheet">
<title></title>
</head>
<body>
	<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon"/>
			<jsp:param value="易耗品管理" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>
							易耗品管理
							<p class="btn_link" style="float: right;">
								<a href="javascript:void(0)" class="a3"
									onclick="$.refreshWin();"><i class="fa  fa-undo"></i>刷新列表</a>
								<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 0)}">
								<a href="javascript:void(0)" class="a4"
									onclick="loadCreatePage();"><i class="fa fa-plus"></i>创建</a>
								</c:if>
							</p>
						</h3>
					</div>
					<div class="light_grey"></div>
					<div class="content-widgets">
						<div class="widget-container">
							<div class="select_b">
								<div class="select_div">
									<span>物品名称：</span>
									<input type="text" id="queryName" name="queryName" style="margin-bottom: 0; padding: 6px; width: 120px;" placeholder="" value="">
								</div>
								<div class="select_div">
									<span>保存位置：</span>
									<select id="queryPosition" name="queryPosition">
									</select>
									<!-- <input type="text" id="queryPosition" name="queryPosition" style="margin-bottom: 0; padding: 6px; width: 120px;" placeholder="" value=""> -->
								</div>
								<button type="button" class="btn btn-primary" onclick="search()">查询</button>
								<div class="clear"></div>
							</div>
							<table class="responsive table table-striped" id="data-table">
								<thead>
									<tr role="row">
											<th style="display: none;">id</th>
											<th style="display: none;">schoolId</th>
											<th>物品名称</th>
											<th>物品编号</th>
											<th>生产日期</th>
											<th>型号规格</th>
											<th>计量单位</th>
											<th>数量</th>
											<th>保存位置</th>
											<th>保存年限</th>
											<th>单价</th>
											<th>厂家</th>
											<th>产地</th>
											<th style="display: none;">是否删除(0:未删除,1:已删除)</th>
											<th>备注</th>
											<th style="display: none;">创建时间,用户排序</th>
											<th style="display: none;">修改时间</th>
										<th class="caozuo" style="max-width: 250px;">操作</th>
									</tr>
								</thead>
								<tbody id="consumable_list_content">
									<jsp:include page="./list.jsp" />
								</tbody>
							</table>
							<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
								<jsp:param name="id" value="consumable_list_content" />
								<jsp:param name="url" value="/schoolaffair/consumable/index?sub=list&dm=${param.dm}" />
								<jsp:param name="pageSize" value="${page.pageSize}" />
							</jsp:include>
							<div class="clear"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	$(function(){
		var url = "${ctp}/schoolaffair/laboratoryroom/getLaboratoryRoomInfoList";
		var data = {}
		$.post(url, data, function(list){
			var obj = eval("("+list+")");
			$("#queryPosition").html("");
			$("#queryPosition").append("<option value=''>请选择</option>");
			for ( var i = 0; i < obj.length; i++  ) {
				var opt = "<option value='"+obj[i].id+"'>"+obj[i].name+"</option>";
				$("#queryPosition").append(opt);
			}
		 });
	});
	
	function search() {
		var val = {};
		var name = $("#queryName").val();
		var consumablePositionId = $("#queryPosition").val();
		if (name != null && name != "") {
			val.name = name;
		} 
		if ( consumablePositionId != null && consumablePositionId != "" ) {
			val.consumablePositionId = consumablePositionId;
		}
		var id = "consumable_list_content";
		var url = "/schoolaffair/consumable/index?sub=list&dm=${param.dm}";
		myPagination(id, val, url);
	}

	// 	加载创建对话框
	function loadCreatePage() {
		$.initWinOnTopFromLeft('创建', '${ctp}/schoolaffair/consumable/creator', '700', '710');
	}
	//  加载编辑对话框
	function loadEditPage(id) {
		$.initWinOnTopFromLeft('编辑', '${ctp}/schoolaffair/consumable/editor?id=' + id, '900', '500');
	}
	
	function loadViewerPage(id) {
		$.initWinOnTopFromLeft('详情', '${ctp}/schoolaffair/consumable/viewer?id=' + id, '700', '500');
	}
	
	// 	删除对话框
	function deleteObj(obj, id) {
		$.confirm("确定执行此次操作？", function() {
			executeDel(obj, id);
		});
	}

	// 	执行删除
	function executeDel(obj, id) {
		$.post("${ctp}/schoolaffair/consumable/" + id, {"_method" : "delete"}, function(data, status) {
			if ("success" === status) {
				if ("success" === data) {
					$.success("删除成功");
					$("#" + id + "_tr").remove();
					$.refreshWin();
				} else if ("fail" === data) {
					$.error("删除失败，系统异常", 1);
				}
			}
		});
	}
</script>
</html>