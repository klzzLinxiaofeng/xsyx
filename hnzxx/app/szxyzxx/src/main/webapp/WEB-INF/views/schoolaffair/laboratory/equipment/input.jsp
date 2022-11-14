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
.control-group{
	float:left;
	width:50%;
}
.form-horizontal .control-label{
	width:100px;
}
.form-horizontal .controls{
	margin-left:115px;
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
					<form class="form-horizontal tan_form" id="equipment_form" action="javascript:void(0);">
							
							<div class="control-group" style="display: none;">
								<label class="control-label">
									id：
								</label>
								<div class="controls">
								<input type="text" id="id" name="id" class="span13" placeholder="" value="${equipment.id}">
								</div>
							</div>
							<div class="control-group" style="display: none;">
								<label class="control-label">
									schoolId：
								</label>
								<div class="controls">
								<input type="text" id="schoolId" name="schoolId" class="span13" placeholder="" value="${equipment.schoolId}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									<font color="red">*</font>仪器名称：
								</label>
								<div class="controls">
								<input type="text" id="name" name="name" class="span13" placeholder="" value="${equipment.name}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									仪器编码：
								</label>
								<div class="controls">
								<input type="text" id="code" name="code" class="span13" placeholder="" value="${equipment.code}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									<font color="red">*</font>实验室名称：
								</label>
								<div class="controls">
								<select id="laboratoryRoomId" name="laboratoryRoomId" class="span13"  ">
									
								</select>
								
								<%-- <input type="text" id="laboratoryRoomId" name="laboratoryRoomId" class="span13" placeholder="" value="${equipment.laboratoryRoomId}"> --%>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									实验室台号：
								</label>
								<div class="controls">
								<input type="text" id="roomPosition" name="roomPosition" class="span13" placeholder="" value="${equipment.roomPosition}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									购买时间：
								</label>
								<div class="controls">
								<input type="text" id="boughtTime" name="boughtTime"
								class="span13 {date:true}" placeholder="" 
								value="<fmt:formatDate pattern="yyyy-MM-dd" value='${equipment.boughtTime}'></fmt:formatDate>"
								onclick="WdatePicker();" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
								<%--  class="span13" placeholder="" value="${equipment.boughtTime}"> --%>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									<font color="red">*</font>仪器状态：
								</label>
								<div class="controls">
								<select id="status" name="status" class="span4 chzn-select"  ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
									<option value ="true" <c:if test="${equipment.status == true}">selected="selected"</c:if>>正常</option>
									<option value ="false" <c:if test="${equipment.status == false}">selected="selected"</c:if>>故障</option>
								</select>
								<%-- <input type="text" id="status" name="status" class="span13" placeholder="" value="${equipment.status}"> --%>
								</div>
							</div>
							<div class="control-group" style="display:none;">
								<label class="control-label">
									是否删除(0:未删除,1:已删除)：
								</label>
								<div class="controls">
								<input type="text" id="isDelete" name="isDelete" class="span13" placeholder="" value="${equipment.isDelete}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									品牌：
								</label>
								<div class="controls">
								<input type="text" id="brand" name="brand" class="span13" placeholder="" value="${equipment.brand}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									规格：
								</label>
								<div class="controls">
								<input type="text" id="specifications" name="specifications" class="span13" placeholder="" value="${equipment.specifications}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									型号：
								</label>
								<div class="controls">
								<input type="text" id="type" name="type" class="span13" placeholder="" value="${equipment.type}">
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label">
									故障原因：
								</label>
								<div class="controls">
								<input type="text" id="cause" name="cause" class="span13" placeholder="" value="${equipment.cause}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									预计修复时间：
								</label>
								<div class="controls">
								<input type="text" id="preRepariTime" name="preRepariTime" 
								class="span13 {date:true}" placeholder="" 
								value="<fmt:formatDate pattern="yyyy-MM-dd" value='${equipment.preRepariTime}'></fmt:formatDate>"
								onclick="WdatePicker();" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
								<!-- class="span13" placeholder="" value="${equipment.preRepariTime}"> -->
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									<font color="red">*</font>是否已报修：
								</label>
								<div class="controls">
								<select id="isNotice" name="isNotice" class="span4 chzn-select"  ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
									<option value="false" <c:if test="${equipment.isNotice == false}">selected="selected"</c:if>>否</option>
									<option value="true" <c:if test="${equipment.isNotice == true}">selected="selected"</c:if>>是</option>
								</select>
								<%-- <input type="text" id="isNotice" name="isNotice" class="span13" placeholder="" value="${equipment.isNotice}"> --%>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									备注：
								</label>
								<div class="controls">
								<input type="text" id="remark" name="remark" class="span13" placeholder="" value="${equipment.remark}">
								</div>
							</div>
							<div class="control-group" style="display: none;">
								<label class="control-label">
									创建时间,用户排序：
								</label>
								<div class="controls">
								<input type="text" id="createDate" name="createDate" 
								class="span13 {date:true}" placeholder="" 
								value="<fmt:formatDate pattern="yyyy-MM-dd" value='${equipment.createDate}'></fmt:formatDate>"
								onclick="WdatePicker();" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
								<%-- class="span13" placeholder="" value="${equipment.createDate}"> --%>
								</div>
							</div>
							<div class="control-group" style="display: none;">
								<label class="control-label">
									修改时间：
								</label>
								<div class="controls">
								<input type="text" id="modifyDate" name="modifyDate" 
								class="span13 {date:true}" placeholder="" 
								value="<fmt:formatDate pattern="yyyy-MM-dd" value='${equipment.modifyDate}'></fmt:formatDate>"
								onclick="WdatePicker();" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
								<%-- class="span13" placeholder="" value="${equipment.modifyDate}"> --%>
								</div>
							</div>
						<div class="form-actions tan_bottom">
							<input type="hidden" id="id" name="id" value="${equipment.id}" />
								<button class="btn btn-warning" type="button"
									onclick="saveOrUpdate();">确定</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	
	$(function(){
		var roomId = "${equipment.laboratoryRoomId}";
		var url = "${ctp}/schoolaffair/laboratoryroom/getLaboratoryRoomInfoList";
		var data = {}
		$.post(url, data, function(list){
			var obj = eval("("+list+")");
			$("#laboratoryRoomId").html("");
			for ( var i = 0; i < obj.length; i++  ) {
				var opt;
				if ( roomId != null && roomId == obj[i].id ) {
					opt = "<option value='"+obj[i].id+"' selected='selected'>"+obj[i].name+"</option>";
				} else {
					opt = "<option value='"+obj[i].id+"'>"+obj[i].name+"</option>";
				} 
				$("#laboratoryRoomId").append(opt);
			}
			 });
	});
	
	
	

	var checker;
	$(function() {
		checker = initValidator();
	});
	
	function initValidator() {
		return $("#equipment_form").validate({
			errorClass : "myerror",
			rules : {
				"name" : { required:true },
				"laboratoryRoomId":{ required:true }
			},
			messages : {
				"name" : { required:"仪器名称必填" },
				"laboratoryRoomId":{ required:"请先创建实验室" }
			}
		});
	}
	
	//保存或更新修改
	function saveOrUpdate() {
		if (checker.form()) {
			var loader = new loadLayer();
			var $id = $("#id").val();
			var $requestData = formData2JSONObj("#equipment_form");
			var url = "${ctp}/schoolaffair/equipment/creator";
			if ("" != $id) {
				$requestData._method = "put";
				url = "${ctp}/schoolaffair/equipment/" + $id;
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
	
</script>
</html>