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
.control-group{
	float:left;
	width:50%;
}
</style>
</head>
<body style="background-color: cdd4d7 !important">
	<div class="row-fluid">
		<div class="span12">
			<div class="content-widgets" style="margin-bottom: 0">
				<div class="widget-container" style="padding: 20px 0 0;">
					<form class="form-horizontal tan_form" id="healthDiseaseReport_form" action="javascript:void(0);">
							<div class="control-group">
								<label class="control-label"><font style="color: red">*</font>
									报告时间：
								</label>
								<div class="controls">
								<input type="text" id="reportDate" name="reportDate" class="span4" placeholder="" value='<fmt:formatDate pattern="yyyy-MM-dd" value="${healthDiseaseReport.reportDate}"></fmt:formatDate>' onFocus="WdatePicker({minDate:'#F{$dp.$D(\'beginDate\')}', maxDate:new Date()})" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><font style="color: red">*</font>
									上报单位：
								</label>
								<div class="controls">
								<input type="text" id="reportUnit" name="reportUnit" class="span4" placeholder="" value="${healthDiseaseReport.reportUnit}" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><font style="color: red">*</font>
									上报电话：
								</label>
								<div class="controls">
								<input type="text" id="reportPhone" name="reportPhone" class="span4" placeholder="" value="${healthDiseaseReport.reportPhone}" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><font style="color: red">*</font>
									接报人：
								</label>
								<div class="controls">
								<input type="text" id="reportPerson" name="reportPerson" class="span4" placeholder="" value="${healthDiseaseReport.reportPerson}" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><font style="color: red">*</font>
									病名：
								</label>
								<div class="controls">
								<input type="text" id="disease" name="disease" class="span4" placeholder="" value="${healthDiseaseReport.disease}" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><font style="color: red">*</font>
									发病地点：
								</label>
								<div class="controls">
								<input type="text" id="attackSite" name="attackSite" class="span4" placeholder="" value="${healthDiseaseReport.attackSite}" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><font style="color: red">*</font>
									发病人数：
								</label>
								<div class="controls">
								<input type="text" id="attackNumber" name="attackNumber" class="span4" placeholder="" value="${healthDiseaseReport.attackNumber}" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><font style="color: red">*</font>
									首例时间：
								</label>
								<div class="controls">
								<input type="text" id="beginDate" name="beginDate" class="span4" placeholder="" value='<fmt:formatDate pattern="yyyy-MM-dd" value="${healthDiseaseReport.beginDate}"></fmt:formatDate>' onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'reportDate\')}'})" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><font style="color: red">*</font>
									主要症状：
								</label>
								<div class="controls">
								<input type="text" id="symptom" name="symptom" class="span4" placeholder="" value="${healthDiseaseReport.symptom}" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><font style="color: red">*</font>
									疫情处理：
								</label>
								<div class="controls">
								<input type="text" id="handle" name="handle" class="span4" placeholder="" value="${healthDiseaseReport.handle}" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><font style="color: red">*</font>
									报告人：
								</label>
								<div class="controls">
								<input type="text" id="reporter" name="reporter" class="span4" placeholder="" value="${healthDiseaseReport.reporter}" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									备注：
								</label>
								<div class="controls">
								<textarea  id="remark" name="remark"
									class="span4" rows="3" cols="1"
									${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>${healthDiseaseReport.remark}</textarea>
								</div>
							</div>
							
						<div class="form-actions tan_bottom">
							<input type="hidden" id="id" name="id" value="${healthDiseaseReport.id}" />
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
		return $("#healthDiseaseReport_form").validate({
			errorClass : "myerror",
			rules : {
				"reportDate" : {
					required : true
				},
				"reportUnit" : {
					required : true,
					maxlength : 30
				},
				"reportPhone" : {
					required : true,
					isTel : true
				},
				"reportPerson" : {
					required : true,
					maxlength : 30
				},
				"disease" : {
					required : true,
					maxlength : 30
				},
				"attackSite" : {
					required : true,
					maxlength : 30
				},
				"attackNumber" : {
					required : true,
					digits : true,
					maxlength : 10
				},
				"beginDate" : {
					required : true
				},
				"symptom" : {
					required : true,
					maxlength : 30
				},
				"handle" : {
					required : true,
					maxlength : 50
				},
				"reporter" : {
					required : true,
					maxlength : 30
				},
				"remark" : {
					maxlength : 200
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
			var $requestData = formData2JSONObj("#healthDiseaseReport_form");
			var url = "${ctp}/schoolaffair/healthDiseaseReport/creator";
			if ("" != $id) {
				$requestData._method = "put";
				url = "${ctp}/schoolaffair/healthDiseaseReport/" + $id;
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