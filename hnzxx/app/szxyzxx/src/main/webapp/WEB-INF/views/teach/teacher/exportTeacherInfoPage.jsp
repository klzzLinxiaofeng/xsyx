<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/views/embedded/common.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${sca:getDefaultSchoolName()}</title>
<style type="text/css">
input[type="checkbox"]{
	position:relative;
	top:-3px;
	margin-right:8px;
}
</style>
</head>
<body style="background-color:cdd4d7 !important">

<form name="teacherItemForm" id="teacherItemForm" method="post">
	<div class="row-fluid">
		<div class="span12">
			<div class="content-widgets" style="margin-bottom:0">
				<div class="widget-container" style="padding:20px 0 0;">
					<p class="all_select"><input type="checkbox" onclick="checkAll();" name="checkAllids" id="checkAllids" />全选</p>
					
					<div class="shuju_list">
						<ul>
							<li><input type="checkbox" name="id" checked="checked" value="name:姓名" />姓名</li>
			                <li><input type="checkbox" name="id" checked="checked" value="sex:性别" />性别</li>
			                <li><input type="checkbox" name="id" checked="checked" value="englishName:英文名" />英文名</li>
			                <li><input type="checkbox" name="id" checked="checked" value="userName:用户名" />用户名</li>
			                <li><input type="checkbox" name="id"  value="birthDate:出生日期" />出生日期</li>
			                <li><input type="checkbox" name="id"  value="jobNumber:工号" />工号</li>
			                <li><input type="checkbox" name="id"  value="certificateType:证件类型" />证件类型</li>
			                <li><input type="checkbox" name="id"  value="mobile:手机号码" />手机号码</li>
			                <li><input type="checkbox" name="id"  value="certificateNum:证件号码" />证件号码</li>
			                <li><input type="checkbox" name="id" checked="checked" value="workBeginDate:参加工作时间" />参加工作时间</li>
			                <li><input type="checkbox" name="id"  value="nationality:国籍" />国籍</li>
			                <li><input type="checkbox" name="id"  value="enrollDate:入职时间" />入职时间</li>
			                <li><input type="checkbox" name="id"  value="nation:民族" />民族</li>
			                <li><input type="checkbox" name="id"  value="leaveDate:离职时间" />离职时间</li>
			                <li><input type="checkbox" name="id"  value="nativePlace:籍贯" />籍贯</li>
			                <li><input type="checkbox" name="id"  value="position:职务" />职务</li>
			                <li><input type="checkbox" name="id"  value="birthPlace:出生地" />出生地</li>
			                <li><input type="checkbox" name="id"  value="occupationCode:岗位职业" />岗位职业</li>
			                <li><input type="checkbox" name="id"  value="marriage:婚姻状况" />婚姻状况</li>
			                <li><input type="checkbox" name="id"  value="qualification:学历" />学历</li>
			                <li><input type="checkbox" name="id"  value="political:政治面貌" />政治面貌</li>
			                <li><input type="checkbox" name="id"  value="specialty:特长" />特长</li>
			                <li><input type="checkbox" name="id"  value="religiousBelief:宗教信仰" />宗教信仰</li>
			                <li><input type="checkbox" name="id"  value="postStaffing:岗位编制" />岗位编制</li>
			                <li><input type="checkbox" name="id"  value="register:户口性质" />户口性质</li>
			                <li><input type="checkbox" name="id"  value="jobState:在职状态" />在职状态</li>
			                <li><input type="checkbox" name="id"  value="registerPlace:户口所在地" />户口所在地</li>
			                <li><input type="checkbox" name="id"  value="telephone:办公室电话" />办公室电话</li>
			                <li><input type="checkbox" name="id"  value="nowAddress:现地址" />现地址</li>
			                <li><input type="checkbox" name="id"  value="email:邮件" />邮件</li>
			                <li><input type="checkbox" name="id"  value="liveAddress:居住地址" />居住地址</li>
			                <li><input type="checkbox" name="id"  value="remark:备注" />备注</li>
						</ul>
						<div class="clear"></div>
					</div>
					<div class="form-actions tan_bottom">
						<button  class="btn btn-warning" type="button" id="saveButtion"  onclick="exportTeacherInfo();">导出</button>
					</div>
					
				</div>
			</div>
		</div>
	</div>
</form>

<form name="teacherComplexForm" id="teacherComplexForm" action="${pageContext.request.contextPath}/teach/teacher/exportTeacherInfo" method="post">
	<input type="hidden" name="ids" id="ids" value="" />
	<input type="hidden" name="name" id="name" value="${name}" />
</form>
<script>
	function checkAll() {
		var ids = document.getElementsByName("id");
		if (document.getElementById("checkAllids").checked) {
			for ( var i = 0; i < ids.length; i++) {
				ids[i].checked = "checked";
			}
		} else {
			for ( var i = 0; i < ids.length; i++) {
				ids[i].checked = "";
			}
		}
	}
	

		function exportTeacherInfo(){
			var loader = new loadLayer();
			loader.show();
			var ids = document.getElementsByName("id");
			var szoptions="";
			var j = 0
			for ( var i = 0; i < ids.length; i++) {
				if (ids[i].checked == true) {
					j++
					if(szoptions==""){
						szoptions += ids[i].value;
					}else{
						szoptions += ","+ids[i].value;
					}
				}
			}
			if (j <= 0) {
				$.success("请选择要导出的信息");
				return false;
			}
			
			$("#ids").val(szoptions);
			$("#teacherComplexForm").submit();
			$("#saveButtion").prop("disabled",true);
			loader.close();

	}
</script>
</body>
</html>
