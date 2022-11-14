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
							微课播放
							<p style="float:right;" class="btn_link">
						<a class="a4" href="javascript:void(0)"><i class="fa fa-reply"></i> 返回 </a>
					</p>
						</h3>
					</div>
					<div class="content-widgets" style="margin-bottom:0">
						<div class="video_div">
							<p class="title">平面向量垂直、共线的坐标表示</p>
							<div class="play_area_1"><img src="${pageContext.request.contextPath}/res/images/video_1.jpg" style="width:786px;height:619px;"></div>
							<p class="p1">上传教师: <span class="blue_1">张三 </span><span class="green_1">物理 > 高一年级     </span> <a href="javascript:void(0)" class="zan_over">13</a></p>
							<p class="p2">内容: <span>请同学们认真观看课前学习视频，并理解如何用慢镜回放的方法，把小作文写得具体、生动。</span></p>
						</div>
					</div>
					<div class="comment">
						<div class="pl_div">
							<div class="tab">评论</div>
							<div class="jqte">
								<textarea  maxlength="200" class="jqte_textarea" data-origin="textarea"></textarea>
							</div>
							<div class="comment-submit">
								<span>还可以输入<em id="title-counter">200</em>个字符</span> 
								<input type="button" value="评论" id="commentBtn">
							</div>
						</div>
						<div class="commentList">
							<div class="comment-title">所有评论</div>
							<div class="comment-list">
								<div class="comment-item clearfix">
									<div class="comment-img">
										<img src="http://files.jiaoxueyun.com/develop/portrait/20110914001/thjingmiao/2013/11-07/1383789334563.jpg" alt="头像" onerror="this.src='/image/login/noPhoto.jpg'">
									</div>
									<div class="comment-content">
										<h4>
											<span class="name">罗晶淼</span>2015-05-27 
											<span class="floor">3楼 </span>
										</h4>
										<div style="word-break:break-all;">老师讲的很好</div>
									</div>
								</div>
								<div class="comment-item clearfix">
									<div class="comment-img">
										<img src="http://files.jiaoxueyun.com/develop/portrait/20110914001/thjingmiao/2013/11-07/1383789334563.jpg" alt="头像" onerror="this.src='/image/login/noPhoto.jpg'">
									</div>
									<div class="comment-content">
										<h4>
											<span class="name">罗晶淼</span>2015-05-27 
											<span class="floor">2楼 </span>
										</h4>
										<div style="word-break:break-all;">老师讲的很好</div>
									</div>
								</div>
								<div class="comment-item clearfix">
									<div class="comment-img">
										<img src="http://files.jiaoxueyun.com/develop/portrait/20110914001/thjingmiao/2013/11-07/1383789334563.jpg" alt="头像" onerror="this.src='/image/login/noPhoto.jpg'">
									</div>
									<div class="comment-content">
										<h4>
											<span class="name">罗晶淼</span>2015-05-27 
											<span class="floor">1楼 </span>
										</h4>
										<div style="word-break:break-all;">老师讲的很好</div>
									</div>
								</div>
							</div>
							<div class="page">
								<ul>
								<li>
				                    	<a href="javascript:void(0)">&lt;</a>
					                </li>
									<li>
				                    	<a href="javascript:void(0)" class="on">1</a>
					                </li>
				   					<li>
				                    	<a href="javascript:void(0)">2</a>
					                </li>
					                <li>
				                    	<a href="javascript:void(0)">3</a>
					                </li>
					                <li>
				                    	<a href="javascript:void(0)">&gt;</a>
					                </li>
				                </ul>
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