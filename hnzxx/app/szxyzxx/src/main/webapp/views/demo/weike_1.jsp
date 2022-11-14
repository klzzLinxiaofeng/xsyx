<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/views/embedded/common.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${sca:getDefaultSchoolName()}</title>
<style type="text/css">
.form-horizontal .control-label{width:30px;}
.form-horizontal .controls {
    margin-left: 40px;
}
</style>
</head>
<body>
	<div class="container-fluid">
	<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-calculator" name="icon"/>
			<jsp:param value="布置微课" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid ">
			<div class="span12">
				<div class="content-widgets white" style="margin-bottom: 0;">
					<div class="widget-container">
					<form id="dept_form" class="form-horizontal" novalidate="novalidate">
						<div class="control-group">
							<label class="control-label">
								时间
							</label>
							<div class="controls">
								<input type="text" value="" placeholder="2015-01-22" class="span2">
								<select class="span1"><option>1</option><option>2</option><option>3</option><option>4</option><option>5</option><option>6</option><option>7</option><option>8</option><option>9</option><option>10</option><option>11</option><option>12</option></select>
								<span style="margin:0 10px 0 5px;">点</span><span style="margin:0 5px 0 10px;">至</span>
								<input type="text" value="" placeholder="2015-01-22" class="span2">
								<select class="span1"><option>1</option><option>2</option><option>3</option><option>4</option><option>5</option><option>6</option><option>7</option><option>8</option><option>9</option><option>10</option><option>11</option><option>12</option></select>
								<span style="margin:0 10px 0 5px;">点</span>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">班级</label>
							<div class="controls">
							<div class="banji">
								<ul class="nj">
									<li><a href="javascript:void(0)">三年级</a></li>
									<li><a href="javascript:void(0)">四年级</a></li>
									<li><a href="javascript:void(0)">小组</a></li>
								</ul>
								<div class="clear"></div>
								<div class="fenban" style="display:none;">
										<ul>
											<li><input type="checkbox">全部</li>
											<li><input type="checkbox">5班</li>
											<li><input type="checkbox">6班</li>
											<li><input type="checkbox">8班</li>
										</ul>
								</div>
								<div class="tishi" >选择您要布置的年纪、班级或小组</div>
							</div>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">微课</label>
							<div class="controls">
							<div class="banji">
								<ul class="nj">
									<li><a href="javascript:void(0)">微课库</a></li>
									<li><a href="javascript:void(0)">本地上传</a></li>
									<li><a href="javascript:void(0)">我的云网盘</a></li>
								</ul>
								<div class="ts" style="display:none;">
									<!-- <p class="yx">已选微课<span>2</span></p> -->
									<div class="yx_weike">
										<div class="yx_num"><a href="javascript:void(0)" class="close_all" style="display:none;"><i class="fa fa-trash-o"></i>清空</a><p class="yx"><a href="javascript:void(0)">已选微课<span>2</span></a></p></div>
										<div class="yx_wk" style="display:none">
											<ul>
												<li><a href="javascript:void(0)" class="name">关于分数乘法的学习微课</a><p>来自公共微课库</p><a href="javascript:void(0)" class="close_1"></a></li>
												<li><a href="javascript:void(0)" class="name">分数乘法课后练习题</a><p>来自我的微课</p><a href="javascript:void(0)" class="close_1"></a></li>
											</ul>
										</div>
									</div>
									<button class="btn btn-danger">布置已选微课</button>
								</div>
								<div class="clear"></div>
								<div class="weike" style="display:none;">
								<div class="a1" style="display:none;">
								<div class="blue"></div>
									<div class="unit-area">
										<div class="hd">
											<h1 style="text-indent: 0;">
														数学六年级上人教版
												<br>
													<a href="javascript:void(0)" class="qiehuan">切换教材</a>
											</h1>
										</div>
										<div class="bd jspScrollable">
											<div class="jspContainer">
												<div class="jspPane">
													<ul class="unit-list mb20">
														<li class="unit-item"><a href="javaScript:void(0);"  class="unit-link strong ib" title="全部微课"><b>全部微课</b></a></li>
														<li  class="unit-item"><a href="javaScript:;" class="unit-link strong ib" title="第一单元"><b>第一单元</b></a>
															<ul class="lesson-list">
																<li  class="lesson-item"><a title="看图说话学拼音" href="javaScript:;" class="lesson-link ib pl5 pt5">看图说话学拼音</a></li>
																<li  class="lesson-item"><a title="识字一" href="javaScript:;" class="lesson-link ib pl5 pt5">识字一</a></li>
																<li  class="lesson-item"><a title="我爱上学" href="javaScript:;" class="lesson-link ib pl5 pt5">我爱上学</a></li>
																<li  class="lesson-item"><a title="语文百花园一" href="javaScript:;" class="lesson-link ib pl5 pt5">语文百花园一</a></li>
															</ul>
														</li>
														<li  class="unit-item"><a href="javaScript:;" class="unit-link strong ib" title="第一单元"><b>第一单元</b></a>
															<ul class="lesson-list">
																<li  class="lesson-item"><a title="看图说话学拼音" href="javaScript:;" class="lesson-link ib pl5 pt5">看图说话学拼音</a></li>
																<li  class="lesson-item"><a title="识字一" href="javaScript:;" class="lesson-link ib pl5 pt5">识字一</a></li>
																<li  class="lesson-item"><a title="我爱上学" href="javaScript:;" class="lesson-link ib pl5 pt5">我爱上学</a></li>
																<li  class="lesson-item"><a title="语文百花园一" href="javaScript:;" class="lesson-link ib pl5 pt5">语文百花园一</a></li>
															</ul>
														</li>
													</ul>
												</div>
											</div>
										</div>
									</div>
									<div class="main">
										<div class="top">
											<p class="left">全部微课（20,206个）</p>
											<p class="right">排序：　<a href="javascript:void(0)" class="on">评分<i class="fa fa-arrow-down"></i></a><a href="javascript:void(0)">浏览量<i class="fa fa-arrow-down"></i></a><a href="javascript:void(0)">最新<i class="fa fa-arrow-down"></i></a></p>
										</div>
										<div class="doc-list-area">
											<dl>
												<dt><img src="${ctp}/res/images/school.jpg"></dt>
												<dd>
													<div class="item-msg">
														<div class="top_1">
															<div class="item-title"><a href="javascript:void(0)">关于分数乘法的学习微课</a></div>
															<div><span>教材目录：2 分数乘法 分数乘法</span></div>
															<div><span>上传时间：2015-1-22</span></div>
														</div>
														<div  class="item-comment">
															<span>349人浏览过</span>
															<span class="small-rating"><span style="width:80.0%"></span></span>
															<span class="fen">4</span>
															<span class="ren">（49人评价）</span>
														</div>
													</div>
													<div class="select"><input type="checkbox">选择</div>
												</dd>
											</dl>
											<dl>
												<dt><img src="${ctp}/res/images/school.jpg"></dt>
												<dd>
													<div class="item-msg">
														<div class="top_1">
															<div class="item-title"><a href="javascript:void(0)">关于分数乘法的学习微课</a></div>
															<div><span>教材目录：2 分数乘法 分数乘法</span></div>
															<div><span>上传时间：2015-1-22</span></div>
														</div>
														<div  class="item-comment">
															<span>349人浏览过</span>
															<span class="small-rating"><span style="width:80.0%"></span></span>
															<span class="fen">4</span>
															<span class="ren">（49人评价）</span>
														</div>
													</div>
													<div class="select"><input type="checkbox">选择</div>
												</dd>
											</dl>
											<dl>
												<dt><img src="${ctp}/res/images/school.jpg"></dt>
												<dd>
													<div class="item-msg">
														<div class="top_1">
															<div class="item-title"><a href="javascript:void(0)">关于分数乘法的学习微课</a></div>
															<div><span>教材目录：2 分数乘法 分数乘法</span></div>
															<div><span>上传时间：2015-1-22</span></div>
														</div>
														<div  class="item-comment">
															<span>349人浏览过</span>
															<span class="small-rating"><span style="width:80.0%"></span></span>
															<span class="fen">4</span>
															<span class="ren">（49人评价）</span>
														</div>
													</div>
													<div class="select"><input type="checkbox">选择</div>
												</dd>
											</dl>
										</div>
									</div>
									<div class="clear"></div>
								</div>
								<div class="a2" style="display:none">
									<div class="blue"></div>
									<div class="upload_wk" style="display:none;">
										<div class="u_wk"><a href="javascript:void(0)"></a></div>
									</div>
									<div class="ul_wk">
										<div class="u_message">
											<div class="title">第一章 第一节 一元二次函数.mp4</div>
											<div class="jindu">
												<div class="ddt">
													<div class="i" style="width:100%"></div>
												</div>
												<span>100%</span>
												<a class="yes" href="javascript:void(0)">确认上传</a>
												<a class="no" href="javascript:void(0)">取消上传</a>
											</div>
											<div class="sudu">
												<span class="s1">上传速度：100k/s</span>
												<span class="s1">已上传：100M/100M</span>
												<span class="s1">剩余时间：03:00</span>
											</div>
										</div>
										<div class="u_wk_message">
											<div class="title">微课信息</div>
												<div class="control-group">
													<label class="control-label" style="width:80px;">
														标题：
													</label>
													<div class="controls" style="margin-left:100px;">
													<input type="text" value="" placeholder="微课标题" class="span5" style="width:550px;">
													</div>
												</div>
												<div class="control-group">
													<label class="control-label" style="width:80px;">
														内容：
													</label>
													<div class="controls" style="margin-left:100px;">
													<textarea class="span5" style="width:550px;"></textarea>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label" style="width:80px;">
														知识点：
													</label>
													<div class="controls" style="margin-left:100px;">
													<select style="width:134px;"><option>五年级</option><option>六年级</option></select>
													<select style="width:134px;"><option>语文</option><option>数学</option></select>
													<select style="width:134px;"><option>上册</option><option>下册</option></select>
													<select style="width:134px;"><option>人教版</option><option>北师大</option></select>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label" style="width:80px;">
														关键字：
													</label>
													<div class="controls" style="margin-left:100px;">
													<input type="text" value="" placeholder="微课" class="span5" style="width:550px;">
													</div>
												</div>
										</div>
									</div>
								</div>
								<div class="a3" style="display:none;">
									<div class="blue"></div>
									<div class="my_weike">
										<div class="doc-list-area">
											<dl>
												<dt><img src="${ctp}/res/images/school.jpg"></dt>
												<dd>
													<div class="item-msg">
														<div class="top_1">
															<div class="item-title"><a href="javascript:void(0)">关于分数乘法的学习微课</a></div>
															<div><span>教材目录：2 分数乘法 分数乘法</span></div>
															<div><span>上传时间：2015-1-22</span></div>
														</div>
														<div  class="item-comment">
															<span>349人浏览过</span>
															<span class="small-rating"><span style="width:80.0%"></span></span>
															<span class="fen">4</span>
															<span class="ren">（49人评价）</span>
														</div>
													</div>
													<div class="select"><input type="checkbox">选择</div>
												</dd>
											</dl>
											<dl>
												<dt><img src="${ctp}/res/images/school.jpg"></dt>
												<dd>
													<div class="item-msg">
														<div class="top_1">
															<div class="item-title"><a href="javascript:void(0)">关于分数乘法的学习微课</a></div>
															<div><span>教材目录：2 分数乘法 分数乘法</span></div>
															<div><span>上传时间：2015-1-22</span></div>
														</div>
														<div  class="item-comment">
															<span>349人浏览过</span>
															<span class="small-rating"><span style="width:80.0%"></span></span>
															<span class="fen">4</span>
															<span class="ren">（49人评价）</span>
														</div>
													</div>
													<div class="select"><input type="checkbox">选择</div>
												</dd>
											</dl>
											<dl>
												<dt><img src="${ctp}/res/images/school.jpg"></dt>
												<dd>
													<div class="item-msg">
														<div class="top_1">
															<div class="item-title"><a href="javascript:void(0)">关于分数乘法的学习微课</a></div>
															<div><span>教材目录：2 分数乘法 分数乘法</span></div>
															<div><span>上传时间：2015-1-22</span></div>
														</div>
														<div  class="item-comment">
															<span>349人浏览过</span>
															<span class="small-rating"><span style="width:80.0%"></span></span>
															<span class="fen">4</span>
															<span class="ren">（49人评价）</span>
														</div>
													</div>
													<div class="select"><input type="checkbox">选择</div>
												</dd>
											</dl>
										</div>
									</div>
								</div>
								</div>
								<div class="tishi" >选择您的微课资源</div>
							</div>
							</div>
						</div>
					</form>
				</div>
				</div>
			</div>
		</div>
	</div>
	<script>
		$(function(){
			$(".form-horizontal .controls .banji .nj li a").click(function(){
				$(this).parent().siblings().removeClass("active");
				$(this).parent().addClass("active");
				$(this).parent().parent().siblings(".fenban,.weike,.ts").show();
				$(this).parent().parent().siblings(".tishi").hide();
				var i=$(this).parent().index();
				$(this).parent().parent().siblings(".weike").children().hide();
				$(this).parent().parent().siblings(".weike").children().eq(i).show();
			});
			$(".form-horizontal .controls .banji .ts .yx a").click(function(){
				$(".form-horizontal .controls .banji .ts .yx_weike").css("width","300px");
				$(".form-horizontal .controls .banji .ts .close_all").show();
				$(".form-horizontal .controls .banji .ts .yx_weike .yx_wk").show();
			});
			$(".form-horizontal .controls .banji .ts .yx_weike").click(function(){
				return false;
			});
			$(document).click(function(){
				$(".form-horizontal .controls .banji .ts .close_all").hide();
				$(".form-horizontal .controls .banji .ts .yx_weike .yx_wk").hide();
				$(".form-horizontal .controls .banji .ts .yx_weike").css("width","117px");
			})
		});
	</script>
</body>
</html>
