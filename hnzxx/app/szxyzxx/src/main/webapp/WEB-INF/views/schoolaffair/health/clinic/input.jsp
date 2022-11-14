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
					<form class="form-horizontal tan_form" id="healthClinic_form" action="javascript:void(0);">
							<div class="control-group">
								<label class="control-label"><font style="color: red">*</font>
									卫生室编号
								</label>
								<div class="controls">
								<input type="text" id="code" name="code" class="span4" placeholder="" value="${healthClinic.code}" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><font style="color: red">*</font>
									卫生室名称
								</label>
								<div class="controls">
								<input type="text" id="name" name="name" class="span4" placeholder="" value="${healthClinic.name}" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><font style="color: red">*</font>
									负责人
								</label>
								<div class="controls">
								<input type="text" id="principal" name="principal" class="span4" placeholder="" value="${healthClinic.principal}" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><font style="color: red">*</font>
									所属大楼
								</label>
								<div class="controls">
									<select id="floorId" name="floorId" class="span4 chzn-select" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
									<option value="">请选择</option>
									<c:forEach items="${floorList }" var="floor">
										<option value="${floor.id }" id="${floor.isDelete }" data-id="${floor.state }" <c:if test="${healthClinic.floorId == floor.id }">selected</c:if>>${floor.name }</option>
									</c:forEach>
								</select>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><font style="color: red">*</font>
									所在楼层：
								</label>
								<div class="controls">
								<input type="hidden" id="layer" value="${empty healthClinic ? '' : healthClinic.storey}">
								<select id="storey" name="storey" class="span4" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}></select>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><font style="color: red">*</font>
									所在房间：
								</label>
								<div class="controls">
								<input type="text" id="room" name="room" class="span4" placeholder="" value="${healthClinic.room}" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									备注：
								</label>
								<div class="controls">
								<textarea  id="remark" name="remark"
									class="span4" rows="3" cols="1"
									${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>${healthClinic.remark}</textarea>
								</div>
							</div>
							
						<div class="form-actions tan_bottom">
							<input type="hidden" id="id" name="id" value="${healthClinic.id}" />
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
		
		findStorey();
		$("#floorId").change(function() {
			findStorey();
			$("#layer").val("1");
		});
		
		checkIsDelete();
		
		$(".chzn-select").chosen();
		
		checker = initValidator();
	});
	
	function checkIsDelete() {
		var isDelete = $("#floorId option:selected").attr("id");
		var state = $("#floorId option:selected").attr("data-id");
// 		alert(state)
		if(isDelete === "true"){
			$("#floorId").attr('disabled', 'disabled');
			$("#storey").attr('disabled', 'disabled');
			$("#position").attr('disabled', 'disabled');
		}else if(state === "4"){
			$("#floorId option").each(function(index, value){
				var isDelete = $(this).attr("id");
				if(isDelete === "true"){
					$(this).remove();
				}
			});
		}else {
			$("#floorId option").each(function(index, value){
				var isDelete = $(this).attr("id");
				var state = $(this).attr("data-id");
				if(isDelete === "true"){
					$(this).remove();
				}
				if(state === "4") {
					$(this).remove();
				}
			});
		}
	}
	
	//查询该大楼的层数
	function findStorey() {
		var id = $("#floorId").val();
		var layer = $("#layer").val();
		$.post("${ctp}/schoolaffair/healthClinic/findStorey?id=" + id, function(data, status) {
			if ("success" === status) {
				data = eval("(" + data + ")");
				var $length = data.responseData;
				var $storey = $("#storey");
				$storey.find("option").remove();
				for (var i = 1; i <= $length; i++) {
					if (layer == i) {
						$storey.append("<option value='" + i + "'selected='selected'>" + i + "</option>");
					} else {
						$storey.append("<option value='" + i + "'>" + i + "</option>");
					}
				}
			}
		});
	}
	
	function initValidator() {
		return $("#healthClinic_form").validate({
			errorClass : "myerror",
			rules : {
				"code" : {
					required : true,
					accCheck : true,
					maxlength : 30,
					remote : {
						asnyc : false,
						type : "GET",
						url : "${ctp}/schoolaffair/healthClinic/checker",
						data : {
							'dxlx' : 'code',
							'code' : function() {
								return $("#code").val();
							},
							'id' : function() {
								return $("#id").val();
							}
						}
					}
				},
				"name" : {
					required : true,
					maxlength : 30
				},
				"principal" : {
					required : true,
					maxlength : 30
				},
				"floorId" : {
					selectNone : true
				},
				"storey" : {
					selectNone : true
				},
				"room" : {
					required : true,
					maxlength : 30
				},
				"remark" : {
					maxlength : 200
				}
			},
			messages : {
				"code" : {
					remote : "编号已存在"
				}
			}
		});
	}
	
	//保存或更新修改
	function saveOrUpdate() {
		if (checker.form()) {
			var loader = new loadLayer();
			var $id = $("#id").val();
			var $requestData = formData2JSONObj("#healthClinic_form");
			var url = "${ctp}/schoolaffair/healthClinic/creator";
			if ("" != $id) {
				$requestData._method = "put";
				url = "${ctp}/schoolaffair/healthClinic/" + $id;
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