<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<title>课表查询</title>
<style type="text/css">
.table th, .table td {
	text-align: center;
}

.table thead {
	background-color: #418BCA;
	color: #fff;
}

.table .blue {
	background-color: #00859D;
}

.table tbody td {
	padding-bottom: 14px;
	position: relative;
	vertical-align: middle;
}

.table tbody td .edit {
	position: absolute;
	bottom: 0;
	color: #7c798f;
	right: 8px;
	cursor: pointer;
	font-weight: bold;
	line-height: 14px;
}

.table tbody td .add {
	position: absolute;
	bottom: 0;
	color: #7c798f;
	right: 12px;
	cursor: pointer;
	font-weight: bold;
	line-height: 14px;
}

.table tbody td .delete {
	position: absolute;
	bottom: 0;
	color: #7c798f;
	left: 8px;
	cursor: pointer;
	font-weight: bold;
	line-height: 14px;
}

.row-fluid .span13 {
	width: 75%;
}

.myerror {
	color: red !important;
	width: 34%;
	display: inline-block;
	padding-left: 10px;
}
</style>
</head>
<body>

	<div class="container-fluid">
	<c:choose>
		<c:when test='${param.type ==  "all"}'>
			<jsp:include page="/views/embedded/navigation.jsp">
				<jsp:param name="title" value="教师课表查询"/>
				<jsp:param name="icon" value="icon-glass" />
				<jsp:param name="menuId" value="${param.dm}" />
			</jsp:include>
		</c:when>
		<c:otherwise>
			<jsp:include page="/views/embedded/navigation.jsp">
				<jsp:param name="title" value="个人课表查询"/>
				<jsp:param name="icon" value="icon-glass" />
				<jsp:param name="menuId" value="${param.dm}" />
			</jsp:include>
		</c:otherwise>
	</c:choose>
	
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>
							<c:if test='${param.type ==  "all"}'>教师课表查询</c:if>
							<c:if test='${param.type !=  "all"}'>个人课表查询</c:if>
						</h3>
					</div>
					<div class="content-widgets">
						<div class="widget-container">
							<div class="select_b">
								<div class="select_div">
									<span>学年：</span> <select id="xn" name="xn" class="chzn-select" style="width: 120px;" onchange="initTerm();"></select>
								</div>
								<div class="select_div">
									<span>学期：</span> <select id="xq" name="xq" class="chzn-select" style="width: 160px;"></select>
								</div>
								<c:if test='${param.type ==  "all"}'>
									<div class="select_div">
										<span>教师：</span><input id="teachId" data-id="" type="text" class="span6" name="teachId" value="" style="margin-bottom: 0" />
									</div>
								</c:if>
								<button class="btn btn-primary" type="button"
									onClick="getSyllabus();">查询</button>
								<div class="clear"></div>
							</div>
						</div>
					</div>
					<input type="hidden" value="${teacnId}">
					<div class="termCode">
						<ul id="termCodeUl">

						</ul>
						<div class="clear"></div>
					</div>
					<div class="row-fluid" style="width: 98%; margin: 15px auto;">
						<div id="kb_tb"></div>
					</div>
				</div>
			</div>
		</div>

	</div>
</body>
<script type="text/javascript">
	var type = "${param.type}";

	function getSyllabus() {
		var $requestData = {};
		
		var schoolYear = $("#xn").val();
		var termCode = $("#xq").val(); 
		if ("" === schoolYear || "undefind" === schoolYear) {
			$.error("请选择学年");
			return false;
		}
		if ("" === termCode || "undefind" === termCode) {
			$.error("请选择学期");
			return false;
		}
		$requestData.schoolYear = schoolYear;
		$requestData.termCode = termCode;
		
		if ("all" === type) {
			var teachId = $("#teachId").attr("data-id");
			if (teachId != "" && teachId != "undefined") {
				$requestData.teachId = teachId;
			} else {
				$.error("请选择教师");
				return false;
			}
		} else {
			$requestData.teachId = "${teachId}";
		}

		$.get("${pageContext.request.contextPath}/teach/syllabus/searcher/teacher/list", $requestData, function(data, status) {
			if ("success" === status) {
				$("#kb_tb").html("").html(data);
			}
		});

		$("#termCodeUl li").removeClass("active");
		$(obj).addClass("active");
	}

	$(function() {
		$.SchoolYearSelector({
			"selector" : "#xn",
			"afterHandler" : function(selector) {
				selector.trigger("change");
			}
		});

		if ("all" === type) {
			$.createMemberSelector({
				"inputIdSelector" : "#teachId",
				"enableBatch" : false
			});
		}
	});

	function initTerm() {
		var year = $("#xn").val();
		$.SchoolTermSelector({
			"selector" : "#xq",
			"condition" : {"schoolYear" : year},
			"afterHandler" : function($this) {
				$("#xq_chzn").remove();
				$this.show().removeClass("chzn-done").chosen();
			}
		});
		
		$("#kb_tb").html("");
	}

	function selectedHandler(data) {
		var ids = data.ids;
		var names = data.names;
		var windowName = data.windowName;
		$.closeWindowByName(windowName);
		$("#teachId_select").val(names[0]);
		$("#teachId").attr("data-id", ids[0]);
	}
</script>
</html>