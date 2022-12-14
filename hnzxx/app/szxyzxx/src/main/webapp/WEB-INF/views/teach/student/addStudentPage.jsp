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
                        <li><a href="#"><i name="dashboard" class="fa fa-home"></i></a>????????????</li>
                    </ul>
                </div>
        </div>
		<div class="row-fluid ">
			<div class="span12">
				<div class="content-widgets white" style="margin-bottom: 0;">
					<div class="widget-head">
						<h3>
							??????????????????
<!-- 							<p style="float: right;" class="btn_link"> -->
<!-- 								<a class="a4" href="javascript:void(0)"><i -->
<!-- 									class="fa fa-mail-reply"></i>????????????</a> -->
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
						<button href="javascript:void(0)" onclick="saveUserInfo();" class="btn btn-warning finish" style="position:absolute;right:25px;top:11px;">??????</button>
					</div>
					<div class="widget-container gray ">
						<div class="form-container">
							<form id="stepy1" class="form-horizontal left-align form-well" novalidate="novalidate" style="padding-bottom:0;margin-bottom:0">
								<fieldset title="????????????">
									<legend style="display: none;">??????????????????</legend>
									<table class="table table-bordered t_table">
										<tbody>
											<tr>
												<td><span class="red">*</span>??????</td>
												<td><input type="text" id="name" maxlength="10" name="name" value="" data-id="" placeholder="??????" /></td>
												<td><span class="red"></span>??????????????????</td>
												<td><select name="idCardType" id="idCardType"></select></td>
												<td rowspan="8" style="width: 165px; position: relative">
													<img src="${pageContext.request.contextPath}/res/images/noPhoto.jpg" id="imgId">
													<a href="javascript:void(0)" class="up_img"></a> 
													<!-- <input type="file" id="uploader" name="uploader"/> -->
													<input type="hidden" id="photoUuid" name="photoUuid" value=""/>
												</td>
											</tr>
											<tr>
												<td><span class="red">*</span>??????</td>
												<td><select id="sex" name="sex"></select></td>
												<td><span class="red"></span>???????????????</td>
												<td><input type="text" class="{chrnum : true}" name="idCardNumber" id="idCardNumber" value="" placeholder="????????????"/></td>
											</tr>
											<tr>
												<td><span class="red"></span>????????????</td>
												<td><input type="text" id="birthday" name="birthday" value="" placeholder="????????????" onclick="WdatePicker();"/></td>
												<td><span class="red"></span>???????????????</td>
												<td><select name="abroadCode" id="abroadCode" ></select></td>
											</tr>
											
											<tr>
												
												<td>????????????</td>
												<td><select id="politicalStatus" name="politicalStatus"></select></td>
												<td><span class="red"></span>????????????</td>
												<td><select id="healthStatus" name="healthStatus"></select></td>
											</tr>
											<tr>
												<td><span class="red"></span>??????</td>
												<td><select name="race" id="race"></select></td>
<!-- 												<td>??????</td> -->
<!-- 												<td><select name="bloodType" id="bloodType"></select></td> -->
												<td>??????</td>
												<td>
													<select name="roleId" id="roleId">
														<option value="${role.id}">${role.name}</option>
													</select>
												</td>
											</tr>
											<tr>
												<td><span class="red"></span>??????/??????</td>
												<td colspan="3"><select name="nationality" id="nationality"></select></td>
<!-- 												<td>??????</td> -->
<!-- 												<td> -->
<!-- 													<select name="roleId" id="roleId"> -->
<%-- 														<option value="${role.id}">${role.name}</option> --%>
<!-- 													</select> -->
<!-- 												</td> -->
											</tr>
											<tr>
												<td><span class="red"></span>?????????</td>
												<td colspan="3">
<!-- 													<input  type="text" name="birthPlace" id="birthPlace" value="" placeholder="?????????"/> -->
													<select id="sj1" style="width:32%;float:left;margin-right:5px;"></select>
													<select id="shij1" style="width:33%;float:left;margin-right:5px;"></select>
													<select id="qxj1"  style="width:33%"></select>
												</td>
												
											</tr>
											<tr>
												<td><span class="red"></span>??????</td>
												<td colspan="3">
<!-- 													<input type="text" name="nativePlace" id="nativePlace" value="" placeholder="??????"/> -->
													<select id="sj" style="width:32%;float:left;margin-right:5px;"></select>
													<select id="shij" style="width:33%;float:left;margin-right:5px;"></select>
													<select id="qxj" style="width:33%;"></select>
												</td>
											</tr>
										</tbody>
									</table>
								</fieldset>
								<fieldset title="????????????">
									<legend style="display: none;">???????????????</legend>
									<table class="table table-bordered t_table">
										<!-- <tbody>
											<tr>
												<td>????????????</td>
												<td><input type="text" id="pinyinName" name="pinyinName" value="" placeholder="????????????"/></td>
												<td><span class="red">*</span>???????????????</td>
												<td>
												<input type="text"  name="residenceAddress" id="residenceAddress" value="" placeholder="???????????????"/>
													<select id="sj2" style="width:50%;float:left"></select>
													<select id="shij2" style="width:50%"></select>
													<select id="qxj2" name="residenceAddressCode" style="width:50%"></select>
												</td>
											</tr>
											<tr>
												<td>?????????</td>
												<td><input type="text" id="usedName" name="usedName" value="" placeholder="?????????"/></td>
												<td><span class="red">*</span>????????????</td>
												<td><select name="residenceType" id="residenceType"></select></td>
											</tr>
											<tr>
												<td>??????????????????</td>
												<td><input type="text" id="idCardDate" name="idCardDate" value="" placeholder="??????????????????" onclick="WdatePicker();"/></td>
												<td>??????</td>
												<td><input  type="text" name="specialSkill" id="specialSkill" value="" placeholder="??????"/></td>
											</tr>
										</tbody> -->
										<tbody>
											<tr>
												<td>????????????</td>
												<td><input type="text" id="pinyinName" name="pinyinName" value="" placeholder="????????????"/></td>
												<td>?????????</td>
												<td><input type="text" id="usedName" name="usedName" value="" placeholder="?????????"/></td>
												
											</tr>
											<tr>
												<td><span class="red"></span>????????????</td>
												<td><select name="residenceType" id="residenceType"></select></td>
												<td>??????????????????</td>
												<td><input type="text" id="idCardDate" name="idCardDate" value="" placeholder="??????????????????" onclick="WdatePicker();"/></td>
											</tr>
											<tr>
												<td><span class="red"></span>???????????????</td>
												<td colspan="3">
<!-- 												<input type="text"  name="residenceAddress" id="residenceAddress" value="" placeholder="???????????????"/> -->
													<select id="sj2" style="width:32%;float:left;margin-right:5px"></select>
													<select id="shij2" style="width:33%;float:left;margin-right:5px"></select>
													<select id="qxj2"  style="width:33%"></select>
												</td>
											</tr>
											<tr>
												<td>??????</td>
												<td colspan="3"><input  type="text" name="specialSkill" id="specialSkill" value="" placeholder="??????"/></td>
											</tr>
										</tbody>
									</table>
								</fieldset>
								<fieldset title="????????????">
									<legend style="display: none;">???????????????</legend>
									<table class="table table-bordered t_table">
										<tr>
											<td>????????????</td>
											<td><input type="text" maxlength="10"  name="studentNumber" id="studentNumber" value="" placeholder="????????????"/></td>
											<td>????????????</td>
											<td><input type="text" id="enrollDate" name="enrollDate" style="width:320px;" value="" placeholder="????????????" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'leaveDate\')}',dateFmt:'yyyy-MM-dd HH:mm'});"/></td>
										</tr>
										<tr>
											<td>????????????</td>
											<td><input type="text" maxlength="3" name="number" id="number" value="" placeholder="????????????"/></td>
											<td><span class="red"></span>????????????</td>
											<td><select name="enrollType" id="enrollType"></select></td>
										</tr>
										<tr>
<!-- 											<td>????????????</td> -->
<!-- 											<td><select name="studentType" id="studentType"></select></td> -->
											<td>??????</td>
											<td><select name="gradeId" id="gradeId" style="width:332px;"></select></td>
											<td><span class="red"></span>????????????</td>
											<td><select name="attendSchoolType" id="attendSchoolType"></select></td>
										</tr>
										<tr>
											<td>??????</td>
											<td><select name="teamId" id="teamId" style="width:332px;"></select></td>
											<td>????????????</td>
											<td><select name="studentSource" id="studentSource"  ></select></td>
										</tr>
										<tr>
											<td>????????????</td>
											<td><input type="text" id="leaveDate" name="leaveDate" value="" placeholder="????????????" onclick="WdatePicker({minDate:'#F{$dp.$D(\'enrollDate\')}',dateFmt:'yyyy-MM-dd HH:mm'});"/></td>
											<td></td>
											<td></td>
										</tr>
									</table>
								</fieldset>
								<fieldset title="????????????">
									<legend style="display: none;">?????????????????????</legend>
									<table class="table table-bordered t_table">
										<tr>
											<td><span class="red"></span>?????????</td>
											<td><input type="text"  name="address" id="address" value="" placeholder="?????????"/></td>
											<td>????????????</td>
											<td><input type="text"  name="zipCode" id="zipCode" value="" placeholder="????????????"/></td>
										</tr>
										<tr>
											<td><span class="red"></span>????????????</td>
											<td><input type="text"  name="resideAddress" id="resideAddress" value="" placeholder="????????????"/></td>
											<td>????????????</td>
											<td><input type="text"  name="email" id="email" value="" placeholder="????????????"/></td>
										</tr>
										<tr>
											<td><span class="red"></span>????????????</td>
											<td><input type="text"  name="homeAddress" id="homeAddress" value="" placeholder="????????????"/></td>
											<td>????????????</td>
											<td><input type="text"  name="homepage" id="homepage" value="" placeholder="????????????"/></td>
										</tr>
										<tr>
											<td>????????????</td>
											<td><input type="text"  name="mobile" id="mobile" value="" placeholder="????????????"/></td>
											<td></td>
											<td></td>
										</tr>
									</table>
								</fieldset>
								<fieldset title="????????????">
									<legend style="display: none;">??????????????????</legend>
									<table class="table table-bordered t_table">
										<tr>
											<td><span class="red"></span>??????????????????</td>
											<td>
												<select name="isOnlyChild" id="isOnlyChild">
													<option value="0">???</option>
													<option value="1">???</option>
												</select>
											</td>
											<td>????????????</td>
											<td><select name="followStudy" id="followStudy"></select></td>
										</tr>
										<tr>
											<td><span class="red"></span>????????????????????????</td>
											<td>
												<select name="isPreeducated" id="isPreeducated">
													<option value="0">???</option>
													<option value="1">???</option>
												</select>
											</td>
											<td>???????????????</td>
											<td><select name="disabilityType" id="disabilityType"></select></td>
										</tr>
										<tr>
											<td><span class="red"></span>??????????????????</td>
											<td>
												<select name="isUnattendedChild" id="isUnattendedChild">
													<option value="0">???????????????</option>
													<option value="1">??????????????????</option>
													<option value="2">??????????????????</option>
												</select>
											</td>
											<td>???????????????????????????</td>
											<td>
												<select name="isBuyedDegree" id="isBuyedDegree">
													<option value="0">???</option>
													<option value="1">???</option>
												</select>
											</td>
										</tr>
										<tr>
											<td><span class="red"></span>????????????????????????????????????</td>
											<td>
												<select name="isCityLabourChild" id="isCityLabourChild">
													<option value="0">???</option>
													<option value="1">???</option>
												</select>
											</td>
											<td><span class="red"></span>????????????????????????</td>
											<td>
												<select name="isSponsored" id="isSponsored">
													<option value="0">???</option>
													<option value="1">???</option>
												</select>
											</td>
										</tr>
										<tr>
											<td><span class="red"></span>????????????</td>
											<td>
												<select name="isOrphan" id="isOrphan">
													<option value="0">???</option>
													<option value="1">???</option>
												</select>
											</td>
											<td><span class="red"></span>??????????????????</td>
											<td>
												<select name="isFirstRecommended" id="isFirstRecommended">
													<option value="0">???</option>
													<option value="1">???</option>
												</select>
											</td>
										</tr>
										<tr>
											<td><span class="red"></span>???????????????????????????</td>
											<td>
												<select name="isMartyrChild" id="isMartyrChild">
													<option value="0">???</option>
													<option value="1">???</option>
												</select>
											</td>
											<td></td>
											<td></td>
<!-- 											<td>?????????????????????????????????????????????</td> -->
<!-- 											<td> -->
<!-- 												<select name="needSpecialCare" id="needSpecialCare"> -->
<!-- 													<option value="0">???</option> -->
<!-- 													<option value="1">???</option> -->
<!-- 												</select> -->
<!-- 											</td> -->
										</tr>
<!-- 										<tr> -->
<!-- 											<td>??????????????????</td> -->
<!-- 											<td><select name="houseAuthority" id="houseAuthority"></select></td> -->
<!-- 										</tr> -->
									</table>
								</fieldset>
								<fieldset title="????????????">
									<legend style="display: none;">???????????????</legend>
									<table class="table table-bordered t_table">
										<tr>
											<td>???????????????</td>
											<td><select name="schoolDistance" id="schoolDistance"></select></td>
											<td>????????????????????????</td>
											<td>
												<select name="bySchoolBus" id="bySchoolBus">
													<option value="0">???</option>
													<option value="1">???</option>
												</select>
											</td>
										</tr>
										<tr>
											<td>?????????????????????</td>
											<td><select name="trafficType" id="trafficType"></select></td>
										</tr>
									</table>
								</fieldset>
								
								<fieldset title="????????????">
									<legend style="display: none;">?????????????????????</legend>
									<div class="cy_select">
									<ul>
										<li><a href="javascript:void(0)">???????????????</a></li>
										<li><a href="javascript:void(0)">???????????????</a></li>
									</ul>
									<div class="clear"></div>
									</div>
								<div class="family_table">
									<table class="table table-bordered t_table">
										<tr>
											<td><span class="red"></span>??????????????????????????????</td>
											<td><input type="text" class="{maxlength : 10,required: true}"  id="pName_0" value="???????????????"/></td>
											<td><span class="red"></span>??????</td>
											<td><select class="parentRelation {required: true}"  id="parentRelation_0"></select></td>
										</tr>
										
										<tr>
											<td><span class="red"></span>????????????</td>
											<td><input type="text" class="{mobile:true,required:true}" id="mobile_0" placeholder="????????????"/></td>
											<td><span class="red"></span>???????????????</td>
											<td><input type="text" class="{maxlength:18,chrnum : true,required:true}"  id="pIdCardNumber_0"  placeholder="????????????"/></td>
										</tr>
										<tr>
											<td>????????????</td>
											<td><input type="text" class="{maxlength : 20}"  id="prealtionRemarks_0" placeholder="????????????"/></td>
											<td><span class="red"></span>???????????????</td>
											<td><select class="rank" class="{required: true}" id="rank_0" ></select></td>
										</tr>
										<tr>
											<td>??????</td>
											<td><select class="pRace" id="pRace_0"></select></td>
											<td><span class="red"></span>??????????????????</td>
											<td><select class="pIdCardType" class="{required: true}" id="pIdCardType_0"></select></td>
										</tr>
										<tr>
											<td>????????????</td>
											<td><input type="text" class="{maxlength:20}" id="workingPlace_0"  placeholder="????????????"/></td>
											<td>??????</td>
											<td><input type="text" id="position_0"  placeholder="??????"/></td>
										</tr>
										<tr>
											<td><span class="red"></span>?????????</td>
											<td colspan="3"><input type="text" class="{required: true}" id="pAddress_0" placeholder="?????????"/></td>
										</tr>
										<tr>
											<td><span class="red"></span>???????????????</td>
											<td colspan="3">
												<select id="sjp_0" class="{required: true}" style="width:32%;float:left;margin-right:5px"></select>
												<select id="shijp_0" style="width:33%;float:left;margin-right:5px"></select>
												<select id="qxjp_0"  style="width:33%;"></select>
											</td>
										</tr>
									</table>
									<table class="table table-bordered t_table" style="display:none">
										<tr>
											<td><span class="red"></span>??????????????????????????????</td>
											<td><input type="text" class="{maxlength : 10,required: true}"  id="pName_1" value="???????????????"/></td>
											<td><span class="red"></span>??????</td>
											<td><select class="parentRelation {required: true}"  id="parentRelation_1"></select></td>
										</tr>
										
										<tr>
											<td><span class="red"></span>????????????</td>
											<td><input type="text" class="{mobile:true,required:true}" id="mobile_1" placeholder="????????????"/></td>
											<td><span class="red"></span>???????????????</td>
											<td><input type="text" class="{maxlength:18,chrnum : true,required:true}"  id="pIdCardNumber_1"  placeholder="????????????"/></td>
										</tr>
										<tr>
											<td>????????????</td>
											<td><input type="text" class="{maxlength : 20}"  id="prealtionRemarks_1" placeholder="????????????"/></td>
											<td><span class="red"></span>???????????????</td>
											<td><select class="rank" class="{required: true}" id="rank_1" ></select></td>
										</tr>
										<tr>
											<td>??????</td>
											<td><select class="pRace" id="pRace_1"></select></td>
											<td><span class="red"></span>??????????????????</td>
											<td><select class="pIdCardType" class="{required: true}" id="pIdCardType_1"></select></td>
										</tr>
										<tr>
											<td>????????????</td>
											<td><input type="text" class="{maxlength:20}" id="workingPlace_1"  placeholder="????????????"/></td>
											<td>??????</td>
											<td><input type="text" id="position_1"  placeholder="??????"/></td>
										</tr>
										<tr>
											<td><span class="red"></span>?????????</td>
											<td colspan="3"><input type="text" class="{required: true}" id="pAddress_1" placeholder="?????????"/></td>
										</tr>
										<tr>
											<td><span class="red"></span>???????????????</td>
											<td colspan="3">
												<select id="sjp_1" class="{required: true}" style="width:32%;float:left;margin-right:5px"></select>
												<select id="shijp_1" style="width:33%;float:left;margin-right:5px"></select>
												<select id="qxjp_1"  style="width:33%;"></select>
											</td>
										</tr>
									</table>
									</div>
								</fieldset>
								
								<fieldset title="??????">
									<legend style="display: none;">????????????</legend>
									<div class="widget-container">
										<div class="control-group" style="width:100%;">
											<div class="controls">
												<textarea id="resume" name="resume" rows="8" cols="120" style="width: 80%" class="tinymce-simple"></textarea>
											</div>
										</div>
									</div>
									<div class="clear"></div>
								</fieldset>
								
								<fieldset title="??????">
									<legend style="display: none;">????????????</legend>
									<div class="widget-container">
										<div class="control-group" style="width:100%;">
											<div class="controls">
												<textarea id="remark" name="remark" rows="8" cols="120" style="width: 80%" class="tinymce-simple"></textarea>
											</div>
										</div>
									</div>
									<div class="clear"></div>
								</fieldset>
								<button href="javascript:void(0)" onclick="saveUserInfo();" class="btn btn-warning finish" style="display:none">??????</button>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="form-actions tan_bottom" >
		<button class="btn" type="button" onclick="cancel();" style="position:fixed;left:482px;">??????</button>
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
			"gradeSelectId" : "gradeId", //??????select??????ID ?????????nj
		   	"teamSelectId" : "teamId",
		   	"selectOne" : "true", 
		   	"isEchoSelected" : "false"
		});
 
//     	??????????????????
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
            backLabel: '?????????',
            nextLabel: '?????????',
            block: true,
            description: true,
            legend: false,
            titleClick: true,
            titleTarget: '#stepy_tabby1'
            });
		//==============================================
		//??????
		$.jcGcSelector("#sex", {tc : "GB-XB"}, "", function() {
			
		});
		//??????
		$.jcGcSelector("#race", {tc : "GB-MZ"}, "01", function() {
			
		});
		//??????
		$.jcGcSelector("#nationality", {tc : "GB-GJ"}, "156", function() {
			
		});
		//??????????????????
		$.jcGcSelector("#idCardType", {tc : "JY-SFZJLX"}, "1", function() {
			
		});	
		//??????????????????
		$.jcGcSelector("#abroadCode", {tc : "JY-GATQW"}, "00", function() {
			
		});	
		//????????????
		$.jcGcSelector("#politicalStatus", {tc : "GB-ZZMM"}, "13", function() {
			
		});
		//????????????
		$.jcGcSelector("#healthStatus", {tc : "GB-JKZK"}, "1", function() {
			
		});
		//??????
		$.jcGcSelector("#bloodType", {tc : "JY-XX"}, "", function() {
			
		});
		//????????????
		$.jcGcSelector("#residenceType", {tc : "GB-HKLB"}, "3", function() {
			
		});
		//????????????
		$.jcGcSelector("#studentType", {tc : "JY-XSLB","level":"1"}, "", function() {
			
		});
		//????????????
		$.jcGcSelector("#enrollType", {tc : "JY-RXFS"}, "01", function() {
			
		});
		//????????????
		$.jcGcSelector("#attendSchoolType", {tc : "JY-JDFS"}, "1", function() {
			
		});	
		//????????????
		$.jcGcSelector("#studentSource", {tc : "JY-ZXXXSLY"}, "1", function() {
			
		});	
		//??????????????????
		$.jcGcSelector("#followStudy", {tc : "XY-JY-SBJD"}, "", function() {
			
		});	
		//????????????
		$.jcGcSelector("#disabilityType", {tc : "JY-CJLX"}, "", function() {
			
		});	
		//??????????????????
		$.jcGcSelector("#houseAuthority", {tc : "XY-JY-FWQS"}, "", function() {
			
		});	
		//???????????????????????????
		$.jcGcSelector("#schoolDistance", {tc : "XY-JY-SXXJL"}, "", function() {
			
		});	
		//?????????????????????
		$.jcGcSelector("#trafficType", {tc : "XY-JY-JTFS"}, "", function() {
			
		});	
		
		// ????????????????????????
		//??????
		$.jcGcSelector("#parentRelation_0", {tc : "XY-JY-XSJTGX"}, "", function() {	});	
		$.jcGcSelector("#parentRelation_1", {tc : "XY-JY-XSJTGX"}, "", function() {	});	
		//???????????????
		$.jcGcSelector("#rank_0", {tc : "XY-JY-JZLB"}, "1", function() { });	
		$.jcGcSelector("#rank_1", {tc : "XY-JY-JZLB"}, "0", function() { });	
		//????????????
		$.jcGcSelector("#pRace_0", {tc : "GB-MZ"}, "01", function() { });	
		$.jcGcSelector("#pRace_1", {tc : "GB-MZ"}, "01", function() { });	
		//????????????????????????
		$.jcGcSelector("#pIdCardType_0", {tc : "JY-SFZJLX"}, "1", function() { });	
		$.jcGcSelector("#pIdCardType_1", {tc : "JY-SFZJLX"}, "1", function() { });	
		//???????????????	
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
	
		//???????????? JSON ??????  1????????? 2???????????? 4????????????????????????
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
    		$("#stepy1-step-3").children("table").children("tbody").append("<tr><td>" +i+ "</td><td><input type='text' /></td><td><input type='text' /></td><td><input type='text' /></td><td><input type='text' /></td><td><button type='button' class='btn btn-blue'>??????</button><button type='button' class='btn btn-warning'>??????</button></td></tr>")
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
            fileTypeDesc: "????????????",
            fileTypeExts: "*.jpg",
            method: 'post',
            multi: false, // ???????????????????????????
            auto: true, //Set to false if you do not want the files to automatically upload when they are added to the queue.
            removeTimeout: 1,
            queueSizeLimit: 1,
            fileSizeLimit: 4 * 1024,
            buttonText: "????????????",
            requeueErrors: false, //If set to true, files that return errors during an upload are requeued and the upload is repeatedly tried.
            height: 20,
            width: 66,
            onUploadSuccess: function(file, data, response) {
           	 var $jsonObj = eval("(" + data + ")");
           	 $("#photoUuid").val($jsonObj.uuid);
           	 
           	 $("#imgId").attr('src',$jsonObj.url); 
           	
            },
            onUploadStart: function(file) { //???????????????????????????????????????????????????
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
    	//2016-8-15???????????????????????????????????????   ?????????????????????????????????????????????
    	if($("#teamId").val()==""){
    		if($("#number").val() != ""){
    			$.alert("??????????????????????????????????????????????????????????????????????????????");
    		}
    	}
    	
    	//2016-3-17 ????????????????????????
//    	if($("#photoUuid").val()==""){
//    		$.alert("??????????????????");
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
        				$.success("????????????");
        				if(parent.core_iframe != null) {
        						parent.core_iframe.window.location.reload();
        					} else {
        						parent.window.location.reload();
        					}
        				$.closeWindow();
        			}else if("studentNumber2IsExit" === data.info){
        				$.error("??????????????????");
        			}else if("teamNumberIsExit" === data.info){
        				$.error("?????????????????????");
        			}else {
        				$.error(data.info);
        			}
        		}else{
        			$.error("???????????????");
        		}
        	});
    	}else{
    		$.alert("????????????????????????????????????");
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
						required:"????????????",
						maxlength:"???????????????10?????????",
						chinese : "???????????????"
					},
					"sex":{
						required:"????????????"
					},
// 					"studentNum":{
// 						required:"???????????????",
// 						checkStudentNum:"?????????????????????,????????????G+???????????????",
// 						remote:"??????????????????"
// 					},
					"role":{
						required:"????????????"
					},
					"englishName":{
						isEnglish:"???????????????"
					}
				}
			});
    	}
    
    //???????????????
    function isCardNo() {
    	var card = "w411381198512113914";
    	var card_ = card.substr(0, 1);
    	var cardTemp = card.substr(1,card.length);
        if(card_ == "G"){
        	// ??????????????????15?????????18??????15?????????????????????18??????17??????????????????????????????????????????????????????????????????X  
            var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;  
            if(reg.test(cardTemp) === false){
            	$.alert("????????????????????????");  
                return  false;
            }else{
            	$.alert("??????");  
         	   return true;
            }
        }else{
        	$.alert("???????????????G?????????");
        	return false;
        }
       
    } 
  </script>
</body>
</html>
