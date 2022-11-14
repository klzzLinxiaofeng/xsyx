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
	display: inline-block;
	padding-left: 10px;
}
   .form-horizontal .control-group{
    	width:45%;
    	display:inline-block;
    	float:left;
    }
</style>
</head>
<body style="background-color: cdd4d7 !important">
		<div class="row-fluid ">
			<div class="span12">
				<div class="content-widgets" style="margin-bottom: 0">
				<div class="widget-container" style="padding: 20px 0 0;">
					<form class="form-horizontal tan_form" id="parent_form" class="form-horizontal left-align form-well" novalidate="novalidate">
							
							<div class="control-group">
									<label class="control-label"><span class="red">*</span>学年</label>
										<div class="controls">
										<select id="schoolYear" disabled="disabled" name="schoolYear" class="chzn-select" style="width:220px;"></select>
									
									</div>
								</div>
								<div class="control-group">
									<label class="control-label"><span class="red">*</span>年级</label>
									<div class="controls">
										 <select id="gradeId" disabled="disabled"  name="gradeId" class="chzn-select" style="width:220px;"></select>
										
									</div>
								</div>
									
								<div class="control-group">
									<label class="control-label"><span class="red">*</span>班级</label>
									<div class="controls">
										<select id="teamId"  name="teamId" class="chzn-select" style="width:220px;" disabled="disabled" ></select>
										
									</div>
							</div>
							<div class="control-group">
								<label class="control-label"><span class="red">*</span>姓名</label>
									
									
									<div class="controls">
										<select id="studentId"  name="studentId" class="chzn-select" style="width:220px;" disabled="disabled" ></select>
										
									</div>
							</div>
									
							<div class="control-group">
								<label class="control-label">
									<span class="red">*</span>与学生关系
								</label>
								<div class="controls">
								<%-- <input type="text"  style="width:220px;"  id="parentRelation" name="parentRelation" class="span13"
									placeholder="与学生关系" value="${parentVo.parentRelation}"> --%>
									<select id="parentRelation" name="parentRelation" class="chzn-select" style="width:220px;"></select>
								</div>
								
							</div>
							<div class="control-group">
								<label class="control-label">
									<span class="red">*</span>主监护人
								</label>
								<div class="controls">
								<%-- <input type="text"  style="width:220px;"  id="rank" name="rank" class="span13"
									placeholder="类型" value="${parentVo.rank}"> --%>
									<select id="rank" name="rank" class="chzn-select" style="width:220px;"></select>
								
								</div>
								
							</div>
							<div class="control-group">
								<label class="control-label">
									<span class="red">*</span>家长姓名
								</label>
								<div class="controls">
								<input type="text"  style="width:220px;"  id="name" name="name" class="span13"
									placeholder="家长姓名，最多10个字" value="${parentVo.name}">
								</div>
								
							</div>
							<div class="control-group">
								<label class="control-label">
									家长账号
								</label>
								<div class="controls">
								<input type="text" readonly="readonly"  style="width:220px;"  id="userName" name="userName" class="span13"
									placeholder="家长账号" value="${parentVo.userName}">
								</div>
								
							</div>
							<div class="control-group">
								<label class="control-label">
									<span class="red">*</span>手机号码
								</label>
								<div class="controls">
								<input type="text"  style="width:220px;"  id="mobile" name="mobile" class="span13"
									placeholder="手机号码" value="${parentVo.mobile}">
								</div>
								
							</div>
							<div class="control-group">
								<label class="control-label">
									邮箱
								</label>
								<div class="controls">
								<input type="text"  style="width:220px;"  id="email" name="email" class="span13"
									placeholder="邮箱" value="${parentVo.email}">
								</div>
								
							</div>

						<div class="control-group">
							<label class="control-label">
								车牌号
							</label>
							<div class="controls">
								<input type="text"  style="width:620px;"  id="licensePlate" name="licensePlate" class="span13"
									   placeholder="车牌号" value="${parentVo.licensePlate}">
							</div>
							<div class="controls">
								<span>多个车牌请用<span style="color: red">英文逗号</span>分开</span>
							</div>
						</div>

							
						
							
						<div class="form-actions tan_bottom">
						<input  type="hidden" id="id" name="id" class="span13"  value="${parentVo.id}">
								<button class="btn btn-warning" type="button"
									onclick="saveOrUpdate();">确定</button>
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
		
		$.initCascadeSelector({
			"type" : "stu",
			"yearSelectId" : "schoolYear", //学年select标签ID 默认为xn
			"gradeSelectId" : "gradeId", //年级select标签ID 默认为nj
			"teamSelectId" : "teamId",  //班级select标签ID 默认为bj
			"stuSelectId" : "studentId",  //学生select标签ID 默认为stu
 			"isEchoSelected" : true,
 			"yearSelectedVal" : "${parentVo.schoolYear}", //要回显的学年唯一标识
			"gradeSelectedVal" : "${parentVo.gradeId}", //要回显的年级唯一标识
			"teamSelectedVal" : "${parentVo.teamId}", //要回显的班级唯一标识
			"stuSelectedVal" : "${parentVo.studentId}" //要回显的学生唯一标识 
		});
		
 
 		
 		$.jcGcSelector("#parentRelation", {tc : "GB-JTGX"}, "${parentVo.parentRelation}", function() {
			$("#parentRelation").chosen();
		});
 		
 		$.jcGcSelector("#rank", {tc : "XY-JY-JZLB"}, "${parentVo.rank}", function() {
			$("#rank").chosen();
		});
 		
		checker = initValidator();
	});
	
	function initValidator() {
		return $("#parent_form").validate({
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
				"parentRelation":{
					required : true
				},
				"rank":{
					required : true
				},
				"name":{
					required : true,
					maxlength:10
				},
				"mobile":{
					required : true,
					remote : {
							async : true,
							type : "GET",
							url : "${pageContext.request.contextPath}/teach/parent/checkMobile",
							data : {
								'id':'${parentVo.id}',
								'dxlx' : 'mobile',
								'mobile' : function() {
								return $("#mobile").val();
							}
							}
					}
				},
				
				"email":{
					required : false,
					email:true
				}
				
			},
			messages : {
				"mobile":"电话号码不能用！",
				"email":"邮箱中需要有'@'和'.'，'.'后面至少需要有两位！"
			}
		});
	}
	
	//保存或更新修改
	function saveOrUpdate() {
		if (checker.form()) {
			var loader = new loadLayer();
			var $requestData = formData2JSONObj("#parent_form");
			var url = "${ctp}/teach/parent/modifyParent";
			$requestData._method = "put";
			loader.show();
			$.post(url, $requestData, function(data, status) {
				if("success" === status) {
					data = eval("(" + data + ")");
					if("success" === data.info) {
						$.success('保存成功');
						if(parent.core_iframe != null) {
 							parent.core_iframe.window.location.reload();
 						} else {
 							parent.window.location.reload();
 						}
						$.closeWindow();
					} else {
						$.error(data.info);
						
					}
				}else{
					$.error("服务器异常");
				}
				loader.close();
			});
		}
	}
	
</script>
</html>