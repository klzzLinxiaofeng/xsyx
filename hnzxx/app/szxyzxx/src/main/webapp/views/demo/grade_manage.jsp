<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/views/embedded/common.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/res/css/extra/add.css" rel="stylesheet">
<title>${sca:getDefaultSchoolName()}</title>
<style type="text/css">
</style>
</head>
<body>
	<div class="container-fluid">
	<div class="row-fluid ">
                <div class="span12">
                    <ul class="breadcrumb">
                        <li><a href="#"><i name="dashboard" class="fa fa-home"></i></a>年级</li>
                    </ul>
                </div>
        </div>
		<div class="row-fluid ">
			<div class="span12">
				<div class="content-widgets white" style="margin-bottom: 0;">
					<div class="widget-head">
						<h3>
							年级管理
							<p style="float: right;" class="btn_link">
								<a class="a4" href="javascript:void(0)"><i
									class="fa fa-plus"></i>新增</a>
							</p>
						</h3>
					</div>
					<div class="control-group">
										<div class="select_success">
											<ul>
												<li class="on"><a href="javascript:void(0)">当年学年</a></li>
												<li ><a href="javascript:void(0)">按学年检索</a></li>
												<li ><a href="javascript:void(0)">全部记录</a></li>
											</ul>
										</div>
										<div class="select_table">
										<div style="padding:10px;background-color:#fff" class="select_2">
											<table class="table table-bordered responsive">
												<thead><tr><th>全部名称</th><th>简称</th><th>学年</th><th>学段</th><th>班级总是</th><th>操作</th></tr></thead>
												<tbody><tr><td>小学一年级</td><td>一年级</td><td>2014-2015学年</td><td>小学</td><td>0</td><td><button class="btn btn-blue">编辑</button></td></tr>
													<tr><td>小学一年级</td><td>一年级</td><td>2014-2015学年</td><td>小学</td><td>0</td><td><button class="btn btn-blue">编辑</button></td></tr>
													<tr><td>小学一年级</td><td>一年级</td><td>2014-2015学年</td><td>小学</td><td>0</td><td><button class="btn btn-blue">编辑</button></td></tr>
												</tbody>
											</table>
											</div>
											<div style="padding:10px;display:none;background-color:#fff" class="select_2" >
											<form class="form-horizontal"><div class="control-group">
									<label class="control-label">学年：</label>
									<div class="controls">
										<select>
											<option>2014-2015学年</option>
											<option>2015-2016学年</option>
										</select>
									</div>
								</div></form>
											<table class="table table-bordered responsive">
												<thead><tr><th>全部名称</th><th>简称</th><th>学年</th><th>学段</th><th>班级总是</th><th>操作</th></tr></thead>
												<tbody><tr><td>小学一年级</td><td>一年级</td><td>2014-2015学年</td><td>小学</td><td>0</td><td><button class="btn btn-blue">编辑</button></td></tr>
													<tr><td>小学一年级</td><td>一年级</td><td>2014-2015学年</td><td>小学</td><td>0</td><td><button class="btn btn-blue">编辑</button></td></tr>
													<tr><td>小学一年级</td><td>一年级</td><td>2014-2015学年</td><td>小学</td><td>0</td><td><button class="btn btn-blue">编辑</button></td></tr>
												</tbody>
											</table>
											</div>
											<div style="padding:10px;display:none;background-color:#fff" class="select_2">
											<table class="table table-bordered responsive">
												<thead><tr><th>全部名称</th><th>简称</th><th>学年</th><th>学段</th><th>班级总是</th><th>操作</th></tr></thead>
												<tbody>
												<tr><td>小学一年级</td><td>一年级</td><td>2014-2015学年</td><td>小学</td><td>0</td><td><button class="btn btn-blue">编辑</button></td></tr>
													<tr><td>小学二年级</td><td>二年级</td><td>2014-2015学年</td><td>小学</td><td>0</td><td><button class="btn btn-blue">编辑</button></td></tr>
													<tr><td>小学一年级</td><td>一年级</td><td>2014-2015学年</td><td>小学</td><td>0</td><td><button class="btn btn-blue">编辑</button></td></tr>
													<tr><td>小学一年级</td><td>一年级</td><td>2014-2015学年</td><td>小学</td><td>0</td><td><button class="btn btn-blue">编辑</button></td></tr>
													<tr><td>小学二年级</td><td>二年级</td><td>2014-2015学年</td><td>小学</td><td>0</td><td><button class="btn btn-blue">编辑</button></td></tr>
													<tr><td>小学一年级</td><td>一年级</td><td>2014-2015学年</td><td>小学</td><td>0</td><td><button class="btn btn-blue">编辑</button></td></tr>
												</tbody>
											</table>
											</div>
										</div>
									</div>
				</div>
									
			</div>
		</div>
	</div>
	<script>
    $(function(){
			$(".select_success li a").click(function(){
				
				$(".select_success li a").parent().removeClass("on");
				$(this).parent().addClass("on");
				var i=$(this).parent().index();
				$(".select_table .select_2").hide();
				$(".select_table .select_2").eq(i).show()
			})
		});
	</script>
</body>
</html>
