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
	width: 227px;
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
					<form class="form-horizontal tan_form" id="healthMedicineInspection_form" action="javascript:void(0);">
							<div class="control-group">
								<label class="control-label"><font style="color: red">*</font>
									卫生室
								</label>
								<div class="controls">
								<select id="clinicId" name="clinicId" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""} class="chzn-select">
									<option value="">请选择</option>
									<c:forEach items="${clinicList }" var="clinic">
										<option value="${clinic.id }" id="${clinic.isDelete }" <c:if test="${healthMedicineInspection.clinicId == clinic.id }">selected</c:if>>${clinic.name }</option>
									</c:forEach>
								</select>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><font style="color: red">*</font>
									检查日期：
								</label>
								<div class="controls">
								<input type="text" id="examineDate" name="examineDate" class="span4" placeholder="" value='<fmt:formatDate pattern="yyyy-MM-dd" value="${healthMedicineInspection.examineDate}"></fmt:formatDate>' onclick="WdatePicker();" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><font style="color: red">*</font>
									药品名称：
								</label>
								<div class="controls">
								<input type="text" id="name" name="name" class="span4" placeholder="" value="${healthMedicineInspection.name}" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><font style="color: red">*</font>
									库存量：
								</label>
								<div class="controls">
								<input type="text" id="stock" name="stock" class="span4" placeholder="" value="${healthMedicineInspection.stock}" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><font style="color: red">*</font>
									单位：
								</label>
								<div class="controls">
								<select id="unit" name="unit"></select>
<%-- 								<input type="text" id="unit" name="unit" class="span4" placeholder="" value="${healthMedicineInspection.unit}" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}> --%>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><font style="color: red">*</font>
									药品状态：
								</label>
								<div class="controls">
								<select id="state" name="state"></select>
<%-- 								<input type="text" id="state" name="state" class="span4" placeholder="" value="${healthMedicineInspection.state}" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}> --%>
								</div>
							</div>
							
						<div class="form-actions tan_bottom">
							<input type="hidden" id="id" name="id" value="${healthMedicineInspection.id}" />
							<c:if test="${isCK == null || isCk == ''}">
								<button class="btn btn-warning" type="button"
									onclick="saveOrUpdate();">确定</button>
							</c:if>
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
		
		checkIsDelete();
		
		$(".chzn-select").chosen();
		
		checker = initValidator();
		
		//单位
		$.jcGcSelector("#unit", {tc : "XY-JY-YPDW"}, "${healthMedicineInspection.unit}", function() {
			$("#unit").chosen();
		});
		
		//药品状态
		$.jcGcSelector("#state", {tc : "XY-JY-YPZT"}, "${healthMedicineInspection.state}", function() {
			$("#state").chosen();
		});
		
	});
	
	function initValidator() {
		return $("#healthMedicineInspection_form").validate({
			errorClass : "myerror",
			rules : {
				"clinicId" : {
					selectNone : true
				},
				"examineDate" : {
					required : true
				},
				"name" : {
					required : true,
					maxlength : 30
				},
				"stock" : {
					required : true,
					digits : true,
					maxlength: 5
				},
				"unit" : {
					selectNone : true
				},
				"state" : {
					selectNone : true
				}
			},
			messages : {
				
			}
		});
	}
	
	function checkIsDelete() {
		var isDelete = $("#clinicId option:selected").attr("id");
		if(isDelete === "true"){
			$("#clinicId").attr('disabled', 'disabled');
		}else{
			$("#clinicId option").each(function(index, value){
				var isDelete = $(this).attr("id");
				if(isDelete === "true"){
					$(this).remove();
				}
			});
		}
	}
	
	//保存或更新修改
	function saveOrUpdate() {
		if (checker.form()) {
			var loader = new loadLayer();
			var $id = $("#id").val();
			var $requestData = formData2JSONObj("#healthMedicineInspection_form");
			var url = "${ctp}/schoolaffair/healthMedicineInspection/creator";
			if ("" != $id) {
				$requestData._method = "put";
				url = "${ctp}/schoolaffair/healthMedicineInspection/" + $id;
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