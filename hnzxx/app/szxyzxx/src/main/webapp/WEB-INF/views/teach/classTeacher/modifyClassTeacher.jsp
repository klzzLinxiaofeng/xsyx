<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<title>年级列表</title>
<style>
	.form-horizontal .control-label{width:0px;}
	.form-horizontal .controls{margin-left:20px;}
	.form-horizontal .controls ul{margin:0}
	.form-horizontal .controls ul li{float:left}
	.form-horizontal .controls ul li a{ border-radius: 5px;display: block;padding: 5px 10px; background-color: white;color: black;}
	.form-horizontal .controls ul li a.active{background-color: #ffb401 !important;}
</style>
</head>
<body>
			<div class="container-fluid">
				<div class="row-fluid">
					<div class="widget-container">
						<form class="form-horizontal" id="modifyClassTeacherForm" action="javascript:void(0);">
							<input type="hidden" value="${id}" name="id" id="id"/>
							<input type="hidden" name="teacherId" id="teacherId" value="">
							<%-- <table class="responsive table table-striped table-bordered" id="data-table">
								<thead>
									<tr role="row">
										 <th>姓名</th>
									</tr>
								</thead>
								<tbody id="module_list_content">
									 <c:forEach items="${teacherList}" var="teacher">
				                        <tr>
				                            <td><input type="radio" id="teacherId" name="teacherId" <c:if test="${teacher.id==teacherId}">checked</c:if> value="${teacher.id}">${teacher.name}</td>
				                        </tr>
			                       	  </c:forEach>
								</tbody>
							</table> --%>
							<p style="font-weight:bold;font-size:14px;">班主任列表</p>
							<div class="control-group">
										<label class="control-label"></label>
										<div class="controls">
											<ul>
											<c:forEach items="${teacherList}" var="teacher">
												<li><a href="javascript:void(0)" <c:if test='${teacher.id==teacherId}'>class="active"</c:if>   onclick="pushNameAndId('${teacher.id}')">${teacher.name}</a></li>
											</c:forEach>
											</ul>
										</div>
							</div>
							<div class="form-actions tan_bottom">
							<button class="btn btn-warning" type="button" onclick="saveOrUpdate();">确定</button>
						</div>
						</form>
					</div>
				</div>
			</div>
</body>
<script type="text/javascript">
$(function(){
	$(".form-horizontal .controls ul li a").click(function(){
		$(".form-horizontal .controls ul li a").removeClass("active");
		$(this).addClass("active")
	})
});

function pushNameAndId(id){
	$("#teacherId").val(id);
}

//保存或更新修改
function saveOrUpdate() {
	var loader = new loadLayer();
	var $id = $("#id").val();
	var teacherId = $("#teacherId").val();
	var $requestData = formData2JSONObj("#addClassTeacherForm");
	var url = "${pageContext.request.contextPath}/teach/classTeacher/updateClassTeacher?id="+$id+"&teacherId="+teacherId;
	loader.show();
	$.post(url, $requestData, function(data, status) {
		if("success" === status) {
			$.success('保存成功');
			if("success" === data) {
				if(parent.core_iframe != null) {
						parent.core_iframe.window.location.reload();
					} else {
						parent.window.location.reload();
					}
				$.closeWindow();
			} else {
				
			}
		}else{
			$.error("保存失败");
		}
		loader.close();
	});
}
</script>
</html>