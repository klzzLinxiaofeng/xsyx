<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<title></title>
<%@ include file="/views/embedded/common.jsp"%>
<link href="${ctp}/res/css/bbx/bbx.css" rel="stylesheet">
<link href="${ctp}/res/css/extra/add.css" rel="stylesheet">
<style>
.row-fluid .span13 {
	width: 75%;
}

.row-fluid .span4 {
	width: 75%;
}

.myerror {
	color: red !important;
	width: 22%;
	display: inline-block;
	padding-left: 10px;
}

.chzn-container{vertical-align:middle;margin-right:15px;}
</style>
</head>
<body style="background-color: #F3F3F3 !important;">
	<div class="row-fluid">
		<div class="span12">
			<div class="content-widgets" style="margin-bottom: 0">
				<div class="widget-container" style="padding: 20px 0 0;">
					<form class="form-horizontal padding15" id="bwinfo_form">					
						<!-- <div style="display: none;" class="control-group" id="classMasterSearch">
							<select  id="teamId" class="span4 chzn-select" style="width: 120px;"></select>
						</div> -->	
						
						<div class="control-group" id="classMasterSearch">
							<select  id="bj" name="teamId1" class="span4 chzn-select" style="width: 120px;"></select>
						</div>	
						<div class="control-group" id="schoolManagerSearch">
							<div hidden><select id="xn"></select></div>
							<span style="margin-right:2px;">年级</span>
							<select id="nj" name="gradeId" style="width:160px;"></select>
							<span style="margin-right:2px;">班级</span>
							<select id="bj" name="teamId" style="width:160px; margin-left:15px;vertical-align:bottom;"></select>
						</div>
						
						<div class="control-group">
							<textarea type="text" id="content" name="content" class="span12" placeholder="请输入寻物启事"
							 style="resize: none;width: 650px; height: 100px;max-width: 650px;max-height: 100px;"></textarea>
						</div>											
					</form>
					<div class="form-actions tan_bottom_1">
							<input type="hidden" id="id" name="id" value="${bpBwInfoMore.id}" />
							<input type="hidden" id="teamId" name="teamId" value="${bpBwInfoMore.teamId}" />
							<button class="btn btn-warning" type="button" onclick="saveOrUpdate();">确定</button>
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
	var id = $("#id").val();
	if(id == ""){
		if(currentRoleCode == "SCHOOL_LEADER"){
			$("#classMasterSearch").html("");
			$("#classMasterSearch").hide();		
			$.initCascadeSelector({
				"type" : "team",
				"gradeFirstOptTitle" : "全校",
				"teamFirstOptTitle" : "全年级",
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
		
	}else{
		$("#classMasterSearch").html("");
		$("#classMasterSearch").hide();	
		$("#schoolManagerSearch").html("");		
		$("#schoolManagerSearch").hide();	
	}	
	
	$("#content").val("${bpBwInfoMore.content}");
	checker = initValidator();
});


/* $(function(){
	var $requestData = {"roleType" : "${sessionScope[sca:currentUserKey()].currentRoleCode}"};
	var teamId = "${teamId}";
	if ( teamId == null || '' == teamId ) {
		$.BbxRoleTeamAccountSelector({
			   "selector" : "#teamId",
			   "condition" : $requestData,
			   "selectedVal" : "",
			   "afterHandler" : function() {
				}	
		   });
	} else {
		$("#teamId").append("<option value='"+teamId+"'></option>");
	}
	$("#content").val("${bpBwInfoMore.content}");
	checker = initValidator();
}); */

	
	function initValidator() {
		return $("#bwinfo_form").validate({
			errorClass : "myerror",
			rules : {
				"content" : {
					required : true,
					maxlength: 200
				}
			},
			messages : {
				
			}
		});
	}
	
	//保存或更新修改
	function saveOrUpdate() {
		if (checker.form()) {
			var loader = new loadLayer();
			var $id = $("#id").val();
			//var teamId = $("#teamId").val();
			//var $requestData = formData2JSONObj("#bwinfo_form");
			//$requestData.teamId = teamId;
			var $requestData = {};
			$requestData.content = $("#content").val();
			$requestData.id = $id;
			//alert($requestData.id);
			
			var url = "${ctp}/bbx/bpBwInfoMore/notice/creator";
			if ("" != $id) {
				$requestData.teamId = $("#teamId").val();
				$requestData._method = "put";
				url = "${ctp}/bbx/bpBwInfoMore/notice/" + $id;
			}else{
				$requestData.gradeId = $("#nj").val();
				$requestData.teamId = $("#bj").val();
			}
			loader.show();
			$.post(url, $requestData, function(data, status) {
				if("success" === status) {
					$.success('操作成功');
					data = eval("(" + data + ")");
					if("success" === data.info) {
						if(parent.core_iframe != null) {
 							// parent.core_iframe.window.location.reload();
							parent.core_iframe.window.search();
 						} else {
 							parent.window.location.search();
 						}
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
	
</script>
</html>