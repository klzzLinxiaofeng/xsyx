<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/views/embedded/common.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ taglib prefix="entity" uri="http://www.jiaoxueyun.com/entity"%>
<script src="${pageContext.request.contextPath}/res/plugin/falgun/js/stepy.jquery.js"></script>
<script src="${pageContext.request.contextPath}/res/plugin/falgun/js/tiny_mce/tiny_mce.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/common/plugin/uploadify/jquery.uploadify.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/common/plugin/uploadify/jquery.uploadify.min.js"></script>
	<%--文件上传插件--%>
	<link rel="stylesheet" type="text/css" href="${ctp}/res/css/uploadifive/uploadifive.css">
	<script src="${ctp}/res/js/uploadifive/jquery.uploadifive.min.js" type="text/javascript"></script>
<%@ include file="/views/embedded/plugin/avatar_js.jsp"%>
<script type="text/javascript" defer="defer" src="${ctp}/res/plugin/My97DatePicker/WdatePicker.js"></script>
<title>${sca:getDefaultSchoolName()}</title>
<style type="text/css">
. form-horizontal{position:relative;}
 /*    .form-horizontal .control-group{
    	width:45%;
    	display:inline-block;
    } */
    .table thead th{height:34px;line-height:34px;}
    legend + .control-group{margin-top:0;}
    
    table td .up_img {
    background: url("${pageContext.request.contextPath}/res/css/extra/images/up_img.jpg") no-repeat;
    display: block;
    height: 30px;
    width: 98px;
    position:absolute;
    top:230px;
    left:30px;
}
td input[type="text"],td select,td textarea{margin-bottom:0;width:100%;}
td input[type="text"],td textarea{border:0 none;width:95%;}
table{background-color:#fff;}
table td img{width:155px;height:175px;margin-left:5px;cursor:pointer;}
.red {
    color: red;
    padding: 0 5px;
}
 .myerror {
		color: red !important;
		width: 34%;
		display: inline-block;
		padding-left: 10px;
	}
	.btn-extend {
    color: #ffffff;
    background-color: #e88a05;
    position:fixed;
    bottom:12px;
    z-index:1;
}
.button-back{
	left:340px;
}
.button-next,#modifyUserInfoForm-buttons-7 .button-back{
	left:412px;
}
.btn-extend:hover{
	background-color: #BF7204;
}
</style>
</head>
<body>
	<div class="container-fluid">
	<div class="row-fluid ">
                <div class="span12">
                    <ul class="breadcrumb">
                        <li><a href="#"><i name="dashboard" class="fa fa-home"></i></a>修改学生</li>
                    </ul>
                </div>
        </div>
		<div class="row-fluid ">
			<div class="span12">
				<div class="content-widgets white" style="margin-bottom: 0;">
					<div class="widget-head">
						<h3>
							学生基本信息
<!-- 							<p style="float: right;" class="btn_link"> -->
<!-- 								<a class="a4" href="javascript:void(0)"><i -->
<!-- 									class="fa fa-mail-reply"></i>返回列表</a> -->
<!-- 							</p> -->
						</h3>
					</div>
				</div>
				<div class="stepy-widget">
					<div class="widget-head clearfix bondi-blue">
						<div id="stepy_tabby1">
							<ul id="stepy_form-titles" class="stepy-titles">
							</ul>
						</div>
						<button href="javascript:void(0)" onclick="updateUserInfo();" class="btn btn-warning finish" style="position:absolute;right:25px;top:11px;">保存</button>
					</div>
					<div class="widget-container gray ">
						<div class="form-container">
							<form id="modifyUserInfoForm"  class="form-horizontal left-align form-well" novalidate="novalidate" style="padding-bottom:0;margin-bottom:0">
								<%-- <input type="hidden" id="personId" name="personId" value="${userDetailInfo.personId}"/>
								<input type="hidden" id="userId" name="userId" value="${userDetailInfo.userId}"/> --%>
								<input type="hidden" id="studentId" name="studentId" value="${studentId}"/> 
								<fieldset title="基本信息">
									<legend style="display: none;">基本账户信息</legend>
									<table class="table table-bordered t_table">
										<tbody>
											<tr>
												<td><span class="red">*</span>姓名</td>
												<td><input type="text" id="name" name="name" maxlength="6" value="${studentData.basic.name }" data-id="" placeholder="姓名" /></td>
												<td><span class="red"></span>身份证件类型</td>
												<td><select name="idCardType" id="idCardType"></select></td>
												<td rowspan="7" style="width: 165px; position: relative">
													<c:choose>
														<c:when test="${not empty studentData.basic.photoUuid}">
															<img src="<entity:getHttpUrl uuid='${studentData.basic.photoUuid}'/>" id="imgId"/>
														</c:when>
														<c:otherwise>
															<img src="${pageContext.request.contextPath}/res/images/noPhoto.jpg" id="imgId">
														</c:otherwise>
													</c:choose>
<%-- 													<img src="${pageContext.request.contextPath}/res/images/noPhoto.jpg" id="imgId"> --%>
													<a href="javascript:void(0)" class="up_img"></a>
<!-- 													<input type="file" id="uploader" name="uploader"/> -->
													<input type="hidden" id="photoUuid" name="photoUuid" value="${studentData.basic.photoUuid }"/>
												</td>
											</tr>
											<tr>
												<td><span class="red">*</span>性别</td>
												<td><select id="sex" name="sex"></select></td>
												<td><span class="red"></span>身份证件号</td>
												<td><input type="text" name="idCardNumber" maxlength="18" class="{chrnum : true}" id="idCardNumber" value="${studentData.basic.idCardNumber }" placeholder="证件号码"/></td>
											</tr>
											<tr>
												<td><span class="red"></span>出生日期</td>
												<td><input type="text" id="birthday" name="birthday" 
													value='<fmt:formatDate pattern="yyyy-MM-dd" value="${studentData.basic.birthday }"></fmt:formatDate>' 
													placeholder="出生日期" onclick="WdatePicker();"/></td>
												<td><span class="red"></span>港澳台侨外</td>
												<td><select name="abroadCode" id="abroadCode" ></select></td>
											</tr>
											
											<tr>
												<td><span class="red"></span>健康状况</td>
												<td><select id="healthStatus" name="healthStatus"></select></td>
												<td>政治面貌</td>
												<td><select id="politicalStatus" name="politicalStatus"></select></td>
											</tr>
											<tr>
												<td><span class="red"></span>民族</td>
												<td><select name="race" id="race"></select></td>
												<td><span class="red"></span>国籍/地区</td>
												<td><select name="nationality" id="nationality"></select></td>
<!-- 												<td>血型</td> -->
<!-- 												<td><select name="bloodType" id="bloodType"></select></td> -->
											</tr>
											<tr>
												<td><span class="red"></span>出生地</td>
												<td colspan="3">
<%-- 													<input  type="text" name="birthPlace" id="birthPlace" value="${studentData.basic.birthPlace }" placeholder="出生地"/> --%>
													<select id="sj1" <%--class="{required: true}"--%> style="width:32%;float:left;margin-right:5px"></select>
													<select id="shij1" style="width:33%;float:left;margin-right:5px"></select>
													<select id="qxj1" style="width:33%;"></select>
												</td>
											</tr>
											<tr><td><span class="red"></span>籍贯</td>
												<td colspan="3">
<%-- 													<input type="text" name="nativePlace" id="nativePlace" value="${studentData.basic.nativePlace }" placeholder="籍贯"/> --%>
													<select id="sj" <%--class="{required: true}"--%> style="width:32%;float:left;margin-right:5px"></select>
													<select id="shij" style="width:33%;float:left;margin-right:5px"></select>
													<select id="qxj" style="width:33%;"></select>
												</td></tr>
										</tbody>
									</table>
								</fieldset>
								<fieldset title="辅助信息">
									<legend style="display: none;">户口、特长</legend>
									<table class="table table-bordered t_table">
										<tbody>
											<tr>
												<td>姓名拼音</td>
												<td><input type="text" id="pinyinName" name="pinyinName" value="${studentData.assist.pinyinName }" placeholder="姓名拼音"/></td>
												<td>曾用名</td>
												<td><input type="text" id="usedName" name="usedName" value="${studentData.assist.usedName }" placeholder="曾用名"/></td>
											</tr>
											<tr>
												<td><span class="red"></span>户口性质</td>
												<td><select name="residenceType" id="residenceType"></select></td>
												<td>身份证有效期</td>
												<td><input type="text" id="idCardDate" name="idCardDate" 
													value='<fmt:formatDate pattern="yyyy-MM-dd" value="${studentData.assist.idCardDate }"></fmt:formatDate>' 
													placeholder="身份证有效期" onclick="WdatePicker();"/></td>
											</tr>
											<tr>
												<td><span class="red"></span>户口所在地</td>
												<td colspan="3">
<%-- 												<input type="text"  name="residenceAddress" id="residenceAddress" value="${studentData.assist.residenceAddress }" placeholder="户口所在地"/> --%>
													<select id="sj2" <%--class="{required: true}"--%> style="width:32%;float:left;margin-right:5px"></select>
													<select id="shij2" <%--class="{required: true}"--%> style="width:33%;float:left;margin-right:5px"></select>
													<select id="qxj2"  <%--class="{required: true}"--%> style="width:33%;"></select>
												</td>
											</tr>
											<tr>
												<td>特长</td>
												<td colspan="3"><input  type="text" name="specialSkill" id="specialSkill" value="${studentData.assist.specialSkill }" placeholder="特长"/></td>
											</tr>
										</tbody>
									</table>
								</fieldset>
								<fieldset title="学籍信息">
									<legend style="display: none;">学籍、班级</legend>
									<table class="table table-bordered t_table">
										<tr>
											<td>学籍辅号</td>
											<td><input type="text" maxlength="10" <c:if test="${canModify eq false}">disabled="disabled"</c:if> name="studentNumber" id="studentNumber" value="${studentData.archive.studentNumber }" placeholder="学籍辅号"/></td>
<%-- 											<td>${studentData.archive.studentNumber }</td> --%>
											<td>入学时间</td>
											<td><input type="text" id="enrollDate" name="enrollDate" 
												value='<fmt:formatDate pattern="yyyy-MM-dd" value="${studentData.archive.enrollDate }"></fmt:formatDate>'
												placeholder="入学时间" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'leaveDate\')}',dateFmt:'yyyy-MM-dd HH:mm'});"/></td>
										</tr>
										<tr>
											<td>班内学号</td>
											<td>
												<input type="text" maxlength="10"  name="number" id="number" value="${studentData.archive.number }" placeholder="班内学号"/>
											</td>
											<td><span class="red"></span>入学方式</td>
											<td><select name="enrollType" id="enrollType"></select></td>
										</tr>
										<tr>
<!-- 											<td>学生类别</td> -->
<!-- 											<td><select name="studentType" id="studentType"></select></td> -->
											<td>年级</td>
											<td>
												<select id="nj" name="gradeId" value="${gradeId}">
												</select></td>
											<td><span class="red"></span>就读方式</td>
											<td><select name="attendSchoolType" id="attendSchoolType"></select></td>
										</tr>
										<tr>
											<td>班级</td>
											<td>
												<select id="bj" name="teamId" value="${teamId}">
											</select></td>
											<td>学生来源</td>
											<td><select name="studentSource" id="studentSource"></select></td>
										</tr>
										<tr>
											<td>离校时间</td>
											<td><input type="text" id="leaveDate" name="leaveDate" 
												value='<fmt:formatDate pattern="yyyy-MM-dd" value="${studentData.archive.leaveDate }"></fmt:formatDate>'
												placeholder="离校时间" onclick="WdatePicker({minDate:'#F{$dp.$D(\'enrollDate\')}',dateFmt:'yyyy-MM-dd HH:mm'});"/></td>
											<td>异动状态</td>
											<td><select name="studyState" id="studyState"></select></td>
										</tr>
									</table>
								</fieldset>
								<fieldset title="联系信息">
									<legend style="display: none;">住址、联系方式</legend>
									<table class="table table-bordered t_table">
										<tr>
											<td><span class="red"></span>现住址</td>
											<td><input type="text" <%--class="{required: true}"--%> name="address" id="address" value="${studentData.relation.address }" placeholder="现住址"/></td>
											<td>邮政编码</td>
											<td><input type="text"  name="zipCode" id="zipCode" value="${studentData.relation.zipCode }" placeholder="邮政编码"/></td>
										</tr>
										<tr>
											<td><span class="red"></span>通信地址</td>
											<td><input type="text" <%--class="{required: true}"--%> name="resideAddress" id="resideAddress" value="${studentData.relation.resideAddress }" placeholder="通信地址"/></td>
											<td>电子邮箱</td>
											<td><input type="text"  name="email" id="email" value="${studentData.relation.email }" placeholder="电子邮箱"/></td>
										</tr>
										<tr>
											<td><span class="red"></span>家庭地址</td>
											<td><input type="text" <%--class="{required: true}"--%> name="homeAddress" id="homeAddress" value="${studentData.relation.homeAddress }" placeholder="家庭地址"/></td>
											<td>主页地址</td>
											<td><input type="text"  name="homepage" id="homepage" value="${studentData.relation.homepage }" placeholder="主页地址"/></td>
										</tr>
										<tr>
											<td>联系电话</td>
											<td><input type="text"  name="mobile" id="mobile" value="${studentData.relation.mobile }" placeholder="联系电话"/></td>
										</tr>
									</table>
								</fieldset>
								<fieldset title="扩展信息">
									<legend style="display: none;">个人信息调查</legend>
									<table class="table table-bordered t_table">
										<tr>
											<td><span class="red">*</span>是否独生子女</td>
											<td>
												<select name="isOnlyChild" class="{required: true}" id="isOnlyChild">
<!-- 													<option value="">请选择</option> -->
													<option value="0" <c:if test="${studentData.extra.isOnlyChild eq false}">selected</c:if> >否</option>
													<option value="1" <c:if test="${studentData.extra.isOnlyChild eq true}">selected</c:if> >是</option>
												</select>
											</td>
											<td>随班就读</td>
											<td><select name="followStudy" id="followStudy"></select></td>
										</tr>
										<tr>
											<td><span class="red">*</span>是否受过学前教育</td>
											<td>
												<select name="isPreeducated" class="{required: true}" id="isPreeducated">
<!-- 													<option value="">请选择</option> -->
													<option value="0" <c:if test="${studentData.extra.isPreeducated eq false}">selected</c:if> >否</option>
													<option value="1" <c:if test="${studentData.extra.isPreeducated eq true}">selected</c:if> >是</option>
												</select>
											</td>
											<td>残疾人类型</td>
											<td><select name="disabilityType" id="disabilityType"></select></td>
										</tr>
										<tr>
											<td>是否留守儿童</td>
											<td>
												<select name="isUnattendedChild" id="isUnattendedChild">
<!-- 													<option value="">请选择</option> -->
													<option value="0" <c:if test="${studentData.extra.isUnattendedChild eq '0'}">selected</c:if> >非留守儿童</option>
													<option value="1" <c:if test="${studentData.extra.isUnattendedChild eq '1'}">selected</c:if> >单亲留守儿童</option>
													<option value="2" <c:if test="${studentData.extra.isUnattendedChild eq '2'}">selected</c:if> >双亲留守儿童</option>
												</select>
											</td>
											<td>是否由政府购买学位</td>
											<td>
												<select name="isBuyedDegree" id="isBuyedDegree">
													<option value="0" <c:if test="${studentData.extra.isBuyedDegree eq false}">selected</c:if> >否</option>
													<option value="1" <c:if test="${studentData.extra.isBuyedDegree eq true }">selected</c:if> >是</option>
												</select>
											</td>
										</tr>
										<tr>
											<td><span class="red">*</span>是否进城务工人员随迁子女</td>
											<td>
												<select name="isCityLabourChild" class="{required: true}" id="isCityLabourChild">
<!-- 													<option value="">请选择</option> -->
													<option value="0" <c:if test="${studentData.extra.isCityLabourChild eq false}">selected</c:if> >否</option>
													<option value="1" <c:if test="${studentData.extra.isCityLabourChild eq true }">selected</c:if> >是</option>
												</select>
											</td>
											<td><span class="red">*</span>是否需要申请资助</td>
											<td>
												<select name="isSponsored" class="{required: true}" id="isSponsored">
<!-- 													<option value="">请选择</option> -->
													<option value="0" <c:if test="${studentData.extra.isSponsored eq false}">selected</c:if> >否</option>
													<option value="1" <c:if test="${studentData.extra.isSponsored eq true }">selected</c:if> >是</option>
												</select>
											</td>
										</tr>
										<tr>
											<td><span class="red">*</span>是否孤儿</td>
											<td>
												<select name="isOrphan" class="{required: true}" id="isOrphan">
<!-- 													<option value="">请选择</option> -->
													<option value="0" <c:if test="${studentData.extra.isOrphan eq false}">selected</c:if> >否</option>
													<option value="1" <c:if test="${studentData.extra.isOrphan eq true }">selected</c:if> >是</option>
												</select>
											</td>
											<td><span class="red">*</span>是否享受一补</td>
											<td>
												<select name="isFirstRecommended" class="{required: true}" id="isFirstRecommended">
<!-- 													<option value="">请选择</option> -->
													<option value="0" <c:if test="${studentData.extra.isFirstRecommended eq false}">selected</c:if> >否</option>
													<option value="1" <c:if test="${studentData.extra.isFirstRecommended eq true }">selected</c:if> >是</option>
												</select>
											</td>
										</tr>
										<tr>
										<td><span class="red">*</span>是否烈士或优抚子女</td>
										<td>
											<select name="isMartyrChild" class="{required: true}" id="isMartyrChild">
												<!-- 													<option value="">请选择</option> -->
												<option value="0" <c:if test="${studentData.extra.isMartyrChild eq false}">selected</c:if> >否</option>
												<option value="1" <c:if test="${studentData.extra.isMartyrChild eq true }">selected</c:if> >是</option>
											</select>
										</td>
										<%--	<td></td>
											<td></td>--%>
										<td>性格</td>
										<td>
											<input type="text"  name="xingge" id="xingge" value="${studentData.extra.xingge}" placeholder="性格"/>
										</td>
										</tr>
										<tr>
											<td>我的爱好</td>
											<td>
												<input type="text"  name="aihao" id="aihao" value="${studentData.extra.aihao}" placeholder="性格"/>
											</td>
											<%--	<td></td>
                                                <td></td>--%>
											<td>最喜欢的书</td>
											<td>
												<input type="text"  name="likeBook" id="likeBook" value="${studentData.extra.likeBook}" placeholder="性格"/>
											</td>
										</tr>
										<tr>
											<td>最敬佩的人</td>
											<td>
												<input type="text"  name="jingpei" id="jingpei" value="${studentData.extra.jingpei}" placeholder="性格"/>
											</td>
											<%--	<td></td>
                                               <td></td>--%>
											<td>我的座右铭</td>
											<td>
												<input type="text"  name="zuoyouming" id="zuoyouming " value="${studentData.extra.zuoyouming}" placeholder="性格"/>
											</td>
										</tr>
									</table>
								</fieldset>
								<fieldset title="交通方式">
									<legend style="display: none;">上下学方式</legend>
									<table class="table table-bordered t_table">
										<tr>
											<td>上下学距离</td>
											<td><select name="schoolDistance" id="schoolDistance"></select></td>
											<td>是否需要乘坐校车</td>
											<td>
												<select name="bySchoolBus" id="bySchoolBus">
<!-- 													<option value="">请选择</option> -->
													<option value="0" <c:if test="${studentData.traffic.bySchoolBus eq false}">selected</c:if> >否</option>
													<option value="1" <c:if test="${studentData.traffic.bySchoolBus eq true }">selected</c:if> >是</option>
												</select>
											</td>
										</tr>
										<tr>
											<td>上下学交通方式</td>
											<td><select name="trafficType" id="trafficType"></select></td>
										</tr>
									</table>
								</fieldset>
								<fieldset title="家庭成员">
									<legend style="display: none;">关系、联系方式</legend>
									<div class="cy_select">
									<ul>
										<c:forEach items="${studentData.parent.parentMess }" var="parent" varStatus="index">
											<li><a href="javascript:void(0)" id="li_${index.index}">家庭成员</a></li>
										</c:forEach>
									</ul>
									<div class="clear"></div>
									</div>
								<div class="family_table">
									<c:forEach items="${studentData.parent.parentMess }" var="parent" varStatus="index">
									<table class="table table-bordered t_table" style="display:none">
										<tr>
											<td><span class="red">*</span>家庭成员或监护人姓名</td>
											<td><input type="text" class="{maxlength : 10,required: true}"  id="pName_${index.index}" value="${parent.name }" placeholder="姓名"/></td>
											<td><span class="red">*</span>关系</td>
											<td><select class="parentRelation {required: true}"  id="parentRelation_${index.index}"></select></td>
										</tr>
										
										<tr>
											<td><span class="red">*</span>联系电话</td>
											<td><input type="text" class="{mobile:true,required:true}" id="mobile_${index.index}" value="${parent.mobile }" placeholder="联系电话"/></td>
											<td><span class="red">*</span>身份证件号</td>
											<td><input type="text" class="{maxlength:18,chrnum : true,required:true}"  id="pIdCardNumber_${index.index}" value="${parent.idCardNumber }" placeholder="证件号码"/></td>
										</tr>
										<tr>
											<td>关系说明</td>
											<td><input type="text" class="{maxlength : 20}"  id="prealtionRemarks_${index.index}" value="${parent.prealtionRemarks }" placeholder="关系说明"/></td>
											<td><span class="red">*</span>是否监护人</td>
											<td><select class="rank" class="{required: true}" id="rank_${index.index}" ></select></td>
										</tr>
										<tr>
											<td>民族</td>
											<td><select class="pRace" id="pRace_${index.index}"></select></td>
											<td><span class="red">*</span>身份证件类型</td>
											<td><select class="pIdCardType" class="{required: true}" id="pIdCardType_${index.index}"></select></td>
										</tr>
										<tr>
											<td>工作单位</td>
											<td><input type="text" class="{maxlength:20}" id="workingPlace_${index.index}" value="${parent.workingPlace }" placeholder="工作单位"/></td>
											<td>职务</td>
											<td><input type="text" id="position_${index.index}" value="${parent.position }" placeholder="职务"/></td>
										</tr>
										<tr>
											<td><span class="red">*</span>现住址</td>
											<td colspan="3"><input type="text" class="{required: true}" id="pAddress_${index.index}" value="${parent.address }" placeholder="现住址"/></td>
										</tr>
										<tr>
											<td><span class="red">*</span>户口所在地</td>
											<td colspan="3">
<%-- 											<input type="text" class="{maxlength : 20}" id="residenceAddress_${index.index}" value="${parent.residenceAddress }" placeholder="户口所在地"/> --%>
												<select id="sjp_${index.index}" class="{required: true}" style="width:32%;float:left;margin-right:5px"></select>
												<select id="shijp_${index.index}" style="width:33%;float:left;margin-right:5px"></select>
												<select id="qxjp_${index.index}"  style="width:33%;"></select>
											</td>
										</tr>
										<tr style="display:none;">
											<td><input type="text" id="parentUserId_${index.index}" value="${parent.parentUserId }" /></td>
										</tr>
									</table>
									</c:forEach>
									
									</div>
								</fieldset>
								
								<fieldset title="备注">
									<legend style="display: none;">其他信息</legend>
									<div class="widget-container">
										<div class="control-group" style="width:100%;">
											<div class="controls">
												<textarea id="remark" name="remark" rows="8" cols="120" style="width: 80%" class="tinymce-simple">${studentData.remarks.remark}</textarea>
											</div>
										</div>
									</div>
									<div class="clear"></div>
								</fieldset>
									<fieldset title="成长感言">
										<legend style="display: none;">成长感言</legend>
										<div class="widget-container">
											<div class="control-group" style="width:100%;">
												<div class="controls">
													<label>我最喜欢</label>
													<input id="ganyan" name="ganyan" rows="8" cols="120" style="width: 80%; " class="tinymce-simple" value="${studentData.extra.ganyan}"/>
													<label>这个学期我想在</label>
													<input id="ganyan1" name="ganyan1" rows="8" cols="120" style="width: 80%" class="tinymce-simple" value="${studentData.extra.ganyan1}"/>
													<label>上取得进步！</label>
													<label>我会努力的！</label>

												</div>
												<div class="controls">
													<label class="control-label">
														我最喜欢的照片：
													</label>
													<div id="zp" style="display:inline-block;">
															<div class="img_1" style="margin: 5px;">
																<img style="width:233px;height:130px;" class="ims"  id="pictureUrl" name="pictureUrl" onclick="change(this);" src='${studentData.extra.pictureUrl}'/>
															</div>
													</div>
														<div><span>支持jpg、gif、png、bmp格式</span></div>
														<input type="hidden" id="uploader" name="uploader">
													<input id="pictureUuid" name="pictureUuid" value="${studentData.extra.pictureUuid}" style="display: none"/>
												</div>
											</div>
										</div>
										<div class="clear"></div>
									</fieldset>
								<button href="javascript:void(0)" onclick="saveUserInfo();" style="display: none;" class="btn btn-warning finish" >保存</button>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="form-actions tan_bottom" >
		<button class="btn" type="button" onclick="cancel();" style="position:fixed;left:482px;">取消</button>
	</div>
	<script>
		function uploadFile() {
			$('#uploader').uploadifive({
				'auto': true,
				'fileObjName' : 'file',
				//'queueID': 'queue',
				'buttonText': '上传最喜欢的图片',
				removeCompleted:true,
				formData: {
					'jsessionId': '<%=request.getSession().getId()%>'
				},
				'uploadScript': '/uploader/common',
				'onUploadComplete': function (file,data) {
					var $jsonObj = eval("(" + data + ")");
					var img = '<div class="img_1"  style="margin: 5px;">' +
							'<img style="width:233px;height:130px;" class="ims" onclick="change(this);" src="'
							+ $jsonObj.url
							+ '"/>&nbsp;&nbsp;&nbsp;</div>';
						$("#pictureUuid").val($jsonObj.uuid);

					$("#zp").html(img);
				},
				onUpload:function (file) { //上传开始时触发（每个文件触发一次）
					$("#infoBox").prev("p").css("display", "none");
					$("#infoBox").css("display", "block");
				},
				onFallback : function() {
					alert("该浏览器无法使用!");
				},
			});
		}
		function initSelect() {
			//初始填充年级
			//因查询年级不需学期，所以不需在学期填充后的回调中执行
			addOption('/indicators/index/grade?schoolYear='+${year}, "nj", "id", "name",${gradeId});
			//添加班级
			addOption('/teach/team/list/json?enableRole=false&gradeId='+${gradeId}, "bj", "id", "name",${teamId})
		}
		$("#nj").change(function(){
			$("#bj").html('<option value="">请选择</option>');
			//添加班级
				addOption('/teach/team/list/json?enableRole=false&gradeId=' + $(this).val(), "bj", "id", "name",null)
		})

		function addOption(url, id, valProperty, namePropety,aaa, callback) {
			var ia=$("#"+id).val();
			$.get(url, function (d) {
				var  cd = JSON.parse(d);
				for (var i = 0; i < cd.length; i++) {
					var obj = cd[i];
					if (aaa == obj[valProperty]) {
						$("#" + id).append("<option selected value=" + obj[valProperty] + ">" + obj[namePropety] + "</option>");
					} else {
						$("#" + id).append("<option value=" + obj[valProperty] + ">" + obj[namePropety] + "</option>");
					}
				}
				if (callback != null && callback != undefined) {
					callback(d);
				}
			})
		}




		var checker="";
	function cancel(){
		$.closeWindow();
	}
	$.createAvartarEditor({
		"btn" : "#imgId,.up_img",
		"avatarSize" : "208,208",
		"avatarSizeLabel" : "208x208"
	});
	
	function selectedImgHandler(data) {
		$("#imgId").attr("src", data.imgUrl);
		$("#photoUuid").val(data.uuid)
	}
	
	function numx2Str(){
		var obj = {"0":"一","1":"二","2":"三","3":"四","4":"五","5":"六","6":"七","7":"八","8":"九","9":"十"};
		$.each(obj, function(key, val) {      
			$("#li_"+key).text("家庭成员"+val);
	 	}); 
	}
		function change(obj) {
			var imgSrc = $(obj).attr("src");
			window.open(imgSrc);
		}
    $(function(){
		uploadFile();
		initSelect();
    	$(".t_table tr").each(function(){ 
			$(this).children("td:eq(0),td:eq(2)").css({"background-color":"#F4F4F4","text-align":"right","padding-right":"10px"});
		}); 
    	$(".btn_link .add_family").click(function(){
    		var i=$("table tbody > tr").length+1;
    		$("#stepy1-step-3").children("table").children("tbody").append("<tr><td>" +i+ "</td><td><input type='text' /></td><td><input type='text' /></td><td><input type='text' /></td><td><input type='text' /></td><td><button type='button' class='btn btn-blue'>编辑</button><button type='button' class='btn btn-warning'>删除</button></td></tr>")
    	});
    	numx2Str();
//     	切换家庭成员
		$(".family_table table").eq(0).show();
		$(".cy_select ul li").eq(0).addClass("on");
    	$(".cy_select ul li a").click(function(){
    		$(".cy_select ul li").removeClass("on");
    		$(this).parent().addClass("on");
    		var i=$(this).parent().index();
    		$(".family_table table").hide();
    		$(".family_table table").eq(i).show();
    	});
    	
        $('#modifyUserInfoForm').stepy({
        	backLabel: '上一步',
            nextLabel: '下一步',
            block: true,
            description: true,
            legend: false,
            titleClick: true,
            titleTarget: '#stepy_tabby1'
        });
        
        checker =  initValidator();
        
		//判断默认值
		//民族
		var mz = "${studentData.basic.race}";
		if(mz == null || mz == ""){
			mz = "01";
		}
		
		//国籍
		var gj = "${studentData.basic.nationality}";
		if(gj == null || gj == ""){
			gj = "156";
		}
		
		//身份证类型
		var sfz = "${studentData.basic.idCardType}";
		if(sfz == null || sfz == ""){
			sfz = "1";
		}
		
		//港澳台
		var gat = "${studentData.basic.abroadCode}";
		if(gat == null || gat == ""){
			gat = "00";
		}
		
		//政治面貌
		var zzmm = "${studentData.basic.politicalStatus}";
		if(zzmm == null || zzmm == ""){
			zzmm = "13";
		}
		
		//健康状况
		var jkzk = "${studentData.basic.healthStatus}";
		if(jkzk == null || jkzk == ""){
			jkzk = "1";
		}
		
		//户口性质
		var hkxz = "${studentData.assist.residenceType}";
		if(hkxz == null || hkxz == ""){
			hkxz = "3";
		}
		
		//入学方式
		var rxfs = "${studentData.archive.enrollType}";
		if(rxfs == null || rxfs == ""){
			rxfs = "01";
		}
		
		//就读方式
		var jdfs = "${studentData.archive.attendSchoolType}";
		if(jdfs == null || jdfs == ""){
			jdfs = "1";
		}
		
		//学生来源
		var xsly = "${studentData.archive.studentSource}";
		if(xsly == null || xsly == ""){
			xsly = "1";
		}

		//在读状态
		var zdzt = "${studentData.archive.studyState}";
		if(zdzt == null || zdzt == ""){
			zdzt = "01";
		}
		
		//性别
		$.jcGcSelector("#sex", {tc : "GB-XB"}, "${studentData.basic.sex }", function() {
			
		});
		//民族
		$.jcGcSelector("#race", {tc : "GB-MZ"}, mz, function() {
			
		});
		//国籍
		$.jcGcSelector("#nationality", {tc : "GB-GJ"}, gj, function() {
			
		});
		//身份证件类型
		$.jcGcSelector("#idCardType", {tc : "JY-SFZJLX"}, sfz, function() {
			
		});	
		//港澳台侨外码
		$.jcGcSelector("#abroadCode", {tc : "JY-GATQW"}, gat, function() {
			
		});	
		//政治面貌
		$.jcGcSelector("#politicalStatus", {tc : "GB-ZZMM"}, zzmm, function() {
			
		});
		//健康状况
		$.jcGcSelector("#healthStatus", {tc : "GB-JKZK"}, jkzk, function() {
			
		});
		//血型
		$.jcGcSelector("#bloodType", {tc : "JY-XX"}, "${studentData.basic.bloodType }", function() {
			
		});
		//户口性质
		$.jcGcSelector("#residenceType", {tc : "GB-HKLB"}, hkxz, function() {
			
		});
		//学生类别
		$.jcGcSelector("#studentType", {tc : "JY-XSLB","level":"1"}, "${studentData.archive.studentType }", function() {
			
		});
		//入学方式
		$.jcGcSelector("#enrollType", {tc : "JY-RXFS"}, rxfs, function() {
			
		});
		//就读方式
		$.jcGcSelector("#attendSchoolType", {tc : "JY-JDFS"}, jdfs, function() {
			
		});	
		//学生来源
		$.jcGcSelector("#studentSource", {tc : "JY-ZXXXSLY"}, xsly, function() {
			
		});	
		//是否随班就读
		$.jcGcSelector("#followStudy", {tc : "XY-JY-SBJD"}, "${studentData.extra.followStudy }", function() {
			
		});	
		//残疾类型
		$.jcGcSelector("#disabilityType", {tc : "JY-CJLX"}, "${studentData.extra.disabilityType }", function() {
			
		});	
		//所住房屋权属
		$.jcGcSelector("#houseAuthority", {tc : "XY-JY-FWQS"}, "${studentData.extra.houseAuthority }", function() {
			
		});	
		//上下学距离（公里）
		$.jcGcSelector("#schoolDistance", {tc : "XY-JY-SXXJL"}, "${studentData.traffic.schoolDistance }", function() {
			
		});	
		//上下学交通方式
		$.jcGcSelector("#trafficType", {tc : "XY-JY-JTFS"}, "${studentData.traffic.trafficType }", function() {
			
		});
		//在读状态
		$.jcGcSelector("#studyState", {tc : "JY-XSDQZT"}, "${studentData.archive.studyState}", function() {

		});
		
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
		
		var parentMess = "${studentData.parent.parentMess}";
		if(parentMess!=""){
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
				$.jcGcSelector("#parentRelation_"+i, {tc : "XY-JY-XSJTGX"}, obj[i].parentRelation, function() {	
				
				});	
				//是否监护人
				$.jcGcSelector("#rank_"+i, {tc : "XY-JY-JZLB"}, obj[i].rank, function() { 
					
				});	
				
				//家长民族
				var race = obj[i].race;
				if(race == null || race == "" || race == "null"){
					race = "01";
				}
				$.jcGcSelector("#pRace_"+i, {tc : "GB-MZ"},race, function() {
					
				});	
				
				//家长身份证件类型
				var idCardType = obj[i].idCardType;
				if(idCardType == null || idCardType == "" || idCardType == "null"){
					idCardType = "1";
				}
				$.jcGcSelector("#pIdCardType_"+i, {tc : "JY-SFZJLX"}, idCardType, function() { 
					
				});	
			}
		}
		
		//$.initRegionSelector("province", "city", "district", "true", "${userDetailInfo.province}", "${userDetailInfo.city}", "${userDetailInfo.district}");
// 		$.initRegionSelector({
//     		sjSelector : "province",
// 			shijSelector : "city",
// 			qxjSelector : "district",
			
// 			isEchoSelected : true,
// 			sjSelectedVal : "${userDetailInfo.province}",
// 			shijSelectedVal : "${userDetailInfo.city}",
// 			qxjSelectedVal : "${userDetailInfo.district}"
//     	});
		
		uploadImageFile();
		checker =  initValidator();
		
    })
//     $(function() {
// 		$('textarea.tinymce-simple').tinymce({
// 			script_url : '${pageContext.request.contextPath}/res/plugin/falgun/js/tiny_mce/tiny_mce.js',
// 			theme : "simple"
// 			});
// 		});
    

	function uploadImageFile(){
		 var obj = $("#uploader").uploadify({
            swf: '${pageContext.request.contextPath}/res/js/common/plugin/uploadify/uploadify.swf',
            uploader: '${pageContext.request.contextPath}/uploader/common',
            formData: {'jsessionId': '<%=request.getSession().getId()%>'},
            fileObjName: 'file',
            fileTypeDesc: "图片上传",
            fileTypeExts: "*.jpg",
            method: 'post',
            multi: false, // 是否能选择多个文件
            auto: true, //Set to false if you do not want the files to automatically upload when they are added to the queue.
            removeTimeout: 1,
            queueSizeLimit: 1,
            fileSizeLimit: 4 * 1024,
            buttonText: "图片上传",
            requeueErrors: false, //If set to true, files that return errors during an upload are requeued and the upload is repeatedly tried.
            height: 20,
            width: 66,
            onUploadSuccess: function(file, data, response) {
           	  	var $jsonObj = eval("(" + data + ")");
           	 	$("#photoUuid").val($jsonObj.uuid);
           	 	$("#imgId").attr('src',$jsonObj.url); 
            },
            onUploadStart: function(file) { //上传开始时触发（每个文件触发一次）
                $("#infoBox").prev("p").css("display", "none");
                $("#infoBox").css("display", "block");
            },
            onUploadError: function(file, errorCode, errorMsg, errorString) {
            	$.alert('The file ' + file.name + ' could not be uploaded: ' + errorString);
            }
        });
	}
    //
	function parent2Json(){
		var parentData = "";
		var parentlength = "${parentlength}";
		for(var i =0; i< parentlength;i++){
			var parent = "{";
			var name = $("#pName_"+i).val();
			var parentRelation = $("#parentRelation_"+i).val();
			var prealtionRemarks = $("#prealtionRemarks_"+i).val();
			var race = $("#pRace_"+i).val();
			var workingPlace = $("#workingPlace_"+i).val();
			var address = $("#pAddress_"+i).val();
			//var residenceAddress = $("#residenceAddress_"+i).val();
			var residenceAddressCode = $("#qxjp_"+i).val();
			if($("#qxjp_"+i).val() != "" && $("#qxjp_"+i).val() != null && $("#qxjp_"+i).val() != undefined){
				residenceAddressCode = $("#qxjp_"+i).val();
    		}else if($("#shijp_"+i).val() != "" && $("#shijp_"+i).val() != null && $("#shijp_"+i).val() != undefined){
    			residenceAddressCode = $("#shijp_"+i).val();
    		}else{
    			residenceAddressCode = $("#sjp_"+i).val();
    		}
			var mobile = $("#mobile_"+i).val();
			var rank = $("#rank_"+i).val();
			var idCardType = $("#pIdCardType_"+i).val();
			var idCardNumber = $("#pIdCardNumber_"+i).val();
			var position = $("#position_"+i).val();
			var parentUserId = $("#parentUserId_"+i).val();
			
			parent += "name:'"+name;
			parent += "',parentRelation:'"+parentRelation;
			parent += "',prealtionRemarks:'"+prealtionRemarks;
			parent += "',race:'"+race;
			parent += "',workingPlace:'"+workingPlace;
			parent += "',address:'"+address;
			parent += "',residenceAddressCode:'"+residenceAddressCode;
			parent += "',mobile:'"+mobile;
			parent += "',rank:'"+rank;
			parent += "',idCardType:'"+idCardType;
			parent += "',idCardNumber:'"+idCardNumber;
			parent += "',position:'"+position;
			parent += "',parentUserId:'"+parentUserId;
			parent += "'}";
	
			if(parentData != ""){
				parentData += "," + parent;
			}else{
				parentData = "[" + parent;
			}
		}
		if(parentData != ""){
			parentData += "]";
		}
		return parentData;
	}
    
	function checkComplet(){
    	var status = true;
    	$("input[type='text']").each(function(){
    		if($(this).attr("id")!="mobile") {
    			if($(this).val()==""){
        			status=false;
        			return;
        		}
			}
    	})
    	$("select").each(function(){
    		if($(this).val()==""){
    			status=false;
    			return;
    		}
    	})
    	if("${parentlength}" == 0){
    		status=false;
    	}
    	return status;
    }
	
	function updateUserInfo(){
		var loader = new loadLayer();
    	if (checker.form()) {
    		//2016-8-15新增学号与学籍辅助号的验证   没有选择班级的，班内学号不能填
        	if($("#teamId").val()==""){
        		if($("#number").val() != ""){
        			$.alert("当前您输入了学生班内学号，请为学生分班或者选择班级！");
        		}
        	}
    		
        	if($("#qxj1").val() != "" && $("#qxj1").val() != null && $("#qxj1").val() != undefined){
    			$("#qxj1").attr("name","birthPlaceCode");
    		}else if($("#shij1").val() != "" && $("#shij1").val() != null && $("#shij1").val() != undefined){
    			$("#shij1").attr("name","birthPlaceCode");
    		}else{
    			$("#sj1").attr("name","birthPlaceCode");
    		}
        	
        	if($("#qxj").val() != "" && $("#qxj").val() != null && $("#qxj").val() != undefined){
    			$("#qxj").attr("name","nativePlaceCode");
    		}else if($("#shij").val() != "" && $("#shij").val() != null && $("#shij").val() != undefined){
    			$("#shij").attr("name","nativePlaceCode");
    		}else{
    			$("#sj").attr("name","nativePlaceCode");
    		}
        	
        	if($("#qxj2").val() != "" && $("#qxj2").val() != null && $("#qxj2").val() != undefined){
    			$("#qxj2").attr("name","residenceAddressCode");
    		}else if($("#shij2").val() != "" && $("#shij2").val() != null && $("#shij2").val() != undefined){
    			$("#shij2").attr("name","residenceAddressCode");
    		}else{
    			$("#sj2").attr("name","residenceAddressCode");
    		}
			    	var ganyan=$("#ganyan").val();
			var ganyan1=$("#ganyan1").val();
			var pictureUuid=$("#pictureUuid").val();
    		var $requestData = formData2JSONObj("#modifyUserInfoForm");
    		var parentdata = parent2Json();
    		if(parentdata == null || parentdata == "undefind"){
    			parentdata = "";
    		}
			$requestData.ganyan=ganyan;
			$requestData.ganyan1=ganyan1;
			$requestData.pictureUuid=pictureUuid;
    		$requestData.parentData = parentdata;
    		$requestData.teamId =$("#bj").val();
			$requestData.gradeId=$("#nj").val();
			$requestData.ordelGradeId=${gradeId};
    		
        	var url = "${pageContext.request.contextPath}/teach/student/updateStudent";
        	
        	var statu = checkComplet();
        	
        	$requestData.isComplet = statu;
        	
        	/* var aliasName = '${userDetailInfo.alias}';
        	if(aliasName != null && aliasName != ""){
        		$requestData.alias = aliasName;
        	} */
        	
        	//console.log(JSON.stringify($requestData));
			loader.show();
        	$.post(url, $requestData, function(data, status) {
        		if("success" === status) {
        			data = eval("(" + data + ")");
        			if("success" === data.info) {
        				$.success("保存成功");
        				if(parent.core_iframe != null) {
        						parent.core_iframe.window.location.reload();
        					} else {
        						parent.window.location.reload();
        					}
        				$.closeWindow();
        			}else if("studentNumber2IsExit" === data.info){
        				$.error("学籍号已存在");
        			}else if("teamNumberIsExit" === data.info){
        				$.error("班内学号已存在");
        			} else {
        				$.error(data.info);
        			}
        		}else{
        			$.error("服务器异常");
        		}
				loader.close();
        	});
    	}else{
    		$.alert("请完善页签上的所有必填信息！");
    		return;
    	}
    }
    
function initValidator() {
	return $("#modifyUserInfoForm").validate({
			errorClass : "myerror",
			rules : {
				"name" : {
					required : true,
					minlength : 1,
					maxlength : 10,
					chinese : true
				},
//				"pinyinName" : {
//					maxlength : 25,
//					isEnglish : true
//				},
//				"englishName" : {
//					maxlength : 25,
//					isEnglish : true
//				},
				"sex":{
					required : true
				},"gradeId":{
					required : true
				},"teamId":{
					required : true
				}
//				"birthday" : {
//					required : true
//				},
//				"role":{
//					required : true
//				},
//				"birthPlace" : {
//					required : true,
//					maxlength : 20
//				},
//				"nativePlace" : {
//					maxlength : 25,
//					required : true
//				},
//				"race" : {
//					required : true
//				},
//				"nationality" : {
//					required : true
//				},
//				"idCardType" : {
//
//					required : true
//				},
//				"idCardNumber" : {
//					maxlength : 25,
//					required : true
//				},
//				"abroadCode" : {
//					required : true
//				},
//				"healthStatus" : {
//					required : true
//				},
//				"residenceAddress" : {
//					maxlength : 50,
//					required : true
//				},
//				"residenceType" : {
//					required : true
//				},
//				"enrollType" : {
//					required : true
//				},
//				"attendSchoolType" : {
//					required : true
//				},
//				"resideAddress" : {
//					required : true,
//					maxlength : 50
//				},
//				"usedName" : {
//					maxlength : 10
//				},
//				"specialSkill" : {
//					maxlength : 50
//				},
//				"address" : {
//					required : true,
//					maxlength : 50
//				},
//				"zipCode" : {
//					maxlength : 10,
//					postCode : true
//				},
//				"email" : {
//					maxlength : 25,
//					email : true
//				},
//				"homeAddress" : {
//					required : true,
//					maxlength : 100
//				},
//				"homepage" : {
//					maxlength : 100,
//					url : true
//				},
//				"mobile" : {
//					mobile : true,
//					required : false
//				},
//				"isOnlyChild" : {
//					required : true
//				},
//				"isPreeducated" : {
//					required : true
//				},
//				"isUnattendedChild" : {
//					required : true
//				},
//				"isCityLabourChild" : {
//					required : true
//				},
//				"isOrphan" : {
//					required : true
//				},
//				"isMartyrChild" : {
//					required : true
//				},
//				"isSponsored" : {
//					required : true
//				},
//				"isFirstRecommended" : {
//					required : true
//				},
//				"remark" : {
//					maxlength : 500
//				}
// 				,
// 				"studentNum":{
// 					required : true,
// 					checkStudentNum:true,
// 					remote : {
// 						async : false,
// 						type : "GET",
// 						url : "${pageContext.request.contextPath}/teach/student/checkerStudentNum",
// 						data : {
// 							'dxlx' : 'studentNum',
// 							'studentNum' : function() {
// 								return $("#studentNum").val();
// 							}
// 						}
// 					}
// 				}
			},
			messages : {
				"name":{
					required:"名字必填",
					maxlength:"最长不能超10个汉字",
					chinese : "请输入中文"
				},
				"sex":{
					required:"性别必选"
				}
// 				"studentNum":{
// 					required:"学籍号必填",
// 					checkStudentNum:"学籍号格式有误",
// 					remote:"学籍号已存在"
// 				},
//				"englishName":{
//					isEnglish:"必须是英文"
//				}
			}
		});
	}
  </script>
</body>
</html>
