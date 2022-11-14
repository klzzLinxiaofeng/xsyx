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
					<form class="form-horizontal tan_form" id="apsmoralevaluationitem_form" action="javascript:void(0);">
							
							<div class="control-group">
								<label class="control-label">
									社团名称：
								</label>
								<div class="controls">
								<input type="text" id="name" name="name" readonly="readonly" class="span13" placeholder="" value="${apsMoralEvaluationItem.name}">
								<span class="red">*</span>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									社团组织分类：
								</label>
								<div class="controls">
								<input type="text" id="classification" readonly="readonly" name="classification" class="span13" placeholder="" value="${apsMoralEvaluationItem.classification}">
								<span class="red">*</span>
								</div>
							</div>
							<div class="control-group">
							<label class="control-label">
									备注：
							</label>
								<div class="controls">
									<textarea cols="5" readonly="readonly" rows="5" name="remark" class="span13">${apsMoralEvaluationItem.remark}</textarea>
								</div>
							</div>
							
					</form>
				</div>
			</div>
		</div>
	</div>
</body>

</html>