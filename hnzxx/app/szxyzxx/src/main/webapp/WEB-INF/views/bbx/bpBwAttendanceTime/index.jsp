<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link rel="stylesheet" href="${ctp}/res/css/bbx/bbx.css">
<link href="${ctp}/res/css/extra/add.css" rel="stylesheet">
<title></title>
</head>
<body>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span12">
			<div class="content-widgets">
				<div class="select_top">
				<div class="s1 s1_bg" id="schoolManagerSearch">
					<div hidden>
						<select id="xn"></select>
					</div>
					<select id="nj" name="gradeId" style="width: 160px;"></select> 
<!-- 					<select id="bj" name="teamId" style="width: 160px;"></select> -->
					<button class="btn btn-success" style="margin-top: -23px;" id="sosuo"
						onclick="search()">搜索</button>
					<div class="content">
						<div class="click">
							<button class="btn btn-warning" type="button"
								onclick="loadCreatePage();">发布</button>
						</div>
					</div>
				</div>
				</div>
				</div>
				
				<div class="content-widgets white">
					<div class="content-widgets">
						<div class="widget-container">
							<table class="responsive table table-striped" id="data-table">
								<thead>
									<tr role="row">
											<th>年级</th>
											<th>上学时间</th>
											<th>放学时间</th>
										<th class="caozuo" style="max-width: 250px;">操作</th>
									</tr>
								</thead>
								<tbody id="bwAttendanceTime_list_content">
									<jsp:include page="./list.jsp" />
								</tbody>
							</table>
							<%--<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">--%>
								<%--<jsp:param name="id" value="bwAttendanceTime_list_content" />--%>
								<%--<jsp:param name="url" value="/bbx/bpBwAttendanceTime/index?sub=list&dm=${param.dm}" />--%>
								<%--<jsp:param name="pageSize" value="${page.pageSize}" />--%>
							<%--</jsp:include>--%>
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
		$.initCascadeSelector({
			"type" : "team",
			"teamCallback" : function($this) {
			}
		});	
	})

	function search() {
		var val = {};
		var gradeId = $("#nj").val();
		if (gradeId != null && gradeId != "") {
			val.gradeId = gradeId;
		}
		var id = "bwAttendanceTime_list_content";
		var url = "/bbx/bpBwAttendanceTime/index?sub=list&dm=${param.dm}";
		myPagination(id, val, url);
	}

	// 	加载创建对话框
	function loadCreatePage() {
		var gradeId = $("#nj").val();
		if(gradeId == "" || gradeId == null){
			$.error("请选择年级");
			return;
		}
		$.initWinOnTopFromLeft('创建', '${ctp}/bbx/bpBwAttendanceTime/creator?gradeId='+gradeId, '700', '300');
	}
	//  加载编辑对话框
	function loadEditPage(id) {
		$.initWinOnTopFromLeft('编辑', '${ctp}/bbx/bpBwAttendanceTime/editor?gradeId=' + id, '700', '240');
	}
	
	// 	删除对话框
	function deleteObj(obj, id) {
		$.confirm("确定执行此次操作？", function() {
			executeDel(obj, id);
		});
	}

	// 	执行删除
	function executeDel(obj, id) {
		$.post("${ctp}/bbx/bpBwAttendanceTime/" + id, {"_method" : "delete"}, function(data, status) {
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