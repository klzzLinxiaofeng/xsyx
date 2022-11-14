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
.box_list_in span{    float: left;
    font-size: 14px;
    width: 139px;
    margin-right: 15px;
    margin-top: 10px;
    color: #fff;
    line-height: 30px;
    text-align: center;
    background: #a8a8a8;
    display: block;
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;}
    
.box_list_in span.on{
	background:#0d7bd5;
	color:#fff;
}
.box_list_in span:hover{
	color:#FFF;
	cursor: pointer;
	background:#0d7bd5;
}
input[type=radio]{vertical-align: sub; margin-right:5px; margin-left:8px;}
    
</style>
</head>
<body style="background-color: cdd4d7 !important">
	<div class="row-fluid">
		<div class="span12">
			<div class="content-widgets" style="margin-bottom: 0">
				<div class="widget-container" style="padding: 20px 0 0;">
					<form class="form-horizontal tan_form" id="courseConfig_form" action="javascript:void(0);">
							<div class="control-group">
								<label class="control-label">
									年级：
								</label>
								<div class="controls">
									<select  id="gradeId" name="gradeId" class="span13" style="width: 200px;" onchange="findCourseList()"></select>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									开始时间：
								</label>
								<div class="controls">
									<input type="text" id="signupStartDate" name="signupStartDate" placeholder="开始时间" 
									value="<fmt:formatDate value="${courseConfig.signupStartDate}" pattern="yyyy-MM-dd HH:mm"/>"
									class="Wdate" onFocus="WdatePicker({lang:'zh-cn',minDate:'%y-%M-%d %H:%m',dateFmt:'yyyy-MM-dd'})"
									style="height: 34px; line-height: 34px; font-size: 12px;width:200px;">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									结束时间：
								</label>
								<div class="controls">
									<input type="text" id="signupFinishDate" name="signupFinishDate" placeholder="结束时间" 
									value="<fmt:formatDate value="${courseConfig.signupFinishDate}" pattern="yyyy-MM-dd HH:mm"/>" 
									class="Wdate" 
									onFocus="WdatePicker({lang:'zh-cn',minDate:'#F{$dp.$D(\'signupStartDate\',{m:+5})}',dateFmt:'yyyy-MM-dd'})"
									style="height: 34px; line-height: 34px; font-size: 12px;width:200px;">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									走班课程：
								</label>
								<div class="controls">
								<input type="text" id="courseArrStr" class="span13" placeholder="" value="" disabled="disabled">
								<input type="hidden" id="electiveCourses" name="electiveCourses" value="" />
								<input type="hidden" id="totalElectiveCount" name="totalElectiveCount" value="" />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									选择几门科目：
								</label>
								<div class="controls">
								<input type="text" id="allowedElectiveCount" name="allowedElectiveCount" class="span13" placeholder="" 
									style="width:200px;" value="${courseConfig.allowedElectiveCount}" onchange="getCoursesCombinationList()">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									设置报名人数上限：
								</label>
								<div class="controls">
									<input type="radio" id="isLimited" name="isLimited" value="true"/>是
									<input type="radio" id="isLimited" name="isLimited" value="false" checked="checked"/>否
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label">
									开放选课组合：
								</label>
								<div class="controls">
									<div class="box_list_in controls-div" style="overflow: hidden;">
										<span></span>
									</div>
								</div>
							</div>
						<div class="form-actions tan_bottom">
							<input type="hidden" id="id" name="id" value="${courseConfig.id}" />
							<input type="hidden" id="schoolYear" name="schoolYear" value="${schoolYear}" />
							<input type="hidden" id="termCode" name="termCode" value="${termCode}" />
							<c:choose>
								<c:when test="${isCK == 'disable'}"><button class="btn btn-warning" type="button" onclick="$.closeWindow();">关闭</button></c:when>
								<c:otherwise><button class="btn btn-warning" type="button" onclick="saveOrUpdate();">确定</button></c:otherwise>
							</c:choose>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	var checker;

	$(".box_list_in").on("click",'span',function(){
		$(this).toggleClass("on");
	})
	$(function() {
		var gradeId ="${courseConfig.gradeId}";
		
		checker = initValidator();
		$.GradeSelector({
			"selector" : "#gradeId",
			"condition" : {
				"selectedVal" : gradeId,
				"schoolYear" : "${schoolYear}"
			},
			"afterHandler" : function() {
				$("#gradeId").val(gradeId);
				findCourseList();
			}
		});
		
		var isCK = "${isCK}";
		if(isCK == "disable"){
			$("input[type='text']").attr("disabled", "disabled");	
			$("select").attr("disabled", "disabled");	
		}
		
	});
	
	function initValidator() {
		return $("#courseConfig_form").validate({
			errorClass : "myerror",
			rules : {
				"signupStartDate" : {
					required : true
				},
				"signupFinishDate" : {
					required : true,
				},
				"gradeId" : {
					required : true,
				},
				"allowedElectiveCount" : {
					required : true,
				}
			},
			messages : {
				
			}
		});
	}
	
	function check(){
		var gradeId = $("#gradeId").val();
		var schoolYear = $("#schoolYear").val();
		var termCode = $("#termCode").val();
		var electiveCourses = $("#electiveCourses").val();
		if(electiveCourses == null || electiveCourses == ""){
			$.error("请设置本学期走班课程");
			return false;
		}
		
		$.post("${ctp}/bbx/courseConfig/checkCourseConfig", 
			{gradeId:gradeId, schoolYear:schoolYear, termCode:termCode}, 
			function(data) {
				if(data == "false"){
					$.error("本学期该年级已创建选课");
					return false;
				}else{
					return true;
				}
			}
		);	
		
	}
	
	function findCourseList(){
		var gradeId = $("#gradeId").val();
		var schoolYear = $("#schoolYear").val();
		var termCode = $("#termCode").val();
		if(gradeId != null && gradeId != ""){
			$.post("${ctp}/bbx/courseConfig/getCourseArrStr", 
				{gradeId:gradeId, schoolYear:schoolYear, termCode:termCode}, 
				function(data) {	
					if(data != null && data !=""){
						data = eval("(" + data + ")");
						$("#courseArrStr").val(data.courseArrStr);
						$("#electiveCourses").val(data.electiveCourses);
						$("#totalElectiveCount").val(data.totalElectiveCount);
					}else{
						$("#courseArrStr").val("");
						$("#electiveCourses").val("");
						$("#totalElectiveCount").val("");
					}	
				}
			);
		}else{
			$("#courseArrStr").val("");
			$("#electiveCourses").val("");
			$("#totalElectiveCount").val("");
		}
	}
	
	function getCoursesCombinationList(){
		var gradeId = $("#gradeId").val();
		var schoolYear = $("#schoolYear").val();
		var termCode = $("#termCode").val();
		var html_ = '';
		var allowedElectiveCount = $("#allowedElectiveCount").val();
		if(gradeId != null && gradeId != "" && allowedElectiveCount != ""){
			$.post("${ctp}/bbx/courseConfig/getCoursesCombinationList", 
				{gradeId:gradeId, schoolYear:schoolYear, termCode:termCode, allowedElectiveCount:allowedElectiveCount}, 
				function(data) {	
					data = eval("(" + data + ")");
					$.each(data,function(i,v){
						html_+='<span data-course-code="'+v.courseIds+'">'+v.courseNames+'</span>'
					});
					$('.box_list_in').html(html_)
				}
			);
		}
	}
	
	//保存或更新修改
	function saveOrUpdate() {
		if (checker.form()) {	
			var gradeId = $("#gradeId").val();
			var schoolYear = $("#schoolYear").val();
			var termCode = $("#termCode").val();
			var electiveCourses = $("#electiveCourses").val();
			var $id = $("#id").val();
			if(electiveCourses == null || electiveCourses == ""){
				$.error("请设置本学期该年级走班课程");
				return;
			}	
			
			if($id == null || "" == $id){
				$.post("${ctp}/bbx/courseConfig/checkCourseConfig", 
						{gradeId:gradeId, schoolYear:schoolYear, termCode:termCode}, 
						function(data) {
							if(data == "false"){
								$.error("本学期该年级已创建选课");
								return;
							}else{
								save();
							}
						}
					);	
			}else{
				save();
			}
					
		}
	}
	
	function save(){
		var loader = new loadLayer();
		var $id = $("#id").val();
		var $requestData = {};
		$requestData.schoolYear = $("#schoolYear").val();
		$requestData.termCode = $("#termCode").val();
		$requestData.gradeId = $("#gradeId").val();
		$requestData.totalElectiveCount = $("#totalElectiveCount").val();
		$requestData.allowedElectiveCount = $("#allowedElectiveCount").val();
		$requestData.electiveCourses = $("#electiveCourses").val();
		$requestData.signupStartDate = $("#signupStartDate").val();
		$requestData.signupFinishDate = $("#signupFinishDate").val();
		$requestData.isLimited = $("input[name='isLimited']:checked").val();
		var jsonArray = [];
		var jsonArrayName = [];
		$(".box_list_in .on").each(function(){
			jsonArray.push($(this).attr("data-course-code"));
			jsonArrayName.push($(this).html());
		});
		$requestData.courseCombinationIds = JSON.stringify(jsonArray);	
		$requestData.courseCombinationNames = JSON.stringify(jsonArrayName);
		//alert($requestData.courseCombinationNames);
		//return; 
		
		var url = "${ctp}/bbx/courseConfig/creator";
		if ("" != $id) {
			$requestData._method = "put";
			url = "${ctp}/bbx/courseConfig/" + $id;
		}
		loader.show();
		$.post(url, $requestData, function(data, status) {
			if("success" === status) {
				$.success('操作成功');
				data = eval("(" + data + ")");
				if("success" === data.info) {
					parent.core_iframe.window.search();
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
</script>
</html>