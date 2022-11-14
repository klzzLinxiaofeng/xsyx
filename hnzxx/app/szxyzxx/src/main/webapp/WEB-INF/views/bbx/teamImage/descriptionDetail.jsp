<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
<link href="${ctp}/res/css/extra/add.css" rel="stylesheet">
<link href="${ctp}/res/css/bbx/bbx.css" rel="stylesheet">
<%@ include file="/views/embedded/plugin/uploadify.jsp"%>
<style type="text/css">
.uploadify{position:absolute;opacity:0;left:0;top:0;height: 70px; width: 80px;}
.uploadify-queue{width:80px;}
.myerror {color: red !important;width: 22%;display: inline-block;padding-left: 10px;}
.date .myerror{display:block;}
.img_div{position:relative	}
.block{ background:rgba(165, 165, 165, 0.55); width:100%; height:100%;}
.pic_img{    position: absolute; top: 0;left: 0;z-index:99;top:10px;width: 257px;height: 184px; left: 10px;}
.pic_img p{  position: absolute; top: 0;left: 0;}
.pic_img p img{ width:50px; height:50px;}
.pic_img p.img_ok{ left:50%; top:50%; margin-top:-25px; margin-left:-25px;}
</style>

<div class="widget-container" style="padding: 20px 0 0;">
	<form class="form-horizontal padding15" id="welcomeText_form">								
		<div class="control-group">
			<textarea  id="description" name="description" class="span12" style="margin-left:10px;   resize: none;width: 1200px; 
				height: 400px;max-width: 1150px;max-height: 600px;" placeholder="请输入班级描述"></textarea>
			<input type="hidden" id="teamId" name="teamId" value="${teamId}"/>
		</div>	
		<div class="control-group" style="margin-left:5px;">
<%-- 			<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 0)}"> --%>
				<button class="btn btn-warning" type="button" id="sosuo" onclick="saveTeamImage()" >保存</button>
<%-- 			</c:if>	 --%>
		</div>												
	</form>
</div>

<script type="text/javascript">
var checker;
$(function() {	
	checker = initValidator();	
	$("#description").val("${description}");
});

function initValidator() {
	return $("#welcomeText_form").validate({
		errorClass : "myerror",
		rules : {	
			"description" : {
				maxlength : 300
			}
		},
		messages : {
			
		}
	});
}

function saveTeamImage(){
	if (checker.form()) {
		var loader = new loadLayer();
		var url = "${pageContext.request.contextPath}/bbx/teamImage/creator";		
		var $requestData = {};
		$requestData.teamId = $("#teamId").val();	
		var description=document.getElementById("description").value; 
		description=description.replace('\n',' '); 
		document.getElementById("description").value=description;
		$requestData.description = $("#description").val();
		loader.show();
		$.post(url, $requestData, function(data, status) {				
			if("success" === status) {
				$.success('操作成功');
				data = eval("(" + data + ")");
				if("success" === data.info) {
					getTeamImage();
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
