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
input[type="radio"], input[type="checkbox"]{ margin:0 4px;margin-left:6px;}
</style>
</head>
<body style="background-color: cdd4d7 !important">
	<div class="row-fluid">
		<div class="span12">
			<div class="content-widgets" style="margin-bottom: 0">
				<div class="widget-container" style="padding: 20px 0 0;">
					<form class="form-horizontal tan_form" id="schoolcourse_form" action="javascript:void(0);">
							<div class="control-group" style="display: none;">
								<label class="control-label">
									id：
								</label>
								<div class="controls">
								<input type="text" id="id" name="id" class="span13" placeholder="" value="${schoolCourse.id}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									学校：
								</label>
								<div class="controls">
								<input style="display: none;" type="text" id="schoolId" name="schoolId" class="span13" placeholder="" value="${schoolCourse.schoolId}">
								<input style="margin-left: -20px" type="text" disabled="disabled" class="span13" placeholder="" value="${schoolCourse.schoolName}">
								<%-- <input type="text" class="span13" placeholder="" value="${schoolCourse.schoolName}"> --%>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									开放走班功能：
								</label>
								<div class="controls">
								<label style="margin-top: 5px; margin-left: -20px;">
									<input name="isOpen" type="radio" value="true" />是
									<input name="isOpen" type="radio" value="false" />否
								</label>
								<%-- <input type="text" id="isOpen" name="isOpen" class="span13" placeholder="" value="${schoolCourse.isOpen}"> --%>
								</div>
							</div>
							<%-- <div class="control-group">
								<label class="control-label">
									创建时间：
								</label>
								<div class="controls">
								<input type="text" id="createDate" name="createDate" class="span13" placeholder="" value="${schoolCourse.createDate}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									修改时间：
								</label>
								<div class="controls">
								<input type="text" id="modifyDate" name="modifyDate" class="span13" placeholder="" value="${schoolCourse.modifyDate}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									是否删除：
								</label>
								<div class="controls">
								<input type="text" id="isDeleted" name="isDeleted" class="span13" placeholder="" value="${schoolCourse.isDeleted}">
								</div>
							</div> --%>
						<div class="form-actions tan_bottom">
							<input type="hidden" id="id" name="id" value="${schoolCourse.id}" />
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
$.isOpen = function () {
	var isOpen = "${schoolCourse.isOpen}";
	if ( isOpen == "true" ) {
		$("input:radio[value='true']").attr('checked','true');
	} else {
		$("input:radio[value='false']").attr('checked','false');
	} 
}

	var checker;
	
	$(function() {
		checker = initValidator();
		$.isOpen();
		
		
	});
	
	function initValidator() {
		return $("#schoolcourse_form").validate({
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
			var $requestData = formData2JSONObj("#schoolcourse_form");
			var url = "${ctp}/bbx/schoolCourse/creator";
			if ("" != $id) {
				$requestData._method = "put";
				url = "${ctp}/bbx/schoolCourse/" + $id;
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