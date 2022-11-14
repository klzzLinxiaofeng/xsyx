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
					<form class="form-horizontal tan_form"
						id="dormitoryattendance_form" action="javascript:void(0);">
						<div class="control-group">
							<label class="control-label"> <span class="red">*</span>日期
							</label>
							<div class="controls">
								<input type="text" id="createDate" name="createDate"
									class="span4" placeholder="请输入时间"
									value="<fmt:formatDate pattern="yyyy-MM-dd "
															value='${dormitoryAttendance.createDate}'></fmt:formatDate>"
									onclick="WdatePicker();">
							</div>
						</div>

						<div class="control-group">
							<label class="control-label"> <span class="red">*</span>考勤类型
							</label>
							<div class="controls">
								<select id="attendanceType" name="attendanceType"
									class="span4 chzn-select" onchange="getAttendanceType();">
								</select>
							</div>
						</div>

						<div class="control-group" hidden>
							<label class="control-label"> <span class="red">*</span>学年
							</label>
							<div class="controls">
								<select id="xn" name="schoolYearId" class="span4 chzn-select">
								 <option value="${sy.year }">${sy.name }</option>
								</select>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label"> <span class="red">*</span>年级
							</label>
							<div class="controls">
								<select id="nj" name="gradeId" class="span4 chzn-select" onchange="getGrade();">
								  <c:forEach items="${gradeList }" var="grade">
								    <option value="${grade.id }" <c:if test="${dormitoryAttendance.gradeId==grade.id }">selected</c:if>>${grade.name }</option>
								  </c:forEach>
								</select>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label"> <span class="red">*</span>班级
							</label>
							<div class="controls">
								<select id="bj" name="teamNumber" class="span4 chzn-select" >
								  <option value="">请选择</option>
								</select>
							</div>
						</div>

						<div class="control-group">
							<label class="control-label"> <span class="red">*</span>姓名
							</label>
							<div class="controls">
								<select id="stu" name="studentId" class="span4 chzn-select">
								 <option value="">请选择</option>
								</select>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label"> <span class="red">*</span>宿舍楼号
							</label>
							<div class="controls">
							   <input type="text" class="span4" id="floorName" name="floorName" value="" readOnly>
							   <input type="hidden" class="span4" id="dormitoryId" name="dormitoryId" value="" readOnly/>
								<%-- <select id="floorCode" name="floorCode"
									class="span4 chzn-select" onchange="getFloorCode();">
									<option value="">请选择</option>
									<c:forEach items="${dormitoryList}" var="dormitory">
										<option value="${dormitory.floorCode}"
											<c:if test="${dormitory.floorCode==dormitoryAttendance.floorCode }">selected</c:if>>${dormitory.floorCode}</option>
									</c:forEach>
								</select> --%>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label"> <span class="red">*</span>寝室编号
							</label>
							<div class="controls">
								 <input type="text"  class="span4" id="dormitoryCode"  name="dormitoryCode" value="" readOnly/>
								<!-- <select id="dormitoryCode" name="dormitoryCode"
									class="span4 chzn-select"
									onchange="getDormitoryCode(floorCode);">
									<option value="">请选择</option>
								</select> -->
							</div>
						</div>

						<div class="control-group">
							<label class="control-label"> 备注 </label>
							<div class="controls">
								<textarea rows="3" cols="5" id="remark" name="remark"
									class="span4">${dormitoryAttendance.remark }</textarea>
							</div>
						</div>
						<div class="form-actions tan_bottom">
							<input type="hidden" id="id" name="id"
								value="${dormitoryAttendance.id}" />
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
		$.jcGcSelector("#attendanceType", {"tc" : "XY-YH-KQLX"}, "${dormitoryAttendance.attendanceType}");
// 		$("#nj").val("${grade.id }").chosen();
		getGrade();
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
	
	function getGrade(){
		var obj = document.getElementById("nj"); //selectId
		var index = obj.selectedIndex; //选中索引
		var gradeId = obj.options[index].value; //选中文本
		$("#bj").get(0).options.length=0;
		getTeam(gradeId);
		}
	
	
	
	
	//异步获取班级列表
	function getTeam(gradeId){
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
							if(data[i].id==${dormitoryAttendance.teamNumber}){
								teamNumber = data[i].id;	
								$("#bj").append("<option value="+data[i].id+" selected>"+data[i].name+"</option>");
							}else{
								$("#bj").append("<option value="+data[i].id+">"+data[i].name+"</option>");
						  }
						}
						remove("bj");
					}
				}
				
				$("#stu").get(0).options.length=0;
                getTeams(gradeId);
                $("#bj").change(function(){
                	$("#stu").get(0).options.length=0;
                	getTeams(gradeId);
        		});
			
			}
				});
	}
	
	function getTeams(gradeId){
		var obj1 = document.getElementById("bj"); //selectId
		var index1 = obj1.selectedIndex; //选中索引
		var teamNumber = obj1.options[index1].value; //选中文本
		getStudent(gradeId,teamNumber);
	}
	
	//异步获取学生列表
	function getStudent(gradeId,teamNumber){
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
							if(data[i].id==${dormitoryAttendance.studentId}){
								$("#stu").append("<option value="+data[i].id+" selected>"+data[i].name+"</option>");
							}else{
								
								$("#stu").append("<option value="+data[i].id+">"+data[i].name+"</option>");
							}
						}
						remove("stu");
					}
					
				}
				getFloorCode(gradeId,teamNumber);
                $("#stu").change(function(){
                	getFloorCode(gradeId,teamNumber);
        		});
				   }
				}); 
	}
	function getFloorCode(gradeId,teamNumber){
		var obj = document.getElementById("stu"); //selectId
		var index = obj.selectedIndex; //选中索引
		var studentId = obj.options[index].value; //选中文本
		getFloorAndDormitory(gradeId,teamNumber,studentId);
		
		}
	
	//异步获取该学生所住的宿舍楼号和宿舍号
	
	function getFloorAndDormitory(gradeId,teamNumber,studentId){
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
	
	function getAttendanceType(){
		var obj = document.getElementById("attendanceType"); //selectId
		var index = obj.selectedIndex; //选中索引
		var attendanceType = obj.options[index].value; //选中文本
	}
	function getDormitoryCode(floorCode){
		var url = "${pageContext.request.contextPath}/schoolaffair/dormitoryCodeListAjax/getDormitoryCodeList";
				$.ajax({
					url : url,
					data : 'floorCode=' + floorCode,
					type : 'post',
					cache : false,
					dataType : 'json',
					success : function(data) {
						if(data.length!=0){
							for(var i=0;i<data.length;i++){
								if(data[i].floorCode == floorCode){
									if(data[i].dormitoryCode==${dormitoryAttendance.dormitoryCode}){
										$("#dormitoryCode").append("<option value="+data[i].dormitoryCode+" selected>"+data[i].dormitoryCode+"</option>");
									}else{
										
										$("#dormitoryCode").append("<option value="+data[i].dormitoryCode+">"+data[i].dormitoryCode+"</option>");
									}
								}
							}
							
						}
						   }
						});
				}
	//保存或更新修改
	function saveOrUpdate() {
		if (checker.form()) {
			var loader = new loadLayer();
			var $requestData = formData2JSONObj("#dormitoryattendance_form");
			var url = "${pageContext.request.contextPath}/schoolaffair/dormitoryAttendance/saveEditor";
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