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
.table .blue{
	background-color: #00859D;
}
.table tbody td{
	padding-bottom:14px;
	position:relative;
	vertical-align: middle;
}
.table tbody td .edit{
	position:absolute;
	bottom:0;
	color:#7c798f;
	right:8px;
	cursor:pointer;
	font-weight:bold;
	line-height:14px;
}
.table tbody td .add{
	position:absolute;
	bottom:0;
	color:#7c798f;
	right:12px;
	cursor:pointer;
	font-weight:bold;
	line-height:14px;
}
.table tbody td .delete{
	position:absolute;
	bottom:0;
	color:#7c798f;
	left:8px;
	cursor:pointer;
	font-weight:bold;
	line-height:14px;
}
.chzn-container {

}	
.row-fluid .span13 {
	width: 75%;
}
.myerror {
	color: red !important;
	width:34%;
	display:inline-block;
	padding-left:10px;
}
.select_b .select_div span{
	float:left;
	line-height:31px;
	padding-left:0px;
	
}	

</style>
</head>
<body>
		<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param name="title" value="班级课表查询" />
			<jsp:param name="icon" value="icon-glass" />
			<jsp:param name="menuId" value="${param.dm}"  />
		</jsp:include>
			<div class="row-fluid">
				<div class="span12">
					<div class="content-widgets white">
					<div class="widget-head">
							<h3>
								班级课表查询
							</h3>
						</div>
						<div class="content-widgets">
							<div class="widget-container">
								<div class="select_b">
									<div class="select_div">
										<span style="padding-left:30px;">学年：</span> <select id="xn" name="xn" class="chzn-select" style="width:120px;"></select>
									</div>
									<div class="select_div">
										<span style="padding-left:30px;">年级：</span><select id="nj" name="nj" class="chzn-select" style="width:120px;"></select>
									</div>
									<div class="select_div">
										<span style="padding-left:30px;">班级： </span> <select id="bj" name="bj" class="chzn-select" style="width:120px;"></select>
									</div>
									<div class="select_div">
										<span style="padding-left:30px;">学期： </span> <select id="xq" name="xq" class="chzn-select" style="width:160px;"></select>
									</div>
									<button class="btn btn-primary" type="button" onClick="getSyllabus();">查询</button>
									<div class="clear"></div>
								</div>
							</div>
						</div>
<!-- 						<div class="termCode"> -->
<!-- 							<ul id="termCodeUl"> -->
							
<!-- 							</ul> -->
<!-- 							<div class="clear"></div> -->
<!-- 						</div> -->
						<div class="row-fluid" style="width:98%;margin: 15px auto;">
							<div id="kb_tb">
								
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
		});
	});
	
// 	function initTerm() {
// 		var year = $("#xn").val();
// 		var nj = $("#nj").val();
// 		var bj = $("#bj").val();
// 		if("" === year || "undefind" === year) {
// 			$.error("请选择学年");
// 		}
// 		if("" === nj || "undefind" === nj) {
// 			$.error("请选择年级");
// 		}
// 		if("" === bj || "undefind" === bj) {
// 			$.error("请选择班级");
// 		}
// 		$.getSchoolTerm({"schoolYear" : year}, function(data, status) {
// 			var $termCodeUl = $("#termCodeUl");
// 			$termCodeUl.html("");
// 			$.each(data, function(index, value) {
// 				$termCodeUl.append("<li data-term-code='" + value.code + "' onclick='getSyllabus(\"" + value.code + "\", this);'>" + value.name + "</li>");
// 			});
// 		});
// 		$("#kb_tb").html("");
// 	}
	
	function getSyllabus() {
		var year = $("#xn").val();
		var nj = $("#nj").val();
		var bj = $("#bj").val();
		var termCode = $("#xq").val();
		if ("" === year || "undefind" === year) {
			$.error("请选择学年");
			return false;
		}
		if ("" === nj || "undefind" === nj) {
			$.error("请选择年级");
			return false;
		}
		if ("" === bj || "undefind" === bj) {
			$.error("请选择班级");
			return false;
		}
		if ("" === termCode || "undefind" === termCode) {
			$.error("请选择学期");
			return false;
		}
		var $requestData = {};
		$requestData.teamId = $("#bj").val();
		$requestData.termCode = termCode;
		if($requestData.teamId == null || "" === $requestData.teamId) {
			$("#kb_tb").html("");
			$.error("请选择班级");
			return false;
		}
		
		$.get("${pageContext.request.contextPath}/teach/syllabus/searcher/team/list", $requestData, function(data, status) {
			if("success" === status) {
				$("#kb_tb").html("").html(data);
			}
		});
		
// 		$("#termCodeUl li").removeClass("active");
// 		$(obj).addClass("active");		
	}
</script>
</html>