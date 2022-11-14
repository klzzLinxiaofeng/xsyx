<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/views/embedded/common.jsp"%>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改新生注册信息</title>
<style>
	.myerror {
	color: red !important;
	width: 34%;
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
					<form class="form-horizontal" id="modifyNewStudent_form" name="modifyNewStudent_form" action="javascript:void(0);">
						<input type="hidden" id="id" name="id" value="${newStudent.id }"/>
						<div class="control-group">
							<label class="control-label"><font style="color:red">*</font>姓名</label>
							<div class="controls">
								<input type="text" name="name" id="name" value="${newStudent.name }" class="span1"/>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">英文名</label>
							<div class="controls">
								<input type="text" name="englishName" id="englishName" value="${newStudent.englishName }" class="span2">
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
								<input type="text" id="birthDate" name="birthDate" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${newStudent.birthDate }"></fmt:formatDate>" class="span2" placeholder="出生日期" onclick="WdatePicker();"/>
							</div>
						</div>
						
						<div class="control-group">
							<label class="control-label"><font style="color:red">*</font>学生类型</label>
							<div class="controls">
								<select id="studentType" name="studentType"></select>
							</div>
						</div>
						
						<div class="control-group">
							<label class="control-label"><font style="color:red">*</font>学年</label>
							<div class="controls">
								<select id="schoolYear" name="schoolYear"></select>
							</div>
						</div>
						
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
								 <input type="text" name="idCardNumber" id="idCardNumber" value="${newStudent.idCardNumber }" class="span2"/>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">国籍</label>
							<div class="controls">
								<select name="nationality" id="nationality"  class="span2"></select>
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
								 <input type="text" id="nativePlace" name="nativePlace" value="${newStudent.nativePlace }" class="span2"/>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">出生地</label>
							<div class="controls">
								 <input type="text" id="birthPlace" name="birthPlace" value="${newStudent.birthPlace }" class="span2"/>
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
								 <input type="text" id="residenceAddress" name="residenceAddress" value="${newStudent.residenceAddress }" class="span2"/>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">现地址</label>
							<div class="controls">
								 <input type="text" id="address" name="address" value="${newStudent.address }" class="span2"/>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">居住地址</label>
							<div class="controls">
								 <input type="text" id="resideAddress" name="resideAddress" value="${newStudent.resideAddress }" class="span2"/>
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
								 <input type="text" id="telephone" name="telephone" value="${newStudent.telephone }" class="span2"/>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">邮件</label>
							<div class="controls">
								 <input type="text" id="email" name="email" value="${newStudent.email }" class="span2"/>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label"><font style="color:red">*</font>招生人</label>
							<div class="controls">
								 <input type="text" id="enrollPerson" name="enrollPerson" value="${newStudent.enrollPerson }" class="span2"/>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">招生电话</label>
							<div class="controls">
								 <input type="text" id="enrollTel" name="enrollTel" value="${newStudent.enrollTel }" class="span2"/>
							</div>
						</div>
						<div class="clear"></div>
						<div class="form-actions tan_bottom">
							<button class="btn btn-warning" type="button" onclick="saveOrUpdate();">修改</button>
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
	//国籍
	$.jcGcSelector("#nationality", {tc : "GB-GJ"}, "${newStudent.nationality}", function() {
		
	});
	//民族
	$.jcGcSelector("#race", {tc : "GB-MZ"}, "${newStudent.race}", function() {
		
	});
	//户口性质
	$.jcGcSelector("#residenceType", {tc : "GB-HKLB"}, "${newStudent.residenceType}", function() {
		
	});
	//政治面貌
	$.jcGcSelector("#politicalStatus", {tc : "GB-ZZMM"}, "${newStudent.politicalStatus}", function() {
		
	});
	//宗教信仰
	$.jcGcSelector("#religion", {tc : "GB-ZJXY"}, "${newStudent.religion}", function() {
		
	});
	//性别
	$.jcGcSelector("#sex", {tc : "GB-XB"}, "${newStudent.sex}", function() {
		
	});
	//证件类型
	$.jcGcSelector("#idCardType", {tc : "JY-SFZJLX"}, "${newStudent.idCardType}", function() {
		
	});
	//学生类别
	$.jcGcSelector("#studentType", {tc : "JY-XSLB","level":"1"}, "${newStudent.studentType}", function() {
		
	});
	//学年
	$.SchoolYearSelector({
		"selector" : "#schoolYear",
		"condition" : {},
		"selectedVal" : "${newStudent.schoolYear}",
		"afterHandler" : function(selector) {
			
		}
	});

	checker = initValidator();
});

function getFullName(obj){
	var name = $(obj).text();
	$("#name").val(name);
}


function initValidator() {
	return $("#modifyNewStudent_form")
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
		var $requestData = formData2JSONObj("#modifyNewStudent_form");
		var url = "${pageContext.request.contextPath}/entrollStudent/newStudent/updateNewStudent";
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