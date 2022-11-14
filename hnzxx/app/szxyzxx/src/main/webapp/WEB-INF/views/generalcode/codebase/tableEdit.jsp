<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<title>代码规范表</title>
</head>
<body>
<div class="container-fluid" style="margin-top:15px;">
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>
							代码规范表
							<p class="btn_link" style="float: right;">
							<a onclick="createTable();" class="a4" href="javascript:void(0)" ><i class="fa fa-plus"></i>新建</a>
							</p>
						</h3>
					</div>
					<div class="content-widgets">
						<div class="widget-container">
							<div class="select_b">
								<div class="select_div">
									<span>名称：</span>
									<input type="text" id="codeName" name="codeName">
								</div>
								
								  <button type="button" class="btn btn-primary" onclick="search()">查询</button>
								<div class="clear"></div>
							</div>
							<table class="responsive table table-striped" id="data-table">
								<thead>
									<tr>
										<th>次序</th>
										<th>名称</th>
										<th>代码</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody id="table_list_content">
									<!-- <tr>
										<td>1</td>
										<td>国籍</td>
										<td>JY-GJ</td>
										<td><button class="btn btn-success">编辑</button><button class="btn btn-danger">删除</button></td>
									</tr>
									<tr>
										<td>2</td>
										<td>国籍</td>
										<td>JY-GJ</td>
										<td><button class="btn btn-success">编辑</button><button class="btn btn-danger">删除</button></td>
									</tr> -->
									<c:forEach items="${tableList}" var="table" varStatus="status"> 
										<tr id="${table.id}_tr">
											<td>${status.index + 1}</td>
											<td>${table.name}</td>
											<td>${table.code}</td>
											<td><button class="btn btn-success" onclick="editTable('${table.id}');">编辑</button>
											<button class="btn btn-danger" onclick="delTable(this, '${table.id}');">删除</button></td>
										</tr>
										
									</c:forEach>
								</tbody>
							</table>
							<div class="clear"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div style="text-align:center;background-color:#29c5c7;height:40px;padding-top:8px;">
		<!-- <button class="btn btn-success">导入</button> -->
		<button class="btn">返回</button>
	</div>
</body>
<script type="text/javascript">

function search() {
	var val = {};
	var name = $("#codeName").val();
	if (name != null && name != "") {
		val.name = name;
	}
	var id = "table_list_content";
	var url = "/gc/codebase/index?sub=list&dm=${param.dm}";
	myPagination(id, val, url);
}

//	删除对话框
function delTable(obj, id) {
	$.confirm("确定执行此次操作？", function() {
		executeDel(obj, id);
	});
}

// 	执行删除
function executeDel(obj, id) {
	$.post("${ctp}/gc/codebase/editTable/" + id, {"_method" : "delete"}, function(data, status) {
		if ("success" === status) {
			if ("success" === data) {
				$.success("删除成功");
				$("#" + id + "_tr").remove();
			} else if ("fail" === data) {
				$.error("删除失败，系统异常", 1);
			}
		}
	});
	parent.window.location.reload();
	$.closeWindow();
}


//加载创建对话框
function createTable() {
	var frameworkId = ${tableList[0].frameworkId};
	$.initWinOnTopFromLeft('新增代码表', '${ctp}/gc/codebase/createTable?frameworkId=' + frameworkId, '850', '650');
}
//加载创建对话框
function editTable(id) {
	$.initWinOnTopFromLeft('编辑代码表', '${ctp}/gc/codebase/editTable?id=' + id, '850', '650');
}
</script>
</html>