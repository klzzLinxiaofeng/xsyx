<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/css/extra/add.css" rel="stylesheet">
<style>
.form-horizontal .control-label{width:60px}
.form-horizontal .controls{margin-left:80px;}
</style>
</head>
<body style="background-color:cdd4d7 !important">
	<div class="row-fluid" >
		<div class="span12">
			<div class="content-widgets" style="margin-bottom:0">
				<div class="widget-container" style="padding:10px 0 0;">
					<form class="form-horizontal tan_form" id="addClassRoomTeacherForm" action="javascript:void(0);">
						<input type="hidden" value="${gradeId}" name="gradeId" id="gradeId"/>
               	   		<input type="hidden" value="${teamId}" name="teamId" id="teamId"/>
               	   		<input type="hidden" value="${subjectName}" name="subjectName" id="subjectName"/> 
 	               	   	<input type="hidden" value="${urlType}" name="type" id="type"/>
 	               	   	<input type="hidden" value="${subjectCode}" name="subjectCode" id="subjectCode"/>
	               	   	<input type="hidden" id="teacherId" name="teacherId" value=""/> 
						<div class="control-group" style="border-bottom:1px solid #999;padding-bottom:10px;margin-bottom:10px;">
							<label class="control-label">姓名</label>
							<div class="controls">
								<input type="text" id="name" name="name" class="span3" placeholder="请输入姓名关键字" value="" >
								<button class="btn btn-warning" onclick="search();">搜索</button>
<!-- 								<span class="red">请输入姓名关键字</span> -->
							</div>
						</div>
						<div class="select_view" id="teacher_list">
							
						</div>
						
						<div class="form-actions tan_bottom" >
<!-- 							<button class="btn btn-warning" type="button" onclick="batchAdd();">批量添加成员</button> -->
							<button class="btn btn-warning" type="button" onclick="$.closeWindow();">取消</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
		$(function() {
			$(".select_view").on("click", ".add-btn", function() {
				var $this = $(this);
				var id = $this.attr("data-id");
				var name = $this.attr("data-name");
				$("#teacherId").val(id);
				saveOrUpdate();
			});
			
		});
		
		function saveOrUpdate() {
			var loader = new loadLayer();
			var $requestData = formData2JSONObj("#addClassRoomTeacherForm");
			var url = "${pageContext.request.contextPath}/teach/classRoomTeacher/addClassRoomTeacher";
			loader.show();
			$.post(url, $requestData, function(data, status) {
				if("success" === status) {
					//data = eval("(" + data + ")");
					if("success" === data) {
						$.success("保存成功");
						if(parent.core_iframe != null) {
								parent.core_iframe.window.location.reload();
							} else {
								parent.window.location.reload();
							}
						$.closeWindow();
					} else {
						$.error("保存失败");
					}
				}else{
					$.error("服务器异常");
				}
				loader.close();
			});
		}
		
		function search() {
			var name = $("#name").val();
			$("#teacher_list").load("${ctp}/teach/teacher/list", {"name" : name, "type" : "0"}, function() {
						
			});
		}

		
</script>
</html>