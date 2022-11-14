<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<%@ include file="/views/embedded/plugin/zTree.jsp"%>
<%@ include file="/views/embedded/plugin/dept_selector_js.jsp"%>
<link href="${ctp}/res/css/extra/add.css" rel="stylesheet">
<title></title>
</head>
<body>
	<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon"/>
			<jsp:param value="职称管理" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>
							职称管理列表
							<p class="btn_link" style="float: right;">
								<a href="javascript:void(0)" class="a3"
									onclick="$.refreshWin();"><i class="fa  fa-undo"></i>刷新列表</a>
<%-- 								<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 0)}"> --%>
								<a href="javascript:void(0)" class="a4"
									onclick="loadCreatePage();"><i class="fa fa-plus"></i>添加评定</a>
<%-- 								</c:if> --%>
							</p>
						</h3>
					</div>
					<div class="light_grey"></div>
					<div class="content-widgets">
						<div class="widget-container">
							<div class="select_b">
								<div class="select_div">
									<span>职称：</span>
									<select id="titleType" name="titleType"></select>
								</div>
								<div class="select_div" style="position:relative">
									<span>关键字：</span>
									<input type="text" id="teacherId" name="teacherId" style="margin-bottom: 0; padding: 6px; width: 120px;" placeholder="点击选择教师" value="">
									<b id="delete_name" style="cursor:pointer;position:absolute;right:10px;top:7px;font-size:20px;display:none" onclick="delete_name()">x</b>
								</div>
								<button type="button" class="btn btn-primary" onclick="search()">查询</button>
								<div class="clear"></div>
							</div>
							<table class="responsive table table-striped" id="data-table">
								<thead>
									<tr role="row">
											<th>序号</th>
											<th>评定对象</th>
											<th>批准人</th>
											<th>获取职称</th>
											<th>获取方式</th>
											<th>获取时间</th>
										<th class="caozuo" style="max-width: 250px;">操作</th>
									</tr>
								</thead>
								<tbody id="title_list_content">
									<jsp:include page="./list.jsp" />
								</tbody>
							</table>
							<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
								<jsp:param name="id" value="title_list_content" />
								<jsp:param name="url" value="/personnel/title/index?sub=list&dm=${param.dm}" />
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

	$(function() {
		
		$.jcGcSelector("#titleType", {"tc" : "JY-ZYJSZW", "level" : "1"}, "${teacherTitle.titleType}", function(selector) {
			selector.chosen();
		});
		
		
		$.createMemberSelector({
			"inputIdSelector" : "#teacherId",
			"ry_type" : "teach",
			"enableBatch" : false,
			"layerOp" : {
				shadeClose : true
			}
			
		});
		
		
			});

	function search() {
		var val = {};
		var teacherId = $("#teacherId").val();
		if (teacherId != null && teacherId != "") {
			val.teacherId = teacherId;
		}
		var titleType = $("#titleType").val();
		if (titleType != null && titleType != "") {
			val.titleType = titleType;
		}
		var id = "title_list_content";
		var url = "/personnel/title/index?sub=list&dm=${param.dm}";
		myPagination(id, val, url);
	}

	// 	加载创建对话框
	function loadCreatePage() {
		$.initWinOnTopFromLeft('添加评定', '${ctp}/personnel/title/creator', '720', '470');
	}
	//  加载编辑对话框
	function loadEditPage(id) {
		$.initWinOnTopFromLeft('编辑评定', '${ctp}/personnel/title/editor?id=' + id, '720', '470');
	}
	
	function loadViewerPage(id) {
		$.initWinOnTopFromLeft('详情', '${ctp}/personnel/title/viewer?id=' + id, '720', '470');
	}
	
	// 	删除对话框
	function deleteObj(obj, id) {
		$.confirm("确定执行此次操作？", function() {
			executeDel(obj, id);
		});
	}
	
	//删除关键字里面的搜索条件
	function delete_name(data){
		$("#teacherId_select").val("");
		$("#teacherId").val("");
		$("#delete_name").hide();
	}
	
	// 	执行删除
	function executeDel(obj, id) {
		$.post("${ctp}/personnel/title/" + id, {"_method" : "delete"}, function(data, status) {
			if ("success" === status) {
				if ("success" === data) {
					$.success("删除成功");
					$("#" + id + "_tr").remove();
				} else if ("fail" === data) {
					$.error("删除失败，系统异常", 1);
				}
			}
		});
	}
	
	
	

	
	//创建弹出框的筛选教师控件的回调函数
	function selectedHandler(data) {
		$(parent.window.frames[data.targetWindowName].document).find("#teacherId_select").val(data.names[0]);
		$(parent.window.frames[data.targetWindowName].document).find("#teacherId").val(data.ids[0]);
		$(parent.window.frames[data.targetWindowName].document).find("#teacherId").next("label").remove();
		$.success("设置成功");
		$("#delete_name").show();
		$.closeWindowByNameOnParent(data.windowName);
	}
</script>
</html>