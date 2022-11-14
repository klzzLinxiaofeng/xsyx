<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/css/dxa/dxa.css" rel="stylesheet">
<style>
	.xj_list .xj_div{padding:0}
	.table th:first-child,.table td:first-child{padding-left:15px;}
	.finish_style{color:#189f4b}
	.unfinish_style{color:#9f0c1a}
</style>
<title>布置微课</title>
</head>
<body>
<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon"/>
			<jsp:param value="布置微课" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="xsxj">
				<div class="nav">
					<a href="javascript:void(0)" class="on">视频</a>
					<a href="javascript:void(0)">文档</a>
				</div>
				<div class="xj_list">
					<div class="xj_div">
						<table class="responsive table table-striped" id="data-table">
								<thead>
									<tr>
										<th>序号</th>
										<th>文件名称</th>
										<th>状态</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>1</td>
										<td>一元一次方程.mp4</td>
										<td><span class="finish_style">完成</span></td>
										<td><button class="btn btn-success">详情</button></td>
									</tr>
									<tr>
										<td>2</td>
										<td>一元一次方程.mp4</td>
										<td><span class="finish_style">完成</span></td>
										<td><button class="btn btn-success">详情</button></td>
									</tr>
									<tr>
										<td>3</td>
										<td>一元一次方程.mp4</td>
										<td><span class="finish_style">完成</span></td>
										<td><button class="btn btn-success">详情</button></td>
									</tr>
									<tr>
										<td>4</td>
										<td>一元一次方程.mp4</td>
										<td><span class="unfinish_style">未完成</span></td>
										<td><button class="btn btn-success">详情</button></td>
									</tr>
									<tr>
										<td>5</td>
										<td>一元一次方程.mp4</td>
										<td><span class="finish_style">完成</span></td>
										<td><button class="btn btn-success">详情</button></td>
									</tr>
									<tr>
										<td>6</td>
										<td>一元一次方程.mp4</td>
										<td><span class="finish_style">完成</span></td>
										<td><button class="btn btn-success">详情</button></td>
									</tr>
									<tr>
										<td>7</td>
										<td>一元一次方程.mp4</td>
										<td><span class="finish_style">完成</span></td>
										<td><button class="btn btn-success">详情</button></td>
									</tr>
									<tr>
										<td>8</td>
										<td>一元一次方程.mp4</td>
										<td><span class="finish_style">完成</span></td>
										<td><button class="btn btn-success">详情</button></td>
									</tr>
									<tr>
										<td>9</td>
										<td>一元一次方程.mp4</td>
										<td><span class="finish_style">完成</span></td>
										<td><button class="btn btn-success">详情</button></td>
									</tr>
									<tr>
										<td>10</td>
										<td>一元一次方程.mp4</td>
										<td><span class="finish_style">完成</span></td>
										<td><button class="btn btn-success">详情</button></td>
									</tr>
								</tbody>
							</table>
							<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
								<jsp:param name="id" value="studentAid_list_content" />
								<jsp:param name="url" value="/teach/studentaid/studentAidList?sub=list&dm=${param.dm}" />
								<jsp:param name="pageSize" value="${page.pageSize}" />
							</jsp:include>
							<div class="clear"></div>
					</div>
					<div class="xj_div" style="display:none;">
						<table class="responsive table table-striped" id="data-table">
								<thead>
									<tr>
										<th>序号</th>
										<th>文件名称</th>
										<th>状态</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>1</td>
										<td>一元一次方程.docx</td>
										<td><span class="finish_style">完成</span></td>
										<td><button class="btn btn-success">详情</button></td>
									</tr>
									<tr>
										<td>2</td>
										<td>一元一次方程.docx</td>
										<td><span class="finish_style">完成</span></td>
										<td><button class="btn btn-success">详情</button></td>
									</tr>
									<tr>
										<td>3</td>
										<td>一元一次方程.docx</td>
										<td><span class="finish_style">完成</span></td>
										<td><button class="btn btn-success">详情</button></td>
									</tr>
									<tr>
										<td>4</td>
										<td>一元一次方程.docx</td>
										<td><span class="unfinish_style">未完成</span></td>
										<td><button class="btn btn-success">详情</button></td>
									</tr>
									<tr>
										<td>5</td>
										<td>一元一次方程.docx</td>
										<td><span class="finish_style">完成</span></td>
										<td><button class="btn btn-success">详情</button></td>
									</tr>
									<tr>
										<td>6</td>
										<td>一元一次方程.docx</td>
										<td><span class="finish_style">完成</span></td>
										<td><button class="btn btn-success">详情</button></td>
									</tr>
									<tr>
										<td>7</td>
										<td>一元一次方程.docx</td>
										<td><span class="finish_style">完成</span></td>
										<td><button class="btn btn-success">详情</button></td>
									</tr>
									<tr>
										<td>8</td>
										<td>一元一次方程.docx</td>
										<td><span class="finish_style">完成</span></td>
										<td><button class="btn btn-success">详情</button></td>
									</tr>
									<tr>
										<td>9</td>
										<td>一元一次方程.docx</td>
										<td><span class="finish_style">完成</span></td>
										<td><button class="btn btn-success">详情</button></td>
									</tr>
									<tr>
										<td>10</td>
										<td>一元一次方程.docx</td>
										<td><span class="finish_style">完成</span></td>
										<td><button class="btn btn-success">详情</button></td>
									</tr>
								</tbody>
							</table>
							<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
								<jsp:param name="id" value="studentAid_list_content" />
								<jsp:param name="url" value="/teach/studentaid/studentAidList?sub=list&dm=${param.dm}" />
								<jsp:param name="pageSize" value="${page.pageSize}" />
							</jsp:include>
							<div class="clear"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>
	$(function(){
		$(".xsxj .nav a").click(function(){
			$(".xsxj .nav a").removeClass("on");
			$(this).addClass("on");
			var i=$(this).index();
			$(".xj_list .xj_div").hide();
			$(".xj_list .xj_div").eq(i).show();
		})
	})
</script>
</body>
</html>