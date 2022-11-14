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

.myerror {
	color: red !important;
	width: 34%;
	display: inline-block;
	padding-left: 10px;
}

.row-fluid .span4 {
	width: 227px;
}

</style>
</head>
<body style="background-color: #F3F3F3 !important">
		<div class="row-fluid ">
			<div class="span12">
				<div style="margin-bottom: 0" class="content-widgets">
				<div style="padding: 20px 0 0;" class="widget-container">
					<form class="form-horizontal" id="floor_form"
						action="javascript:void(0);">
						<div class="control-group">
							<label class="control-label"><font style="color: red">*</font>编号</label>
							<div class="controls">
								<input type="text" id="code" name="code" class="span4"
									placeholder="请输入楼层编号, 不能为空" <c:if test="${not empty floor.code}">disabled="disabled"</c:if> value="${floor.code}"
									${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label"><font style="color: red">*</font>名称</label>
							<div class="controls">
								<input type="text" id="name" name="name" class="span4"
									placeholder="请输入楼层名称, 不能为空" value="${floor.name}"
									${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label"><font style="color: red">*</font>位置</label>
							<div class="controls">
								<input type="text" id="position" name="position" class="span4"
									placeholder="请输入楼层 具体位置, 不能为空" value="${floor.position}"
									${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label"><font style="color: red">*</font>层数</label>
							<div class="controls">
								<input type="text" id="layerNumber" name="layerNumber"
									class="span4" value="${floor.layerNumber}"
									${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label"><font style="color: red">*</font>状态</label>
							<div class="controls">
								<select class="span4" id="state" name="state"
									${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
								</select>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label"><font style="color: red">*</font>用途</label>
							<div class="controls">
								<select class="span4" id="type" name="type"
									${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
								</select>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">描述</label>
							<div class="controls">
								<textarea id="description" name="description"
									class="span4" rows="3" cols="1"
									${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>${floor.description}</textarea>
							</div>
						</div>
						<div class="form-actions tan_bottom"
							style="padding-left: 0; background-color: #eee; text-align: center">
							<c:if test="${isCK == null || isCk == ''}">
								<input type="hidden" id="id" name="id" value="${floor.id}" />
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
		checker = initValidator();
		$.jcGcSelector("#type", {"tc" : "JY-JZWYT"}, '${floor.type}', function() {});
		$.jcGcSelector("#state", {"tc" : "JY-FJSYZT"}, '${floor.state}', function() {});
	});

	function initValidator() {
		return $("#floor_form").validate({
			errorClass : "myerror",
			rules : {
				"code" : {
					required : true,
					accCheck : true,
					maxlength : 30,
					remote : {
						async : false,
						type : "GET",
						url : "${pageContext.request.contextPath}/schoolaffair/floor/checker",
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
						url : "${pageContext.request.contextPath}/schoolaffair/floor/nameChecker",
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
				"position" : {
					required : true,
					maxlength : 50
				},
				"description" : {
					maxlength : 100
				},
				"layerNumber" : {
					required : true,
					digits : true
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
					remote : "名称已存在"
				}
			}
		});
	}

	//保存或更新修改
	function saveOrUpdate() {
		if (checker.form()) {
			var $id = $("#id").val();
			var $requestData = formData2JSONObj("#floor_form");
			var url = "${pageContext.request.contextPath}/schoolaffair/floor/creator";
			if ("" != $id) {
				$requestData._method = "put";
				url = "${pageContext.request.contextPath}/schoolaffair/floor/" + $id;
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