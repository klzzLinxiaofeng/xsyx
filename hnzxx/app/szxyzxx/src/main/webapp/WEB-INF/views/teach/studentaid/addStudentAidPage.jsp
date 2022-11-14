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
    
    .myerror {
		color: red !important;
		display: inline-block;
		padding-left: 10px;
	}
</style>
</head>
<body style="background-color: cdd4d7 !important">
		<div class="row-fluid ">
			<div class="span12">
				<div class="content-widgets" style="margin-bottom: 0">
				<div class="widget-container" style="padding: 20px 0 0;">
							<form id="stepy1" name="stepy1" class="form-horizontal left-align form-well" novalidate="novalidate" action="javascript:void(0);">
									
									<div class="control-group">
									
										<label class="control-label"><span class="red">*</span>学年</label>
										<div class="controls">
											<select id="schoolYear" name="schoolYear" class="chzn-select"style="width:220px;"></select>
									
										</div>
									</div>
									<div class="control-group">
									
										<label class="control-label"><span class="red">*</span>年级</label>
										<div class="controls">
											 <select id="gradeId" name="gradeId" class="chzn-select" style="width:220px;"></select>
										</div>
									</div>
									
									<div class="control-group">
									
										<label class="control-label"><span class="red">*</span>班级</label>
										<div class="controls">
											<select id="teamId" name="teamId" class="chzn-select" style="width:220px;"></select>
										</div>
									</div>
									<div class="control-group">
									
										<label class="control-label"><span class="red">*</span>姓名</label>
										<div class="controls">
											 <select id="studentId" name="studentId" class="chzn-select" style="width:220px;"></select>
										
										</div>
									</div>
									
									<div class="control-group">
									
										<label class="control-label"><span class="red">*</span>贫困类别</label>
										<div class="controls">
										<!-- 	<select id="povertyCategory" name="povertyCategory">
												<option>1</option>
												<option>2</option>
												<option>3</option>
											</select> -->
											<select id="povertyCategory" name="povertyCategory" class="chzn-select"style="width:220px;"></select>
									     <%-- <input type="text" name="povertyCategory" id="povertyCategory" value="${studentAidVo.povertyCategory}" placeholder="贫困类别"/> --%>
										</div>
									</div>
									
									<div class="control-group">
									
										<label class="control-label"><span class="red">*</span>贫困原因</label>
										<div class="controls">
											<select id="povertyCauses" name="povertyCauses" class="chzn-select"style="width:220px;"></select>
											<%-- <input type="text" name="povertyCauses" id="povertyCauses" value="${studentAidVo.povertyCauses}" placeholder="贫困原因"/> --%>
										</div>
									</div>
									
									<div class="control-group">
									
										<label class="control-label"><span class="red">*</span>助困形式</label>
										<div class="controls">
										<!-- 	<select name="aidForm" id="aidForm">
												<option>1</option>
												<option>2</option>
												<option>3</option>
											</select> -->
											
											<input type="text" name="aidForm" id="aidForm" value="${studentAidVo.aidForm}" placeholder="助困形式,少于20个字" />
										</div>
									</div>
									
									<div class="control-group">
									
										<label class="control-label"><span class="red">*</span>家庭年收入/人口</label>
										<div class="controls">
											<input  type="text" name="oneIncome" id="oneIncome" value="${studentAidVo.oneIncome}" placeholder="家庭年收入/人口,不小于0,不大于8位的金额"/>
										</div>
									</div>
									
									<div class="control-group">
									
										<label class="control-label"><span class="red">*</span>资助日期</label>
										<div class="controls">
											<input type="text" id="aidDay" name="aidDay" value='<fmt:formatDate pattern="yyyy-MM-dd" value="${studentAidVo.aidDay}"></fmt:formatDate>' placeholder="资助日期" onclick="WdatePicker();"/>
										</div>
									</div>
									
									<div class="control-group">
									
										<label class="control-label"><span class="red">*</span>资助金额（元）</label>
										<div class="controls">
											<input  type="text" name="aidAmount" id="aidAmount" value="${studentAidVo.aidAmount}" placeholder="资助金额,不小于0,不大于8位的金额"/>
										</div>
									</div>
									
									<div class="control-group">
										<label class="control-label">备注</label>
										<div class="controls">
											<%-- <input   name="remark" id=remark value="${studentAidVo.remark}" placeholder="备注"/> --%>
											<textarea rows="3" cols=""  name="remark" id="remark"  placeholder="备注">${studentAidVo.remark}</textarea>
										</div>
										<!-- ${studentAid.remark} -->
									</div>
									<div class="clear"></div>
								
								
								<div class="form-actions tan_bottom">
									<button type="button" onclick="saveUserInfo();" class="btn btn-warning">保存</button>
								</div>
								
								<!-- <div class="form-actions tan_bottom">
								<button class="btn btn-warning" type="button"
									onclick="saveUserInfo();">确定</button>
						</div> -->
							</form>
						</div>
					</div>
				</div>
			</div>
	<script type="text/javascript">
	var checker="";
	$(function() {
			$.initCascadeSelector({
				"type" : "stu",
				"yearSelectId" : "schoolYear", //学年select标签ID 默认为xn
				"gradeSelectId" : "gradeId", //年级select标签ID 默认为nj
				"teamSelectId" : "teamId",  //班级select标签ID 默认为bj
				"stuSelectId" : "studentId",  //学生select标签ID 默认为stu
				"yearSelectedVal" : "${studentAidVo.schoolYear}", //要回显的学年唯一标识
				"gradeSelectedVal" : "${studentAidVo.gradeId}", //要回显的年级唯一标识
				"teamSelectedVal" : "${studentAidVo.teamId}", //要回显的班级唯一标识
				 "stuSelectedVal" : "${studentAidVo.studentId}" //要回显的学生唯一标识 
			});
		
		//困难程度
		$.jcGcSelector("#povertyCategory", {tc : "JY-KNCD"}, "", function() {
			$("#povertyCategory").chosen();
		});
		//困难原因
		$.jcGcSelector("#povertyCauses", {tc : "JY-KNYY"}, "", function() {
			$("#povertyCauses").chosen();
		});
		
	   /* $('textarea.tinymce-simple').tinymce({
			script_url : '${pageContext.request.contextPath}/res/plugin/falgun/js/tiny_mce/tiny_mce.js',
			theme : "simple"
		}); */
		
		
		checker =  initValidator();
		
    });
	

    
    function saveUserInfo(){
    	
    	if (checker.form()) {
    		var loader = new loadLayer();
    		var $requestData = formData2JSONObj("#stepy1");
        	var url = "${pageContext.request.contextPath}/teach/studentaid/addStudentAid";
        	loader.show();
        	$.post(url, $requestData, function(data, status) {
				if ("success" === status) {
					data = eval("(" + data + ")");
					if ("success" === data.info) {
						$.success('保存成功');
						if (parent.core_iframe != null) {
							parent.core_iframe.window.location.reload();
						} else {
							parent.window.location.reload();
						}
						$.closeWindow();
					} else {
						$.error("保存失败");
					}
				} else {
					$.error("服务器异常");
				}
				loader.close();
			});
    	}
    }
    
    function initValidator() {
    	return $("#stepy1").validate({
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
					},
					"username":{
						remote:"用户名已存在"
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
