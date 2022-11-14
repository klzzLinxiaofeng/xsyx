<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<title></title>
<%@ include file="/views/embedded/common.jsp"%>
<style>
.row-fluid .span13 {
	width: 75%;
}

.row-fluid .span4 {
	width: 75%;
}

.myerror {
	color: red !important;
	width: 22%;
	display: inline-block;
	padding-left: 10px;
}
</style>
</head>
<body style="background-color: cdd4d7 !important">
	<div class="row-fluid">
		<div class="span12">
			<div class="content-widgets" style="margin-bottom: 0">
				<div class="widget-container" style="padding: 20px 0 0;">
					<form class="form-horizontal tan_form" id="room_form" action="javascript:void(0);">
							
							<div class="control-group">
								<label class="control-label">
									房间编号：
								</label>
								<div class="controls">
								<input type="text" id="code" name="code" class="span13" placeholder="" value="${room.code}">
								<span class="red">*</span>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									建筑物名称：
								</label>
								<div class="controls">
								<input type="text" id="name" name="name" class="span13" placeholder="" value="${room.name}">
								<span class="red">*</span>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									房间名称：
								</label>
								<div class="controls">
								<input type="text" id="roomName" name="roomName" class="span13" placeholder="" value="${room.roomName}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									房间用途：
								</label>
								<div class="controls">
								<select name="roomUse">
									<c:choose>
										<c:when test="${room.roomUse == 1 }">
										<option value="1" selected="selected">教室</option>
										<option value="2">实验室</option>
										<option value="3">物理实验室</option>
										<option value="4">生物实验室</option>
										<option value="99">其他实验室</option>
										</c:when>
										<c:when test="${room.roomUse == 2 }">
										<option value="1">教室</option>
										<option value="2" selected="selected">实验室</option>
										<option value="3">物理实验室</option>
										<option value="4">生物实验室</option>
										<option value="99">其他实验室</option>
										</c:when>
										<c:when test="${room.roomUse == 3 }">
										<option value="1">教室</option>
										<option value="2">实验室</option>
										<option value="3" selected="selected">物理实验室</option>
										<option value="4">生物实验室</option>
										<option value="99">其他实验室</option>
										</c:when>
										<c:when test="${room.roomUse == 4 }">
										<option value="1">教室</option>
										<option value="2">实验室</option>
										<option value="3" >物理实验室</option>
										<option value="4" selected="selected">生物实验室</option>
										<option value="99">其他实验室</option>
										</c:when>
										
										<c:when test="${room.roomUse == 99 }">
										<option value="1">教室</option>
										<option value="2">实验室</option>
										<option value="3" >物理实验室</option>
										<option value="4" >生物实验室</option>
										<option value="99" selected="selected">其他实验室</option>
										</c:when>
										<c:otherwise>
										<option value="1">教室</option>
										<option value="2">实验室</option>
										<option value="3" >物理实验室</option>
										<option value="4" >生物实验室</option>
										<option value="99">其他实验室</option>
										</c:otherwise>
									</c:choose>
								</select>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									房间所处楼层：
								</label>
								<div class="controls" style="width: 160px">
								<input type="text" id="roomFloor" name="roomFloor" class="span13" placeholder="" value="${room.roomFloor}">
								<strong>层</strong>
								<span class="red">*</span>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									房间建筑面积：
								</label>
								<div class="controls" style="width: 240px"> 
								<input type="text" id="roomArea" name="roomArea" class="span13" placeholder="" value="${room.roomArea}">
								<strong>平方米</strong>
								<span class="red">*</span>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									房间使用面积：
								</label>
								<div class="controls" style="width: 240px">
								<input type="text" id="roomUseArea" name="roomUseArea" class="span13" placeholder="" value="${room.roomUseArea}">
								<strong>平方米</strong>
								<span class="red">*</span>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									备注：
								</label>
								<div class="controls">
								<textarea rows="5" cols="5" id="remark" name="remark" class="span13">${room.remark}</textarea>
								</div>
							</div>
							
						<div class="form-actions tan_bottom">
							<input type="hidden" id="id" name="id" value="${room.id}" />
								<button class="btn btn-warning" type="button"
									onclick="saveOrUpdate();">保存</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	var checker;
	$(function() {
		checker = initValidator();
	});
	
	function initValidator() {
		return $("#room_form").validate({
			errorClass : "myerror",
			rules : {
				
			},
			messages : {
				
			}
		});
	}
	
	//保存或更新修改
	function saveOrUpdate() {
		if (checker.form()) {
			
			var code = $("#code").val();
			if(code == null || code == ''){
				alert("房间编号不能为空");
				return false ;
			}
			
			var name = $("#name").val();
			if(name == null || name == ''){
				alert("建筑物名称不能为空");
				return false ;
			}
			
			var roomFloor = $("#roomFloor").val();
			if (!checkNumber(roomFloor)) {
				  alert("房间所处楼层，请输入正确数字");
				  return false;
			}
			var roomUseArea = $("#roomUseArea").val();
			if (!checkNumber(roomUseArea)) {
				  alert("房间使用面积，请输入正确数字");
				  return false;
			}
			
			var roomArea = $("#roomArea").val();
			if (!checkNumber(roomArea)) {
				  alert("房间建筑面积，请输入正确数字");
				  return false;
			}
			
			
			var loader = new loadLayer();
			var $id = $("#id").val();
			var $requestData = formData2JSONObj("#room_form");
			var url = "${ctp}/schoolaffair/room/creator";
			if ("" != $id) {
				$requestData._method = "put";
				url = "${ctp}/schoolaffair/room/" + $id;
			}
			loader.show();
			$.post(url, $requestData, function(data, status) {
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
	}
	
	//验证字符串是否是数字
	function checkNumber(theObj) {
	  var reg = /^[0-9]+.?[0-9]*$/;
	  if (reg.test(theObj)) {
	    return true;
	  }
	  return false;
	}
</script>
</html>