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

.content-widgets input[type="radio"]{
	position:relative;
	top:-1px;
	margin:0 8px;
}

</style> 
</head>
<body style="background-color: cdd4d7 !important">
	<div class="row-fluid">
		<div class="span12">
			<div class="content-widgets" style="margin-bottom: 0">
				<div class="widget-container" style="padding: 20px 0 0;">
					<form class="form-horizontal tan_form" id="studentemployment_form" action="javascript:void(0);">
							<%-- <div class="control-group">
								<label class="control-label">
									主键：
								</label>
								<div class="controls">
								<input type="text" id="id" name="id" class="span13" placeholder="" value="${studentEmployment.id}">
								</div>
							</div> --%>
							<%-- <div class="control-group">
								<label class="control-label">
									学生ID
								</label>
								<div class="controls">
								<input type="text" id="studentId" name="studentId" class="span13" placeholder="" value="${studentEmployment.studentId}">
								</div>
							</div> --%>
							<div class="control-group">
								<label class="control-label">
									学生姓名：
								</label>
								<div class="controls">
								<input type="text" id="name" name="name" class="span13" placeholder="" value="${studentEmployment.name}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									性别：
								</label>
								<%-- <div class="controls">
								<input type="text" id="sex" name="sex" class="span13" placeholder="" value="${studentEmployment.sex}">
								</div> --%>
								<div class="controls" id="radio">
									<input type="radio" id="sex" name="sex" placeholder="" value="男" ${studentEmployment.sex=='男'?'Checked':''}/>男
									<input type="radio" id="sex" name="sex" placeholder="" value="女" ${studentEmployment.sex=='女'?'Checked':''}/>女
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									入学时间：
								</label>
								<div class="controls">
								<input type="text" id="enrollDate" name="enrollDate" class="span13" placeholder="" value="${studentEmployment.enrollDate}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									学籍号：
								</label>
								<div class="controls">
								<input type="text" id="studentNumber" name="studentNumber" class="span13" placeholder="" value="${studentEmployment.studentNumber}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									联系方式：
								</label>
								<div class="controls">
								<input type="text" id="mobile" name="mobile" class="span13" placeholder="" value="${studentEmployment.mobile}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									单位名称：
								</label>
								<div class="controls">
								<input type="text" id="company" name="company" class="span13" placeholder="" value="${studentEmployment.company}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									就业地点：
								</label>
								<div class="controls">
								<input type="text" id="employAddress" name="employAddress" class="span13" placeholder="" value="${studentEmployment.employAddress}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									就业时间：
								</label>
								<div class="controls">
								<input type="text" id="employDate" name="employDate" class="span13" placeholder="" value="${studentEmployment.employDate}">
								</div>
							</div>
							
						<div class="form-actions tan_bottom">
							<input type="hidden" id="id" name="id" value="${studentEmployment.id}" />
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
	$(function() {
		checker = initValidator();
	});
	
	function initValidator() {
		return $("#studentemployment_form").validate({
			errorClass : "myerror",
			rules : {
				
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
			var $requestData = formData2JSONObj("#studentemployment_form");
			var url = "${ctp}/teach/employment/creator";
			if ("" != $id) {
				$requestData._method = "put";
				url = "${ctp}/teach/employment/" + $id;
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