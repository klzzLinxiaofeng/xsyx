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
					<form class="form-horizontal tan_form" id="consumable_form" action="javascript:void(0);">
							<div class="control-group" style="display: none;">
								<label class="control-label">
									id：
								</label>
								<div class="controls">
								<input type="text" id="id" name="id" class="span13" placeholder="" value="${consumable.id}">
								</div>
							</div>
							<div class="control-group" style="display:none;">
								<label class="control-label">
									schoolId：
								</label>
								<div class="controls">
								<input type="text" id="schoolId" name="schoolId" class="span13" placeholder="" value="${consumable.schoolId}">
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label">
									<font color="red">*</font>物品名称：
								</label>
								<div class="controls">
								<input type="text" id="name" name="name" class="span13" placeholder="" value="${consumable.name}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									物品编码：
								</label>
								<div class="controls">
								<input type="text" id="code" name="code" class="span13" placeholder="" value="${consumable.code}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									<font color="red">*</font>保存位置：
								</label>
								<div class="controls">
								<select id="consumablePositionId" name="consumablePositionId" class="span13"  ">
									
								</select>
								<%-- <input type="text" id="consumablePositionId" name="consumablePositionId" class="span13" placeholder="" value="${consumable.consumablePositionId}"> --%>
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label">
									生产日期：
								</label>
								<div class="controls">
								<input type="text" id="produceDate" name="produceDate" 
								class="span13 {date:true}" placeholder="" 
								value="<fmt:formatDate pattern="yyyy-MM-dd" value='${equipment.createDate}'></fmt:formatDate>"
								onclick="WdatePicker();" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
								<%-- class="span13" placeholder="" value="${consumable.produceDate}"> --%>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									型号规格：
								</label>
								<div class="controls">
								<input type="text" id="type" name="type" class="span13" placeholder="" value="${consumable.type}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									计量单位：
								</label>
								<div class="controls">
								<input type="text" id="unit" name="unit" class="span13" placeholder="" value="${consumable.unit}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									数量：
								</label>
								<div class="controls">
								<input type="text" id="quantity" name="quantity" class="span13 {digits:true}" placeholder="" value="${consumable.quantity}" onkeyup="this.value=this.value.replace(/[^\d.]/g, '')">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									单价：
								</label>
								<div class="controls">
								<input type="text" id="price" name="price" class="span13" placeholder="" value="${consumable.price}" onkeyup="this.value=this.value.replace(/[^\d.]/g, '')">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									厂家：
								</label>
								<div class="controls">
								<input type="text" id="manufacturer" name="manufacturer" class="span13" placeholder="" value="${consumable.manufacturer}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									产地：
								</label>
								<div class="controls">
								<input type="text" id="producingArea" name="producingArea" class="span13" placeholder="" value="${consumable.producingArea}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									保存年限：
								</label>
								<div class="controls">
								<input type="text" id="storageLife" name="storageLife" class="span13" placeholder="" value="${consumable.storageLife}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									备注：
								</label>
								<div class="controls">
								<input type="text" id="remark" name="remark" class="span13" placeholder="" value="${consumable.remark}">
								</div>
							</div>
							<div class="control-group" style="display: none;">
								<label class="control-label">
									位置名称：
								</label>
								<div class="controls">
								<input type="text" id="position" name="position" class="span13" placeholder="" value="${consumable.position}">
								</div>
							</div>
							<div class="control-group" style="display: none;">
								<label class="control-label">
									是否删除(0:未删除,1:已删除)：
								</label>
								<div class="controls">
								<input type="text" id="isDelete" name="isDelete" class="span13" placeholder="" value="${consumable.isDelete}">
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
								<%-- class="span13" placeholder="" value="${consumable.createDate}"> --%>
								</div>
							</div>
							<div class="control-group" style="display: none;">
								<label class="control-label">
									修改时间：
								</label>
								<div class="controls">
								<input type="text" id="modifyDate" name="modifyDate" 
								class="span13 {date:true}" placeholder="" 
								value="<fmt:formatDate pattern="yyyy-MM-dd" value='${equipment.createDate}'></fmt:formatDate>"
								onclick="WdatePicker();" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
								<%-- class="span13" placeholder="" value="${consumable.modifyDate}"> --%>
								</div>
							</div>
						<div class="form-actions tan_bottom">
							<input type="hidden" id="id" name="id" value="${consumable.id}" />
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
 		var roomId = "${consumable.consumablePositionId}"
		var url = "${ctp}/schoolaffair/laboratoryroom/getLaboratoryRoomInfoList";
		var data = {}
		$.post(url, data, function(list){
			var obj = eval("("+list+")");
			$("#consumablePositionId").html("");
			for ( var i = 0; i < obj.length; i++  ) {
				 var opt;
				 if ( roomId != null && roomId == obj[i].id ) {
					opt = "<option value='"+obj[i].id+"' selected='selected'>"+obj[i].name+"</option>";
				} else {
					opt = "<option value='"+obj[i].id+"'>"+obj[i].name+"</option>";
				}  
				 //var opt = "<option value='"+obj[i].id+"'>"+obj[i].name+"</option>";
				$("#consumablePositionId").append(opt);
			}
			 });
	}); 

	var checker;
	$(function() {
		checker = initValidator();
	});
	
	function initValidator() {
		return $("#consumable_form").validate({
			errorClass : "myerror",
			rules : {
				"name" : { required:true },
				"consumablePositionId" : {required:true}
			},
			messages : {
				"name" : { required:"物品名称必填"},
				"consumablePositionId" : {required:"请先创建实验室"}
			}
		});
	}
	
	//保存或更新修改
	function saveOrUpdate() {
		if (checker.form()) {
			var loader = new loadLayer();
			var $id = $("#id").val();
			var $requestData = formData2JSONObj("#consumable_form");
			var url = "${ctp}/schoolaffair/consumable/creator";
			if ("" != $id) {
				$requestData._method = "put";
				url = "${ctp}/schoolaffair/consumable/" + $id;
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