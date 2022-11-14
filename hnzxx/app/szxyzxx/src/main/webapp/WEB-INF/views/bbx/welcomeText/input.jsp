<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<title></title>
<%@ taglib prefix="entity" uri="http://www.jiaoxueyun.com/entity"%>
<%@ include file="/views/embedded/common.jsp"%>
<%@ include file="/views/embedded/plugin/uploadify.jsp"%>
<link href="${ctp}/res/css/bbx/bbx.css" rel="stylesheet">
<link href="${ctp}/res/css/extra/add.css" rel="stylesheet">
<style>
.form-horizontal .controls #zp .img_1 {
	float: left;
	margin: 10px;
	position: relative;
	top: 0;
	width: 233px;
	height: 130px;
}

.form-horizontal .controls #zp .img_1 img {
	width: 233px;
	height: 130px;
}

.form-horizontal .controls #zp .img_1 a {
	position: absolute;
	font-size: 16px;
	/* font-weight: bold; */
	color: #888;
	right: 0px;
	top: 0px;
	display: block;
	width: 14px;
	height: 14px;
	line-height: 14px;
	text-align: center;
	cursor: pointer;
	background-color: #eee;
	border: 1px solid #aaa;
}

.form-horizontal .controls {
	margin-left: 100px;
}

.myerror {
	color: red !important;
	width: 22%;
	display: inline-block;
	padding-left: 10px;
}
.date .myerror{
	display:block;
}
.chzn-container{vertical-align:middle;margin-right:15px;}
input[type="radio"], input[type="checkbox"]{ margin:0 4px;margin-left:6px;}
</style>
</head>
<body style="background-color: #F3F3F3 !important;">
	<div class="row-fluid">
		<div class="span12">
			<div class="content-widgets" style="margin-bottom: 0">
				<div class="widget-container" style="padding: 20px 0 0;">
					<form class="form-horizontal padding15" id="welcomeText_form">					
						<div class="control-group" id="classMasterSearch">
							<select  id="bj" name="teamId1" class="span4 chzn-select" style="width: 120px;"></select>
						</div>	
						<div class="control-group" id="schoolManagerSearch">
							<div hidden><select id="xn"></select></div>
							<span style="line-height:;margin-right:10px;" >年级</span><select id="nj" name="gradeId" style="width:160px;"></select>
							<span style="margin-right:10px;">班级</span><select id="bj" name="teamId" style="width:160px; margin-left:15px;vertical-align:bottom;"></select>
						</div>				
						<div class="control-group">
							<textarea type="text" id="welcomeText" name="welcomeText" class="span12" placeholder="请输入欢迎词"
							 style="resize: none;width: 650px; height: 100px;max-width: 650px;max-height: 100px;"></textarea>
						</div>											
						<div class="control-group">
							<input type="radio" id="check" name="check" onclick="clickRadio(1)" value="1">立即发布
							<input type="radio" id="check" name="check" onclick="clickRadio(2)" value="2">
							<input style="height: 34px; line-height: 34px; font-size: 12px;width:200px;"
									class="sj_time" id="welcomeTextTime" name="welcomeTextTime"
									onclick="WdatePicker({minDate:'%y-%M-%d %H:%m:%s', dateFmt:'yyyy-MM-dd HH:mm:ss'});"
									placeholder="触发时间" value="" type="text" disabled="disabled">
							<input style="height: 34px; line-height: 34px; font-size: 12px;width:200px;"
									class="sj_time" id="welcomeTextEndTime" name="welcomeTextEndTime"
									onclick="WdatePicker({minDate:'#F{$dp.$D(\'welcomeTextTime\',{m:+5})}', dateFmt:'yyyy-MM-dd HH:mm:ss'});"
									placeholder="结束时间" value="" type="text">
						</div>		
					</form>
					<div class="form-actions tan_bottom_1">
						<input type="hidden" id="id" name="id" value="" />
						<a href="javascript:void(0)" class="yellow" onclick="saveOrUpdate();">发布</a>
						<a href="javascript:void(0)" onclick="closeWin();">取消</a>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
<script type="text/javascript">
	var checker;
	var currentRoleCode = '${sessionScope[sca:currentUserKey()].currentRoleCode}';
	
	$(function() {
			
		if(currentRoleCode == "SCHOOL_LEADER"){
			$("#classMasterSearch").html("");
			$("#classMasterSearch").hide();		
			$.initCascadeSelector({
				"type" : "team",
				"gradeFirstOptTitle" : "全部",
				"teamFirstOptTitle" : "全部",
				"teamCallback" : function($this) {
				}
			});			
		}else{
			$("#schoolManagerSearch").html("");		
			$("#schoolManagerSearch").hide();	
			
			var $requestData = {"roleType" : "${sessionScope[sca:currentUserKey()].currentRoleCode}"};
			$.BbxRoleTeamAccountSelector({
				   "selector" : "#bj",
				   "condition" : $requestData,
				   "selectedVal" : "",			
				   "afterHandler" : function() {
					}	
			   });
		}	
		
		checker = initValidator();	
	});

	function clickRadio(type){
		if(type == 1){
			$("#welcomeTextTime").val("");
			$("#welcomeTextTime").attr("disabled", true); 
		}else if(type == 2){
			$("#welcomeTextTime").removeAttr("disabled"); 
		}
	}
	
	function initValidator() {
		return $("#welcomeText_form").validate({
			errorClass : "myerror",
			rules : {
				"teamId1" : {
					required : true
				},	
				"welcomeText" : {
					required : true,
					minlength : 1,
					maxlength : 200
				},	
				"check" : {
					required : true,
					check1 : true
				},
				"welcomeTextEndTime" : {
					required : true
				}
			},
			messages : {
				
			}
		});
	}
		
	$.validator.addMethod("check1", function(value, element, param) {
		var result = true;
		var $this = $(element);
		var welcomeTextTime = $("#welcomeTextTime").val();
		var radioValue = $("#check:checked").val();
		if(radioValue == "2" && welcomeTextTime == ""){
			result = false;
			
		}
	   	return this.optional(element) || result;
	}, "触发时间不能为空");

		
	//保存或更新修改
	function saveOrUpdate() {
		if (checker.form()) {
			var loader = new loadLayer();
			var url = "${pageContext.request.contextPath}/bbx/welcomeText/creator";
			//var $requestData = formData2JSONObj("#welcomeText_form");			
			var $requestData = {};
			$requestData.teamId = $("#bj").val();
			$requestData.gradeId = $("#nj").val();
			$requestData.welcomeText = $("#welcomeText").val();
			$requestData.welcomeTextTime = $("#welcomeTextTime").val();	
			if($requestData.welcomeTextTime == ""){
				$requestData.welcomeTextTime = (new Date()).format("yyyy-MM-dd hh:mm:ss");
			}
			$requestData.welcomeTextEndTime = $("#welcomeTextEndTime").val();	
			loader.show();
			$.post(url, $requestData, function(data, status) {				
				if("success" === status) {
					$.success('操作成功');
					data = eval("(" + data + ")");
					if("success" === data.info) {
						parent.core_iframe.search();
						$.closeWindow();
					} else {
						$.error("操作失败");
					}
				}else{
					$.error("操作失败");
				}
				loader.close();
			});
		}
	}
	
	Date.prototype.format = function (fmt) { //author: meizz 
	    var o = {
	        "M+": this.getMonth() + 1, //月份 
	        "d+": this.getDate(), //日 
	        "h+": this.getHours(), //小时 
	        "m+": this.getMinutes(), //分 
	        "s+": this.getSeconds(), //秒 
	        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
	        "S": this.getMilliseconds() //毫秒 
	    };
	    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	    for (var k in o)
	    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
	    return fmt;
	}

	function closeWin(){
		$.confirm("确定离开此页面？", function() {
			$.closeWindow();
		});
	}
	
	function teamChange(obj){
		var teamId = $(obj).val();
		$("#teamId").val(teamId);
	}
	
</script>
</html>