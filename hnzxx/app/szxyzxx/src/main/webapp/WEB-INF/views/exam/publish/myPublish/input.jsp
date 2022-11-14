<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<title></title>
<%@ include file="/views/embedded/common.jsp"%>
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
.qx_select{
	line-height:25px;
}
input[type="radio"]{
	position:relation;
	margin-right:10px;
	top:-1px;
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
								<input type="text" id="id" name="id" class="span13" placeholder="" value="${emexampublish.id}">
								</div>
							</div>
							<div class="control-group" style="display: none">
								<label class="control-label">
									创建时间：
								</label>
								<div class="controls">
								<input type="text" id="createDate" name="createDate" class="span13" placeholder="" value="${emexampublish.createDate}">
								</div>
							</div>
							<div class="control-group" style="display: none">
								<label class="control-label">
									做关联的uuid：
								</label>
								<div class="controls" >
								<input type="text" id="uuid" name="uuid" class="span13" placeholder="" value="${emexampublish.uuid}">
								</div>
							</div>
							<div class="control-group" >
								<label class="control-label">
									标题：
								</label>
								<div class="controls">
								<input type="text" id="title" name="title" class="span13" placeholder="" value="${emexampublish.title}">
								</div>
							</div>
							<div class="control-group" >
								<label class="control-label">
									开始时间：
								</label>
								<div class="controls">
<%-- 								<input type="text" id="startDate" name="startDate" class="span13" placeholder="" value="${emexampublish.startDate}"> --%>
								 <input id="startDate" class="span13" type="text"  onclick="WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm', maxDate: '#F{$dp.$D(\'finishedDate\')}'})" placeholder="开始日期" class="span2" value="<fmt:formatDate value="${emexampublish.startDate}" pattern="yyyy-MM-dd HH:mm" />">
								</div>
							</div>
							<div class="control-group" >
								<label class="control-label">
									结束时间：
								</label>
								<div class="controls">
<%-- 								<input type="text" id="finishedDate" name="finishedDate" class="span13" placeholder="" value="${emexampublish.finishedDate}"> --%>
								<input id="finishedDate" class="span13" type="text" onclick="WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm', minDate: '#F{$dp.$D(\'startDate\')}', maxDate: ''})" placeholder="结束日期" class="span2" value="<fmt:formatDate value="${emexampublish.finishedDate}" pattern="yyyy-MM-dd HH:mm" />" >
								</div>
							</div>
							<div class="control-group" >
								<label class="control-label">
									发布对象：
								</label>
								<div class="controls">
<%-- 								<input type="text" id="teamName" name="teamName" class="span13" placeholder="" value="${teamList}"> --%>
								<textarea rows="10" cols="25" class="span13" style="    resize: none;background-color:#fff" disabled="disabled">${teamList}</textarea>
								</div>
							</div>
							<div class="control-group" style="display: none">
								<label class="control-label">
									isCheck：
								</label>
								<div class="controls">
								<input type="text" id="isCheck" name="isCheck" class="span13" placeholder="" value="${emexampublish.isCheck}">
								</div>
							</div>
							<div class="control-group" >
								<label class="control-label">
									答案权限:
								</label>
								<div class="controls">
								<div class="qx_select"><input type="radio" name="root" value="0" <c:if test="${emexampublish.isCheck eq 0}">checked="checked"</c:if>>不允许查看答案</div>
								<div class="qx_select"><input type="radio" name="root" value="1" <c:if test="${emexampublish.isCheck eq 1}">checked="checked"</c:if>>允许随时查看答案</div>
								<div class="qx_select"><input type="radio" name="root" value="2" <c:if test="${emexampublish.isCheck eq 2}">checked="checked"</c:if>>允许提交后查看答案</div>
								<div class="qx_select"><input type="radio" name="root" value="3" <c:if test="${emexampublish.isCheck eq 3}">checked="checked"</c:if>>允许测试结束后查看答案</div>
								</div>
							</div>
						<div class="form-actions tan_bottom">
							<input type="hidden" id="id" name="id" value="${emexampublish.id}" />
								<button class="btn btn-warning" type="button"
									onclick="saveOrUpdate();">确定</button>
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
		if (checker.form()) {
			var loader = new loadLayer();
			var $id = $("#id").val();
			var url = "${ctp}/exampublish/"+$id;
			var data = {};
			data["title"]=$('#title').val();
            data["startDate"] = $("#startDate").val();
            data["finishedDate"] = $("#finishedDate").val();
            data["isCheck"]=$("input[name=root]:checked").val();
            data["_method"]="put";
            if (data["startDate"] == null || data["startDate"] == "") {
                $.alert("请输入开始日期");
                return;
            }
            if (data["finishedDate"] == null || data["finishedDate"] == "") {
                $.alert("请输入结束日期");
                return;
            }
            if (data["title"] == null || data["title"] == "") {
                $.alert("请输入标题");
                return;
            }
			loader.show();
			$.post(url, {"publishData": JSON.stringify(data)}, function(data, status) {
				if("success" === status) {
					$.success('操作成功');
					data = eval("(" + data + ")");
					if("success" === data.info) {
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
	}
	
</script>
</html>