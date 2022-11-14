<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<title>班班星账号管理</title>
<link rel="stylesheet" href="${ctp}/res/css/bbx/bbx.css">
<style type="text/css">
.select_top .s1 select,.select_top .s1 input{float: left;margin-right: 5px}
</style>
</head>
<body>
<div class="container-fluid">
	<div class="row-fluid">
		<div class="select_top">
			<div class="s1 s1_bg" <c:if test="${userType=='2' }">hidden</c:if>>
				<select style="width: 120px;" id="province" name="province" ></select>
				<select style="width: 120px;" id="city" name="city" ></select>
				<select style="width: 120px;" id="district" name="district" ></select>
				<input type="text" id="name" name="name" placeholder="学校关键字">
				<button class="btn btn-success" onclick="search();">搜索</button>
			</div>
		</div>
	</div>
		<div class="row-fluid">
			<div class="zh_list">
				<table class="responsive table table-striped">
					<thead>
						<tr>
							<th>序号</th>
							<th>学校名称</th>
							<th>学校类型</th>
							<th>班级数量</th>
							<th>办学类别</th>
							<th>联系电话</th>
							<th>所在地</th>
							<th class="caozuo">操作</th>
						</tr>
					</thead>
					<tbody id="teamAccountManager_list_content"> 
					      <jsp:include page="./list.jsp"/>
					</tbody>
					</table>
					<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
						<jsp:param name="id" value="teamAccountManager_list_content" />
						<jsp:param name="url"
							value="/bbx/teamAccountManager/index?sub=list&dm=${param.dm}" />
						<jsp:param name="pageSize" value="${page.pageSize }" />
					</jsp:include>
					<div class="clear"></div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	$.initRegionSelector({
		sjSelector : "province",
		shijSelector : "city",
		qxjSelector : "district",
		isEchoSelected : true,
		sjSelectedVal : "${school.province}",
		shijSelectedVal : "${school.city}",
		qxjSelectedVal : "${school.district}"
	});	
	 
function search() {
	var val = {};
	var province = $("#province").val();
	var city = $("#city").val();
	var district = $("#district").val();
	var name = $("#name").val();
	if (province != null && province != "") {
		val.province = province;
	}

	if (city != null && city != "") {
		val.city = city;
	}

	if (district != null && district != "") {
		val.district = district;
	}

	if (name != null && name != "") {
		val.name = name;
	}

	var id = "teamAccountManager_list_content";
	var url = "/bbx/teamAccountManager/index?sub=list&dm=${param.dm}";
	myPagination(id, val, url);
}


	function enter(id) {
		window.location.href = "${ctp}/bbx/teamAccount/index?schoolId="+id;
	}

</script>
</html>