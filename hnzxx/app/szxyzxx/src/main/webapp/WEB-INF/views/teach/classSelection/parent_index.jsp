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
			<jsp:param value="fa-asterisk" name="icon" />
			<jsp:param value="选课管理" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>
							选课管理列表
							<p class="btn_link" style="float: right;">
								<a href="javascript:void(0)" class="a3"
									onclick="$.refreshWin();"><i class="fa  fa-undo"></i>刷新列表</a>
							</p>
						</h3>
					</div>
					<div class="light_grey"></div>
					<div class="content-widgets">
						<div class="widget-container">
							<!-- 							<div class="select_b"> -->
							<!-- 								<div class="select_div"> -->
							<!-- 									<span>开始上课日期：</span> -->
							<!-- 									<input type="text" id="startDate" name="startDate" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDate\')}'})" style="margin-bottom: 0; padding: 6px; width: 120px;" placeholder="2015-09-01" value=""> -->
							<!-- 									&nbsp;-&nbsp; -->
							<!-- 									<input type="text" id="endDate" name="endDate" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'startDate\')}'})" style="margin-bottom: 0; padding: 6px; width: 120px;" placeholder="2015-09-05" value=""> -->
							<!-- 								</div> -->
							<!-- 								<div class="select_div"> -->
							<!-- 									<span>关键字：</span> -->
							<!-- 									<input type="text" id="keyword" name="keyword" style="margin-bottom: 0; padding: 6px; width: 120px;" placeholder="教师或课程名称" value=""> -->
							<!-- 								</div> -->
							<!-- 								<button type="button" class="btn btn-primary" onclick="search()">查询</button> -->
							<!-- 								<div class="clear"></div> -->
							<!-- 							</div> -->

							<input type="hidden" id="studentUserId">
							<div class="select_c">
								<input type="hidden" id="psListSize" value="${psList.size() }">
								<c:forEach items="${psList}" var="ps" varStatus="status">
										<a data-id="${ps.studentUserId}" href="javascript:void(0)" onclick="getPublicClass('${ps.studentUserId}')" <c:if test='${status.index==0}'>class="on"</c:if>>${ps.studentName}</a>
								</c:forEach>
							</div>

<!-- 							<table class="responsive table table-striped" id="data-table"> -->
<!-- 								<thead>									<tr role="row"> -->
<!-- 										<th>序号</th> -->
<!-- 										<th>课程名称</th> -->
<!-- 										<th>上课教师</th> -->
<!-- 										<th>开始上课日期</th> -->
<!-- 										<th>课程总节数</th> -->
<!-- 										<th>人数上限</th> -->
<!-- 										<th>已报名人数</th> -->
<!-- 										<th>报名截止日期</th> -->
<!-- 										<th>报名状态</th> -->
<!-- 										<th class="caozuo" style="max-width: 250px;">操作</th> -->
<!-- 									</tr> -->
<!-- 								</thead> -->
<!-- 								<tbody id="classSelection_list_content"> -->
<%-- 									<jsp:include page="./list.jsp" /> --%>
<!-- 								</tbody> -->
<!-- 							</table> -->
									<div class="widget-container" id="code_Nation"
									style="position: relative;">
									<div class="widget-head" style="border-bottom: 0 none;">
										<h3 id="krTeacher">
											选课列表
											<p style="float: right; margin-bottom: 3px;" class="btn_link">
<!-- 												<a href="javascript:void(0)" class="a3" -->
<!-- 													style="position: absolute; right: 130px; top: 3px; border-radius: 0; height: 30px;" -->
<!-- 													onclick="$.refreshWin();"><i class="fa  fa-undo"></i>刷新列表</a> -->
											</p>
										</h3>
									</div>
							<table class="table  table-striped responsive" id="tbody">
									<tbody id="tbodyId"></tbody>
							</table>
							<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
								<jsp:param name="id" value="classSelection_list_content" />
								<jsp:param name="url"
									value="/teach/classSelection/parentIndex?sub=list&dm=${param.dm}" />
								<jsp:param name="pageSize" value="${page.pageSize}" />
							</jsp:include>
							<div class="clear"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
// 	function search() {
// 		var val = {};
// 		var keyword = $("#keyword").val();
// 		var startDate = $("#startDate").val();
// 		var endDate = $("#endDate").val();
// 		if (keyword != null && keyword != "") {
// 			val.keyword = keyword;
// 		}
// 		if (startDate != null && startDate != "") {
// 			val.startDate = startDate;
// 		}
// 		if (endDate != null && endDate != "") {
// 			val.endDate = endDate;
// 		}
// 		var id = "classSelection_list_content";
// 		var url = "/teach/classSelection/index?sub=list&dm=${param.dm}";
// 		myPagination(id, val, url);
// 	}

	$(function() {
		$(".select_c a").click(function() {
			$(".select_c a").removeClass("on");
			$(this).addClass("on");
		});
		
		var studentUserId = $(".select_c a").attr("data-id");
// 		alert(studentUserId)
		$("#studentUserId").val(studentUserId);
		
		var psListSize = $("#psListSize").val();
		if(psListSize != 0) {
			getPublicClass(studentUserId);
		}
	});
	
	// 	加载创建对话框
	function loadCreatePage() {
		$.initWinOnTopFromLeft('创建', '${ctp}/teach/classSelection/creator',
				'700', '290');
	}
	//  加载编辑对话框
	function loadEditPage(id) {
		$.initWinOnTopFromLeft('编辑', '${ctp}/teach/classSelection/editor?id='
				+ id, '700', '300');
	}

	function loadViewerPage(id) {
		$.initWinOnTopFromLeft('详情', '${ctp}/teach/classSelection/viewer?id='
				+ id, '600', '680');
	}

	// 	删除对话框
	function deleteObj(obj, id) {
		$.confirm("确定执行此次操作？", function() {
			executeDel(obj, id);
		});
	}

	// 	执行删除
	function executeDel(obj, id) {
		$.post("${ctp}/teach/classSelection/" + id, {
			"_method" : "delete"
		}, function(data, status) {
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

	$.ajaxSetup({
		async : false
	});
	
	function enroll(classId) {
		var studentUserId = $("#studentUserId").val();
		var url = "${ctp}/teach/classSelection/enrollOther";
		$.post(url, {
			"_method" : "post",
			"classId" : classId,
			"studentUserId" : studentUserId
		}, function(data, status) {
			if ("success" === status) {
				if ("success" === data) {
					$.success("报名成功");
					setTimeout("getPublicClass(" + studentUserId + ")", 1000);
// 					window.location.href="${ctp}/teach/classSelection/getPublicClass?dm=${param.dm}&studentUserId=" + studentUserId;
// 					parent.core_iframe.window.location.reload();
				} else if ("fail" === data) {
					$.error("报名失败", 1);
				} else if ("forbid" == data) {
					$.error("报名失败，找不到您相关的班级信息");
				}
			}
		});
// 		getPublicClass(studentUserId);
	}

	function cancelEnroll(classId) {
		var studentUserId = $("#studentUserId").val();
		var url = "${ctp}/teach/classSelection/cancelEnrollOther";
		$.post(url, {
			"_method" : "post",
			"classId" : classId,
			"studentUserId" : studentUserId
		}, function(data, status) {
			if ("success" === status) {
				if ("success" === data) {
					$.success("取消成功");
					setTimeout("getPublicClass(" + studentUserId + ")", 1000);
// 					window.location.href="${ctp}/teach/classSelection/getPublicClass?dm=${param.dm}&studentUserId=" + studentUserId;
// 					parent.core_iframe.window.location.reload();
				} else if ("fail" === data) {
					$.error("取消失败，系统异常", 1);
				} else if ("" === data) {
					$.error("取消失败，查询不到该学生信息");
				}
			}
		});
// 		getPublicClass(studentUserId);
	}

	//获取公选课列表
	function getPublicClass(studentUserId) {
// 		alert("userId:" + studentUserId);
		$("#studentUserId").val(studentUserId);
		var url = "/teach/classSelection/getPublicClass?dm=${param.dm}";
		myPagination("tbodyId", {"studentUserId" : studentUserId}, url);
// 		$.post(url, {"_method" : "get", "studentUserId" : studentUserId}, function(data, status) {});
	}
</script>
</html>