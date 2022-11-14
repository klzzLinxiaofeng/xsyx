<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/res/css/extra/add.css" rel="stylesheet">
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
							微课管理
						</h3>
					</div>
					<div class="content-widgets">
						<div class="widget-container">
							<table class="responsive table table-striped" id="data-table">
							<thead>
								<tr role="row">
									 <th>作业时间</th>
			                         <th>班级</th>
			                         <th>学习人数</th>
			                         <th>未学习人数</th>
			                         <th class="caozuo">操作</th>
								</tr>
							</thead>
							<tbody id="module_list_content">
								<tr>
									<td>2015年2月4日~2015年2月4日  23:00</td>
									<td>六年级3班</td>
									<td>0</td>
									<td>55</td>
									<td class="caozuo"><a href="javascript:void(0)">修改信息</a><a href="javascript:void(0)">删除</a></td>
								</tr>
								<tr>
									<td>2015年2月4日~2015年2月4日  23:00</td>
									<td>六年级3班</td>
									<td>0</td>
									<td>55</td>
									<td class="caozuo"><a href="javascript:void(0)">修改信息</a><a href="javascript:void(0)">删除</a></td>
								</tr>
								<tr >
									<td>2015年2月4日~2015年2月4日  23:00</td>
									<td>六年级3班</td>
									<td>0</td>
									<td>55</td>
									<td class="caozuo"><a href="javascript:void(0)">修改信息</a><a href="javascript:void(0)">删除</a></td>
								</tr>
							</tbody>
						</table>
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