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
			<jsp:param value="药品检查情况" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>
							药品检查情况列表
							<p class="btn_link" style="float: right;">
								<a href="javascript:void(0)" class="a3"
									onclick="$.refreshWin();"><i class="fa  fa-undo"></i>刷新列表</a>
								<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 0)}">
								<a href="javascript:void(0)" class="a4"
									onclick="loadCreatePage();"><i class="fa fa-plus"></i>添加药品</a>
								</c:if>
							</p>
						</h3>
					</div>
					<div class="light_grey"></div>
					<div class="content-widgets">
						<div class="widget-container">
							<div class="select_b">
								<div class="select_div">
									<span>卫生室：</span>
									<select id="clinicId" name="clinicId" class="chzn-select" style="width:120px;">
<!-- 										<option value="">请选择</option> -->
<%-- 										<c:forEach items="${clinicList }" var="clinic"> --%>
<%-- 											<option value="${clinic.id }">${clinic.name }</option> --%>
<%-- 										</c:forEach> --%>
									</select>
								</div>
								<div class="select_div">
									<span>药品名称：</span>
									<input type="text" id="name" name="name" style="margin-bottom: 0; padding: 6px; width: 120px;" placeholder="" value="">
								</div>
								<button type="button" class="btn btn-primary" onclick="search()">查询</button>
								<div class="clear"></div>
							</div>
							<table class="responsive table table-striped" id="data-table">
								<thead>
									<tr role="row">
											<th>序号</th>
											<th>卫生室</th>
											<th>检查日期</th>
											<th>药品名称</th>
											<th>库存量</th>
											<th>单位</th>
											<th>药品状态</th>
											
										<th class="caozuo" style="max-width: 250px;">操作</th>
									</tr>
								</thead>
								<tbody id="healthMedicineInspection_list_content">
									<jsp:include page="./list.jsp" />
								</tbody>
							</table>
							<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
								<jsp:param name="id" value="healthMedicineInspection_list_content" />
								<jsp:param name="url" value="/schoolaffair/healthMedicineInspection/index?sub=list&dm=${param.dm}" />
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
		var name = $("#name").val();
		var clinicId = $("#clinicId").val();
		if (name != null && name != "") {
			val.name = name;
		}
		if (clinicId != null && clinicId != "") {
			val.clinicId = clinicId;
		}
		var id = "healthMedicineInspection_list_content";
		var url = "/schoolaffair/healthMedicineInspection/index?sub=list&dm=${param.dm}";
		myPagination(id, val, url);
	}

	$(function() {
		$.HqClinicSelector({
			"selector" : "#clinicId",
			"afterHandler" : function() {
				
			}
		});
	});
	
	// 	加载创建对话框
	function loadCreatePage() {
		$.initWinOnTopFromLeft('创建', '${ctp}/schoolaffair/healthMedicineInspection/creator', '600', '450');
	}
	//  加载编辑对话框
	function loadEditPage(id) {
		$.initWinOnTopFromLeft('编辑', '${ctp}/schoolaffair/healthMedicineInspection/editor?id=' + id, '600', '450');
	}
	
	function loadViewerPage(id) {
		$.initWinOnTopFromLeft('详情', '${ctp}/schoolaffair/healthMedicineInspection/viewer?id=' + id, '600', '450');
	}
	
	// 	删除对话框
	function deleteObj(obj, id) {
		$.confirm("确定执行此次操作？", function() {
			executeDel(obj, id);
		});
	}

	// 	执行删除
	function executeDel(obj, id) {
		$.post("${ctp}/schoolaffair/healthMedicineInspection/" + id, {"_method" : "delete"}, function(data, status) {
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