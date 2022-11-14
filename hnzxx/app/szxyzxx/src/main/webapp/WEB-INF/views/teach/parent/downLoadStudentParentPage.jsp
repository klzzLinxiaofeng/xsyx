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
.chzn-container .chzn-results{
	max-height:120px;
}
</style>
</head>
<body style="background-color: cdd4d7 !important">
		<div class="row-fluid ">
			<div class="span12">
				<div class="content-widgets" style="margin-bottom: 0">
				<div class="widget-container" style="padding: 20px 0 0;">
					<h6 id="errorInfo">${parentVo.errorInfo} </h6>
					<form action="${pageContext.request.contextPath}/teach/parent/parentDownLoadList" method="get"  id="parent_form" class="form-horizontal left-align form-well" novalidate="novalidate">	
<!--  					<form class="form-horizontal tan_form" id="parent_form" class="form-horizontal left-align form-well" novalidate="novalidate">
 -->						
							
							<div class="control-group">
									<label class="control-label">学年</label>
										<div class="controls">
										<select id="schoolYear" name="schoolYear" class="chzn-select" style="width:220px;"></select>
									
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">年级</label>
									<div class="controls">
										 <select id="gradeId" name="gradeId" class="chzn-select" style="width:220px;"></select>
										
									</div>
								</div>
									
								<div class="control-group">
									<label class="control-label">班级</label>
									<div class="controls">
										<select id="teamId" name="teamId" class="chzn-select" style="width:220px;"></select>
										
									</div>
							</div>
							<div class="control-group">
								<label class="control-label">学生姓名</label>
									<div class="controls">
										<select id="studentId"  name="studentId" class="chzn-select" style="width:220px;"></select>
									
									</div>
							</div>
							
						<div class="form-actions tan_bottom">
								<button class="btn btn-warning" type="submit"
									onclick="downLoadModel();">导出数据</button>
						</div>
					</form>
					
					</div>
				</div>
			</div>
		</div>
</body>
<script type="text/javascript">

	$(function() {
		
		$.initCascadeSelector({
			"type" : "stu",
			"yearSelectId" : "schoolYear", //学年select标签ID 默认为xn
			"gradeSelectId" : "gradeId", //年级select标签ID 默认为nj
			"teamSelectId" : "teamId",  //班级select标签ID 默认为bj
			"stuSelectId" : "studentId"
		});
		
		
	});
	
	function downLoadModel(){
		
		document.getElementById("errorInfo").innerHTML=""
		
		
		
	}
	
</script>
</html>