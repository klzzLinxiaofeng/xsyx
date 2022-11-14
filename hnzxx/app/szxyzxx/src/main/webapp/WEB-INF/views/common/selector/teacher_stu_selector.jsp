<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/css/extra/add.css" rel="stylesheet">
<style>
.form-horizontal .control-label{width:60px;}
.form-horizontal .controls{margin-left:80px;}
.form-horizontal.tan_form {
padding-bottom: 100px;
}
.close_1{
	color:#C5C5C5;
	position:absolute;
	font-size:12px;
	font-weight:bold;
	cursor:pointer;
	top:5px;
	right:20px;
}
.cancelBubble{
	border: 1px solid #DFDFDF;
	padding:0 20px;
	border-radius: 15px;
	margin-right:20px;
	margin-bottom:10px;
	display:inline-block;
	width:138px;
	height:24px;
	line-height:24px;
	position:relative;
}
.cancelBubble span{
	display:block;
	width:125px;
	text-overflow: ellipsis;
    white-space: nowrap;
    overflow: hidden;
    display: block;
    word-wrap:normal;
}
#clear_button{position:relative;top:-10px;}
.table th,.table td{padding-left:15px;}
</style>
</head>
<body style="background-color:cdd4d7 !important">
	<div class="row-fluid" >
		<div class="span12">
			<div class="content-widgets" style="margin-bottom:0">
				<div class="widget-container" style="padding:10px 0 0;">
					<form class="form-horizontal tan_form" id="role_form" action="javascript:void(0);">
						<div class="control-group">
							<label class="control-label">姓名：</label>
							<div class="controls">
								<input type="text" id="name" name="name" class="span3" placeholder="请输入姓名关键字" value="" style="width:151px;">
								<button class="btn btn-warning" onclick="search();">搜索</button>
<!-- 								<span class="red">请输入姓名关键字</span> -->
							</div>
						</div>
						<c:if test='${param.enableBatch}'>
							<div class="control-group" style="border-bottom:1px solid #999;padding-bottom:10px;margin-bottom:10px;">
								<label class="control-label">已选：</label>
								<div id="batch_data" class="controls">
<!-- 									<button id="clear_button" class="btn btn-danger" onclick="batchClear();">清除</button> -->
								</div>
							</div>
						</c:if>
						<div class="select_view" id="teacher_list">
							
							
						</div>
						<div class="form-actions tan_bottom" style="">
							<c:if test='${param.enableBatch}'>
								<button class="btn btn-warning" type="button" onclick="batchAdd();">批量添加成员</button>
							</c:if>
							<button class="btn" type="button" onclick="$.closeWindow();">取消</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
		var isTop = "${param.isTop}";
		$(function() {
			initCheckBtn();
			$(".select_view").on("click", ".add-btn", function() {
				var $this = $(this);
				var id = $this.attr("data-id");
				var name = $this.attr("data-name");
				var ids = new Array();
				var names = new Array();
				ids.push(id);
				names.push(name);
				var data = {"ids" : ids, "names" : names, "windowName" : window.name, "btnId" : "${param.id}", "frameIndex" : parent.layer.getFrameIndex(window.name), "targetWindowName" : "${param.targetWN}"};
				var isSuc;
				if("true" === isTop) {
					var $ifm = $("#core_iframe", window.parent.document);
					if($ifm.length > 0) {
						var ifm = $ifm.get(0);
						isSuc =ifm.contentWindow.selectedStuHandler(data);
						$.closeWindow();
					}
				} else {
					isSuc = window.parent.selectedStuHandler(data);
					$.closeWindow();
				}
				if(isSuc) {
					$.success("设置成功");
				}
			});
		});
		
		function batchAdd() {
// 			var checkedInput = $("#ryxx_trs").find("input:checkbox:checked");
			var checkedInput = $("#batch_data .cancelBubble");
			if(checkedInput.length <= 0) {
				$.error("您还未选择学生");
				return;
			}
			var ids = new Array();
			var names = new Array();
			$.each(checkedInput, function(index, value) {
				ids.push($(value).find("a").attr("data-id"));
				names.push($(value).find("span").text());
			});
			var data = {"ids" : ids, "names" : names, "windowName" : window.name, "btnId" : "${param.id}", "frameIndex" : parent.layer.getFrameIndex(window.name), "targetWindowName" : "${param.targetWN}"};
			var isSuc;
			if("true" === isTop) {
				var $ifm = $("#core_iframe", window.parent.document);
				if($ifm.length > 0) {
					var ifm = $ifm.get(0);
					isSuc =ifm.contentWindow.selectedStuHandler(data);
					$.closeWindow();
				}
			} else {
				isSuc = window.parent.selectedStuHandler(data);
				$.closeWindow();
			}
			if(isSuc) {
				$.success("设置成功");
			}			
		}
		
		function search() {
			var name = $("#name").val();
			$("#teacher_list").load("${ctp}/teach/student/list", {"name" : name, "type" : "0","usePage":true,"teacher":"0"}, function() {
						
			});
		}
		
		function initCheckBtn() {
			$(".select_view").on("click", "#checkAll", function() {
				if(this.checked){
					$("#ryxx_trs tr td").find("input:checkbox:checked").click();		
				}
				$("#teacher_list #ryxx_trs tr td input").click();
				$("#ryxx_trs tr td input:checkbox[name='ids']").prop("checked", this.checked);
				if(!this.checked){
// 					$("#batch_data").empty();
					$("#teacher_list #ryxx_trs tr td").click();
					$("#ryxx_trs tr td input:checkbox[name='ids']").prop("checked", this.checked);
				}
			});
		}
		
	</script>
</html>