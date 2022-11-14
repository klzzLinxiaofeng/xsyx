<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<title></title>
<%@ include file="/views/embedded/common.jsp"%>
<link href="${ctp}/res/css/bbx/bbx.css" rel="stylesheet">
<style>
.name{
	display:block;white-space:nowrap; overflow:hidden; text-overflow:ellipsis;width:110px;
}
.texta{
	padding:0;
}
.myerror {
	color: red !important;
	width: 22%;
	display: inline-block;
	padding-left: 10px;
}
.date .myerror{
	display:block;
}
input[type="radio"], input[type="checkbox"]{ margin:0 4px;margin-left:6px;}
</style>
</head>
<body>
<div class="row-fluid">
		<div class="span12">
			<div class="content-widgets" style="margin-bottom:0">
				<div class="widget-container" style="padding: 20px 0 0;">
					<form class="form-horizontal" id="bwattendances_form">
						<input type="hidden" id="teamId" name="teamId" value="${teamId }" />
						<div class="control-group" style="padding-left: 10px;">
							<input style="height: 34px; line-height: 34px; font-size: 12px;width:200px;"
									class="sj_time" id="attendanceDay" name="attendanceDay"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'});"
									placeholder="考勤日期" value="" type="text">
						</div>	
						<div id="type" class="control-group" style="padding-left: 10px;">
							<input name="attendances" id="isLate" value="isLate" type="radio" onclick="changeAttendanceType('isLate')"/>迟到
							<input name="attendances" id="isOutEarly" value="isOutEarly" type="radio" onclick="changeAttendanceType('isOutEarly')"/>早退
							<input name="attendances" id="isAbsent" value="isAbsent" type="radio" onclick="changeAttendanceType('isAbsent')"/>缺勤
							<input name="attendances" id="isLeave" value="isLeave" type="radio" onclick="changeAttendanceType('isLeave')"/>请假
							<input name="attendances" id="isRemove" value="isRemove" type="radio" onclick="changeAttendanceType('isRemove')"/>撤回当日考勤
						</div>
						<div class="control-group" style="padding-left: 10px;display: none" id="leaveRemarkDiv">
							<input type="text" placeholder="请假原因" id="remarks" name="remarks"  class="span13" style="width: 600px;">
						</div>
						<div class="ziri-pop" style="height:303px;overflow:auto;">
							<div class="ziri-num" id="studentList">
								<c:forEach items="${studentList}" var="student">
									<a href="javascript:void(0);" data-student-id="${student.userId}" >
										<span class="left stu-head"><img src="${student.entityId}"/></span>
										<div class="left stu-info">
											<em class="name">${student.name }</em>
										</div>
									</a>
								</c:forEach>
							</div>
							
						</div>
						
						<div class="form-actions tan_bottom_1">
								<a href="javascript:void(0)" class="yellow" onclick="saveOrUpdate()">确定</a>
								<a href="javascript:void(0)" onclick="closeWin();">取消</a>
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
		$(".ziri-num a").click(function(){
			$(this).toggleClass("on");
		})
		
		checker = initValidator();	
	});
	
	function initValidator() {
		return $("#bwattendances_form").validate({
			errorClass : "myerror",
			rules : {
				"attendanceDay" : {
					required : true
				},	
				"attendances" : {
					required : true
				},
				"remarks" : {
					maxlength : 30
				}
			},
			messages : {
				
			}
		});
	}
	
	//保存或更新修改
	function saveOrUpdate() {
		var loader = new loadLayer();
		if(checker.form()){
			var jsonArray = [];
			var $id = $("#id").val();
			var teamId = $("#teamId").val();
			var attendanceDay = $("#attendanceDay").val();
			$("#studentList .on").each(function(){
				var curJson = {};
				curJson.studentId = $(this).attr("data-student-id");
				jsonArray.push(curJson);
			});
			var type =$('input:radio[name="attendances"]:checked').val();
			var result = {};
			result.studentItems = jsonArray;
			var items = JSON.stringify(result);
			var $request = {};
			$request.items = items;
			$request.type = type;
			$request.teamId = teamId;
			$request.attendanceDay = attendanceDay;
			$request.remarks = $("#remarks").val();
			var url = "${ctp}/bbx/bpBwAttendances/creator";
			if(jsonArray.length==0){
				$.error("还没选择学生，请选择!");
			}
			loader.show();
			$.post(url, $request, function(data, status) {
				if("success" === status) {
					$.success('操作成功');
					if("success" === data){
						parent.core_iframe.search();
						$.closeWindow();
					}
				}else{
					$.error("操作失败");
				}
				loader.close();
			});
		}
			
	}
	
	function changeAttendanceType(type){
		if(type == 'isLeave'){
			$("#leaveRemarkDiv").show();
		}else{
			$("#leaveRemarkDiv").hide();
			$("#remarks").val("");
		}
	}
	
</script>
</html>