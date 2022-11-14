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
					<form class="form-horizontal tan_form" id="laboratoryroom_form" action="javascript:void(0);">
							<div class="control-group" style="display: none;">
								<label class="control-label">
									实验室id：
								</label>
								<div class="controls">
								<input type="text" id="id" name="id" class="span13" placeholder="" value="${laboratoryroom.id}">
								</div>
							</div>
							<div class="control-group" style="display: none;">
								<label class="control-label">
									schoolId：
								</label>
								<div class="controls">
								<input type="text" id="schoolId" name="schoolId" class="span13" placeholder="" value="${laboratoryroom.schoolId}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									<font color="red">*</font>实验室名称：
								</label>
								<div class="controls">
								<input type="text" id="name" name="name" class="span13" placeholder="" value="${laboratoryroom.name}" onblur="validateName()">
								<span id="spa" style="color: red"/>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									实验室编码：
								</label>
								<div class="controls">
								<input type="text" id="code" name="code" class="span13" placeholder="" value="${laboratoryroom.code}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									<font color="red">*</font>实验室状态：
								</label>
								<div class="controls">
								<select id="status" name="status" class="span4 chzn-select"  ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
									<option value ="true" <c:if test="${laboratoryroom.status == true}">selected="selected"</c:if>>正常</option>
									<option value ="false" <c:if test="${laboratoryroom.status == false}">selected="selected"</c:if>>故障</option>
								</select>
								<%-- <input type="text" id="status" name="status" class="span13" placeholder="" value="${laboratoryroom.status}"> --%>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									<font color="red">*</font>实验室地址：
								</label>
								<div class="controls">
								<select id="address" name="address" class="span13" class="span4 chzn-select"  ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
									
								</select>
								<%-- <input type="text" id="address" name="address" class="span13" placeholder="" value="${laboratoryroom.address}"> --%>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									容纳人数：
								</label>
								<div class="controls">
								<input type="text" id="capacity" name="capacity" class="span13 {digits:true}" placeholder="" value="${laboratoryroom.capacity}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									容纳组数：
								</label>
								<div class="controls">
								<input type="text" id="capacityTeam" name="capacityTeam" class="span13 {digits:true}" placeholder="" value="${laboratoryroom.capacityTeam}" >
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									占用面积(平方米)：
								</label>
								<div class="controls">
								<input type="text" id="square" name="square" class="span13" placeholder="" value="${laboratoryroom.square}" onkeyup="this.value=this.value.replace(/[^\d.]/g, '')">
								<!-- onkeyup="this.value=this.value.replace(/\D/g, '')" -->
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									实验室面向课程：
								</label>
								<div class="controls">
								<input type="text" id="courseName" name="courseName" class="span13" placeholder="" value="${laboratoryroom.courseName}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									故障原因：
								</label>
								<div class="controls">
								<input type="text" id="troubleCause" name="troubleCause" class="span13" placeholder="" value="${laboratoryroom.troubleCause}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									<font color="red">*</font>是否报修：
								</label>
								<div class="controls">
								<select id="isNotice" name="isNotice" class="span4 chzn-select"  ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
									<option value="false" <c:if test="${laboratoryroom.isNotice == false}">selected="selected"</c:if>>否</option>
									<option value="true" <c:if test="${laboratoryroom.isNotice == true}">selected="selected"</c:if>>是</option>
								</select>
								<%-- <input type="text" id="isNotice" name="isNotice" class="span13" placeholder="" value="${laboratoryroom.isNotice}"> --%>
								</div>
							</div>
							<%-- <div class="control-group">
								<label class="control-label">
									<span class="red">*</span>日期
								</label>
								<div class="controls">
								<input type="text" id="createDate" name="createDate" class="span4" placeholder="请输入时间" value="${laboratoryroom.createDate}" onclick="WdatePicker();" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
								</div>
							</div>  --%>
							<div class="control-group">
								<label class="control-label">
									预计修复时间：
								</label>
								<div class="controls">
								<input type="text" id="preRepairTime" name="preRepairTime" class="span13 {date:true}" 
								placeholder="请输入时间" value="<fmt:formatDate pattern="yyyy-MM-dd" value='${laboratoryroom.preRepairTime}'></fmt:formatDate>"
								onclick="WdatePicker();" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
								</div>
							</div>
							<div class="control-group" style="display: none;">
								<label class="control-label">
									创建时间,用户排序：
								</label>
								<div class="controls">
								<input type="text" id="createDate" name="createDate" class="span13 {date:true}" placeholder="" 
								value="<fmt:formatDate pattern="yyyy-MM-dd" value='${laboratoryroom.createDate}'></fmt:formatDate>"
								onclick="WdatePicker();" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
								</div>
							</div>
							<div class="control-group" style="display: none;">
								<label class="control-label">
									修改时间：
								</label>
								<div class="controls">
								<input type="text" id="modifyDate" name="modifyDate" class="span13 {date:true}" placeholder="" 
								value="<fmt:formatDate pattern="yyyy-MM-dd" value='${laboratoryroom.modifyDate}'></fmt:formatDate>"
								onclick="WdatePicker();" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
								</div>
							</div>
							<%-- <input type="text" id="operationName" name="operationName" style="display: none;" value="${operationName}" > --%>
						<div class="form-actions tan_bottom">
							<input type="hidden" id="id" name="id" value="${laboratoryroom.id}" />
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
	var initName = "${laboratoryroom.name}";
	var isPermit = true;
	function validateName() {
		var name = $("#name").val();
		$("#spa").empty();
		 if ( initName != name ) {
			/* $.ajax({
			   type: "GET",
			   contentType:"application/x-www-form-urlencoded:charset=UTF-8",
			   url: "${ctp}/schoolaffair/laboratoryroom/validateName",
			   data: {
					name:name
			   },
			   success: function(msg){
		   		  if ( msg == "true" ) {
				  	$("#spa").html("");
				  	$("#spa").append("名称已存在");
				  	isPermit = false;
				  }  
			   }
			});  */
			var url = "${ctp}/schoolaffair/laboratoryroom/validateName";
			$.post(url, {name:name}, function(data){  
				  if ( data == "true" ) {
				  	$("#spa").html("");
				  	$("#spa").append("名称已存在");
				  	isPermit = false;
				  } else {
					  isPermit = true;
				  }
				}); 
		} else {
			isPermit = true;
		}
		
	}

	var checker;
	$(function() {
		$.HqFloorSelector({
			   "selector" : "#address",
			   "selectedVal":"${laboratoryroom.address }",
			   "afterHandler" : function() {
				}
		   });
		checker = initValidator();
	});
	
	function initValidator() {
		return $("#laboratoryroom_form").validate({
			errorClass : "myerror",
			rules : {
				"name":{
					required:true,
					/* remote : {
						async : false,
						type : "GET",
						url : "${ctp}/schoolaffair/laboratoryroom/validateName",
						data : {
							'schoolId' : $("#schoolId").val(),
							'name' : $("#name").val()
						}
					} */
				},
				"address":{
					required:true,
				},
				
			},
			messages : {
				"name":{
					required:"实验室名称必填"
				},
				"address":{
					required:"实验室地址必选"
				}
			}
		});
	}
	
	//保存或更新修改
	function saveOrUpdate() {
		if ( isPermit==true ) {
			if (checker.form()) {
				var loader = new loadLayer();
				var $id = $("#id").val();
				var $requestData = formData2JSONObj("#laboratoryroom_form");
				var url = "${ctp}/schoolaffair/laboratoryroom/creator";
				if ("" != $id) {
					$requestData._method = "put";
					url = "${ctp}/schoolaffair/laboratoryroom/" + $id;
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
	}
	
</script>
</html>