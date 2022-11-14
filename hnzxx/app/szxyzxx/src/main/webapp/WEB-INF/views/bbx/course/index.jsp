<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${ctp}/res/css/extra/add.css" rel="stylesheet">
<title></title>
<style type="text/css">
.pagination {
	margin: 0 21px 20px 0;
}

</style>
</head>
<body>
	<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon"/>
			<jsp:param value="走班科目" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>
							走班科目
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
								<div class="select_div"><span>学年：</span> <select id="xn" name="xn" style="width:150px;"></select></div>
								<div class="select_div"><span>学期：</span> <select id="xq" name="xq" style="width:150px;"></select></div>
								<div class="select_div"><span>年级：</span> <select id="nj" name="nj" style="width:150px;"></select></div>
								<div class="select_div" style="display: none;"><span>班级：</span> <select id="bj" name="bj" class="chzn-select" style="width:150px;"></select></div>
								<button type="button" class="btn btn-primary" onclick="search()">查询</button>
								<div class="clear"></div>
							</div>
							</div>
							<table class="responsive table table-striped" id="data-table" style="margin: 20px 20px 1px 20px; width: 96.5%;">
								<thead>
									<tr role="row">
											<th>科目名称</th>
<!-- 											<th>总学时</th> -->
											<th>每周学时</th>
											<!-- <th>每科计划报名人数</th> -->
										<th class="caozuo" style="max-width: 250px;">操作</th>
									</tr>
								</thead>
								<tbody id="course_list_content">
									<jsp:include page="./list.jsp" />
								</tbody>
							</table>
							<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
								<jsp:param name="id" value="course_list_content" />
								<jsp:param name="url" value="/bbx/course/index?sub=list&dm=${param.dm}" />
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
	$.initCascadeSelector({
		"type" : "team",
		"selectOne":true,
		"yearChangeCallback" : function(year) {
			if(year != "") {
				$.SchoolTermSelector({
					"selector" : "#xq",
					"condition" : {"schoolYear" : year},
					"afterHandler" : function($this) {
						$("#xq_chzn").remove();
						$this.show().removeClass("chzn-done").chosen();
					}
				});
			} else {
				$("#xq").val("");
				$("#xq_chzn").remove();
				$("#xq").show().removeClass("chzn-done").chosen();
			}
		}
// 		"gradeCallback" : function($this){
// 			search();
// 		}
	});
});




	function search() {
		var val = {};
		var gradeId = $("#nj").val();
		var startTerm = $("#xq").val();
		if (gradeId != null && gradeId != "") {
			val.gradeId = gradeId;
		}
		if (startTerm != null && startTerm != "") {
			val.startTerm = startTerm;
		}
		var id = "course_list_content";
		var url = "/bbx/course/index?sub=list&dm=${param.dm}";
		myPagination(id, val, url);
	}

	// 	加载创建对话框
	function loadCreatePage() {
		var gradeId = $("#nj").val();
		if ("" === gradeId || "undefind" === gradeId) {
			$.error("请选择年级");
			return false;
		}
		var termCode = $("#xq").val();
		if ("" === termCode || "undefind" === termCode) {
			$.error("请选择学期");
			return false;
		}
		$.initWinOnTopFromLeft('创建', '${ctp}/bbx/course/creator?gradeId=' + gradeId+"&termCode="+termCode, '700', '430');
	}
	//  加载编辑对话框
	function loadEditPage(id) {
		$.initWinOnTopFromLeft('编辑', '${ctp}/bbx/course/editor?id=' + id, '700', '300');
	}
	
	function loadViewerPage(id) {
		$.initWinOnTopFromLeft('详情', '${ctp}/bbx/course/viewer?id=' + id, '700', '300');
	}
	
	// 	删除对话框
	function deleteObj(obj, id) {
		$.confirm("确定执行此次操作？", function() {
			executeDel(obj, id);
		});
	}

	// 	执行删除
	function executeDel(obj, id) {
		$.post("${ctp}/bbx/course/" + id, {"_method" : "delete"}, function(data, status) {
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