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
</head>
<body style="background-color: cdd4d7 !important">
	<div class="row-fluid">
		<div class="span12">
			<div class="content-widgets" style="margin-bottom: 0">
				<div class="widget-container" style="padding: 20px 0 0;">
					<form class="form-horizontal tan_form" id="dormitoryattendance_form" action="javascript:void(0);">
							<div class="control-group">
								<label class="control-label">
									日期
								</label>
								<div class="controls">
								<input type="text" id="createDate" name="createDate" class="span4" placeholder="请输入时间" value="<fmt:formatDate pattern="yyyy-MM-dd "
															value='${dormitoryAttendance.createDate}'></fmt:formatDate>" onclick="WdatePicker();"${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label">
									考勤类型
								</label>
								<div class="controls">
								 <select id="attendanceType" name="attendanceType" class="span4 chzn-select"  onchange="getAttendanceType();" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
								 </select>
								</div>
							</div>
							
							<div class="control-group"  hidden>
								<label class="control-label">
									学年
								</label>
								<div class="controls">
								<select id="xn" name="schoolYearId" class="span4 chzn-select" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
								</select>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									年级
								</label>
								<div class="controls">
								<select id="nj" name="gradeId" class="span4 chzn-select" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
								</select>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									班级
								</label>
								<div class="controls">
								<select id="bj" name="teamNumber" class="span4 chzn-select" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
								</select>
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label">
									姓名
								</label>
								<div class="controls">
								<select id="stu" name="studentId"class="span4 chzn-select" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
								</select>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									宿舍楼号
								</label>
								<div class="controls">
									<select id="floorName" name="floorName" class="span4 chzn-select" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""} >
									   <option value="${dormitoryAttendance.floorName}" selected>${dormitoryAttendance.floorName}</option>
									</select>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									寝室编号
								</label>
								<div class="controls">
								  <select id="dormitoryCode" name="dormitoryCode" class="span4 chzn-select"  ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
								       <option value="${dormitoryAttendance.dormitoryCode}" selected>${dormitoryAttendance.dormitoryCode}</option>
								   </select>
								</div>
							</div>
							
						  <div class="control-group">
								<label class="control-label">
									备注
								</label>
								<div class="controls">
								    <textarea rows="3" cols="5" id="remark" name="remark" class="span4"  ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>${dormitoryAttendance.remark }</textarea>
								</div>
							</div>	
						<div class="form-actions tan_bottom">
							<input type="hidden" id="id" name="id" value="${dormitoryAttendance.id}" />
								
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
$(function() {
	$.jcGcSelector("#attendanceType", {"tc" : "XY-YH-KQLX"}, "${dormitoryAttendance.attendanceType}");
	$.initCascadeSelector({
		"type" : "stu",
		"yearSelectId" : "xn", //学年select标签ID 默认为xn
		"gradeSelectId" : "nj", //年级select标签ID 默认为nj
		"teamSelectId" : "bj",  //班级select标签ID 默认为bj
		"stuSelectId" : "stu",  //学生select标签ID 默认为stu
			"isEchoSelected" : true,
			"yearSelectedVal" : "${sy.year}", //要回显的学年唯一标识
		"gradeSelectedVal" : "${dormitoryAttendance.gradeId}", //要回显的年级唯一标识
		"teamSelectedVal" : "${dormitoryAttendance.teamNumber}", //要回显的班级唯一标识
		"stuSelectedVal" : "${dormitoryAttendance.studentId}" //要回显的学生唯一标识 
	});

});

</script>
</html>