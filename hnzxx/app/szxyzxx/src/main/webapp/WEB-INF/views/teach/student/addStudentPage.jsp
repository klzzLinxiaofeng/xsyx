<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<%@ include file="/views/embedded/common.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="${pageContext.request.contextPath}/res/plugin/falgun/js/stepy.jquery.js"></script>
<script src="${pageContext.request.contextPath}/res/plugin/falgun/js/tiny_mce/tiny_mce.js"></script>
	<script type="text/javascript" defer="defer" src="${ctp}/res/plugin/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/common/plugin/uploadify/jquery.uploadify.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/common/plugin/uploadify/jquery.uploadify.min.js"></script>
<%@ include file="/views/embedded/plugin/avatar_js.jsp"%>
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
    bottom:10px;
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
.button-next,#stepy1-buttons-6 .button-back{
	left:412px;
}
.btn-extend:hover{
	background-color: #BF7204;
}
.chzn-container-single .chzn-search input{box-sizing: border-box;}
</style>
</head>
<body>
	<div class="container-fluid">
	<div class="row-fluid ">
                <div class="span12">
                    <ul class="breadcrumb">
                        <li><a href="#"><i name="dashboard" class="fa fa-home"></i></a>添加学生</li>
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
						<button href="javascript:void(0)" onclick="saveUserInfo();" class="btn btn-warning finish" style="position:absolute;right:25px;top:11px;">保存</button>
					</div>
					<div class="widget-container gray ">
						<div class="form-container">
							<form id="stepy1" class="form-horizontal left-align form-well" novalidate="novalidate" style="padding-bottom:0;margin-bottom:0">
								<fieldset title="基本信息">
									<legend style="display: none;">基本账户信息</legend>
									<table class="table table-bordered t_table">
										<tbody>
											<tr>
												<td><span class="red">*</span>姓名</td>
												<td><input type="text" id="name" maxlength="10" name="name" value="" data-id="" placeholder="姓名" /></td>
												<td><span class="red"></span>身份证件类型</td>
												<td><select name="idCardType" id="idCardType"></select></td>
												<td rowspan="8" style="width: 165px; position: relative">
													<img src="${pageContext.request.contextPath}/res/images/noPhoto.jpg" id="imgId">
													<a href="javascript:void(0)" class="up_img"></a> 
													<!-- <input type="file" id="uploader" name="uploader"/> -->
													<input type="hidden" id="photoUuid" name="photoUuid" value=""/>
												</td>
											</tr>
											<tr>
												<td><span class="red">*</span>性别</td>
												<td><select id="sex" name="sex"></select></td>
												<td><span class="red"></span>身份证件号</td>
												<td><input type="text" class="{chrnum : true}" name="idCardNumber" id="idCardNumber" value="" placeholder="证件号码"/></td>
											</tr>
											<tr>
												<td><span class="red"></span>出生日期</td>
												<td><input type="text" id="birthday" name="birthday" value="" placeholder="出生日期" onclick="WdatePicker();"/></td>
												<td><span class="red"></span>港澳台侨外</td>
												<td><select name="abroadCode" id="abroadCode" ></select></td>
											</tr>
											
											<tr>
												
												<td>政治面貌</td>
												<td><select id="politicalStatus" name="politicalStatus"></select></td>
												<td><span class="red"></span>健康状况</td>
												<td><select id="healthStatus" name="healthStatus"></select></td>
											</tr>
											<tr>
												<td><span class="red"></span>民族</td>
												<td><select name="race" id="race"></select></td>
<!-- 												<td>血型</td> -->
<!-- 												<td><select name="bloodType" id="bloodType"></select></td> -->
												<td>角色</td>
												<td>
													<select name="roleId" id="roleId">
														<option value="${role.id}">${role.name}</option>
													</select>
												</td>
											</tr>
											<tr>
												<td><span class="red"></span>国籍/地区</td>
												<td colspan="3"><select name="nationality" id="nationality"></select></td>
<!-- 												<td>角色</td> -->
<!-- 												<td> -->
<!-- 													<select name="roleId" id="roleId"> -->
<%-- 														<option value="${role.id}">${role.name}</option> --%>
<!-- 													</select> -->
<!-- 												</td> -->
											</tr>
											<tr>
												<td><span class="red"></span>出生地</td>
												<td colspan="3">
<!-- 													<input  type="text" name="birthPlace" id="birthPlace" value="" placeholder="出生地"/> -->
													<select id="sj1" style="width:32%;float:left;margin-right:5px;"></select>
													<select id="shij1" style="width:33%;float:left;margin-right:5px;"></select>
													<select id="qxj1"  style="width:33%"></select>
												</td>
												
											</tr>
											<tr>
												<td><span class="red"></span>籍贯</td>
												<td colspan="3">
<!-- 													<input type="text" name="nativePlace" id="nativePlace" value="" placeholder="籍贯"/> -->
													<select id="sj" style="width:32%;float:left;margin-right:5px;"></select>
													<select id="shij" style="width:33%;float:left;margin-right:5px;"></select>
													<select id="qxj" style="width:33%;"></select>
												</td>
											</tr>
										</tbody>
									</table>
								</fieldset>
								<fieldset title="辅助信息">
									<legend style="display: none;">户口、特长</legend>
									<table class="table table-bordered t_table">
										<!-- <tbody>
											<tr>
												<td>姓名拼音</td>
												<td><input type="text" id="pinyinName" name="pinyinName" value="" placeholder="姓名拼音"/></td>
												<td><span class="red">*</span>户口所在地</td>
												<td>
												<input type="text"  name="residenceAddress" id="residenceAddress" value="" placeholder="户口所在地"/>
													<select id="sj2" style="width:50%;float:left"></select>
													<select id="shij2" style="width:50%"></select>
													<select id="qxj2" name="residenceAddressCode" style="width:50%"></select>
												</td>
											</tr>
											<tr>
												<td>曾用名</td>
												<td><input type="text" id="usedName" name="usedName" value="" placeholder="曾用名"/></td>
												<td><span class="red">*</span>户口性质</td>
												<td><select name="residenceType" id="residenceType"></select></td>
											</tr>
											<tr>
												<td>身份证有效期</td>
												<td><input type="text" id="idCardDate" name="idCardDate" value="" placeholder="身份证有效期" onclick="WdatePicker();"/></td>
												<td>特长</td>
												<td><input  type="text" name="specialSkill" id="specialSkill" value="" placeholder="特长"/></td>
											</tr>
										</tbody> -->
										<tbody>
											<tr>
												<td>姓名拼音</td>
												<td><input type="text" id="pinyinName" name="pinyinName" value="" placeholder="姓名拼音"/></td>
												<td>曾用名</td>
												<td><input type="text" id="usedName" name="usedName" value="" placeholder="曾用名"/></td>
												
											</tr>
											<tr>
												<td><span class="red"></span>户口性质</td>
												<td><select name="residenceType" id="residenceType"></select></td>
												<td>身份证有效期</td>
												<td><input type="text" id="idCardDate" name="idCardDate" value="" placeholder="身份证有效期" onclick="WdatePicker();"/></td>
											</tr>
											<tr>
												<td><span class="red"></span>户口所在地</td>
												<td colspan="3">
<!-- 												<input type="text"  name="residenceAddress" id="residenceAddress" value="" placeholder="户口所在地"/> -->
													<select id="sj2" style="width:32%;float:left;margin-right:5px"></select>
													<select id="shij2" style="width:33%;float:left;margin-right:5px"></select>
													<select id="qxj2"  style="width:33%"></select>
												</td>
											</tr>
											<tr>
												<td>特长</td>
												<td colspan="3"><input  type="text" name="specialSkill" id="specialSkill" value="" placeholder="特长"/></td>
											</tr>
										</tbody>
									</table>
								</fieldset>
								<fieldset title="学籍信息">
									<legend style="display: none;">学籍、班级</legend>
									<table class="table table-bordered t_table">
										<tr>
											<td>学籍辅号</td>
											<td><input type="text" maxlength="10"  name="studentNumber" id="studentNumber" value="" placeholder="学籍辅号"/></td>
											<td>入学时间</td>
											<td><input type="text" id="enrollDate" name="enrollDate" style="width:320px;" value="" placeholder="入学时间" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'leaveDate\')}',dateFmt:'yyyy-MM-dd HH:mm'});"/></td>
										</tr>
										<tr>
											<td>班内学号</td>
											<td><input type="text" maxlength="3" name="number" id="number" value="" placeholder="班内学号"/></td>
											<td><span class="red"></span>入学方式</td>
											<td><select name="enrollType" id="enrollType"></select></td>
										</tr>
										<tr>
<!-- 											<td>学生类别</td> -->
<!-- 											<td><select name="studentType" id="studentType"></select></td> -->
											<td>年级</td>
											<td><select name="gradeId" id="gradeId" style="width:332px;"></select></td>
											<td><span class="red"></span>就读方式</td>
											<td><select name="attendSchoolType" id="attendSchoolType"></select></td>
										</tr>
										<tr>
											<td>班级</td>
											<td><select name="teamId" id="teamId" style="width:332px;"></select></td>
											<td>学生来源</td>
											<td><select name="studentSource" id="studentSource"  ></select></td>
										</tr>
										<tr>
											<td>离校时间</td>
											<td><input type="text" id="leaveDate" name="leaveDate" value="" placeholder="离校时间" onclick="WdatePicker({minDate:'#F{$dp.$D(\'enrollDate\')}',dateFmt:'yyyy-MM-dd HH:mm'});"/></td>
											<td></td>
											<td></td>
										</tr>
									</table>
								</fieldset>
								<fieldset title="联系信息">
									<legend style="display: none;">住址、联系方式</legend>
									<table class="table table-bordered t_table">
										<tr>
											<td><span class="red"></span>现住址</td>
											<td><input type="text"  name="address" id="address" value="" placeholder="现住址"/></td>
											<td>邮政编码</td>
											<td><input type="text"  name="zipCode" id="zipCode" value="" placeholder="邮政编码"/></td>
										</tr>
										<tr>
											<td><span class="red"></span>通信地址</td>
											<td><input type="text"  name="resideAddress" id="resideAddress" value="" placeholder="通信地址"/></td>
											<td>电子邮箱</td>
											<td><input type="text"  name="email" id="email" value="" placeholder="电子邮箱"/></td>
										</tr>
										<tr>
											<td><span class="red"></span>家庭地址</td>
											<td><input type="text"  name="homeAddress" id="homeAddress" value="" placeholder="家庭地址"/></td>
											<td>主页地址</td>
											<td><input type="text"  name="homepage" id="homepage" value="" placeholder="主页地址"/></td>
										</tr>
										<tr>
											<td>联系电话</td>
											<td><input type="text"  name="mobile" id="mobile" value="" placeholder="联系电话"/></td>
											<td></td>
											<td></td>
										</tr>
									</table>
								</fieldset>
								<fieldset title="扩展信息">
									<legend style="display: none;">个人信息调查</legend>
									<table class="table table-bordered t_table">
										<tr>
											<td><span class="red"></span>是否独生子女</td>
											<td>
												<select name="isOnlyChild" id="isOnlyChild">
													<option value="0">否</option>
													<option value="1">是</option>
												</select>
											</td>
											<td>随班就读</td>
											<td><select name="followStudy" id="followStudy"></select></td>
										</tr>
										<tr>
											<td><span class="red"></span>是否受过学前教育</td>
											<td>
												<select name="isPreeducated" id="isPreeducated">
													<option value="0">否</option>
													<option value="1">是</option>
												</select>
											</td>
											<td>残疾人类型</td>
											<td><select name="disabilityType" id="disabilityType"></select></td>
										</tr>
										<tr>
											<td><span class="red"></span>是否留守儿童</td>
											<td>
												<select name="isUnattendedChild" id="isUnattendedChild">
													<option value="0">非留守儿童</option>
													<option value="1">单亲留守儿童</option>
													<option value="2">双亲留守儿童</option>
												</select>
											</td>
											<td>是否由政府购买学位</td>
											<td>
												<select name="isBuyedDegree" id="isBuyedDegree">
													<option value="0">否</option>
													<option value="1">是</option>
												</select>
											</td>
										</tr>
										<tr>
											<td><span class="red"></span>是否进城务工人员随迁子女</td>
											<td>
												<select name="isCityLabourChild" id="isCityLabourChild">
													<option value="0">否</option>
													<option value="1">是</option>
												</select>
											</td>
											<td><span class="red"></span>是否需要申请资助</td>
											<td>
												<select name="isSponsored" id="isSponsored">
													<option value="0">否</option>
													<option value="1">是</option>
												</select>
											</td>
										</tr>
										<tr>
											<td><span class="red"></span>是否孤儿</td>
											<td>
												<select name="isOrphan" id="isOrphan">
													<option value="0">否</option>
													<option value="1">是</option>
												</select>
											</td>
											<td><span class="red"></span>是否享受一补</td>
											<td>
												<select name="isFirstRecommended" id="isFirstRecommended">
													<option value="0">否</option>
													<option value="1">是</option>
												</select>
											</td>
										</tr>
										<tr>
											<td><span class="red"></span>是否烈士或优抚子女</td>
											<td>
												<select name="isMartyrChild" id="isMartyrChild">
													<option value="0">否</option>
													<option value="1">是</option>
												</select>
											</td>
											<td></td>
											<td></td>
<!-- 											<td>是否因身体原因需要学校特别照顾</td> -->
<!-- 											<td> -->
<!-- 												<select name="needSpecialCare" id="needSpecialCare"> -->
<!-- 													<option value="0">否</option> -->
<!-- 													<option value="1">是</option> -->
<!-- 												</select> -->
<!-- 											</td> -->
										</tr>
<!-- 										<tr> -->
<!-- 											<td>所住房屋权属</td> -->
<!-- 											<td><select name="houseAuthority" id="houseAuthority"></select></td> -->
<!-- 										</tr> -->
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
													<option value="0">否</option>
													<option value="1">是</option>
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
										<li><a href="javascript:void(0)">家庭成员一</a></li>
										<li><a href="javascript:void(0)">家庭成员二</a></li>
									</ul>
									<div class="clear"></div>
									</div>
								<div class="family_table">
									<table class="table table-bordered t_table">
										<tr>
											<td><span class="red"></span>家庭成员或监护人姓名</td>
											<td><input type="text" class="{maxlength : 10,required: true}"  id="pName_0" value="家庭成员一"/></td>
											<td><span class="red"></span>关系</td>
											<td><select class="parentRelation {required: true}"  id="parentRelation_0"></select></td>
										</tr>
										
										<tr>
											<td><span class="red"></span>联系电话</td>
											<td><input type="text" class="{mobile:true,required:true}" id="mobile_0" placeholder="联系电话"/></td>
											<td><span class="red"></span>身份证件号</td>
											<td><input type="text" class="{maxlength:18,chrnum : true,required:true}"  id="pIdCardNumber_0"  placeholder="证件号码"/></td>
										</tr>
										<tr>
											<td>关系说明</td>
											<td><input type="text" class="{maxlength : 20}"  id="prealtionRemarks_0" placeholder="关系说明"/></td>
											<td><span class="red"></span>是否监护人</td>
											<td><select class="rank" class="{required: true}" id="rank_0" ></select></td>
										</tr>
										<tr>
											<td>民族</td>
											<td><select class="pRace" id="pRace_0"></select></td>
											<td><span class="red"></span>身份证件类型</td>
											<td><select class="pIdCardType" class="{required: true}" id="pIdCardType_0"></select></td>
										</tr>
										<tr>
											<td>工作单位</td>
											<td><input type="text" class="{maxlength:20}" id="workingPlace_0"  placeholder="工作单位"/></td>
											<td>职务</td>
											<td><input type="text" id="position_0"  placeholder="职务"/></td>
										</tr>
										<tr>
											<td><span class="red"></span>现住址</td>
											<td colspan="3"><input type="text" class="{required: true}" id="pAddress_0" placeholder="现住址"/></td>
										</tr>
										<tr>
											<td><span class="red"></span>户口所在地</td>
											<td colspan="3">
												<select id="sjp_0" class="{required: true}" style="width:32%;float:left;margin-right:5px"></select>
												<select id="shijp_0" style="width:33%;float:left;margin-right:5px"></select>
												<select id="qxjp_0"  style="width:33%;"></select>
											</td>
										</tr>
									</table>
									<table class="table table-bordered t_table" style="display:none">
										<tr>
											<td><span class="red"></span>家庭成员或监护人姓名</td>
											<td><input type="text" class="{maxlength : 10,required: true}"  id="pName_1" value="家庭成员二"/></td>
											<td><span class="red"></span>关系</td>
											<td><select class="parentRelation {required: true}"  id="parentRelation_1"></select></td>
										</tr>
										
										<tr>
											<td><span class="red"></span>联系电话</td>
											<td><input type="text" class="{mobile:true,required:true}" id="mobile_1" placeholder="联系电话"/></td>
											<td><span class="red"></span>身份证件号</td>
											<td><input type="text" class="{maxlength:18,chrnum : true,required:true}"  id="pIdCardNumber_1"  placeholder="证件号码"/></td>
										</tr>
										<tr>
											<td>关系说明</td>
											<td><input type="text" class="{maxlength : 20}"  id="prealtionRemarks_1" placeholder="关系说明"/></td>
											<td><span class="red"></span>是否监护人</td>
											<td><select class="rank" class="{required: true}" id="rank_1" ></select></td>
										</tr>
										<tr>
											<td>民族</td>
											<td><select class="pRace" id="pRace_1"></select></td>
											<td><span class="red"></span>身份证件类型</td>
											<td><select class="pIdCardType" class="{required: true}" id="pIdCardType_1"></select></td>
										</tr>
										<tr>
											<td>工作单位</td>
											<td><input type="text" class="{maxlength:20}" id="workingPlace_1"  placeholder="工作单位"/></td>
											<td>职务</td>
											<td><input type="text" id="position_1"  placeholder="职务"/></td>
										</tr>
										<tr>
											<td><span class="red"></span>现住址</td>
											<td colspan="3"><input type="text" class="{required: true}" id="pAddress_1" placeholder="现住址"/></td>
										</tr>
										<tr>
											<td><span class="red"></span>户口所在地</td>
											<td colspan="3">
												<select id="sjp_1" class="{required: true}" style="width:32%;float:left;margin-right:5px"></select>
												<select id="shijp_1" style="width:33%;float:left;margin-right:5px"></select>
												<select id="qxjp_1"  style="width:33%;"></select>
											</td>
										</tr>
									</table>
									</div>
								</fieldset>
								
								<fieldset title="简历">
									<legend style="display: none;">学生简历</legend>
									<div class="widget-container">
										<div class="control-group" style="width:100%;">
											<div class="controls">
												<textarea id="resume" name="resume" rows="8" cols="120" style="width: 80%" class="tinymce-simple"></textarea>
											</div>
										</div>
									</div>
									<div class="clear"></div>
								</fieldset>
								
								<fieldset title="备注">
									<legend style="display: none;">其他信息</legend>
									<div class="widget-container">
										<div class="control-group" style="width:100%;">
											<div class="controls">
												<textarea id="remark" name="remark" rows="8" cols="120" style="width: 80%" class="tinymce-simple"></textarea>
											</div>
										</div>
									</div>
									<div class="clear"></div>
								</fieldset>
								<button href="javascript:void(0)" onclick="saveUserInfo();" class="btn btn-warning finish" style="display:none">保存</button>
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
// 		alert(JSON.stringify(data));
		$("#imgId").attr("src", data.imgUrl);
		$("#photoUuid").val(data.uuid)
	}
	
    $(function(){
    	$.initRegionLevelSelector({
			"lv" :"3",
			"sjSelector" : "sj",
			"shijSelector" : "shij",
			"qxjSelector" : "qxj"
		});
		
		$.initRegionLevelSelector({
			"lv" :"3",
			"sjSelector" : "sj1",
			"shijSelector" : "shij1",
			"qxjSelector" : "qxj1"
		});
		
		$.initRegionLevelSelector({
			"lv" :"3",
			"sjSelector" : "sj2",
			"shijSelector" : "shij2",
			"qxjSelector" : "qxj2"
		});
		
		 $.initNBSCascadeSelector({
			"type" : "team",
			"gradeSelectId" : "gradeId", //年级select标签ID 默认为nj
		   	"teamSelectId" : "teamId",
		   	"selectOne" : "true", 
		   	"isEchoSelected" : "false"
		});
 
//     	切换家庭成员
		$(".family_table table").eq(0).show();
		$(".cy_select ul li").eq(0).addClass("on");
    	$(".cy_select ul li a").click(function(){
    		$(".cy_select ul li").removeClass("on");
    		$(this).parent().addClass("on");
    		var i=$(this).parent().index();
    		$(".family_table table").hide();
    		$(".family_table table").eq(i).show();
    	})
    	
        $('#stepy1').stepy({
            backLabel: '上一步',
            nextLabel: '下一步',
            block: true,
            description: true,
            legend: false,
            titleClick: true,
            titleTarget: '#stepy_tabby1'
            });
		//==============================================
		//性别
		$.jcGcSelector("#sex", {tc : "GB-XB"}, "", function() {
			
		});
		//民族
		$.jcGcSelector("#race", {tc : "GB-MZ"}, "01", function() {
			
		});
		//国籍
		$.jcGcSelector("#nationality", {tc : "GB-GJ"}, "156", function() {
			
		});
		//身份证件类型
		$.jcGcSelector("#idCardType", {tc : "JY-SFZJLX"}, "1", function() {
			
		});	
		//港澳台侨外码
		$.jcGcSelector("#abroadCode", {tc : "JY-GATQW"}, "00", function() {
			
		});	
		//政治面貌
		$.jcGcSelector("#politicalStatus", {tc : "GB-ZZMM"}, "13", function() {
			
		});
		//健康状况
		$.jcGcSelector("#healthStatus", {tc : "GB-JKZK"}, "1", function() {
			
		});
		//血型
		$.jcGcSelector("#bloodType", {tc : "JY-XX"}, "", function() {
			
		});
		//户口性质
		$.jcGcSelector("#residenceType", {tc : "GB-HKLB"}, "3", function() {
			
		});
		//学生类别
		$.jcGcSelector("#studentType", {tc : "JY-XSLB","level":"1"}, "", function() {
			
		});
		//入学方式
		$.jcGcSelector("#enrollType", {tc : "JY-RXFS"}, "01", function() {
			
		});
		//就读方式
		$.jcGcSelector("#attendSchoolType", {tc : "JY-JDFS"}, "1", function() {
			
		});	
		//学生来源
		$.jcGcSelector("#studentSource", {tc : "JY-ZXXXSLY"}, "1", function() {
			
		});	
		//是否随班就读
		$.jcGcSelector("#followStudy", {tc : "XY-JY-SBJD"}, "", function() {
			
		});	
		//残疾类型
		$.jcGcSelector("#disabilityType", {tc : "JY-CJLX"}, "", function() {
			
		});	
		//所住房屋权属
		$.jcGcSelector("#houseAuthority", {tc : "XY-JY-FWQS"}, "", function() {
			
		});	
		//上下学距离（公里）
		$.jcGcSelector("#schoolDistance", {tc : "XY-JY-SXXJL"}, "", function() {
			
		});	
		//上下学交通方式
		$.jcGcSelector("#trafficType", {tc : "XY-JY-JTFS"}, "", function() {
			
		});	
		
		// 家庭成员信息选项
		//关系
		$.jcGcSelector("#parentRelation_0", {tc : "XY-JY-XSJTGX"}, "", function() {	});	
		$.jcGcSelector("#parentRelation_1", {tc : "XY-JY-XSJTGX"}, "", function() {	});	
		//是否监护人
		$.jcGcSelector("#rank_0", {tc : "XY-JY-JZLB"}, "1", function() { });	
		$.jcGcSelector("#rank_1", {tc : "XY-JY-JZLB"}, "0", function() { });	
		//家长民族
		$.jcGcSelector("#pRace_0", {tc : "GB-MZ"}, "01", function() { });	
		$.jcGcSelector("#pRace_1", {tc : "GB-MZ"}, "01", function() { });	
		//家长身份证件类型
		$.jcGcSelector("#pIdCardType_0", {tc : "JY-SFZJLX"}, "1", function() { });	
		$.jcGcSelector("#pIdCardType_1", {tc : "JY-SFZJLX"}, "1", function() { });	
		//户口所在地	
		$.initRegionLevelSelector({
			"lv" :"3",
			"sjSelector" : "sjp_0",
			"shijSelector" : "shijp_0",
			"qxjSelector" : "qxjp_0"
		});
		$.initRegionLevelSelector({
			"lv" :"3",
			"sjSelector" : "sjp_1",
			"shijSelector" : "shijp_1",
			"qxjSelector" : "qxjp_1"
		});
	
		//获取角色 JSON 数据  1为教师 2为管理员 4为学生的下拉列表
		$.RoleSelector({
			"condition" : {
				"userType" : "4"
			}
		});
		
		uploadImageFile();
		checker =  initValidator();
    });
    $(function(){
		$(".t_table tr").each(function(){ 
			$(this).children("td:eq(0),td:eq(2)").css({"background-color":"#F4F4F4","text-align":"right","padding-right":"10px"});
		}); 
	});
    $(function(){
    	$(".btn_link .add_family").click(function(){
    		var i=$("table tbody > tr").length+1;
    		$("#stepy1-step-3").children("table").children("tbody").append("<tr><td>" +i+ "</td><td><input type='text' /></td><td><input type='text' /></td><td><input type='text' /></td><td><input type='text' /></td><td><button type='button' class='btn btn-blue'>编辑</button><button type='button' class='btn btn-warning'>删除</button></td></tr>")
    	});
    });
    $(function() {
		$('textarea.tinymce-simple').tinymce({
			script_url : '${pageContext.request.contextPath}/res/plugin/falgun/js/tiny_mce/tiny_mce.js',
			theme : "simple"
			});
		});
    
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
    
    function parent2Json(){
		var parentData = "";
		for(var i = 0; i< 2;i++){
			var parent = "{";
			var name = $("#pName_"+i).val();
			var parentRelation = $("#parentRelation_"+i).val();
			var prealtionRemarks = $("#prealtionRemarks_"+i).val();
			var race = $("#pRace_"+i).val();
			var workingPlace = $("#workingPlace_"+i).val();
			var address = $("#pAddress_"+i).val();
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
    
    function saveUserInfo(){
    	//2016-8-15新增学号与学籍辅助号的验证   没有选择班级的，班内学号不能填
    	if($("#teamId").val()==""){
    		if($("#number").val() != ""){
    			$.alert("当前您输入了学生班内学号，请为学生分班或者选择班级！");
    		}
    	}
    	
    	//2016-3-17 添加必填提示校验
//    	if($("#photoUuid").val()==""){
//    		$.alert("请上传头像！");
//    		return;
//    	}
    	
    	if (checker.form()) {
    		
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
    		
    		var $requestData = formData2JSONObj("#stepy1");
    		var parentData = parent2Json();
    		$requestData.parentData = parentData;
        	var url = "${pageContext.request.contextPath}/teach/student/addStudent";
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
        			}else {
        				$.error(data.info);
        			}
        		}else{
        			$.error("服务器异常");
        		}
        	});
    	}else{
    		$.alert("请完善页签上的必填信息！");
    		return;
    	}
    }
    
    function initValidator() {
    	return $("#stepy1").validate({
				errorClass : "myerror",
				rules : {
					"name" : {
						required : true,
						minlength : 1,
						maxlength : 10,
						"chinese" : true
					},
//					"pinyinName" : {
//						maxlength : 25,
//						isEnglish : true
//					},
//					"englishName" : {
//						maxlength : 25,
//						isEnglish : true
//					},
					"sex":{
						required : true
					},"gradeId":{
						required : true
					},"teamId":{
						required : true
					}
//					"birthday" : {
//						required : true
//					},
//					"role":{
//						required : true
//					},
//					"birthPlace" : {
//						required : true,
//						maxlength : 20
//					},
//					"nativePlace" : {
//						maxlength : 25,
//						required : true
//					},
//					"race" : {
//						required : true
//					},
//					"nationality" : {
//						required : true
//					},
//					"idCardType" : {
//						required : true
//					},
//					"idCardNumber" : {
//						maxlength : 25,
//						required : true
//					},
//					"abroadCode" : {
//						required : true
//					},
//					"healthStatus" : {
//						required : true
//					},
//					"residenceAddress" : {
//						maxlength : 50,
//						required : true
//					},
//					"residenceType" : {
//						required : true
//					},
//					"enrollType" : {
//						required : true
//					},
//					"attendSchoolType" : {
//						required : true
//					},
//					"resideAddress" : {
//						required : true,
//						maxlength : 50
//					},
//					"usedName" : {
//						maxlength : 10
//					},
//					"specialSkill" : {
//						maxlength : 50
//					},
//					"address" : {
//						required : true,
//						maxlength : 50
//					},
//					"zipCode" : {
//						maxlength : 10,
//						postCode : true
//					},
//					"email" : {
//						maxlength : 25,
//						email : true
//					},
//					"homeAddress" : {
//						required : true,
//						maxlength : 100
//					},
//					"homepage" : {
//						maxlength : 100,
//						url : true
//					},
//					"mobile" : {
//						mobile : true
//// 						required : true
//					},
//					"isOnlyChild" : {
//						required : true
//					},
//					"isPreeducated" : {
//						required : true
//					},
//					"isUnattendedChild" : {
//						required : true
//					},
//					"isCityLabourChild" : {
//						required : true
//					},
//					"isOrphan" : {
//						required : true
//					},
//					"isMartyrChild" : {
//						required : true
//					},
//					"isSponsored" : {
//						required : true
//					},
//					"isFirstRecommended" : {
//						required : true
//					},
//					"remark" : {
//						maxlength : 500
//					}
// 					,
// 					"studentNum":{
// 						required : true,
// 						checkStudentNum:true,
// 						remote : {
// 							async : false,
// 							type : "GET",
// 							url : "${pageContext.request.contextPath}/teach/student/checkerStudentNum",
// 							data : {
// 								'dxlx' : 'studentNum',
// 								'studentNum' : function() {
// 									return $("#studentNum").val();
// 								}
// 							}
// 						}
// 					}
					
				},
				messages : {
					"name":{
						required:"名字必填",
						maxlength:"最长不能超10个汉字",
						chinese : "请输入中文"
					},
					"sex":{
						required:"性别必选"
					},
// 					"studentNum":{
// 						required:"学籍号必填",
// 						checkStudentNum:"学籍号格式有误,正确格式G+身份证号码",
// 						remote:"学籍号已存在"
// 					},
					"role":{
						required:"角色必选"
					},
					"englishName":{
						isEnglish:"必须是英文"
					}
				}
			});
    	}
    
    //验证学籍号
    function isCardNo() {
    	var card = "w411381198512113914";
    	var card_ = card.substr(0, 1);
    	var cardTemp = card.substr(1,card.length);
        if(card_ == "G"){
        	// 身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X  
            var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;  
            if(reg.test(cardTemp) === false){
            	$.alert("身份证输入不合法");  
                return  false;
            }else{
            	$.alert("合法");  
         	   return true;
            }
        }else{
        	$.alert("学籍号是以G开头的");
        	return false;
        }
       
    } 
  </script>
</body>
</html>
