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
	.close_1{
	color:#333;
	position:relative;
	font-size:18px;
	font-weight:bold;
	cursor:pointer;
	top: -5px;
	margin-right: 10px;
}
.cancelBubble{
	border: 1px solid #999;
	padding: 3px;
	border-radius: 5px;
	margin-right:10px;
	margin-bottom:10px;
	display:inline-block;
}
</style>
</head>
<body>
	<div class="container-fluid">
<%-- 		<jsp:include page="/views/embedded/navigation.jsp"> --%>
<%-- 			<jsp:param value="fa-asterisk" name="icon"/> --%>
<%-- 			<jsp:param value="热门微课" name="title" /> --%>
<%-- 			<jsp:param value="${param.dm}" name="menuId" /> --%>
<%-- 		</jsp:include> --%>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
<!-- 						<h3> -->
<!-- 							微课星列表 -->
						<div class="widget-container">
							<div class="select_div">
								<label class="control-label">已选微课：</label>
								<div id="batch_data" class="controls">
								</div>
							</div>
<!-- 						</h3> -->
						</div>
					</div>
					<div class="light_grey"></div>
					<div class="content-widgets">
						<div class="widget-container">
							<div class="select_b">
								<div class="select_div">
									<span>标题：</span>
									<input type="text" id="title" name="title" style="margin-bottom: 0; padding: 6px; width: 120px;" placeholder="" value="">
								</div>
								<button type="button" class="btn btn-primary" onclick="search()">查询</button>
								<div class="clear"></div>
							</div>
							<table class="responsive table table-striped" id="data-table">
								<thead>
									<tr role="row">
											<th style="text-align:center"><input type="checkbox" id="checkAll"/></th>
											<th>微课标题</th>
											<th>时长</th>
											<th>作者</th>
											<th>创建时间</th>
										<th style="max-width: 250px;">操作</th>
									</tr>
								</thead>
								<tbody id="microLessonHot_list_content">
									<jsp:include page="./input_list.jsp" />
								</tbody>
							</table>
							<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
								<jsp:param name="id" value="microLessonHot_list_content" />
								<jsp:param name="url" value="/wkx/microlessonhot/create?sub=list&dm=${param.dm}" />
								<jsp:param name="pageSize" value="${page.pageSize}" />
							</jsp:include>
							<div class="clear"></div>
						</div>
					</div>
					
				</div>
			</div>
			
		</div>
	</div>
	<div class="form-actions tan_bottom" style="">
						
							<button class="btn btn-warning" type="button" onclick="batchAdd();">批量添加成员</button>
						
						<button class="btn" type="button" onclick="$.closeWindow();">取消</button>
					</div>
</body>
<script type="text/javascript">
	function search() {
		var val = {};
		var title = $("#title").val();
		if (title != null && title != "") {
			val.title = title;
		}
		var id = "microLessonHot_list_content";
		var url = "/wkx/microlessonhot/create?sub=list&dm=${param.dm}";
		myPagination(id, val, url);
	}

	// 	加载创建对话框
	function loadCreatePage() {
		$.initWinOnTopFromLeft('创建', '${ctp}/wkx/microlessonhot/creator', '700', '390');
	}
	//  加载编辑对话框
	function loadEditPage(id) {
		$.initWinOnTopFromLeft('编辑', '${ctp}/wkx/microlessonhot/editor?id=' + id, '700', '390');
	}
	
	function loadViewerPage(id) {
		$.initWinOnTopFromLeft('详情', '${ctp}/wkx/microlessonhot/viewer?id=' + id, '700', '300');
	}
	function selectValue(id){
		var loader = new loadLayer();
		var url = "${ctp}/wkx/microlessonhot/creator";
		loader.show();
		$.post(url,{"lessonId" : id},function(data,status){
			if("success" === status) {
				$.success('操作成功');
				data = eval("(" + data + ")");
				if("success" === data.info) {
					if(parent.core_iframe != null) {
							parent.core_iframe.window.location.reload();
						} else {
							parent.window.location.reload();
						}
					$.closeWindow();
				} else {
					$.error("操作失败");
				}
			}else{
				$.error("操作失败");
			}
			loader.close();
		});
	}
	function batchAdd() {
		var loader = new loadLayer();
		var checkedInput = $("#batch_data .cancelBubble");
		if(checkedInput.length <= 0) {
			$.error("您还未选择教师");
			return;
		}
		
		var ids = "";
		$.each(checkedInput, function(index, value) {
			if(index == 0){
				ids = $(value).find("a").attr("data-id")
			}else{
				ids = ids + "," + $(value).find("a").attr("data-id");
			}
		});
		var url = "${ctp}/wkx/microlessonhot/batchCreate";
		loader.show();
		$.post(url,{"lessonIds" : ids},function(data,status){
			if("success" === status) {
				$.success('操作成功');
				data = eval("(" + data + ")");
				if("success" === data.info) {
					if(parent.core_iframe != null) {
							parent.core_iframe.window.location.reload();
						} else {
							parent.window.location.reload();
						}
					$.closeWindow();
				} else {
					$.error("操作失败");
				}
			}else{
				$.error("操作失败");
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

	// 	执行删除
	function executeDel(obj, id) {
		$.post("${ctp}/wkx/microlessonhot/" + id, {"_method" : "delete"}, function(data, status) {
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
		$("#microLessonHot_list_content").on("click","input",function(){
			var $value = $(this).val();
			var length = $("#cancelBubble_" + $value).length;
			$("#clear_button").remove();
			if(length <= 0){
				$("#batch_data").append("<span class='cancelBubble' id='cancelBubble_" + $value + "'></span>");
				$("#cancelBubble_" + $value).append("<span style='color:#2A7658;'>" + $(this).attr("data-name") + "</span>");
				$("#cancelBubble_" + $value).append("<a class='close_1' data-id='"+ $value + "' onclick='move(this)'>x</a>");
			}else{
				$("#cancelBubble_" + $value).remove();
			}
			$("#batch_data").append('<button id="clear_button" class="btn btn-danger" onclick="batchClear();">清除</button>');
			if(length > 0){
				$("#clear_button").remove();
			}
			
		});
		$(".cancelBubble").bind("click",function(){
			return false;
		});
		initCheckBtn();
	});
	function move(obj){
		$(obj).parent().remove();
		var length = $(".cancelBubble").length;
		if(length <= 0){
			$("#clear_button").remove();
		}
		$("input:checkbox[value='" + $(obj).attr("data-id") + "']").prop({checked:false});
	}
	function batchClear(){
		$("#batch_data").empty();
		$("#microLessonHot_list_content").find("input:checkbox:checked").prop({checked:false});
		$("#checkAll").prop({checked:false});
		$("#clear_button").remove();
// 		$("#batch_data").append('<button id="clear_button" class="btn btn-danger" onclick="batchClear();">清除</button>');
	}
	function initCheckBtn() {
		$("#data-table").on("click", "#checkAll", function() {
			if(this.checked){
				$("#microLessonHot_list_content tr td").find("input:checkbox:checked").click();		
			}
			$("#microLessonHot_list_content tr td input").click();
			$("#microLessonHot_list_content tr td input:checkbox[name='ids']").prop("checked", this.checked);
			if(!this.checked){
//					$("#batch_data").empty();
				$("#microLessonHot_list_content tr td").click();
				$("#microLessonHot_list_content tr td input:checkbox[name='ids']").prop("checked", this.checked);
				$("#clear_button").hide();
			}
		});
	}
</script>
</html>