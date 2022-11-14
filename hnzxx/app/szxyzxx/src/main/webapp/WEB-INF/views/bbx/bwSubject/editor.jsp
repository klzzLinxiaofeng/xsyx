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
<body style="background-color: #ffffff !important">
	<div class="row-fluid">
		<div class="span12">
			<div class="content-widgets" style="margin-bottom: 0">
				<div class="widget-container" style="padding: 20px 0 0;">
					<input type="hidden" id="gradeId" name="gradeId" value="${gradeId}" />
					<form class="form-horizontal tan_form" id="bwSubject_form" action="javascript:void(0);">
							<div class="control-group">
								<label class="control-label">
									科目：
								</label>
								<div class="controls">
									<span>${bwSubject.subjectName }</span>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									每周学时：
								</label>
								<div class="controls">
								<input type="text" id="hoursPerWeek" name="hoursPerWeek" class="span13" placeholder="" value="${bwSubject.hoursPerWeek}">
								</div>
							</div>
						<div class="form-actions tan_bottom">
							<input type="hidden" id="id" name="id" value="${bwSubject.id}" />
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
		return $("#bwSubject_form").validate({
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
			var hoursPerWeek = $("#hoursPerWeek").val();
			var $requestData = {};
			$requestData.hoursPerWeek = hoursPerWeek;
			$requestData._method = "put";
			url = "${ctp}/bbx/bwSubject/" + $id;
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