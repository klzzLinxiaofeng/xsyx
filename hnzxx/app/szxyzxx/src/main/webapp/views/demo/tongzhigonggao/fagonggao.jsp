<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/css/extra/oa.css" rel="stylesheet">
<title>文印</title>
<style type="text/css">
	.row-fluid .span4{
		width:220px;
	}
	input[type="radio"]{
		margin:0 5px;
		position:relative;
		top:-1px;
	}
</style>
<script type="text/javascript">
	$(function(){
		$(".people_select .c_middle .sz_left a").click(function(){
			$(".people_select .c_middle .sz_left a").removeClass("on");
			$(this).addClass("on");
			var i=$(this).index();
			$(".people_select .c_middle #ry_left>div").hide();
			$(".people_select .c_middle #ry_left>div").eq(i).show();
		});
		$(".people_select .c_middle ").on("click","#ry_left ul li a",function(){
			var li_html=$(this).parent();
			$(".people_select .c_middle .ry_right ul").append(li_html);
			var l=$(".people_select .c_middle .ry_right ul li").length;
			$(".people_select .c_middle .ry_right .yx").text('已选 '+l+'人')
		});
		$(".people_select .c_middle").on("click",".ry_right ul li a",function(){
			var li_html=$(this).parent();
			$(".people_select .c_middle #ry_left ul").append(li_html);
			var l=$(".people_select .c_middle .ry_right ul li").length;
			$(" .c_middle .ry_right .yx").text('已选 '+l+'人')
		})
		$(".people_select .cl_bottom .btn-blue").click(function(){
			var l=$(".people_select .c_middle .ry_right ul li").length;
			if(l!=0){
				for(k=0;k<l;k++){
					var a_html="<a href='javascript:void(0)'><i class='fa  fa-close'></i></a>";
					$(".s_select .d2 ul").append('<li>'+$('.ry_right ul li ').eq(k).children().children('.name').text()+a_html+'</li>');
					$(".s_select .d1").hide();
					$(".s_select .d2").show();
				}
				$(".people_select .c_middle .ry_right ul li").remove();
				$(".people_select .c_middle .ry_right .yx").text('已选 0人');
				$(".people_select").hide();
			}
		});
		$(".s_select .d1 a,.s_select .d2 a").click(function(){
			$(".people_select").show();
		});
		$(".d2 ul").on("click","li a",function(){
			$(this).parent().remove();
		})
		$(".people_select .c_top .c1 .close_1,.btn-cancel").click(function(){
			$(".people_select").hide();
		});
		
		/* 部门选择 */
		$(".bmxz .bm_top").click(function(){
			if($(".bm_bottom").is(":hidden")){
				$(".bmxz .bm_bottom").show();
				$(this).children("b").removeClass("fa-caret-down").addClass("fa-caret-up");
			}else{
				$(".bmxz .bm_bottom").hide();
				$(this).children("b").removeClass("fa-caret-up").addClass("fa-caret-down");
			}
		});
		$(".bm_bottom .queren button").click(function(){
			$("input[name='bumen']:checked").each(function (){
				var a_html="<a href='javascript:void(0)'><i class='fa  fa-close'></i></a>";
				/* alert($(".bm_select .d2 ul li span").text()); */
				var i=$(".bm_select .d2 ul li").length;
				for(var k=0;k<i;k++){
					var span_html=$(".bm_select .d2 ul li").eq(k).children("span").text();
					if(span_html==this.value){
						$(".bm_select .d2 ul li").eq(k).remove();
					}
				}
				$(".bm_select .d2 ul").append('<li><span>'+this.value+'</span>'+a_html+'</li>');
            });
			$(".bmxz .bm_bottom").hide();
			$(".bmxz .bm_top").children("b").removeClass("fa-caret-up").addClass("fa-caret-down");
		})
		
	});
</script>
</head>
<body>
<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon"/>
			<jsp:param value="公告" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="oa_top">
						<ul class="top_ul">
				            <li><a href="javascript:void(0)" class="on">发通知</a></li>
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
								<label class="control-label">作者：</label>
								<div class="controls">
									<input type="text" class="span4 left_red" placeholder="作者，少于40个中文字符" />
								</div>
							</div>
							<div class="control-group content">
								<label class="control-label">发布日期：</label>
								<div class="controls">
									<input type="text" class="span4" placeholder="留空是当前时间"   onclick="WdatePicker();" />
								</div>
							</div>
							<div class="control-group content">
								<label class="control-label">摘要：</label>
								<div class="controls">
								<textarea class="span8 left_red"></textarea>
								</div>
							</div>
							<div class="control-group content">
								<label class="control-label">正文：</label>
								<div class="controls">
									<textarea style="" class="span8"></textarea>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">附件：</label>
								<div class="controls">
									<button class="btn"><i class="fa fa-upload"></i>上传附件</button>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">发布对象：</label>
								<div class="controls" style="padding-top:5px;">
									<input type="radio" name="daike" />全员
									<input type="radio" name="daike" />
									<div style="display:inline-block"><span >部门</span>
									<div class="bm_select">
									<div class="d2" style="float:left"><ul style="float:left"></ul></div>
										<div class=" bmxz" style="float:left">
											<div class="bm_top">请选择部门 <b class="fa fa-caret-down"></b></div>
											<div class="bm_bottom" style="display:none">
												<ul>
													<li>财务部<input type="checkbox" name="bumen" value="财务部"></li>
													<li>教育部<input type="checkbox" name="bumen" value="教育部"></li>
													<li>行政部<input type="checkbox" name="bumen" value="行政部"></li>
													<li>体育部<input type="checkbox" name="bumen" value="体育部"></li>
													<li>会计部<input type="checkbox" name="bumen" value="会计部"></li>
												</ul>
												<div class="queren"><button class="btn btn-warning">确认</button></div>
											</div>
										</div>
									</div>
									</div>
									<input type="radio" name="daike" />个人
									<div class="s_select" style="position:relative;display:inline-block;">
								<div class="d1" ><a href="javascript:void(0)">选择代课老师</a></div>
								<div class="d2" style="display:none;position:relative;top:5px;"><ul style="float:left"></ul><a href="javascript:void(0)" style="float:left">添加</a><div class="clear"></div></div>
									<div class="people_select" style="display:none;">
										<div class="c_top">
											<div class="c1">
												<p>选择人员</p>
												<a href="javascript:void(0)" class="close_1"><i class="fa  fa-times"></i></a>
											</div>
											<input type="text" placeholder="输入同事姓名或手机号">
										</div>
										<div class="c_middle">
											<div class="p_left">
												<div class="sz sz_left">
													<a href="javascript:void(0)" class="on">常用</a>
													<a href="javascript:void(0)">搜索</a>
													<a href="javascript:void(0)">部门</a>
												</div>
												
												<div class="ry" id="ry_left">
												<div >
													<p class="yx">全部  3人</p>
													<ul>
														<li>
															<a href="javascript:void(0)">
																<img src="${pageContext.request.contextPath}/res/images/noPhoto.jpg" />
																<span class="name">刘艳青</span>
																<span class="phone">13800138000</span>
															</a>
														</li>
														<li>
															<a href="javascript:void(0)">
																<img src="${pageContext.request.contextPath}/res/images/noPhoto.jpg" />
																<span class="name">陈文光</span>
																<span class="phone">13800138000</span>
															</a>
														</li>
														<li>
															<a href="javascript:void(0)">
																<img src="${pageContext.request.contextPath}/res/images/noPhoto.jpg" />
																<span class="name">罗之宝</span>
																<span class="phone">13800138000</span>
															</a>
														</li>
														<li>
															<a href="javascript:void(0)">
																<img src="${pageContext.request.contextPath}/res/images/noPhoto.jpg" />
																<span class="name">罗志明</span>
																<span class="phone">13800138000</span>
															</a>
														</li>
														<li>
															<a href="javascript:void(0)">
																<img src="${pageContext.request.contextPath}/res/images/noPhoto.jpg" />
																<span class="name">周津</span>
																<span class="phone">13800138000</span>
															</a>
														</li>
														<li>
															<a href="javascript:void(0)">
																<img src="${pageContext.request.contextPath}/res/images/noPhoto.jpg" />
																<span class="name">王志家</span>
																<span class="phone">13800138000</span>
															</a>
														</li>
													</ul>
													</div>
													<div style="display:none">
													<p class="yx">全部  1人</p>
													<ul>
														<li>
															<a href="javascript:void(0)">
																<img src="${pageContext.request.contextPath}/res/images/noPhoto.jpg" />
																<span class="name">潘伟良</span>
																<span class="phone">13800138000</span>
															</a>
														</li>
														
													</ul>
													</div>
													<div style="display:none">
													<p class="yx">全部  2人</p>
													<ul >
														<li>
															<a href="javascript:void(0)">
																<img src="${pageContext.request.contextPath}/res/images/noPhoto.jpg" />
																<span class="name">晶淼</span>
																<span class="phone">13800138000</span>
															</a>
														</li>
														<li>
															<a href="javascript:void(0)">
																<img src="${pageContext.request.contextPath}/res/images/noPhoto.jpg" />
																<span class="name">何菊英</span>
																<span class="phone">13800138000</span>
															</a>
														</li>
													</ul>
													</div>
												</div>
											</div>
											<div class="p_middle"></div>
											<div class="p_right">
												<div class="sz">
													<a href="javascript:void(0)">已选人员</a>
												</div>
												<div class="ry ry_right">
												<div>
													<p class="yx">已选 0人</p>	
													<ul>
													</ul>
												</div>
												</div>
											</div>
										</div>
										<div class="cl_bottom">
												<button class="btn btn-blue">确定</button>
												<button class="btn btn-cancel">取消</button>
											</div>
									</div>
								</div>
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