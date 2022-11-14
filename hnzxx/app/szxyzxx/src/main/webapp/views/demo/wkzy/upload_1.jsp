<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<title>学校列表</title>
</head>
<body>
	<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon"/>
			<jsp:param value="微课管理" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>
							上传微课
							<p style="float:right;" class="btn_link">
						<a class="a4" href="javascript:void(0)"><i class="fa fa-reply"></i>返回我的微课</a>
					</p>
						</h3>
					</div>
					<div class="content-widgets" style="margin-bottom:0">
						<div style="display:block;" class="upload_wk_1">
							<div class="u_wk_1"><a href="javascript:void(0)"></a></div>
						</div>
						<div class="form-horizontal" style="padding-bottom:0"><div class="controls" style="margin-left:0"><div class="weike" style="border:0 none;">
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
							<div class="u_wk_message" >
							<div class="u_wk_left">
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
										<textarea class="span5" style="width:550px;height:120px;"></textarea>
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
								<div class="u_wk_right">
									<div class="title">视频预览</div>
									<div class="video_show"></div>
								</div>
								<div class="clear"></div>
							</div>
							</div></div></div>
						</div>
						</div>
					</div>
				</div>
			</div>
		</div>
</body>
<script type="text/javascript">
</script>
</html>