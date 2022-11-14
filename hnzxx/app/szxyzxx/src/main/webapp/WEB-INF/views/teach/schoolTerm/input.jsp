<%@ page language="java"
	import="platform.education.user.contants.UserContants"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<title>学期管理</title>
<%@ include file="/views/embedded/common.jsp"%>
	<script type="text/javascript" defer="defer" src="${ctp}/res/plugin/My97DatePicker/WdatePicker.js"></script>
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
<body style="background-color:cdd4d7 !important">
	<div class="row-fluid">
		<div class="span12">
			<div class="content-widgets"  style="margin-bottom:0">
				<div class="widget-container" style="padding:20px 0 0;">
					<input type="hidden" id="schoolId" name="schoolId"
						value="${schoolId}" />
					<form class="form-horizontal" id="schoolTerm_form"
						action="javascript:void(0);">
						 <div class="control-group">
							<label class="control-label"><font style="color: red">*</font>所属学年</label>
							<div class="controls">
								<select id="schoolYear" name="schoolYear" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}></select>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label"><font style="color: red">*</font>国标学期名称</label>
							<div class="controls">
								<select class="span4" id="gbCode" name="gbCode"
									onchange="setCode();"
									${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
								</select>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label"><font style="color: red">*</font>校内学期名称</label>
							<div class="controls">
								<input type="text" id="name" name="name" class="span4"
									placeholder="请输入学期名称, 如秋季学期" value="${schoolTerm.name}"
									${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
							</div>
						</div>
						
						<div class="control-group">
							<label class="control-label"><font style="color: red">*</font>开始时间</label>
							<div class="controls">
								<input type="text" id="beginDate" name="beginDate" class="span4"
									placeholder="请输入开始时间 如2014-09-01"
									value='<fmt:formatDate pattern="yyyy-MM-dd" value="${schoolTerm.beginDate}"></fmt:formatDate>'
									onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'finishDate\')}'})"
									${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label"><font style="color: red">*</font>结束时间</label>
							<div class="controls">
								<input type="text" id="finishDate" name="finishDate"
									class="span4" placeholder="请输入结束时间 如2015-07-01"
									value='<fmt:formatDate pattern="yyyy-MM-dd" value="${schoolTerm.finishDate}"></fmt:formatDate>'
									onFocus="WdatePicker({minDate:'#F{$dp.$D(\'beginDate\')}'})"
									${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
							</div>
						</div>
						
						<div class="control-group">
							<div class="controls">
								<input type="hidden" readOnly="readonly" id="code" name="code"
									class="span4" placeholder="唯一标识符, 如02-2014-1"
									value="${schoolTerm.code}">
							</div>
						</div>
						
						<div class="form-actions tan_bottom"
							style="padding-left: 0; background-color: #eee; text-align: center">
							<c:if test="${isCK == null || isCk == ''}">
								<input type="hidden" id="id" name="id" value="${schoolTerm.id}" />
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
		$.SchoolYearSelector({
			"selector" : "#schoolYear",
			"afterHandler" : function() {
				
			}
		});
		$("#gbCode").on("change", function() {
			var $gbCode = $("#gbCode").val();
			var text = $("#gbCode option:selected").text();
			if($gbCode != "" && $gbCode != null){
				$("#name").val(text);
			}
			$("#code").focus();
		});
		$("#schoolYear").on("change", function() {
			setCode();
			$("#code").focus();
		});
		$.jcGcSelector("#gbCode", {
			"tc" : "XY-JY-XQ"
		}, '${schoolTerm.gbCode}');
	});

	function setCode() {
		var $gbCode = $("#gbCode").val();
		var $schoolYear = $("#schoolYear").val();
		if("" != $schoolYear && null != $schoolYear){
			var $code = $("#schoolId").val() + "-" + $("#schoolYear").val();
			if ("" != $gbCode && null != $gbCode) {
				$code = $code + "-" + $gbCode;
			}
		}
		$("#code").val($code);
	}

	function initValidator() {
		return $("#schoolTerm_form")
				.validate(
						{
							errorClass : "myerror",
							rules : {
// 								"code" : {
// 									required : true,
// 									remote : {
// 										async : false,
// 										type : "GET",
// 										url : "${pageContext.request.contextPath}/teach/schoolTerm/checker",
// 										data : {
// 											'dxlx' : 'code',
// 											'code' : function() {
// 												return $("#code").val();
// 											},
// 											'id' : function() {
// 												return $("#id").val();
// 											}
// 										}
// 									}
// 								},
								"name" : {
									required : true,
									maxlength : 30
								},
								"schoolYear" : {
									selectNone : true
								},
								"gbCode" : {
									selectNone : true
								},
								"beginDate" : {
									required : true
								},
								"finishDate" : {
									required : true
								}
							},
							messages : {
// 								"code" : {
// 									remote : "该识别代码已经存在"
// 								}
							}
						});
	}

	//保存或更新修改
	function saveOrUpdate() {
		if (checker.form()) {
			var loader = new loadLayer();
			var $id = $("#id").val();
			var $requestData = formData2JSONObj("#schoolTerm_form");
			var url = "${pageContext.request.contextPath}/teach/schoolTerm/creator";
			if ("" != $id) {
				$requestData._method = "put";
				url = "${pageContext.request.contextPath}/teach/schoolTerm/" + $id;
			}
			loader.show();
			$.post(url, $requestData, function(data, status) {
				if ("success" === status) {
					$.success('保存成功');
					data = eval("(" + data + ")");
					if ("success" === data.info) {
						if (parent.core_iframe != null) {
							parent.core_iframe.window.location.reload();
						} else {
							parent.window.location.reload();
						}
						$.closeWindow();
					}else if("fail" === data.info){
						$.error("已经存在相同的学期记录，不允许再添加");
					} else {

					}
				} else {
					$.error("保存失败");
				}
				loader.close();
			});
		}
	}
</script>
</html>