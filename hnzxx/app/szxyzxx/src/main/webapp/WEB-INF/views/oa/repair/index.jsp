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
			<jsp:param value="维修" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>
							维修列表
							<p class="btn_link" style="float: right;">
								<a href="javascript:void(0)" class="a3"
									onclick="$.refreshWin();"><i class="fa  fa-undo"></i>刷新列表</a>
								<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 0)}">
								<a href="javascript:void(0)" class="a4"
									onclick="loadCreatePage();"><i class="fa fa-plus"></i>创建维修</a>
								</c:if>
							</p>
						</h3>
					</div>
					<div class="light_grey"></div>
					<div class="content-widgets">
						<div class="widget-container">
							<div class="select_b">
								<div class="select_div">
									<span>标题：</span>
									<input type="text" id="title" name="title" style="margin-bottom: 0; padding: 6px; width: 120px;" placeholder="" value="">
								</div>
								<div class="select_div">
									<span>保修人姓名：</span>
									<input type="text" id="posterName" name="posterName" style="margin-bottom: 0; padding: 6px; width: 120px;" placeholder="" value="">
								</div>
								<button type="button" class="btn btn-primary" onclick="search()">查询</button>
								<div class="clear"></div>
							</div>
							<table class="responsive table table-striped" id="data-table">
								<thead>
									<tr role="row">
											<th>序号</th>
<!-- 											<th>appId</th> -->
<!-- 											<th>所属的单位、学校</th> -->
											<th>标题</th>
											<th>保修人姓名</th>
											<th>保修时间</th>
											<th>保修部门</th>
											<th>维修状态</th>
											<th>受理人</th>
											<th>负责人、维修人</th>
											<th>负责人联系电话</th>
											<th>受理时间</th>
											<th>维修完成时间</th>
											<th>维修内容</th>
											<th>评价得分</th>
											<th>评价内容</th>
										<th class="caozuo" style="max-width: 250px;">操作</th>
									</tr>
								</thead>
								<tbody id="repair_list_content">
									<jsp:include page="./list.jsp" />
								</tbody>
							</table>
							<c:if test="${isReceive != null && isReceive != ''}">
								<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
									<jsp:param name="id" value="repair_list_content" />
									<jsp:param name="url" value="/oa/repair/index?sub=list&isReceive=receive&dm=${param.dm}" />
									<jsp:param name="pageSize" value="${page.pageSize}" />
								</jsp:include>
							</c:if>
							<c:if test="${isReceive == null || isReceive == ''}">
								<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
									<jsp:param name="id" value="repair_list_content" />
									<jsp:param name="url" value="/oa/repair/index?sub=list&dm=${param.dm}" />
									<jsp:param name="pageSize" value="${page.pageSize}" />
								</jsp:include>
							</c:if>
							<div class="clear"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<input type="hidden" id="isReceive" value="${isReceive}"/>
</body>
<script type="text/javascript">
	function search() {
		var val = {
				"title" : $("#title").val(),
				"posterName" : $("#posterName").val()
		};
		var id = "repair_list_content";
		var url = "/oa/repair/index?sub=list&dm=${param.dm}";
		if($("#isReceive").val()!=null && $("#isReceive").val()!=""){
			url = "/oa/repair/index?sub=list&isReceive=receive&dm=${param.dm}";
		}
		myPagination(id, val, url);
	}

	// 	加载创建对话框
	function loadCreatePage() {
		$.initWinOnTopFromLeft('创建', '${ctp}/oa/repair/creator', '700', '400');
	}
	//  加载编辑对话框
	function loadEditPage(id) {
		$.initWinOnTopFromLeft('编辑', '${ctp}/oa/repair/editor?id=' + id, '700', '400');
	}
	//  加载详情对话框
	function loadViewerPage(id) {
		$.initWinOnTopFromLeft('详情', '${ctp}/oa/repair/viewer?id=' + id, '700', '400');
	}
	//  加载受理对话框
	function loadRececivePage(id) {
		$.initWinOnTopFromLeft('受理', '${ctp}/oa/repair/rececive?id=' + id, '700', '400');
	}
	//  加载完成对话框
	function loadFinishPage(id) {
		$.initWinOnTopFromLeft('维修完成', '${ctp}/oa/repair/finish?id=' + id, '700', '400');
	}
	//  加载评价对话框
	function loadAssessPage(id) {
		$.initWinOnTopFromLeft('评价', '${ctp}/oa/repair/assess?id=' + id, '700', '400');
	}
	// 	删除对话框
	function deleteObj(obj, id) {
		$.confirm("确定执行此次操作？", function() {
			executeDel(obj, id);
		});
	}

	// 	执行删除
	function executeDel(obj, id) {
		$.post("${ctp}/oa/repair/" + id, {"_method" : "delete"}, function(data, status) {
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
</script>
</html>