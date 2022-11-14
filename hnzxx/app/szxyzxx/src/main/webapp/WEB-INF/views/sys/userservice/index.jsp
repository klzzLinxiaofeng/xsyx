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
			<jsp:param value="fa-asterisk" name="icon"/>
			<jsp:param value="学校视频会议管理列表" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>
							学校视频会议管理列表
							<p class="btn_link" style="float: right;">
								<a href="javascript:void(0)" class="a3"
									onclick="$.refreshWin();"><i class="fa  fa-undo"></i>刷新列表</a>
									<a href="javascript:void(0)" class="a2" onclick="batchReg();"><i class="fa fa-plus"></i>批量开通服务</a>
<!-- 								<a href="javascript:void(0)" class="a2" -->
<!-- 									onclick="loadCreatePage();"><i class="fa fa-plus"></i>批量开通视频会议</a> -->
							</p>
						</h3>
					</div>
					<div class="light_grey"></div>
					<div class="content-widgets">
						<div class="widget-container">
							<div class="select_b">
								<div class="select_div">
									<span>学校名称：</span>
									<input type="text" id="name" name="name" style="margin-bottom: 0; padding: 6px; width: 120px;" placeholder="" value="">
								</div>
								<button type="button" class="btn btn-primary" onclick="search()">查询</button>
								<div class="clear"></div>
							</div>
							<table class="responsive table table-striped" id="data-table">
								<thead>
									<tr role="row">
											<th><input type="checkbox" id="checkAll"></th>
											<th>学校ID</th>
											<th>学校</th>
											<th>是否开通成功</th>
											<th>账号</th>
											<th>后缀</th>
											<th>开通时间</th>
										<th class="caozuo" style="max-width: 250px;">操作</th>
									</tr>
								</thead>
								<tbody id="userService_list_content">
									<jsp:include page="./list.jsp" />
								</tbody>
							</table>
							<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
								<jsp:param name="id" value="userService_list_content" />
								<jsp:param name="url" value="/user/service/index?sub=list&dm=${param.dm}" />
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
	function search() {
		var val = {};
		var name = $("#name").val();
		if (name != null && name != "") {
			val.name = name;
		}
		var id = "userService_list_content";
		var url = "/user/service/index?sub=list&dm=${param.dm}";
		myPagination(id, val, url);
	}
	function editSuffix(schoolId){
		$.initWinOnTopFromLeft('自定义开通', '${ctp}/user/service/editSuffix?schoolId=' + schoolId, '700', '300');
	}
	//开通视频会议服务(单例)
	function register(schoolId,schoolName,schoolEnglishName){
		var loader = new loadLayer();
		var $requestData = {};
		$requestData.schoolId = schoolId;
		$requestData.schoolEnglishName = schoolEnglishName;
		$requestData.schoolName = schoolName;
		var url = "${ctp}/user/service/creator";
		loader.show();
		$.post(url, $requestData, function(data, status) {
			if("success" === status) {
				data = eval("(" + data + ")");
				$("#" + schoolId + "_tr").find("td .btn-green").hide();
				if("success" === data.info){
					$.success('开通成功');
					var userWeb = data.responseData;
					var createDate = userWeb.createDate;
					createDate = getSmpFormatDate(createDate);
					$("#" + schoolId + "_tr").find("td").eq(3).html("已开通");
					$("#" + schoolId + "_tr").find("td").eq(3).css("color","#179F4B");
					$("#" + schoolId + "_tr").find("td").eq(3).css("color","#179F4B");
					$("#" + schoolId + "_tr").find("td").eq(4).html(userWeb.account);
					$("#" + schoolId + "_tr").find("td").eq(5).html(userWeb.ext);
					$("#" + schoolId + "_tr").find("td").eq(6).html(createDate);
				}else{
					$.error("后缀名" + schoolEnglishName + "已存在，请自定义开通！");
					$("#" + schoolId + "_tr").find("td .btn-red").show();
				}
			}else{
				$.error("该学校已开通");
			}
			loader.close();
		});
	}
	//批量开通视频会议服务
	function batchReg(){
		var selector = $("#userService_list_content input:checkbox[name='ids']:checked");
// 		$("#" + schoolId + "_tr").find("td .btn-red").show();
		var errStr = "";
		var length = selector.length;
		var flag = 0;
		var loader = new loadLayer();
		if(length > 0){
			loader.show();
			$.each(selector,function(value,index){
				flag += 1;
				var schoolId = $(index).val();
				var schoolName = $(index).attr("data-schoolName");
				var schoolEnglishName = $(index).attr("data-schoolEnglishName");
				$("#" + schoolId + "_tr").find("td .btn-green").hide();
				var $requestData = {};
				$requestData.schoolId = schoolId;
				$requestData.schoolEnglishName = schoolEnglishName;
				$requestData.schoolName = schoolName;
				var url = "${ctp}/user/service/creator";
				$.post(url, $requestData, function(data, status) {
					if("success" === status) {
						data = eval("(" + data + ")");
						if("success" === data.info){
							var userWeb = data.responseData;
							var createDate = userWeb.createDate;
							createDate = getSmpFormatDate(createDate);
							$("#" + schoolId + "_tr").find("td").eq(3).html("已开通");
							$("#" + schoolId + "_tr").find("td").eq(3).css("color","#179F4B");
							$("#" + schoolId + "_tr").find("td").eq(3).css("color","#179F4B");
							$("#" + schoolId + "_tr").find("td").eq(4).html(userWeb.account);
							$("#" + schoolId + "_tr").find("td").eq(5).html(userWeb.ext);
							$("#" + schoolId + "_tr").find("td").eq(6).html(createDate);
						}else{
							errStr = errStr + (errStr != "" ? "," : "") + "【" + schoolEnglishName + "】";
							$("#" + schoolId + "_tr").find("td .btn-red").show();
						}
						if(flag == length){
							loader.close();
							if(errStr != ""){
								$.alert("后缀名" + errStr + "已被开通过，请自定义开通！");
							}else{
								$.success("批量开通成功！");
							}
						}
					}
				});
			});
		}else{
			$.error("请至少选择一条记录后再操作");
		}
	}
	//简单的日期转换
	function getSmpFormatDate(date) { 
		var data = new Date(date);
		var year = data.getFullYear();
		var month = data.getMonth() + 1;
		if(month < 10){
			month = "0" + month;
		}
		var day = data.getDate()
		return year + "/" + month + "/" + day;
	} 
	// 	加载创建对话框
	function loadCreatePage() {
		$.initWinOnTopFromLeft('创建', '${ctp}/user/service/creator', '700', '290');
	}
	//  加载编辑对话框
	function loadEditPage(id) {
		$.initWinOnTopFromLeft('编辑', '${ctp}/user/service/editor?id=' + id, '700', '300');
	}
	
	function loadViewerPage(id) {
		$.initWinOnTopFromLeft('详情', '${ctp}/user/service/viewer?id=' + id, '700', '300');
	}
	
	// 	删除对话框
	function deleteObj(obj, id) {
		$.confirm("确定执行此次操作？", function() {
			executeDel(obj, id);
		});
	}

	// 	执行删除
	function executeDel(obj, id) {
		$.post("${ctp}/user/service/" + id, {"_method" : "delete"}, function(data, status) {
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
	$(function(){
		initCheckBtn();
	});
	function initCheckBtn() {
		$("#checkAll").on("click", function() {
			$("#userService_list_content input:checkbox[name='ids']").prop(
					"checked", this.checked);
		});
	}
	
</script>
</html>