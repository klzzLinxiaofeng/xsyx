<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${ctp}/res/css/extra/add.css" rel="stylesheet">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/res/css/extra/zuoweibiao.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/res/plugin/falgun/js/jquery.easyui.min.js"></script>
<title></title>
</head>
<style>
.install {
	height: 50px;
	float: left;
	width: 100%;
}

.install button i {
	margin-right: 8px;
}

.fa-pencil {
	margin-left: 8px;
}

.install p {
	font-size: 12px;
	color: #333;
	line-height: 30px;
	text-align: center;
	margin-top: 10px;
}

.setting {
	background: #f0f0f0;
	margin-top: 20px;
}

.nj a.on {
	background: #0d7bd5;
}

.bj a.on {
	background: #0d7bd5;
}

.add {
	float: right;
	background: #47b9f4;
	width: 60px;
	line-height: 30px;
	color: #fff;
	text-align: center;
	position: relative;
	top: -40px;
	right: 16px;
	margin: 0 0 -20px 0px;
	border: 0px;
}

.js a.on {
	background: #0d7bd5;
}

.position {
	width: 100%;
	padding-bottom: 30px;
}

.left {
	min-height: 433px;
	background: #fff;
	border: 1px #d9dfe7 solid;
	margin: 0px 329px 0 15px;
	display: block;
}

.left span {
	text-align: center;
}

.left span p {
	background: #428bca;
	font-size: 12px;
	width: 26%;
	border-radius: 5px;
	-moz-border-radius: 5px;
	-webkit-border-radius: 5px;
	line-height: 45px;
	color: #fff;
	margin: auto;
	margin-top: 20px;
}

.left ul {
	margin: 15px 0 0 0;
	width: 100%;
}

.left ul li {
	text-align: center;
	margin-bottom: 10px;
	height: 30px;
	line-height: 35px;
}

.left ul li p {
	max-width: 100px;
}

.left ul li a {
	background: #ccc;
	line-height: 30px;
	width: 9.6%;
	font-size: 12px;
	color: #fff;
	display: inline-block;
	margin-left: 2%;
	height: 30px;
	overflow: hidden;
}

.left ul li a {
	*display: inline;
}

.male {
	background: #47b9f4;
}

.female {
	background: #f64cae;
}

.you {
	min-height: 433px;
	width: 288px;
	background: #fff;
	border: 1px #d9dfe7 solid;
	float: right;
	margin-right: 20px;
}

.you span {
	height: 30px;
	background: #f5f5f5;
	float: left;
	width: 100%;
	border-bottom: 1px #d4d4d4 solid;
}

.you span p {
	color: #6d6e6f;
	font-size: 12px;
	margin: 0 0 0 10px;
	line-height: 30px;
	float: left;
}

.you span p.surplus {
	float: right;
	margin-right: 10px;
}

strong.number {
	color: #f44646;
	margin: 0 5px 0 5px;
}

.right {
	height: 400px;
	overflow-x: hidden;
	width: 100%;
}

.right ul {
	margin: 0;
	padding: 0;
}

.right p {
	color: #b3defa;
	display: block;
	overflow: hidden;
	text-overflow: ellipsis;
	margin: 0;
}

.item {
	height: 40px;
	color: #fff;
	font-size: 12px;
	text-align: center;
	display: block;
	text-overflow: ellipsis;
	overflow: hidden;
	white-space: nowrap;
}

.item p {
	/* 	width:77px; */
	
}

.assigned {
	overflow: hidden;
	height: 30px;
	margin-top: -3px;
	padding-top: 3px;
	text-align: center;
	text-overflow: ellipsis;
}

.right ul li {
	margin: 10px 0 0 10px;
	padding-top: 5px;
	width: 80px;
	height: 45px;
}

.right .item {
	float: left;
}

.white {
	border: 0;
}

.set {
	/*max-width: 100px;*/
	
}

.left ul li .d {
	margin-left: 4%;
}

.left ul li .d p {
	width: 77px;
}

.left ul li span .l {
	margin: 0 1px 0 0;
	border-radius: 5px 0 0 5px;
}

.left ul li span .r {
	margin: 0;
	border-radius: 0 5px 5px 0;
}

.choice ul li {
	min-height: 50px;
	display: block;
	clear: both;
	float: left;
	width: 100%;
}

.choice {
	float: left;
	width: 100%;
}

.position {
	float: left;
}

.bj {
	min-height: 50px;
}

.item img {
	border-radius: 50%;
	width: 30px;
	margin-top: 4px;
}

.item p {
	margin: 0 8px 0 0;
	color: #fff;
	float: right;
}

#student li img {
	width: 30%;
	margin: 5px 0px 0 5px;
	float: left;
}

#student li p {
	margin: 7px 0 0 0;
	float: right;
	width: 50px;
	text-align: center;
}

#seat li a li img {
	width: 20px;
	float: left;
	margin: 5px 0 0 5px;
}

#seat li a li p {
	float: left;
	margin: -2px 0 0 0;
	display: block;
	white-space: nowrap;
	overflow: hidden;
	text-overflow: ellipsis;
	width: 55px;
}

.choice {
	min-height: 45px;
	background: #f0f0f0;
	border-bottom: 1px #ccc solid;
}

#team_chzn {
	margin: 10px;
}

.cishu {
	height: 45px;
	background: #fff;
}

.cishu p {
	color: #333;
	line-height: 45px;
	font-size: 14px;
	font-family: 微软雅黑;
	margin: 0;
}

.shu {
	color: #00a0e9;
	font-family: '微软雅黑';
	font-size: 18px;
	font-weight: bold;
	margin-right: 5px;
}

.zp {
	float: left;
	margin: 15px 18px 0 18px;
}
</style>
<body>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<!-- <div class="widget-head">
						<h3>座位表</h3>
					</div> -->
					<div class="setting">
						<div>
							<p style="float: left; margin: 0 0 0 15px; line-height: 40px;">${teamName }</p>
							<button class="btn btn-blue" onclick="back()"
								style="float: right; margin-top: 5px; background: #428bca;">返回</button>
						</div>
						<div class="clear"></div>
						<div
							style="background: #fff; width: 100%; float: left; margin-top: 20px;">
							<div class="install">
								<p id="className">${teamName }普通教室</p>
							</div>
							<div class="position">
								<div class="left" style="width: 840px; margin: 0 auto;">
									<span><p>讲台</p></span>
									<ul id="seat">
										<c:forEach items="${list }" var="seat" varStatus="i">
											<li class='li'><c:forEach items="${seat }" var="item"
													varStatus="j">
													<a id="${i.index }_${j.index }" class="drop">
														<ul style='margin: 0'>
															<c:if test="${item!=null }">
																<c:choose>
																	<c:when test="${item.studentSex==2 }">
																		<li data-obj-id="${item.studentId}"
																			data-obj-name="${item.studentName}"
																			class='female item'><img
																			src="<avatar:avatar userId='${item.userId }'></avatar:avatar>" />
																		<p>${item.studentName }</p></li>
																	</c:when>
																	<c:otherwise>
																		<li data-obj-id="${item.studentId}"
																			data-obj-name="${item.studentName}" class='male item'><img
																			src="<avatar:avatar userId='${item.userId }'></avatar:avatar>" />
																		<p>${item.studentName }</p></li>
																	</c:otherwise>
																</c:choose>
															</c:if>
														</ul>
													</a>
												</c:forEach></li>
										</c:forEach>
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
function back(){
	window.location.href="${ctp}/clazz/seatChart/schoolMasterView";
}
</script>
</html>