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
			<jsp:param value="领用明细" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>
							领用明细
							<p class="btn_link" style="float: right;">
								<a href="javascript:void(0)" class="a3"
									onclick="$.refreshWin();"><i class="fa  fa-undo"></i>刷新列表</a>
								<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 0)}">
								<a href="javascript:void(0)" class="a4"
									onclick="loadCreatePage();"><i class="fa fa-plus"></i>添加领用</a>
								</c:if>
							</p>
						</h3>
					</div>
					<div class="light_grey"></div>
					<div class="content-widgets">
						<div class="widget-container">
							<div class="select_b">
								<div class="select_div">
								<span>食堂：</span>
									<div class="select_div">
<!-- 									<input type="text" id="name" name="name" style="margin-bottom: 0; padding: 6px; width: 120px;" placeholder="" value=""> -->
									<select id="canteenCode" name="canteenCode" placeholder="食堂" style="margin-bottom: 0; padding: 6px; width: 120px;" class="span4">
									</select>
								</div>
								<div class="select_div">
								<span>领取时间：</span>
									<input type="text" id="takeDate" name="takeDate" class="span4" placeholder="领取时间" style="margin-bottom: 0; padding: 6px; width: 120px;" onclick="WdatePicker();">
								</div>
								</div>
								<button type="button" class="btn btn-primary" onclick="search()">查询</button>
								<div class="clear"></div>
							</div>
							<table class="responsive table table-striped" id="data-table">
								<thead>
									<tr role="row">
											<th>序号</th>
											<th>食堂</th>
											<th>商品名称</th>
											<th>原有库存</th>
											<th>领用数量</th>
											<th>剩余库存</th>
											<th>领用时间</th>
											<th style="width:12%">录入时间</th>
											<th>领取人</th>
										<th class="caozuo" style="max-width: 250px;">操作</th>
									</tr>
								</thead>
								<tbody id="canteenTakeOrder_list_content">
									<jsp:include page="./list.jsp" />
								</tbody>
							</table>
							<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
								<jsp:param name="id" value="canteenTakeOrder_list_content" />
								<jsp:param name="url" value="/schoolaffair/canteenTakeOrder/index?sub=list&dm=${param.dm}" />
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
	function search() {
		var val = {
				"canteenCode" : $("#canteenCode").val(),
				"takeDate" : $("#takeDate").val()
		};
		var name = $("#name").val();
		if (name != null && name != "") {
			val.name = name;
		}
		var id = "canteenTakeOrder_list_content";
		var url = "/schoolaffair/canteenTakeOrder/index?sub=list&dm=${param.dm}";
		myPagination(id, val, url);
	}
	
	$(function(){
		$.HqCanteenSelector({
			   "selector" : "#canteenCode",
			   "selectedVal" : "",
			   "afterHandler" : function() {
				}	
		   });
	});
	// 	加载创建对话框
	function loadCreatePage() {
		$.initWinOnTopFromLeft('创建', '${ctp}/schoolaffair/canteenTakeOrder/creator', '600', '500');
	}
	
	// 	删除对话框
	function deleteObj(obj, id) {
		$.confirm("确定执行此次操作？", function() {
			executeDel(obj, id);
		});
	}

	// 	执行删除
	function executeDel(obj, id) {
		$.post("${ctp}/schoolaffair/canteenTakeOrder/" + id, {"_method" : "delete"}, function(data, status) {
			if ("success" === status) {
				if ("success" === data) {
					$.success("删除成功");
					$("#" + id + "_tr").remove();
				} else if ("fail" === data) {
					$.error("删除失败，系统异常", 1);
				}
			}
			window.location.reload();
		});
	}
</script>
</html>