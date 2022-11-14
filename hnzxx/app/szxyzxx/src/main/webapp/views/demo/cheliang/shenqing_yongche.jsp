<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/css/extra/oa.css" rel="stylesheet">
<title>车辆</title>
<style type="text/css">
	.row-fluid .span4{
		width:220px;
	}
</style>
<script type="text/javascript">
$(function(){
	$(".yc_sq .a_1 a").click(function(){
		$(".xzcl").show();
	});
	$(".yc_sq .a_2 a").click(function(){
		$(".xzcl").show();
	});
	$(".xzcl .c_top .c1 .close_1,.xzcl .cl_bottom .btn-cancel").click(function(){
		$(".xzcl").hide();
	})
	$(".xzcl .cl_list ul li .ky").click(function(){
		$(".xzcl .cl_list ul li .btn-cz").removeClass("btn-click");
		$(this).addClass("btn-click");
	});
	$(".xzcl .cl_bottom .btn-blue").click(function(){
		if($(".xzcl .cl_list ul li .btn-cz").hasClass("btn-click")){
			var c_name=$(".btn-click").prev().children(".p2").text();
			var c_pai=$(".btn-click").prev().children().children(".chepai").text();
			var cheliang=c_name+''+'['+c_pai+']'
			$(".yc_sq .a_1").hide();
			$(".yc_sq .a_2").show();
			$(".yc_sq .a_2 span").text(cheliang);
			$(".xzcl").hide();
		}
	})
});
</script>
</head>
<body>
<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon"/>
			<jsp:param value="车辆" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="oa_top">
						<ul class="top_ul">
				            <li><a href="javascript:void(0)" class="on">申请用车</a></li>
				        </ul>
					</div>
					<div class="yc_sq">
						<form class="form-horizontal" action="javascript:void(0);">
							<div class="control-group">
								<label class="control-label">标题：</label>
								<div class="controls">
									<input type="text" class="span8 left_red" placeholder="请输入标题，少于40个中文字符" />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">发布时间：</label>
								<div class="controls">
									<input type="text" class="span4" placeholder="留空是当前时间"   onclick="WdatePicker();" />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">申请车辆：</label>
								<div class="controls">
									<div class="add_cheliang">
										<div  class="a_1">
											<a href="javascript:void(0)" class="add_car">添加</a>
										</div>
										<div class="a_2" style="display:none;">
											<span></span>
											<a href="javascript:void(0)" class="edit_car">修改</a>
										</div>
										<div class="xzcl" style="display:none;">
											<div class="c_top">
												<div class="c1">
													<p>选择车辆</p>
													<a class="close_1" href="javascript:void(0)"><i class="fa  fa-times"></i></a>
												</div>
												<input type="text" placeholder="输入车辆名称或车牌号" />
											</div>
											<div class="cl_list">
												<ul>
													<li>
														<div class="touxiang"> 
															<img src="${pageContext.request.contextPath}/res/images/cheliang.jpg">
														</div>
														<div class="detail">
															<div class="p2">黄色小型接送巴士1</div>
															<div class="p3"><i class="fa fa-users"></i><p class="p_div">荷载人数</p><span>20人</span></div>
															<div class="p3"><i class="fa  fa-money"></i><p class="p_div">车牌号</p><span class="chepai">粤A123456</span></div>
															<div class="p3"><i class="fa fa-exclamation-triangle"></i><p class="p_div">当前状态</p><span class="keyong">可用</span>
															</div>
														</div>
														<a href="javascript:void(0)" class=" ky btn-cz"></a>
														<div class="clear"></div>
													</li>
													<li>
														<div class="touxiang"> 
															<img src="${pageContext.request.contextPath}/res/images/cheliang.jpg">
														</div>
														<div class="detail">
															<div class="p2">黄色小型接送巴士2</div>
															<div class="p3"><i class="fa fa-users"></i><p class="p_div">荷载人数</p><span>20人</span></div>
															<div class="p3"><i class="fa  fa-money"></i><p class="p_div">车牌号</p><span class="chepai">粤A123457</span></div>
															<div class="p3"><i class="fa fa-exclamation-triangle"></i><p class="p_div">当前状态</p><span class="keyong">可用</span>
															</div>
														</div>
														<a href="javascript:void(0)" class="ky btn-cz"></a>
														<div class="clear"></div>
													</li>
													<li>
														<div class="touxiang"> 
															<img src="${pageContext.request.contextPath}/res/images/cheliang.jpg">
														</div>
														<div class="detail">
															<div class="p2">黄色小型接送巴士3</div>
															<div class="p3"><i class="fa fa-users"></i><p class="p_div">荷载人数</p><span>20人</span></div>
															<div class="p3"><i class="fa  fa-money"></i><p class="p_div">车牌号</p><span class="chepai">粤A123458</span></div>
															<div class="p3"><i class="fa fa-exclamation-triangle"></i><p class="p_div">当前状态</p><span class="buke">使用中</span>
															</div>
														</div>
														<a href="javascript:void(0)" class="bky btn-cz"></a>
														<div class="clear"></div>
													</li>
													<li>
														<div class="touxiang"> 
															<img src="${pageContext.request.contextPath}/res/images/cheliang.jpg">
														</div>
														<div class="detail">
															<div class="p2">黄色小型接送巴士4</div>
															<div class="p3"><i class="fa fa-users"></i><p class="p_div">荷载人数</p><span>20人</span></div>
															<div class="p3"><i class="fa  fa-money"></i><p class="p_div">车牌号</p><span class="chepai">粤A123459</span></div>
															<div class="p3"><i class="fa fa-exclamation-triangle"></i><p class="p_div">当前状态</p><span class="buke">使用中</span>
															</div>
														</div>
														<a href="javascript:void(0)" class="bky btn-cz"></a>
														<div class="clear"></div>
													</li>
												</ul>
											</div>
											<div class="cl_bottom">
												<button class="btn btn-blue">确定</button>
												<button class="btn btn-cancel">取消</button>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">使用时间：</label>
								<div class="controls">
									<input type="text" class="span4 left_red" placeholder="开始时间"  onclick="WdatePicker();" /> &nbsp; 至   &nbsp; <input type="text" class="span4 left_red" placeholder="结束时间"   onclick="WdatePicker();" />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">备注：</label>
								<div class="controls">
									<textarea style="" class="span8"></textarea>
								</div>
							</div>
							<div class="caozuo" style="text-align:center;">
								<button class="btn btn-success">发布</button> <button class="btn">预览</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>