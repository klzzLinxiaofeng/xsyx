<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/views/embedded/common.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>批量创建班级</title>
<style type="text/css">

</style>
</head>
<body style="background-color:cdd4d7 !important">
	<div class="row-fluid">
		<div class="span12">
			<div class="content-widgets" style="margin-bottom:0">
				<div class="widget-container" style="padding:20px 0 0;">
					<div class="widget-container">
						<form class="form-horizontal">
							<div class="control-group">
								<label class="control-label">年级：</label>
								<div class="controls" style="line-height:28px;">
									${grade.name }
									<input type="hidden" id="gradeId" name="gradeId" value="${grade.id }">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">起始班号：</label>
								<div class="controls">
									<input type="text" class="first_num" id="startNumber" name="startNumber" onblur="checkNumber();"> &nbsp; (起始班号的最小值为1)
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">待创建班级：</label>
								<div class="controls add_class">
									<div class="all_class" id="all_class">
										<a href="javascript:void(0)" class="plus" ></a>
									</div>
								</div>
							</div>
						</form>
					</div>
					<div class="form-actions tan_bottom" style="background-color:#297657;">
            	<div style="text-align:center;">
             		<button  class="btn btn-danger" type="button" onclick="saveTeamBatch();">保存</button>
<!--              		<button  class="btn" type="button">取消</button> -->
            	</div>
            </div>
				</div>
			</div>
		</div>
	</div>
	<script>
	$(function(){
		$(".add_class").on("click",".num",function(){
			$(this).toggleClass("on");
		});
		$(".add_class .plus").click(function(){
			var startNumber = $("#startNumber").val();
			if(startNumber == null || startNumber == "" || startNumber == undefined) {
				$.alert("请先输入起始班号！");
				return;
			}
			startNumber = $.trim(startNumber);
			var reg = /^\+?[1-9]\d*$/;
			if(isNaN(startNumber) || !reg.test(startNumber)) {
				$.alert("请输入整数！");
				return;
			}
			
			var i=$(".first_num").val();
			var j=$(".num").length;
			var k=parseInt(i)+parseInt(j);
			$("#all_class").append('<a href="javascript:void(0)" class="num on"><span>'+k+'</span>班</a>');
		});
		
		$(".first_num").change( function() {
			var startNumber = $("#startNumber").val();
			startNumber = $.trim(startNumber);
			var reg = /^\+?[1-9]\d*$/;
			if(isNaN(startNumber) || !reg.test(startNumber)) {
				$.alert("请输入整数！");
				return;
			}
			var i=$(".first_num").val();
			var j=$(".num").length;
			var k=parseInt(i)+parseInt(j);
			$(".all_class .num").remove();
			for(var l=i;l<k;l++){
				$(".all_class").append('<a href="javascript:void(0)" class="num on"><span>'+l+'</span>班</a>');
			}
			});
	});
   		
// 	function checkNumber() {
// 		var startNumber = $("#startNumber").val();
// 		startNumber = $.trim(startNumber);
// 		var reg = /^\+?[1-9]\d*$/;
// 		if(isNaN(startNumber) || !reg.test(startNumber)) {
// 			$.alert("请输入整数！");
// 		}
// 		if(startNumber < 1) {
// // 			$.alert("起始班号的最小值为1!");
// 			$("#startNumber").val("");
// 		}
// 	}
	
	function saveTeamBatch() {
		var loader = new loadLayer();
// 		var arr = [];
		var str = "";
		$("#all_class").find("a[class='num on']").each(function(index, element) {
			var text = $(this).text();
			str = str + text + ";";
// 			arr.push(text);
		});
		if(str.length == 0) {
			$.alert("请选择待创建班级！");
			return;
		}
		str = $.trim(str);
		str = str.substring(0,str.length-1);
		var gradeId = $("#gradeId").val();
		var url = "${pageContext.request.contextPath}/teach/team/addTeamBatch";
		loader.show();
		$.post(url, {"gradeId" : gradeId, "str" : str}, function(data, status){
			if ("success" === status) {
				$.success('保存成功');
				data = eval("(" + data + ")");
				if ("success" === data.info) {
					if (parent.core_iframe != null) {
						parent.core_iframe.window.ajaxFunction(gradeId, null);
					} else {
						parent.window.ajaxFunction(gradeId, null);
					}
					$.closeWindow();
				} else {
					$.closeWindow();
				}
			} else {
				$.error("保存失败");
			}
			loader.close();
		});
		
	}
	
	</script>
</body>
</html>
