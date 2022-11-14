<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/views/embedded/taglib.jsp"%>
<link href="${pageContext.request.contextPath}/res/css/extra/add.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/css/bbx/bbx_left.css" rel="stylesheet">
<style type="text/css">
#navigation {
height: 100%;
overflow: auto;
padding-bottom: 50px;
}
</style>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/common/jquery.nicescroll.js"></script>
<script type="text/javascript">

	function getMenu(id, accessUrl, icon, name, desc) {
		return "<li ui-sref-active='active' data-desc='" + desc + "' data-url='" + accessUrl + "' data-menu-id='" + id 
				+ "' class=''><a ui-sref='app.dashboard' href='javascript:void(0);'><i class='" + icon 
				+ "'></i><span>" + name 
				+ "</span></a></li>";
	}

	function createLv2Menu(parentId, id, name, icon, accessUrl, desc) {
		var lv2Cotrainer = $("#left_head_" + parentId); 
		var lv2Ul = $(".panel-body").find("ul[data-parentid='" + parentId + "']");
		var lv2Menu = getMenu(id,accessUrl, icon, name, desc);
// 		var homeMenu = getMenu("", "/views/embedded/mannager_index.jsp", "fa fa-home", "首页");
		if(lv2Ul.length <= 0) {
			var startUl = $("<ul id='navigation' data-parentId='" + parentId + "' class='ng-scope navigation' ripple='' nav-collapse=''>" + lv2Menu + "</ul>");
// 			var startUl = $("<ul id='navigation' data-parentId='" + parentId + "' class='ng-scope navigation' ripple='' nav-collapse=''>" + homeMenu + lv2Menu + "</ul>");
			lv2Cotrainer.append(startUl);
		} else {
			lv2Ul.append(lv2Menu);
		}
	}
	
	function createLv3Menu(parentId, id, name, icon, accessUrl, desc) {
		var lv3Contrainer = $("li[data-menu-id='" + parentId + "']");
		var lv3Ul = lv3Contrainer.find("ul[data-parentid='" + parentId + "']");
		var lv3Menu = getMenu(id, accessUrl, "fa fa-caret-right", name, desc);
		if(lv3Ul.length <= 0) {
			var startUl = "<ul data-parentid = '" + parentId + "' style='display: none;'>" + lv3Menu + "</ul>";
			lv3Contrainer.append(startUl);
		} else {
			lv3Ul.append(lv3Menu);
		}
	}
	
	function createLv4Menu(parentId, id, name, icon, accessUrl, desc) {
		var lv4Contrainer = $("li[data-menu-id='" + parentId + "']");
		var lv4Ul = lv4Contrainer.find("ul[data-parentId='" + parentId + "']");
		var lv4Menu = getMenu(id, accessUrl, "fa fa-caret-right", name, desc);
		if(lv4Ul.length <= 0) {
			var startUl = "<ul data-parentid = '" + parentId + "' style='display: none;'>" + lv4Menu + "</ul>";
			lv4Contrainer.append(startUl);
		} else {
			lv4Ul.append(lv4Menu);
		}
	}
</script>
<div class="left_mu" >
<div class="leftbar clearfix lef_menu"  style=" padding-top: 6px;z-index:0;left:0px;width:200px">
	
		<c:choose>
		 <c:when test="${sessionScope[sca:currentUserKey()].currentRoleCode=='CLASS_MASTER'}">
			<div class="panel-body" id="left_head_BAN_BAN_XING">
			<ul id="navigation" data-parentid="BAN_BAN_XING" class="ng-scope navigation" ripple="" nav-collapse="">
				
				<!-- <li ui-sref-active="active" data-desc="" data-url="/bbx/screenPermission/index"
					data-menu-id="PING_MU_QUAN_XIAN" class=""><a
					ui-sref="app.dashboard" href="javascript:void(0);"><i
						class="fa bbx_2"></i><span>屏幕权限</span></a></li>
				<li ui-sref-active="active" data-desc="" data-url="/bbx/circleMessage/index"
					data-menu-id="BAN_JI_DONG_TAI" class=""><a
					ui-sref="app.dashboard" href="javascript:void(0);"><i
						class="fa bbx_3"></i><span>班级动态</span></a></li>
				<li ui-sref-active="active" data-desc="" data-url="/bbx/praise/index"
					data-menu-id="BIAO_YANG_LAN" class=""><a
					ui-sref="app.dashboard" href="javascript:void(0);"><i
						class="fa bbx_4"></i><span>表扬栏</span></a></li>
				<li ui-sref-active="active" data-desc="" data-url="/clazz/teamActivity/index"
					data-menu-id="BAN_JI_HUO_DONG" class=""><a
					ui-sref="app.dashboard" href="javascript:void(0);"><i
						class="fa bbx_5"></i><span>班级活动</span></a></li>
				<li ui-sref-active="active" data-desc="" data-url="/clazz/classMember/index"
					data-menu-id="BAN_JI_CHENG_YUAN" class=""><a
					ui-sref="app.dashboard" href="javascript:void(0);"><i
						class="fa bbx_6"></i><span>班级成员</span></a></li>
				<li ui-sref-active="active" data-desc="" data-url="/bbx/teamAward/index"
					data-menu-id="BAN_JI_RONG_YU" class=""><a
					ui-sref="app.dashboard" href="javascript:void(0);"><i
						class="fa bbx_7"></i><span>班级荣誉</span></a></li>
				<li ui-sref-active="active" data-desc="" data-url="/bbx/declaration/index"
					data-menu-id="BAN_JI_XUAN_YAN" class=""><a
					ui-sref="app.dashboard" href="javascript:void(0);"><i
						class="fa bbx_8"></i><span>班级宣言</span></a></li>
				<li ui-sref-active="active" data-desc="" data-url="/clazz/teamDutyUser/index"
					data-menu-id="ZHI_RI_SHENG" class=""><a
					ui-sref="app.dashboard" href="javascript:void(0);"><i
						class="fa bbx_9"></i><span>值日生</span></a></li>
				<li ui-sref-active="active" data-desc="" data-url="/clazz/seatChart/teacherIndex"
					data-menu-id="ZUO_WEI_BIAO" class=""><a
					ui-sref="app.dashboard" href="javascript:void(0);"><i
						class="fa bbx_10"></i><span>座位表</span></a></li>
				<li ui-sref-active="active" data-desc="" data-url="/clazz/teamSyllabus/index"
					data-menu-id="KE_CHENG_BIAO" class=""><a
					ui-sref="app.dashboard" href="javascript:void(0);"><i
						class="fa bbx_11"></i><span>课程表</span></a></li>
				<li ui-sref-active="active" data-desc="" data-url="/clazz/teamHomework/index"
					data-menu-id="ZUO_YE_GONG_SHI" class=""><a
					ui-sref="app.dashboard" href="javascript:void(0);"><i
						class="fa bbx_12"></i><span>作业通知</span></a></li>
				<li ui-sref-active="active" data-desc="" data-url="/bbx/teamMessage/index"
					data-menu-id="BAN_JI_TONG_ZHI" class=""><a
					ui-sref="app.dashboard" href="javascript:void(0);"><i
						class="fa bbx_13"></i><span>家校通知</span></a></li>
				<li ui-sref-active="active" data-desc="" data-url="/bbx/download/index"
					data-menu-id="APP_XIA_ZAI" class=""><a
					ui-sref="app.dashboard" href="javascript:void(0);"><i
						class="fa bbx_14"></i><span>班班星APP下载</span></a></li>
			    <li ui-sref-active="active" data-desc="" data-url="/bbx/welcome/index"
					data-menu-id="HUAN_YING_CI" class=""><a
					ui-sref="app.dashboard" href="javascript:void(0);"><i
						class="fa bbx_15"></i><span>欢迎词</span></a></li> -->
			</ul>
		</div>
		 </c:when>
		 <c:when test="${sessionScope[sca:currentUserKey()].currentRoleCode=='SCHOOL_MASTER' or sessionScope[sca:currentUserKey()].currentRoleCode=='SCHOOL_LEADER'}">
			<div class="panel-body" id="left_head_BAN_BAN_XING">
			<ul id="navigation" data-parentid="BAN_BAN_XING" class="ng-scope navigation" ripple="" nav-collapse="">
				
				<!-- <li ui-sref-active="active" data-desc="" data-url=""
					data-menu-id="ZONG_HE_TONG_JI" class=""><a
					ui-sref="app.dashboard" href="javascript:void(0);"><i
						class="fa bbx_15"></i><span>综合统计</span></a></li>-->
				<!-- <li ui-sref-active="active" data-desc="" data-url="/bbx/teamMessage/schoolMasterView"
					data-menu-id="QUAN_XIAO_TONG_ZHI" class=""><a
					ui-sref="app.dashboard" href="javascript:void(0);"><i
						class="fa bbx_16"></i><span>家校通知</span></a></li> 
						<li ui-sref-active="active" data-desc="" data-url="/clazz/teamHomework/schoolMasterView"
					data-menu-id="ZUO_YE_GONG_SHI" class=""><a
					ui-sref="app.dashboard" href="javascript:void(0);"><i
						class="fa bbx_12"></i><span>作业通知</span></a></li>
				<li ui-sref-active="active" data-desc="" data-url="/clazz/classMember/schoolMasterView"
					data-menu-id="BAN_JI_CHENG_YUAN" class=""><a
					ui-sref="app.dashboard" href="javascript:void(0);"><i
						class="fa bbx_6"></i><span>班级成员</span></a></li>
				<li ui-sref-active="active" data-desc="" data-url="/clazz/seatChart/schoolMasterView"
					data-menu-id="ZUO_WEI_BIAO" class=""><a
					ui-sref="app.dashboard" href="javascript:void(0);"><i
						class="fa bbx_10"></i><span>座位表</span></a></li> -->
				
			
<!-- 				<li ui-sref-active="active" data-desc="" data-url="/bbx/download/index" -->
<!-- 					data-menu-id="APP_XIA_ZAI" class=""><a -->
<!-- 					ui-sref="app.dashboard" href="javascript:void(0);"><i -->
<!-- 						class="fa bbx_14"></i><span>班班星APP下载</span></a></li> -->
			</ul>
		</div> 
		 </c:when>
		 <c:when test="${sessionScope[sca:currentUserKey()].currentRoleCode=='SUBJECT_TEACHER'}">
			<div class="panel-body" id="left_head_BAN_BAN_XING">
			<ul id="navigation" data-parentid="BAN_BAN_XING" class="ng-scope navigation" ripple="" nav-collapse="">
				
				<!-- <li ui-sref-active="active" data-desc="" data-url="/clazz/teamHomework/index"
					data-menu-id="ZUO_YE_GONG_SHI" class=""><a
					ui-sref="app.dashboard" href="javascript:void(0);"><i
						class="fa bbx_12"></i><span>作业通知</span></a></li>
				<li ui-sref-active="active" data-desc="" data-url="/bbx/circleMessage/index"
					data-menu-id="BAN_JI_DONG_TAI" class=""><a
					ui-sref="app.dashboard" href="javascript:void(0);"><i
						class="fa bbx_3"></i><span>班级动态</span></a></li>
				<li ui-sref-active="active" data-desc="" data-url="/clazz/classMember/index"
					data-menu-id="BAN_JI_CHENG_YUAN" class=""><a
					ui-sref="app.dashboard" href="javascript:void(0);"><i
						class="fa bbx_6"></i><span>班级成员</span></a></li>
				<li ui-sref-active="active" data-desc="" data-url="/clazz/seatChart/teacherIndex"
					data-menu-id="ZUO_WEI_BIAO" class=""><a
					ui-sref="app.dashboard" href="javascript:void(0);"><i
						class="fa bbx_10"></i><span>座位表</span></a></li>
				<li ui-sref-active="active" data-desc="" data-url="/clazz/teamSyllabus/index"
					data-menu-id="KE_CHENG_BIAO" class=""><a
					ui-sref="app.dashboard" href="javascript:void(0);"><i
						class="fa bbx_11"></i><span>课程表</span></a></li> -->
<!-- 				 <li ui-sref-active="active" data-desc="" data-url="/bbx/download/index" -->
<!-- 					data-menu-id="APP_XIA_ZAI" class=""><a -->
<!-- 					ui-sref="app.dashboard" href="javascript:void(0);"><i -->
<!-- 						class="fa bbx_14"></i><span>班班星APP下载</span></a></li> -->
			</ul>
		</div> 
		 </c:when>
		 <c:when test="${sessionScope[sca:currentUserKey()].currentRoleCode=='STUDENT'}">
		 <div class="panel-body" id="left_head_BAN_BAN_XING">
			<ul id="navigation" data-parentid="BAN_BAN_XING" class="ng-scope navigation" ripple="" nav-collapse="">
			</ul>
		</div> 
		</c:when>
		 <c:otherwise>
		 
		 </c:otherwise>
		 </c:choose>
		 
		 <c:forEach items="${headModules}" var="module">
		<div class="panel-body" id="left_head_${module.code}" style="display: none">
		</div>
	</c:forEach>

</div>
	<div class="zg_btn"><a id="left_close" class="left_close left_open" href="javascript:void(0)"> <span></span> </a></div>
</div>

<c:forEach items="${modules}" var="module">
	<c:if test="${module.level == 1}">
		<script type="text/javascript">
			createLv2Menu('${module.parentCode}', '${module.code}', '${module.name}', '${module.icon}', '${module.accessUrl}', '${module.description}');
		</script>
	</c:if>
</c:forEach>

<c:forEach items="${modules}" var="module">
	<c:if test="${module.level == 2}">
		<script type="text/javascript">
			createLv3Menu('${module.parentCode}', '${module.code}', '${module.name}', '${module.icon}', '${module.accessUrl}', '${module.description}');
		</script>
	</c:if>
</c:forEach>

<c:forEach items="${modules}" var="module">
	<c:if test="${module.level == 3}">
		<script type="text/javascript">
			createLv4Menu('${module.parentCode}', '${module.code}', '${module.name}', '${module.icon}', '${module.accessUrl}', '${module.description}');
		</script>
	</c:if>
</c:forEach>


<script type="text/javascript">

	$(function(){
		var ifm = document.getElementById("core_iframe");
	    $(".lef_menu").on("click", " li a", function(event){
	        if($(this).parent().siblings().children("a").children().filter(".fa-minus")){
	            $(this).parent().siblings().children("a").children().filter(".fa-minus").removeClass("fa-minus").addClass("fa-plus");
	        } 
	        $(this).parent().siblings().children("ul").stop().slideUp(300);
	        $(this).next().slideToggle(700);
	        if($(this).children().eq(2).hasClass("fa-minus")){
	            $(this).children().eq(2).removeClass("fa-minus").addClass("fa-plus");
	        } else{
	            $(this).children().eq(2).removeClass("fa-plus").addClass("fa-minus");
	        }
	        
	        var $this = $(this);
			var url = $this.parent().attr("data-url");
			var moduleId = $this.parent().attr("data-menu-id");
			
			toAccessUrl(url, moduleId, ifm);
			
			event.stopPropagation();
	    });
	    
	    $(".lef_menu").on("click", ".navigation>li>a", function(){
	    	$(".navigation>li").removeClass("active");
	        $(this).parent().addClass("active");
	    });
	    
	    $(".lef_menu").on("click", ".navigation>li>ul a", function(){
	    	
	    	$(".navigation>li>ul a").removeClass("blue_1");
	        $(this).addClass("blue_1");
	    });
	    
	    var menus = $(".panel-body li");
	    $.each(menus, function(index, value) {
	    	var $this = $(value);
	    	if($this.find("ul").length > 0) {
	    		$this.find("a:first").append("<i class='fa fa-plus'></i>");
	    	}
	    });
	    
	});
	
	/* var h=$(parent.window).height()-76;
	$(function(){
		$(".leftbar").css("height",h);
	}) ; */
	$(document).ready(function() {  
		$(".leftbar").niceScroll({"styler":"fb","cursorcolor":"#BDBDBD","cursorwidth":"5"});
		var h = document.documentElement.clientHeight;
		$(".left_mu").css("height",h);
	}); 

</script>
