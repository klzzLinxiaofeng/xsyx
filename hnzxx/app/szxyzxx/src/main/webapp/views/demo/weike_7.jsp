<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<title>学校列表</title>
</head>
<body>
	<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon"/>
			<jsp:param value="微课管理" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>
							查看详情
							<p style="float:right;" class="btn_link">
						<a class="a4" href="javascript:void(0)"><i class="fa fa-reply"></i>返回列表</a>
					</p>
						</h3>
					</div>
					<div class="gray_1">
						<p><b>班级：</b>六年级(3)班<b> 科目：</b>语文 <b>作业时间：</b>2015年2月4日 ~ 2015年2月4日 23:00</p>
					</div>
					<div class="content-widgets">
						<div class="widget-container">
						<div class="zysj">
							<table class="table_1">
								<tbody>
									<tr>
										<td rowspan="2">
											<button class="btn btn-warning">查看微课</button>
										</td>
										<td rowspan="2" class="a1">
											作业数据
										</td>
										<td class="a2">平均完成时间</td>
										<td class="a2">已完成</td>
										<td class="a2">部分完成</td>
										<td class="a2">未做</td>
										<td rowspan="2" style="text-align:right">
											<button class="btn btn-blue">一键写评语</button>
										</td>
									</tr>
									<tr>
										<td class="a2">30分30秒</td>
										<td class="a3">55人</td>
										<td class="a4">0人</td>
										<td class="a4">0人</td>
									</tr>
								</tbody>
							</table>
						</div>
						<div style="max-height:500px;overflow:auto;display:block;margin-bottom:10px;">
							<table class="responsive table table-striped" id="data-table" >
							<thead>
								<tr role="row">
									 <th>学籍号</th>
			                         <th>姓名</th>
			                         <th>完成情况</th>
			                         <th>完成时间</th>
			                         <th>完成用时</th>
			                         <th class="caozuo">操作</th>
								</tr>
							</thead>
							<tbody id="module_list_content" >
								<tr>
									<td>G445155199005194978</td>
									<td>赵欣</td>
									<td>已完成</td>
									<td>2014-04-15 19:00</td>
									<td>03:00</td>
									<td class="caozuo"><a href="javascript:void(0)">查看评语</a></td>
								</tr>
								<tr>
									<td>G445155199005194978</td>
									<td>赵欣</td>
									<td>已完成</td>
									<td>2014-04-15 19:00</td>
									<td>03:00</td>
									<td class="caozuo"><a href="javascript:void(0)">查看评语</a></td>
								</tr>
								<tr>
									<td>G445155199005194978</td>
									<td>赵欣</td>
									<td>已完成</td>
									<td>2014-04-15 19:00</td>
									<td>03:00</td>
									<td class="caozuo"><a href="javascript:void(0)">写评语</a></td>
								</tr>
								<tr>
									<td>G445155199005194978</td>
									<td>赵欣</td>
									<td>已完成</td>
									<td>2014-04-15 19:00</td>
									<td>03:00</td>
									<td class="caozuo"><a href="javascript:void(0)">查看评语</a></td>
								</tr>
							</tbody>
						</table>
						</div>
						<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
							<jsp:param name="id" value="module_list_content" />
							<jsp:param name="url" value="/teach/school/schoolList?sub=list" />
							<jsp:param name="pageSize" value="${page.pageSize }" />
						</jsp:include>
						<div class="clear"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
</script>
</html>