<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/res/css/extra/add.css" 
	rel="stylesheet">
<%@ include file="/views/embedded/common.jsp"%>
<title>宿舍列表</title>
</head>
<body>
	<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon"/>
			<jsp:param value="宿舍信息" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>  
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>
							宿舍列表
							<p class="btn_link" style="float: right;">
							<!-- <a href="javascript:void(0)" class="a1"
									onclick="loadDownLoadPage();"><i class="fa fa-download"></i>模板下载</a> -->
<!-- 							 <a href="javascript:void(0)" class="a2" -->
<!-- 									onclick=""><i class="fa fa-plus"></i>导出数据</a>  -->
								<!-- <a href="javascript:void(0)" class="a3"
									onclick="upLoadPage();"><i class="fa fa-plus"></i>批量导入数据</a> -->
								 <a href="javascript:void(0)" class="a3" style="position:absolute;right:210px;top:3px;border-radius:0; height:30px;l"
									onclick="$.refreshWin();"><i class="fa  fa-undo"></i>刷新列表</a>
								<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 0)}">
								<a href="javascript:void(0)" class="a4"
									onclick="loadCreatePage();"><i class="fa fa-plus"></i>添加宿舍</a>
								</c:if>
							</p>
						</h3>
					</div>
					<div class="light_grey"></div>
					<div class="content-widgets">
						<div class="widget-container">
						<form action="${pageContext.request.contextPath}/schoolaffair/dormitory/dormitoryVoCheck" method="get" style="position:relative;">						  
					     <button type="submit" class="btn btn-info  a2 fa fa-plus" style="  position:absolute;right:82px;top:-58px;border-radius:0; background-color:#EFAD4D; height:30px;l">&nbsp;&nbsp;导出数据 </button>
							<div class="select_b">
								<div class="select_div">
								    <span>宿舍楼号：</span>
								    <select id="floorCode" name="floorCode" style="width:120px;" >
								    	<option value="">请选择</option>
								     <c:forEach items="${dormitory }" var="dt">
								        <option value="${dt.floorCode }">${dt.floorName }</option>
								     </c:forEach>
								    </select>
								</div>
								<div class="select_div">
								    <span>入住性别：</span>
								    <select id="sex" name="sex" style="width:120px;">
								    	<option value="">请选择</option>
								        <option value="1">男</option>
								        <option value="2">女</option>
								    </select>
								</div>
								<div class="select_div">
									<span>关键字 ：</span>
									<input type="text" id="dormitoryCode" name="dormitoryCode" style="margin-bottom: 0; padding: 6px; width: 120px;" placeholder="寝室编号" value="">
								</div>
								<button type="button" class="btn btn-primary" onclick="search()">查询</button>
								<div class="clear"></div>
							</form>
						
							<table class="responsive table table-striped" id="data-table">
								<thead>
									<tr role="row">
											<th>序号</th>
											<th>宿舍楼号</th>
											<th>寝室编号</th>
											<th>入住性别</th>
											<th>可住人数</th>
										<th class="caozuo">操作</th>
									</tr>
								</thead>
								<tbody id="dormitory_list_content">
									<jsp:include page="./list.jsp" />
								</tbody>
							</table>
							<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
								<jsp:param name="id" value="dormitory_list_content" />
								<jsp:param name="url" value="/schoolaffair/dormitory/list?sub=list&dm=${param.dm}" />
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

$(function(){
	$("#floorCode").val("${dt.floorCode}").chosen();
	$("#sex").val("").chosen();
	search();
});
	function search() {
		 var val = {
				"floorCode":$("#floorCode").val(),
				"sex":$("#sex").val(),
				"dormitoryCode":$("#dormitoryCode").val()
		}; 
		var floorCode = $("#floorCode").val();
		if (floorCode != null && floorCode != "") {
			val.floorCode = floorCode;
		}
		
		var sex = $("#sex").val();
		if (sex != null && sex != "") {
			val.sex = sex;
		}
		
		var dormitoryCode = $("#dormitoryCode").val();
		if (dormitoryCode != null && dormitoryCode != "") {
			val.dormitoryCode = dormitoryCode;
		}
		var id = "dormitory_list_content";
		var url = "/schoolaffair/dormitory/list?sub=list&dm=${param.dm}";
		myPagination(id, val, url);
	}

	 //加载下载模板对话框
	 function loadDownLoadPage(){
		 $.initWinOnTopFromTop('导出宿舍信息模板', '${pageContext.request.contextPath}/schoolaffair/dormitory/downLoadDormitoryPage', '600', '500'); 
	 }
	 
	 //加载批量上传对话框
	 function upLoadPage(){
		 $.initWinOnTopFromTop('批量导入数据', '${pageContext.request.contextPath}/schoolaffair/dormitory/upLoadDormitoryInfoPage', '800', '400'); 
	 }
	
	// 	加载创建对话框
	function loadCreatePage() {
		$.initWinOnTopFromLeft('添加宿舍', '${pageContext.request.contextPath}/schoolaffair/dormitory/creator', '600', '500');
	}
	//  加载编辑对话框
	function loadEditPage(id) {
		$.initWinOnTopFromLeft('编辑', '${pageContext.request.contextPath}/schoolaffair/dormitory/editor?id=' + id, '600', '500');
	}
	
	function loadViewerPage(id) {
		$.initWinOnTopFromLeft('查看', '${pageContext.request.contextPath}/schoolaffair/dormitory/viewer?id=' + id, '600', '500');
	}
	
	// 	删除对话框
	function deleteObj(obj, id) {
		$.confirm("确定执行此次操作？", function() {
			executeDel(obj, id);
		});
	}

	// 	执行删除
	function executeDel(obj, id) {
		$.post("${pageContext.request.contextPath}/schoolaffair/dormitory/" + id, {"_method" : "delete"}, function(data, status) {
			if ("success" === status) {
				if ("success" === data) {
					$.success("删除成功");
					$("#" + id + "_tr").remove();
				} else if ("fail" === data) {
					$.error("删除失败，系统异常", 1);
				}
			}
			window.location.reload();
		});
	}
</script>
</html>