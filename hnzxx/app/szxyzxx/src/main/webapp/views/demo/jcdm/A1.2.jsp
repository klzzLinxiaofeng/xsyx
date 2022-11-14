<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<title>代码规范表</title>
</head>
<body>
<div class="container-fluid" style="margin-top:15px;">
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>
							代码规范表
							<p class="btn_link" style="float: right;">
							<a onclick="loadCreatePage();" class="a4" href="javascript:void(0)"><i class="fa fa-plus"></i>新建</a>
							</p>
						</h3>
					</div>
					<div class="content-widgets">
						<div class="widget-container">
							<div class="select_b">
								<div class="select_div">
									<input type="text">
								</div>
								
								  <button type="button" class="btn btn-primary" onclick="search()">查询</button>
								<div class="clear"></div>
							</div>
							<table class="responsive table table-striped" id="data-table">
								<thead>
									<tr>
										<th>次序</th>
										<th>名称</th>
										<th>代码</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>1</td>
										<td>国籍</td>
										<td>JY-GJ</td>
										<td><button class="btn btn-success">编辑</button><button class="btn btn-danger">删除</button></td>
									</tr>
									<tr>
										<td>2</td>
										<td>国籍</td>
										<td>JY-GJ</td>
										<td><button class="btn btn-success">编辑</button><button class="btn btn-danger">删除</button></td>
									</tr>
								</tbody>
							</table>
							<div class="clear"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div style="text-align:center;background-color:#29c5c7;height:40px;padding-top:8px;">
		<button class="btn btn-success">导入</button>
		<button class="btn">返回</button>
	</div>
</body>
</html>