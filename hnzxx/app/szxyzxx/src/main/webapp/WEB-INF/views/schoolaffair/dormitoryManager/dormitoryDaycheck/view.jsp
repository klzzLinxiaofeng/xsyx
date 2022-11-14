<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/views/embedded/common.jsp"%>
<html>
<head>
<title></title>
<style>
<style>
.row-fluid .span13 {
	width: 75%;
}

.row-fluid .span4 {
	width: 227px;
}

.row-fluid .span5 {
	width: 30px;
	margin-right: 3px;  
}
.myerror {
	color: red !important;
	width: 22%;
	display: inline-block;
	padding-left: 10px;
}
</style>
</style>
</head>
<body style="background-color: cdd4d7 !important">
	<div class="row-fluid">
		<div class="span12">
			<div class="content-widgets" style="margin-bottom: 0">
				<div class="widget-container" style="padding: 20px 0 0;">
					<form class="form-horizontal tan_form" id="dormitorydaycheck_form" action="javascript:void(0);">
							<div class="control-group" hidden>
								<label class="control-label">
									学年 
								</label>
								<div class="controls">
								<select id="xn" name="schoolYearId" style="width: 227px;" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""} >
								</select>
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label">
									年级
								</label>
								<div class="controls">
								<select id="nj" name="gradeId" style="width:227px;"  ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
								</select>
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label">
									班级 
								</label>
								<div class="controls">
								<select id="bj" name="teamNumber" style="width: 227px;"  ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
								</select>
								</div>
							</div>
							
							 <div class="control-group">
								<label class="control-label">
									日期
								</label>
								<div class="controls">
								<input type="text" id="createDate" name="createDate" class="span4"  value="<fmt:formatDate pattern="yyyy-MM-dd "
															value='${dormitoryDaycheck.createDate }'></fmt:formatDate>"  ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
								</div>
							</div> 
							
							<div class="control-group">
								<label class="control-label">
									检查类型
								</label>
								
							  <div class="controls">
								<select id="checkType" name="checkType" class="span4 chzn-select"  onchange="getCheckType();" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
								</select>
								</div>
							</div>
							
							
							<div class="control-group">
								<label class="control-label">
									宿舍楼号
								</label>
								<div class="controls">
								<select id="floorCode" name="floorCode" class="span4 chzn-select"  ${isCK != null && isCk != '' ? "disabled='disabled'" : ""} >
								   <option value="${dormitoryDaycheck.dormitoryId}" selected>${dormitoryDaycheck.floorName}</option>
								</select>
								</div>
							</div>
							
							
							<div class="control-group">
								<label class="control-label">
									寝室编号
								</label>
								<div class="controls"  id="dCode">
								    <select id="dormitoryCode" name="dormitoryCode" class="span4 chzn-select"  ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
								      <option value="${dormitoryDaycheck.dormitoryCode }"selected>${dormitoryDaycheck.dormitoryCode }</option>
								    </select>
								</div>
							</div>
							
							
							<div class="control-group">
								<label class="control-label">
									检查结果
								</label>
								<div class="controls">
								<input type="text" id="checkResult" name="checkResult" class="span4"  value="${dormitoryDaycheck.checkResult}" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
								</div>
							</div>
							
							
							<div class="control-group">
								<label class="control-label">
									检查说明
								</label>
								<div class="controls">
								    <textarea rows="3" cols="5" id="remark" name="remark" class="span4"  ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>${dormitoryDaycheck.remark }</textarea>
								</div>
							</div>
							
						<div class="form-actions tan_bottom">
							<input type="hidden" id="id" name="id" value="${dormitorydaycheck.id}" />
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
$(function() {
	$.jcGcSelector("#checkType", {"tc" : "XY-YH-RCJCLX"},"${dormitoryDaycheck.checkType}");
	$.initCascadeSelector({
		"type" : "team",
		"yearSelectId" : "xn", //学年select标签ID 默认为xn
		"gradeSelectId" : "nj", //年级select标签ID 默认为nj
		"teamSelectId" : "bj",  //班级select标签ID 默认为bj
			"isEchoSelected" : true,
			"yearSelectedVal" : "${sy.year}", //要回显的学年唯一标识
		"gradeSelectedVal" : "${dormitoryDaycheck.gradeId}", //要回显的年级唯一标识
		"teamSelectedVal" : "${dormitoryDaycheck.teamNumber}", //要回显的班级唯一标识
	});

});

</script>
</html>