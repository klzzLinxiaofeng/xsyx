<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/css/dygl/class_collective.css" rel="stylesheet">
<title>评价报表</title>
</head>
<body>
<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon"/>
			<jsp:param value="评价报表" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>
							评价报表
							<p class="btn_link" style="float: right;">
							<a href="javascript:void(0)" class="a4" onclick="">导出</a>
							</p>
						</h3>
					</div>
					<div class="content-widgets">
						<div class="widget-container">
							<div class="select_b">
								
								<div class="select_div">
									<span>学期：</span> <select></select>
								</div>
								<div class="select_div">
									<span>年级：</span> <select></select>
								</div>
								<div class="select_div">
									<span>班级：</span> <select></select>
								</div>
								<div class="select_div">
									<span>月份：</span> <select></select>
								</div>
								<div class="select_div">
									<span>周次：</span> <select></select>
								</div>
								<div class="clear"></div>
							</div>
							 <div class="card_detail">
                        <div class="project-rated">
							<p>班级体评价状况数据分析</p>
							<div class="points-content" style="display: block;">
                            <div class="points" style="position: relative;">
                            <table class="responsive table table-striped reflective-evaluate" id="data-table">
                            <thead style="background:#daf0fb;">
                                <tr role="row" style="color:#4c708a;">
                                   <th>班级</th>
                                   <th style="width:35%;">总分数</th>
                                   <th>全年级占比</th>
                                </tr>
                            </thead>
                            <tbody id="module_list_content">
                                 <tr class="">
                                     <td>一年级</td>
                                     <td>-5<div><p style="width:10%;"></p></div></td>
                                     <td>20%</td>
                                </tr>
                                 <tr class="">
                                     <td>二年级</td>
                                      <td>-3<div><p style="width:30%;"></p></div></td>
                                      <td>20%</td>
                                </tr>
                                 <tr class="">
                                     <td>三年级</td>
                                      <td>-2<div><p style="width:70%;"></p></div></td>
                                      <td>20%</td>
                                </tr>
                                 <tr class="">
                                     <td>四年级</td>
                                      <td>-4<div><p  style="width:20%;"></p></div></td>
                                      <td>20%</td>
                                </tr>
                                 <tr class="">
                                     <td>五年级</td>
                                      <td>-5<div><p  style="width:90%;"></p></div></td>
                                      <td>20%</td>
                                </tr>
                                <tr class="">
                                      <td><b>统计：</b>扣分项<span>-300</span></td>
                                      <td>加分项<span style="color: #2399dc;">+15</span></td>
                                      <td>总分项<span>-250</span></td>
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
			</div>
		</div>
	</div>
</body>
</html>