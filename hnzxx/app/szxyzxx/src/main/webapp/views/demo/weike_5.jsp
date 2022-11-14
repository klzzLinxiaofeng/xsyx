<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<title>微课管理</title>
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
							微课播放
							<p style="float:right;" class="btn_link">
						<a class="a4" onclick="$.refreshWin();" href="javascript:void(0)"><i class="fa  fa-undo"></i>返回</a>
					</p>
						</h3>
					</div>
					<div class="play_area">
						<div class="p_detail">
							<div class="title">平面向量垂直、共线的坐标表示</div>
							<div class="play_1">
								<div class="p_left"><img src="${pageContext.request.contextPath}/res/images/video_1.jpg"></div>
								<div class="p_right">
									<ul>
										<li>
											<a href="javascript:void(0)"><img src="${pageContext.request.contextPath}/res/images/video_1.jpg"></a>
											<p class="de_1">学习完毕</p>
											<p class="de_2"><a href="javascript:void(0)">平面向量垂直、共线的坐标表示</a></p>
											<p class="de_3">数学  &gt; 初一</p>
										</li>
										<li>
											<a href="javascript:void(0)"><img src="${pageContext.request.contextPath}/res/images/video_1.jpg"></a>
											<p class="de_1">02</p>
											<p class="de_2"><a href="javascript:void(0)">平面向量垂直、共线的坐标表示</a></p>
											<p class="de_3">数学  &gt; 初一</p>
										</li>
										<li>
											<a href="javascript:void(0)"><img src="${pageContext.request.contextPath}/res/images/video_1.jpg"></a>
											<p class="de_1">03</p>
											<p class="de_2"><a href="javascript:void(0)">平面向量垂直、共线的坐标表示</a></p>
											<p class="de_3">数学  &gt; 初一</p>
										</li>
										
									</ul>
								</div>
								<div class="clear"></div>
							</div>
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