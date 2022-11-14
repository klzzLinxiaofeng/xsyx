<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/views/embedded/common.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/res/css/extra/add.css"
	rel="stylesheet">
<title>${sca:getDefaultSchoolName()}</title>
<style type="text/css">
table th, table td {
	height: 29px;
	line-height: 29px;
}
</style>
</head>
<body>
	<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon" />
			<jsp:param value="班主任管理" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>班主任管理</h3>
						<div class="light_grey"></div>
						<div class="content-widgets">
							<div class="select_c">
								<c:forEach items="${gradeList}" var="grade" varStatus="status"> 
								<a data-obj-id="${grade.id}" data-obj-name="${grade.name }" href="javascript:void(0)" onclick="getTeamList('${grade.id }')"
										<c:if test='${status.index==0}'>class="on"</c:if>>${grade.fullName }</a>
								</c:forEach>
							</div>
							<div class="widget-container" id="code_Nation">
								<div style="position: relative; width: 100%;">
									<input type="hidden" id="gradeId" name="gradeId"  value=""/>
									<input type="hidden" id="teamId" name="teamId"  value=""/>
									<table class="table  table-striped responsive table-bordered">
										<thead>
											<tr role="row">
												<th>班级名称</th>
						                        <th>班主任</th>
						                        <th>操作</th>
											</tr>
										</thead>
										<tbody id="module_list_content">
											
										</tbody>
									</table>
									<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
										<jsp:param name="id" value="module_list_content" />
										<jsp:param name="url" value="/teach/classTeacher/getTeamList?sub=list&dm=${param.dm}" />
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
	
$(function() {
	$(".select_c a").click(function() {
		$(".select_c a").removeClass("on");
		$(this).addClass("on");
	});
	
	var gradeId = $(".select_c a").attr("data-obj-id");
	$("#gradeId").val(gradeId);
	getTeamList(gradeId);
	
	$.createMemberSelectorByClass({
		"inputClassSelector" : ".add_classTeacher",
		"enableBatch" : false
	});
});


//获取班级列表
function getTeamList(gradeId) {
	$("#gradeId").val(gradeId);
	var url = "/teach/classTeacher/getTeamList?dm=${param.dm}";
	myPagination("module_list_content", {"gradeId" : gradeId}, url);
}

	function ajaxFunction(gradeId, gradeName) {
		var url = "${pageContext.request.contextPath}/teach/classTeacher/getTeamList";
		var aj = $.ajax({
			url : url,
			data : 'gradeId=' + gradeId,
			type : 'post',
			cache : false,
			dataType : 'json',
			success : function(data) {
				loadTable(data);
					
				$.createMemberSelectorByClass({
					"inputClassSelector" : ".add_classTeacher",
					"enableBatch" : false
				});
			},
			error : function() {
				$.alert("异常！");
			}
		});
	}
	
	function loadTable(data) {
		var tbodyId = $("#module_list_content");
		if (data.length == 0) {
			tbodyId.html("<tr ><td colspan='3'>暂无班级数据！</td></tr>");
			return false;
		} else {
			var tr_ = "";
			var teamTrs = "";
			var teamtds = "";
			for (var i = 0, len = data.length; i < len; i++) {
				var teacherName = "";
				var id = data[i].id;
				if (data[i].teacherName != null) {
					teacherName = data[i].teacherName;
				}
				teamTrs = "<tr role='row'><td>" + data[i].teamName
						+ "</td><td>" + teacherName + "</td>";
				if (id == 0) {
					teamtds = "<td><button id='"+data[i].teamId+"' data_obj_grade='"+data[i].gradeId+"' class='add_classTeacher btn btn-blue' type='button'>新增</button></td></tr>";
				}
				if (id != 0) {
					teamtds = "<td><button id='"+data[i].teamId+"' data_obj_grade='"+data[i].gradeId+"' class='add_classTeacher btn btn-blue' type='button'>编辑</button></td></tr>";
				}
				tr_ += teamTrs + teamtds;
			}
			//alert(tr_);
			tbodyId.html(tr_);
		}
	}

	function selectedHandler(data) {
		var loader = new loadLayer();
		loader.show();
		// 		alert(JSON.stringify(data));
		var $requestData = {};
		var gradeId = $("#gradeId").val();
		var teamId = data.btnId
		var teacherId = data.ids;
		var type = "1";
		//alert($requestData);
// 		var url = "${pageContext.request.contextPath}/teach/classTeacher/addClassTeacher?gradeId="
// 				+ gradeId
// 				+ "&teamId="
// 				+ teamId
// 				+ "&teacherId="
// 				+ teacherId
// 				+ "&type=" + type;
		var url = "${pageContext.request.contextPath}/teach/classTeacher/addOrModifyClassTeacher?gradeId="
			+ gradeId
			+ "&teamId="
			+ teamId
			+ "&teacherId="
			+ teacherId;
		$.post(url, $requestData, function(responseData, status) {
			if ("success" === status) {
				if ("success" === responseData) {
					$.success("保存成功");
					if (parent.core_iframe != null) {
						getTeamList(gradeId);
					} else {
						getTeamList(gradeId);
					}
					$.closeWindowByName(data.windowName);
				} else {
					$.error("保存失败");
				}
			} else {
				$.error("服务器异常");
			}
			loader.close();
		});
	}

	// 	删除对话框
	function deleteObj(obj, id) {
		$.confirm("确定执行此次操作？", function() {
			executeDel(obj, id);
		});
	}
	
	//移除班主任
	function executeDel(obj, id) {
		var gradeId = $("#gradeId").val();
		$.post("${pageContext.request.contextPath}/teach/classTeacher/" + id, {"_method" : "delete"}, function(data, status) {
			if ("success" === status) {
				if ("success" === data) {
					$.success("移除成功");
					getTeamList(gradeId);
				} else if ("fail" === data) {
					$.error("移除失败");
				}else {
					$.error("移除失败，系统异常");
				}
			}
		});
	}
	
	// 	function getTeacherList(teamId,gradeId){
	// 		$.initWinOnTopFromLeft(
	// 				"新增班主任",
	// 				"${pageContext.request.contextPath}/teach/classTeacher/addClassTeacherPage?teamId="
	// 						+ teamId
	// 						+ "&gradeId="+ gradeId
	// 						+ "&urlType=1", '800', '500');
	// 	}
</script>
</html>
