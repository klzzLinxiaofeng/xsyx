<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学生档案查看</title>
<%@ include file="/views/embedded/common.jsp"%>
<%@ taglib prefix="entity" uri="http://www.jiaoxueyun.com/entity"%>
<!-- Load TinyMCE -->
<script src="${pageContext.request.contextPath}/res/plugin/falgun/js/tiny_mce/jquery.tinymce.js"></script>
<script src="${pageContext.request.contextPath}/res/plugin/falgun/js/custom.js"></script>
<script src="${pageContext.request.contextPath}/res/plugin/falgun/js/respond.min.js"></script>
<script src="${pageContext.request.contextPath}/res/plugin/falgun/js/ios-orientationchange-fix.js"></script>
<style>
input[type="radio"]{
	margin:0 5px;
	position:relative;
	top:-1px;
}
.form-horizontal{
	padding-bottom:0;
}
.xs_middle_1 .span1{
	width:70px;
}
.xs_middle_1 .span2{
	width:80px;
}
.xs_middle_1 .span3{
	width:100px;
}
.chzn-container{
	float:left;
}
#set_table table{
	width: 1200px;
    max-width: none;
}
</style>
<script>
$(function() {
	/* text-editor begin*/
// 	$('textarea.tinymce-simple').tinymce({
// 		script_url : '${pageContext.request.contextPath}/res/plugin/falgun/js/tiny_mce/tiny_mce.js',
// 		theme : "simple"
// 		});
	/* text-editor end*/
	/* 基本档案  begin*/
	$(".xs_left_1 ul li a").click(function(){
		$(".xs_left_1 ul li").removeClass("on");
		$(this).parent().addClass("on");
		var i=$(this).parent().index();
		$(".xs_middle_1 .xsbj_div").hide();
		$(".xs_middle_1 .xsbj_div").eq(i).show();
	});
	/* 基本档案  end*/
	/* table第一行  begin*/
	$(".table-striped  tr").each(function(){
		$(this).children("").eq(0).css("padding-left","14px");
	});
	$(".table-striped tbody").each(function(){
		$(this).children("tr").eq(0).children().css("background-color","#DAF0FA"); 
	});
	/* table第一行 end*/
	var m=$(".xszc .dacx .da_select li").length;
	$(".xszc .dacx .da_select li a").click(function(){
		$(".xszc .dacx .da_select li").removeClass("on");
		$(this).parent().addClass("on");
		var i=$(this).parent().index();
		$(".xszc .da_div .xsda").hide();
		$(".xszc .da_div .xsda").eq(i).show();
	});
	$(".xszc .dacx .da_select .left").click(function(){
		var j=$('.xszc .dacx .da_select li[class|="on"]').index();
		if(j>0){
		var k=j-1;
		$(".xszc .dacx .da_select li").removeClass("on");
		$(".xszc .dacx .da_select li").eq(k).addClass("on");
		$(".xszc .da_div .xsda").hide();
		$(".xszc .da_div .xsda").eq(k).show();
		var left = $(".xszc .dacx .da_select ul" ).css("left");
		 var left1 = parseInt(left);
		if(left1<0){
			$(".xszc .dacx .da_select ul").css("left",left1+67)
		}
		}
	});
	$(".xszc .dacx .da_select .right").click(function(){
		var j=$('.xszc .dacx .da_select li[class|="on"]').index();
		if(j<m-1){
		var l=j+1;
		$(".xszc .dacx .da_select li").removeClass("on");
		$(".xszc .dacx .da_select li").eq(l).addClass("on");
		$(".xszc .da_div .xsda").hide();
		$(".xszc .da_div .xsda").eq(l).show();
		if(7<j){
			var left = $(".xszc .dacx .da_select ul" ).css("left");
			 var left1 = parseInt(left) - 67;
			$(".xszc .dacx .da_select ul").css("left",left1)
		}
		};
		
		
	});
	
// 	切换家庭成员
	$(".family_select ul li a").click(function(){
		$(".family_select ul li").removeClass("on");
		$(this).parent().addClass("on");
		var i=$(this).parent().index();
		$(".family_div .f_div").hide();
		$(".family_div .f_div").eq(i).show();
	})
	});
</script>
</head>
<body style="background-color:#F3F3F3 !important">
	<div class="row-fluid" >
		<div class="span12">
			<div class="content-widgets" style="margin-bottom:0">
				<div class="widget-container" style="padding:20px 0 0;">
					<form class="form-horizontal tan_form" id="" action="javascript:void(0);">
						<div class="xszc" style="width:877px;">
							<div class="dacx">
								<div class="da_select">
									<a class="left" href="javascript:void(0)"></a>
									<div class="se_middle">
										<ul style="left:0;">
										  <li class="on"><a href="javascript:void(0)">基本档案</a></li>
										  <li><a href="javascript:void(0)" onclick="findStudentAid('${studentId}');">学生资助</a></li>
										  <li><a href="javascript:void(0)" onclick="findStudentPunish('${studentId}');">学生处分</a></li>
										  <li><a href="javascript:void(0)" onclick="findStudentAward('${studentId}');">学生奖励</a></li>
										  <li><a href="javascript:void(0)" onclick="findStudentMoral('${studentId}');">学生德育</a></li>
										  <li><a href="javascript:void(0)" onclick="findStudentAttendance('${studentId}');">学生考勤</a></li>
										  <li><a href="javascript:void(0)" onclick="findStudentHealth('${studentId}');">学生健康</a></li>
<!-- 										  <li><a href="javascript:void(0)">学生留级</a></li> -->
<!-- 										  <li><a href="javascript:void(0)">学生跳级</a></li> -->
										  <li><a href="javascript:void(0)" onclick="findStudentScore('${studentId}');">学生成绩</a></li>
										</ul>
									</div>
									<a class="right" href="javascript:void(0)"></a>
								</div>
								<div class="da_div">
									<div class="xsda" style="padding:9px 0 0 10px;width:670px;height:572px; display:block;">
										<div class="xs_left_1">
											<ul>
												<li class="on"><a href="javascript:void(0)">基本信息</a></li>
												<!-- <li><a href="javascript:void(0)">戶籍信息</a></li>
												<li><a href="javascript:void(0)">常用信息</a></li>
												<li><a href="javascript:void(0)">家庭成员</a></li>
												<li><a href="javascript:void(0)">备注</a></li> -->
												<li><a href="javascript:void(0)">辅助信息</a></li>
												<li><a href="javascript:void(0)">学籍信息</a></li>
												<li><a href="javascript:void(0)">联系信息</a></li>
												<li><a href="javascript:void(0)">扩展信息</a></li>
												<li><a href="javascript:void(0)">交通方式</a></li>
												<li><a href="javascript:void(0)">家庭成员</a></li>
												<li><a href="javascript:void(0)">备注</a></li>
												<li><a href="javascript:void(0)">学习简历</a></li>
											</ul>
										</div>
										<div class="xs_middle_1" style="border-right:0 none;border-bottom:0 none;height:573px;">
											<div class="xsbj_div" >
													<div class="control-group">
														<label class="control-label">姓名</label>
														<div class="controls">
															<input type="text" value="${studentData.basic.name }" disabled="disabled" />
														</div>
													</div>
													<div class="control-group">
														<label class="control-label">性别</label>
														<div class="controls">
															<select id="sex" name="sex" disabled="disabled"></select>
														</div>
													</div>
													<div class="control-group">
														<label class="control-label">出生日期</label>
														<div class="controls">
															<input type="text" value='<fmt:formatDate pattern="yyyy-MM-dd" value="${studentData.basic.birthday }"></fmt:formatDate>' disabled="disabled" />
														</div>
													</div>
													<div class="control-group">
														<label class="control-label">出生地</label>
														<div class="controls">
<%-- 															<input type="text" value="${studentData.basic.birthPlace }" disabled="disabled" /> --%>
															<select id="sj1" class="{required: true}" disabled="disabled" style="width:30%;float:left"></select>
															<select id="shij1" class="{required: true}" disabled="disabled" style="width:30%"></select>
															<select id="qxj1" name="birthPlaceCode" class="{required: true}" disabled="disabled" style="width:30%"></select>
														</div>
													</div>
													<div class="control-group">
														<label class="control-label">籍贯</label>
														<div class="controls">
<%-- 															<input type="text" value="${studentData.basic.nativePlace }" disabled="disabled" /> --%>
															<select id="sj" class="{required: true}" disabled="disabled" style="width:30%;float:left"></select>
															<select id="shij" class="{required: true}" disabled="disabled" style="width:30%"></select>
															<select id="qxj" name="nativePlaceCode" class="{required: true}" disabled="disabled" style="width:30%"></select>
														</div>
													</div>
													<div class="control-group">
														<label class="control-label">民族</label>
														<div class="controls">
															<select name="race" id="race" disabled="disabled"></select>
														</div>
													</div>
													<div class="control-group">
														<label class="control-label">国籍/地区</label>
														<div class="controls">
															<select name="nationality" id="nationality" disabled="disabled"></select>
														</div>
													</div>
													<div class="control-group">
														<label class="control-label">身份证件类型</label>
														<div class="controls">
															<select name="idCardType" id="idCardType" disabled="disabled"></select>
														</div>
													</div>
													<div class="control-group">
														<label class="control-label">身份证件号</label>
														<div class="controls">
															<input type="text" value="${studentData.basic.idCardNumber }" disabled="disabled" />
														</div>
													</div>
													<div class="control-group">
														<label class="control-label">港澳台侨外</label>
														<div class="controls">
															<select name="abroadCode" id="abroadCode" disabled="disabled"></select>
														</div>
													</div>
													<div class="control-group">
														<label class="control-label">政治面貌</label>
														<div class="controls">
															<select id="politicalStatus" name="politicalStatus" disabled="disabled"></select>
														</div>
													</div>
													<div class="control-group">
														<label class="control-label">健康状况</label>
														<div class="controls">
															<select id="healthStatus" name="healthStatus" disabled="disabled"></select>
														</div>
													</div>
													<div class="control-group">
														<label class="control-label">血型</label>
														<div class="controls">
															<select name="bloodType" id="bloodType" disabled="disabled"></select>
														</div>
													</div>
											</div>
											<div class="xsbj_div" style="display:none">	
													<div class="control-group">
														<label class="control-label">姓名拼音</label>
														<div class="controls">
															<input type="text" value="${studentData.assist.pinyinName }" disabled="disabled" />
														</div>
													</div>
													<div class="control-group">
														<label class="control-label">曾用名</label>
														<div class="controls">
															<input type="text" value="${studentData.assist.usedName }" disabled="disabled" />
														</div>
													</div>
													<div class="control-group">
														<label class="control-label">身份证有效期</label>
														<div class="controls">
															<input type="text" Value='<fmt:formatDate pattern="yyyy-MM-dd" value="${studentData.assist.idCardDate }"></fmt:formatDate>' disabled="disabled" />
														</div>
													</div>
													<div class="control-group">
														<label class="control-label">户口所在地</label>
														<div class="controls">
<%-- 															<input type="text" value="${studentData.assist.residenceAddress }" disabled="disabled" /> --%>
															<select id="sj2" class="{required: true}" disabled="disabled" style="width:30%;float:left"></select>
															<select id="shij2" class="{required: true}" disabled="disabled" style="width:30%"></select>
															<select id="qxj2" name="residenceAddressCode" class="{required: true}" disabled="disabled" style="width:30%"></select>
														</div>
													</div>
													<div class="control-group">
														<label class="control-label">户口性质</label>
														<div class="controls">
															<select name="residenceType" id="residenceType" disabled="disabled"></select>
														</div>
													</div>
													<div class="control-group">
														<label class="control-label">特长</label>
														<div class="controls">
															<input type="text" value="${studentData.assist.specialSkill }" disabled="disabled" />
														</div>
													</div>
											</div>
											<div class="xsbj_div" style="display:none">	
													<div class="control-group">
														<label class="control-label">学籍辅号</label>
														<div class="controls">
															<input type="text" value="${studentData.archive.studentNumber }" disabled="disabled" />
														</div>
													</div>
													<div class="control-group">
														<label class="control-label">班内学号</label>
														<div class="controls">
															<input type="text" value="${studentData.archive.number }" disabled="disabled" />
														</div>
													</div>
<!-- 													<div class="control-group"> -->
<!-- 														<label class="control-label">学生类别</label> -->
<!-- 														<div class="controls"> -->
<!-- 															<select name="studentType" id="studentType" disabled="disabled"></select> -->
<!-- 														</div> -->
<!-- 													</div> -->
													<div class="control-group">
														<label class="control-label">年级</label>
														<div class="controls">
															<input type="text" value="${gradeName}" disabled="disabled" />
														</div>
													</div>
													<div class="control-group">
														<label class="control-label">班级</label>
														<div class="controls">
															<input type="text" value="${teamName }" disabled="disabled" />
														</div>
													</div>
													<div class="control-group">
														<label class="control-label">入学时间</label>
														<div class="controls">
															<input type="text" value='<fmt:formatDate pattern="yyyy-MM-dd" value="${studentData.archive.enrollDate }"></fmt:formatDate>' disabled="disabled" />
														</div>
													</div>
													<div class="control-group">
														<label class="control-label">入学方式</label>
														<div class="controls">
															<select name="enrollType" id="enrollType" disabled="disabled"></select>
														</div>
													</div>
													<div class="control-group">
														<label class="control-label">就读方式</label>
														<div class="controls">
															<select name="attendSchoolType" id="attendSchoolType" disabled="disabled"></select>
														</div>
													</div>
													<div class="control-group">
														<label class="control-label">学生来源</label>
														<div class="controls">
															<select name="studentSource" id="studentSource" disabled="disabled"></select>
														</div>
													</div>
													<div class="control-group">
														<label class="control-label">离校时间</label>
														<div class="controls">
															<input type="text" value='<fmt:formatDate pattern="yyyy-MM-dd" value="${studentData.archive.leaveDate }"></fmt:formatDate>' disabled="disabled" />
														</div>
													</div>
											</div>
											<div class="xsbj_div" style="display:none">	
													<div class="control-group">
														<label class="control-label">现住址</label>
														<div class="controls">
															<input type="text" value="${studentData.relation.address }" disabled="disabled" />
														</div>
													</div>
													<div class="control-group">
														<label class="control-label">通信地址</label>
														<div class="controls">
															<input type="text" value="${studentData.relation.resideAddress }" disabled="disabled" />
														</div>
													</div>
													<div class="control-group">
														<label class="control-label">家庭地址</label>
														<div class="controls">
															<input type="text" value="${studentData.relation.homeAddress }" disabled="disabled" />
														</div>
													</div>
													<div class="control-group">
														<label class="control-label">联系电话</label>
														<div class="controls">
															<input type="text" value="${studentData.relation.mobile }" disabled="disabled" />
														</div>
													</div>
													<div class="control-group">
														<label class="control-label">邮政编码</label>
														<div class="controls">
															<input type="text" value="${studentData.relation.zipCode }" disabled="disabled" />
														</div>
													</div>
													<div class="control-group">
														<label class="control-label">电子邮箱</label>
														<div class="controls">
															<input type="text" value="${studentData.relation.email }" disabled="disabled" />
														</div>
													</div>
													<div class="control-group">
														<label class="control-label">主页地址</label>
														<div class="controls">
															<input type="text" value="${studentData.relation.homepage }" disabled="disabled" />
														</div>
													</div>
											</div>
											<div class="xsbj_div" style="display:none">	
													<div class="control-group">
														<label class="control-label">是否独生子女</label>
														<div class="controls">
															<select disabled="disabled">
																<option <c:if test="${studentData.extra.isOnlyChild eq true }">selected="selected"</c:if>>是</option>
																<option <c:if test="${studentData.extra.isOnlyChild eq false}">selected="selected"</c:if>>否</option>
															</select>
														</div>
													</div>
													<div class="control-group">
														<label class="control-label">是否受过学前教育</label>
														<div class="controls">
															<select disabled="disabled">
																<option <c:if test="${studentData.extra.isPreeducated eq true }">selected="selected"</c:if>>是</option>
																<option <c:if test="${studentData.extra.isPreeducated eq false}">selected="selected"</c:if>>否</option>
															</select>
														</div>
													</div>
													<div class="control-group">
														<label class="control-label">是否留守儿童</label>
														<div class="controls">
															<select disabled="disabled">
																<option <c:if test="${studentData.extra.isUnattendedChild eq '0'}">selected="selected"</c:if>>非留守儿童</option>
																<option <c:if test="${studentData.extra.isUnattendedChild eq '1'}">selected="selected"</c:if>>单亲留守儿童</option>
																<option <c:if test="${studentData.extra.isUnattendedChild eq '2'}">selected="selected"</c:if>>双亲留守儿童</option>
															</select>
														</div>
													</div>
													<div class="control-group">
														<label class="control-label">是否进城务工人员随迁子女</label>
														<div class="controls">
															<select disabled="disabled">
																<option <c:if test="${studentData.extra.isCityLabourChild eq true }">selected="selected"</c:if>>是</option>
																<option <c:if test="${studentData.extra.isCityLabourChild eq false}">selected="selected"</c:if>>否</option>
															</select>
														</div>
													</div>
													<div class="control-group">
														<label class="control-label">是否孤儿</label>
														<div class="controls">
															<select disabled="disabled">
																<option <c:if test="${studentData.extra.isOrphan eq true }">selected="selected"</c:if>>是</option>
																<option <c:if test="${studentData.extra.isOrphan eq false}">selected="selected"</c:if>>否</option>
															</select>
														</div>
													</div>
													<div class="control-group">
														<label class="control-label">是否烈士或优抚子女</label>
														<div class="controls">
															<select disabled="disabled">
																<option <c:if test="${studentData.extra.isMartyrChild eq true }">selected="selected"</c:if>>是</option>
																<option <c:if test="${studentData.extra.isMartyrChild eq false}">selected="selected"</c:if>>否</option>
															</select>
														</div>
													</div>
<!-- 													<div class="control-group"> -->
<!-- 														<label class="control-label">所住房屋权属</label> -->
<!-- 														<div class="controls"> -->
<!-- 															<select name="houseAuthority" id="houseAuthority" disabled="disabled"></select> -->
<!-- 														</div> -->
<!-- 													</div> -->
													<div class="control-group">
														<label class="control-label">随班就读</label>
														<div class="controls">
															<select name="followStudy" id="followStudy" disabled="disabled"></select>
														</div>
													</div>
													<div class="control-group">
														<label class="control-label">残疾人类型</label>
														<div class="controls">
															<select name="disabilityType" id="disabilityType" disabled="disabled"></select>
														</div>
													</div>
													<div class="control-group">
														<label class="control-label">是否由政府购买学位</label>
														<div class="controls">
															<select disabled="disabled">
																<option <c:if test="${studentData.extra.isBuyedDegree eq true }">selected="selected"</c:if>>是</option>
																<option <c:if test="${studentData.extra.isBuyedDegree eq false}">selected="selected"</c:if>>否</option>
															</select>
														</div>
													</div>
													<div class="control-group">
														<label class="control-label">是否需要申请资助</label>
														<div class="controls">
															<select disabled="disabled">
																<option <c:if test="${studentData.extra.isSponsored eq true }">selected="selected"</c:if>>是</option>
																<option <c:if test="${studentData.extra.isSponsored eq false}">selected="selected"</c:if>>否</option>
															</select>
														</div>
													</div>
													<div class="control-group">
														<label class="control-label">是否享受一补</label>
														<div class="controls">
															<select disabled="disabled">
																<option <c:if test="${studentData.extra.isFirstRecommended eq true }">selected="selected"</c:if>>是</option>
																<option <c:if test="${studentData.extra.isFirstRecommended eq false}">selected="selected"</c:if>>否</option>
															</select>
														</div>
													</div>
<!-- 													<div class="control-group"> -->
<!-- 														<label class="control-label">是否因身体原因需要学校特别照顾</label> -->
<!-- 														<div class="controls"> -->
<!-- 															<select disabled="disabled"> -->
<%-- 																<option <c:if test="${studentData.extra.needSpecialCare eq true }">selected="selected"</c:if>>是</option> --%>
<%-- 																<option <c:if test="${studentData.extra.needSpecialCare eq false}">selected="selected"</c:if>>否</option> --%>
<!-- 															</select> -->
<!-- 														</div> -->
<!-- 													</div> -->
											</div>
											<div class="xsbj_div" style="display:none">	
													<div class="control-group">
														<label class="control-label">上下学距离</label>
														<div class="controls">
															<select name="schoolDistance" id="schoolDistance" disabled="disabled"></select>
														</div>
													</div>
													<div class="control-group">
														<label class="control-label">上下学交通方式</label>
														<div class="controls">
															<select name="trafficType" id="trafficType" disabled="disabled"></select>
														</div>
													</div>
													<div class="control-group">
														<label class="control-label">是否需要乘坐校车</label>
														<div class="controls">
															<select disabled="disabled">
																<option <c:if test="${studentData.traffic.bySchoolBus eq true }">selected="selected"</c:if>>是</option>
																<option <c:if test="${studentData.traffic.bySchoolBus eq false}">selected="selected"</c:if>>否</option>
															</select>
														</div>
													</div> 
											</div>
											
											<div class="xsbj_div" style="display:none;padding:0">
												<div class="family_select">
												<ul>
													<!-- <li class="on"><a href="javascript:void(0)">家庭成员一</a>
													</li><li><a href="javascript:void(0)">家庭成员二</a></li> -->
													<c:forEach items="${studentData.parent.parentMess }" var="parent" varStatus="index">
														<li><a href="javascript:void(0)" id="li_${index.index}">家庭成员</a></li>
													</c:forEach>
												</ul>
												<div class="clear"></div>
											</div>
												<div class="family_div">
												<c:forEach items="${studentData.parent.parentMess }" var="parent" varStatus="index">
													<div class="f_div">
													<div class="control-group">
														<label class="control-label">家庭成员或监护人姓名</label>
														<div class="controls">
															<input type="text" value="${parent.name }" disabled="disabled" />
														</div>
													</div>
													<div class="control-group">
														<label class="control-label">关系</label>
														<div class="controls">
															<select name="parentRelation" id="parentRelation_${index.index}" disabled="disabled"></select>
														</div>
													</div>
													<div class="control-group">
														<label class="control-label">关系说明</label>
														<div class="controls">
															<input type="text" value="${parent.prealtionRemarks }" disabled="disabled" />
														</div>
													</div>
													<div class="control-group">
														<label class="control-label">民族</label>
														<div class="controls">
															<select name="pRace" id="pRace_${index.index}" disabled="disabled"></select>
														</div>
													</div>
													<div class="control-group">
														<label class="control-label">工作单位</label>
														<div class="controls">
															<input type="text" value="${parent.workingPlace }" disabled="disabled" />
														</div>
													</div>
													<div class="control-group">
														<label class="control-label">现住址</label>
														<div class="controls">
															<input type="text" value="${parent.address }" disabled="disabled" />
														</div>
													</div>
													<div class="control-group">
														<label class="control-label">户口所在地</label>
														<div class="controls">
<%-- 															<input type="text" value="${parent.residenceAddress }" disabled="disabled" /> --%>
															<select id="sjp_${index.index}" class="{required: true}" disabled="disabled" style="width:30%;float:left"></select>
															<select id="shijp_${index.index}" class="{required: true}" disabled="disabled" style="width:30%"></select>
															<select id="qxjp_${index.index}"  class="{required: true}" disabled="disabled" style="width:30%"></select>
														</div>
													</div>
													<div class="control-group">
														<label class="control-label">联系电话</label>
														<div class="controls">
															<input type="text" value="${parent.mobile }" disabled="disabled" />
														</div>
													</div>
													<div class="control-group">
														<label class="control-label">是否监护人</label>
														<div class="controls">
															<select name="rank" id="rank_${index.index}" disabled="disabled"></select>
														</div>
													</div>
													<div class="control-group">
														<label class="control-label">身份证件类型</label>
														<div class="controls">
															<select name="pIdCardType" id="pIdCardType_${index.index}" disabled="disabled"></select>
														</div>
													</div>
													<div class="control-group">
														<label class="control-label">身份证件号</label>
														<div class="controls">
															<input type="text" value="${parent.idCardNumber }" disabled="disabled" />
														</div>
													</div>
													<div class="control-group">
														<label class="control-label">职务</label>
														<div class="controls">
															<input type="text" value="${parent.position }" disabled="disabled" />
														</div>
													</div>
													</div>
													</c:forEach>
												</div>
											</div>
											<div class="xsbj_div" style="display:none;">
												<textarea rows="5" cols="80" style="width: 100%" class="tinymce-simple" disabled="disabled">${studentData.remarks.remark}</textarea>
											</div>
											<div class="xsbj_div" style="display:none;">
												<textarea rows="5" cols="80" style="width: 100%" class="tinymce-simple" disabled="disabled">${studentData.resumes.resume}</textarea>
											</div>
										</div>
									</div>
									<div class="xsda" style="display:none;">
										<table class="responsive table table-striped">
											<thead>
												<tr>
													<th>序号</th>
													<th>学年</th>
													<th>贫困类别</th>
													<th>贫困原因</th>
													<th>助困形式</th>
													<th>家庭收入/人口</th>
													<th>资助金额</th>
													<th>资助日期</th>
												</tr>
											</thead>
											<tbody class="studentAid"></tbody>
										</table>
									</div>
									<div class="xsda" style="display:none;">
										<table class="responsive table table-striped">
											<thead>
												<tr>
													<th>序号</th>
													<th>学年</th>
													<th>处分类型</th>
													<th>处分原因</th>
													<th>处分日期</th>
													<th>到期日期</th>
													<th>撤销日期</th>
													<th>是否撤销处分</th>
												</tr>
											</thead>
											<tbody class="studentPunish"></tbody>
										</table>
									</div>
									<div class="xsda" style="display:none;">
										<table class="responsive table table-striped">
											<thead>
												<tr>
													<th>序号</th>
													<th>学年</th>
													<th>获奖内容</th>
													<th>获奖级别</th>
													<th>获奖名次</th>
													<th>获奖类型</th>
													<th>获奖日期</th>
													<th>颁奖单位</th>
												</tr>
											</thead>
											<tbody class="studentAward"></tbody>
										</table>
									</div>
									<div class="xsda" style="display:none;">
										<table class="responsive table table-striped">
											<thead>
												<tr>
													<th>序号</th>
													<th>学年</th>
													<th>总评价</th>
													<th>说明</th>
												</tr>
											</thead>
											<tbody class="studentMoral"></tbody>
										</table>
									</div>
									<div class="xsda" style="display:none;">
										<table class="responsive table table-striped">
											<thead>
												<tr>
													<th>序号</th>
													<th>学年</th>
													<th>事假</th>
													<th>病假</th>
													<th>缺课</th>
													<th>旷课</th>
													<th>迟到</th>
													<th>早退</th>
												</tr>
											</thead>
											<tbody class="studentAttendance"></tbody>
										</table>
									</div>
									<div class="xsda" style="display:none;">
										<table class="responsive table table-striped">
											<thead>
												<tr>
													<th>序号</th>
													<th>学年</th>
													<th>健康类型</th>
													<th>附件</th>
												</tr>
											</thead>
											<tbody class="studentHealth"></tbody>
										</table>
									</div>
									<!-- <div class="xsda" style="display:none;">
										<table class="responsive table table-striped">
											<thead>
												<tr>
													<th>序号</th>
													<th>学期</th>
													<th>所在班级</th>
													<th>留往班级</th>
													<th>留级日期</th>
													<th>留级原因</th>
													<th>留级状态</th>
												</tr>
											</thead>
											<tbody>
												<tr>
													<td>1</td>
													<td>2014-2014学年上学期</td>
													<td>初一（1）班</td>
													<td>初一（2）班</td>
													<td>2014-12-12</td>
													<td>没有完成学业</td>
													<td>批注留级</td>
												</tr>
												<tr>
													<td>1</td>
													<td>2014-2014学年上学期</td>
													<td>初一（2）班</td>
													<td>初一（4）班</td>
													<td>2014-12-12</td>
													<td>没有完成学业</td>
													<td>批注留级</td>
												</tr>
											</tbody>
										</table>
									</div>
									<div class="xsda" style="display:none;">
										<table class="responsive table table-striped">
											<thead>
												<tr>
													<th>序号</th>
													<th>学期</th>
													<th>所在班级</th>
													<th>跳往班级</th>
													<th>跳级日期</th>
													<th>跳级原因</th>
													<th>跳级状态</th>
												</tr>
											</thead>
											<tbody>
												<tr>
													<td>1</td>
													<td>2014-2014学年上学期</td>
													<td>初一（1）班</td>
													<td>初一（2）班</td>
													<td>2014-12-12</td>
													<td>已经完成学业</td>
													<td>批注跳级</td>
												</tr>
												<tr>
													<td>1</td>
													<td>2014-2014学年上学期</td>
													<td>初一（2）班</td>
													<td>初一（4）班</td>
													<td>2014-12-12</td>
													<td>已经完成学业</td>
													<td>批注跳级</td>
												</tr>
											</tbody>
										</table>
									</div> -->
									<div class="xsda" style="display:none;padding-top:9px;">
									<div class="nj_div" style="height:57px;">
									<div class="select_b">
										<div class="select_div"><span>学年：</span><select id="schoolYear" name="schoolYear" onchange="onChangeSchoolYear();" class="chzn-select" style="width: 220px;"></select></div>
										<div class="select_div"><span>学期：</span><select id="termCode" name="termCode" class="chzn-select"style="width:220px;float:left"></select></div>
										<button onclick="findScore('${studentId}');" class="btn btn-info" style="display:inline-block;">查询</button>
										<div class="clear"></div>
									</div>
									</div>
									<div style="width:677px;overflow:auto;" id="set_table"> 
										<table class="responsive table table-striped studentScore" style="width:1200px;max-width:none;">
										</table>
										</div>
									</div>
								</div>
							</div>
							
							<div class="xs_right_1" style="height: 576px;">
								<c:choose>
									<c:when test="${not empty studentData.basic.photoUuid}">
										<img alt="头像" src="<entity:getHttpUrl uuid='${studentData.basic.photoUuid}'/>" />
									</c:when>
									<c:otherwise>
										<img alt="头像" src="${pageContext.request.contextPath}/res/images/noPhoto.jpg">
									</c:otherwise>
								</c:choose>
								<p>姓名</p>
								<input type="text" disabled="disabled" value="${studentData.basic.name }">
								<p>学籍号</p>
								<input type="text" disabled="disabled" value="${studentData.archive.studentNumber }">
							</div>
						</div>
<!-- 						<div class="form-actions tan_bottom"> -->
<!-- 							<button class="btn btn-warning" type="button">完整档案导出</button> -->
<!-- 							<button class="btn btn-warning" type="button" >成绩单导出</button> -->
<!-- 						</div> -->
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
function numx2Str(){
	var obj = {"0":"一","1":"二","2":"三","3":"四","4":"五","5":"六","6":"七","7":"八","8":"九","9":"十"};
	$.each(obj, function(key, val) {      
		$("#li_"+key).text("家庭成员"+val);
 	}); 
}
$(function(){
	$.initRegionLevelSelector({
		"lv" :"3",
		"sjSelector" : "sj1",
		"shijSelector" : "shij1",
		"qxjSelector" : "qxj1",
		isEchoSelected : true,
		"sjSelectedVal" : "${birthGradeCode}",
		"shijSelectedVal" : "${birthParentCode}",
		"qxjSelectedVal" : "${birthCode}"
	});
	
	$.initRegionLevelSelector({
		"lv" :"3",
		"sjSelector" : "sj",
		"shijSelector" : "shij",
		"qxjSelector" : "qxj",
		isEchoSelected : true,
		"sjSelectedVal" : "${nativeGradeCode}",
		"shijSelectedVal" : "${nativeParentCode}",
		"qxjSelectedVal" : "${nativeCode}"
	});
	
	$.initRegionLevelSelector({
		"lv" :"3",
		"sjSelector" : "sj2",
		"shijSelector" : "shij2",
		"qxjSelector" : "qxj2",
		isEchoSelected : true,
		"sjSelectedVal" : "${RAGradeCode}",
		"shijSelectedVal" : "${RAParentCode}",
		"qxjSelectedVal" : "${residenceAddressCode}"
	});
	numx2Str();
	//性别
	$.jcGcSelector("#sex", {tc : "GB-XB"}, "${studentData.basic.sex }", function() {});
	//民族
	$.jcGcSelector("#race", {tc : "GB-MZ"}, "${studentData.basic.race }", function() {});
	//国籍
	$.jcGcSelector("#nationality", {tc : "GB-GJ"}, "${studentData.basic.nationality }", function() {});
	//身份证件类型
	$.jcGcSelector("#idCardType", {tc : "JY-SFZJLX"}, "${studentData.basic.idCardType }", function() {});	
	//港澳台侨外码
	$.jcGcSelector("#abroadCode", {tc : "JY-GATQW"}, "${studentData.basic.abroadCode }", function() {});	
	//政治面貌
	$.jcGcSelector("#politicalStatus", {tc : "GB-ZZMM"}, "${studentData.basic.politicalStatus }", function() {});
	//健康状况
	$.jcGcSelector("#healthStatus", {tc : "GB-JKZK"}, "${studentData.basic.healthStatus }", function() {});
	//血型
	$.jcGcSelector("#bloodType", {tc : "JY-XX"}, "${studentData.basic.bloodType }", function() {});
	//户口性质
	$.jcGcSelector("#residenceType", {tc : "GB-HKLB"}, "${studentData.assist.residenceType }", function() {});
	//学生类别
	$.jcGcSelector("#studentType", {tc : "JY-XSLB","level":"1"}, "${studentData.archive.studentType }", function() {});
	//入学方式
	$.jcGcSelector("#enrollType", {tc : "JY-RXFS"}, "${studentData.archive.enrollType }", function() {});
	//就读方式
	$.jcGcSelector("#attendSchoolType", {tc : "JY-JDFS"}, "${studentData.archive.attendSchoolType }", function() {});	
	//学生来源
	$.jcGcSelector("#studentSource", {tc : "JY-ZXXXSLY"}, "${studentData.archive.studentSource }", function() {});	
	//是否随班就读
	$.jcGcSelector("#followStudy", {tc : "XY-JY-SBJD"}, "${studentData.extra.followStudy }", function() {});	
	//残疾类型
	$.jcGcSelector("#disabilityType", {tc : "JY-CJLX"}, "${studentData.extra.disabilityType }", function() {});	
	//所住房屋权属
	$.jcGcSelector("#houseAuthority", {tc : "XY-JY-FWQS"}, "${studentData.extra.houseAuthority }", function() {});	
	//上下学距离（公里）
	$.jcGcSelector("#schoolDistance", {tc : "XY-JY-SXXJL"}, "${studentData.traffic.schoolDistance }", function() {});	
	//上下学交通方式
	$.jcGcSelector("#trafficType", {tc : "XY-JY-JTFS"}, "${studentData.traffic.trafficType }", function() {});	
	var parentMess = "${studentData.parent.parentMess}";
	var obj = eval("(" + parentMess + ")");
	for(var i =0; i < "${parentlength}"; i++){
		var code = obj[i].residenceAddressCode;
		var codeArr = code.split(",");
		$.initRegionLevelSelector({
			"lv" :"3",
			"sjSelector" : "sjp_"+i,
			"shijSelector" : "shijp_"+i,
			"qxjSelector" : "qxjp_"+i,
			isEchoSelected : true,
			"sjSelectedVal" : codeArr[0],
 			"shijSelectedVal" : codeArr[1],
 			"qxjSelectedVal" : codeArr[2]
		});
		//关系
		$.jcGcSelector("#parentRelation_"+i, {tc : "XY-JY-XSJTGX"}, obj[i].parentRelation, function() {});	
		//是否监护人
		$.jcGcSelector("#rank_"+i, {tc : "XY-JY-JZLB"}, obj[i].rank, function() {});	
		//家长民族
		$.jcGcSelector("#pRace_"+i, {tc : "GB-MZ"}, obj[i].race, function() {});	
		//家长身份证件类型
		$.jcGcSelector("#pIdCardType_"+i, {tc : "JY-SFZJLX"}, obj[i].idCardType, function() {});	
	}
});

//学生资助
function findStudentAid(studentId) {
	$(".studentAid").load("${ctp}/teach/studentaid/view", {"_method" : "GET", "studentId" : studentId}, function(data, status) {});
}

//学生处分
function findStudentPunish(studentId) {
	$(".studentPunish").load("${ctp}/teach/studentPunish/view", {"_method" : "GET", "studentId" : studentId}, function(data, status) {});
}

//学生奖励
function findStudentAward(studentId) {
	$(".studentAward").load("${ctp}/teach/studentAward/view", {"_method" : "GET", "studentId" : studentId}, function(data, status) {});
}

//学生德育
function findStudentMoral(studentId) {
	$(".studentMoral").load("${ctp}/teach/moralEvaluationStudent/view", {"_method" : "GET", "studentId" : studentId}, function(data, status) {});
}

//学生考勤
function findStudentAttendance(studentId) {
	$(".studentAttendance").load("${ctp}/teach/studentCheckAttendance/view", {"_method" : "GET", "studentId" : studentId}, function(data, status) {});
}

//学生健康
function findStudentHealth(studentId) {
	$(".studentHealth").load("${ctp}/teach/studentHealthArchive/view", {"_method" : "GET", "studentId" : studentId}, function(data, status) {});
}

//学生成绩
function findStudentScore(studentId) {
	var xn = $("#schoolYear").val();
	var xq = $("#termCode").val();
	$(".studentScore").load("${ctp}/teach/scoreSelect/view", {"_method" : "GET", "studentId" : studentId, "schoolYear" : xn, "termCode" : xq}, function(data, status) {});
// 	alert(xn);
// 	alert(xq);
}

$(function(){
	$.SchoolYearSelector({"selector" : "#schoolYear", "afterHandler" : function() {onChangeSchoolYear();}
	});
});

//选择学年获取该学年下的学期
function onChangeSchoolYear(){
	var xn = $("#schoolYear").val();
	if(xn == null || xn == ""){
		xn = '${sessionScope[sca:currentUserKey()].schoolYear}';
	  }
	
	  $.getSchoolTerm({"schoolYear" : xn}, function(data, status) {
			var $xq = $("#termCode");
			
			$xq.html("");
			$.each(data, function(index, value) {
				$xq.append("<option value='" + value.code + "'>" + value.name + "</option>");
			});
		});
  }

function findScore(studentId) {
	findStudentScore(studentId);
}
</script>
</html>