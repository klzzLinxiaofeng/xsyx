<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加报名学生</title>
<%@ include file="/views/embedded/common.jsp"%> 
<style>
	.myerror {
	color: red !important;
	width: 35%;
	display: inline-block;
	padding-left: 10px;
}
.row-fluid .span4 {
	width: 200px;
}

.form-horizontal .control-group{
	width:45%;
	float:left;
}
.form-horizontal .control-label{
width:80px;
}
.form-horizontal .controls{
	margin-left:100px;
}
</style>
</head>
<body style="background-color:cdd4d7 !important">
	<div class="row-fluid">
		<div class="span12">
			<div class="content-widgets"  style="margin-bottom:0">
				<div class="widget-container" style="padding:20px 0 0;">
					<form class="form-horizontal" id="newStudent_form" name="newStudent_form" action="javascript:void(0);">
						<div class="control-group" style="margin-bottom:-5px;">
							<label class="control-label"><font style="color:red">*</font>姓名</label>
							<div class="controls"><input type="text" name="name" id="name" class="span1"/></div>
						</div>
						<div class="control-group">
							<label class="control-label">英文名</label>
							<div class="controls">
								<input type="text" name="englishName" id="englishName" value="" class="span2">
							</div>
						</div>

						<div class="control-group">
							<label class="control-label"><font style="color:red">*</font>性别</label>
							<div class="controls">
								<select id="sex" name="sex" class="span2"></select>
							</div>
						</div>
						
						<div class="control-group">
							<label class="control-label"><font style="color:red">*</font>出生日期</label>
							<div class="controls">
								<input type="text" id="birthDate" name="birthDate" value="" class="span2" placeholder="出生日期" onclick="WdatePicker();"/>
							</div>
						</div>
						
						<div class="control-group">
							<label class="control-label"><font style="color:red">*</font>学生类型</label>
							<div class="controls">
								<select id="studentType" name="studentType" class="span2"></select>
							</div>
						</div>
						
						
						<div class="control-group">
							<label class="control-label"><font style="color:red">*</font>学年</label>
							<div class="controls">
								<select id="schoolYear" name="schoolYear" class="span2"></select>
							</div>
						</div>
						<div class="clear"></div>
						<div class="control-group">
							<label class="control-label">证件类型</label>
							<div class="controls">
								<select name="idCardType" id="idCardType" class="span2">
								</select>
							</div>
						</div>
						
						<div class="control-group">
							<label class="control-label">证件号码</label>
							<div class="controls">
								 <input type="text" name="idCardNumber" id="idCardNumber" class="span2"/>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">国籍</label>
							<div class="controls">
								<select name="nationality" id="nationality" class="span2"></select>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">民族</label>
							<div class="controls">
								<select name="race" id="race" class="span2"></select>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">籍贯</label>
							<div class="controls">
								 <input type="text" id="nativePlace" name="nativePlace" class="span2"/>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">出生地</label>
							<div class="controls">
								 <input type="text" id="birthPlace" name="birthPlace" class="span2"/>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">户口性质</label>
							<div class="controls">
								 <select name="residenceType" id="residenceType" class="span2"></select>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">户口所在地</label>
							<div class="controls">
								 <input type="text" id="residenceAddress" name="residenceAddress" class="span2"/>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">现地址</label>
							<div class="controls">
								 <input type="text" id="address" name="address" class="span2"/>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">居住地址</label>
							<div class="controls">
								 <input type="text" id="resideAddress" name="resideAddress" class="span2"/>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">政治面貌</label>
							<div class="controls">
								<select id="politicalStatus" name="politicalStatus" class="span2"></select>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">宗教信仰</label>
							<div class="controls">
								<select name="religion" id="religion" class="span2"></select>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">是否独生子</label>
							<div class="controls">
								 <select name="isOnlyChild" id="isOnlyChild" class="span2">
									<option value="1">是</option>
									<option value="0">否</option>
								</select>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label"><font style="color:red">*</font>电话</label>
							<div class="controls">
								 <input type="text" id="telephone" name="telephone" class="span2"/>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">邮件</label>
							<div class="controls">
								 <input type="text" id="email" name="email" class="span2"/>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label"><font style="color:red">*</font>招生人</label>
							<div class="controls">
								 <input type="text" id="enrollPerson" name="enrollPerson" class="span2"/>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">招生电话</label>
							<div class="controls">
								 <input type="text" id="enrollTel" name="enrollTel" class="span2"/>
							</div>
						</div>
						<div class="clear"></div>
						<div class="form-actions tan_bottom">
							<button class="btn btn-warning" type="button" onclick="saveOrUpdate();">确定</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	var checker;
	$(function () {
		checker = initValidator();
		
		//国籍
		$.jcGcSelector("#nationality", {tc : "GB-GJ"}, "", function() {
			
		});
		//民族
		$.jcGcSelector("#race", {tc : "GB-MZ"}, "", function() {
			
		});
		//户口性质
		$.jcGcSelector("#residenceType", {tc : "GB-HKLB"}, "", function() {
			
		});
		//政治面貌
		$.jcGcSelector("#politicalStatus", {tc : "GB-ZZMM"}, "", function() {
			
		});
		//宗教信仰
		$.jcGcSelector("#religion", {tc : "GB-ZJXY"}, "", function() {
			
		});
		//性别
		$.jcGcSelector("#sex", {tc : "GB-XB"}, "", function() {
			
		});
		//证件类型
		$.jcGcSelector("#idCardType", {tc : "JY-SFZJLX"}, "", function() {
			
		});
		//学生类别
		$.jcGcSelector("#studentType", {tc : "JY-XSLB","level":"1"}, "", function() {
			
		});
		
		$.initCascadeSelector({
		    "type" : "schoolYear",
			"yearSelectId" : "schoolYear", //学年select标签ID 默认为xn
			"selectOne" : true
		});
		
	});
	
	function getFullName(obj){
		var name = $(obj).text();
		$("#name").val(name);
	}
	
	
	function initValidator() {
		return $("#newStudent_form")
			.validate(
					{
						errorClass : "myerror",
						rules : {
							"name" : {
								required : true
							},
							"sex":{
								required : true
							},
							"birthDate":{
								required : true
							},
							"telephone":{
								required : true,
								isTel : true
							},
							"enrollPerson":{
								required : true
							},
							"studentType":{
								required : true
							},
							"schoolYear":{
								required : true
							}
						},
						messages : {
							"name":{
								required : "姓名必填"
							},
							"sex":{
								required : "性别必选"
							},
							"birthDate":{
								required : "出生日期必填"
							},
							"telephone":{
								required : "电话必填",
								isTel : "请输入合法的电话号码"
							},
							"enrollPerson":{
								required : "招生人必填"
							},
							"studentType":{
								required : "学生类型必填"
							},
							"schoolYear":{
								required : "学年必填"
							}
						}
					});
	}

	function saveOrUpdate() {
		if(checker.form()){
			var loader = new loadLayer();
			var $requestData = formData2JSONObj("#newStudent_form");
			var url = "${pageContext.request.contextPath}/entrollStudent/newStudent/addNewStudent";
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
					}else if("idCardNumberExit" === data.info){
						$.error("该身份证已存在");
					}else if("telephoneNumberExit" === data.info){
						$.error("该号码已存在");
					} else {
						$.error("保存失败");
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