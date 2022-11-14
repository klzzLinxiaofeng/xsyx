<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加部门成员</title>
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/css/extra/add.css" rel="stylesheet">
<style>
</style>
</head>
<body style="background-color:cdd4d7 !important">
	<div class="row-fluid" >
		<div class="span12">
			<div class="content-widgets" style="margin-bottom:0">
				<div class="widget-container" style="padding:20px 0 0;">
					<form class="form-horizontal tan_form" id="role_form" action="javascript:void(0);">
						<div class="control-group">
							<label class="control-label">姓名</label>
							<div class="controls">
								<input type="text" id="code" name="code" class="span3" placeholder="请输入唯一角色编号, 不能为空" value="${role.code}" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
								<button class="btn btn-warning">搜索</button>
								<span class="red">请输入姓名关键字</span>
							</div>
						</div>
						<div class="select_view">
							<p class="title">搜索结果（3个结果）</p>
							<table class="table table-bordered responsive white">
								<thead><tr><th><input type="checkbox" /></th><th>序号</th><th>姓名</th>
								<th>性别</th><th>联系方式</th><th>所在部门</th><th>在职状态</th><th>操作</th></tr></thead>
								<tbody>
								<tr><td><input type="checkbox" /></td><td>1</td><td>杨晓宇</td><td>女</td><td>13451147560</td><td>无</td><td>在职</td><td><button class="btn btn-blue" type="button">添加</button><button class="btn btn-blue" type="button">查看</button></td></tr>
								<tr><td><input type="checkbox" /></td><td>1</td><td>杨晓宇</td><td>女</td><td>13451147560</td><td>无</td><td>在职</td><td><button class="btn btn-blue" type="button">添加</button><button class="btn btn-blue" type="button">查看</button></td></tr>
									<tr><td><input type="checkbox" /></td><td>1</td><td>杨晓宇</td><td>女</td><td>13451147560</td><td>无</td><td>在职</td><td><button class="btn btn-blue" type="button">添加</button><button class="btn btn-blue" type="button">查看</button></td></tr>
								</tbody>
							</table>
						</div>
							<div class="form-actions tan_bottom">
								<input type="hidden" id="id" name="id" value="${role.id}"/>
								<c:if test="${isCK == null || isCk == ''}">
								<button class="btn btn-warning" type="button" onclick="saveOrUpdate();">确定</button>
								</c:if>
							</div>
						
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	
</script>
</html>