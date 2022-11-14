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
			<jsp:param value="传染病疫情报告登记" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>
							传染病疫情报告登记列表
							<p class="btn_link" style="float: right;">
								<a href="javascript:void(0)" class="a3"
									onclick="$.refreshWin();"><i class="fa  fa-undo"></i>刷新列表</a>
								<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 0)}">
								<a href="javascript:void(0)" class="a4"
									onclick="loadCreatePage();"><i class="fa fa-plus"></i>添加报告</a>
								</c:if>
							</p>
						</h3>
					</div>
					<div class="light_grey"></div>
					<div class="content-widgets">
						<div class="widget-container">
							<div class="select_b">
								<div class="select_div">
									<span>日期：</span>
									<input type="text" id="startDate" name="startDate" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDate\')}'})" style="margin-bottom: 0; padding: 6px; width: 120px;" placeholder="2015-09-01" value="">
									&nbsp;-&nbsp;
									<input type="text" id="endDate" name="endDate" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'startDate\')}'})" style="margin-bottom: 0; padding: 6px; width: 120px;" placeholder="2015-09-05" value="">
								</div>
								<div class="select_div">
									<span>病名：</span>
									<input type="text" id="disease" name="disease" style="margin-bottom: 0; padding: 6px; width: 120px;" placeholder="" value="">
								</div>
								<button type="button" class="btn btn-primary" onclick="search()">查询</button>
								<div class="clear"></div>
							</div>
							<table class="responsive table table-striped" id="data-table">
								<thead>
									<tr role="row">
											<th>序号</th>
											<th>报告时间</th>
											<th>上报单位</th>
											<th>上报电话</th>
											<th>接报人</th>
											<th>病名</th>
											<th>发病地点</th>
											<th>发病人数</th>
											<th>首例时间</th>
											<th>主要症状</th>
											<th>疫情处理</th>
											<th>报告人</th>
											
										<th class="caozuo" style="max-width: 250px;">操作</th>
									</tr>
								</thead>
								<tbody id="healthDiseaseReport_list_content">
									<jsp:include page="./list.jsp" />
								</tbody>
							</table>
							<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
								<jsp:param name="id" value="healthDiseaseReport_list_content" />
								<jsp:param name="url" value="/schoolaffair/healthDiseaseReport/index?sub=list&dm=${param.dm}" />
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
		var disease = $("#disease").val();
		var startDate = $("#startDate").val();
		var endDate = $("#endDate").val();
		if (disease != null && disease != "") {
			val.disease = disease;
		}
		if (startDate != null && startDate != "") {
			val.startDate = startDate;
		}
		if (endDate != null && endDate != "") {
			val.endDate = endDate;
		}
		var id = "healthDiseaseReport_list_content";
		var url = "/schoolaffair/healthDiseaseReport/index?sub=list&dm=${param.dm}";
		myPagination(id, val, url);
	}

	// 	加载创建对话框
	function loadCreatePage() {
		$.initWinOnTopFromLeft('创建', '${ctp}/schoolaffair/healthDiseaseReport/creator', '900', '600');
	}
	//  加载编辑对话框
	function loadEditPage(id) {
		$.initWinOnTopFromLeft('编辑', '${ctp}/schoolaffair/healthDiseaseReport/editor?id=' + id, '900', '500');
	}
	
	function loadViewerPage(id) {
		$.initWinOnTopFromLeft('详情', '${ctp}/schoolaffair/healthDiseaseReport/viewer?id=' + id, '900', '500');
	}
	
	// 	删除对话框
	function deleteObj(obj, id) {
		$.confirm("确定执行此次操作？", function() {
			executeDel(obj, id);
		});
	}

	// 	执行删除
	function executeDel(obj, id) {
		$.post("${ctp}/schoolaffair/healthDiseaseReport/" + id, {"_method" : "delete"}, function(data, status) {
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