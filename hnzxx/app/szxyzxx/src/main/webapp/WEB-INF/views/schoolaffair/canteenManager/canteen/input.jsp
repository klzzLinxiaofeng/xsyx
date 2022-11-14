<%@ page language="java"
	import="platform.education.user.contants.UserContants"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<title>添加食堂</title>
<%@ include file="/views/embedded/common.jsp"%>
<style>
.row-fluid .span13 {
	width: 75%;
}

.myerror {
	color: red !important;
	width: 34%;
	display: inline-block;
	padding-left: 10px;
}

.row-fluid .span4 {
	width: 227px;
}

.row-fluid .span2 {
	display: inline-block;
	width: 100px;
}
</style>
</head>
<body style="background-color: #F3F3F3 !important">
		<div class="row-fluid ">
			<div class="span12">
				<div style="margin-bottom: 0" class="content-widgets">
				<div style="padding: 20px 0 0;" class="widget-container">
					<form class="form-horizontal" id="canteen_form"
						action="javascript:void(0);">
						<div class="control-group">
							<label class="control-label"><font style="color: red">*</font>食堂编号</label>
							<div class="controls">
								<input type="text" id="code" name="code" class="span4"
									placeholder="请输入食堂编号, 不能为空" <c:if test="${not empty canteen.code}">disabled="disabled"</c:if> value="${canteen.code}"
									${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
							</div>
						</div>
						
						<div class="control-group">
							<label class="control-label"><font style="color: red">*</font>食堂名称</label>
							<div class="controls">
								<input type="text" id="name" name="name" class="span4"
									placeholder="请输入食堂名称, 不能为空" value="${canteen.name}"
									${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
							</div>
						</div>
						
						<div class="control-group">
							<label class="control-label"><font style="color: red">*</font>负责人</label>
							<div class="controls">
								<input type="text" id="leader" name="leader" class="span4"
									placeholder="请输入负责人, 不能为空" value="${canteen.leader}"
									${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
							</div>
						</div>
						
						<div class="control-group">
							<label class="control-label"><font style="color: red">*</font>营业时段</label>
							<div class="controls">
								<input type="text" id="runTime" name="runTime" class="span4"
									placeholder="样式：周一到周五-几点到几点" value="${canteen.runTime}"
									${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
							</div>
						</div>
						
						<div class="control-group">
								<label class="control-label"><font style="color: red">*</font>
									所属大楼
								</label>
								<div class="controls">
									<select id="floorCode" name="floorCode" class="span4 chzn-select" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
									<option value="">请选择</option>
									<c:forEach items="${floorList }" var="floor">
										<option value="${floor.code}" id="${floor.isDelete }" data-id="${floor.state }" <c:if test="${canteen.floorCode == floor.code }">selected</c:if>>${floor.name }</option>
									</c:forEach>
								</select>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><font style="color: red">*</font>
									所在楼层：
								</label>
								<div class="controls">
								<input type="hidden" id="layer" value="${empty canteen ? '' : canteen.layerNumber}">
								<select id="layerNumber" name="layerNumber" class="span4" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}></select>
								</div>
							</div>
						
						<div class="control-group">
							<label class="control-label">备注</label>
							<div class="controls">
								<textarea  id="remark" name="remark"
									class="span4" rows="3" cols="1"
									${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>${canteen.remark}</textarea>
							</div>
						</div>
						<div class="form-actions tan_bottom"
							style="padding-left: 0; background-color: #eee; text-align: center">
							<c:if test="${isCK == null || isCk == ''}">
								<input type="hidden" id="id" name="id" value="${canteen.id}" />
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
		//大楼发生改变
		$("#floorCode").change(function() {
			findStorey();
			$("#layer").val("1");
			
		});
		
		checkIsDelete();
		
		checker = initValidator();

		$(".chzn-select").chosen();
	})

	function checkIsDelete() {
		var isDelete = $("#floorCode option:selected").attr("id");
		var state = $("#floorCode option:selected").attr("data-id");
// 		alert(state)
		if(isDelete === "true"){
			$("#floorCode").attr('disabled', 'disabled');
			$("#layerNumber").attr('disabled', 'disabled');
		}else if(state === "4"){
			$("#floorCode option").each(function(index, value){
				var isDelete = $(this).attr("id");
				if(isDelete === "true"){
					$(this).remove();
				}
			});
		}else {
			$("#floorCode option").each(function(index, value){
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
		var code = $("#floorCode").val();
		var layer = $("#layer").val();
		$.post("${ctp}/schoolaffair/floor/findlayernumber?code=" + code, function(data, status) {
			if ("success" === status) {
				data = eval("(" + data + ")");
				var $length = data.responseData;
				var $layerNumber = $("#layerNumber");
				$layerNumber.find("option").remove();
				for (var i = 1; i <= $length; i++) {
					if (layer == i) {
						$layerNumber.append("<option value='" + i + "'selected='selected'>" + i + "</option>");
					} else {
						$layerNumber.append("<option value='" + i + "'>" + i + "</option>");
					}
				}
			}
		});
	}

	function initValidator() {
		return $("#canteen_form").validate(
						{
							errorClass : "myerror",
							rules : {
								"code" : {
									required : true,
									accCheck : true,
									maxlength : 30,
									remote : {
										async : false,
										type : "GET",
										url : "${pageContext.request.contextPath}/schoolaffair/canteen/checker",
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
									minlength : 1,
									maxlength : 20,
									stringCheck:true,
									remote : {
										async : false,
										type : "POST",
										url : "${pageContext.request.contextPath}/schoolaffair/canteen/nameChecker",
										data : {
											'dxlx' : 'name',
											'name' : function() {
												return $("#name").val();
											},
											'id' : function() {
												return $("#id").val();
											}
										}
									}
								},
								"leader" : {
									required : true,
									maxlength : 30
								},
								"runTime" : {
									required : true,
									maxlength : 30
								},
								"floorCode" : {
									selectNone : true
								},
								"layerNumber" : {
									digits : true
								},
								"remark" : {
									maxlength : 30
								}
							},
							messages : {
								"code" : {
									remote : "编号已存在"
								},
								"name" : {
									stringCheck:"只能输入中文字符",
									remote : "名称已存在"
								}
							}
						});
	}

	//保存或更新修改
	function saveOrUpdate() {
		if (checker.form()) {
			var $id = $("#id").val();
			var $requestData = formData2JSONObj("#canteen_form");
			var url = "${pageContext.request.contextPath}/schoolaffair/canteen/creator";
			if ("" != $id) {
				$requestData._method = "put";
				url = "${pageContext.request.contextPath}/schoolaffair/canteen/" + $id;
			}
			var loader = new loadLayer();
			loader.show();
			$.post(url, $requestData, function(data, status) {
				if ("success" === status) {
					$.success('操作成功');
					data = eval("(" + data + ")");
					if ("success" === data.info) {
						if (parent.core_iframe != null) {
							parent.core_iframe.window.location.reload();
						} else {
							parent.window.location.reload();
						}
						$.closeWindow();
					} else {
						$.error("操作失败");
					}
				} else {
					$.error("操作失败");
				}
				loader.close();
			});
		}
	}
</script>
</html>