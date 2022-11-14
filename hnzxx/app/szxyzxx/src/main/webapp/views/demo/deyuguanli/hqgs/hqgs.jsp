<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/css/dygl/class_collective.css" rel="stylesheet">
<title>红旗公示</title>
</head>
<body>
<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon"/>
			<jsp:param value="红旗公示" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>
							红旗公示
							<p class="btn_link" style="float: right;">
                                <a href="javascript:void(0)" onclick="" class="a2">评定</a>
							 	<a href="javascript:void(0)" onclick="" class="a3"><i class="fa fa-plus"></i>导出</a>
                                <a href="javascript:void(0)" onclick="" class="a4">设置评分标准</a>
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
									<span>周次：</span> <select></select>
								</div>
								<div class="clear"></div>
							</div>
							 <div class="points-content table-spacing" style="display: block;">
                            <p>流动红旗评比  <span>第一周</span></p>
                            <div class="points limit-cancel" style="position: relative;">
                            <table class="responsive table table-striped reflective-evaluate" >
                            <thead>
                                <tr role="row">
                                   <th>班级</th>
                                   <th>班主任</th>
                                   <th>扣分</th>
                                   <th>加分</th>
                                   <th>得分</th>
                                   <th>恶性事件</th>
                                   <th>班级排名</th>
                                   <th>流动红旗</th>
                                   <th>班级详情</th>
                                </tr>
                            </thead>
                            <tbody >
                                 <tr class="">
                                     <td>1班</td>
                                     <td>张小龙</td>
                                     <td><span>1.5</span></td>
                                     <td><span>0</span></td>
                                     <td><span>99</span></td>
                                     <td></td>
                                     <td>3</td>
                                     <td>红旗班级</td>
                                     <td><a href="javascript:void(0);">查看</a></td>
                                </tr>
                                 <tr class="">
                                     <td>2班</td>
                                     <td>张小龙</td>
                                     <td><span>1.5</span></td>
                                     <td><span>0</span></td>
                                     <td><span>99</span></td>
                                     <td></td>
                                     <td>3</td>
                                     <td>红旗班级</td>
                                     <td><a href="javascript:void(0);">查看</a></td>
                                </tr>
                                 <tr class="">
                                     <td>3班</td>
                                     <td>张小龙</td>
                                     <td><span>1.5</span></td>
                                     <td><span>0</span></td>
                                     <td><span>99</span></td>
                                     <td></td>
                                     <td>3</td>
                                     <td>红旗班级</td>
                                     <td><a href="javascript:void(0);">查看</a></td>
                                </tr>
                                 <tr class="">
                                     <td>4班</td>
                                     <td>张小龙</td>
                                     <td><span>1.5</span></td>
                                     <td><span>0</span></td>
                                     <td><span>99</span></td>
                                     <td></td>
                                     <td>3</td>
                                     <td>红旗班级</td>
                                     <td><a href="javascript:void(0);">查看</a></td>
                                </tr>
                                 <tr class="">
                                     <td>5班</td>
                                     <td>张小龙</td>
                                     <td><span>1.5</span></td>
                                     <td><span>0</span></td>
                                     <td><span>99</span></td>
                                     <td></td>
                                     <td>3</td>
                                     <td>红旗班级</td>
                                     <td><a href="javascript:void(0);">查看</a></td>
                                </tr>
                                <tr class="">
                                     <td>6班</td>
                                     <td>张小龙</td>
                                     <td><span>1.5</span></td>
                                     <td><span>0</span></td>
                                     <td><span>99</span></td>
                                     <td></td>
                                     <td>3</td>
                                     <td>红旗班级</td>
                                     <td><a href="javascript:void(0);">查看</a></td>
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
</body>
</html>