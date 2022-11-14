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
					<form class="form-horizontal tan_form" id="teachertitle_form" action="javascript:void(0);">
							<div class="control-group">
								<label class="control-label">
									<font color="red">*</font>评定对象：
								</label>
								<div class="controls">
								<input type="text" id="teacherId" name="teacherId" class="span13" data-id="${teacherTitle.teacherId}"
									placeholder="" value="${teacherTitle.teacherName}"  ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									<font color="red">*</font>批准人：
								</label>
								<div class="controls">
								<input type="text" id="approver" name="approver" class="span13 {stringCheck:true}" placeholder="" value="${teacherTitle.approver}" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									<font color="red">*</font>获取职称：
								</label>
								<div class="controls">
									<select id="titleType" name="titleType" class="span13" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}></select>
<!-- 								<input type="text" id="titleType" name="titleType" class="span13" -->
<%-- 									placeholder="" value="${teachertitle.titleType}"> --%>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									<font color="red">*</font>获取方式：
								</label>
								<div class="controls">
									<select id="acquireType" name="acquireType" class="span13" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}></select>
<!-- 									<input type="text" id="acquireType" name="acquireType" class="span13" -->
<%-- 										placeholder="" value="${teachertitle.acquireType}"> --%>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									<font color="red">*</font>申报时间：
								</label>
								<div class="controls">
								<input type="text" id="declareDate" name="declareDate" end-id="acquireDate" class="span13" placeholder="" 
									value='<fmt:formatDate pattern="yyyy-MM-dd" value="${teacherTitle.declareDate}"></fmt:formatDate>' 
									onclick="WdatePicker();" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									<font color="red">*</font>获取时间：
								</label>
								<div class="controls">
								<input type="text" id="acquireDate" name="acquireDate" class="span13" placeholder="" 
									value='<fmt:formatDate pattern="yyyy-MM-dd" value="${teacherTitle.acquireDate}"></fmt:formatDate>'
									onclick="WdatePicker();" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									详情：
								</label>
								<div class="controls">
								<input type="text" id="description" name="description" class="span13"
									placeholder="" value="${teacherTitle.description}" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
								</div>
							</div>
						<div class="form-actions tan_bottom">
							<input type="hidden" id="id" name="id" value="${teacherTitle.id}" />
								<c:if test="${isCK == null || isCK == '' }">
									<button class="btn btn-warning" type="button" onclick="saveOrUpdate();">确定</button>
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
		$.createMemberSelector({
			"inputIdSelector" : "#teacherId",
			"isOnTopWindow" : true,
			"enableBatch" : false 
		});
		
		$.jcGcSelector("#titleType", {"tc" : "JY-ZYJSZW", "level" : "1"}, "${teacherTitle.titleType}", function(selector) {
			selector.chosen();
		});
		
		$.jcGcSelector("#acquireType", {"tc" : "XY-JY-ZCQDFS"}, "${teacherTitle.acquireType}", function(selector) {
			selector.chosen();
		});
	});
	
	function initValidator() {
		return $("#teachertitle_form").validate({
			errorClass : "myerror",
			rules : {
				"teacherId" : {
					required : true
				},
				"approver" : {
					required : true,
					stringCheck : true
				},
				"titleType" : {
					required : true
				},
				"acquireType" : {
					required : true
				},
				"declareDate" : {
					required : true,
					lessThanEndDate : true
				},
				"acquireDate" : {
					required : true
				},
			},
			messages : {
				"declareDate" : {
					lessThanEndDate : "申报时间要在获取时间之前"
				}
			}
		});
	}
	
	//保存或更新修改
	function saveOrUpdate() {
		if (checker.form()) {
			var loader = new loadLayer();
			var $id = $("#id").val();
			var $requestData = formData2JSONObj("#teachertitle_form");
			var url = "${ctp}/personnel/title/creator";
			if ("" != $id) {
				$requestData._method = "put";
				url = "${ctp}/personnel/title/" + $id;
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