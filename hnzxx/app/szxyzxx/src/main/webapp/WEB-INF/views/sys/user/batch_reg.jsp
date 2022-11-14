<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/views/embedded/common.jsp"%>
<%@ include file="/views/embedded/plugin/avatar_js.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/res/css/extra/add.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/plugin/falgun/css/new_add.css" rel="stylesheet">
<title>批量开通推送服务</title>
</head>
<body>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>
						</h3>
					</div>
					<div class="content-widgets">
						<div class="widget-container">
							<div class="select_b">
								<div class="select_div">
									<span>学校：</span>
									<select id="schoolId" placeholder="请选择" style="margin-bottom: 0; padding: 6^px; width: 184px; margin-right: 3px;">
										<option value="">请选择</option>
										<c:forEach items="${schools}" var="school" >
											<option value="${school.id}">${school.name}</option>
										</c:forEach>
									</select>
								</div>
								<div class="select_div">
									<span>&nbsp;  &nbsp;类型：</span>
									<select id="userType" placeholder="请选择" style="margin-bottom: 0; padding: 6^px; width: 184px; margin-right: 3px;">
										<option value="">请选择</option>
										<option value="0">教师</option>
										<option value="1">学生</option>
										<option value="2">家长</option>
									</select>
								</div>
								<div class="clear"></div>
							</div>
							<div class="select_b">
								<div class="select_div">
									<span>账号：</span><input type="text" id="userName" name="userName" data-id-container="userName" style="margin-bottom: 0; padding: 6^px; height:31px; width: 184px; margin-right: 3px;"
											placeholder=""value="">
								</div>
								<div class="select_div">
									<span>用户昵称：</span><input type="text" id="name" name="name" data-id-container="name" style="margin-bottom: 0; padding: 6^px; height:31px; width: 184px; margin-right: 3px;">
								</div>
								<button type="button" class="btn btn-primary" onclick="regPush()">开通服务</button>
								<div class="clear"></div>
								<table class="responsive table table-striped" id="data-table">
								<thead>
									<tr role="row">
										<th>用户账号</th>
										<th>状态</th>
									</tr>
								</thead>
								<tbody id="suc_list_content">
									<tr class="active">
										<th colspan="2" style="text-align: center;">开通成功账号列表</th>
									</tr>
								</tbody>
								<tbody id="fail_list_content">
									<tr class="active">
										<th colspan="2" style="text-align: center;">已开通账号列表</th>
									</tr>
								</tbody>
							</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
<script type="text/javascript">
	$(function() {
		$("#schoolId").chosen({search_contains: true});
		$("#userType").chosen();
		$("#data-table").hide();
	});

	function regPush() {
		var loader = new loadLayer();
		var url = "${pageContext.request.contextPath}/sys/user/batchReg";
		var userName = $("#userName").val();
		var name = $("#name").val();
		var schoolId = $("#schoolId").val();
		var type = $("#userType").val();
		url = url + "?userName=" + userName + "&name=" + name + "&schoolId=" + schoolId + "&type=" + type;
		loader.show();
		$.post(url,{},function(data,status){
			loader.close();
			if ("success" === status) {
				data = eval("(" + data + ")");
// 				var isSuc = true;
				$("#data-table").show();
				var $sucSelector = $("#suc_list_content");
				var $failSelector = $("#fail_list_content");
				$sucSelector.find(".new_add").remove();
				$failSelector.find(".new_add").remove();
				var usernames = "";
				$.each(data, function(index, value) {
					var username = value.pk;
					var info = eval("(" + value.info + ")");
					if(400 === info.statusCode) {
						if("duplicate_unique_property_exists" === info.error) {
							$failSelector.append('<tr class="new_add"><td>' + username + '</td><td><span style="color:green;">已开通过</span></td>');
						}
					}else if(200 === info.statusCode){
						$sucSelector.append('<tr class="new_add"><td>' + username + '</td><td><span style="color:green;">开通成功</span></td>');
					}
				});
// 				if(isSuc) {
// 					$.alert("开通成功");					
// 				} else {
// 					$.alert("账号" + usernames + "已开通过");
// 				}
			} else {
				$.error("注册失败，系统异常");
			}
		});
	}

	
</script>
</html>