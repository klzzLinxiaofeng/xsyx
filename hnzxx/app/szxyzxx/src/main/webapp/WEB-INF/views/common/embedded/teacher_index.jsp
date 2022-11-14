<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<%@ include file="/views/embedded/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="${pageContext.request.contextPath}/res/css/extra/index.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/res/plugin/falgun/css/fullcalendar.css" rel="stylesheet">
	<script src="${pageContext.request.contextPath}/res/plugin/falgun/js/fullcalendar.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/date/lunar_calendar.js"></script>
	<title>教师首页</title>
	<style type="text/css">
		body{
			overflow-x: hidden;
		}
		.fc-header-title h2{
			color:#fff;
		}
		.fc-header .fc-button{
			margin:6px 0;
		}
		.fc-header-title{
			/* margin-top:6px; */
		}
		.fc-state-default{
			opacity: 0.7;
		}
		.fc-event-inner{
			text-overflow: ellipsis;
			white-space: nowrap;
			overflow: hidden;
			display: block;
			word-wrap:normal;
		}
		.fc-border-separate th, .fc-border-separate td{
			border-color:#e0e0e0;
			color:#fff;
		}
		.fc-border-separate th{
			height:25px;
			line-height:25px;
		}
		.fc-state-highlight {
			background: rgba(30,150,255, 0.5) ;
		}
		.fc-header{
			border:1px solid #e0e0e0;
			border-bottom:0 none;
		}
		.fc-text-arrow{
			color:#fff;
		}
		.fc-state-default.fc-corner-left,.fc-state-default.fc-corner-right{
			border-radius:0;
		}
		.fc-state-default{
			background:#3393b7 ;
			color:#fff;
			opacity:1;
			border:0 none;
			text-shadow:0 0 0 rgba(255, 255, 255, 1);
			box-shadow:0 0 0 rgba(255, 255, 255, 0);
		}
		.fc-button-today{
			color:#fff;
		}
		.fc .fc-header-left{
			padding-left:5px;
		}
		.solarday,.holiday{
			margin-right:2px;
		}
		.fc-grid .fc-day-number{
			padding-right:2px;
			color:#fff;
		}
		.navbar-inner{
			padding-right:0;
		}
		#main_head_menus{
			display:none;
		}
		.navbar-inverse .navbar-inner {
			background-color: transparent;
		}

		#message{
			display:none;
		}
		.yyxz ul li {
			width: 96px;
		}
		.yyxz ul li a {
			text-align: center;
		}
		.yyxz ul li img{
			margin-left:0;
		}
		.fc-state-highlight{
			background:#4a7fb3;
		}
		.table-bordered{
			margin-bottom: 0;
		}
		#imgs{
			width: 100%;
			height: 150px;
		}
		#liss{
			width: 100%;
		}
	</style>
</head>
<body>
<div class="main_bg" style="background-size:100%"></div>
<!-- <div class="main"></div> -->

<div id="manager_head" style="position:relative;"><jsp:include page="/views/embedded/manager_head.jsp"/> </div>
<c:if test='${sessionScope[sca:currentUserKey()].userName eq "superadmin"}' >
	<div class="tj_nav">
		<ul>
			<li class="on"><a href="javascript:void(0)">运营情况</a></li>
			<li><a href="javascript:void(0)">用户活跃度</a></li>
			<li ><a href="javascript:void(0)">用户数量</a></li>
			<li><a href="javascript:void(0)">教学资源应用情况</a></li>
			<!-- 			<li><a href="javascript:void(0)">教学资源总量统计</a></li> -->
			<!-- <li><a href="javascript:void(0)">教学资源学科分布</a></li> -->
			<li><a href="javascript:void(0)">教学资源学科分布</a></li>
			<li><a href="javascript:void(0)"><i class="ppyy_icon"></i>产品应用</a></li>
		</ul>
	</div>
</c:if>
<div class="content">
	<div class="change_bg"><a href="javascript:void(0)" ></a></div>
	<div class="index_tjdiv">

		<c:if test='${sessionScope[sca:currentUserKey()].userName eq "superadmin"}' >
			<div class="index_tj" >
				<div class="bjyy div_jg">
					<div class="title bjyy">开通学校及人数统计</div>
					<div class="div_padding">
						<div class="sl_ul index_sl">
							<ul>
								<li class="l4">
									<div class="left"></div>
									<span class="zz_1">开通学校</span>
									<p class="p1"><span>129</span>所</p>
								</li>
								<li class="l5">
									<div class="left"></div>
									<span class="zz_1">开通人数（不含家长）</span>
									<p class="p1"><span>79607</span>人</p>
								</li>
							</ul>
							<div class="clear"></div>
						</div>
						<div id="ktxxjrs" style="height:250px;float:left;width:50%;"></div>
						<div class="clear"></div>
					</div>
				</div>
				<div class="bjyy div_jg">
					<div class="title bjyy">资源统计</div>
					<div class="div_padding">
						<div class="sl_ul index_sl">
							<ul>
								<li class="l6">
									<div class="left"></div>
									<span class="zz_1">资源总数</span>
									<p class="p1"><span>916697</span>个</p>
								</li>
								<li class="l2">
									<div class="left"></div>
									<span class="zz_1">资源总量</span>
									<p class="p1"><span>1468</span>GB</p>
								</li>
							</ul>
							<div class="clear"></div>
						</div>
						<div id="zytj" style="height:250px;float:left;width:50%;"></div>
						<div class="clear"></div>
					</div>
				</div>
				<div class="bjyy" style="display:none;">
					<div class="title bjyy">学校活跃度排行</div>
					<div class="school_rank">
						<ul>
							<li class="first"><span>1</span>宁夏育才中学</li>
							<li class="second"><span>2</span>韶关仁化中学</li>
							<li class="third"><span>3</span>章贡区滨江二小</li>
							<li><span>4</span>宁夏育才中学</li>
							<li><span>5</span>韶关仁化中学</li>
							<li><span>6</span>章贡区滨江二小</li>
							<li><span>7</span>宁夏育才中学</li>
							<li><span>8</span>韶关仁化中学</li>
							<li><span>9</span>章贡区滨江二小</li>
						</ul>
						<div class="clear"></div>
					</div>
				</div>
			</div>
			<div class="index_tj" >
				<div class="bjyy div_jg">
					<div class="title bjyy">用户月活跃度统计</div>
					<div id="yyyhyd" style="height:465px;"></div>
				</div>
				<div class="bjyy">
					<div class="title bjyy">用户日活跃度统计</div>
					<div id="yyrhyd" style="height:400px;"></div>
				</div>
			</div>
			<div class="index_tj bjyy">
				<div class="title bjyy">用户数量</div>
				<div id="china_map" style="height: 640px;"></div>
			</div>
			<div class="index_tj" >
				<div class=" div_jg">
					<div class="sl_ul second_sl">
						<ul>
							<li class="l7">
								<div class="left"></div>
								<span class="zz_1">教学资源浏览量</span>
								<p class="p1"><span>1785426</span>次</p>
								<span class="zz_2">昨日新增 2467</span>
							</li>
							<li class="l8">
								<div class="left"></div>
								<span class="zz_1">教学资源下载量</span>
								<p class="p1"><span>193107</span>次</p>
								<span class="zz_2">昨日新增 1065</span>
							</li>
							<li class="l9">
								<div class="left"></div>
								<span class="zz_1">教学资源上传量</span>
								<p class="p1"><span>916697</span>次</p>
								<span class="zz_2">昨日新增 478</span>
							</li>
						</ul>
					</div>
					<div class="clear"></div>
				</div>
				<div>
					<div class="bjyy div_jg">
						<div class="title bjyy">各学段教学资源浏览统计</div>
						<div id="jxzyll" style="height:400px;"></div>
					</div>
					<div class="bjyy div_jg">
						<div class="title bjyy">各学段教学资源下载统计</div>
						<div id="jxzyxz" style="height:400px;"></div>
					</div>
					<div class="bjyy div_jg">
						<div class="title bjyy">各学段教学资源上传统计</div>
						<div id="jxzysc" style="height:400px;"></div>
					</div>
				</div>
			</div>

			<!-- <div class="index_tj" >
			<div class=" div_jg">
			<div class="sl_ul">
			<ul>
			<li class="l1">
			<div class="left"></div>
			<span class="zz_1">资源数量</span>
			<p class="p1"><span>293107</span>个</p>
			</li>
			<li class="l2">
			<div class="left"></div>
			<span class="zz_1">资源容量</span>
			<p class="p1"><span>30.2</span>TB</p>
			</li>
			<li class="l3">
			<div class="left"></div>
			<span class="zz_1">今日新增</span>
			<p class="p1"><span>293</span>个</p>
			</li>
			</ul>
			</div>
			<div class="clear"></div>
			</div>
			<div>
			<div class="bjyy half_div">
			<div class="title bjyy">教学资源数量统计</div>
			<div id="jxzytj" style="height:540px;"></div>
			</div>
			<div class="bjyy half_div">
			<div class="title bjyy">各学段教学资源统计</div>
			<div id="gxdjxtj" style="height:540px;"></div>
			</div>
			</div>
			</div> -->
			<!-- <div class="index_tj" >
			<div class="bjyy half_div div_jg">
			<div class="title bjyy">小学各学科资源分布统计</div>
			<div id="xxzyfb" style="height:558px;"></div>
			</div>
			<div class="bjyy half_div div_jg">
			<div class="title bjyy">初中各学科资源分布统计</div>
			<div id="czzyfb" style="height:558px;"></div>
			</div>
			<div class="bjyy half_div div_jg">
			<div class="title bjyy">高中各学科资源分布统计</div>
			<div id="gzzyfb" style="height:558px;"></div>
			</div>
			</div> -->
			<div class="index_tj">
				<div class="bjyy div_jg">
					<div class="title bjyy">各类型教学资源统计</div>
					<div id="glxjxzy" style="height:558px;"></div>
				</div>
				<div class="bjyy div_jg">
					<div class="title bjyy">小学各类型资源统计</div>
					<div id="xxzytj" style="height:220px;"></div>
				</div>
				<div class="bjyy div_jg">
					<div class="title bjyy">初中各类型资源统计</div>
					<div id="czzytj" style="height:220px;"></div>
				</div>
				<div class="bjyy div_jg">
					<div class="title bjyy">高中各类型资源统计</div>
					<div id="gzzytj" style="height:220px;"></div>
				</div>
			</div>
		</c:if>

		<div class="i_left index_tj" id="div_left" style='${sessionScope[sca:currentUserKey()].userName eq "superadmin" ? "display:none;" : ""}  z-index:1;'>

		</div>
	</div>
	<div class="i_right" style="right:-468px;">
		<div class="time">
			<div class="shadeDiv"style="left:0;width:425px"></div>
			<div class="zl" style="width:425px">

				<img alt="img" src="<avatar:avatar userId='${sessionScope[sca:currentUserKey()].id}'></avatar:avatar>">
				<%-- 					<img alt="img" src="${pageContext.request.contextPath}/res/images/noPhoto.jpg"> --%>
				<div class="z_right">
					<input type="hidden" id="userTypes" value="${sessionScope[sca:currentUserKey()].userTypes}">
					<input type="hidden" id="schoolTermCode" value="${sessionScope[sca:currentUserKey()].schoolTermCode}">
					<input type="hidden" id="teacherId" value="${sessionScope[sca:currentUserKey()].teacherId}">
					<input type="hidden" id="studentId" value="${sessionScope[sca:currentUserKey()].studentId}">
					<input type="hidden" id="teamId" value="${teamId }">
					<p class="name">${sessionScope[sca:currentUserKey()].realName}</p>
					<div class="js">
						<div class="js_li" style="padding-right:20px;border-right:1px solid #CCCCCC;width:120px;">
							<p class="p1">
								<c:choose>
									<c:when test="${fn:contains(sessionScope[sca:currentUserKey()].userTypes, 1) }">所属部门</c:when>
									<c:when test="${fn:contains(sessionScope[sca:currentUserKey()].userTypes, 2) }">所属部门</c:when>
									<c:when test="${fn:contains(sessionScope[sca:currentUserKey()].userTypes, 4) }">所属班级</c:when>
									<%-- 									<c:when test="${sessionScope[sca:currentUserKey()].userTypes == '4'}">所属班级</c:when> --%>
									<c:otherwise></c:otherwise>
								</c:choose>
							</p>
							<p class="p2" title="${depOrteamName }">${depOrteamName }</p>
						</div>
						<div class="js_li" style="padding-left:20px;width:120px;">
							<p class="p1">角色</p>
							<p class="p2" title="${roleName }">${roleName }</p>
						</div>
					</div>
					<p class="data" id="lunar"></p>
					<!-- 						<p class="data">2015-07-03  &nbsp; 星期五  &nbsp; 农历五月十八</p> -->
				</div>
			</div>
			<!-- 				<div class="shadeDiv" style="width:100px;right:0;"></div> -->
			<!-- 				<div class="tianqi" style="padding:25px 5px 20px;overflow:hidden"> -->
			<!-- 					 <iframe allowtransparency="true" frameborder="0" width="130" height="130" scrolling="no" src="http://tianqi.2345.com/plugin/widget/index.htm?s=2&z=1&t=1&v=1&d=1&bd=0&k=&f=&q=1&e=1&a=1&c=59287&w=130&h=130&align=center"></iframe> -->
			<!-- 					 <iframe allowtransparency="true" frameborder="0" width="130" height="130" scrolling="no" src="http://tianqi.2345.com/plugin/widget/index.htm?s=2&z=1&t=1&v=1&d=1&bd=0&k=&f=ffffff&q=1&e=1&a=1&c=59287&w=130&h=130&align=center"></iframe> -->
			<!-- 				</div> -->
		</div>
		<div class="shadeDiv" style="width:425px;height:10px;margin-top: 10px;"></div>
		<div class="kebiao">
			<div class="shadeDiv" style="width:425px;height:10px;"></div>
			<div class="kb_select">
				<a href="javascript:void(0)" class="on">日历</a>
				<c:choose>
					<c:when test="${fn:contains(sessionScope[sca:currentUserKey()].userTypes, 1) }">
						<a href="javascript:void(0)" onclick="getSyllabus();">课程表</a>
					</c:when>
					<c:when test="${fn:contains(sessionScope[sca:currentUserKey()].userTypes, 4) }">
						<a href="javascript:void(0)" onclick="getSyllabus();">课程表</a>
					</c:when>
					<c:otherwise></c:otherwise>
				</c:choose>
			</div>
			<div class="kb_list">
				<div class="wdkc" >
					<div class="shadeDiv" style="width:425px;margin:-18px 0 0 -20px;height:250px;"></div>
					<div id='calendar'>
					</div>
				</div>
				<div style="display:none;">
					<div class="shadeDiv2" style="width:425px;margin:-18px 0 0 -20px;min-height: 295px;"></div>
					<div class="kcb">
						<table class="responsive table  table-bordered table-striped">
							<thead>
							<tr>
								<th></th>
								<th>一</th>
								<th>二</th>
								<th>三</th>
								<th>四</th>
								<th>五</th>
								<th>六</th>
								<th>日</th>
							</tr>
							</thead>
							<tbody>
							<tr>
								<td>第1节</td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
							<tr>
								<td>第2节</td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
							<tr>
								<td>第3节</td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
							<tr>
								<td>第4节</td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
							<tr>
								<td>第5节</td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
							<tr>
								<td>第6节</td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
							<tr>
								<td>第7节</td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
		<!-- 			<div class="shadeDiv" style="width:425px;margin-top:10px;"></div> -->


		<c:choose>
			<c:when test='${pageContext.request.serverName eq "school.x-bull.com" or pageContext.request.serverName eq "school.10niu.cn"}'>

			</c:when>
			<c:otherwise>
				<div class="yyxz">
						<%--<a href="javascript:void(0)" onclick="SHOW_more()" style="float:right;color:#fff;display:block;line-height:36px;width:50px;text-align:center;">更多</a>--%>
					<div class="shadeDiv" style="width:425px;height:0;"></div>
					<p class="yy_title">小程序二维码</p>
					<ul>
						<!-- 					<li> -->
						<!-- 						<a href="javascript:void(0)" onclick="down_app(0);"> -->
							<%-- 							<img alt="" src="${pageContext.request.contextPath}/res/images/yy_5.png"> --%>
						<!-- 							<span>教育云家长端</span> -->
						<!-- 						</a> -->
						<!-- 					</li> -->
						<!-- 					<li> -->
						<!-- 						<a href="javascript:void(0)" onclick="down_app(1);"> -->
							<%-- 							<img alt="" src="${pageContext.request.contextPath}/res/images/yy_6.png"> --%>
						<!-- 							<span>教育云教师端</span> -->
						<!-- 						</a> -->
						<!-- 					</li> -->
						<li id="liss">
							<a href="javascript:void(0)" >
								<img alt="" src="${ctp}/res/images/applets.png" onclick="Change(this);" id="imgs">
								<span style="font-size: 17px">智慧香市e小校园</span>
							</a>
						</li>

						<%--<li>
							<a href="javascript:void(0)" onclick="down_app_tag('qy');">
								<img alt="" src="https://cdn.studyo.cn/education/edu/2016-8-25/c94dd9926e52915ef4a94be78ceb67ae.png">
								<span>奇云APP</span>
							</a>
						</li>--%>
						<c:forEach items="${appReleaseVoList}" var="appRelease" varStatus="i">
							<!-- 不是ios端 -->
							<c:if test="${appRelease.appKey !='xunyun#educloud#mobile#android_qy' and  appRelease.appKey !='xunyun#educloud#mobile#android' and appRelease.appKey != 'xunyun#educloud#mobile#ios' and appRelease.appKey != 'xunyun#microlesson#pc' and appRelease.appKey != 'xunyun#microlesson#moblie#android#teacher' and appRelease.appKey != 'xunyun#microlesson#moblie#android#parent' and appRelease.appKey != 'xunyun#microlesson#moblie#android#student'}">
								<%--<li>
									<a href="javascript:void(0)" onclick="down_app('${appRelease.id}');">
										<img alt="" src="${appRelease.trademark}">
										<span>${appRelease.name }</span>
									</a>
								</li>--%>
							</c:if>
						</c:forEach>
							<%-- <li>
                               <a href="${pageContext.request.contextPath}/res/excel/zonda96.rar">
                                   <img alt="" src="${pageContext.request.contextPath}/res/images/zdpk.png">
                                   <span>智能排课</span>
                               </a>
                           </li> --%>
					</ul>
					<div class="clear"></div>
				</div>
				<!-- 			<div class="shadeDiv" style="width:425px;margin-top:10px;"></div> -->
				<div class="yyxz">
					<div class="shadeDiv" style="width:425px;height:0;"></div>
					<p class="yy_title">使用手册</p>
					<ul>
						<li>
							<a href="${pageContext.request.contextPath}/res/excel/system_help.docx">
								<img alt="" src="${pageContext.request.contextPath}/res/images/init_help.png">
							</a>
						</li>
						<li>
							<a href="${pageContext.request.contextPath}/res/excel/student_help.docx">
								<img alt="" src="${pageContext.request.contextPath}/res/images/operating_student.png">
							</a>
						</li>
						<li>
							<a href="${pageContext.request.contextPath}/res/excel/teacher_help.docx">
								<img alt="" src="${pageContext.request.contextPath}/res/images/operating_teacher.png">
							</a>
						</li>
					</ul>
				</div>
			</c:otherwise>
		</c:choose>

	</div>
	<div class="clear"></div>
</div>
<div class="bizhi" style="display:none">
	<div class="b_top">
		<p>更换主题</p>
		<a href="javascript:void(0)" class="btn-close">x</a>
	</div>
	<div class="b_middle ghbz">
		<p class="title">更换壁纸</p>
		<ul>
			<li class="on">
				<a href="javascript:void(0)">
					<img src="${pageContext.request.contextPath}/res/css/extra/images/bizhi0.jpg" data-id="/res/css/extra/images/bizhi0.jpg" alt="壁纸">
					<span>壁纸1</span>
				</a>
			</li>
			<li>
				<a href="javascript:void(0)">
					<img src="${pageContext.request.contextPath}/res/css/extra/images/bizhi1.jpg" data-id="/res/css/extra/images/bizhi1.jpg" alt="壁纸">
					<span>壁纸2</span>
				</a>
			</li>
			<li >
				<a href="javascript:void(0)">
					<img src="${pageContext.request.contextPath}/res/css/extra/images/bizhi2.jpg" data-id="/res/css/extra/images/bizhi2.jpg" alt="壁纸">
					<span>壁纸3</span>
				</a>
			</li>
			<li>
				<a href="javascript:void(0)">
					<img src="${pageContext.request.contextPath}/res/css/extra/images/bizhi3.jpg" data-id="/res/css/extra/images/bizhi3.jpg"  alt="壁纸">
					<span>壁纸4</span>
				</a>
			</li>
			<li>
				<a href="javascript:void(0)">
					<img src="${pageContext.request.contextPath}/res/css/extra/images/bizhi4.jpg" data-id="/res/css/extra/images/bizhi4.jpg" alt="壁纸">
					<span>壁纸5</span>
				</a>
			</li>
			<li>
				<a href="javascript:void(0)">
					<img src="${pageContext.request.contextPath}/res/css/extra/images/bizhi5.png" data-id="/res/css/extra/images/bizhi5.png" alt="壁纸">
					<span>壁纸6</span>
				</a>
			</li>
			<li>
				<a href="javascript:void(0)">
					<img src="${pageContext.request.contextPath}/res/css/extra/images/bizhi6.png" data-id="/res/css/extra/images/bizhi6.png" alt="壁纸">
					<span>壁纸7</span>
				</a>
			</li>
			<li >
				<a href="javascript:void(0)">
					<img src="${pageContext.request.contextPath}/res/css/extra/images/bizhi7.jpg" data-id="/res/css/extra/images/bizhi7.jpg" alt="壁纸">
					<span>壁纸8</span>
				</a>
			</li>
			<li>
				<a href="javascript:void(0)">
					<img src="${pageContext.request.contextPath}/res/css/extra/images/bizhi8.jpg" data-id="/res/css/extra/images/bizhi8.jpg"  alt="壁纸">
					<span>壁纸9</span>
				</a>
			</li>
			<li>
				<a href="javascript:void(0)">
					<img src="${pageContext.request.contextPath}/res/css/extra/images/bizhi9.jpg" data-id="/res/css/extra/images/bizhi9.jpg" alt="壁纸">
					<span>壁纸10</span>
				</a>
			</li>
		</ul>
		<div class="clear"></div>
	</div>
	<div class="b_middle ghzts">
		<p class="title">更换主题色</p>
		<ul>
			<li class="on">
				<a href="javascript:void(0)">
					<div class="f_0d7bd5" data-class="f_0d7bd5"></div>
				</a>
			</li>
			<li>
				<a href="javascript:void(0)">
					<div class="f_45b84b" data-class="f_45b84b"></div>
				</a>
			</li>
			<li >
				<a href="javascript:void(0)">
					<div class="f_000000" data-class="f_000000"></div>
				</a>
			</li>
			<li>
				<a href="javascript:void(0)">
					<div class="f_02adb3" data-class="f_02adb3"></div>
				</a>
			</li>
			<li>
				<a href="javascript:void(0)">
					<div class="f_b3027b" data-class="f_b3027b"></div>
				</a>
			</li>
		</ul>
		<div class="clear"></div>
	</div>
	<div class="b_bottom">
		<button class="btn btn-warning save">保存</button>
		<button class="btn btn-close">取消</button>
	</div>
</div>
<div class="zhezhao"></div>
</body>

<script>

    $(function(){

        if(navigator.appName == "Microsoft Internet Explorer" && navigator.appVersion.match(/9./i)=="9.")
        {
            $(".shadeDiv").css("filter","Alpha(opacity=90)");
            $(".shadeDiv2").css("filter","Alpha(opacity=90)");
        }


        $("#main_head_menus").remove();
        var total_width = $(window).width();
        var left_width = -(total_width-468);
        var _div=document.getElementById("div_left");
        _div.style.left=(left_width)+"px";
        $(".i_left").animate({left:'0px'},900);
        $(".i_right").animate({right:'50px'},900);
        /* var a=$(".navbar .nav li a", window.top.document).text();
        var b=$(".panel-body>ul>li", window.top.document).children().children("span").text(); */
        // 顶部li个数
        var l=$(".navbar .nav li", window.top.document).length;
        for(var k=0;k<l-1;k++){
            var title_html=$('.navbar .nav li a', window.top.document).eq(k+1).text();
            var menu_top_id=$('.navbar .nav li', window.top.document).eq(k+1).attr("data-menu-id");
            var li_url=$('.navbar .nav li', window.top.document).eq(k+1).attr("data-url");
            $(".content .i_left").append("<div class='mk_list'><p class='title'>"+title_html+"<span style='display:none'>"+menu_top_id+"</span></p></div>");

            if(menu_top_id!='BAN_BAN_XING'){
                var li_html=$("#left_head_"+menu_top_id, window.top.document).children().children();
                // 	每个左边li的个数
                var l1=li_html.length;
                $(".content .i_left .mk_list").eq(k).append("<ul></ul>")
                for(var m=0;m<l1;m++){
                    var b=li_html.eq(m).children().children("span").text();
                    var menu_id=li_html.eq(m).attr("data-menu-id");
// 			alert(menu_id)
                    $(".content .i_left .mk_list").eq(k).children("ul").append("<li class="+menu_id+"><a href='javascript:void(0)' onclick=\"menu('" + menu_top_id + "','" + menu_id +"');\"><span>"+b+"</span></a></li>")
                }
            }else{
                if(li_url.indexOf("@(target=_blank)") != -1) {
                    li_url = li_url.replace("@(target=_blank)", "");
                } else if (li_url.indexOf("@(target=_self)") != -1) {
                    li_url = li_url.replace("@(target=_self)", "");
                }
                $(".content .i_left .mk_list").eq(k).append("<ul><li class='BAN_BAN_XING'><a href='javascript:toUrl(\"" + li_url + "\");'><span>班班星</span></a></li></ul>")
            }
            $(".content .i_left .mk_list").eq(k).append("<div class='clear'></div>")
        }
        /*$(window.frames["FF"].document).find("#mobile7 .wtbg").css("color","red");
         $('.navbar .nav li a', window.top.document) */

        $("#lunar").text(showCal());
        getWallpaper();

    })

    function toUrl(li_url) {
        window.parent.location="${pageContext.request.contextPath}"+li_url;
    }

    function getWallpaper() {
        var url = "${ctp}/hnzxx/ui/setting/getWallpaper"
        $.post(url, {"_method" : "get"}, function(data, status) {
            if("success" === status) {
                if(data != null && data != "") {
                	//alert(data)
                	//循环遍历
                	var data = JSON.parse(data);
                	
                	$(".bizhi .b_middle ul li img").each(function(index,item){
                		var wallpaperPath = $(this).attr("data-id") ;
                		if(wallpaperPath == data.wallpaperPath){
                		
                			$(this).parent().parent().siblings().removeClass("on");
                            $(this).parent().parent().addClass("on")
                		}
                	})
                	
                	$(".bizhi .ghzts ul li div").each(function(index,item){
                		var wallpaperColor = $(this).attr("data-class") ;
                		if(wallpaperColor == data.wallpaperColor){
                			$(this).parent().parent().siblings().removeClass("on");
                            $(this).parent().parent().addClass("on")
                		}
                	})
                	
                    $(".main_bg").css({"background":"url(${ctp}"+data.wallpaperPath+") no-repeat","backgroundSize":"100% "+h2+""});
                    $("#main_body",window.parent.document).find(".navbar-inner").removeClass().addClass("navbar-inner "+data.wallpaperColor+"");
                    //$(".main_bg").css("backgroundSize","100%");
                }else {
                    $(".main_bg").css({"background":"url(${ctp}/res/css/extra/images/bizhi3.jpg) no-repeat","backgroundSize":"100%"+h2+""});
                    //$(".main_bg").css("backgroundSize","100%");
                }
            }
        });
    }

    function getSyllabus() {
        var userTypes = $("#userTypes").val();
        var schoolTermCode = $("#schoolTermCode").val();
        var teacherId = $("#teacherId").val();
        var studentId = $("studentId").val();
        var teamId = $("#teamId").val();
        if(userTypes.indexOf("1") != -1) {
            if(teacherId != null && teacherId != "") {
                $(".kcb").load("${ctp}/teach/syllabus/searcher/teacher/list", {"_method" : "GET", "teachId" : teacherId, "termCode" : schoolTermCode, "type" : 1}, function(data, status) {
                    setTimeout(function () {
                        var gaodu = $(".kcb").height();
                        $(".shadeDiv2").height(gaodu);
                    },1300);
                });
            }
        }else if(userTypes == "4") {
            if(teamId != null && teamId != "") {
                $(".kcb").load("${ctp}/teach/syllabus/searcher/team/list", {"_method" : "GET", "teamId" : teamId, "termCode" : schoolTermCode, "type" : 1}, function(data, status) {
                    var gaodu = $(".kcb").height();
                    $(".shadeDiv2").height(gaodu);
// 				alert(gaodu);
                });
                setTimeout(function () {
                    var gaodu = $(".kcb").height();
                    $(".shadeDiv2").height(gaodu);
                },1300);
            }
        }else {

        }


    }

    function menu(dataMenuId, leftMenuId) {

		var leftHead = $("#left_head_" + dataMenuId, window.top.document);  //第一层(左侧菜单div)
		var data_url = leftHead.find("ul[data-parentid='" + dataMenuId + "']>li[data-menu-id='" + leftMenuId + "']").attr("data-url");
		//总务数据需要新窗口打开
		if(data_url=="/res/bigScreenData/index.html") {
			window.open(data_url);
			return;
		}
		else if(dataMenuId=='XIAO_WU_GUAN_LI' && leftMenuId=='KAO_QING_GUAN_LI'){
			window.open(data_url);
			return;
		}
		else if( leftMenuId=='qxjzjs'){
			window.open(data_url);
			return;
		}
		else if( leftMenuId=='DAPING_TWO' || leftMenuId=='BOBAO_STREE' || leftMenuId=='BOBAO_FOUR' || leftMenuId=='BOBAO_FIVE'){
			window.open(data_url);
			return;
		}
        $("#main_head_menus ul li[data-menu-id='" + dataMenuId + "']", window.top.document).click(); //顶部菜单的单击123


        leftHead.find("ul[data-parentid='" + dataMenuId + "']>li[data-menu-id='" + leftMenuId + "'] a:first>i:first").click();
        if(data_url == null || data_url == "") {
            var leftFirst = leftHead.find("ul[data-parentid='" + dataMenuId + "']>li[data-menu-id='" + leftMenuId + "']");
            leftFirst.find("ul li:eq(0) a:first>i:first").click();
            data_url = leftFirst.find("ul li:eq(0)").attr("data-url");
            if(data_url == null || data_url == "") {
                var leftSecond = leftFirst.find("ul li:eq(0)");
                leftSecond.find("ul li:eq(0) a:first>i:first").click();
                data_url = leftSecond.find("ul li:eq(0)").attr("data-url");
            }
        }
        /* $(".left_mu").animate({
            width: '+=200'
          }, 500);
        $(".leftbar").show(1000);
        $(".man_right").css("margin-left","200px");
        $(this).removeClass("left_open"); */
        $('.left_mu', window.top.document).css("width","215px");
        $('.left_mu', window.top.document).show();
        $(".leftbar", window.top.document).show();
        $(".man_right", window.top.document).css("margin-left","200px");
        $("#left_close", window.top.document).removeClass("left_open");
        $(".top-nav", window.top.document).css({"position":"relative","top":"0px"});
        $("#core_iframe,.leftbar", window.top.document).css("height", h2-56);
        $("#message", window.top.document).css("top","0px");
    }

    function InItCalData(){
        var InItCalUrl = "${ctp}/oa/schedule/dataInIt";
        return InItCalUrl;
    }

    $(document).ready(function() {
        var date = new Date();
        var d = date.getDate();
        var m = date.getMonth();
        var y = date.getFullYear();

        $('#calendar').fullCalendar({
            height: 250,
            header: {
                left: 'prev,next today',
                center: 'title',
                right: ''
            },
            editable: false,
            eventClick: function(calEvent, jsEvent, view) {
                menu("YUN_BAN_GONG","OA_RI_CHENG_AN_PAI");

            },
            /* dayClick : function(date, allDay, jsEvent, view) {
                alert(date)
                menu("YUN_BAN_GONG","OA_RI_CHENG_AN_PAI");
                    }, */
            events: InItCalData()
// 		events: [
// 			{
// 				title: '今天',
// 				start: new Date(y, m, 1)
// 			},
// 			{
// 				title: '生日',
// 				start: new Date(y, m, d+1, 19, 0),
// 				end: new Date(y, m, d+1, 22, 30),
// 				allDay: false
// 			},
// 			{
// 				title: '要去桂林出差',
// 				start: new Date(y, m, 28),
// 				end: new Date(y, m, 29),
// 				url: 'http://google.com/'
// 			}
// 		]
        });

    });

    var h = document.documentElement.clientHeight;
    var h2 = document.body.clientHeight;
    var w= document.documentElement.clientWidth;
    var h1 = (document.documentElement.clientHeight-296)/2;
    var w1= (document.documentElement.clientWidth-900)/2;


    function cover() {
        var win_width = $(window).width();
        var win_height = $(window).height();
        $(".main_bg").css({
            "width" : win_width,
            "height" : win_height
        });
    }
	function Change(obj) {
		var imgSrc = $(obj).attr("src");
		window.open(imgSrc);
	}
    $(function(){

        /* $(".holiday").prev(".solarday").hide(); */
        var win_height = $(window).height();
        var h = document.documentElement.clientHeight;
        $(window).load(function(){
            cover();
        })
        $(window).resize(function() {//浏览器窗口变化
            w1= (document.documentElement.clientWidth-900)/2;
            $(".bizhi").css({"left":w1,"top":"150px"});
            h2 = document.body.clientHeight;
            $(".content").css("min-height",h2-18);
            cover();
        });
        $(".main").css("min-height","1081px");
        $(".content").css("min-height",h-18);
        $(".content .change_bg a").click(function(e){
            e.stopPropagation();
            $(".bizhi,.zhezhao").show();
            $(".zhezhao").css({"width":"100%","height":h});
            $(".bizhi").css({"left":w1,"top":"150px"});
        });
        $(".btn-close").click(function(){
            $(".bizhi,.zhezhao").hide();
        })
        $(".bizhi .b_middle ul li a").click(function(){
        	$(this).parent().siblings().removeClass("on");
            $(this).parent().addClass("on")
        });
        $(".save").click(function(){
            var src=$(".bizhi .ghbz ul .on img").attr("data-id");
            var zt_class=$(".bizhi .ghzts ul .on div").attr("data-class");
            $(".main_bg").css({"background":"url(${ctp}"+src+") no-repeat","backgroundSize":"100% "+h2+""});
            $("#main_body",window.parent.document).find(".navbar-inner").removeClass().addClass("navbar-inner "+zt_class+"");
            //$(".main_bg").css("backgroundSize",""+h2+"");
            $(".bizhi,.zhezhao").hide();
            saveWallpaper(src,zt_class);
        });
        $(".bizhi").click(function (e) {
            e.stopPropagation();//阻止事件向上冒泡
        });

        $(document).click(function(){
            if(!$(".bizhi").is(":hidden")){
                $(".bizhi,.zhezhao").hide();
            }
        });

        $(".kebiao .kb_select a").click(function(){
            $(".kebiao .kb_select a").removeClass("on");
            $(this).addClass("on");
            var i=$(this).index();
            $(".kebiao .kb_list").children().hide();
            $(".kebiao .kb_list").children().eq(i).show();

            if(i==1){
                $(".shadeDiv2").show();
            }else{
                $(".shadeDiv2").hide();
            }
        });

    });

    function saveWallpaper(wallpaperPath,zt_class) {
        var url = "${ctp}/hnzxx/ui/setting/saveOrUpdate"
        $.post(url, {"_method" : "post", "wallpaperPath" : wallpaperPath,"wallpaperColor":zt_class}, function(data, status){})
    }


    function down_app(id) {
        $.initWinOnTopFromLeft("应用下载", "${pageContext.request.contextPath}/sys/appupdate/download?id="+id, '535', '240');
    }

    function down_app_tag(tag) {
        $.initWinOnTopFromLeft("应用下载", "${pageContext.request.contextPath}/sys/appupdate/download/page?tag="+tag, '535', '240');
    }

    function SHOW_more() {
        $.initWindow({
            "title": false,
            "maxmin" : false,
            "top" : "56",
            "shift" : "top",
            "height" : $(parent.window).height() - 60,
            "url" : "${pageContext.request.contextPath}/views/embedded/application_store.jsp",
            "close" : function(index) {
            },
            "closeBtn" : false
        });
    }
    $("#message", window.top.document).css({"position":"relative","top":"56px"});
    window.onscroll =function(){
        var scrollTop=0;
        if(document.documentElement&&document.documentElement.scrollTop)
        {
            scrollTop=document.documentElement.scrollTop;
        }
        else if(document.body)
        {
            scrollTop=document.body.scrollTop;
        }
        h=56-scrollTop;
        $("#message", window.top.document).css({"position":"relative","top":h});
        return scrollTop;
    }

</script>
<c:if test='${sessionScope[sca:currentUserKey()].userName eq "superadmin"}' >
	<!-- 新增统计js -->
	<script src="${pageContext.request.contextPath}/res/plugin/echarts/js/echarts.min.js"></script>
	<script src="${pageContext.request.contextPath}/res/plugin/echarts/js/china.js"></script>
	<script src="${pageContext.request.contextPath}/res/plugin/Highcharts-4.0.3/js/highcharts.js"></script>
	<script>
        $(function(){
            /* var cskd=$(".index_tj").width();
            $("#china_map").css("width",cskd); */
            $(".index_tj").css("z-index","-1")
            $(".index_tj").eq(0).css("z-index","1");
            //$(".index_tj").css({"position":"absolute","top":"-9999px"});
            $(".tj_nav ul li a").click(function(){
                $(".tj_nav ul li").removeClass("on");
                $(this).parent().addClass("on");
                var z=$(this).parent().index();
                $(".index_tj").hide();
                //$(".index_tj").eq(6).css("z-index","-1")
                $(".index_tj").eq(z).show();

                $(".index_tj").eq(z).css("z-index","1")
                /* if(z==6){
                    $(".index_tj").eq(6).css("z-index","1")
                }else{
                    $(".index_tj").eq(6).css("z-index","-1")

                } */
            });
        });
        // 	数校云综合统计

        $(function () {
            $('#ktxxjrs').highcharts({
                chart: {
                    type: 'column',
                    backgroundColor: 'rgba(0,0,0,0)'
                },
                title: {
                    text: ''
                },
                subtitle: {
                    text: ''
                },
                credits:{
                    enabled:false // 禁用版权信息
                },
                xAxis: {
                    categories: [
                        '教师人数',
                        '学生人数',
                        '家长人数'
                    ],
                    labels: {
                        style: {
                            color: '#fff',//颜色
                        }
                    },
                    crosshair: true
                },
                yAxis: {
                    min: 0,
                    title: {
                        text: ''
                    },
                    labels: {
                        style: {
                            color: '#fff',//颜色
                        }
                    },
                },
                tooltip: {
                    headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
                    pointFormat: '<tr><td style="color:{series.color};padding:0">{point.y} </td>' +
                    '</tr>',
                    footerFormat: '</table>',
                    shared: true,
                    useHTML: true
                },
                legend:{
                    enabled :false
                },
                plotOptions: {
                    column: {
                        dataLabels:{
                            enabled:true, // dataLabels设为true
                            style:{
                                color:'#fff'
                            }
                        },
                        colorByPoint:true
                    }
                },
                series: [{
                    //colorByPoint:true,  或者直接写在这里
                    data: [8136,71471,49587]
                }]
            });
            $('#zytj').highcharts({
                chart: {
                    type: 'column',
                    backgroundColor: 'rgba(0,0,0,0)'
                },
                title: {
                    text: ''
                },
                subtitle: {
                    text: ''
                },
                credits:{
                    enabled:false // 禁用版权信息
                },
                xAxis: {
                    categories: [
                        '微课',
                        '课件',
                        '作业',
                        '试卷',
                        '教案',
                        '素材',
                        '导学案',
                        '其他'
                    ],
                    labels: {
                        style: {
                            color: '#fff',//颜色
                        }
                    },
                    crosshair: true
                },
                yAxis: {
                    min: 0,
                    title: {
                        text: ''
                    },
                    labels: {
                        style: {
                            color: '#fff',//颜色
                        }
                    },
                },
                tooltip: {
                    headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
                    pointFormat: '<tr><td style="color:{series.color};padding:0">{point.y} </td>' +
                    '</tr>',
                    footerFormat: '</table>',
                    shared: true,
                    useHTML: true
                },
                legend:{
                    enabled :false
                },
                plotOptions: {
                    column: {
                        dataLabels:{
                            enabled:true, // dataLabels设为true
                            style:{
                                color:'#fff'
                            }
                        },
                        colorByPoint:true
                    }
                },
                series: [{
                    //colorByPoint:true,  或者直接写在这里
                    data: [7000,364472,79241,48484,180759,173317,64187,973]
                }]
            });
        });

        // 教学资源浏览统计
        $(function () {
            $('#jxzyll').highcharts({
                chart: {
                    plotBackgroundColor: null,
                    plotBorderWidth: null,
                    plotShadow: false,
                    backgroundColor: 'rgba(0,0,0,0)',
                    type: 'pie'
                },
                title: {
                    text: ''
                },
                credits:{
                    enabled:false // 禁用版权信息
                },
                tooltip: {
                    formatter: function() {
                        return '<b>'+ this.point.name +'</b>: '+ Highcharts.numberFormat(this.percentage, 1) +'%';
                    }
                },
                plotOptions: {
                    pie: {
                        allowPointSelect: true,
                        cursor: 'pointer',
                        dataLabels: {
                            color:'#fff',
                            enabled: true,
//                     format: '<p><b>{point.name}</b></p><br>数量: {point.percentage:.1f} %',
                            format: '<p><b>{point.name}</b></p><br>数量: {point.y} 个<br>占比: {point.percentage:.1f} % ',
                            style: {
                                color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                            }
                        }
                    }
                },
                series: [{
                    name: "人数",
                    colorByPoint: true,
                    data: [{
                        name: "小学",
                        y: 534830
                    }, {
                        name: "初中",
                        y: 1020305
                    }, {
                        name: "高中",
                        y: 230291
                    }]
                }]
            });
            $('#jxzyxz').highcharts({
                chart: {
                    plotBackgroundColor: null,
                    plotBorderWidth: null,
                    plotShadow: false,
                    backgroundColor: 'rgba(0,0,0,0)',
                    type: 'pie'
                },
                title: {
                    text: ''
                },
                credits:{
                    enabled:false // 禁用版权信息
                },
                tooltip: {
                    formatter: function() {
                        return '<b>'+ this.point.name +'</b>: '+ Highcharts.numberFormat(this.percentage, 1) +'%';
                    }
                },
                plotOptions: {
                    pie: {
                        allowPointSelect: true,
                        cursor: 'pointer',
                        dataLabels: {
                            color:'#fff',
                            enabled: true,
//                     format: '<p><b>{point.name}</b></p><br>数量: {point.percentage:.1f} %',
                            format: '<p><b>{point.name}</b></p><br>数量: {point.y} 个<br>占比: {point.percentage:.1f} % ',
                            style: {
                                color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                            }
                        }
                    }
                },
                series: [{
                    name: "人数",
                    colorByPoint: true,
                    data: [{
                        name: "小学",
                        y: 20894
                    }, {
                        name: "高中",
                        y: 50493
                    }, {
                        name: "初中",
                        y: 121720
                    }]
                }]
            });
            $('#jxzysc').highcharts({
                chart: {
                    plotBackgroundColor: null,
                    plotBorderWidth: null,
                    plotShadow: false,
                    backgroundColor: 'rgba(0,0,0,0)',
                    type: 'pie'
                },
                title: {
                    text: ''
                },
                credits:{
                    enabled:false // 禁用版权信息
                },
                tooltip: {
                    formatter: function() {
                        return '<b>'+ this.point.name +'</b>: '+ Highcharts.numberFormat(this.percentage, 1) +'%';
                    }
                },
                plotOptions: {
                    pie: {
                        allowPointSelect: true,
                        cursor: 'pointer',
                        dataLabels: {
                            color:'#fff',
                            enabled: true,
//                     format: '<p><b>{point.name}</b></p><br>数量: {point.percentage:.1f} %',
                            format: '<p><b>{point.name}</b></p><br>数量: {point.y} 个<br>占比: {point.percentage:.1f} % ',
                            style: {
                                color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                            }
                        }
                    }
                },
                series: [{
                    name: "人数",
                    colorByPoint: true,
                    data: [{
                        name: "小学",
                        y: 181320
                    }, {
                        name: "高中",
                        y: 123815
                    }, {
                        name: "初中",
                        y: 611562
                    }]
                }]
            });
        });

        // 	用户活跃度统计
        // 获得今天之前的一整个月
        function get_allmonth(){
            var myDate = new Date();
            var riqi=new Array();
            if(myDate.getDate()>1){
                for(var i=myDate.getDate();i>0;i--){
                    riqi.unshift((myDate.getMonth()+1)+"/"+i);
                }
            }
            var day = new Date(myDate.getFullYear(),(myDate.getMonth()),0);
            //获取上个月天数：
            var daycount = day.getDate();
            for(var j=daycount;j>myDate.getDate();j--){
                riqi.unshift((myDate.getMonth())+"/"+j);
            }
            return riqi;
        }
        $(function () {
            /* 用户月活跃度统计 */
            $('#yyyhyd').highcharts({
                chart: {
                    backgroundColor: 'rgba(0,0,0,0)'
                },
                title: {
                    text: '',
                    x: -20 //center
                },
                subtitle: {
                    text: '',
                    x: -20
                },
                xAxis: {
                    categories:get_allmonth(),
                    labels: {
                        style: {
                            color: '#fff',//颜色
                        }
                    },
                },
                yAxis: {
                    title: {
                        text: ''
                    },
                    labels: {
                        style: {
                            color: '#fff',//颜色
                        }
                    },
                    plotLines: [{
                        value: 0,
                        width: 1,
                        color: '#808080'
                    }]
                },
                tooltip: {
                    valueSuffix: '人'
                },
                legend: {
                    layout: 'vertical',
                    align: 'right',
                    verticalAlign: 'middle',
                    borderWidth: 0
                },
                series: [{
                    name: '资源上传',
                    color: '#2f7ed8',
                    data: [2005,2564,2934,2498,3012,3456,2777,2993,3777,4321,3632,3187,2988,2452,2001,2693,3222,3987,3219,2002,2500,2987,3392,4456,3592,3321,2988,2532,2234,2876,4823]
                },{
                    name: '资源浏览',
                    color: '#0aca7b',
                    data: [3823,4232,3521,3212,2998,3567,4682,4988,5412,4421,3923,4212,3321,2898,3512,3982,4321,3329,3812,4442,3911,3124,3610,3999,4512,3228,2982,3412,3928,3522,4745]
                }, {
                    name: '资源下载',
                    color: '#cf3b3b',
                    data: [1892,2412,2921,3012,2412,3100,3789,3991,4100,3511,3109,3211,2761,2212,2876,3112,3311,2311,3387,3871,3188,2412,2967,3124,3421,2412,2279,2787,3255,2799,3876]
                }]
            });
//     用户日活跃度统计
            Highcharts.setOptions({
                global: {
                    useUTC: false
                }
            });

            $('#yyrhyd').highcharts({
                title: {

                },
                chart: {
                    type: 'spline',
                    animation: Highcharts.svg, // don't animate in old IE
                    marginRight: 10,
                    backgroundColor: 'rgba(0,0,0,0)',//背景颜色
                    events: {
                        load: function () {

                            // set up the updating of the chart each second
                            var series = this.series[0];
                            setInterval(function () {
                                var x = (new Date()).getTime(), // current time
                                    y = Math.floor(Math.random()*200+350)
                                series.addPoint([x, y], true, true);
                            }, 1800000);
                        }
                    }
                },

                credits:{
                    enabled:false // 禁用版权信息
                },
                title: {
                    text: '',
                },

                xAxis: {
                    type: 'datetime',
                    tickPixelInterval: 150,
                    labels: {
                        style: {
                            color: '#fff',//颜色
                        }
                    },
                },
                yAxis: {
                    title: {
                        text: ''
                    },
                    plotLines: [{
                        value: 0,
                        width: 1,
                        color: '#808080'
                    }],
                    labels: {
                        style: {
                            color: '#fff',//颜色
                        }
                    },
                },
                tooltip: {
                    formatter: function () {
                        return '<b>' + this.series.name + '</b><br/>' +
                            Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) + '<br/>' +
                            Highcharts.numberFormat(this.y, 2);
                    }
                },
                legend: {
                    enabled: false
                },
                exporting: {
                    enabled: false
                },
                series: [{
                    name: '访问量',
                    data: (function () {
                        // generate an array of random data
                        var data = [],
                            time = (new Date()).getTime(),
                            i;

                        for (i = -19; i <= 0; i += 1) {
                            data.push({
                                x: time + i * 1000000,
                                y: Math.floor(Math.random()*200+350)
                            });
                        }
                        return data;
                    }())
                }]
            });
        });
        //教学资源总量统计
        $(function () {
            $('#jxzytj').highcharts({
                chart: {
                    plotBackgroundColor: null,
                    plotBorderWidth: null,
                    plotShadow: false,
                    backgroundColor: 'rgba(0,0,0,0)',
                    type: 'pie'
                },
                title: {
                    text: ''
                },
                credits:{
                    enabled:false // 禁用版权信息
                },
                tooltip: {
                    formatter: function() {
                        return '<b>'+ this.point.name +'</b>: '+ Highcharts.numberFormat(this.percentage, 1) +'%';
                    }
                },
                plotOptions: {
                    pie: {
                        allowPointSelect: true,
                        cursor: 'pointer',
                        dataLabels: {
                            color:'#fff',
                            enabled: true,
//                     format: '<p><b>{point.name}</b></p><br>数量: {point.percentage:.1f} %',
                            format: '<p><b>{point.name}</b></p><br>数量: {point.y} 个<br>占比: {point.percentage:.1f} % ',
                            style: {
                                color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                            }
                        }
                    }
                },
                series: [{
                    name: "人数",
                    colorByPoint: true,
                    data: [{
                        name: "校本资源",
                        y: 734830
                    }, {
                        name: "平台资源",
                        y: 1120305
                    }, {
                        name: "外购资源",
                        y: 183403
                    }]
                }]
            });
            $('#gxdjxtj').highcharts({
                chart: {
                    plotBackgroundColor: null,
                    plotBorderWidth: null,
                    plotShadow: false,
                    backgroundColor: 'rgba(0,0,0,0)',
                    type: 'pie'
                },
                title: {
                    text: ''
                },
                credits:{
                    enabled:false // 禁用版权信息
                },
                tooltip: {
                    formatter: function() {
                        return '<b>'+ this.point.name +'</b>: '+ Highcharts.numberFormat(this.percentage, 1) +'%';
                    }
                },
                plotOptions: {
                    pie: {
                        allowPointSelect: true,
                        cursor: 'pointer',
                        dataLabels: {
                            color:'#fff',
                            enabled: true,
//                     format: '<p><b>{point.name}</b></p><br>数量: {point.percentage:.1f} %',
                            format: '<p><b>{point.name}</b></p><br>数量: {point.y} 个<br>占比: {point.percentage:.1f} % ',
                            style: {
                                color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                            }
                        }
                    }
                },
                series: [{
                    name: "人数",
                    colorByPoint: true,
                    data: [{
                        name: "小学",
                        y: 937727
                    }, {
                        name: "高中",
                        y: 489249
                    }, {
                        name: "初中",
                        y: 611562
                    }]
                }]
            });
        });
        //教学资源学科分布统计
        $(function () {
            $('#xxzyfb').highcharts({
                chart: {
                    type: 'bar',
                    backgroundColor: 'rgba(0,0,0,0)'
                },
                title: {
                    text: ''
                },
                subtitle: {
                    text: ''
                },
                credits:{
                    enabled:false // 禁用版权信息
                },
                xAxis: {
                    categories: [
                        '微课',
                        '课件',
                        '作业',
                        '试卷',
                        '教案',
                        '素材',
                        '导学案',
                        '其他'
                    ],
                    labels: {
                        style: {
                            color: '#fff',//颜色
                        }
                    },
                    crosshair: true
                },
                yAxis: {
                    min: 0,
                    title: {
                        text: ''
                    },
                    labels: {
                        style: {
                            color: '#fff',//颜色
                        }
                    },
                },
                tooltip: {
                    headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
                    pointFormat: '<tr><td style="color:{series.color};padding:0">{point.y} </td>' +
                    '</tr>',
                    footerFormat: '</table>',
                    shared: true,
                    useHTML: true
                },
                legend:{
                    enabled :false
                },
                plotOptions: {
                    bar: {
                        dataLabels:{
                            enabled:true, // dataLabels设为true
                            style:{
                                color:'#fff'
                            }
                        },
                        colorByPoint:true
                    }
                },
                series: [{
                    //colorByPoint:true,  或者直接写在这里
                    data: [1386,72165,15690,9600,35790,34317,12709,193]
                }]
            });
            $('#czzyfb').highcharts({
                chart: {
                    type: 'bar',
                    backgroundColor: 'rgba(0,0,0,0)'
                },
                title: {
                    text: ''
                },
                subtitle: {
                    text: ''
                },
                credits:{
                    enabled:false // 禁用版权信息
                },
                xAxis: {
                    categories: [
                        '微课',
                        '课件',
                        '作业',
                        '试卷',
                        '教案',
                        '素材',
                        '导学案',
                        '其他'
                    ],
                    labels: {
                        style: {
                            color: '#fff',//颜色
                        }
                    },
                    crosshair: true
                },
                yAxis: {
                    min: 0,
                    title: {
                        text: ''
                    },
                    labels: {
                        style: {
                            color: '#fff',//颜色
                        }
                    },
                },
                tooltip: {
                    headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
                    pointFormat: '<tr><td style="color:{series.color};padding:0">{point.y} </td>' +
                    '</tr>',
                    footerFormat: '</table>',
                    shared: true,
                    useHTML: true
                },
                legend:{
                    enabled :false
                },
                plotOptions: {
                    bar: {
                        dataLabels:{
                            enabled:true, // dataLabels设为true
                            style:{
                                color:'#fff'
                            }
                        },
                        colorByPoint:true
                    }
                },
                series: [{
                    //colorByPoint:true,  或者直接写在这里
                    data: [4669,243103,52853,32339,120567,115602,42813,649]
                }]
            });
            $('#gzzyfb').highcharts({
                chart: {
                    type: 'bar',
                    backgroundColor: 'rgba(0,0,0,0)'
                },
                title: {
                    text: ''
                },
                subtitle: {
                    text: ''
                },
                credits:{
                    enabled:false // 禁用版权信息
                },
                xAxis: {
                    categories: [
                        '微课',
                        '课件',
                        '作业',
                        '试卷',
                        '教案',
                        '素材',
                        '导学案',
                        '其他'
                    ],
                    labels: {
                        style: {
                            color: '#fff',//颜色
                        }
                    },
                    crosshair: true
                },
                yAxis: {
                    min: 0,
                    title: {
                        text: ''
                    },
                    labels: {
                        style: {
                            color: '#fff',//颜色
                        }
                    },
                },
                tooltip: {
                    headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
                    pointFormat: '<tr><td style="color:{series.color};padding:0">{point.y} </td>' +
                    '</tr>',
                    footerFormat: '</table>',
                    shared: true,
                    useHTML: true
                },
                legend:{
                    enabled :false
                },
                plotOptions: {
                    bar: {
                        dataLabels:{
                            enabled:true, // dataLabels设为true
                            style:{
                                color:'#fff'
                            }
                        },
                        colorByPoint:true
                    }
                },
                series: [{
                    //colorByPoint:true,  或者直接写在这里
                    data: [945,49204,10698,6545,24402,23398,8665,131]
                }]
            });
        });
        //各类型教学资源统计
        $(function () {
            $('#glxjxzy').highcharts({
                chart: {
                    plotBackgroundColor: null,
                    plotBorderWidth: null,
                    plotShadow: false,
                    backgroundColor: 'rgba(0,0,0,0)',
                    type: 'pie'
                },
                title: {
                    text: ''
                },
                credits:{
                    enabled:false // 禁用版权信息
                },
                tooltip: {
                    formatter: function() {
                        return '<b>'+ this.point.name +'</b>: '+ Highcharts.numberFormat(this.percentage, 1) +'%';
                    }
                },
                plotOptions: {
                    pie: {
                        allowPointSelect: true,
                        cursor: 'pointer',
                        dataLabels: {
                            color:'#fff',
                            enabled: true,
//                     format: '<p><b>{point.name}</b></p><br>数量: {point.percentage:.1f} %',
                            format: '<p><b>{point.name}</b></p><br>数量: {point.y} 个<br>占比: {point.percentage:.1f} % ',
                            style: {
                                color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                            }
                        }
                    }
                },
                series: [{
                    name: "人数",
                    colorByPoint: true,
                    data: [ {
                        name: "微课",
                        y: 7000
                    }, {
                        name: "课件",
                        y: 364472
                    },{
                        name: "作业",
                        y:79241
                    },{
                        name: "试卷",
                        y: 48484
                    },{
                        name: "教案",
                        y: 180759
                    },{
                        name: "素材",
                        y: 173317
                    },{
                        name: "导学案",
                        y: 64187
                    },{
                        name: "其他",
                        y: 973
                    }]
                }]
            });
            $('#xxzytj').highcharts({
                chart: {
                    type: 'column',
                    backgroundColor: 'rgba(0,0,0,0)'
                },
                title: {
                    text: ''
                },
                subtitle: {
                    text: ''
                },
                credits:{
                    enabled:false // 禁用版权信息
                },
                xAxis: {
                    categories: [
                        '微课',
                        '课件',
                        '作业',
                        '试卷',
                        '教案',
                        '素材',
                        '导学案',
                        '其他'
                    ],
                    labels: {
                        style: {
                            color: '#fff',//颜色
                        }
                    },
                    crosshair: true
                },
                yAxis: {
                    min: 0,
                    title: {
                        text: ''
                    },
                    labels: {
                        style: {
                            color: '#fff',//颜色
                        }
                    },
                },
                tooltip: {
                    headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
                    pointFormat: '<tr><td style="color:{series.color};padding:0">{point.y} </td>' +
                    '</tr>',
                    footerFormat: '</table>',
                    shared: true,
                    useHTML: true
                },
                legend:{
                    enabled :false
                },
                plotOptions: {
                    column: {
                        dataLabels:{
                            enabled:true, // dataLabels设为true
                            style:{
                                color:'#fff'
                            }
                        },
                        colorByPoint:true
                    }
                },
                series: [{
                    //colorByPoint:true,  或者直接写在这里
                    data: [1386,72165,15690,9600,35790,34317,12709,193]
                }]
            });
            $('#czzytj').highcharts({
                chart: {
                    type: 'column',
                    backgroundColor: 'rgba(0,0,0,0)'
                },
                title: {
                    text: ''
                },
                subtitle: {
                    text: ''
                },
                credits:{
                    enabled:false // 禁用版权信息
                },
                xAxis: {
                    categories: [
                        '微课',
                        '课件',
                        '作业',
                        '试卷',
                        '教案',
                        '素材',
                        '导学案',
                        '其他'
                    ],
                    labels: {
                        style: {
                            color: '#fff',//颜色
                        }
                    },
                    crosshair: true
                },
                yAxis: {
                    min: 0,
                    title: {
                        text: ''
                    },
                    labels: {
                        style: {
                            color: '#fff',//颜色
                        }
                    },
                },
                tooltip: {
                    headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
                    pointFormat: '<tr><td style="color:{series.color};padding:0">{point.y} </td>' +
                    '</tr>',
                    footerFormat: '</table>',
                    shared: true,
                    useHTML: true
                },
                legend:{
                    enabled :false
                },
                plotOptions: {
                    column: {
                        dataLabels:{
                            enabled:true, // dataLabels设为true
                            style:{
                                color:'#fff'
                            }
                        },
                        colorByPoint:true
                    }
                },
                series: [{
                    //colorByPoint:true,  或者直接写在这里
                    data: [4669,243103,52853,32339,120567,115602,42813,649]
                }]
            });
            $('#gzzytj').highcharts({
                chart: {
                    type: 'column',
                    backgroundColor: 'rgba(0,0,0,0)'
                },
                title: {
                    text: ''
                },
                subtitle: {
                    text: ''
                },
                credits:{
                    enabled:false // 禁用版权信息
                },
                xAxis: {
                    categories: [
                        '微课',
                        '课件',
                        '作业',
                        '试卷',
                        '教案',
                        '素材',
                        '导学案',
                        '其他'
                    ],
                    labels: {
                        style: {
                            color: '#fff',//颜色
                        }
                    },
                    crosshair: true
                },
                yAxis: {
                    min: 0,
                    title: {
                        text: ''
                    },
                    labels: {
                        style: {
                            color: '#fff',//颜色
                        }
                    },
                },
                tooltip: {
                    headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
                    pointFormat: '<tr><td style="color:{series.color};padding:0">{point.y} </td>' +
                    '</tr>',
                    footerFormat: '</table>',
                    shared: true,
                    useHTML: true
                },
                legend:{
                    enabled :false
                },
                plotOptions: {
                    column: {
                        dataLabels:{
                            enabled:true, // dataLabels设为true
                            style:{
                                color:'#fff'
                            }
                        },
                        colorByPoint:true
                    }
                },
                series: [{
                    //colorByPoint:true,  或者直接写在这里
                    data: [945,49204,10698,6545,24402,23398,8665,131]
                }]
            });
        });
        // 	用户数据统计
        var dom = document.getElementById("china_map");
        var myChart = echarts.init(dom);
        var app = {};
        option = null;
        function randomData() {
            return Math.round(Math.random() * 1000);
        }

        var option = {
            title : {
                text : '',
                subtext : '',
                textStyle:{    //图例文字的样式
                    color:'#fff',
                    fontSize:24 ,
                    lineHeight:120
                },
                left : 'center'
            },
            tooltip : {
                trigger : 'item'
            },
            legend : {
                orient : 'vertical',
                left : 'left',
                data : [ '' ]
            },
            visualMap : {
                min : 0,
                max : 40000,
                left : 'left',
                top : 'bottom',
                textStyle:{    //图例文字的样式
                    color:'#fff',
                    fontSize:12
                },
                text : [ '高', '低' ], // 文本，默认为数值文本
                inRange: {
                    color: ['#70d9ff','#29b7f7', '#2196f5','#1665c1']
                },
                calculable : true
            },
            toolbox : {
                show : false,
                orient : 'vertical',
                left : 'right',
                top : 'center',
                feature : {
                    dataView : {
                        readOnly : false
                    },
                    restore : {},
                    saveAsImage : {}
                }
            },
            series : [ {
                name : '人数',
                type : 'map',
                mapType : 'china',
                label : {
                    normal : {
                        show : true
                    },
                    emphasis : {
                        show : true
                    }
                },
                itemStyle:{
                    normal: {
                        borderWidth: .5,//区域边框宽度
                        borderColor: '#13bfff',//区域边框颜色
                        color:'#fff',//圆点颜色
                        areaColor:"#a1e3ff",//区域颜色
                    },
                },

                data : [ {
                    name : '广东',
                    value : '8954'
                }, {
                    name : '云南',
                    value : '8063'
                }, {
                    name : '江西',
                    value : '16728'
                }, {
                    name : '贵州',
                    value : '3125'
                }, {
                    name : '广西',
                    value : '2943'
                }, {
                    name : '河南',
                    value : '33400'
                }, {
                    name : '宁夏',
                    value : '8184'
                } ]
            } ]
        };
        if (option && typeof option === "object") {
            myChart.setOption(option, true);
        }

        myChart.on("click", function(param) {
            /* var wid=$("body").width()*0.9;
            var hei=$("body").height()*0.9; */
            if(param.name=="宁夏"){
                $.initWinOnTopFromLeft("学校用户统计",'${pageContext.request.contextPath}/views/demo/china_map/ningxia.jsp');
            }else if(param.name=="广东"){
                $.initWinOnTopFromLeft("学校用户统计",'${pageContext.request.contextPath}/views/demo/china_map/guangdong.jsp');
            }else if(param.name=="云南"){
                $.initWinOnTopFromLeft("学校用户统计",'${pageContext.request.contextPath}/views/demo/china_map/yunnan.jsp');
            }else if(param.name=="江西"){
                $.initWinOnTopFromLeft("学校用户统计",'${pageContext.request.contextPath}/views/demo/china_map/jiangxi.jsp');
            }else if(param.name=="贵州"){
                $.initWinOnTopFromLeft("学校用户统计",'${pageContext.request.contextPath}/views/demo/china_map/guizhou.jsp');
            }else if(param.name=="广西"){
                $.initWinOnTopFromLeft("学校用户统计",'${pageContext.request.contextPath}/views/demo/china_map/guangxi.jsp');
            }else if(param.name=="河南"){
                $.initWinOnTopFromLeft("学校用户统计",'${pageContext.request.contextPath}/views/demo/china_map/henan.jsp');
            }
        });


	</script>
</c:if>
</html>
