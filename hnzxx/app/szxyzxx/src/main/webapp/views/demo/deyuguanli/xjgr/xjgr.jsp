<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<title>星级个人</title>
<link href="${pageContext.request.contextPath}/res/css/dygl/class_collective.css" rel="stylesheet">
</head>
<body>
<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon"/>
			<jsp:param value="星级个人" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>
							星级个人
						</h3>
					</div>
					<div class="content-widgets">
							<div class="check-rated">
							<div class="minutes-rated">
	                            <a href="javascript:void(0);" class="see-rated">班级之星</a>
	                            <a href="javascript:void(0);">年级之星</a>
	                            <a href="javascript:void(0);">全校之星</a>
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
													<p class="btn_link" style="float: right; line-height: 47px; margin-right: 10px;">
														<a href="javascript:void(0)" class="a3" onclick=""><i class="fa  fa-plus"></i> 导出</a> 
														<a href="javascript:void(0)" class="a3" onclick="">导出星级个人照片</a> 
														<a href="javascript:void(0)" class="a2" onclick="">评定</a>
														<a href="javascript:void(0)" class="a4" onclick="">设置</a>
													</p>
													<div class="clear"></div>
												</div>
											</div>
										</div>
										<div class="points-content table-spacing" style="display: block;">
                            <p>月发展性评价星级个人</p>
                            <p class="specific-while"><span>三年级（2班）  </span>  月份：  <span>  5月份</span></p>
                            <div class="points limit-cancel" style="position: relative;">
                            <table class="responsive table  reflective-evaluate" id="data-table">
                            <thead>
                                <tr role="row">
                                   <th>序号</th>
                                   <th>姓名</th>
                                   <th>发展评价卡</th>
                                   <th>激励评价卡</th>
                                   <th>总评价卡数</th>
                                </tr>
                            </thead>
                            <tbody id="module_list_content">
                                 <tr class="">
                                     <td>1</td>
                                     <td>张小龙</td>
                                     <td>1</td>
                                     <td>1</td>
                                     <td>2</td>
                                </tr>
                                 <tr class="">
                                     <td>2</td>
                                     <td>张小龙</td>
                                     <td>3</td>
                                     <td>1</td>
                                     <td>4</td>
                                </tr>
                                 <tr class="">
                                     <td>3</td>
                                     <td>张小龙</td>
                                     <td>1</td>
                                     <td>7</td>
                                     <td>8</td>
                                </tr>
                                 <tr class="">
                                     <td>4</td>
                                     <td>张小龙</td>
                                     <td>3</td>
                                     <td>0</td>
                                     <td>3</td>
                                </tr>
                                 <tr class="">
                                     <td>5</td>
                                     <td>张小龙</td>
                                     <td>1</td>
                                     <td>1</td>
                                     <td>2</td>
                                </tr>
                                <tr class="">
                                     <td>6</td>
                                     <td>张小龙</td>
                                     <td>6</td>
                                     <td>3</td>
                                     <td>9</td>
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
															<span>月次：</span> <select></select>
														</div>
														<p class="btn_link" style="float: right; line-height: 47px; margin-right: 10px;">
														<a href="javascript:void(0)" class="a3" onclick=""><i class="fa  fa-plus"></i> 导出</a> 
														<a href="javascript:void(0)" class="a2" onclick="">评定</a>
														<a href="javascript:void(0)" class="a4" onclick="">设置</a>
													</p>
														<div class="clear"></div>
													</div>
											</div>
										</div>
										<div class="points-content table-spacing" style="display: block;">
                            <p>发展性评价年级之星</p>
                            <p class="specific-while"><span>三年级  </span>  月份：  <span>  5月份</span></p>
                            <div class="points limit-cancel" style="position: relative;">
                            <table class="responsive table table-striped reflective-evaluate" id="data-table">
                            <thead>
                                <tr role="row">
                                   <th>序号</th>
                                   <th>姓名</th>
                                   <th>年级班级</th>
                                   <th>评价总分</th>
                                </tr>
                            </thead>
                            <tbody id="module_list_content">
                                 <tr class="">
                                     <td>1</td>
                                     <td>张小龙</td>
                                     <td>二（1）班</td>
                                     <td>10</td>
                                </tr>
                                 <tr class="">
                                     <td>2</td>
                                     <td>张小龙</td>
                                     <td>二（1）班</td>
                                     <td>2</td>
                                </tr>
                                 <tr class="">
                                     <td>3</td>
                                     <td>张小龙</td>
                                     <td>二（1）班</td>
                                     <td>34</td>
                                </tr>
                                 <tr class="">
                                     <td>4</td>
                                     <td>张小龙</td>
                                     <td>二（1）班</td>
                                     <td>56</td>
                                </tr>
                                 <tr class="">
                                     <td>5</td>
                                     <td>张小龙</td>
                                     <td>二（1）班</td>
                                     <td>17</td>
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
															<span>月次：</span> <select></select>
														</div>
														<p class="btn_link" style="float: right; line-height: 47px; margin-right: 10px;">
														<a href="javascript:void(0)" class="a3" onclick=""><i class="fa  fa-plus"></i> 导出</a> 
														<a href="javascript:void(0)" class="a2" onclick="">评定</a>
														<a href="javascript:void(0)" class="a4" onclick="">设置</a>
													</p>
														<div class="clear"></div>
													</div>
											</div>
										</div>
										<div class="points-content table-spacing" style="display: block;">
                            <p>发展性评价校园之星</p>
                            <p class="specific-while"> 月份：  <span>  6月份</span></p>
                            <div class="points limit-cancel" style="position: relative;">
                            <table class="responsive table table-striped reflective-evaluate" id="data-table">
                            <thead>
                                <tr role="row">
                                   <th>序号</th>
                                   <th>姓名</th>
                                   <th>年级班级</th>
                                   <th>评价总分</th>
                                </tr>
                            </thead>
                            <tbody id="module_list_content">
                                 <tr class="">
                                     <td>1</td>
                                     <td>张小龙</td>
                                     <td>六(1)班</td>
                                     <td>31</td>
                                </tr>
                                 <tr class="">
                                     <td>2</td>
                                     <td>张小龙</td>
                                     <td>二(6)班</td>
                                     <td>28</td>
                                </tr>
                                 <tr class="">
                                     <td>3</td>
                                     <td>张小龙</td>
                                     <td>四(2)班</td>
                                     <td>21</td>
                                </tr>
                                 <tr class="">
                                     <td>4</td>
                                     <td>张小龙</td>
                                     <td>七(1)班</td>
                                     <td>19</td>
                                </tr>
                                 <tr class="">
                                     <td>5</td>
                                     <td>张小龙</td>
                                     <td>三(1)班</td>
                                     <td>11</td>
                                </tr>
                                <tr class="">
                                     <td>6</td>
                                     <td>张小龙</td>
                                     <td>三(9)班</td>
                                     <td>6</td>
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