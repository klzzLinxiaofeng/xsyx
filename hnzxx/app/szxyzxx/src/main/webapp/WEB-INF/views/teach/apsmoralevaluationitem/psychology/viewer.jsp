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
							<input type="hidden" name="type" value="4">
							<div class="control-group">
								<label class="control-label">
									心理健康教师名称：
								</label>
								<div class="controls">
								<input type="text" id="name" readonly="readonly" name="name" class="span13" placeholder="" value="${apsMoralEvaluationItem.name}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									是否专职教师：
								</label>
								<div class="controls">
								<select name="isFullTeach" disabled="disabled">
									<c:choose>
										<c:when test="${apsMoralEvaluationItem.isFullTeach==0 }">
											<option value="0">否</option>
										</c:when>
										<c:otherwise>
											<option value="1">是</option>
										</c:otherwise>
									</c:choose>
								</select>
								
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									是否心理专业：
								</label>
								<div class="controls">
								<select name="isMind" disabled="disabled">
									<c:choose>
										<c:when test="${apsMoralEvaluationItem.isMind==0 }">
											<option value="0" >否</option>
										</c:when>
										<c:otherwise>
											<option value="1">是</option>
										</c:otherwise>
									</c:choose>
								</select>
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label">
									时间：
								</label>
								<div class="controls">
								<input type="text" readonly="readonly" id="pubishTime" name="pubishTime" class="span13" placeholder="" value="<fmt:formatDate value="${apsMoralEvaluationItem.pubishDate}" pattern="yyyy-MM-dd" />" placeholder="时间" >
								</div>
							</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>