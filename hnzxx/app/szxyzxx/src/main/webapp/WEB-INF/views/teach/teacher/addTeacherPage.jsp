<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/views/embedded/common.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/res/css/extra/add.css" rel="stylesheet">
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/common/plugin/uploadify/jquery.uploadify.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/common/plugin/uploadify/jquery.uploadify.min.js"></script>
<%@include file="/views/embedded/plugin/dept_selector_js.jsp" %>
<%@ include file="/views/embedded/plugin/zTree.jsp"%>
<%@ include file="/views/embedded/plugin/avatar_js.jsp"%>
<title>${sca:getDefaultSchoolName()}</title>
<style type="text/css">
input[type="text"],select,textarea{margin-bottom:0;width:100%;}
input[type="text"],textarea{border:0 none;width:95%;}
table{background-color:#fff;}
table td img{width:155px;height:187px;}
table td .up_img {
    background: url("${pageContext.request.contextPath}/res/css/extra/images/up_img.jpg") no-repeat;
    display: block;
    height: 30px;
    width: 98px;
    position:absolute;
    top:197px;
    left:30px;
}
.myerror {
		color: red !important;
		width: 34%;
		display: inline-block;
		padding-left: 10px;
	}
	 .tan_bottom {
    background-color: transparent;
    background-image: linear-gradient(rgba(42, 118, 88, 0.9) 0px, rgba(42, 118, 88, 0.8) 100%);
    bottom: 0;
    margin-bottom: 0;
    min-height: 34px;
    padding: 10px 0;
    position: fixed;
    text-align: center;
    width: 100%;
}
table td img{position:relative;top:-20px;left:5px;}
.jsxx{
	background:#0093a8;
	height:45px;
	line-height:45px;
	margin-bottom: 15px;
}
.jsxx a{
	padding:0 20px;
	float:left;
	text-align:center;
	color:#fff;
	font-family: ????????????;
	font-size: 18px;
	background:#0093a8;
	cursor:pointer;
}
.jsxx a.tianjia{
	color: #0093a8;
	background: #fff;
}
</style>
</head>
<body style="background-color:#F5F5F5">
	<div class="add_teacher">
		<p class="p1">????????????</p>
		<p><span class="red">*</span>????????????</p>
	</div>
	<div class="jsxx">
		<a hrft="javascript:void(0);" class="tianjia">????????????</a>
		<a hrft="javascript:void(0);">????????????</a>
	</div>
	
	<form id="teacherForm" class="form-horizontal">
	<div class="jsnr">
	
	<table class="table table-bordered">
		<tbody>
			<tr>
				<td><span class="red">*</span>??????</td>
				<td><input type="text" id="name" name="name"  value="" data-id="" placeholder="??????"/></td>
				<td>?????????</td>
				<td><input type="text" id="englishName" name="englishName" value="" data-id="" placeholder="?????????"/></td>
				<td rowspan="6" style="width:165px;position:relative">
					<img src="${pageContext.request.contextPath}/res/images/noPhoto.jpg" id="imgId">
					<a href="javascript:void(0)" class="up_img"></a>
<!-- 					<input type="file" id="uploader" name="uploader"/> -->
					<!-- <a href="javascript:void(0)" class="up_img"></a>
					<input type="hidden" name="uploadify" id="uploadify" /> 
					<div id="tp_queue"></div> -->
<!-- 					<input type="hidden" id="entityId" name="entityId" value=""/> -->
					<input type="hidden" id="photoUuid" name="photoUuid" value=""/>
				</td>
			</tr>
			<!-- <tr>
				<td><span class="red">*</span>?????????</td>
				<td><input type="text" id="userName" name="userName"  value="" data-id="" placeholder="?????????"/></td>
				<td></td>
				<td></td>
				<td rowspan="6" style="width:165px;position:relative"><a href="javascript:void(0)" class="up_img"></a></td> 
			</tr>-->
			<tr>
				<td>??????</td>
				<td>
					<select id="sex" name="sex"></select>
				</td>
				<td>????????????</td>
				<td><input type="text" id="birthDate" name="birthDate" value="" onclick="WdatePicker();" data-id="" placeholder="????????????"/></td>
			</tr>
			<tr>
				<td>??????</td>
				<td><input type="text" id="jobNumber" name="jobNumber" value="" data-id="" placeholder="??????"/></td>
				<td><!-- <span class="red">*</span> -->????????????</td>
				<td><select id="certificateType" name="certificateType">
						
				    </select>
				</td>
			</tr>
			<tr>
				<td><span class="red">*</span>????????????</td>
				<td><input type="text" id="mobile" name="mobile"  value="" data-id="" placeholder="????????????"/></td>
				<td><!-- <span class="red">*</span> -->????????????</td>
				<td><input type="text" id="certificateNum" name="certificateNum"  value="" data-id="" placeholder="????????????"/></td>
			</tr>
			<tr>
				<td>??????????????????</td>
				<td><input type="text" id="workBeginDate" name="workBeginDate" value="" onclick="WdatePicker();" data-id="" placeholder=""/></td>
				<td>??????</td>
				<td>
					<select id="nationality"  name="nationality"></select>
				</td>
			</tr>
			<tr>
				<td>????????????</td><td><input type="text" id="enrollDate" name="enrollDate" onclick="WdatePicker();" value="" data-id="" placeholder=""/></td>
				<td>??????</td>
				<td>
					<select id="nation" name="nation">
					</select>
				</td>
			</tr>
			<tr>
				<td>????????????</td>
				<td><input type="text" id="leaveDate" name="leaveDate" onclick="WdatePicker();" value="" data-id="" placeholder=""/></td>
				<td>??????</td>
				<td colspan="2"><input type="text" id="nativePlace" name="nativePlace" value="" data-id="" placeholder="??????"/></td>
			</tr>
			<tr>
				<td>??????</td>
				<td><input type="text" id="position" name="position" value="" data-id="" placeholder="??????"/></td>
				<td>?????????</td>
				<td colspan="2"><input type="text" id="birthPlace" name="birthPlace" value="" data-id="" placeholder="?????????"/></td>
			</tr>
			<tr>
				<td>????????????</td>
				<td>
					<select id="occupationCode" name="occupationCode">
					</select>
				</td>
				<td>????????????</td>
				<td colspan="2">
					<select id="marriage" name="marriage">
					</select>
				</td>
			</tr>
			<tr>
				<td>??????</td>
				<td>
					<select id="qualification" name="qualification">
					</select>
				</td>
				<td>????????????</td>
				<td colspan="2">
					<select id="political" name="political">
					</select>
				</td>
			</tr>
			<tr>
				<td>??????</td>
				<td><input type="text" id="specialty" name="specialty" value="" data-id="" placeholder="??????"/></td>
				<td>????????????</td>
				<td colspan="2">
					<select id="religiousBelief" name="religiousBelief">
					</select>
				</td>
			</tr>
			<tr>
				<td>????????????</td>
				<td><select id="postStaffing" name="postStaffing">
<!-- 						<option value="0">???</option> -->
<!-- 						<option value="1">???</option> -->
					</select>
				</td>
				<td>????????????</td>
				<td colspan="2">
					<select id="register" name="register">
					</select>
				</td>
			</tr>
			<tr>
				<td>????????????</td>
				<td><select id="jobState" name="jobState"></select>
				</td>
				<td>???????????????</td>
				<td colspan="2"><input type="text" id="registerPlace" name="registerPlace" value="" data-id="" placeholder="???????????????"/></td>
			</tr>
			<tr>
				<td>???????????????</td>
				<td><input type="text" id="telephone" name="telephone" value="" data-id="" placeholder="???????????????"/></td>
				<td>?????????</td>
				<td colspan="2"><input type="text" id="nowAddress" name="nowAddress"  value="" data-id="" placeholder="?????????"/></td>
			</tr>
			<tr>
				<td>??????</td>
				<td><input type="text" id="email" name="email"  value="" data-id="" placeholder="??????"/></td>
				<td>????????????</td>
				<td colspan="2"><input type="text" id="liveAddress" name="liveAddress" value="" data-id="" placeholder="????????????"/></td>
			</tr>
			<tr>
				<td><span class="red">*</span>??????</td>
				<td><select id="role" name="role"></select></td>
				<td>??????</td>
				<td colspan="2"><div id="dept"></div></td>
			</tr>
			<tr>
				<td>??????</td>
				<td colspan="4"><textarea id="remark" name="remark"></textarea></td>
			</tr>
		</tbody>
	  </table>
	  </div>
	 
	 <!-- 2016-12-08 ???????????????????????? -->
	 <div class="jsnr">
	 	<table class="table table-bordered">
		<tbody>
			<tr>
				<td>??????????????????</td>
				<td><input type="text" class="{maxsize:100}" id="joinReason" name="joinReason"  value="" data-id="" placeholder="???????????????????????????"/></td>
				<td>??????</td>
				<td><input type="text" class="{maxsize:10}" id="degree" name="degree" value="" data-id="" placeholder="???????????????"/></td>
			</tr>
			<tr>
				<td>??????</td>
				<td>
					<input type="text" class="{maxsize:10}" id="identity" name="identity" value="" data-id="" placeholder="???????????????"/>
				</td>
				<td>????????????</td>
				<td><input type="text" class="{maxsize:10}" id="actualPosition" name="actualPosition" value="" data-id="" placeholder="?????????????????????"/></td>
			</tr>
			<tr>
				<td>????????????</td>
				<td><input type="text" class="{maxsize:10}" id="postType" name="postType" value="" data-id="" placeholder="?????????????????????"/></td>
				<td>????????????</td>
				<td><input type="text" class="{maxlength:10}" id="wages" name="wages" value="" data-id="" placeholder="?????????????????????"/>
				</td>
			</tr>
			<tr>
				<td>????????????</td>
				<td><input type="text" class="{maxsize:10}" id="managementType" name="managementType"  value="" data-id="" placeholder="?????????????????????"/></td>
				<td>????????????????????????</td>
				<td><input onclick="WdatePicker({maxDate:'#F{$dp.$D(\'currentPositionPeriod\')}',dateFmt:'yyyy-MM'});" type="text" id="currentPositionDate" name="currentPositionDate" value="" data-id="" placeholder="?????????????????????????????????"/></td>
			</tr>
			<tr>
				<td>??????????????????</td>
				<td><input type="text" class="{maxsize:10}" id="personnelFundsType" name="personnelFundsType" value="" data-id="" placeholder="???????????????????????????"/></td>
				<td>????????????????????????</td>
				<td>
					<input onclick="WdatePicker({minDate:'#F{$dp.$D(\'currentPositionDate\',{m:+5})}',dateFmt:'yyyy-MM'});" type="text" id="currentPositionPeriod" name="currentPositionPeriod" value="" data-id="" placeholder="?????????????????????????????????"/>
				</td>
			</tr>
			<tr>
				<td>????????????</td>
				<td><input type="text" id="joinPartyDate" name="joinPartyDate" onclick="WdatePicker({dateFmt:'yyyy-MM'});" value="" data-id="" placeholder="?????????????????????"/>
				</td>
				<td>????????????</td>
				<td>
					<input type="text" class="{maxsize:10}" id="lowPost" name="lowPost" value="" data-id="" placeholder="?????????????????????"/>
				</td>
			</tr>
			<tr>
				<td>????????????</td>
				<td><input type="text" class="{maxsize:10}" id="checkInterrupt" name="checkInterrupt" value="" data-id="" placeholder="?????????????????????"/></td>
				<td>????????????????????????</td>
				<td colspan="2"><input type="text" id="lowPostDate" name="lowPostDate" onclick="WdatePicker({dateFmt:'yyyy-MM'});" value="" data-id="" placeholder="??????????????????????????????"/></td>
			</tr>
			<tr>
				<td>????????????</td>
				<td><input type="text" class="{maxlength:2,number:true}" id="interruptworkYears" name="interruptworkYears" value="" data-id="" placeholder="?????????????????????"/></td>
				<td>??????????????????</td>
				<td colspan="2"><input type="text" class="{maxsize:10}" id="cadrePosts" name="cadrePosts" value="" data-id="" placeholder="???????????????????????????"/></td>
			</tr>
			<tr>
				<td>??????</td>
				<td>
					<input type="text" class="{maxlength:2,number:true}" id="workYears" name="workYears" value="" data-id="" placeholder="???????????????"/>
				</td>
				<td>????????????</td>
				<td colspan="2">
					<input type="text" class="{maxsize:10}" id="rankType" name="rankType" value="" data-id="" placeholder="?????????????????????"/>
				</td>
			</tr>
			<tr>
				<td>????????????</td>
				<td>
					<input type="text" class="{maxlength:2,number:true}" id="accruedAge" name="accruedAge" value="" data-id="" placeholder="?????????????????????"/>
				</td>
				<td>??????????????????</td>
				<td colspan="2">
					<input type="text" id="rankChangeDate" name="rankChangeDate" onclick="WdatePicker({dateFmt:'yyyy-MM'});" value="" data-id="" placeholder="???????????????????????????"/>
				</td>
			</tr>
			<tr>
				<td>????????????</td>
				<td><input type="text" class="{maxsize:20}" id="firstDegree" name="firstDegree" value="" data-id="" placeholder="?????????????????????"/></td>
				<td>??????????????????</td>
				<td colspan="2">
					<input type="text" class="{maxsize:20}" id="technicalPosition" name="technicalPosition" value="" data-id="" placeholder="???????????????????????????"/>
				</td>
			</tr>
			<tr>
				<td>????????????????????????</td>
				<td><input type="text" class="{maxsize:50}" id="fristDegreeGraduateSchool" name="fristDegreeGraduateSchool" value="" data-id="" placeholder="?????????????????????????????????"/>
				</td>
				<td>???????????????</td>
				<td colspan="2">
					<input type="text" class="{maxsize:10}" id="nonLeadershipFlag" name="nonLeadershipFlag" value="" data-id="" placeholder="????????????????????????"/>
				</td>
			</tr>
			<tr>
				<td>????????????????????????</td>
				<td><input type="text" class="{maxsize:50}" id="firstDegreeMajor" name="firstDegreeMajor" value="" data-id="" placeholder="?????????????????????????????????"/>
				</td>
				<td>????????????</td>
				<td colspan="2"><input type="text" class="{maxlength:20}" id="post" name="post" value="" data-id="" placeholder="?????????????????????"/></td>
			</tr>
			<tr>
				<td>????????????</td>
				<td><input type="text" class="{maxsize:10}" id="highestDegree" name="highestDegree" value="" data-id="" placeholder="?????????????????????"/></td>
				<td>????????????</td>
				<td colspan="2">
					<select id="examinationResult" name="examinationResult">
						<option value="">?????????</option>
						<option value="0">??????</option>
						<option value="1" >??????</option>
						<option value="2" >????????????</option>
						<option value="3" >?????????</option>
						<option value="4" >???????????????</option>
					</select>
				</td>
			</tr>
		</tbody>
	  </table>
	 </div>
	</form>
<div class="form-actions tan_bottom">
		<button class="btn btn-warning" href="javascript:void(0)" onclick="saveTeacherInfo();">??????</button>
<!-- 		<button class="btn btn-warning">??????</button> -->
	</div>
</body>
<script type="text/javascript">
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
	$(function(){

	$(".jsnr").hide();//??????wenben
    $(".jsnr:eq(0)").show();//???????????????wenben
    $(".jsxx a").click(function(){
        $(".jsxx a").removeClass("tianjia");//????????????
        $(this).addClass("tianjia");//????????????
        var i=$(this).index();//????????????
        $(".jsnr").hide();//??????wenben
        $(".jsnr:eq("+i+")").show();//?????????i???wenben
    });
		
		$("table tr").each(function(){ 
			$(this).children("td:eq(0),td:eq(2)").css({"background-color":"#F4F4F4","text-align":"right","padding-right":"10px"});
		}); 
		
		//??????
		$.jcGcSelector("#qualification", {tc : "GB-XL"}, "", function() {
			
		});
		//????????????
		$.jcGcSelector("#jobState", {tc : "JY-JZGDQZT"}, "", function() {
			
		});
		//??????
		$.jcGcSelector("#nationality", {tc : "GB-GJ"}, "", function() {
			
		});
		//??????
		$.jcGcSelector("#nation", {tc : "GB-MZ"}, "", function() {
			
		});
		//????????????
		$.jcGcSelector("#register", {tc : "GB-HKLB"}, "", function() {
			
		});
		//????????????
		$.jcGcSelector("#political", {tc : "GB-ZZMM"}, "", function() {
			
		});
		//????????????
		$.jcGcSelector("#religiousBelief", {tc : "GB-ZJXY"}, "", function() {
			
		});
		//??????
		$.jcGcSelector("#sex", {tc : "GB-XB"}, "", function() {
			
		});
		//????????????
		$.jcGcSelector("#marriage", {tc : "GB-HYZK"}, "", function() {
			
		});
		
		//????????????
		$.jcGcSelector("#certificateType", {tc : "JY-SFZJLX"}, "", function() {
			
		});
		
		//??????
		
		//????????????
		$.jcGcSelector("#occupationCode", {tc : "JY-GWZY"}, "", function() {
			
		});
		
		//????????????
		$.jcGcSelector("#postStaffing", {tc : "JY-BZLB"}, "", function() {
			
		});
		
		//???????????? JSON ??????  1????????? 2???????????? 4????????????????????????
		$.RoleSelector({"condition" : {"userType" : "1", "property" : "default_role", "ascending" : true}, "afterHandler" : function($this) {
// 			$this.find("option[data-code='TEACHER']").attr("selected", "selected");
			$this.find("option[data-code = 'SUBJECT_TEACHER']").attr("selected", true);
		}});
		
		//???????????????
		$.createDeptSelector({
			"deptContainer" : "#dept",
			enableBatch : true,
// 			selectedItemId : "${depId}",
// 			selectedItemTitle : "${depName}"
			});
		
		uploadImageFile();
		checker =  initValidator();
		
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
	
	function saveTeacherInfo(){
		if (checker.form()) {
			var loader = new loadDialog();
			var departmentIds = $("#dept").attr("data-id");
			var $requestData = formData2JSONObj("#teacherForm");
			
			currentPositionDate = $requestData.currentPositionDate;
			currentPositionPeriod = $requestData.currentPositionPeriod;
			joinPartyDate = $requestData.joinPartyDate;
			lowPostDate = $requestData.lowPostDate;
			rankChangeDate = $requestData.rankChangeDate;
			var reg = /^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/; 
			if(currentPositionDate != "" && currentPositionDate != "undefined" && !currentPositionDate.match(reg)){
				$requestData.currentPositionDate = currentPositionDate + "-01";
			}
			if(currentPositionPeriod != "" && currentPositionPeriod != "undefined" && !currentPositionPeriod.match(reg)){
				$requestData.currentPositionPeriod = currentPositionPeriod + "-01";
			}
			if(joinPartyDate != "" && joinPartyDate != "undefined" && !joinPartyDate.match(reg)){
				$requestData.joinPartyDate = joinPartyDate + "-01";
			}
			if(lowPostDate != "" && lowPostDate != "undefined" && !lowPostDate.match(reg)){
				$requestData.lowPostDate = lowPostDate + "-01";
			}
			if(rankChangeDate != "" && rankChangeDate != "undefined" && !rankChangeDate.match(reg)){
				$requestData.rankChangeDate = rankChangeDate + "-01";
			}
			
			$requestData.departmentIds = departmentIds;
	    	var url = "${pageContext.request.contextPath}/teach/teacher/addTeacher";
	    	loader.show();
	    	$.post(url, $requestData, function(data, status) {
	    		if("success" === status) {
	    			data = eval("(" + data + ")");
	    			if("success" === data.info) {
	    				$.success('????????????');
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
	    			$.error("???????????????");
	    		}
	    		loader.close();
	    	});
		}
	}
	
	function initValidator() {
    	return $("#teacherForm").validate({
				errorClass : "myerror",
				rules : {
					"name" : {
						required : true,
						minlength : 1,
						maxlength : 20,
						chinese:true
					},
					"role":{
						required : true
					},
					"mobile":{
						required : true,
						mobile:true
					},
					"englishName":{
						required : false,
						isEnglish:true
					},
					"jobNumber" : {
						maxlength : 20
					}
// 					"certificateType":{
// 						required : true
// 					},
// 					"certificateNum":{
// 						required : true
// 					}
				},
				messages : {
					"name":{
						required:"??????????????????",
						chinese:"???????????????"
					},
					"role":{
						required:"????????????"
					},
					"englishName":{
						isEnglish:"???????????????"
					},
					"mobile":{
						mobile:"??????????????????????????????"
					},
					"jobNumber":{
						maxlength : "????????????20?????????"
					}
				}
			});
    	}
</script>
</html>
 