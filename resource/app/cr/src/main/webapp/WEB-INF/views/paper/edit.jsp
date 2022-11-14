<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<title>试卷</title>
<link href="${pageContext.request.contextPath}/res/qyjx/css/button.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/qyjx/css/qyzj_dxa.css" rel="stylesheet">
<%@ include file="/views/embedded/common.jsp"%>
<style>
body{
	font-size:14px;
	color:#999999;
}
input[type="radio"]{
	margin-right: 10px;
    top: -3px;
    position: relative;
}
input[type="text"]{
	border-radius: 4px;
    background: #f8f8f9;
}
button{
		width:100px;
		height:40px;
		line-height:40px;
		text-align:center;
}
.tan_bottom{
	background: #fff;
}
.form-actions {
    border-top: #fff 1px solid;
    margin-bottom: 20px;
}
.form-horizontal {
   padding-bottom: 0px; 
}
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
.qx_select{
	line-height:25px;
	width: 181px;
    display: inline-block;
}
.form-horizontal .controls{
	margin-left: 150px;
}
.form-horizontal .control-label{
	width:139px;
}
.row-fluid [class*="span"]:first-child{
	width:364px;
}
</style>
</head>
<body style="background-color: white">
	<div class="row-fluid">
		<div class="span12">
			<div class="content-widgets" style="margin-bottom: 0">
				<div class="widget-container" style="padding: 20px 0 0;">
					<form class="form-horizontal tan_form" id="emexampublish_form" action="javascript:void(0);">
							<div class="control-group" style="display: none">
								<label class="control-label" >
									主键：
								</label>
								<div class="controls">
								<input type="text" id="id" name="id" class="span13" placeholder="" value="${task.id}">
								</div>
							</div>
							<div class="control-group" style="display: none">
								<label class="control-label">
									创建时间：
								</label>
								<div class="controls">
								<input type="text" id="createDate" name="createDate" class="span13" placeholder="" value="${task.createDate}">
								</div>
							</div>
							<div class="control-group" style="display: none">
								<label class="control-label">
									做关联的uuid：
								</label>
								<div class="controls" >
								<input type="text" id="uuid" name="uuid" class="span13" placeholder="" value="${task.uuid}">
								</div>
							</div>
							<div class="control-group" >
								<label class="control-label">
									试卷标题：
								</label>
								<div class="controls">
								<input type="text" id="title" name="title" class="span13" placeholder="" value="${task.title}">
								</div>
							</div>
							<div class="control-group" >
								<label class="control-label">
									开始时间：
								</label>
								<div class="controls">
<%-- 								<input type="text" id="startDate" name="startDate" class="span13" placeholder="" value="${task.startDate}"> --%>
								 <input id="startDate" class="span13" type="text"  onclick="WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm', maxDate: '#F{$dp.$D(\'finishedDate\')}'})" placeholder="开始日期" class="span2" value="<fmt:formatDate value="${task.startTime}" pattern="yyyy-MM-dd HH:mm" />">
								</div>
							</div>
							<div class="control-group" >
								<label class="control-label">
									结束时间：
								</label>
								<div class="controls">
<%-- 								<input type="text" id="finishedDate" name="finishedDate" class="span13" placeholder="" value="${task.finishedDate}"> --%>
								<input id="finishedDate" class="span13" type="text" onclick="WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm', minDate: '#F{$dp.$D(\'startDate\')}', maxDate: ''})" placeholder="结束日期" class="span2" value="<fmt:formatDate value="${task.finishTime}" pattern="yyyy-MM-dd HH:mm" />" >
								</div>
							</div>
							<div class="control-group" >
								<label class="control-label">
									发布对象：
								</label>
								<div class="controls">
<%-- 								<input type="text" id="teamName" name="teamName" class="span13" placeholder="" value="${teamList}"> --%>
								<textarea rows="10" cols="25" class="span13" style=" width: 364px;height: 87px;   resize: none;background-color:#fff" disabled="disabled">${name}</textarea>
								</div>
							</div>
							<div class="control-group" style="display: none">
								<label class="control-label">
									isCheck：
								</label>
								<div class="controls">
								<input type="text" id="isCheck" name="isCheck" class="span13" placeholder="" value="${task.isCheck}">
								</div>
							</div>
							<div class="control-group" >
								<label class="control-label">
									答案权限：
								</label>
								<div class="controls" style="width: 366px;">
								<div class="qx_select"><input type="radio" name="root" value="0" <c:if test="${task.isCheck eq 0}">checked="checked"</c:if>>不允许查看答案</div>
								<div class="qx_select"><input type="radio" name="root" value="1" <c:if test="${task.isCheck eq 1}">checked="checked"</c:if>>允许随时查看答案</div>
								<div class="qx_select"><input type="radio" name="root" value="2" <c:if test="${task.isCheck eq 2}">checked="checked"</c:if>>允许提交后查看答案</div>
								<div class="qx_select"><input type="radio" name="root" value="3" <c:if test="${task.isCheck eq 3}">checked="checked"</c:if>>允许测试结束后查看答案</div>
								</div>
							</div>
						<div class="form-actions tan_bottom">
							<input type="hidden" id="id" name="id" value="${task.id}" />
								<button class="btn-blue" type="button" onclick="saveOrUpdate();" style="margin-right: 23px;">确定</button>
								<button class="btn-lightGray" type="button" onclick="$.closeWindow()">取消</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	var checker;
	$(function() {
		checker = initValidator();
	});
	
	function initValidator() {
		return $("#emexampublish_form").validate({
			errorClass : "myerror",
			rules : {
				
			},
			messages : {
				
			}
		});
	}
	
	//保存或更新修改
	function saveOrUpdate() {
			var loader = new loadLayer();
			var $id = $("#id").val();
			var url = "${pageContext.request.contextPath}/paperTask/update/task";
			var data = {};
			 var task=new Object();
			 task["id"]=$("#id").val();
			 task["title"]=$('#title').val();
			 task["startTime"] =   Date.parse($("#startDate").val());
			 task["finishTime"] = Date.parse($("#finishedDate").val());
			 task["isCheck"]=$("input[name=root]:checked").val();
            if (task["startTime"] == null || task["startDate"] == "") {
                $.alert("请输入开始日期");
                return;
            }
            if (task["finishTime"] == null || task["finishedDate"] == "") {
                $.alert("请输入结束日期");
                return;
            }
            if (task["title"] == null || task["title"] == "") {
                $.alert("请输入标题");
                return;
            }
            if (task["title"].length>30) {
                $.alert("标题不能大于20个字");
                return;
            }
            data.task=task;
			data=JSON.stringify(data);
			loader.show();
			$.post(url, {"data":data}, function(data, status) {
				if("success" === status) {
					$.success('操作成功');
// 					data = eval("(" + data + ")");
					if("success" === data) {
						if(parent.core_iframe != null) {
 							parent.core_iframe.window.location.reload();
 						} else {
 							parent.window.location.reload();
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
	
</script>
</html>