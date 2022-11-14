<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<title></title>
<%@ taglib prefix="entity" uri="http://www.jiaoxueyun.com/entity"%>
<%@ include file="/views/embedded/common.jsp"%>
<%@ include file="/views/embedded/plugin/uploadify.jsp"%>
<link rel="stylesheet" href="${ctp}/res/css/extra/add.css" >
<link rel="stylesheet" href="${ctp}/res/css/bbx/bbx.css">
<style>
.myerror {
	color: red !important;
	width: 22%;
	display: inline-block;
	padding-left: 10px;
}
.remove_fj{
	color:#666;
	margin-left:15px;
	font-size:16px;
	font-family:"微软雅黑";
	width:18px;
	height:18px;
	text-align:center;
	line-height:18px;
	display:inline-block;
}
.remove{
	margin-top: -35px;
}
.uploadify{
	position:absolute;
	opacity:0;
	left:0;
	top:0;
}
.uploadify-queue{width:80px;}
.fileName{
	width: 50px;
	height: 15px;
	overflow: hidden;
	display: inline-block;
}
.edit ul{
	padding:0;
}
 #a p{
	padding:0 0 10px 0;min-width:240px;
}
#a p a{
	font-size: 16px;
font-weight: bold;
}
.edit ul li{ width:100%;height:auto;}
.widget-container{ padding:0;}
</style>
</head>
<body style="background-color: cdd4d7 !important">
<div class="row-fluid" style="height:100%;">
	<div class="span12">
 		<div class="content-widgets" style="margin-bottom:0;height:100%;">
			<div class="widget-container" style="height:100%;">
				<form class="form-horizontal tan_form" id="bwexaminfo_form" action="javascript:void(0);">
					<div class="trend">
    					<div class="edit"  style="height:90%;">
    						<div class="control-group" style="padding:20px 0 0 20px; display: none;">
    							<select  id="teamId" class="span4 chzn-select" style="width: 200px;"></select>			   
							</div>	
							<div class="control-group" style="padding:20px 0 0 20px;">
								<!-- <span>考试科目：</span> --><select  id="subjectCode" name="subjectCode" class="span13" style="width: 200px;"></select>
							</div>
							<div class="control-group" style="padding:0 20px;">
								<input type="text" id="startTime" name="startTime" placeholder="开始时间" value="<fmt:formatDate value="${bwExamInfo.startTime }" pattern="yyyy-MM-dd HH:mm"/>"
								class="Wdate" onFocus="WdatePicker({lang:'zh-cn',minDate:'%y-%M-%d %H:%m',dateFmt:'yyyy-MM-dd HH:mm'})"
								style="height: 34px; line-height: 34px; font-size: 12px;width:200px;">
								-<input type="text" id="finishTime" name="finishTime" placeholder="结束时间" value="<fmt:formatDate value="${bwExamInfo.finishTime }" pattern="yyyy-MM-dd HH:mm"/>" 
								class="Wdate" onFocus="WdatePicker({lang:'zh-cn',minDate:'#F{$dp.$D(\'startTime\',{m:+5})}',dateFmt:'yyyy-MM-dd HH:mm',})"
								style="height: 34px; line-height: 34px; font-size: 12px;width:200px; margin-left: 5px">
							</div>
							<div class="control-group" style="padding:0 20px;">
								<span>考试场地：</span><input type="text" id="examRoomName" name="examRoomName" class="span13" style="width: 350px;"
									 placeholder="输入考试场地" value="${bwExamInfo.examRoomName}">
							</div>		
							
							<div class="control-group" style="padding:0 20px;">
								<span>监考老师：</span><input type="text" id="teacherName" name="teacherName" class="span13" style="width: 350px;" 
									placeholder="输入监考老师" value="${bwExamInfo.teacherName}">
							</div>
							<div class="control-group" style="padding:0 20px;">
								<span>注意事项：</span><input type="text" id="content" name="content" class="span13"   style="width: 350px;"
									placeholder="输入考试注意事项" value="${bwExamInfo.content}">
							</div>
						</div>
    					<div class="clear"></div>
    					<div class="form-actions tan_bottom_1">
							<input type="hidden" id="id" name="id" value="${bwExamInfo.id}" />
							<a href="javascript:void(0)" class="yellow" onclick="saveOrUpdate();">发布</a>
							<a href="javascript:void(0)" onclick="closeWin();">取消</a>
               		 	</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
</body>
<script type="text/javascript">
$(function() {
	checker = initValidator();		
	var teamId = "${teamId}";
	if ( teamId == null || '' == teamId ) {
		var $requestData = {"roleType" : "${sessionScope[sca:currentUserKey()].currentRoleCode}"};
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
	$.PjSubjectSelector({
		"selector" : "#subjectCode",
		"selectedVal" : "${bwExamInfo.subjectCode}"
	});
	
});
var checker;
$(function() {
	checker = initValidator();
});


function initValidator() {
	return $("#bwexaminfo_form").validate({
		errorClass : "myerror",
		rules : {
			"startTime" : {
				required : true
			},
			"finishTime" : {
				required : true,
			},
			"subjectCode" : {
				required : true,
			},
			"examRoomName" : {
				maxlength: 20
			},
			"teacherName" : {
				maxlength: 10
			},
			"content" : {
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
		var teamId = $("#teamId").val();
		var $requestData = formData2JSONObj("#bwexaminfo_form");
		$requestData.teamId = teamId;
		if($requestData.subjectCode===""){
			$.error("请选择科目");
			return false;
		}
		var url = "${ctp}/bbx/bpBwExamInfo/creator";
		if ("" != $id) {
			$requestData._method = "put";
			url = "${ctp}/bbx/bpBwExamInfo/" + $id;
		}
		loader.show();
		$.post(url, $requestData, function(data, status) {
			if("success" === status) {
				$.success('发布成功');
				data = eval("(" + data + ")");
				if("success" === data.info) {
					if(parent.core_iframe != null) {
// 							parent.core_iframe.window.location.search();
							parent.core_iframe.search();
						} else {
// 							parent.window.location.search();
							parent.core_iframe.search();
						}
					$.closeWindow();
				} else {
					$.error("发布失败");
				}
			}else{
				$.error("发布失败");
			}
			loader.close();
		});
	}
}
</script>
</html>