<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${ctp}/res/css/bbx/bbx.css" rel="stylesheet">
<title></title>
</head>
<body>
<div class="container-fluid">
	<div class="row-fluid">
		<div class="select_class">
			<div hidden><select id="xn" ></select></div>
		    <select id="nj" onchange="search();" ></select>
			<select id="bj" onchange="search();" style="width:120px;"></select>
			<button class="btn btn-success" onclick="search()" style="margin: 0 20px 0 0;height: 30px;float: right;">搜索</button>
			
		</div>
	</div>
	</div>
</body>
<script type="text/javascript">
$.initCascadeSelector({"type" : "team", "teamCallback" : function($this){
}});
function search(){
	var val = {};
	var xn = $("#xn").val();
	var nj = $("#nj").val();
	var bj = $("#bj").val();
	if (xn != null && xn != "") {
		val.schoolYear = xn;
	}

	if (nj != null && nj != "") {
		val.gradeId = nj;
	}

	if (bj != null && bj != "") {
		val.id = bj;
	}else{
		return;
	}
	window.location.href="${ctp}/clazz/seatChart/sm_get?id="+val.id;
}



</script>