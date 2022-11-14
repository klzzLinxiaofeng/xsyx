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
									<span class="red">*</span>日期
								</label>
								<div class="controls">
								<input type="text" id="createDate" name="createDate" class="span4" placeholder="请输入时间" onclick="WdatePicker();"${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label">
									<span class="red">*</span>考勤类型
								</label>
								<div class="controls">
								 <select id="attendanceType" name="attendanceType" class="span4 chzn-select"  onchange="getAttendanceType();" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
								 </select>
								</div>
							</div>
							
							<div class="control-group" hidden >
								<label class="control-label">
									<span class="red">*</span>学年
								</label>
								<div class="controls">
								<select id="xn" name="schoolYearId" class="span4 chzn-select" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
								  <option value="${stc.schoolYear }">${stc.schoolYearName }</option>
								</select>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									<span class="red">*</span>年级
								</label>
								<div class="controls">
								<select id="nj" name="gradeId" class="span4 chzn-select" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
								   <option value="">请选择</option>
								  <c:forEach items="${gradeList }" var="grade">
								    <option value="${grade.id }">${grade.name }</option>
								  </c:forEach>
								</select>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									<span class="red">*</span>班级
								</label>
								<div class="controls">
								<select id="bj" name="teamNumber" class="span4 chzn-select" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
								 <option value="">请选择</option>
								</select>
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label">
									<span class="red">*</span>姓名
								</label>
								<div class="controls">
								<select id="stu" name="studentId"class="span4 chzn-select" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
								 <option value="">请选择</option>
								</select>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									<span class="red">*</span>宿舍楼号
								</label>
								<div class="controls">
								    <input type="text" class="span4" id="floorName" name="floorName" value="" readOnly">
								    <input type="hidden" class="span4" id="dormitoryId" name="dormitoryId" value="" readOnly/>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									<span class="red">*</span>寝室编号
								</label>
								<div class="controls">
								  <input type="text"  class="span4" id="dormitoryCode"  name="dormitoryCode" value="" readOnly/>
								</div> 
							</div>
							
						  <div class="control-group">
								<label class="control-label">
									备注
								</label>
								<div class="controls">
								    <textarea rows="3" cols="5" id="remark" name="remark" class="span4"  ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}></textarea>
								</div>
							</div>	
						<div class="form-actions tan_bottom">
							<input type="hidden" id="id" name="id" value="${dormitoryattendance.id}" />
								<button class="btn btn-warning" type="button"
									onclick="saveOrUpdate();">保存</button>
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
		remove("nj");
		$.jcGcSelector("#attendanceType", {"tc" : "XY-YH-KQLX"});
		$("#nj").val("${grade.id }").chosen();
		$("#nj").change(function(){
			$("#bj").get(0).options.length=1;
			getTeam();
			$("#stu").get(0).options.length=1;
			$("#dormitoryId").val("");
			$("#dormitoryCode").val("");
		});
		
		checker = initValidator();
	});

	
	function initValidator() {
		return $("#dormitoryattendance_form").validate({
			errorClass : "myerror",
			rules : {
				"createDate":{
					required:true
				}, 
				 
				"attendanceType":{
					required:true
				},
				"scholYearId":{
					required:true
				},
				"gradeId":{
					required:true
				},
				"teamNumber":{
					required:true
				},
				
				"dormitoryId":{
					required:true
				},
				"dormitoryCode":{
					required:true
				}
				
			},
			messages : {
				"createDate":{
					required:"日期必填"
				}, 
				"attendanceType":{
					required:"考勤必选"
				},
				"scholYearId":{
					required:"学年必选"
				},
				"gradeId":{
					required:"年级必选"
				},
				"teamNumber":{
					required:"班级必选"
				},
				 
				
				"dormitoryId":{
					required:"宿舍楼号必选"
				},
				"dormitoryCode":{
					required:"寝室编号必选"
				}
				
			}
		});
	}
	
	//异步获取班级列表
	function getTeam(){
		var gradeId = $("#nj").val();
		var url = "${pageContext.request.contextPath}/schoolaffair/dormitoryAttendance/ajax/getTeamList";
		$.ajax({
			url : url,
			data : 'gradeId=' + gradeId,
			type : 'post',
			cache : false,
			dataType : 'json',
			success : function(data) {
				if(data.length!=0){
					for(var i=0;i<data.length;i++){
						if(data[i].gradeId == gradeId){
						$("#bj").append("<option value="+data[i].id+">"+data[i].name+"</option>");
						}
						remove("bj");
					}
					
				}
				   }
				}); 
		$("#bj").change(function(){
			$("#stu").get(0).options.length=1;
			getStudent();
			$("#floorName").val("");
			$("#dormitoryId").val("");
			$("#dormitoryCode").val("");
			
		});
		
	}
	
	
	//异步获取学生列表
	function getStudent(){
		var gradeId = $("#nj").val();
		var teamNumber = $("#bj").val();
		var url = "${pageContext.request.contextPath}/schoolaffair/dormitoryAttendance/ajax/getStudentList";
		$.ajax({
			url : url,
			data : {"gradeId": gradeId,"teamNumber":teamNumber},
			type : 'post',
			cache : false,
			dataType : 'json',
			success : function(data) {
				if(data.length!=0){
					for(var i=0;i<data.length;i++){
						if(data[i].teamId == teamNumber){
						$("#stu").append("<option value="+data[i].id+">"+data[i].name+"</option>");
						}
						remove("stu");
					}
					
				}
				   }
				}); 
		$("#stu").change(function(){
			getFloorAndDormitory();
		});
	}
	
	//异步获取该学生所住是宿舍楼号和宿舍号
	
	function getFloorAndDormitory(){
		var gradeId = $("#nj").val();
		var teamNumber = $("#bj").val();
		var studentId = $("#stu").val();
		var url = "${pageContext.request.contextPath}/schoolaffair/dormitoryAttendance/ajax/getFloorAndDormitory";
		$.ajax({
			url : url,
			data : {"gradeId": gradeId,"teamNumber":teamNumber,"studentId":studentId},
			type : 'post',
			cache : false,
			dataType : 'json',
			success : function(data) {
				$("#floorName").val(data.floorName);
				$("#dormitoryId").val(data.dormitoryId);
				$("#dormitoryCode").val(data.dormitoryCode);
				   }
				}); 
	}
	
	
	function remove(id){
    	var options = "";
    	$("#"+id).find("option").each(function(j,m){
    		if(options.indexOf($(m)[0].outerHTML) == -1){
    				options += $(m)[0].outerHTML; 
    			}        
    		});        
    	$("#"+id).html(options); 
	}
	//保存或更新修改
	function saveOrUpdate() {
		if (checker.form()) {
			var loader = new loadLayer();
			var $id = $("#id").val();
			var $requestData = formData2JSONObj("#dormitoryattendance_form");
			var url = "${pageContext.request.contextPath}/schoolaffair/dormitoryAttendance/creator";
			if ("" != $id) {
				$requestData._method = "put";
				url = "${pageContext.request.contextPath}/schoolaffair/dormitoryAttendance/" + $id;
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