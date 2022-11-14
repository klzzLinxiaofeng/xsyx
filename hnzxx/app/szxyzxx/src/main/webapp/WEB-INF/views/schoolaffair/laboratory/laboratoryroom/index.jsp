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
			<jsp:param value="实验室管理" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>
							实验室信息管理
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
									<span>实验室名称：</span>
									<input maxlength="20" type="text" id="queryName" name="queryName" style="margin-bottom: 0; padding: 6px; width: 120px;" placeholder="" value="">
								</div>
								<div class="select_div">
									<span>实验室状态：</span>
									<select id="queryStatus" name="queryStatus">
										<option value="">不限</option>
										<option value ="true">正常</option>
									    <option value ="false">故障</option>
									</select>
								</div>
								<button type="button" class="btn btn-primary" onclick="search()">查询</button>
								<div class="clear"></div>
							</div>
							<table class="responsive table table-striped" id="data-table">
								<thead>
									<tr role="row">
											<th style="display: none;">实验室id</th>
											<th style="display: none;">schoolId</th>
											<th>实验室名称</th>
											<th>实验室编码</th>
											<th>实验室地址</th>
											<th>容纳人数</th>
											<th>容纳组数</th>
											<th>占用面积</th>
											<th>实验室面向课程</th>
											<th>实验室状态</th>
											<th>故障原因</th>
											<th>是否已报修</th>
											<th>预计修复时间</th>
											<th>仪器设备</th>
											<th style="display: none;">是否删除</th>
											<th style="display: none;">创建时间,用户排序</th>
											<th style="display: none;">修改时间</th>
										<th class="caozuo" style="max-width: 250px;">操作</th>
									</tr>
								</thead>
								<tbody id="laboratoryroom_list_content">
									<jsp:include page="./list.jsp" />
								</tbody>
							</table>
							<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
								<jsp:param name="id" value="laboratoryroom_list_content" />
								<jsp:param name="url" value="/schoolaffair/laboratoryroom/index?sub=list&dm=${param.dm}" />
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
		var val = {};
		var name = $("#queryName").val();
		var status = $("#queryStatus").val();
		if (name != null && name != "") {
			val.name = name;
		}
		
		if (status != null && status !="") {
			val.status = status;
		}
		
		var id = "laboratoryroom_list_content";
		var url = "/schoolaffair/laboratoryroom/index?sub=list&dm=${param.dm}";
		myPagination(id, val, url);
	}

	// 	加载创建对话框
	function loadCreatePage() {
		$.initWinOnTopFromLeft('创建', '${ctp}/schoolaffair/laboratoryroom/creator', '700', '660');
	}
	//  加载编辑对话框
	function loadEditPage(id) {
		$.initWinOnTopFromLeft('编辑', '${ctp}/schoolaffair/laboratoryroom/editor?id=' + id, '900', '500');
	}
	
	/* function loadViewerPage(id) {
		$.initWinOnTopFromLeft('详情', '${ctp}/schoolaffair/laboratoryroom/viewer?id=' + id, '700', '300');
	} */
	
	// 	删除对话框
	function deleteObj(obj, id) {
		$.confirm("确定执行此次操作？", function() {
			executeDel(obj, id);
		});
	}

	// 	执行删除
	function executeDel(obj, id) {
		$.post("${ctp}/schoolaffair/laboratoryroom/" + id, {"_method" : "delete"}, function(data, status) {
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