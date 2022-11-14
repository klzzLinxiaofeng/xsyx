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
					<form class="form-horizontal tan_form" id="teacherSalary_form" action="javascript:void(0);">
							<div class="control-group">
								<label class="control-label"><font style="color: red">*</font>
									薪资金额：
								</label>
								<div class="controls">
								<input type="text" id="salary" name="salary" class="span4" placeholder="薪资金额" value="${teacherSalary.salary}" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}><span>&nbsp;&nbsp;(元)</span>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									说明：
								</label>
								<div class="controls">
								<textarea cols="1" rows="3" id="description" name="description" class="span4" placeholder="" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>${teacherSalary.description}</textarea>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><font style="color: red">*</font>
									有效开始时间：
								</label>
								<div class="controls">
								<input type="text" id="validDate" name="validDate" class="span4" placeholder="" value='<fmt:formatDate pattern="yyyy-MM-dd" value="${teacherSalary.validDate}"></fmt:formatDate>' onclick="WdatePicker();" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
								</div>
							</div>
							
							<input type="hidden" id="teacherId" name="teacherId" value="${teacherId }"> 
						<div class="form-actions tan_bottom">
							<input type="hidden" id="id" name="id" value="${teacherSalary.id}" />
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
		checker = initValidator();
	});
	
	function initValidator() {
		return $("#teacherSalary_form").validate({
			errorClass : "myerror",
			rules : {
				"salary" : {
					required : true,
					number : true
				},
				"validDate" : {
					required : true
				},
				"description" : {
					maxlength: 200
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
			var $requestData = formData2JSONObj("#teacherSalary_form");
			var url = "${ctp}/personnel/teacherSalary/creator";
			if ("" != $id) {
				$requestData._method = "put";
				url = "${ctp}/personnel/teacherSalary/" + $id;
			}
			loader.show();
			$.post(url, $requestData, function(data, status) {
				if("success" === status) {
					$.success('操作成功');
					data = eval("(" + data + ")");
					if("success" === data.info) {
						parent.window.location.reload();
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