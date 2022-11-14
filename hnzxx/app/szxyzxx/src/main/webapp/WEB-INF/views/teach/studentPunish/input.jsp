<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/views/embedded/common.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<style type="text/css">
. form-horizontal{position:relative;}
	.add_img{
		background:url("${pageContext.request.contextPath}/res/css/extra/images/up_img.jpg") no-repeat;
		display:block;
		width:98px;
		height:30px;
		
	}
	.head_photo{   height: 185px;
    right: 20px;
    position: absolute;
    top: 101px;
    width: 155px;}
    .form-horizontal .control-group{
    	width:45%;
    	display:inline-block;
    	float:left;
    }
    .table thead th{height:34px;line-height:34px;}
    legend + .control-group{margin-top:0;}
    
    .myerror {
		color: red !important;
		width: 34%;
		display: inline-block;
		padding-left: 10px;
	}
</style>
</head>
<body style="background-color:cdd4d7 !important">
	<div class="row-fluid">
		<div class="span12">
<div class="content-widgets" style="margin-bottom:0">
				<div class="widget-container" style="padding:20px 0 0;">
						
							<form id="studentpunish_form" class="form-horizontal left-align form-well" novalidate="novalidate">
								 <input type="hidden" id="id" name="id" value="${studentPunish.id}" style="width:220px;"/>
								 <div class="control-group">
										<label class="control-label"><span class="red">*</span>学年</label>
										<div class="controls">
											<select id="schoolYear" name="schoolYear" class="chzn-select"style="width:220px;"></select>
									
										</div>
								</div>
								 <div class="control-group">
										<label class="control-label"><span class="red">*</span>年级</label>
										<div class="controls">
											<select id="gradeId" name="gradeId" class="chzn-select"style="width:220px;"></select>
									
										</div>
								</div>
								 <div class="control-group">
										<label class="control-label"><span class="red">*</span>班级</label>
										<div class="controls">
											<select id="teamId" name="teamId" class="chzn-select"style="width:220px;"></select>
									
										</div>
								</div>
							
							<div class="control-group">
								<label class="control-label">
									<span class="red">*</span>姓名
								</label>
								<div class="controls">
								<select id="studentId" name="studentId" class="chzn-select"style="width:220px;"></select>
								</div>
								
							</div>
							
							<div class="control-group">
								<label class="control-label">
									<span class="red">*</span>处分类型
								</label>
								<div class="controls">
								<input type="text" id="punishType" name="punishType" class="span13"
									placeholder="处分类型" value="${studentPunish.punishType}" style="width:220px;"/>
								</div>
								
							</div>
							<div class="control-group">
								<label class="control-label">
									<span class="red">*</span>处分原因
								</label>
								<div class="controls">
								<%-- <input type="text" id="punishCause" name="punishCause" class="span13"
									placeholder="处分原因" value="${studentPunish.punishCause}" style="width:220px;"/> --%>
									
									<select id="punishCause" name="punishCause" class="chzn-select"style="width:220px;"></select>
								</div>
								
							</div>
							<div class="control-group">
								<label class="control-label">
									<span class="red">*</span>处分日期
								</label>
								<div class="controls">
								<input type="text" id="punishDay" name="punishDay" class="span13"
									placeholder="处分日期" value='<fmt:formatDate pattern="yyyy-MM-dd" value="${studentPunish.punishDay}"></fmt:formatDate>' 
									onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'punishEndDay\')}'})" 
									 style="width:220px;"/>
								</div>
								
							</div>
							
							
							
							<div class="control-group">
								<label class="control-label">
									到期日期
								</label>
								<div class="controls">
								<input type="text" id="punishEndDay" name="punishEndDay" class="span13"
									placeholder="到期日期" value='<fmt:formatDate pattern="yyyy-MM-dd" value="${studentPunish.punishEndDay}"></fmt:formatDate>'
									 onFocus="WdatePicker({minDate:'#F{$dp.$D(\'punishDay\')}'})"
									style="width:220px;"/>
								</div>
								
							</div>
							<div class="control-group">
								<label class="control-label">
									撤销日期
								</label>
								<div class="controls">
								
								<input type="text" id="repealDay" name="repealDay" class="span13"
									placeholder="撤销日期" value='<fmt:formatDate pattern="yyyy-MM-dd" value="${studentPunish.repealDay}"></fmt:formatDate>' 
									onFocus="WdatePicker({minDate:'#F{$dp.$D(\'punishDay\')}'})"
								 style="width:220px;"/>
								
								</div>
								
							</div>
							<div class="control-group">
								<label class="control-label">
									<span class="red">*</span>是否撤销处分
								</label>
								<div class="controls">
								<%-- <input type="text" id="isRepeal" name="isRepeal" class="span13"
									placeholder="" value="${studentPunish.isRepeal}" style="width:220px;"/> --%>
									<select id="isRepeal" name="isRepeal" style="width:220px;">
									<option value="false">否</option>
									<option value="true">是</option>
									</select>
								</div>
								
							</div>
							
							<div class="control-group">
								<label class="control-label">
									备注
								</label>
								<div class="controls">
								
								<%-- <input type="text" id="remark" name="remark" class="span13"
									placeholder="备注" value="${studentPunish.remark}" style="width:200px;"/> --%>
								<textarea rows="3" cols=""  name="remark" id="remark"  placeholder="备注">${studentPunish.remark}</textarea>
								
								</div>
								
							</div>
							<div class="clear"></div>
								<div class="form-actions tan_bottom">
							<input type="hidden" id="id" name="id" value="${studentPunish.id}" />
								<button class="btn btn-warning" type="button"
									onclick="saveUserInfo();">确定</button>
						</div>
								
								<!-- <button  onclick="saveUserInfo();" class="btn btn-warning " >保存</button> -->
							</form>
						</div>
					</div>
				</div>
			</div>
	<script type="text/javascript">
var checker;
	$("#isRepeal option[value='${studentPunish.isRepeal}']").attr("selected","selected");
	$(function() {
		$.initCascadeSelector({
			"type" : "stu",
			"yearSelectId" : "schoolYear", //学年select标签ID 默认为xn
			"gradeSelectId" : "gradeId", //年级select标签ID 默认为nj
			"teamSelectId" : "teamId",  //班级select标签ID 默认为bj
			"stuSelectId" : "studentId",  //学生select标签ID 默认为stu
			 "isEchoSelected" : true,
			 "yearSelectedVal" : "${studentPunish.schoolYear}", //要回显的学年唯一标识
			"gradeSelectedVal" : "${studentPunish.gradeId}", //要回显的年级唯一标识
			"teamSelectedVal" : "${studentPunish.teamId}", //要回显的班级唯一标识 
			 "stuSelectedVal" : "${studentPunish.studentId}" //要回显的学生唯一标识 
				
		});
		
		//违纪类别
		$.jcGcSelector("#punishCause", {tc : "JY-WJLB"}, "${studentPunish.punishCause}", function() {
			$("#punishCause").chosen();
		});
	
	checker =  initValidator();
	
});
	
	$(function() {
		checker = initValidator();
	});
	
	function initValidator() {
		return $("#studentpunish_form").validate({
			errorClass : "myerror",
			rules : {
				   "schoolYear":{
						required : true
					},
					"gradeId":{
						required : true
					},
					"teamId":{
						required : true
					},
					"studentId":{
						required : true
					},
					"punishType":{
						required : true
					},
					"punishCause":{
						required : true
					},
					"punishDay":{
						required : true
					},
					"isRepeal":{
						required : true
					}
			},
			messages : {
				
			}
		});
	}
	
	//保存或更新修改
	function saveUserInfo() {
		if (true) {
			var loader = new loadLayer();
			var $id = $("#id").val();
			var $requestData = formData2JSONObj("#studentpunish_form");
		    var url = "${pageContext.request.contextPath}/teach/studentPunish/addStudentPunish";
			if ("" != $id) {
				$requestData._method = "put";
				url = "${pageContext.request.contextPath}/teach/studentPunish/updateStudentPunish";
			}; 
			loader.show();
			$.post(url, $requestData, function(data, status) {
				if("success" === status) {
					$.success('保存成功');
					data = eval("(" + data + ")");
					if("success" === data.info) {
						if(parent.core_iframe != null) {
 							parent.core_iframe.window.location.reload();
 						} else {
 							parent.window.location.reload();
 						}
						$.closeWindow();
					} else {
						
					}
				}else{
					$.error("保存失败");
				}
				loader.close();
			});
		}
	}
	</script>
</body>
</html>
