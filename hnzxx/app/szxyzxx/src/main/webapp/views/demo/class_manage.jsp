<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/views/embedded/common.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/res/css/extra/add.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/res/plugin/falgun/js/bootstrap-fileupload.js"></script>
<title>${sca:getDefaultSchoolName()}</title>
<style type="text/css">
</style>
</head>
<body>
	<div class="container-fluid">
	 <div class="row-fluid ">
                <div class="span12">
                    <ul class="breadcrumb">
                        <li><a href="#"><i class="fa fa-home" name="dashboard"></i></a>首页</li>
                    </ul>
                </div>
        </div>
	<div class="row-fluid">
            <div class="span12">
            <div class="content-widgets white">
            <div class="widget-head">
					<h3>班级管理（广州市第三中学）
					</h3>
			</div>
			<div class="light_grey"></div>
                <div class="content-widgets">
						<div class="select_b">
							<div class="select_div"><span>学年：</span><select style="margin-bottom: 0; padding: 6px; width: 120px;"><option>2015-2016</option><option>2014-2015</option></select></div>
							<!-- <button class="btn btn-primary">搜索</button> -->
							<p style="float:right;margin-bottom:3px;" class="btn_link">
						<a class="a2" href="javascript:void(0)"><i class="fa fa-download"></i>批量导入学生</a>
						<a class="a3" href="javascript:void(0)"><i class="fa fa-plus"></i>批量导入任课老师</a>
					</p>
							<div class="clear"></div>
						</div>
						<div class="select_c">
							<a href="javascript:void(0)" class="on">一年级</a>
							<a href="javascript:void(0)">五年级</a>
						</div>
						<div class="widget-container">
						<div class="widget-head" style="border-bottom:0 none;">
					<h3>班级
					<p style="float:right;margin-bottom:3px;" class="btn_link">
						<a class="a3" href="javascript:void(0)"><i class="fa fa-plus"></i>新增班级</a>
						<a class="a4" href="javascript:void(0)"><i class="fa fa-plus"></i>批量创建班级</a>
					</p></h3>
			</div>
                        <table class="table  table-striped responsive table-bordered">
                            <thead>
                            <tr>
                                <th>
                                    #
                                </th>
                                <th>
                                    First Name
                                </th>
                                <th>
                                    Last Name
                                </th>
                                <th>
                                    Username
                                </th>
                                <th>
                                	操作
                                </th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td rowspan="2">
                                    1
                                </td>
                                <td>
                                    Mark
                                </td>
                                <td>
                                    Otto
                                </td>
                                <td>
                                    @mdo
                                </td>
                                <td>
									<button type="button" class="btn btn-blue">编辑</button>
								</td>
                            </tr>
                            <tr>
                                <td>
                                    Mark
                                </td>
                                <td>
                                    Otto
                                </td>
                                <td>
                                    @TwBootstrap
                                </td>
                                <td>
									<button type="button" class="btn btn-blue">新建</button>
								</td>
                            </tr>
                            <tr>
                                <td>
                                    2
                                </td>
                                <td>
                                    Jacob
                                </td>
                                <td>
                                    Thornton
                                </td>
                                <td>
                                    @fat
                                </td>
                                <td>
									<button type="button" class="btn btn-blue">新建</button>
								</td>
                            </tr>
                            <tr>
                                <td>
                                    3
                                </td>
                                <td colspan="2">
                                    Larry the Bird
                                </td>
                                <td>
                                    @twitter
                                </td>
                                <td>
									<button type="button" class="btn btn-blue">新建</button>
								</td>
                            </tr>
                            </tbody>
                            </table>
                             <jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
							<jsp:param name="id" value="mytbody" />
							<jsp:param name="url" value="/jwgz/jwkhcj/cx/table?bjDm=${bjDm}&khszDm=${khszDm}" />
							<jsp:param name="pageSize" value=" ${page.pageSize }" />
						</jsp:include>
						<div class="clear"></div>
                    </div>
                </div>
                </div>
            </div>
        </div>
	</div>
	<script>
		$(function(){
			$(".select_c a").click(function(){
				$(".select_c a").removeClass("on");
				$(this).addClass("on");
			})
		})
	</script>
</body>
</html>
