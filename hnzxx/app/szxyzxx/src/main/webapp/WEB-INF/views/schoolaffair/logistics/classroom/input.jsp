<%@ page language="java"
	import="platform.education.user.contants.UserContants"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<title>菜单创建</title>
<%@ include file="/views/embedded/common.jsp"%>
<style>
.row-fluid .span13 {
	width: 75%;
}
.chzn-container{
	float:left;
	margin-right:5px;
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
					<form class="form-horizontal" id="classroom_form"
						action="javascript:void(0);">
						<div class="control-group">
							<label class="control-label"><font style="color: red">*</font>编号</label>
							<div class="controls">
								<input type="text" id="code" name="code" class="span4"
									placeholder="请输入教室编号, 不能为空" <c:if test="${not empty classroom.code}">disabled="disabled"</c:if> value="${classroom.code}"
									${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label"><font style="color: red">*</font>名称</label>
							<div class="controls">
								<input type="text" id="name" name="name" class="span4"
									placeholder="请输入教室名称, 不能为空" value="${classroom.name}"
									${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label"><font style="color: red">*</font>类型</label>
							<div class="controls">
								<select class="span4" id="type" name="type"
									${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
								</select>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label"><font style="color: red">*</font>位置</label>
							<div class="controls">
<%-- 								<select id="floorId" name="floorId" class="span2" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}></select> --%>
								<select id="floorId" name="floorId" class="span2 chzn-select" 
									${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
									<option value="">请选择</option>
									<c:forEach items="${floorList }" var="floor">
										<option value="${floor.id }" id="${floor.isDelete }" data-id="${floor.state }" <c:if test="${classroom.floorId == floor.id }">selected</c:if>>${floor.name }</option>
									</c:forEach>
								</select> 
								<input type="hidden" id="layer" value="${empty classroom ? '' : classroom.storey}">
								<select id="storey" name="storey" class="span2" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}></select>
								<input type="text" id="position" name="position" class="span2"
									placeholder="请输入具体位置" value="${classroom.position}"
									${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">容纳人数</label>
							<div class="controls">
								<input type="text" id="capacity" name="capacity" class="span4"
									value="${classroom.capacity}"
									${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">网络点数</label>
							<div class="controls">
								<input type="text" id="networkPoints" name="networkPoints"
									class="span4" value="${classroom.networkPoints}"
									${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">电话点数</label>
							<div class="controls">
								<input type="text" id="phonePoints" name="phonePoints"
									class="span4" value="${classroom.phonePoints}"
									${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">电话号码</label>
							<div class="controls">
								<input type="text" id="telephone" name="telephone" class="span4"
									value="${classroom.telephone}"
									${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">考试人数</label>
							<div class="controls">
								<input type="text" id="examineeCount" name="examineeCount"
									class="span4" value="${classroom.examineeCount}"
									${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label"><font style="color: red">*</font>状态</label>
							<div class="controls">
								<select class="span4" id="state" name="state"
									${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
									<option value="0"
										<c:if test="${classroom.state == 0 }">selected</c:if>>是</option>
									<option value="1"
										<c:if test="${classroom.state == 1 }">selected</c:if>>否</option>
								</select>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">备注</label>
							<div class="controls">
								<textarea  id="description" name="description"
									class="span4" rows="3" cols="1"
									${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>${classroom.description}</textarea>
							</div>
						</div>
						<div class="form-actions tan_bottom"
							style="padding-left: 0; background-color: #eee; text-align: center">
							<c:if test="${isCK == null || isCk == ''}">
								<input type="hidden" id="id" name="id" value="${classroom.id}" />
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
	       
// 		$.FloorSelector({
// 			   "selector" : "#floorId",
// 			   "selectedVal" : "${classroom.floorId}",
// 			   "afterHandler" : function() {
				  
// 				}
// 		   });
		
		findStorey();
		$("#floorId").change(function() {
			findStorey();
			$("#layer").val("1");
		});
		
		checkIsDelete();
		
		 $(".chzn-select").chosen();
		
		checker = initValidator();

		$.jcGcSelector("#type", {"tc" : "JY-JSLX"}, '${classroom.type}');
		
	})
	

	function checkIsDelete() {
		var isDelete = $("#floorId option:selected").attr("id");
// 		alert(isDelete)
		var state = $("#floorId option:selected").attr("data-id");
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
	
// 	function checkIsDelete() {
// 		var isDelete = $("#floorId option:selected").attr("id");
// 		if(isDelete === "true"){
// 			$("#floorId").attr('disabled', 'disabled');
// 			$("#storey").attr('disabled', 'disabled');
// 			$("#position").attr('disabled', 'disabled');
// 		}else{
// 			$("#floorId option").each(function(index, value){
// 				var isDelete = $(this).attr("id");
// 				if(isDelete === "true"){
// 					$(this).remove();
// 				}
// 			});
// 		}
// 	}
	
	//查询该大楼的层数
	function findStorey() {
		var id = $("#floorId").val();
		var layer = $("#layer").val();
		$.post("${pageContext.request.contextPath}/schoolaffair/classroom/findStorey?id=" + id, function(data, status) {
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
		return $("#classroom_form").validate(
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
										url : "${pageContext.request.contextPath}/schoolaffair/classroom/checker",
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
									maxlength : 30,
									remote : {
										async : false,
										type : "POST",
										url : "${pageContext.request.contextPath}/schoolaffair/classroom/classNameChecker",
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
								"floorId" : {
									selectNone : true
								},
								"position" : {
									maxlength : 50
								},
								"capacity" : {
									digits : true
								},
								"networkPoints" : {
									digits : true
								},
								"phonePoints" : {
									digits : true
								},
								"telephone" : {
									isTel : true
								},
								"examineeCount" : {
									digits : true
								},
								"description" : {
									maxlength : 100
								},
								"storey" : {
									selectNone : true
								},
								"state" : {
									selectNone : true
								},
								"type" : {
									selectNone : true
								}
							},
							messages : {
								"code" : {
									remote : "编号已存在"
								},
								"name" : {
									remote : "教室名称已存在"
								}
							}
						});
	}

	//保存或更新修改
	function saveOrUpdate() {
		if (checker.form()) {
			var $id = $("#id").val();
			var $requestData = formData2JSONObj("#classroom_form");
			var url = "${pageContext.request.contextPath}/schoolaffair/classroom/creator";
			if ("" != $id) {
				$requestData._method = "put";
				url = "${pageContext.request.contextPath}/schoolaffair/classroom/" + $id;
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