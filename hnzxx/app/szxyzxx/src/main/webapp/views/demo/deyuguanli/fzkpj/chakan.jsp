<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<title>发展评价卡</title>
<link href="${pageContext.request.contextPath}/res/css/dygl/class_collective.css" rel="stylesheet">
</head>
<body>
<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon"/>
			<jsp:param value="发展评价卡" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>
							发展评价卡
						</h3>
					</div>
					<div class="content-widgets">
							<div class="check-rated">
							<div class="minutes-rated">
	                            <a href="javascript:void(0);" class="see-rated">发展评价卡查看</a>
	                            <a href="javascript:void(0);">发展评价卡录入</a>
	                        </div>
                        <div class="card_detail">
									<div class="project-rated">
										<div class="content-widgets">
											<div class="widget-container" style="padding: 20px 0 0 0">
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
														<span>月次：</span> <select></select>
													</div>
													<p class="btn_link"
														style="float: right; line-height: 47px; margin-right: 10px;">
														<a href="javascript:void(0)" class="a3"
															onclick="$.refreshWin();"><i
															class="fa  fa-plus"></i> 导出</a> <a
															href="javascript:void(0)" class="a2" onclick=""><i
															class="fa  fa-bar-chart"></i> 评价报表</a>
													</p>
													<div class="clear"></div>
												</div>
											</div>
										</div>
										<div  style="display: block;">
                            <p class="unreviewed ">三年级（2）班 5月份<span class="reviewed-cease">（已审核）</span> 录入时间：2016-07-25</p>
                           <!--  <p class="unreviewed ">三年级（2）班 5月份<span class="reviewed-no">（未审核）</span> 录入时间：2016-07-25</p> -->
										<div class="points limit-high" style="position: relative;">
                            <table class="responsive table table-striped reflective-evaluate" id="data-table">
                            <thead>
                                <tr role="row">
                                   <th>学号</th>
                                   <th>姓名</th>
                                   <th style="width:35%;">评价卡</th>
                                </tr>
                            </thead>
                            <tbody id="module_list_content">
                                 <tr class="">
                                     <td>1</td>
                                     <td>张小龙</td>
                                     <td>15</td>
                                </tr>
                                 <tr class="">
                                     <td>2</td>
                                     <td>张小龙</td>
                                     <td>15</td>
                                </tr>
                                 <tr class="">
                                     <td>3</td>
                                     <td>张小龙</td>
                                     <td>15</td>
                                </tr>
                                 <tr class="">
                                     <td>4</td>
                                     <td>张小龙</td>
                                     <td>15</td>
                                </tr>
                                 <tr class="">
                                     <td>5</td>
                                     <td>张小龙</td>
                                     <td>15</td>
                                </tr>
                                <tr class="">
                                     <td>6</td>
                                     <td>张小龙</td>
                                     <td>15</td>
                                </tr>
                                <tr class="">
                                     <td>7</td>
                                     <td>张小龙</td>
                                     <td>15</td>
                                </tr>
                                <tr class="">
                                     <td>8</td>
                                     <td>张小龙</td>
                                      <td>15</td>
                                </tr>
                                <tr class="">
                                     <td>9</td>
                                     <td>张小龙</td>
                                     <td>15</td>
                                </tr>
                                <tr class="">
                                     <td>10</td>
                                     <td>张小龙</td>
                                      <td>15</td>
                                </tr><tr class="">
                                     <td>10</td>
                                     <td>张小龙</td>
                                      <td>15</td>
                                </tr><tr class="">
                                     <td>10</td>
                                     <td>张小龙</td>
                                     <td>15</td>
                                </tr><tr class="">
                                     <td>10</td>
                                     <td>张小龙</td>
                                      <td>15</td>
                                </tr></tr><tr class="">
                                     <td>10</td>
                                     <td>张小龙</td>
                                      <td>15</td>
                                </tr>
                                </tr><tr class="">
                                     <td>10</td>
                                     <td>张小龙</td>
                                      <td>15</td>
                                </tr>
                                </tr><tr class="">
                                     <td>10</td>
                                     <td>张小龙</td>
                                      <td>15</td>
                                </tr>
                                </tr><tr class="">
                                     <td>10</td>
                                     <td>张小龙</td>
                                      <td>15</td>
                                </tr>
                                </tr><tr class="">
                                     <td>10</td>
                                     <td>张小龙</td>
                                      <td>15</td>
                                </tr>
                             </tbody>
                        </table>
                        </div>
                        </div>
									</div>
									<div class="project-rated">
										<div class="content-widgets">
											<div class="widget-container" style="padding: 20px 0 0 0">
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
															<span>月次：</span> <select></select>
														</div>
														<p class="btn_link"
															style="float: right; margin: 10px 10px 0 0;">
															<a href="javascript:void(0)" class="a4" onclick="">保存</a>
														</p>
														<div class="clear"></div>
													</div>
											</div>
										</div>
										<div class="points limit-high" style="position: relative;">
                            <table class="responsive table table-striped reflective-evaluate" id="data-table">
                            <thead>
                                <tr role="row">
                                   <th>学号</th>
                                   <th>姓名</th>
                                   <th style="width:35%;">评价卡</th>
                                </tr>
                            </thead>
                            <tbody id="module_list_content">
                                 <tr class="">
                                     <td>1</td>
                                     <td>张小龙</td>
                                     <td><input type="text" class="td-spacing"></td>
                                </tr>
                                 <tr class="">
                                     <td>2</td>
                                     <td>张小龙</td>
                                     <td><input type="text" class="td-spacing"></td>
                                </tr>
                                 <tr class="">
                                     <td>3</td>
                                     <td>张小龙</td>
                                     <td><input type="text" class="td-spacing"></td>
                                </tr>
                                 <tr class="">
                                     <td>4</td>
                                     <td>张小龙</td>
                                     <td><input type="text" class="td-spacing"></td>
                                </tr>
                                 <tr class="">
                                     <td>5</td>
                                     <td>张小龙</td>
                                     <td><input type="text" class="td-spacing"></td>
                                </tr>
                                <tr class="">
                                     <td>6</td>
                                     <td>张小龙</td>
                                     <td><input type="text" class="td-spacing"></td>
                                </tr>
                                <tr class="">
                                     <td>7</td>
                                     <td>张小龙</td>
                                     <td><input type="text" class="td-spacing"></td>
                                </tr>
                                <tr class="">
                                     <td>8</td>
                                     <td>张小龙</td>
                                     <td><input type="text" class="td-spacing"></td>
                                </tr>
                                <tr class="">
                                     <td>9</td>
                                     <td>张小龙</td>
                                     <td><input type="text" class="td-spacing"></td>
                                </tr>
                                <tr class="">
                                     <td>10</td>
                                     <td>张小龙</td>
                                     <td><input type="text" class="td-spacing"></td>
                                </tr><tr class="">
                                     <td>10</td>
                                     <td>张小龙</td>
                                     <td><input type="text" class="td-spacing"></td>
                                </tr><tr class="">
                                     <td>10</td>
                                     <td>张小龙</td>
                                     <td><input type="text" class="td-spacing"></td>
                                </tr><tr class="">
                                     <td>10</td>
                                     <td>张小龙</td>
                                     <td><input type="text" class="td-spacing"></td>
                                </tr><tr class="">
                                     <td>10</td>
                                     <td>张小龙</td>
                                     <td><input type="text" class="td-spacing"></td>
                                </tr><tr class="">
                                     <td>10</td>
                                     <td>张小龙</td>
                                     <td><input type="text" class="td-spacing"></td>
                                </tr><tr class="">
                                     <td>10</td>
                                     <td>张小龙</td>
                                     <td><input type="text" class="td-spacing"></td>
                                </tr><tr class="">
                                     <td>10</td>
                                     <td>张小龙</td>
                                     <td><input type="text" class="td-spacing"></td>
                                </tr><tr class="">
                                     <td>10</td>
                                     <td>张小龙</td>
                                     <td><input type="text" class="td-spacing"></td>
                                </tr><tr class="">
                                     <td>10</td>
                                     <td>张小龙</td>
                                     <td><input type="text" class="td-spacing"></td>
                                </tr><tr class="">
                                     <td>10</td>
                                     <td>张小龙</td>
                                     <td><input type="text" class="td-spacing"></td>
                                </tr><tr class="">
                                     <td>10</td>
                                     <td>张小龙</td>
                                     <td><input type="text" class="td-spacing"></td>
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
	<script type="text/javascript">
	$(".minutes-rated a").click(function(){
	    $(".minutes-rated a").removeClass("see-rated");
	    $(this).addClass("see-rated");
	    var i=$(this).index();
	    $(".project-rated").hide();
	    $(".project-rated").eq(i).show();
	});
	</script>
</body>
</html>