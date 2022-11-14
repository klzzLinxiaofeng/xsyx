<%@ page language="java"
	import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>菜单同步</title>
<%@ include file="/views/embedded/common.jsp"%>
<link href="${ctp}/res/plugin/falgun/css/add.css" rel="stylesheet">
<%@ include file="/views/embedded/plugin/zTree.jsp"%>
<style>
.cdlj {
	text-overflow: ellipsis;
	white-space: nowrap;
	overflow: hidden;
	word-wrap: normal;
	max-width: 520px;
	display: block;
}
.chzn-container{
	position:relative;
	top:7px;
}
#checkDiv label{
	display: block;
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
    padding:0 0 10px 15px;
}
#checkDiv label input{
	margin: 0 5px 0 0;
}
#checkDiv{
	width: 309px;
    float: left;
    height: 555px;
    overflow-x: hidden;
    margin-bottom: 10px;
 	background-color: #fff;
 	padding-top:15px;
 	margin-top:10px;
}
#initTree{
	   
    width: 250px;
    background-color: #fff;
    overflow-x: hidden;
    height: 550px;
    float: left;
    margin-left: 25px;
}
.yhz{
	height: 34px;
	line-height: 34px;
	padding: 4px;
	background-color: #f5f5f5;
	font-size: 16px;
    color: #828080;
    border: 1px #D4D4D4 solid;
}
.ztree{
/* 	margin:0 0 0 10px; */
}
#groupChecked label{
	margin: 15px 0 0 20px; 
}
</style>
</head>
<body>
	<!-- <div class="layout">
		<div class="main-wrapper"> -->
	<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon"/>
			<jsp:param value="菜单同步" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid ">
			<div class="span12">
				<div class="content-widgets white" style="margin:0;">
					<div class="widget-head">
						<h3 class="page-header" style="height: 34px">
							<div class="select_div">
								<!-- <span>所属组：</span>
								<select style="padding: 6px; width: 220px;" id="school" name="school" onchange="initZtree(this)">
									<option value="0">系统默认角色组</option>
								</select> -->
								<button onclick="savePage()" style="margin-top: 9px;">分配</button>
							</div>
						</h3>
					</div>
				</div>
			</div>
		</div>
<!-- 		<div style="float:left;"> -->
		<div  class="yhz">所选用户组</div>
		<div id="checkDiv" >
		<label>
				<input id="checkAll" type="checkbox">全选
		</label>
		<c:forEach items="${groups }" var="group">
			<label>
				<input type="checkbox" name="ids" value="${group.id }" onclick="checkedGroup('${group.id}',this)">${group.name }
			</label>
		</c:forEach>
			</div>
<!-- 			</div> -->
		<div class="row-fluid" style="margin-top: 10px;">
		<div id="groupChecked" style="width:300px;height:570px;float:left;background-color: #fff;margin-left: 25px;overflow-x: hidden;">
		
		</div>
			<div class="widget-container" id="initTree">
			<jsp:include page="./tree_sub.jsp"></jsp:include>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	$(function() {
		$.GroupSelector({
			"selector" : "#school"
		});
		$("#checkAll").on("click", function() {
			if(this.checked){
				$("#checkDiv input:checkbox[name='ids']").prop("checked", this.checked);
				$lab = $("#checkDiv input:checkbox[name='ids']")
				$("#groupChecked").empty();
				$lab.each(function(){
					var layer = "";
					layer = "<label data-id='"+$(this).val()+"'>";
					layer += $(this).parent().text()
					layer += "</label>";
					$(layer).appendTo($("#groupChecked"));
				});
			}else{
				$("#checkDiv input:checkbox[name='ids']").prop("checked", this.checked);
				$("#groupChecked").empty();
			}
		});	
	});

	function initZtree($this) {
		var $this = $($this);
		
		var groupId = $this.val();
		
		if(groupId != "") {
			  
		} else {
			$.alert("请选择组");
		}
	}
	
	function checkedGroup(id,obj){
		if(obj.checked){
		   var layer = "";
		   layer = "<label data-id='"+$(obj).val()+"'>";
		   layer += $(obj).parent().text()
		   layer += "</label>";
		   $(layer).appendTo($("#groupChecked"));
		}else{
			$("#groupChecked label[data-id='"+id+"']").remove();
		}
	}
</script>
</html>