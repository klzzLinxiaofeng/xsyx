<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/css/extra/add.css"
	rel="stylesheet">
<title>楼层列表</title>
</head>
<body>
	<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon" />
			<jsp:param value="楼层管理" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>
							楼层列表
							<p class="btn_link" style="float: right;">
								<a style="margin-right: 118px;" href="javascript:void(0)" onclick="window.location.reload();"
									class="a3"><i class="fa  fa-undo"></i>刷新列表</a>
								<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 0)}">
<!-- 									<a href="javascript:void(0)" class="a2" -->
<!-- 										id="downloadExcel" onclick="downloadExcel();"><i class="fa fa-plus"></i>导出楼层信息</a> -->
									<a href="javascript:void(0)" class="a4"
										onclick="loadCreatePage();"><i class="fa fa-plus"></i>新增楼层</a>
								</c:if>
							</p>
						</h3>
					</div>
					<div class="content-widgets">
						<div class="widget-container">
						<form action="${ctp}/schoolaffair/floor/downLoadFloor" method="post" style="position:relative;">
						<button type="submit" class="btn btn-info  a2 fa fa-plus" style="position:absolute;right:72px;top:-56px;border-radius:0; background-color:#EFAD4D; height:30px;">&nbsp;&nbsp;导出楼层信息 </button>
							<div class="select_b">
								<div class="select_div">
									<span>楼层名称：</span><input type="text" id="name" name="name"
										data-id-container="name"
										style="margin-bottom: 0; width: 120px; margin-right: 3px;"
										placeholder="" data-id="" value="">
								</div>
								<button onclick="search()" class="btn btn-primary" type="button">查询</button>
								<div class="clear"></div>
							</div>
						</form>
							<table class="responsive table table-striped" id="data-table">
								<thead>
									<tr role="row">
										<th>序号</th>
										<th>名称</th>
										<th>编号</th>
										<th>用途</th>
										<th>楼层数</th>
										<!-- <th>所属学校</th> -->
										<th>状态</th>
										<th>创建时间</th>
										<th class="caozuo">操作</th>
									</tr>
								</thead>
								<tbody id="floor_list_content">
									<jsp:include page="./list.jsp" />
								</tbody>
							</table>
							<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
								<jsp:param name="id" value="floor_list_content" />
								<jsp:param name="url"
									value="/schoolaffair/floor/list?sub=list&dm=${param.dm}" />
								<jsp:param name="pageSize" value="${page.pageSize }" />
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
		var val = {};
		var name = $("#name").val();

		if (name != null && name != "") {
			val.name = name;
		}

		var id = "floor_list_content";
		var url = "/schoolaffair/floor/list?sub=list&dm=${param.dm}";
		myPagination(id, val, url);
	}

	//	加载创建对话框
	function loadCreatePage() {
		$
				.initWinOnTopFromLeft(
						'新增楼层',
						'${pageContext.request.contextPath}/schoolaffair/floor/creator',
						'600', '500');
	}

	//  加载编辑菜单对话框  isCK 参数是 是否是参看
	function loadEditPage(id, isCK) {
		var mes = "编辑楼层";
		if (isCK == 'disable') {
			mes = "楼层详情";
		}
		$.initWinOnTopFromLeft(mes,
				'${pageContext.request.contextPath}/schoolaffair/floor/editor?id='
						+ id + '&isCK=' + isCK, '600', '500');
	}
	
	function downloadExcel() {
		var name = $("#name").val();
		var url = "${ctp}/schoolaffair/floor/downLoadFloor?";
		var param = "name=" + name;
		url = url + param;
		url = encodeURI(url);
		$("#downloadExcel").attr("href", url);
	}

	// 	删除菜单对话框
	function deleteObj(obj, id) {
		$.confirm("确定执行此次操作？", function() {
			executeDel(obj, id);
		});
	}
	
	// 	执行删除
	function executeDel(obj, id) {
		$.post("${pageContext.request.contextPath}/schoolaffair/floor/" + id, {
			"_method" : "delete"
		}, function(data, status) {
			if ("success" === status) {
				if ("success" === data) {
					$.success("删除成功");
					$("#" + id + "_tr").remove();
				} else if ("fail" === data) {
					$.error("删除失败，系统异常");
				}
			}
		});
	}
	$(function(){
		if($(".a4").length==0){
			$(".a2").css("right","-20px");
			$(".a3").css("margin-top","2px");
		}
	})
</script>
</html>