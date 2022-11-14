<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<title>学校列表</title>
<style>
	.table tr td,.table tr th{
		text-align:center;
		vertical-align:middle;
	}
</style>
</head>
<body>
	<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon"/>
			<jsp:param value="学期统计" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>
							学期统计<span style="font-size:14px;">(学生微课完成次数)</span>
							<p style="float:right;" class="btn_link">
						<a class="a4" href="javascript:void(0)"><i class="fa fa-reply"></i>返回列表</a>
					</p>
						</h3>
					</div>
					
					<div class="content-widgets">
					<p style="height:33px;line-height:33px;font-size:14px;color:#4B4B4B;margin-bottom:0;padding-left:12px;font-family:'微软雅黑';">六年级1班(语文)</p>
						<div style="max-height:500px;overflow:auto;display:block;margin-bottom:10px;width:100%;">
							<table class="responsive table table-striped table-bordered"  >
							<thead>
								<tr role="row">
									 <th>姓名</th>
			                         <th>9月<br>(4次)</th>
			                         <th>10月<br>(4次)</th>
			                         <th>11月<br>(4次)</th>
			                         <th>12月<br>(4次)</th>
			                         <th>13月<br>(4次)</th>
			                         <th>总共<br>(20次)</th>
								</tr>
							</thead>
							<tbody id="module_list_content" >
								<tr>
									<td>罗志明</td>
									<td>4</td>
									<td>3</td>
									<td>4</td>
									<td>3</td>
									<td>4</td>
									<td>18</td>
								</tr>
								<tr>
									<td>罗志明</td>
									<td>4</td>
									<td>3</td>
									<td>4</td>
									<td>3</td>
									<td>4</td>
									<td>18</td>
								</tr>
								<tr>
									<td>周津</td>
									<td>4</td>
									<td>3</td>
									<td>4</td>
									<td>3</td>
									<td>4</td>
									<td>18</td>
								</tr>
								<tr>
									<td>罗志明</td>
									<td>4</td>
									<td>3</td>
									<td>4</td>
									<td>3</td>
									<td>4</td>
									<td>18</td>
								</tr>
								<tr>
									<td>罗志明</td>
									<td>4</td>
									<td>3</td>
									<td>4</td>
									<td>3</td>
									<td>4</td>
									<td>18</td>
								</tr>
								<tr>
									<td>周津</td>
									<td>4</td>
									<td>3</td>
									<td>4</td>
									<td>3</td>
									<td>4</td>
									<td>18</td>
								</tr>
							</tbody>
						</table>
						</div>
						<div class="clear"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
</body>
<script type="text/javascript">
</script>
</html>