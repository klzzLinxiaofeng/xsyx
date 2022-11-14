<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/css/extra/add.css" rel="stylesheet">
<title>学期列表</title>
</head>
<body>
	<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon" />
			<jsp:param value="学期管理" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>
							学期列表
							<p class="btn_link" style="float: right;">
								<a href="javascript:void(0)" onclick="$.refreshWin();"
									class="a3"><i class="fa  fa-undo"></i>刷新列表</a> 
								<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 0)}">
									<a href="javascript:void(0)" class="a4"
										onclick="loadCreatePage();"><i class="fa fa-plus"></i>新增学期</a>
								</c:if>
							</p>
						</h3>
					</div>
					<div class="light_grey"></div>
					<div class="content-widgets">
						<div class="widget-container">
							<div class="select_b">
								<div class="select_div">
									<span>学年：</span>
									 <c:choose>
									<c:when test="${not empty schoolYear.year }">
											<select id="schoolYear" name="schoolYear" disabled="disabled"></select>
											<input type="text" id="schoolYear" type="text" value="${schoolYear.year }">
									</c:when>
									<c:otherwise>
										<select id="schoolYear" name="schoolYear"></select>
										
									 </c:otherwise>
								</c:choose>
								</div>
								<button type="button" class="btn btn-primary" onclick="search()">查询</button>
								<div class="clear"></div>
							</div>
							<table class="responsive table table-striped" id="data-table">
							<thead>
								<tr role="row">
									<th>序号</th>
									<th>学年</th>
									<th>学期名称</th>
									<th>开始时间</th>
									<th>结束时间</th>
									<th class="caozuo">操作</th>
								</tr>
							</thead>
							<tbody id="schoolTerm_list_content">
								<jsp:include page="./list.jsp" />
							</tbody>
						</table>
						<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
							<jsp:param name="id" value="schoolTerm_list_content" />
							<jsp:param name="url" value="/teach/schoolTerm/list?sub=list&dm=${param.dm}" />
							<jsp:param name="pageSize" value="${page.pageSize }" />
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
	$(function(){
		$.SchoolYearSelector({
			"selector" : "#schoolYear",
			"afterHandler" : function() {
				search();
			}
		});
	});

	function search() {
		var val = {
				"schoolYear" : $("#schoolYear").val()
		};
		var id = "schoolTerm_list_content";
		var url = "/teach/schoolTerm/list?sub=list&dm=${param.dm}";
		myPagination(id, val, url);
	}
	
	//	加载创建对话框
	function loadCreatePage() {
		var $schoolYear = $("#schoolYear").val();
		if("" != $schoolYear && null != $schoolYear) {
			 url = "${pageContext.request.contextPath}/teach/schoolTerm/creatorOther?year=" + $schoolYear; 
		}else {
			$.alert("请先选择学年");
			return;
// 			 url = "${pageContext.request.contextPath}/teach/schoolTerm/creator";
		}
		$.initWinOnTopFromLeft('新增学期', url, '600', '400');
	}

	//  加载编辑菜单对话框  isCK 参数是 是否是参看
	function loadEditPage(id, isCK, isOther) {
		var mes = "编辑学期";
		if (isCK == 'disable') {
			mes = "学期详情";
		}
		$.initWinOnTopFromLeft(mes, '${pageContext.request.contextPath}/teach/schoolTerm/editor?id=' + id + '&isCK=' + isCK, '600', '400');
	}

	// 	删除菜单对话框
	function deleteObj(obj, id) {
		$.confirm("确定执行此次操作？", function() {
			executeDel(obj, id);
		});
	}

	// 	执行删除
	function executeDel(obj, id) {
		$.post("${pageContext.request.contextPath}/teach/schoolTerm/" + id, {
			"_method" : "delete"
		}, function(data, status) {
			if ("success" === status) {
				if ("success" === data) {
					$.success("删除成功");
					$("#" + id + "_tr").remove();
				} else if ("fail" === data) {
					$.error("删除失败，系统异常");
				}
			}
		});
	}

	// 设置当前学年下当前学期
	function setCurrentTerm(id, $this) {
		var $this_ = $($this);
		$.post("${pageContext.request.contextPath}/teach/schoolTerm/currentTerm?id=" + id, function(data, status) {
			if ("success" === status) {
				if ("success" === data) {
					$.success("设置成功!");
					$(".set-current").addClass("btn-blue").removeClass("btn-success");
					$this_.removeClass("btn-blue").addClass("btn-success");
					$(".del-current").removeAttr("disabled");
					$this_.prev().attr("disabled",true);

					//解决设置学期之后不刷新，不能再次设置学期的问题
					search();
				} else if ("fail" === data) {
					$.error("设置失败，系统异常!");
				}
			}
		});
	}
</script>
</html>