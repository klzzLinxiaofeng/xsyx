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
.row-fluid .span13 {
	width: 46%;

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
								<label class="control-label">房室名：</label>
								<div class="controls" style="line-height:28px;">
									<input type="text" class="roomName" id="roomName" name="roomName"/>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									房室类型：
								</label>
								<div class="controls">
								<select id="typeCode" name="typeCode" class="span13" placeholder="" value="${room.typeCode}">
								</select>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									容纳人数：
								</label>
								<div class="controls">
								<input type="text" id="galleryful" name="galleryful" class="span13" placeholder="0" value="${room.galleryful}" onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}" onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}">
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label">
									排序：
								</label>
								<div class="controls">
								<input type="text" id="reorder" name="reorder" class="span13" placeholder="0" value="${room.reorder}">
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label">
									是否可用：
								</label>
								<div class="controls">
								<label>
									<input name="isUsed" type="radio" value="1"  style="margin:0; margin-right:5px;"/><span style="line-height: 30px;">是</span>
									<input name="isUsed" type="radio" value="0"  style="margin:0 5px;"/><span style="line-height: 30px;">否</span>
								</label> 
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">起始房室号：</label>
								<div class="controls">
<!-- 									<input type="text" class="first_num" id="startNumber" name="startNumber" onblur="checkNumber();"> &nbsp; (起始的最小值为1) -->
										<input type="text" class="first_num" id="startNumber" name="startNumber"> &nbsp; (起始的最小值为1)
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">待创建房室号：</label>
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
             		<button  class="btn btn-danger" type="button" onclick="saveRoomBatch();">保存</button>
<!--              		<button  class="btn" type="button">取消</button> -->
            	</div>
            </div>
				</div>
			</div>
		</div>
	</div>
<script>
	
$.RoomType = function(selectId) {
	var selector = $(selectId);
	selector.html("");
	var initTypeCode = "${room.typeCode}";
	selector.append("<option value=''>请选择</option>");
	url = "${pageContext.request.contextPath}" + "/bbx/RoomType/getRoomTypeList";
	$.get(url,function(data){
		$.each(data, function (index, obj) {
			if ( initTypeCode == obj.code ) {
				selector.append("<option value='" + obj.code + "' selected='selected'>" + obj.name +"</option>" );
			} else {
				selector.append("<option value='" + obj.code + "'>" + obj.name +"</option>" );
			}
        });
	},"json");
	
}

$.isUsed = function () {
var isUsed = "${room.isUsed}";
if ( isUsed == "true" || isUsed == "" ) {
	$("input:radio[value='1']").attr('checked','true');
} else if ( isUsed == "false" ) {
	$("input:radio[value='0']").attr('checked','false');
} 
}

$(function() {
	$.RoomType("#typeCode");
	$.isUsed();
});
	
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
			$("#all_class").append('<a href="javascript:void(0)" class="num on"><span>'+k+'</span></a>');
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
	
	function saveRoomBatch() {
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
		var typeCode = $("#typeCode").val();
		str = $.trim(str);
		str = str.substring(0,str.length-1);
		var galleryful = $("#galleryful").val();
		var name = $("#roomName").val();
		var reorder = $("#reorder").val();
		var isUsed = $('input:radio:checked').val();
		var url = "${pageContext.request.contextPath}/bbx/Room/addRoomBatch";
		var $requestData = {};
		$requestData.name = name;
		$requestData.galleryful = galleryful;
		$requestData.str = str;
		$requestData.reorder = reorder;
		$requestData.typeCode = typeCode;
		$requestData.isUsed = isUsed;
		loader.show();
		$.post(url, $requestData, function(data, status){
			if ("success" === status) {
				$.success('保存成功');
				data = eval("(" + data + ")");
				if ("success" === data.info) {
					if (parent.core_iframe != null) {
						parent.core_iframe.window.location.reload();
					} else {
						parent.window.location.reload();
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
