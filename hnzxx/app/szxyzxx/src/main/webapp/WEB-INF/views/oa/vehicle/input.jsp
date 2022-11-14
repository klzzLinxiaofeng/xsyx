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
					<input type="hidden" id="isCK" value="${isCK}"/>
					<form class="form-horizontal tan_form" id="vehicle_form" action="javascript:void(0);">
<!-- 							<div class="control-group"> -->
<!-- 								<label class="control-label"> -->
<!-- 									<span style="color: red;">*</span>房间名称： -->
<!-- 								</label> -->
<!-- 								<div class="controls"> -->
<%-- 								<input type="text" id="name" name="name" class="span13" placeholder="" value="${vehicle.name}"> --%>
<!-- 								</div> -->
<!-- 							</div> -->
							<div class="control-group">
								<label class="control-label">
									<span style="color: red;">*</span>车牌号码：
								</label>
								<div class="controls">
								<input type="text" id="plateNumber" name="plateNumber" class="span13" placeholder="" value="${vehicle.plateNumber}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									<span style="color: red;">*</span>汽车型号：
								</label>
								<div class="controls">
								<input type="text" id="model" name="model" class="span13" placeholder="" value="${vehicle.model}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									<span style="color: red;">*</span>车架号：
								</label>
								<div class="controls">
								<input type="text" id="frameNumber" name="frameNumber" class="span13" placeholder="" value="${vehicle.frameNumber}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									<span style="color: red;">*</span>发动机号：
								</label>
								<div class="controls">
								<input type="text" id="engineNumber" name="engineNumber" class="span13" placeholder="" value="${vehicle.engineNumber}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									<span style="color: red;">*</span>购置日期：
								</label>
								<div class="controls">
								<input type="text" id="purchaseDate" name="purchaseDate" class="span13" placeholder="" 
									value='<fmt:formatDate pattern="yyyy-MM-dd" value="${vehicle.purchaseDate}"></fmt:formatDate>' onclick="WdatePicker();">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									<span style="color: red;">*</span>车辆类别：
								</label>
								<div class="controls">
								<select id="vehicleType" name="vehicleType" style="width: 380px;"></select>
								</div>
							</div>
							<div class="control-group" style="display: none;">
								<label class="control-label">
									使用状况：
								</label>
								<div class="controls">
								<select id="serviceCondition" name="serviceCondition" style="width: 380px;" disabled="disabled" >
									<option value="0" <c:if test="${vehicle.serviceCondition=='0'}">selected="selected"</c:if> >空闲</option>
									<option value="1" <c:if test="${vehicle.serviceCondition=='1'}">selected="selected"</c:if> >使用中</option>
								</select>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									备注：
								</label>
								<div class="controls">
								<textarea style="width: 380px;" id="remark" name="remark">${vehicle.remark}</textarea>
								</div>
							</div>
						<div class="form-actions tan_bottom">
							<input type="hidden" id="id" name="id" value="${vehicle.id}" />
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
	var checker;
	var isCK = $("#isCK").val();
	if(isCK!=""){
		$(".controls").disable();
		$(".form-actions").hide();
	}
	$(function() {
		checker = initValidator();
		$.jcGcSelector("#vehicleType", {"tc" : "XY-JY-CLLX"}, "${vehicle.vehicleType}", function(selector) {
			selector.chosen();
		});
	});
	
	function initValidator() {
		return $("#vehicle_form").validate({
			errorClass : "myerror",
			rules : {
// 				"name" : {
// 					required : true,
// 					maxlength : 50
// 				},
				"plateNumber" : {
					required : true,
					maxlength : 20
				},
				"model" : {
					required : true,
					maxlength : 10
				},
				"frameNumber" : {
					required : true,
					maxlength : 5
				},
				"engineNumber" : {
					required : true,
					maxlength : 20
				},
				"purchaseDate" : {
					required : true
				},
				"vehicleType" : {
					required : true
				},
				"remark" : {
					maxlength : 200
				},
				"serviceCondition" : {
					required : true
				}
			},
			messages : {
				
			}
		});
	}
	
	//保存或更新修改
	function saveOrUpdate() {
		if (checker.form()) {
			var loader = new loadLayer();
			var $id = $("#id").val();
			var $requestData = formData2JSONObj("#vehicle_form");
			var url = "${ctp}/oa/vehicle/creator";
			if ("" != $id) {
				$requestData._method = "put";
				url = "${ctp}/oa/vehicle/" + $id;
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
					}else if("fail" === data.info){
						$.alert("车牌号已存在，请重新输入");
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