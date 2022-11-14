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
<a id="downLoadExcel"  class="a2" href="" class="a2" ></a>
<form name="studentAidItemForm" id="studentAidItemForm" action="${pageContext.request.contextPath}/teach/studentaid/downLoadExcel" method="post">
	<div class="row-fluid">
		<div class="span12">
			<div class="content-widgets" style="margin-bottom:0">
				<div class="widget-container" style="padding:20px 0 0;">
					<p class="all_select"><input type="checkbox" onclick="checkAll();" name="checkAllids" id="checkAllids" />全选</p>
					<input type="hidden" name="ids" id="ids" value="" />
					<input type="hidden" name="schoolYear" id="schoolYear" value="${condition.schoolYear}" />
					<input type="hidden" name="gradeId" id="gradeId" value="${condition.gradeId}" />
					<input type="hidden" name="teamId" id="teamId" value="${condition.teamId}" />
					<input type="hidden" name="studentId" id="studentId" value="${condition.studentId}" />
					<div class="shuju_list">
						<ul>
							
							<li><input type="checkbox" name="id" checked="checked" value="schoolYearName:学年" />学年</li>
			                <li><input type="checkbox" name="id" checked="checked" value="gradeName:年级" />年级</li>
			                <li><input type="checkbox" name="id" checked="checked" value="teamName:班级" />班级</li>
			                <li><input type="checkbox" name="id" checked="checked" value="studentName:姓名" />姓名</li>
			                <li><input type="checkbox" name="id" checked="checked" value="povertyCategory:贫困类别" />贫困类别</li>
			                <li><input type="checkbox" name="id" checked="checked" value="povertyCauses:贫困原因" />贫困原因</li>
			                <li><input type="checkbox" name="id" checked="checked" value="aidForm:助困形式" />助困形式</li>
			                <li><input type="checkbox" name="id"  checked="checked" value="oneIncome:家庭收入/人口" />家庭收入/人口</li>
			                <li><input type="checkbox" name="id" checked="checked"  value="aidAmount:资助金额" />资助金额</li>
			                <li><input type="checkbox" name="id"  checked="checked" value="aidDay:资助日期" />资助日期</li>
			                <li><input type="checkbox" name="id" checked="checked"  value="remark:备注" />备注</li>
			               
						</ul>
						<div class="clear"></div>
					</div>
					<div class="form-actions tan_bottom">
						<button  class="btn btn-warning" type="button" id="saveButtion"  onclick="exportStudentAidInfo();">导出</button>
					</div>
				</div>
			</div>
		</div>
	</div>
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
	
	
	//导出对话框
	function exportStudentAidInfo(){
		alert("s");
		var ids = document.getElementsByName("id");
		var szoptions="";
		var j = 0
		alert(ids.length);
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
		/* var studentId = $("#studentId").val();
		var teamId = $("#teamId").val();
		var schoolYear = $("#schoolYear").val();
		var gradeId = $("#gradeId").val();
		
		
		var param = "studentId="+studentId+"&"+"teamId="+teamId+"&"+"schoolYear="+schoolYear+"&"+"gradeId="+gradeId+"&"+"ids="+ids; */
	   
		$("#studentAidItemForm").submit(); 
	}
</script>
</body>
</html>
