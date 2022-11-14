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
    }
    .table thead th{height:34px;line-height:34px;}
    legend + .control-group{margin-top:0;}
</style>
</head>
<body style="background-color: cdd4d7 !important">
		<div class="row-fluid ">
			<div class="span12">
				<div class="content-widgets" style="margin-bottom: 0">
				<div class="widget-container" style="padding: 20px 0 0;">
							<form id="modifyStudentAidForm"  class="form-horizontal left-align form-well" novalidate="novalidate">
								<input type="hidden" id="id" name="id" value="${studentAidVo.id}"/>
									<div class="control-group">
										<label class="control-label">学年</label>
										<div class="controls">
											<select id="schoolYear" name="schoolYear" class="chzn-select" style="width:220px;">
											
											</select>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">年级</label>
										<div class="controls">
											 <select id="gradeId" name="gradeId" class="chzn-select" style="width:220px;">
											
											 </select>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">班级</label>
										<div class="controls">
											 <select id="teamId" name="teamId" class="chzn-select" style="width:220px;">
											 
											 </select>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">姓名</label>
										<div class="controls">
											 <select id="studentId" name="studentId" class="chzn-select" style="width:220px;">
											
											 </select>
										</div>
									</div>
									
									<div class="control-group">
										<label class="control-label">贫困类别</label>
										<div class="controls">
											<select id="povertyCategory" name="povertyCategory" class="chzn-select" style="width:220px;"></select>
											
										</div>
									</div>
									
									<div class="control-group">
										<label class="control-label">贫困原因</label>
										<div class="controls">
											<select id="povertyCauses" name="povertyCauses" class="chzn-select" style="width:220px;"></select>
										
										</div>
									</div>
									
									<div class="control-group">
										<label class="control-label">助困形式</label>
										<div class="controls">
											
											<input type="text" name="aidForm" id="aidForm" value="${studentAidVo.aidForm}" placeholder="助困形式,少于20个字"/>
										</div>
									</div>
									
									<div class="control-group">
										<label class="control-label">家庭年收入/人口</label>
										<div class="controls">
											<input  type="text" name="oneIncome" id="oneIncome" value="${studentAidVo.oneIncome}" placeholder="家庭年收入/人口,不小于0,不大于8位的金额"/>
										</div>
									</div>
									
									<div class="control-group">
										<label class="control-label">资助日期</label>
										<div class="controls">
											<input type="text" id="aidDay" name="aidDay" value='<fmt:formatDate pattern="yyyy-MM-dd" value="${studentAidVo.aidDay}"></fmt:formatDate>' placeholder="资助日期" onclick="WdatePicker();"/>
										</div>
									</div>
									
									<div class="control-group">
										<label class="control-label">资助金额（元）</label>
										<div class="controls">
											<input  type="text" name="aidAmount" id="aidAmount" value="${studentAidVo.aidAmount}" placeholder="资助金额,不小于0,不大于8位的金额"/>
										</div>
									</div>
									
									<div class="control-group">
										<label class="control-label">备注</label>
										<div class="controls">
<%-- 											<input  type="text" name="remark" id=remark value="${studentAidVo.remark}" placeholder="备注"/>
 --%>										
 											 <textarea rows="3" cols=""  name="remark" id="remark"  placeholder="备注">${studentAidVo.remark}</textarea>
 										
 										</div>
									</div>
									<input  type="hidden" name="isDelete" id=isDelete value="${studentAidVo.isDelete}" />
									
								<!-- <button onclick="updateUserInfo();" class="btn btn-warning finish" >保存</button> -->
								<div class="form-actions tan_bottom">
								<input type="hidden" id="id" name="id" value="${studentAidVo.id}" />
								<button class="btn btn-warning" type="button"
									onclick="updateUserInfo();">保存</button>
						</div>
							</form>
						</div>
					</div>
				</div>
			</div>
	<script>

	var checker="";
 	$(function() {
 		$.ajaxSetup({  
 		    async : false
 		});
		$.initCascadeSelector({
			"type" : "stu",
			"yearSelectId" : "schoolYear", //学年select标签ID 默认为xn
			"gradeSelectId" : "gradeId", //年级select标签ID 默认为nj
			"teamSelectId" : "teamId",  //班级select标签ID 默认为bj
			"stuSelectId" : "studentId",  //学生select标签ID 默认为stu
 			"isEchoSelected" : true,
 			"yearSelectedVal" : "${studentAidVo.schoolYear}", //要回显的学年唯一标识
			"gradeSelectedVal" : "${studentAidVo.gradeId}", //要回显的年级唯一标识
			"teamSelectedVal" : "${studentAidVo.teamId}", //要回显的班级唯一标识
			"stuSelectedVal" : "${studentAidVo.studentId}" //要回显的学生唯一标识 
		});
		
		//困难程度
		$.jcGcSelector("#povertyCategory", {tc : "JY-KNCD"}, "${studentAidVo.povertyCategory}", function() {
			$("#povertyCategory").chosen();
		});
		//困难原因
		$.jcGcSelector("#povertyCauses", {tc : "JY-KNYY"}, "${studentAidVo.povertyCauses}", function() {
			$("#povertyCauses").chosen();
		});
		
		checker =  initValidator();
		$(".chzn-select").trigger("liszt:updated");
    });
	
    function updateUserInfo(){
    	
    	if (checker.form()) {
    		var loader = new loadLayer();
    		var $requestData = formData2JSONObj("#modifyStudentAidForm");
    		var $id = $("#id").val();
    		$requestData._method = "put";
        	var url = "${pageContext.request.contextPath}/teach/studentaid/updateStudentAid";
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
    
    function initValidator() {
    	return $("#modifyStudentAidForm").validate({
				errorClass : "myerror",
				rules : {
					
				   "schoolYear":{
						required : true
					},
					"gradeName":{
						required : true
					},
					"teamName":{
						required : true
					},
					"studentName":{
						required : true
					},
					"povertyCategory":{
						required : true
					},
					"povertyCauses":{
						required : true
					},
					"aidForm":{
						required : true,
						maxlength:20
					},
					"oneIncome":{
						required : true,
						maxlength:10,
						remote : {
							async : true,
							type : "GET",
							url : "${pageContext.request.contextPath}/teach/studentaid/checkAmount",
							data : {
								'dxlx' : 'oneIncome',
								'oneIncome' : function() {
									return $("#oneIncome").val();
							 	 	}
							 	}
							}
					},
					"aidDay" : {
						required : true
					},
					"aidAmount":{
						required : true,
						maxlength:10,
						remote : {
							async : true,
							type : "GET",
							url : "${pageContext.request.contextPath}/teach/studentaid/checkAmount",
							data : {
								'dxlx' : 'aidAmount',
								'aidAmount' : function() {
									return $("#aidAmount").val();
							 	 	}
							 	}
							}
					}
				},
				messages : {
					 /* "name":{
						remote:"学生名字重复"
					} */
					"oneIncome":{
						remote:"请填写正确金额"
					},
					"aidAmount":{
						remote:"请填写正确金额"
					}
				}
			});
    	}

	</script>
</body>
</html>
