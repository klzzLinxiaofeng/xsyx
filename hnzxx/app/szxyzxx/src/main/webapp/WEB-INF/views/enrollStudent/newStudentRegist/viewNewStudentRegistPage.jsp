<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/views/embedded/common.jsp"%>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加报名学生</title>
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
					<form class="form-horizontal" id="newStudent_form" name="newStudent_form" action="javascript:void(0);">
						<div class="control-group">
							<label class="control-label"><font style="color:red">*</font>姓名</label>
							<div class="controls"><input type="text" name="name" id="name" value="${newStudent.name }" readonly="readonly" class="span1"/></div>
						</div>
						<div class="control-group">
							<label class="control-label">英文名</label>
							<div class="controls">
								<input type="text" name="englishName" id="englishName" value="${newStudent.englishName }" readonly="readonly" class="span2">
							</div>
						</div>

						<div class="control-group">
							<label class="control-label">性别</label>
							<div class="controls">
								<select id="sex" name="sex" class="span2" disabled="disabled"></select>
							</div>
						</div>
						
						<div class="control-group">
							<label class="control-label">出生日期</label>
							<div class="controls">
								<input type="text" id="birthDate" name="birthDate" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${newStudent.birthDate }"></fmt:formatDate>" readonly="readonly" class="span2" placeholder="出生日期"/>
							</div>
						</div>
						
						<div class="control-group">
							<label class="control-label">证件类型</label>
							<div class="controls">
								<select name="idCardType" id="idCardType" disabled="disabled" class="span2">
								</select>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">证件号码</label>
							<div class="controls">
								 <input type="text" name="idCardNumber" value="${newStudent.idCardNumber }" id="idCardNumber" readonly="readonly" class="span2"/>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">国籍</label>
							<div class="controls">
								<select name="nationality" id="nationality" class="span2" disabled="disabled"></select>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">民族</label>
							<div class="controls">
								<select name="race" id="race" class="span2" disabled="disabled"></select>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">籍贯</label>
							<div class="controls">
								 <input type="text" id="nativePlace" name="nativePlace" value="${newStudent.nativePlace }" readonly="readonly" class="span2"/>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">出生地</label>
							<div class="controls">
								 <input type="text" id="birthPlace" name="birthPlace" value="${newStudent.birthPlace }" readonly="readonly" class="span2"/>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">户口性质</label>
							<div class="controls">
								 <select name="residenceType" id="residenceType" class="span2" disabled="disabled"></select>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">户口所在地</label>
							<div class="controls">
								 <input type="text" id="residenceAddress" name="residenceAddress" value="${newStudent.residenceAddress }" readonly="readonly" class="span2"/>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">现地址</label>
							<div class="controls">
								 <input type="text" id="address" name="address" value="${newStudent.address }" readonly="readonly" class="span2"/>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">居住地址</label>
							<div class="controls">
								 <input type="text" id="resideAddress" name="resideAddress" value="${newStudent.resideAddress }" readonly="readonly" class="span2"/>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">政治面貌</label>
							<div class="controls">
								<select id="politicalStatus" name="politicalStatus" class="span2" disabled="disabled"></select>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">宗教信仰</label>
							<div class="controls">
								<select name="religion" id="religion" class="span2" disabled="disabled"></select>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">是否独生子</label>
							<div class="controls">
								 <select name="isOnlyChild" id="isOnlyChild" disabled="disabled" class="span2">
									<option value="1">是</option>
									<option value="0">否</option>
								</select>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">电话</label>
							<div class="controls">
								 <input type="text" id="telephone" name="telephone" value="${newStudent.telephone }" readonly="readonly" class="span2"/>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">邮件</label>
							<div class="controls">
								 <input type="text" id="email" name="email" value="${newStudent.email }" readonly="readonly" class="span2"/>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">招生人</label>
							<div class="controls">
								 <input type="text" id="enrollPerson" name="enrollPerson" value="${newStudent.enrollPerson }" readonly="readonly" class="span2"/>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">招生电话</label>
							<div class="controls">
								 <input type="text" id="enrollTel" name="enrollTel" value="${newStudent.enrollTel }" readonly="readonly" class="span2"/>
							</div>
						</div>
						<div class="clear"></div>
<!-- 						<div class="form-actions tan_bottom"> -->
<!-- 							<button class="btn btn-warning" type="button" onclick="saveOrUpdate();">确定</button> -->
<!-- 						</div> -->
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
		$.jcGcSelector("#nationality", {tc : "GB-GJ"}, "${newStudent.nationality }", function() {
			
		});
		//民族
		$.jcGcSelector("#race", {tc : "GB-MZ"}, "${newStudent.race }", function() {
			
		});
		//户口性质
		$.jcGcSelector("#residenceType", {tc : "GB-HKLB"}, "${newStudent.residenceType }", function() {
			
		});
		//政治面貌
		$.jcGcSelector("#politicalStatus", {tc : "GB-ZZMM"}, "${newStudent.politicalStatus }", function() {
			
		});
		//宗教信仰
		$.jcGcSelector("#religion", {tc : "GB-ZJXY"}, "${newStudent.religion }", function() {
			
		});
		//性别
		$.jcGcSelector("#sex", {tc : "GB-XB"}, "${newStudent.sex }", function() {
			
		});
		//证件类型
		$.jcGcSelector("#idCardType", {tc : "JY-SFZJLX"}, "${newStudent.idCardType }", function() {
			
		});
	});

</script>
</html>